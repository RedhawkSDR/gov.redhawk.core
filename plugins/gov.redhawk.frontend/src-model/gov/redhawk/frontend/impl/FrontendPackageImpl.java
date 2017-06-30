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

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
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
public class FrontendPackageImpl extends EPackageImpl implements FrontendPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tunerContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unallocatedTunerContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tunerStatusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listenerAllocationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType scaStructPropertyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType scaSimplePropertyEDataType = null;

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
	 * @see gov.redhawk.frontend.FrontendPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private FrontendPackageImpl() {
		super(eNS_URI, FrontendFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link FrontendPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static FrontendPackage init() {
		if (isInited)
			return (FrontendPackage) EPackage.Registry.INSTANCE.getEPackage(FrontendPackage.eNS_URI);

		// Obtain or create and register package
		FrontendPackageImpl theFrontendPackage = (FrontendPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof FrontendPackageImpl
			? EPackage.Registry.INSTANCE.get(eNS_URI)
			: new FrontendPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theFrontendPackage.createPackageContents();

		// Initialize created meta-data
		theFrontendPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theFrontendPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(FrontendPackage.eNS_URI, theFrontendPackage);
		return theFrontendPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTunerContainer() {
		return tunerContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTunerContainer_TunerStatus() {
		return (EReference) tunerContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTunerContainer_UnallocatedContainer() {
		return (EReference) tunerContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnallocatedTunerContainer() {
		return unallocatedTunerContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnallocatedTunerContainer_TunerContainer() {
		return (EReference) unallocatedTunerContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnallocatedTunerContainer_TunerType() {
		return (EAttribute) unallocatedTunerContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnallocatedTunerContainer_Count() {
		return (EAttribute) unallocatedTunerContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTunerStatus() {
		return tunerStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTunerStatus_TunerContainer() {
		return (EReference) tunerStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_TunerStatusStruct() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Simples() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Allocated() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_TunerID() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_TunerType() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_AllocationID() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_CenterFrequency() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Bandwidth() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_SampleRate() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_GroupID() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_RfFlowID() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Enabled() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Gain() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_Agc() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_ReferenceSource() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTunerStatus_DeviceControl() {
		return (EAttribute) tunerStatusEClass.getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTunerStatus_ListenerAllocations() {
		return (EReference) tunerStatusEClass.getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getTunerStatus__GetSimple__String() {
		return tunerStatusEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getListenerAllocation() {
		return listenerAllocationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getListenerAllocation_TunerStatus() {
		return (EReference) listenerAllocationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getListenerAllocation_ListenerID() {
		return (EAttribute) listenerAllocationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getScaStructProperty() {
		return scaStructPropertyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getScaSimpleProperty() {
		return scaSimplePropertyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrontendFactory getFrontendFactory() {
		return (FrontendFactory) getEFactoryInstance();
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
		tunerContainerEClass = createEClass(TUNER_CONTAINER);
		createEReference(tunerContainerEClass, TUNER_CONTAINER__TUNER_STATUS);
		createEReference(tunerContainerEClass, TUNER_CONTAINER__UNALLOCATED_CONTAINER);

		unallocatedTunerContainerEClass = createEClass(UNALLOCATED_TUNER_CONTAINER);
		createEReference(unallocatedTunerContainerEClass, UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER);
		createEAttribute(unallocatedTunerContainerEClass, UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE);
		createEAttribute(unallocatedTunerContainerEClass, UNALLOCATED_TUNER_CONTAINER__COUNT);

		tunerStatusEClass = createEClass(TUNER_STATUS);
		createEReference(tunerStatusEClass, TUNER_STATUS__TUNER_CONTAINER);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__TUNER_STATUS_STRUCT);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__SIMPLES);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__ALLOCATED);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__TUNER_ID);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__TUNER_TYPE);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__ALLOCATION_ID);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__CENTER_FREQUENCY);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__BANDWIDTH);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__SAMPLE_RATE);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__GROUP_ID);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__RF_FLOW_ID);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__ENABLED);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__GAIN);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__AGC);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__REFERENCE_SOURCE);
		createEAttribute(tunerStatusEClass, TUNER_STATUS__DEVICE_CONTROL);
		createEReference(tunerStatusEClass, TUNER_STATUS__LISTENER_ALLOCATIONS);
		createEOperation(tunerStatusEClass, TUNER_STATUS___GET_SIMPLE__STRING);

		listenerAllocationEClass = createEClass(LISTENER_ALLOCATION);
		createEReference(listenerAllocationEClass, LISTENER_ALLOCATION__TUNER_STATUS);
		createEAttribute(listenerAllocationEClass, LISTENER_ALLOCATION__LISTENER_ID);

		// Create data types
		scaStructPropertyEDataType = createEDataType(SCA_STRUCT_PROPERTY);
		scaSimplePropertyEDataType = createEDataType(SCA_SIMPLE_PROPERTY);
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
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(tunerContainerEClass, TunerContainer.class, "TunerContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTunerContainer_TunerStatus(), this.getTunerStatus(), this.getTunerStatus_TunerContainer(), "tunerStatus", null, 0, -1,
			TunerContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getTunerContainer_UnallocatedContainer(), this.getUnallocatedTunerContainer(), this.getUnallocatedTunerContainer_TunerContainer(),
			"unallocatedContainer", null, 0, -1, TunerContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
			!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unallocatedTunerContainerEClass, UnallocatedTunerContainer.class, "UnallocatedTunerContainer", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnallocatedTunerContainer_TunerContainer(), this.getTunerContainer(), this.getTunerContainer_UnallocatedContainer(), "tunerContainer",
			null, 0, 1, UnallocatedTunerContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnallocatedTunerContainer_TunerType(), theEcorePackage.getEString(), "tunerType", null, 0, 1, UnallocatedTunerContainer.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnallocatedTunerContainer_Count(), theEcorePackage.getEInt(), "count", null, 0, 1, UnallocatedTunerContainer.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tunerStatusEClass, TunerStatus.class, "TunerStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTunerStatus_TunerContainer(), this.getTunerContainer(), this.getTunerContainer_TunerStatus(), "tunerContainer", null, 0, 1,
			TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getTunerStatus_TunerStatusStruct(), this.getScaStructProperty(), "tunerStatusStruct", null, 0, 1, TunerStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Simples(), this.getScaSimpleProperty(), "simples", null, 0, -1, TunerStatus.class, IS_TRANSIENT, IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Allocated(), theEcorePackage.getEBoolean(), "allocated", null, 0, 1, TunerStatus.class, IS_TRANSIENT, IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_TunerID(), theEcorePackage.getEString(), "tunerID", null, 0, 1, TunerStatus.class, IS_TRANSIENT, IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_TunerType(), theEcorePackage.getEString(), "tunerType", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_AllocationID(), theEcorePackage.getEString(), "allocationID", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_CenterFrequency(), theEcorePackage.getEDouble(), "centerFrequency", null, 0, 1, TunerStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Bandwidth(), theEcorePackage.getEDouble(), "bandwidth", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_SampleRate(), theEcorePackage.getEDouble(), "sampleRate", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_GroupID(), theEcorePackage.getEString(), "groupID", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_RfFlowID(), theEcorePackage.getEString(), "rfFlowID", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Enabled(), theEcorePackage.getEBoolean(), "enabled", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Gain(), theEcorePackage.getEDouble(), "gain", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_Agc(), theEcorePackage.getEBoolean(), "agc", null, 0, 1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_ReferenceSource(), theEcorePackage.getEInt(), "referenceSource", null, 0, 1, TunerStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTunerStatus_DeviceControl(), theEcorePackage.getEBoolean(), "deviceControl", null, 0, 1, TunerStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTunerStatus_ListenerAllocations(), this.getListenerAllocation(), this.getListenerAllocation_TunerStatus(), "listenerAllocations",
			null, 0, -1, TunerStatus.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getTunerStatus__GetSimple__String(), this.getScaSimpleProperty(), "getSimple", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "propID", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(listenerAllocationEClass, ListenerAllocation.class, "ListenerAllocation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getListenerAllocation_TunerStatus(), this.getTunerStatus(), this.getTunerStatus_ListenerAllocations(), "tunerStatus", null, 0, 1,
			ListenerAllocation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getListenerAllocation_ListenerID(), theEcorePackage.getEString(), "listenerID", null, 0, 1, ListenerAllocation.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(scaStructPropertyEDataType, ScaStructProperty.class, "ScaStructProperty", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(scaSimplePropertyEDataType, ScaSimpleProperty.class, "ScaSimpleProperty", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // FrontendPackageImpl
