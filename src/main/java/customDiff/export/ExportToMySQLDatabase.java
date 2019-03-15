package customDiff.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jgit.util.Base64;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Customization;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.Product;
import customDiff.SPLdomain.ProductPortfolio;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;


public class ExportToMySQLDatabase implements ExportTarget {
	
	String pathToDataFile;
	
	public void export(String pathToDataFile){
		
		try{
			
			generateInserts();
	
		}catch (Exception e ){
			e.printStackTrace();
		}

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
		
		String inserts10 = generateInsertsFor_VariationPoint_table();
		
		String inserts11 = generateInsertsFor_FeatureInVariationPoint_table();
		
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
			
		
		String inserts12 = generateInsertsFor_Customization_Table();
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
		allInserts.add(inserts11);
		allInserts.add(inserts12);
		customDiff.utils.FileUtils.writeToFile(this.pathToDataFile+"data-inserts.sql",allInserts);
		
	}



	private String generateInsertsFor_FeatureInVariationPoint_table() {
		String insert="";
		String header = "INSERT INTO feature_in_variationpoint (idfeature,idvariationpoint) VALUES\n";
		//for each core asset in  baselines
		//generate!!!
		
		//for each product in product releases
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<SourceCodeFile> assets ;
		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();
		ArrayList<VariationPoint> vps;
		VariationPoint vp;
		Set<Feature> featureList;
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if (products==null) continue;
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					assets = releases.get(j).getProductAssets();
					for (int z=0; z < assets.size(); z++){
						vps= assets.get(z).getVariationPoints();
						for (int k=0; k< vps.size();k++){
							vp=vps.get(k);
							featureList = vp.getFeatures();
							Iterator<Feature> it=featureList.iterator();
							while (it.hasNext()) {
								insert=insert.concat(header).concat("('"+it.next().getIdFeature()+"',"+vp.getIdVP()+");\n");
							}
						}
					}
				}
			}	
		}
		return insert;
	}
	


	private String generateInsertsFor_VariationPoint_table() {
		String insert="";
		String header="INSERT INTO Variationpoint (idvariationpoint, expression, body, line_init, line_end, idproductasset, idcoreasset, parent) VALUES\n";
		//for each core asset in  baselines
		//generate!!!
		
		ArrayList<VariationPoint> vps;
		VariationPoint vp;
		ArrayList<CoreAssetBaseline> baselines = customDiff.CustomDiff.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> assets = null;
		
		for ( int i=0; i < baselines.size(); i++){
			assets = baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< assets.size();j++){
				vps=assets.get(j).getVariationPoints();
				if(vps!=null) 
					for(int z=0; z<vps.size();z++){
						vp=vps.get(z);
						if (vp.getParentVP()!=null)
							insert=insert.concat(header).concat("("+vp.getIdVP()+",'"+encodeToBase64(vp.getExpression())+"','"+encodeToBase64(vp.getBody())+
									"',"+vp.getLineInit()+","+vp.getLineEnd()+","+"null,"+assets.get(j).getId()+","+vp.getParentVP().getIdVP()+");\n");
						else insert=insert.concat(header).concat("("+vp.getIdVP()+",'"+encodeToBase64(vp.getExpression())+"','"+encodeToBase64(vp.getBody())
								+"',"+vp.getLineInit()+","+vp.getLineEnd()+",null,"+assets.get(j).getId()+",null"+");\n");
					}
			}
		}
	
		//for each product in product releases
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		
		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();
		
		ArrayList<SourceCodeFile> passets;
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if(products==null) continue;
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					 passets = releases.get(j).getProductAssets();
					for (int z=0; z < passets.size(); z++){
						vps= passets.get(z).getVariationPoints();
						for (int k=0; k< vps.size();k++){
							vp=vps.get(k);
							if (vp.getParentVP()!=null)
								insert=insert.concat(header).concat("("+vp.getIdVP()+",'"+encodeToBase64(vp.getExpression())+"','"+encodeToBase64(vp.getBody())+
										"',"+vp.getLineInit()+","+vp.getLineEnd()+","+passets.get(z).getId()+",null,"+vp.getParentVP().getIdVP()+");\n");
							
							else insert=insert.concat(header).concat("("+vp.getIdVP()+",'"+encodeToBase64(vp.getExpression())+"','"+encodeToBase64(vp.getBody())+"',"+vp.getLineInit()+","+vp.getLineEnd()+","+passets.get(z).getId()+",null,null"+");\n");
								
						}
					}
				}
			}
		}
		
		return insert;
	}


	private String generateInsertsFor_ProductPortfolio_Table() {
		String insert="";
		String header = "INSERT INTO ProductPortfolio (idPortfolio,SPL_idSPL, CoreAssetBAseline_idBaseline) VALUES\n";
		ArrayList<ProductPortfolio> list = customDiff.CustomDiff.spl.getProductPortfolios();
		
		for (int i=0; i < list.size(); i++){
			insert=insert.concat(header).concat("('"+list.get(i).getPortfolioID()+"','"+customDiff.CustomDiff.spl.getId()+"','"+list.get(i).getDerivedFrom().getId()+"');\n");
			
		}		
		return insert;
	}


	private String generateInsertsFor_Product_Table() {
		String insert="";
		String header="INSERT INTO Product (idProduct, name, ProductPortfolio_idPortfolio) VALUES\n";
		ProductPortfolio pp;

		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();

		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if (products== null) continue;
			for (int i=0; i < products.size(); i++){
				insert=insert.concat(header).concat("("+products.get(i).getId()+",'"+products.get(i).getName()+"','"+products.get(i).getInPortfolio().getPortfolioID()+"');\n");
			}
		}
		
		return insert;	
	}


	private String generateInsertsFor_Product_Release_Table() {
		String header="INSERT INTO ProductRelease (idRelease, releaseDate, Product_idProduct) VALUES\n";
		String insert ="";
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases;
		ArrayList<Product> products;
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if(products==null) continue;
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++)
					insert=insert.concat(header).concat("('"+releases.get(j).getTagName()+"','"+ convertDateToMysqlForm(releases.get(j).getReleaseDate())+"',"+releases.get(j).getFromProduct().getId()+");\n");
			}
		}
		return insert;	
	}


	private String generateInsertsFor_Product_Asset_table() {
		String insert="";
		String header = "INSERT INTO ProductAsset (idProductAsset, name, path, content, size, ProductRelease_idRelease, absolute_diff, relative_diff) VALUES\n";
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<SourceCodeFile> assets ;

		ArrayList<Product> products;
		
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();
		
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if(products==null) continue;
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					assets = releases.get(j).getProductAssets();
					for (int z=0; z < assets.size(); z++)
						insert=insert.concat(header).concat("("+assets.get(z).getId() +",'"+assets.get(z).getFileName()+"','"+assets.get(z).getPath()+"','"+encodeToBase64 (assets.get(z).getContent())+"',"+assets.get(z).getTotalLines()+",'"+releases.get(j).getTagName()+"','"+encodeToBase64(assets.get(z).getAbsolute_diff())+"','"+encodeToBase64(assets.get(z).getRelative_diff())+"');\n");
				}
			}
		}
		return insert;	
	}


	private String generateInsertsFor_Customization_Table() {
		String header="INSERT INTO Customization (added_lines,deleted_lines,diff,CoreAsset_idCoreAsset,ProductAsset_idProductAsset, isNewAsset, idvariationpoint) VALUES\n";
		String insert="";
		
		ProductPortfolio pp;
		ArrayList<ProductRelease> releases ;
		ArrayList<Customization> customs ;
		Customization cust ;
		ArrayList<Product> products;
		VariationPoint vp,parent;
		ArrayList<ProductPortfolio> portfolios = customDiff.CustomDiff.spl.getProductPortfolioList();
		//TODO whatch out with variation point pa
		for(int x=0; x < portfolios.size();x++){
			pp=portfolios.get(x);
			products = pp.getProducts();
			if(products==null) continue;
			for (int i=0; i < products.size(); i++){
				 releases = products.get(i).getReleases();
				for (int j=0; j< releases.size(); j++){
					customs = releases.get(j).getCustomizations();
					for(int z=0; z < customs.size(); z++){
						cust = customs.get(z);
						vp = cust.getVariationpointpa();//can be null
						if(cust.isNewAsset()==true){
							if(vp==null) 
								insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+"null"+","+cust.getProductasset().getId() +","+ "1"+", null );\n");
							else {
								//compute also a customization for its parents VPs!!
								insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+"null"+","+cust.getProductasset().getId()+","+"1"+ ","+cust.getVariationpointpa().getIdVP() +");\n");
								parent =vp.getParentVP();
								while(parent!=null){
									insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+"null"+","+cust.getProductasset().getId()+","+"1"+ ","+parent.getIdVP() +");\n");
									parent = parent.getParentVP();
								}
								
							}
								
						}
							
						else {
							if(vp!=null){
								insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+cust.getCoreasset().getId()+","+cust.getProductasset().getId()+","+"0"+","+cust.getVariationpointpa().getIdVP() +");\n");
								parent =vp.getParentVP();
								while(parent!=null){
									insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+cust.getCoreasset().getId()+","+cust.getProductasset().getId()+","+"0"+","+parent.getIdVP() +");\n");
									parent = parent.getParentVP();
								}
							} 
							else insert=insert.concat(header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+cust.getCustomdiff()+"',"+cust.getCoreasset().getId()+","+cust.getProductasset().getId()+","+"0"+",null);\n");
						}													
					}
				}
			}
		}
		return insert;	
	}


	private String generateInsertsFor_CoreAsset_has_Feature_Table() {
		String insert="";
		String header = "INSERT INTO CoreAsset_has_Feature (Feature_idFeature,CoreAsset_idCoreAsset) VALUES\n";
		ArrayList<CoreAssetBaseline> baselines = customDiff.CustomDiff.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> caList = null;
		
		for(int i=0; i< baselines.size();i++){
			caList=baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< caList.size();j++){
				ArrayList<Feature> features = caList.get(j).getFeatureList();
				
				for (int z=0; z < features.size(); z++){
					insert=insert.concat(header).concat("('"+features.get(z).getIdFeature()+"',"+caList.get(j).getId() +");\n");
			
				}
			
			}
			
		}
		
		return insert;	
	}


	private String generateInsertsFor_CoreAssets_Table() {
		
		String insert="";
		String header="INSERT INTO CoreAsset (idCoreAsset, name, path, content, size, CoreAssetBaseline_idBaseline) VALUES\n";
		ArrayList<CoreAssetBaseline> baselines = customDiff.CustomDiff.spl.getCoreAssetBaselines();
		ArrayList<SourceCodeFile> list = null;
		
		for ( int i=0; i < baselines.size(); i++){
			list = baselines.get(i).getCoreAssetFiles();
			for(int j=0; j< list.size();j++){
				insert=insert.concat(header).concat("("+list.get(j).getId() +",'"+list.get(j).getFileName()+"','"+list.get(j).getPath()+"','"+encodeToBase64 (list.get(j).getContent())+"',"+list.get(j).getTotalLines()+",'"+baselines.get(i).getId()+"');\n");
			}
		}
		
		return insert;
	}


	private String  generateInsertsFor_CoreAsset_Baseline_has_Feature() {
		String insert="";
		String header="INSERT INTO CoreAssetBaseline_has_Feature (CoreAssetBaseline_idBaseline, Feature_idFeature) VALUES\n";
		ArrayList<CoreAssetBaseline> baselines = customDiff.CustomDiff.spl.getCoreAssetBaselines();
		ArrayList<Feature> featuresInBaseline;
		
	
		for(int i=0; i< baselines.size();i++){
			featuresInBaseline = baselines.get(i).getFeatures();
			for(int j=0; j < featuresInBaseline.size();j++){
				insert=insert.concat(header).concat("('"+baselines.get(i).getId()+"','"+featuresInBaseline.get(j).getIdFeature()+"');\n");
			}
		}
		return insert;
	}


	private String generateInsertsFor_Feature_Table() {
		String insert="";
		String header = "INSERT INTO Feature (idFeature, name) VALUES\n";
		ArrayList<Feature> list = customDiff.CustomDiff.features;
		if (list==null) return "";
		
		Iterator<Feature> it = list.iterator();
		Feature f = null;
		while(it.hasNext())  {
			f=it.next();
			insert=insert.concat(header).concat("('" +f.getIdFeature()+"'"+",'"+f.getName()  +"');\n");
		}
		return insert;
	}


	private String generateInsertsFor_CoreAssetBaseline_Table() {
		String insert="";
		String header = "INSERT INTO CoreAssetBaseline (idBaseline,releaseDate,SPL_idSPL) VALUES\n";
		ArrayList<CoreAssetBaseline> list = customDiff.CustomDiff.spl.getCoreAssetBaselines();
		if (list==null) return "";
		
		Iterator<CoreAssetBaseline> it = list.iterator();
		CoreAssetBaseline b;
		while (it.hasNext()){
			b = it.next();
			insert=	insert.concat(header).concat("(" +"'"+b.getId()+"'"+","+"'"+convertDateToMysqlForm (b.getReleaseDate())+"'"+","+"'"+customDiff.CustomDiff.spl.getId() +"'"  +");\n");
		}
		return insert;
	}
	
	private String generateInsertsFor_SPL_Table() {
		String insert="";
	
		insert=	insert.concat("INSERT INTO SPL (idSPL)  VALUES \n " );
		insert=insert.concat ("('"+ customDiff.CustomDiff.spl.getId()+ "')"); 
		
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
		if (str ==null) return null;
		String   bytesEncoded = Base64.encodeBytes(str.getBytes());
	//	System.out.println("ecncoded value is " + new String(bytesEncoded ));
		// Decode data on other side, by processing encoded data
	//	byte[] valueDecoded= Base64.decode(bytesEncoded );
		//System.out.println("Decoded value is " + new String(valueDecoded));
		
		return bytesEncoded;
	}

}
