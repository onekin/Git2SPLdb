package org.onekin.customdiff.spldomain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface SourceCodeFile {

	
	String getContent();
	String getPath();
	String getFileName();
	int getId();
	int getTotalLines();
	List<Feature> getFeatureList();
	List<VariationPoint> getVariationPoints();
	HashMap <Integer,ArrayList<String>> getFeatureToCodeMapping();
	void setFeatureToCodeMapping (HashMap <Integer, ArrayList<String>>  featureToCodeMapping);
	void setVariationPoints (List<VariationPoint> variationPoints);
	String getRelativePath();
	void setAbsolute_diff(String computeTheDiffFileBetweenCommits);
	String getAbsolute_diff();
	String getRelative_diff();
	void addVariationPoint(VariationPoint vp_ca);
	boolean getIsNewAsset();
	
}
