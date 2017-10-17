package customDiff;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.revwalk.RevCommit;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.Feature;
import SPLconcepts.SPL;
import SPLconcepts.SourceCodeFile;


public class CustomDiff {
	
	public static String coreAssetsRepo; 
	public static String productRepo;
	public static String baselineToMine;
	public static String pathToResources; //Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/
	public static String pathToWhereCustomizationsAreComputed;//folder to look for customization effort. 
		
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
			coreAssetsRepo=args[0];
			productRepo=args[0];
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
		
		/** 0:initialize the SPL objects **/
		spl = new SPL(productRepo);
		features= new ArrayList<Feature>();
		
		/** 1: fetch All the tags from the Git repository	**/
		Map<String, Ref> refs = customDiff.RefUtils.getAllTagsFromRepo();
		System.out.println(refs.toString());
		
		/** 2: fetch the baseline tag to mine & create new coreAssetBaseline release **/
		RevCommit baselineCommit = customDiff.RefUtils.getCommitFromTagName(baselineToMine);	
		CoreAssetBaseline baseline = new CoreAssetBaseline(baselineCommit, baselineCommit.getCommitTime(), baselineToMine);
		spl.addBaseline(baseline);
		mineFeaturesAndAssetsForBaseline(baseline);
		
		/** 3: fetch product releases that belong to that baseline	**/
		mineProductReleases(baselineCommit);
		
		
		/** 4: for each product release - compute customization effort.	**/
		

		/** 5: export data to the database	**/
		
	}

	private static void mineProductReleases(RevCommit baselineCommit) {
		// TODO Auto-generated method stub
		
	}

	private static void mineFeaturesAndAssetsForBaseline(CoreAssetBaseline baseline) {
		BaselineMiner baselineMiner = new BaselineMiner();
		
		ArrayList<SourceCodeFile> files = baselineMiner.mine(baseline, pathToWhereCustomizationsAreComputed);
		System.out.println("Number of files in Baseline:"+files.size());
		baseline.setCoreAssetFiles(files);
		
		/** Printing baseline contents **/
		System.out.println("Features in baseline: "+baseline.getId()+" are:"+ baseline.getFeatures().toString());
		Iterator<Feature> it = baseline.getFeatures().iterator();
		Feature f;
		while(it.hasNext()){
			f = it.next();
			System.out.println(f.getName());
		}
	}
	
	

}
