package org.onekin.customdiff.spldomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProductAssetFileAnnotated implements SourceCodeFile {

    int id;
    String fileName;
    String path;
    String relativePath;
    String content;
    int totalLines;
    ProductRelease fromPRelease;
    String relative_diff;
    String absolute_diff;


    boolean isNewAsset;
    ArrayList<Feature> newfeatures; //tentative

    HashMap<Integer, ArrayList<String>> featureToCodeMapping = new HashMap<Integer, ArrayList<String>>();
    ArrayList<Feature> featureList = new ArrayList<Feature>();
    List<VariationPoint> variationPoints;

    public ProductAssetFileAnnotated(int id, String fileName, String path, String content, int totalLines,
                                     ProductRelease pr, String relativepath, boolean isNew, String st) {

        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.content = content;
        this.totalLines = totalLines;
        this.fromPRelease = pr;
        this.relativePath = relativepath;
        this.newfeatures = new ArrayList<Feature>(); //tentative
        this.isNewAsset = isNew;
        variationPoints = new ArrayList<VariationPoint>();
    }


    public String getRelativePath() {
        return relativePath;
    }


    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }


    public List<VariationPoint> getVariationPoints() {
        return variationPoints;
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


    public ProductRelease getFromPRelease() {
        return fromPRelease;
    }


    public void setFromPRelease(ProductRelease fromPRelease) {
        this.fromPRelease = fromPRelease;
    }


    public HashMap<Integer, ArrayList<String>> getFeatureToCodeMapping() {
        return featureToCodeMapping;
    }


    public void setFeatureToCodeMapping(
            HashMap<Integer, ArrayList<String>> featureToCodeMapping) {
        this.featureToCodeMapping = featureToCodeMapping;
    }

    @Override
    public void setVariationPoints(List<VariationPoint> variationPoints) {
        this.variationPoints=variationPoints;
    }


    public ArrayList<Feature> getFeatureList() {
        return featureList;
    }


    public void setFeatureList(ArrayList<Feature> featureList) {
        this.featureList = featureList;
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public void setContent(String content) {
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


    public void setVariationPoints(ArrayList<VariationPoint> variationPoints) {
        this.variationPoints = variationPoints;
    }


    public String getRelative_diff() {
        return relative_diff;
    }


    public void setRelative_diff(String relative_diff) {
        this.relative_diff = relative_diff;
    }


    public String getAbsolute_diff() {
        return absolute_diff;
    }


    public void setAbsolute_diff(String absolute_diff) {
        this.absolute_diff = absolute_diff;
    }

    public void addVariationPoint(VariationPoint vp) {
        if (variationPoints == null) {
            this.variationPoints = new ArrayList<>();
        }
        this.variationPoints.add(vp);

    }


    public boolean isNewAsset() {
        return isNewAsset;
    }


    public void setNewAsset(boolean isNewAsset) {
        this.isNewAsset = isNewAsset;
    }


    @Override
    public boolean getIsNewAsset() {
        // TODO Auto-generated method stub
        return isNewAsset;
    }

}
