/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.ui.views.monitor.model.ports.impl;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.ui.views.monitor.model.ports.Monitor;
import gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry;
import gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortsFactory;
import gov.redhawk.ui.views.monitor.model.ports.PortsPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PortsPackageImpl extends EPackageImpl implements PortsPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portMonitorEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portConnectionMonitorEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass monitorRegistryEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portSupplierMonitorEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass monitorEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass portStatisticsProviderEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType portUsageTypeEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType portStatisticsEDataType = null;

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
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PortsPackageImpl() {
		super(eNS_URI, PortsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link PortsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PortsPackage init() {
		if (isInited) return (PortsPackage)EPackage.Registry.INSTANCE.getEPackage(PortsPackage.eNS_URI);

		// Obtain or create and register package
		PortsPackageImpl thePortsPackage = (PortsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PortsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PortsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ScaPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePortsPackage.createPackageContents();

		// Initialize created meta-data
		thePortsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePortsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PortsPackage.eNS_URI, thePortsPackage);
		return thePortsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortMonitor() {
		return portMonitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortMonitor_Port() {
		return (EReference)portMonitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortMonitor_Connections() {
		return (EReference)portMonitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortMonitor_State() {
		return (EAttribute)portMonitorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortConnectionMonitor() {
		return portConnectionMonitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortConnectionMonitor_Port() {
		return (EReference)portConnectionMonitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortConnectionMonitor_ConnectionId() {
		return (EAttribute)portConnectionMonitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMonitorRegistry() {
		return monitorRegistryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMonitorRegistry_Monitors() {
		return (EReference)monitorRegistryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortSupplierMonitor() {
		return portSupplierMonitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortSupplierMonitor_PortContainer() {
		return (EReference)portSupplierMonitorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPortSupplierMonitor_Monitors() {
		return (EReference)portSupplierMonitorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMonitor() {
		return monitorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPortStatisticsProvider() {
		return portStatisticsProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPortStatisticsProvider_Data() {
		return (EAttribute)portStatisticsProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPortUsageType() {
		return portUsageTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getPortStatistics() {
		return portStatisticsEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortsFactory getPortsFactory() {
		return (PortsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		portMonitorEClass = createEClass(PORT_MONITOR);
		createEReference(portMonitorEClass, PORT_MONITOR__PORT);
		createEReference(portMonitorEClass, PORT_MONITOR__CONNECTIONS);
		createEAttribute(portMonitorEClass, PORT_MONITOR__STATE);

		portConnectionMonitorEClass = createEClass(PORT_CONNECTION_MONITOR);
		createEReference(portConnectionMonitorEClass, PORT_CONNECTION_MONITOR__PORT);
		createEAttribute(portConnectionMonitorEClass, PORT_CONNECTION_MONITOR__CONNECTION_ID);

		monitorRegistryEClass = createEClass(MONITOR_REGISTRY);
		createEReference(monitorRegistryEClass, MONITOR_REGISTRY__MONITORS);

		portSupplierMonitorEClass = createEClass(PORT_SUPPLIER_MONITOR);
		createEReference(portSupplierMonitorEClass, PORT_SUPPLIER_MONITOR__PORT_CONTAINER);
		createEReference(portSupplierMonitorEClass, PORT_SUPPLIER_MONITOR__MONITORS);

		monitorEClass = createEClass(MONITOR);

		portStatisticsProviderEClass = createEClass(PORT_STATISTICS_PROVIDER);
		createEAttribute(portStatisticsProviderEClass, PORT_STATISTICS_PROVIDER__DATA);

		// Create data types
		portUsageTypeEDataType = createEDataType(PORT_USAGE_TYPE);
		portStatisticsEDataType = createEDataType(PORT_STATISTICS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ScaPackage theScaPackage = (ScaPackage)EPackage.Registry.INSTANCE.getEPackage(ScaPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		portMonitorEClass.getESuperTypes().add(this.getMonitor());
		portMonitorEClass.getESuperTypes().add(this.getPortStatisticsProvider());
		portConnectionMonitorEClass.getESuperTypes().add(this.getPortStatisticsProvider());
		portSupplierMonitorEClass.getESuperTypes().add(this.getMonitor());

		// Initialize classes and features; add operations and parameters
		initEClass(portMonitorEClass, PortMonitor.class, "PortMonitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		EGenericType g1 = createEGenericType(theScaPackage.getScaPort());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getPortMonitor_Port(), g1, null, "port", null, 0, 1, PortMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortMonitor_Connections(), this.getPortConnectionMonitor(), this.getPortConnectionMonitor_Port(), "connections", null, 0, -1, PortMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortMonitor_State(), this.getPortUsageType(), "state", null, 0, 1, PortMonitor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(portConnectionMonitorEClass, PortConnectionMonitor.class, "PortConnectionMonitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortConnectionMonitor_Port(), this.getPortMonitor(), this.getPortMonitor_Connections(), "port", null, 0, 1, PortConnectionMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPortConnectionMonitor_ConnectionId(), theEcorePackage.getEString(), "connectionId", null, 0, 1, PortConnectionMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(monitorRegistryEClass, MonitorRegistry.class, "MonitorRegistry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMonitorRegistry_Monitors(), this.getMonitor(), null, "monitors", null, 0, -1, MonitorRegistry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(portSupplierMonitorEClass, PortSupplierMonitor.class, "PortSupplierMonitor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPortSupplierMonitor_PortContainer(), theScaPackage.getScaPortContainer(), null, "portContainer", null, 0, 1, PortSupplierMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPortSupplierMonitor_Monitors(), this.getPortMonitor(), null, "monitors", null, 0, -1, PortSupplierMonitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(monitorEClass, Monitor.class, "Monitor", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(monitorEClass, null, "fetchStats", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(portStatisticsProviderEClass, PortStatisticsProvider.class, "PortStatisticsProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPortStatisticsProvider_Data(), this.getPortStatistics(), "data", null, 0, 1, PortStatisticsProvider.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(portUsageTypeEDataType, PortUsageType.class, "PortUsageType", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(portStatisticsEDataType, PortStatistics.class, "PortStatistics", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //PortsPackageImpl
