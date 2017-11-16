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
package gov.redhawk.ui.views.connmgr.impl;

import gov.redhawk.model.sca.ScaPackage;

import gov.redhawk.ui.views.connmgr.ConnMgrFactory;
import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;

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
public class ConnMgrPackageImpl extends EPackageImpl implements ConnMgrPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaConnectionManagerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionStatusEClass = null;

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
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ConnMgrPackageImpl() {
		super(eNS_URI, ConnMgrFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ConnMgrPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ConnMgrPackage init() {
		if (isInited)
			return (ConnMgrPackage) EPackage.Registry.INSTANCE.getEPackage(ConnMgrPackage.eNS_URI);

		// Obtain or create and register package
		ConnMgrPackageImpl theConnMgrPackage = (ConnMgrPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConnMgrPackageImpl
			? EPackage.Registry.INSTANCE.get(eNS_URI)
			: new ConnMgrPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CfPackage.eINSTANCE.eClass();
		ExtendedPackage.eINSTANCE.eClass();
		DcdPackage.eINSTANCE.eClass();
		DmdPackage.eINSTANCE.eClass();
		DpdPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		PartitioningPackage.eINSTANCE.eClass();
		PrfPackage.eINSTANCE.eClass();
		SadPackage.eINSTANCE.eClass();
		ScaPackage.eINSTANCE.eClass();
		ScdPackage.eINSTANCE.eClass();
		SpdPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theConnMgrPackage.createPackageContents();

		// Initialize created meta-data
		theConnMgrPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConnMgrPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConnMgrPackage.eNS_URI, theConnMgrPackage);
		return theConnMgrPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScaConnectionManager() {
		return scaConnectionManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScaConnectionManager_Connections() {
		return (EReference) scaConnectionManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectionStatus() {
		return connectionStatusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_ConnectionRecordID() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_ConnectionID() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_RequesterID() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_Connected() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_SourceIOR() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_SourceEntityName() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_SourcePortName() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_SourceRepoID() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_TargetIOR() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_TargetEntityName() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_TargetPortName() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConnectionStatus_TargetRepoID() {
		return (EAttribute) connectionStatusEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnMgrFactory getConnMgrFactory() {
		return (ConnMgrFactory) getEFactoryInstance();
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
		scaConnectionManagerEClass = createEClass(SCA_CONNECTION_MANAGER);
		createEReference(scaConnectionManagerEClass, SCA_CONNECTION_MANAGER__CONNECTIONS);

		connectionStatusEClass = createEClass(CONNECTION_STATUS);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__CONNECTION_RECORD_ID);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__CONNECTION_ID);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__REQUESTER_ID);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__CONNECTED);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__SOURCE_IOR);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__SOURCE_ENTITY_NAME);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__SOURCE_PORT_NAME);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__SOURCE_REPO_ID);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__TARGET_IOR);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__TARGET_ENTITY_NAME);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__TARGET_PORT_NAME);
		createEAttribute(connectionStatusEClass, CONNECTION_STATUS__TARGET_REPO_ID);
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
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theScaPackage.getCorbaObjWrapper());
		EGenericType g2 = createEGenericType(theCfPackage.getConnectionManager());
		g1.getETypeArguments().add(g2);
		scaConnectionManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getConnectionManagerOperations());
		scaConnectionManagerEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(scaConnectionManagerEClass, ScaConnectionManager.class, "ScaConnectionManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaConnectionManager_Connections(), this.getConnectionStatus(), null, "connections", null, 0, -1, ScaConnectionManager.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionStatusEClass, ConnectionStatus.class, "ConnectionStatus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectionStatus_ConnectionRecordID(), theEcorePackage.getEString(), "connectionRecordID", null, 0, 1, ConnectionStatus.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_ConnectionID(), theEcorePackage.getEString(), "connectionID", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_RequesterID(), theEcorePackage.getEString(), "requesterID", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_Connected(), theEcorePackage.getEBoolean(), "connected", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_SourceIOR(), theEcorePackage.getEString(), "sourceIOR", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_SourceEntityName(), theEcorePackage.getEString(), "sourceEntityName", null, 0, 1, ConnectionStatus.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_SourcePortName(), theEcorePackage.getEString(), "sourcePortName", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_SourceRepoID(), theEcorePackage.getEString(), "sourceRepoID", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_TargetIOR(), theEcorePackage.getEString(), "targetIOR", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_TargetEntityName(), theEcorePackage.getEString(), "targetEntityName", null, 0, 1, ConnectionStatus.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_TargetPortName(), theEcorePackage.getEString(), "targetPortName", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectionStatus_TargetRepoID(), theEcorePackage.getEString(), "targetRepoID", null, 0, 1, ConnectionStatus.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // ConnMgrPackageImpl
