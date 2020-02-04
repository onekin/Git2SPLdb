package org.onekin.customdiff.spldomain;

import java.util.ArrayList;

public class SPL {
	private String id;
	private ArrayList<CoreAssetBaseline> coreAssetBaselines = new ArrayList<CoreAssetBaseline>();
	private ArrayList<ProductPortfolio> productPortfolios = new ArrayList<ProductPortfolio>();
	

	
	public SPL(String id){
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<ProductPortfolio> getProductPortfolios() {
		return productPortfolios;
	}

	public void setProductPortfolios(ArrayList<ProductPortfolio> productPortfolios) {
		this.productPortfolios = productPortfolios;
	}

	public ArrayList<CoreAssetBaseline> getCoreAssetBaselines() {
		return coreAssetBaselines;
	}

	
	public void setCoreAssetBaselines(ArrayList<CoreAssetBaseline> baselines){
		this.coreAssetBaselines=baselines;
	}
	
	public void addBaseline(CoreAssetBaseline baseline){
		coreAssetBaselines.add(baseline);
	}
	
	public int getCoreAssetBaselineSize(){
		return coreAssetBaselines.size();
	}
	
	public int getProductPortfoliosSize(){
		return productPortfolios.size();
	}


	public CoreAssetBaseline getCoreAssetBaseline(int i) {
		
		return coreAssetBaselines.get(i);
	}
	
public ProductPortfolio getProductPortfolio(int i) {
		
		return productPortfolios.get(i);
	}

public ArrayList<ProductPortfolio> getProductPortfolioList() {
	
	return this.productPortfolios;
}
	
}
