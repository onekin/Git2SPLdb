package customDiff;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import customDiff.SPLdomain.Product;
import customDiff.SPLdomain.ProductAssetFileAnnotated;
import customDiff.SPLdomain.ProductPortfolio;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;
import customDiff.utils.RefUtils;

public class ProductPortfolioMiner{

	Integer portfoliocount=1;
	ArrayList<ProductRelease> productReleases;
	ProductPortfolio pp;
	

	public void mine (String baselineToMine){
		
		System.out.println ("------ Mining Product Portfolios ------");
		
	
		
		// 1: Identify which productreleases were derived from the baseline
		RevCommit baselineCommit = RefUtils.getCommitFromRefName(baselineToMine); 
				
		System.out.println(baselineCommit);
		
		// 2: get all the product tags that were derived from the baselineCommit
		ArrayList<Ref> prRefs = getProductTagsDerivedFromBaseline(baselineCommit); 
		if(prRefs.size()==0) return;
		else System.out.println("List of product releases in baseline "+prRefs.size());
		
		
		// 3: create product portfolio and add it to the spl
		 pp = new ProductPortfolio(CustomDiff.spl.getCoreAssetBaseline(0), "Series-from-"+CustomDiff.spl.getCoreAssetBaseline(0).getId());
	
		CustomDiff.portfolios.add(pp);
		ArrayList<Product> products = new ArrayList<Product>();//list of the  portfolio products
		CustomDiff.portfolios.get(0).setProducts(products);
		
		// 4: for each product release Ref, create a product release object and extract product.
		
		Ref ref;
		Iterator<Ref> it = prRefs.iterator();
		productReleases=new ArrayList<ProductRelease>();
		while(it.hasNext()){
			ref = it.next();
			System.out.println("Analysisng "+ref.getName());
			Product p = createProductFromRef(ref, CustomDiff.portfolios.get(0));
			Date d = new Date (RefUtils.getCommitFromRef(ref).getCommitTime());
			ProductRelease pr = new ProductRelease(ref.getName(), p, d, RefUtils.getCommitFromRef(ref));//id,from-product,date,revCommit
			p.addProductRelease(pr);
			productReleases.add(pr);
			extractAssetsForProductRelease(pr, CustomDiff.pathToWhereCustomizationsAreComputed);
			System.out.println (pr.toString());
		}

		System.out.println ("------ Finished Mining Product Portfolios ------");
	}



	private Product createProductFromRef(Ref ref, ProductPortfolio pp) {
		String name = customDiff.utils.Utils.extractRefNameForProduct(ref.getName());//refs/tags/<name>
		
		ArrayList<Product> products = pp.getProducts();
		Iterator<Product> it = products.iterator();
		Product aux;
		while(it.hasNext()){
			aux = it.next();
			if(aux.getName().equals(ref.getName())){
				return aux;//already existing product, but yet it has another release
			}
		}
		
		Product p= new Product(customDiff.utils.Utils.getNewProductId(), name, pp);
		System.out.println (p.toString());
		products.add(p);
		return p;
	}

	private ArrayList<Ref> getProductTagsDerivedFromBaseline(RevCommit baselineCommit) {
	
		ArrayList<Ref> allrefs = RefUtils.listTagsFromRepoByPrefix("refs/tags/"+CustomDiff.productsReleaseName);// get all the tags that match "product" pattern
		//System.out.println("allrefs: "+allrefs);
		Iterator<Ref> it = allrefs.iterator();
		
		ArrayList<Ref> prs = new ArrayList<Ref>();
		while(it.hasNext()){
			Ref aux = it.next();
			if (RefUtils.isRefFirstDerivedFromBaseline(baselineCommit,aux)){
				System.out.println(aux.getName()+ " DERIVED FROM "+baselineCommit.getName());
				prs.add(aux);
			}
		}
		return prs;
	}
	
	private void extractAssetsForProductRelease(ProductRelease pr,  String inPath) {
		RevCommit revCommit = pr.getReleasedCommit();
		Repository repo;
		try {
			repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			System.out.println("Commit: "+revCommit.getId().getName());
			Git git = new Git(repo);
			git.checkout().setName(revCommit.getId().getName()).call();
			RevWalk walk = new RevWalk(repo);
	        RevCommit commit = walk.parseCommit(revCommit.getId());
	        RevTree tree = commit.getTree();
	        
	        TreeWalk treeWalk = new TreeWalk(repo);
	        treeWalk.addTree(tree);
	        treeWalk.setRecursive(true); //FALSE
	        
	        SourceCodeFile file;
	        String  fileContent;
	        ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();
	        while (treeWalk.next()) {
	            if(treeWalk.getPathString().contains(inPath)){
	              fileContent = customDiff.utils.FileUtils.readFileContentFromRepository(customDiff.CustomDiff.repositoryPath+"/"+treeWalk.getPathString());
	              
	              //1: create new core asset file
	              file= new ProductAssetFileAnnotated(customDiff.utils.Utils.getNewCoreAssetId(), treeWalk.getNameString(), treeWalk.getPathString(), fileContent, 
		    				  fileContent.split("\n").length, pr, 
		    				  customDiff.CustomDiff.pathToWhereCustomizationsAreComputed.concat(treeWalk.getPathString().split(customDiff.CustomDiff.pathToWhereCustomizationsAreComputed)[1]));
	              
	              //2: extract features and variation points for the product asset
	             
		      	  ArrayList<VariationPoint> variationPoints = customDiff.utils.VariationPointAnalysisUtils.extractVPsFromFile(file, pr.getFromProduct().getInPortfolio().getDerivedFrom(), true);
		      	  file.setVariationPoints(variationPoints);
		      	  file.setFeatureToCodeMapping(customDiff.utils.VariationPointAnalysisUtils.extractFeatureMapFromFile(file));
	              files.add(file);
	            
	            }	            	
	        }
	        pr.setProductAssets(files);
	        repo.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}



}
