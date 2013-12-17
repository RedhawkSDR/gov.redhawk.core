/**
 */
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.frontend.FrontendPackage
 * @generated
 */
public interface FrontendFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  FrontendFactory eINSTANCE = gov.redhawk.frontend.impl.FrontendFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Analog Device</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Analog Device</em>'.
   * @generated
   */
  AnalogDevice createAnalogDevice();

  /**
   * Returns a new object of class '<em>Tuner</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuner</em>'.
   * @generated
   */
  Tuner createTuner();

  /**
   * Returns a new object of class '<em>Tuner Status</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuner Status</em>'.
   * @generated
   */
  TunerStatus createTunerStatus();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  FrontendPackage getFrontendPackage();

} //FrontendFactory
