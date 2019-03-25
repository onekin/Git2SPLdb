package customDiff.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.FeatureSibling;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;

public class VariationPointAnalysisUtils {

	public static HashMap<Integer, ArrayList<String>> extractFeaturesAndVPsForCoreAsset(SourceCodeFile ca,
			CoreAssetBaseline baseline, boolean secondRelease) {
		Collection<ArrayList<String>> values = null;
		Iterator<ArrayList<String>> ite;
		ArrayList<String> valueList;
		Iterator<String> i;
		String variable;

		// 1: extract Feature Map for a file
		HashMap<Integer, ArrayList<String>> map = extractFeatureMapFromFile(ca); // line-feature map

		// 3: extract variation points for a file
		// luego a�adir a la bseline

		if (map == null)
			return null;

		ca.setFeatureToCodeMapping(map);

		// System.out.println("Feature-to-code Map for file "+ca.getFileName()+ " is:
		// \n" +ca.getFeatureToCodeMapping().toString());

		values = map.values();// ArrayList with repeated features;
		ite = values.iterator();
		while (ite.hasNext()) {// Add features to the core asset and to the main list of features for the spl.
			valueList = ite.next();
			i = valueList.iterator();
			while (i.hasNext()) {
				variable = i.next();
				Feature f = new Feature(variable, variable);
				if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(customDiff.CustomDiff.features,
						variable)) {
					customDiff.CustomDiff.features.add(f);
				}
				if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(ca.getFeatureList(), variable)) {
					ca.getFeatureList().add(f);
				}
			}
		}

		// 2: Add features found in the core asset to the baseline
		ArrayList<Feature> features = ca.getFeatureList();
		Iterator<Feature> iterator = features.iterator();
		Feature feature;
		while (iterator.hasNext()) {
			feature = iterator.next();
			if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(),
					feature.getName()))
				baseline.addFeature(feature);
		}
		ArrayList<VariationPoint> vps = extractVPsFromFile(ca, baseline, secondRelease);
		ca.setVariationPoints(vps);

		return map;
	}

	public static HashMap<Integer, ArrayList<String>> extractFeatureMapFromFile(SourceCodeFile file) {// Read the file
																										// lineByLine
		// works fine: tested with properties.js

		HashMap<Integer, ArrayList<String>> featureToCodeMapping = new HashMap<Integer, ArrayList<String>>();// line -
																												// list
																												// of
																												// features
																												// for
																												// the
																												// line
		String content = file.getContent();
		int nestingLevel = -1;
		if (!content.contains(customDiff.CustomDiff.annotationPatternBeginning))
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
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternBeginning)) {
					nestingLevel++;// level 0 is when we are inside a VP
					currentExpressionsInNestedLevels.put(nestingLevel, lines[i]);
					if (nestingLevel > 0) {// we need to add all the features for all the nesting levels.
						listFeatures = new ArrayList<String>();
						for (int k = 0; k <= nestingLevel; k++)
							listFeatures.addAll(
									extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
					} else
						listFeatures = extractAllFeaturesFromTheExpression(
								currentExpressionsInNestedLevels.get(nestingLevel));
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
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternEnd))
					nestingLevel--;
			} // end for
			file.setFeatureToCodeMapping(featureToCodeMapping);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return featureToCodeMapping;
	}

	public static ArrayList<VariationPoint> extractVPsFromFile(SourceCodeFile file, CoreAssetBaseline baseline,
			boolean secondRelease) {

		String content = file.getContent();
		if (!content.contains(customDiff.CustomDiff.annotationPatternBeginning))
			return null; // the file does not contain variability in it

		ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();

		HashMap<Integer, VariationPoint> currentVPsInNestingLevels = new HashMap<Integer, VariationPoint>();// key is
																											// the
																											// number of
																											// the VP
																											// currently
																											// being
																											// processing
																											// //nesting
																											// level

		try {
			String[] lines = content.split("\n");
			Set<String> listFeatures = new HashSet<String>();
			int nestingLevel = -1;
			FeatureSibling featureSibling;
			for (int i = 0; i < lines.length; i++) {
				VariationPoint vp = null;
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternBeginning)) {

					nestingLevel++; // level 0 is when we are inside a VP
					if (nestingLevel == 0)
						vp = new VariationPoint(Utils.getVPId(), lines[i], i + 1, null);// first level vp point
					else
						vp = new VariationPoint(Utils.getVPId(), lines[i], i + 1,
								currentVPsInNestingLevels.get(nestingLevel - 1));// with parent
					listFeatures.addAll(extractAllFeaturesFromTheExpression(lines[i]));// extract the list of features
					featureSibling = new FeatureSibling(listFeatures);
					if (baseline.getFeatureSiblings().contains(featureSibling)) {
						featureSibling = baseline.getFeatureSiblings()
								.get(baseline.getFeatureSiblings().indexOf(featureSibling));
					} else {
						featureSibling.setId(baseline.getFeatureSiblings().size() + 1);
						baseline.getFeatureSiblings().add(featureSibling);
					}
					vp.setFeatureSibling(featureSibling);
					vp.setFeatures(findFeaturesByNames(listFeatures, baseline));// (findFeaturesByNames(listFeatures,
																				// baseline));

					if (secondRelease) {
						// System.out.println("Finding new features in product for
						// files:"+file.getFileName());
						vp.setNewFeatures(findNewFeatures(extractAllFeaturesFromTheExpression(lines[i]), baseline)); // SET
																														// NEW
																														// FEATURES!
					}

					variationPoints.add(vp);
					currentVPsInNestingLevels.put(nestingLevel, vp);

					// System.out.println("New variation Point Created "+vp.toString());
					if (nestingLevel >= 0) { // we need to add the body to all nested VPs
						for (int k = 0; k <= nestingLevel; k++)// the line needs to go to parent VPs as well.
							currentVPsInNestingLevels.get(k)
									.setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n" + lines[i]));
					}
					// System.out.println("New VP in "+file.getFileName()+": "+lines[i]);
					// System.out.println("Features: "+listFeatures.toString());
				} else {
					if (lines[i].contains(customDiff.CustomDiff.annotationPatternEnd)) {
						// add the line to all parents of this VP
						for (int k = 0; k <= nestingLevel; k++)// the line needs to go to parent VPs as well.
							currentVPsInNestingLevels.get(k)
									.setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n" + lines[i]));
						// calculate ending Line for the VP
						currentVPsInNestingLevels.get(nestingLevel).setLineEnd(i + 1);
						listFeatures.removeAll(extractAllFeaturesFromTheExpression(
								currentVPsInNestingLevels.get(nestingLevel).getExpression()));
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

	/*
	 * private static ArrayList<String> extractAllFeaturesFromTheExpression(String
	 * expression) { ArrayList<String> listfeatures = new ArrayList<String>();
	 * String[] pieces = expression.split("'"); // Expression example
	 * //PV:IFCOND(pv:hasFeature('Fa') and // pv:hasFeature('FB')) for (int i = 0; i
	 * < pieces.length; i++) { if ((i / 2) * 2 != i) { // if it is odd
	 * listfeatures.add(pieces[i]); } } //
	 * System.out.println("Expression: "+expression+" -> Features //
	 * "+listfeatures.toString()); return listfeatures; }
	 */
	private static ArrayList<String> extractAllFeaturesFromTheExpression(String expression) {
		ArrayList<String> listfeatures = new ArrayList<String>();
		String[] pieces;
		if (expression.trim().startsWith("#ifdef") || expression.trim().startsWith("#ifndef")) {
			expression = expression.replaceAll("#ifdef", "").replaceAll("#ifndef", "");
			expression= expression.split("//")[0];
			listfeatures.add(expression.trim().toUpperCase());

		} else {
			expression= expression.replaceAll("/^(&&|\\||\\s|\\|\\))$/", "");
			expression= expression.replaceAll("#if", "");
			expression= expression.split("//")[0];
			pieces = expression.toLowerCase().split("/^(IS_ENABLED\\(|ENABLED\\(|DEFINED\\(|DISABLED\\(|!DEFINED\\()$/");
			for (int i = 2; i < pieces.length; i=i+2) {
				listfeatures.add(pieces[i].trim().toUpperCase());
			}

		}
		return listfeatures;
	}

	private static Set<Feature> findFeaturesByNames(Set<String> listFeatures, CoreAssetBaseline baseline) {
		Iterator<String> itNames = listFeatures.iterator();
		// System.out.println("Baseline features: "+baseline.getFeatures().toString());
		// System.out.println("Finding features in list: "+listFeatures.toString());
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
		System.out.println("Features found:" + features.toString());
		return features;
	}

	private static ArrayList<Feature> findNewFeatures(ArrayList<String> featuresExtracted, CoreAssetBaseline baseline) {
		Iterator<String> extractedFeatures = featuresExtracted.iterator();
		// System.out.println( " In findNewFeatures " );
		String name;
		Feature f;
		ArrayList<Feature> newFeatures = new ArrayList<Feature>();

		boolean match = false;

		while (extractedFeatures.hasNext()) {// for each feature found in the product asset
			name = extractedFeatures.next();
			Iterator<Feature> baseFeatures = baseline.getFeatures().iterator();
			match = false;

			while (baseFeatures.hasNext()) {
				f = baseFeatures.next();
				if (f.getName().equals(name)) {
					match = true; // newFeatureNames.add(f.getName());
					break;// the
				}
			}
			if (match == false) {
				Feature newFeature = new Feature(name, name, true);
				newFeatures.add(newFeature);
				System.out.println(" NEW FEATURE FOUND: " + name);
			}
		}
		return newFeatures;
	}

}
