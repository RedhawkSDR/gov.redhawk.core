/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
// BEGIN GENERATED CODE
package gov.redhawk.ui.views.allocmgr.impl;

import CF.DataType;

import gov.redhawk.ui.views.allocmgr.AllocMgrPackage;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Allocation Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getAllocationID <em>Allocation ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getRequestingDomain <em>Requesting
 * Domain</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getAllocationProps <em>Allocation Props</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getDeviceIOR <em>Device IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getDeviceLabel <em>Device Label</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getDeviceMgrIOR <em>Device Mgr IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getDeviceMgrLabel <em>Device Mgr Label</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl#getSourceID <em>Source ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AllocationStatusImpl extends MinimalEObjectImpl.Container implements AllocationStatus {
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
	 * The default value of the '{@link #getRequestingDomain() <em>Requesting Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestingDomain()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUESTING_DOMAIN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequestingDomain() <em>Requesting Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequestingDomain()
	 * @generated
	 * @ordered
	 */
	protected String requestingDomain = REQUESTING_DOMAIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getAllocationProps() <em>Allocation Props</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocationProps()
	 * @generated
	 * @ordered
	 */
	protected static final DataType[] ALLOCATION_PROPS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAllocationProps() <em>Allocation Props</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocationProps()
	 * @generated
	 * @ordered
	 */
	protected DataType[] allocationProps = ALLOCATION_PROPS_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeviceIOR() <em>Device IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceIOR()
	 * @generated
	 * @ordered
	 */
	protected static final String DEVICE_IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeviceIOR() <em>Device IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceIOR()
	 * @generated
	 * @ordered
	 */
	protected String deviceIOR = DEVICE_IOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeviceLabel() <em>Device Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String DEVICE_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeviceLabel() <em>Device Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceLabel()
	 * @generated
	 * @ordered
	 */
	protected String deviceLabel = DEVICE_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeviceMgrIOR() <em>Device Mgr IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceMgrIOR()
	 * @generated
	 * @ordered
	 */
	protected static final String DEVICE_MGR_IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeviceMgrIOR() <em>Device Mgr IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceMgrIOR()
	 * @generated
	 * @ordered
	 */
	protected String deviceMgrIOR = DEVICE_MGR_IOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getDeviceMgrLabel() <em>Device Mgr Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceMgrLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String DEVICE_MGR_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDeviceMgrLabel() <em>Device Mgr Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceMgrLabel()
	 * @generated
	 * @ordered
	 */
	protected String deviceMgrLabel = DEVICE_MGR_LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceID() <em>Source ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceID()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceID() <em>Source ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceID()
	 * @generated
	 * @ordered
	 */
	protected String sourceID = SOURCE_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AllocationStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AllocMgrPackage.Literals.ALLOCATION_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAllocationID() {
		return allocationID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllocationID(String newAllocationID) {
		String oldAllocationID = allocationID;
		allocationID = newAllocationID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID, oldAllocationID, allocationID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequestingDomain() {
		return requestingDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequestingDomain(String newRequestingDomain) {
		String oldRequestingDomain = requestingDomain;
		requestingDomain = newRequestingDomain;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN, oldRequestingDomain, requestingDomain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataType[] getAllocationProps() {
		return allocationProps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAllocationProps(DataType[] newAllocationProps) {
		DataType[] oldAllocationProps = allocationProps;
		allocationProps = newAllocationProps;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS, oldAllocationProps, allocationProps));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeviceIOR() {
		return deviceIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDeviceIOR(String newDeviceIOR) {
		String oldDeviceIOR = deviceIOR;
		deviceIOR = newDeviceIOR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR, oldDeviceIOR, deviceIOR));
		setDeviceLabel(DEVICE_LABEL_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeviceLabel() {
		return deviceLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeviceLabel(String newDeviceLabel) {
		String oldDeviceLabel = deviceLabel;
		deviceLabel = newDeviceLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL, oldDeviceLabel, deviceLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeviceMgrIOR() {
		return deviceMgrIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDeviceMgrIOR(String newDeviceMgrIOR) {
		String oldDeviceMgrIOR = deviceMgrIOR;
		deviceMgrIOR = newDeviceMgrIOR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR, oldDeviceMgrIOR, deviceMgrIOR));
		setDeviceMgrLabel(DEVICE_MGR_LABEL_EDEFAULT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDeviceMgrLabel() {
		return deviceMgrLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeviceMgrLabel(String newDeviceMgrLabel) {
		String oldDeviceMgrLabel = deviceMgrLabel;
		deviceMgrLabel = newDeviceMgrLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL, oldDeviceMgrLabel, deviceMgrLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceID() {
		return sourceID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceID(String newSourceID) {
		String oldSourceID = sourceID;
		sourceID = newSourceID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID, oldSourceID, sourceID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
			return getAllocationID();
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
			return getRequestingDomain();
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
			return getAllocationProps();
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
			return getDeviceIOR();
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
			return getDeviceLabel();
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
			return getDeviceMgrIOR();
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
			return getDeviceMgrLabel();
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			return getSourceID();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
			setAllocationID((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
			setRequestingDomain((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
			setAllocationProps((DataType[]) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
			setDeviceIOR((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
			setDeviceLabel((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
			setDeviceMgrIOR((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
			setDeviceMgrLabel((String) newValue);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			setSourceID((String) newValue);
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
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
			setAllocationID(ALLOCATION_ID_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
			setRequestingDomain(REQUESTING_DOMAIN_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
			setAllocationProps(ALLOCATION_PROPS_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
			setDeviceIOR(DEVICE_IOR_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
			setDeviceLabel(DEVICE_LABEL_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
			setDeviceMgrIOR(DEVICE_MGR_IOR_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
			setDeviceMgrLabel(DEVICE_MGR_LABEL_EDEFAULT);
			return;
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			setSourceID(SOURCE_ID_EDEFAULT);
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
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_ID:
			return ALLOCATION_ID_EDEFAULT == null ? allocationID != null : !ALLOCATION_ID_EDEFAULT.equals(allocationID);
		case AllocMgrPackage.ALLOCATION_STATUS__REQUESTING_DOMAIN:
			return REQUESTING_DOMAIN_EDEFAULT == null ? requestingDomain != null : !REQUESTING_DOMAIN_EDEFAULT.equals(requestingDomain);
		case AllocMgrPackage.ALLOCATION_STATUS__ALLOCATION_PROPS:
			return ALLOCATION_PROPS_EDEFAULT == null ? allocationProps != null : !ALLOCATION_PROPS_EDEFAULT.equals(allocationProps);
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_IOR:
			return DEVICE_IOR_EDEFAULT == null ? deviceIOR != null : !DEVICE_IOR_EDEFAULT.equals(deviceIOR);
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_LABEL:
			return DEVICE_LABEL_EDEFAULT == null ? deviceLabel != null : !DEVICE_LABEL_EDEFAULT.equals(deviceLabel);
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_IOR:
			return DEVICE_MGR_IOR_EDEFAULT == null ? deviceMgrIOR != null : !DEVICE_MGR_IOR_EDEFAULT.equals(deviceMgrIOR);
		case AllocMgrPackage.ALLOCATION_STATUS__DEVICE_MGR_LABEL:
			return DEVICE_MGR_LABEL_EDEFAULT == null ? deviceMgrLabel != null : !DEVICE_MGR_LABEL_EDEFAULT.equals(deviceMgrLabel);
		case AllocMgrPackage.ALLOCATION_STATUS__SOURCE_ID:
			return SOURCE_ID_EDEFAULT == null ? sourceID != null : !SOURCE_ID_EDEFAULT.equals(sourceID);
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
		result.append(" (allocationID: ");
		result.append(allocationID);
		result.append(", requestingDomain: ");
		result.append(requestingDomain);
		result.append(", allocationProps: ");
		result.append(allocationProps);
		result.append(", deviceIOR: ");
		result.append(deviceIOR);
		result.append(", deviceLabel: ");
		result.append(deviceLabel);
		result.append(", deviceMgrIOR: ");
		result.append(deviceMgrIOR);
		result.append(", deviceMgrLabel: ");
		result.append(deviceMgrLabel);
		result.append(", sourceID: ");
		result.append(sourceID);
		result.append(')');
		return result.toString();
	}

} // AllocationStatusImpl
