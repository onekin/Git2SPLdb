package org.onekin.customdiff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.repodriller.domain.ChangeSet;
import org.repodriller.domain.Commit;
import org.repodriller.scm.SCMRepository;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Utils {

    private static int customizationCounter = 0;
    private static int assetCounter = 0;
    private static int productCounter = 0;
    private static int vpCounter = 0;
    private static int id_productrelease = 0;
    private static int id_developer = 0;

    private static final Logger logger = LogManager.getLogger(Utils.class);
    private Utils() {
    }

    static public ArrayList<String> getCommitHashesBetweenTwoTags(String baselineTag, String productReleaseTag) {
        // this snipped works fine
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            Git git = new Git(repo);
            Ref refFrom = repo.getRef(baselineTag);
            Ref refTo = repo.getRef(productReleaseTag);

            ArrayList<String> commitHashList = new ArrayList<String>();

            Iterable<RevCommit> list = git.log().addRange(refFrom.getObjectId(), refTo.getObjectId()).call();
            Iterator<RevCommit> it = list.iterator();
            while (it.hasNext()) {
                RevCommit c = it.next();
                logger.info(c.getName());
                commitHashList.add(c.getName());
            }
            return commitHashList;

        } catch (
                Exception e) {
            logger.error(e);
            return new ArrayList<>();
        }

    }

    static public ChangeSet convertHashToCommit(String hash, SCMRepository scmRepository) {

        List<ChangeSet> all = scmRepository.getScm().getChangeSets();

        for (ChangeSet cs : all) {
            if (hash.equals(cs.getId())) {
                return cs;
            }
        }
        return null;
    }

    static public String getTagForACommitHash(String hash) {

        try (Repository repository = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            Git git = new Git(repository);

            java.util.List<Ref> list = git.tagList().call(); // all the tags in the repository
            for (Ref tag : list) {// for each tag found
                ObjectId object = tag.getObjectId();
                if (object.getName().equals(hash))
                    return tag.getName();
            }
            return "";
        } catch (IOException | GitAPIException e) {
            logger.error(e);
            return "";
        }
    }

    static public String extractRefName(String refname, int extractIndex) {
        // refname e.g.= refs/tags/Baseline-v1.0

        String[] parsed = refname.split("/");
        if (parsed[extractIndex] != null)
            return parsed[extractIndex];
        else
            return refname;

    }

    static public String computeTheDiffFileBetweenCommits(String hash_old, String hash_new, String filePath) {
        try (Repository repository = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            Git git = new Git(repository); // compute the diff w.r.t the baseline it was derived from

            ObjectId oldCommitObj, newCommitObj;
            oldCommitObj = repository.resolve(hash_old);
            newCommitObj = repository.resolve(hash_new);

            RevWalk revWalk = new RevWalk(repository);
            RevCommit oldCommit = revWalk.parseCommit(oldCommitObj);
            RevCommit newCommit = revWalk.parseCommit(newCommitObj);

            try (ObjectReader reader = git.getRepository().newObjectReader()) {
                CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();

                oldTreeIter.reset(reader, oldCommit.getTree());
                CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
                newTreeIter.reset(reader, newCommit.getTree());

                DiffFormatter diffFormatter = new DiffFormatter(System.out);
                diffFormatter.setRepository(git.getRepository());

                List<org.eclipse.jgit.diff.DiffEntry> entries = diffFormatter.scan(newTreeIter, oldTreeIter);
                Iterator<org.eclipse.jgit.diff.DiffEntry> it = entries.iterator();
                while (it.hasNext()) {
                    org.eclipse.jgit.diff.DiffEntry entry = it.next();
                    logger.info("entry.getNewPath()=" + entry.getNewPath());
                    logger.info("filePath:" + filePath);

                    if (entry.getNewPath().equals(filePath)) {
                        logger.info("ABSOLUTE DIFF");
                        logger.info(entry.toString());
                        diffFormatter.format(entry);
                        logger.info("Diff formater DIFF");
                        logger.info(diffFormatter.toString());
                        return entry.toString();
                    }

                }
            } catch (IOException e) {
                logger.error(e);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        return " ";
    }

    public static Iterable<RevCommit> getCommitsInBranch(String branchName) {

        try (Repository repository = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            Git git = new Git(repository);
            Iterable<RevCommit> revCommits = git.log().add(repository.resolve(branchName)).call();
            repository.close();
            return revCommits;
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return new ArrayList<>();
    }

    public static boolean isCommitInBranch(Commit newerCommit, String branchName) {
        Iterable<RevCommit> commitsForBranch = Utils.getCommitsInBranch(branchName);
        Iterator<RevCommit> it = commitsForBranch.iterator();

        while (it.hasNext()) {
            RevCommit co = it.next();
            if (co.getName().compareTo(newerCommit.getHash()) == 0)
                return true;
        }

        return false;
    }

    static public int getNewCustomizationId() {
        customizationCounter++;
        return customizationCounter;
    }

    static public int getNewProductId() {
        productCounter++;
        return productCounter;
    }

    public static int getNewAssetId() {
        assetCounter++;
        return assetCounter;
    }

    public static void resetAssetId() {

        assetCounter = 0;
    }

    public static int getVPId() {
        vpCounter++;
        return vpCounter;
    }

    public static String extractRefNameForProduct(String name) {
        // ref name =
        String aux = extractRefName(name, 2);
        return aux.split("-v")[0];

    }

    public static int getDeveloperId() {
        id_developer++;
        return id_developer;
    }

    public static int getProductReleaseId() {
        id_productrelease++;
        return id_productrelease;
    }

    public static <T, E> T getKeyFromValue(Map<T, E> map, E value) {
        for (Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void resetVpId() {
        vpCounter = 0;
    }

}
