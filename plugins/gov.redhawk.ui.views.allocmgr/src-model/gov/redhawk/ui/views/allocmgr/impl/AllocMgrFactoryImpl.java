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

import gov.redhawk.ui.views.allocmgr.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AllocMgrFactoryImpl extends EFactoryImpl implements AllocMgrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AllocMgrFactory init() {
		try {
			AllocMgrFactory theAllocMgrFactory = (AllocMgrFactory) EPackage.Registry.INSTANCE.getEFactory(AllocMgrPackage.eNS_URI);
			if (theAllocMgrFactory != null) {
				return theAllocMgrFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AllocMgrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocMgrFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER:
			return createScaAllocationManager();
		case AllocMgrPackage.ALLOCATION_STATUS:
			return createAllocationStatus();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaAllocationManager createScaAllocationManager() {
		ScaAllocationManagerImpl scaAllocationManager = new ScaAllocationManagerImpl();
		return scaAllocationManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocationStatus createAllocationStatus() {
		AllocationStatusImpl allocationStatus = new AllocationStatusImpl();
		return allocationStatus;
	}

	// END GENERATED CODE

	public AllocationStatus createAllocationStatus(CF.AllocationManagerPackage.AllocationStatusType cfStatus) {
		AllocationStatus allocationStatus = createAllocationStatus();
		allocationStatus.setAllocationID(cfStatus.allocationID);
		allocationStatus.setRequestingDomain(cfStatus.requestingDomain);
		allocationStatus.setAllocationProps(cfStatus.allocationProperties);
		allocationStatus.setDeviceIOR(cfStatus.allocatedDevice.toString());
		allocationStatus.setDeviceMgrIOR(cfStatus.allocationDeviceManager.toString());
		allocationStatus.setSourceID(cfStatus.sourceID);
		return allocationStatus;
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocMgrPackage getAllocMgrPackage() {
		return (AllocMgrPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AllocMgrPackage getPackage() {
		return AllocMgrPackage.eINSTANCE;
	}

} // AllocMgrFactoryImpl
