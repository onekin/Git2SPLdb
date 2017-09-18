package utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffLine;

import preprocessing.Main;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Customization;
import SPLconcepts.Feature;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductRelease;
import SPLconcepts.SourceCodeFile;
import SPLconcepts.VariationPoint;


public class FeatureAnalysisUtils {
	
	
	public static ArrayList <Customization> computeCustomizationDetails(String fileName, String path, String content , List<DiffLine> lines, ProductRelease pr, Commit commit) {	
		//Lista de modificaciones de un archivo. Para una modificacion, que feature cambia y que operacion: borrar o a�adir
	
		
		
		ArrayList <Customization>	featureModificationDetailList  = new  ArrayList <Customization> (); 
    
		HashMap <Integer,ArrayList<String>> map = utils.FileUtils.getProductAssetByFilePath(path, pr).getFeatureToCodeMapping();// extractFeatureMapFromFile(content);
		if(map==null) return null;
		 
		Iterator<DiffLine> diffLineIterator = lines.iterator(); //iteratate diffs 
		ProductAssetFileAnnotated paModified = utils.FileUtils.getProductAssetByFilePath(path, pr);
		int lineNumber=0;
		String modType=null;
		DiffLine aux=null;
		ArrayList<String> featureNames = null;
   
      
		while (diffLineIterator.hasNext()){//for each diff know which lines where added/deleted and SUM
			aux= diffLineIterator.next();
			lineNumber= aux.getLineNumber();
			modType=aux.getType().toString();
			featureNames= map.get(lineNumber);
			
		   	if(modType!="KEPT"){//a line that serves as diff context 
		   		
		   		SourceCodeFile ca = utils.FileUtils.getCoreAssetByProductAssetPath(paModified.getRelativePath(), pr);
		   		VariationPoint vp = getVariationPointOfChangedAssetLine(paModified.getRelativePath(), pr, lineNumber);
		   		
		   		int newFeature=0;
		   		int existingAsset = isAssetInBaseline(pr.getFromProduct().getInPortfolio().getDerivedFrom(),fileName);
		   		if (vp!=null){//cuando no hay un VP
		   			System.out.println("VP changed is "+vp.getIdVP());
		   			if(vp.getNewFeatures()!=null)
			   			newFeature = 1;
			   		else newFeature = 0;	
		   		} 
		   		
		   		Customization cust = new  Customization(modType, lineNumber, paModified, 
		   				ca, pr, 
		   				newFeature,
		   				existingAsset,
		   				vp);	   	
		   		
		    	featureModificationDetailList.add(cust);
		   				
		   	}
		}

		return featureModificationDetailList;
	}
	

	private static int isAssetInBaseline(CoreAssetBaseline baseline,
			String fileName) {
		
		ArrayList<SourceCodeFile> list = baseline.getCoreAssetFiles();
		Iterator<SourceCodeFile> it = list.iterator();
		SourceCodeFile ca;
		
		while(it.hasNext()){
			ca = it.next();
			System.out.println("is asset in baseline? "+ca.getFileName()+ "    VS  "+fileName);
			if (ca.getFileName().equals(fileName))
				return 1;
		}
		return 0;
	}



	private static int isFeatureInBaseline(CoreAssetBaseline baseline,String featureName) {
		
		ArrayList<Feature> list = baseline.getFeatures();
		
		Iterator<Feature> it = list.iterator();
		Feature f;
		
		while(it.hasNext()){
			f = it.next();
			if (f.getIdFeature().equals(featureName))
				return 1;
		}
		return 0;
	}

