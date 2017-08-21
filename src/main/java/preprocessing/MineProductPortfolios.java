package preprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.repodriller.RepositoryMining;
import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffLine;
import org.repodriller.domain.DiffParser;
import org.repodriller.domain.Modification;
import org.repodriller.filter.range.CommitRange;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.persistence.csv.CSVFile;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.GitRepository;
import org.repodriller.scm.SCMRepository;

import OldMain.CustomizationDetail;
import OldMain.Customs;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CustomizationEffort;
import SPLconcepts.Product;
import SPLconcepts.ProductPortfolio;
import SPLconcepts.ProductRelease;

public class MineProductPortfolios implements CommitVisitor {
	
	boolean headerFlag=false;
	
	ProductPortfolio pp;
	ArrayList<ProductPortfolio> portfolios;
	Product product;
	ProductRelease pr;
	
	Commit currentCommit = null;
	ArrayList <String> listOfAnalyzedProductBranches = new ArrayList<String> ();
	ArrayList <CoreAssetBaseline> listOfAnalyzedCoreAssetBaselines = new ArrayList <CoreAssetBaseline>(); 
	Integer portfoliocount=1;

	//these commits are already filtered core asset releases
	//mine "product" branches whose parents is only a core asset baseline
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Portfolio-ID","DerivedFrom-baselineID","ProductName", "ReleaseName", "ReleaseDate");
		}
		
		//note: each "commit" is a coreAssetBAseline; then each commit has a productPortfolio attached.
		
		CoreAssetBaseline originB = Main.spl.getCoreAssetBaselineFromCommit(commit);
		pp = new ProductPortfolio(originB , portfoliocount);
		
		Product p;

		if (isCommitIntoProductBranch(commit) ){//let's see if commits belong to product branches
			if (commit.getBranches().contains(Customs.coreAssetsBranchPatternName)){ //this commit is the origin of the product
				Iterator<String> it = commit.getBranches().iterator();
				String brName;
				while(it.hasNext()){
					
				  brName =it.next();
				  if (brName.contains(Main.productBranchPatternName)){
					  p = new Product(commit, brName, pp);
					  p.computeAllReleases();
					  
					  for (int i=0; i< p.getReleases().size(); i++){
						  writer.write(
								  pp.getPortfolioID(),
								  pp.getDerivedFrom().getCommit().getHash(), 
								  p.getBranchName(),
								  p.getReleases().get(i).getIdRelease(),
								  p.getReleases().get(i).getReleaseDate());
					  }
				  }

				}
				portfoliocount++;
				
				//mine Customizations for each Product
				/*
				ArrayList<String> commitList = new ArrayList <String>();
				commitList.add(commit.getHash());
				new RepositoryMining()
				.in(GitRepository.singleProject(Main.productRepo))
				.through(commitList)//(Commits.list(Main.spl.getCoreAssetBaselinesAsCommitsHashes()))
				.filters()
				.process(new MineProductPortfolios(), new CSVFile (Main.pathToResources+"/customizations.csv"))
				.mine();
				*/
				/*
				for(int i=0; i<p.getReleases().size();i++){
					writer.write(
							pp.getPortfolioID(),//"Series "+portfoliocount+".X",
							pp.getDerivedFrom(), //derived from coreAssetBaseline
							p.getBranchName(),
						"tag",//	p.getReleases().get(0).getTag(),
						"date" )//	p.getReleases().get(0).getReleaseDate())
							;
				}
				*/
			
			}
		}
		else {
			
		}
	}

	public boolean isCommitIntoProductBranch(Commit co){
		Set<String> branches = co.getBranches();
		Iterator<String> it = branches.iterator();
		while (it.hasNext()){
			if (it.next().toString().startsWith(Main.productBranchPatternName))
				return true;
		}
		return false;
		
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
