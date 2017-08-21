/**
 */
package SPLmodel.impl;

import SPLmodel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SPLmodelFactoryImpl extends EFactoryImpl implements SPLmodelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SPLmodelFactory init() {
		try {
			SPLmodelFactory theSPLmodelFactory = (SPLmodelFactory)EPackage.Registry.INSTANCE.getEFactory(SPLmodelPackage.eNS_URI);
			if (theSPLmodelFactory != null) {
				return theSPLmodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SPLmodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPLmodelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SPLmodelPackage.SPL: return createSPL();
			case SPLmodelPackage.CORE_ASSET_BASELINE: return createCoreAssetBaseline();
			case SPLmodelPackage.FEATURE: return createFeature();
			case SPLmodelPackage.CORE_ASSET: return createCoreAsset();
			case SPLmodelPackage.CUSTOMIZATION_EFFORT: return createCustomizationEffort();
			case SPLmodelPackage.PRODUCT_PORTFOLIO: return createProductPortfolio();
			case SPLmodelPackage.PRODUCT: return createProduct();
			case SPLmodelPackage.PRODUCT_RELEASE: return createProductRelease();
			case SPLmodelPackage.PRODUCT_ASSET: return createProductAsset();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPL createSPL() {
		SPLImpl spl = new SPLImpl();
		return spl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAssetBaseline createCoreAssetBaseline() {
		CoreAssetBaselineImpl coreAssetBaseline = new CoreAssetBaselineImpl();
		return coreAssetBaseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAsset createCoreAsset() {
		CoreAssetImpl coreAsset = new CoreAssetImpl();
		return coreAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CustomizationEffort createCustomizationEffort() {
		CustomizationEffortImpl customizationEffort = new CustomizationEffortImpl();
		return customizationEffort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductPortfolio createProductPortfolio() {
		ProductPortfolioImpl productPortfolio = new ProductPortfolioImpl();
		return productPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product createProduct() {
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductRelease createProductRelease() {
		ProductReleaseImpl productRelease = new ProductReleaseImpl();
		return productRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductAsset createProductAsset() {
		ProductAssetImpl productAsset = new ProductAssetImpl();
		return productAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPLmodelPackage getSPLmodelPackage() {
		return (SPLmodelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SPLmodelPackage getPackage() {
		return SPLmodelPackage.eINSTANCE;
	}

} //SPLmodelFactoryImpl
