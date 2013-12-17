/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.TunerContainer#getAnalogDevice <em>Analog Device</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerContainer#getTuners <em>Tuners</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer()
 * @model
 * @generated
 */
public interface TunerContainer extends EObject
{
  /**
   * Returns the value of the '<em><b>Analog Device</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.AnalogDevice#getTunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Analog Device</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Analog Device</em>' container reference.
   * @see #setAnalogDevice(AnalogDevice)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_AnalogDevice()
   * @see gov.redhawk.frontend.AnalogDevice#getTunerContainer
   * @model opposite="tunerContainer" transient="false"
   * @generated
   */
  AnalogDevice getAnalogDevice();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerContainer#getAnalogDevice <em>Analog Device</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Analog Device</em>' container reference.
   * @see #getAnalogDevice()
   * @generated
   */
  void setAnalogDevice(AnalogDevice value);

  /**
   * Returns the value of the '<em><b>Tuners</b></em>' attribute list.
   * The list contents are of type {@link gov.redhawk.model.sca.ScaStructProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuners</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuners</em>' attribute list.
   * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_Tuners()
   * @model unique="false" dataType="gov.redhawk.frontend.TunerStruct"
   * @generated
   */
  EList<ScaStructProperty> getTuners();

} // TunerContainer
