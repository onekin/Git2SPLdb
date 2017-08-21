/**
 */
package SPLmodel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Core Asset Baseline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.CoreAssetBaseline#getIdBaseline <em>Id Baseline</em>}</li>
 *   <li>{@link SPLmodel.CoreAssetBaseline#getReleaseDate <em>Release Date</em>}</li>
 *   <li>{@link SPLmodel.CoreAssetBaseline#getProductPortfolio <em>Product Portfolio</em>}</li>
 *   <li>{@link SPLmodel.CoreAssetBaseline#getCoreasset <em>Coreasset</em>}</li>
 *   <li>{@link SPLmodel.CoreAssetBaseline#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline()
 * @model
 * @generated
 */
public interface CoreAssetBaseline extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Baseline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Baseline</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Baseline</em>' attribute.
	 * @see #setIdBaseline(String)
	 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline_IdBaseline()
	 * @model
	 * @generated
	 */
	String getIdBaseline();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAssetBaseline#getIdBaseline <em>Id Baseline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Baseline</em>' attribute.
	 * @see #getIdBaseline()
	 * @generated
	 */
	void setIdBaseline(String value);

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
	 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline_ReleaseDate()
	 * @model
	 * @generated
	 */
	String getReleaseDate();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAssetBaseline#getReleaseDate <em>Release Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Release Date</em>' attribute.
	 * @see #getReleaseDate()
	 * @generated
	 */
	void setReleaseDate(String value);

	/**
	 * Returns the value of the '<em><b>Product Portfolio</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link SPLmodel.ProductPortfolio#getDerivedFrom <em>Derived From</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Product Portfolio</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Product Portfolio</em>' reference.
	 * @see #setProductPortfolio(ProductPortfolio)
	 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline_ProductPortfolio()
	 * @see SPLmodel.ProductPortfolio#getDerivedFrom
	 * @model opposite="derivedFrom"
	 * @generated
	 */
	ProductPortfolio getProductPortfolio();

	/**
	 * Sets the value of the '{@link SPLmodel.CoreAssetBaseline#getProductPortfolio <em>Product Portfolio</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Product Portfolio</em>' reference.
	 * @see #getProductPortfolio()
	 * @generated
	 */
	void setProductPortfolio(ProductPortfolio value);

	/**
	 * Returns the value of the '<em><b>Coreasset</b></em>' containment reference list.
	 * The list contents are of type {@link SPLmodel.CoreAsset}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coreasset</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coreasset</em>' containment reference list.
	 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline_Coreasset()
	 * @model containment="true"
	 * @generated
	 */
	EList<CoreAsset> getCoreasset();

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link SPLmodel.Feature}.
	 * It is bidirectional and its opposite is '{@link SPLmodel.Feature#getCoreassetbaseline <em>Coreassetbaseline</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see SPLmodel.SPLmodelPackage#getCoreAssetBaseline_Feature()
	 * @see SPLmodel.Feature#getCoreassetbaseline
	 * @model opposite="coreassetbaseline"
	 * @generated
	 */
	void setCoreasset(EList<CoreAsset> listCA);
	
	EList<Feature> getFeature();

	void setFeature(EList<Feature> listF);

} // CoreAssetBaseline
