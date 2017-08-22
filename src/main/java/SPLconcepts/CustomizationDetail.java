package SPLconcepts;


public class CustomizationDetail{
	

	String featureModifiedName;
	String operation;//ADDED,REMOVED,KEPT
	Integer lineOfCodeModified;
	String fileName;
	String filePath;
	CustomizationEffort custom;

	public CustomizationDetail(String featureName, String modType, int lineOfCodeModified, String fileName, String filePath) {
		this.featureModifiedName=featureName;
		this.operation=modType;
		this.lineOfCodeModified=lineOfCodeModified;
		this.fileName=fileName;
		this.filePath=filePath;
	
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public CustomizationEffort getCustom() {
		return custom;
	}

	public void setCustom(CustomizationEffort custom) {
		this.custom = custom;
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
