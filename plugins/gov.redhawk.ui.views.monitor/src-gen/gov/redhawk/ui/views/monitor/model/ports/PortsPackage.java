/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package gov.redhawk.ui.views.monitor.model.ports;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * @since 5.0
 * <!-- end-user-doc -->
 * @see gov.redhawk.ui.views.monitor.model.ports.PortsFactory
 * @model kind="package"
 * @generated
 */
public interface PortsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ports";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://redhawk.gov/ui/views/monitor/ports";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ports";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PortsPackage eINSTANCE = gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.Monitor <em>Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.Monitor
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getMonitor()
	 * @generated
	 */
	int MONITOR = 4;
	/**
	 * The number of structural features of the '<em>Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR_FEATURE_COUNT = 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl <em>Port Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortMonitor()
	 * @generated
	 */
	int PORT_MONITOR = 0;
	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_MONITOR__DATA = MONITOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_MONITOR__PORT = MONITOR_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_MONITOR__CONNECTIONS = MONITOR_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_MONITOR__STATE = MONITOR_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Port Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_MONITOR_FEATURE_COUNT = MONITOR_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortStatisticsProviderImpl <em>Port Statistics Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortStatisticsProviderImpl
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortStatisticsProvider()
	 * @generated
	 */
	int PORT_STATISTICS_PROVIDER = 5;
	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_STATISTICS_PROVIDER__DATA = 0;
	/**
	 * The number of structural features of the '<em>Port Statistics Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_STATISTICS_PROVIDER_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortConnectionMonitorImpl <em>Port Connection Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortConnectionMonitorImpl
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortConnectionMonitor()
	 * @generated
	 */
	int PORT_CONNECTION_MONITOR = 1;
	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTION_MONITOR__DATA = PORT_STATISTICS_PROVIDER__DATA;
	/**
	 * The feature id for the '<em><b>Port</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTION_MONITOR__PORT = PORT_STATISTICS_PROVIDER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Connection Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTION_MONITOR__CONNECTION_ID = PORT_STATISTICS_PROVIDER_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Port Connection Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_CONNECTION_MONITOR_FEATURE_COUNT = PORT_STATISTICS_PROVIDER_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.MonitorRegistryImpl <em>Monitor Registry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.MonitorRegistryImpl
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getMonitorRegistry()
	 * @generated
	 */
	int MONITOR_REGISTRY = 2;
	/**
	 * The feature id for the '<em><b>Monitors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR_REGISTRY__MONITORS = 0;
	/**
	 * The number of structural features of the '<em>Monitor Registry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MONITOR_REGISTRY_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortSupplierMonitorImpl <em>Port Supplier Monitor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortSupplierMonitorImpl
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortSupplierMonitor()
	 * @generated
	 */
	int PORT_SUPPLIER_MONITOR = 3;
	/**
	 * The feature id for the '<em><b>Port Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_SUPPLIER_MONITOR__PORT_CONTAINER = MONITOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Monitors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_SUPPLIER_MONITOR__MONITORS = MONITOR_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Port Supplier Monitor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_SUPPLIER_MONITOR_FEATURE_COUNT = MONITOR_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '<em>Port Usage Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BULKIO.PortUsageType
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortUsageType()
	 * @generated
	 */
	int PORT_USAGE_TYPE = 6;
	/**
	 * The meta object id for the '<em>Port Statistics</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BULKIO.PortStatistics
	 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortStatistics()
	 * @generated
	 */
	int PORT_STATISTICS = 7;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor <em>Port Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Monitor</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortMonitor
	 * @generated
	 */
	EClass getPortMonitor();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getPort()
	 * @see #getPortMonitor()
	 * @generated
	 */
	EReference getPortMonitor_Port();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getConnections()
	 * @see #getPortMonitor()
	 * @generated
	 */
	EReference getPortMonitor_Connections();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getState()
	 * @see #getPortMonitor()
	 * @generated
	 */
	EAttribute getPortMonitor_State();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor <em>Port Connection Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Connection Monitor</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor
	 * @generated
	 */
	EClass getPortConnectionMonitor();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Port</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getPort()
	 * @see #getPortConnectionMonitor()
	 * @generated
	 */
	EReference getPortConnectionMonitor_Port();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getConnectionId <em>Connection Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Id</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getConnectionId()
	 * @see #getPortConnectionMonitor()
	 * @generated
	 */
	EAttribute getPortConnectionMonitor_ConnectionId();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry <em>Monitor Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Monitor Registry</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry
	 * @generated
	 */
	EClass getMonitorRegistry();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry#getMonitors <em>Monitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Monitors</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry#getMonitors()
	 * @see #getMonitorRegistry()
	 * @generated
	 */
	EReference getMonitorRegistry_Monitors();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor <em>Port Supplier Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Supplier Monitor</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor
	 * @generated
	 */
	EClass getPortSupplierMonitor();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getPortContainer <em>Port Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Port Container</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getPortContainer()
	 * @see #getPortSupplierMonitor()
	 * @generated
	 */
	EReference getPortSupplierMonitor_PortContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getMonitors <em>Monitors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Monitors</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getMonitors()
	 * @see #getPortSupplierMonitor()
	 * @generated
	 */
	EReference getPortSupplierMonitor_Monitors();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.Monitor <em>Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Monitor</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.Monitor
	 * @generated
	 */
	EClass getMonitor();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider <em>Port Statistics Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port Statistics Provider</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider
	 * @generated
	 */
	EClass getPortStatisticsProvider();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider#getData()
	 * @see #getPortStatisticsProvider()
	 * @generated
	 */
	EAttribute getPortStatisticsProvider_Data();

	/**
	 * Returns the meta object for data type '{@link BULKIO.PortUsageType <em>Port Usage Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Port Usage Type</em>'.
	 * @see BULKIO.PortUsageType
	 * @model instanceClass="BULKIO.PortUsageType" serializeable="false"
	 * @generated
	 */
	EDataType getPortUsageType();

	/**
	 * Returns the meta object for data type '{@link BULKIO.PortStatistics <em>Port Statistics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Port Statistics</em>'.
	 * @see BULKIO.PortStatistics
	 * @model instanceClass="BULKIO.PortStatistics" serializeable="false"
	 * @generated
	 */
	EDataType getPortStatistics();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PortsFactory getPortsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl <em>Port Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortMonitor()
		 * @generated
		 */
		EClass PORT_MONITOR = eINSTANCE.getPortMonitor();
		/**
		 * The meta object literal for the '<em><b>Port</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_MONITOR__PORT = eINSTANCE.getPortMonitor_Port();
		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_MONITOR__CONNECTIONS = eINSTANCE.getPortMonitor_Connections();
		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_MONITOR__STATE = eINSTANCE.getPortMonitor_State();
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortConnectionMonitorImpl <em>Port Connection Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortConnectionMonitorImpl
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortConnectionMonitor()
		 * @generated
		 */
		EClass PORT_CONNECTION_MONITOR = eINSTANCE.getPortConnectionMonitor();
		/**
		 * The meta object literal for the '<em><b>Port</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_CONNECTION_MONITOR__PORT = eINSTANCE.getPortConnectionMonitor_Port();
		/**
		 * The meta object literal for the '<em><b>Connection Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_CONNECTION_MONITOR__CONNECTION_ID = eINSTANCE.getPortConnectionMonitor_ConnectionId();
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.MonitorRegistryImpl <em>Monitor Registry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.MonitorRegistryImpl
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getMonitorRegistry()
		 * @generated
		 */
		EClass MONITOR_REGISTRY = eINSTANCE.getMonitorRegistry();
		/**
		 * The meta object literal for the '<em><b>Monitors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MONITOR_REGISTRY__MONITORS = eINSTANCE.getMonitorRegistry_Monitors();
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortSupplierMonitorImpl <em>Port Supplier Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortSupplierMonitorImpl
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortSupplierMonitor()
		 * @generated
		 */
		EClass PORT_SUPPLIER_MONITOR = eINSTANCE.getPortSupplierMonitor();
		/**
		 * The meta object literal for the '<em><b>Port Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_SUPPLIER_MONITOR__PORT_CONTAINER = eINSTANCE.getPortSupplierMonitor_PortContainer();
		/**
		 * The meta object literal for the '<em><b>Monitors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PORT_SUPPLIER_MONITOR__MONITORS = eINSTANCE.getPortSupplierMonitor_Monitors();
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.Monitor <em>Monitor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.Monitor
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getMonitor()
		 * @generated
		 */
		EClass MONITOR = eINSTANCE.getMonitor();
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortStatisticsProviderImpl <em>Port Statistics Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortStatisticsProviderImpl
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortStatisticsProvider()
		 * @generated
		 */
		EClass PORT_STATISTICS_PROVIDER = eINSTANCE.getPortStatisticsProvider();
		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT_STATISTICS_PROVIDER__DATA = eINSTANCE.getPortStatisticsProvider_Data();
		/**
		 * The meta object literal for the '<em>Port Usage Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BULKIO.PortUsageType
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortUsageType()
		 * @generated
		 */
		EDataType PORT_USAGE_TYPE = eINSTANCE.getPortUsageType();
		/**
		 * The meta object literal for the '<em>Port Statistics</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BULKIO.PortStatistics
		 * @see gov.redhawk.ui.views.monitor.model.ports.impl.PortsPackageImpl#getPortStatistics()
		 * @generated
		 */
		EDataType PORT_STATISTICS = eINSTANCE.getPortStatistics();

	}

} //PortsPackage
