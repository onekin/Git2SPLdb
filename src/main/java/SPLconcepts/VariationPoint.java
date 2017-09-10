package SPLconcepts;

import java.util.ArrayList;

public class VariationPoint {
	int idVP;
	String expression;
	String body;
	int lineInit;
	int lineEnd;
	ArrayList<Feature> features;
	ArrayList<String> newFeatures;
	VariationPoint parentVP;
	
	 
	
	public VariationPoint(int idVP, String expression, String body,
			int lineInit, int lineEnd, ArrayList<Feature> features, VariationPoint parentVP, ArrayList<String> newFeatures) {
		super();
		this.idVP = idVP;
		this.expression = expression;
		this.body = body;
		this.lineInit = lineInit;
		this.lineEnd = lineEnd;
		this.features=features;
		this.parentVP = parentVP;
	}
	
	
	public VariationPoint(int id, String expression, int lineInit, VariationPoint parentVP) {
		this.idVP = id;
		this.expression = expression;
		this.lineInit = lineInit;
		this.parentVP = parentVP;
		this.body = "";
	}


	public int getIdVP() {
		return idVP;
	}
	public void setIdVP(int idVP) {
		this.idVP = idVP;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getLineInit() {
		return lineInit;
	}
	public void setLineInit(int lineInit) {
		this.lineInit = lineInit;
	}
	public int getLineEnd() {
		return lineEnd;
	}
	public void setLineEnd(int lineEnd) {
		this.lineEnd = lineEnd;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}


	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}


	public VariationPoint getParentVP() {
		return parentVP;
	}


	public void setParentVP(VariationPoint parentVP) {
		this.parentVP = parentVP;
	}


	public ArrayList<String> getNewFeatures() {
		return newFeatures;
	}


	public void setNewFeatures(ArrayList<String> newFeatures) {
		this.newFeatures = newFeatures;
	}

	
}
