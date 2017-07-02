package SPLconcepts;

import java.util.ArrayList;

import org.repodriller.domain.Commit;

public class Product {

	Commit originCommit =null;
	String branchName=null;
	ArrayList<ProductRelease> releases=null;
	ProductPortfolio inPortfolio=null;

	public Product(Commit originCommit, String branchName, ProductPortfolio inPortfolio){
		this.originCommit = originCommit;
		this.branchName = branchName;	
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
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
