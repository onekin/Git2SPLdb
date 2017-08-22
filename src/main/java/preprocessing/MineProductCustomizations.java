package preprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffParser;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;
import org.repodriller.domain.DiffLine;
import utils.FeatureAnalysisUtils;
import SPLconcepts.CustomizationDetail;
import SPLconcepts.ProductRelease;

public class MineProductCustomizations{   
	// implements CommitVisitor {

	private ProductRelease productRelease;
	ArrayList<CustomizationDetail> details = new ArrayList<CustomizationDetail>();
	
	boolean headerFlag=false;

	public MineProductCustomizations(ProductRelease productRelease) {
		this.productRelease = productRelease;
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<CustomizationDetail> mine(SCMRepository repo, Commit commit, PersistenceMechanism writer) {//for each commit belonging to the product release at hand
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Product","Feature","Churn");
		}
		ArrayList<CustomizationDetail> totalModifications = new ArrayList<CustomizationDetail>();
		for (Modification m : commit.getModifications()) { // for each modification in each commit. Modifications are one per file changed
			System.out.println("INSIDE FOR ");
			if( m.getNewPath().startsWith(Main.pathToWhereCustomizationsAreComputed)){ // only compute modifications that are performed to "path"
				totalModifications.addAll ( computeFeatureCustomizationsInModification("NEWFile",commit,m,writer,this.productRelease));//comupte modification in the new file w.r.t the old file (additions and removals)
				totalModifications.addAll ( computeFeatureCustomizationsInModification("OLDFile",commit,m,writer,this.productRelease));//compute modification in the old file w.r.r the new file (removals)
							
			}
		}
		productRelease.getCustomizations().addAll(totalModifications);
		return totalModifications;
	}

	private ArrayList<CustomizationDetail> computeFeatureCustomizationsInModification(String operation, Commit commit, Modification m, PersistenceMechanism writer, ProductRelease pr) {
		
		int counter=1;
		List<DiffLine> lines = null;
		
		String sourceCodeFile;
		DiffParser parsedDiff = new DiffParser(m.getDiff());
		int numberOfBlock= parsedDiff.getBlocks().size();//how many blocks in the diff
		System.out.println("\n\n\n------------------Commit:"+commit.getHash()+"---------");
		System.out.println("ProductBranch-- File name--- Feature changed ---Operation ---LOCs");
		System.out.println("------------------------------------------------------------------");
		
		ArrayList<CustomizationDetail> list =null;
		while (counter<=numberOfBlock){//analyze those lines that are removed/added in the new file
			if(operation=="NEWFile")	
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInNewFile();//added new lines
			else if (operation == "OLDFile") 
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInOldFile(); //deleted lines
			
			sourceCodeFile= m.getSourceCode();
			
			list = FeatureAnalysisUtils.computeCustomizationDetails( m.getFileName(), m.getNewPath(), sourceCodeFile, lines, pr, commit) ; //get details of customizations
			
			/** JUST FOR PRINTING DETAILS; WE CAN REMOVE THIS **/
			Iterator<CustomizationDetail> it= list.iterator();
			while (it.hasNext()){
				CustomizationDetail aux = it.next();
				System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureModifiedName()+ " "+ aux.getOperation());
				writer.write(commit.getBranches(),aux.getFeatureModifiedName(),aux.getOperation());
				
			}
			counter++;
		}	
		return list;
	}
}

