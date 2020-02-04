package org.onekin.customdiff.spldomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.revwalk.RevCommit;


public class CoreAssetBaseline{//extends Commit
	
	String id;
	
	Date releaseDate;
	RevCommit revCommit;
	public List<Feature> features = new ArrayList<>();
	public List<SourceCodeFile> coreAssetFiles = new ArrayList<>();

	private List<Customization> customizations;
		
	private List<FeatureSibling> featureSiblings = new ArrayList<>();
	
	public CoreAssetBaseline(RevCommit revCommit, int commitTime, String refName) {
		this.revCommit = revCommit;
		this.releaseDate = new Date(commitTime);
		this.id = refName;
		
	}

	public void addFeature(Feature f){
		if(features==null)
			features=new ArrayList<>();
		features.add(f);
	}
	
	public void addCoreAssetFile(SourceCodeFile ca){
		if(coreAssetFiles==null)
			coreAssetFiles=new ArrayList<>();
		coreAssetFiles.add(ca);
	}
	

	public Feature getFeature(int i){		
		return features.get(i);
	}
	
	public SourceCodeFile getCoreAsset(int i){
		return coreAssetFiles.get(i);
	}
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String tag) {
		this.id = tag;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public List<Feature> getFeatures() {
		return features;
	}
	
	public void setFeature(List<Feature> features) {
		this.features = features;
	}
	
	public List<SourceCodeFile> getCoreAssetFiles() {
		return coreAssetFiles;
	}
	
	public void setCoreAssetFiles(List<SourceCodeFile> coreAssetFiles) {
		this.coreAssetFiles = coreAssetFiles;
	}

	public RevCommit getRevCommit() {
		return revCommit;
	}

	public void setRevCommit(RevCommit revCommit) {
		this.revCommit = revCommit;
	}

	public void setCustomizations(List<Customization> customizations) {
		this.customizations=customizations;
	}
	
	public List<Customization> getCustomizations() {
		return customizations;
	}

	public List<FeatureSibling> getFeatureSiblings() {
		return featureSiblings;
	}

	public void setFeatureSiblings(List<FeatureSibling> featureSiblings) {
		this.featureSiblings = featureSiblings;
	}

}
