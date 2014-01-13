/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.TunerContainer;

import gov.redhawk.model.sca.ScaDevice;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.ModelDeviceImpl#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.ModelDeviceImpl#getScaDevice <em>Sca Device</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelDeviceImpl extends MinimalEObjectImpl.Container implements ModelDevice
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
   * The cached value of the '{@link #getScaDevice() <em>Sca Device</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getScaDevice()
   * @generated
   * @ordered
   */
  protected ScaDevice<?> scaDevice;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelDeviceImpl()
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
    return FrontendPackage.Literals.MODEL_DEVICE;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER, oldTunerContainer, newTunerContainer);
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
        msgs = ((InternalEObject)tunerContainer).eInverseRemove(this, FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE, TunerContainer.class, msgs);
      if (newTunerContainer != null)
        msgs = ((InternalEObject)newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE, TunerContainer.class, msgs);
      msgs = basicSetTunerContainer(newTunerContainer, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER, newTunerContainer, newTunerContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaDevice<?> getScaDevice()
  {
    return scaDevice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setScaDevice(ScaDevice<?> newScaDevice)
  {
    ScaDevice<?> oldScaDevice = scaDevice;
    scaDevice = newScaDevice;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.MODEL_DEVICE__SCA_DEVICE, oldScaDevice, scaDevice));
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
        if (tunerContainer != null)
          msgs = ((InternalEObject)tunerContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER, null, msgs);
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
        return getTunerContainer();
      case FrontendPackage.MODEL_DEVICE__SCA_DEVICE:
        return getScaDevice();
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)newValue);
        return;
      case FrontendPackage.MODEL_DEVICE__SCA_DEVICE:
        setScaDevice((ScaDevice<?>)newValue);
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)null);
        return;
      case FrontendPackage.MODEL_DEVICE__SCA_DEVICE:
        setScaDevice((ScaDevice<?>)null);
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
      case FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER:
        return tunerContainer != null;
      case FrontendPackage.MODEL_DEVICE__SCA_DEVICE:
        return scaDevice != null;
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
    result.append(" (scaDevice: ");
    result.append(scaDevice);
    result.append(')');
    return result.toString();
  }

} //ModelDeviceImpl
