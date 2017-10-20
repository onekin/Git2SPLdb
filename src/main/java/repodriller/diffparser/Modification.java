/**
 * Copyright 2014 Maurício Aniche

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package repodriller.diffparser;


import java.io.File;
import java.util.List;

import org.eclipse.jgit.revwalk.RevCommit;

import customDiff.SPLdomain.VariationPoint;
import customDiff.blame.BlamedLine;


public class Modification {

	private String oldPath;//coreAsset
	private String newPath;//productAsset
	private ModificationType type;
	private String diff;
	private String sourceCode;//productasset code
	private int added; //lines added
	private int removed;//lines deleted
	RevCommit oldCommit, newCommit;
	List<BlamedLine> blameLines;
	
	public Modification(String oldPath, String newPath, ModificationType type, String diff, 
			String sourceCode, RevCommit oldCommit, RevCommit newCommit ) {
		this.oldPath = oldPath;
		this.newPath = newPath;
		this.type = type;
		this.diff = diff;
		this.sourceCode = sourceCode;
		this.newCommit=newCommit;
		this.oldCommit = oldCommit;
		
		for(String line : diff.replace("\r", "").split("\n")) {
			if(line.startsWith("+") && !line.startsWith("+++")) added++;
			if(line.startsWith("-") && !line.startsWith("---")) removed++;
		}
		
		blameLines = customDiff.blame.BlameUtils.blame(oldPath, newCommit.getName(), false);
		
		
	}

	public String getOldPath() {
		return oldPath;
	}

	public String getNewPath() {
		return newPath;
	}

	public ModificationType getType() {
		return type;
	}

	public String getDiff() {
		return diff;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	@Override
	public String toString() {
		return "Modification [oldPath=" + oldPath + ", newPath=" + newPath + ", type=" + type
				+ "]";
	}

	public boolean wasDeleted() {
		return type.equals(ModificationType.DELETE);
	}

	public boolean fileNameEndsWith(String suffix) {
		return newPath.toLowerCase().endsWith(suffix.toLowerCase());
	}

	public boolean fileNameMatches(String regex) {
		return newPath.toLowerCase().matches(regex);
	}

	public String getFileName() {
		String thePath = newPath!=null && !newPath.equals("/dev/null") ? newPath : oldPath;
		if(!thePath.contains(File.separator)) return thePath;
		
		String[] fileName = thePath.split(File.separator);
		return fileName[fileName.length-1];
	}
	

	public int getAdded() {
		return added;
	}
	
	public int getRemoved() {
		return removed;
	}

	public RevCommit getOldCommit() {
		return oldCommit;
	}

	public void setOldCommit(RevCommit oldCommit) {
		this.oldCommit = oldCommit;
	}

	public RevCommit getNewCommit() {
		return newCommit;
	}

	public void setNewCommit(RevCommit newCommit) {
		this.newCommit = newCommit;
	}

	public List<BlamedLine> getBlameLines() {
		return blameLines;
	}

	public void setBlameLines(List<BlamedLine> blameLines) {
		this.blameLines = blameLines;
	}

	
}
