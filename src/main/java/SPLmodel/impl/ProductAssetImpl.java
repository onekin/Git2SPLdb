/**
 */
package SPLmodel.impl;

import SPLmodel.ProductAsset;
import SPLmodel.ProductRelease;
import SPLmodel.SPLmodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Product Asset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getIdAsset <em>Id Asset</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getPath <em>Path</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getContent <em>Content</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getSize <em>Size</em>}</li>
 *   <li>{@link SPLmodel.impl.ProductAssetImpl#getFromRelease <em>From Release</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProductAssetImpl extends MinimalEObjectImpl.Container implements ProductAsset {
	/**
	 * The default value of the '{@link #getIdAsset() <em>Id Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdAsset()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_ASSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdAsset() <em>Id Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdAsset()
	 * @generated
	 * @ordered
	 */
	protected String idAsset = ID_ASSET_EDEFAULT;

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
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContent() <em>Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContent()
	 * @generated
	 * @ordered
	 */
	protected String content = CONTENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final int SIZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected int size = SIZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFromRelease() <em>From Release</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFromRelease()
	 * @generated
	 * @ordered
	 */
	protected ProductRelease fromRelease;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductAssetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.PRODUCT_ASSET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdAsset() {
		return idAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdAsset(String newIdAsset) {
		String oldIdAsset = idAsset;
		idAsset = newIdAsset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__ID_ASSET, oldIdAsset, idAsset));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContent() {
		return content;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContent(String newContent) {
		String oldContent = content;
		content = newContent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__CONTENT, oldContent, content));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getSize() {
		return size;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSize(int newSize) {
		int oldSize = size;
		size = newSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductRelease getFromRelease() {
		if (fromRelease != null && fromRelease.eIsProxy()) {
			InternalEObject oldFromRelease = (InternalEObject)fromRelease;
			fromRelease = (ProductRelease)eResolveProxy(oldFromRelease);
			if (fromRelease != oldFromRelease) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE, oldFromRelease, fromRelease));
			}
		}
		return fromRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProductRelease basicGetFromRelease() {
		return fromRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFromRelease(ProductRelease newFromRelease, NotificationChain msgs) {
		ProductRelease oldFromRelease = fromRelease;
		fromRelease = newFromRelease;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE, oldFromRelease, newFromRelease);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFromRelease(ProductRelease newFromRelease) {
		if (newFromRelease != fromRelease) {
			NotificationChain msgs = null;
			if (fromRelease != null)
				msgs = ((InternalEObject)fromRelease).eInverseRemove(this, SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET, ProductRelease.class, msgs);
			if (newFromRelease != null)
				msgs = ((InternalEObject)newFromRelease).eInverseAdd(this, SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET, ProductRelease.class, msgs);
			msgs = basicSetFromRelease(newFromRelease, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE, newFromRelease, newFromRelease));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				if (fromRelease != null)
					msgs = ((InternalEObject)fromRelease).eInverseRemove(this, SPLmodelPackage.PRODUCT_RELEASE__PRODUCTASSET, ProductRelease.class, msgs);
				return basicSetFromRelease((ProductRelease)otherEnd, msgs);
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
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				return basicSetFromRelease(null, msgs);
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
			case SPLmodelPackage.PRODUCT_ASSET__ID_ASSET:
				return getIdAsset();
			case SPLmodelPackage.PRODUCT_ASSET__NAME:
				return getName();
			case SPLmodelPackage.PRODUCT_ASSET__PATH:
				return getPath();
			case SPLmodelPackage.PRODUCT_ASSET__CONTENT:
				return getContent();
			case SPLmodelPackage.PRODUCT_ASSET__SIZE:
				return getSize();
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				if (resolve) return getFromRelease();
				return basicGetFromRelease();
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
			case SPLmodelPackage.PRODUCT_ASSET__ID_ASSET:
				setIdAsset((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__NAME:
				setName((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__PATH:
				setPath((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__CONTENT:
				setContent((String)newValue);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__SIZE:
				setSize((Integer)newValue);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				setFromRelease((ProductRelease)newValue);
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
			case SPLmodelPackage.PRODUCT_ASSET__ID_ASSET:
				setIdAsset(ID_ASSET_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				setFromRelease((ProductRelease)null);
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
			case SPLmodelPackage.PRODUCT_ASSET__ID_ASSET:
				return ID_ASSET_EDEFAULT == null ? idAsset != null : !ID_ASSET_EDEFAULT.equals(idAsset);
			case SPLmodelPackage.PRODUCT_ASSET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SPLmodelPackage.PRODUCT_ASSET__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case SPLmodelPackage.PRODUCT_ASSET__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
			case SPLmodelPackage.PRODUCT_ASSET__SIZE:
				return size != SIZE_EDEFAULT;
			case SPLmodelPackage.PRODUCT_ASSET__FROM_RELEASE:
				return fromRelease != null;
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
		result.append(" (idAsset: ");
		result.append(idAsset);
		result.append(", name: ");
		result.append(name);
		result.append(", path: ");
		result.append(path);
		result.append(", content: ");
		result.append(content);
		result.append(", size: ");
		result.append(size);
		result.append(')');
		return result.toString();
	}

} //ProductAssetImpl
