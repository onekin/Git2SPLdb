package metamodel;

import java.util.Map;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

import SPLmodel.CoreAsset;
import SPLmodel.CoreAssetBaseline;
import SPLmodel.CustomizationEffort;
import SPLmodel.Feature;
import SPLmodel.Product;
import SPLmodel.ProductAsset;
import SPLmodel.ProductPortfolio;
import SPLmodel.ProductRelease;
import SPLmodel.SPL;
import SPLmodel.SPLmodelFactory;
import SPLmodel.SPLmodelPackage;
import SPLmodel.impl.SPLmodelPackageImpl;
import preprocessing.Main;



public class ZPruebaPopulateModel {

	public static void main(String[] args) {
		
		SPLmodelPackage.eINSTANCE.eClass(); //SPLmodelPackage.eINSTANCE
		SPLmodelFactory factory = SPLmodelFactory.eINSTANCE;
		
		
		SPL mySPL = factory.createSPL(); // new SPLImpl();
		
		/**Añadir a mano ejemplo*/
		
		mySPL.setIdSPL("TestSPL");
		
		
		CoreAssetBaseline CAB = factory.createCoreAssetBaseline();//new CoreAssetBaselineImpl();
		CAB.setIdBaseline("Baseline-v1.0");
		CAB.setReleaseDate("2017, 08, 01");
		
		CoreAssetBaseline CAB2 = factory.createCoreAssetBaseline();//new CoreAssetBaselineImpl();
		CAB.setIdBaseline("Baseline-v2.0");
		CAB.setReleaseDate("2017, 10, 01");
		
		Feature featureA = factory.createFeature();//new FeatureImpl();
		featureA.setIdFeature("FeatureA");
		featureA.setName("FeatureA");
		featureA.getCoreassetbaseline().add(CAB);
		CAB.getFeature().add(featureA);
		
		Feature featureB = factory.createFeature();//new FeatureImpl();
		featureB.setIdFeature("featureB");
		featureB.setName("featureB");
		featureB.getCoreassetbaseline().add(CAB);
		CAB.getFeature().add(featureB);
		 
		
		 CoreAsset ca = factory.createCoreAsset();//new CoreAssetImpl();
		 ca.setIdCoreAsset("file1.txt");
		 ca.setName("file1.txt");
		 ca.setPath("dasdasd/dadasdad");
		 ca.setContent("this is the content\n of file1.txt");
		 ca.setSize(2);
		 
		 CoreAsset ca2 = factory.createCoreAsset(); //new CoreAssetImpl();
		 ca2.setIdCoreAsset("file2.txt");
		 ca2.setName("file21.txt");
		 ca2.setPath("dasdasd/dadasdad");
		 ca2.setContent("this is the content\n of file2.txt");
		 ca2.setSize(100);
		 
		 
		 CAB.getCoreasset().add(ca);
		 CAB.getCoreasset().add(ca2);
		
		 ProductAsset pa = factory.createProductAsset();//new ProductAssetImpl();
		 pa.setIdAsset("file1.txt");
		 pa.setName("file1.txt");
		 pa.setPath("dasdasd/dadasdad");
		 pa.setContent("this is the content\n of file1.txt |n for product-berlin");
		 pa.setSize(3);
		
		 CustomizationEffort custEff = factory.createCustomizationEffort();//new CustomizationEffortImpl();
		 custEff.setIdCustomization(1);
		 custEff.setAdded_LOCs(1);
		 custEff.setDeleted_LOCs(0);
		 custEff.setProductasset(pa);
		 custEff.setCoreAsset(ca);
		 
		 ProductPortfolio pp = factory.createProductPortfolio(); //new ProductPortfolioImpl();
		 pp.setDerivedFrom(CAB);
		 pp.setIdProductPortfolio("series1");
		 
		 Product p = factory.createProduct();//new ProductImpl();
		 p.setIdProduct("product-berlin");
		 p.setName("product-berlin");
		 p.setInPortfolio(pp);
		 
		 
		 ProductRelease pr = factory.createProductRelease();//new ProductReleaseImpl();
		 pr.setIdRelease("PB-v1.0");
		 pr.setReleaseDate("2017-AGOS-02");
		 pr.setProduct(p);
		 pa.setFromRelease(pr);
		 
		 //Adding portfolio and core asset baselines to SPL
		 mySPL.getBaselines().add(CAB);
		 mySPL.getBaselines().add(CAB2);
		 mySPL.getPortfolio().add(pp);
		 
		 
		 
		 /****************** SERIALIZE the MODEL********************/

	        // As of here we preparing to save the model content

	        // Register the XMI resource factory for the .splmodel extension

		 	SPLmodelPackageImpl.init();
		 
	        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
	        Map<String, Object> m = reg.getExtensionToFactoryMap();
	        
	        m.put("splmodel", new XMLResourceFactoryImpl());

	        // Obtain a new resource set
	        ResourceSet resSet = new ResourceSetImpl();
	        

	        // create a resource/spl-data
	        Resource resource = resSet.createResource(URI.createURI(Main.pathToResources+"/spl-data/MySPL.splmodel"));
	        // Get the first model element and cast it to the right type, in my
	        // example everything is hierarchical included in this first node
	        resource.getContents().add(mySPL);
	        resource.getContents().add(featureA);
	        resource.getContents().add(featureB);
	        resource.getContents().add(p);

	        resource.getContents().add(pp);
	        resource.getContents().add(pr);
	        resource.getContents().add(ca);
	        resource.getContents().add(ca2);
	        resource.getContents().add(pa);
	        resource.getContents().add(custEff);
	        

	        // now save the content.
	        try {
	         
	            resource.save(Collections.EMPTY_MAP);
	            //save it
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        // Transform the model
	      Iterator<EObject>  it = resource.getContents().listIterator();
	      while (it.hasNext()){
	    	  System.out.println(it.next().toString());  
	      }
			

	    }
}


