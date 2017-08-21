/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.Product#getIdProduct <em>Id Product</em>}</li>
 *   <li>{@link SPLmodel.Product#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.Product#getInPortfolio <em>In Portfolio</em>}</li>
 *   <li>{@link SPLmodel.Product#getReleases <em>Releases</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getProduct()
 * @model
 * @generated
 */
public interface Product extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Product</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Product</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Product</em>' attribute.
	 * @see #setIdProduct(String)
	 * @see SPLmodel.SPLmodelPackage#getProduct_IdProduct()
	 * @model
	 * @generated
	 */
	String getIdProduct();

	/**
	 * Sets the value of the '{@link SPLmodel.Product#getIdProduct <em>Id Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Product</em>' attribute.
	 * @see #getIdProduct()
	 * @generated
	 */
	void setIdProduct(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see SPLmodel.SPLmodelPackage#getProduct_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SPLmodel.Product#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>In Portfolio</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SPLmodel.ProductPortfolio#getProducts <em>Products</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Portfolio</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Portfolio</em>' reference.
	 * @see #setInPortfolio(ProductPortfolio)
	 * @see SPLmodel.SPLmodelPackage#getProduct_InPortfolio()
	 * @see SPLmodel.ProductPortfolio#getProducts
	 * @model opposite="products"
	 * @generated
	 */
	ProductPortfolio getInPortfolio();

	/**
	 * Sets the value of the '{@link SPLmodel.Product#getInPortfolio <em>In Portfolio</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Portfolio</em>' reference.
	 * @see #getInPortfolio()
	 * @generated
	 */
	void setInPortfolio(ProductPortfolio value);

	/**
	 * Returns the value of the '<em><b>Releases</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.ProductRelease}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.ProductRelease#getProduct <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Releases</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Releases</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getProduct_Releases()
	 * @see SPLmodel.ProductRelease#getProduct
	 * @model opposite="product"
	 * @generated
	 */
	EList<ProductRelease> getReleases();

} // Product
