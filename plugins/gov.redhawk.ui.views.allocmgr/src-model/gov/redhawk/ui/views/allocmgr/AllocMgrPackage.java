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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.ui.views.allocmgr.AllocMgrFactory
 * @model kind="package"
 * @generated
 */
public interface AllocMgrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "allocmgr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://allocmgr.views.ui.redhawk.gov/allocmgr";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "allocmgr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AllocMgrPackage eINSTANCE = gov.redhawk.ui.views.allocmgr.impl.AllocMgrPackageImpl.init();

	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl <em>Allocation
	 * Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl
	 * @see gov.redhawk.ui.views.allocmgr.impl.AllocMgrPackageImpl#getAllocationStatus()
	 * @generated
	 */
	int ALLOCATION_STATUS = 0;

	/**
	 * The feature id for the '<em><b>Allocation ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__ALLOCATION_ID = 0;

	/**
	 * The feature id for the '<em><b>Requesting Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__REQUESTING_DOMAIN = 1;

	/**
	 * The feature id for the '<em><b>Allocation Props</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__ALLOCATION_PROPS = 2;

	/**
	 * The feature id for the '<em><b>Device IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__DEVICE_IOR = 3;

	/**
	 * The feature id for the '<em><b>Device Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__DEVICE_LABEL = 4;

	/**
	 * The feature id for the '<em><b>Device Mgr IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__DEVICE_MGR_IOR = 5;

	/**
	 * The feature id for the '<em><b>Device Mgr Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__DEVICE_MGR_LABEL = 6;

	/**
	 * The feature id for the '<em><b>Source ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS__SOURCE_ID = 7;

	/**
	 * The number of structural features of the '<em>Allocation Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS_FEATURE_COUNT = 8;

	/**
	 * The number of operations of the '<em>Allocation Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_STATUS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.allocmgr.impl.AllocationManagerImpl <em>Allocation
	 * Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.allocmgr.impl.AllocationManagerImpl
	 * @see gov.redhawk.ui.views.allocmgr.impl.AllocMgrPackageImpl#getAllocationManager()
	 * @generated
	 */
	int ALLOCATION_MANAGER = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_MANAGER__STATUS = 0;

	/**
	 * The number of structural features of the '<em>Allocation Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_MANAGER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Allocation Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALLOCATION_MANAGER_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus <em>Allocation
	 * Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Allocation Status</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus
	 * @generated
	 */
	EClass getAllocationStatus();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationID
	 * <em>Allocation ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation ID</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationID()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_AllocationID();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getRequestingDomain <em>Requesting Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requesting Domain</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getRequestingDomain()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_RequestingDomain();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationProps <em>Allocation Props</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Allocation Props</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getAllocationProps()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_AllocationProps();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceIOR
	 * <em>Device IOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device IOR</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceIOR()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_DeviceIOR();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceLabel
	 * <em>Device Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Label</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceLabel()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_DeviceLabel();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrIOR
	 * <em>Device Mgr IOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Mgr IOR</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrIOR()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_DeviceMgrIOR();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrLabel <em>Device Mgr Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Mgr Label</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getDeviceMgrLabel()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_DeviceMgrLabel();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.allocmgr.AllocationStatus#getSourceID
	 * <em>Source ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source ID</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationStatus#getSourceID()
	 * @see #getAllocationStatus()
	 * @generated
	 */
	EAttribute getAllocationStatus_SourceID();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.allocmgr.AllocationManager <em>Allocation
	 * Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Allocation Manager</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationManager
	 * @generated
	 */
	EClass getAllocationManager();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link gov.redhawk.ui.views.allocmgr.AllocationManager#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Status</em>'.
	 * @see gov.redhawk.ui.views.allocmgr.AllocationManager#getStatus()
	 * @see #getAllocationManager()
	 * @generated
	 */
	EReference getAllocationManager_Status();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AllocMgrFactory getAllocMgrFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl
		 * <em>Allocation Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.allocmgr.impl.AllocationStatusImpl
		 * @see gov.redhawk.ui.views.allocmgr.impl.AllocMgrPackageImpl#getAllocationStatus()
		 * @generated
		 */
		EClass ALLOCATION_STATUS = eINSTANCE.getAllocationStatus();

		/**
		 * The meta object literal for the '<em><b>Allocation ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__ALLOCATION_ID = eINSTANCE.getAllocationStatus_AllocationID();

		/**
		 * The meta object literal for the '<em><b>Requesting Domain</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__REQUESTING_DOMAIN = eINSTANCE.getAllocationStatus_RequestingDomain();

		/**
		 * The meta object literal for the '<em><b>Allocation Props</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__ALLOCATION_PROPS = eINSTANCE.getAllocationStatus_AllocationProps();

		/**
		 * The meta object literal for the '<em><b>Device IOR</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__DEVICE_IOR = eINSTANCE.getAllocationStatus_DeviceIOR();

		/**
		 * The meta object literal for the '<em><b>Device Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__DEVICE_LABEL = eINSTANCE.getAllocationStatus_DeviceLabel();

		/**
		 * The meta object literal for the '<em><b>Device Mgr IOR</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__DEVICE_MGR_IOR = eINSTANCE.getAllocationStatus_DeviceMgrIOR();

		/**
		 * The meta object literal for the '<em><b>Device Mgr Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__DEVICE_MGR_LABEL = eINSTANCE.getAllocationStatus_DeviceMgrLabel();

		/**
		 * The meta object literal for the '<em><b>Source ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ALLOCATION_STATUS__SOURCE_ID = eINSTANCE.getAllocationStatus_SourceID();

		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.allocmgr.impl.AllocationManagerImpl
		 * <em>Allocation Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.allocmgr.impl.AllocationManagerImpl
		 * @see gov.redhawk.ui.views.allocmgr.impl.AllocMgrPackageImpl#getAllocationManager()
		 * @generated
		 */
		EClass ALLOCATION_MANAGER = eINSTANCE.getAllocationManager();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ALLOCATION_MANAGER__STATUS = eINSTANCE.getAllocationManager_Status();

	}

} // AllocMgrPackage
