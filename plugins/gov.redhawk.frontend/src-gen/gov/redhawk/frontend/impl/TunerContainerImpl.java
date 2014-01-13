/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ModelDevice;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;

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
 *   <li>{@link gov.redhawk.frontend.impl.TunerContainerImpl#getTuners <em>Tuners</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerContainerImpl extends MinimalEObjectImpl.Container implements TunerContainer
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
  public EList<Tuner> getTuners()
  {
    if (tuners == null)
    {
      tuners = new EObjectContainmentWithInverseEList<Tuner>(Tuner.class, this, FrontendPackage.TUNER_CONTAINER__TUNERS, FrontendPackage.TUNER__TUNER_CONTAINER);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetModelDevice((ModelDevice)otherEnd, msgs);
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        return basicSetModelDevice(null, msgs);
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        setModelDevice((ModelDevice)newValue);
        return;
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        setModelDevice((ModelDevice)null);
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
      case FrontendPackage.TUNER_CONTAINER__MODEL_DEVICE:
        return basicGetModelDevice() != null;
      case FrontendPackage.TUNER_CONTAINER__TUNERS:
        return tuners != null && !tuners.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //TunerContainerImpl
