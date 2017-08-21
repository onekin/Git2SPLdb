/**
 */
package SPLmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Product Asset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.ProductAsset#getIdAsset <em>Id Asset</em>}</li>
 *   <li>{@link SPLmodel.ProductAsset#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.ProductAsset#getPath <em>Path</em>}</li>
 *   <li>{@link SPLmodel.ProductAsset#getContent <em>Content</em>}</li>
 *   <li>{@link SPLmodel.ProductAsset#getSize <em>Size</em>}</li>
 *   <li>{@link SPLmodel.ProductAsset#getFromRelease <em>From Release</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getProductAsset()
 * @model
 * @generated
 */
public interface ProductAsset extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Asset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Asset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Asset</em>' attribute.
	 * @see #setIdAsset(String)
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_IdAsset()
	 * @model
	 * @generated
	 */
	String getIdAsset();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getIdAsset <em>Id Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Asset</em>' attribute.
	 * @see #getIdAsset()
	 * @generated
	 */
	void setIdAsset(String value);

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
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Content</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Content</em>' attribute.
	 * @see #setContent(String)
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_Content()
	 * @model
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getContent <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' attribute.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(String value);

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see #setSize(int)
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

	/**
	 * Returns the value of the '<em><b>From Release</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SPLmodel.ProductRelease#getProductasset <em>Productasset</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Release</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Release</em>' reference.
	 * @see #setFromRelease(ProductRelease)
	 * @see SPLmodel.SPLmodelPackage#getProductAsset_FromRelease()
	 * @see SPLmodel.ProductRelease#getProductasset
	 * @model opposite="productasset"
	 * @generated
	 */
	ProductRelease getFromRelease();

	/**
	 * Sets the value of the '{@link SPLmodel.ProductAsset#getFromRelease <em>From Release</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Release</em>' reference.
	 * @see #getFromRelease()
	 * @generated
	 */
	void setFromRelease(ProductRelease value);

} // ProductAsset
