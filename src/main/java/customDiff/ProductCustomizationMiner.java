package customDiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import java.io.ByteArrayOutputStream;
import org.eclipse.jgit.lib.ObjectReader;
import customDiff.SPLdomain.Customization;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.ProductRelease;
import customDiff.utils.Utils;
import repodriller.diffparser.CustomDiffBlock;
import repodriller.diffparser.DiffParser;
import repodriller.diffparser.Modification;
import repodriller.diffparser.ModificationType;



public class ProductCustomizationMiner {

	ProductRelease pr;
	ArrayList<Customization> customizations;
	

	/* Fixed. */	
	private int maxNumberFilesInACommit = 5000; /* TODO Expose an API to control this value? Also in SubversionRepository. */
	private int maxSizeOfDiff = 100000; /* TODO Expose an API to control this value? Also in SubversionRepository. */

	
	public void mine(ProductRelease pr) {
		System.out.println ("------ Mining Product Customizations for "+pr.getTagName()+"------");
		System.out.println ("--------------------------------------------------------------------");
		this.pr = pr;
		customizations = new ArrayList<Customization>();
		
		//1: compute the Git diff between productrelease commit and the baseline it was derived from.
		List<Modification> modifications = getDiffsBetweenCommits(pr.getFromProduct().getInPortfolio().getDerivedFrom().getRevCommit(), pr.getReleasedCommit());//base,head
		
		//2: for each  diff, call the parser-> parse diffs per VP changed. 
		Iterator<Modification> it = modifications.iterator();
		Modification mod;
		while (it.hasNext()){
			mod = it.next();//4: for each  modifications parse and extract  customDiff
			
			if(! mod.getNewPath().startsWith(customDiff.CustomDiff.pathToWhereCustomizationsAreComputed)) 
				continue;
			else customizations.addAll( computeCustomizationsInModification(mod, pr));

		}
		
		pr.setCustomizations(customizations);
		
		/** Printing **/
		System.out.println("MINED "+pr.getTagName()+ " CUSTOMIZATIONS "+customizations.size());
		Iterator<Customization> it1 = customizations.iterator();
		while(it1.hasNext()){
			System.out.println(" "+it1.next().toString()); 
		}
		
	}
	

	
	
	private ArrayList<Customization> computeCustomizationsInModification(Modification m, ProductRelease pr) {
		
		ArrayList<Customization> customizations = new ArrayList<Customization>();
		
		//the DiffParser is in charge of parsing the diff into customDiffs
		DiffParser parsedDiff = new DiffParser(m, pr);
		
		
		if(parsedDiff.getCustomDiffBlocks()==null || parsedDiff.getCustomDiffBlocks().size()==0) 
			return customizations;
		
		int numberOfCustomDiffBlocks = parsedDiff.getCustomDiffBlocks().size();
		int counter=0;
		
		while (counter<numberOfCustomDiffBlocks){
			CustomDiffBlock custom = parsedDiff.getCustomDiffBlocks().get(counter);
			if(custom!=null) {
				/** Create Customization Facts **/
				Customization customFact = new Customization
						(Utils.getNewCustomizationId(), custom.getAddedlines(), custom.getDeteledlines(), m.getDiff(), 
							false,false, pr,custom);
				//pr.getFileByPath(m.getNewPath()).getIsNew(), pr.getFileByPath(m.getNewPath()).getHasNewFeature()
				customFact.toString();
				customizations.add(customFact);
			}
			counter++;
		}
		return customizations;
	}



	private List<Modification> getDiffsBetweenCommits(RevCommit oldCommit, RevCommit newCommit) {
		
		
		try{
			ArrayList<Modification> modifications = new ArrayList<Modification>();
			
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

			// 2: Convert each of the associated DiffEntry's (of a product release) to a Modification!
			for (DiffEntry diff : diffs) {
				ModificationType change = Enum.valueOf(ModificationType.class, diff.getChangeType().toString());

				String oldPath = diff.getOldPath();
				String newPath = diff.getNewPath();

				String diffText = "";
				String sc = "";
				if (diff.getChangeType() != ChangeType.DELETE) {
					diffText = getDiffText(repo, diff);
					//System.out.println(diffText);
					sc = getSourceCode(repo, diff);
				}

				if (diffText.length() > maxSizeOfDiff) {
					System.out.println("diff for " + newPath + " too big");
					diffText = "-- TOO BIG --";
				}
				System.out.println("Big diff:\n"+diffText);
				modifications.add(new Modification(oldPath, newPath, change, diffText, sc, oldCommit,newCommit));
			
			
			}
			repo.close();
		return modifications;
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
