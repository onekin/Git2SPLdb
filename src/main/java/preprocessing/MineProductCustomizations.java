package preprocessing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffParser;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;
import org.repodriller.domain.DiffLine;

import SPLconcepts.CustomizationEffort;
import SPLconcepts.ProductRelease;

public class MineProductCustomizations implements CommitVisitor {

	private ProductRelease productRelease;

	public MineProductCustomizations(ProductRelease productRelease) {
		this.productRelease = productRelease;
	}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	//commits are from product releases 
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		for (Modification m : commit.getModifications()) {// POR CADA MODIFICACION DE UN COMMIT
			
			//If modofications are performed to files in path  "pathToWhereCustomizationsAreComputed"
			if(  m.getNewPath().startsWith(Main.pathToWhereCustomizationsAreComputed))
			{
				String parentSha;
				String fileName;
		
				parentSha=commit.getParent();
				
				fileName=m.getNewPath() + "/" + m.getFileName();
				System.out.println("changed file: "+fileName);
				
				DiffParser parsedDiff = new DiffParser(m.getDiff());
				int numberOfBlock= parsedDiff.getBlocks().size();//how many blocks in the diff
				
				
				int counter=1;
				List<DiffLine> addedNewLines,deletedInOld;
				String sourceCodeFile;
				
				System.out.println("\n\n\n------------------Commit:"+commit.getHash()+"---------");
				System.out.println("ProductBranch-- File name--- Feature changed ---Operation ---LOCs");
				System.out.println("------------------------------------------------------------------");
				
				
				while (counter<=numberOfBlock){//analyze those lines that appeared in the file
						addedNewLines = parsedDiff.getBlocks().get(counter-1).getLinesInNewFile();
						sourceCodeFile= m.getSourceCode();
						
						
						
						ArrayList<CustomizationEffort> list = FeatureAnalysisUtils.computeFeatureChanged(sourceCodeFile, addedNewLines, m.getFileName(), m.getOldPath(),productRelease) ;
						Iterator<CustomizationEffort> it= list.iterator();
						CustomizationEffort aux ;
						while (it.hasNext()){
							aux = it.next();
							System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureNameModified()+ " "+ aux.getChurn());
							if( (aux.getFeatureNameModified()!="none") && (aux.getFeatureNameModified()!=null)){
								writer.write(commit.getBranches(),aux.getFeatureNameModified(),aux.getChurn());
								//ENTRY FOR FEATURE-FILE, value
								writer.write(aux.getFeatureNameModified(),m.getFileName(),aux.getChurn());
							}
						}
						counter++;
					}	
					
				counter=1;//restart counter
					
					while (counter<=numberOfBlock){//analyze those lines that disapeared in the old file
						deletedInOld = parsedDiff.getBlocks().get(counter-1).getLinesInOldFile();
						sourceCodeFile= m.getSourceCode();
						
						ArrayList<CustomizationEffort> list = FeatureAnalysisUtils.computeFeatureChanged(sourceCodeFile, deletedInOld, m.getFileName(), m.getOldPath(), productRelease);
						Iterator<CustomizationEffort> it= list.iterator();
						CustomizationEffort aux ;
						while (it.hasNext()){
							aux = it.next();
							System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureNameModified()+"  "+ aux.getChurn());
							if( (aux.getFeatureNameModified()!="none") && (aux.getFeatureNameModified()!=null)){
								//WRITE FOR PRODUCT-FEATURE
								writer.write(commit.getBranches(),aux.getFeatureNameModified(),aux.getChurn());
								//ENTRY FOR FEATURE-FILE, value
								writer.write(aux.getFeatureNameModified(), m.getFileName(), aux.getChurn());
							}
						}
						counter++;
					}
				}
		
		}

	}
}

