/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.Feature#getIdFeature <em>Id Feature</em>}</li>
 *   <li>{@link SPLmodel.Feature#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.Feature#getCoreassetbaseline <em>Coreassetbaseline</em>}</li>
 *   <li>{@link SPLmodel.Feature#getCoreAssets <em>Core Assets</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Feature</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Feature</em>' attribute.
	 * @see #setIdFeature(String)
	 * @see SPLmodel.SPLmodelPackage#getFeature_IdFeature()
	 * @model
	 * @generated
	 */
	String getIdFeature();

	/**
	 * Sets the value of the '{@link SPLmodel.Feature#getIdFeature <em>Id Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Feature</em>' attribute.
	 * @see #getIdFeature()
	 * @generated
	 */
	void setIdFeature(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see SPLmodel.SPLmodelPackage#getFeature_Name()
	 * @model default=""
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link SPLmodel.Feature#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Coreassetbaseline</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.CoreAssetBaseline}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.CoreAssetBaseline#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coreassetbaseline</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coreassetbaseline</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getFeature_Coreassetbaseline()
	 * @see SPLmodel.CoreAssetBaseline#getFeature
	 * @model opposite="feature"
	 * @generated
	 */
	EList<CoreAssetBaseline> getCoreassetbaseline();

	/**
	 * Returns the value of the '<em><b>Core Assets</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.CoreAsset}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.CoreAsset#getMappedTo <em>Mapped To</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Assets</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Assets</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getFeature_CoreAssets()
	 * @see SPLmodel.CoreAsset#getMappedTo
	 * @model opposite="mappedTo" required="true"
	 * @generated
	 */
	EList<CoreAsset> getCoreAssets();

	void setCoreAssetBaseline(EList <CoreAssetBaseline> CaBaselines);
	void setCoreAssets (EList <CoreAsset> cas);
} // Feature
