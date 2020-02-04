package org.onekin.customdiff.diffparser;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DiffBlock {
private static final Logger logger = LogManager.getLogger(DiffBlock.class);

	private int d1;
	private int d2;
	private int d3;
	private int d4;
	private String[] lines;
	private String content;

	
	public DiffBlock(String diffBlock) {
		this.content = diffBlock;
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
		 logger.error("ERROR. Impossible to get line positions in this diff: {} ", content);
		}
	}

	public String[] getLines() {
		return lines;
	}

	private List<DiffLine> getLines(int start, String ch) {
		List<DiffLine> oldLines = new ArrayList<>();
		int counter = start; 
		for(String line : lines) {
			if(line.startsWith(ch) || line.startsWith(" ")) {
				oldLines.add(new DiffLine(counter, line.substring(1), typeOf(line)));
				counter++;
			}
		}
		return oldLines;
		
	}
	
	private DiffLineType typeOf(String line) {
		if(line.startsWith(" ")) return DiffLineType.KEPT;
		if(line.startsWith("+")) return DiffLineType.ADDED;
		if(line.startsWith("-")) return DiffLineType.REMOVED;
		return null;
	}

	public List<DiffLine> getLinesInOldFile() {
		return getLines(d1, "-");
	}


	public List<DiffLine> getLinesInNewFile() {
		return getLines(d3, "+");
	}
	
	public String getContent() {
		return content;
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

	public void setContent(String content) {
		this.content = content;
	}
	
}
