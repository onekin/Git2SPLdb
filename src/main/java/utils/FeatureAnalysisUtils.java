package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.repodriller.domain.DiffLine;

import preprocessing.Main;


import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.CustomizationDetail;
import SPLconcepts.CustomizationEffort;
import SPLconcepts.ProductRelease;
import SPLconcepts.SourceCodeFile;

public class FeatureAnalysisUtils {
	
	
	public static ArrayList <CustomizationDetail> computeFeatureChanged(String sourceCodeFile, List<DiffLine> newLines, String fileName, String filePath, ProductRelease inRelease) {	
		//Lista de modificaciones de un archivo. Para una modificacion, que feature cambia y que operacion: borrar o a–adir
	
		try {
			
			ArrayList <CustomizationDetail>	featureModificationDetailList  = new  ArrayList <CustomizationDetail> (); 
	        File auxFile= new File(Main.pathToAuxWorkSpace); //csvFilePath
	        PrintWriter writer = new PrintWriter(auxFile);
	        writer.print(sourceCodeFile);
	        writer.close();
	        
			SourceCodeFile  featureFile= new CoreAssetFileAnnotated(auxFile);
	        
	        HashMap<Integer, String> map;
			
			map = featureFile.readFileToGetFeatureMap(auxFile);
			 
	        Iterator<DiffLine> diffLineIterator = newLines.iterator(); //iteratate diffs 
	        
	        int lineNumber=0;
	        String modType=null;
	        DiffLine aux=null;
	        String featureName = null;
	        int addedLines=0, deletedLines=0;
	       
	        while (diffLineIterator.hasNext()){//for each diff know which lines where added/deleted and SUM
	        	aux= diffLineIterator.next();
	        	lineNumber= aux.getLineNumber();
	        	modType=aux.getType().toString();
	        	featureName= map.get(lineNumber);
	        	
		       //	if(modType!="KEPT"){
		        	System.out.println("feature changed: "+featureName+ " mod-type: "+modType+ " line num:"+lineNumber);
		        	//public CustomizationDetailUtil(String featureName, String modType, int lineOfCodeModified, String fileName, String filePath) {
		        	CustomizationDetail cust = new CustomizationDetail(featureName, modType, lineNumber, fileName, filePath);
		        	featureModificationDetailList.add(cust);
		       				
		       	//}
	        }
		
			return featureModificationDetailList; 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
