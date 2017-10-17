package customDiff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import utils.FeatureAnalysisUtils;
import utils.Utils;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.Feature;
import SPLconcepts.SourceCodeFile;
import SPLconcepts.VariationPoint;

public class VariationPointAnalysisUtils {
	
	
	public static HashMap<Integer, ArrayList<String>> extractFeaturesAndVPsForCoreAsset (SourceCodeFile ca, CoreAssetBaseline baseline) {		
		Collection<ArrayList<String>> values = null;
		Iterator<ArrayList<String>> ite;
		ArrayList<String> valueList;
		Iterator<String> i;
		String variable;
		
		
		//1: extract Feature Map for a file
		HashMap<Integer, ArrayList <String>> map = extractFeatureMapFromFile(ca,baseline); //line-feature map
		
		//2: extract variation points for a file
		ArrayList<VariationPoint> vps = extractVPsFromFile(ca, baseline);

		if (map == null) return null;
		

		ca.setFeatureToCodeMapping(map);
		ca.setVariationPoints(vps);
		
		System.out.println("Feature-to-code Map for file "+ca.getFileName()+ " is: \n" +ca.getFeatureToCodeMapping().toString());
		
		values = map.values();//ArrayList with repeated features;
		ite= values.iterator();
		while (ite.hasNext()){//Add features to the core asset and to the main list of features for the spl.
			valueList = ite.next();
			i = valueList.iterator();
			while(i.hasNext()){
				variable = i.next();
				Feature f = new Feature(variable, variable);
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(customDiff.CustomDiff.features, variable)){
					customDiff.CustomDiff.features.add(f);
				}
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList( ca.getFeatureList(), variable) ){
					ca.getFeatureList().add(f);
				}
			}
		}
		return map;
	}
	
	

public static HashMap <Integer,ArrayList <String>>  extractFeatureMapFromFile (SourceCodeFile file, CoreAssetBaseline baseline) {//Read the file lineByLine
		//works fine: tested with properties.js
	
		HashMap <Integer,ArrayList <String>> featureToCodeMapping = new HashMap<Integer, ArrayList <String>>();//line - list of features for the line
		String content = file.getContent();
		int nestingLevel=-1;
		if (!content.contains(customDiff.CustomDiff.annotationPatternBeginning)) return null; //the file does not contain variability in it
		
		try {
			String[] lines = content.split("\n");
			int counter=0;
			ArrayList<String> listFeatures = new ArrayList<String>();
			HashMap <Integer,String> currentExpressionsInNestedLevels = new HashMap<Integer,String>();//pair of nesting level, and expression	
			
			for(int i=0;i<lines.length;i++){
				counter ++;
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternBeginning)){
					nestingLevel++;//level 0 is when we are inside a VP
					currentExpressionsInNestedLevels.put(nestingLevel,lines[i]);
						if (nestingLevel> 0){//we need to add all the features for all the nesting levels.
							listFeatures = new ArrayList<String>();
							for(int k=0; k<=nestingLevel;k++)
								listFeatures.addAll(extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
						}else
							listFeatures = extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(nestingLevel));
				}else{
					if(nestingLevel==-1){
						listFeatures = new ArrayList<String>();
					}
					if (nestingLevel ==0){
						listFeatures = extractAllFeaturesFromTheExpression (currentExpressionsInNestedLevels.get(nestingLevel)); 
					}
					if (nestingLevel > 0) {
						listFeatures = new ArrayList<String>();
						for(int k=0; k<=nestingLevel;k++)
							listFeatures.addAll(extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
					}
				}//end else
				featureToCodeMapping.put(counter,listFeatures);
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternEnd))
					nestingLevel--;
			}//end for
			file.setFeatureToCodeMapping(featureToCodeMapping);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return featureToCodeMapping;
	}
	
