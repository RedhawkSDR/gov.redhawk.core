/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaDevice;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.ModelDevice#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.ModelDevice#getScaDevice <em>Sca Device</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getModelDevice()
 * @model
 * @generated
 */
public interface ModelDevice extends EObject
{
  /**
   * Returns the value of the '<em><b>Tuner Container</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getModelDevice <em>Model Device</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Container</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Container</em>' containment reference.
   * @see #setTunerContainer(TunerContainer)
   * @see gov.redhawk.frontend.FrontendPackage#getModelDevice_TunerContainer()
   * @see gov.redhawk.frontend.TunerContainer#getModelDevice
   * @model opposite="modelDevice" containment="true"
   * @generated
   */
  TunerContainer getTunerContainer();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.ModelDevice#getTunerContainer <em>Tuner Container</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Container</em>' containment reference.
   * @see #getTunerContainer()
   * @generated
   */
  void setTunerContainer(TunerContainer value);

  /**
   * Returns the value of the '<em><b>Sca Device</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sca Device</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sca Device</em>' attribute.
   * @see #setScaDevice(ScaDevice)
   * @see gov.redhawk.frontend.FrontendPackage#getModelDevice_ScaDevice()
   * @model unique="false" dataType="gov.redhawk.frontend.ScaDevice"
   * @generated
   */
  ScaDevice<?> getScaDevice();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.ModelDevice#getScaDevice <em>Sca Device</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sca Device</em>' attribute.
   * @see #getScaDevice()
   * @generated
   */
  void setScaDevice(ScaDevice<?> value);

} // ModelDevice
