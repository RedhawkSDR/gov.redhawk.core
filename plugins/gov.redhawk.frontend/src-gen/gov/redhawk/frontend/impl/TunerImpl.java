/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerStatus;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerImpl#getRfFlowID <em>Rf Flow ID</em>}</li>
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FrontendPackage.TUNER__ALLOCATION_ID:
        return getAllocationID();
      case FrontendPackage.TUNER__TUNER_TYPE:
        return getTunerType();
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        return isDeviceControl();
      case FrontendPackage.TUNER__GROUP_ID:
        return getGroupID();
      case FrontendPackage.TUNER__RF_FLOW_ID:
        return getRfFlowID();
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
      case FrontendPackage.TUNER__ALLOCATION_ID:
        setAllocationID((String)newValue);
        return;
      case FrontendPackage.TUNER__TUNER_TYPE:
        setTunerType((String)newValue);
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
      case FrontendPackage.TUNER__ALLOCATION_ID:
        setAllocationID(ALLOCATION_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER__TUNER_TYPE:
        setTunerType(TUNER_TYPE_EDEFAULT);
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
      case FrontendPackage.TUNER__ALLOCATION_ID:
        return ALLOCATION_ID_EDEFAULT == null ? allocationID != null : !ALLOCATION_ID_EDEFAULT.equals(allocationID);
      case FrontendPackage.TUNER__TUNER_TYPE:
        return TUNER_TYPE_EDEFAULT == null ? tunerType != null : !TUNER_TYPE_EDEFAULT.equals(tunerType);
      case FrontendPackage.TUNER__DEVICE_CONTROL:
        return deviceControl != DEVICE_CONTROL_EDEFAULT;
      case FrontendPackage.TUNER__GROUP_ID:
        return GROUP_ID_EDEFAULT == null ? groupID != null : !GROUP_ID_EDEFAULT.equals(groupID);
      case FrontendPackage.TUNER__RF_FLOW_ID:
        return RF_FLOW_ID_EDEFAULT == null ? rfFlowID != null : !RF_FLOW_ID_EDEFAULT.equals(rfFlowID);
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
    result.append(" (allocationID: ");
    result.append(allocationID);
    result.append(", tunerType: ");
    result.append(tunerType);
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
