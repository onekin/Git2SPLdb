package customDiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.EmptyTreeIterator;
import org.eclipse.jgit.util.io.DisabledOutputStream;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.RawTextComparator;
import java.io.ByteArrayOutputStream;
import org.eclipse.jgit.lib.ObjectReader;

import customDiff.SPLdomain.CustomizationLine;
import customDiff.SPLdomain.ProductRelease;



import repodriller.domain.*;


public class ProductCustomizationMiner {

	ProductRelease pr;
	ArrayList<CustomizationLine> customizations;
	ArrayList<Modification> modifications = new ArrayList<Modification>();
	


	/* Fixed. */	
	private int maxNumberFilesInACommit = 5000; /* TODO Expose an API to control this value? Also in SubversionRepository. */
	private int maxSizeOfDiff = 100000; /* TODO Expose an API to control this value? Also in SubversionRepository. */


	
	public void mine(ProductRelease pr) {
		this.pr = pr;
		customizations = new ArrayList<CustomizationLine>();
		
		//1: compute the diff between productrelease commit and the baseline it was derived from.
		List<DiffEntry> diffs = getDiffsBetweenCommits(pr.getFromProduct().getInPortfolio().getDerivedFrom().getRevCommit(), pr.getReleasedCommit());//base,head
		
		
	
	}
	
	
	
	
	
	private List<DiffEntry> getDiffsBetweenCommits(RevCommit oldCommit, RevCommit newCommit) {
		
		try{
			
			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath+"/.git");	
			DiffFormatter df = new DiffFormatter( new ByteArrayOutputStream() );
			Git git = new Git(repo);
			
			ObjectReader reader = git.getRepository().newObjectReader();
			CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
			ObjectId oldTree =  oldCommit.getTree();
			oldTreeIter.reset( reader, oldTree );
			
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			ObjectId newTree = newCommit.getTree();
			newTreeIter.reset( reader, newTree );
			
			df.setRepository( git.getRepository() );
			
			// 1: get the diff entries for commits.
			List<DiffEntry> diffs = df.scan( oldTreeIter, newTreeIter );

			// 2: for each entry compute customization effort. 
			for (DiffEntry diff : diffs) {
				ModificationType change = Enum.valueOf(ModificationType.class, diff.getChangeType().toString());

				String oldPath = diff.getOldPath();
				String newPath = diff.getNewPath();

				String diffText = "";
				String sc = "";
				if (diff.getChangeType() != ChangeType.DELETE) {
					diffText = getDiffText(repo, diff);
					System.out.println(diffText);
					sc = getSourceCode(repo, diff);
				}

				if (diffText.length() > maxSizeOfDiff) {
					System.out.println("diff for " + newPath + " too big");
					diffText = "-- TOO BIG --";
				}

				this.modifications.add(new Modification(oldPath, newPath, change, diffText, sc));
			
			
			}
		return diffs;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}


	
	private String getSourceCode(Repository repo, DiffEntry diff) throws IOException {
		try {
			ObjectReader reader = repo.newObjectReader();
			byte[] bytes = reader.open(diff.getNewId().toObjectId()).getBytes();
			return new String(bytes, "utf-8");
		} catch (Throwable e) {
			return "";
		}
	}

	
	
	private List<DiffEntry> diffsForTheCommit(Repository repo, RevCommit commit) throws IOException {

		AnyObjectId currentCommit = repo.resolve(commit.getName());
		AnyObjectId parentCommit = commit.getParentCount() > 0 ? repo.resolve(commit.getParent(0).getName()) : null;

		try  {
			DiffFormatter df = new DiffFormatter(DisabledOutputStream.INSTANCE);
            df.setBinaryFileThreshold(2 * 1024); // 2 mb max a file
            df.setRepository(repo);
            df.setDiffComparator(RawTextComparator.DEFAULT);
            df.setDetectRenames(true);
            setContext(df);

            List<DiffEntry> diffs = null;

            if (parentCommit == null) {
               
                	RevWalk rw = new RevWalk(repo);
                    diffs = df.scan(new EmptyTreeIterator(), new CanonicalTreeParser(null, rw.getObjectReader(), commit.getTree()));
               
            } else {
                diffs = df.scan(parentCommit, currentCommit);
            }
            return diffs;
        }catch(Exception e){
        	e.printStackTrace();
        }
		return null;
	}

	private String getDiffText(Repository repo, DiffEntry diff) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
        	DiffFormatter df2 = new DiffFormatter(out);
            String diffText;
            df2.setRepository(repo);
			df2.format(diff);
			diffText = out.toString("UTF-8");
			return diffText;
		} catch (Throwable e) {
			return "";
		}
	}


	private void setContext(DiffFormatter df) {
		try {
			int context = getSystemProperty("git.diffcontext");
			df.setContext(context);
		} catch (Exception e) {
			return;
		}
	}
	
	
	private int getSystemProperty (String name) throws NumberFormatException {
		String val = System.getProperty(name);
		return Integer.parseInt(val);
	}
}
