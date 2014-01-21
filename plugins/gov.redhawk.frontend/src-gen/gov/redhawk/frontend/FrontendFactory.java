/**
 */
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

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
   * Returns a new object of class '<em>Model Device</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Device</em>'.
   * @generated
   */
  ModelDevice createModelDevice();

  /**
   * Returns a new object of class '<em>Tuner Container</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuner Container</em>'.
   * @generated
   */
  TunerContainer createTunerContainer();

  /**
   * Returns a new object of class '<em>Tuner Status</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuner Status</em>'.
   * @generated
   */
  TunerStatus createTunerStatus();

  /**
   * Returns a new object of class '<em>Listener Allocation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Listener Allocation</em>'.
   * @generated
   */
  ListenerAllocation createListenerAllocation();

  /**
   * Returns an instance of data type '<em>Sca Device</em>' corresponding the given literal.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal a literal of the data type.
   * @return a new instance value of the data type.
   * @generated
   */
  ScaDevice<?> createScaDevice(String literal);

  /**
   * Returns a literal representation of an instance of data type '<em>Sca Device</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param instanceValue an instance value of the data type.
   * @return a literal representation of the instance value.
   * @generated
   */
  String convertScaDevice(ScaDevice<?> instanceValue);

  /**
   * Returns an instance of data type '<em>Tuner Status Struct</em>' corresponding the given literal.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal a literal of the data type.
   * @return a new instance value of the data type.
   * @generated
   */
  ScaStructProperty createTunerStatusStruct(String literal);

  /**
   * Returns a literal representation of an instance of data type '<em>Tuner Status Struct</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param instanceValue an instance value of the data type.
   * @return a literal representation of the instance value.
   * @generated
   */
  String convertTunerStatusStruct(ScaStructProperty instanceValue);

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  FrontendPackage getFrontendPackage();

} //FrontendFactory
