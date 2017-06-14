import org.repodriller.domain.Commit;
import org.repodriller.persistence.PersistenceMechanism;
import org.repodriller.scm.CommitVisitor;
import org.repodriller.scm.SCMRepository;

public class DevelopersVisitor implements CommitVisitor {

	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		
		writer.write(
			commit.getHash(),
			commit.getCommitter().getName(),
			commit.getMsg(),
			commit.getModifications()
		);

	}

	public String name() {
		return "developers";
	}

}