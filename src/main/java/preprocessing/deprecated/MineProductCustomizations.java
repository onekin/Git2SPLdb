package preprocessing.deprecated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffParser;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.SCMRepository;
import org.repodriller.domain.DiffLine;

import customDiff.SPLdomain.CustomizationLine;
import customDiff.SPLdomain.ProductAssetFileAnnotated;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.utils.FeatureAnalysisUtils;

public class MineProductCustomizations{   
	// implements CommitVisitor {

	private ProductRelease productRelease;
	ArrayList<CustomizationLine> details = new ArrayList<CustomizationLine>();
	
	boolean headerFlag=false;

	public MineProductCustomizations(ProductRelease productRelease) {
		this.productRelease = productRelease;
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}


	public ArrayList<CustomizationLine> mine    (SCMRepository repo, Commit commit, PersistenceMechanism writer) {//for each commit belonging to the product release at hand
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Product","Feature","File","Churn");
		}
		ArrayList<CustomizationLine> totalModifications = new ArrayList<CustomizationLine>();
	//	System.out.println("In mine Product customizaitons, for: "+commit.getHash());
		for (Modification m : commit.getModifications()) { // for each modification in each commit. Modifications are one per file changed
			System.out.println("The diff:");
			System.out.println(m.getDiff().toString());
			if( m.getNewPath().contains(Main.pathToWhereCustomizationsAreComputed)){ // only compute modifications that are performed to "path"
				/*ArrayList<Customization> mod1 = computeFeatureCustomizationsInModification("NEWFile",commit,m,writer,this.productRelease);
				ArrayList<Customization> mod2 = computeFeatureCustomizationsInModification("OLDFile",commit,m,writer,this.productRelease) ;
				if (mod1!=null) totalModifications.addAll (mod1);//comupte modification in the new file w.r.t the old file (additions and removals)
				if (mod2!=null) totalModifications.addAll (mod2);//compute modification in the old file w.r.r the new file (removals)
					*/
				System.out.println("Compiting CUSTOMIZATIONS for: "+commit.getHash());
				totalModifications.addAll (computeFeatureCustomizationsInModification("OLDFile",commit,m,writer,this.productRelease));
				totalModifications.addAll (computeFeatureCustomizationsInModification("NEWFile",commit,m,writer,this.productRelease));
			}
		}
		productRelease.getCustomizations().addAll(totalModifications);
		return totalModifications;
	}

	private ArrayList<CustomizationLine> computeFeatureCustomizationsInModification(String operation, Commit commit, Modification m, PersistenceMechanism writer, ProductRelease pr) {
		
		int counter=1;
		List<DiffLine> lines = null;
		System.out.println(" INSIDE computeFeatureCustomizationsInModification");
		String sourceCode;
		System.out.println(" Modification: "+m.getSourceCode());
		DiffParser parsedDiff = new DiffParser(m.getDiff());
		int numberOfBlock= parsedDiff.getBlocks().size();//how many blocks in the diff
		
		System.out.println("\n\n\n------------------Commit:"+commit.getHash()+"---------");
		System.out.println("Product branch-- File name--- Feature changed ---Operation ---LOCs");
		System.out.println("------------------------------------------------------------------");
		
		ArrayList<CustomizationLine> list = new ArrayList<CustomizationLine>();
		while (counter<=numberOfBlock){//analyze those lines that are removed/added in the new file
			if(operation=="NEWFile")	
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInNewFile();//added new lines
			else if (operation == "OLDFile") 
				lines = parsedDiff.getBlocks().get(counter-1).getLinesInOldFile(); //deleted lines
			
			sourceCode= m.getSourceCode();//source 	code in product asset		
			SourceCodeFile paModified = customDiff.utils.FileUtils.getProductAssetByFilePath(m.getNewPath(), pr);

	

			//compute the diff w.r.t baseline
			paModified.setAbsolute_diff(customDiff.utils.Utils.computeTheDiffFileBetweenCommits(pr.getFromProduct().getInPortfolio().getDerivedFrom().getRevCommit().getName(), commit.getHash(), m.getNewPath()));
			
			
			ArrayList<CustomizationLine> listilla = FeatureAnalysisUtils.computeCustomizationDetails(m.getFileName(), m.getNewPath(), sourceCode, lines, pr, commit) ;
			if(listilla!=null)
				list.addAll(listilla); //get details of customizations
			counter++;
		}
		
		/** JUST FOR PRINTING DETAILS; WE CAN REMOVE THIS 
		if (list!=null){ 
			Iterator<Customization> it= list.iterator();
			while (it.hasNext()){
				Customization aux = it.next();
				System.out.println(commit.getBranches()+"  "
				+m.getFileName()+" "+
			//	" "+aux.getCoreAssetFile().getFileName() 
						" "+aux.getOperation());
			//	writer.write(commit.getBranches(),aux.getVp(),aux.getCoreAssetFile().getFileName(), aux.getOperation());
			}
		}**/
		return list;
	}
}
