package org.onekin.customdiff.export;


import org.eclipse.jgit.util.Base64;
import org.onekin.customdiff.CustomDiffProducts;
import org.onekin.customdiff.spldomain.*;
import org.onekin.customdiff.utils.FileUtils;
import org.onekin.customdiff.utils.Utils;

import java.util.*;

public class ExportToStarDiagramProducts implements ExportTarget {

    String pathToDataFile;
    int developer_group =1;
    int id_feature_group= 1;
    int id_parentfeature=1;
    int id_package =1;

    HashMap<Integer, String> packages = new HashMap<Integer, String>();
    HashMap<Integer, ArrayList<Feature>> f_groups = new HashMap<Integer, ArrayList<Feature>>();
    ArrayList<Feature> addedFeatures = new ArrayList<>();
    HashMap<Integer, String> addedparentFeatures = new HashMap<Integer, String>();

    public void export(String pathToDataFile){//generate SQL with data inserts.

        try{

            ArrayList<String> allInserts = generateInserts(pathToDataFile);
            FileUtils.writeToFile(pathToDataFile, allInserts);

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

        allInserts.add(generateInsertsForCoreAssets());//core assets from baseline
        allInserts.add(generateInsertsForFeatures()); //from baseline & parent features
        allInserts.add(generateInsertsForVariationPoints());//from baseline core assets only
        allInserts.add(generateInsertsForDevelopers());
        allInserts.add(generateInsertsForNewProductAssets()); //for new product assets && new Features
        allInserts.add(generateInsertsForProductsReleasesAndCustomizations());//includes inserts to new variation points created in products

        return allInserts;
    }



    private String generateInsertsForNewProductAssets(){
        String  insert="";

        Iterator<Product> products = CustomDiffProducts.spl.getProductPortfolio(0).getProducts().iterator();
        Product p;
        while(products.hasNext()){
            p=products.next();
            Iterator<ProductRelease> prs = p.getReleases().iterator();
            while(prs.hasNext())
                insert = insert.concat (generateInsertsForNewProductAssets(prs.next()));
        }
        return insert;
    }

    private String generateInsertsForNewProductAssets(ProductRelease pr) { //THOSE assets that have isNew=true
        Iterator<SourceCodeFile> assets = pr.getProductAssets().iterator();

        String  insert="";
        String header = "INSERT INTO core_asset (idcoreasset, name, path,isNewAsset,size,idpackage) VALUES\n"; //,content
        SourceCodeFile asset;
        String package_name;
        int id_pack=0;

        while(assets.hasNext()){
            asset = assets.next();

            if (asset.getIsNewAsset()){//INSERT THE NEW ASSET
                package_name = getComponentPackageForCoreAsset(asset);

                if (packages.containsValue(package_name))
                    id_pack = getKeyByValue(package_name);
                else insert = insert + getInsertsForComponentPackage(package_name);

                if (id_pack ==0 ){
                    id_pack = this.id_package;
                }

                insert = insert.concat(header).concat("(" +asset.getId() +",'"+asset.getFileName()+"','"+asset.getPath()+"',1,"
                        +(asset.getContent()).split("\n").length+"," +id_pack +");\n"); //	'"+encodeToBase64(asset.getContent())
            }
        }
        return insert;
    }


    private String generateInsertsForNewFeaturesInProductAsset(SourceCodeFile pa, ArrayList<Feature> newFeatures) {
        String  insert="";
        String header = "INSERT INTO feature (idfeature, name, isNew, idparentfeature) VALUES\n";


        Iterator<VariationPoint> vps = pa.getVariationPoints().iterator();
        Set<Feature> featurelist;
        while(vps.hasNext()){
            featurelist = vps.next().getNewFeatures();
            if (featurelist==null) continue;
            Iterator<Feature> features = featurelist.iterator();
            Feature f;
            while(features.hasNext()){
                f=features.next();
                if (f.getIsNew() && !newFeatures.contains(f)){
                    insert = insert.concat(header).concat("('"+f.getIdFeature()+"','"+f.getName()+"',1,1);\n");
                    newFeatures.add(f);
                }

            }
        }
        return insert;
    }

    private String getInsertsForProductCustomizations(ProductRelease pr) { // for customizations and its developers
        String  insert="";
        String developer_group_header="INSERT INTO developer_group (id_developer_group) VALUES \n";
        String developer_bridge_header="INSERT INTO developer_bridge (id_developer_group,id_developer) VALUES \n";



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
            if (cust.getVariationpointca() !=null &&
                    (cust.getVariationpointca().getExpression().equals("No Expression")
                            || cust.getVariationpointpa().getExpression().equals("No Expression"))){
                insert = insert.concat(insertEmptyVPForCustomization(cust));
            }
            insert = insert.concat (generateInsertForCustomization( cust, pr) );
            developer_group ++;
        }

        return insert;
    }