	private static int isAnyFeatureNewInBaseline(CoreAssetBaseline baseline, ArrayList<Feature> features) {
		
		ArrayList<Feature> list = baseline.getFeatures();
		Iterator<Feature> baselineIt = list.iterator();
		Iterator<Feature> it = features.iterator();
	//	ArrayList <Feature> newFeatures=new ArrayList<Feature>();
		Feature f;
		int match = 0;
		
		while(it.hasNext()){
			f = it.next();
			match = 0;
			while(baselineIt.hasNext()) {
			  if (f.getIdFeature().equals(baselineIt.next().getName()))
				match = 1;
			}
			if (match ==0) {
				//newFeatures.add(f);
				return 1;
			} 
		}
		return 0;
	}




public static HashMap <Integer,ArrayList <String>>  extractFeatureMapFromFile (SourceCodeFile file, CoreAssetBaseline baseline) {//Read the file lineByLine
		
		HashMap <Integer,ArrayList <String>> featureToCodeMapping = new HashMap<Integer, ArrayList <String>>();
		
		String content = file.getContent();
		int nestingLevel=-1;
		if (!content.contains(Main.annotationPatternBeginning)) return null; //the file does not contain variability in it
		
		try {
		
			String[] lines = content.split("\n");
			int counter=1;
			ArrayList<String> listFeatures = new ArrayList<String>();
			HashMap <Integer,String> currentExpressionsInNestedLevels = new HashMap<Integer,String>();//pair of nesting level, and expression	
			
			for(int i=0;i<lines.length;i++){
				if (lines[i].contains(Main.annotationPatternBeginning)){
					nestingLevel++;//level 0 is when we are inside a VP
					currentExpressionsInNestedLevels.put(nestingLevel,lines[i]);
						if (nestingLevel> 0){//we need to add all the features for all the nesting levels.
							listFeatures = new ArrayList<String>();
							for(int k=0; k<=nestingLevel;k++)
								listFeatures.addAll(extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
						}else
							listFeatures = extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(nestingLevel));
						
				}else{
					if (lines[i].contains(Main.annotationPatternEnd)){
						
						nestingLevel--;
					}
					//else{//no pattern has been found; we might be inside VPs (nested or not)
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
											
				//	}
				}
				
				featureToCodeMapping.put(counter,listFeatures);
				System.out.println(file.getFileName()+"; Line: "+counter+" belongs to: "+listFeatures.toString());
				
				counter ++;
			}
			file.setFeatureToCodeMapping(featureToCodeMapping);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return featureToCodeMapping;
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

	public static boolean isFeatureInFeaturesList(ArrayList<Feature> featureList, String name){
		Iterator<Feature>  it= featureList.iterator();
		Feature f;
		while (it.hasNext()){
			f = it.next();
			if (f.getIdFeature().equals(name))
				return true;
		}
		return false;
	}



	public static ArrayList<VariationPoint> extractVPsFromFile(SourceCodeFile file, CoreAssetBaseline baseline) {
		// TODO Auto-generated method stub
	
		String content = file.getContent();
		if (!content.contains(Main.annotationPatternBeginning)) return null; //the file does not contain variability in it
		
		ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
		
		HashMap <Integer, VariationPoint> currentVPsInNestingLevels = new HashMap<Integer,  VariationPoint>();//key is the number of the VP currently being processing //nesting level
		
		try {
			String[] lines = content.split("\n");			
			int nestingLevel=-1;
			for(int i=0;i<lines.length;i++){
				VariationPoint vp = null;
				if (lines[i].contains(Main.annotationPatternBeginning)){
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
					if (lines[i].contains(Main.annotationPatternEnd)){
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
			Iterator<VariationPoint> it = variationPoints.iterator();
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
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		return variationPoints;
		
	}


public static VariationPoint getVariationPointOfChangedAssetLine(String relPath, ProductRelease pr, int lineNumber) {
	
	System.out.println("Finding VP for file - for CUSTOMIZATION ANALYSIS: "+relPath);
	ProductAssetFileAnnotated pa = utils.FileUtils.getProductAssetByFilePath(relPath, pr);
		
		VariationPoint vp, keyVP=null;
		
		System.out.println("IN getVariationPointOfChangedAssetLine " + pa.getFileName());
		ArrayList<VariationPoint> listVPs = pa.getVariationPoints();
		System.out.println("IN getVariationPointOfChangedAssetLine; VPs" +pa.getVariationPoints());
		System.out.println("Line number" +lineNumber);

		Iterator<VariationPoint> it = listVPs.iterator();
		
		while (it.hasNext()) {
			vp=it.next();
			System.out.println(" Line init:" +vp.getLineInit()+ ";   Line end: "+ vp.getLineEnd());
			if((vp.getLineInit()<=lineNumber) && (lineNumber<=vp.getLineEnd())) {
				keyVP=vp;
				System.out.println("SIIIIIIII");
			}
				
		}
		
		return keyVP;
	}
	
}
