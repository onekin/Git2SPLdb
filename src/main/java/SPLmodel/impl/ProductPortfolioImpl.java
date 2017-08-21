/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAssetBaseline;
import SPLmodel.Product;
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

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Portfolio</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.ProductPortfolioImpl#getIdProductPortfolio <em>Id Product Portfolio</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductPortfolioImpl#getDerivedFrom <em>Derived From</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductPortfolioImpl#getProducts <em>Products</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductPortfolioImpl extends MinimalEObjectImpl.Container implements ProductPortfolio {
	/**
	 * The default value of the '{@link #getIdProductPortfolio() <em>Id Product Portfolio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdProductPortfolio()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_PRODUCT_PORTFOLIO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdProductPortfolio() <em>Id Product Portfolio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdProductPortfolio()
	 * @generated
	 * @ordered
	 */
	protected String idProductPortfolio = ID_PRODUCT_PORTFOLIO_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDerivedFrom() <em>Derived From</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDerivedFrom()
	 * @generated
	 * @ordered
	 */
	protected CoreAssetBaseline derivedFrom;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductPortfolioImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.PRODUCT_PORTFOLIO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdProductPortfolio() {
		return idProductPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdProductPortfolio(String newIdProductPortfolio) {
		String oldIdProductPortfolio = idProductPortfolio;
		idProductPortfolio = newIdProductPortfolio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO, oldIdProductPortfolio, idProductPortfolio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAssetBaseline getDerivedFrom() {
		if (derivedFrom != null && derivedFrom.eIsProxy()) {
			InternalEObject oldDerivedFrom = (InternalEObject)derivedFrom;
			derivedFrom = (CoreAssetBaseline)eResolveProxy(oldDerivedFrom);
			if (derivedFrom != oldDerivedFrom) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, oldDerivedFrom, derivedFrom));
			}
		}
		return derivedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreAssetBaseline basicGetDerivedFrom() {
		return derivedFrom;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDerivedFrom(CoreAssetBaseline newDerivedFrom, NotificationChain msgs) {
		CoreAssetBaseline oldDerivedFrom = derivedFrom;
		derivedFrom = newDerivedFrom;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, oldDerivedFrom, newDerivedFrom);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDerivedFrom(CoreAssetBaseline newDerivedFrom) {
		if (newDerivedFrom != derivedFrom) {
			NotificationChain msgs = null;
			if (derivedFrom != null)
				msgs = ((InternalEObject)derivedFrom).eInverseRemove(this, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, CoreAssetBaseline.class, msgs);
			if (newDerivedFrom != null)
				msgs = ((InternalEObject)newDerivedFrom).eInverseAdd(this, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, CoreAssetBaseline.class, msgs);
			msgs = basicSetDerivedFrom(newDerivedFrom, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM, newDerivedFrom, newDerivedFrom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getProducts() {
		if (products == null) {
			products = new EObjectWithInverseResolvingEList<Product>(Product.class, this, SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS, SPLmodelPackage.PRODUCT__IN_PORTFOLIO);
		}
		return products;
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				if (derivedFrom != null)
					msgs = ((InternalEObject)derivedFrom).eInverseRemove(this, SPLmodelPackage.CORE_ASSET_BASELINE__PRODUCT_PORTFOLIO, CoreAssetBaseline.class, msgs);
				return basicSetDerivedFrom((CoreAssetBaseline)otherEnd, msgs);
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProducts()).basicAdd(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				return basicSetDerivedFrom(null, msgs);
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO:
				return getIdProductPortfolio();
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				if (resolve) return getDerivedFrom();
				return basicGetDerivedFrom();
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				return getProducts();
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO:
				setIdProductPortfolio((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				setDerivedFrom((CoreAssetBaseline)newValue);
				return;
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO:
				setIdProductPortfolio(ID_PRODUCT_PORTFOLIO_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				setDerivedFrom((CoreAssetBaseline)null);
				return;
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				getProducts().clear();
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
			case SPLmodelPackage.PRODUCT_PORTFOLIO__ID_PRODUCT_PORTFOLIO:
				return ID_PRODUCT_PORTFOLIO_EDEFAULT == null ? idProductPortfolio != null : !ID_PRODUCT_PORTFOLIO_EDEFAULT.equals(idProductPortfolio);
			case SPLmodelPackage.PRODUCT_PORTFOLIO__DERIVED_FROM:
				return derivedFrom != null;
			case SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS:
				return products != null && !products.isEmpty();
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
		result.append(" (idProductPortfolio: ");
		result.append(idProductPortfolio);
		result.append(')');
		return result.toString();
	}

} //ProductPortfolioImpl
