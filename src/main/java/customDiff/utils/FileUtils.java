package customDiff.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import customDiff.SPLdomain.CoreAssetBaseline;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;




public class FileUtils {
	

	
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
	
	public static String readFileContentFromRepository(String fullPath){
		try{
			//  System.out.println("File Utils. File:"+ fullPath);
				BufferedReader br = new BufferedReader(new FileReader(fullPath));
				StringBuilder sb = new StringBuilder();
			    String line = br.readLine();

			    while (line != null) {
			        sb.append(line);//+"\n");
			        sb.append("\n");
			        line = br.readLine();
			    }
		//	  System.out.println("In File Utils - readFileContent: \n"+ sb.toString());
			  br.close();
			  
			  return   sb.toString();
		}
		  catch(Exception e){
			  e.printStackTrace();
		  }
		return null;  
	}

	public static SourceCodeFile getProductAssetByFileName(String fileName, ProductRelease pr) {
		ArrayList<SourceCodeFile> listAssets = pr.getProductAssets();
		if (listAssets!= null){
			Iterator<SourceCodeFile> it = listAssets.iterator();
			while(it.hasNext()){
				SourceCodeFile pa = it.next();
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

	public static void writeToFile(String path, ArrayList<String> allInserts) {
		Iterator<String> it = allInserts.iterator();
		String st;
			try {
				 out = new PrintWriter(path);
				while (it.hasNext()){
				  st=it.next();
				  out.append(st);
				}
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	}

	public static SourceCodeFile getProductAssetByFilePath(String path, ProductRelease pr) {
		System.out.println("Finding path:"+path);
		ArrayList<SourceCodeFile> listAssets = pr.getProductAssets();
		if (listAssets!= null){
			Iterator<SourceCodeFile> it = listAssets.iterator();
			while(it.hasNext()){
				SourceCodeFile pa = it.next();
		//		System.out.println(pa.getRelativePath());
				if(path.equals(pa.getRelativePath()))
					return pa;
			}
		}
		return null;
	}
	
	public static SourceCodeFile getCoreAssetByProductAssetPath(String path, ProductRelease pr ) {
		CoreAssetBaseline baseline = pr.getFromProduct().getInPortfolio().getDerivedFrom();
		ArrayList<SourceCodeFile> caList = baseline.getCoreAssetFiles();
		//System.out.println("Finding path:"+path);
		if (caList!= null){
			Iterator<SourceCodeFile> it = caList.iterator();
			while(it.hasNext()){
				SourceCodeFile ca = it.next();
				//System.out.println(ca.getRelativePath());
				if(path.equals(ca.getRelativePath()))
					return ca;
			}
		
		}
		System.out.println("NOT FOUND path:"+path);
		return null;
	}



	public static SourceCodeFile getCoreAssetByFilePath(String relPath) {
		
		ArrayList<SourceCodeFile> caList = customDiff.CustomDiff.spl.getCoreAssetBaseline(0).getCoreAssetFiles();
		if (caList!= null){
			Iterator<SourceCodeFile> it = caList.iterator();
			while(it.hasNext()){
				SourceCodeFile ca = it.next();
				//System.out.println(ca.getRelativePath());
				if(relPath.equals(ca.getRelativePath()))
					return ca;
			}
		
		}
		System.out.println("NOT FOUND path:"+relPath);
		return null;
		
	}

	

}
