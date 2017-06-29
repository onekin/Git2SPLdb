import java.util.ArrayList;
import java.util.Set;

import org.repodriller.domain.Commit;
import org.repodriller.filter.commit.CommitFilter;


public class FilerCommitsForProducts implements CommitFilter{


	public boolean accept(Commit commit) {
		// Accept only commits that are unique to product branches
		ArrayList<String> branches = (ArrayList<String>) commit.getBranches();
		for (int i=0;i<branches.size();i++){
			if (branches.get(i).contains("product"))
			  return true;
		}
		return false;
	}

}
