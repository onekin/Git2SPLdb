package preprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.domain.ChangeSet;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import exporting.ExportTarget;
import exporting.ExportToMySQLDatabase;
import utils.Utils;
import SPLconcepts.Customization;
import SPLconcepts.Feature;
import SPLconcepts.Product;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductPortfolio;
import SPLconcepts.ProductRelease;
import SPLconcepts.SPL;
import SPLconcepts.SourceCodeFile;

public class Main implements Study {
	
	String variabilityImplementation= "annotation";//composition
	public static String coreAssetsRepo; // Users/Onekin/Documents/workspace/WeatherStationSPL
	public static String productRepo;
	public static String pathToResources; //Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/alluvial/sankey.csv
	
	public static String pathToWhereCustomizationsAreComputed;//"input"
		
		/**Settings for Git repos and annotation based SPLs*/
	
		public static String productBranchPatternName="product";
		public static String coreAssetsBranchPatternName="master";
		public static String coreAssetsReleaseName="baseline";
		public static String productsReleaseName="product";
		public final static String annotationPatternBeginning= "hasFeature";//pv:hasFeature
		public final static String annotationPatternEnd="PV:ENDCOND";//"PV:ENDCOND";
	

	public static SPL spl;
	public static ArrayList<Feature> features;
	

	public static void main(String[] args) {
		
		System.out.println ("Parameter lengh: "+args.length);
		System.out.println ("Parameter to string: "+args.toString());
		/**
		 * /Users/onekin/Documents/workspace/WeatherStationSPL
			/Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
			"input"
		 * 
		 * */
		
		if(args.length==4){
			
			coreAssetsRepo=args[0];
			productRepo=args[0];
			pathToResources=args[1];
	//		pathToAuxWorkSpace = args[2];
			pathToWhereCustomizationsAreComputed=args[3];
			new RepoDriller().start(new Main());
		}
		else System.out.println ("You need to provide me with the setting parameters");
	}
	
	public void execute() {
		//1. mine from Git repository to SPL model
		this.spl=new SPL(this.productRepo);
		this.features= new ArrayList<Feature>(); 
		mineCoreAssetBaselines();
		mineProductPorfolios();
		
	//	System.out.println("Main.spl.getProductPortfoliosSize() ="+Main.spl.getProductPortfoliosSize());
	//	System.out.println(Main.spl.getProductPortfolio(0).getProductFromPortfolio(0));
		
		for(int i=0; i< Main.spl.getProductPortfoliosSize(); i++){
			for (int j=0; j < spl.getProductPortfolio(i).getNumberOfProductsInPortfolio();j++){//for each product in porfolio, execute mineCustomizations
				for (int z=0; z < spl.getProductPortfolio(i).getProductFromPortfolio(j).getReleases().size(); z++)
					mineCustomizationEffort(spl.getProductPortfolio(i).getProductFromPortfolio(j).getReleases().get(z),"Release-"+spl.getProductPortfolio(i).getProductFromPortfolio(j).getReleases().get(z).getIdRelease()+z);
					
			}
		}
		
		//3. print info
	 //	printCustomizations(); printPP(); printFilesInRelease(); printlnFeatures(); 
		printCoreAssetsAndVariationPoints();
	 	//System.out.println("Core Asset counter:" +utils.Utils.getCoreAssetFileCounter());
	 	//System.out.println("Product Asset counter :" +utils.Utils.getProductAssetFileCounter());
	 	for(int i=0; i< spl.getCoreAssetBaselineSize(); i++){
	 		ArrayList<SourceCodeFile> assets = spl.getCoreAssetBaseline(i).getCoreAssetFiles();
	 		for (int j=0; j< assets.size(); j++){
	 			System.out.println("ASSET ID:"+assets.get(j).getId());
	 			System.out.println("Feature-to-code:"+assets.get(j).getFeatureToCodeMapping());
	 		}
	 	}
	 	
	 	ExportTarget export = new ExportToMySQLDatabase(Main.pathToResources+"/db-data/");
		export.export();
	}


	private void printCoreAssetsAndVariationPoints() {
		// TODO Auto-generated method stub
		
	}

	private void printlnFeatures() {
		System.out.println("-------Printing feature list-------------");
		for  (int i=0;i<this.features.size();i++){
			System.out.println("Feature Names: "+features.get(i).getName());
		}
		System.out.println("---------------------------");
	}

	private void printFilesInRelease() {

		String branchName = "productBerlin";
		
		Product p = Main.spl.getProductPortfolio(0).getProductFromPortfolio(branchName);
		ArrayList<ProductAssetFileAnnotated> caFiles = p.getReleases().get(0).getProductAssets();
		Iterator<ProductAssetFileAnnotated> it = caFiles.iterator();
		int i=1;
		while (it.hasNext()){
			ProductAssetFileAnnotated ca = it.next();
			if(ca.getFileName().equals("Weather Station User Manual.xml")){
				System.out.println("RELEASE:" +p.getReleases().get(1).getIdRelease());
				System.out.println("File number:" +i);
			  System.out.println("File path:" +ca.getPath());
			  System.out.println("File name:" +ca.getFileName());
			  System.out.println("Content: \n "+ ca.getContent());
			}
			i++;
		}

	}

