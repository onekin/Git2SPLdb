package customDiff.SPLdomain;

import java.util.ArrayList;

public class CustomizationLine{
	
	String operation;//ADDED,REMOVED
	Integer lineOfCodeModified;
	
	SourceCodeFile productFile;
	SourceCodeFile coreAssetFile;
	VariationPoint vp;//it changes a variation Point
	String wholediff;
	
	boolean isNewAsset;
	boolean hasNewFeature;
	ProductRelease inRelease=null;

	public CustomizationLine( String modType, int lineOfCodeModified, SourceCodeFile productFile, 
			SourceCodeFile coreAssetFile, ProductRelease inRelease, int featureFound, int assetFound, VariationPoint vp) {
		
		this.operation=modType;
		this.lineOfCodeModified=lineOfCodeModified;
		this. coreAssetFile=coreAssetFile;
		this. productFile=productFile;	
		this.inRelease = inRelease;
		this.vp = vp;
		
		if (featureFound >= 1) this.hasNewFeature = true;
		else this.hasNewFeature = false;
			
		if (assetFound==1) this.isNewAsset = false;
		else{
			this.isNewAsset = true;
			System.out.print("NEW ASSETT FOUND\n"+productFile.getFileName());
		} 

	}

	public boolean isHasNewFeature() {
		return hasNewFeature;
	}



	public void setHasNewFeature(boolean hasNewFeature) {
		this.hasNewFeature = hasNewFeature;
	}



	public VariationPoint getVp() {
		return vp;
	}


	public void setVp(VariationPoint vp) {
		this.vp = vp;
	}


	public boolean getIsNewAsset() {
		return isNewAsset;
	}

	public void setIsNewAsset(boolean isNewAsset) {
		this.isNewAsset = isNewAsset;
	}




	public SourceCodeFile getProductFile() {
		return productFile;
	}

	public void setProductFile(SourceCodeFile productFile) {
		this.productFile = productFile;
	}
	public SourceCodeFile getCoreAssetFile() {
		return coreAssetFile;
	}

	public void setCoreAssetFile(SourceCodeFile coreAssetFile) {
		this.coreAssetFile = coreAssetFile;
	}

	public ProductRelease getInRelease() {
		return inRelease;
	}

	public void setInRelease(ProductRelease inRelease) {
		this.inRelease = inRelease;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getLineOfCodeModified() {
		return lineOfCodeModified;
	}

	public void setLineOfCodeModified(Integer numLinesOfCode) {
		this.lineOfCodeModified = numLinesOfCode;
	}

	public ArrayList<Feature> extractFeaturesImpactedByCustomization() {
		ArrayList<Feature> features;
		features = vp.getFeatures();
		VariationPoint parent = vp.getParentVP();
		
		while(parent!=null) {
			features.addAll(parent.getFeatures());
			parent =vp.getParentVP();
		}
		
		return features;
	}


	public String getDiff() {
		return wholediff;
	}
	
	public void setDiff(String diff) {
		this.wholediff = diff;
	}


	public void setNewAsset(boolean isNewAsset) {
		this.isNewAsset = isNewAsset;
	}

	public String getWholediff() {
		return wholediff;
	}

	public void setWholediff(String wholediff) {
		this.wholediff = wholediff;
	}

	
	
}
