/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Release</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.ProductRelease#getIdRelease <em>Id Release</em>}</li>
 *   <li>{@link SPLmodel.ProductRelease#getReleaseDate <em>Release Date</em>}</li>
 *   <li>{@link SPLmodel.ProductRelease#getProduct <em>Product</em>}</li>
 *   <li>{@link SPLmodel.ProductRelease#getProductasset <em>Productasset</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getProductRelease()
 * @model
 * @generated
 */
public interface ProductRelease extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Release</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Release</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Release</em>' attribute.
	 * @see #setIdRelease(String)
	 * @see SPLmodel.SPLmodelPackage#getProductRelease_IdRelease()
	 * @model
	 * @generated
	 */
	String getIdRelease();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductRelease#getIdRelease <em>Id Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Release</em>' attribute.
	 * @see #getIdRelease()
	 * @generated
	 */
	void setIdRelease(String value);

	/**
	 * Returns the value of the '<em><b>Release Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Release Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Release Date</em>' attribute.
	 * @see #setReleaseDate(String)
	 * @see SPLmodel.SPLmodelPackage#getProductRelease_ReleaseDate()
	 * @model
	 * @generated
	 */
	String getReleaseDate();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductRelease#getReleaseDate <em>Release Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Release Date</em>' attribute.
	 * @see #getReleaseDate()
	 * @generated
	 */
	void setReleaseDate(String value);

	/**
	 * Returns the value of the '<em><b>Product</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SPLmodel.Product#getReleases <em>Releases</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product</em>' reference.
	 * @see #setProduct(Product)
	 * @see SPLmodel.SPLmodelPackage#getProductRelease_Product()
	 * @see SPLmodel.Product#getReleases
	 * @model opposite="releases"
	 * @generated
	 */
	Product getProduct();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductRelease#getProduct <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product</em>' reference.
	 * @see #getProduct()
	 * @generated
	 */
	void setProduct(Product value);

	/**
	 * Returns the value of the '<em><b>Productasset</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.ProductAsset}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.ProductAsset#getFromRelease <em>From Release</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Productasset</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Productasset</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getProductRelease_Productasset()
	 * @see SPLmodel.ProductAsset#getFromRelease
	 * @model opposite="fromRelease"
	 * @generated
	 */
	EList<ProductAsset> getProductasset();

} // ProductRelease
