/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;
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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerContainer <em>Tuner Container</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerStatusStruct <em>Tuner Status Struct</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getSimples <em>Simples</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerID <em>Tuner ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerType <em>Tuner Type</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getAllocationID <em>Allocation ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getCenterFrequency <em>Center Frequency</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getBandwidth <em>Bandwidth</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getSampleRate <em>Sample Rate</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getGroupID <em>Group ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getRfFlowID <em>Rf Flow ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getGain <em>Gain</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isAgc <em>Agc</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getReferenceSource <em>Reference Source</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isDeviceControl <em>Device Control</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getListenerAllocations <em>Listener Allocations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerStatusImpl extends MinimalEObjectImpl.Container implements TunerStatus
{
  /**
   * The default value of the '{@link #getTunerStatusStruct() <em>Tuner Status Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStatusStruct()
   * @generated
   * @ordered
   */
  protected static final ScaStructProperty TUNER_STATUS_STRUCT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTunerStatusStruct() <em>Tuner Status Struct</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTunerStatusStruct()
   * @generated
   * @ordered
   */
  protected ScaStructProperty tunerStatusStruct = TUNER_STATUS_STRUCT_EDEFAULT;

  /**
   * The cached value of the '{@link #getSimples() <em>Simples</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimples()
   * @generated
   * @ordered
   */
  protected EList<ScaSimpleProperty> simples;

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
   * The default value of the '{@link #isAgc() <em>Agc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAgc()
   * @generated
   * @ordered
   */
  protected static final boolean AGC_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAgc() <em>Agc</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAgc()
   * @generated
   * @ordered
   */
  protected boolean agc = AGC_EDEFAULT;

  /**
   * The default value of the '{@link #getReferenceSource() <em>Reference Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceSource()
   * @generated
   * @ordered
   */
  protected static final long REFERENCE_SOURCE_EDEFAULT = 0L;

  /**
   * The cached value of the '{@link #getReferenceSource() <em>Reference Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferenceSource()
   * @generated
   * @ordered
   */
  protected long referenceSource = REFERENCE_SOURCE_EDEFAULT;

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
   * The cached value of the '{@link #getListenerAllocations() <em>Listener Allocations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListenerAllocations()
   * @generated
   * @ordered
   */
  protected EList<ListenerAllocation> listenerAllocations;

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
  public TunerContainer getTunerContainer()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER) return null;
    return (TunerContainer)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerContainer basicGetTunerContainer()
  {
    if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER) return null;
    return (TunerContainer)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTunerContainer(TunerContainer newTunerContainer, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newTunerContainer, FrontendPackage.TUNER_STATUS__TUNER_CONTAINER, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerContainer(TunerContainer newTunerContainer)
  {
    if (newTunerContainer != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER && newTunerContainer != null))
    {
      if (EcoreUtil.isAncestor(this, newTunerContainer))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newTunerContainer != null)
        msgs = ((InternalEObject)newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS, TunerContainer.class, msgs);
      msgs = basicSetTunerContainer(newTunerContainer, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_CONTAINER, newTunerContainer, newTunerContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ScaStructProperty getTunerStatusStruct()
  {
    return tunerStatusStruct;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerStatusStruct(ScaStructProperty newTunerStatusStruct)
  {
    ScaStructProperty oldTunerStatusStruct = tunerStatusStruct;
    tunerStatusStruct = newTunerStatusStruct;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT, oldTunerStatusStruct, tunerStatusStruct));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ScaSimpleProperty> getSimples()
  {
    if (simples == null)
    {
      simples = new EDataTypeEList<ScaSimpleProperty>(ScaSimpleProperty.class, this, FrontendPackage.TUNER_STATUS__SIMPLES);
    }
    return simples;
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_ID, oldTunerID, tunerID));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_TYPE, oldTunerType, tunerType));
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
   * @generated_NOT
   */
  public void setAllocationID(String newAllocationID)
  {
    String oldAllocationID = allocationID;
    allocationID = newAllocationID;
    if (eNotificationRequired()) {
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__ALLOCATION_ID, oldAllocationID, allocationID));
      getTunerContainer().eNotify(new ENotificationImpl((TunerContainerImpl) getTunerContainer(), Notification.MOVE, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS, Notification.NO_INDEX, Notification.NO_INDEX));
      getListenerAllocations().clear();
      if (allocationID != null && allocationID.indexOf(",") > -1) {
    	  String[] allocations = allocationID.split(",");
    	  for (int index = 1; index < allocations.length; ++index) {
    		  if (allocations[index].length() > 0) {
	    		  ListenerAllocation listener = FrontendFactory.eINSTANCE.createListenerAllocation();
	    		  listener.setListenerID(allocations[index]);
	    		  listenerAllocations.add(listener);
    		  }
    	  }
      }
    }
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__GROUP_ID, oldGroupID, groupID));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__RF_FLOW_ID, oldRfFlowID, rfFlowID));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__GAIN, oldGain, gain));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAgc()
  {
    return agc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAgc(boolean newAgc)
  {
    boolean oldAgc = agc;
    agc = newAgc;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__AGC, oldAgc, agc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public long getReferenceSource()
  {
    return referenceSource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferenceSource(long newReferenceSource)
  {
    long oldReferenceSource = referenceSource;
    referenceSource = newReferenceSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE, oldReferenceSource, referenceSource));
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
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__DEVICE_CONTROL, oldDeviceControl, deviceControl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ListenerAllocation> getListenerAllocations()
  {
    if (listenerAllocations == null)
    {
      listenerAllocations = new EObjectContainmentWithInverseEList<ListenerAllocation>(ListenerAllocation.class, this, FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS, FrontendPackage.LISTENER_ALLOCATION__TUNER_STATUS);
    }
    return listenerAllocations;
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetTunerContainer((TunerContainer)otherEnd, msgs);
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getListenerAllocations()).basicAdd(otherEnd, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        return basicSetTunerContainer(null, msgs);
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        return ((InternalEList<?>)getListenerAllocations()).basicRemove(otherEnd, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS, TunerContainer.class, msgs);
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        if (resolve) return getTunerContainer();
        return basicGetTunerContainer();
      case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
        return getTunerStatusStruct();
      case FrontendPackage.TUNER_STATUS__SIMPLES:
        return getSimples();
      case FrontendPackage.TUNER_STATUS__TUNER_ID:
        return getTunerID();
      case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
        return getTunerType();
      case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
        return getAllocationID();
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        return getCenterFrequency();
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        return getBandwidth();
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        return getSampleRate();
      case FrontendPackage.TUNER_STATUS__GROUP_ID:
        return getGroupID();
      case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
        return getRfFlowID();
      case FrontendPackage.TUNER_STATUS__ENABLED:
        return isEnabled();
      case FrontendPackage.TUNER_STATUS__GAIN:
        return getGain();
      case FrontendPackage.TUNER_STATUS__AGC:
        return isAgc();
      case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
        return getReferenceSource();
      case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
        return isDeviceControl();
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        return getListenerAllocations();
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
        setTunerStatusStruct((ScaStructProperty)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__SIMPLES:
        getSimples().clear();
        getSimples().addAll((Collection<? extends ScaSimpleProperty>)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_ID:
        setTunerID((String)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
        setTunerType((String)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
        setAllocationID((String)newValue);
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
      case FrontendPackage.TUNER_STATUS__GROUP_ID:
        setGroupID((String)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
        setRfFlowID((String)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__ENABLED:
        setEnabled((Boolean)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__GAIN:
        setGain((Double)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__AGC:
        setAgc((Boolean)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
        setReferenceSource((Long)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
        setDeviceControl((Boolean)newValue);
        return;
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        getListenerAllocations().clear();
        getListenerAllocations().addAll((Collection<? extends ListenerAllocation>)newValue);
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        setTunerContainer((TunerContainer)null);
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
        setTunerStatusStruct(TUNER_STATUS_STRUCT_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__SIMPLES:
        getSimples().clear();
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_ID:
        setTunerID(TUNER_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
        setTunerType(TUNER_TYPE_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
        setAllocationID(ALLOCATION_ID_EDEFAULT);
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
      case FrontendPackage.TUNER_STATUS__GROUP_ID:
        setGroupID(GROUP_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
        setRfFlowID(RF_FLOW_ID_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__ENABLED:
        setEnabled(ENABLED_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__GAIN:
        setGain(GAIN_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__AGC:
        setAgc(AGC_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
        setReferenceSource(REFERENCE_SOURCE_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
        setDeviceControl(DEVICE_CONTROL_EDEFAULT);
        return;
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        getListenerAllocations().clear();
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
      case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
        return basicGetTunerContainer() != null;
      case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
        return TUNER_STATUS_STRUCT_EDEFAULT == null ? tunerStatusStruct != null : !TUNER_STATUS_STRUCT_EDEFAULT.equals(tunerStatusStruct);
      case FrontendPackage.TUNER_STATUS__SIMPLES:
        return simples != null && !simples.isEmpty();
      case FrontendPackage.TUNER_STATUS__TUNER_ID:
        return TUNER_ID_EDEFAULT == null ? tunerID != null : !TUNER_ID_EDEFAULT.equals(tunerID);
      case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
        return TUNER_TYPE_EDEFAULT == null ? tunerType != null : !TUNER_TYPE_EDEFAULT.equals(tunerType);
      case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
        return ALLOCATION_ID_EDEFAULT == null ? allocationID != null : !ALLOCATION_ID_EDEFAULT.equals(allocationID);
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
        return centerFrequency != CENTER_FREQUENCY_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
        return bandwidth != BANDWIDTH_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
        return sampleRate != SAMPLE_RATE_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__GROUP_ID:
        return GROUP_ID_EDEFAULT == null ? groupID != null : !GROUP_ID_EDEFAULT.equals(groupID);
      case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
        return RF_FLOW_ID_EDEFAULT == null ? rfFlowID != null : !RF_FLOW_ID_EDEFAULT.equals(rfFlowID);
      case FrontendPackage.TUNER_STATUS__ENABLED:
        return enabled != ENABLED_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__GAIN:
        return gain != GAIN_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__AGC:
        return agc != AGC_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
        return referenceSource != REFERENCE_SOURCE_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
        return deviceControl != DEVICE_CONTROL_EDEFAULT;
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
        return listenerAllocations != null && !listenerAllocations.isEmpty();
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
    result.append(" (tunerStatusStruct: ");
    result.append(tunerStatusStruct);
    result.append(", simples: ");
    result.append(simples);
    result.append(", tunerID: ");
    result.append(tunerID);
    result.append(", tunerType: ");
    result.append(tunerType);
    result.append(", allocationID: ");
    result.append(allocationID);
    result.append(", centerFrequency: ");
    result.append(centerFrequency);
    result.append(", bandwidth: ");
    result.append(bandwidth);
    result.append(", sampleRate: ");
    result.append(sampleRate);
    result.append(", groupID: ");
    result.append(groupID);
    result.append(", rfFlowID: ");
    result.append(rfFlowID);
    result.append(", enabled: ");
    result.append(enabled);
    result.append(", gain: ");
    result.append(gain);
    result.append(", agc: ");
    result.append(agc);
    result.append(", referenceSource: ");
    result.append(referenceSource);
    result.append(", deviceControl: ");
    result.append(deviceControl);
    result.append(')');
    return result.toString();
  }

} //TunerStatusImpl
