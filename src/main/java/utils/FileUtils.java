package utils;

import java.io.BufferedReader;
import java.io.FileReader;

import org.repodriller.domain.Commit;

public class FileUtils {
	
	
	
	
	public static void readAFileFromRepositoryCommit(Commit co, String fileName, String fullPath){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
				StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			    String everything = sb.toString();
		}
		  catch(Exception e){
			  e.printStackTrace();
		  }  
	}
	

}
