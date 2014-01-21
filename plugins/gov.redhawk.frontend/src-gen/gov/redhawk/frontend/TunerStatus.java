/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner Status Struct</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getBandwidthTolerance <em>Bandwidth Tolerance</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getSampleRateTolerance <em>Sample Rate Tolerance</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isComplex <em>Complex</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isValid <em>Valid</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAvailableFrequency <em>Available Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAvailableBandwidth <em>Available Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAvailableGain <em>Available Gain</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAvailableSampleRate <em>Available Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getOutputFormat <em>Output Format</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getOutputMulticast <em>Output Multicast</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getOutputVlan <em>Output Vlan</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getOutputPort <em>Output Port</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getDecimation <em>Decimation</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTuner_number <em>Tuner number</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getListenerAllocations <em>Listener Allocations</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus()
 * @model
 * @generated
 */
public interface TunerStatus extends EObject
{
  /**
   * Returns the value of the '<em><b>Tuner Container</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Container</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Container</em>' container reference.
   * @see #setTunerContainer(TunerContainer)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerContainer()
   * @see gov.redhawk.frontend.TunerContainer#getTunerStatus
   * @model opposite="tunerStatus" transient="false"
   * @generated
   */
  TunerContainer getTunerContainer();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Container</em>' container reference.
   * @see #getTunerContainer()
   * @generated
   */
  void setTunerContainer(TunerContainer value);

  /**
   * Returns the value of the '<em><b>Tuner Status Struct</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Status Struct</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Status Struct</em>' attribute.
   * @see #setTunerStatusStruct(ScaStructProperty)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerStatusStruct()
   * @model unique="false" dataType="gov.redhawk.frontend.TunerStatusStruct"
   * @generated
   */
  ScaStructProperty getTunerStatusStruct();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerStatusStruct <em>Tuner Status Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Status Struct</em>' attribute.
   * @see #getTunerStatusStruct()
   * @generated
   */
  void setTunerStatusStruct(ScaStructProperty value);

  /**
   * Returns the value of the '<em><b>Tuner Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Type</em>' attribute.
   * @see #setTunerType(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerType()
   * @model unique="false"
   * @generated
   */
  String getTunerType();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Type</em>' attribute.
   * @see #getTunerType()
   * @generated
   */
  void setTunerType(String value);

  /**
   * Returns the value of the '<em><b>Allocation ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Allocation ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Allocation ID</em>' attribute.
   * @see #setAllocationID(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AllocationID()
   * @model unique="false"
   * @generated
   */
  String getAllocationID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Allocation ID</em>' attribute.
   * @see #getAllocationID()
   * @generated
   */
  void setAllocationID(String value);

  /**
   * Returns the value of the '<em><b>Center Frequency</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Center Frequency</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Center Frequency</em>' attribute.
   * @see #setCenterFrequency(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_CenterFrequency()
   * @model unique="false"
   * @generated
   */
  double getCenterFrequency();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Center Frequency</em>' attribute.
   * @see #getCenterFrequency()
   * @generated
   */
  void setCenterFrequency(double value);

  /**
   * Returns the value of the '<em><b>Bandwidth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bandwidth</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bandwidth</em>' attribute.
   * @see #setBandwidth(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Bandwidth()
   * @model unique="false"
   * @generated
   */
  double getBandwidth();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bandwidth</em>' attribute.
   * @see #getBandwidth()
   * @generated
   */
  void setBandwidth(double value);

  /**
   * Returns the value of the '<em><b>Sample Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sample Rate</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sample Rate</em>' attribute.
   * @see #setSampleRate(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_SampleRate()
   * @model unique="false"
   * @generated
   */
  double getSampleRate();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sample Rate</em>' attribute.
   * @see #getSampleRate()
   * @generated
   */
  void setSampleRate(double value);

  /**
   * Returns the value of the '<em><b>Group ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group ID</em>' attribute.
   * @see #setGroupID(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_GroupID()
   * @model unique="false"
   * @generated
   */
  String getGroupID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Group ID</em>' attribute.
   * @see #getGroupID()
   * @generated
   */
  void setGroupID(String value);

  /**
   * Returns the value of the '<em><b>Rf Flow ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rf Flow ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rf Flow ID</em>' attribute.
   * @see #setRfFlowID(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_RfFlowID()
   * @model unique="false"
   * @generated
   */
  String getRfFlowID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rf Flow ID</em>' attribute.
   * @see #getRfFlowID()
   * @generated
   */
  void setRfFlowID(String value);

