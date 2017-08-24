package SPLconcepts;

import java.util.ArrayList;
import java.util.HashMap;



public class ProductAssetFileAnnotated implements SourceCodeFile{
	
	int id;
	String fileName;
	String path;
	String content;
	int totalLines;
	ProductRelease fromPRelease; 

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<Feature> featureList= new ArrayList<Feature>();
	
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


	public ArrayList<Feature> getFeatureList() {
		return featureList;
	}


	public void setFeatureList(ArrayList<Feature> featureList) {
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
