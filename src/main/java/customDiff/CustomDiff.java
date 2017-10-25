package customDiff;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.ProductPortfolio;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SPL;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.export.ExportTarget;
import customDiff.export.ExportToStarDiagram;


public class CustomDiff {
	
	public static String repositoryPath; 
	public static String baselineToMine;
	public static String pathToResources; //E.g.: Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/
	public static String pathToWhereCustomizationsAreComputed;//folder to look for customization effort. 
		
	/**Settings for Git repos and annotation based SPLs*/
	public static String productBranchPatternName="product";
	public static String coreAssetsBranchPatternName="master";
	public static String coreAssetsReleaseName="baseline";
	public static String productsReleaseName="product";
	public final static String annotationPatternBeginning= "hasFeature";//pv:hasFeature
	public final static String annotationPatternEnd="PV:ENDCOND";//"PV:ENDCOND";
		
	public static SPL spl;
	public static ArrayList<ProductPortfolio> portfolios;
	public static ArrayList<Feature> features;
	public static ArrayList<Developer> allDevelopers;
		
	public static void main(String[] args) {
		System.out.println ("---------------------------------------------------------");
		System.out.println ("--- Welcome to CustomDiff: Git to db extraction module---");
		System.out.println ("---------------------------------------------------------");
		
		/**
			/Users/onekin/git/WeatherStationSPL
			/Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
			input
			Baseline-v1.0
		**/
		
		if(args.length==4){
			repositoryPath=args[0];
			pathToResources=args[1];
			pathToWhereCustomizationsAreComputed=args[2];
			baselineToMine=args[3];
		}
		else 
			System.out.println ("You need to provide me with the setting parameters");
		System.out.println("Arguments: "+args[3].toString());
		
		startMiningProcess();
	}

	private static void startMiningProcess(){
		
		/** 1:initialize the SPL objects **/
		spl = new SPL(repositoryPath);
		features= new ArrayList<Feature>();
		allDevelopers = new ArrayList<Developer>();
		
		/** 2: fetch the baseline tag to mine & create new coreAssetBaseline release **/
			BaselineMiner baselineMiner = new BaselineMiner();
			baselineMiner.mine(baselineToMine);
		

		/** 3: identify product releases **/			
			portfolios= new ArrayList<ProductPortfolio>();
			ProductPortfolioMiner prMiner = new ProductPortfolioMiner();
			prMiner.mine(baselineToMine);
			spl.setProductPortfolios(portfolios);

		/** 4: for each product release - compute customization effort.	**/
			
			Iterator< ProductRelease> it = prMiner.productReleases.iterator();
			
			ProductCustomizationMiner customizationsMiner;
			while(it.hasNext()){
				customizationsMiner = new ProductCustomizationMiner();
				customizationsMiner.mine(it.next());
			}
			
		/** 5: export data to the database	**/
		ExportTarget export = new ExportToStarDiagram();
		export.export(pathToResources+"/db-data/olap-inserts.sql");
		
	}

	

	

}
