package SPLconcepts;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.jgit.revwalk.RevCommit;

import utils.Utils;

public class ProductRelease {

	private String idRelease;
	private Product fromProduct;
	private Date releaseDate;
	private	ArrayList<ProductAssetFileAnnotated> productAssets = new ArrayList<ProductAssetFileAnnotated>();
	private RevCommit releasedCommit;
	ArrayList<Customization> customList=new ArrayList<Customization>();
	
	public ProductRelease(String idRelease, Product fromProduct, Date l, RevCommit releasedCommit){
		
		this.fromProduct = fromProduct;
		this.releaseDate = l;
		this.releasedCommit = releasedCommit;	
		this.idRelease = Utils.extractRefName(idRelease, 2);
	}
	
	
	public void addCustomization(Customization cust){
		this.customList.add(cust);
	}
	
	public ArrayList<Customization>  getCustomizations(){
		return this.customList;
	}
	
	public String getIdRelease() {
		return idRelease;
	}
	public void setIdRelease(String tag) {
		this.idRelease = tag;
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
	public ArrayList<ProductAssetFileAnnotated> getProductAssets() {
		return productAssets;
	}
	public void setProductAssets(ArrayList<ProductAssetFileAnnotated> productAssets) {
		this.productAssets = productAssets;
	}

	public RevCommit getReleasedCommit() {
		return releasedCommit;
	}

	public void setReleasedCommit(RevCommit releasedCommit) {
		this.releasedCommit = releasedCommit;
	}


	
	
	
	
	
} 
