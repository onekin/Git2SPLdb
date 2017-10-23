package repodriller.diffparser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.CustomDiff;
import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.ProductRelease;
import customDiff.SPLdomain.SourceCodeFile;
import customDiff.SPLdomain.VariationPoint;
import customDiff.blame.BlamedLine;
import customDiff.utils.RefUtils;
import customDiff.utils.Utils;

public class DiffParser {

	private List<DiffBlock> diffBlocks;
	private List<CustomDiffBlock> customDiffBlocks; //by variation point
	private String fullDiff;
	private ProductRelease pr;
	private String path; //pa path
	private Modification modification;
	
	
	public DiffParser(Modification mod, ProductRelease pr){//(String fullDiff, ProductRelease pr, String path) {
		this.fullDiff = mod.getDiff();//fullDiff;
		diffBlocks = new ArrayList<DiffBlock>();
		this.pr  =pr;
		this.path = mod.getNewPath();// path;
		this.modification = mod;
		extractDiffBlocks();
		
		extractCustomDiffBlocks();/** Parse blocks for customDiff*/
		
	}

	
	private void extractDiffBlocks() {
		String[] lines = fullDiff.replace("\r", "").split("\n");
		String[] linesNoHeader = Arrays.copyOfRange(lines, 4, lines.length);

		StringBuilder currentDiff = new StringBuilder();
		boolean currentInADiff = false;
		
		for(int i = 0; i < linesNoHeader.length; i++) {
			String currentLine = linesNoHeader[i];
			if(currentLine.startsWith("@@ -") && !currentInADiff) {
				currentInADiff = true;
			}
			else if(currentLine.startsWith("@@ -") && currentInADiff) {
				diffBlocks.add(new DiffBlock(currentDiff.toString()));
				currentDiff = new StringBuilder();
				currentInADiff = false;
				i--;
			}

			if(currentInADiff) currentDiff.append(currentLine + "\n");
		}
		diffBlocks.add(new DiffBlock(currentDiff.toString()));

	}
	
	/** Leticia Montalvillo **/
	public void extractCustomDiffBlocks(){
		customDiffBlocks = new ArrayList<CustomDiffBlock>();
		int numberOfBlock= getBlocks().size(); //how many blocks in the "normal" diff	
		System.out.println("Number of blocks to parse are: "+numberOfBlock);
		
		for(int i=0; i < numberOfBlock; i++){
			customDiffBlocks. addAll (parseBlockToExtractCustomDiffBlocks(getBlocks().get(i)));
		}
	}
	
	
	
	private ArrayList<CustomDiffBlock> parseBlockToExtractCustomDiffBlocks(DiffBlock diffBlock) {
		
		System.out.println("Parsing diff block diff \n"+diffBlock.getDiffBlock());
		ArrayList<CustomDiffBlock> customDiffBlocks = new ArrayList<CustomDiffBlock>();
		
		String header = diffBlock.getLines()[0];//("@@ -(\\d*),(\\d*) \\+(\\d*),(\\d*) @@.*");
		int diff_lineCounter=0;
		int totalDeletedLines=0, total_addedLines=0, total_contextLines=0;//For d1 and d3
		int diff_contextLines=0, diff_deletedlines=0, diff_addedLines=0;//for d2 and d4
		int d1=diffBlock.getD1();//2
		int d3= diffBlock.getD3();//2
		int d2=diffBlock.getD2(), d4 = diffBlock.getD4();
		String[] lines = diffBlock.getLines();
		
		int startLineNextblock= 1;//the start diff line for the next block
		boolean anyVP=false;
		
		for (String line: lines ){
			if(line.startsWith("-")) {
				diff_deletedlines++;
				totalDeletedLines++;
			} 
			else if(line.startsWith("+")) {
				diff_addedLines++;
				total_addedLines++;
			}  else //is a context line
				if(line.contains(CustomDiff.annotationPatternBeginning) || line.contains(CustomDiff.annotationPatternEnd)){			
					//
					diff_contextLines++;
					d2 = diff_contextLines + diff_deletedlines;
					d4 = diff_contextLines + diff_addedLines;
					
					customDiffBlocks.add(createCustomDiffBlock(diffBlock,startLineNextblock, diff_lineCounter-1, true, 
							d1, d2,d3,d4));
					
					//headers for the next diff block
					d1 = diffBlock.getD1() +  total_contextLines + totalDeletedLines ;// + diff_deletedlines;
					d3 = diffBlock.getD3() +  total_contextLines + total_addedLines;//total_addedLines - totalDeletedLines;
					
					startLineNextblock = diff_lineCounter;
					diff_contextLines=0; diff_deletedlines=0; diff_addedLines=0;
					anyVP=true;
					total_contextLines++;
					
				
			}else {
				if(!line.startsWith("@@")) {
					diff_contextLines++;
					total_contextLines++;
				}
			}
			diff_lineCounter ++;
		}
		
		if (anyVP==false){// TODO if the diff did not contain any variation point start or end; in it.It goes directly without parsing it
			customDiffBlocks.add(createCustomDiffBlock(diffBlock,startLineNextblock, lines.length-1, false,
					d1,d2,d3,d4));
		}
		return customDiffBlocks;
	}
	
