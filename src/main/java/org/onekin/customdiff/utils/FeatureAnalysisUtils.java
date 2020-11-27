package org.onekin.customdiff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.spldomain.*;

import java.util.*;

public class FeatureAnalysisUtils {

    private static final Logger logger = LogManager.getLogger(FeatureAnalysisUtils.class);
    private FeatureAnalysisUtils() {
        super();
    }

    private static Set<Feature> findFeaturesByNames(ArrayList<String> listFeatures, CoreAssetBaseline baseline) {
        Iterator<String> itNames = listFeatures.iterator();

        String name;
        Feature f;
        Set<Feature> features = new HashSet<Feature>();

        while (itNames.hasNext()) {
            name = itNames.next();
            Iterator<Feature> itFeatures = baseline.getFeatures().iterator();
            while (itFeatures.hasNext()) {
                f = itFeatures.next();
                if (f.getName().equals(name))
                    features.add(f);
            }
        }
        return features;
    }

    private static Set<Feature> findNewFeatures(ArrayList<String> listFeatures, CoreAssetBaseline baseline) {
        Iterator<String> itNames = listFeatures.iterator();

        String name;
        Feature f;
        Set<Feature> newFeatures = new HashSet<>();

        boolean match = false;

        while (itNames.hasNext()) {
            name = itNames.next();
            Iterator<Feature> itFeatures = baseline.getFeatures().iterator();
            match = false;
            while (itFeatures.hasNext()) {
                f = itFeatures.next();
                if (f.getName().equals(name)) {
                    match = true;
                    continue;
                }
            }
            if (match == false) {
                Feature newFeature = new Feature(name, name);
                newFeatures.add(newFeature);
                logger.info(" NEW FEATURE FOUND: " + name);
            }
        }
        return newFeatures;
    }

    public static boolean isFeatureInFeaturesList(List<Feature> featureList, String name) {
        Iterator<Feature> it = featureList.iterator();
        Feature f;

        while (it.hasNext()) {
            f = it.next();
            if (f.getIdFeature().equals(name)) {
                return true;
            }
        }

        return false;
    }

    private static ArrayList<String> extractAllFeaturesFromTheExpression(String expression) {
        ArrayList<String> listfeatures = new ArrayList<String>();
        String[] pieces = expression.split("'"); // Expression example //PV:IFCOND(pv:hasFeature('Fa') and
        // pv:hasFeature('FB'))
        for (int i = 0; i < pieces.length; i++) {
            if ((i / 2) * 2 != i) { // if it is odd
                listfeatures.add(pieces[i]);
            }
        }
        return listfeatures;
    }

