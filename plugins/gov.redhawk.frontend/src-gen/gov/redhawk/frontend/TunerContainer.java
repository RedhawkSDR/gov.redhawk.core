/**
 */
package gov.redhawk.frontend;

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
 *   <li>{@link gov.redhawk.frontend.TunerContainer#getModelDevice <em>Model Device</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner Status</em>}</li>
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
   * Returns the value of the '<em><b>Model Device</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.ModelDevice#getTunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Device</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Device</em>' container reference.
   * @see #setModelDevice(ModelDevice)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_ModelDevice()
   * @see gov.redhawk.frontend.ModelDevice#getTunerContainer
   * @model opposite="tunerContainer" transient="false"
   * @generated
   */
  ModelDevice getModelDevice();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerContainer#getModelDevice <em>Model Device</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Device</em>' container reference.
   * @see #getModelDevice()
   * @generated
   */
  void setModelDevice(ModelDevice value);

  /**
   * Returns the value of the '<em><b>Tuner Status</b></em>' containment reference list.
   * The list contents are of type {@link gov.redhawk.frontend.TunerStatus}.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner Container</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Status</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Status</em>' containment reference list.
   * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_TunerStatus()
   * @see gov.redhawk.frontend.TunerStatus#getTunerContainer
   * @model opposite="tunerContainer" containment="true"
   * @generated
   */
  EList<TunerStatus> getTunerStatus();

} // TunerContainer
