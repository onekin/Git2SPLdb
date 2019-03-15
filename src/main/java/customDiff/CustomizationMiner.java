package customDiff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffEntry.ChangeType;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.Customization;
import customDiff.SPLdomain.CustomizationType;
import customDiff.SPLdomain.Feature;
import customDiff.SPLdomain.FeatureSibling;
import customDiff.utils.Utils;
import repodriller.diffparser.CustomDiffBlock;
import repodriller.diffparser.DiffParser;
import repodriller.diffparser.Modification;
import repodriller.diffparser.ModificationType;

public class CustomizationMiner {

	CoreAssetBaseline baseline2;
	ArrayList<Customization> customizations;

	/* Fixed. */
	private int maxNumberFilesInACommit = 5000; /*
												 * TODO Expose an API to control this value? Also in
												 * SubversionRepository.
												 */
	private int maxSizeOfDiff = 100000; /* TODO Expose an API to control this value? Also in SubversionRepository. */

	public void mine(CoreAssetBaseline baseline, CoreAssetBaseline baseline2) {
		System.out.println("------ Mining Product Customizations for " + baseline2.getId() + "------");
		System.out.println("--------------------------------------------------------------------");
		this.baseline2 = baseline2;
		customizations = new ArrayList<Customization>();
		RevCommit baselineTag = baseline.getRevCommit();

		// 1: compute the Git diff between productrelease commit and the baseline it was
		// derived from.
		List<Modification> modifications = getDiffsBetweenCommits(baselineTag, baseline2.getRevCommit());

		// 2: for each diff, call the parser-> parse diffs per VP changed.
		Iterator<Modification> it = modifications.iterator();
		Modification mod;
		while (it.hasNext()) {
			mod = it.next();// 4: for each modifications parse and extract the customDiff

			if (!mod.getNewPath().startsWith(customDiff.CustomDiff.pathToWhereCustomizationsAreComputed))
				continue;
			else
				customizations.addAll(computeCustomizationsInModification(mod, baseline2));
		}
		customizations.addAll(computeScatteringAndTanglingModification(baseline, baseline2));

		baseline2.setCustomizations(customizations);

		/** Printing **/
		/*
		 * System.out.println("MINED " + baseline2.getId() + " CUSTOMIZATIONS " +
		 * customizations.size()); Iterator<Customization> it1 =
		 * customizations.iterator(); while (it1.hasNext()) { System.out.println(" " +
		 * it1.next().toString()); }
		 */

	}

	private List<Customization> computeScatteringAndTanglingModification(CoreAssetBaseline baseline,
			CoreAssetBaseline baseline22) {
		List<Customization> scatteringCust = new ArrayList<Customization>();
		Optional<Feature> op;
		int scatteringNumber, newSize, oldSize;

		for (Feature feature : baseline2.getFeatures()) {
			op = baseline.getFeatures().stream().filter(f2 -> f2.getIdFeature().equals(feature.getIdFeature()))
					.findFirst();
			if (op.isPresent()) {
				newSize = feature.getFeatureSiblings().size();
				oldSize = op.get().getFeatureSiblings().size();
				scatteringNumber = newSize >= oldSize ? newSize - oldSize : 0;
				if (!feature.getFeatureSiblings().equals(op.get().getFeatureSiblings())) {
					scatteringCust.addAll(computeTanglingCusts(feature.getIdFeature(), feature.getFeatureSiblings(),
							op.get().getFeatureSiblings()));
				}
			} else {
				scatteringNumber = feature.getFeatureSiblings().size();
				scatteringCust.addAll(computeTanglingCustsForNewFeature(feature));
			}
			if (scatteringNumber > 0) {
				scatteringCust.add(new Customization(Utils.getNewCustomizationId(),
						CustomizationType.FEATURE_SCATTERING_MODIFICATION, scatteringNumber, feature.getIdFeature()));
			}
		}
		return scatteringCust;
	}

