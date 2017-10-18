package preprocessing.deprecated;


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

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.CoreAssetFileAnnotated;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;
import customDiff.utils.Utils;




public class MineBaselines implements CommitVisitor {

	boolean headerFlag=false;

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
	//for each commit in master (i.e. a baseline version; get all core asset files there)
		/*	
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Baseline-ID","Release date","CoreAssetName");
		}
				
		if(commit.getBranches().contains(Main.coreAssetsBranchPatternName)) {		
			try{
				
				
				repo.getScm().checkout(commit.getHash());
				String baselineTag = Utils.getTagForACommitHash(commit.getHash());
				if (baselineTag==null) return;
				if (!baselineTag.toLowerCase().contains(Main.coreAssetsReleaseName))//.equals(Baseline-v1.0)
					return;
					
				CoreAssetBaseline CABaseline = new CoreAssetBaseline(commit, new Date (commit.getDate().getTimeInMillis()), baselineTag);
				ArrayList<SourceCodeFile> cas = new  ArrayList<SourceCodeFile>();	//files for the coreasset baseline
				
					List<RepositoryFile> files = repo.getScm().files();
					System.out.println("Number of files in Baseline:"+files.size());
					SourceCodeFile CAfile = null;
					
					for(RepositoryFile file : files) { //Mining Files for baseline
						if(file.getFile().getAbsolutePath().contains(Main.pathToWhereCustomizationsAreComputed)){
							CAfile= new CoreAssetFileAnnotated(utils.Utils.getNewCoreAssetId(), file.getFile().getName(),  file.getFile().getPath(), file.getSourceCode(), 
									file.getSourceCode().split("\n").length, CABaseline,
									Main.pathToWhereCustomizationsAreComputed.concat(file.getFile().getAbsolutePath().split(Main.pathToWhereCustomizationsAreComputed)[1]));
							
							CAfile.setFeatureToCodeMapping(extractCAFileFeaturesAndVPs(CAfile, CABaseline));
							//CABaseline.addCoreAssetFile(CAfile);
							cas.add(CAfile);
							System.out.println(CAfile.toString());
							System.out.println(CAfile.getFeatureToCodeMapping());
							writer.write( CABaseline.getId(),	 
									CABaseline.getReleaseDate(), 
									file.getFullName(), 
									file.getSourceCode().split("\n").length, 
									CAfile.getFeatureToCodeMapping());
							
							//meter las features del CA en el baseline
							ArrayList<Feature> features = CAfile.getFeatureList();
							
							Iterator<Feature> iterator = features.iterator();
							Feature feature;
							while (iterator.hasNext()){
								feature = iterator.next();
								if(!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(CABaseline.getFeatures(), feature.getName()))
									CABaseline.addFeature(feature);
							}
						}
						
					
					}//endfor
					CABaseline.setCoreAssetFiles(cas);
					Main.spl.addBaseline(CABaseline);
					System.out.println("Features in baseline: "+CABaseline.getId()+" are:"+CABaseline.getFeatures().toString());
			} finally {
			
					repo.getScm().reset();
			}	
		}*/
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}
	


	/*private HashMap<Integer, ArrayList<String>> extractCAFileFeaturesAndVPs(SourceCodeFile ca, CoreAssetBaseline baseline) {		
		Collection<ArrayList<String>> values = null;
		Iterator<ArrayList<String>> ite;
		ArrayList<String> valueList;
		Iterator<String> i;
		String variable;
		
		
		HashMap<Integer, ArrayList <String>> map = utils.FeatureAnalysisUtils.extractFeatureMapFromFile(ca,baseline); //line-feature map
		ArrayList<VariationPoint> vps = utils.FeatureAnalysisUtils.extractVPsFromFile(ca, baseline);
		
		System.out.println("Map "+map);
		System.out.println("VPs "+vps);

		if (map == null) return null;
		
		ca.setFeatureToCodeMapping(map);
		ca.setVariationPoints(vps);
		
		System.out.println("CoreAsset Mining. Feature-to-code Map for file "+ca.getId()+ " is " +ca.getFeatureToCodeMapping().toString());
		System.out.println("CoreAsset Mining. VPs for file "+ca.getId()+ " is " +ca.getVariationPoints().toString());
		
		
		values = map.values();//ArrayList with repeted features
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
*/
}
