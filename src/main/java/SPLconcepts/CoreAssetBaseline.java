package SPLconcepts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.repodriller.domain.Commit;

import utils.Utils;

public class CoreAssetBaseline{//extends Commit
	
	String id;
	Commit commit;
	Date releaseDate;
	
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<SourceCodeFile> coreAssetFiles = new ArrayList<SourceCodeFile>();
	
	public CoreAssetBaseline (Commit c, Date calendar, String tag){
		this.commit = c;
		this.releaseDate = calendar;
		//Parsing the tag: pattern origin/
		this.id = Utils.extractRefName(tag, 2);
	}
	
	public void addFeature(Feature f){
		if(features==null)
			features=new ArrayList<Feature>();
		features.add(f);
	}
	
	public void addCoreAssetFile(SourceCodeFile ca){
		if(coreAssetFiles==null)
			coreAssetFiles=new ArrayList<SourceCodeFile>();
		coreAssetFiles.add(ca);
	}
	

	public Feature getFeature(int i){		
		return features.get(i);
	}
	
	public SourceCodeFile getCoreAsset(int i){
		return coreAssetFiles.get(i);
	}
	
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
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
	
	public ArrayList<Feature> getFeatures() {
		return features;
	}
	
	public void setFeature(ArrayList<Feature> features) {
		this.features = features;
	}
	
	public ArrayList<SourceCodeFile> getCoreAssetFiles() {
		return coreAssetFiles;
	}
	
	public void setCoreAssetFiles(ArrayList<SourceCodeFile> coreAssetFiles) {
		this.coreAssetFiles = coreAssetFiles;
	}
	
	
}
