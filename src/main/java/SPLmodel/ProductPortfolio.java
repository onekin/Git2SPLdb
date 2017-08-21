/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Portfolio</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.ProductPortfolio#getIdProductPortfolio <em>Id Product Portfolio</em>}</li>
 *   <li>{@link SPLmodel.ProductPortfolio#getDerivedFrom <em>Derived From</em>}</li>
 *   <li>{@link SPLmodel.ProductPortfolio#getProducts <em>Products</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getProductPortfolio()
 * @model
 * @generated
 */
public interface ProductPortfolio extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Product Portfolio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Product Portfolio</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Product Portfolio</em>' attribute.
	 * @see #setIdProductPortfolio(String)
	 * @see SPLmodel.SPLmodelPackage#getProductPortfolio_IdProductPortfolio()
	 * @model
	 * @generated
	 */
	String getIdProductPortfolio();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductPortfolio#getIdProductPortfolio <em>Id Product Portfolio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Product Portfolio</em>' attribute.
	 * @see #getIdProductPortfolio()
	 * @generated
	 */
	void setIdProductPortfolio(String value);

	/**
	 * Returns the value of the '<em><b>Derived From</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SPLmodel.CoreAssetBaseline#getProductPortfolio <em>Product Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived From</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived From</em>' reference.
	 * @see #setDerivedFrom(CoreAssetBaseline)
	 * @see SPLmodel.SPLmodelPackage#getProductPortfolio_DerivedFrom()
	 * @see SPLmodel.CoreAssetBaseline#getProductPortfolio
	 * @model opposite="productPortfolio"
	 * @generated
	 */
	CoreAssetBaseline getDerivedFrom();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductPortfolio#getDerivedFrom <em>Derived From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived From</em>' reference.
	 * @see #getDerivedFrom()
	 * @generated
	 */
	void setDerivedFrom(CoreAssetBaseline value);

	/**
	 * Returns the value of the '<em><b>Products</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.Product}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.Product#getInPortfolio <em>In Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Products</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Products</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getProductPortfolio_Products()
	 * @see SPLmodel.Product#getInPortfolio
	 * @model opposite="inPortfolio"
	 * @generated
	 */
	EList<Product> getProducts();

} // ProductPortfolio
