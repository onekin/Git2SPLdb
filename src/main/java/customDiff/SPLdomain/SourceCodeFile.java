package customDiff.SPLdomain;


import java.util.ArrayList;
import java.util.HashMap;

public interface SourceCodeFile {

	int id=0; 
	String fileName=null;
	String path = null;
	String content = null;
	int totalLines = 0;
	ArrayList<VariationPoint> variationPoints = null;
	HashMap <Integer,ArrayList<String>> featureToCodeMapping = null;
	
	public String getContent();
	public String getPath();
	public String getFileName();
	public int getId();
	public int getTotalLines();
	public ArrayList<Feature> getFeatureList();
	public ArrayList<VariationPoint> getVariationPoints();
	public HashMap <Integer,ArrayList<String>> getFeatureToCodeMapping();
	public void setFeatureToCodeMapping (HashMap <Integer, ArrayList<String>>  featureToCodeMapping);
	public void setVariationPoints (ArrayList<VariationPoint> variationPoints);
	public String getRelativePath();
	public void setAbsolute_diff(String computeTheDiffFileBetweenCommits);
	public String getAbsolute_diff();
	public String getRelative_diff();
	public void addVariationPoint(VariationPoint vp_ca);
	public boolean getIsNewAsset();
	
}
