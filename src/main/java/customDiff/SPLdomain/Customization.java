package customDiff.SPLdomain;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.utils.RefUtils;

import repodriller.diffparser.CustomDiffBlock;
public class Customization {
	
		//ArrayList<Integer> linesOfCodeModified;
		int linesAdded;
		int linesDeleted;	
		boolean isNewAsset;
		boolean hasNewFeature;
		ProductRelease inRelease;
		String wholediff;
		CustomDiffBlock customDiffBlock; //Developers,commits,customDiff, variationPoint

		
		public Customization(int linesAdded, int linesDeleted, String wholediff, boolean isNewAsset, boolean hasNewFeature, 
				ProductRelease inRelease, CustomDiffBlock customDiffBlock) {
			
			this.linesAdded = linesAdded;
			this.linesDeleted = linesDeleted;
			this.isNewAsset = isNewAsset;
			this.hasNewFeature = hasNewFeature;
			this.inRelease = inRelease;
			this.wholediff=wholediff;
			this.customDiffBlock = customDiffBlock;
		}

		public String toString(){
			return "CustomFact for PR:"+inRelease.getTagName()+ " for asset: "+customDiffBlock.getPaModified().getFileName()+
			" added:"+linesAdded+ " deleted:"+linesDeleted+" vp_ca:"+customDiffBlock.getVp_ca().getVPFullExpression(customDiffBlock.getVp_ca())
			+" vp_pa:"+customDiffBlock.getVp_pa().getVPFullExpression(customDiffBlock.getVp_pa())
			+" customDiff:\n"+customDiffBlock.getDiffBlock();
		}
		public int getChangeChurn(){
			return linesAdded + linesDeleted;
		}
		
		public int getChangeDelta(){
			
			return customDiffBlock.getPaModified().getTotalLines() - customDiffBlock.getCoreAsset().getTotalLines();
		}
		
		public int getChangePercentage(){
			
			return (getChangeChurn() / customDiffBlock.getPaModified().getTotalLines()) * 100;
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
			return customDiffBlock.getDiffBlock();
		}
	
		public SourceCodeFile getProductFile() {
			return customDiffBlock.getPaModified();
		}
	
		public SourceCodeFile getCoreAssetFile() {
			return customDiffBlock.getCoreAsset();
		}
		
		public VariationPoint getVp_ca() {
			return customDiffBlock.getVp_ca();
		}
	
		public VariationPoint getVp_pa() {
			return customDiffBlock.getVp_pa();
		}
		public String getWholediff() {
			return wholediff;
		}
		public void setWholediff(String wholediff) {
			this.wholediff = wholediff;
		}
		public ArrayList<Developer> getDevelopers() {
			return customDiffBlock.getDevelopers();
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

		public ArrayList<String> getCommitMessages(){
			return customDiffBlock.getMessages();
		}

		public String getCommitShas(){
			String commits="";
			
			Iterator<RevCommit> it = customDiffBlock.getCommits().iterator();
			RevCommit commit;
			
			while(it.hasNext()){
				commit = it.next();
				commits = commits.concat(commit.getName()+"; ");
				
			}
			return commits;
		}

		public String getCommitMessagesToString() {
			String messages="";
			Iterator<String> it = getCommitMessages().iterator();
			while(it.hasNext())
				messages = messages.concat(it.next()+"; ");
				
			return messages;
		}

}
