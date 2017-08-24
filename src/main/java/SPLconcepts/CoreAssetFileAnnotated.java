package SPLconcepts;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.repodriller.scm.RepositoryFile;



public class CoreAssetFileAnnotated implements SourceCodeFile{
	int id; //this is the full name of the file (e.g. /Users/user/Documents/repo/src/file1.txt)
	String fileName;
	String path;
	String content;
	int totalLines;
	CoreAssetBaseline fromBaseline;


	HashMap <Integer,String> featureToCodeMapping = new HashMap<Integer, String>();
	ArrayList<Feature> featureList= new ArrayList<Feature>();
	
	public CoreAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines, CoreAssetBaseline CABaseline) {

		this.id= id;
		this.fileName=fileName;
		this.path = path;
		this.content = content;
		this.totalLines = totalLines; 
		this.fromBaseline = CABaseline ;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalLines() {
		return totalLines;
	}

	public void setTotalLines(int totalLines) {
		this.totalLines = totalLines;
	}

	public CoreAssetBaseline getFromBaseline() {
		return fromBaseline;
	}

	public void setFromBaseline(CoreAssetBaseline fromBaseline) {
		this.fromBaseline = fromBaseline;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HashMap<Integer, String> getFeatureToCodeMapping() {
		return featureToCodeMapping;
	}

	public void setFeatureToCodeMapping(
			HashMap<Integer, String> featureToCodeMapping) {
		this.featureToCodeMapping = featureToCodeMapping;
	}

	public ArrayList<Feature> getFeatureList() {
		return featureList;
	}


}
