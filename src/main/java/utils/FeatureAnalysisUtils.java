package utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffLine;

import preprocessing.Main;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.CustomizationDetail;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductRelease;


public class FeatureAnalysisUtils {
	
	
	public static ArrayList <CustomizationDetail> computeCustomizationDetails(String fileName, String path, String fileContent, List<DiffLine> lines, ProductRelease pr, Commit commit) {	
		//Lista de modificaciones de un archivo. Para una modificacion, que feature cambia y que operacion: borrar o a–adir
	
		ArrayList <CustomizationDetail>	featureModificationDetailList  = new  ArrayList <CustomizationDetail> (); 
    
		HashMap<Integer, String> map = extractFeatureMapFromFile(fileContent);
		 
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
		    	//System.out.println("feature changed: "+featureName+ " mod-type: "+modType+ " line num:"+lineNumber);
		    	CustomizationDetail cust = new CustomizationDetail(featureName, modType, lineNumber,
		    			utils.FileUtils.getCoreAssetByProductAssetName(fileName, pr), 
		    			utils.FileUtils.getProductAssetByFileName(fileName, pr), pr);
		    	featureModificationDetailList.add(cust);
		   				
		   	}
		}

		return featureModificationDetailList;
	}
	
	
	/**
	public HashMap <Integer,String>  extractFeatureMapFromFile(File file) {//Read the file lineByLine
		HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
		ArrayList<String> featureList= new ArrayList<String>();
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			//Construct BufferedReader from InputStreamReader
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			int counter=1;
			String featureName="none";
			
			while ((line = br.readLine()) != null){
				if (line.contains(Customs.annotationPatternBeginning)){		
					featureToCodeMapping.put(counter,featureName);
					featureName=line.split("pv:hasFeature")[1];
					featureList.add(featureName);
				}else{
					if (line.contains(Customs.annotationPatternEnd)){
					
						featureToCodeMapping.put(counter,featureName);
						featureName="none";
					}
					else{
						featureToCodeMapping.put(counter,featureName);
					}
				}
				counter ++;
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
		return featureToCodeMapping;
	}
*/
	

	public static HashMap <Integer,String>  extractFeatureMapFromFile(String content ) {//Read the file lineByLine
		
		HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
		ArrayList<String> featureList= new ArrayList<String>();
		
		try {
		
			String[] lines = content.split("\n");
			
			String line = null;
			int counter=1;
			String featureName="none";
			
			for(int i=0;i<lines.length;i++){
				if (lines[i].contains(Main.annotationPatternBeginning )){		
					featureToCodeMapping.put(counter,featureName);
					featureName=lines[i].split("pv:hasFeature")[1];
					featureList.add(featureName);
				}else{
					if (lines[i].contains(Main.annotationPatternEnd)){
					
						featureToCodeMapping.put(counter,featureName);
						featureName="none";
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


}
