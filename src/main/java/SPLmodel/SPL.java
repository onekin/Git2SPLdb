/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SPL</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.SPL#getIdSPL <em>Id SPL</em>}</li>
 *   <li>{@link SPLmodel.SPL#getBaselines <em>Baselines</em>}</li>
 *   <li>{@link SPLmodel.SPL#getPortfolio <em>Portfolio</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getSPL()
 * @model
 * @generated
 */
public interface SPL extends EObject {
	/**
	 * Returns the value of the '<em><b>Id SPL</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id SPL</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id SPL</em>' attribute.
	 * @see #setIdSPL(String)
	 * @see SPLmodel.SPLmodelPackage#getSPL_IdSPL()
	 * @model
	 * @generated
	 */
	String getIdSPL();

	/**
	 * Sets the value of the '{@link SPLmodel.SPL#getIdSPL <em>Id SPL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id SPL</em>' attribute.
	 * @see #getIdSPL()
	 * @generated
	 */
	void setIdSPL(String value);

	/**
	 * Returns the value of the '<em><b>Baselines</b></em>' containment reference list.
	 * The list contents are of type {@link SPLmodel.CoreAssetBaseline}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Baselines</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Baselines</em>' containment reference list.
	 * @see SPLmodel.SPLmodelPackage#getSPL_Baselines()
	 * @model containment="true"
	 * @generated
	 */
	EList<CoreAssetBaseline> getBaselines();

	/**
	 * Returns the value of the '<em><b>Portfolio</b></em>' containment reference list.
	 * The list contents are of type {@link SPLmodel.ProductPortfolio}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Portfolio</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Portfolio</em>' containment reference list.
	 * @see SPLmodel.SPLmodelPackage#getSPL_Portfolio()
	 * @model containment="true"
	 * @generated
	 */
	EList<ProductPortfolio> getPortfolio();

} // SPL
