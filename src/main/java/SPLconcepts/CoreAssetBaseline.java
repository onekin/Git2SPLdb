package SPLconcepts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.jgit.revwalk.RevCommit;
import org.repodriller.domain.Commit;

import utils.Utils;

public class CoreAssetBaseline{//extends Commit
	
	String id;
	Commit commit;
	Date releaseDate;
	RevCommit revCommit;
	public ArrayList<Feature> features = new ArrayList<Feature>();
	public ArrayList<SourceCodeFile> coreAssetFiles = new ArrayList<SourceCodeFile>();
	
	public CoreAssetBaseline (Commit c, Date calendar, String tag){
		this.commit = c;
		this.releaseDate = calendar;
		//Parsing the tag: pattern origin/
		System.out.println("TAG:"+tag);
		
		this.id = Utils.extractRefName(tag, 2);
	
	}
	
	public CoreAssetBaseline(RevCommit revCommit, int commitTime, String refName) {
		this.revCommit = revCommit;
		this.releaseDate = new Date(commitTime);
		this.id = refName; // it is not pased: Utils.extractRefName(refName, 2); // 
		this.commit = null;//utils.RefUtils.getRepoDrillerCommit(revCommit);
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

	public RevCommit getRevCommit() {
		return revCommit;
	}

	public void setRevCommit(RevCommit revCommit) {
		this.revCommit = revCommit;
	}

}
