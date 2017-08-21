package preprocessing;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import SPLconcepts.ProductRelease;

public class Utils {

	
	static public List getCommitHashesBetweenTwoTags(String baselineTag, String productReleaseTag) {
		//this snipped sworks fine
		try{
			  Repository repo = new FileRepository(preprocessing.Main.productRepo+"/.git");
			  Git git = new Git(repo);
		  Ref refFrom = repo.getRef(baselineTag);
		  Ref refTo = repo.getRef(productReleaseTag);
		
		 List commitHashList = new  List();
		  
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
	
	static public String getTagForACommitHash(String hash){
		Repository repository;
		try {
			repository = new FileRepository(preprocessing.Main.productRepo+"/.git");
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
		return parsed[extractIndex];
		
	}
		  
		
	
}
