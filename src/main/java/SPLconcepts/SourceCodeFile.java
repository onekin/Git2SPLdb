package SPLconcepts;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

public interface SourceCodeFile {

	
	String fileName=null;
	String path = null;
	String content = null;
	
	public String getContent();
	public String getPath();
	public String getFileName();
	
}
