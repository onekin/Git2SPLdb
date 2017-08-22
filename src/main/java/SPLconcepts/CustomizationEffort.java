package SPLconcepts;

import java.util.ArrayList;
import java.util.List;

import org.repodriller.domain.DiffLine;

public class CustomizationEffort{

  int customizationId;
  SourceCodeFile productFile;
  SourceCodeFile coreAssetFile;
  String featureNameModified;
  ProductRelease inRelease=null;
  //Feature featureModified;
  
  int addedLines = 0;
  int deletedLines = 0;
  int keptLines = 0;

	  public CustomizationEffort(int customizationId, ProductRelease inRelease, String featureNameModified, SourceCodeFile productFile, SourceCodeFile coreAsseCodeFile){
		  this.customizationId = customizationId;
		  this.inRelease = inRelease;
		  this.productFile = productFile;
		  this.coreAssetFile = coreAsseCodeFile;
		  this.featureNameModified = featureNameModified;
	  }
	  	
	public int getKeptLines() {
		return keptLines;
	}
	
	
	
	public void setKeptLines(int keptLines) {
		this.keptLines = keptLines;
	}



		public int getCustomizationId() {
		return customizationId;
	}


	public void setCustomizationId(int customizationId) {
		this.customizationId = customizationId;
	}
	
	public ProductRelease getInRelease() {
		return inRelease;
	}

	
	public void setInRelease(ProductRelease inRelease) {
		this.inRelease = inRelease;
	}
	
	public void  addAddedLines(int amount){
		addedLines = addedLines +amount;
	}
	
	public void  addDeletedLines(int amount){
		deletedLines = deletedLines +amount;
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
