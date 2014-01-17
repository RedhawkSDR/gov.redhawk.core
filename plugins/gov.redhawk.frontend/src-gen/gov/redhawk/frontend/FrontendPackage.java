/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
   * The meta object id for the '{@link gov.redhawk.frontend.impl.ModelDeviceImpl <em>Model Device</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.frontend.impl.ModelDeviceImpl
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getModelDevice()
   * @generated
   */
  int MODEL_DEVICE = 0;

  /**
   * The feature id for the '<em><b>Tuner Container</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_DEVICE__TUNER_CONTAINER = 0;

  /**
   * The feature id for the '<em><b>Sca Device</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_DEVICE__SCA_DEVICE = 1;

  /**
   * The number of structural features of the '<em>Model Device</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_DEVICE_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Model Device</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_DEVICE_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link gov.redhawk.frontend.impl.TunerContainerImpl <em>Tuner Container</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.frontend.impl.TunerContainerImpl
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerContainer()
   * @generated
   */
  int TUNER_CONTAINER = 1;

  /**
   * The feature id for the '<em><b>Model Device</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_CONTAINER__MODEL_DEVICE = 0;

  /**
   * The feature id for the '<em><b>Tuner Status</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_CONTAINER__TUNER_STATUS = 1;

  /**
   * The number of structural features of the '<em>Tuner Container</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_CONTAINER_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Tuner Container</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_CONTAINER_OPERATION_COUNT = 0;

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
   * The feature id for the '<em><b>Tuner Container</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER_CONTAINER = 0;

  /**
   * The feature id for the '<em><b>Tuner Status Struct</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER_STATUS_STRUCT = 1;

  /**
   * The feature id for the '<em><b>Tuner Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER_TYPE = 2;

  /**
   * The feature id for the '<em><b>Allocation ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__ALLOCATION_ID = 3;

  /**
   * The feature id for the '<em><b>Center Frequency</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__CENTER_FREQUENCY = 4;

  /**
   * The feature id for the '<em><b>Bandwidth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__BANDWIDTH = 5;

  /**
   * The feature id for the '<em><b>Sample Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__SAMPLE_RATE = 6;

  /**
   * The feature id for the '<em><b>Group ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__GROUP_ID = 7;

  /**
   * The feature id for the '<em><b>Rf Flow ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__RF_FLOW_ID = 8;

  /**
   * The feature id for the '<em><b>Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__ENABLED = 9;

  /**
   * The feature id for the '<em><b>Bandwidth Tolerance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__BANDWIDTH_TOLERANCE = 10;

  /**
   * The feature id for the '<em><b>Sample Rate Tolerance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__SAMPLE_RATE_TOLERANCE = 11;

  /**
   * The feature id for the '<em><b>Complex</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__COMPLEX = 12;

  /**
   * The feature id for the '<em><b>Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__GAIN = 13;

  /**
   * The feature id for the '<em><b>Agc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__AGC = 14;

  /**
   * The feature id for the '<em><b>Valid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__VALID = 15;

  /**
   * The feature id for the '<em><b>Available Frequency</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__AVAILABLE_FREQUENCY = 16;

  /**
   * The feature id for the '<em><b>Available Bandwidth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__AVAILABLE_BANDWIDTH = 17;

  /**
   * The feature id for the '<em><b>Available Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__AVAILABLE_GAIN = 18;

  /**
   * The feature id for the '<em><b>Available Sample Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__AVAILABLE_SAMPLE_RATE = 19;

  /**
   * The feature id for the '<em><b>Reference Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__REFERENCE_SOURCE = 20;

  /**
   * The feature id for the '<em><b>Output Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__OUTPUT_FORMAT = 21;

  /**
   * The feature id for the '<em><b>Output Multicast</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__OUTPUT_MULTICAST = 22;

  /**
   * The feature id for the '<em><b>Output Vlan</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__OUTPUT_VLAN = 23;

  /**
   * The feature id for the '<em><b>Output Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__OUTPUT_PORT = 24;

  /**
   * The feature id for the '<em><b>Decimation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__DECIMATION = 25;

  /**
   * The feature id for the '<em><b>Tuner number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER_NUMBER = 26;

  /**
   * The feature id for the '<em><b>Device Control</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__DEVICE_CONTROL = 27;

  /**
   * The feature id for the '<em><b>Tuner ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS__TUNER_ID = 28;

  /**
   * The number of structural features of the '<em>Tuner Status</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS_FEATURE_COUNT = 29;

  /**
   * The number of operations of the '<em>Tuner Status</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUNER_STATUS_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '<em>Sca Device</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.model.sca.ScaDevice
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaDevice()
   * @generated
   */
  int SCA_DEVICE = 3;

  /**
   * The meta object id for the '<em>Tuner Status Struct</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see gov.redhawk.model.sca.ScaStructProperty
   * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatusStruct()
   * @generated
   */
  int TUNER_STATUS_STRUCT = 4;


  /**
   * Returns the meta object for class '{@link gov.redhawk.frontend.ModelDevice <em>Model Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Device</em>'.
   * @see gov.redhawk.frontend.ModelDevice
   * @generated
   */
  EClass getModelDevice();

  /**
   * Returns the meta object for the containment reference '{@link gov.redhawk.frontend.ModelDevice#getTunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tuner Container</em>'.
   * @see gov.redhawk.frontend.ModelDevice#getTunerContainer()
   * @see #getModelDevice()
   * @generated
   */
  EReference getModelDevice_TunerContainer();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.ModelDevice#getScaDevice <em>Sca Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sca Device</em>'.
   * @see gov.redhawk.frontend.ModelDevice#getScaDevice()
   * @see #getModelDevice()
   * @generated
   */
  EAttribute getModelDevice_ScaDevice();

  /**
   * Returns the meta object for class '{@link gov.redhawk.frontend.TunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuner Container</em>'.
   * @see gov.redhawk.frontend.TunerContainer
   * @generated
   */
  EClass getTunerContainer();

  /**
   * Returns the meta object for the container reference '{@link gov.redhawk.frontend.TunerContainer#getModelDevice <em>Model Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Model Device</em>'.
   * @see gov.redhawk.frontend.TunerContainer#getModelDevice()
   * @see #getTunerContainer()
   * @generated
   */
  EReference getTunerContainer_ModelDevice();

  /**
   * Returns the meta object for the containment reference list '{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tuner Status</em>'.
   * @see gov.redhawk.frontend.TunerContainer#getTunerStatus()
   * @see #getTunerContainer()
   * @generated
   */
  EReference getTunerContainer_TunerStatus();

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
   * Returns the meta object for the container reference '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Tuner Container</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTunerContainer()
   * @see #getTunerStatus()
   * @generated
   */
  EReference getTunerStatus_TunerContainer();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner Status Struct</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tuner Status Struct</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTunerStatusStruct()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_TunerStatusStruct();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tuner Type</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTunerType()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_TunerType();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Allocation ID</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getAllocationID()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_AllocationID();

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
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Group ID</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getGroupID()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_GroupID();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Rf Flow ID</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getRfFlowID()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_RfFlowID();

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
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getBandwidthTolerance <em>Bandwidth Tolerance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Bandwidth Tolerance</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getBandwidthTolerance()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_BandwidthTolerance();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getSampleRateTolerance <em>Sample Rate Tolerance</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Sample Rate Tolerance</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getSampleRateTolerance()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_SampleRateTolerance();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isComplex <em>Complex</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Complex</em>'.
   * @see gov.redhawk.frontend.TunerStatus#isComplex()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Complex();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Gain</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getGain()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Gain();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Agc</em>'.
   * @see gov.redhawk.frontend.TunerStatus#isAgc()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Agc();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isValid <em>Valid</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Valid</em>'.
   * @see gov.redhawk.frontend.TunerStatus#isValid()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Valid();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAvailableFrequency <em>Available Frequency</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Available Frequency</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getAvailableFrequency()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_AvailableFrequency();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAvailableBandwidth <em>Available Bandwidth</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Available Bandwidth</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getAvailableBandwidth()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_AvailableBandwidth();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAvailableGain <em>Available Gain</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Available Gain</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getAvailableGain()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_AvailableGain();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getAvailableSampleRate <em>Available Sample Rate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Available Sample Rate</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getAvailableSampleRate()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_AvailableSampleRate();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Reference Source</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getReferenceSource()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_ReferenceSource();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getOutputFormat <em>Output Format</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Format</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getOutputFormat()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_OutputFormat();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getOutputMulticast <em>Output Multicast</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Multicast</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getOutputMulticast()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_OutputMulticast();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getOutputVlan <em>Output Vlan</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Vlan</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getOutputVlan()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_OutputVlan();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getOutputPort <em>Output Port</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Output Port</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getOutputPort()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_OutputPort();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getDecimation <em>Decimation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Decimation</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getDecimation()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Decimation();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTuner_number <em>Tuner number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tuner number</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTuner_number()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_Tuner_number();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Device Control</em>'.
   * @see gov.redhawk.frontend.TunerStatus#isDeviceControl()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_DeviceControl();

  /**
   * Returns the meta object for the attribute '{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Tuner ID</em>'.
   * @see gov.redhawk.frontend.TunerStatus#getTunerID()
   * @see #getTunerStatus()
   * @generated
   */
  EAttribute getTunerStatus_TunerID();

  /**
   * Returns the meta object for data type '{@link gov.redhawk.model.sca.ScaDevice <em>Sca Device</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Sca Device</em>'.
   * @see gov.redhawk.model.sca.ScaDevice
   * @model instanceClass="gov.redhawk.model.sca.ScaDevice<?>"
   * @generated
   */
  EDataType getScaDevice();

  /**
   * Returns the meta object for data type '{@link gov.redhawk.model.sca.ScaStructProperty <em>Tuner Status Struct</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Tuner Status Struct</em>'.
   * @see gov.redhawk.model.sca.ScaStructProperty
   * @model instanceClass="gov.redhawk.model.sca.ScaStructProperty"
   * @generated
   */
  EDataType getTunerStatusStruct();

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
     * The meta object literal for the '{@link gov.redhawk.frontend.impl.ModelDeviceImpl <em>Model Device</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.frontend.impl.ModelDeviceImpl
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getModelDevice()
     * @generated
     */
    EClass MODEL_DEVICE = eINSTANCE.getModelDevice();

    /**
     * The meta object literal for the '<em><b>Tuner Container</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_DEVICE__TUNER_CONTAINER = eINSTANCE.getModelDevice_TunerContainer();

    /**
     * The meta object literal for the '<em><b>Sca Device</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_DEVICE__SCA_DEVICE = eINSTANCE.getModelDevice_ScaDevice();

    /**
     * The meta object literal for the '{@link gov.redhawk.frontend.impl.TunerContainerImpl <em>Tuner Container</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.frontend.impl.TunerContainerImpl
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerContainer()
     * @generated
     */
    EClass TUNER_CONTAINER = eINSTANCE.getTunerContainer();

    /**
     * The meta object literal for the '<em><b>Model Device</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER_CONTAINER__MODEL_DEVICE = eINSTANCE.getTunerContainer_ModelDevice();

    /**
     * The meta object literal for the '<em><b>Tuner Status</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER_CONTAINER__TUNER_STATUS = eINSTANCE.getTunerContainer_TunerStatus();

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
     * The meta object literal for the '<em><b>Tuner Container</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUNER_STATUS__TUNER_CONTAINER = eINSTANCE.getTunerStatus_TunerContainer();

    /**
     * The meta object literal for the '<em><b>Tuner Status Struct</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__TUNER_STATUS_STRUCT = eINSTANCE.getTunerStatus_TunerStatusStruct();

    /**
     * The meta object literal for the '<em><b>Tuner Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__TUNER_TYPE = eINSTANCE.getTunerStatus_TunerType();

    /**
     * The meta object literal for the '<em><b>Allocation ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__ALLOCATION_ID = eINSTANCE.getTunerStatus_AllocationID();

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
     * The meta object literal for the '<em><b>Group ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__GROUP_ID = eINSTANCE.getTunerStatus_GroupID();

    /**
     * The meta object literal for the '<em><b>Rf Flow ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__RF_FLOW_ID = eINSTANCE.getTunerStatus_RfFlowID();

    /**
     * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__ENABLED = eINSTANCE.getTunerStatus_Enabled();

    /**
     * The meta object literal for the '<em><b>Bandwidth Tolerance</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__BANDWIDTH_TOLERANCE = eINSTANCE.getTunerStatus_BandwidthTolerance();

    /**
     * The meta object literal for the '<em><b>Sample Rate Tolerance</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__SAMPLE_RATE_TOLERANCE = eINSTANCE.getTunerStatus_SampleRateTolerance();

    /**
     * The meta object literal for the '<em><b>Complex</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__COMPLEX = eINSTANCE.getTunerStatus_Complex();

    /**
     * The meta object literal for the '<em><b>Gain</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__GAIN = eINSTANCE.getTunerStatus_Gain();

    /**
     * The meta object literal for the '<em><b>Agc</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__AGC = eINSTANCE.getTunerStatus_Agc();

    /**
     * The meta object literal for the '<em><b>Valid</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__VALID = eINSTANCE.getTunerStatus_Valid();

    /**
     * The meta object literal for the '<em><b>Available Frequency</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__AVAILABLE_FREQUENCY = eINSTANCE.getTunerStatus_AvailableFrequency();

    /**
     * The meta object literal for the '<em><b>Available Bandwidth</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__AVAILABLE_BANDWIDTH = eINSTANCE.getTunerStatus_AvailableBandwidth();

    /**
     * The meta object literal for the '<em><b>Available Gain</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__AVAILABLE_GAIN = eINSTANCE.getTunerStatus_AvailableGain();

    /**
     * The meta object literal for the '<em><b>Available Sample Rate</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__AVAILABLE_SAMPLE_RATE = eINSTANCE.getTunerStatus_AvailableSampleRate();

    /**
     * The meta object literal for the '<em><b>Reference Source</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__REFERENCE_SOURCE = eINSTANCE.getTunerStatus_ReferenceSource();

    /**
     * The meta object literal for the '<em><b>Output Format</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__OUTPUT_FORMAT = eINSTANCE.getTunerStatus_OutputFormat();

    /**
     * The meta object literal for the '<em><b>Output Multicast</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__OUTPUT_MULTICAST = eINSTANCE.getTunerStatus_OutputMulticast();

    /**
     * The meta object literal for the '<em><b>Output Vlan</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__OUTPUT_VLAN = eINSTANCE.getTunerStatus_OutputVlan();

    /**
     * The meta object literal for the '<em><b>Output Port</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__OUTPUT_PORT = eINSTANCE.getTunerStatus_OutputPort();

    /**
     * The meta object literal for the '<em><b>Decimation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__DECIMATION = eINSTANCE.getTunerStatus_Decimation();

    /**
     * The meta object literal for the '<em><b>Tuner number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__TUNER_NUMBER = eINSTANCE.getTunerStatus_Tuner_number();

    /**
     * The meta object literal for the '<em><b>Device Control</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__DEVICE_CONTROL = eINSTANCE.getTunerStatus_DeviceControl();

    /**
     * The meta object literal for the '<em><b>Tuner ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUNER_STATUS__TUNER_ID = eINSTANCE.getTunerStatus_TunerID();

    /**
     * The meta object literal for the '<em>Sca Device</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.model.sca.ScaDevice
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getScaDevice()
     * @generated
     */
    EDataType SCA_DEVICE = eINSTANCE.getScaDevice();

    /**
     * The meta object literal for the '<em>Tuner Status Struct</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see gov.redhawk.model.sca.ScaStructProperty
     * @see gov.redhawk.frontend.impl.FrontendPackageImpl#getTunerStatusStruct()
     * @generated
     */
    EDataType TUNER_STATUS_STRUCT = eINSTANCE.getTunerStatusStruct();

  }

} //FrontendPackage
