import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
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
		.in(GitRepository.singleProject("/Users/Onekin/Documents/workspace/WeatherStation/"))
		.through(Commits.all())
		.process(new DevelopersVisitor(), 
				new CSVFile("/Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/weatherStation.csv"))
		.mine();
	}
}