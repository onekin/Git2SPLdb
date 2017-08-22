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


	public void mine(SCMRepository repo, Commit commit, PersistenceMechanism writer) {//for each commit belonging to the product release at hand
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Product","Feature","Churn");
		}
		
		for (Modification m : commit.getModifications()) { // for each modification in each commit. Modifications are one per file changed
			System.out.println("INSIDE FOR ");
			if( m.getNewPath().startsWith(Main.pathToWhereCustomizationsAreComputed)){ // only compute modifications that are performed to "path"
				computeFeatureCustomizationsInModification("ADDED",commit,m,writer);
				computeFeatureCustomizationsInModification("REMOVED",commit,m,writer);
				//computeCustomizationsForAModification("KEPT",commit,writer);				
			}
		}
		//HERE CREATE A CUSTOMIZATION EFFORT OBJECT! based on the CustomizationDetails
	}

	private void computeFeatureCustomizationsInModification(String operation, Commit commit, Modification m, PersistenceMechanism writer) {
		
		int counter=1;
		List<DiffLine> lines = null;
		
		String sourceCodeFile;
		DiffParser parsedDiff = new DiffParser(m.getDiff());
		int numberOfBlock= parsedDiff.getBlocks().size();//how many blocks in the diff
		System.out.println("\n\n\n------------------Commit:"+commit.getHash()+"---------");
		System.out.println("ProductBranch-- File name--- Feature changed ---Operation ---LOCs");
		System.out.println("------------------------------------------------------------------");
		
		
		while (counter<=numberOfBlock){//analyze those lines that appeared as NEW in the file
			if(operation=="ADDED")	
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInNewFile();//added new lines
				
			else if (operation == "REMOVED") 
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInOldFile(); //deleted lines
			sourceCodeFile= m.getSourceCode();
			
			ArrayList<CustomizationDetail> list ;
			list = FeatureAnalysisUtils.computeFeatureChanged(sourceCodeFile, lines, m.getFileName(), m.getOldPath(), productRelease) ; //get details of customizations
			
			Iterator<CustomizationDetail> it= list.iterator();//JUST FOR PRINTING DETAILS; WE CAN REMOVE THIS
			while (it.hasNext()){
				CustomizationDetail aux = it.next();
				//this.productRelease.getCustomization().add(aux);
				System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureModifiedName()+ " "+ aux.getOperation());
				if( (aux.getFeatureModifiedName()!="none") && (aux.getFeatureModifiedName()!=null)){
					writer.write(commit.getBranches(),aux.getFeatureModifiedName(),aux.getLineOfCodeModified());
				}
			}
			counter++;
		}	
		
	}
}

