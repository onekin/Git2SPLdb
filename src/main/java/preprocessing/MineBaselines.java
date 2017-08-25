package preprocessing;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.repodriller.domain.Commit;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;


import utils.Utils;

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Feature;
import SPLconcepts.SourceCodeFile;

public class MineBaselines implements CommitVisitor {

	boolean headerFlag=false;

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
	//for each commit in master (i.e. a baseline version; get all core asset files there)
		
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Baseline-ID","Release date","CoreAssetName");
		}
				
		if(commit.getBranches().contains(Main.coreAssetsBranchPatternName)) {		
			try{
				
				repo.getScm().checkout(commit.getHash());
				String baselineTag = Utils.getTagForACommitHash(commit.getHash());
				CoreAssetBaseline CABaseline = new CoreAssetBaseline(commit, new Date (commit.getDate().getTimeInMillis()), baselineTag);
				Main.spl.addBaseline(CABaseline);
				
					List<RepositoryFile> files = repo.getScm().files();
					
					SourceCodeFile CAfile;
					for(RepositoryFile file : files) {//Mining Files for baseline
						if(file.getFile().getAbsolutePath().contains(Main.pathToWhereCustomizationsAreComputed)){
							CAfile= new CoreAssetFileAnnotated(utils.Utils.getNewCoreAssetId(), file.getFile().getName(),  file.getFile().getPath(), file.getSourceCode(), file.getSourceCode().split("\n").length, CABaseline);
							CABaseline.addCoreAssetFile(CAfile);
							mineFeaturesInBaseline(CABaseline);
						}
				
					
					writer.write(
							CABaseline.getId(),	
							CABaseline.getReleaseDate(),
							file.getFullName(),
							file.getSourceCode().split("\n").length);
					
				}
			} finally {
					repo.getScm().reset();
			}	
		}
	}
	
	private void mineFeaturesInBaseline(CoreAssetBaseline baseline) {
		
		 ArrayList<SourceCodeFile> listFiles = baseline.getCoreAssetFiles();
		Iterator<SourceCodeFile> it = listFiles.iterator();
		Collection<String> values = null;
		SourceCodeFile ca;
		Iterator<String> ite;
		String value;
		
		while(it.hasNext()){
			ca = it.next();
			
			HashMap<Integer, String> map = utils.FeatureAnalysisUtils.extractFeatureMapFromCoreAsset(ca); //line-feature map
			if (map == null) return;
			
			values = map.values();//all the features in the file - repeteated!!
			ite= values.iterator();//all the identified feature
			while (ite.hasNext()){
				value = ite.next();
				Feature f = new Feature(value, value);
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(Main.features, value)){
					Main.features.add(f);
				}
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList( ca.getFeatureList(), value ) ){
					ca.getFeatureList().add(f);
				}
				if(!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(  baseline.getFeatures(), value))
					baseline.addFeature(f);
					
			}
			
		}
		
	}

	public boolean isCommitTagged(SCMRepository repo, Commit c){
	//	repo.
		c.getHash();
		
		return false;
		
	}
	
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
