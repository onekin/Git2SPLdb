package customDiff.SPLdomain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Feature {
	String name;
	String idFeature;

	ArrayList<SourceCodeFile> coreassets;
	ArrayList<CoreAssetBaseline> inBaselines;
	boolean isNew = false;
	ArrayList<Feature> requires;
	ArrayList<Feature> excludes;
	String parentFeatureName;
	private Set<FeatureSibling> featureSiblings = new HashSet<>();

	public Feature(String name, String idFeature) {
		this.name = name;
		this.idFeature = idFeature;
	}

	public Feature(String name, String idFeature, boolean isNew) {
		this.name = name;
		this.idFeature = idFeature;
		this.isNew = isNew;
	}

	public Feature(String name, String idFeature, boolean isNew, String parentFeatureName) {
		this.name = name;
		this.idFeature = idFeature;
		this.isNew = isNew;
		this.parentFeatureName = parentFeatureName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdFeature() {
		return idFeature;
	}

	public void setIdFeature(String idFeature) {
		this.idFeature = idFeature;
	}

	public ArrayList<CoreAssetBaseline> getInBaselines() {
		return inBaselines;
	}

	public void setInBaselines(ArrayList<CoreAssetBaseline> inBaselines) {
		this.inBaselines = inBaselines;
	}

	public ArrayList<Feature> getRequires() {
		return requires;
	}

	public ArrayList<Feature> getExcludes() {
		return excludes;
	}

	public String toString() {
		return name;
	}

	public boolean getIsNew() {
		return isNew;
	}

	public int getIsNewToInt() {
		if (isNew)
			return 1;
		else
			return 0;
	}

	public String getParentFeatureName() {
		return parentFeatureName;
	}

	public void setParentFeatureName(String parentFeatureName) {
		System.out.println("Feature " + name + " has parent: " + parentFeatureName);
		this.parentFeatureName = parentFeatureName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idFeature == null) ? 0 : idFeature.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feature other = (Feature) obj;
		if (idFeature == null) {
			if (other.idFeature != null)
				return false;
		} else if (!idFeature.equals(other.idFeature))
			return false;
		return true;
	}

	public Set<FeatureSibling> getFeatureSiblings() {
		return featureSiblings;
	}

	public void setFeatureSiblings(Set<FeatureSibling> featureSiblings) {
		this.featureSiblings = featureSiblings;
	}

}