public static ArrayList<VariationPoint> extractVPsFromFile(SourceCodeFile file, CoreAssetBaseline baseline) {

	String content = file.getContent();
	if (!content.contains(customDiff.CustomDiff.annotationPatternBeginning)) return null; //the file does not contain variability in it
	
	ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
	
	HashMap <Integer, VariationPoint> currentVPsInNestingLevels = new HashMap<Integer,  VariationPoint>();//key is the number of the VP currently being processing //nesting level
	
	try {
		String[] lines = content.split("\n");			
		int nestingLevel=-1;
		for(int i=0;i<lines.length;i++){
			VariationPoint vp = null;
			if (lines[i].contains(customDiff.CustomDiff.annotationPatternBeginning)){
				nestingLevel++; //level 0 is when we are inside a VP
				if (nestingLevel==0)
				  vp = new VariationPoint(Utils.getVPId(), lines[i],i+1,null);//newVariation point
				else vp = new VariationPoint(Utils.getVPId(), lines[i],i+1,currentVPsInNestingLevels.get(nestingLevel-1));
				vp.setFeatures(findFeaturesByNames(extractAllFeaturesFromTheExpression (lines[i]),baseline) );
				vp.setNewFeatures(findNewFeaturesByNames(extractAllFeaturesFromTheExpression (lines[i]),baseline) );
				variationPoints.add(vp);
				currentVPsInNestingLevels.put(nestingLevel, vp);				
				
				System.out.println("New variation Point Created "+vp.toString());
					if (nestingLevel>= 0){ //we need to add the body to all nested VPs
						for(int k=0; k <= nestingLevel;k++)//the line needs to go to parent VPs as well.
							currentVPsInNestingLevels.get(k).setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n"+lines[i]));
					}
			}else{
				if (lines[i].contains(customDiff.CustomDiff.annotationPatternEnd)){
					//add the line to all parents of this VP
					for(int k=0; k <= nestingLevel;k++)//the line needs to go to parent VPs as well.
						currentVPsInNestingLevels.get(k).setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n"+lines[i]));
					//calculate ending Line for the VP
					currentVPsInNestingLevels.get(nestingLevel).setLineEnd(i+1);
					nestingLevel--;
				}
				else{ //no pattern has been found; we might be inside VPs (nested or not) or inside no VP at all
					if(nestingLevel>=0)
						for(int k=0; k<=nestingLevel;k++)
							currentVPsInNestingLevels.get(k).setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n"+lines[i]));
				}
			}
		}
		file.setVariationPoints(variationPoints);
		
		//printing VPS			
		/*Iterator<VariationPoint> it = variationPoints.iterator();
		VariationPoint var;
		System.out.println("---------------------------------");
		System.out.println("------ Variation Points ---------");
		while (it.hasNext()) {
			var = it.next();
			
			System.out.println("Var "+var.getIdVP()+ 
			" Expression:"+var.getExpression() +
			" LineInit:"+ var.getLineInit() +
			" LineEnd:"+ var.getLineEnd() +
			" Parent?:"+ var.getParentVP()+ 
			" Body:"+ var.getBody() +
			" \nfeatures:"+ var.getFeatures().toString());
		}		*/
	} catch (Exception e) {
		e.printStackTrace();
	}
 
	return variationPoints;
	
}


	private static ArrayList<String> extractAllFeaturesFromTheExpression(String expression) {
		ArrayList<String> listfeatures = new ArrayList<String>();
		String[] pieces = expression.split("'"); //Expression example //PV:IFCOND(pv:hasFeature('Fa') and pv:hasFeature('FB'))
		for (int i=0; i< pieces.length;i++){
			if ( (i/2)*2 != i ){ //if it is odd
				listfeatures.add(pieces[i]);
			}
		}
	return listfeatures;
	}

	private static ArrayList<Feature> findFeaturesByNames(ArrayList<String> listFeatures, CoreAssetBaseline baseline) {
		Iterator<String> itNames = listFeatures.iterator();
		
		String name; Feature f;
		ArrayList<Feature> features = new ArrayList<Feature>();
		
		while(itNames.hasNext()){
			name =itNames.next();
			Iterator<Feature> itFeatures = baseline.getFeatures().iterator();
			while(itFeatures.hasNext()){
				f = itFeatures.next();
				if (f.getName().equals(name))
					features.add(f);
			}
		}
		return features;
	}
	

private static ArrayList<String> findNewFeaturesByNames(ArrayList<String> listFeatures, CoreAssetBaseline baseline) {
	Iterator<String> itNames = listFeatures.iterator();
	
	String name; Feature f;
	ArrayList<String> newFeatureNames = new ArrayList<String>();
	
	boolean match=false;
	
	while(itNames.hasNext()){
		name =itNames.next();
		Iterator<Feature> itFeatures = baseline.getFeatures().iterator();
		match = false;
		while(itFeatures.hasNext()){
			f = itFeatures.next();
			if (f.getName().equals(name)) {
				match=true; //newFeatureNames.add(f.getName());
				continue;
			}
		}
		if (match==false) {
			newFeatureNames.add(name);
			System.out.println( " New feature encountered: " +name );
		}
	}
	return newFeatureNames;
}
	
}
