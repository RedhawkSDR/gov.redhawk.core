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
package gov.redhawk.ui.views.allocmgr;

import CF.DataType;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Allocation Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationID <em>Allocation ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getRequestingDomain <em>Requesting Domain</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationProps <em>Allocation Props</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceIOR <em>Device IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceLabel <em>Device Label</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrIOR <em>Device Mgr IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrLabel <em>Device Mgr Label</em>}</li>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getSourceID <em>Source ID</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus()
 * @model
 * @generated
 */
public interface AllocationStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Allocation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocation ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocation ID</em>' attribute.
	 * @see #setAllocationID(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_AllocationID()
	 * @model
	 * @generated
	 */
	String getAllocationID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationID <em>Allocation
	 * ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allocation ID</em>' attribute.
	 * @see #getAllocationID()
	 * @generated
	 */
	void setAllocationID(String value);

	/**
	 * Returns the value of the '<em><b>Requesting Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requesting Domain</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requesting Domain</em>' attribute.
	 * @see #setRequestingDomain(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_RequestingDomain()
	 * @model
	 * @generated
	 */
	String getRequestingDomain();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getRequestingDomain <em>Requesting
	 * Domain</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requesting Domain</em>' attribute.
	 * @see #getRequestingDomain()
	 * @generated
	 */
	void setRequestingDomain(String value);

	/**
	 * Returns the value of the '<em><b>Allocation Props</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocation Props</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocation Props</em>' attribute.
	 * @see #setAllocationProps(DataType[])
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_AllocationProps()
	 * @model dataType="mil.jpeojtrs.sca.cf.DataTypeArray"
	 * @generated
	 */
	DataType[] getAllocationProps();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationProps <em>Allocation
	 * Props</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Allocation Props</em>' attribute.
	 * @see #getAllocationProps()
	 * @generated
	 */
	void setAllocationProps(DataType[] value);

	/**
	 * Returns the value of the '<em><b>Device IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device IOR</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device IOR</em>' attribute.
	 * @see #setDeviceIOR(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_DeviceIOR()
	 * @model
	 * @generated
	 */
	String getDeviceIOR();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceIOR <em>Device IOR</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device IOR</em>' attribute.
	 * @see #getDeviceIOR()
	 * @generated
	 */
	void setDeviceIOR(String value);

	/**
	 * Returns the value of the '<em><b>Device Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Label</em>' attribute.
	 * @see #setDeviceLabel(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_DeviceLabel()
	 * @model derived="true"
	 * @generated
	 */
	String getDeviceLabel();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceLabel <em>Device
	 * Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Label</em>' attribute.
	 * @see #getDeviceLabel()
	 * @generated
	 */
	void setDeviceLabel(String value);

	/**
	 * Returns the value of the '<em><b>Device Mgr IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Mgr IOR</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Mgr IOR</em>' attribute.
	 * @see #setDeviceMgrIOR(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_DeviceMgrIOR()
	 * @model
	 * @generated
	 */
	String getDeviceMgrIOR();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrIOR <em>Device Mgr
	 * IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Mgr IOR</em>' attribute.
	 * @see #getDeviceMgrIOR()
	 * @generated
	 */
	void setDeviceMgrIOR(String value);

	/**
	 * Returns the value of the '<em><b>Device Mgr Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Mgr Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Mgr Label</em>' attribute.
	 * @see #setDeviceMgrLabel(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_DeviceMgrLabel()
	 * @model derived="true"
	 * @generated
	 */
	String getDeviceMgrLabel();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrLabel <em>Device Mgr
	 * Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Mgr Label</em>' attribute.
	 * @see #getDeviceMgrLabel()
	 * @generated
	 */
	void setDeviceMgrLabel(String value);

	/**
	 * Returns the value of the '<em><b>Source ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source ID</em>' attribute.
	 * @see #setSourceID(String)
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationStatus_SourceID()
	 * @model
	 * @generated
	 */
	String getSourceID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getSourceID <em>Source ID</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source ID</em>' attribute.
	 * @see #getSourceID()
	 * @generated
	 */
	void setSourceID(String value);

} // AllocationStatus
