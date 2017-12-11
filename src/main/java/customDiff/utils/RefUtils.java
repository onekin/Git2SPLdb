package customDiff.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import customDiff.CustomDiff;
import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.CoreAssetFileAnnotated;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.SourceCodeFile;


public class RefUtils {

	public static RevCommit getCommitFromRefName(String tag) {
		RevCommit commit = null;
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");			
			Ref ref = repo.getRef(tag);
		//	System.out.println(ref);
			RevWalk walk = new RevWalk( repo );
			commit = walk.parseCommit( ref.getObjectId() );//find the commit for a 

			repo.close();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}		
		return commit;
	}
	
	public static RevCommit getCommitFromCommitSha (String sha) {
		RevCommit commit = null;
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");			
			ObjectId obj = repo.resolve(sha);
			RevWalk walk = new RevWalk( repo );
			commit = walk.parseCommit( obj );
			repo.close();
			return commit;
			
			}catch(Exception e){
				e.printStackTrace();
			}		
		return commit;
	}
	
	public static RevCommit getCommitFromRef(Ref ref) {
		RevCommit commit = null;
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");			
			
			RevWalk walk = new RevWalk( repo );
			commit = walk.parseCommit( ref.getObjectId() );//find the commit for a 

			repo.close();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}		
		return commit;
	}
	

	public static ArrayList<Ref> listAllTagsFromRepo(){
		ArrayList<Ref> refNames = new ArrayList<Ref>();
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			List<Ref> references = new Git(repo).tagList().call();
			for (Ref ref : references) {
			    refNames.add(ref);
			}
			repo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return refNames;
	}
	
	public static ArrayList<Ref> listTagsFromRepoByPrefix(String prefix){
		ArrayList<Ref> refNames = new ArrayList<Ref>();
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			List<Ref> references = new Git(repo).tagList().call();
			for (Ref ref : references) {
			    if(ref.getName().toLowerCase().startsWith(prefix.toLowerCase()))//System.out.println("Tag: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
			    	refNames.add(ref);
			}
			repo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return refNames;
	}
	
	public static ArrayList<Ref> listTagsFromRepoByContains(String substring){
		ArrayList<Ref> refNames = new ArrayList<Ref>();
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			List<Ref> references = new Git(repo).tagList().call();
			for (Ref ref : references) {
			    if(ref.getName().toLowerCase().contains(substring.toLowerCase()))//System.out.println("Tag: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
			    	refNames.add(ref);
			}
			repo.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return refNames;
	}
	
	public static Map<String, Ref> getAllTagsFromRepo(){
		Map<String, Ref> refMap = null;
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			refMap = repo.getTags();
			repo.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return refMap;
	}
	
	
	public static boolean isRefFirstDerivedFromBaseline(RevCommit baselineCommit, Ref productRelease) {
		//if there is a commit in between baselineCommit&productRelease, which has a baseline tag, or it is in the baseline release branch
		//then the product release has a newer origin baseline
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			RevWalk walk = new RevWalk( repo );
			Git git = new Git(repo);
			RevCommit startCommit = walk.parseCommit( repo.resolve( baselineCommit.getName() ) );
			RevCommit endCommit  = walk.parseCommit( repo.resolve( productRelease.getName() ) );
			
			Iterable<RevCommit> commitsInBetween = git.log().addRange(startCommit, endCommit).call();//get the commits between both tags			
			Iterator<RevCommit> it = commitsInBetween.iterator();
			RevCommit commit;
			while(it.hasNext()){
				commit = it.next();
				if(isCommitInBranch(commit, CustomDiff.coreAssetsBranchName) ||isCommitBaselineRelease(commit) || commit.getCommitTime() < baselineCommit.getCommitTime()){
					//System.out.println(productRelease.getName()+" NOT derived from "+baselineCommit.getName());
					return false;
				}
			}
			//System.out.println(productRelease.getName()+" derived from "+baselineCommit.getName());
			repo.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}	
	
	private static boolean isCommitBaselineRelease(RevCommit commit) {
		ArrayList<Ref> baselineTags = listTagsFromRepoByPrefix("refs/tags/"+CustomDiff.coreAssetsReleaseTagPrefix);	//get all baseline tags
		Iterator<Ref> it = baselineTags.iterator();
		Ref ref;
		while(it.hasNext()){
			ref = it.next();
			if (getCommitFromRef(ref).getName().equals(commit.getName()))
				return true;
		}
		return false;
	}

	public static boolean isCommitInBranch(RevCommit commit, String branchName){
		// git branch --contains <commit>
		try{
			//Repository repo = new FileRepository(customDiff.CustomDiff.productRepo+"/.git");
			//new Git(repo).branchList().
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return false;
	}


	public static Iterable<RevCommit> getCommitsBetweenCommits(RevCommit baselineCommit, RevCommit prCommit) {
		try {
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			RevWalk walk = new RevWalk( repo );
			Git git = new Git(repo);
			RevCommit startCommit = walk.parseCommit( repo.resolve( baselineCommit.getName() ) );
			RevCommit endCommit  = walk.parseCommit( repo.resolve( prCommit.getName() ) );
			
			Iterable<RevCommit> commitsInBetween = git.log().addRange(startCommit, endCommit).call();//get the commits between both tags			
			
			repo.close();
			return 	commitsInBetween;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static String getCommitMessage(String sha) {
		RevCommit com = getCommitFromCommitSha(sha);
		return com.getFullMessage();
	}
	
	public static PersonIdent getCommitAuthor(String sha) {
		RevCommit com = getCommitFromCommitSha(sha);
		return com.getAuthorIdent();
	}
}
