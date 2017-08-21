/**
 */
package SPLmodel.impl;

import SPLmodel.CoreAsset;
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
 * An implementation of the model object '<em><b>Core Asset</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getIdCoreAsset <em>Id Core Asset</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getName <em>Name</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getPath <em>Path</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getContent <em>Content</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getSize <em>Size</em>}</li>
 *   <li>{@link SPLmodel.impl.CoreAssetImpl#getMappedTo <em>Mapped To</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CoreAssetImpl extends MinimalEObjectImpl.Container implements CoreAsset {
	/**
	 * The default value of the '{@link #getIdCoreAsset() <em>Id Core Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdCoreAsset()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_CORE_ASSET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdCoreAsset() <em>Id Core Asset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdCoreAsset()
	 * @generated
	 * @ordered
	 */
	protected String idCoreAsset = ID_CORE_ASSET_EDEFAULT;

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
	 * The cached value of the '{@link #getMappedTo() <em>Mapped To</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedTo()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> mappedTo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CoreAssetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SPLmodelPackage.Literals.CORE_ASSET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdCoreAsset() {
		return idCoreAsset;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdCoreAsset(String newIdCoreAsset) {
		String oldIdCoreAsset = idCoreAsset;
		idCoreAsset = newIdCoreAsset;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET__ID_CORE_ASSET, oldIdCoreAsset, idCoreAsset));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET__PATH, oldPath, path));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET__CONTENT, oldContent, content));
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
			eNotify(new ENotificationImpl(this, Notification.SET, SPLmodelPackage.CORE_ASSET__SIZE, oldSize, size));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getMappedTo() {
		if (mappedTo == null) {
			mappedTo = new EObjectWithInverseResolvingEList.ManyInverse<Feature>(Feature.class, this, SPLmodelPackage.CORE_ASSET__MAPPED_TO, SPLmodelPackage.FEATURE__CORE_ASSETS);
		}
		return mappedTo;
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
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMappedTo()).basicAdd(otherEnd, msgs);
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
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				return ((InternalEList<?>)getMappedTo()).basicRemove(otherEnd, msgs);
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
			case SPLmodelPackage.CORE_ASSET__ID_CORE_ASSET:
				return getIdCoreAsset();
			case SPLmodelPackage.CORE_ASSET__NAME:
				return getName();
			case SPLmodelPackage.CORE_ASSET__PATH:
				return getPath();
			case SPLmodelPackage.CORE_ASSET__CONTENT:
				return getContent();
			case SPLmodelPackage.CORE_ASSET__SIZE:
				return getSize();
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				return getMappedTo();
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
			case SPLmodelPackage.CORE_ASSET__ID_CORE_ASSET:
				setIdCoreAsset((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET__NAME:
				setName((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET__PATH:
				setPath((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET__CONTENT:
				setContent((String)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET__SIZE:
				setSize((Integer)newValue);
				return;
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				getMappedTo().clear();
				getMappedTo().addAll((Collection<? extends Feature>)newValue);
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
			case SPLmodelPackage.CORE_ASSET__ID_CORE_ASSET:
				setIdCoreAsset(ID_CORE_ASSET_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET__CONTENT:
				setContent(CONTENT_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				getMappedTo().clear();
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
			case SPLmodelPackage.CORE_ASSET__ID_CORE_ASSET:
				return ID_CORE_ASSET_EDEFAULT == null ? idCoreAsset != null : !ID_CORE_ASSET_EDEFAULT.equals(idCoreAsset);
			case SPLmodelPackage.CORE_ASSET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case SPLmodelPackage.CORE_ASSET__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case SPLmodelPackage.CORE_ASSET__CONTENT:
				return CONTENT_EDEFAULT == null ? content != null : !CONTENT_EDEFAULT.equals(content);
			case SPLmodelPackage.CORE_ASSET__SIZE:
				return size != SIZE_EDEFAULT;
			case SPLmodelPackage.CORE_ASSET__MAPPED_TO:
				return mappedTo != null && !mappedTo.isEmpty();
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
		result.append(" (idCoreAsset: ");
		result.append(idCoreAsset);
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

} //CoreAssetImpl
