/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.AnalogDevice;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerContainer;

import gov.redhawk.model.sca.ScaStructProperty;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getAnalogDevice <em>Analog Device</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getTuners <em>Tuners</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerContainerImpl extends MinimalEObjectImpl.Container implements TunerContainer
{
  /**
   * The cached value of the '{@link #getTuners() <em>Tuners</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTuners()
   * @generated
   * @ordered
   */
  protected EList<ScaStructProperty> tuners;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TunerContainerImpl()
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
    return FrontendPackage.Literals.TUNER_CONTAINER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalogDevice getAnalogDevice()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE) return null;
    return (AnalogDevice)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnalogDevice basicGetAnalogDevice()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE) return null;
    return (AnalogDevice)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnalogDevice(AnalogDevice newAnalogDevice, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newAnalogDevice, FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnalogDevice(AnalogDevice newAnalogDevice)
  {
    if (newAnalogDevice != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE && newAnalogDevice != null))
    {
      if (EcoreUtil.isAncestor(this, newAnalogDevice))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newAnalogDevice != null)
        msgs = ((InternalEObject)newAnalogDevice).eInverseAdd(this, FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER, AnalogDevice.class, msgs);
      msgs = basicSetAnalogDevice(newAnalogDevice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE, newAnalogDevice, newAnalogDevice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScaStructProperty> getTuners()
  {
    if (tuners == null)
    {
      tuners = new EDataTypeEList<ScaStructProperty>(ScaStructProperty.class, this, FrontendPackage.TUNER_CONTAINER__TUNERS);
    }
    return tuners;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetAnalogDevice((AnalogDevice)otherEnd, msgs);
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
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        return basicSetAnalogDevice(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER, AnalogDevice.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        if (resolve) return getAnalogDevice();
        return basicGetAnalogDevice();
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
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
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        setAnalogDevice((AnalogDevice)newValue);
        return;
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
        getTuners().clear();
        getTuners().addAll((Collection<? extends ScaStructProperty>)newValue);
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
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        setAnalogDevice((AnalogDevice)null);
        return;
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
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
      case FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE:
        return basicGetAnalogDevice() != null;
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
        return tuners != null && !tuners.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (tuners: ");
    result.append(tuners);
    result.append(')');
    return result.toString();
  }

} //TunerContainerImpl
