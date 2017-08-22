package SPLconcepts;


public class CustomizationDetail{
	

	String featureModifiedName;
	String operation;//ADDED,REMOVED
	Integer lineOfCodeModified;

	
	SourceCodeFile productFile;
	SourceCodeFile coreAssetFile;
	ProductRelease inRelease=null;

	public CustomizationDetail(String featureName, String modType, int lineOfCodeModified, SourceCodeFile productFile, SourceCodeFile coreAssetFile, ProductRelease inRelease) {
		
		this.featureModifiedName=featureName;
		this.operation=modType;
		this.lineOfCodeModified=lineOfCodeModified;
		this. coreAssetFile=coreAssetFile;
		this. productFile=productFile;	
		this.inRelease = inRelease;
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
