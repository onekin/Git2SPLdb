package customDiff.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import customDiff.CustomDiff;
import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;


public class VariationPointAnalysisUtils {
	
	
	public static HashMap<Integer, ArrayList<String>> extractFeaturesAndVPsForCoreAsset (SourceCodeFile ca, CoreAssetBaseline baseline) {		
		Collection<ArrayList<String>> values = null;
		Iterator<ArrayList<String>> ite;
		ArrayList<String> valueList;
		Iterator<String> i;
		String variable;
	
		//1: extract Feature Map for a file
		HashMap<Integer, ArrayList <String>> map = extractFeatureMapFromFile(ca); //line-feature map
		if (map == null) return null;
		System.out.println("File "+ca.getFileName()+ "has map: "+ map.toString());
		ca.setFeatureToCodeMapping(map);

		
		// 2: extract features from the map & add it to the baseline & file
		values = map.values();//ArrayList with repeated features;
		ite= values.iterator();
		while (ite.hasNext()){//Add features to the core asset and to the main list of features for the spl.
			valueList = ite.next();
			i = valueList.iterator();
			while(i.hasNext()){
				variable = i.next();
				Feature f = new Feature(variable, variable);
				if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(customDiff.CustomDiff.features, variable)){
					customDiff.CustomDiff.features.add(f);
					//System.out.println(f.getName()+"  ADDED to the feature list!");
				}
				//else System.out.println(f.getName()+" is NOT added to the feature list!");
				if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList( ca.getFeatureList(), variable) ){
					ca.getFeatureList().add(f);
				}
				if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList( baseline.getFeatures(), variable) ){
					baseline.addFeature(f);
				}
			}
		}
		
		System.out.println("baseline features "+baseline.getFeatures().toString());
		System.out.println("File has features:"+ca.getFeatureList().toString());
	/*	
		 //2: Add features found in the core asset to the baseline
        ArrayList<Feature> features = ca.getFeatureList();
        System.out.println("Feature List:"+features);
        Iterator<Feature> iterator = features.iterator();
        Feature feature;
        while (iterator.hasNext()){
      	feature = iterator.next();
  		
      	if(!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(), feature.getName())) {
      		System.out.println(feature.getIdFeature()+" ADDED TO THE BASELINE");
      		baseline.addFeature(feature);
      	}
  			
  		//just added
	  		if (!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(customDiff.CustomDiff.features, feature.getName())){
				customDiff.CustomDiff.features.add(feature);
				System.out.println(feature.getName()+"  ADDED to the feature list!");
			}
  		}*/
        
        
		//3: extract variation points for a file
		ArrayList<VariationPoint> vps = extractVPsFromFile(ca, baseline, false);
		//luego anadir a la bseline
		ca.setVariationPoints(vps);
		
		//System.out.println("Feature-to-code Map for file "+ca.getFileName()+ " is: \n" +ca.getFeatureToCodeMapping().toString());
		
		
		return map;
	}
	
	

