import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.repodriller.domain.Commit;
import org.repodriller.domain.DiffLine;
import org.repodriller.domain.DiffParser;
import org.repodriller.domain.Modification;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class ProductModificationsVisitor implements CommitVisitor {

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		for(Modification m : commit.getModifications()) {// POR CADA MODIFICACION DE UN COMMIT

			String parentSha;
			if(! commit.getBranches().contains("master")){//analyses all branches that are not master
				parentSha=commit.getParent();
				
				DiffParser parsedDiff = new DiffParser(m.getDiff());
				int numberOfBlock= parsedDiff.getBlocks().size();//who many block in the diff
				int counter=1;
				List<DiffLine> addedNewLines,deletedInOld;
				String sourceCodeFile;
				
				System.out.println("\n\n\n------------------Commit:"+commit.getHash()+"---------");
				System.out.println("ProductBranch-- File name--- Feature changed ---Operation ---LOCs");
				System.out.println("------------------------------------------------------------------");
				
				while (counter<=numberOfBlock){//analyze those lines that appeared in the file
						addedNewLines = parsedDiff.getBlocks().get(counter-1).getLinesInNewFile();
						sourceCodeFile= m.getSourceCode();
						
						AnalyzeFeatureDetail analyzeFeatureDetail = new AnalyzeFeatureDetail(sourceCodeFile,addedNewLines);
						ArrayList<FeatureModificationDetail> list = analyzeFeatureDetail.computeFeatureChanged(sourceCodeFile, addedNewLines);
						Iterator<FeatureModificationDetail> it= list.iterator();
						FeatureModificationDetail aux ;
						while (it.hasNext()){
							aux = it.next();
							System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureModifiedName()+" " +aux.getOperation()+ " "+ aux.getNumLinesOfCode());
							writer.write(
									commit.getBranches()+";",
									m.getFileName()+";",
									
									aux.getFeatureModifiedName()+";",
									aux.getOperation()+";",
									aux.getNumLinesOfCode()
									//m.getType()
									//m.getAdded(),
									//m.getRemoved()
									);
						}
						counter++;
					}	
				
					counter=1;
					while (counter<=numberOfBlock){//analyze those lines that disapeared in the old file
						
						deletedInOld = parsedDiff.getBlocks().get(counter-1).getLinesInOldFile();
						
						sourceCodeFile= m.getSourceCode();
						
						AnalyzeFeatureDetail analyzeFeatureDetail = new AnalyzeFeatureDetail(sourceCodeFile,deletedInOld);
						ArrayList<FeatureModificationDetail> list = analyzeFeatureDetail.computeFeatureChanged(sourceCodeFile, deletedInOld);
						Iterator<FeatureModificationDetail> it= list.iterator();
						FeatureModificationDetail aux ;
						while (it.hasNext()){
							aux = it.next();
							System.out.println(commit.getBranches()+"  "+m.getFileName()+" "+aux.getFeatureModifiedName()+" " +aux.getOperation()+ " "+ aux.getNumLinesOfCode());
							writer.write(
									commit.getBranches()+";",
									m.getNewPath()+";",
									m.getFileName()+";",
									
									aux.getFeatureModifiedName()+";",
									aux.getOperation()+";",
									aux.getNumLinesOfCode()
									);
						}
						counter++;
					}
				}
		}
}

	public String name() {
		// TODO Auto-generated method stub
		return null;
	}
}

