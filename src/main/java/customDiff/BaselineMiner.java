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

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Feature;
import SPLconcepts.SourceCodeFile;

public class BaselineMiner {
	
	

	public ArrayList<SourceCodeFile> mine(CoreAssetBaseline baseline, String pathToWhereCustomizationsAreComputed) {
		
		 ArrayList<SourceCodeFile> files = extractCoreAssetsAndFeaturesFromBaseline(baseline, pathToWhereCustomizationsAreComputed);
		 
		 return files;
	}
	
	
	
	public ArrayList<SourceCodeFile> extractCoreAssetsAndFeaturesFromBaseline( CoreAssetBaseline baseline,  String inPath){	
			
		ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();
			
			try{
				RevCommit revCommit = baseline.getRevCommit();
				Repository repo = new FileRepository(customDiff.CustomDiff.productRepo+"/.git");
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
		              fileContent = utils.FileUtils.readFileContentFromRepository(customDiff.CustomDiff.productRepo+"/"+treeWalk.getPathString());
		              
		              //1: create new core asset file
		              CAfile= new CoreAssetFileAnnotated(utils.Utils.getNewCoreAssetId(), treeWalk.getNameString(), treeWalk.getPathString(), fileContent, 
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
			    		if(!utils.FeatureAnalysisUtils.isFeatureInFeaturesList(baseline.getFeatures(), feature.getName()))
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
