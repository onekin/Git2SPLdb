package exporting;

import java.util.ArrayList;
import java.util.Iterator;

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Customization;
import SPLconcepts.Feature;
import SPLconcepts.Product;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductPortfolio;
import SPLconcepts.ProductRelease;
import SPLconcepts.SourceCodeFile;
import preprocessing.Main;

public class ExportToMySQLDatabase implements ExportTarget {
	
	String pathToDataFile;
	
	public ExportToMySQLDatabase (String path){
		this.pathToDataFile = path;
	}
	
	
	public String export(){
		String insert_clauses = "";
		
		insert_clauses=insert_clauses.concat(  generateInsertsFor_SPL_Table());
		insert_clauses=	insert_clauses.concat(  generateInsertsFor_CoreAssetBaseline_Table());
		insert_clauses=	insert_clauses.concat(  generateInsertsFor_Feature_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_CoreAsset_Baseline_has_Feature());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_CoreAssets_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_CoreAsset_has_Feature_Table());
		insert_clauses=	insert_clauses.concat(  generateInsertsFor_CoreAssets_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_ProductPortfolio_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_Product_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_Product_Release_Table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_Product_Asset_table());
		insert_clauses=	insert_clauses.concat(	generateInsertsFor_Customization_Table());
		
		utils.FileUtils.writeToFile(this.pathToDataFile,insert_clauses);
		
