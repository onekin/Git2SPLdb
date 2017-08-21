/**
 */
package SPLmodel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see SPLmodel.SPLmodelFactory
 * @model kind="package"
 * @generated
 */
public interface SPLmodelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "SPLmodel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "customs";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "spl_custom";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SPLmodelPackage eINSTANCE = SPLmodel.impl.SPLmodelPackageImpl.init();

	/**
	 * The meta object id for the '{@link SPLmodel.impl.SPLImpl <em>SPL</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.SPLImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getSPL()
	 * @generated
	 */
	int SPL = 0;

	/**
	 * The feature id for the '<em><b>Id SPL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__ID_SPL = 0;

	/**
	 * The feature id for the '<em><b>Baselines</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__BASELINES = 1;

	/**
	 * The feature id for the '<em><b>Portfolio</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL__PORTFOLIO = 2;

	/**
	 * The number of structural features of the '<em>SPL</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>SPL</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.CoreAssetBaselineImpl <em>Core Asset Baseline</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.CoreAssetBaselineImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getCoreAssetBaseline()
	 * @generated
	 */
	int CORE_ASSET_BASELINE = 1;

	/**
	 * The feature id for the '<em><b>Id Baseline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE__ID_BASELINE = 0;

	/**
	 * The feature id for the '<em><b>Release Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE__RELEASE_DATE = 1;

	/**
	 * The feature id for the '<em><b>Product Portfolio</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO = 2;

	/**
	 * The feature id for the '<em><b>Coreasset</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE__COREASSET = 3;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE__FEATURE = 4;

	/**
	 * The number of structural features of the '<em>Core Asset Baseline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Core Asset Baseline</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_BASELINE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.FeatureImpl <em>Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.FeatureImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 2;

	/**
	 * The feature id for the '<em><b>Id Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__ID_FEATURE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Coreassetbaseline</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__COREASSETBASELINE = 2;

	/**
	 * The feature id for the '<em><b>Core Assets</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE__CORE_ASSETS = 3;

	/**
	 * The number of structural features of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.CoreAssetImpl <em>Core Asset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.CoreAssetImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getCoreAsset()
	 * @generated
	 */
	int CORE_ASSET = 3;

	/**
	 * The feature id for the '<em><b>Id Core Asset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__ID_CORE_ASSET = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__NAME = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__PATH = 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__CONTENT = 3;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__SIZE = 4;

	/**
	 * The feature id for the '<em><b>Mapped To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET__MAPPED_TO = 5;

	/**
	 * The number of structural features of the '<em>Core Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Core Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_ASSET_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.CustomizationEffortImpl <em>Customization Effort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.CustomizationEffortImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getCustomizationEffort()
	 * @generated
	 */
	int CUSTOMIZATION_EFFORT = 4;

	/**
	 * The feature id for the '<em><b>Id Customization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION = 0;

	/**
	 * The feature id for the '<em><b>Added LO Cs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT__ADDED_LO_CS = 1;

	/**
	 * The feature id for the '<em><b>Deleted LO Cs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT__DELETED_LO_CS = 2;

	/**
	 * The feature id for the '<em><b>Cust CA</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT__CUST_CA = 3;

	/**
	 * The feature id for the '<em><b>Productasset</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT__PRODUCTASSET = 4;

	/**
	 * The number of structural features of the '<em>Customization Effort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Customization Effort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOMIZATION_EFFORT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.ProductPortfolioImpl <em>Product Portfolio</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.ProductPortfolioImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductPortfolio()
	 * @generated
	 */
	int PRODUCT_PORTFOLIO = 5;

	/**
	 * The feature id for the '<em><b>Id Product Portfolio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO = 0;

	/**
	 * The feature id for the '<em><b>Derived From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_PORTFOLIO__DERIVED_FROM = 1;

	/**
	 * The feature id for the '<em><b>Products</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_PORTFOLIO__PRODUCTS = 2;

	/**
	 * The number of structural features of the '<em>Product Portfolio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_PORTFOLIO_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Product Portfolio</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_PORTFOLIO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.ProductImpl <em>Product</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.ProductImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 6;

	/**
	 * The feature id for the '<em><b>Id Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ID_PRODUCT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = 1;

	/**
	 * The feature id for the '<em><b>In Portfolio</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__IN_PORTFOLIO = 2;

	/**
	 * The feature id for the '<em><b>Releases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT__RELEASES = 3;

	/**
	 * The number of structural features of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Product</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.ProductReleaseImpl <em>Product Release</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.ProductReleaseImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductRelease()
	 * @generated
	 */
	int PRODUCT_RELEASE = 7;

	/**
	 * The feature id for the '<em><b>Id Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE__ID_RELEASE = 0;

	/**
	 * The feature id for the '<em><b>Release Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE__RELEASE_DATE = 1;

	/**
	 * The feature id for the '<em><b>Product</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE__PRODUCT = 2;

	/**
	 * The feature id for the '<em><b>Productasset</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE__PRODUCTASSET = 3;

	/**
	 * The number of structural features of the '<em>Product Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Product Release</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_RELEASE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link SPLmodel.impl.ProductAssetImpl <em>Product Asset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see SPLmodel.impl.ProductAssetImpl
	 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductAsset()
	 * @generated
	 */
	int PRODUCT_ASSET = 8;

	/**
	 * The feature id for the '<em><b>Id Asset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__ID_ASSET = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__NAME = 1;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__PATH = 2;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__CONTENT = 3;

	/**
	 * The feature id for the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__SIZE = 4;

	/**
	 * The feature id for the '<em><b>From Release</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET__FROM_RELEASE = 5;

	/**
	 * The number of structural features of the '<em>Product Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Product Asset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCT_ASSET_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link SPLmodel.SPL <em>SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SPL</em>'.
	 * @see SPLmodel.SPL
	 * @generated
	 */
	EClass getSPL();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.SPL#getIdSPL <em>Id SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id SPL</em>'.
	 * @see SPLmodel.SPL#getIdSPL()
	 * @see #getSPL()
	 * @generated
	 */
	EAttribute getSPL_IdSPL();

	/**
	 * Returns the meta object for the containment reference list '{@link SPLmodel.SPL#getBaselines <em>Baselines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Baselines</em>'.
	 * @see SPLmodel.SPL#getBaselines()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_Baselines();

	/**
	 * Returns the meta object for the containment reference list '{@link SPLmodel.SPL#getPortfolio <em>Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Portfolio</em>'.
	 * @see SPLmodel.SPL#getPortfolio()
	 * @see #getSPL()
	 * @generated
	 */
	EReference getSPL_Portfolio();

	/**
	 * Returns the meta object for class '{@link SPLmodel.CoreAssetBaseline <em>Core Asset Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Core Asset Baseline</em>'.
	 * @see SPLmodel.CoreAssetBaseline
	 * @generated
	 */
	EClass getCoreAssetBaseline();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAssetBaseline#getIdBaseline <em>Id Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Baseline</em>'.
	 * @see SPLmodel.CoreAssetBaseline#getIdBaseline()
	 * @see #getCoreAssetBaseline()
	 * @generated
	 */
	EAttribute getCoreAssetBaseline_IdBaseline();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAssetBaseline#getReleaseDate <em>Release Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Release Date</em>'.
	 * @see SPLmodel.CoreAssetBaseline#getReleaseDate()
	 * @see #getCoreAssetBaseline()
	 * @generated
	 */
	EAttribute getCoreAssetBaseline_ReleaseDate();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.CoreAssetBaseline#getProductPortfolio <em>Product Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product Portfolio</em>'.
	 * @see SPLmodel.CoreAssetBaseline#getProductPortfolio()
	 * @see #getCoreAssetBaseline()
	 * @generated
	 */
	EReference getCoreAssetBaseline_ProductPortfolio();

	/**
	 * Returns the meta object for the containment reference list '{@link SPLmodel.CoreAssetBaseline#getCoreasset <em>Coreasset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Coreasset</em>'.
	 * @see SPLmodel.CoreAssetBaseline#getCoreasset()
	 * @see #getCoreAssetBaseline()
	 * @generated
	 */
	EReference getCoreAssetBaseline_Coreasset();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.CoreAssetBaseline#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Feature</em>'.
	 * @see SPLmodel.CoreAssetBaseline#getFeature()
	 * @see #getCoreAssetBaseline()
	 * @generated
	 */
	EReference getCoreAssetBaseline_Feature();

	/**
	 * Returns the meta object for class '{@link SPLmodel.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see SPLmodel.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.Feature#getIdFeature <em>Id Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Feature</em>'.
	 * @see SPLmodel.Feature#getIdFeature()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_IdFeature();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.Feature#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SPLmodel.Feature#getName()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Name();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.Feature#getCoreassetbaseline <em>Coreassetbaseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Coreassetbaseline</em>'.
	 * @see SPLmodel.Feature#getCoreassetbaseline()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Coreassetbaseline();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.Feature#getCoreAssets <em>Core Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Core Assets</em>'.
	 * @see SPLmodel.Feature#getCoreAssets()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_CoreAssets();

	/**
	 * Returns the meta object for class '{@link SPLmodel.CoreAsset <em>Core Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Core Asset</em>'.
	 * @see SPLmodel.CoreAsset
	 * @generated
	 */
	EClass getCoreAsset();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAsset#getIdCoreAsset <em>Id Core Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Core Asset</em>'.
	 * @see SPLmodel.CoreAsset#getIdCoreAsset()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EAttribute getCoreAsset_IdCoreAsset();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAsset#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SPLmodel.CoreAsset#getName()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EAttribute getCoreAsset_Name();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAsset#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see SPLmodel.CoreAsset#getPath()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EAttribute getCoreAsset_Path();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAsset#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see SPLmodel.CoreAsset#getContent()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EAttribute getCoreAsset_Content();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CoreAsset#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see SPLmodel.CoreAsset#getSize()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EAttribute getCoreAsset_Size();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.CoreAsset#getMappedTo <em>Mapped To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mapped To</em>'.
	 * @see SPLmodel.CoreAsset#getMappedTo()
	 * @see #getCoreAsset()
	 * @generated
	 */
	EReference getCoreAsset_MappedTo();

	/**
	 * Returns the meta object for class '{@link SPLmodel.CustomizationEffort <em>Customization Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Customization Effort</em>'.
	 * @see SPLmodel.CustomizationEffort
	 * @generated
	 */
	EClass getCustomizationEffort();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CustomizationEffort#getIdCustomization <em>Id Customization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Customization</em>'.
	 * @see SPLmodel.CustomizationEffort#getIdCustomization()
	 * @see #getCustomizationEffort()
	 * @generated
	 */
	EAttribute getCustomizationEffort_IdCustomization();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CustomizationEffort#getAdded_LOCs <em>Added LO Cs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Added LO Cs</em>'.
	 * @see SPLmodel.CustomizationEffort#getAdded_LOCs()
	 * @see #getCustomizationEffort()
	 * @generated
	 */
	EAttribute getCustomizationEffort_Added_LOCs();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.CustomizationEffort#getDeleted_LOCs <em>Deleted LO Cs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Deleted LO Cs</em>'.
	 * @see SPLmodel.CustomizationEffort#getDeleted_LOCs()
	 * @see #getCustomizationEffort()
	 * @generated
	 */
	EAttribute getCustomizationEffort_Deleted_LOCs();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.CustomizationEffort#getCustCA <em>Cust CA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cust CA</em>'.
	 * @see SPLmodel.CustomizationEffort#getCustCA()
	 * @see #getCustomizationEffort()
	 * @generated
	 */
	EReference getCustomizationEffort_CustCA();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.CustomizationEffort#getProductasset <em>Productasset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Productasset</em>'.
	 * @see SPLmodel.CustomizationEffort#getProductasset()
	 * @see #getCustomizationEffort()
	 * @generated
	 */
	EReference getCustomizationEffort_Productasset();

	/**
	 * Returns the meta object for class '{@link SPLmodel.ProductPortfolio <em>Product Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Portfolio</em>'.
	 * @see SPLmodel.ProductPortfolio
	 * @generated
	 */
	EClass getProductPortfolio();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductPortfolio#getIdProductPortfolio <em>Id Product Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Product Portfolio</em>'.
	 * @see SPLmodel.ProductPortfolio#getIdProductPortfolio()
	 * @see #getProductPortfolio()
	 * @generated
	 */
	EAttribute getProductPortfolio_IdProductPortfolio();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.ProductPortfolio#getDerivedFrom <em>Derived From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Derived From</em>'.
	 * @see SPLmodel.ProductPortfolio#getDerivedFrom()
	 * @see #getProductPortfolio()
	 * @generated
	 */
	EReference getProductPortfolio_DerivedFrom();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.ProductPortfolio#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Products</em>'.
	 * @see SPLmodel.ProductPortfolio#getProducts()
	 * @see #getProductPortfolio()
	 * @generated
	 */
	EReference getProductPortfolio_Products();

	/**
	 * Returns the meta object for class '{@link SPLmodel.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product</em>'.
	 * @see SPLmodel.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.Product#getIdProduct <em>Id Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Product</em>'.
	 * @see SPLmodel.Product#getIdProduct()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_IdProduct();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.Product#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SPLmodel.Product#getName()
	 * @see #getProduct()
	 * @generated
	 */
	EAttribute getProduct_Name();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.Product#getInPortfolio <em>In Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>In Portfolio</em>'.
	 * @see SPLmodel.Product#getInPortfolio()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_InPortfolio();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.Product#getReleases <em>Releases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Releases</em>'.
	 * @see SPLmodel.Product#getReleases()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Releases();

	/**
	 * Returns the meta object for class '{@link SPLmodel.ProductRelease <em>Product Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Release</em>'.
	 * @see SPLmodel.ProductRelease
	 * @generated
	 */
	EClass getProductRelease();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductRelease#getIdRelease <em>Id Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Release</em>'.
	 * @see SPLmodel.ProductRelease#getIdRelease()
	 * @see #getProductRelease()
	 * @generated
	 */
	EAttribute getProductRelease_IdRelease();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductRelease#getReleaseDate <em>Release Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Release Date</em>'.
	 * @see SPLmodel.ProductRelease#getReleaseDate()
	 * @see #getProductRelease()
	 * @generated
	 */
	EAttribute getProductRelease_ReleaseDate();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.ProductRelease#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Product</em>'.
	 * @see SPLmodel.ProductRelease#getProduct()
	 * @see #getProductRelease()
	 * @generated
	 */
	EReference getProductRelease_Product();

	/**
	 * Returns the meta object for the reference list '{@link SPLmodel.ProductRelease#getProductasset <em>Productasset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Productasset</em>'.
	 * @see SPLmodel.ProductRelease#getProductasset()
	 * @see #getProductRelease()
	 * @generated
	 */
	EReference getProductRelease_Productasset();

	/**
	 * Returns the meta object for class '{@link SPLmodel.ProductAsset <em>Product Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Product Asset</em>'.
	 * @see SPLmodel.ProductAsset
	 * @generated
	 */
	EClass getProductAsset();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductAsset#getIdAsset <em>Id Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id Asset</em>'.
	 * @see SPLmodel.ProductAsset#getIdAsset()
	 * @see #getProductAsset()
	 * @generated
	 */
	EAttribute getProductAsset_IdAsset();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductAsset#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see SPLmodel.ProductAsset#getName()
	 * @see #getProductAsset()
	 * @generated
	 */
	EAttribute getProductAsset_Name();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductAsset#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see SPLmodel.ProductAsset#getPath()
	 * @see #getProductAsset()
	 * @generated
	 */
	EAttribute getProductAsset_Path();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductAsset#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see SPLmodel.ProductAsset#getContent()
	 * @see #getProductAsset()
	 * @generated
	 */
	EAttribute getProductAsset_Content();

	/**
	 * Returns the meta object for the attribute '{@link SPLmodel.ProductAsset#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see SPLmodel.ProductAsset#getSize()
	 * @see #getProductAsset()
	 * @generated
	 */
	EAttribute getProductAsset_Size();

	/**
	 * Returns the meta object for the reference '{@link SPLmodel.ProductAsset#getFromRelease <em>From Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>From Release</em>'.
	 * @see SPLmodel.ProductAsset#getFromRelease()
	 * @see #getProductAsset()
	 * @generated
	 */
	EReference getProductAsset_FromRelease();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SPLmodelFactory getSPLmodelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link SPLmodel.impl.SPLImpl <em>SPL</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.SPLImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getSPL()
		 * @generated
		 */
		EClass SPL = eINSTANCE.getSPL();

		/**
		 * The meta object literal for the '<em><b>Id SPL</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPL__ID_SPL = eINSTANCE.getSPL_IdSPL();

		/**
		 * The meta object literal for the '<em><b>Baselines</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__BASELINES = eINSTANCE.getSPL_Baselines();

		/**
		 * The meta object literal for the '<em><b>Portfolio</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPL__PORTFOLIO = eINSTANCE.getSPL_Portfolio();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.CoreAssetBaselineImpl <em>Core Asset Baseline</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.CoreAssetBaselineImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getCoreAssetBaseline()
		 * @generated
		 */
		EClass CORE_ASSET_BASELINE = eINSTANCE.getCoreAssetBaseline();

		/**
		 * The meta object literal for the '<em><b>Id Baseline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET_BASELINE__ID_BASELINE = eINSTANCE.getCoreAssetBaseline_IdBaseline();

		/**
		 * The meta object literal for the '<em><b>Release Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET_BASELINE__RELEASE_DATE = eINSTANCE.getCoreAssetBaseline_ReleaseDate();

		/**
		 * The meta object literal for the '<em><b>Product Portfolio</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO = eINSTANCE.getCoreAssetBaseline_ProductPortfolio();

		/**
		 * The meta object literal for the '<em><b>Coreasset</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_ASSET_BASELINE__COREASSET = eINSTANCE.getCoreAssetBaseline_Coreasset();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_ASSET_BASELINE__FEATURE = eINSTANCE.getCoreAssetBaseline_Feature();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.FeatureImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Id Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__ID_FEATURE = eINSTANCE.getFeature_IdFeature();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__NAME = eINSTANCE.getFeature_Name();

		/**
		 * The meta object literal for the '<em><b>Coreassetbaseline</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__COREASSETBASELINE = eINSTANCE.getFeature_Coreassetbaseline();

		/**
		 * The meta object literal for the '<em><b>Core Assets</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE__CORE_ASSETS = eINSTANCE.getFeature_CoreAssets();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.CoreAssetImpl <em>Core Asset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.CoreAssetImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getCoreAsset()
		 * @generated
		 */
		EClass CORE_ASSET = eINSTANCE.getCoreAsset();

		/**
		 * The meta object literal for the '<em><b>Id Core Asset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET__ID_CORE_ASSET = eINSTANCE.getCoreAsset_IdCoreAsset();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET__NAME = eINSTANCE.getCoreAsset_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET__PATH = eINSTANCE.getCoreAsset_Path();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET__CONTENT = eINSTANCE.getCoreAsset_Content();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_ASSET__SIZE = eINSTANCE.getCoreAsset_Size();

		/**
		 * The meta object literal for the '<em><b>Mapped To</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_ASSET__MAPPED_TO = eINSTANCE.getCoreAsset_MappedTo();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.CustomizationEffortImpl <em>Customization Effort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.CustomizationEffortImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getCustomizationEffort()
		 * @generated
		 */
		EClass CUSTOMIZATION_EFFORT = eINSTANCE.getCustomizationEffort();

		/**
		 * The meta object literal for the '<em><b>Id Customization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION = eINSTANCE.getCustomizationEffort_IdCustomization();

		/**
		 * The meta object literal for the '<em><b>Added LO Cs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMIZATION_EFFORT__ADDED_LO_CS = eINSTANCE.getCustomizationEffort_Added_LOCs();

		/**
		 * The meta object literal for the '<em><b>Deleted LO Cs</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUSTOMIZATION_EFFORT__DELETED_LO_CS = eINSTANCE.getCustomizationEffort_Deleted_LOCs();

		/**
		 * The meta object literal for the '<em><b>Cust CA</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMIZATION_EFFORT__CUST_CA = eINSTANCE.getCustomizationEffort_CustCA();

		/**
		 * The meta object literal for the '<em><b>Productasset</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOMIZATION_EFFORT__PRODUCTASSET = eINSTANCE.getCustomizationEffort_Productasset();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.ProductPortfolioImpl <em>Product Portfolio</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.ProductPortfolioImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductPortfolio()
		 * @generated
		 */
		EClass PRODUCT_PORTFOLIO = eINSTANCE.getProductPortfolio();

		/**
		 * The meta object literal for the '<em><b>Id Product Portfolio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO = eINSTANCE.getProductPortfolio_IdProductPortfolio();

		/**
		 * The meta object literal for the '<em><b>Derived From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_PORTFOLIO__DERIVED_FROM = eINSTANCE.getProductPortfolio_DerivedFrom();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_PORTFOLIO__PRODUCTS = eINSTANCE.getProductPortfolio_Products();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.ProductImpl <em>Product</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.ProductImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Id Product</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__ID_PRODUCT = eINSTANCE.getProduct_IdProduct();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT__NAME = eINSTANCE.getProduct_Name();

		/**
		 * The meta object literal for the '<em><b>In Portfolio</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__IN_PORTFOLIO = eINSTANCE.getProduct_InPortfolio();

		/**
		 * The meta object literal for the '<em><b>Releases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT__RELEASES = eINSTANCE.getProduct_Releases();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.ProductReleaseImpl <em>Product Release</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.ProductReleaseImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductRelease()
		 * @generated
		 */
		EClass PRODUCT_RELEASE = eINSTANCE.getProductRelease();

		/**
		 * The meta object literal for the '<em><b>Id Release</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_RELEASE__ID_RELEASE = eINSTANCE.getProductRelease_IdRelease();

		/**
		 * The meta object literal for the '<em><b>Release Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_RELEASE__RELEASE_DATE = eINSTANCE.getProductRelease_ReleaseDate();

		/**
		 * The meta object literal for the '<em><b>Product</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_RELEASE__PRODUCT = eINSTANCE.getProductRelease_Product();

		/**
		 * The meta object literal for the '<em><b>Productasset</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_RELEASE__PRODUCTASSET = eINSTANCE.getProductRelease_Productasset();

		/**
		 * The meta object literal for the '{@link SPLmodel.impl.ProductAssetImpl <em>Product Asset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see SPLmodel.impl.ProductAssetImpl
		 * @see SPLmodel.impl.SPLmodelPackageImpl#getProductAsset()
		 * @generated
		 */
		EClass PRODUCT_ASSET = eINSTANCE.getProductAsset();

		/**
		 * The meta object literal for the '<em><b>Id Asset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ASSET__ID_ASSET = eINSTANCE.getProductAsset_IdAsset();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ASSET__NAME = eINSTANCE.getProductAsset_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ASSET__PATH = eINSTANCE.getProductAsset_Path();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ASSET__CONTENT = eINSTANCE.getProductAsset_Content();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRODUCT_ASSET__SIZE = eINSTANCE.getProductAsset_Size();

		/**
		 * The meta object literal for the '<em><b>From Release</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCT_ASSET__FROM_RELEASE = eINSTANCE.getProductAsset_FromRelease();

	}

} //SPLmodelPackage
