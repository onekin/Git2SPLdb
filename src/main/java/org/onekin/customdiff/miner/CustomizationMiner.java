package org.onekin.customdiff.miner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.spldomain.SPL;
import org.onekin.customdiff.utils.Utils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.onekin.customdiff.diffparser.CustomDiffBlock;
import org.onekin.customdiff.diffparser.DiffParser;
import org.onekin.customdiff.diffparser.Modification;
import org.onekin.customdiff.diffparser.ModificationType;
import org.onekin.customdiff.spldomain.CoreAssetBaseline;
import org.onekin.customdiff.spldomain.Customization;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomizationMiner {

    private static final Logger logger = LogManager.getLogger(CustomizationMiner.class);
    CoreAssetBaseline baseline2;
    ArrayList<Customization> customizations;

    private static final int MAX_SIZE_OF_DIFF = 100000000; //TODO Expose an API to control this value?

    public void mine(CoreAssetBaseline baseline, CoreAssetBaseline baseline2, SPL spl) {
        logger.info("------ Mining Product Customizations for " + baseline2.getId() + "------");
        logger.info("--------------------------------------------------------------------");
        this.baseline2 = baseline2;
        customizations = new ArrayList<>();
        RevCommit baselineTag = baseline.getRevCommit();

        // 1: compute the Git diff between productrelease commit and the baseline it was
        // derived from.
        List<Modification> modifications = getDiffsBetweenCommits(baselineTag, baseline2.getRevCommit());

        // 2: for each diff, call the parser-> parse diffs per VP changed.
        Iterator<Modification> it = modifications.iterator();
        Modification mod;
        while (it.hasNext()) {
            mod = it.next();// 4: for each modifications parse and extract the customDiff

            if (mod.getNewPath().startsWith(CustomDiff.pathToWhereCustomizationsAreComputed)) {
                customizations.addAll(computeCustomizationsInModification(mod, baseline2,spl));
                logger.info("ADDDE CUSTOM " + mod);
            }

        }

        baseline2.setCustomizations(customizations);
    }



    private List<Customization> computeCustomizationsInModification(Modification m, CoreAssetBaseline baseline22, SPL spl) {

        ArrayList<Customization> customizationList = new ArrayList<>();

        // the DiffParser is in charge of parsing the diff into customDiffs
        DiffParser parsedDiff = new DiffParser(m, baseline22,spl);

        if (parsedDiff.getCustomDiffBlocks() == null || parsedDiff.getCustomDiffBlocks().isEmpty())
            return customizationList;

        int numberOfCustomDiffBlocks = parsedDiff.getCustomDiffBlocks().size();
        int counter = 0;

        while (counter < numberOfCustomDiffBlocks) {
            CustomDiffBlock custom = parsedDiff.getCustomDiffBlocks().get(counter);
            if (custom != null) {
                /** Create Customization Facts **/
                Customization customFact = new Customization(Utils.getNewCustomizationId(), custom.getAddedlines(),
                        custom.getDeteledlines(), m.getDiff(), custom.getIsNewAsset(), false, baseline22, custom);
                // pr.getFileByPath(m.getNewPath()).getIsNew(),
                // pr.getFileByPath(m.getNewPath()).getHasNewFeature()
                customFact.toString();
                customizationList.add(customFact);
            }
            counter++;
        }
        return customizationList;
    }

    private List<Modification> getDiffsBetweenCommits(RevCommit oldCommit, RevCommit newCommit) {


        List<Modification> modifications = new ArrayList<>();

        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            DiffFormatter df = new DiffFormatter(new ByteArrayOutputStream());
            Git git = new Git(repo);

            try (ObjectReader reader = git.getRepository().newObjectReader()) {
                CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
                ObjectId oldTree = oldCommit.getTree();
                oldTreeIter.reset(reader, oldTree);

                CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
                ObjectId newTree = newCommit.getTree();
                newTreeIter.reset(reader, newTree);

                df.setRepository(git.getRepository());

                // 1: get the diff entries for commits.
                List<DiffEntry> diffs = df.scan(oldTreeIter, newTreeIter);

                // 2: Convert each of the associated DiffEntry's (of a product release) to a
                // Modification!
                for (DiffEntry diff : diffs) {
                    ModificationType change = Enum.valueOf(ModificationType.class, diff.getChangeType().toString());

                    String oldPath = diff.getOldPath();
                    String newPath = diff.getNewPath();

                    String diffText = "";
                    String sc = "";
                    if (diff.getChangeType() != ChangeType.DELETE) {
                        diffText = getDiffText(repo, diff);
                        sc = getSourceCode(repo, diff);
                    }

                    if (diffText.length() > MAX_SIZE_OF_DIFF) {
                        logger.info("diff for " + newPath + " too big");
                        diffText = "-- TOO BIG --";
                    }
                    modifications.add(new Modification(oldPath, newPath, change, diffText, sc, oldCommit, newCommit));

                }
                return modifications;
            } catch (IOException e) {
                logger.error(e);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return new ArrayList<>();
    }

    private String getSourceCode(Repository repo, DiffEntry diff) {
        try (ObjectReader reader = repo.newObjectReader()) {
            byte[] bytes = reader.open(diff.getNewId().toObjectId()).getBytes();
            return new String(bytes, "utf-8");
        } catch (IOException e) {
            logger.error(e);
            return "";
        }
    }

    private String getDiffText(Repository repo, DiffEntry diff) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            DiffFormatter df2 = new DiffFormatter(out);
            String diffText;
            df2.setRepository(repo);
            df2.format(diff);
            diffText = out.toString("UTF-8");
            return diffText;
        } catch (IOException e) {
            return "";
        }
    }

}
