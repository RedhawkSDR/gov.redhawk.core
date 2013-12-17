/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.frontend.FrontendFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel prefix='Frontend' dataTypeConverters='true' binaryCompantibleReflectiveMethods='true' fileExtensions='xml' colorProviders='true' fontProviders='true' tablesProviders='true' resource='XML' templateDirectory='/gov.redhawk.frontend/templates' forceOverwrite='true' modelPluginVariables='org.eclipse.xtext.xbase.lib' contentTypeIdentifier='http://redhawk.gov/frontend/1.0.0' editDirectory='/gov.redhawk.frontend.edit/src-gen' basePackage='gov.redhawk'"
 * @generated
 */
public interface FrontendPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "frontend";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://redhawk.gov/frontend/1.0.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "frontend";

  /**
   * The package content type ID.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eCONTENT_TYPE = "http://redhawk.gov/frontend/1.0.0";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FrontendPackage eINSTANCE = gov.redhawk.frontend.impl.FrontendPackageImpl.init();

  /**
   * The meta object id for the '{@link gov.redhawk.frontend.impl.AnalogDeviceImpl <em>Analog Device</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.frontend.impl.AnalogDeviceImpl
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getAnalogDevice()
   * @generated
   */
  int ANALOG_DEVICE = 0;

  /**
   * The feature id for the '<em><b>Tuners</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALOG_DEVICE__TUNERS = 0;

  /**
   * The number of structural features of the '<em>Analog Device</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALOG_DEVICE_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Analog Device</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANALOG_DEVICE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link gov.redhawk.frontend.impl.TunerImpl <em>Tuner</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.frontend.impl.TunerImpl
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTuner()
   * @generated
   */
  int TUNER = 1;

  /**
   * The feature id for the '<em><b>Analog Device</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__ANALOG_DEVICE = 0;

  /**
   * The feature id for the '<em><b>Allocation ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__ALLOCATION_ID = 1;

  /**
   * The feature id for the '<em><b>Tuner Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__TUNER_TYPE = 2;

  /**
   * The feature id for the '<em><b>Device Control</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__DEVICE_CONTROL = 3;

  /**
   * The feature id for the '<em><b>Group ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__GROUP_ID = 4;

  /**
   * The feature id for the '<em><b>Rf Flow ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__RF_FLOW_ID = 5;

  /**
   * The feature id for the '<em><b>Tuner Status</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__TUNER_STATUS = 6;

  /**
   * The feature id for the '<em><b>Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER__GAIN = 7;

  /**
   * The number of structural features of the '<em>Tuner</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_FEATURE_COUNT = 8;

  /**
   * The number of operations of the '<em>Tuner</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link gov.redhawk.frontend.impl.TunerStatusImpl <em>Tuner Status</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.frontend.impl.TunerStatusImpl
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatus()
   * @generated
   */
  int TUNER_STATUS = 2;

  /**
   * The feature id for the '<em><b>Tuner</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER = 0;

  /**
   * The feature id for the '<em><b>Center Frequency</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__CENTER_FREQUENCY = 1;

  /**
   * The feature id for the '<em><b>Bandwidth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__BANDWIDTH = 2;

  /**
   * The feature id for the '<em><b>Sample Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__SAMPLE_RATE = 3;

  /**
   * The feature id for the '<em><b>Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__ENABLED = 4;

  /**
   * The number of structural features of the '<em>Tuner Status</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS_FEATURE_COUNT = 5;

  /**
   * The number of operations of the '<em>Tuner Status</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS_OPERATION_COUNT = 0;


  /**
   * Returns the meta object for class '{@link gov.redhawk.frontend.AnalogDevice <em>Analog Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Analog Device</em>'.
   * @see gov.redhawk.frontend.AnalogDevice
   * @generated
   */
  EClass getAnalogDevice();

  /**
   * Returns the meta object for the containment reference list '{@link gov.redhawk.frontend.AnalogDevice#getTuners <em>Tuners</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tuners</em>'.
   * @see gov.redhawk.frontend.AnalogDevice#getTuners()
   * @see #getAnalogDevice()
   * @generated
   */
  EReference getAnalogDevice_Tuners();

  /**
   * Returns the meta object for class '{@link gov.redhawk.frontend.Tuner <em>Tuner</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuner</em>'.
   * @see gov.redhawk.frontend.Tuner
   * @generated
   */
  EClass getTuner();

  /**
   * Returns the meta object for the container reference '{@link gov.redhawk.frontend.Tuner#getAnalogDevice <em>Analog Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Analog Device</em>'.
   * @see gov.redhawk.frontend.Tuner#getAnalogDevice()
   * @see #getTuner()
   * @generated
   */
  EReference getTuner_AnalogDevice();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#getAllocationID <em>Allocation ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Allocation ID</em>'.
   * @see gov.redhawk.frontend.Tuner#getAllocationID()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_AllocationID();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#getTunerType <em>Tuner Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tuner Type</em>'.
   * @see gov.redhawk.frontend.Tuner#getTunerType()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_TunerType();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#isDeviceControl <em>Device Control</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Device Control</em>'.
   * @see gov.redhawk.frontend.Tuner#isDeviceControl()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_DeviceControl();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#getGroupID <em>Group ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Group ID</em>'.
   * @see gov.redhawk.frontend.Tuner#getGroupID()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_GroupID();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#getRfFlowID <em>Rf Flow ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Rf Flow ID</em>'.
   * @see gov.redhawk.frontend.Tuner#getRfFlowID()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_RfFlowID();

  /**
   * Returns the meta object for the containment reference '{@link gov.redhawk.frontend.Tuner#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tuner Status</em>'.
   * @see gov.redhawk.frontend.Tuner#getTunerStatus()
   * @see #getTuner()
   * @generated
   */
  EReference getTuner_TunerStatus();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.Tuner#getGain <em>Gain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Gain</em>'.
   * @see gov.redhawk.frontend.Tuner#getGain()
   * @see #getTuner()
   * @generated
   */
  EAttribute getTuner_Gain();

  /**
   * Returns the meta object for class '{@link gov.redhawk.frontend.TunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuner Status</em>'.
   * @see gov.redhawk.frontend.TunerStatus
   * @generated
   */
  EClass getTunerStatus();

  /**
   * Returns the meta object for the container reference '{@link gov.redhawk.frontend.TunerStatus#getTuner <em>Tuner</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Tuner</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTuner()
   * @see #getTunerStatus()
   * @generated
   */
  EReference getTunerStatus_Tuner();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Center Frequency</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getCenterFrequency()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_CenterFrequency();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bandwidth</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getBandwidth()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Bandwidth();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sample Rate</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getSampleRate()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_SampleRate();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Enabled</em>'.
   * @see gov.redhawk.frontend.TunerStatus#isEnabled()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Enabled();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  FrontendFactory getFrontendFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link gov.redhawk.frontend.impl.AnalogDeviceImpl <em>Analog Device</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.frontend.impl.AnalogDeviceImpl
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getAnalogDevice()
     * @generated
     */
    EClass ANALOG_DEVICE = eINSTANCE.getAnalogDevice();

    /**
     * The meta object literal for the '<em><b>Tuners</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANALOG_DEVICE__TUNERS = eINSTANCE.getAnalogDevice_Tuners();

    /**
     * The meta object literal for the '{@link gov.redhawk.frontend.impl.TunerImpl <em>Tuner</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.frontend.impl.TunerImpl
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTuner()
     * @generated
     */
    EClass TUNER = eINSTANCE.getTuner();

    /**
     * The meta object literal for the '<em><b>Analog Device</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER__ANALOG_DEVICE = eINSTANCE.getTuner_AnalogDevice();

    /**
     * The meta object literal for the '<em><b>Allocation ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__ALLOCATION_ID = eINSTANCE.getTuner_AllocationID();

    /**
     * The meta object literal for the '<em><b>Tuner Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__TUNER_TYPE = eINSTANCE.getTuner_TunerType();

    /**
     * The meta object literal for the '<em><b>Device Control</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__DEVICE_CONTROL = eINSTANCE.getTuner_DeviceControl();

    /**
     * The meta object literal for the '<em><b>Group ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__GROUP_ID = eINSTANCE.getTuner_GroupID();

    /**
     * The meta object literal for the '<em><b>Rf Flow ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__RF_FLOW_ID = eINSTANCE.getTuner_RfFlowID();

    /**
     * The meta object literal for the '<em><b>Tuner Status</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER__TUNER_STATUS = eINSTANCE.getTuner_TunerStatus();

    /**
     * The meta object literal for the '<em><b>Gain</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER__GAIN = eINSTANCE.getTuner_Gain();

    /**
     * The meta object literal for the '{@link gov.redhawk.frontend.impl.TunerStatusImpl <em>Tuner Status</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.frontend.impl.TunerStatusImpl
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatus()
     * @generated
     */
    EClass TUNER_STATUS = eINSTANCE.getTunerStatus();

    /**
     * The meta object literal for the '<em><b>Tuner</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER_STATUS__TUNER = eINSTANCE.getTunerStatus_Tuner();

    /**
     * The meta object literal for the '<em><b>Center Frequency</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__CENTER_FREQUENCY = eINSTANCE.getTunerStatus_CenterFrequency();

    /**
     * The meta object literal for the '<em><b>Bandwidth</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__BANDWIDTH = eINSTANCE.getTunerStatus_Bandwidth();

    /**
     * The meta object literal for the '<em><b>Sample Rate</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__SAMPLE_RATE = eINSTANCE.getTunerStatus_SampleRate();

    /**
     * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__ENABLED = eINSTANCE.getTunerStatus_Enabled();

  }

} //FrontendPackage
