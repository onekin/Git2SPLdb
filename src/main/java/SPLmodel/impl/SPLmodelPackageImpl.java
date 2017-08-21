/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAsset;
import SPLmodel.CoreAssetBaseline;
import SPLmodel.CustomizationEffort;
import SPLmodel.Feature;
import SPLmodel.Product;
import SPLmodel.ProductAsset;
import SPLmodel.ProductPortfolio;
import SPLmodel.ProductRelease;
import SPLmodel.SPLmodelFactory;
import SPLmodel.SPLmodelPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPLmodelPackageImpl extends EPackageImpl implements SPLmodelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass splEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coreAssetBaselineEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass coreAssetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customizationEffortEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productPortfolioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productReleaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass productAssetEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see SPLmodel.SPLmodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SPLmodelPackageImpl() {
		super(eNS_URI, SPLmodelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link SPLmodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SPLmodelPackage init() {
		if (isInited) return (SPLmodelPackage)EPackage.Registry.INSTANCE.getEPackage(SPLmodelPackage.eNS_URI);

		// Obtain or create and register package
		SPLmodelPackageImpl theSPLmodelPackage = (SPLmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof SPLmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new SPLmodelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theSPLmodelPackage.createPackageContents();

		// Initialize created meta-data
		theSPLmodelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSPLmodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SPLmodelPackage.eNS_URI, theSPLmodelPackage);
		return theSPLmodelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSPL() {
		return splEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSPL_IdSPL() {
		return (EAttribute)splEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSPL_Baselines() {
		return (EReference)splEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSPL_Portfolio() {
		return (EReference)splEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoreAssetBaseline() {
		return coreAssetBaselineEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAssetBaseline_IdBaseline() {
		return (EAttribute)coreAssetBaselineEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAssetBaseline_ReleaseDate() {
		return (EAttribute)coreAssetBaselineEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreAssetBaseline_ProductPortfolio() {
		return (EReference)coreAssetBaselineEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreAssetBaseline_Coreasset() {
		return (EReference)coreAssetBaselineEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreAssetBaseline_Feature() {
		return (EReference)coreAssetBaselineEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeature() {
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeature_IdFeature() {
		return (EAttribute)featureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFeature_Name() {
		return (EAttribute)featureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeature_Coreassetbaseline() {
		return (EReference)featureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeature_CoreAssets() {
		return (EReference)featureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCoreAsset() {
		return coreAssetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAsset_IdCoreAsset() {
		return (EAttribute)coreAssetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAsset_Name() {
		return (EAttribute)coreAssetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAsset_Path() {
		return (EAttribute)coreAssetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAsset_Content() {
		return (EAttribute)coreAssetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCoreAsset_Size() {
		return (EAttribute)coreAssetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCoreAsset_MappedTo() {
		return (EReference)coreAssetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomizationEffort() {
		return customizationEffortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomizationEffort_IdCustomization() {
		return (EAttribute)customizationEffortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomizationEffort_Added_LOCs() {
		return (EAttribute)customizationEffortEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCustomizationEffort_Deleted_LOCs() {
		return (EAttribute)customizationEffortEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomizationEffort_CustCA() {
		return (EReference)customizationEffortEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomizationEffort_Productasset() {
		return (EReference)customizationEffortEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductPortfolio() {
		return productPortfolioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductPortfolio_IdProductPortfolio() {
		return (EAttribute)productPortfolioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductPortfolio_DerivedFrom() {
		return (EReference)productPortfolioEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductPortfolio_Products() {
		return (EReference)productPortfolioEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProduct() {
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProduct_IdProduct() {
		return (EAttribute)productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProduct_Name() {
		return (EAttribute)productEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProduct_InPortfolio() {
		return (EReference)productEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProduct_Releases() {
		return (EReference)productEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductRelease() {
		return productReleaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductRelease_IdRelease() {
		return (EAttribute)productReleaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductRelease_ReleaseDate() {
		return (EAttribute)productReleaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductRelease_Product() {
		return (EReference)productReleaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductRelease_Productasset() {
		return (EReference)productReleaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProductAsset() {
		return productAssetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductAsset_IdAsset() {
		return (EAttribute)productAssetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductAsset_Name() {
		return (EAttribute)productAssetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductAsset_Path() {
		return (EAttribute)productAssetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductAsset_Content() {
		return (EAttribute)productAssetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProductAsset_Size() {
		return (EAttribute)productAssetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getProductAsset_FromRelease() {
		return (EReference)productAssetEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPLmodelFactory getSPLmodelFactory() {
		return (SPLmodelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		splEClass = createEClass(SPL);
		createEAttribute(splEClass, SPL__ID_SPL);
		createEReference(splEClass, SPL__BASELINES);
		createEReference(splEClass, SPL__PORTFOLIO);

		coreAssetBaselineEClass = createEClass(CORE_ASSET_BASELINE);
		createEAttribute(coreAssetBaselineEClass, CORE_ASSET_BASELINE__ID_BASELINE);
		createEAttribute(coreAssetBaselineEClass, CORE_ASSET_BASELINE__RELEASE_DATE);
		createEReference(coreAssetBaselineEClass, CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO);
		createEReference(coreAssetBaselineEClass, CORE_ASSET_BASELINE__COREASSET);
		createEReference(coreAssetBaselineEClass, CORE_ASSET_BASELINE__FEATURE);

		featureEClass = createEClass(FEATURE);
		createEAttribute(featureEClass, FEATURE__ID_FEATURE);
		createEAttribute(featureEClass, FEATURE__NAME);
		createEReference(featureEClass, FEATURE__COREASSETBASELINE);
		createEReference(featureEClass, FEATURE__CORE_ASSETS);

		coreAssetEClass = createEClass(CORE_ASSET);
		createEAttribute(coreAssetEClass, CORE_ASSET__ID_CORE_ASSET);
		createEAttribute(coreAssetEClass, CORE_ASSET__NAME);
		createEAttribute(coreAssetEClass, CORE_ASSET__PATH);
		createEAttribute(coreAssetEClass, CORE_ASSET__CONTENT);
		createEAttribute(coreAssetEClass, CORE_ASSET__SIZE);
		createEReference(coreAssetEClass, CORE_ASSET__MAPPED_TO);

		customizationEffortEClass = createEClass(CUSTOMIZATION_EFFORT);
		createEAttribute(customizationEffortEClass, CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION);
		createEAttribute(customizationEffortEClass, CUSTOMIZATION_EFFORT__ADDED_LO_CS);
		createEAttribute(customizationEffortEClass, CUSTOMIZATION_EFFORT__DELETED_LO_CS);
		createEReference(customizationEffortEClass, CUSTOMIZATION_EFFORT__CUST_CA);
		createEReference(customizationEffortEClass, CUSTOMIZATION_EFFORT__PRODUCTASSET);

		productPortfolioEClass = createEClass(PRODUCT_PORTFOLIO);
		createEAttribute(productPortfolioEClass, PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO);
		createEReference(productPortfolioEClass, PRODUCT_PORTFOLIO__DERIVED_FROM);
		createEReference(productPortfolioEClass, PRODUCT_PORTFOLIO__PRODUCTS);

		productEClass = createEClass(PRODUCT);
		createEAttribute(productEClass, PRODUCT__ID_PRODUCT);
		createEAttribute(productEClass, PRODUCT__NAME);
		createEReference(productEClass, PRODUCT__IN_PORTFOLIO);
		createEReference(productEClass, PRODUCT__RELEASES);

		productReleaseEClass = createEClass(PRODUCT_RELEASE);
		createEAttribute(productReleaseEClass, PRODUCT_RELEASE__ID_RELEASE);
		createEAttribute(productReleaseEClass, PRODUCT_RELEASE__RELEASE_DATE);
		createEReference(productReleaseEClass, PRODUCT_RELEASE__PRODUCT);
		createEReference(productReleaseEClass, PRODUCT_RELEASE__PRODUCTASSET);

		productAssetEClass = createEClass(PRODUCT_ASSET);
		createEAttribute(productAssetEClass, PRODUCT_ASSET__ID_ASSET);
		createEAttribute(productAssetEClass, PRODUCT_ASSET__NAME);
		createEAttribute(productAssetEClass, PRODUCT_ASSET__PATH);
		createEAttribute(productAssetEClass, PRODUCT_ASSET__CONTENT);
		createEAttribute(productAssetEClass, PRODUCT_ASSET__SIZE);
		createEReference(productAssetEClass, PRODUCT_ASSET__FROM_RELEASE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(splEClass, SPLmodel.SPL.class, "SPL", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSPL_IdSPL(), ecorePackage.getEString(), "idSPL", null, 0, 1, SPLmodel.SPL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSPL_Baselines(), this.getCoreAssetBaseline(), null, "baselines", null, 0, -1, SPLmodel.SPL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSPL_Portfolio(), this.getProductPortfolio(), null, "portfolio", null, 0, -1, SPLmodel.SPL.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(coreAssetBaselineEClass, CoreAssetBaseline.class, "CoreAssetBaseline", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCoreAssetBaseline_IdBaseline(), ecorePackage.getEString(), "idBaseline", null, 0, 1, CoreAssetBaseline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCoreAssetBaseline_ReleaseDate(), ecorePackage.getEString(), "ReleaseDate", null, 0, 1, CoreAssetBaseline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCoreAssetBaseline_ProductPortfolio(), this.getProductPortfolio(), this.getProductPortfolio_DerivedFrom(), "productPortfolio", null, 0, 1, CoreAssetBaseline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCoreAssetBaseline_Coreasset(), this.getCoreAsset(), null, "coreasset", null, 0, -1, CoreAssetBaseline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCoreAssetBaseline_Feature(), this.getFeature(), this.getFeature_Coreassetbaseline(), "feature", null, 0, -1, CoreAssetBaseline.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFeature_IdFeature(), ecorePackage.getEString(), "idFeature", null, 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFeature_Name(), ecorePackage.getEString(), "name", "", 0, 1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_Coreassetbaseline(), this.getCoreAssetBaseline(), this.getCoreAssetBaseline_Feature(), "coreassetbaseline", null, 0, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_CoreAssets(), this.getCoreAsset(), this.getCoreAsset_MappedTo(), "coreAssets", null, 1, -1, Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(coreAssetEClass, CoreAsset.class, "CoreAsset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCoreAsset_IdCoreAsset(), ecorePackage.getEString(), "idCoreAsset", null, 0, 1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCoreAsset_Name(), ecorePackage.getEString(), "name", null, 0, 1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCoreAsset_Path(), ecorePackage.getEString(), "path", null, 0, 1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCoreAsset_Content(), ecorePackage.getEString(), "content", null, 0, 1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCoreAsset_Size(), ecorePackage.getEInt(), "size", null, 0, 1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCoreAsset_MappedTo(), this.getFeature(), this.getFeature_CoreAssets(), "mappedTo", null, 1, -1, CoreAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customizationEffortEClass, CustomizationEffort.class, "CustomizationEffort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCustomizationEffort_IdCustomization(), ecorePackage.getEInt(), "idCustomization", null, 0, 1, CustomizationEffort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomizationEffort_Added_LOCs(), ecorePackage.getEInt(), "added_LOCs", null, 0, 1, CustomizationEffort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCustomizationEffort_Deleted_LOCs(), ecorePackage.getEInt(), "deleted_LOCs", null, 0, 1, CustomizationEffort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomizationEffort_CustCA(), this.getCoreAsset(), null, "custCA", null, 0, 1, CustomizationEffort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCustomizationEffort_Productasset(), this.getProductAsset(), null, "productasset", null, 0, 1, CustomizationEffort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productPortfolioEClass, ProductPortfolio.class, "ProductPortfolio", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductPortfolio_IdProductPortfolio(), ecorePackage.getEString(), "idProductPortfolio", null, 0, 1, ProductPortfolio.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductPortfolio_DerivedFrom(), this.getCoreAssetBaseline(), this.getCoreAssetBaseline_ProductPortfolio(), "derivedFrom", null, 0, 1, ProductPortfolio.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductPortfolio_Products(), this.getProduct(), this.getProduct_InPortfolio(), "products", null, 0, -1, ProductPortfolio.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProduct_IdProduct(), ecorePackage.getEString(), "idProduct", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProduct_Name(), ecorePackage.getEString(), "name", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_InPortfolio(), this.getProductPortfolio(), this.getProductPortfolio_Products(), "inPortfolio", null, 0, 1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_Releases(), this.getProductRelease(), this.getProductRelease_Product(), "releases", null, 0, -1, Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productReleaseEClass, ProductRelease.class, "ProductRelease", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductRelease_IdRelease(), ecorePackage.getEString(), "idRelease", null, 0, 1, ProductRelease.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductRelease_ReleaseDate(), ecorePackage.getEString(), "releaseDate", null, 0, 1, ProductRelease.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductRelease_Product(), this.getProduct(), this.getProduct_Releases(), "product", null, 0, 1, ProductRelease.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductRelease_Productasset(), this.getProductAsset(), this.getProductAsset_FromRelease(), "productasset", null, 0, -1, ProductRelease.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productAssetEClass, ProductAsset.class, "ProductAsset", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductAsset_IdAsset(), ecorePackage.getEString(), "idAsset", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductAsset_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductAsset_Path(), ecorePackage.getEString(), "path", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductAsset_Content(), ecorePackage.getEString(), "content", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductAsset_Size(), ecorePackage.getEInt(), "size", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProductAsset_FromRelease(), this.getProductRelease(), this.getProductRelease_Productasset(), "fromRelease", null, 0, 1, ProductAsset.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //SPLmodelPackageImpl
