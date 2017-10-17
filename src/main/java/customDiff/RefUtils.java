package customDiff;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Feature;
import SPLconcepts.SourceCodeFile;

public class RefUtils {

	public static RevCommit getCommitFromTagName(String baselineToMine) {
		RevCommit commit = null;
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.productRepo+"/.git");
			System.out.println(repo);
			
			
			Ref ref = repo.getRef(baselineToMine);
			//System.out.println("REF Name: "+ref.getName());
			//System.out.println("REF: "+ref);
			
			RevWalk walk = new RevWalk( repo );
			commit = walk.parseCommit( ref.getObjectId() );//find the commit for a 

			repo.close();
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}		
		return commit;
	}

	public static ArrayList<String> listAllTagsFromRepo(){
		ArrayList<String> refNames = new ArrayList<String>();
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.productRepo+"/.git");
			List<Ref> references = new Git(repo).tagList().call();
			for (Ref ref : references) {
			    //System.out.println("Tag: " + ref + " " + ref.getName() + " " + ref.getObjectId().getName());
			    refNames.add(ref.getName());
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
			Repository repo = new FileRepository(customDiff.CustomDiff.productRepo+"/.git");
			refMap = repo.getTags();
			repo.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return refMap;
	}
	
	
	
	
}
