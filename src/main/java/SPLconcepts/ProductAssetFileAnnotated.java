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
	
	int id;
	String fileName;
	String path;
	String content;
	int totalLines;
	ProductRelease fromPRelease; 

	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<String> featureList= new ArrayList<String>();
	
	public ProductAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines, ProductRelease pr ) {
		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines;
		this.fromPRelease = pr;
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
}
