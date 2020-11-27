package org.onekin.customdiff.diffparser;

import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.CustomDiffProducts;
import org.onekin.customdiff.blame.BlamedLine;
import org.onekin.customdiff.spldomain.*;
import org.onekin.customdiff.utils.*;


public class DiffParserProducts {
    private static final Logger logger = LogManager.getLogger(DiffParserProducts.class);

    private List<DiffBlock> diffBlocks;
    private List<CustomDiffBlock> customDiffBlocks; //by variation point
    private String fullDiff;
    private ProductRelease pr;
    private String path; //pa path
    private Modification modification;
    private String linesNoHeader;
    private boolean isNewAsset;


    public DiffParserProducts(Modification mod, ProductRelease pr, SPL spl) {//(String fullDiff, ProductRelease pr, String path) {
        this.fullDiff = mod.getDiff();//fullDiff;
        diffBlocks = new ArrayList<DiffBlock>();
        this.pr = pr;

        this.path = mod.getNewPath();// always New path;
        System.out.println("NEW PATH" + path);

        this.modification = mod;
        extractDiffBlocks();
        if (mod.getOldPath() == null) {
            System.out.println("A modification NEW ASSET");
            this.isNewAsset = true;
        } else this.isNewAsset = false;

        extractCustomDiffBlocks(spl);/** Parse blocks for customDiff*/

    }


    private void extractDiffBlocks() {
        String[] lines = fullDiff.replace("\r", "").split("\n");
        String[] linesNoHeader = Arrays.copyOfRange(lines, 4, lines.length);
        this.linesNoHeader = lines[0] + "\n" + lines[1] + "\n" + lines[2] + "\n" + lines[3];
        StringBuilder currentDiff = new StringBuilder();
        boolean currentInADiff = false;

        for (int i = 0; i < linesNoHeader.length; i++) {
            String currentLine = linesNoHeader[i];
            if (currentLine.startsWith("@@ -") && !currentInADiff) {
                currentInADiff = true;
            } else if (currentLine.startsWith("@@ -") && currentInADiff) {
                diffBlocks.add(new DiffBlock(currentDiff.toString()));
                currentDiff = new StringBuilder();
                currentInADiff = false;
                i--;
            }

            if (currentInADiff) currentDiff.append(currentLine + "\n");
        }
        diffBlocks.add(new DiffBlock(currentDiff.toString()));

    }

    /**
     * Leticia Montalvillo
     **/
    public void extractCustomDiffBlocks(SPL spl) {
        customDiffBlocks = new ArrayList<CustomDiffBlock>();
        int numberOfBlock = getBlocks().size(); //how many blocks in the "normal" diff
        System.out.println("Number of blocks to parse are: " + numberOfBlock);

        for (int i = 0; i < numberOfBlock; i++) {
            customDiffBlocks.addAll(parseBlockToExtractCustomDiffBlocks(getBlocks().get(i), spl));
        }
    }


