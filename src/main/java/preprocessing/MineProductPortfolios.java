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

import OldMain.AnalyzeFeatureDetail;
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

	//these commits are already filtered core asset releases
	//mine "product" branches whose parents is only a core asset baseline
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {

		//note: each commit is a coreAssetBAseline; then each commit has a productPortfolio attached.
		pp = new ProductPortfolio(Main.spl.getCoreAssetBaselineFromCommit(commit));
		
		String productBranchName;
		Product p;
		ProductRelease release;
		CustomizationEffort custEffort;

		//let's see if commits belong to product branches
		if (isCommitIntoProductBranch(commit) ){
			if (commit.getBranches().contains(Customs.coreAssetsBranchPatternName)){ //this is the origin of the product
				Iterator<String> it = commit.getBranches().iterator();
				String brName =it.next();
				p = new Product(commit, brName, pp);
				p.computeAllItsReleases();
				//
				//mine Customizations for each Product
				new RepositoryMining()
				.in(GitRepository.singleProject(Main.productRepo))
				.through((CommitRange) commit)//(Commits.list(Main.spl.getCoreAssetBaselinesAsCommitsHashes()))
				.filters()
				.process(new MineProductPortfolios(), new CSVFile (Main.pathToResources+"/customizationss.csv"))
				.mine();
			}
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
