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

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.ui.views.allocmgr.AllocMgrFactory;
import gov.redhawk.ui.views.allocmgr.AllocMgrPackage;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;

import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;
import mil.jpeojtrs.sca.cf.CfPackage;

import mil.jpeojtrs.sca.cf.extended.ExtendedPackage;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dmd.DmdPackage;
import mil.jpeojtrs.sca.dpd.DpdPackage;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SpdPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AllocMgrPackageImpl extends EPackageImpl implements AllocMgrPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaAllocationManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass allocationStatusEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AllocMgrPackageImpl() {
		super(eNS_URI, AllocMgrFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AllocMgrPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AllocMgrPackage init() {
		if (isInited)
			return (AllocMgrPackage) EPackage.Registry.INSTANCE.getEPackage(AllocMgrPackage.eNS_URI);

		// Obtain or create and register package
		AllocMgrPackageImpl theAllocMgrPackage = (AllocMgrPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AllocMgrPackageImpl
			? EPackage.Registry.INSTANCE.get(eNS_URI)
			: new AllocMgrPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ScaPackage.eINSTANCE.eClass();
		CfPackage.eINSTANCE.eClass();
		PrfPackage.eINSTANCE.eClass();
		SpdPackage.eINSTANCE.eClass();
		SadPackage.eINSTANCE.eClass();
		DcdPackage.eINSTANCE.eClass();
		ScdPackage.eINSTANCE.eClass();
		DmdPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		ExtendedPackage.eINSTANCE.eClass();
		PartitioningPackage.eINSTANCE.eClass();
		DpdPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theAllocMgrPackage.createPackageContents();

		// Initialize created meta-data
		theAllocMgrPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAllocMgrPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AllocMgrPackage.eNS_URI, theAllocMgrPackage);
		return theAllocMgrPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScaAllocationManager() {
		return scaAllocationManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScaAllocationManager_Allocations() {
		return (EReference) scaAllocationManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAllocationStatus() {
		return allocationStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_AllocationID() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_RequestingDomain() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_AllocationProps() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_DeviceIOR() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_DeviceLabel() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_DeviceMgrIOR() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_DeviceMgrLabel() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAllocationStatus_SourceID() {
		return (EAttribute) allocationStatusEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocMgrFactory getAllocMgrFactory() {
		return (AllocMgrFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		scaAllocationManagerEClass = createEClass(SCA_ALLOCATION_MANAGER);
		createEReference(scaAllocationManagerEClass, SCA_ALLOCATION_MANAGER__ALLOCATIONS);

		allocationStatusEClass = createEClass(ALLOCATION_STATUS);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__ALLOCATION_ID);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__REQUESTING_DOMAIN);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__ALLOCATION_PROPS);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__DEVICE_IOR);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__DEVICE_LABEL);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__DEVICE_MGR_IOR);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__DEVICE_MGR_LABEL);
		createEAttribute(allocationStatusEClass, ALLOCATION_STATUS__SOURCE_ID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ScaPackage theScaPackage = (ScaPackage) EPackage.Registry.INSTANCE.getEPackage(ScaPackage.eNS_URI);
		CfPackage theCfPackage = (CfPackage) EPackage.Registry.INSTANCE.getEPackage(CfPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theScaPackage.getCorbaObjWrapper());
		EGenericType g2 = createEGenericType(theCfPackage.getAllocationManager());
		g1.getETypeArguments().add(g2);
		scaAllocationManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getAllocationManagerOperations());
		scaAllocationManagerEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(scaAllocationManagerEClass, ScaAllocationManager.class, "ScaAllocationManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaAllocationManager_Allocations(), this.getAllocationStatus(), null, "allocations", null, 0, -1, ScaAllocationManager.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(allocationStatusEClass, AllocationStatus.class, "AllocationStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAllocationStatus_AllocationID(), ecorePackage.getEString(), "allocationID", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_RequestingDomain(), ecorePackage.getEString(), "requestingDomain", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_AllocationProps(), theCfPackage.getDataTypeArray(), "allocationProps", null, 0, 1, AllocationStatus.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_DeviceIOR(), ecorePackage.getEString(), "deviceIOR", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_DeviceLabel(), ecorePackage.getEString(), "deviceLabel", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_DeviceMgrIOR(), ecorePackage.getEString(), "deviceMgrIOR", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_DeviceMgrLabel(), ecorePackage.getEString(), "deviceMgrLabel", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAllocationStatus_SourceID(), ecorePackage.getEString(), "sourceID", null, 0, 1, AllocationStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // AllocMgrPackageImpl
