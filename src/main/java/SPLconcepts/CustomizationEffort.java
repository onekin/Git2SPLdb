package SPLconcepts;

public class CustomizationEffort{

  SourceCodeFile productFile;
  SourceCodeFile coreAssetFile;
  String featureNameModified;
  //Feature featureModified;
  
  int addedLines = 0;
  int deletedLines = 0;
  int churn = 0;
  
  public CustomizationEffort(SourceCodeFile productFile, SourceCodeFile coreAssetFile,  String featureNameModified){
	  this.productFile = productFile;
	  this.coreAssetFile = coreAssetFile;
	  this.featureNameModified = featureNameModified;
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
	
	public String getFeatureNameModified() {
		return featureNameModified;
	}
	
	public void setFeatureNameModified(String featureModified) {
		this.featureNameModified = featureModified;
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
