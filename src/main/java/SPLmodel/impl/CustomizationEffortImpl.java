/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAsset;
import SPLmodel.CustomizationEffort;
import SPLmodel.ProductAsset;
import SPLmodel.SPLmodelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Customization Effort</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.CustomizationEffortImpl#getIdCustomization <em>Id Customization</em>}</li>
 *   <li>{@link SPLmodel.impl.CustomizationEffortImpl#getAdded_LOCs <em>Added LO Cs</em>}</li>
 *   <li>{@link SPLmodel.impl.CustomizationEffortImpl#getDeleted_LOCs <em>Deleted LO Cs</em>}</li>
 *   <li>{@link SPLmodel.impl.CustomizationEffortImpl#getCustCA <em>Cust CA</em>}</li>
 *   <li>{@link SPLmodel.impl.CustomizationEffortImpl#getProductasset <em>Productasset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomizationEffortImpl extends MinimalEObjectImpl.Container implements CustomizationEffort {
	/**
	 * The default value of the '{@link #getIdCustomization() <em>Id Customization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdCustomization()
	 * @generated
	 * @ordered
	 */
	protected static final int ID_CUSTOMIZATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getIdCustomization() <em>Id Customization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdCustomization()
	 * @generated
	 * @ordered
	 */
	protected int idCustomization = ID_CUSTOMIZATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getAdded_LOCs() <em>Added LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdded_LOCs()
	 * @generated
	 * @ordered
	 */
	protected static final int ADDED_LO_CS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAdded_LOCs() <em>Added LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdded_LOCs()
	 * @generated
	 * @ordered
	 */
	protected int added_LOCs = ADDED_LO_CS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeleted_LOCs() <em>Deleted LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeleted_LOCs()
	 * @generated
	 * @ordered
	 */
	protected static final int DELETED_LO_CS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDeleted_LOCs() <em>Deleted LO Cs</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeleted_LOCs()
	 * @generated
	 * @ordered
	 */
	protected int deleted_LOCs = DELETED_LO_CS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCustCA() <em>Cust CA</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustCA()
	 * @generated
	 * @ordered
	 */
	protected CoreAsset custCA;

	/**
	 * The cached value of the '{@link #getProductasset() <em>Productasset</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductasset()
	 * @generated
	 * @ordered
	 */
	protected ProductAsset productasset;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomizationEffortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.CUSTOMIZATION_EFFORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getIdCustomization() {
		return idCustomization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdCustomization(int newIdCustomization) {
		int oldIdCustomization = idCustomization;
		idCustomization = newIdCustomization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION, oldIdCustomization, idCustomization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getAdded_LOCs() {
		return added_LOCs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdded_LOCs(int newAdded_LOCs) {
		int oldAdded_LOCs = added_LOCs;
		added_LOCs = newAdded_LOCs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CUSTOMIZATION_EFFORT__ADDED_LO_CS, oldAdded_LOCs, added_LOCs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDeleted_LOCs() {
		return deleted_LOCs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeleted_LOCs(int newDeleted_LOCs) {
		int oldDeleted_LOCs = deleted_LOCs;
		deleted_LOCs = newDeleted_LOCs;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CUSTOMIZATION_EFFORT__DELETED_LO_CS, oldDeleted_LOCs, deleted_LOCs));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAsset getCustCA() {
		if (custCA != null && custCA.eIsProxy()) {
			InternalEObject oldCustCA = (InternalEObject)custCA;
			custCA = (CoreAsset)eResolveProxy(oldCustCA);
			if (custCA != oldCustCA) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA, oldCustCA, custCA));
			}
		}
		return custCA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAsset basicGetCustCA() {
		return custCA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCustCA(CoreAsset newCustCA) {
		CoreAsset oldCustCA = custCA;
		custCA = newCustCA;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA, oldCustCA, custCA));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductAsset getProductasset() {
		if (productasset != null && productasset.eIsProxy()) {
			InternalEObject oldProductasset = (InternalEObject)productasset;
			productasset = (ProductAsset)eResolveProxy(oldProductasset);
			if (productasset != oldProductasset) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET, oldProductasset, productasset));
			}
		}
		return productasset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductAsset basicGetProductasset() {
		return productasset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProductasset(ProductAsset newProductasset) {
		ProductAsset oldProductasset = productasset;
		productasset = newProductasset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET, oldProductasset, productasset));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION:
				return getIdCustomization();
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ADDED_LO_CS:
				return getAdded_LOCs();
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__DELETED_LO_CS:
				return getDeleted_LOCs();
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA:
				if (resolve) return getCustCA();
				return basicGetCustCA();
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET:
				if (resolve) return getProductasset();
				return basicGetProductasset();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION:
				setIdCustomization((Integer)newValue);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ADDED_LO_CS:
				setAdded_LOCs((Integer)newValue);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__DELETED_LO_CS:
				setDeleted_LOCs((Integer)newValue);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA:
				setCustCA((CoreAsset)newValue);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET:
				setProductasset((ProductAsset)newValue);
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
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION:
				setIdCustomization(ID_CUSTOMIZATION_EDEFAULT);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ADDED_LO_CS:
				setAdded_LOCs(ADDED_LO_CS_EDEFAULT);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__DELETED_LO_CS:
				setDeleted_LOCs(DELETED_LO_CS_EDEFAULT);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA:
				setCustCA((CoreAsset)null);
				return;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET:
				setProductasset((ProductAsset)null);
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
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ID_CUSTOMIZATION:
				return idCustomization != ID_CUSTOMIZATION_EDEFAULT;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__ADDED_LO_CS:
				return added_LOCs != ADDED_LO_CS_EDEFAULT;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__DELETED_LO_CS:
				return deleted_LOCs != DELETED_LO_CS_EDEFAULT;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__CUST_CA:
				return custCA != null;
			case SPLmodelPackage.CUSTOMIZATION_EFFORT__PRODUCTASSET:
				return productasset != null;
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
		result.append(" (idCustomization: ");
		result.append(idCustomization);
		result.append(", added_LOCs: ");
		result.append(added_LOCs);
		result.append(", deleted_LOCs: ");
		result.append(deleted_LOCs);
		result.append(')');
		return result.toString();
	}

	public void setCoreAsset(CoreAsset ca) {
	this.custCA = ca;
		
	}

} //CustomizationEffortImpl
