package org.onekin.customdiff.diffparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.blame.BlamedLine;
import org.onekin.customdiff.spldomain.*;
import org.onekin.customdiff.utils.FeatureAnalysisUtils;
import org.onekin.customdiff.utils.FileUtils;
import org.onekin.customdiff.utils.RefUtils;
import org.onekin.customdiff.utils.Utils;

import java.util.*;

public class DiffParser {

    private List<DiffBlock> diffBlocks;
    private List<CustomDiffBlock> customDiffBlocks; // by variation point
    private String fullDiff;
    private CoreAssetBaseline baseline2;
    private String path; // pa path
    private Modification modification;
    private String linesNoHeader;
    private boolean isNewAsset;

    private static final Logger logger = LogManager.getLogger(DiffParser.class);

    private static final String NO_EXPRESSION = "No Expression";

    public DiffParser(Modification mod, CoreAssetBaseline baseline2, SPL spl) {
        this.fullDiff = mod.getDiff();
        diffBlocks = new ArrayList<>();
        this.baseline2 = baseline2;

        this.path = mod.getNewPath();
        logger.debug("NEW PATH {}", path);

        this.modification = mod;
        extractDiffBlocks();
        if (mod.getOldPath() == null) {
            logger.debug("A modification NEW ASSET");
            this.isNewAsset = true;
        } else {
            this.isNewAsset = false;
        }

        extractCustomDiffBlocks(spl);/** Parse blocks for customDiff */

    }

    private void extractDiffBlocks() {
        String[] lines = fullDiff.replace("\r", "").split("\n");
        if (lines.length > 4) {
            String[] linesNoHeaderArray = Arrays.copyOfRange(lines, 4, lines.length);
            this.linesNoHeader = lines[0] + "\n" + lines[1] + "\n" + lines[2] + "\n" + lines[3];
            StringBuilder currentDiff = new StringBuilder();
            boolean currentInADiff = false;

            for (int i = 0; i < linesNoHeaderArray.length; i++) {
                String currentLine = linesNoHeaderArray[i];
                if (currentLine.startsWith("@@ -") && !currentInADiff) {
                    currentInADiff = true;
                } else if (currentLine.startsWith("@@ -") && currentInADiff) {
                    diffBlocks.add(new DiffBlock(currentDiff.toString()));
                    currentDiff = new StringBuilder();
                    currentInADiff = false;
                    i--;
                }

                if (currentInADiff)
                    currentDiff.append(currentLine + "\n");
            }
            diffBlocks.add(new DiffBlock(currentDiff.toString()));
        }
    }

    /**
     * Leticia Montalvillo
     **/
    public void extractCustomDiffBlocks(SPL spl) {
        customDiffBlocks = new ArrayList<CustomDiffBlock>();
        int numberOfBlock = getBlocks().size(); // how many blocks in the "normal" diff
        logger.debug("Number of blocks to parse are: {}", numberOfBlock);

        for (int i = 0; i < numberOfBlock; i++) {
            customDiffBlocks.addAll(parseBlockToExtractCustomDiffBlocks(getBlocks().get(i),spl));
        }
    }

