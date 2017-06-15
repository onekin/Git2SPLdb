import java.util.Arrays;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.commit.OnlyInBranches;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;

import org.repodriller.scm.GitRepository;

public class MyStudy implements Study {

	public static void main(String[] args) {
		new RepoDriller().start(new MyStudy());
	}

	public void execute() {
		// do the magic here! ;)
		new RepositoryMining()
		.in(GitRepository.singleProject("/Users/Onekin/Desktop/sensors"))
		//.in(GitRepository.singleProject("/Users/Onekin/Documents/workspace/WeatherStation"))
		.through(Commits.all())
		.filters()
			//	new OnlyInBranches(Arrays.asList("master")))
		.process(new ProductModificationsVisitor(), 
				new CSVFile("/Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/sensors-baseline.csv"))
		.mine();
		
	}
}