    private String generateInsertForCustomization(Customization cust, ProductRelease pr) {

        String insert= "";
        String custom_header = "INSERT INTO customization_fact (idcustomization, lines_added,lines_deleted," +
                "custom_diff,commit_set,message_set,greater_diff" +
                ",idvariationpoint,id_developer_group, idproductrelease, type) VALUES\n";

        int idVariationPoint;
        if (cust.getProductasset().getIsNewAsset()) idVariationPoint= cust.getVariationpointpa().getIdVP();
        else idVariationPoint= cust.getVariationpointca().getIdVP();

        if (cust.getType().name().equals("CHANGE_IN_VARIATION_POINT_BODY") || (cust.getType().name().equals("CHANGE_OUTSIDE_VARIATION_POINT")))
            idVariationPoint = cust.getVariationpointca().getIdVP();

        //There is a new Vpoint
        if (cust.getType().name().equals("NEW_VARIATION_POINT") || cust.getType().name().equals("MODIFIED_VARIATION_POINT_EXPRESSION") || cust.getType().name().equals("NEW_VARIATION_POINT_WITH_NEW_FEATURES")){
            insert = insert.concat (generateInsertForNewVariationpointInProductAsset(cust, false));
            idVariationPoint = cust.getVariationpointpa().getIdVP();
        }

        else if (cust.getType().name().equals("NEW_ASSET_WITH_NO_VARIATIONPOINT")){//introduce fake VP with no expression
            System.out.println("NEW NEW_ASSET_WITH_NO_VARIATIONPOINT");
            String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group) VALUES\n";
            insert = insert.concat(headerVP).concat("("+cust.getVariationpointpa().getIdVP()+",'No Expression',"+cust.getProductasset().getId()+",0);\n");

        }


//		if (cust.getType().name().equals("REMOVE_VARIATION_POINT"))
//			idVariationPoint = cust.getVariationpointca().getIdVP();


//		if (cust.getType().name().equals("MODIFIED_VARIATION_POINT_EXPRESSION_WITH_NEWFEATURES"))



//		if (cust.getType().name().equals("NEW_ASSET_WITH_VARIATIONPOINTS"))
//		if (cust.getType().name().equals("NEW_ASSET_WITH_NEW_VARIATIONPOINT_AND_NEW_FEATURES"))



        insert = insert.concat(custom_header).concat("("+cust.getId()+","+cust.getLinesAdded()+","+cust.getLinesDeleted()+",'"+encodeToBase64(cust.getCustomdiff())+"','"+
                cust.getCommitShas()+"','"+cust.getCommitMessagesToString()+"','"+encodeToBase64(cust.getWholediff())+"',"
                +idVariationPoint+","+developer_group+","+pr.getId()+",'"+cust.getType().name()+"');\n");

