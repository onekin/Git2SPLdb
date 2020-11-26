package org.onekin.customdiff.miner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.spldomain.*;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.onekin.customdiff.utils.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BaselineMiner {

    private static final Logger logger = LogManager.getLogger(BaselineMiner.class);


    public CoreAssetBaseline mine(String baselineToMine, boolean secondRelease, SPL spl, String pathToWhereCustomizationsAreComputed, List<Feature> allFeatures) {// CoreAssetBaseline baseline, String
        // pathToWhereCustomizationsAreComputed
        logger.info("Mining baseline : " + baselineToMine);
        RevCommit baselineCommit = RefUtils.getCommitFromRefName(baselineToMine);
        CoreAssetBaseline baseline = new CoreAssetBaseline(baselineCommit, baselineCommit.getCommitTime(),
                baselineToMine);
        spl.addBaseline(baseline);

        // This is JUST FOR ONE BASELINE
        List<SourceCodeFile> files = extractCoreAssetsAndFeaturesFromBaseline(baseline, pathToWhereCustomizationsAreComputed, secondRelease, pathToWhereCustomizationsAreComputed, allFeatures);

        logger.info("Number of files in Baseline: " + files.size());
        baseline.setCoreAssetFiles(files);


        /** Printing baseline contents **/
        logger.info("Features in baseline: " + baseline.getId() + " are:" + baseline.getFeatures().toString());
        Iterator<Feature> it = baseline.getFeatures().iterator();
        Feature f;
        while (it.hasNext()) {
            f = it.next();
            logger.info(f.getName() + " with parent-->" + f.getParentFeatureName());
        }

        setFeatureSiblingInEachFeature(baseline);

        if (!secondRelease) {
            Utils.resetAssetId();
            Utils.resetVpId();
        }
        return baseline;
    }

    private void setFeatureSiblingInEachFeature(CoreAssetBaseline baseline) {
        for (Feature feature : baseline.getFeatures()) {
            feature.setFeatureSiblings(baseline.getFeatureSiblings().stream().filter(x -> x.getFeatures().contains(feature.getIdFeature())).collect(Collectors.toSet()));
        }
    }

    public List<SourceCodeFile> extractCoreAssetsAndFeaturesFromBaseline(CoreAssetBaseline baseline, String inPath,
                                                                         boolean secondRelease, String pathToWhereCustomizationsAreComputed, List<Feature> allFeatures) {

        ArrayList<SourceCodeFile> files = new ArrayList<>();

        RevCommit revCommit = baseline.getRevCommit();
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            logger.info("Commit: " + revCommit.getId().getName());
            Git git = new Git(repo);
            git.checkout().setName(revCommit.getId().getName()).call();

            RevWalk walk = new RevWalk(repo);
            RevCommit commit = walk.parseCommit(revCommit.getId());
            RevTree tree = commit.getTree();

            TreeWalk treeWalk = new TreeWalk(repo);
            treeWalk.addTree(tree);
            treeWalk.setRecursive(true); // FALSE

            SourceCodeFile caFile;
            String fileContent;

            while (treeWalk.next()) {
                if (treeWalk.getPathString().contains(inPath)) {
                    fileContent = FileUtils.readFileContentFromRepository(
                            CustomDiff.repositoryPath + "/" + treeWalk.getPathString());

                    // 1: create new core asset file
                    caFile = new CoreAssetFileAnnotated(Utils.getNewAssetId(),
                            treeWalk.getNameString(), treeWalk.getPathString(), fileContent,
                            fileContent.split("\n").length, baseline,
                            pathToWhereCustomizationsAreComputed.concat(treeWalk.getPathString()
                                    .split(pathToWhereCustomizationsAreComputed)[1]));

                    // 2: extract features and variation points for the core asset
                    caFile.setFeatureToCodeMapping(VariationPointAnalysisUtils
                            .extractFeaturesAndVPsForCoreAsset(caFile, baseline, secondRelease, allFeatures));

                    files.add(caFile);
                    logger.info(caFile.toString());
                    logger.info(caFile.getFeatureToCodeMapping());
                    logger.info(baseline.getId() + " " + baseline.getReleaseDate() + " " + caFile.getFileName()
                            + " " + caFile.getFeatureToCodeMapping());

                    // 3: Add features found in the core asset to the baseline
                    List<Feature> features = caFile.getFeatureList();
                    Iterator<Feature> iterator = features.iterator();
                    Feature feature;
                    // 4: get their parents!!
                    String parent = "";
                    while (iterator.hasNext()) {
                        feature = iterator.next();
                        if (!FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(),
                                feature.getName())) {
                            parent = FeatureModelParser
                                    .getParentFeatureOfFeatureByName(feature.getName());
                            feature.setParentFeatureName(parent);
                            baseline.addFeature(feature);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return files;

    }

}
