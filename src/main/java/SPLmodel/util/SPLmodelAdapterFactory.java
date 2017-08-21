/**
 */
package SPLmodel.util;

import SPLmodel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see SPLmodel.SPLmodelPackage
 * @generated
 */
public class SPLmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SPLmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SPLmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SPLmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SPLmodelSwitch<Adapter> modelSwitch =
		new SPLmodelSwitch<Adapter>() {
			@Override
			public Adapter caseSPL(SPL object) {
				return createSPLAdapter();
			}
			@Override
			public Adapter caseCoreAssetBaseline(CoreAssetBaseline object) {
				return createCoreAssetBaselineAdapter();
			}
			@Override
			public Adapter caseFeature(Feature object) {
				return createFeatureAdapter();
			}
			@Override
			public Adapter caseCoreAsset(CoreAsset object) {
				return createCoreAssetAdapter();
			}
			@Override
			public Adapter caseCustomizationEffort(CustomizationEffort object) {
				return createCustomizationEffortAdapter();
			}
			@Override
			public Adapter caseProductPortfolio(ProductPortfolio object) {
				return createProductPortfolioAdapter();
			}
			@Override
			public Adapter caseProduct(Product object) {
				return createProductAdapter();
			}
			@Override
			public Adapter caseProductRelease(ProductRelease object) {
				return createProductReleaseAdapter();
			}
			@Override
			public Adapter caseProductAsset(ProductAsset object) {
				return createProductAssetAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.SPL <em>SPL</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.SPL
	 * @generated
	 */
	public Adapter createSPLAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.CoreAssetBaseline <em>Core Asset Baseline</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.CoreAssetBaseline
	 * @generated
	 */
	public Adapter createCoreAssetBaselineAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.CoreAsset <em>Core Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.CoreAsset
	 * @generated
	 */
	public Adapter createCoreAssetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.CustomizationEffort <em>Customization Effort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.CustomizationEffort
	 * @generated
	 */
	public Adapter createCustomizationEffortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.ProductPortfolio <em>Product Portfolio</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.ProductPortfolio
	 * @generated
	 */
	public Adapter createProductPortfolioAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.Product <em>Product</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.Product
	 * @generated
	 */
	public Adapter createProductAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.ProductRelease <em>Product Release</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.ProductRelease
	 * @generated
	 */
	public Adapter createProductReleaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link SPLmodel.ProductAsset <em>Product Asset</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see SPLmodel.ProductAsset
	 * @generated
	 */
	public Adapter createProductAssetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SPLmodelAdapterFactory