public static HashMap <Integer,ArrayList <String>>  extractFeatureMapFromFile (SourceCodeFile file) {//Read the file lineByLine
		//works fine: tested with properties.js
	
		HashMap <Integer,ArrayList <String>> featureToCodeMapping = new HashMap<Integer, ArrayList <String>>();//line - list of features for the line
		String content = file.getContent();
		//System.out.println(content);
		int nestingLevel=-1;
		if (!content.contains(customDiff.CustomDiff.annotationPatternBeginningCpp)) 
			return null; //the file does not contain variability in it
		
		try {
			String[] lines = content.split("\n");
		
			System.out.println(file.getFileName()+" number of  lines: "+lines.length);
			int counter=0;
			ArrayList<String> listFeatures = new ArrayList<String>();
			HashMap <Integer,String> currentExpressionsInNestedLevels = new HashMap<Integer,String>();//pair of nesting level, and expression	
			System.out.println("BEFORE THE FOR in extractFeatureMapFromFile");
			String line;
			for(int i=0;i<lines.length;i++){
				counter ++;
				line = lines[i].replaceAll("\\s+","");
				if (lines[i].startsWith(customDiff.CustomDiff.annotationPatternBeginningCpp)){
					System.out.println("extractFeatureMapFromFile ->Found VP: "+lines[i]);
					nestingLevel++;//level 0 is when we are inside a VP
					currentExpressionsInNestedLevels.put(nestingLevel,lines[i]);
						if (nestingLevel> 0){//we need to add all the features for all the nesting levels.
							listFeatures = new ArrayList<String>();
							for(int k=0; k<=nestingLevel;k++) {
								listFeatures.addAll(extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
							}
								
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
				if (line.startsWith(customDiff.CustomDiff.annotationPatternEndCpp))
					nestingLevel--;
			}//end for
			
			file.setFeatureToCodeMapping(featureToCodeMapping);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return featureToCodeMapping;
	}
	
public static ArrayList<VariationPoint> extractVPsFromFile(SourceCodeFile file, CoreAssetBaseline baseline, boolean product) {

	String content = file.getContent();
	if (!content.contains(customDiff.CustomDiff.annotationPatternBeginningCpp)) return null; //the file does not contain variability in it
	
	ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
	
	HashMap <Integer, VariationPoint> currentVPsInNestingLevels = new HashMap<Integer,  VariationPoint>();//key is the number of the VP currently being processing //nesting level
	
	try {
		String[] lines = content.split("\n");			
		ArrayList<String> listFeatures = new ArrayList<String>();
		int nestingLevel=-1;
		String line;
		
		for(int i=0;i<lines.length;i++){
			System.out.println("Line "+(i+1));
			VariationPoint vp = null;
			//line = lines[i].replaceAll("\\s+","");
		//	if (lines[i].contains(customDiff.CustomDiff.annotationPatternBeginningCpp)){
			if (lines[i].startsWith(customDiff.CustomDiff.annotationPatternBeginningCpp)){
				System.out.println("FOUND VP in line:"+lines[i]);
				nestingLevel++; //level 0 is when we are inside a VP
				if (nestingLevel==0)
				  vp = new VariationPoint(Utils.getVPId(), lines[i],i+1,null);//first level vp point
				else vp = new VariationPoint(Utils.getVPId(), lines[i],i+1,currentVPsInNestingLevels.get(nestingLevel-1));//with parent
				listFeatures.addAll(extractAllFeaturesFromTheExpression(lines[i]));//extract the list of features
				System.out.println("features:"+listFeatures);
				vp.setFeatures(findFeaturesByNames(listFeatures,baseline));//(findFeaturesByNames(listFeatures, baseline));
				
				if (product) {
					//System.out.println("Finding new features in product for files:"+file.getFileName());
					vp.setNewFeatures(findNewFeatures(extractAllFeaturesFromTheExpression (lines[i]), baseline) ); //SET NEW FEATURES!
				}
					
				variationPoints.add(vp);
				currentVPsInNestingLevels.put(nestingLevel, vp);				
				
				//System.out.println("New variation Point Created "+vp.toString());
					if (nestingLevel>= 0){ //we need to add the body to all nested VPs
						for(int k=0; k <= nestingLevel;k++)//the line needs to go to parent VPs as well.
							currentVPsInNestingLevels.get(k).setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n"+lines[i]));
					}
			//	System.out.println("New VP in "+file.getFileName()+": "+lines[i]);
			//	System.out.println("Features: "+listFeatures.toString());
			}else{
				if (lines[i].startsWith(customDiff.CustomDiff.annotationPatternEndCpp)){
					//add the line to all parents of this VP
					System.out.println("VP END FOUND!!:" +lines[i]);
					for(int k=0; k <= nestingLevel;k++)//the line needs to go to parent VPs as well.
						currentVPsInNestingLevels.get(k).setBody(currentVPsInNestingLevels.get(k).getBody().concat("\n"+lines[i]));
					//calculate ending Line for the VP
					currentVPsInNestingLevels.get(nestingLevel).setLineEnd(i+1);//(i+1)
					listFeatures.removeAll(extractAllFeaturesFromTheExpression(currentVPsInNestingLevels.get(nestingLevel).getExpression()));
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
	
	} catch (Exception e) {
		e.printStackTrace();
	}
 
	return variationPoints;
	
}


	private static ArrayList<String> extractAllFeaturesFromTheExpression(String expression) {
		ArrayList<String> listfeatures = new ArrayList<String>();
		//String[] pieces = expression.split("'"); 
		//Expression example //PV:IFCOND(pv:hasFeature('Fa') and pv:hasFeature('FB'))
		//Expression example #if HAS_FEATURE_DRY_PUMP_DELAY == 1
		String mainPiece = expression.split(customDiff.CustomDiff.annotationPatternBeginningCpp)[1];
		mainPiece= mainPiece.replaceAll("\\s+","");//remove spaced
		mainPiece= mainPiece.split("==")[0];//remove spaced
		//System.out.println("main piece: "+mainPiece);
		listfeatures.add(mainPiece);
		//String[] pieces = mainPiece.split("==");
		//for (int i=0; i< pieces.length;i++){
		//	if ( (i/2)*2 != i ){ //if it is odd
		//		listfeatures.add(pieces[i]);
		//	}
	//	}
	//	System.out.println("extracted features from expression: "+expression+" -> are "+listfeatures.toString());
	return listfeatures;
	}

	private static ArrayList<Feature> findFeaturesByNames(ArrayList<String> listFeatures, CoreAssetBaseline baseline) {
		Iterator<String> itNames = listFeatures.iterator();
	    System.out.println("Baseline features: "+baseline.getFeatures().toString());
	 	System.out.println("Finding features in list: "+listFeatures.toString());
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
		System.out.println("Features found:"+features.toString());
		return features;
	}
	

	private static ArrayList<Feature> findNewFeatures(ArrayList<String> featuresExtracted, CoreAssetBaseline baseline) {
		Iterator<String> extractedFeatures = featuresExtracted.iterator();
	//	System.out.println( " In findNewFeatures "  );
		String name; Feature f;
		ArrayList<Feature> newFeatures = new ArrayList<Feature>();
		
		boolean match=false;
		
		while(extractedFeatures.hasNext()){//for each feature found in the product asset
			name =extractedFeatures.next();
			Iterator<Feature> baseFeatures = baseline.getFeatures().iterator();
			match = false;
			
			while(baseFeatures.hasNext()){
				f = baseFeatures.next();
				if (f.getName().equals(name)) {
					match=true; //newFeatureNames.add(f.getName());
					break;//the
				}
			}
			if (match==false) {
				Feature newFeature = new Feature(name,name,true);
				newFeatures.add(newFeature);
				System.out.println( "NEW FEATURE FOUND:" +name );
			}
		}
		return newFeatures;
	}	

}