		System.out.println("------INSERTS---------");
		System.out.println(insert_clauses);
		return insert_clauses;
	}
	
	private String generateInsertsFor_ProductPortfolio_Table() {
		String insert="INSERT INTO ProductPortfolio (Feature_idFeature,CoreAsset_idCoreAsset) VALUES\n";
		ArrayList<ProductPortfolio> list = Main.spl.getProductPortfolios();
		
		for (int i=0; i < list.size(); i++){
			
		}
		
		return insert;
	}


	private String generateInsertsFor_Product_Table() {
		// TODO Auto-generated method stub
		return "";
	}


	private String generateInsertsFor_Product_Release_Table() {
		// TODO Auto-generated method stub
		return "";
	}


	private String generateInsertsFor_Product_Asset_table() {
		String insert="INSERT INTO ProductAsset (idProductAsset, name, path, content, size, ProductRelease_idRelease) VALUES\n";
		
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<ProductAssetFileAnnotated> assets ;
		ProductAssetFileAnnotated asset;
		Customization cust ;
		ArrayList<Product> products;
		
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();
		Iterator<ProductPortfolio> it = portfolios.iterator();
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					assets = releases.get(j).getProductAssets();
					for (int z=0; z < assets.size(); z++){
						insert=insert.concat("("+assets.get(z).getId() +",'"+assets.get(z).getFileName()+"','"+assets.get(z).getPath()+"','"+assets.get(z).getContent()+"',"+assets.get(z).getTotalLines()+",'"+releases.get(j).getIdRelease()+"')");
						if (z+1 < assets.size()) insert=insert.concat(",\n");
					}
					if (j +1 < releases.size()) insert=insert.concat(",\n");
				}
				if (i+1  < products.size() ) insert=insert.concat(",\n");
			}
			if (x+1 <  portfolios.size())  insert=insert.concat(",\n");
		}

		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_Customization_Table() {
		String insert="INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset,Feature_idFeature) VALUES\n";
		
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<Customization> customs ;
		Customization cust ;
		ArrayList<Product> products;
		
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();
		Iterator<ProductPortfolio> it = portfolios.iterator();
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					customs = releases.get(j).getCustomizations();
					for(int z=0; z < customs.size(); z++){
						cust = customs.get(z);
						insert=insert.concat("('"+cust.getOperation()    +"',"+cust.getCoreAssetFile().getId()+","+cust.getProductFile().getId()+",'"+cust.getFeatureModifiedName() +"')");
						if (z+1 < customs.size()) insert=insert.concat(",\n");
					}
					if (j+1 < releases.size()) insert=insert.concat(",\n");
				}
				if (i+1 < products.size()) insert=insert.concat(",\n");
			}
			if (x+1 < portfolios.size()) insert=insert.concat(",\n");
		}
		
		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_CoreAsset_has_Feature_Table() {
		String insert="INSERT INTO CoreAsset_has_Feature (Feature_idFeature,CoreAsset_idCoreAsset) VALUES\n";
		
		ArrayList<CoreAssetBaseline> baselines = Main.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> list = null;
		
		ArrayList<String> featureIds;
		for(int i=0; i< baselines.size();i++){
			list=baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< list.size();j++){
				featureIds = list.get(j).getFeatureList();
				for (int z=0; z < featureIds.size(); z++){
					insert=insert.concat("('"+featureIds.get(z)+"',"+list.get(j).getId() +")");
					if (z+1 < featureIds.size()) insert=insert.concat(",\n");
				}
				if (j+1 < list.size()) insert=insert.concat(",\n");
			}
			if (i+1 < baselines.size()) insert=insert.concat(",\n");
		}
		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_CoreAssets_Table() {
		
		String insert="INSERT INTO CoreAsset (idCoreAsset, name, path, content, size, CoreAssetBaseline_idBaseline) VALUES\n";
		
		ArrayList<CoreAssetBaseline> baselines = Main.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> list = null;
		
		for ( int i=0; i < baselines.size(); i++){
			list = baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< list.size();j++){
				insert=insert.concat("("+list.get(j).getId() +",'"+list.get(j).getFileName()+"','"+list.get(j).getPath()+"','"+list.get(j).getContent()+"',"+list.get(j).getTotalLines()+",'"+baselines.get(i).getId()+"')");
				if (j+1 < list.size()) insert=insert.concat(",\n");
			}
			if (i+1 < baselines.size()) insert=insert.concat(",\n");
		}
		insert=insert.concat(";\n");
		return insert;
	}


	private String  generateInsertsFor_CoreAsset_Baseline_has_Feature() {
		String insert="INSERT INTO CoreAssetBaseline_has_Feature (CoreAssetBaseline_idBaseline, Feature_idFeature) VALUES\n";
		
		ArrayList<CoreAssetBaseline> baselines = Main.spl.getCoreAssetBaselines();
		ArrayList<Feature> featuresInBaseline;
		
	
		for(int i=0; i< baselines.size();i++){
			featuresInBaseline = baselines.get(i).getFeatures();
			for(int j=0; j < featuresInBaseline.size();j++){
				insert=insert.concat("('"+baselines.get(i).getId()+"','"+featuresInBaseline.get(j).getIdFeature()+"')");
				if (j+1 < featuresInBaseline.size()) insert.concat(",\n");
			}
			if (i+1 < baselines.size()) insert=insert.concat(",\n");
		}
		insert=insert.concat(";\n");
		return insert;
	}


	private String generateInsertsFor_Feature_Table() {
		String insert="INSERT INTO Feature (idFeature, name) VALUES\n";
		ArrayList<Feature> list = Main.features;
		if (list==null) return "";
		
		Iterator<Feature> it = list.iterator();
		Feature f=it.next();
		if (f!=null)
			while (true){
				insert=insert.concat("('" +f.getIdFeature()+"'"+",'"+f.getName()  +"')");
				if(it.hasNext()) insert=insert.concat(",\n");
				else break;
				f = it.next();
			}
		insert=insert.concat(";\n");
		return insert;
	}


	private String generateInsertsFor_CoreAssetBaseline_Table() {
		String insert="INSERT INTO CoreAssetBaseline (idBaseline,releaseDate,SPL_idSPL) VALUES\n";
		
		ArrayList<CoreAssetBaseline> list = Main.spl.getCoreAssetBaselines();
		if (list==null) return "";
		
		Iterator<CoreAssetBaseline> it = list.iterator();
		CoreAssetBaseline b=it.next();
		if (b!=null)
			while (true){
				insert=	insert.concat("(" +"'"+b.getId()+"'"+","+"'"+b.getReleaseDate()+"'"+","+"'"+Main.spl.getId() +"'"  +")");
				if(it.hasNext()) insert=insert.concat(",\n");
				else break;
				b = it.next();
			}
		insert=insert.concat(";\n");
		return insert;
	}
	
	private String generateInsertsFor_SPL_Table() {
		String insert="";
	
		insert=	insert.concat("INSERT INTO SPL (idSPL)  VALUES \n " );
		insert=insert.concat ("('"+ Main.spl.getId()+ "')"); 
		
		insert=insert.concat(";\n");
		
		return insert;
		
	}

}
