#Git2SPLdb

Git2SPLdb is part of the CustomDIFF tool. Check a video about it: https://www.youtube.com/watch?v=HF7ZdebMAIo

Git2SPLdb mines a Git repository, where an SPL is  being developed. It traverses all the branches where products have customized/changes feature implementations, and computes the customizations effort those products (i.e. what featuares have been customized and how much). Finally, it outputs  a set of mysql-based scripts - that constain the information about product customizations.

#Git2SPLdb works for:
1.- Annotated based SPLs. So far it works for pure::variants framework. This means that code is annotated with preprocessor directives with the pattern PV:IFCOND(hasFeature(....))
2.- For git repositories that have:
  2.1. A (master) branch where the core-assets are developed. At least one commit needs to be taged (e.g. Baseline-v1)
  2.2 There are multiple branches created off the tagged commit from master. Product branches need to follow the same naming convention pattern. E.g. all start by product (productA, productC)
  2.3. Product branches account with several commits. And at least there is one taged commit in product branches (e.g. productA-v1)
To see how a git repository for developing SPLs should look like, go to: https://github.com/letimome/WeatherStationSPL-JSS

#Get Git2SPLdb & run it

Git2SPLdb contains the entire Eclipse project. So just clone it to your local computer & import it in Eclipse.

You should run customDIFF.customDIFF.java. With a silimar to this configuration. 10 parameters (some of them are repeated- Sorry):

1. the path to your local Git repo: E.g.  /Users/onekin/git/WeatherStationSPL-JSS
2. The path to your local copy of Git2SPLdb's  resources folder. E.g. /Users/onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources
3. Which folder of your Git repo you want to mine  input
4. The tag name for the core-assets. Eg.  Baseline-v1.0
5. The path to the feature model of the git repository  /Users/onekin/git/WeatherStationSPL-JSS/WS.xfm
6. Which folder of your Git repo you want to mine   (repeated). E.g.  input
7. The characters by which product branch names start.  E.g. product
8. The tag name for the core-assets. Eg.  Baseline-v1.0
9. The branch name of  the main branch where the core-assets are tagged. E.g.  master
10. The path to the feature model of the git repository  /Users/onekin/git/WeatherStationSPL-JSS/WS.xfm 

After running it you would need to create a mysql database, with the two scripts that Git2SPLdb has generated in the project path "/scr/main/resource/db-data"
*olapCustoms.sql --> this is the database squema
*olap-inserts.sql --> these are the data inserts to populate the database

#What to do next?
Once you have the database in place, you can deploy CustomDIFF to start visualizing the data stored in you db. Go to:
https://github.com/letimome/CustomDiff


#Acknowledgements
The parsing Git2SPLdb 3rd party tools that SPLCustoms utilizes
RepoDriller, a framework to mine Git repos: see at https://github.com/mauricioaniche/repodriller 

#Copyright
Copyright (c) 2018 University of the Basque Country Spain (UPV/EHU)
