package customDiff.export;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.jgit.util.Base64;



import customDiff.CustomDiff;
import customDiff.SPLdomain.Customization;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.Product;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;

public class ExportToStarDiagram implements ExportTarget{

	String pathToDataFile;
	int developer_group =1;
	
	public void export(String pathToDataFile){
		
		try{
			
			ArrayList<String> allInserts = generateInserts(pathToDataFile);
	
			customDiff.utils.FileUtils.writeToFile(pathToDataFile, allInserts);
			
		}catch (Exception e ){
			e.printStackTrace();
		}

	}

	private String generateInsert(){
		String  insert="";
		String header = "INSERT INTO table () VALUES\n";
		insert=insert.concat(header).concat("(  );\n");
		return insert;
	}
	
	private ArrayList<String> generateInserts(String pathToDataFile) {
		ArrayList<String> allInserts = new ArrayList<String>();
		
		allInserts.add(generateInsertsForCoreAssets());
		allInserts.add(generateInsertsForFeatures());
		allInserts.add(generateInsertsForVariationPoints());
		allInserts.add(generateInsertsForDevelopers());
		allInserts.add(generateInsertsForProductsReleasesAndCustomizations());
		//inserts for newly created assets in products!
		return allInserts;
	}
	


	private String getInsertsForProductCustomizations(ProductRelease pr) { // for customizations and its developers
		String  insert="";
		String developer_group_header="INSERT INTO developer_group (id_developer_group) VALUES \n";
		String developer_bridge_header="INSERT INTO developer_bridge (id_developer_group,id_developer) VALUES \n";
		
		String custom_header = "INSERT INTO customization_fact (lines_added,lines_deleted,custom_diff,commit_set,message_set,greater_diff" +
				",id_variationpoint,id_developer_group, id_productrelease) VALUES\n";
		
		Iterator<Customization> customs = pr.getCustomizations().iterator();
		Customization cust;
		Developer dev;
		Iterator<Developer> developers ;
		while(customs.hasNext()){
			cust = customs.next();
			developers = cust.getDevelopers().iterator();
			insert = insert.concat(developer_group_header).concat("("+developer_group+");\n");//insert developer group
			while(developers.hasNext()){//insert developer bridge
				 dev = developers.next();
				 insert = insert.concat(developer_bridge_header).concat("("+developer_group+","+dev.getId()+");\n");
			}
			if (cust.getVp_ca().getExpression().equals("No Expression") || cust.getVp_pa().getExpression().equals("No Expression")){
				insert = insert.concat(insertEmptyVPForCustomization(cust));
			} 
			//TODO insert customization -- check if va_ca is null -->meaning a new VP! Check also a new asset in product. 
			insert = insert.concat(custom_header).concat("("+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+encodeToBase64(cust.getCustomdiff())+"','"+
					cust.getCommitShas()+"','"+cust.getCommitMessagesToString()+"','"+encodeToBase64(cust.getWholediff())+"',"
					+cust.getVp_ca().getIdVP()+","+developer_group+","+pr.getId()+");\n");
			
			developer_group ++;
		}
		
		return insert;
	}
	
	private String insertEmptyVPForCustomization(Customization cust) {
		String headerVP = "INSERT INTO variation_point (id_variationpoint, expression, id_coreasset,id_feature_group) VALUES\n";	
		String insert="";
		insert = insert.concat(headerVP).concat("("+cust.getVp_ca().getIdVP()+",'No Expression',"+cust.getCoreAssetFile().getId()+",0);\n");
		return insert;
	}

	private String generateInsertsForDevelopers() {
		String  insert="";
		String header = "INSERT INTO developer (id_developer,name,email) VALUES\n";
		Iterator<Developer> devs = customDiff.CustomDiff.allDevelopers.iterator();
		Developer dev;
		
		while(devs.hasNext()){
			dev= devs.next();
			insert=insert.concat(header).concat("("+dev.getId()+",'"+dev.getName()+"','"+dev.getEmail()+"');\n");
		}
		return insert;
	}

	private String generateInsertsForProductsReleasesAndCustomizations() {
		String  insert="";
		String product_header = "INSERT INTO product (id_product,name) VALUES\n";
		
		Iterator<Product> products = customDiff.CustomDiff.spl.getProductPortfolio(0).getProducts().iterator();
		Product p;
		while(products.hasNext()){
			p=products.next();
			insert = insert.concat(product_header).concat("("+p.getId()+",'"+p.getName()+"');");//introduce product
			insert = insert.concat(getInsertsForProductReleases(p));
		}
		
		return insert;
	}

