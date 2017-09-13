package SPLconcepts;

import java.util.ArrayList;

public class Customization{
	
	String operation;//ADDED,REMOVED
	Integer lineOfCodeModified;
	SourceCodeFile productFile;
	SourceCodeFile coreAssetFile;
	VariationPoint vp;//it changes a variation Point
	
	boolean isNewAsset;
	boolean isNewFeature;
	ProductRelease inRelease=null;

	public Customization( String modType, int lineOfCodeModified, SourceCodeFile productFile, 
			SourceCodeFile coreAssetFile, ProductRelease inRelease, int featureFound, int assetFound, VariationPoint vp) {
		
		//this.featureModifiedName=featureName;
		this.operation=modType;
		this.lineOfCodeModified=lineOfCodeModified;
		this. coreAssetFile=coreAssetFile;
		this. productFile=productFile;	
		this.inRelease = inRelease;
		this.vp = vp;
		
		if (featureFound >= 1) this.isNewFeature = true;
		else this.isNewFeature = false;
			
		if (assetFound==1) this.isNewAsset = false;
		else{
			this.isNewAsset = true;
			System.out.print("NEW ASSSSSSETTT \n"+productFile.getFileName());
		} 
			
		
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

	public boolean getIsNewFeature() {
		return isNewFeature;
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

}