	private List<Customization> computeTanglingCusts(String featureId, Set<FeatureSibling> newFeatureSiblings, Set<FeatureSibling> oldFeatureSiblings) {
		List<Customization> tanglingCust = new ArrayList<>();
		List<String> allNewFeatures =  newFeatureSiblings.stream().map(FeatureSibling::getFeatures).flatMap(Set::stream).collect(Collectors.toList());
		List<String> allOldFeatures =  oldFeatureSiblings.stream().map(FeatureSibling::getFeatures).flatMap(Set::stream).collect(Collectors.toList());
		allNewFeatures.removeAll(allOldFeatures);
		for(String featureId2: allNewFeatures) {
			tanglingCust.add(new Customization(Utils.getNewCustomizationId(),
					CustomizationType.FEATURE_TANGLING_MODIFICATION, featureId + " " + featureId2));
		}
		return tanglingCust;
	}

	private List<Customization> computeTanglingCustsForNewFeature(Feature feature) {
		List<Customization> tanglingCust = new ArrayList<>();
		for (FeatureSibling fSibling : feature.getFeatureSiblings()) {
			for (String featureId : fSibling.getFeatures()) {
				tanglingCust.add(new Customization(Utils.getNewCustomizationId(),
						CustomizationType.FEATURE_TANGLING_MODIFICATION, feature.getIdFeature() + " " + featureId));
			}
		}
		return tanglingCust;
	}

	private ArrayList<Customization> computeCustomizationsInModification(Modification m, CoreAssetBaseline baseline22) {

		ArrayList<Customization> customizations = new ArrayList<Customization>();

		// the DiffParser is in charge of parsing the diff into customDiffs
		DiffParser parsedDiff = new DiffParser(m, baseline22);

		if (parsedDiff.getCustomDiffBlocks() == null || parsedDiff.getCustomDiffBlocks().size() == 0)
			return customizations;

		int numberOfCustomDiffBlocks = parsedDiff.getCustomDiffBlocks().size();
		int counter = 0;

		while (counter < numberOfCustomDiffBlocks) {
			CustomDiffBlock custom = parsedDiff.getCustomDiffBlocks().get(counter);
			if (custom != null) {
				/** Create Customization Facts **/
				Customization customFact = new Customization(Utils.getNewCustomizationId(), custom.getAddedlines(),
						custom.getDeteledlines(), m.getDiff(), custom.getIsNewAsset(), false, baseline22, custom);
				// pr.getFileByPath(m.getNewPath()).getIsNew(),
				// pr.getFileByPath(m.getNewPath()).getHasNewFeature()
				customFact.toString();
				customizations.add(customFact);
			}
			counter++;
		}
		return customizations;
	}

	private List<Modification> getDiffsBetweenCommits(RevCommit oldCommit, RevCommit newCommit) {

		try {
			ArrayList<Modification> modifications = new ArrayList<Modification>();

			Repository repo = new FileRepository(customDiff.CustomDiff.repositoryPath + "/.git");
			DiffFormatter df = new DiffFormatter(new ByteArrayOutputStream());
			Git git = new Git(repo);

			ObjectReader reader = git.getRepository().newObjectReader();
			CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
			ObjectId oldTree = oldCommit.getTree();
			oldTreeIter.reset(reader, oldTree);

			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			ObjectId newTree = newCommit.getTree();
			newTreeIter.reset(reader, newTree);

			df.setRepository(git.getRepository());

			// 1: get the diff entries for commits.
			List<DiffEntry> diffs = df.scan(oldTreeIter, newTreeIter);

			// 2: Convert each of the associated DiffEntry's (of a product release) to a
			// Modification!
			for (DiffEntry diff : diffs) {
				ModificationType change = Enum.valueOf(ModificationType.class, diff.getChangeType().toString());

				String oldPath = diff.getOldPath();
				String newPath = diff.getNewPath();

				String diffText = "";
				String sc = "";
				if (diff.getChangeType() != ChangeType.DELETE) {
					diffText = getDiffText(repo, diff);
					// System.out.println(diffText);
					sc = getSourceCode(repo, diff);
				}

				if (diffText.length() > maxSizeOfDiff) {
					System.out.println("diff for " + newPath + " too big");
					diffText = "-- TOO BIG --";
				}
				// System.out.println("Big diff:\n"+diffText);
				modifications.add(new Modification(oldPath, newPath, change, diffText, sc, oldCommit, newCommit));

			}
			repo.close();
			return modifications;
		} catch (Exception e) {
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

	private int getSystemProperty(String name) throws NumberFormatException {
		String val = System.getProperty(name);
		return Integer.parseInt(val);
	}
}