    private ArrayList<CustomDiffBlock> parseBlockToExtractCustomDiffBlocks(DiffBlock diffBlock, SPL spl) {

        logger.debug("Parsing diff block diff {}", diffBlock.getContent());
        ArrayList<CustomDiffBlock> customDiffBlocksList = new ArrayList<CustomDiffBlock>();

        int diff_lineCounter = 0;
        int totalDeletedLines = 0, total_addedLines = 0, total_contextLines = 0;// For d1 and d3
        int diff_contextLines = 0, diff_deletedlines = 0, diff_addedLines = 0;// for d2 and d4
        int d1 = diffBlock.getD1();// 2
        int d3 = diffBlock.getD3();// 2
        int d2 = diffBlock.getD2();
        int d4 = diffBlock.getD4();
        String[] lines = diffBlock.getLines();

        int startLineNextblock = 1;// the start diff line for the next block
        boolean anyVP = false;

        for (String line : lines) {
            if (line.startsWith("-") && (!line.contains(CustomDiff.annotationPatternBeginning))
                    && (!(line.contains(CustomDiff.annotationPatternEnd)))) {
                diff_deletedlines++;
                totalDeletedLines++;
            } else if (line.startsWith("+") && !(line.contains(CustomDiff.annotationPatternBeginning))
                    && (!(line.contains(CustomDiff.annotationPatternEnd)))) {
                diff_addedLines++;
                total_addedLines++;
            } else // context line where there is a VP, or added/deleted VP
                if (line.contains(CustomDiff.annotationPatternBeginning)
                        || line.contains(CustomDiff.annotationPatternEnd)) {

                    diff_contextLines++;
                    d2 = diff_contextLines + diff_deletedlines;
                    d4 = diff_contextLines + diff_addedLines;

                    /** Create a new custom Diff block! **/
                    if (line.contains(CustomDiff.annotationPatternEnd)) {
                        customDiffBlocksList.add(createCustomDiffBlock(diffBlock, startLineNextblock, diff_lineCounter, true,
                                d1, d2, d3, d4,spl));
                        startLineNextblock = diff_lineCounter + 1;

                    } else {
                        customDiffBlocksList.add(createCustomDiffBlock(diffBlock, startLineNextblock, diff_lineCounter - 1,
                                true, d1, d2, d3, d4,spl));
                        startLineNextblock = diff_lineCounter;
                    }

                    // headers for the next diff block
                    d1 = diffBlock.getD1() + total_contextLines + totalDeletedLines;
                    d3 = diffBlock.getD3() + total_contextLines + total_addedLines;

                    diff_contextLines = 0;
                    diff_deletedlines = 0;
                    diff_addedLines = 0;
                    anyVP = true;
                    total_contextLines++;
                } else {
                    if (!line.startsWith("@@")) {
                        diff_contextLines++;
                        total_contextLines++;
                    }
                }
            diff_lineCounter++;
        } // end for

        if (anyVP == false) {// TODO if the diff did not contain any variation point start or end; in it.It
            // goes directly without parsing it
            customDiffBlocksList.add(createCustomDiffBlock(diffBlock, startLineNextblock, lines.length - 1, false,
                    diffBlock.getD1(), diffBlock.getD2(), diffBlock.getD3(), diffBlock.getD4(),spl));
        }
        return customDiffBlocksList;
    }

