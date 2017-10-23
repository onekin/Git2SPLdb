package customDiff.SPLdomain;

import java.util.ArrayList;
import java.util.HashMap;



public class ProductAssetFileAnnotated implements SourceCodeFile{
	
	int id;
	String fileName;
	String path;
	String relativePath;
	String content;
	int totalLines;
	ProductRelease fromPRelease; 
	String relative_diff;
	String absolute_diff;

	HashMap <Integer,ArrayList<String>> featureToCodeMapping = new HashMap <Integer,ArrayList<String>>();
	ArrayList<Feature> featureList= new ArrayList<Feature>();
	ArrayList<VariationPoint> variationPoints = new ArrayList<VariationPoint>();
	
	public ProductAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines, ProductRelease pr , String relativepath) {
		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines;
		this.fromPRelease = pr;
		this.relativePath = relativepath;
	}


	public String getRelativePath() {
		return relativePath;
	}


	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}


	public ArrayList<VariationPoint> getVariationPoints() {
		return variationPoints;
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


	public HashMap <Integer,ArrayList<String>> getFeatureToCodeMapping() {
		return featureToCodeMapping;
	}


	public void setFeatureToCodeMapping(
			HashMap <Integer,ArrayList<String>> featureToCodeMapping) {
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


	public void setVariationPoints(ArrayList<VariationPoint> variationPoints) {
		this.variationPoints = variationPoints;
	}


	public String getRelative_diff() {
		return relative_diff;
	}


	public void setRelative_diff(String relative_diff) {
		this.relative_diff = relative_diff;
	}


	public String getAbsolute_diff() {
		return absolute_diff;
	}


	public void setAbsolute_diff(String absolute_diff) {
		this.absolute_diff = absolute_diff;
	}

	public void addVariationPoint(VariationPoint vp) {
		this.variationPoints.add(vp);
		
	}
}
