package customDiff.SPLdomain;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.utils.RefUtils;

import repodriller.diffparser.CustomDiffBlock;
public class Customization {
	
		//ArrayList<Integer> linesOfCodeModified;
		int id;
		int linesAdded;
		int linesDeleted;	
		boolean isNewAsset;
		boolean hasNewFeature;
		private ProductRelease inRelease;
		private String wholediff;
		private VariationPoint variationpointpa;
		private VariationPoint variationpointca;
		private String custom_diff;
		private SourceCodeFile productasset;
		private SourceCodeFile coreasset;
		private ArrayList<Developer> developers;
		private ArrayList<String> messages;
		private ArrayList<RevCommit> commits;
		CustomizationType type;
		
		private CustomDiffBlock customDiffBlock; //Developers,commits,customDiff, variationPoint
		
		public Customization(int id, int linesAdded, int linesDeleted, String wholediff, 
				boolean isNewAsset, boolean hasNewFeature, 
				ProductRelease inRelease, CustomDiffBlock customDiffBlock) {
			this.id=id;
			this.linesAdded = linesAdded;
			this.linesDeleted = linesDeleted;
			this.isNewAsset = isNewAsset;
			this.hasNewFeature = hasNewFeature;
			this.inRelease = inRelease;
			this.wholediff=wholediff;
			this.customDiffBlock = customDiffBlock;
			this.productasset = customDiffBlock.getPaModified();
			this.coreasset = customDiffBlock.getCoreAsset();
			this.developers = customDiffBlock.getDevelopers();
			this.commits = customDiffBlock.getCommits();
			this.custom_diff = customDiffBlock.getDiffBlock();
			this.variationpointca = customDiffBlock.getVp_ca();
			this.variationpointpa = customDiffBlock.getVp_pa();
			this.messages = customDiffBlock.getMessages();
			this.type = customDiffBlock.getType();
		}

		public String toString(){
			return "CustomFact for PR:"+inRelease.getTagName()+ " for asset: "+customDiffBlock.getPaModified().getFileName()+
			" added:"+linesAdded+ " deleted:"+linesDeleted
			//+" vp_ca:"+customDiffBlock.getVp_ca().getVPFullExpression(customDiffBlock.getVp_ca())
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
	
		
		public void setWholediff(String wholediff) {
			this.wholediff = wholediff;
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

	

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}


		public String getCommitShas(){
			String commits="";
			
			Iterator<RevCommit> it = this.commits.iterator();
			RevCommit commit;
			
			while(it.hasNext()){
				commit = it.next();
				commits = commits.concat(commit.getName()+"; ");
				
			}
			return commits;
		}

		public String getCommitMessagesToString() {
			String messages="";
			Iterator<String> it = this.messages.iterator();
			while(it.hasNext())
				messages = messages.concat(it.next()+"; ");
				
			return messages;
		}

		public CustomDiffBlock getCustomDiffBlock() {
			return customDiffBlock;
		}

		public void setCustomDiffBlock(CustomDiffBlock customDiffBlock) {
			this.customDiffBlock = customDiffBlock;
		}

		public SourceCodeFile getProductasset() {
			return productasset;
		}

		public void setProductasset(SourceCodeFile productasset) {
			this.productasset = productasset;
		}

		public SourceCodeFile getCoreasset() {
			return coreasset;
		}

		public void setCoreasset(SourceCodeFile coreasset) {
			this.coreasset = coreasset;
		}

		public ArrayList<Developer> getDevelopers() {
			return developers;
		}

		public void setDevelopers(ArrayList<Developer> developers) {
			this.developers = developers;
		}

		public ArrayList<String> getMessages() {
			return messages;
		}

		public void setMessages(ArrayList<String> messages) {
			this.messages = messages;
		}

		public VariationPoint getVariationpointpa() {
			return variationpointpa;
		}

		public void setVariationpointpa(VariationPoint variationpointpa) {
			this.variationpointpa = variationpointpa;
		}

		public VariationPoint getVariationpointca() {
			return variationpointca;
		}

		public void setVariationpointca(VariationPoint variationpointca) {
			this.variationpointca = variationpointca;
		}

		public ArrayList<RevCommit> getCommits() {
			return commits;
		}

		public void setCommits(ArrayList<RevCommit> commits) {
			this.commits = commits;
		}

		public String getWholediff() {
			return wholediff;
		}

		public String getCustom_diff() {
			return custom_diff;
		}

		public void setCustom_diff(String custom_diff) {
			this.custom_diff = custom_diff;
		}
		
		public CustomizationType getType() {
			return this.type;
		}


}