	private String getInsertsForProductReleases(Product p) {
		String  insert="";//id_productrelease
		String releaseHeader = "INSERT INTO product_release (id_productrelease,name,date,commits_set,id_product) VALUES\n";
		
		Iterator<ProductRelease> releases = p.getReleases().iterator();
		ProductRelease pr;
		while(releases.hasNext()){
			pr = releases.next();
			insert = insert.concat(releaseHeader).concat("("+pr.getId()+",'"+ pr.getTagName()+"','"+convertDateToMysqlForm(pr.getReleaseDate())+"','"+pr.getCommitsSetToString()
					+"'," +pr.getFromProduct().getId()+");\n");//inserts for product release
			
			insert = insert.concat(getInsertsForProductCustomizations(pr));
		}
		
		return insert;
	}



	private String generateInsertsForFeatures() {
		String  insert="";
		String header = "INSERT INTO feature (id_feature,name) VALUES\n";
		String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
		String header_f_group = "INSERT INTO feature_group (id_feature_group) VALUES\n";
		
		Iterator<Feature> it = CustomDiff.features.iterator();
		Feature f;
		while(it.hasNext()){
			f = it.next();
			insert=insert.concat(header).concat("('"+f.getIdFeature()+"','"+f.getName()+"');\n");
		}
		
		//empty feature
		insert=insert.concat(header).concat("('No Feature','No Feature');\n");//no feature
		insert=insert.concat(header_f_group).concat("(0);\n"); //group for no feature
		insert=insert.concat(header_bridge).concat("(0,'No Feature');\n");
		return insert;
	}


	private String generateInsertsForVariationPoints(){
		String insert="";
		int id_feature_group= 1;
		
		String headerVP = "INSERT INTO variation_point (id_variationpoint, expression, id_coreasset,id_feature_group) VALUES\n";	
		String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
		String header_f_group = "INSERT INTO feature_group (id_feature_group) VALUES\n";
		
		ArrayList<SourceCodeFile> coreassets = CustomDiff.spl.getCoreAssetBaseline(0).getCoreAssetFiles();
		Iterator<SourceCodeFile> it = coreassets.iterator();
		SourceCodeFile ca;
		ArrayList<VariationPoint> vps;
		Iterator<VariationPoint> ite;
		VariationPoint vp;
		Iterator<Feature> features;
		Feature f;
		
		while(it.hasNext()){//if vp.
			ca = it.next();
			vps = ca.getVariationPoints();
			if(vps==null) continue;
			ite = vps.iterator();  
			while(ite.hasNext()){
				vp =ite.next();
				if(vp.getFeatures()==null) continue;
				System.out.print("\nVp in file:"+ca.getFileName()+" has vp "+vp.getVPFullExpression(vp) +" with features:\n");
				insert = insert.concat(header_f_group).concat("("+id_feature_group+");\n"); // feature_group
				features = vp.getFeatures().iterator();
				while(features.hasNext()){
					f = features.next();
					insert= insert.concat(header_bridge).concat("("+id_feature_group+",'"+f.getIdFeature()+"');\n");//feature bridge
					System.out.println(f.getIdFeature());
				}
				
				insert=insert.concat(headerVP).concat("(" +vp.getIdVP()+",'"+encodeToBase64(vp.getVPFullExpression(vp))
						+"',"+ca.getId()+","+id_feature_group+");\n");//add vp;
				
				id_feature_group++;
			}
		}
		
		return insert;
	}


	private String generateInsertsForCoreAssets() {
		String  insert="";
		String header = "INSERT INTO core_asset (id_coreasset, name, path,size,isNewAsset,content) VALUES\n";
		
		ArrayList<SourceCodeFile> coreassets = CustomDiff.spl.getCoreAssetBaseline(0).getCoreAssetFiles();
		Iterator<SourceCodeFile> it = coreassets.iterator();
		SourceCodeFile ca;
		while(it.hasNext()){
			ca = it.next();
			insert=insert.concat(header).concat("("+ca.getId()+",'"+ca.getFileName()+"','"+ca.getPath()
					+"',"+ca.getTotalLines()+","+"0,'"+encodeToBase64(ca.getContent())+"');\n");
		}
		
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

		return bytesEncoded;
	}

}
