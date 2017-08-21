/**
 */
package SPLmodel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Customization Effort</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.CustomizationEffort#getIdCustomization <em>Id Customization</em>}</li>
 *   <li>{@link SPLmodel.CustomizationEffort#getAdded_LOCs <em>Added LO Cs</em>}</li>
 *   <li>{@link SPLmodel.CustomizationEffort#getDeleted_LOCs <em>Deleted LO Cs</em>}</li>
 *   <li>{@link SPLmodel.CustomizationEffort#getCustCA <em>Cust CA</em>}</li>
 *   <li>{@link SPLmodel.CustomizationEffort#getProductasset <em>Productasset</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort()
 * @model
 * @generated
 */
public interface CustomizationEffort extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Customization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Customization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Customization</em>' attribute.
	 * @see #setIdCustomization(int)
	 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort_IdCustomization()
	 * @model
	 * @generated
	 */
	int getIdCustomization();

	/**
	 * Sets the value of the '{@link SPLmodel.CustomizationEffort#getIdCustomization <em>Id Customization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Customization</em>' attribute.
	 * @see #getIdCustomization()
	 * @generated
	 */
	void setIdCustomization(int value);

	/**
	 * Returns the value of the '<em><b>Added LO Cs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Added LO Cs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Added LO Cs</em>' attribute.
	 * @see #setAdded_LOCs(int)
	 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort_Added_LOCs()
	 * @model
	 * @generated
	 */
	int getAdded_LOCs();

	/**
	 * Sets the value of the '{@link SPLmodel.CustomizationEffort#getAdded_LOCs <em>Added LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Added LO Cs</em>' attribute.
	 * @see #getAdded_LOCs()
	 * @generated
	 */
	void setAdded_LOCs(int value);

	/**
	 * Returns the value of the '<em><b>Deleted LO Cs</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deleted LO Cs</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deleted LO Cs</em>' attribute.
	 * @see #setDeleted_LOCs(int)
	 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort_Deleted_LOCs()
	 * @model
	 * @generated
	 */
	int getDeleted_LOCs();

	/**
	 * Sets the value of the '{@link SPLmodel.CustomizationEffort#getDeleted_LOCs <em>Deleted LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deleted LO Cs</em>' attribute.
	 * @see #getDeleted_LOCs()
	 * @generated
	 */
	void setDeleted_LOCs(int value);

	/**
	 * Returns the value of the '<em><b>Cust CA</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cust CA</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cust CA</em>' reference.
	 * @see #setCustCA(CoreAsset)
	 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort_CustCA()
	 * @model
	 * @generated
	 */
	CoreAsset getCustCA();

	/**
	 * Sets the value of the '{@link SPLmodel.CustomizationEffort#getCustCA <em>Cust CA</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cust CA</em>' reference.
	 * @see #getCustCA()
	 * @generated
	 */
	void setCustCA(CoreAsset value);

	/**
	 * Returns the value of the '<em><b>Productasset</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Productasset</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Productasset</em>' reference.
	 * @see #setProductasset(ProductAsset)
	 * @see SPLmodel.SPLmodelPackage#getCustomizationEffort_Productasset()
	 * @model
	 * @generated
	 */
	ProductAsset getProductasset();

	/**
	 * Sets the value of the '{@link SPLmodel.CustomizationEffort#getProductasset <em>Productasset</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Productasset</em>' reference.
	 * @see #getProductasset()
	 * @generated
	 */
	void setProductasset(ProductAsset value);

	void setCoreAsset(CoreAsset ca);

} // CustomizationEffort
