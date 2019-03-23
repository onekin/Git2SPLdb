package customDiff;

import java.util.ArrayList;
import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.ProductPortfolio;
import customDiff.SPLdomain.SPL;
import customDiff.export.ExportTarget;
import customDiff.export.ExportToStarDiagram;

public class CustomDiff {

    /**
     * Configurable fields - arguments
     */
    public static String repositoryPath = "/Users/leticia/git/linux";
    public static String baselineToMine2 = "v5.0-rc8";
    public static String baselineToMine = "v5.0";
    public static String pathToResources = "/Users/leticia/git/SPL2Gitdb/src/main/resources";
    // Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/
    public static String pathToWhereCustomizationsAreComputed="kernel";// folder to look for customization effort.
    public static ArrayList<String> exludedExtensions;
    public static String featureModelPath = null;
    public static String componentPackageRoot;

    public static String coreAssetsReleaseTagPrefix = "v5.0";
    public static String coreAssetsBranchName = "master";

    /*annotation beginings can be #if, #ifndef #ifdef; the ending is #endif*/
    public final static String annotationPatternBeginning = "#if";// pv:hasFeature
    public final static String annotationPatternEnd = "#endif";// "PV:ENDCOND";


    /**
     * Field variables *
     */
    public static SPL spl;
    public static ArrayList<ProductPortfolio> portfolios;
    public static ArrayList<Feature> features;
    public static ArrayList<Developer> allDevelopers;

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------");
        System.out.println("--- Welcome to CustomDiff: Git to db extraction module---");
        System.out.println("---------------------------------------------------------");

        /**
         * /Users/onekin/git/WeatherStationSPL
         * /Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
         * input Baseline-v1.0 /Users/onekin/git/WeatherStationSPL/WS.xfm input
         * product baseline master /Users/onekin/git/WeatherStationSPL/WS.xfm
         *
         *
         */
        if (args.length == 10) {
            repositoryPath = args[0];
            pathToResources = args[1];
            pathToWhereCustomizationsAreComputed = args[2];
            baselineToMine = args[3];
            featureModelPath = args[4];
            componentPackageRoot = args[5];
            baselineToMine2 = args[6];
            coreAssetsReleaseTagPrefix = args[7];
            coreAssetsBranchName = args[8];
            featureModelPath = args[9];
        }
        else{
            //take initilized ones
            exludedExtensions = new  ArrayList<>();
            exludedExtensions.add("xtfpga.dtsi");
            exludedExtensions.add("Makefile");
        }
        
        startMiningProcess();
    }

    private static void startMiningProcess() {

        /**
         * 1:initialize the SPL objects *
         */
        spl = new SPL(repositoryPath);
        features = new ArrayList<>();
        allDevelopers = new ArrayList<>();

        /**
         * 2: fetch the first baseline tag to mine & create new
         * coreAssetBaseline release
		 *
         */
        BaselineMiner baselineMiner = new BaselineMiner();
        CoreAssetBaseline baseline = baselineMiner.mine(baselineToMine, false);

        /**
         * 3: fetch the second baseline tag to mine & create new
         * coreAssetBaseline release
		 *
         */
        BaselineMiner baselineMiner2 = new BaselineMiner();
        CoreAssetBaseline baseline2 = baselineMiner2.mine(baselineToMine2, true);

        /**
         * 4: for each product release - compute customization effort. *
         */
        CustomizationMiner customizationsMiner = new CustomizationMiner();
        customizationsMiner.mine(baseline, baseline2);

        /**
         * 5: export data to the database *
         */
        ExportTarget export = new ExportToStarDiagram();
        export.export(pathToResources + "/db-data/olap-inserts.sql");

    }

}
