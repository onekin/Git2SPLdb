package customDiff.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.repodriller.domain.ChangeSet;
import org.repodriller.domain.Commit;
import org.repodriller.scm.SCMRepository;




public class Utils {
	
	static int customizationCounter = 0;
	static int coreAssetFileCounter = 0;
	static int productAssetFileCounter = 0;
	static int productCounter = 0;
	static int vpCounter = 0;


	
	static public ArrayList<String> getCommitHashesBetweenTwoTags(String baselineTag, String productReleaseTag) {
		//this snipped works fine
		try{
		  Repository repo = new FileRepository(preprocessing.deprecated.Main.productRepo+"/.git");
		  Git git = new Git(repo);
		  Ref refFrom = repo.getRef(baselineTag);
		  Ref refTo = repo.getRef(productReleaseTag);
		
		  ArrayList<String>  commitHashList = new  ArrayList<String>();
		  
		Iterable<RevCommit> list = git.log().addRange(refFrom.getObjectId(), refTo.getObjectId()).call();
		Iterator<RevCommit> it = list.iterator();
		while(it.hasNext()){
			RevCommit c = it.next();
			System.out.println(c.getName());
			commitHashList.add(c.getName());
		}
		return commitHashList;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}		
	}
	
	static public ChangeSet convertHashToCommit(String hash, SCMRepository scmRepository){
			
			List<ChangeSet> all = scmRepository.getScm().getChangeSets();
	
			for(ChangeSet cs : all) {
				if(hash.equals(cs.getId())) {
					return cs;
				}
		}
		return null;
	}
	
	static public String getTagForACommitHash(String hash){
		Repository repository;
		try {
			repository = new FileRepository(preprocessing.deprecated.Main.productRepo+"/.git");
			Git git = new Git(repository);
		
			java.util.List<Ref> list = git.tagList().call();	//all the tags in the repository
			for (Ref tag : list) {//for each tag found
				ObjectId object = tag.getObjectId(); 
			    if (object.getName().equals(hash) )
			    	return tag.getName();
			}
			return null;
		
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	static public  String extractRefName(String refname, int extractIndex){
		//refname e.g.= refs/tags/Baseline-v1.0
	
		String[] parsed = refname.split("/");
		if (parsed[extractIndex]!=null)
		  return parsed[extractIndex];
		else return refname;
		
	}
		  
	static public String computeTheDiffFileBetweenCommits(String hash_old, String hash_new, String filePath) {
		try {
			//git diff hash_old hash_new filename
			Repository repository = new FileRepository(preprocessing.deprecated.Main.productRepo+"/.git");
			Git git = new Git(repository);		//compute the diff w.r.t the baseline it was derived from
			

			ObjectId oldCommitObj, newCommitObj;
			oldCommitObj = repository.resolve(hash_old);
			newCommitObj = repository.resolve(hash_new);
			
			RevWalk revWalk = new RevWalk( repository );
			RevCommit oldCommit = revWalk.parseCommit( oldCommitObj );
			RevCommit  newCommit  = revWalk.parseCommit( newCommitObj );
		
			ObjectReader reader = git.getRepository().newObjectReader();
			CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
	
			
			oldTreeIter.reset( reader,oldCommit.getTree() );
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			newTreeIter.reset( reader, newCommit.getTree() );
			
			DiffFormatter diffFormatter = new DiffFormatter( System.out );
			diffFormatter.setRepository( git.getRepository() );
			
			List<org.eclipse.jgit.diff.DiffEntry> entries = diffFormatter.scan( newTreeIter, oldTreeIter );
			Iterator<org.eclipse.jgit.diff.DiffEntry> it = entries.iterator();
			while (it.hasNext()) {
				org.eclipse.jgit.diff.DiffEntry entry = it.next();
				System.out.println("entry.getNewPath()=" +entry.getNewPath());
				System.out.println("filePath:"+filePath);
				
				if (entry.getNewPath().equals(filePath)){
					System.out.println("ABSOLUTE DIFF");
					System.out.println(entry.toString());
					diffFormatter.format( entry );
					System.out.println("Diff formater DIFF");
					System.out.println(diffFormatter.toString());
					return entry.toString();
				}
						
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return " ";
	}
	

		public static Iterable<RevCommit> getCommitsInBranch(String branchName) {
			
			try {
				Repository repository = new FileRepository(preprocessing.deprecated.Main.productRepo+"/.git");
				Git git = new Git(repository);
				 Iterable<RevCommit> revCommits = git.log()
			                .add(repository.resolve(branchName))
			                .call();
			     return  revCommits;
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
		public static boolean isCommitInBranch(Commit newerCommit, String branchName) {
			Iterable <RevCommit> commitsForBranch = Utils.getCommitsInBranch(branchName);
			Iterator <RevCommit> it = commitsForBranch.iterator();
			
			while(it.hasNext()){
				RevCommit co = it.next();
				if(co.getName().compareTo(newerCommit.getHash())==0)
					return true;
			}
			
			return false;
		}

		static public int getNewCustomizationId(){
			customizationCounter ++;
			return customizationCounter;
		}
		
		static public int getNewProductId(){
			productCounter ++;
			return productCounter;
		}
		
		public static int getNewProductAssetId() {
			 productAssetFileCounter++;
			return productAssetFileCounter;
		}

		public static int getNewCoreAssetId() {
			coreAssetFileCounter ++;
			return coreAssetFileCounter;
		}
		
		public static int getVPId() {
			vpCounter++;
			return vpCounter;
		}

		public static String extractRefNameForProduct(String name) {
			//ref name = 
			String aux = extractRefName(name, 2);
			return aux.split("-v")[0];
			
		}
		
		
	
}
