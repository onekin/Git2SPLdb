package repodriller.diffparser;

import customDiff.SPLdomain.Developer;
import customDiff.SPLdomain.VariationPoint;


public class DiffLine {

	private final int lineNumber;
	private final String line;
	private DiffLineType type;
	

	private Developer blamedDeveloper;
	
	public DiffLine(int lineNumber, String line, DiffLineType type) {
		this.lineNumber = lineNumber;
		this.line = line;
		this.type = type;
	}
	
	public DiffLine(int lineNumber, String line, DiffLineType type, VariationPoint vp) {
		this.lineNumber = lineNumber;
		this.line = line;
		this.type = type;
	
	}
	
	public DiffLine(int lineNumber, String line, DiffLineType type, VariationPoint vp, Developer developer) {
		this.lineNumber = lineNumber;
		this.line = line;
		this.type = type;
	
		this.blamedDeveloper = developer;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLine() {
		return line;
	}
	
	public DiffLineType getType() {
		return type;
	}


	public String toString() {
		return "DiffLine [lineNumber=" + lineNumber + ", line=" + line + ", type=" + type + "]";
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + lineNumber;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DiffLine other = (DiffLine) obj;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		if (type != other.type)
			return false;
		return true;
	}



	public Developer getBlamedDeveloper() {
		return blamedDeveloper;
	}

	public void setBlamedDeveloper(Developer blamedDeveloper) {
		this.blamedDeveloper = blamedDeveloper;
	}

	
	
}
