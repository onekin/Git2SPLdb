package SPLconcepts;

import java.util.ArrayList;
import java.util.Date;

public class ProductRelease {

	String idRelease;
	Product fromProduct;
	Date releaseDate;
	ArrayList<ProductAssetFileAnnotated> productAssets;
	
	public ProductRelease(String idRelease, Product fromProduct, Date l){
		this.idRelease = idRelease;
		this.fromProduct = fromProduct;
		this.releaseDate = l;
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
	
	
	
	
	
} 
