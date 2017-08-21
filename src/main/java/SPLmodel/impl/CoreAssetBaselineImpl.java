/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAsset;
import SPLmodel.CoreAssetBaseline;
import SPLmodel.Feature;
import SPLmodel.ProductPortfolio;
import SPLmodel.SPLmodelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Core Asset Baseline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.CoreAssetBaselineImpl#getIdBaseline <em>Id Baseline</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetBaselineImpl#getReleaseDate <em>Release Date</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetBaselineImpl#getProductPortfolio <em>Product Portfolio</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetBaselineImpl#getCoreasset <em>Coreasset</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetBaselineImpl#getFeature <em>Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CoreAssetBaselineImpl extends MinimalEObjectImpl.Container implements CoreAssetBaseline {
	/**
	 * The default value of the '{@link #getIdBaseline() <em>Id Baseline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdBaseline()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_BASELINE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdBaseline() <em>Id Baseline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdBaseline()
	 * @generated
	 * @ordered
	 */
	protected String idBaseline = ID_BASELINE_EDEFAULT;

	/**
	 * The default value of the '{@link #getReleaseDate() <em>Release Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseDate()
	 * @generated
	 * @ordered
	 */
	protected static final String RELEASE_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReleaseDate() <em>Release Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleaseDate()
	 * @generated
	 * @ordered
	 */
	protected String releaseDate = RELEASE_DATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProductPortfolio() <em>Product Portfolio</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductPortfolio()
	 * @generated
	 * @ordered
	 */
	protected ProductPortfolio productPortfolio;

	/**
	 * The cached value of the '{@link #getCoreasset() <em>Coreasset</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreasset()
	 * @generated
	 * @ordered
	 */
	protected EList<CoreAsset> coreasset;

	/**
	 * The cached value of the '{@link #getFeature() <em>Feature</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFeature()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> feature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoreAssetBaselineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.CORE_ASSET_BASELINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdBaseline() {
		return idBaseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdBaseline(String newIdBaseline) {
		String oldIdBaseline = idBaseline;
		idBaseline = newIdBaseline;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET_BASELINE__ID_BASELINE, oldIdBaseline, idBaseline));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReleaseDate(String newReleaseDate) {
		String oldReleaseDate = releaseDate;
		releaseDate = newReleaseDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET_BASELINE__RELEASE_DATE, oldReleaseDate, releaseDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductPortfolio getProductPortfolio() {
		if (productPortfolio != null && productPortfolio.eIsProxy()) {
			InternalEObject oldProductPortfolio = (InternalEObject)productPortfolio;
			productPortfolio = (ProductPortfolio)eResolveProxy(oldProductPortfolio);
			if (productPortfolio != oldProductPortfolio) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, oldProductPortfolio, productPortfolio));
			}
		}
		return productPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductPortfolio basicGetProductPortfolio() {
		return productPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProductPortfolio(ProductPortfolio newProductPortfolio, NotificationChain msgs) {
		ProductPortfolio oldProductPortfolio = productPortfolio;
		productPortfolio = newProductPortfolio;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, oldProductPortfolio, newProductPortfolio);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProductPortfolio(ProductPortfolio newProductPortfolio) {
		if (newProductPortfolio != productPortfolio) {
			NotificationChain msgs = null;
			if (productPortfolio != null)
				msgs = ((InternalEObject)productPortfolio).eInverseRemove(this, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, ProductPortfolio.class, msgs);
			if (newProductPortfolio != null)
				msgs = ((InternalEObject)newProductPortfolio).eInverseAdd(this, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, ProductPortfolio.class, msgs);
			msgs = basicSetProductPortfolio(newProductPortfolio, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, newProductPortfolio, newProductPortfolio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CoreAsset> getCoreasset() {
		if (coreasset == null) {
			coreasset = new EObjectContainmentEList<CoreAsset>(CoreAsset.class, this, SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET);
		}
		return coreasset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeature() {
		if (feature == null) {
			feature = new EObjectWithInverseResolvingEList.ManyInverse<Feature>(Feature.class, this, SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE, SPLmodelPackage.FEATURE__COREASSETBASELINE);
		}
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				if (productPortfolio != null)
					msgs = ((InternalEObject)productPortfolio).eInverseRemove(this, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, ProductPortfolio.class, msgs);
				return basicSetProductPortfolio((ProductPortfolio)otherEnd, msgs);
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getFeature()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				return basicSetProductPortfolio(null, msgs);
			case SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET:
				return ((InternalEList<?>)getCoreasset()).basicRemove(otherEnd, msgs);
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				return ((InternalEList<?>)getFeature()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__ID_BASELINE:
				return getIdBaseline();
			case SPLmodelPackage.CORE_ASSET_BASELINE__RELEASE_DATE:
				return getReleaseDate();
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				if (resolve) return getProductPortfolio();
				return basicGetProductPortfolio();
			case SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET:
				return getCoreasset();
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				return getFeature();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__ID_BASELINE:
				setIdBaseline((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__RELEASE_DATE:
				setReleaseDate((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				setProductPortfolio((ProductPortfolio)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET:
				getCoreasset().clear();
				getCoreasset().addAll((Collection<? extends CoreAsset>)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				getFeature().clear();
				getFeature().addAll((Collection<? extends Feature>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__ID_BASELINE:
				setIdBaseline(ID_BASELINE_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__RELEASE_DATE:
				setReleaseDate(RELEASE_DATE_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				setProductPortfolio((ProductPortfolio)null);
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET:
				getCoreasset().clear();
				return;
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				getFeature().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SPLmodelPackage.CORE_ASSET_BASELINE__ID_BASELINE:
				return ID_BASELINE_EDEFAULT == null ? idBaseline != null : !ID_BASELINE_EDEFAULT.equals(idBaseline);
			case SPLmodelPackage.CORE_ASSET_BASELINE__RELEASE_DATE:
				return RELEASE_DATE_EDEFAULT == null ? releaseDate != null : !RELEASE_DATE_EDEFAULT.equals(releaseDate);
			case SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO:
				return productPortfolio != null;
			case SPLmodelPackage.CORE_ASSET_BASELINE__COREASSET:
				return coreasset != null && !coreasset.isEmpty();
			case SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE:
				return feature != null && !feature.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (idBaseline: ");
		result.append(idBaseline);
		result.append(", ReleaseDate: ");
		result.append(releaseDate);
		result.append(')');
		return result.toString();
	}

	public void setCoreasset(EList<CoreAsset> listCA) {
		this.coreasset = listCA;
		
	}

	public void setFeature(EList<Feature> listF) {
		this.feature=listF;
		
	}

} //CoreAssetBaselineImpl
