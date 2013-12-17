/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analog Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.AnalogDevice#getTunerContainer <em>Tuner Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getAnalogDevice()
 * @model
 * @generated
 */
public interface AnalogDevice extends EObject
{
  /**
   * Returns the value of the '<em><b>Tuner Container</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getAnalogDevice <em>Analog Device</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Container</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Container</em>' containment reference.
   * @see #setTunerContainer(TunerContainer)
   * @see gov.redhawk.frontend.FrontendPackage#getAnalogDevice_TunerContainer()
   * @see gov.redhawk.frontend.TunerContainer#getAnalogDevice
   * @model opposite="analogDevice" containment="true"
   * @generated
   */
  TunerContainer getTunerContainer();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.AnalogDevice#getTunerContainer <em>Tuner Container</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Container</em>' containment reference.
   * @see #getTunerContainer()
   * @generated
   */
  void setTunerContainer(TunerContainer value);

} // AnalogDevice
