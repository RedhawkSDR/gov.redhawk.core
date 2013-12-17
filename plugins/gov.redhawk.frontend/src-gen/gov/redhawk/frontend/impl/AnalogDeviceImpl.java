/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.AnalogDevice;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.Tuner;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analog Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.AnalogDeviceImpl#getTuners <em>Tuners</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalogDeviceImpl extends MinimalEObjectImpl.Container implements AnalogDevice
{
  /**
   * The cached value of the '{@link #getTuners() <em>Tuners</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTuners()
   * @generated
   * @ordered
   */
  protected EList<Tuner> tuners;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnalogDeviceImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FrontendPackage.Literals.ANALOG_DEVICE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Tuner> getTuners()
  {
    if (tuners == null)
    {
      tuners = new EObjectContainmentWithInverseEList<Tuner>(Tuner.class, this, FrontendPackage.ANALOG_DEVICE__TUNERS, FrontendPackage.TUNER__ANALOG_DEVICE);
    }
    return tuners;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getTuners()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        return ((InternalEList<?>)getTuners()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        return getTuners();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        getTuners().clear();
        getTuners().addAll((Collection<? extends Tuner>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        getTuners().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNERS:
        return tuners != null && !tuners.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AnalogDeviceImpl
