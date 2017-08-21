package preprocessing;
import java.awt.List;
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
import org.repodriller.domain.Commit;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import SPLconcepts.Product;
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
		
		for(int i=0; i< Main.spl.getProductPortfoliosSize(); i++){
			for (int j=0; j < spl.getProductPortfolio(i).getNumberOfProductsInPortfolio();j++){//for each product in porfolio, execute mineCustomizations
				for (int z=0; z < spl.getProductPortfolio(i).getProductFromPortfolio(j).getReleases().size(); z++)
					mineCustomizationEffort(spl.getProductPortfolio(i).getProductFromPortfolio(j).getReleases().get(z));
					
			}
		}
		
		//(optional)save to EMF model 
		
		//2. Transform to csv files
	
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
	
		
	private void mineCustomizationEffort(ProductRelease productRelease) {
		System.out.println("INSIDE mineCustomizationEffort");
		
		List commitIDs = Utils.getCommitHashesBetweenTwoTags( productRelease.getFromProduct().getInPortfolio().getDerivedFrom().getTag(),
				productRelease.getIdRelease());
				
		new RepositoryMining()
		.in(GitRepository.singleProject(productRepo))
		.through((CommitRange) commitIDs)
		.filters()
		.process(new MineProductCustomizations(), new CSVFile (pathToResources+"/spl-data/customizations.csv"))
		.mine();
		
	}

}
