package repodriller.diffparser;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.SPLdomain.CustomizationType;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;

public class CustomDiffBlock {



	private int d1;
	private int d2;
	private int d3;
	private int d4;
	private String[] lines;
	private String diffBlock;//customdiff
	
	/**Added by Leticia Montalvillo*/
	private int addedlines;
	private int deteledlines;
	private VariationPoint vp_pa;
	private VariationPoint vp_ca;
	private String additionalVPHeader;
	private SourceCodeFile paModified;
	private SourceCodeFile coreAsset;
	private ArrayList<RevCommit> commits;
	private ArrayList<Developer> developers;
	private ArrayList<String> messages;
	CustomizationType type;

	
	public CustomDiffBlock(String diffBlock, VariationPoint vp_pa, VariationPoint vp_ca , String additionalheader, 
			SourceCodeFile paModified, SourceCodeFile coreAsset, ArrayList<RevCommit> commits, ArrayList<Developer> developers,
			ArrayList<String> messages, CustomizationType type) {//le pasas ya el formateado
		this.diffBlock = diffBlock;
		this.lines = diffBlock.replace("\r", "").split("\n");
		this.vp_pa= vp_pa;
		this.vp_ca= vp_ca;
		this.additionalVPHeader = additionalheader;
		//lines[0] = lines[0] + additionalheader; ya viene en diffblock
		this.coreAsset=coreAsset;
		this.paModified = paModified;
		this.developers = developers;
		this.commits = commits;
		this.messages = messages;
		this.type = type;
		getLinePositions();
		getAddedAndDeletedLines();
	}
	
	private void getLinePositions() {
		String positions = lines[0];
		Pattern p = Pattern.compile("@@ -(\\d*),(\\d*) \\+(\\d*),(\\d*) @@.*");
		Matcher matcher = p.matcher(positions);
		
		if(matcher.matches()) {
			d1 = Integer.parseInt(matcher.group(1));
			d2 = Integer.parseInt(matcher.group(2));
			d3 = Integer.parseInt(matcher.group(3));
			d4 = Integer.parseInt(matcher.group(4));
		} else {
		//	throw new RepoDrillerException("Impossible to get line positions in this diff: " + diffBlock);
		}
	}

	public String[] getLines() {
		return lines;
	}

	private List<DiffLine> getLines(int start, int qtyLines, String ch) {
		List<DiffLine> oldLines = new ArrayList<DiffLine>();
		int counter = start; 
		for(String line : lines) {
			if(line.startsWith(ch) || line.startsWith(" ")) {
				oldLines.add(new DiffLine(counter, line.substring(1), typeOf(line)));
				counter++;
			}
		}
		if(counter!=start+qtyLines) ;//throw new RepoDrillerException("malformed diff");
		
		return oldLines;
		
	}
	
	private DiffLineType typeOf(String line) {
		if(line.startsWith(" ")) return DiffLineType.KEPT;
		if(line.startsWith("+")) return DiffLineType.ADDED;
		if(line.startsWith("-")) return DiffLineType.REMOVED;
		return null;
		//throw new RepoDrillerException("type of diff line not recognized: " + line);
	}

	public List<DiffLine> getLinesInOldFile() {
		return getLines(d1, d2, "-");
	}


	public List<DiffLine> getLinesInNewFile() {
		return getLines(d3, d4, "+");
	}
	
	public String getDiffBlock() {
		return diffBlock;
	}


	
	public VariationPoint getVp_pa() {
		return vp_pa;
	}

	public void setVp_pa(VariationPoint vp_pa) {
		this.vp_pa = vp_pa;
	}

	public VariationPoint getVp_ca() {
		return vp_ca;
	}

	public void setVp_ca(VariationPoint vp_ca) {
		this.vp_ca = vp_ca;
	}

	public void getAddedAndDeletedLines(){
		int added=0, removed=0;
		for(String line : diffBlock.replace("\r", "").split("\n")) {
			if(line.startsWith("+") && !line.startsWith("+++")) added++;
			if(line.startsWith("-") && !line.startsWith("---")) removed++;
		}
		this.addedlines = added;
		this.deteledlines = removed;
	}

	public int getAddedlines() {
		return addedlines;
	}

	public void setAddedlines(int addedlines) {
		this.addedlines = addedlines;
	}

	public int getDeteledlines() {
		return deteledlines;
	}

	public void setDeteledlines(int deteledlines) {
		this.deteledlines = deteledlines;
	}

	public String getAdditionalVPHeader() {
		return additionalVPHeader;
	}

	public void setAdditionalVPHeader(String additionalVPHeader) {
		this.additionalVPHeader = additionalVPHeader;
	}

	public int getD1() {
		return d1;
	}

	public void setD1(int d1) {
		this.d1 = d1;
	}

	public int getD2() {
		return d2;
	}

	public void setD2(int d2) {
		this.d2 = d2;
	}

	public int getD3() {
		return d3;
	}

	public void setD3(int d3) {
		this.d3 = d3;
	}

	public int getD4() {
		return d4;
	}

	public void setD4(int d4) {
		this.d4 = d4;
	}

	public SourceCodeFile getPaModified() {
		return paModified;
	}

	public void setPaModified(SourceCodeFile paModified) {
		this.paModified = paModified;
	}

	public SourceCodeFile getCoreAsset() {
		return coreAsset;
	}

	public void setCoreAsset(SourceCodeFile coreAsset) {
		this.coreAsset = coreAsset;
	}

	public void setLines(String[] lines) {
		this.lines = lines;
	}

	public void setDiffBlock(String diffBlock) {
		this.diffBlock = diffBlock;
	}

	public ArrayList<RevCommit> getCommits() {
		return commits;
	}

	public void setCommits(ArrayList<RevCommit> commits) {
		this.commits = commits;
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

	public CustomizationType getType() {
		return this.type;
	}	
}
