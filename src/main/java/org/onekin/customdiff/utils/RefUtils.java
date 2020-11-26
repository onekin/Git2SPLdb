package org.onekin.customdiff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class RefUtils {

    private static final Logger logger = LogManager.getLogger(RefUtils.class);

    private RefUtils() {
    }

    public static RevCommit getCommitFromRefName(String tag) {
        RevCommit commit = null;
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             RevWalk walk = new RevWalk(repo)) {
            Ref ref = repo.findRef(tag);

            commit = walk.parseCommit(ref.getObjectId());//find the commit for a
        } catch (IOException e) {
            logger.error(e);
            return null;
        }

        return commit;
    }

    public static RevCommit getCommitFromCommitSha(String sha) {
        RevCommit commit = null;
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             RevWalk walk = new RevWalk(repo)) {
            ObjectId obj = repo.resolve(sha);
            commit = walk.parseCommit(obj);
            return commit;
        } catch (IOException e) {
            logger.error(e);
            return RevCommit.parse(null);
        }
    }

    public static RevCommit getCommitFromRef(Ref ref) {
        RevCommit commit = null;
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             RevWalk walk = new RevWalk(repo)) {
            commit = walk.parseCommit(ref.getObjectId());//find the commit for a
        } catch (IOException e) {
            logger.error(e);
            return RevCommit.parse(null);
        }
        return commit;
    }


    public static ArrayList<Ref> listAllTagsFromRepo() {
        ArrayList<Ref> refNames = new ArrayList<Ref>();
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             Git git = new Git(repo)) {
            List<Ref> references = git.tagList().call();
            for (Ref ref : references) {
                refNames.add(ref);
            }
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return refNames;
    }

    public static ArrayList<Ref> listTagsFromRepoByPrefix(String prefix) {
        ArrayList<Ref> refNames = new ArrayList<Ref>();
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             Git git = new Git(repo)) {
            List<Ref> references = git.tagList().call();
            for (Ref ref : references) {
                if (ref.getName().toLowerCase().startsWith(prefix.toLowerCase())) {
                    refNames.add(ref);
                } else if (ref.getName().toLowerCase().contains(prefix.toLowerCase())){
                    refNames.add(ref);
                }
            }
            repo.close();
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return refNames;
    }

    public static ArrayList<Ref> listTagsFromRepoByContains(String substring) {
        ArrayList<Ref> refNames = new ArrayList<Ref>();
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             Git git = new Git(repo)) {
            List<Ref> references = git.tagList().call();
            for (Ref ref : references) {
                if (ref.getName().toLowerCase().contains(substring.toLowerCase()))
                    refNames.add(ref);
            }
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return refNames;
    }

    public static Map<String, Ref> getAllTagsFromRepo() {
        Map<String, Ref> refMap = null;
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git")) {
            refMap = repo.getTags();
        } catch (IOException e) {
            logger.error(e);
        }
        return refMap;
    }


    public static boolean isRefFirstDerivedFromBaseline(RevCommit baselineCommit, Ref productRelease) {
        //if there is a commit in between baselineCommit&productRelease, which has a baseline tag, or it is in the baseline release branch
        //then the product release has a newer origin baseline
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             RevWalk walk = new RevWalk(repo);
             Git git = new Git(repo);
        ) {
            RevCommit startCommit = walk.parseCommit(repo.resolve(baselineCommit.getName()));
            RevCommit endCommit = walk.parseCommit(repo.resolve(productRelease.getName()));

            Iterable<RevCommit> commitsInBetween = git.log().addRange(startCommit, endCommit).call();//get the commits between both tags
            Iterator<RevCommit> it = commitsInBetween.iterator();
            RevCommit commit;
            while (it.hasNext()) {
                commit = it.next();
                if (isCommitBaselineRelease(commit) || commit.getCommitTime() < baselineCommit.getCommitTime()) {
                    return false;
                }
            }
            return true;
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return false;
    }

    private static boolean isCommitBaselineRelease(RevCommit commit) {
        ArrayList<Ref> baselineTags = listTagsFromRepoByPrefix("refs/tags/" + CustomDiff.coreAssetsReleaseTagPrefix);    //get all baseline tags
        Iterator<Ref> it = baselineTags.iterator();
        Ref ref;
        while (it.hasNext()) {
            ref = it.next();
            if (getCommitFromRef(ref).getName().equals(commit.getName()))
                return true;
        }
        return false;
    }


    public static Iterable<RevCommit> getCommitsBetweenCommits(RevCommit baselineCommit, RevCommit prCommit) {
        try (Repository repo = new FileRepository(CustomDiff.repositoryPath + "/.git");
             RevWalk walk = new RevWalk(repo);
             Git git = new Git(repo)) {
            RevCommit startCommit = walk.parseCommit(repo.resolve(baselineCommit.getName()));
            RevCommit endCommit = walk.parseCommit(repo.resolve(prCommit.getName()));

            Iterable<RevCommit> commitsInBetween = git.log().addRange(startCommit, endCommit).call();//get the commits between both tags

            return commitsInBetween;
        } catch (IOException | GitAPIException e) {
            logger.error(e);
        }
        return null;

    }

    public static String getCommitMessage(String sha) {
        RevCommit com = getCommitFromCommitSha(sha);
        return com.getFullMessage();
    }

    public static PersonIdent getCommitAuthor(String sha) {
        RevCommit com = getCommitFromCommitSha(sha);
        if (com == null) {
            return new PersonIdent("Unknown", "unknown");
        } else {
            return com.getAuthorIdent();
        }
    }
}