    /**
     * Custom Diff: per variation point. With expression in the header & with
     * authors name in changes
     **/
    private CustomDiffBlock createCustomDiffBlock(DiffBlock diffBlock, int startLine, int endline, boolean fixHeader,
                                                  int d1, int d2, int d3, int d4, SPL spl) {
        Set<Developer> developers = new HashSet<Developer>();
        ArrayList<RevCommit> commits = new ArrayList<RevCommit>();
        ArrayList<String> messages = new ArrayList<String>();
        CustomizationType type;

        logger.debug("New chunk! From line {}  to line: {}", +startLine, endline);

        ArrayList<String> newDiff = new ArrayList<>();
        String[] originalDiff = diffBlock.getLines();
        logger.debug("1");
        newDiff.add("@@ -" + d1 + "," + d2 + " +" + d3 + "," + d4 + " @@ ");

        /** 2: blame added and deleted lines **/ // add initial context lines and final context lines. TODO

        logger.debug("2");
        newDiff = blameChangedLines(diffBlock, startLine, endline, developers, commits, messages, newDiff,
                originalDiff);
        logger.debug("3");

        if (diffBlockHasModifications(newDiff) == false)
            return null; // there were no modifications in this chunk - it was part of the context*/

        logger.debug("4");
        SourceCodeFile coreAsset = null;
        SourceCodeFile paModified = FileUtils.getProductAssetByFilePath(path, baseline2);
        logger.debug("5");
        coreAsset = FileUtils.getCoreAssetByProductAssetPath(path, baseline2);


        /** 3: extract variation points for the customization **/
        // Variation point changed -- On product asset
        VariationPoint vp_pa = FeatureAnalysisUtils
                .getVariationPointOfChangedProductAssetLine(paModified.getRelativePath(), baseline2, d3);
        // Variation point changed -- On core asset asset
        VariationPoint vp_ca = null;
        if (paModified.getIsNewAsset() == false)
            vp_ca = FeatureAnalysisUtils
                    .getVariationPointOfChangedCoreAssetLine(paModified.getRelativePath(), d1,spl);

        /** Create intermediary VPs for changes outside a VP */
        // REMOVE THOSE
        if (vp_ca == null && paModified.getIsNewAsset() == false) {
            vp_ca = new VariationPoint(Utils.getVPId(), NO_EXPRESSION, 0, null, new FeatureSibling(0, "No feature"));
            if (coreAsset.getVariationPoints() == null) {
                coreAsset.setVariationPoints(new ArrayList<>());
            }
            coreAsset.addVariationPoint(vp_ca);

        }
        if (vp_pa == null) {
            vp_pa = new VariationPoint(Utils.getVPId(), NO_EXPRESSION, 0, null, new FeatureSibling(0, "No feature"));
            logger.debug("new vp_pa {} for pa: {}", vp_pa.getIdVP(), paModified);
            paModified.addVariationPoint(vp_pa);
        }

        /** 4: elucidate change type **/
        logger.debug("extractChangeTypeFromDiff for {}", paModified.getFileName());
        type = extractChangeTypeFromDiff(newDiff, vp_ca, vp_pa, paModified.getIsNewAsset());

        /** 5: create diff header change type **/
        String diffHeader = "Expression: " + getHeaderForVariationPoint(vp_pa) + "Change Type: " + type;
        newDiff.set(0, newDiff.get(0).concat(diffHeader));// .concat("\n"+developerdHeader).concat("\nCommit messages:
        // "+messages.toString()));//add VP expression to header

        /** 6: create a new Diff Block **/
        return new CustomDiffBlock(linesNoHeader + "\n" + arrayListToString(newDiff), vp_pa, vp_ca, diffHeader,
                paModified, coreAsset, commits, developers, messages, type);

    }

    private CustomizationType extractChangeTypeFromDiff(ArrayList<String> newDiff, VariationPoint vca,
                                                        VariationPoint vpa, boolean isNewAsset) {
        Iterator<String> it = newDiff.iterator();
        CustomizationType type = null;

        boolean addPvInit = false, deletePvInit = false, addPVEnd = false, deletePVEnd = false;

        String line;

        // changes for new assetisNewAsset
        logger.debug("NEW_ASSET: {}", isNewAsset);
        if (isNewAsset == true) {
            logger.debug("this is a NEW_ASSET");

            if (vpa.getExpression().equals(NO_EXPRESSION))
                return CustomizationType.NEW_ASSET_WITH_NO_VARIATIONPOINT;
            else if (vpa.getNewFeatures() != null)
                return CustomizationType.NEW_ASSET_WITH_NEW_VARIATIONPOINT_AND_NEW_FEATURES;
            else
                return CustomizationType.NEW_ASSET_WITH_VARIATIONPOINTS;
        }

        while (it.hasNext()) {
            line = it.next();

            if (line.startsWith("+") && line.contains(CustomDiff.annotationPatternBeginning))
                addPvInit = true;
            else {
                if (line.startsWith("+") && line.contains(CustomDiff.annotationPatternBeginning))
                    deletePvInit = true;
            }
        }

        if (addPvInit == false && deletePvInit == false && !vpa.getExpression().equals(NO_EXPRESSION))
            type = CustomizationType.CHANGE_IN_VARIATION_POINT_BODY;
        else if (addPvInit == false && deletePvInit == false && vpa.getExpression().equals(NO_EXPRESSION))
            type = CustomizationType.CHANGE_OUTSIDE_VARIATION_POINT;
        else if (addPvInit == true && deletePvInit == false && vpa.getNewFeatures() == null)
            type = CustomizationType.NEW_VARIATION_POINT;
        else if (addPvInit == true && deletePvInit == false && vpa.getNewFeatures() != null)
            type = CustomizationType.NEW_VARIATION_POINT_WITH_NEW_FEATURES;
        else if (addPvInit == true && deletePvInit == true && vpa.getNewFeatures() == null)
            type = CustomizationType.MODIFIED_VARIATION_POINT_EXPRESSION;
        else if (addPvInit == true && deletePvInit == true && vpa.getNewFeatures() != null)
            type = CustomizationType.MODIFIED_VARIATION_POINT_EXPRESSION_WITH_NEWFEATURES;
        else if (addPvInit == false && deletePvInit == true)
            type = CustomizationType.REMOVE_VARIATION_POINT;

        return type;
    }