        return insert;
    }



    private String generateInsertForNewVariationpointInProductAsset(Customization cust, boolean isNew) {
        //new VPS and new features if they are new!!
        String insert = "";
        String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group) VALUES\n";
        String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
        String header_f_group = "INSERT INTO feature_group (id_feature_group) VALUES\n";
        String headerFeature = "INSERT INTO feature (idfeature, name, isNew) VALUES\n";


        //INSERT NEW FEATURES AND ALSO THEIR BRIDGE
        insert = insert.concat(header_f_group).concat("("+id_feature_group+");\n"); // feature_group

        Set<Feature> allfeatures = cust.getVariationpointpa().getFeatures();
        if(allfeatures!=null) {
            allfeatures.addAll(cust.getVariationpointpa().getNewFeatures());
        } else{
            allfeatures=cust.getVariationpointpa().getNewFeatures();
        }
        if(allfeatures==null){
            allfeatures=new HashSet<>();
        }

        Iterator<Feature> features = allfeatures.iterator();
        Feature f;
        while(features.hasNext()){
            f = features.next();
            if (f.getIsNew() && !this.addedFeatures.contains(f)){ //INSERT THE NEW FEATURE!
                insert = insert.concat(headerFeature).concat("('"+f.getIdFeature()+"','"+f.getName()+"',1);\n");
                addedFeatures.add(f);
            }
            insert= insert.concat(header_bridge).concat("("+id_feature_group+",'"+f.getIdFeature()+"');\n");//feature bridge
        }

        //INSERT VP
        int assetId;
        if (!isNew) assetId = cust.getCoreasset().getId();
        else assetId = cust.getProductasset().getId();

        insert=insert.concat(headerVP).concat("(" +cust.getVariationpointpa().getIdVP()+",'"+encodeToBase64(cust.getVariationpointpa().getVPFullExpression(cust.getVariationpointpa()))
                +"',"+assetId+","+id_feature_group+");\n");//add vp;

        id_feature_group++;
        return insert;
    }

    private String insertEmptyVPForCustomization(Customization cust) {

        String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group) VALUES\n";
        String insert="";
        insert = insert.concat(headerVP).concat("("+cust.getVariationpointca().getIdVP()+",'No Expression',"+cust.getCoreasset().getId()+",0);\n");
        return insert;
    }

    private String generateInsertsForDevelopers() {
        String  insert="";
        String header = "INSERT INTO developer (iddeveloper,name,email) VALUES\n";
        Iterator<Developer> devs = CustomDiffProducts.allDevelopers.iterator();
        Developer dev;

        while(devs.hasNext()){
            dev= devs.next();
            insert=insert.concat(header).concat("("+dev.getId()+",'"+dev.getName()+"','"+dev.getEmail()+"');\n");
        }
        return insert;
    }

    private String generateInsertsForProductsReleasesAndCustomizations() {
        String  insert="";
        //	String product_header = "INSERT INTO product (idproduct,name) VALUES\n";

        Iterator<Product> products = CustomDiffProducts.spl.getProductPortfolio(0).getProducts().iterator();
        Product p;
        while(products.hasNext()){
            p=products.next();
            //insert = insert.concat(product_header).concat("("+p.getId()+",'"+p.getName()+"');");//introduce product
            insert = insert.concat(getInsertsForProductReleases(p));
        }

        return insert;
    }

    private String getInsertsForProductReleases(Product p) {
        String  insert="";//id_productrelease
        String releaseHeader = "INSERT INTO product_release (idproductrelease,name,date,commits_set) VALUES\n";//id_product

        Iterator<ProductRelease> releases = p.getReleases().iterator();
        ProductRelease pr;
        while(releases.hasNext()){
            pr = releases.next();
            insert = insert.concat(releaseHeader).concat("("+pr.getId()+",'"+ pr.getTagName()+"','"+convertDateToMysqlForm(pr.getReleaseDate())+"','"+pr.getCommitsSetToString()
                    +"');\n");//inserts for product release  +pr.getFromProduct().getId()+

            insert = insert.concat(getInsertsForProductCustomizations(pr));
        }
        return insert;
    }



    private String generateInsertsForFeatures() {
        String  insert="";

        String header_parent = "INSERT INTO parent_feature (idparentfeature,name) VALUES\n";
        String header = "INSERT INTO feature (idfeature,name,isNew,idparent) VALUES\n";
        //empty feature- for customizations with no VP
        String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
        String header_f_group = "INSERT INTO feature_group (id_feature_group) VALUES\n";

        insert=insert.concat(header_parent).concat("(0,'No Feature');\n");
        insert=insert.concat(header).concat("('No Feature','No Feature',0,0);\n");//no feature
        insert=insert.concat(header_f_group).concat("(0);\n"); //group for no feature
        insert=insert.concat(header_bridge).concat("(0,'No Feature');\n");


        Iterator<Feature> it = CustomDiffProducts.features.iterator();
        Feature f;
        int idparent=0;
        while(it.hasNext()){
            f = it.next();
            addedFeatures.add(f);
            if(addedparentFeatures.containsValue(f.getParentFeatureName())){//if it is already in inserted in the db

                idparent= Utils.getKeyFromValue(addedparentFeatures, f.getParentFeatureName());
                System.out.println("Parent "+f.getParentFeatureName()+ " already inserted with id: "+idparent);
            }
            else{//insert the parent_feature first
                insert=insert.concat(header_parent).concat("("+id_parentfeature+",'"+f.getParentFeatureName()+"');\n");
                idparent=id_parentfeature;
                addedparentFeatures.put(id_parentfeature,f.getParentFeatureName());
                id_parentfeature++;
            }

            insert=insert.concat(header).concat("('"+f.getIdFeature()+"','"+f.getName()+"',"+f.getIsNewToInt()+","+idparent+");\n");


        }



        return insert;
    }


    private String generateInsertsForVariationPoints(){
        String insert="";


        String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group) VALUES\n";
        String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
        String header_f_group = "INSERT INTO feature_group (id_feature_group) VALUES\n";

        List<SourceCodeFile> coreassets = CustomDiffProducts.spl.getCoreAssetBaseline(0).getCoreAssetFiles();
        Iterator<SourceCodeFile> it = coreassets.iterator();
        SourceCodeFile ca;
        List<VariationPoint> vps;
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
                //	System.out.print("\nVp in file:"+ca.getFileName()+" has vp "+vp.getVPFullExpression(vp) +" with features:\n");
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
        String header = "INSERT INTO core_asset (idcoreasset, name, path,size,isNewAsset,idpackage) VALUES\n";//,content


        List<SourceCodeFile> coreassets = CustomDiffProducts.spl.getCoreAssetBaseline(0).getCoreAssetFiles();
        Iterator<SourceCodeFile> it = coreassets.iterator();
        SourceCodeFile ca;

        String package_name;
        int id_pack=0;
        while(it.hasNext()){
            ca = it.next();
            package_name = getComponentPackageForCoreAsset(ca);
            if (packages.containsValue(package_name))
                id_pack = getKeyByValue(package_name);
            else {
                insert = insert + getInsertsForComponentPackage(package_name);
                id_pack = this.id_package -1;
            }


            insert = insert.concat(header).concat("("+ca.getId()+",'"+ca.getFileName()+"','"+ca.getPath()
                    +"',"+ca.getTotalLines()+","+"0"+","+id_pack+");\n"); //encodeToBase64(ca.getContent())
        }

        return insert;

    }

    private String getInsertsForComponentPackage(String component_name) {
        String  insert="";
        String header = "INSERT INTO component_package (idpackage, name, isroot) VALUES\n";

        this.packages.put(id_package, component_name);

        int root=0;
        if (component_name.equals(CustomDiffProducts.componentPackageRoot))
            root = 1;

        insert = insert.concat(header).concat("("+id_package+","+"'"+component_name+"',"+root+");\n");

        this.id_package ++;
        return insert;
    }

    public  int  getKeyByValue( String component) {
        for (Map.Entry<Integer,String> entry : packages.entrySet()) {
            if (component.equals(entry.getValue())) {
                System.out.println("component: "+ component+ "  Identified! with key:"+entry.getKey());
                return entry.getKey();
            }
        }
        return 0;
    }


    private String getComponentPackageForCoreAsset(SourceCodeFile ca) {
        String component_package_name;
        String ca_path = ca.getPath();
        String ca_name = ca.getFileName();


        String[] splitted = ca_path.split(CustomDiffProducts.componentPackageRoot);
        System.out.println("name:" + ca_path);

        String[] compontent = splitted[1].split("/");
        System.out.println("component: " + compontent[1]);

        component_package_name = compontent[1];

        if (component_package_name.equals(ca_name))
            component_package_name=CustomDiffProducts.componentPackageRoot;//it belongs to the root folder, it does not have a component attached.

        return component_package_name;
    }



    private String convertDateToMysqlForm(Date date){
        java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(date);
        return currentTime;
    }

    private String encodeToBase64(String str) {
        // encode data on your side using BASE64
        // https://stackoverflow.com/questions/19743851/base64-java-encode-and-decode-a-string
        if (str == null) {
            return null;
        }

        return Base64.encodeBytes(str.getBytes());
    }
}
