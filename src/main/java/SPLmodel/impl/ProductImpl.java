/**
 */
package SPLmodel.impl;

import SPLmodel.Product;
import SPLmodel.ProductPortfolio;
import SPLmodel.ProductRelease;
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
 * An implementation of the model object '<em><b>Product</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.ProductImpl#getIdProduct <em>Id Product</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductImpl#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductImpl#getInPortfolio <em>In Portfolio</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductImpl#getReleases <em>Releases</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductImpl extends MinimalEObjectImpl.Container implements Product {
	/**
	 * The default value of the '{@link #getIdProduct() <em>Id Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdProduct()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_PRODUCT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdProduct() <em>Id Product</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdProduct()
	 * @generated
	 * @ordered
	 */
	protected String idProduct = ID_PRODUCT_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The cached value of the '{@link #getInPortfolio() <em>In Portfolio</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInPortfolio()
	 * @generated
	 * @ordered
	 */
	protected ProductPortfolio inPortfolio;

	/**
	 * The cached value of the '{@link #getReleases() <em>Releases</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReleases()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductRelease> releases;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.PRODUCT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdProduct() {
		return idProduct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdProduct(String newIdProduct) {
		String oldIdProduct = idProduct;
		idProduct = newIdProduct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT__ID_PRODUCT, oldIdProduct, idProduct));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductPortfolio getInPortfolio() {
		if (inPortfolio != null && inPortfolio.eIsProxy()) {
			InternalEObject oldInPortfolio = (InternalEObject)inPortfolio;
			inPortfolio = (ProductPortfolio)eResolveProxy(oldInPortfolio);
			if (inPortfolio != oldInPortfolio) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.PRODUCT__IN_PORTFOLIO, oldInPortfolio, inPortfolio));
			}
		}
		return inPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductPortfolio basicGetInPortfolio() {
		return inPortfolio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInPortfolio(ProductPortfolio newInPortfolio, NotificationChain msgs) {
		ProductPortfolio oldInPortfolio = inPortfolio;
		inPortfolio = newInPortfolio;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT__IN_PORTFOLIO, oldInPortfolio, newInPortfolio);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInPortfolio(ProductPortfolio newInPortfolio) {
		if (newInPortfolio != inPortfolio) {
			NotificationChain msgs = null;
			if (inPortfolio != null)
				msgs = ((InternalEObject)inPortfolio).eInverseRemove(this, SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS, ProductPortfolio.class, msgs);
			if (newInPortfolio != null)
				msgs = ((InternalEObject)newInPortfolio).eInverseAdd(this, SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS, ProductPortfolio.class, msgs);
			msgs = basicSetInPortfolio(newInPortfolio, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT__IN_PORTFOLIO, newInPortfolio, newInPortfolio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductRelease> getReleases() {
		if (releases == null) {
			releases = new EObjectWithInverseResolvingEList<ProductRelease>(ProductRelease.class, this, SPLmodelPackage.PRODUCT__RELEASES, SPLmodelPackage.PRODUCT_RELEASE__PRODUCT);
		}
		return releases;
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
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				if (inPortfolio != null)
					msgs = ((InternalEObject)inPortfolio).eInverseRemove(this, SPLmodelPackage.PRODUCT_PORTFOLIO__PRODUCTS, ProductPortfolio.class, msgs);
				return basicSetInPortfolio((ProductPortfolio)otherEnd, msgs);
			case SPLmodelPackage.PRODUCT__RELEASES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getReleases()).basicAdd(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				return basicSetInPortfolio(null, msgs);
			case SPLmodelPackage.PRODUCT__RELEASES:
				return ((InternalEList<?>)getReleases()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT__ID_PRODUCT:
				return getIdProduct();
			case SPLmodelPackage.PRODUCT__NAME:
				return getName();
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				if (resolve) return getInPortfolio();
				return basicGetInPortfolio();
			case SPLmodelPackage.PRODUCT__RELEASES:
				return getReleases();
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
			case SPLmodelPackage.PRODUCT__ID_PRODUCT:
				setIdProduct((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT__NAME:
				setName((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				setInPortfolio((ProductPortfolio)newValue);
				return;
			case SPLmodelPackage.PRODUCT__RELEASES:
				getReleases().clear();
				getReleases().addAll((Collection<? extends ProductRelease>)newValue);
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
			case SPLmodelPackage.PRODUCT__ID_PRODUCT:
				setIdProduct(ID_PRODUCT_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				setInPortfolio((ProductPortfolio)null);
				return;
			case SPLmodelPackage.PRODUCT__RELEASES:
				getReleases().clear();
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
			case SPLmodelPackage.PRODUCT__ID_PRODUCT:
				return ID_PRODUCT_EDEFAULT == null ? idProduct != null : !ID_PRODUCT_EDEFAULT.equals(idProduct);
			case SPLmodelPackage.PRODUCT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SPLmodelPackage.PRODUCT__IN_PORTFOLIO:
				return inPortfolio != null;
			case SPLmodelPackage.PRODUCT__RELEASES:
				return releases != null && !releases.isEmpty();
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
		result.append(" (idProduct: ");
		result.append(idProduct);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ProductImpl
