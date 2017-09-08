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
		//Lista de modificaciones de un archivo. Para una modificacion, que feature cambia y que operacion: borrar o a–adir
	
		ArrayList <Customization>	featureModificationDetailList  = new  ArrayList <Customization> (); 
    
		HashMap <Integer,ArrayList<String>> map = extractFeatureMapFromFile(content);
		if(map==null) return null;
		 
		Iterator<DiffLine> diffLineIterator = lines.iterator(); //iteratate diffs 
		
		int lineNumber=0;
		String modType=null;
		DiffLine aux=null;
		ArrayList<String> featureNames = null;
   
      
		while (diffLineIterator.hasNext()){//for each diff know which lines where added/deleted and SUM
			aux= diffLineIterator.next();
			lineNumber= aux.getLineNumber();
			modType=aux.getType().toString();
			featureNames= map.get(lineNumber);
			
		   	if(modType!="KEPT"){//a line that reserces as diff context 
		   		//(String featureName, String modType, int lineOfCodeModified, 
		   		//SourceCodeFile productFile, 
		   		//		SourceCodeFile coreAssetFile, ProductRelease inRelease, int isNewFeature, int isNewAsset)
		    	Customization cust = new Customization(featureNames.get(0), modType, lineNumber,
		    			utils.FileUtils.getProductAssetByFileName(fileName, pr),
		    			utils.FileUtils.getCoreAssetByProductAssetName(fileName, pr), 
		    			pr, 
		    			isFeatureInBaseline(pr.getFromProduct().getInPortfolio().getDerivedFrom(), featureNames.get(0)),
		    			isAssetInBaseline(pr.getFromProduct().getInPortfolio().getDerivedFrom(), fileName)
		    			);
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
			if (ca.getFileName().equals(fileName))
				return 1;
		}
		
		return 0;
	}



	private static int isFeatureInBaseline(CoreAssetBaseline baseline,
			String featureName) {
		
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




public static HashMap <Integer,ArrayList <String>>  extractFeatureMapFromFile (SourceCodeFile file, CoreAssetBaseline baseline) {//Read the file lineByLine
		
		HashMap <Integer,ArrayList <String>> featureToCodeMapping = new HashMap<Integer, ArrayList <String>>();
		
		String content = file.getContent();
		ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
		int nestingLevel=-1;
		
		if (!content.contains(Main.annotationPatternBeginning)) return null; //the file does not contain variability in it
		
		try {
		
			String[] lines = content.split("\n");
			
			int counter=1;
			ArrayList<String> listFeatures = new ArrayList<String>();
			HashMap <Integer,String> currentExpressionsInNestedLevels = new HashMap<Integer,String>();//pair of nesting level, and expression
			
			
			for(int i=0;i<lines.length;i++){
				VariationPoint vp = null;
				if (lines[i].contains(Main.annotationPatternBeginning)){
					 
					vp = new VariationPoint(Utils.getVPId(), lines[i],i);//newVariation point
					variationPoints.add(vp);
					vp.setExpression(lines[i]);
					vp.setFeatures(findFeaturesByNames(listFeatures,baseline));
					 System.out.println("Variation Point Created "+vp.toString());
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
					else{//no pattern has been found; we might be inside VPs (nested or not)
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
											
					}
				}
				featureToCodeMapping.put(counter,listFeatures);
				
				System.out.println(file.getFileName()+"; Line: "+counter+" belongs to: "+listFeatures.toString());
				
				counter ++;
			}
			file.setFeatureToCodeMapping(featureToCodeMapping);
			file.setVariationPoints(variationPoints);
			
			
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



//ESTE METODO ESTA DUPLICADO PARA EL COMPUTO DE LAS CUSTOMIZACIONES!!!!
public static HashMap <Integer,ArrayList <String>>  extractFeatureMapFromFile (String content) {//Read the file lineByLine
	
	HashMap <Integer,ArrayList <String>> featureToCodeMapping = new HashMap<Integer, ArrayList <String>>();
	
	ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
	int nestingLevel=-1;
	
	if (!content.contains(Main.annotationPatternBeginning)) return null; //the file does not contain variability in it
	
	try {
	
		String[] lines = content.split("\n");
		
		int counter=1;
		ArrayList<String> listFeatures = new ArrayList<String>();
		HashMap <Integer,String> currentExpressionsInNestedLevels = new HashMap<Integer,String>();//pair of nesting level, and expression
		
		for(int i=0;i<lines.length;i++){
			if (lines[i].contains(Main.annotationPatternBeginning )){
				
				VariationPoint vp = new VariationPoint(Utils.getVPId(), lines[i],i);
				variationPoints.add(vp);
				
				nestingLevel++;//level 0 is when we are inside a VP
				currentExpressionsInNestedLevels.put(nestingLevel,lines[i]);
					if (nestingLevel> 0){//we need to add all the features for all the nesting levels.
						listFeatures = new ArrayList<String>();
						for(int k=0; k<=nestingLevel;k++)
							listFeatures.addAll(extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(k)));
					}else
						listFeatures = extractAllFeaturesFromTheExpression(currentExpressionsInNestedLevels.get(nestingLevel));
					featureToCodeMapping.put(counter,listFeatures);
					
			}else{
				if (lines[i].contains(Main.annotationPatternEnd)){
					nestingLevel--;
					featureToCodeMapping.put(counter,listFeatures);
				
				}
				else{//no pattern has been found; we might be inside VPs (nested or not)
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
					featureToCodeMapping.put(counter,listFeatures);//no features to which the line belongs to
					
				}
			}
			
			counter ++;
		}
		
		//print Feature to code mapping
		
	} catch (Exception e) {
		e.printStackTrace();
	}
 
	return featureToCodeMapping;

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

}
