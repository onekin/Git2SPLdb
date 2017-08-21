/**
 */
package SPLmodel.impl;

import SPLmodel.Product;
import SPLmodel.ProductAsset;
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
 * An implementation of the model object '<em><b>Product Release</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.ProductReleaseImpl#getIdRelease <em>Id Release</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductReleaseImpl#getReleaseDate <em>Release Date</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductReleaseImpl#getProduct <em>Product</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductReleaseImpl#getProductasset <em>Productasset</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductReleaseImpl extends MinimalEObjectImpl.Container implements ProductRelease {
	/**
	 * The default value of the '{@link #getIdRelease() <em>Id Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_RELEASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdRelease() <em>Id Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdRelease()
	 * @generated
	 * @ordered
	 */
	protected String idRelease = ID_RELEASE_EDEFAULT;

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
	 * The cached value of the '{@link #getProduct() <em>Product</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProduct()
	 * @generated
	 * @ordered
	 */
	protected Product product;

	/**
	 * The cached value of the '{@link #getProductasset() <em>Productasset</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProductasset()
	 * @generated
	 * @ordered
	 */
	protected EList<ProductAsset> productasset;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductReleaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.PRODUCT_RELEASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdRelease() {
		return idRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdRelease(String newIdRelease) {
		String oldIdRelease = idRelease;
		idRelease = newIdRelease;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_RELEASE__ID_RELEASE, oldIdRelease, idRelease));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_RELEASE__RELEASE_DATE, oldReleaseDate, releaseDate));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product getProduct() {
		if (product != null && product.eIsProxy()) {
			InternalEObject oldProduct = (InternalEObject)product;
			product = (Product)eResolveProxy(oldProduct);
			if (product != oldProduct) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.PRODUCT_RELEASE__PRODUCT, oldProduct, product));
			}
		}
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Product basicGetProduct() {
		return product;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProduct(Product newProduct, NotificationChain msgs) {
		Product oldProduct = product;
		product = newProduct;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_RELEASE__PRODUCT, oldProduct, newProduct);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProduct(Product newProduct) {
		if (newProduct != product) {
			NotificationChain msgs = null;
			if (product != null)
				msgs = ((InternalEObject)product).eInverseRemove(this, SPLmodelPackage.PRODUCT__RELEASES, Product.class, msgs);
			if (newProduct != null)
				msgs = ((InternalEObject)newProduct).eInverseAdd(this, SPLmodelPackage.PRODUCT__RELEASES, Product.class, msgs);
			msgs = basicSetProduct(newProduct, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_RELEASE__PRODUCT, newProduct, newProduct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProductAsset> getProductasset() {
		if (productasset == null) {
			productasset = new EObjectWithInverseResolvingEList<ProductAsset>(ProductAsset.class, this, SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET, SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE);
		}
		return productasset;
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
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				if (product != null)
					msgs = ((InternalEObject)product).eInverseRemove(this, SPLmodelPackage.PRODUCT__RELEASES, Product.class, msgs);
				return basicSetProduct((Product)otherEnd, msgs);
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProductasset()).basicAdd(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				return basicSetProduct(null, msgs);
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				return ((InternalEList<?>)getProductasset()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT_RELEASE__ID_RELEASE:
				return getIdRelease();
			case SPLmodelPackage.PRODUCT_RELEASE__RELEASE_DATE:
				return getReleaseDate();
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				if (resolve) return getProduct();
				return basicGetProduct();
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				return getProductasset();
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
			case SPLmodelPackage.PRODUCT_RELEASE__ID_RELEASE:
				setIdRelease((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__RELEASE_DATE:
				setReleaseDate((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				setProduct((Product)newValue);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				getProductasset().clear();
				getProductasset().addAll((Collection<? extends ProductAsset>)newValue);
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
			case SPLmodelPackage.PRODUCT_RELEASE__ID_RELEASE:
				setIdRelease(ID_RELEASE_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__RELEASE_DATE:
				setReleaseDate(RELEASE_DATE_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				setProduct((Product)null);
				return;
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				getProductasset().clear();
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
			case SPLmodelPackage.PRODUCT_RELEASE__ID_RELEASE:
				return ID_RELEASE_EDEFAULT == null ? idRelease != null : !ID_RELEASE_EDEFAULT.equals(idRelease);
			case SPLmodelPackage.PRODUCT_RELEASE__RELEASE_DATE:
				return RELEASE_DATE_EDEFAULT == null ? releaseDate != null : !RELEASE_DATE_EDEFAULT.equals(releaseDate);
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCT:
				return product != null;
			case SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET:
				return productasset != null && !productasset.isEmpty();
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
		result.append(" (idRelease: ");
		result.append(idRelease);
		result.append(", releaseDate: ");
		result.append(releaseDate);
		result.append(')');
		return result.toString();
	}

} //ProductReleaseImpl
