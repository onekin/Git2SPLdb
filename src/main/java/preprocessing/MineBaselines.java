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
				ArrayList<SourceCodeFile> cas = new  ArrayList<SourceCodeFile>();	//files for the coreasset baseline
				
					List<RepositoryFile> files = repo.getScm().files();
					System.out.println("Number of files in Baseline:"+files.size());
					SourceCodeFile CAfile = null;
					for(RepositoryFile file : files) { //Mining Files for baseline
						if(file.getFile().getAbsolutePath().contains(Main.pathToWhereCustomizationsAreComputed)){
							CAfile= new CoreAssetFileAnnotated(utils.Utils.getNewCoreAssetId(), file.getFile().getName(),  file.getFile().getPath(), file.getSourceCode(), file.getSourceCode().split("\n").length, CABaseline);
							
							CAfile.setFeatureToCodeMapping(extractCAFileFeaturesAndVPs(CAfile));
							//CABaseline.addCoreAssetFile(CAfile);
							cas.add(CAfile);
						}
						writer.write( CABaseline.getId(),	 CABaseline.getReleaseDate(), file.getFullName(), file.getSourceCode().split("\n").length, CAfile.getFeatureToCodeMapping());
						
						//meter las features del CA en el baseline
						ArrayList<Feature> features = CAfile.getFeatureList();
						
						Iterator<Feature> iterator = features.iterator();
						Feature feature;
						while (iterator.hasNext()){
							feature = iterator.next();
							if(!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(CABaseline.getFeatures(), feature.getName()));
							CABaseline.addFeature(feature);
						}
					
					}//endfor
					
					CABaseline.setCoreAssetFiles(cas);
					Main.spl.addBaseline(CABaseline);
					
					System.out.println("Features in baseline: "+CABaseline.getId()+" are:"+CABaseline.getFeatures().toString());
			} finally {
			
					repo.getScm().reset();
			}	
		}
	}
	


	private HashMap<Integer, ArrayList<String>> extractCAFileFeaturesAndVPs(SourceCodeFile ca) {		
		Collection<ArrayList<String>> values = null;
		Iterator<ArrayList<String>> ite;
		ArrayList<String> valueList;
		Iterator<String> i;
		String variable;
		
		
		HashMap<Integer, ArrayList <String>> map = utils.FeatureAnalysisUtils.extractFeatureMapFromFile(ca); //line-feature map
		System.out.println("Map "+map);

		if (map == null) return null;
		
		ca.setFeatureToCodeMapping(map);
		System.out.println("Map for file "+ca.getId()+ " is " +ca.getFeatureToCodeMapping().toString());
		
		values = map.values();//ArrayList with repered features
		ite= values.iterator();
		while (ite.hasNext()){
			valueList = ite.next();
			i = valueList.iterator();
			while(i.hasNext()){
				variable = i.next();
				Feature f = new Feature(variable, variable);
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(Main.features, variable)){
					Main.features.add(f);
				}
				if (!utils.FeatureAnalysisUtils.isFeatureInFeaturesList( ca.getFeatureList(), variable ) ){
					ca.getFeatureList().add(f);
				}
				
			}
		}
		return map;
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