    public static ArrayList<VariationPoint> extractVPsFromFile(SourceCodeFile file, CoreAssetBaseline baseline) {

        String content = file.getContent();
        if (!content.contains(CustomDiff.annotationPatternBeginning)) {
            return new ArrayList<>();
        }

        ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();

        HashMap<Integer, VariationPoint> currentVPsInNestingLevels = new HashMap<Integer, VariationPoint>();
        try {
            String[] lines = content.split("\n");
            int nestingLevel = -1;
            for (int i = 0; i < lines.length; i++) {
                VariationPoint vp = null;
                if (lines[i].contains(CustomDiff.annotationPatternBeginning)) {
                    nestingLevel++; // level 0 is when we are inside a VP
                    if (nestingLevel == 0)
                        vp = new VariationPoint(Utils.getVPId(), lines[i], i + 1, null);// newVariation point
                    else
                        vp = new VariationPoint(Utils.getVPId(), lines[i], i + 1,
                                currentVPsInNestingLevels.get(nestingLevel - 1));
                    vp.setFeatures(findFeaturesByNames(extractAllFeaturesFromTheExpression(lines[i]), baseline));
                    vp.setNewFeatures(findNewFeatures(extractAllFeaturesFromTheExpression(lines[i]), baseline));
                    variationPoints.add(vp);
                    currentVPsInNestingLevels.put(nestingLevel, vp);

                    logger.info("New variation Point Created " + vp.toString());
                    if (nestingLevel >= 0) { // we need to add the body to all nested VPs
                        for (int k = 0; k <= nestingLevel; k++)// the line needs to go to parent VPs as well.
                            currentVPsInNestingLevels.get(k)
                                    .setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n" + lines[i]));
                    }
                } else {
                    if (lines[i].contains(CustomDiff.annotationPatternEnd)) {
                        // add the line to all parents of this VP
                        for (int k = 0; k <= nestingLevel; k++)// the line needs to go to parent VPs as well.
                            currentVPsInNestingLevels.get(k)
                                    .setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n" + lines[i]));
                        // calculate ending Line for the VP
                        currentVPsInNestingLevels.get(nestingLevel).setLineEnd(i + 1);
                        nestingLevel--;
                    } else { // no pattern has been found; we might be inside VPs (nested or not) or inside
                        // no VP at all
                        if (nestingLevel >= 0)
                            for (int k = 0; k <= nestingLevel; k++)
                                currentVPsInNestingLevels.get(k)
                                        .setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n" + lines[i]));
                    }
                }
            }
            file.setVariationPoints(variationPoints);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return variationPoints;
    }

    public static VariationPoint getVariationPointOfChangedProductAssetLine(String relPath, CoreAssetBaseline baseline2,
                                                                            int lineNumber) {
        SourceCodeFile pa = FileUtils.getProductAssetByFilePath(relPath, baseline2);
        VariationPoint vp, keyVP = null;

        List<VariationPoint> listVPs = pa.getVariationPoints();
        if (listVPs == null)
            return null;
        Iterator<VariationPoint> it = listVPs.iterator();

        while (it.hasNext()) {
            vp = it.next();
            if ((vp.getLineInit() <= lineNumber) && (lineNumber <= vp.getLineEnd())) {
                keyVP = vp;
            }

        }

        return keyVP;
    }

    public static VariationPoint getVariationPointOfChangedCoreAssetLine(String relPath, int lineNumber, SPL spl) {
        SourceCodeFile ca = FileUtils.getCoreAssetByFilePath(relPath,  spl);

        VariationPoint vp, keyVP = null;

        List<VariationPoint> listVPs = ca.getVariationPoints();
        if (listVPs != null) {
            Iterator<VariationPoint> it = listVPs.iterator();

            while (it.hasNext()) {
                vp = it.next();
                if ((vp.getLineInit() <= lineNumber) && (lineNumber <= vp.getLineEnd())) {
                    keyVP = vp;
                }
            }
        } else {
            ca.setVariationPoints(new ArrayList<>());
        }
        return keyVP;
    }

    public static HashMap<Integer, ArrayList<String>> extractFeatureMapFromFile(SourceCodeFile file) {// Read the file lineByLine

        HashMap<Integer, ArrayList<String>> featureToCodeMapping = new HashMap<>();
        String content = file.getContent();
        int nestingLevel = -1;
        if (!content.contains(CustomDiff.annotationPatternBeginning))
            return null; // the file does not contain variability in it

        try {
            String[] lines = content.split("\n");
            int counter = 0;
            ArrayList<String> listFeatures = new ArrayList<String>();
            HashMap<Integer, String> currentExpressionsInNestedLevels = new HashMap<Integer, String>();// pair of
            // nesting
            // level, and
            // expression

            for (int i = 0; i < lines.length; i++) {
                counter++;
                if (lines[i].contains(CustomDiff.annotationPatternBeginning)) {
                    nestingLevel++;// level 0 is when we are inside a VP
                    currentExpressionsInNestedLevels.put(nestingLevel, lines[i]);
                    if (nestingLevel > 0) {// we need to add all the features for all the nesting levels.
                        listFeatures = new ArrayList<String>();
                        for (int k = 0; k <= nestingLevel; k++)
                            listFeatures.addAll(
                                    extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
                    } else {
                        listFeatures = extractAllFeaturesFromTheExpression(
                                currentExpressionsInNestedLevels.get(nestingLevel));
                    }
                } else {
                    if (nestingLevel == -1) {
                        listFeatures = new ArrayList<String>();
                    }
                    if (nestingLevel == 0) {
                        listFeatures = extractAllFeaturesFromTheExpression(
                                currentExpressionsInNestedLevels.get(nestingLevel));
                    }
                    if (nestingLevel > 0) {
                        listFeatures = new ArrayList<String>();
                        for (int k = 0; k <= nestingLevel; k++)
                            listFeatures.addAll(
                                    extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
                    }
                } // end else
                featureToCodeMapping.put(counter, listFeatures);
                if (lines[i].contains(CustomDiff.annotationPatternEnd))
                    nestingLevel--;
            } // end for
            file.setFeatureToCodeMapping(featureToCodeMapping);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return featureToCodeMapping;
    }


    public static VariationPoint getVariationPointOfChangedProductAssetLine(String relPath, ProductRelease productRelease,
                                                                            int lineNumber) {
        SourceCodeFile pa = FileUtils.getProductAssetByFilePath(relPath, productRelease);
        VariationPoint vp, keyVP = null;

        List<VariationPoint> listVPs = pa.getVariationPoints();
        if (listVPs == null)
            return null;
        Iterator<VariationPoint> it = listVPs.iterator();

        while (it.hasNext()) {
            vp = it.next();
            if ((vp.getLineInit() <= lineNumber) && (lineNumber <= vp.getLineEnd())) {
                keyVP = vp;
            }

        }

        return keyVP;
    }

}