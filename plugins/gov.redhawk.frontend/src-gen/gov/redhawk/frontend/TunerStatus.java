/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getTuner <em>Tuner</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getCenterFrequency <em>Center Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#getSampleRate <em>Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.TunerStatus#isEnabled <em>Enabled</em>}</li>
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
   * Returns the value of the '<em><b>Tuner</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.Tuner#getTunerStatus <em>Tuner Status</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner</em>' container reference.
   * @see #setTuner(Tuner)
   * @see gov.redhawk.frontend.FrontendPackage#getTunerStatus_Tuner()
   * @see gov.redhawk.frontend.Tuner#getTunerStatus
   * @model opposite="tunerStatus" transient="false"
   *        annotation="http://www.eclipse.org/emf/2002/GenModel property='Readonly'"
   * @generated
   */
  Tuner getTuner();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.TunerStatus#getTuner <em>Tuner</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner</em>' container reference.
   * @see #getTuner()
   * @generated
   */
  void setTuner(Tuner value);

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

} // TunerStatus