    private ArrayList<CustomDiffBlock> parseBlockToExtractCustomDiffBlocks(DiffBlock diffBlock, SPL spl) {

        System.out.println("Parsing diff block diff \n" + diffBlock.getContent());
        ArrayList<CustomDiffBlock> customDiffBlocks = new ArrayList<CustomDiffBlock>();

        String header = diffBlock.getLines()[0];//("@@ -(\\d*),(\\d*) \\+(\\d*),(\\d*) @@.*");
        int diff_lineCounter = 0;
        int totalDeletedLines = 0, total_addedLines = 0, total_contextLines = 0;//For d1 and d3
        int diff_contextLines = 0, diff_deletedlines = 0, diff_addedLines = 0;//for d2 and d4
        int d1 = diffBlock.getD1();//2
        int d3 = diffBlock.getD3();//2
        int d2 = diffBlock.getD2(), d4 = diffBlock.getD4();
        String[] lines = diffBlock.getLines();

        int startLineNextblock = 1;//the start diff line for the next block
        boolean anyVP = false;

        for (String line : lines) {
            if (line.startsWith("-") && (!line.contains(CustomDiff.annotationPatternBeginning)) && (!(line.contains(CustomDiff.annotationPatternEnd)))) {
                diff_deletedlines++;
                totalDeletedLines++;
            } else if (line.startsWith("+") && !(line.contains(CustomDiff.annotationPatternBeginning)) && (!(line.contains(CustomDiff.annotationPatternEnd)))) {
                diff_addedLines++;
                total_addedLines++;
            } else // context line where there is a VP, or added/deleted VP
                if (line.contains(CustomDiff.annotationPatternBeginning) || line.contains(CustomDiff.annotationPatternEnd)) {

                    diff_contextLines++;
                    d2 = diff_contextLines + diff_deletedlines;
                    d4 = diff_contextLines + diff_addedLines;

                    /** Create a new custom Diff block! **/
                    customDiffBlocks.add(createCustomDiffBlock(diffBlock, startLineNextblock, diff_lineCounter - 1, true, d1, d2, d3, d4, spl));

                    //headers for the next diff block
                    d1 = diffBlock.getD1() + total_contextLines + totalDeletedLines;// + diff_deletedlines;
                    d3 = diffBlock.getD3() + total_contextLines + total_addedLines;//total_addedLines - totalDeletedLines;

                    startLineNextblock = diff_lineCounter;
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
        }//end for

        if (anyVP == false) {// TODO if the diff did not contain any variation point start or end; in it.It goes directly without parsing it
            customDiffBlocks.add(createCustomDiffBlock(diffBlock, startLineNextblock, lines.length - 1, false, diffBlock.getD1(),
                    diffBlock.getD2(), diffBlock.getD3(), diffBlock.getD4(), spl));
        }
        return customDiffBlocks;
    }


    /**
     * Custom Diff: per variation point. With expression in the header & with authors name in changes
     **/
    private CustomDiffBlock createCustomDiffBlock(DiffBlock diffBlock, int startLine, int endline, boolean fixHeader, int d1, int d2, int d3, int d4, SPL spl) {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        ArrayList<RevCommit> commits = new ArrayList<RevCommit>();
        ArrayList<String> messages = new ArrayList<String>();
        CustomizationType type;

        System.out.println("New chunk! From line " + startLine + " to line:" + endline);

        ArrayList<String> newDiff = new ArrayList<>();
        String[] originalDiff = diffBlock.getLines();
        System.out.println("1");
        //if (fixHeader) /** 1: Add the new header with lines fixed **/
        newDiff.add("@@ -" + d1 + "," + d2 + " +" + d3 + "," + d4 + " @@ ");

        /** 2: blame added and deleted lines **/ //add initial context lines and final context lines. TODO

        System.out.println("2");
        newDiff = blameChangedLines(diffBlock, startLine, endline, developers, commits, messages, newDiff, originalDiff);
        System.out.println("3");

        if (diffBlockHasModifications(newDiff) == false)
            return null; //there were no modifications in this chunk - it was part of the context

        System.out.println("4");
        SourceCodeFile coreAsset = null;
        SourceCodeFile paModified = FileUtils.getProductAssetByFilePath(path, pr);
        System.out.println("5");
        if (!isNewAsset) coreAsset = FileUtils.getCoreAssetByProductAssetPath(path, pr);

        System.out.println("paModified: " + paModified.getFileName());


        /** 3: extract variation points for the customization **/
        //Variation point changed -- On product asset
        VariationPoint vp_pa = FeatureAnalysisUtils.getVariationPointOfChangedProductAssetLine(
                paModified.getRelativePath(),
                pr, d3);
        //Variation point changed -- On core asset asset
        VariationPoint vp_ca = null;
        if (paModified.getIsNewAsset() == false)
            vp_ca = FeatureAnalysisUtils.getVariationPointOfChangedCoreAssetLine(paModified.getRelativePath(), d1, spl);//;startLine+diffBlock.getD1());


        /**Create intermediary VPs for changes outside a VP*/
        //REMOVE THOSE
        if (vp_ca == null && paModified.getIsNewAsset() == false) {
            vp_ca = new VariationPoint(Utils.getVPId(), "No Expression", 0, null);
            coreAsset.addVariationPoint(vp_ca);

        }
        if (vp_pa == null) {
            vp_pa = new VariationPoint(Utils.getVPId(), "No Expression", 0, null);
            System.out.println("new vp_pa " + vp_pa.getIdVP() + "for pa: " + paModified);
            paModified.addVariationPoint(vp_pa);
        }

        /** 4: elucidate change type **/
        System.out.println("extractChangeTypeFromDiff for " + paModified.getFileName());
        type = extractChangeTypeFromDiff(newDiff, vp_ca, vp_pa, paModified.getIsNewAsset());

        /** 5: create diff header change type **/
        String diffHeader = "Expression: " + getHeaderForVariationPoint(vp_pa) + "Change Type: " + type;
        newDiff.set(0, newDiff.get(0).concat(diffHeader));//.concat("\n"+developerdHeader).concat("\nCommit messages: "+messages.toString()));//add VP expression to header

        /** 6: create a new Diff Block  **/
        return new CustomDiffBlock(linesNoHeader + "\n" + arrayListToString(newDiff), vp_pa, vp_ca, diffHeader, paModified, coreAsset, commits, new HashSet<>(developers), messages, type);

    }


    private CustomizationType extractChangeTypeFromDiff(ArrayList<String> newDiff, VariationPoint vca, VariationPoint vpa, boolean isNewAsset) {
        Iterator<String> it = newDiff.iterator();
        CustomizationType type = null;

        boolean addPvInit = false, deletePvInit = false,
                addPVEnd = false, deletePVEnd = false;

        String line;

        //changes for new assetisNewAsset
        System.out.println("NEW_ASSET:" + isNewAsset);
        if (isNewAsset == true) {
            System.out.println("this is a NEW_ASSET");

            if (vpa.getExpression().equals("No Expression")) return CustomizationType.NEW_ASSET_WITH_NO_VARIATIONPOINT;
            else if (vpa.getNewFeatures() != null)
                return CustomizationType.NEW_ASSET_WITH_NEW_VARIATIONPOINT_AND_NEW_FEATURES;
            else return CustomizationType.NEW_ASSET_WITH_VARIATIONPOINTS;
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

        if (addPvInit == false && deletePvInit == false && !vpa.getExpression().equals("No Expression"))
            type = CustomizationType.CHANGE_IN_VARIATION_POINT_BODY;
        else if (addPvInit == false && deletePvInit == false && vpa.getExpression().equals("No Expression"))
            type = CustomizationType.CHANGE_OUTSIDE_VARIATION_POINT;
        else if (addPvInit == true && deletePvInit == false && vpa.getNewFeatures() == null)
            type = CustomizationType.NEW_VARIATION_POINT;
        else if (addPvInit == true && deletePvInit == false && vpa.getNewFeatures() != null)
            type = CustomizationType.NEW_VARIATION_POINT_WITH_NEW_FEATURES;
        else if (addPvInit == true && deletePvInit == true && vpa.getNewFeatures() == null)
            type = CustomizationType.MODIFIED_VARIATION_POINT_EXPRESSION;
        else if (addPvInit == true && deletePvInit == true && vpa.getNewFeatures() != null)
            type = CustomizationType.MODIFIED_VARIATION_POINT_EXPRESSION_WITH_NEWFEATURES;
        else if (addPvInit == false && deletePvInit == true) type = CustomizationType.REMOVE_VARIATION_POINT;


        return type;
    }


    private ArrayList<String> blameChangedLines(DiffBlock diffBlock, int startLine, int endline, ArrayList<Developer> developers,
                                                ArrayList<RevCommit> commits, ArrayList<String> messages, ArrayList<String> newDiff, String[] originalDiff) {
        int newFilelineCounter = startLine + diffBlock.getD3() - 2;//TODO startLine+diffBlock.getD3()-1
        String strblame;
        String commit = null;
        String author = null, message = null;
        String developerdHeader = "Developed by:";
        try {


            for (int i = startLine; i <= endline; i++) {// 1: annotate the added and deleted lines with BLAME
                strblame = "";
                if (originalDiff[i].contains("No newline at end of file")) continue;
                if (originalDiff[i].startsWith("+")) {// 1.1: annotate the added and deleted lines with BLAME
                    List<BlamedLine> blames = modification.getBlameLines();
                    if (blames == null) continue;
                    author = blames.get(newFilelineCounter).getAuthor();
                    int line = blames.get(newFilelineCounter).getLineNumber();
                    commit = blames.get(newFilelineCounter).getCommit();
                    String lineAdded = blames.get(newFilelineCounter).getLine();
                    developerdHeader = developerdHeader.concat(author + " ");

                    if (!commits.contains(RefUtils.getCommitFromCommitSha(commit))) {
                        message = RefUtils.getCommitMessage(commit);
                        if (!messages.contains(message)) messages.add(message);
                    }
                    strblame = "//Authored by: " + author + " in commit:" + commit + ", with message:" + message;
                    commits.add(RefUtils.getCommitFromCommitSha(commit));
                    developers.add(addAuthor(RefUtils.getCommitAuthor(commit)));
                }
                newDiff.add(originalDiff[i] + strblame);//add line to the customDiffBlock

                if (!originalDiff[i].startsWith("-")) {// 1.2: annotate the deleted lines with BLAME --REVERSE TODO
                    newFilelineCounter++;
                }
            }//end for
        } catch (IndexOutOfBoundsException e){
            logger.error(e);
        }
        return newDiff;
    }


    private Developer addAuthor(PersonIdent author) {
        Developer dev;
        Iterator<Developer> it = CustomDiffProducts.allDevelopers.iterator();
        while (it.hasNext()) {
            dev = it.next();
            if (dev.getName().equals(author.getName()))
                return dev;
        }
        dev = new Developer(Utils.getDeveloperId(), author.getName(), author.getEmailAddress());
        CustomDiffProducts.allDevelopers.add(dev);

        return dev;
    }

    private String getHeaderForVariationPoint(VariationPoint vp) {
        if (vp == null) return "No VP";

        String header = vp.getExpression().replace("PV:IFCOND", "");
        //header = header.replace(" ", "");
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


    public List<DiffBlock> getBlocks() {//hunks
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