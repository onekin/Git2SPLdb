/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Core Asset</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.CoreAsset#getIdCoreAsset <em>Id Core Asset</em>}</li>
 *   <li>{@link SPLmodel.CoreAsset#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.CoreAsset#getPath <em>Path</em>}</li>
 *   <li>{@link SPLmodel.CoreAsset#getContent <em>Content</em>}</li>
 *   <li>{@link SPLmodel.CoreAsset#getSize <em>Size</em>}</li>
 *   <li>{@link SPLmodel.CoreAsset#getMappedTo <em>Mapped To</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getCoreAsset()
 * @model
 * @generated
 */
public interface CoreAsset extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Core Asset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Core Asset</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Core Asset</em>' attribute.
	 * @see #setIdCoreAsset(String)
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_IdCoreAsset()
	 * @model
	 * @generated
	 */
	String getIdCoreAsset();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAsset#getIdCoreAsset <em>Id Core Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Core Asset</em>' attribute.
	 * @see #getIdCoreAsset()
	 * @generated
	 */
	void setIdCoreAsset(String value);

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
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAsset#getName <em>Name</em>}' attribute.
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
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAsset#getPath <em>Path</em>}' attribute.
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
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_Content()
	 * @model
	 * @generated
	 */
	String getContent();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAsset#getContent <em>Content</em>}' attribute.
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
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_Size()
	 * @model
	 * @generated
	 */
	int getSize();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAsset#getSize <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Size</em>' attribute.
	 * @see #getSize()
	 * @generated
	 */
	void setSize(int value);

	/**
	 * Returns the value of the '<em><b>Mapped To</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.Feature}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.Feature#getCoreAssets <em>Core Assets</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mapped To</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mapped To</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getCoreAsset_MappedTo()
	 * @see SPLmodel.Feature#getCoreAssets
	 * @model opposite="coreAssets" required="true"
	 * @generated
	 */
	EList<Feature> getMappedTo();

} // CoreAsset
