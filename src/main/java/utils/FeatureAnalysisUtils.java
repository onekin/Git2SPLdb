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


public class FeatureAnalysisUtils {
	
	
	public static ArrayList <Customization> computeCustomizationDetails(String fileName, String path, String fileContent, List<DiffLine> lines, ProductRelease pr, Commit commit) {	
		//Lista de modificaciones de un archivo. Para una modificacion, que feature cambia y que operacion: borrar o a–adir
	
		ArrayList <Customization>	featureModificationDetailList  = new  ArrayList <Customization> (); 
    
		HashMap<Integer, String> map = extractFeatureMapFromFile(fileContent);
		if(map==null) return null;
		 
		Iterator<DiffLine> diffLineIterator = lines.iterator(); //iteratate diffs 
		
		int lineNumber=0;
		String modType=null;
		DiffLine aux=null;
		String featureName = null;
   
      
		while (diffLineIterator.hasNext()){//for each diff know which lines where added/deleted and SUM
			aux= diffLineIterator.next();
			lineNumber= aux.getLineNumber();
			modType=aux.getType().toString();
			featureName= map.get(lineNumber);
			
		   	if(modType!="KEPT"){//a line that reserces as diff context 
		   		//(String featureName, String modType, int lineOfCodeModified, SourceCodeFile productFile, 
		   		//		SourceCodeFile coreAssetFile, ProductRelease inRelease, int isNewFeature, int isNewAsset)
		    	Customization cust = new Customization(featureName, modType, lineNumber,
		    			utils.FileUtils.getCoreAssetByProductAssetName(fileName, pr), 
		    			utils.FileUtils.getProductAssetByFileName(fileName, pr),
		    			pr, 
		    			isFeatureInBaseline(pr.getFromProduct().getInPortfolio().getDerivedFrom(), featureName),
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
			System.out.println("AQUUUUIQ");
			System.out.println(ca.getFileName()+"== "+(fileName));
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



	public static HashMap <Integer,String>  extractFeatureMapFromCoreAsset (SourceCodeFile file) {//Read the file lineByLine
		
		HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
		ArrayList<String> featureList= new ArrayList<String>();
		String content = file.getContent();
	//	if (!content.contains(Main.annotationPatternBeginning)) return null; 
		
		try {
		
			String[] lines = content.split("\n");
			
			int counter=1;
			String featureName="undefined";
			
			for(int i=0;i<lines.length;i++){
				if (lines[i].contains(Main.annotationPatternBeginning )){		
					featureToCodeMapping.put(counter,featureName);
					featureName=lines[i].split("pv:hasFeature")[1];
					
					featureName = featureName.split("'")[1];
					featureList.add(featureName);
				}else{
					if (lines[i].contains(Main.annotationPatternEnd)){
						featureToCodeMapping.put(counter,featureName);
						featureName="undefined";
					}
					else{
						featureToCodeMapping.put(counter,featureName);
					}
				}
				counter ++;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		
		return featureToCodeMapping;
	}

public static HashMap <Integer,String>  extractFeatureMapFromFile (String content) {//Read the file lineByLine
		
		HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
		ArrayList<String> featureList= new ArrayList<String>();
	
		if (!content.contains(Main.annotationPatternBeginning)) return null; 
		
		try {
		
			String[] lines = content.split("\n");
			
			int counter=1;
			String featureName="undefined";
			
			for(int i=0;i<lines.length;i++){
				if (lines[i].contains(Main.annotationPatternBeginning )){		
					featureToCodeMapping.put(counter,featureName);
					featureName=lines[i].split("pv:hasFeature")[1];
					
					featureName = featureName.split("'")[1];
					featureList.add(featureName);
				}else{
					if (lines[i].contains(Main.annotationPatternEnd)){
						featureToCodeMapping.put(counter,featureName);
						featureName="undefined";
					}
					else{
						featureToCodeMapping.put(counter,featureName);
					}
				}
				counter ++;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
		
		return featureToCodeMapping;
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
