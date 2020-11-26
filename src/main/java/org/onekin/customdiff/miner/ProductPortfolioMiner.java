package org.onekin.customdiff.miner;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.CustomDiffProducts;
import org.onekin.customdiff.spldomain.*;
import org.onekin.customdiff.utils.FileUtils;
import org.onekin.customdiff.utils.Utils;
import org.onekin.customdiff.utils.VariationPointAnalysisUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.onekin.customdiff.utils.RefUtils;

public class ProductPortfolioMiner {

    private Integer portfoliocount = 1;
    private ArrayList<ProductRelease> productReleases;
    private ProductPortfolio pp;


    public void mine(String baselineToMine, SPL spl, String pathToWhereCustomizationsAreComputed, List<ProductPortfolio> portfolios) {

        System.out.println("------ Mining Product Portfolios ------");
        System.out.println("---------------------------------------");

        // 1: Identify which baseline commit
        RevCommit baselineCommit = RefUtils.getCommitFromRefName(baselineToMine);

        System.out.println("Baseline Commit to mine: " + baselineCommit.getName());

        // 2: get all the product tags that were derived from the baselineCommit
        ArrayList<Ref> prRefs = getProductTagsDerivedFromBaseline(baselineCommit);
        if (prRefs.size() == 0) return;
        else System.out.println("List of product releases in baseline " + prRefs.size());


        // 3: create product portfolio and add it to the spl
        pp = new ProductPortfolio(spl.getCoreAssetBaseline(0), "Series-from-" + spl.getCoreAssetBaseline(0).getId());

        portfolios.add(pp);

        ArrayList<Product> products = new ArrayList<Product>();//list of the  portfolio products
        portfolios.get(0).setProducts(products);

        // 4: for each product release Ref, create a product release object and extract product.

        Ref ref;
        Iterator<Ref> it = prRefs.iterator();
        productReleases = new ArrayList<ProductRelease>();
        while (it.hasNext()) {
            ref = it.next();

            Product p = createProductFromRef(ref, portfolios.get(0));
            Date d = new Date(RefUtils.getCommitFromRef(ref).getCommitTime());
            ProductRelease pr = new ProductRelease(Utils.getProductReleaseId(), ref.getName(), p, d, RefUtils.getCommitFromRef(ref));//id,from-product,date,revCommit
            Iterable<RevCommit> listOfcommits = RefUtils.getCommitsBetweenCommits(baselineCommit, RefUtils.getCommitFromRef(ref));
            pr.setListOfcommits(listOfcommits);
            p.addProductRelease(pr);
            productReleases.add(pr);
            extractAssetsForProductRelease(pr, pathToWhereCustomizationsAreComputed);

            /**Printing purposes only -- -- delete*/
            System.out.println("Commits for " + ref.getName());
            Iterator<RevCommit> ite = listOfcommits.iterator();
            RevCommit c;
            while (ite.hasNext()) {
                c = ite.next();
                System.out.println("Commit:" + c.getName());
            }
            System.out.println("Product Release found: " + pr.toString());
        }

        System.out.println("------ Finished Mining Product Portfolios ------");
    }


    private Product createProductFromRef(Ref ref, ProductPortfolio pp) {
        String name = Utils.extractRefNameForProduct(ref.getName());//refs/tags/<name>

        ArrayList<Product> products = pp.getProducts();
        Iterator<Product> it = products.iterator();
        Product aux;
        while (it.hasNext()) {
            aux = it.next();
            if (aux.getName().equals(ref.getName())) {
                return aux;//already existing product, but yet it has another release
            }
        }

        Product p = new Product(Utils.getNewProductId(), name, pp);
        System.out.println(p.toString());
        products.add(p);
        return p;
    }

