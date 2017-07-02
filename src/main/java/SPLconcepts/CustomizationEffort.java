package SPLconcepts;

public class CustomizationEffort{
	
  SourceCodeFile productFile;
  SourceCodeFile coreAssetFile;
  Feature featureModified;
  
  int addedLines;
  int deletedLines;
  int churn;
  
  public CustomizationEffort(SourceCodeFile productFile, SourceCodeFile coreAssetFile ,  Feature featureModified){
	  this.productFile = productFile;
	  this.coreAssetFile = coreAssetFile;
	  this.featureModified = featureModified;
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
	
	public Feature getFeatureModified() {
		return featureModified;
	}
	
	public void setFeatureModified(Feature featureModified) {
		this.featureModified = featureModified;
	}
	
	public int getAddedLines() {
		return addedLines;
	}
	
	public void setAddedLines(int addedLines) {
		this.addedLines = addedLines;
	}
	
	public int getDeletedLines() {
		return deletedLines;
	}
	
	public void setDeletedLines(int deletedLines) {
		this.deletedLines = deletedLines;
	}
	
	public int getChurn() {
		return churn;
	}
	
	public void setChurn(int churn) {
		this.churn = churn;
	}
}
