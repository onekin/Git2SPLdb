package org.onekin.customdiff.export;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.onekin.customdiff.spldomain.Feature;
import org.onekin.customdiff.utils.FileUtils;
import org.onekin.customdiff.utils.Utils;
import org.eclipse.jgit.util.Base64;

import org.onekin.customdiff.CustomDiff;
import org.onekin.customdiff.spldomain.CoreAssetBaseline;
import org.onekin.customdiff.spldomain.Customization;
import org.onekin.customdiff.spldomain.CustomizationType;
import org.onekin.customdiff.spldomain.Developer;
import org.onekin.customdiff.spldomain.FeatureSibling;
import org.onekin.customdiff.spldomain.SourceCodeFile;
import org.onekin.customdiff.spldomain.VariationPoint;

public class ExportToStarDiagram implements ExportTarget {

	private int developerGroup = 1;
	private int idParentFeature = 1;
	private int idPackage = 1;

	private HashMap<Integer, String> packages = new HashMap<>();
	private ArrayList<Feature> addedFeatures = new ArrayList<>();
	private HashMap<Integer, String> addedParentFeatures = new HashMap<>();

	@Override
	public void export(String pathToDataFile) {// generate SQL with data inserts.

		try {
			System.out.println("Starting export to SQL");
			ArrayList<String> allInserts = generateInserts();
			FileUtils.writeToFile(pathToDataFile, allInserts);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private ArrayList<String> generateInserts() {

		ArrayList<String> allInserts = new ArrayList<>();

		allInserts.add(generateInsertsForCoreAssets());// core assets from baseline
		System.out.println("inserts for CA done");
		allInserts.add(generateInsertsForFeatures()); // from baseline & parent features
		allInserts.add(generateInsertsForVariationPointsAndFeatureGroups());// from baseline core assets only
		allInserts.add(generateInsertsForDevelopers());
		allInserts.addAll(generateInsertsForProductsReleasesAndCustomizations());// includes inserts to new variation
		// points created in products
		System.out.println("returning inserts");
		return allInserts;
	}




	private List<String> getInsertsForCustomizations(CoreAssetBaseline coreAssetBaseline) { // for customizations and its
		// developers
		List<String> insert = new ArrayList<>();
		String developer_group_header = "INSERT INTO developer_group (id_developer_group) VALUES \n";
		String developer_bridge_header = "INSERT INTO developer_bridge (id_developer_group,id_developer) VALUES \n";
		insert.add(developer_group_header.concat("(-1);\n"));
		Iterator<Customization> customs = coreAssetBaseline.getCustomizations().iterator();
		Customization cust;
		Developer dev;
		Iterator<Developer> developers;
		while (customs.hasNext()) {

			cust = customs.next();
			if (cust.getType() != CustomizationType.FEATURE_SCATTERING_MODIFICATION
					&& cust.getType() != CustomizationType.FEATURE_TANGLING_MODIFICATION) {
				developers = cust.getDevelopers().iterator();
				insert.add(developer_group_header.concat("(" + developerGroup + ");\n"));// insert
				// developer
				// group
				while (developers.hasNext()) {// insert developer bridge
					dev = developers.next();
					insert.add(developer_bridge_header
							.concat("(" + developerGroup + "," + dev.getId() + ");\n"));
				}
				/*
				 * if (cust.getVariationpointca() != null &&
				 * (cust.getVariationpointca().getExpression().equals("No Expression") ||
				 * cust.getVariationpointpa().getExpression().equals("No Expression"))) { insert
				 * = insert.concat(insertEmptyVPForCustomization(cust)); }
				 */
				insert.addAll(generateInsertForCustomization(cust, coreAssetBaseline));
				developerGroup++;
			} else {
				insert.addAll(generateInsertForCustomization(cust, coreAssetBaseline));
			}
		}

		return insert;
	}

	private Collection<? extends String> generateInsertForCustomization(Customization cust, CoreAssetBaseline coreAssetBaseline) {

		List<String> insert;// id_productrelease

		insert = generateInsertForLOCCustomization(cust, coreAssetBaseline);

		return insert;
	}

	private List<String> generateInsertForLOCCustomization(Customization cust, CoreAssetBaseline coreAssetBaseline) {
		List<String> insert = new ArrayList<>();// id_productrelease
		String custom_header = "INSERT INTO customization_fact (idcustomization, lines_added,lines_deleted,"
				+ "custom_diff,commit_set,message_set,greater_diff"
				+ ",idvariationpoint,id_developer_group, idproductrelease, type) VALUES\n";

		int idVariationPoint;
		if (cust.getProductasset().getIsNewAsset()) {
			idVariationPoint = cust.getVariationpointpa().getIdVP();
		} else {
			idVariationPoint = cust.getVariationpointpa().getIdVP();
		}

		if (cust.getType().name().equals("CHANGE_IN_VARIATION_POINT_BODY")
				|| (cust.getType().name().equals("CHANGE_OUTSIDE_VARIATION_POINT"))) {
			idVariationPoint = cust.getVariationpointpa().getIdVP();
		}

		// There is a new Vpoint
		if (cust.getType().name().equals("NEW_VARIATION_POINT")
				|| cust.getType().name().equals("MODIFIED_VARIATION_POINT_EXPRESSION")
				|| cust.getType().name().equals("NEW_VARIATION_POINT_WITH_NEW_FEATURES")) {
			idVariationPoint = cust.getVariationpointpa().getIdVP();
		} else if (cust.getType().name().equals("NEW_ASSET_WITH_NO_VARIATIONPOINT")) {// introduce fake VP with no
																						// expression
			System.out.println("NEW NEW_ASSET_WITH_NO_VARIATIONPOINT");
			String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group, start_line) VALUES\n";
			insert.add(headerVP.concat("(" + cust.getVariationpointpa().getIdVP() + ",'No Expression',"
					+ cust.getProductasset().getId() + ",0,"+cust.getVariationpointpa().getLineInit())+";\n");

		}

		// if (cust.getType().name().equals("REMOVE_VARIATION_POINT"))
		// idVariationPoint = cust.getVariationpointca().getIdVP();
		// if
		// (cust.getType().name().equals("MODIFIED_VARIATION_POINT_EXPRESSION_WITH_NEWFEATURES"))
		// if (cust.getType().name().equals("NEW_ASSET_WITH_VARIATIONPOINTS"))
		// if
		// (cust.getType().name().equals("NEW_ASSET_WITH_NEW_VARIATIONPOINT_AND_NEW_FEATURES"))
		String b = custom_header +
				"(" + cust.getId() + "," + cust.getLinesAdded() + "," + cust.getLinesDeleted() + ",'" +
				encodeToBase64(cust.getCustomdiff()) + "','" + cust.getCommitShas() + "','" +
				encodeToBase64(cust.getCommitMessagesToString()) + "',''," +
				idVariationPoint + "," + developerGroup + ",'" + coreAssetBaseline.getId() + "','" +
				cust.getType().name() + "');\n";
		insert.add(b);

		return insert;
	}


	private String generateInsertsForDevelopers() {
		String insert = "";
		String header = "INSERT INTO developer (iddeveloper,name,email) VALUES\n";
		Iterator<Developer> devs = CustomDiff.allDevelopers.iterator();
		Developer dev;

		while (devs.hasNext()) {
			dev = devs.next();
			insert = insert.concat(header)
					.concat("(" + dev.getId() + ",'" + dev.getName() + "','" + dev.getEmail() + "');\n");
		}
		return insert;
	}

	private List<String> generateInsertsForProductsReleasesAndCustomizations() {

		return getInsertsForReleases(CustomDiff.spl.getCoreAssetBaseline(1));
	}

	private List<String> getInsertsForReleases(CoreAssetBaseline coreAssetBaseline) {
		List<String> insert = new ArrayList<>();// id_productrelease
		String releaseHeader = "INSERT INTO product_release (idproductrelease,name,date,commits_set) VALUES\n";// id_product
		insert.add((releaseHeader)
				.concat("('" + coreAssetBaseline.getId() + "','" + coreAssetBaseline.getId() + "','"
						+ convertDateToMysqlForm(coreAssetBaseline.getReleaseDate()) + "','"
						+ coreAssetBaseline.getRevCommit().toString() + "');\n"));
		/*
		 * Iterator<ProductRelease> releases =
		 * coreAssetBaseline.getReleases().iterator(); ProductRelease pr; while
		 * (releases.hasNext()) { pr = releases.next(); insert =
		 * insert.concat(releaseHeader).concat("(" + pr.getId() + ",'" + pr.getTagName()
		 * + "','" + convertDateToMysqlForm(pr.getReleaseDate()) + "','" +
		 * pr.getCommitsSetToString() + "');\n");// inserts // for // product // release
		 * // +pr.getFromProduct().getId()+
		 */

		 insert.addAll(getInsertsForCustomizations(coreAssetBaseline));

		return insert;
	}

	private String generateInsertsForFeatures() {
		String insert = "";

		String header_parent = "INSERT INTO parent_feature (idparentfeature,name) VALUES\n";
		String header = "INSERT INTO feature (idfeature,name,isNew,idparent) VALUES\n";
		// empty feature- for customizations with no VP
		String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
		String header_f_group = "INSERT INTO feature_group (id_feature_group, feature_expression) VALUES\n";

		insert = insert.concat(header_parent).concat("(0,'No Feature');\n");
		insert = insert.concat(header).concat("('No Feature','No Feature',0,0);\n");// no feature
		insert = insert.concat(header_f_group).concat("(0, ' No feature');\n"); // group for no feature
		insert = insert.concat(header_bridge).concat("(0,'No Feature');\n");

		Iterator<Feature> it = CustomDiff.features.iterator();
		Feature f;
		int idParent;
		while (it.hasNext()) {
			f = it.next();
			addedFeatures.add(f);
			if (addedParentFeatures.containsValue(f.getParentFeatureName())) {// if it is already in inserted in the db

				idParent = Utils.getKeyFromValue(addedParentFeatures, f.getParentFeatureName());
				System.out.println("Parent " + f.getParentFeatureName() + " already inserted with id: " + idParent);
			} else {// insert the parent_feature first
				insert = insert.concat(header_parent)
						.concat("(" + idParentFeature + ",'" + f.getParentFeatureName() + "');\n");
				idParent = idParentFeature;
				addedParentFeatures.put(idParentFeature, f.getParentFeatureName());
				idParentFeature++;
			}

			insert = insert.concat(header).concat(
					"('" + f.getIdFeature() + "','" + f.getName() + "'," + f.getIsNewToInt() + "," + idParent + ");\n");

		}

		return insert;
	}

	private String generateInsertsForVariationPointsAndFeatureGroups() {
		String insert = "";

		String headerVP = "INSERT INTO variation_point (idvariationpoint, expression, idcoreasset,id_feature_group, body, lines_number, start_line) VALUES\n";
		String header_bridge = "INSERT INTO feature_bridge (id_feature_group, id_feature) VALUES\n";
		String header_f_group = "INSERT INTO feature_group (id_feature_group, feature_expression) VALUES\n";

		insert = insert.concat(headerVP).concat("(-1,'',-1, 0,'',0 ,0);\n");

		List<SourceCodeFile> coreassets = CustomDiff.spl.getCoreAssetBaseline(1).getCoreAssetFiles();
		List<FeatureSibling> featureSiblings = CustomDiff.spl.getCoreAssetBaseline(1).getFeatureSiblings();

		Iterator<SourceCodeFile> it = coreassets.iterator();
		SourceCodeFile ca;
		List<VariationPoint> vps;
		Iterator<VariationPoint> ite;
		VariationPoint vp;
		Iterator<String> features;
		for (FeatureSibling featureSibling : featureSiblings) {
			insert = insert.concat(header_f_group)
					.concat("(" + featureSibling.getId() + ",'" + featureSibling.getFeatureExpression() + "');\n"); // feature_group
			features = featureSibling.getFeatures().iterator();
			while (features.hasNext()) {
				insert = insert.concat(header_bridge)
						.concat("(" + featureSibling.getId() + ",'" + features.next() + "');\n");// feature bridge
			}
		}
		int vpLines;
		while (it.hasNext()) {// if vp.
			ca = it.next();
			vps = ca.getVariationPoints();
			if (vps == null) {
				continue;
			}
			ite = vps.iterator();
			while (ite.hasNext()) {
				vp = ite.next();

				// System.out.print("\nVp in file:"+ca.getFileName()+" has vp
				// "+vp.getVPFullExpression(vp) +" with features:\n");
				vpLines = (vp.getLineEnd() - vp.getLineInit() + 1) >= 0 ? (vp.getLineEnd() - vp.getLineInit() + 1) : 1;
				try {
					insert = insert.concat(headerVP)
							.concat("(" + vp.getIdVP() + ",'" + encodeToBase64(vp.getVPFullExpression(vp)) + "',"
									+ ca.getId() + "," + vp.getFeatureSibling().getId() + ",'"
									+ encodeToBase64(vp.getBody()) + "'," + vpLines +","+vp.getLineInit()+ ");\n");// add vp;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return insert;
	}

	private String generateInsertsForCoreAssets() {
		StringBuilder insert = new StringBuilder();
		String header = "INSERT INTO core_asset (idcoreasset, name, path,size,isNewAsset,idpackage,content) VALUES\n";

		List<SourceCodeFile> coreassets = CustomDiff.spl.getCoreAssetBaseline(1).getCoreAssetFiles();
		Iterator<SourceCodeFile> it = coreassets.iterator();
		SourceCodeFile ca;
		try {
			String package_name;
			int idPack;
			while (it.hasNext()) {
				ca = it.next();
				package_name = getComponentPackageForCoreAsset(ca);
				if (packages.containsValue(package_name)) {
					idPack = getKeyByValue(package_name);
				} else {
					insert.append(getInsertsForComponentPackage(package_name));
					idPack = this.idPackage - 1;
				}

				insert = new StringBuilder(insert.toString().concat(header).concat("(" + ca.getId() + ",'" + ca.getFileName() + "','" + ca.getPath()
						+ "'," + ca.getTotalLines() + "," + "0" + "," + idPack + ",'" + encodeToBase64(ca.getContent()) + "');\n"));

			}
			insert = new StringBuilder(insert.toString().concat(header).concat("(-1,'','',0,0,1,'');\n"));

			System.out.println("Done generateInsertsForCoreAssets");
			return insert.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return insert.toString();
	}

	private String getInsertsForComponentPackage(String component_name) {
		String insert = "";
		String header = "INSERT INTO component_package (idpackage, name, isroot) VALUES\n";

		this.packages.put(idPackage, component_name);

		int root = 0;
		if (component_name.equals(CustomDiff.componentPackageRoot)) {
			root = 1;
		}

		insert = insert.concat(header).concat("(" + idPackage + "," + "'" + component_name + "'," + root + ");\n");

		this.idPackage++;
		return insert;
	}

	private int getKeyByValue(String component) {
		for (Entry<Integer, String> entry : packages.entrySet()) {
			if (component.equals(entry.getValue())) {
				System.out.println("component: " + component + "  Identified! with key:" + entry.getKey());
				return entry.getKey();
			}
		}
		return 0;
	}

	private String getComponentPackageForCoreAsset(SourceCodeFile ca) {
		String component_package_name;
		String ca_path = ca.getPath();
		String ca_name = ca.getFileName();

		String[] splitted = ca_path.split(CustomDiff.componentPackageRoot);
		System.out.println("name:" + ca_path);
		String[] compontent = splitted[1].split("/");
		if (compontent.length > 1) {
			System.out.println("component: " + compontent[1]);

			component_package_name = compontent[1];
		} else {
			component_package_name = ca_name;
		}
		if (component_package_name.equals(ca_name)) {
			component_package_name = CustomDiff.componentPackageRoot;// it belongs to the root folder, it does not have
		} // a component attached.

		return component_package_name;
	}

	private String convertDateToMysqlForm(Date date) {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(date);
	}

	private String encodeToBase64(String str) {
		// encode data on your side using BASE64
		// https://stackoverflow.com/questions/19743851/base64-java-encode-and-decode-a-string
		if (str == null) {
			return null;
		}

		return Base64.encodeBytes(str.getBytes());
	}

}
