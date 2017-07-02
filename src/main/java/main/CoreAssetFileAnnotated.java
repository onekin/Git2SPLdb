package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

import main.Customs;


public class CoreAssetFileAnnotated {

	File file;
	String fileName;
	String path;
	String content;
	//String hash;
	RepositoryFile repoFile;

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	

	public HashMap <Integer,String>  readFileToGetFeatureMap(File file) throws IOException {//Read the file lineByLine
		FileInputStream fis = new FileInputStream(file);
	 
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
		return featureToCodeMapping;
	}
}
