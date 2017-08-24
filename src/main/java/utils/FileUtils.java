package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import org.repodriller.domain.Commit;

import preprocessing.Main;

import SPLconcepts.CoreAssetBaseline;
import SPLconcepts.ProductAssetFileAnnotated;
import SPLconcepts.ProductRelease;
import SPLconcepts.SourceCodeFile;

public class FileUtils {
	
	static int coreAssetFileCounter = 0;
	static int productAssetFileCounter = 0;
	private static PrintWriter out;
	
	
	
	public static  void writeToFile(String path, String text){
		
		try {
			out = new PrintWriter(path);
			out.println(text);
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String readAFileFromRepositoryCommit(Commit co, String fileName, String fullPath){
		try{
			BufferedReader br = new BufferedReader(new FileReader(fullPath));
				StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);
			        sb.append(System.lineSeparator());
			        line = br.readLine();
			    }
			  return   sb.toString();
		}
		  catch(Exception e){
			  e.printStackTrace();
		  }
		return null;  
	}

	public static SourceCodeFile getProductAssetByFileName(String fileName, ProductRelease pr) {
		ArrayList<ProductAssetFileAnnotated> listAssets = pr.getProductAssets();
		if (listAssets!= null){
			Iterator<ProductAssetFileAnnotated> it = listAssets.iterator();
			while(it.hasNext()){
				ProductAssetFileAnnotated pa = it.next();
				if(fileName.equals(pa.getFileName()))
					return pa;
			}
		
		}

		return null;
	}

	public static SourceCodeFile getCoreAssetByProductAssetName(String fileName, ProductRelease pr ) {
		CoreAssetBaseline baseline = pr.getFromProduct().getInPortfolio().getDerivedFrom() ;
		ArrayList<SourceCodeFile> caList = baseline.getCoreAssetFiles();
		
		if (caList!= null){
			Iterator<SourceCodeFile> it = caList.iterator();
			while(it.hasNext()){
				SourceCodeFile ca = it.next();
				if(fileName.equals(ca.getFileName()))
					return ca;
			}
		
		}

		return null;
	}

	public static int getCoreAssetFileCounter(){
		return coreAssetFileCounter++;
	}
	
	public static int getProductAssetFileCounter(){
		return productAssetFileCounter++;
	}

}
