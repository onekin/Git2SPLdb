package org.onekin.customdiff.spldomain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.onekin.customdiff.utils.Utils;
import org.eclipse.jgit.revwalk.RevCommit;


public class ProductRelease {
	private int id;
	private String tagName;
	private Product fromProduct;
	private Date releaseDate;
	private	ArrayList<SourceCodeFile> productAssets = new ArrayList<SourceCodeFile>();
	private RevCommit releasedCommit;
	private Iterable<RevCommit> listOfcommits;//list of commits from baseline to the release; In reverse order!
	ArrayList<Customization> customsList=new ArrayList<Customization>();
	
	
	
	public ProductRelease(int id, String tagName, Product fromProduct, Date l, RevCommit releasedCommit){
		this.id = id;
		this.fromProduct = fromProduct;
		this.releaseDate = l;
		this.releasedCommit = releasedCommit;	
		this.tagName = Utils.extractRefName(tagName, 2);
	}
	
	
	public void setCustomizations(ArrayList<Customization> customs){
		this.customsList = customs;
	}
	
	public ArrayList<Customization>  getCustomizations(){
		return this.customsList;
	}
	
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tag) {
		this.tagName = tag;
	}
	public Product getFromProduct() {
		return fromProduct;
	}
	public void setFromProduct(Product fromProduct) {
		this.fromProduct = fromProduct;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public ArrayList<SourceCodeFile> getProductAssets() {
		return productAssets;
	}
	public void setProductAssets(ArrayList<SourceCodeFile> productAssets) {
		this.productAssets = productAssets;
	}

	public RevCommit getReleasedCommit() {
		return releasedCommit;
	}

	public void setReleasedCommit(RevCommit releasedCommit) {
		this.releasedCommit = releasedCommit;
	}

	
	
	public Iterable<RevCommit> getListOfcommits() {
		return listOfcommits;
	}


	public void setListOfcommits(Iterable<RevCommit> listOfcommits) {
		this.listOfcommits = listOfcommits;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String toString(){
		return "ProductRelease: "+tagName+" belongs to: "+fromProduct+ " with last commit: "+releasedCommit.getName();
	}
	
	public String getCommitsSetToString(){
		String list="";
		Iterator<RevCommit> it = getListOfcommits().iterator();
		RevCommit c;
		while(it.hasNext()){
			c=it.next();
			list = list.concat(c.getName()+";");
		}
		return list;
	}
	
	
} 
