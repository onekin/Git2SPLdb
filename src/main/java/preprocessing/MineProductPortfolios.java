package preprocessing;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.repodriller.domain.ChangeSet;
import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;
import utils.Utils;
import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.Product;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductPortfolio;
import SPLconcepts.ProductRelease;
import SPLconcepts.SourceCodeFile;
import SPLconcepts.VariationPoint;

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
				  System.out.println("Al LORO: branch:"+brName+"   commit:" +commit.getHash() + "  nuevo:" +searchForNewerBaselineCommit(brName,commit).getHash());
				  if(searchForNewerBaselineCommit(brName,commit).getHash().equals(commit.getHash()) //if the product branch is really  derived from this exact baseline commit
						  &&
						 (!brName.equals(Main.coreAssetsBranchPatternName)))
				  {
					  p = new Product(utils.Utils.getNewProductId(), commit, brName, pp);
					  computeAllReleasesForProduct(p,repo);
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
	
	public void computeAllReleasesForProduct(Product p, SCMRepository repo) {
		  try{
			  Repository repository = new FileRepository(preprocessing.Main.productRepo+"/.git");
			  Git git = new Git(repository);
			  Iterable<RevCommit> revCommits = git.log().add(repository.resolve(p.getBranchName())).call();
			  
			  System.out.println("Commits for branch: "+p.getBranchName());
		        for(RevCommit revCommit : revCommits){
		          if(revCommit.getName().equals(p.getOriginCommit().getHash())) break; // do not add commits belonging to the core asset baselines
		        	p.getCommitList().add(revCommit);
		        	System.out.println(revCommit.getName());
		        	
		        }
		        
		        Iterator<RevCommit> it = p.getCommitList().iterator();
				while (it.hasNext()){
					RevCommit co = it.next();
					 List<Ref> list = git.tagList().call();	
					
					ArrayList<ObjectId> commits = new ArrayList<ObjectId>();
					for (Ref tag : list) {
						ObjectId object = tag.getObjectId(); 
					    if (object.getName().equals(co.getName())) {
					        commits.add(object);
					       // System.out.println("release for product" +this.releaseBranchName+ "is commit: "+object.getName()+ "  name:"+tag.getName());
					        ProductRelease pr = new ProductRelease (tag.getName(),p, new Date ( 1000L * co.getCommitTime()), co);
					        p.getReleases().add(pr);
					        mineProductAssetsForProductRelease(pr,repo);
					    }
					}
				}

		  }catch (Exception e ){
			  e.printStackTrace();
		  }
		
	}


	private void mineProductAssetsForProductRelease(ProductRelease pr1, SCMRepository repo) {
		try{
		
	
			repo.getScm().checkout(pr1.getReleasedCommit().getName());
			List<RepositoryFile> files = repo.getScm().files();
			
			SourceCodeFile PAFile;
			for(RepositoryFile file : files) {//Mining Files for a product release
				
				if(!file.getFile().getAbsolutePath().contains(Main.pathToWhereCustomizationsAreComputed)) continue;
				
				PAFile= new ProductAssetFileAnnotated(utils.Utils.getNewProductAssetId(), file.getFile().getName(),  file.getFile().getPath(), file.getSourceCode(),
						file.getSourceCode().split("\n").length, pr1,
						Main.pathToWhereCustomizationsAreComputed.concat(file.getFile().getAbsolutePath().split(Main.pathToWhereCustomizationsAreComputed)[1]));
				pr1.getProductAssets().add((ProductAssetFileAnnotated) PAFile);
				
				HashMap<Integer, ArrayList <String>> map = utils.FeatureAnalysisUtils.extractFeatureMapFromFile(PAFile, pr1.getFromProduct().getInPortfolio().getDerivedFrom()); //line-feature map
				ArrayList<VariationPoint> vps = utils.FeatureAnalysisUtils.extractVPsFromFile(PAFile, pr1.getFromProduct().getInPortfolio().getDerivedFrom());
				
				System.out.println("Map "+map);
				System.out.println("VPs "+vps);

				if (map != null) {
					PAFile.setFeatureToCodeMapping(map);
					PAFile.setVariationPoints(vps);	
				} 
				
		}
		} finally {
				repo.getScm().reset();
		}			
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
