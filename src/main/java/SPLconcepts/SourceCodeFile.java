package SPLconcepts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

public interface SourceCodeFile {

	int id=0; 
	String fileName=null;
	String path = null;
	String content = null;
	int totalLines = 0;
	
	public String getContent();
	public String getPath();
	public String getFileName();
	public int getId();
	public int getTotalLines();
	public ArrayList<String> getFeatureList();
	
}
