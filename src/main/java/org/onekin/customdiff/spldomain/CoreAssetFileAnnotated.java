package org.onekin.customdiff.spldomain;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CoreAssetFileAnnotated implements SourceCodeFile{
	int id; //this is the full name of the file (e.g. /Users/user/Documents/repo/src/file1.txt)
	String fileName;
	String path;
	String content;
	int totalLines;
	CoreAssetBaseline fromBaseline;
	String relativePath;


	HashMap <Integer,ArrayList<String>> featureToCodeMapping = new HashMap<Integer, ArrayList<String>>();
	ArrayList<Feature> featureList= new ArrayList<Feature>();
	List<VariationPoint> variationPoints;
	
	
	public CoreAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines, CoreAssetBaseline CABaseline, String relativePath) {

		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines; 
		this.fromBaseline = CABaseline ;
		this.relativePath = relativePath;
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

	public CoreAssetBaseline getFromBaseline() {
		return fromBaseline;
	}

	public void setFromBaseline(CoreAssetBaseline fromBaseline) {
		this.fromBaseline = fromBaseline;
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

	public void setFeatureToCodeMapping(HashMap<Integer, ArrayList<String>> featureToCodeMapping) {
		this.featureToCodeMapping = featureToCodeMapping;
	}

	@Override
	public void setVariationPoints(List<VariationPoint> variationPoints) {
		this.variationPoints = variationPoints;
	}

	public ArrayList<Feature> getFeatureList() {
		return featureList;
	}

	public List<VariationPoint> getVariationPoints() {

		return variationPoints;
	}

	public HashMap <Integer,ArrayList<String>> getFeatureToCodeMapping() {
		return featureToCodeMapping;
	}
	public void setVariationPoints(ArrayList<VariationPoint> variationPoints) {
		this.variationPoints = variationPoints;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public void setFeatureList(ArrayList<Feature> featureList) {
		this.featureList = featureList;
	}

	public void setAbsolute_diff(String diff) throws UnsupportedOperationException{
		throw new UnsupportedOperationException("Method not supported");
		
	}

	public String getAbsolute_diff() {
		// TODO Auto-generated method stub
		return "";
	}

	public String getRelative_diff() {
		// TODO Auto-generated method stub
		return "";
	}
	public void addVariationPoint(VariationPoint vp) {
		this.variationPoints.add(vp);
		
	}

	@Override
	public boolean getIsNewAsset() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
