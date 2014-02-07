/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaSimpleProperty;
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
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getSimples <em>Simples</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerID <em>Tuner ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getRfFlowID <em>Rf Flow ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getGain <em>Gain</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isAgc <em>Agc</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getReferenceSource <em>Reference Source</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getListenerAllocations <em>Listener Allocations</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getPlots <em>Plots</em>}</li>
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
   * Returns the value of the '<em><b>Simples</b></em>' attribute list.
   * The list contents are of type {@link gov.redhawk.model.sca.ScaSimpleProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simples</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simples</em>' attribute list.
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Simples()
   * @model unique="false" dataType="gov.redhawk.frontend.ScaSimpleProperty"
   * @generated
   */
  EList<ScaSimpleProperty> getSimples();

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

  /**
   * Returns the value of the '<em><b>Plots</b></em>' containment reference list.
   * The list contents are of type {@link gov.redhawk.frontend.Plot}.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.Plot#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Plots</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Plots</em>' containment reference list.
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Plots()
   * @see gov.redhawk.frontend.Plot#getTunerStatus
   * @model opposite="tunerStatus" containment="true"
   * @generated
   */
  EList<Plot> getPlots();

} // TunerStatus
