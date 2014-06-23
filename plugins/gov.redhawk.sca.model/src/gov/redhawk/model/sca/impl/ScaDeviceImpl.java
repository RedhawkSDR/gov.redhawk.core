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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.MergeAggregateDevices;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.PluginUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

import CF.AggregateDevice;
import CF.AggregateDeviceHelper;
import CF.DataType;
import CF.Device;
import CF.DeviceHelper;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Device</b></em>'.
 * @since 12.0 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getChildDevices <em>Child Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getAdminState <em>Admin State</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getOperationalState <em>Operational State</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getUsageState <em>Usage State</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getParentDevice <em>Parent Device</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceImpl#getDevMgr <em>Dev Mgr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaDeviceImpl< D extends Device > extends ScaAbstractComponentImpl<D> implements ScaDevice<D> {

	/**
	 * The cached value of the '{@link #getChildDevices() <em>Child Devices</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildDevices()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaDevice< ? >> childDevices;
	/**
	 * The default value of the '{@link #getAdminState() <em>Admin State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminState()
	 * @generated
	 * @ordered
	 */
	protected static final AdminType ADMIN_STATE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getAdminState() <em>Admin State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdminState()
	 * @generated
	 * @ordered
	 */
	protected AdminType adminState = ADMIN_STATE_EDEFAULT;
	/**
	 * This is true if the Admin State attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean adminStateESet;
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;
	/**
	 * This is true if the Label attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean labelESet;
	/**
	 * The default value of the '{@link #getOperationalState() <em>Operational State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationalState()
	 * @generated
	 * @ordered
	 */
	protected static final OperationalType OPERATIONAL_STATE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getOperationalState() <em>Operational State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationalState()
	 * @generated
	 * @ordered
	 */
	protected OperationalType operationalState = OPERATIONAL_STATE_EDEFAULT;
	/**
	 * This is true if the Operational State attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean operationalStateESet;
	/**
	 * The default value of the '{@link #getUsageState() <em>Usage State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsageState()
	 * @generated
	 * @ordered
	 */
	protected static final UsageType USAGE_STATE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getUsageState() <em>Usage State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsageState()
	 * @generated
	 * @ordered
	 */
	protected UsageType usageState = USAGE_STATE_EDEFAULT;
	/**
	 * This is true if the Usage State attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean usageStateESet;
	/**
	 * The cached value of the '{@link #getParentDevice() <em>Parent Device</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentDevice()
	 * @generated
	 * @ordered
	 */
	protected ScaDevice< ? > parentDevice;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDeviceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DEVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ScaDevice< ? >> getChildDevices() {
		if (childDevices == null) {
			childDevices = new EObjectWithInverseEList.Unsettable<ScaDevice< ? >>(ScaDevice.class, this, ScaPackage.SCA_DEVICE__CHILD_DEVICES,
				ScaPackage.SCA_DEVICE__PARENT_DEVICE);
		}
		return childDevices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetChildDevices() {
		if (childDevices != null)
			((InternalEList.Unsettable< ? >) childDevices).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetChildDevices() {
		return childDevices != null && ((InternalEList.Unsettable< ? >) childDevices).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdminType getAdminState() {
		return adminState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAdminState(AdminType newAdminState) {
		AdminType oldAdminState = adminState;
		adminState = newAdminState;
		boolean oldAdminStateESet = adminStateESet;
		adminStateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__ADMIN_STATE, oldAdminState, adminState, !oldAdminStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetAdminState() {
		AdminType oldAdminState = adminState;
		boolean oldAdminStateESet = adminStateESet;
		adminState = ADMIN_STATE_EDEFAULT;
		adminStateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE__ADMIN_STATE, oldAdminState, ADMIN_STATE_EDEFAULT, oldAdminStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetAdminState() {
		return adminStateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		boolean oldLabelESet = labelESet;
		labelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__LABEL, oldLabel, label, !oldLabelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetLabel() {
		String oldLabel = label;
		boolean oldLabelESet = labelESet;
		label = LABEL_EDEFAULT;
		labelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE__LABEL, oldLabel, LABEL_EDEFAULT, oldLabelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetLabel() {
		return labelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationalType getOperationalState() {
		return operationalState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOperationalState(OperationalType newOperationalState) {
		OperationalType oldOperationalState = operationalState;
		operationalState = newOperationalState;
		boolean oldOperationalStateESet = operationalStateESet;
		operationalStateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__OPERATIONAL_STATE, oldOperationalState, operationalState,
				!oldOperationalStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetOperationalState() {
		OperationalType oldOperationalState = operationalState;
		boolean oldOperationalStateESet = operationalStateESet;
		operationalState = OPERATIONAL_STATE_EDEFAULT;
		operationalStateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE__OPERATIONAL_STATE, oldOperationalState, OPERATIONAL_STATE_EDEFAULT,
				oldOperationalStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetOperationalState() {
		return operationalStateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UsageType getUsageState() {
		return usageState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUsageState(UsageType newUsageState) {
		UsageType oldUsageState = usageState;
		usageState = newUsageState;
		boolean oldUsageStateESet = usageStateESet;
		usageStateESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__USAGE_STATE, oldUsageState, usageState, !oldUsageStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetUsageState() {
		UsageType oldUsageState = usageState;
		boolean oldUsageStateESet = usageStateESet;
		usageState = USAGE_STATE_EDEFAULT;
		usageStateESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE__USAGE_STATE, oldUsageState, USAGE_STATE_EDEFAULT, oldUsageStateESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetUsageState() {
		return usageStateESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDevice< ? > getParentDevice() {
		if (parentDevice != null && parentDevice.eIsProxy()) {
			InternalEObject oldParentDevice = (InternalEObject) parentDevice;
			parentDevice = (ScaDevice< ? >) eResolveProxy(oldParentDevice);
			if (parentDevice != oldParentDevice) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_DEVICE__PARENT_DEVICE, oldParentDevice, parentDevice));
			}
		}
		return parentDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDevice< ? > basicGetParentDevice() {
		return parentDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentDevice(ScaDevice< ? > newParentDevice, NotificationChain msgs) {
		ScaDevice< ? > oldParentDevice = parentDevice;
		parentDevice = newParentDevice;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__PARENT_DEVICE, oldParentDevice,
				newParentDevice);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentDevice(ScaDevice< ? > newParentDevice) {
		if (newParentDevice != parentDevice) {
			NotificationChain msgs = null;
			if (parentDevice != null)
				msgs = ((InternalEObject) parentDevice).eInverseRemove(this, ScaPackage.SCA_DEVICE__CHILD_DEVICES, ScaDevice.class, msgs);
			if (newParentDevice != null)
				msgs = ((InternalEObject) newParentDevice).eInverseAdd(this, ScaPackage.SCA_DEVICE__CHILD_DEVICES, ScaDevice.class, msgs);
			msgs = basicSetParentDevice(newParentDevice, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__PARENT_DEVICE, newParentDevice, newParentDevice));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDeviceManager getDevMgr() {
		ScaDeviceManager devMgr = basicGetDevMgr();
		return devMgr != null && devMgr.eIsProxy() ? (ScaDeviceManager) eResolveProxy((InternalEObject) devMgr) : devMgr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 6); //SUPPRESS CHECKSTYLE MagicNumber
		super.fetchAttributes(subMonitor.newChild(1));
		fetchAdminState(subMonitor.newChild(1));
		fetchLabel(subMonitor.newChild(1));
		fetchOperationalState(subMonitor.newChild(1));
		fetchUsageState(subMonitor.newChild(1));
		fetchProperties(subMonitor.newChild(1));
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		super.internalFetchChildren(subMonitor.newChild(1));
		internalFetchAggregateDevices(subMonitor.newChild(1));
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		if (msg.isTouch()) {
			return;
		}
		switch (msg.getFeatureID(ScaDevice.class)) {
		case ScaPackage.SCA_DEVICE__OBJ:
			unsetAdminState();
			unsetIdentifier();
			unsetLabel();
			unsetOperationalState();
			unsetStarted();
			unsetUsageState();
			unsetProfile();
			unsetChildDevices();
			break;
		case ScaPackage.SCA_DEVICE__PROFILE:
			if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
				unsetProfileURI();
			}
			break;
		default:
			break;
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaDevice< ? >> fetchAggregateDevices(IProgressMonitor monitor) {
		return fetchAggregateDevices(monitor, RefreshDepth.SELF);
	}

	private final VersionedFeature adminStateFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE__ADMIN_STATE);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public AdminType fetchAdminState(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching admin state", 3);
		D localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = adminStateFeature.createTransaction();
		if (localObj != null) {
			try {
				AdminType newValue = localObj.adminState();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE__ADMIN_STATE));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch admin state", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE__ADMIN_STATE));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE__ADMIN_STATE));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		return getAdminState();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature labelFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE__LABEL);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String fetchLabel(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetLabel()) {
			return getLabel();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching label", 3);
		D localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = labelFeature.createTransaction();
		if (localObj != null) {
			try {
				String newLabel = localObj.label();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newLabel, ScaPackage.Literals.SCA_DEVICE__LABEL));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch label", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE__LABEL));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE__LABEL));
		}
		transaction.commit();
		subMonitor.done();
		return getLabel();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature opStateFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE__OPERATIONAL_STATE);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public OperationalType fetchOperationalState(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching operational state", 3);
		D localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = opStateFeature.createTransaction();
		if (localObj != null) {
			try {
				OperationalType newValue = localObj.operationalState();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE__OPERATIONAL_STATE));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch operational state", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE__OPERATIONAL_STATE));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE__OPERATIONAL_STATE));
		}
		transaction.commit();
		subMonitor.done();
		return getOperationalState();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature usageStateFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE__USAGE_STATE);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public UsageType fetchUsageState(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching usage state", 3);
		D localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = usageStateFeature.createTransaction();
		if (localObj != null) {
			try {
				UsageType newValue = localObj.usageState();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE__USAGE_STATE));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch usage state", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE__USAGE_STATE));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE__USAGE_STATE));
		}
		transaction.commit();
		subMonitor.done();
		return getUsageState();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature aggregateDevicesRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE__CHILD_DEVICES);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void internalFetchAggregateDevices(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return;
		}
		if (isSetChildDevices()) {
			if (monitor != null) {
				monitor.done();
			}
			return;
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, 2); //SUPPRESS CHECKSTYLE MagicNumber
		final org.omg.CORBA.Object localObj = getCorbaObj();
		Transaction transaction = aggregateDevicesRevision.createTransaction();
		if (localObj != null) {
			AggregateDevice aggDev = null;
			IStatus tmpStatus = Status.OK_STATUS;

			try {
				if (_is_a(AggregateDeviceHelper.id())) {
					aggDev = AggregateDeviceHelper.unchecked_narrow(getCorbaObj());
				}
			} catch (SystemException e) {
				tmpStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to narrow to aggregate device.", e);
			}
			subMonitor.worked(1);

			// Setup new Devices
			final Set<String> deviceIds = new HashSet<String>();
			if (aggDev != null) {
				try {
					Device[] devices = aggDev.devices();
					for (Device d : devices) {
						deviceIds.add(d.identifier());
					}
				} catch (SystemException e) {
					tmpStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch child devices.", e);
				}
			}
			subMonitor.worked(1);

			final IStatus fetchStatus = tmpStatus;
			transaction.append(new MergeAggregateDevices(this, deviceIds, fetchStatus));
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE__CHILD_DEVICES));
		}
		transaction.commit();
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaDeviceManager basicGetDevMgr() {
		// END GENERATED CODE
		if (eContainer() instanceof ScaDeviceManager) {
			return (ScaDeviceManager) eContainer();
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public AdminType adminState() {
		// END GENERATED CODE
		return getAdminState();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public void adminState(final AdminType newAdminState) {
		// END GENERATED CODE
		D device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		device.adminState(newAdminState);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public boolean allocateCapacity(final DataType[] capacities) throws InvalidCapacity, InvalidState, InsufficientCapacity {
		// END GENERATED CODE
		D device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return device.allocateCapacity(capacities);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public AggregateDevice compositeDevice() {
		// END GENERATED CODE
		D device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return device.compositeDevice();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public void deallocateCapacity(final DataType[] capacities) throws InvalidCapacity, InvalidState {
		// END GENERATED CODE
		D device = fetchNarrowedObject(null);
		if (device == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		device.deallocateCapacity(capacities);
		// BEGIN GENERATED CODE
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
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getChildDevices()).basicAdd(otherEnd, msgs);
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			if (parentDevice != null)
				msgs = ((InternalEObject) parentDevice).eInverseRemove(this, ScaPackage.SCA_DEVICE__CHILD_DEVICES, ScaDevice.class, msgs);
			return basicSetParentDevice((ScaDevice< ? >) otherEnd, msgs);
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
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			return ((InternalEList< ? >) getChildDevices()).basicRemove(otherEnd, msgs);
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			return basicSetParentDevice(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			return getChildDevices();
		case ScaPackage.SCA_DEVICE__ADMIN_STATE:
			return getAdminState();
		case ScaPackage.SCA_DEVICE__LABEL:
			return getLabel();
		case ScaPackage.SCA_DEVICE__OPERATIONAL_STATE:
			return getOperationalState();
		case ScaPackage.SCA_DEVICE__USAGE_STATE:
			return getUsageState();
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			if (resolve)
				return getParentDevice();
			return basicGetParentDevice();
		case ScaPackage.SCA_DEVICE__DEV_MGR:
			if (resolve)
				return getDevMgr();
			return basicGetDevMgr();
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
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			getChildDevices().clear();
			getChildDevices().addAll((Collection< ? extends ScaDevice< ? >>) newValue);
			return;
		case ScaPackage.SCA_DEVICE__ADMIN_STATE:
			setAdminState((AdminType) newValue);
			return;
		case ScaPackage.SCA_DEVICE__LABEL:
			setLabel((String) newValue);
			return;
		case ScaPackage.SCA_DEVICE__OPERATIONAL_STATE:
			setOperationalState((OperationalType) newValue);
			return;
		case ScaPackage.SCA_DEVICE__USAGE_STATE:
			setUsageState((UsageType) newValue);
			return;
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			setParentDevice((ScaDevice< ? >) newValue);
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
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			unsetChildDevices();
			return;
		case ScaPackage.SCA_DEVICE__ADMIN_STATE:
			unsetAdminState();
			return;
		case ScaPackage.SCA_DEVICE__LABEL:
			unsetLabel();
			return;
		case ScaPackage.SCA_DEVICE__OPERATIONAL_STATE:
			unsetOperationalState();
			return;
		case ScaPackage.SCA_DEVICE__USAGE_STATE:
			unsetUsageState();
			return;
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			setParentDevice((ScaDevice< ? >) null);
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
		case ScaPackage.SCA_DEVICE__CHILD_DEVICES:
			return isSetChildDevices();
		case ScaPackage.SCA_DEVICE__ADMIN_STATE:
			return isSetAdminState();
		case ScaPackage.SCA_DEVICE__LABEL:
			return isSetLabel();
		case ScaPackage.SCA_DEVICE__OPERATIONAL_STATE:
			return isSetOperationalState();
		case ScaPackage.SCA_DEVICE__USAGE_STATE:
			return isSetUsageState();
		case ScaPackage.SCA_DEVICE__PARENT_DEVICE:
			return parentDevice != null;
		case ScaPackage.SCA_DEVICE__DEV_MGR:
			return basicGetDevMgr() != null;
		}
		return super.eIsSet(featureID);
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
		result.append(" (adminState: ");
		if (adminStateESet)
			result.append(adminState);
		else
			result.append("<unset>");
		result.append(", label: ");
		if (labelESet)
			result.append(label);
		else
			result.append("<unset>");
		result.append(", operationalState: ");
		if (operationalStateESet)
			result.append(operationalState);
		else
			result.append("<unset>");
		result.append(", usageState: ");
		if (usageStateESet)
			result.append(usageState);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	@Override
	public String label() {
		// END GENERATED CODE
		return getLabel();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public OperationalType operationalState() {
		// END GENERATED CODE
		return getOperationalState();
		// BEGIN GENERATED CODE
	}

	@Override
	public String softwareProfile() {
		// END GENERATED CODE
		return getProfile();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public UsageType usageState() {
		// END GENERATED CODE
		return getUsageState();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected D narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return (D) DeviceHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@Override
	public NotificationChain eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID, NotificationChain msgs) {
		InternalEObject oldContainer = eInternalContainer();
		msgs = super.eBasicSetContainer(newContainer, newContainerFeatureID, msgs);
		ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE__DEV_MGR, oldContainer, eInternalContainer());
		if (msgs == null) {
			msgs = notification;
		} else {
			msgs.add(notification);
		}
		return msgs;

	}

	@SuppressWarnings("unchecked")
	@Override
	protected Class< ? extends D> getCorbaType() {
		return (Class< ? extends D>) Device.class;
	}

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileURI()) {
			return getProfileURI();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch profile URI.", 2);
		ScaDeviceManager devMgr = getDevMgr();
		if (devMgr != null) {
			ScaDeviceManagerFileSystem fileSystem = devMgr.fetchFileSystem(subMonitor.newChild(1));
			if (fileSystem != null) {
				Transaction transaction = profileURIFeature.createTransaction();
				final URI newURI = fileSystem.createURI(fetchProfile(subMonitor.newChild(1)));
				transaction.addCommand(new ScaModelCommand() {

					@Override
					public void execute() {
						setProfileURI(newURI);
					}
				});
				transaction.commit();
			}
		}
		subMonitor.done();
		return getProfileURI();
	}

	/**
	 * @since 19.0
	 */
	@Override
	public EList<ScaDevice< ? >> fetchAggregateDevices(IProgressMonitor monitor, RefreshDepth depth) {
		if (isDisposed()) {
			return ECollections.emptyEList();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Aggregate Devices", 2);
		internalFetchAggregateDevices(subMonitor.newChild(1));
		ScaDevice< ? >[] devices = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaDevice< ? >[]>() {

			@Override
			public void execute() {
				setResult(getChildDevices().toArray(new ScaDevice< ? >[getChildDevices().size()]));
			}
		});
		if (devices != null && depth != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Refreshing devices", devices.length);
			for (ScaDevice< ? > device : devices) {
				try {
					device.refresh(deviceMonitor.newChild(1), depth);
				} catch (InterruptedException e) {
					// PASS
				}
			}
		}
		subMonitor.done();
		if (devices != null) {
			return ECollections.unmodifiableEList(new BasicEList<ScaDevice< ? >>(Arrays.asList(devices)));
		} else {
			return ECollections.emptyEList();
		}
	}

} // ScaDeviceImpl
