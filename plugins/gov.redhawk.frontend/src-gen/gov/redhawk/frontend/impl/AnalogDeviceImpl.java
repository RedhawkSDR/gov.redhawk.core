/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.AnalogDevice;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerContainer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Analog Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.AnalogDeviceImpl#getTunerContainer <em>Tuner Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnalogDeviceImpl extends MinimalEObjectImpl.Container implements AnalogDevice
{
  /**
   * The cached value of the '{@link #getTunerContainer() <em>Tuner Container</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerContainer()
   * @generated
   * @ordered
   */
  protected TunerContainer tunerContainer;

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
  public TunerContainer getTunerContainer()
  {
    return tunerContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTunerContainer(TunerContainer newTunerContainer, NotificationChain msgs)
  {
    TunerContainer oldTunerContainer = tunerContainer;
    tunerContainer = newTunerContainer;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER, oldTunerContainer, newTunerContainer);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerContainer(TunerContainer newTunerContainer)
  {
    if (newTunerContainer != tunerContainer)
    {
      NotificationChain msgs = null;
      if (tunerContainer != null)
        msgs = ((InternalEObject)tunerContainer).eInverseRemove(this, FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE, TunerContainer.class, msgs);
      if (newTunerContainer != null)
        msgs = ((InternalEObject)newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__ANALOG_DEVICE, TunerContainer.class, msgs);
      msgs = basicSetTunerContainer(newTunerContainer, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER, newTunerContainer, newTunerContainer));
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
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        if (tunerContainer != null)
          msgs = ((InternalEObject)tunerContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER, null, msgs);
        return basicSetTunerContainer((TunerContainer)otherEnd, msgs);
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
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        return basicSetTunerContainer(null, msgs);
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
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        return getTunerContainer();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)newValue);
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
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)null);
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
      case FrontendPackage.ANALOG_DEVICE__TUNER_CONTAINER:
        return tunerContainer != null;
    }
    return super.eIsSet(featureID);
  }

} //AnalogDeviceImpl
