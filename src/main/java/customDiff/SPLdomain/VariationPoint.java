package customDiff.SPLdomain;

import java.util.ArrayList;
import java.util.Set;

public class VariationPoint {
	int idVP;
	private String expression;
	private String body;
	private int lineInit;
	private int lineEnd;
	private Set<Feature> features;
	private ArrayList<Feature> newFeatures;
	private VariationPoint parentVP;
	private FeatureSibling featureSibling;

	/*
	 * 
	 * public VariationPoint(int idVP, String expression, String body, int lineInit,
	 * int lineEnd, ArrayList<Feature> features, VariationPoint parentVP,
	 * ArrayList<String> newFeatures) { super(); this.idVP = idVP; this.expression =
	 * expression; this.body = body; this.lineInit = lineInit; this.lineEnd =
	 * lineEnd; this.features=features; this.parentVP = parentVP; }
	 */

	public VariationPoint(int id, String expression, int lineInit, VariationPoint parentVP) {
		this.idVP = id;
		this.expression = expression;
		this.lineInit = lineInit;
		this.parentVP = parentVP;
		this.body = "";
	}

	public VariationPoint(int id, String expression, int lineInit, VariationPoint parentVP,
			FeatureSibling featureSibling) {
		this.idVP = id;
		this.expression = expression;
		this.lineInit = lineInit;
		this.parentVP = parentVP;
		this.body = "";
		this.featureSibling = featureSibling;
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

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		System.out.println("Setting features for VP:" + expression);
		System.out.println(features.toString());
		this.features = features;
	}

	public void addFeatures(ArrayList<Feature> features) {
		this.features.addAll(features);
	}

	public void addFeature(Feature feature) {
		this.features.add(feature);
	}

	public VariationPoint getParentVP() {
		return parentVP;
	}

	public void setParentVP(VariationPoint parentVP) {
		this.parentVP = parentVP;
	}

	public ArrayList<Feature> getNewFeatures() {
		return newFeatures;
	}

	public void setNewFeatures(ArrayList<Feature> newFeatures) {
		this.newFeatures = newFeatures;
	}

	public String getVPFullExpression(VariationPoint vp) {
		if (vp == null)
			return "No VP";

		String header = vp.getExpression().replace("PV:IFCOND", "");
		header = header.replace(" ", "");
		header = header.replace("//", "");
		if (vp.getParentVP() != null) {
			header = header + " AND " + getVPFullExpression(vp.getParentVP());
		}
		return header;
	}

	public void setFeatureSibling(FeatureSibling featureSibling) {
		this.featureSibling = featureSibling;
	}

	public FeatureSibling getFeatureSibling() {
		return featureSibling;
	}
}
