package SPLconcepts;

import java.util.ArrayList;

public class Feature {
	String name;
	String idFeature;
	
	ArrayList<SourceCodeFile> coreassets;
	ArrayList<CoreAssetBaseline> inBaselines;
	
	ArrayList<Feature>  requires;
	ArrayList<Feature>  excludes;
	
	public Feature (String name, String idFeature){
		this.name = name;
		this.idFeature= idFeature;
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
	
	
	
	
	
	
	
}
