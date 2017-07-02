package SPLconcepts;

import java.util.ArrayList;

public class ProductPortfolio {

	CoreAssetBaseline derivedFrom;
	ArrayList<Product> products;
	
	public ProductPortfolio(CoreAssetBaseline derivedFrom) {
		super();
		this.derivedFrom = derivedFrom;
		
	}
	
	public void addProductToPortfolio(Product p){
		if(products==null){
			products= new ArrayList<Product>();
		}
		products.add(p);
	}
	
	public void getProductFromPortfolio(String name){
		return;
	}
	
	public Product getProductFromPortfolio(int i){
		if(i>0 && (i<products.size()))
		  return products.get(i);
	return null;
	}
	
	
	public CoreAssetBaseline getDerivedFrom() {
		return derivedFrom;
	}
	public void setDerivedFrom(CoreAssetBaseline derivedFrom) {
		this.derivedFrom = derivedFrom;
	}
	public ArrayList<Product> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	public int getNumberOfProductsInPortfolio() {
		return products.size();
	}
	
	

}
