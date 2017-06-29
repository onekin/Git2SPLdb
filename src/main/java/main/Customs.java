package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.commit.OnlyInBranches;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;

import org.repodriller.scm.GitRepository;

public class Customs implements Study {

    public static String inputGitRepo; // Users/Onekin/Documents/workspace/WeatherStationSPL
	public static String pathToCSV; //Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/alluvial/sankey.csv
	public static String pathToAuxWorkSpace; //Users/Onekin/temp/aux.txt
	public static String pathToWhereCustomizationsAreComputed;//"input"
	
	/**Settings*/
	public static String productBranchPatternName="product";
	public static String coreAssetsBranchPatternName="master";
	public static String coreAssetsReleaseName="baseline";
	public static String productsReleaseName="product";
	public final static String annotationPatternBeginning="PV:IFCOND(pv:hasFeature"; //PV:IFCOND(pv:hasFeature('FeatureName'))
	public final static String annotationPatternEnd="PV:ENDCOND";
	


	
	public static void main(String[] args) {
		System.out.println ("Parameter lengh: "+args.length);
		System.out.println ("Parameter to string: "+args.toString());
		if(args.length==4){
			inputGitRepo=args[0];
			pathToCSV=args[1];
			pathToAuxWorkSpace = args[2];
			pathToWhereCustomizationsAreComputed=args[3];
			new RepoDriller().start(new Customs());
		}
		else System.out.println ("You need to provide me with the setting parameters");
	}

	public void execute() {
			new RepositoryMining()
			
			.in(GitRepository.singleProject(inputGitRepo))
			.through(Commits.all())
			.filters()
			.process(new CustomizationsAnalysisBaselineFeaturesProductPortfolio(), new CSVFile(pathToCSV))
			.mine();
	}
}