import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.repodriller.RepoDriller;
import org.repodriller.RepositoryMining;
import org.repodriller.Study;
import org.repodriller.filter.commit.OnlyInBranches;
import org.repodriller.filter.range.Commits;
import org.repodriller.persistence.csv.CSVFile;

import org.repodriller.scm.GitRepository;

public class MyStudy implements Study {

	
    public final String path="/Users/leticia/Documents/workspace/" ;

	
	public static void main(String[] args) {
		new RepoDriller().start(new MyStudy());
	}

	public void execute() {
			new RepositoryMining()
			
			.in(GitRepository.singleProject(path+"WeatherStationSPL"))
			.through(Commits.all())
			.filters()
			.process(new ProductModificationsVisitor(), new CSVFile(path+"SPLCustomsWithRepoDriller/src/main/resources/alluvial/sankey.csv"))
			.mine();

			
		
		 
		

	}
}