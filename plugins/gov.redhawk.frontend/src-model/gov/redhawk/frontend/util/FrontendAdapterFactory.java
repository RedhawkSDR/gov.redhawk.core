/**
 */
package gov.redhawk.frontend.util;

import gov.redhawk.frontend.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.frontend.FrontendPackage
 * @generated
 */
public class FrontendAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static FrontendPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FrontendAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = FrontendPackage.eINSTANCE;
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
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
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
  protected FrontendSwitch<Adapter> modelSwitch =
    new FrontendSwitch<Adapter>()
    {
      @Override
      public Adapter caseModelDevice(ModelDevice object)
      {
        return createModelDeviceAdapter();
      }
      @Override
      public Adapter caseTunerContainer(TunerContainer object)
      {
        return createTunerContainerAdapter();
      }
      @Override
      public Adapter caseUnallocatedTunerContainer(UnallocatedTunerContainer object)
      {
        return createUnallocatedTunerContainerAdapter();
      }
      @Override
      public Adapter caseTunerStatus(TunerStatus object)
      {
        return createTunerStatusAdapter();
      }
      @Override
      public Adapter casePlot(Plot object)
      {
        return createPlotAdapter();
      }
      @Override
      public Adapter caseListenerAllocation(ListenerAllocation object)
      {
        return createListenerAllocationAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
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
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.ModelDevice <em>Model Device</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.ModelDevice
   * @generated
   */
  public Adapter createModelDeviceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.TunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.TunerContainer
   * @generated
   */
  public Adapter createTunerContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.UnallocatedTunerContainer <em>Unallocated Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.UnallocatedTunerContainer
   * @generated
   */
  public Adapter createUnallocatedTunerContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.TunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.TunerStatus
   * @generated
   */
  public Adapter createTunerStatusAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.Plot <em>Plot</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.Plot
   * @generated
   */
  public Adapter createPlotAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link gov.redhawk.frontend.ListenerAllocation <em>Listener Allocation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see gov.redhawk.frontend.ListenerAllocation
   * @generated
   */
  public Adapter createListenerAllocationAdapter()
  {
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
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //FrontendAdapterFactory
