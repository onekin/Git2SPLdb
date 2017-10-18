package customDiff;

import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.CoreAssetFileAnnotated;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.SourceCodeFile;

public class BaselineMiner {

	
	public void mine(String baselineToMine) {//CoreAssetBaseline baseline, String pathToWhereCustomizationsAreComputed
				
		RevCommit baselineCommit = customDiff.RefUtils.getCommitFromRefName(baselineToMine);	
		CoreAssetBaseline baseline = new CoreAssetBaseline(baselineCommit, baselineCommit.getCommitTime(), baselineToMine);
		CustomDiff.spl.addBaseline(baseline);
	
		//This is JUST FOR ONE BASELINE
		ArrayList<SourceCodeFile> files = extractCoreAssetsAndFeaturesFromBaseline(baseline, CustomDiff.pathToWhereCustomizationsAreComputed);
		
		System.out.println("Number of files in Baseline: "+files.size());
		baseline.setCoreAssetFiles(files);
		
		/** Printing baseline contents **/
		System.out.println("Features in baseline: "+baseline.getId()+" are:"+ baseline.getFeatures().toString());
		Iterator<Feature> it = baseline.getFeatures().iterator();
		Feature f;
		while(it.hasNext()){
			f = it.next();
			System.out.println(f.getName());
		}
	}
	
	
	public ArrayList<SourceCodeFile> extractCoreAssetsAndFeaturesFromBaseline(CoreAssetBaseline baseline,  String inPath){	
			
		ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();
			
			try{
				RevCommit revCommit = baseline.getRevCommit();
				Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
				System.out.println("Commit: "+revCommit.getId().getName());
				Git git = new Git(repo);
				git.checkout().setName(revCommit.getId().getName()).call();
				
				RevWalk walk = new RevWalk(repo);
		        RevCommit commit = walk.parseCommit(revCommit.getId());
		        RevTree tree = commit.getTree();
		        
		        TreeWalk treeWalk = new TreeWalk(repo);
		        treeWalk.addTree(tree);
		        treeWalk.setRecursive(true); //FALSE
		        
		        SourceCodeFile CAfile;
		        String  fileContent;
					
		        while (treeWalk.next()) {
		            if(treeWalk.getPathString().contains(inPath)){
		              fileContent = customDiff.utils.FileUtils.readFileContentFromRepository(customDiff.CustomDiff.repositoryPath+"/"+treeWalk.getPathString());
		              
		              //1: create new core asset file
		              CAfile= new CoreAssetFileAnnotated(customDiff.utils.Utils.getNewCoreAssetId(), treeWalk.getNameString(), treeWalk.getPathString(), fileContent, 
			    				  fileContent.split("\n").length, baseline, 
			    				  customDiff.CustomDiff.pathToWhereCustomizationsAreComputed.concat(treeWalk.getPathString().split(customDiff.CustomDiff.pathToWhereCustomizationsAreComputed)[1]));
		              
		              //2: extract features and variation points for the core asset
		              CAfile.setFeatureToCodeMapping(customDiff.VariationPointAnalysisUtils.extractFeaturesAndVPsForCoreAsset(CAfile, baseline));
			    	
		              files.add(CAfile);
		              System.out.println(CAfile.toString());
		              System.out.println(CAfile.getFeatureToCodeMapping());
		              System.out.println(baseline.getId() +" " +baseline.getReleaseDate()+ " " +CAfile.getFileName()+ " "+ CAfile.getFeatureToCodeMapping());
			    					
		              //3: Add features found in the core asset to the baseline
		              ArrayList<Feature> features = CAfile.getFeatureList();
		              Iterator<Feature> iterator = features.iterator();
		              Feature feature;
		              while (iterator.hasNext()){
		            	feature = iterator.next();
			    		if(!customDiff.utils.FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(), feature.getName()))
			    			baseline.addFeature(feature);
			    		}
			         }	            	
		        }
				repo.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return files;
			
	}

	

}
