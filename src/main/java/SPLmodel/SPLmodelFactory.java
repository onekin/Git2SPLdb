/**
 */
package SPLmodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see SPLmodel.SPLmodelPackage
 * @generated
 */
public interface SPLmodelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SPLmodelFactory eINSTANCE = SPLmodel.impl.SPLmodelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>SPL</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>SPL</em>'.
	 * @generated
	 */
	SPL createSPL();

	/**
	 * Returns a new object of class '<em>Core Asset Baseline</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Core Asset Baseline</em>'.
	 * @generated
	 */
	CoreAssetBaseline createCoreAssetBaseline();

	/**
	 * Returns a new object of class '<em>Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Feature</em>'.
	 * @generated
	 */
	Feature createFeature();

	/**
	 * Returns a new object of class '<em>Core Asset</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Core Asset</em>'.
	 * @generated
	 */
	CoreAsset createCoreAsset();

	/**
	 * Returns a new object of class '<em>Customization Effort</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Customization Effort</em>'.
	 * @generated
	 */
	CustomizationEffort createCustomizationEffort();

	/**
	 * Returns a new object of class '<em>Product Portfolio</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Portfolio</em>'.
	 * @generated
	 */
	ProductPortfolio createProductPortfolio();

	/**
	 * Returns a new object of class '<em>Product</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product</em>'.
	 * @generated
	 */
	Product createProduct();

	/**
	 * Returns a new object of class '<em>Product Release</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Release</em>'.
	 * @generated
	 */
	ProductRelease createProductRelease();

	/**
	 * Returns a new object of class '<em>Product Asset</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Product Asset</em>'.
	 * @generated
	 */
	ProductAsset createProductAsset();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SPLmodelPackage getSPLmodelPackage();

} //SPLmodelFactory