	private void mineCoreAssetBaselines() {
		new RepositoryMining()
		.in(GitRepository.singleProject(coreAssetsRepo))
		.through(Commits.all())
		.filters()
		.process(new MineBaselines(), new CSVFile (pathToResources+"/spl-data/baselines.csv"))
		.mine();
		System.out.println("Finished processing CoreAsset Baselines");
		System.out.println(spl.getCoreAssetBaseline(0).getCoreAsset(5).getFeatureToCodeMapping());
		
	}

	private void mineProductPorfolios() {

		new RepositoryMining()
		.in(GitRepository.singleProject(productRepo))
		.through( Commits.list( spl.getCoreAssetBaselinesAsCommitsHashes()))
		.filters()
		.process(new MineProductPortfolios(), new CSVFile (pathToResources+"/spl-data/portfolios.csv"))
		.mine();
		
	}
	
	
	
	/****public List<ChangeSet> getCommitIDsToCommits(SCM scm, List<String> commits) {
		List<ChangeSet> all = scm.getChangeSets();
	
		List<ChangeSet> filtered = new ArrayList<ChangeSet>();
		for(ChangeSet cs : all) {
			if(commits.contains(cs.getId())) {
				filtered.add(cs);
			}
		}
		
		return filtered;
	}****/
		
	private void mineCustomizationEffort(ProductRelease productRelease, String name) {
		System.out.println("Mining customizations for "+productRelease.getIdRelease());
		
		ArrayList<String> commitIDs 
		= Utils.getCommitHashesBetweenTwoTags(productRelease.getFromProduct().getInPortfolio().getDerivedFrom().getId(), productRelease.getIdRelease());//baseline it was derived from  -- ProductRelease
				/**new RepositoryMining()
				.in(GitRepository.singleProject(productRepo))
				.through(Commits.list(commitIDs))
				.filters()
				.process(new MineProductCustomizations(productRelease), new CSVFile (pathToResources+"/spl-data/customizations-"+name+"+.csv"))
				.mine();**/
		
		//I'm going my own way
		Iterator<String> it = commitIDs.iterator();
		ArrayList<Customization> commitCustomizationsDetails = new ArrayList<Customization>();
		while(it.hasNext()){//for each commit in the release call mine Product Customization
			String commit = it.next();
		    ChangeSet cs = Utils.convertHashToCommit(commit,GitRepository.singleProject(Main.productRepo));
		    MineProductCustomizations mineCust = new MineProductCustomizations(productRelease);
		    commitCustomizationsDetails.addAll( mineCust.mine(GitRepository.singleProject(productRepo), GitRepository.singleProject(productRepo).getScm().getCommit(cs.getId()), new CSVFile (pathToResources+"/spl-data/customizations-"+name+"+.csv")));
		}
	}
	
	private void printCustomizations() {
		ArrayList<ProductPortfolio> ppList = Main.spl.getProductPortfolioList();
		Iterator<ProductPortfolio> it = ppList.iterator();
		

		
		ProductPortfolio pp;
		while(it.hasNext()){
			pp = it.next();
			ArrayList<Product> products= pp.getProducts();
			Iterator<Product> productIt= products.iterator();
			Product p;
			ArrayList<ProductRelease> listPR;
			while(productIt.hasNext()){
				p=  productIt.next();
				listPR=p.getReleases();
				Iterator<ProductRelease> prIt = listPR.iterator();
				ArrayList<Customization> custs;
				while(prIt.hasNext()){
					custs = prIt.next().getCustomizations();
					Iterator<Customization> ite = custs.iterator();
					Customization cust;
					while(ite.hasNext()){
						cust = ite.next();
						System.out.println(
								//"ID:"+cust.getCustomizationId()+"\n"+
								"In release: " +cust.getInRelease().getIdRelease()+"\n"+
								"Product file: "+cust.getProductFile().getFileName()+"\n"+
								"Core Asset file: "+cust.getCoreAssetFile().getFileName()+"\n"+
								"Feature modified: "+cust.getFeatureModifiedName() +"\n"+
								"Operation: "+cust.getOperation()+"\n"+
								"LineChanged: "+cust.getLineOfCodeModified()+"\n-------------------");		
					}
					
				}
			}
		}
		
	}

	private void printPP() {
		System.out.println("---------------Printing ProductPortfolios--------");
		int size = Main.spl.getProductPortfolios().size();//
		int i=0;
		while( i< size){
			System.out.println("Portfolio: "+Main.spl.getProductPortfolios().get(i).getPortfolioID());
			System.out.println("DerivedFRom: "+ Main.spl.getProductPortfolios().get(i).getDerivedFrom().getId());
			System.out.println("Number of products: "+ Main.spl.getProductPortfolios().get(i).getNumberOfProductsInPortfolio());
			ArrayList<Product> products = Main.spl.getProductPortfolios().get(i).getProducts();
			System.out.println("Product SIZE: "+ products.size());
			
			Iterator <Product> it = products.iterator();
			Product p;
			while (it.hasNext()){
				p=it.next();
				System.out.println("PRODUCT  name " +	p.getBranchName());
				System.out.println("Origin Comit " +	p.getOriginCommit().getHash());
				System.out.println(" releases count:" + p.getReleases().size());
				System.out.println(" releases to string: " + p.getReleases().toString());
			}
			
			i++;
		}

	}
}
