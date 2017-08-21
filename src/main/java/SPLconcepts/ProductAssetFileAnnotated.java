package SPLconcepts;

import java.io.File;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

public class ProductAssetFileAnnotated implements SourceCodeFile{
	
	File file;
	String fileName;
	String path;
	String content;
	RepositoryFile repoFile;

	public ProductAssetFileAnnotated(RepositoryFile repoFile, File file, String fileName, String path,
			String content, String hash) {
		this.file = file;
		this.repoFile = repoFile;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
	}

	public String getContent() {
		
		return content;
	}

	public String getPath() {
	
		return path;
	}

	public String getFileName() {
		
		return fileName;
	}

	public File getFile() {
		// TODO Auto-generated method stub
		return file;
	}
	
	public RepositoryFile getRepoFile() {
		// TODO Auto-generated method stub
		return repoFile;
	}

	public HashMap<Integer, String> readFileToGetFeatureMap(File auxFile) {
		// TODO Auto-generated method stub
		return null;
	}

}
