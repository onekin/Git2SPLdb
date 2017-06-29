import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class AnnotatedFile {
	


	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	
	File file;
	String fileName;
	String path;
	
	public AnnotatedFile(File file){
		this.file=file;
	}
	
	
	public HashMap <Integer,String>  readFileToGetFeatureMap(File file) throws IOException {//Read the file lineByLine
		FileInputStream fis = new FileInputStream(file);
	 
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		int counter=1;
		boolean foundAnnotationBegining=false;
		boolean foundAnnotationEnd=false;
		String featureName="none";
		
		while ((line = br.readLine()) != null){
			if (line.contains(Customs.annotationPatternBeginning)){
				foundAnnotationBegining=true;
				foundAnnotationEnd=false;
				
				featureToCodeMapping.put(counter,featureName);
				featureName=line.split("pv:hasFeature")[1];
				
			}else{
				if (line.contains(Customs.annotationPatternEnd)){
					foundAnnotationBegining=false;
					foundAnnotationEnd=true;
				
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
		return featureToCodeMapping;
	}
}
