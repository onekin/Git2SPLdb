/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAssetBaseline;
import SPLmodel.ProductPortfolio;
import SPLmodel.SPL;
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
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SPL</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.SPLImpl#getIdSPL <em>Id SPL</em>}</li>
 *   <li>{@link SPLmodel.impl.SPLImpl#getBaselines <em>Baselines</em>}</li>
 *   <li>{@link SPLmodel.impl.SPLImpl#getPortfolio <em>Portfolio</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SPLImpl extends MinimalEObjectImpl.Container implements SPL {
	/**
	 * The default value of the '{@link #getIdSPL() <em>Id SPL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdSPL()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_SPL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdSPL() <em>Id SPL</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdSPL()
	 * @generated
	 * @ordered
	 */
	protected String idSPL = ID_SPL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBaselines() <em>Baselines</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaselines()
	 * @generated
	 * @ordered
	 */
	protected EList<CoreAssetBaseline> baselines;

	/**
	 * The cached value of the '{@link #getPortfolio() <em>Portfolio</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortfolio()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductPortfolio> portfolio;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SPLImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.SPL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdSPL() {
		return idSPL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdSPL(String newIdSPL) {
		String oldIdSPL = idSPL;
		idSPL = newIdSPL;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.SPL__ID_SPL, oldIdSPL, idSPL));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CoreAssetBaseline> getBaselines() {
		if (baselines == null) {
			baselines = new EObjectContainmentEList<CoreAssetBaseline>(CoreAssetBaseline.class, this, SPLmodelPackage.SPL__BASELINES);
		}
		return baselines;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductPortfolio> getPortfolio() {
		if (portfolio == null) {
			portfolio = new EObjectContainmentEList<ProductPortfolio>(ProductPortfolio.class, this, SPLmodelPackage.SPL__PORTFOLIO);
		}
		return portfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SPLmodelPackage.SPL__BASELINES:
				return ((InternalEList<?>)getBaselines()).basicRemove(otherEnd, msgs);
			case SPLmodelPackage.SPL__PORTFOLIO:
				return ((InternalEList<?>)getPortfolio()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.SPL__ID_SPL:
				return getIdSPL();
			case SPLmodelPackage.SPL__BASELINES:
				return getBaselines();
			case SPLmodelPackage.SPL__PORTFOLIO:
				return getPortfolio();
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
			case SPLmodelPackage.SPL__ID_SPL:
				setIdSPL((String)newValue);
				return;
			case SPLmodelPackage.SPL__BASELINES:
				getBaselines().clear();
				getBaselines().addAll((Collection<? extends CoreAssetBaseline>)newValue);
				return;
			case SPLmodelPackage.SPL__PORTFOLIO:
				getPortfolio().clear();
				getPortfolio().addAll((Collection<? extends ProductPortfolio>)newValue);
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
			case SPLmodelPackage.SPL__ID_SPL:
				setIdSPL(ID_SPL_EDEFAULT);
				return;
			case SPLmodelPackage.SPL__BASELINES:
				getBaselines().clear();
				return;
			case SPLmodelPackage.SPL__PORTFOLIO:
				getPortfolio().clear();
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
			case SPLmodelPackage.SPL__ID_SPL:
				return ID_SPL_EDEFAULT == null ? idSPL != null : !ID_SPL_EDEFAULT.equals(idSPL);
			case SPLmodelPackage.SPL__BASELINES:
				return baselines != null && !baselines.isEmpty();
			case SPLmodelPackage.SPL__PORTFOLIO:
				return portfolio != null && !portfolio.isEmpty();
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
		result.append(" (idSPL: ");
		result.append(idSPL);
		result.append(')');
		return result.toString();
	}

} //SPLImpl
