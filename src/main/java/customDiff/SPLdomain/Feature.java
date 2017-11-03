package customDiff.SPLdomain;

import java.util.ArrayList;

public class Feature {
	String name;
	String idFeature;
	
	ArrayList<SourceCodeFile> coreassets;
	ArrayList<CoreAssetBaseline> inBaselines;
	boolean isNew = false;
	ArrayList<Feature>  requires;
	ArrayList<Feature>  excludes;
	String parentFeatureName;
	
	public Feature (String name, String idFeature){
		this.name = name;
		this.idFeature= idFeature;
	}
	
	public Feature (String name, String idFeature, boolean isNew){
		this.name = name;
		this.idFeature= idFeature;
		this.isNew = isNew;
	}
	
	public Feature (String name, String idFeature, boolean isNew, String parentFeatureName){
		this.name = name;
		this.idFeature= idFeature;
		this.isNew = isNew;
		this.parentFeatureName = parentFeatureName;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdFeature() {
		return idFeature;
	}
	public void setIdFeature(String idFeature) {
		this.idFeature = idFeature;
	}

	public ArrayList<CoreAssetBaseline> getInBaselines() {
		return inBaselines;
	}
	public void setInBaselines(ArrayList<CoreAssetBaseline> inBaselines) {
		this.inBaselines = inBaselines;
	}
	
	public ArrayList<Feature> getRequires() {
		return requires;
	}

	public ArrayList<Feature> getExcludes() {
		return excludes;
	}
	
	public String toString(){
		return name;
	}

	public boolean getIsNew() {
		return isNew;
	}

	public int getIsNewToInt() {
		if(isNew) return 1;
		else return 0;
	}

	public String getParentFeatureName() {
		return parentFeatureName;
	}

	public void setParentFeatureName(String parentFeatureName) {
		System.out.println("Feature "+name+" has parent: "+parentFeatureName);
		this.parentFeatureName = parentFeatureName;
	}
	

}