	/** Custom Diff: per variation point. With expression in the header & with authors name in changes**/
	private CustomDiffBlock createCustomDiffBlock(DiffBlock diffBlock, int startLine, int endline, 	boolean fixHeader,int d1, int d2, int d3, int d4){
		 ArrayList<Developer> developers = new ArrayList<Developer>();
		 ArrayList<RevCommit> commits = new ArrayList<RevCommit>();
		 ArrayList<String> messages = new ArrayList<String>();
		
		System.out.println("New chunk! From line "+startLine+" to line:"+ endline);
		ArrayList<String> newDiff = new ArrayList<>();
		String[] originalDiff = diffBlock.getLines();
		
		if (fixHeader) {//TODO Fix the header lines!
			//newDiff.add(originalDiff[0]);//add the header.
			newDiff.add("@@-"+d1+","+d2+" +"+d3+","+d4+"@@");//add the header.
			
		}  
		int newFilelineCounter = startLine+diffBlock.getD3()-2;//TODO startLine+diffBlock.getD3()-1
		String strblame;
		String commit = null;
		String author = null, message = null;
		String developerdHeader="Developed by:";
		for(int i=startLine; i<=endline; i++){
			strblame= "";
			if(originalDiff[i].startsWith("+")) {// introduce THE blame for that line, only for added lines
				List<BlamedLine> blames = modification.getBlameLines();
				author =  blames.get(newFilelineCounter).getAuthor();
				int line = blames.get(newFilelineCounter).getLineNumber();
				commit = blames.get(newFilelineCounter).getCommit();String lineAdded = blames.get(newFilelineCounter).getLine();
				
				developerdHeader = developerdHeader.concat(author+" ");
				if (!commits.contains(RefUtils.getCommitFromCommitSha(commit))) {
					message = RefUtils.getCommitMessage(commit);
					if (!messages.contains(message)) messages.add(message);
				}
				strblame = "//Authored by: "+author+" in commit:"+commit+ ", with message:"+message;
				commits.add(RefUtils.getCommitFromCommitSha(commit));
			    developers.add(addAuthor(RefUtils.getCommitAuthor(commit)));
			}
			newDiff.add(originalDiff[i]+strblame);//add line to the customDiffBlock
			
			if (!originalDiff[i].startsWith("-")){//if the operation is delete. BLAME --REVERSE TODO
				newFilelineCounter++;
			}
			
		}//end for
		
		//add initial context lines and final context lines. TODO 

		if( diffBlockHasModifications(newDiff)==false)
			return null; //there are no modifications in this chunk - it was part of the context
				
		SourceCodeFile paModified = customDiff.utils.FileUtils.getProductAssetByFilePath(path, pr);
		SourceCodeFile coreAsset = customDiff.utils.FileUtils.getCoreAssetByProductAssetPath( path,  pr );
		
		//Variation point changed -- On product asset
		VariationPoint vp_pa = customDiff.utils.FeatureAnalysisUtils.getVariationPointOfChangedProductAssetLine
											(paModified.getRelativePath(), pr, d3);// startLine+diffBlock.getD1());
		//Variation point changed -- On core asset asset
		VariationPoint vp_ca = customDiff.utils.FeatureAnalysisUtils.getVariationPointOfChangedCoreAssetLine
				(paModified.getRelativePath(), d1);//;startLine+diffBlock.getD1());
		
		if(vp_ca ==null){
			vp_ca = new VariationPoint(Utils.getVPId(),"No Expression", 0, null);
			coreAsset.addVariationPoint(vp_ca);
		} 
		
		if(vp_pa==null){
			vp_pa = new VariationPoint(Utils.getVPId(), "No Expression", 0, null);
			paModified.addVariationPoint(vp_pa);
		}
		String header = "Expression: "+getHeaderForVariationPoint(vp_pa);
		
		newDiff.set(0, newDiff.get(0).concat(header).concat("\n"+developerdHeader).concat("\nCommit messages: "+messages.toString()));//add VP expression to header
		
	return new CustomDiffBlock(arrayListToString(newDiff),vp_pa,vp_ca,header,paModified, coreAsset, commits, developers, messages);

	}
	
	
	private Developer addAuthor(PersonIdent author) {
		Developer dev;
		Iterator<Developer> it = CustomDiff.allDevelopers.iterator();
		while (it.hasNext()){
			dev = it.next();
			if (dev.getName().equals(author.getName()))
				return dev;
		}
		dev = new Developer(customDiff.utils.Utils.getDeveloperId(), author.getName(), author.getEmailAddress());
		CustomDiff.allDevelopers.add(dev);
		
		return dev;
	}
		
	private String getHeaderForVariationPoint(VariationPoint vp) {
		if(vp==null) return "No VP";
		
		String header= vp.getExpression().replace("PV:IFCOND", "");
		//header = header.replace(" ", "");
		header = header.replace("//", "");
		if(vp.getParentVP()!=null){
			header= header +" -> Nested into -> "+ getHeaderForVariationPoint(vp.getParentVP());
		}
		return header;
	}


	private boolean diffBlockHasModifications(ArrayList<String> newDiff) {
		Iterator<String> it = newDiff.iterator();
		String aux;
		
		while (it.hasNext()){
			aux = it.next();
			if(aux.startsWith("-") || aux.startsWith("+"))
				return true;
			
		}
		return false;
	}


	public List<DiffBlock> getBlocks() {//hunks
		return diffBlocks;
	}

	public String getFullDiff() {
		return fullDiff;
	}
	
	public List<CustomDiffBlock> getCustomDiffBlocks() {
		return customDiffBlocks;
	}
	public void setCustomDiffBlocks(List<CustomDiffBlock> customDiffBlocks) {
		this.customDiffBlocks = customDiffBlocks;
	}
	
	public String arrayListToString(ArrayList<String> array){
		String str="";
		for(int i=0; i< array.size();i++){
			str+=array.get(i)+"\n";
		}
		return str;
	}


}
