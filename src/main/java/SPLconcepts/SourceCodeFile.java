package SPLconcepts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

public interface SourceCodeFile {
	
	
	//File file = null;
	RepositoryFile repoFile = null;
	String fileName=null;
	String path = null;
	String content = null;
	String type = null; //coreAsset or productAsset
	
	
	public String getContent();
	public String getPath();
	public String getFileName();
	public File getFile();
	public HashMap<Integer, String> readFileToGetFeatureMap(File auxFile);
}
