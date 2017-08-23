package SPLconcepts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.repodriller.scm.RepositoryFile;

import deprecated.Customs;


public class ProductAssetFileAnnotated implements SourceCodeFile{
	
	File file;
	String fileName;
	String path;
	String content;
	RepositoryFile repoFile;

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	
	public ProductAssetFileAnnotated(String fileName, String path, String content ) {

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

	

}
