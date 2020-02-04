package org.onekin.customdiff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.miner.BaselineMiner;
import org.onekin.customdiff.miner.CustomizationMiner;
import org.onekin.customdiff.export.ExportTarget;
import org.onekin.customdiff.export.ExportToStarDiagram;
import org.onekin.customdiff.spldomain.*;

import java.util.ArrayList;


public class CustomDiff {

    /**
     * Configurable fields - arguments
     */
    public static  String repositoryPath = "/Users/RaulMedeiros/Documents/19CustomDiff/WebAnnotatorSPL";
    public static  String baselineToMine = "v1";
    public static  String baselineToMine2 = "v2";
    public static   String pathToResources = "/Users/RaulMedeiros/Documents/19CustomDiff/Git2SPLdb/src/main/resources"; // E.g.:
    // Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resource/
    public static  String pathToWhereCustomizationsAreComputed = "input";// folder to look for customization effort.
    public static  String featureModelPath = "/Users/RaulMedeiros/Documents/19CustomDiff/WebAnnotatorSPL/WebAnnotator.xfm";
    public static  String componentPackageRoot = "input";

    public static  String coreAssetsReleaseTagPrefix = "1.";
    public static  String coreAssetsBranchName = "master";
    public  static  String annotationPatternBeginning = "PVSCL:IFCOND";
    public  static  String annotationPatternEnd = "PVSCL:ENDCOND";

    /**
     * Field variables
     **/
    public static SPL spl;
    public static ArrayList<ProductPortfolio> portfolios;
    public static ArrayList<Feature> features;
    public static ArrayList<Developer> allDevelopers;

    private static final Logger logger = LogManager.getLogger(CustomDiff.class);

    public static void main(String[] args) {
        logger.info("---------------------------------------------------------");
        logger.info("--- Welcome to CustomDiff: Git to db extraction module---");
        logger.info("---------------------------------------------------------");

        /**
         * /Users/onekin/git/WeatherStationSPL
         * /Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
         * input Baseline-v1.0 /Users/onekin/git/WeatherStationSPL/WS.xfm input product
         * baseline master /Users/onekin/git/WeatherStationSPL/WS.xfm
         *
         **/

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
        } else {
            logger.info("Parameters are those hard-coded");
        }
        startMiningProcess();
    }

    private static void startMiningProcess() {


        /** 1:initialize the SPL objects **/
        spl = new SPL(repositoryPath);
        features = new ArrayList<>();
        allDevelopers = new ArrayList<>();

        /**
         * 2: fetch the first baseline tag to mine & create new coreAssetBaseline
         * release
         **/
        BaselineMiner baselineMiner = new BaselineMiner();
        CoreAssetBaseline baseline = baselineMiner.mine(baselineToMine, false);

        /**
         * 3: fetch the second baseline tag to mine & create new coreAssetBaseline
         * release
         **/
        BaselineMiner baselineMiner2 = new BaselineMiner();
        CoreAssetBaseline baseline2 = baselineMiner2.mine(baselineToMine2, true);
        ;

        /** 4: for each product release - compute customization effort. **/

        CustomizationMiner customizationsMiner = new CustomizationMiner();
        customizationsMiner.mine(baseline, baseline2);


        /** 5: export data to the database **/
        ExportTarget export = new ExportToStarDiagram();
        export.export(pathToResources + "/db-data/WebAnnotator.sql");


    }

}