  /**
   * Returns the value of the '<em><b>Enabled</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enabled</em>' attribute.
   * @see #setEnabled(boolean)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Enabled()
   * @model unique="false"
   * @generated
   */
  boolean isEnabled();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Enabled</em>' attribute.
   * @see #isEnabled()
   * @generated
   */
  void setEnabled(boolean value);

  /**
   * Returns the value of the '<em><b>Bandwidth Tolerance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bandwidth Tolerance</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bandwidth Tolerance</em>' attribute.
   * @see #setBandwidthTolerance(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_BandwidthTolerance()
   * @model unique="false"
   * @generated
   */
  double getBandwidthTolerance();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getBandwidthTolerance <em>Bandwidth Tolerance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bandwidth Tolerance</em>' attribute.
   * @see #getBandwidthTolerance()
   * @generated
   */
  void setBandwidthTolerance(double value);

  /**
   * Returns the value of the '<em><b>Sample Rate Tolerance</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sample Rate Tolerance</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sample Rate Tolerance</em>' attribute.
   * @see #setSampleRateTolerance(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_SampleRateTolerance()
   * @model unique="false"
   * @generated
   */
  double getSampleRateTolerance();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getSampleRateTolerance <em>Sample Rate Tolerance</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sample Rate Tolerance</em>' attribute.
   * @see #getSampleRateTolerance()
   * @generated
   */
  void setSampleRateTolerance(double value);

  /**
   * Returns the value of the '<em><b>Complex</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Complex</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Complex</em>' attribute.
   * @see #setComplex(boolean)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Complex()
   * @model unique="false"
   * @generated
   */
  boolean isComplex();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isComplex <em>Complex</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Complex</em>' attribute.
   * @see #isComplex()
   * @generated
   */
  void setComplex(boolean value);

  /**
   * Returns the value of the '<em><b>Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gain</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gain</em>' attribute.
   * @see #setGain(double)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Gain()
   * @model unique="false"
   * @generated
   */
  double getGain();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gain</em>' attribute.
   * @see #getGain()
   * @generated
   */
  void setGain(double value);

  /**
   * Returns the value of the '<em><b>Agc</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Agc</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Agc</em>' attribute.
   * @see #setAgc(boolean)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Agc()
   * @model unique="false"
   * @generated
   */
  boolean isAgc();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Agc</em>' attribute.
   * @see #isAgc()
   * @generated
   */
  void setAgc(boolean value);

  /**
   * Returns the value of the '<em><b>Valid</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Valid</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Valid</em>' attribute.
   * @see #setValid(boolean)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Valid()
   * @model unique="false"
   * @generated
   */
  boolean isValid();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isValid <em>Valid</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Valid</em>' attribute.
   * @see #isValid()
   * @generated
   */
  void setValid(boolean value);

  /**
   * Returns the value of the '<em><b>Available Frequency</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Available Frequency</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Available Frequency</em>' attribute.
   * @see #setAvailableFrequency(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AvailableFrequency()
   * @model unique="false"
   * @generated
   */
  String getAvailableFrequency();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAvailableFrequency <em>Available Frequency</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Available Frequency</em>' attribute.
   * @see #getAvailableFrequency()
   * @generated
   */
  void setAvailableFrequency(String value);

  /**
   * Returns the value of the '<em><b>Available Bandwidth</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Available Bandwidth</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Available Bandwidth</em>' attribute.
   * @see #setAvailableBandwidth(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AvailableBandwidth()
   * @model unique="false"
   * @generated
   */
  String getAvailableBandwidth();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAvailableBandwidth <em>Available Bandwidth</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Available Bandwidth</em>' attribute.
   * @see #getAvailableBandwidth()
   * @generated
   */
  void setAvailableBandwidth(String value);

  /**
   * Returns the value of the '<em><b>Available Gain</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Available Gain</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Available Gain</em>' attribute.
   * @see #setAvailableGain(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AvailableGain()
   * @model unique="false"
   * @generated
   */
  String getAvailableGain();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAvailableGain <em>Available Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Available Gain</em>' attribute.
   * @see #getAvailableGain()
   * @generated
   */
  void setAvailableGain(String value);

