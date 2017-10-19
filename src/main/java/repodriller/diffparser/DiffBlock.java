package repodriller.diffparser;



import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import customDiff.SPLdomain.VariationPoint;

//import java.util.stream.*;
//import java.util.Optional;

public class DiffBlock {

	private int d1;
	private int d2;
	private int d3;
	private int d4;
	private String[] lines;
	private String diffBlock;

	
	public DiffBlock(String diffBlock) {
		this.diffBlock = diffBlock;
		this.lines = diffBlock.replace("\r", "").split("\n");
		getLinePositions();
	
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
		 System.out.println("ERROR. Impossible to get line positions in this diff: " + diffBlock);
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
	
	/*public Optional<DiffLine> getLineInOldFile(int line) {
		return getLinesInOldFile().stream().filter(x -> x.getLineNumber() == line).findFirst();
	}

	public Optional<DiffLine> getLineInNewFile(int line) {
		return getLinesInNewFile().stream().filter(x -> x.getLineNumber() == line).findFirst();
	}*/

	public List<DiffLine> getLinesInNewFile() {
		return getLines(d3, d4, "+");
	}
	
	public String getDiffBlock() {
		return diffBlock;
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

	public void setLines(String[] lines) {
		this.lines = lines;
	}

	public void setDiffBlock(String diffBlock) {
		this.diffBlock = diffBlock;
	}
	
}
