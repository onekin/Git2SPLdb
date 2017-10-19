package customDiff.SPLdomain;

import java.util.ArrayList;
public class CustomizationFact {
	
		//ArrayList<Integer> linesOfCodeModified;
		int linesAdded;
		int linesDeleted;
		String customdiff;
		SourceCodeFile productFile;
		SourceCodeFile coreAssetFile;
		VariationPoint vp;//it changes a variation Point
		String wholediff;
		ArrayList<Developer> developers;
		
		boolean isNewAsset;
		boolean hasNewFeature;
		ProductRelease inRelease;
		

		
		public CustomizationFact(int linesAdded, int linesDeleted, String customdiff, SourceCodeFile productFile,
				SourceCodeFile coreAssetFile, VariationPoint vp, String wholediff, ArrayList<Developer> developers,
				boolean isNewAsset, boolean hasNewFeature, ProductRelease inRelease) {
			
			this.linesAdded = linesAdded;
			this.linesDeleted = linesDeleted;
			this.customdiff = customdiff;
			this.productFile = productFile;
			this.coreAssetFile = coreAssetFile;
			this.vp = vp;
			this.wholediff = wholediff;
			this.developers = developers;
			this.isNewAsset = isNewAsset;
			this.hasNewFeature = hasNewFeature;
			this.inRelease = inRelease;
		}

		public String toString(){
			return "CustomFact for PR:"+inRelease.getIdRelease()+ " for asset: "+productFile.getFileName()+
					" added:"+linesAdded+ " deleted:"+linesDeleted+" vp:"+vp.getVPFullExpression(vp)
					+" customDiff:\n"+customdiff;
		}
		public int getChangeChurn(){
			return linesAdded + linesDeleted;
		}
		
		public int getChangeDelta(){
			
			return productFile.getTotalLines() - coreAssetFile.getTotalLines();
		}
		
		public int getChangePercentage(){
			
			return (getChangeChurn() / productFile.getTotalLines()) * 100;
		}
		
		public int getLinesAdded() {
			return linesAdded;
		}
		public void setLinesAdded(int linesAdded) {
			this.linesAdded = linesAdded;
		}
		public int getLinesDeleted() {
			return linesDeleted;
		}
		public void setLinesDeleted(int linesDeleted) {
			this.linesDeleted = linesDeleted;
		}
		public String getCustomdiff() {
			return customdiff;
		}
		public void setCustomdiff(String customdiff) {
			this.customdiff = customdiff;
		}
		public SourceCodeFile getProductFile() {
			return productFile;
		}
		public void setProductFile(SourceCodeFile productFile) {
			this.productFile = productFile;
		}
		public SourceCodeFile getCoreAssetFile() {
			return coreAssetFile;
		}
		public void setCoreAssetFile(SourceCodeFile coreAssetFile) {
			this.coreAssetFile = coreAssetFile;
		}
		public VariationPoint getVp() {
			return vp;
		}
		public void setVp(VariationPoint vp) {
			this.vp = vp;
		}
		public String getWholediff() {
			return wholediff;
		}
		public void setWholediff(String wholediff) {
			this.wholediff = wholediff;
		}
		public ArrayList<Developer> getDevelopers() {
			return developers;
		}
		public void setDevelopers(ArrayList<Developer> developers) {
			this.developers = developers;
		}
		public boolean isNewAsset() {
			return isNewAsset;
		}
		public void setNewAsset(boolean isNewAsset) {
			this.isNewAsset = isNewAsset;
		}
		public boolean isHasNewFeature() {
			return hasNewFeature;
		}
		public void setHasNewFeature(boolean hasNewFeature) {
			this.hasNewFeature = hasNewFeature;
		}
		public ProductRelease getInRelease() {
			return inRelease;
		}
		public void setInRelease(ProductRelease inRelease) {
			this.inRelease = inRelease;
		}





}
