package SPLconcepts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

import deprecated.Customs;


public class ProductAssetFileAnnotated implements SourceCodeFile{
	
	int id;
	String fileName;
	String path;
	String content;
	int totalLines;
	ProductRelease fromPRelease; 

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	
	public ProductAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines, ProductRelease pr ) {
		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines;
		this.fromPRelease = pr;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTotalLines() {
		return totalLines;
	}


	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}


	public ProductRelease getFromPRelease() {
		return fromPRelease;
	}


	public void setFromPRelease(ProductRelease fromPRelease) {
		this.fromPRelease = fromPRelease;
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


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getContent() {
		
		return content;
	}

	public String getPath() {
	
		return path;
	}

	public String getFileName() {
		
		return fileName;
	}
}
