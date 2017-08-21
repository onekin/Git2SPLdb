/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAsset;
import SPLmodel.CoreAssetBaseline;
import SPLmodel.Feature;
import SPLmodel.SPLmodelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.FeatureImpl#getIdFeature <em>Id Feature</em>}</li>
 *   <li>{@link SPLmodel.impl.FeatureImpl#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.impl.FeatureImpl#getCoreassetbaseline <em>Coreassetbaseline</em>}</li>
 *   <li>{@link SPLmodel.impl.FeatureImpl#getCoreAssets <em>Core Assets</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends MinimalEObjectImpl.Container implements Feature {
	/**
	 * The default value of the '{@link #getIdFeature() <em>Id Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdFeature()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_FEATURE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdFeature() <em>Id Feature</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdFeature()
	 * @generated
	 * @ordered
	 */
	protected String idFeature = ID_FEATURE_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCoreassetbaseline() <em>Coreassetbaseline</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreassetbaseline()
	 * @generated
	 * @ordered
	 */
	protected EList<CoreAssetBaseline> coreassetbaseline;

	/**
	 * The cached value of the '{@link #getCoreAssets() <em>Core Assets</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreAssets()
	 * @generated
	 * @ordered
	 */
	protected EList<CoreAsset> coreAssets;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdFeature() {
		return idFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdFeature(String newIdFeature) {
		String oldIdFeature = idFeature;
		idFeature = newIdFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.FEATURE__ID_FEATURE, oldIdFeature, idFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.FEATURE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CoreAssetBaseline> getCoreassetbaseline() {
		if (coreassetbaseline == null) {
			coreassetbaseline = new EObjectWithInverseResolvingEList.ManyInverse<CoreAssetBaseline>(CoreAssetBaseline.class, this, SPLmodelPackage.FEATURE__COREASSETBASELINE, SPLmodelPackage.CORE_ASSET_BASELINE__FEATURE);
		}
		return coreassetbaseline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CoreAsset> getCoreAssets() {
		if (coreAssets == null) {
			coreAssets = new EObjectWithInverseResolvingEList.ManyInverse<CoreAsset>(CoreAsset.class, this, SPLmodelPackage.FEATURE__CORE_ASSETS, SPLmodelPackage.CORE_ASSET__MAPPED_TO);
		}
		return coreAssets;
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
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCoreassetbaseline()).basicAdd(otherEnd, msgs);
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCoreAssets()).basicAdd(otherEnd, msgs);
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
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				return ((InternalEList<?>)getCoreassetbaseline()).basicRemove(otherEnd, msgs);
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				return ((InternalEList<?>)getCoreAssets()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.FEATURE__ID_FEATURE:
				return getIdFeature();
			case SPLmodelPackage.FEATURE__NAME:
				return getName();
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				return getCoreassetbaseline();
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				return getCoreAssets();
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
			case SPLmodelPackage.FEATURE__ID_FEATURE:
				setIdFeature((String)newValue);
				return;
			case SPLmodelPackage.FEATURE__NAME:
				setName((String)newValue);
				return;
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				getCoreassetbaseline().clear();
				getCoreassetbaseline().addAll((Collection<? extends CoreAssetBaseline>)newValue);
				return;
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				getCoreAssets().clear();
				getCoreAssets().addAll((Collection<? extends CoreAsset>)newValue);
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
			case SPLmodelPackage.FEATURE__ID_FEATURE:
				setIdFeature(ID_FEATURE_EDEFAULT);
				return;
			case SPLmodelPackage.FEATURE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				getCoreassetbaseline().clear();
				return;
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				getCoreAssets().clear();
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
			case SPLmodelPackage.FEATURE__ID_FEATURE:
				return ID_FEATURE_EDEFAULT == null ? idFeature != null : !ID_FEATURE_EDEFAULT.equals(idFeature);
			case SPLmodelPackage.FEATURE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SPLmodelPackage.FEATURE__COREASSETBASELINE:
				return coreassetbaseline != null && !coreassetbaseline.isEmpty();
			case SPLmodelPackage.FEATURE__CORE_ASSETS:
				return coreAssets != null && !coreAssets.isEmpty();
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
		result.append(" (idFeature: ");
		result.append(idFeature);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	

	public void setCoreAssets(EList<CoreAsset> cas) {
		this.coreAssets = cas;
		
	}

	public void setCoreAssetBaseline(EList<CoreAssetBaseline> CaBaselines) {
		this.coreassetbaseline = CaBaselines;
		
	}

} //FeatureImpl
