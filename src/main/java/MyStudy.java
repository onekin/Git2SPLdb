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

	
	public static File csvFile= new File(Settings.csvFilePath); //csvFilePath
	public static  PrintWriter writer1;
	
	public static void main(String[] args) {
		new RepoDriller().start(new MyStudy());
		
		// open File and insert a line in the header 
		 

		 RandomAccessFile f;
		try {
			f = new RandomAccessFile(new File(Settings.csvFilePath), "rw");
			 f.seek(0); // to the beginning
			 f.write("source,target,value".getBytes());
			 f.close();
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void execute() {
		// do the magic here! ;)

		
			
			new RepositoryMining()
			.in(GitRepository.singleProject("/Users/Onekin/Desktop/sensors"))
			//.in(GitRepository.singleProject("/Users/Onekin/Documents/workspace/WeatherStation"))
			.through(Commits.all())
			.filters()
			.process(new ProductModificationsVisitor(), new CSVFile("/Users/Onekin/Documents/workspace/SPLCustomsWithRepoDriller/src/main/resources/alluvial/sankey.csv"))
			.mine();

			
		
		 
		

	}
}