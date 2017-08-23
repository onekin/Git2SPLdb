package deprecated;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.DiffLine;

import SPLconcepts.CoreAssetFileAnnotated;

public class AnalyzeFeatureDetail {

	
	public AnalyzeFeatureDetail(String sourceCodeFile, List<DiffLine> newLines) {
	}
	
	public ArrayList <CustomizationDetail> computeFeatureChanged(String sourceCodeFile, List<DiffLine> newLines, String fileName, String filePath) {	
		//Lista de modificaciones de un archivo. Para una modificacion, qu� feature cambia y cuanto
	
		try {
			
			ArrayList <CustomizationDetail>	featureModificationDetailList  = new  ArrayList <CustomizationDetail> (); 
	        File auxFile= new File(Customs.pathToAuxWorkSpace); //csvFilePath
	        PrintWriter writer = new PrintWriter(auxFile);
	        writer.print(sourceCodeFile);
	        writer.close();
	        
			AnnotatedFile featureFile= new AnnotatedFile(auxFile);
	        
	        HashMap<Integer, String> map;
			
			map = featureFile.readFileToGetFeatureMap(auxFile);
			 
	        Iterator<DiffLine> diffLineIterator = newLines.iterator(); //iteratate diffs 
	        
	        int lineNumber=0;
	        String modType=null;
	        DiffLine aux=null;
	        String featureName = null;
	       
	        while (diffLineIterator.hasNext()){//for each diff know which lines where added/deleted and SUM
	        	aux= diffLineIterator.next();
	        	lineNumber= aux.getLineNumber();
	        	modType=aux.getType().toString();
	        	featureName= map.get(lineNumber);
	        	
		       	if(modType!="KEPT"){
		        //	System.out.println("feature changed: "+featureName+ " mod-type: "+modType+ " line num:"+lineNumber);
		  	        	featureModificationDetailList.add(new CustomizationDetail(featureName, modType, 1, fileName, filePath));
		       	}
	        }
		
			return featureModificationDetailList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