    private ArrayList<String> blameChangedLines(DiffBlock diffBlock, int startLine, int endline,
                                                Set<Developer> developers, ArrayList<RevCommit> commits, ArrayList<String> messages,
                                                ArrayList<String> newDiff, String[] originalDiff) {
        int newFilelineCounter = startLine + diffBlock.getD3() - 2;// startLine+diffBlock.getD3()-1
        String strblame;
        String commit = null;
        String author = null, message = null;
        String developerdHeader = "Developed by:";
        List<BlamedLine> blames = modification.getBlameLines();
        try {
            for (int i = startLine; i <= endline; i++) {// 1: annotate the added and deleted lines with BLAME
                strblame = "";
                if (originalDiff[i].contains("No newline at end of file"))
                    continue;
                if (originalDiff[i].startsWith("+")) {// 1.1: annotate the added and deleted lines with BLAME
                    if (blames == null)
                        continue;
                    author = blames.get(newFilelineCounter).getAuthor();
                    blames.get(newFilelineCounter).getLineNumber();
                    commit = blames.get(newFilelineCounter).getCommit();
                    blames.get(newFilelineCounter).getLine();
                    developerdHeader = developerdHeader.concat(author + " ");

                    if (!commits.contains(RefUtils.getCommitFromCommitSha(commit))) {
                        message = RefUtils.getCommitMessage(commit);
                        if (!messages.contains(message))
                            messages.add(message);
                    }
                    strblame = "//Authored by: " + author + " in commit:" + commit + ", with message:" + message;
                    commits.add(RefUtils.getCommitFromCommitSha(commit));
                    developers.add(addAuthor(RefUtils.getCommitAuthor(commit)));
                }
                newDiff.add(originalDiff[i] + strblame);// add line to the customDiffBlock

                if (!originalDiff[i].startsWith("-")) {// 1.2: annotate the deleted lines with BLAME --REVERSE TODO
                    newFilelineCounter++;
                }
            } // end for
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDiff;
    }

    private Developer addAuthor(PersonIdent author) {
        Developer dev;
        Iterator<Developer> it = CustomDiff.allDevelopers.iterator();
        while (it.hasNext()) {
            dev = it.next();
            if (dev.getName().equals(author.getName()))
                return dev;
        }
        dev = new Developer(Utils.getDeveloperId(), author.getName(), author.getEmailAddress());
        CustomDiff.allDevelopers.add(dev);

        return dev;
    }

    private String getHeaderForVariationPoint(VariationPoint vp) {
        if (vp == null)
            return "No VP";

        String header = vp.getExpression().replace("PV:IFCOND", "");
        header = header.replace("//", "");
        if (vp.getParentVP() != null) {
            header = header + " ->Nested into: " + getHeaderForVariationPoint(vp.getParentVP());
        }
        return header;
    }

    private boolean diffBlockHasModifications(ArrayList<String> newDiff) {
        Iterator<String> it = newDiff.iterator();
        String aux;

        while (it.hasNext()) {
            aux = it.next();
            if (aux.startsWith("-") || aux.startsWith("+"))
                return true;

        }
        return false;
    }

    public List<DiffBlock> getBlocks() {// hunks
        return diffBlocks;
    }

    public String getFullDiff() {
        return fullDiff;
    }

    public List<CustomDiffBlock> getCustomDiffBlocks() {
        return customDiffBlocks;
    }

    public void setCustomDiffBlocks(List<CustomDiffBlock> customDiffBlocks) {
        this.customDiffBlocks = customDiffBlocks;
    }

    public String arrayListToString(ArrayList<String> array) {
        String str = "";
        for (int i = 0; i < array.size(); i++) {
            str += array.get(i) + "\n";
        }
        return str;
    }

}
