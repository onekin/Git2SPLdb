package SPLconcepts;

import java.util.ArrayList;
import java.util.List;

import org.repodriller.domain.DiffLine;

public class CustomizationEffort{

  String customizationId;
  SourceCodeFile productFile;
  SourceCodeFile coreAssetFile;
  String featureNameModified;
  //Feature featureModified;
  
  int addedLines = 0;
  int deletedLines = 0;

  public CustomizationEffort(String customizationId){
	  
  }
  
  
  public CustomizationEffort(String customizationId, String featureNameModified, String modType, int amount,  SourceCodeFile productFile, SourceCodeFile coreAssetFile){
	  this.customizationId = customizationId;
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
		return this.addedLines+this.deletedLines;
	}
	


	public ArrayList<CustomizationEffort> computeFeatureChanged(
			String sourceCodeFile, List<DiffLine> addedNewLines,
			String fileName, String oldPath) {
		// TODO Auto-generated method stub
		return null;
	}
}
