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

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTuner <em>Tuner</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getCenterFrequency <em>Center Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getSampleRate <em>Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isEnabled <em>Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerStatusImpl extends MinimalEObjectImpl.Container implements TunerStatus
{
  /**
   * The default value of the '{@link #getCenterFrequency() <em>Center Frequency</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCenterFrequency()
   * @generated
   * @ordered
   */
  protected static final double CENTER_FREQUENCY_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getCenterFrequency() <em>Center Frequency</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCenterFrequency()
   * @generated
   * @ordered
   */
  protected double centerFrequency = CENTER_FREQUENCY_EDEFAULT;

  /**
   * The default value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBandwidth()
   * @generated
   * @ordered
   */
  protected static final double BANDWIDTH_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getBandwidth() <em>Bandwidth</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBandwidth()
   * @generated
   * @ordered
   */
  protected double bandwidth = BANDWIDTH_EDEFAULT;

  /**
   * The default value of the '{@link #getSampleRate() <em>Sample Rate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSampleRate()
   * @generated
   * @ordered
   */
  protected static final double SAMPLE_RATE_EDEFAULT = 0.0;

  /**
   * The cached value of the '{@link #getSampleRate() <em>Sample Rate</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSampleRate()
   * @generated
   * @ordered
   */
  protected double sampleRate = SAMPLE_RATE_EDEFAULT;

  /**
   * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEnabled()
   * @generated
   * @ordered
   */
  protected static final boolean ENABLED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isEnabled()
   * @generated
   * @ordered
   */
  protected boolean enabled = ENABLED_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TunerStatusImpl()
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
    return FrontendPackage.Literals.TUNER_STATUS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tuner getTuner()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER) return null;
    return (Tuner)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Tuner basicGetTuner()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER) return null;
    return (Tuner)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTuner(Tuner newTuner, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newTuner, FrontendPackage.TUNER_STATUS__TUNER, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTuner(Tuner newTuner)
  {
    if (newTuner != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER && newTuner != null))
    {
      if (EcoreUtil.isAncestor(this, newTuner))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newTuner != null)
        msgs = ((InternalEObject)newTuner).eInverseAdd(this, FrontendPackage.TUNER__TUNER_STATUS, Tuner.class, msgs);
      msgs = basicSetTuner(newTuner, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER, newTuner, newTuner));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getCenterFrequency()
  {
    return centerFrequency;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCenterFrequency(double newCenterFrequency)
  {
    double oldCenterFrequency = centerFrequency;
    centerFrequency = newCenterFrequency;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY, oldCenterFrequency, centerFrequency));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getBandwidth()
  {
    return bandwidth;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBandwidth(double newBandwidth)
  {
    double oldBandwidth = bandwidth;
    bandwidth = newBandwidth;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__BANDWIDTH, oldBandwidth, bandwidth));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public double getSampleRate()
  {
    return sampleRate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSampleRate(double newSampleRate)
  {
    double oldSampleRate = sampleRate;
    sampleRate = newSampleRate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__SAMPLE_RATE, oldSampleRate, sampleRate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isEnabled()
  {
    return enabled;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEnabled(boolean newEnabled)
  {
    boolean oldEnabled = enabled;
    enabled = newEnabled;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__ENABLED, oldEnabled, enabled));
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetTuner((Tuner)otherEnd, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        return basicSetTuner(null, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.TUNER__TUNER_STATUS, Tuner.class, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        if (resolve) return getTuner();
        return basicGetTuner();
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        return getCenterFrequency();
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        return getBandwidth();
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        return getSampleRate();
      case FrontendPackage.TUNER_STATUS__ENABLED:
        return isEnabled();
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        setTuner((Tuner)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        setCenterFrequency((Double)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        setBandwidth((Double)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        setSampleRate((Double)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__ENABLED:
        setEnabled((Boolean)newValue);
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        setTuner((Tuner)null);
        return;
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        setCenterFrequency(CENTER_FREQUENCY_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        setBandwidth(BANDWIDTH_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        setSampleRate(SAMPLE_RATE_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__ENABLED:
        setEnabled(ENABLED_EDEFAULT);
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
      case FrontendPackage.TUNER_STATUS__TUNER:
        return basicGetTuner() != null;
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        return centerFrequency != CENTER_FREQUENCY_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        return bandwidth != BANDWIDTH_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        return sampleRate != SAMPLE_RATE_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__ENABLED:
        return enabled != ENABLED_EDEFAULT;
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
    result.append(" (centerFrequency: ");
    result.append(centerFrequency);
    result.append(", bandwidth: ");
    result.append(bandwidth);
    result.append(", sampleRate: ");
    result.append(sampleRate);
    result.append(", enabled: ");
    result.append(enabled);
    result.append(')');
    return result.toString();
  }

} //TunerStatusImpl
