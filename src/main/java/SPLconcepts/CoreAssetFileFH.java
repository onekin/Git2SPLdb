package SPLconcepts;

import java.io.File;

import org.repodriller.scm.RepositoryFile;

public class CoreAssetFileFH implements SourceCodeFile{

	File file;
	String fileName;
	String path;
	String content;
	RepositoryFile repoFile;
	
	public CoreAssetFileFH(File file, String fileName, String path,
			String content, String hash) {
		this.file = file;
		this.repoFile =repoFile;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
	
	}

	public String getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		// TODO Auto-generated method stub
		return null;
	}

	public File getFile() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
