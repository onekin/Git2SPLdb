package customDiff.SPLdomain;

import java.util.HashSet;
import java.util.Set;

public class FeatureSibling {

	private int id;
	private Set<String> features = new HashSet<String>();

	public FeatureSibling(int id, Set<String> features) {
		super();
		this.id = id;
		this.features = features;
	}

	public FeatureSibling() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FeatureSibling(Set<String> listFeatures) {
		this.features.addAll(listFeatures);
	}

	public FeatureSibling(int id, String noFeature) {
		this.id = id;
		features.add(noFeature);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<String> getFeatures() {
		return features;
	}

	public void setFeatures(Set<String> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "FeatureSibling [id=" + id + ", features=" + features + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
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
		FeatureSibling other = (FeatureSibling) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		return true;
	}

	public String getFeatureExpression() {		
		return String.join(" - ", features);
	}

}
