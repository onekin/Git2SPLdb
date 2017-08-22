package preprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jgit.revwalk.RevCommit;
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

import utils.Utils;

import OldMain.CustomizationDetail;
import OldMain.Customs;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CustomizationEffortDeprecated;
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

		//if (isCommitIntoProductBranch(commit) && isCommitIntoProductBranch(originB.getCommit())){//let's see if commits belong to product branches
		//	if (commit.getBranches().contains(Customs.coreAssetsBranchPatternName)){ //this commit is the origin of the product
				
				System.out.println("GETTING branches for: "+commit.getHash()+"\n"+commit.getBranches().toString());
				Iterator<String> it = commit.getBranches().iterator();//lists all branches that include the "commit"
				String brName;
				
				while(it.hasNext()){
				  
				  brName =it.next();
				  System.out.println("Al LORO: branch:"+brName+"   commit:" +commit.getHash() + "nuevo:" +searchForNewerBaselineCommit(brName,commit).getHash());
				  if(searchForNewerBaselineCommit(brName,commit).getHash().equals(commit.getHash()) //if the product branch is really  derived from this exact baseline commit
						  &&
						 (!brName.equals(Main.coreAssetsBranchPatternName)))
				  {
					  p = new Product(commit, brName, pp);
					  p.computeAllReleases();
					  pp.addProductToPortfolio(p);
					  
					  
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
				Main.spl.getProductPortfolioList().add(pp);
				
			
	//		}
	//	}
		
	}
	


	public Commit searchForNewerBaselineCommit(String branchName, Commit currentCommit){
		
		Commit latestCAForBranch=currentCommit;
		ArrayList<Commit> coreAssetBaselines = Main.spl.getCoreAssetBaselinesAsCommits();
		
		Iterator<Commit> caIterator = coreAssetBaselines.iterator();
		while(caIterator.hasNext()){
			Commit newerCommit = caIterator.next();
			if (Utils.isCommitInBranch(newerCommit, branchName))//if the baseilne commit is in the branch
				if( newerCommit.getCommitterDate().after(currentCommit.getCommitterDate())){ //newer is after current commit 
					latestCAForBranch=newerCommit;
			}
			
		}
		
		return latestCAForBranch;
	}

	



	public boolean isCommitIntoAnyProductBranch(Commit co){
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
