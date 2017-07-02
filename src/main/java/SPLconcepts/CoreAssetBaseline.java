package SPLconcepts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.repodriller.domain.Commit;

public class CoreAssetBaseline{//extends Commit
	
	
	Commit commit;
	String tag;
	Calendar releaseDate;
	
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<SourceCodeFile> coreAssetFiles = new ArrayList<SourceCodeFile>();
	
	public CoreAssetBaseline (Commit c, Calendar calendar ){
		commit = c;
		releaseDate = calendar;
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
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Calendar getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Calendar releaseDate) {
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
