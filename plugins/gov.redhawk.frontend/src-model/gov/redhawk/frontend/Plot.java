/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.Plot#getTunerStatus <em>Tuner Status</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Plot#getListenerID <em>Listener ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.Plot#getPlotView <em>Plot View</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getPlot()
 * @model
 * @generated
 */
public interface Plot extends EObject
{
  /**
   * Returns the value of the '<em><b>Tuner Status</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerStatus#getPlots <em>Plots</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tuner Status</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tuner Status</em>' container reference.
   * @see #setTunerStatus(TunerStatus)
   * @see gov.redhawk.frontend.FrontendPackage#getPlot_TunerStatus()
   * @see gov.redhawk.frontend.TunerStatus#getPlots
   * @model opposite="plots" transient="false"
   * @generated
   */
  TunerStatus getTunerStatus();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Plot#getTunerStatus <em>Tuner Status</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tuner Status</em>' container reference.
   * @see #getTunerStatus()
   * @generated
   */
  void setTunerStatus(TunerStatus value);

  /**
   * Returns the value of the '<em><b>Listener ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Listener ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Listener ID</em>' attribute.
   * @see #setListenerID(String)
   * @see gov.redhawk.frontend.FrontendPackage#getPlot_ListenerID()
   * @model unique="false"
   * @generated
   */
  String getListenerID();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Plot#getListenerID <em>Listener ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Listener ID</em>' attribute.
   * @see #getListenerID()
   * @generated
   */
  void setListenerID(String value);

  /**
   * Returns the value of the '<em><b>Plot View</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Plot View</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Plot View</em>' attribute.
   * @see #setPlotView(Object)
   * @see gov.redhawk.frontend.FrontendPackage#getPlot_PlotView()
   * @model unique="false" dataType="gov.redhawk.frontend.PlotObject"
   * @generated
   */
  Object getPlotView();

  /**
   * Sets the value of the '{@link gov.redhawk.frontend.Plot#getPlotView <em>Plot View</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Plot View</em>' attribute.
   * @see #getPlotView()
   * @generated
   */
  void setPlotView(Object value);

} // Plot
