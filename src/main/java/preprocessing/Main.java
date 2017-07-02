package preprocessing;

import main.CustomizationsAnalysisBaselineFeaturesProductPortfolio;
import main.Customs;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.GitRepository;

import SPLconcepts.SPL;

public class Main implements Study {
	
	String variabilityImplementation= "annotation";//composition
	public static String coreAssetsRepo; // Users/Onekin/Documents/workspace/WeatherStationSPL
	public static String productRepo;
	public static String pathToResources; //Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/alluvial/sankey.csv
	public static String pathToAuxWorkSpace; //Users/Onekin/temp/aux.txt
	public static String pathToWhereCustomizationsAreComputed;//"input"
		
		/**Settings*/
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
		if(args.length==4){
			coreAssetsRepo=args[0];
			productRepo=args[0];
			pathToResources=args[2];
			pathToAuxWorkSpace = args[2];
			pathToWhereCustomizationsAreComputed=args[4];
			new RepoDriller().start(new Main());
		}
		else System.out.println ("You need to provide me with the setting parameters");
	}
	
	public void execute() {

		// 1: Mine coreAsset baselines
		new RepositoryMining()
		.in(GitRepository.singleProject(coreAssetsRepo))
		.through(Commits.all())
		.filters()
		.process(new MineBaselines(), new CSVFile (pathToResources+"/baselines.csv"))
		.mine();
		System.out.println("Finished processing CoreAsset Baselines");
		System.out.println( spl.getCoreAssetBaseline(0).getCoreAsset(0).getContent() );
	
		
		//2: Mine product portfolios
		//if(hasFeature(singleRepository))
		new RepositoryMining()
		.in(GitRepository.singleProject(productRepo))
		.through(Commits.list(spl.getCoreAssetBaselinesAsCommitsHashes()))
		.filters()
		.process(new MineProductPortfolios(), new CSVFile (pathToResources+"/portfolios.csv"))
		.mine();
		
		

		//3: lanzar el computo de customization effort 
		
	}

}
