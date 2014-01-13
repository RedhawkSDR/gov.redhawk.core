/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.Tuner#getTunerStruct <em>Tuner Struct</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getTunerID <em>Tuner ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getRfFlowID <em>Rf Flow ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getTunerStatus <em>Tuner Status</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Tuner#getGain <em>Gain</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getTuner()
 * @model
 * @generated
 */
public interface Tuner extends EObject
{
  /**
   * Returns the value of the '<em><b>Tuner Struct</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Struct</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Struct</em>' attribute.
   * @see #setTunerStruct(ScaStructProperty)
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_TunerStruct()
   * @model unique="false" dataType="gov.redhawk.frontend.TunerStruct"
   * @generated
   */
  ScaStructProperty getTunerStruct();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getTunerStruct <em>Tuner Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Struct</em>' attribute.
   * @see #getTunerStruct()
   * @generated
   */
  void setTunerStruct(ScaStructProperty value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_AllocationID()
   * @model unique="false"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
   * @generated
   */
  String getAllocationID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getAllocationID <em>Allocation ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Allocation ID</em>' attribute.
   * @see #getAllocationID()
   * @generated
   */
  void setAllocationID(String value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_TunerType()
   * @model unique="false"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
   * @generated
   */
  String getTunerType();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getTunerType <em>Tuner Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Type</em>' attribute.
   * @see #getTunerType()
   * @generated
   */
  void setTunerType(String value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_TunerID()
   * @model unique="false"
   * @generated
   */
  String getTunerID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getTunerID <em>Tuner ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner ID</em>' attribute.
   * @see #getTunerID()
   * @generated
   */
  void setTunerID(String value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_DeviceControl()
   * @model unique="false"
   * @generated
   */
  boolean isDeviceControl();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#isDeviceControl <em>Device Control</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Device Control</em>' attribute.
   * @see #isDeviceControl()
   * @generated
   */
  void setDeviceControl(boolean value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_GroupID()
   * @model unique="false"
   * @generated
   */
  String getGroupID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getGroupID <em>Group ID</em>}' attribute.
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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_RfFlowID()
   * @model unique="false"
   * @generated
   */
  String getRfFlowID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getRfFlowID <em>Rf Flow ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rf Flow ID</em>' attribute.
   * @see #getRfFlowID()
   * @generated
   */
  void setRfFlowID(String value);

  /**
   * Returns the value of the '<em><b>Tuner Container</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getTuners <em>Tuners</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Container</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Container</em>' container reference.
   * @see #setTunerContainer(TunerContainer)
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_TunerContainer()
   * @see gov.redhawk.frontend.TunerContainer#getTuners
   * @model opposite="tuners" transient="false"
   * @generated
   */
  TunerContainer getTunerContainer();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getTunerContainer <em>Tuner Container</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Container</em>' container reference.
   * @see #getTunerContainer()
   * @generated
   */
  void setTunerContainer(TunerContainer value);

  /**
   * Returns the value of the '<em><b>Tuner Status</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerStatus#getTuner <em>Tuner</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Status</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Status</em>' containment reference.
   * @see #setTunerStatus(TunerStatus)
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_TunerStatus()
   * @see gov.redhawk.frontend.TunerStatus#getTuner
   * @model opposite="tuner" containment="true"
   * @generated
   */
  TunerStatus getTunerStatus();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getTunerStatus <em>Tuner Status</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Status</em>' containment reference.
   * @see #getTunerStatus()
   * @generated
   */
  void setTunerStatus(TunerStatus value);

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
   * @see gov.redhawk.frontend.FrontendPackage#getTuner_Gain()
   * @model unique="false"
   * @generated
   */
  double getGain();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Tuner#getGain <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gain</em>' attribute.
   * @see #getGain()
   * @generated
   */
  void setGain(double value);

} // Tuner
