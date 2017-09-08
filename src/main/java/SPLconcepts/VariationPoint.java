package SPLconcepts;

import java.util.ArrayList;

public class VariationPoint {
	int idVP;
	String expression;
	String body;
	int lineInit;
	int lineEnd;
	ArrayList<Feature> features;
	
	 
	
	public VariationPoint(int idVP, String expression, String body,
			int lineInit, int lineEnd, ArrayList<Feature> features) {
		super();
		this.idVP = idVP;
		this.expression = expression;
		this.body = body;
		this.lineInit = lineInit;
		this.lineEnd = lineEnd;
		this.features=features;
	}
	
	
	public VariationPoint(int id, String expression, int lineInit) {
		this.idVP = id;
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

	
}
