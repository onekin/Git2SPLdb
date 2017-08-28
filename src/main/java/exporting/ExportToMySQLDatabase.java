package exporting;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import SPLconcepts.CoreAssetBaseline;
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
	
	

	
	public void export(){
		
		try{
			
			generateInserts();
			generateViewsForCustomizationAnalysis();
			
		}catch (Exception e ){
			e.printStackTrace();
		}

	}
	
	private void generateViewsForCustomizationAnalysis() {
		// TODO Auto-generated method stub
		
		//Views For alluvial diagram
		ArrayList<String> views=new ArrayList<String>();
		
		views.add(generateAlluvialFeatureBased());
		
		
		utils.FileUtils.writeToFile(this.pathToDataFile+"views.sql",views);
		
	}




	private String generateAlluvialFeatureBased() {
		String view = "create view CustomizaionGBProductFeature AS"
		+"select idBaseline, c.featureNameModified, p.id, p.name, pr.idRelease, count(idCustomization) AS churn"
		+"from coreassetbaseline b INNER JOIN coreasset ca on idBaseline=ca.coreassetbaseline_idbaseline"
		+"inner join customization c on c.coreasset_idcoreasset=ca.idcoreasset"
		+"inner join productasset pa on pa.idProductasset=c.productasset_idproductasset"
		+"inner join productrelease pr on pr.idrelease=pa.productrelease_idRelease"
		+"inner join product p on p.idProduct=pr.product_idproduct"
		+"group by idBaseline,featureNameModified,pr.idRelease";
		
		
		return view;
		
	}




	private void generateInserts() {
		String inserts0 ="";
		ArrayList<String> allInserts = new ArrayList<String>();
		
		inserts0 = generateInsertsFor_SPL_Table();
	//	utils.FileUtils.writeToFile(this.pathToDataFile+"0insertsSPL.sql",inserts0);
		
		
		String inserts1 =    generateInsertsFor_CoreAssetBaseline_Table();
		//		utils.FileUtils.writeToFile(this.pathToDataFile+"1insertsCABaseline.sql",inserts1);
		
		String inserts2 = generateInsertsFor_Feature_Table();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"2insertsFeature.sql",inserts2);
		
		String inserts3 = generateInsertsFor_CoreAsset_Baseline_has_Feature();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"3insertsCABaseline_has_Feature.sql",inserts3);
		
		String inserts4 = generateInsertsFor_CoreAssets_Table();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"4insertsCoreAssets.sql",inserts4);
		
		String inserts5 = generateInsertsFor_CoreAsset_has_Feature_Table();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"5insertsCAhasFeature.sql",inserts5);
		
		String inserts6 = generateInsertsFor_ProductPortfolio_Table();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"6insertsPortpolios.sql",inserts6);
		
		String inserts7 = generateInsertsFor_Product_Table();
		//	utils.FileUtils.writeToFile(this.pathToDataFile+"7insertsProducts.sql",inserts7);
		
		String inserts8 = generateInsertsFor_Product_Release_Table();
		//		utils.FileUtils.writeToFile(this.pathToDataFile+"8insertsProductRelease.sql",inserts8);
		
		String inserts9 = generateInsertsFor_Product_Asset_table();
		//		utils.FileUtils.writeToFile(this.pathToDataFile+"9insertsProductAsset.sql",inserts9);
		
		String inserts10 = generateInsertsFor_Customization_Table();
		//		utils.FileUtils.writeToFile(this.pathToDataFile+"10insertsCustomization.sql",inserts10);
		
		allInserts.add(inserts0);
		allInserts.add(inserts1);
		allInserts.add(inserts2);
		allInserts.add(inserts3);
		allInserts.add(inserts4);
		allInserts.add(inserts5);
		allInserts.add(inserts6);
		allInserts.add(inserts7);
		allInserts.add(inserts8);
		allInserts.add(inserts9);
		allInserts.add(inserts10);
		utils.FileUtils.writeToFile(this.pathToDataFile+"data-inserts.sql",allInserts);
		
	}




	private String generateInsertsFor_ProductPortfolio_Table() {
		String insert="INSERT INTO ProductPortfolio (idPortfolio,SPL_idSPL, CoreAssetBAseline_idBaseline) VALUES\n";
		ArrayList<ProductPortfolio> list = Main.spl.getProductPortfolios();
		
		for (int i=0; i < list.size(); i++){
			insert=insert.concat("('"+list.get(i).getPortfolioID()+"','"+Main.spl.getId()+"','"+list.get(i).getDerivedFrom().getId()+"')");
			if(i+1 < list.size()) 	insert=insert.concat(",\n");
		}
		
		insert=insert.concat(";\n");
		return insert;
	}


	private String generateInsertsFor_Product_Table() {
		String insert="INSERT INTO Product ( idProduct, name, ProductPortfolio_idPortfolio) VALUES\n";
		ProductPortfolio pp;

		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();

		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			for (int i=0; i < products.size(); i++){
				insert=insert.concat("("+products.get(i).getId()+",'"+products.get(i).getBranchName()+"','"+products.get(i).getInPortfolio().getPortfolioID()+"')");
				if (i +1 < products.size()) insert=insert.concat(",\n");
			}
			if (x +1 < portfolios.size()) insert=insert.concat(",\n");
		}
		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_Product_Release_Table() {
		String insert="INSERT INTO ProductRelease (idRelease, releaseDate, Product_idProduct) VALUES\n";
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases;
		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					insert=insert.concat("('"+releases.get(j).getIdRelease()+"','"+ convertDateToMysqlForm(releases.get(j).getReleaseDate())+"',"+releases.get(j).getFromProduct().getId()+")");
					if (j +1 < releases.size()) insert=insert.concat(",\n");
				}
				if (i +1 < products.size()) insert=insert.concat(",\n");
			}
			if (x +1 < portfolios.size()) insert=insert.concat(",\n");
		}
		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_Product_Asset_table() {
		String insert="INSERT INTO ProductAsset (idProductAsset, name, path, content, size, ProductRelease_idRelease) VALUES\n";
		
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<ProductAssetFileAnnotated> assets ;

		ArrayList<Product> products;
		
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					assets = releases.get(j).getProductAssets();
					for (int z=0; z < assets.size(); z++){
						insert=insert.concat("("+assets.get(z).getId() +",'"+assets.get(z).getFileName()+"','"+assets.get(z).getPath()+"','"+encodeToBase64 (assets.get(z).getContent())+"',"+assets.get(z).getTotalLines()+",'"+releases.get(j).getIdRelease()+"')");
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
		String insert="INSERT INTO Customization (operation,CoreAsset_idCoreAsset,ProductAsset_idProductAsset,Feature_idFeature, isNewFeature, isNewAsset, featureNameModified) VALUES\n";
		
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<Customization> customs ;
		Customization cust ;
		ArrayList<Product> products;
		
		ArrayList<ProductPortfolio> portfolios = Main.spl.getProductPortfolioList();
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					customs = releases.get(j).getCustomizations();
					for(int z=0; z < customs.size(); z++){
						cust = customs.get(z);
						if(cust.getIsNewFeature()==true && cust.getIsNewAsset()==true)
							 insert=insert.concat("('"+cust.getOperation()    +"',"+"null"+","+cust.getProductFile().getId()+","+ "null" +","+ cust.getIsNewFeature()+","+cust.getIsNewAsset()+ ",'" +cust.getFeatureModifiedName()+"'" +")");
						else 
							if (cust.getIsNewFeature()==false && cust.getIsNewAsset()==false)
							  insert=insert.concat("('"+cust.getOperation()    +"',"+cust.getCoreAssetFile().getId()+","+cust.getProductFile().getId()+",'"+cust.getFeatureModifiedName() +"',"+ cust.getIsNewFeature()+","+cust.getIsNewAsset()+ ",'" +cust.getFeatureModifiedName()+"'" +")");
							else 
								if (cust.getIsNewFeature()==true && cust.getIsNewAsset()==false)
									 insert=insert.concat("('"+cust.getOperation()    +"',"+cust.getCoreAssetFile().getId()+","+cust.getProductFile().getId()+","+"null" +","+ cust.getIsNewFeature()+","+cust.getIsNewAsset()+ ",'" +cust.getFeatureModifiedName()+"'" +")");
								else if (cust.getIsNewFeature()==false && cust.getIsNewAsset()==true)
									 insert=insert.concat("('"+cust.getOperation()    +"',"+"null"+","+cust.getProductFile().getId()+",'"+cust.getFeatureModifiedName() +"',"+ cust.getIsNewFeature()+","+cust.getIsNewAsset()+ ",'" +cust.getFeatureModifiedName()+"'" +")");
									
						if (z+1 < customs.size()) insert=insert.concat(",\n");
					}
					if (j+1 < releases.size()) insert=insert.concat(",\n");
				}
				if (i+1 < products.size()) insert=insert.concat(",\n");
			}
			//if (x+1 < portfolios.size()) insert=insert.concat(",\n");
		}
		
		insert=insert.concat(";\n");
		return insert;	
	}


	private String generateInsertsFor_CoreAsset_has_Feature_Table() {
		String insert="INSERT INTO CoreAsset_has_Feature (Feature_idFeature,CoreAsset_idCoreAsset) VALUES\n";
		
		ArrayList<CoreAssetBaseline> baselines = Main.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> caList = null;
		
		for(int i=0; i< baselines.size();i++){
			caList=baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< caList.size();j++){
				ArrayList<Feature> features = caList.get(j).getFeatureList();
				
				for (int z=0; z < features.size(); z++){
					insert=insert.concat("('"+features.get(z).getIdFeature()+"',"+caList.get(j).getId() +")");
					if (z+1 < features.size()) insert=insert.concat(",\n");
				}
				if (j+1 < caList.size() && (features.size()!=0)) insert=insert.concat(",\n");
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
				insert=insert.concat("("+list.get(j).getId() +",'"+list.get(j).getFileName()+"','"+list.get(j).getPath()+"','"+encodeToBase64 (list.get(j).getContent())+"',"+list.get(j).getTotalLines()+",'"+baselines.get(i).getId()+"')");
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
				if (j+1 < featuresInBaseline.size()) insert=insert.concat(",\n");
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
		Feature f = null;
		if (it.hasNext())  f=it.next();
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
				insert=	insert.concat("(" +"'"+b.getId()+"'"+","+"'"+convertDateToMysqlForm (b.getReleaseDate())+"'"+","+"'"+Main.spl.getId() +"'"  +")");
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
	//	System.out.println(insert);
		return insert;
		
	}
	
	private String convertDateToMysqlForm(Date date){
		java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String currentTime = sdf.format(date);
			return currentTime;
	}
	
	private String encodeToBase64(String str){
		// encode data on your side using BASE64
		//https://stackoverflow.com/questions/19743851/base64-java-encode-and-decode-a-string
		String   bytesEncoded = Base64.encode(str.getBytes());
	//	System.out.println("ecncoded value is " + new String(bytesEncoded ));

		// Decode data on other side, by processing encoded data
	//	byte[] valueDecoded= Base64.decode(bytesEncoded );
		//System.out.println("Decoded value is " + new String(valueDecoded));
		
		return bytesEncoded;
	}

}