    private ArrayList<Ref> getProductTagsDerivedFromBaseline(RevCommit baselineCommit) {

        ArrayList<Ref> allrefs = RefUtils.listTagsFromRepoByPrefix(CustomDiffProducts.productsReleaseTagPrefix);// get all the tags that match "product" pattern
        //ArrayList<Ref> allrefs = RefUtils.listTagsFromRepoByPrefix("refs/tags/");
        //System.out.println("allrefs: "+allrefs);
        Iterator<Ref> it = allrefs.iterator();

        ArrayList<Ref> prs = new ArrayList<Ref>();
        while (it.hasNext()) {
            Ref aux = it.next();
            if (RefUtils.isRefFirstDerivedFromBaseline(baselineCommit, aux)) {
                System.out.println(aux.getName() + " Id:" + aux.getObjectId().name() + " DERIVED FROM " + baselineCommit.getName());
                prs.add(aux);
            }
        }
        return prs;
    }

    private void extractAssetsForProductRelease(ProductRelease pr, String inPath) {
        RevCommit revCommit = pr.getReleasedCommit();
        Repository repo;
        try {
            repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
            System.out.println("Commit: " + revCommit.getId().getName());
            Git git = new Git(repo);
            git.checkout().setName(revCommit.getId().getName()).call();
            RevWalk walk = new RevWalk(repo);
            RevCommit commit = walk.parseCommit(revCommit.getId());
            RevTree tree = commit.getTree();

            TreeWalk treeWalk = new TreeWalk(repo);
            treeWalk.addTree(tree);
            treeWalk.setRecursive(true); //FALSE

            SourceCodeFile file;
            String fileContent;
            String path;
            boolean isnew = false;
            ArrayList<SourceCodeFile> files = new ArrayList<SourceCodeFile>();
            System.out.println("BEFORE WHILE");
            while (treeWalk.next()) {
                path = treeWalk.getPathString();
                if (path.contains(inPath)) {
                    fileContent = FileUtils.readFileContentFromRepository(CustomDiff.repositoryPath + "/" + treeWalk.getPathString());
                    path = treeWalk.getPathString();
                    System.out.println("BEFORE");
                    isnew = isProductAssetNew(treeWalk.getPathString());
                    //1: create new product asset file
                    file = new ProductAssetFileAnnotated(Utils.getNewAssetId(), treeWalk.getNameString(), treeWalk.getPathString(), fileContent,
                            fileContent.split("\n").length, pr,
                            CustomDiffProducts.pathToWhereCustomizationsAreComputed.concat(treeWalk.getPathString().split(CustomDiffProducts.pathToWhereCustomizationsAreComputed)[1]),
                            isnew, "");//check if it is a new asset

                    //2: extract features and variation points for the product asset
                    List<VariationPoint> variationPoints = VariationPointAnalysisUtils.extractVPsFromFile(file, pr.getFromProduct().getInPortfolio().getDerivedFrom(), true);
                    file.setVariationPoints(variationPoints);
                    file.setFeatureToCodeMapping(VariationPointAnalysisUtils.extractFeatureMapFromFile(file));
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


    private boolean isProductAssetNew(String pathString) {
        System.out.println("IS asset new?" + pathString);
        Iterator<SourceCodeFile> cas = CustomDiffProducts.spl.getCoreAssetBaseline(0).getCoreAssetFiles().iterator();
        while (cas.hasNext()) {
            if (cas.next().getPath().equals(pathString))
                return false;
        }
        System.out.println("NEW ASSET FOUND: " + pathString);
        return true;
    }

    public Integer getPortfoliocount() {
        return portfoliocount;
    }

    public void setPortfoliocount(Integer portfoliocount) {
        this.portfoliocount = portfoliocount;
    }

    public ArrayList<ProductRelease> getProductReleases() {
        return productReleases;
    }

    public void setProductReleases(ArrayList<ProductRelease> productReleases) {
        this.productReleases = productReleases;
    }

    public ProductPortfolio getPp() {
        return pp;
    }

    public void setPp(ProductPortfolio pp) {
        this.pp = pp;
    }
}
