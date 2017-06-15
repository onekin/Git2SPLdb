
public class FeatureModificationDetail {
	
	String featureModifiedName;
	String operation;
	Integer numLinesOfCode;
	

	
	
	public FeatureModificationDetail(String featureName, String modType, int i) {
		this.featureModifiedName=featureName;
		this.operation=modType;
		this.numLinesOfCode=i;
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
