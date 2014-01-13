/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FrontendPackageImpl extends EPackageImpl implements FrontendPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelDeviceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tunerContainerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tunerEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tunerStatusEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType scaDeviceEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType tunerStructEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see gov.redhawk.frontend.FrontendPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private FrontendPackageImpl()
  {
    super(eNS_URI, FrontendFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link FrontendPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static FrontendPackage init()
  {
    if (isInited) return (FrontendPackage)EPackage.Registry.INSTANCE.getEPackage(FrontendPackage.eNS_URI);

    // Obtain or create and register package
    FrontendPackageImpl theFrontendPackage = (FrontendPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FrontendPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new FrontendPackageImpl());

    isInited = true;

    // Initialize simple dependencies
    EcorePackage.eINSTANCE.eClass();

    // Create package meta-data objects
    theFrontendPackage.createPackageContents();

    // Initialize created meta-data
    theFrontendPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theFrontendPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(FrontendPackage.eNS_URI, theFrontendPackage);
    return theFrontendPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelDevice()
  {
    return modelDeviceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelDevice_TunerContainer()
  {
    return (EReference)modelDeviceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModelDevice_ScaDevice()
  {
    return (EAttribute)modelDeviceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTunerContainer()
  {
    return tunerContainerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTunerContainer_ModelDevice()
  {
    return (EReference)tunerContainerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTunerContainer_Tuners()
  {
    return (EReference)tunerContainerEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTuner()
  {
    return tunerEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_TunerStruct()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_AllocationID()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_TunerType()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_TunerID()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_DeviceControl()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_GroupID()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_RfFlowID()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTuner_TunerContainer()
  {
    return (EReference)tunerEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTuner_TunerStatus()
  {
    return (EReference)tunerEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTuner_Gain()
  {
    return (EAttribute)tunerEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTunerStatus()
  {
    return tunerStatusEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTunerStatus_Tuner()
  {
    return (EReference)tunerStatusEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTunerStatus_CenterFrequency()
  {
    return (EAttribute)tunerStatusEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTunerStatus_Bandwidth()
  {
    return (EAttribute)tunerStatusEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTunerStatus_SampleRate()
  {
    return (EAttribute)tunerStatusEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTunerStatus_Enabled()
  {
    return (EAttribute)tunerStatusEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getScaDevice()
  {
    return scaDeviceEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getTunerStruct()
  {
    return tunerStructEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FrontendFactory getFrontendFactory()
  {
    return (FrontendFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    modelDeviceEClass = createEClass(MODEL_DEVICE);
    createEReference(modelDeviceEClass, MODEL_DEVICE__TUNER_CONTAINER);
    createEAttribute(modelDeviceEClass, MODEL_DEVICE__SCA_DEVICE);

    tunerContainerEClass = createEClass(TUNER_CONTAINER);
    createEReference(tunerContainerEClass, TUNER_CONTAINER__MODEL_DEVICE);
    createEReference(tunerContainerEClass, TUNER_CONTAINER__TUNERS);

    tunerEClass = createEClass(TUNER);
    createEAttribute(tunerEClass, TUNER__TUNER_STRUCT);
    createEAttribute(tunerEClass, TUNER__ALLOCATION_ID);
    createEAttribute(tunerEClass, TUNER__TUNER_TYPE);
    createEAttribute(tunerEClass, TUNER__TUNER_ID);
    createEAttribute(tunerEClass, TUNER__DEVICE_CONTROL);
    createEAttribute(tunerEClass, TUNER__GROUP_ID);
    createEAttribute(tunerEClass, TUNER__RF_FLOW_ID);
    createEReference(tunerEClass, TUNER__TUNER_CONTAINER);
    createEReference(tunerEClass, TUNER__TUNER_STATUS);
    createEAttribute(tunerEClass, TUNER__GAIN);

    tunerStatusEClass = createEClass(TUNER_STATUS);
    createEReference(tunerStatusEClass, TUNER_STATUS__TUNER);
    createEAttribute(tunerStatusEClass, TUNER_STATUS__CENTER_FREQUENCY);
    createEAttribute(tunerStatusEClass, TUNER_STATUS__BANDWIDTH);
    createEAttribute(tunerStatusEClass, TUNER_STATUS__SAMPLE_RATE);
    createEAttribute(tunerStatusEClass, TUNER_STATUS__ENABLED);

    // Create data types
    scaDeviceEDataType = createEDataType(SCA_DEVICE);
    tunerStructEDataType = createEDataType(TUNER_STRUCT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes, features, and operations; add parameters
    initEClass(modelDeviceEClass, ModelDevice.class, "ModelDevice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelDevice_TunerContainer(), this.getTunerContainer(), this.getTunerContainer_ModelDevice(), "tunerContainer", null, 0, 1, ModelDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getModelDevice_ScaDevice(), this.getScaDevice(), "scaDevice", null, 0, 1, ModelDevice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tunerContainerEClass, TunerContainer.class, "TunerContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTunerContainer_ModelDevice(), this.getModelDevice(), this.getModelDevice_TunerContainer(), "modelDevice", null, 0, 1, TunerContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTunerContainer_Tuners(), this.getTuner(), this.getTuner_TunerContainer(), "tuners", null, 0, -1, TunerContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tunerEClass, Tuner.class, "Tuner", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTuner_TunerStruct(), this.getTunerStruct(), "tunerStruct", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_AllocationID(), theEcorePackage.getEString(), "allocationID", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_TunerType(), theEcorePackage.getEString(), "tunerType", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_TunerID(), theEcorePackage.getEString(), "tunerID", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_DeviceControl(), theEcorePackage.getEBoolean(), "deviceControl", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_GroupID(), theEcorePackage.getEString(), "groupID", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_RfFlowID(), theEcorePackage.getEString(), "rfFlowID", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTuner_TunerContainer(), this.getTunerContainer(), this.getTunerContainer_Tuners(), "tunerContainer", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTuner_TunerStatus(), this.getTunerStatus(), this.getTunerStatus_Tuner(), "tunerStatus", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTuner_Gain(), theEcorePackage.getEDouble(), "gain", null, 0, 1, Tuner.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tunerStatusEClass, TunerStatus.class, "TunerStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTunerStatus_Tuner(), this.getTuner(), this.getTuner_TunerStatus(), "tuner", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTunerStatus_CenterFrequency(), theEcorePackage.getEDouble(), "centerFrequency", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTunerStatus_Bandwidth(), theEcorePackage.getEDouble(), "bandwidth", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTunerStatus_SampleRate(), theEcorePackage.getEDouble(), "sampleRate", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTunerStatus_Enabled(), theEcorePackage.getEBoolean(), "enabled", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize data types
    initEDataType(scaDeviceEDataType, ScaDevice.class, "ScaDevice", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS, "gov.redhawk.model.sca.ScaDevice<?>");
    initEDataType(tunerStructEDataType, ScaStructProperty.class, "TunerStruct", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

    // Create resource
    createResource(eNS_URI);
  }

} //FrontendPackageImpl
