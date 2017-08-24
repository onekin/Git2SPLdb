package SPLconcepts;


public class Customization{
	

	String featureModifiedName;
	String operation;//ADDED,REMOVED
	Integer lineOfCodeModified;
	SourceCodeFile productFile;
	SourceCodeFile coreAssetFile;
	boolean isNewAsset;
	boolean isNewFeature;
	ProductRelease inRelease=null;

	public Customization(String featureName, String modType, int lineOfCodeModified, SourceCodeFile productFile, 
			SourceCodeFile coreAssetFile, ProductRelease inRelease, int featureFound, int assetFound) {
		
		this.featureModifiedName=featureName;
		this.operation=modType;
		this.lineOfCodeModified=lineOfCodeModified;
		this. coreAssetFile=coreAssetFile;
		this. productFile=productFile;	
		this.inRelease = inRelease;
		if (featureFound==1) this.isNewFeature = false;
		else this.isNewFeature = true;
			
		if (assetFound==1) this.isNewAsset = false;
		else this.isNewAsset = true;
			
		
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

	public String getFeatureModifiedName() {
		return featureModifiedName;
	}

	public void setFeatureModifiedName(String featureModifiedName) {
		this.featureModifiedName = featureModifiedName;
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


}
