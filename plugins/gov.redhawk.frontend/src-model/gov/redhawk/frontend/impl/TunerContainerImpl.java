/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getModelDevice <em>Model Device</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getTunerStatus <em>Tuner Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerContainerImpl extends MinimalEObjectImpl.Container implements TunerContainer
{
  /**
   * The cached value of the '{@link #getTunerStatus() <em>Tuner Status</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStatus()
   * @generated
   * @ordered
   */
  protected EList<TunerStatus> tunerStatus;

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
  public ModelDevice getModelDevice()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE) return null;
    return (ModelDevice)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelDevice basicGetModelDevice()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE) return null;
    return (ModelDevice)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelDevice(ModelDevice newModelDevice, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newModelDevice, FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelDevice(ModelDevice newModelDevice)
  {
    if (newModelDevice != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE && newModelDevice != null))
    {
      if (EcoreUtil.isAncestor(this, newModelDevice))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newModelDevice != null)
        msgs = ((InternalEObject)newModelDevice).eInverseAdd(this, FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER, ModelDevice.class, msgs);
      msgs = basicSetModelDevice(newModelDevice, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE, newModelDevice, newModelDevice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TunerStatus> getTunerStatus()
  {
    if (tunerStatus == null)
    {
      tunerStatus = new EObjectContainmentWithInverseEList<TunerStatus>(TunerStatus.class, this, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS, FrontendPackage.TUNER_STATUS__TUNER_CONTAINER);
    }
    return tunerStatus;
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetModelDevice((ModelDevice)otherEnd, msgs);
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getTunerStatus()).basicAdd(otherEnd, msgs);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        return basicSetModelDevice(null, msgs);
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        return ((InternalEList<?>)getTunerStatus()).basicRemove(otherEnd, msgs);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.MODEL_DEVICE__TUNER_CONTAINER, ModelDevice.class, msgs);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        if (resolve) return getModelDevice();
        return basicGetModelDevice();
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        return getTunerStatus();
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        setModelDevice((ModelDevice)newValue);
        return;
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        getTunerStatus().clear();
        getTunerStatus().addAll((Collection<? extends TunerStatus>)newValue);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        setModelDevice((ModelDevice)null);
        return;
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        getTunerStatus().clear();
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        return basicGetModelDevice() != null;
      case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
        return tunerStatus != null && !tunerStatus.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //TunerContainerImpl
