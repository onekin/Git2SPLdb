package org.onekin.customdiff.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.spldomain.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FileUtils {


    private static PrintWriter out;

    private static final Logger logger = LogManager.getLogger(FileUtils.class);

    private FileUtils(){}


    public static void writeToFile(String path, String text) {

        try {
            out = new PrintWriter(path);
            out.println(text);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String readFileContentFromRepository(String fullPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            br.close();

            return sb.toString();
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    public static SourceCodeFile getProductAssetByFileName(String fileName, ProductRelease pr) {
        ArrayList<SourceCodeFile> listAssets = pr.getProductAssets();
        if (listAssets != null) {
            Iterator<SourceCodeFile> it = listAssets.iterator();
            while (it.hasNext()) {
                SourceCodeFile pa = it.next();
                if (fileName.equals(pa.getFileName()))
                    return pa;
            }

        }

        return null;
    }

    public static SourceCodeFile getCoreAssetByProductAssetName(String fileName, ProductRelease pr) {
        CoreAssetBaseline baseline = pr.getFromProduct().getInPortfolio().getDerivedFrom();
        List<SourceCodeFile> caList = baseline.getCoreAssetFiles();

        if (caList != null) {
            Iterator<SourceCodeFile> it = caList.iterator();
            while (it.hasNext()) {
                SourceCodeFile ca = it.next();
                if (fileName.equals(ca.getFileName()))
                    return ca;
            }

        }

        return null;
    }

    public static void writeToFile(String path, ArrayList<String> allInserts) {
		logger.info(" Writing to " + path);
        Iterator<String> it = allInserts.iterator();
        String st;
        try {
            out = new PrintWriter(path);
            while (it.hasNext()) {
                st = it.next();
                out.append(st);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static SourceCodeFile getProductAssetByFilePath(String path, CoreAssetBaseline baseline2) {
        List<SourceCodeFile> listAssets = baseline2.getCoreAssetFiles();
        if (listAssets != null) {
            Iterator<SourceCodeFile> it = listAssets.iterator();
            while (it.hasNext()) {
                SourceCodeFile pa = it.next();
                if (path.equals(pa.getRelativePath()) || path.equals(pa.getPath()))
                    return pa;
            }
        }
        return null;
    }

    public static SourceCodeFile getProductAssetByFilePath(String path, ProductPortfolio baseline2) {
        List<SourceCodeFile> listAssets = baseline2.getDerivedFrom().getCoreAssetFiles();
        if (listAssets != null) {
            Iterator<SourceCodeFile> it = listAssets.iterator();
            while (it.hasNext()) {
                SourceCodeFile pa = it.next();
                if (path.equals(pa.getRelativePath()) || path.equals(pa.getPath()))
                    return pa;
            }
        }
        return null;
    }

    public static SourceCodeFile getCoreAssetByProductAssetPath(String path, CoreAssetBaseline baseline) {
        List<SourceCodeFile> caList = baseline.getCoreAssetFiles();
        if (caList != null) {
            Iterator<SourceCodeFile> it = caList.iterator();
            while (it.hasNext()) {
                SourceCodeFile ca = it.next();
                if (path.equals(ca.getRelativePath()) || path.equals(ca.getPath()))
                    return ca;
            }

        }
        logger.error("NOT FOUND path:" + path);
        return null;
    }


    public static SourceCodeFile getCoreAssetByFilePath(String relPath, SPL spl) {

        List<SourceCodeFile> caList = spl.getCoreAssetBaseline(0).getCoreAssetFiles();
        if (caList != null) {
            Iterator<SourceCodeFile> it = caList.iterator();
            while (it.hasNext()) {
                SourceCodeFile ca = it.next();
                if (relPath.equals(ca.getRelativePath()))
                    return ca;
            }

        }
		logger.error("NOT FOUND path:" + relPath);
        return null;

    }


    public static SourceCodeFile getProductAssetByFilePath(String path, ProductRelease pr) {
        List<SourceCodeFile> listAssets = pr.getProductAssets();
        if (listAssets != null) {
            Iterator<SourceCodeFile> it = listAssets.iterator();
            while (it.hasNext()) {
                SourceCodeFile pa = it.next();
                if (path.equals(pa.getRelativePath()) || path.equals(pa.getPath()))
                    return pa;
            }
        }
        return null;
    }

    public static SourceCodeFile getCoreAssetByProductAssetPath(String path, ProductRelease productRelease) {
        List<SourceCodeFile> caList = productRelease.getProductAssets();
        if (caList != null) {
            Iterator<SourceCodeFile> it = caList.iterator();
            while (it.hasNext()) {
                SourceCodeFile ca = it.next();
                if (path.equals(ca.getRelativePath()) || path.equals(ca.getPath()))
                    return ca;
            }

        }
        logger.error("NOT FOUND path:" + path);
        return null;
    }
}
