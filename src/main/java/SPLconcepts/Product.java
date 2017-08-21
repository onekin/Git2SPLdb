package SPLconcepts;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.repodriller.domain.Commit;


public class Product {

	
	String releaseBranchName=null;
	ArrayList<ProductRelease> releases= new ArrayList<ProductRelease>();
	ProductPortfolio inPortfolio=null;
	
	ArrayList<RevCommit> commitList=null;
	
	Commit originCommit =null;

	public Product(Commit originCommit, String releaseBranchName, ProductPortfolio inPortfolio){
		this.originCommit = originCommit;
		this.releaseBranchName = releaseBranchName;	
		this.inPortfolio = inPortfolio;
	}
	
	public void addProductRelease(ProductRelease pr){
		if (this.releases==null)
			releases= new ArrayList<ProductRelease> ();
		releases.add(pr);	
	}

	public Commit getOriginCommit() {
		return originCommit;
	}

	public void setOriginCommit(Commit originCommit) {
		this.originCommit = originCommit;
	}

	public String getBranchName() {
		return releaseBranchName;
	}

	public void setBranchName(String branchName) {
		this.releaseBranchName = branchName;
	}

	public ArrayList<ProductRelease> getReleases() {
		return releases;
	}

	public void setReleases(ArrayList<ProductRelease> releases) {
		this.releases = releases;
	}

	public ProductPortfolio getInPortfolio() {
		return inPortfolio;
	}

	public void setInPortfolio(ProductPortfolio inPortfolio) {
		this.inPortfolio = inPortfolio;
	}

	public void computeAllReleases() {
	
		if(commitList==null) commitList= new ArrayList<RevCommit>();
		
		  try{
			  Repository repository = new FileRepository(preprocessing.Main.productRepo+"/.git");
			  Git git = new Git(repository);
			  Iterable<RevCommit> revCommits = git.log().
	        		add(repository.resolve(this.releaseBranchName))
	                .call();
			  System.out.println("Commits for branch: "+this.getBranchName());
		        for(RevCommit revCommit : revCommits){
		        	if(revCommit.getName().equals(this.originCommit.getHash())) break; // do not add commits belonging to the core asset baselines
		        	this.commitList.add(revCommit);
		        	System.out.println(revCommit.getName());
		        	
		        }
		        
		        Iterator<RevCommit> it = commitList.iterator();
				while (it.hasNext()){
					RevCommit co = it.next();
					 List<Ref> list = git.tagList().call();	
					
					ArrayList<ObjectId> commits = new ArrayList<ObjectId>();
					for (Ref tag : list) {
						ObjectId object = tag.getObjectId(); 
					    if (object.getName().equals(co.getName())) {
					        commits.add(object);
					        System.out.println("release for product" +this.releaseBranchName+ "is commit: "+object.getName()+ "  name:"+tag.getName());
					        ProductRelease pr = new ProductRelease (tag.getName(),this, new Date ( 1000L * co.getCommitTime()), co);
					       this.releases.add(pr);

					    }
					}
				}

		  }catch (Exception e ){
			  e.printStackTrace();
		  }
		
	}


	
}
