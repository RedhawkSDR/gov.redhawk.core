/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;

import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerStruct <em>Tuner Struct</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerID <em>Tuner ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getRfFlowID <em>Rf Flow ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerStatus <em>Tuner Status</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getGain <em>Gain</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerImpl extends MinimalEObjectImpl.Container implements Tuner
{
  /**
   * The default value of the '{@link #getTunerStruct() <em>Tuner Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStruct()
   * @generated
   * @ordered
   */
  protected static final ScaStructProperty TUNER_STRUCT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTunerStruct() <em>Tuner Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStruct()
   * @generated
   * @ordered
   */
  protected ScaStructProperty tunerStruct = TUNER_STRUCT_EDEFAULT;

  /**
   * The default value of the '{@link #getAllocationID() <em>Allocation ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllocationID()
   * @generated
   * @ordered
   */
  protected static final String ALLOCATION_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAllocationID() <em>Allocation ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllocationID()
   * @generated
   * @ordered
   */
  protected String allocationID = ALLOCATION_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getTunerType() <em>Tuner Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerType()
   * @generated
   * @ordered
   */
  protected static final String TUNER_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTunerType() <em>Tuner Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerType()
   * @generated
   * @ordered
   */
  protected String tunerType = TUNER_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getTunerID() <em>Tuner ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerID()
   * @generated
   * @ordered
   */
  protected static final String TUNER_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTunerID() <em>Tuner ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerID()
   * @generated
   * @ordered
   */
  protected String tunerID = TUNER_ID_EDEFAULT;

  /**
   * The default value of the '{@link #isDeviceControl() <em>Device Control</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDeviceControl()
   * @generated
   * @ordered
   */
  protected static final boolean DEVICE_CONTROL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isDeviceControl() <em>Device Control</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isDeviceControl()
   * @generated
   * @ordered
   */
  protected boolean deviceControl = DEVICE_CONTROL_EDEFAULT;

  /**
   * The default value of the '{@link #getGroupID() <em>Group ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupID()
   * @generated
   * @ordered
   */
  protected static final String GROUP_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGroupID() <em>Group ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupID()
   * @generated
   * @ordered
   */
  protected String groupID = GROUP_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getRfFlowID() <em>Rf Flow ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRfFlowID()
   * @generated
   * @ordered
   */
  protected static final String RF_FLOW_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRfFlowID() <em>Rf Flow ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRfFlowID()
   * @generated
   * @ordered
   */
  protected String rfFlowID = RF_FLOW_ID_EDEFAULT;

  /**
   * The cached value of the '{@link #getTunerStatus() <em>Tuner Status</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStatus()
   * @generated
   * @ordered
   */
  protected TunerStatus tunerStatus;

  /**
   * The default value of the '{@link #getGain() <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGain()
   * @generated
   * @ordered
   */
  protected static final double GAIN_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getGain() <em>Gain</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGain()
   * @generated
   * @ordered
   */
  protected double gain = GAIN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TunerImpl()
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
    return FrontendPackage.Literals.TUNER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaStructProperty getTunerStruct()
  {
    return tunerStruct;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerStruct(ScaStructProperty newTunerStruct)
  {
    ScaStructProperty oldTunerStruct = tunerStruct;
    tunerStruct = newTunerStruct;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_STRUCT, oldTunerStruct, tunerStruct));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAllocationID()
  {
    return allocationID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAllocationID(String newAllocationID)
  {
    String oldAllocationID = allocationID;
    allocationID = newAllocationID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__ALLOCATION_ID, oldAllocationID, allocationID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTunerType()
  {
    return tunerType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerType(String newTunerType)
  {
    String oldTunerType = tunerType;
    tunerType = newTunerType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_TYPE, oldTunerType, tunerType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTunerID()
  {
    return tunerID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerID(String newTunerID)
  {
    String oldTunerID = tunerID;
    tunerID = newTunerID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_ID, oldTunerID, tunerID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDeviceControl()
  {
    return deviceControl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDeviceControl(boolean newDeviceControl)
  {
    boolean oldDeviceControl = deviceControl;
    deviceControl = newDeviceControl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__DEVICE_CONTROL, oldDeviceControl, deviceControl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGroupID()
  {
    return groupID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupID(String newGroupID)
  {
    String oldGroupID = groupID;
    groupID = newGroupID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__GROUP_ID, oldGroupID, groupID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRfFlowID()
  {
    return rfFlowID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRfFlowID(String newRfFlowID)
  {
    String oldRfFlowID = rfFlowID;
    rfFlowID = newRfFlowID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__RF_FLOW_ID, oldRfFlowID, rfFlowID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerContainer getTunerContainer()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER__TUNER_CONTAINER) return null;
    return (TunerContainer)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerContainer basicGetTunerContainer()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER__TUNER_CONTAINER) return null;
    return (TunerContainer)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTunerContainer(TunerContainer newTunerContainer, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newTunerContainer, FrontendPackage.TUNER__TUNER_CONTAINER, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerContainer(TunerContainer newTunerContainer)
  {
    if (newTunerContainer != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER__TUNER_CONTAINER && newTunerContainer != null))
    {
      if (EcoreUtil.isAncestor(this, newTunerContainer))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newTunerContainer != null)
        msgs = ((InternalEObject)newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__TUNERS, TunerContainer.class, msgs);
      msgs = basicSetTunerContainer(newTunerContainer, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_CONTAINER, newTunerContainer, newTunerContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerStatus getTunerStatus()
  {
    return tunerStatus;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTunerStatus(TunerStatus newTunerStatus, NotificationChain msgs)
  {
    TunerStatus oldTunerStatus = tunerStatus;
    tunerStatus = newTunerStatus;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_STATUS, oldTunerStatus, newTunerStatus);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerStatus(TunerStatus newTunerStatus)
  {
    if (newTunerStatus != tunerStatus)
    {
      NotificationChain msgs = null;
      if (tunerStatus != null)
        msgs = ((InternalEObject)tunerStatus).eInverseRemove(this, FrontendPackage.TUNER_STATUS__TUNER, TunerStatus.class, msgs);
      if (newTunerStatus != null)
        msgs = ((InternalEObject)newTunerStatus).eInverseAdd(this, FrontendPackage.TUNER_STATUS__TUNER, TunerStatus.class, msgs);
      msgs = basicSetTunerStatus(newTunerStatus, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__TUNER_STATUS, newTunerStatus, newTunerStatus));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getGain()
  {
    return gain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGain(double newGain)
  {
    double oldGain = gain;
    gain = newGain;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER__GAIN, oldGain, gain));
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
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetTunerContainer((TunerContainer)otherEnd, msgs);
      case FrontendPackage.TUNER__TUNER_STATUS:
        if (tunerStatus != null)
          msgs = ((InternalEObject)tunerStatus).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - FrontendPackage.TUNER__TUNER_STATUS, null, msgs);
        return basicSetTunerStatus((TunerStatus)otherEnd, msgs);
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
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        return basicSetTunerContainer(null, msgs);
      case FrontendPackage.TUNER__TUNER_STATUS:
        return basicSetTunerStatus(null, msgs);
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
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.TUNER_CONTAINER__TUNERS, TunerContainer.class, msgs);
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
      case FrontendPackage.TUNER__TUNER_STRUCT:
        return getTunerStruct();
      case FrontendPackage.TUNER__ALLOCATION_ID:
        return getAllocationID();
      case FrontendPackage.TUNER__TUNER_TYPE:
        return getTunerType();
      case FrontendPackage.TUNER__TUNER_ID:
        return getTunerID();
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        return isDeviceControl();
      case FrontendPackage.TUNER__GROUP_ID:
        return getGroupID();
      case FrontendPackage.TUNER__RF_FLOW_ID:
        return getRfFlowID();
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        if (resolve) return getTunerContainer();
        return basicGetTunerContainer();
      case FrontendPackage.TUNER__TUNER_STATUS:
        return getTunerStatus();
      case FrontendPackage.TUNER__GAIN:
        return getGain();
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
      case FrontendPackage.TUNER__TUNER_STRUCT:
        setTunerStruct((ScaStructProperty)newValue);
        return;
      case FrontendPackage.TUNER__ALLOCATION_ID:
        setAllocationID((String)newValue);
        return;
      case FrontendPackage.TUNER__TUNER_TYPE:
        setTunerType((String)newValue);
        return;
      case FrontendPackage.TUNER__TUNER_ID:
        setTunerID((String)newValue);
        return;
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        setDeviceControl((Boolean)newValue);
        return;
      case FrontendPackage.TUNER__GROUP_ID:
        setGroupID((String)newValue);
        return;
      case FrontendPackage.TUNER__RF_FLOW_ID:
        setRfFlowID((String)newValue);
        return;
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)newValue);
        return;
      case FrontendPackage.TUNER__TUNER_STATUS:
        setTunerStatus((TunerStatus)newValue);
        return;
      case FrontendPackage.TUNER__GAIN:
        setGain((Double)newValue);
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
      case FrontendPackage.TUNER__TUNER_STRUCT:
        setTunerStruct(TUNER_STRUCT_EDEFAULT);
        return;
      case FrontendPackage.TUNER__ALLOCATION_ID:
        setAllocationID(ALLOCATION_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER__TUNER_TYPE:
        setTunerType(TUNER_TYPE_EDEFAULT);
        return;
      case FrontendPackage.TUNER__TUNER_ID:
        setTunerID(TUNER_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        setDeviceControl(DEVICE_CONTROL_EDEFAULT);
        return;
      case FrontendPackage.TUNER__GROUP_ID:
        setGroupID(GROUP_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER__RF_FLOW_ID:
        setRfFlowID(RF_FLOW_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)null);
        return;
      case FrontendPackage.TUNER__TUNER_STATUS:
        setTunerStatus((TunerStatus)null);
        return;
      case FrontendPackage.TUNER__GAIN:
        setGain(GAIN_EDEFAULT);
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
      case FrontendPackage.TUNER__TUNER_STRUCT:
        return TUNER_STRUCT_EDEFAULT == null ? tunerStruct != null : !TUNER_STRUCT_EDEFAULT.equals(tunerStruct);
      case FrontendPackage.TUNER__ALLOCATION_ID:
        return ALLOCATION_ID_EDEFAULT == null ? allocationID != null : !ALLOCATION_ID_EDEFAULT.equals(allocationID);
      case FrontendPackage.TUNER__TUNER_TYPE:
        return TUNER_TYPE_EDEFAULT == null ? tunerType != null : !TUNER_TYPE_EDEFAULT.equals(tunerType);
      case FrontendPackage.TUNER__TUNER_ID:
        return TUNER_ID_EDEFAULT == null ? tunerID != null : !TUNER_ID_EDEFAULT.equals(tunerID);
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        return deviceControl != DEVICE_CONTROL_EDEFAULT;
      case FrontendPackage.TUNER__GROUP_ID:
        return GROUP_ID_EDEFAULT == null ? groupID != null : !GROUP_ID_EDEFAULT.equals(groupID);
      case FrontendPackage.TUNER__RF_FLOW_ID:
        return RF_FLOW_ID_EDEFAULT == null ? rfFlowID != null : !RF_FLOW_ID_EDEFAULT.equals(rfFlowID);
      case FrontendPackage.TUNER__TUNER_CONTAINER:
        return basicGetTunerContainer() != null;
      case FrontendPackage.TUNER__TUNER_STATUS:
        return tunerStatus != null;
      case FrontendPackage.TUNER__GAIN:
        return gain != GAIN_EDEFAULT;
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
    result.append(" (tunerStruct: ");
    result.append(tunerStruct);
    result.append(", allocationID: ");
    result.append(allocationID);
    result.append(", tunerType: ");
    result.append(tunerType);
    result.append(", tunerID: ");
    result.append(tunerID);
    result.append(", deviceControl: ");
    result.append(deviceControl);
    result.append(", groupID: ");
    result.append(groupID);
    result.append(", rfFlowID: ");
    result.append(rfFlowID);
    result.append(", gain: ");
    result.append(gain);
    result.append(')');
    return result.toString();
  }

} //TunerImpl
