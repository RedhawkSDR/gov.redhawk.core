/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.*;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FrontendFactoryImpl extends EFactoryImpl implements FrontendFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static FrontendFactory init()
  {
    try
    {
      FrontendFactory theFrontendFactory = (FrontendFactory)EPackage.Registry.INSTANCE.getEFactory(FrontendPackage.eNS_URI);
      if (theFrontendFactory != null)
      {
        return theFrontendFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new FrontendFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FrontendFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case FrontendPackage.MODEL_DEVICE: return createModelDevice();
      case FrontendPackage.TUNER_CONTAINER: return createTunerContainer();
      case FrontendPackage.TUNER_STATUS: return createTunerStatus();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FrontendPackage.SCA_DEVICE:
        return createScaDeviceFromString(eDataType, initialValue);
      case FrontendPackage.TUNER_STATUS_STRUCT:
        return createTunerStatusStructFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case FrontendPackage.SCA_DEVICE:
        return convertScaDeviceToString(eDataType, instanceValue);
      case FrontendPackage.TUNER_STATUS_STRUCT:
        return convertTunerStatusStructToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelDevice createModelDevice()
  {
    ModelDeviceImpl modelDevice = new ModelDeviceImpl();
    return modelDevice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerContainer createTunerContainer()
  {
    TunerContainerImpl tunerContainer = new TunerContainerImpl();
    return tunerContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerStatus createTunerStatus()
  {
    TunerStatusImpl tunerStatus = new TunerStatusImpl();
    return tunerStatus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public ScaDevice<?> createScaDevice(String literal)
  {
    return (ScaDevice<?>)super.createFromString(literal);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaDevice<?> createScaDeviceFromString(EDataType eDataType, String initialValue)
  {
    return createScaDevice(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertScaDevice(ScaDevice<?> instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  public String convertScaDeviceToString(EDataType eDataType, Object instanceValue)
  {
    return convertScaDevice((ScaDevice<?>)instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaStructProperty createTunerStatusStruct(String literal)
  {
    return (ScaStructProperty)super.createFromString(FrontendPackage.Literals.TUNER_STATUS_STRUCT, literal);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaStructProperty createTunerStatusStructFromString(EDataType eDataType, String initialValue)
  {
    return createTunerStatusStruct(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTunerStatusStruct(ScaStructProperty instanceValue)
  {
    return super.convertToString(FrontendPackage.Literals.TUNER_STATUS_STRUCT, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertTunerStatusStructToString(EDataType eDataType, Object instanceValue)
  {
    return convertTunerStatusStruct((ScaStructProperty)instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FrontendPackage getFrontendPackage()
  {
    return (FrontendPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static FrontendPackage getPackage()
  {
    return FrontendPackage.eINSTANCE;
  }

} //FrontendFactoryImpl