  /**
   * Returns the value of the '<em><b>Available Sample Rate</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Available Sample Rate</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Available Sample Rate</em>' attribute.
   * @see #setAvailableSampleRate(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_AvailableSampleRate()
   * @model unique="false"
   * @generated
   */
  String getAvailableSampleRate();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getAvailableSampleRate <em>Available Sample Rate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Available Sample Rate</em>' attribute.
   * @see #getAvailableSampleRate()
   * @generated
   */
  void setAvailableSampleRate(String value);

  /**
   * Returns the value of the '<em><b>Reference Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference Source</em>' attribute.
   * @see #setReferenceSource(long)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_ReferenceSource()
   * @model unique="false"
   * @generated
   */
  long getReferenceSource();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference Source</em>' attribute.
   * @see #getReferenceSource()
   * @generated
   */
  void setReferenceSource(long value);

  /**
   * Returns the value of the '<em><b>Output Format</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Format</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Format</em>' attribute.
   * @see #setOutputFormat(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_OutputFormat()
   * @model unique="false"
   * @generated
   */
  String getOutputFormat();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getOutputFormat <em>Output Format</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Format</em>' attribute.
   * @see #getOutputFormat()
   * @generated
   */
  void setOutputFormat(String value);

  /**
   * Returns the value of the '<em><b>Output Multicast</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Multicast</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Multicast</em>' attribute.
   * @see #setOutputMulticast(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_OutputMulticast()
   * @model unique="false"
   * @generated
   */
  String getOutputMulticast();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getOutputMulticast <em>Output Multicast</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Multicast</em>' attribute.
   * @see #getOutputMulticast()
   * @generated
   */
  void setOutputMulticast(String value);

  /**
   * Returns the value of the '<em><b>Output Vlan</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Vlan</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Vlan</em>' attribute.
   * @see #setOutputVlan(long)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_OutputVlan()
   * @model unique="false"
   * @generated
   */
  long getOutputVlan();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getOutputVlan <em>Output Vlan</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Vlan</em>' attribute.
   * @see #getOutputVlan()
   * @generated
   */
  void setOutputVlan(long value);

  /**
   * Returns the value of the '<em><b>Output Port</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Output Port</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Port</em>' attribute.
   * @see #setOutputPort(long)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_OutputPort()
   * @model unique="false"
   * @generated
   */
  long getOutputPort();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getOutputPort <em>Output Port</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Port</em>' attribute.
   * @see #getOutputPort()
   * @generated
   */
  void setOutputPort(long value);

  /**
   * Returns the value of the '<em><b>Decimation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Decimation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Decimation</em>' attribute.
   * @see #setDecimation(long)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Decimation()
   * @model unique="false"
   * @generated
   */
  long getDecimation();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getDecimation <em>Decimation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Decimation</em>' attribute.
   * @see #getDecimation()
   * @generated
   */
  void setDecimation(long value);

  /**
   * Returns the value of the '<em><b>Tuner number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner number</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner number</em>' attribute.
   * @see #setTuner_number(short)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Tuner_number()
   * @model unique="false"
   * @generated
   */
  short getTuner_number();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTuner_number <em>Tuner number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner number</em>' attribute.
   * @see #getTuner_number()
   * @generated
   */
  void setTuner_number(short value);

  /**
   * Returns the value of the '<em><b>Device Control</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Device Control</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Device Control</em>' attribute.
   * @see #setDeviceControl(boolean)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_DeviceControl()
   * @model unique="false"
   * @generated
   */
  boolean isDeviceControl();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Device Control</em>' attribute.
   * @see #isDeviceControl()
   * @generated
   */
  void setDeviceControl(boolean value);

  /**
   * Returns the value of the '<em><b>Tuner ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner ID</em>' attribute.
   * @see #setTunerID(String)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_TunerID()
   * @model unique="false"
   * @generated
   */
  String getTunerID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner ID</em>' attribute.
   * @see #getTunerID()
   * @generated
   */
  void setTunerID(String value);

  /**
   * Returns the value of the '<em><b>Listener Allocations</b></em>' containment reference list.
   * The list contents are of type {@link gov.redhawk.frontend.ListenerAllocation}.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.ListenerAllocation#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Listener Allocations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Listener Allocations</em>' containment reference list.
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_ListenerAllocations()
   * @see gov.redhawk.frontend.ListenerAllocation#getTunerStatus
   * @model opposite="tunerStatus" containment="true"
   * @generated
   */
  EList<ListenerAllocation> getListenerAllocations();

} // TunerStatus
