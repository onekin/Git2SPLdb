package customDiff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.CoreAssetFileAnnotated;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.utils.Utils;

public class BaselineMiner {

    public CoreAssetBaseline mine(String baselineToMine, boolean secondRelease) {// CoreAssetBaseline baseline, String
        // pathToWhereCustomizationsAreComputed
        System.out.println("Mining baseline : " + baselineToMine);
        RevCommit baselineCommit = customDiff.utils.RefUtils.getCommitFromRefName(baselineToMine);
        CoreAssetBaseline baseline = new CoreAssetBaseline(baselineCommit, baselineCommit.getCommitTime(),
                baselineToMine);
        CustomDiff.spl.addBaseline(baseline);

        // This is JUST FOR ONE BASELINE
        System.out.println("Reading files from baseline ");
        ArrayList<SourceCodeFile> files = extractCoreAssetsAndFeaturesFromBaseline(baseline,
                CustomDiff.pathToWhereCustomizationsAreComputed, secondRelease);

        System.out.println("Number of files in Baseline: " + files.size());
        baseline.setCoreAssetFiles(files);
        /**
         * adding root feature *
         */
        /*String rootName = customDiff.utils.FeatureModelParser.getFMRootElement();
		Feature root = new Feature(rootName, rootName, false, null);
		baseline.getFeatures().add(root);/*

		/** Printing baseline contents **/
        System.out.println("Features in baseline: " + baseline.getId() + " are:" + baseline.getFeatures().toString());
        Iterator<Feature> it = baseline.getFeatures().iterator();
        Feature f;
        while (it.hasNext()) {
            f = it.next();
            System.out.println(f.getName() + " with parent-->" + f.getParentFeatureName());
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

    public ArrayList<SourceCodeFile> extractCoreAssetsAndFeaturesFromBaseline(CoreAssetBaseline baseline, String inPath,
            boolean secondRelease) {
        System.out.println("Extract CoreAssets And Features From Baseline " + baseline.getId());
        ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();

        try {
            RevCommit revCommit = baseline.getRevCommit();
            Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath + "/.git");
            System.out.println("Commit: " + revCommit.getId().getName());
            Git git = new Git(repo);
            git.checkout().setName(revCommit.getId().getName()).call();

            RevWalk walk = new RevWalk(repo);
            RevCommit commit = walk.parseCommit(revCommit.getId());
            RevTree tree = commit.getTree();

            TreeWalk treeWalk = new TreeWalk(repo);
            treeWalk.addTree(tree);
            treeWalk.setRecursive(true); // FALSE

            SourceCodeFile CAfile;
            String fileContent;

            while (treeWalk.next()) {

                try {
                    if (treeWalk.getPathString().contains(inPath)) {
                        fileContent = customDiff.utils.FileUtils.readFileContentFromRepository(
                                customDiff.CustomDiff.repositoryPath + "/" + treeWalk.getPathString());
                        // 1: create new core asset file
                        CAfile = new CoreAssetFileAnnotated(customDiff.utils.Utils.getNewAssetId(),
                                treeWalk.getNameString(), treeWalk.getPathString(), fileContent,
                                fileContent.split("\n").length, baseline,
                                customDiff.CustomDiff.pathToWhereCustomizationsAreComputed.concat(treeWalk.getPathString()
                                        .split(customDiff.CustomDiff.pathToWhereCustomizationsAreComputed)[1]));

                        // 2: extract features and variation points for the core asset
                        CAfile.setFeatureToCodeMapping(customDiff.utils.VariationPointAnalysisUtils
                                .extractFeaturesAndVPsForCoreAsset(CAfile, baseline, secondRelease));

                        files.add(CAfile);
                     //   System.out.println(CAfile.getFileName());
                     //   System.out.println(CAfile.getFeatureToCodeMapping());
                     //   System.out.println(baseline.getId() + " " + baseline.getReleaseDate() + " " + CAfile.getFileName()
                     //           + " " + CAfile.getFeatureToCodeMapping());

                        // 3: Add features found in the core asset to the baseline
                        ArrayList<Feature> features = CAfile.getFeatureList();
                        Iterator<Feature> iterator = features.iterator();
                        Feature feature;
                        // 4: get their parents!!
                        String parent = "";
                        while (iterator.hasNext()) {
                            feature = iterator.next();
                            if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(),
                                    feature.getName())) {
                                //parent = customDiff.utils.FeatureModelParser
                                //		.getParentFeatureOfFeatureByName(feature.getName());
                                feature.setParentFeatureName(parent);
                                baseline.addFeature(feature);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            repo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return files;

    }

}
