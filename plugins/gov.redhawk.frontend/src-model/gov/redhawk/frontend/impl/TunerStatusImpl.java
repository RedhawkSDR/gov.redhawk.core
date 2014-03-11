/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// BEGIN GENERATED CODE
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
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
 * An implementation of the model object '<em><b>Tuner Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerContainer <em>Tuner Container</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerStatusStruct <em>Tuner Status Struct</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getSimples <em>Simples</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isAllocated <em>Allocated</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerID <em>Tuner ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getTunerType <em>Tuner Type</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getAllocationID <em>Allocation ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getCenterFrequency <em>Center Frequency</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getBandwidth <em>Bandwidth</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getSampleRate <em>Sample Rate</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getGroupID <em>Group ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getRfFlowID <em>Rf Flow ID</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getGain <em>Gain</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isAgc <em>Agc</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getReferenceSource <em>Reference Source</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#isDeviceControl <em>Device Control</em>}</li>
 * <li>{@link gov.redhawk.frontend.impl.TunerStatusImpl#getListenerAllocations <em>Listener Allocations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TunerStatusImpl extends MinimalEObjectImpl.Container implements TunerStatus {
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
	 * The default value of the '{@link #isAllocated() <em>Allocated</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllocated()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOCATED_EDEFAULT = false;

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
	 * This is true if the Tuner Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean tunerTypeESet;

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
	 * This is true if the Allocation ID attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean allocationIDESet;

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
	 * This is true if the Center Frequency attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean centerFrequencyESet;

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
	 * This is true if the Bandwidth attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean bandwidthESet;

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
	 * This is true if the Sample Rate attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean sampleRateESet;

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
	 * This is true if the Group ID attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean groupIDESet;

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
	 * This is true if the Rf Flow ID attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rfFlowIDESet;

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
	 * This is true if the Enabled attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean enabledESet;

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
	 * This is true if the Gain attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean gainESet;

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
	 * This is true if the Agc attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean agcESet;

	/**
	 * The default value of the '{@link #getReferenceSource() <em>Reference Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceSource()
	 * @generated
	 * @ordered
	 */
	protected static final int REFERENCE_SOURCE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getReferenceSource() <em>Reference Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceSource()
	 * @generated
	 * @ordered
	 */
	protected int referenceSource = REFERENCE_SOURCE_EDEFAULT;

	/**
	 * This is true if the Reference Source attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean referenceSourceESet;

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
	 * This is true if the Device Control attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean deviceControlESet;

	/**
	 * The cached value of the '{@link #getListenerAllocations() <em>Listener Allocations</em>}' containment reference
	 * list.
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
	protected TunerStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FrontendPackage.Literals.TUNER_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TunerContainer getTunerContainer() {
		if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER)
			return null;
		return (TunerContainer) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TunerContainer basicGetTunerContainer() {
		if (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER)
			return null;
		return (TunerContainer) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTunerContainer(TunerContainer newTunerContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newTunerContainer, FrontendPackage.TUNER_STATUS__TUNER_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTunerContainer(TunerContainer newTunerContainer) {
		if (newTunerContainer != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.TUNER_STATUS__TUNER_CONTAINER && newTunerContainer != null)) {
			if (EcoreUtil.isAncestor(this, newTunerContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newTunerContainer != null)
				msgs = ((InternalEObject) newTunerContainer).eInverseAdd(this, FrontendPackage.TUNER_CONTAINER__TUNER_STATUS, TunerContainer.class, msgs);
			msgs = basicSetTunerContainer(newTunerContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_CONTAINER, newTunerContainer, newTunerContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaStructProperty getTunerStatusStruct() {
		return tunerStatusStruct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTunerStatusStruct(ScaStructProperty newTunerStatusStruct) {
		ScaStructProperty oldTunerStatusStruct = tunerStatusStruct;
		tunerStatusStruct = newTunerStatusStruct;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT, oldTunerStatusStruct, tunerStatusStruct));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaSimpleProperty> getSimples() {
		ScaStructProperty _tunerStatusStruct = this.getTunerStatusStruct();
		if (_tunerStatusStruct == null) {
			return ECollections.emptyEList();
		}
		return ECollections.unmodifiableEList(_tunerStatusStruct.getSimples());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isAllocated() {
		return allocationID != null && !allocationID.isEmpty();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getTunerID() {
		if (getTunerContainer() != null) {
			return String.valueOf(getTunerContainer().getTunerStatus().indexOf(this));
		} else {
			return "";
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTunerType() {
		return tunerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTunerType(String newTunerType) {
		String oldTunerType = tunerType;
		tunerType = newTunerType;
		boolean oldTunerTypeESet = tunerTypeESet;
		tunerTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__TUNER_TYPE, oldTunerType, tunerType, !oldTunerTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTunerType() {
		String oldTunerType = tunerType;
		boolean oldTunerTypeESet = tunerTypeESet;
		tunerType = TUNER_TYPE_EDEFAULT;
		tunerTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__TUNER_TYPE, oldTunerType, TUNER_TYPE_EDEFAULT,
				oldTunerTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTunerType() {
		return tunerTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAllocationID() {
		return allocationID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAllocationID(String newAllocationID) {
		String oldAllocationID = allocationID;
		allocationID = newAllocationID;
		boolean oldAllocationIDESet = allocationIDESet;
		allocationIDESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__ALLOCATION_ID, oldAllocationID, allocationID,
				!oldAllocationIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAllocationID() {
		String oldAllocationID = allocationID;
		boolean oldAllocationIDESet = allocationIDESet;
		allocationID = ALLOCATION_ID_EDEFAULT;
		allocationIDESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__ALLOCATION_ID, oldAllocationID, ALLOCATION_ID_EDEFAULT,
				oldAllocationIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAllocationID() {
		return allocationIDESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getCenterFrequency() {
		return centerFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCenterFrequency(double newCenterFrequency) {
		double oldCenterFrequency = centerFrequency;
		centerFrequency = newCenterFrequency;
		boolean oldCenterFrequencyESet = centerFrequencyESet;
		centerFrequencyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY, oldCenterFrequency, centerFrequency,
				!oldCenterFrequencyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetCenterFrequency() {
		double oldCenterFrequency = centerFrequency;
		boolean oldCenterFrequencyESet = centerFrequencyESet;
		centerFrequency = CENTER_FREQUENCY_EDEFAULT;
		centerFrequencyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY, oldCenterFrequency,
				CENTER_FREQUENCY_EDEFAULT, oldCenterFrequencyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetCenterFrequency() {
		return centerFrequencyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getBandwidth() {
		return bandwidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBandwidth(double newBandwidth) {
		double oldBandwidth = bandwidth;
		bandwidth = newBandwidth;
		boolean oldBandwidthESet = bandwidthESet;
		bandwidthESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__BANDWIDTH, oldBandwidth, bandwidth, !oldBandwidthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetBandwidth() {
		double oldBandwidth = bandwidth;
		boolean oldBandwidthESet = bandwidthESet;
		bandwidth = BANDWIDTH_EDEFAULT;
		bandwidthESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__BANDWIDTH, oldBandwidth, BANDWIDTH_EDEFAULT, oldBandwidthESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetBandwidth() {
		return bandwidthESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getSampleRate() {
		return sampleRate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSampleRate(double newSampleRate) {
		double oldSampleRate = sampleRate;
		sampleRate = newSampleRate;
		boolean oldSampleRateESet = sampleRateESet;
		sampleRateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__SAMPLE_RATE, oldSampleRate, sampleRate, !oldSampleRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSampleRate() {
		double oldSampleRate = sampleRate;
		boolean oldSampleRateESet = sampleRateESet;
		sampleRate = SAMPLE_RATE_EDEFAULT;
		sampleRateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__SAMPLE_RATE, oldSampleRate, SAMPLE_RATE_EDEFAULT,
				oldSampleRateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSampleRate() {
		return sampleRateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getGroupID() {
		return groupID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGroupID(String newGroupID) {
		String oldGroupID = groupID;
		groupID = newGroupID;
		boolean oldGroupIDESet = groupIDESet;
		groupIDESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__GROUP_ID, oldGroupID, groupID, !oldGroupIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGroupID() {
		String oldGroupID = groupID;
		boolean oldGroupIDESet = groupIDESet;
		groupID = GROUP_ID_EDEFAULT;
		groupIDESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__GROUP_ID, oldGroupID, GROUP_ID_EDEFAULT, oldGroupIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGroupID() {
		return groupIDESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRfFlowID() {
		return rfFlowID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRfFlowID(String newRfFlowID) {
		String oldRfFlowID = rfFlowID;
		rfFlowID = newRfFlowID;
		boolean oldRfFlowIDESet = rfFlowIDESet;
		rfFlowIDESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__RF_FLOW_ID, oldRfFlowID, rfFlowID, !oldRfFlowIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetRfFlowID() {
		String oldRfFlowID = rfFlowID;
		boolean oldRfFlowIDESet = rfFlowIDESet;
		rfFlowID = RF_FLOW_ID_EDEFAULT;
		rfFlowIDESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__RF_FLOW_ID, oldRfFlowID, RF_FLOW_ID_EDEFAULT, oldRfFlowIDESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetRfFlowID() {
		return rfFlowIDESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		boolean oldEnabledESet = enabledESet;
		enabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__ENABLED, oldEnabled, enabled, !oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetEnabled() {
		boolean oldEnabled = enabled;
		boolean oldEnabledESet = enabledESet;
		enabled = ENABLED_EDEFAULT;
		enabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__ENABLED, oldEnabled, ENABLED_EDEFAULT, oldEnabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetEnabled() {
		return enabledESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getGain() {
		return gain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGain(double newGain) {
		double oldGain = gain;
		gain = newGain;
		boolean oldGainESet = gainESet;
		gainESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__GAIN, oldGain, gain, !oldGainESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetGain() {
		double oldGain = gain;
		boolean oldGainESet = gainESet;
		gain = GAIN_EDEFAULT;
		gainESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__GAIN, oldGain, GAIN_EDEFAULT, oldGainESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetGain() {
		return gainESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAgc() {
		return agc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAgc(boolean newAgc) {
		boolean oldAgc = agc;
		agc = newAgc;
		boolean oldAgcESet = agcESet;
		agcESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__AGC, oldAgc, agc, !oldAgcESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAgc() {
		boolean oldAgc = agc;
		boolean oldAgcESet = agcESet;
		agc = AGC_EDEFAULT;
		agcESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__AGC, oldAgc, AGC_EDEFAULT, oldAgcESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAgc() {
		return agcESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getReferenceSource() {
		return referenceSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceSource(int newReferenceSource) {
		int oldReferenceSource = referenceSource;
		referenceSource = newReferenceSource;
		boolean oldReferenceSourceESet = referenceSourceESet;
		referenceSourceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE, oldReferenceSource, referenceSource,
				!oldReferenceSourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetReferenceSource() {
		int oldReferenceSource = referenceSource;
		boolean oldReferenceSourceESet = referenceSourceESet;
		referenceSource = REFERENCE_SOURCE_EDEFAULT;
		referenceSourceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE, oldReferenceSource,
				REFERENCE_SOURCE_EDEFAULT, oldReferenceSourceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetReferenceSource() {
		return referenceSourceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDeviceControl() {
		return deviceControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDeviceControl(boolean newDeviceControl) {
		boolean oldDeviceControl = deviceControl;
		deviceControl = newDeviceControl;
		boolean oldDeviceControlESet = deviceControlESet;
		deviceControlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.TUNER_STATUS__DEVICE_CONTROL, oldDeviceControl, deviceControl,
				!oldDeviceControlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDeviceControl() {
		boolean oldDeviceControl = deviceControl;
		boolean oldDeviceControlESet = deviceControlESet;
		deviceControl = DEVICE_CONTROL_EDEFAULT;
		deviceControlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, FrontendPackage.TUNER_STATUS__DEVICE_CONTROL, oldDeviceControl, DEVICE_CONTROL_EDEFAULT,
				oldDeviceControlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDeviceControl() {
		return deviceControlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ListenerAllocation> getListenerAllocations() {
		if (listenerAllocations == null) {
			listenerAllocations = new EObjectContainmentWithInverseEList<ListenerAllocation>(ListenerAllocation.class, this,
				FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS, FrontendPackage.LISTENER_ALLOCATION__TUNER_STATUS);
		}
		return listenerAllocations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ScaSimpleProperty getSimple(final String propID) {
		ScaSimpleProperty retVal = null;
		ScaStructProperty _tunerStatusStruct = this.getTunerStatusStruct();
		if (_tunerStatusStruct != null) {
			ScaStructProperty _tunerStatusStruct_1 = this.getTunerStatusStruct();
			retVal = _tunerStatusStruct_1.getSimple(propID);
		}
		return retVal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetTunerContainer((TunerContainer) otherEnd, msgs);
		case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getListenerAllocations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			return basicSetTunerContainer(null, msgs);
		case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
			return ((InternalEList< ? >) getListenerAllocations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			if (resolve)
				return getTunerContainer();
			return basicGetTunerContainer();
		case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
			return getTunerStatusStruct();
		case FrontendPackage.TUNER_STATUS__SIMPLES:
			return getSimples();
		case FrontendPackage.TUNER_STATUS__ALLOCATED:
			return isAllocated();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			setTunerContainer((TunerContainer) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
			setTunerStatusStruct((ScaStructProperty) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
			setTunerType((String) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
			setAllocationID((String) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
			setCenterFrequency((Double) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__BANDWIDTH:
			setBandwidth((Double) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
			setSampleRate((Double) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__GROUP_ID:
			setGroupID((String) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
			setRfFlowID((String) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__ENABLED:
			setEnabled((Boolean) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__GAIN:
			setGain((Double) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__AGC:
			setAgc((Boolean) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
			setReferenceSource((Integer) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
			setDeviceControl((Boolean) newValue);
			return;
		case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
			getListenerAllocations().clear();
			getListenerAllocations().addAll((Collection< ? extends ListenerAllocation>) newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			setTunerContainer((TunerContainer) null);
			return;
		case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
			setTunerStatusStruct(TUNER_STATUS_STRUCT_EDEFAULT);
			return;
		case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
			unsetTunerType();
			return;
		case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
			unsetAllocationID();
			return;
		case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
			unsetCenterFrequency();
			return;
		case FrontendPackage.TUNER_STATUS__BANDWIDTH:
			unsetBandwidth();
			return;
		case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
			unsetSampleRate();
			return;
		case FrontendPackage.TUNER_STATUS__GROUP_ID:
			unsetGroupID();
			return;
		case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
			unsetRfFlowID();
			return;
		case FrontendPackage.TUNER_STATUS__ENABLED:
			unsetEnabled();
			return;
		case FrontendPackage.TUNER_STATUS__GAIN:
			unsetGain();
			return;
		case FrontendPackage.TUNER_STATUS__AGC:
			unsetAgc();
			return;
		case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
			unsetReferenceSource();
			return;
		case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
			unsetDeviceControl();
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case FrontendPackage.TUNER_STATUS__TUNER_CONTAINER:
			return basicGetTunerContainer() != null;
		case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
			return TUNER_STATUS_STRUCT_EDEFAULT == null ? tunerStatusStruct != null : !TUNER_STATUS_STRUCT_EDEFAULT.equals(tunerStatusStruct);
		case FrontendPackage.TUNER_STATUS__SIMPLES:
			return !getSimples().isEmpty();
		case FrontendPackage.TUNER_STATUS__ALLOCATED:
			return isAllocated() != ALLOCATED_EDEFAULT;
		case FrontendPackage.TUNER_STATUS__TUNER_ID:
			return TUNER_ID_EDEFAULT == null ? getTunerID() != null : !TUNER_ID_EDEFAULT.equals(getTunerID());
		case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
			return isSetTunerType();
		case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
			return isSetAllocationID();
		case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
			return isSetCenterFrequency();
		case FrontendPackage.TUNER_STATUS__BANDWIDTH:
			return isSetBandwidth();
		case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
			return isSetSampleRate();
		case FrontendPackage.TUNER_STATUS__GROUP_ID:
			return isSetGroupID();
		case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
			return isSetRfFlowID();
		case FrontendPackage.TUNER_STATUS__ENABLED:
			return isSetEnabled();
		case FrontendPackage.TUNER_STATUS__GAIN:
			return isSetGain();
		case FrontendPackage.TUNER_STATUS__AGC:
			return isSetAgc();
		case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
			return isSetReferenceSource();
		case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
			return isSetDeviceControl();
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
	public Object eInvoke(int operationID, EList< ? > arguments) throws InvocationTargetException {
		switch (operationID) {
		case FrontendPackage.TUNER_STATUS___GET_SIMPLE__STRING:
			return getSimple((String) arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (tunerStatusStruct: ");
		result.append(tunerStatusStruct);
		result.append(", tunerType: ");
		if (tunerTypeESet)
			result.append(tunerType);
		else
			result.append("<unset>");
		result.append(", allocationID: ");
		if (allocationIDESet)
			result.append(allocationID);
		else
			result.append("<unset>");
		result.append(", centerFrequency: ");
		if (centerFrequencyESet)
			result.append(centerFrequency);
		else
			result.append("<unset>");
		result.append(", bandwidth: ");
		if (bandwidthESet)
			result.append(bandwidth);
		else
			result.append("<unset>");
		result.append(", sampleRate: ");
		if (sampleRateESet)
			result.append(sampleRate);
		else
			result.append("<unset>");
		result.append(", groupID: ");
		if (groupIDESet)
			result.append(groupID);
		else
			result.append("<unset>");
		result.append(", rfFlowID: ");
		if (rfFlowIDESet)
			result.append(rfFlowID);
		else
			result.append("<unset>");
		result.append(", enabled: ");
		if (enabledESet)
			result.append(enabled);
		else
			result.append("<unset>");
		result.append(", gain: ");
		if (gainESet)
			result.append(gain);
		else
			result.append("<unset>");
		result.append(", agc: ");
		if (agcESet)
			result.append(agc);
		else
			result.append("<unset>");
		result.append(", referenceSource: ");
		if (referenceSourceESet)
			result.append(referenceSource);
		else
			result.append("<unset>");
		result.append(", deviceControl: ");
		if (deviceControlESet)
			result.append(deviceControl);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} // TunerStatusImpl
