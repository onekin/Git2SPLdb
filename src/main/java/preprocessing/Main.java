package preprocessing;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.domain.ChangeSet;
import org.repodriller.domain.Commit;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;
import org.repodriller.scm.SCM;
import org.repodriller.scm.SCMRepository;

import utils.Utils;

import SPLconcepts.CustomizationEffort;
import SPLconcepts.Product;
import SPLconcepts.ProductPortfolio;
import SPLconcepts.ProductRelease;
import SPLconcepts.SPL;

public class Main implements Study {
	
	String variabilityImplementation= "annotation";//composition
	public static String coreAssetsRepo; // Users/Onekin/Documents/workspace/WeatherStationSPL
	public static String productRepo;
	public static String pathToResources; //Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/alluvial/sankey.csv
	public static String pathToAuxWorkSpace; //Users/Onekin/temp/aux.txt
	public static String pathToWhereCustomizationsAreComputed;//"input"
		
		/**Settings for Git repos and annotation based SPLs*/
	
		public static String productBranchPatternName="product";
		public static String coreAssetsBranchPatternName="master";
		public static String coreAssetsReleaseName="baseline";
		public static String productsReleaseName="product";
		public final static String annotationPatternBeginning="PV:IFCOND(pv:hasFeature"; //PV:IFCOND(pv:hasFeature('FeatureName'))
		public final static String annotationPatternEnd="PV:ENDCOND";
	

	public static SPL spl=new SPL();

	public static void main(String[] args) {
		
		System.out.println ("Parameter lengh: "+args.length);
		System.out.println ("Parameter to string: "+args.toString());
		/**
		 * /Users/onekin/Documents/workspace/WeatherStationSPL
			/Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
			/Users/onekin/Desktop/aux.txt
			"input"
		 * 
		 * */
		
		if(args.length==4){
			
			coreAssetsRepo=args[0];
			productRepo=args[0];
			pathToResources=args[1];
			pathToAuxWorkSpace = args[2];
			pathToWhereCustomizationsAreComputed=args[3];
			new RepoDriller().start(new Main());
		}
		else System.out.println ("You need to provide me with the setting parameters");
	}
	
	public void execute() {
		//1. mine from Git repository to SPL model
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
		
		//(optional) Save to EMF model 
		
		//2. Transform to csv files
		
		//3. print info
		printCustomizations();
	
	}


	private void mineCoreAssetBaselines() {
		new RepositoryMining()
		.in(GitRepository.singleProject(coreAssetsRepo))
		.through(Commits.all())
		.filters()
		.process(new MineBaselines(), new CSVFile (pathToResources+"/spl-data/baselines.csv"))
		.mine();
		System.out.println("Finished processing CoreAsset Baselines");
	//	System.out.println(spl.getCoreAssetBaseline(0).getCoreAsset(0).getContent());
		
	}

	private void mineProductPorfolios() {

		new RepositoryMining()
		.in(GitRepository.singleProject(productRepo))
		.through(Commits.list(spl.getCoreAssetBaselinesAsCommitsHashes()))
		.filters()
		.process(new MineProductPortfolios(), new CSVFile (pathToResources+"/spl-data/portfolios.csv"))
		.mine();
		
	}
	
	ChangeSet convertHashToCommit(String hash, SCMRepository scmRepository){
		
		List<ChangeSet> all = scmRepository.getScm().getChangeSets();

		for(ChangeSet cs : all) {
			if(hash.equals(cs.getId())) {
				return cs;
			}
		}
		return null;
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
		
		ArrayList<String> commitIDs = Utils.getCommitHashesBetweenTwoTags(productRelease.getFromProduct().getInPortfolio().getDerivedFrom().getTag(),
				productRelease.getIdRelease());//baseline it was derived from  -- ProductRelease
				/**		
				new RepositoryMining()
				.in(GitRepository.singleProject(productRepo))
				.through(Commits.list(commitIDs))
				.filters()
				.process(new MineProductCustomizations(productRelease), new CSVFile (pathToResources+"/spl-data/customizations-"+name+"+.csv"))
				.mine();**/
		
		//I'm going my own way
		Iterator<String> it = commitIDs.iterator();
		while(it.hasNext()){//for each commit in the release call mine Product Customization
			String commit = it.next();
		    ChangeSet cs = convertHashToCommit(commit,GitRepository.singleProject(Main.productRepo));
		    MineProductCustomizations mineCust = new MineProductCustomizations(productRelease);
	 	    mineCust.mine(GitRepository.singleProject(productRepo), GitRepository.singleProject(productRepo).getScm().getCommit(cs.getId()), new CSVFile (pathToResources+"/spl-data/customizations-"+name+"+.csv"));
		}
	
	}
	
	private void printCustomizations() {
		ArrayList<ProductPortfolio> ppList = Main.spl.getProductPortfolioList();
		Iterator<ProductPortfolio> it = ppList.iterator();
		
		ProductPortfolio pp;
		while(it.hasNext()){
			pp = it.next();
			ArrayList<Product> products= pp.getProducts();
			Iterator<Product> i= products.iterator();
			Product p;
			ArrayList<ProductRelease> listPR;
			while(i.hasNext()){
				p=  i.next();
				listPR=p.getReleases();
				Iterator<ProductRelease> prIt = listPR.iterator();
				while(prIt.hasNext()){
					ArrayList<CustomizationEffort> custs = prIt.next().getCustomization();
					Iterator<CustomizationEffort> ite = custs.iterator();
					while(ite.hasNext()){
						CustomizationEffort cust = ite.next();
						System.out.println(
								"ID:"+cust.getCustomizationId()+"\n"+
								"In release" +cust.getInRelease().getIdRelease()+"\n"+
								"Product file"+cust.getProductFile().getFileName()+"\n"+
								"Core Asset file:"+cust.getCoreAssetFile().getFileName()+"\n"+
								"Feature modified:"+cust.getFeatureNameModified() +"\n"+
								"Added:"+cust.getAddedLines()+"\n"+
								"Deleted:"+cust.getDeletedLines()+"\n"+
								"Churn"+ cust.getChurn() +"\n-------------------");
								
					}
					
				}
			}
		}
		
	}

}
