package SPLconcepts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

import deprecated.Customs;



public class CoreAssetFileAnnotated implements SourceCodeFile{
	String id; //this is the full name of the file (e.g. /Users/user/Documents/repo/src/file1.txt)
	String fileName;
	String path;
	String content;
	int totalLines;


	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	
	public CoreAssetFileAnnotated(String id, String fileName, String path, String content, int totalLines) {

		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines; 
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
