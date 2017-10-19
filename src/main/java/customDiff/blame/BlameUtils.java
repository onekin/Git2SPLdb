package customDiff.blame;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

public class BlameUtils {
/*Taken from Repo Driller*/
	
	public static List<BlamedLine> blame(String file, String commitToBeBlamed, boolean priorCommit) {
        try{
        	Git git = openRepository();
			ObjectId gitCommitToBeBlamed;
			if(priorCommit) {
				Iterable<RevCommit> commits = git.log().add(git.getRepository().resolve(commitToBeBlamed)).call();
				gitCommitToBeBlamed = commits.iterator().next().getParent(0).getId();
			} else {
				gitCommitToBeBlamed = git.getRepository().resolve(commitToBeBlamed);
			}

			BlameResult blameResult = git.blame().setFilePath(file).setStartCommit(gitCommitToBeBlamed).setFollowFileRenames(true).call();
			if(blameResult != null) {
				int rows = blameResult.getResultContents().size();
				List<BlamedLine> result = new ArrayList<>();
				for(int i = 0; i < rows; i++) {
					result.add(new BlamedLine(i,
							blameResult.getResultContents().getString(i),
							blameResult.getSourceAuthor(i).getName(),
							blameResult.getSourceCommitter(i).getName(),
							blameResult.getSourceCommit(i).getId().getName()));
				}

				return result;
			} else {
				throw new RuntimeException("BlameResult not found.");
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	private static Git openRepository() {
		try{
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");
			Git git = new Git(repo);
			return git;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}