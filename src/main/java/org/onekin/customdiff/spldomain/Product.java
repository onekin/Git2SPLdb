package org.onekin.customdiff.spldomain;

import java.util.ArrayList;
import org.eclipse.jgit.revwalk.RevCommit;

public class Product {

	int id;
	
	String name=null;
	ArrayList<ProductRelease> releases= new ArrayList<ProductRelease>();
	ProductPortfolio inPortfolio=null;
	ArrayList<RevCommit> commitList=new ArrayList<RevCommit>();//all its commits

	ArrayList<SourceCodeFile> newfiles = new ArrayList<SourceCodeFile>();//tentative
	ArrayList<Feature> newfeatures = new ArrayList<Feature>(); //tentative
	
	public Product(int id, String name, ProductPortfolio pp) {
		this.id = id;
		this.name = name;	
		this.inPortfolio = pp;
	}

	

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
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


	public String toString(){
		return "Product id:"+id+ "; name: "+name;
		
	}

	
}
