package preprocessing;


import java.util.Date;
import java.util.List;

import org.repodriller.domain.Commit;

import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.RepositoryFile;
import org.repodriller.scm.SCMRepository;

import deprecated.Customs;

import utils.Utils;

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.CoreAssetFileAnnotated;
import SPLconcepts.SourceCodeFile;

public class MineBaselines implements CommitVisitor {

	boolean headerFlag=false;

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
	//for each commit in master (i.e. a baseline version; get all core asset files there)
		
		if(headerFlag==false){
			headerFlag=true;
			writer.write("Baseline-ID","Release date","CoreAssetName");
		}
				
		if(commit.getBranches().contains(Customs.coreAssetsBranchPatternName)) {		
			try{
				
				repo.getScm().checkout(commit.getHash());
				String baselineTag = Utils.getTagForACommitHash(commit.getHash());
				CoreAssetBaseline CABaseline = new CoreAssetBaseline(commit, new Date (commit.getDate().getTimeInMillis()), baselineTag);
				Main.spl.addBaseline(CABaseline);
				
					List<RepositoryFile> files = repo.getScm().files();
					
					SourceCodeFile CAfile;
					for(RepositoryFile file : files) {//Mining Files for baseline
						if(!file.getFile().getAbsolutePath().contains(Main.pathToWhereCustomizationsAreComputed)) continue;
						CAfile= new CoreAssetFileAnnotated(file.getFullName(), file.getFile().getName(),  file.getFile().getPath(), file.getSourceCode(), file.getSourceCode().split("\n").length);
						CABaseline.addCoreAssetFile(CAfile);
				
					
					writer.write(
							CABaseline.getTag(),	
							CABaseline.getReleaseDate(),
							file.getFullName(),
							file.getSourceCode().split("\n").length);
					
				}
			} finally {
					repo.getScm().reset();
			}	
		}
	}
	
	public boolean isCommitTagged(SCMRepository repo, Commit c){
	//	repo.
		c.getHash();
		
		return false;
		
	}
	
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
