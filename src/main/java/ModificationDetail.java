
public class ModificationDetail{
	
	String featureModifiedName;
	String operation;
	Integer numLinesOfCode;
	String fileName;
	String filePath;
	

	
	
	public ModificationDetail(String featureName, String modType, int i, String fileName, String filePath) {
		this.featureModifiedName=featureName;
		this.operation=modType;
		this.numLinesOfCode=i;
		this.fileName=fileName;
		this.filePath=filePath;
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




	public Integer getNumLinesOfCode() {
		return numLinesOfCode;
	}




	public void setNumLinesOfCode(Integer numLinesOfCode) {
		this.numLinesOfCode = numLinesOfCode;
	}


}
