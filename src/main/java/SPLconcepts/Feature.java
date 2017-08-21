package SPLconcepts;

import java.util.ArrayList;

public class Feature {
	String name;
	String idFeature;
	
	ArrayList<SourceCodeFile> coreassets;
	ArrayList<CoreAssetBaseline> inBaselines;
	
	ArrayList<Feature>  requires;
	ArrayList<Feature>  excludes;
	
}
