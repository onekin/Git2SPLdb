package SPLconcepts;

import java.util.ArrayList;

import org.repodriller.domain.Commit;

public class ProductPortfolio {

	String portfolioID;
	CoreAssetBaseline derivedFrom;
	ArrayList<Product> products;
	
	public ProductPortfolio(CoreAssetBaseline ca, int series) {
		super();
		if (ca==null)
			System.out.println("CoreAssetBaseline is null");
		this.derivedFrom = ca;
		this.portfolioID = "Series "+series+".X";
		
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
	
	public String getPortfolioID(){
		return this.portfolioID;
	}

}
