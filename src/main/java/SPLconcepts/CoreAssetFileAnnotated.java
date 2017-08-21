package SPLconcepts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

import OldMain.Customs;


public class CoreAssetFileAnnotated implements SourceCodeFile{

	File file;
	String fileName;
	String path;
	String content;
	RepositoryFile repoFile;

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	
	public CoreAssetFileAnnotated(File file, RepositoryFile repoFile, String fileName, String path, String content) {
		//super(file, fileName, path, content);
		this.file = file;
		this.repoFile =repoFile;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
	}
	

	


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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}





	public void setFileName(String fileName) {
		this.fileName = fileName;
	}





	public String getPath() {
		return path;
	}





	public void setPath(String path) {
		this.path = path;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public RepositoryFile getRepoFile() {
		return repoFile;
	}





	public void setRepoFile(RepositoryFile repoFile) {
		this.repoFile = repoFile;
	}





	public HashMap<Integer, String> getFeatureToCodeMapping() {
		return featureToCodeMapping;
	}





	public void setFeatureToCodeMapping(
			HashMap<Integer, String> featureToCodeMapping) {
		this.featureToCodeMapping = featureToCodeMapping;
	}





	public ArrayList<String> getFeatureList() {
		return featureList;
	}





	public void setFeatureList(ArrayList<String> featureList) {
		this.featureList = featureList;
	}
	
	
}
