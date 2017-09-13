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

	int id;

	
	String branchName=null;
	ArrayList<ProductRelease> releases= new ArrayList<ProductRelease>();
	ProductPortfolio inPortfolio=null;
	
	ArrayList<RevCommit> commitList=new ArrayList<RevCommit>();
	Commit originCommit =null;

	public Product(int id, Commit originCommit, String releaseBranchName, ProductPortfolio inPortfolio){
		this.id = id;
		this.originCommit = originCommit;
		this.branchName = releaseBranchName;	
		this.inPortfolio = inPortfolio;
	}
	
	public String getBranchName() {
		return branchName;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public ArrayList<RevCommit> getCommitList() {
		return commitList;
	}

	public void setCommitList(ArrayList<RevCommit> commitList) {
		this.commitList = commitList;
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




	
}
