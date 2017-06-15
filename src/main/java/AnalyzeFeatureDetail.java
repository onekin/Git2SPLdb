import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.DiffLine;



public class AnalyzeFeatureDetail {

	
	public AnalyzeFeatureDetail(String sourceCodeFile, List<DiffLine> newLines) {
	}
	
	public ArrayList <FeatureModificationDetail> computeFeatureChanged(String sourceCodeFile, List<DiffLine> newLines) {	
		//Lista de modificaciones de un archivo. Para una modificacion, qué feature cambia y cuanto
	
		try {
			
			ArrayList <FeatureModificationDetail>	featureModificationDetailList  = new  ArrayList <FeatureModificationDetail> (); 
	        File auxFile= new File("/Users/Onekin/Desktop/aux.txt"); //csvFilePath
	        PrintWriter writer = new PrintWriter(auxFile);
	        writer.print(sourceCodeFile);
	        writer.close();
	        
			FeaturedFile featureFile= new FeaturedFile(auxFile);
	        
	        HashMap<Integer, String> map;
			
			map = featureFile.readFileToGetFeatureMap(auxFile);
			 
	        Iterator<DiffLine> diffLineIterator = newLines.iterator();//iteratate diffs 
	        
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
		  	        	featureModificationDetailList.add(new FeatureModificationDetail(featureName, modType, 1));
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
