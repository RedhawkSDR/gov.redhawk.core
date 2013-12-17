/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Analog Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.AnalogDevice#getTuners <em>Tuners</em>}</li>
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
   * Returns the value of the '<em><b>Tuners</b></em>' containment reference list.
   * The list contents are of type {@link gov.redhawk.frontend.Tuner}.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.Tuner#getAnalogDevice <em>Analog Device</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuners</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuners</em>' containment reference list.
   * @see gov.redhawk.frontend.FrontendPackage#getAnalogDevice_Tuners()
   * @see gov.redhawk.frontend.Tuner#getAnalogDevice
   * @model opposite="analogDevice" containment="true"
   * @generated
   */
  EList<Tuner> getTuners();

} // AnalogDevice
