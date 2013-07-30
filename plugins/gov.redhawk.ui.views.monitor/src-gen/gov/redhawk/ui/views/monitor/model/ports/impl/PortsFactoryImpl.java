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
package gov.redhawk.ui.views.monitor.model.ports.impl;

import gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry;
import gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortsFactory;
import gov.redhawk.ui.views.monitor.model.ports.PortsPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class PortsFactoryImpl extends EFactoryImpl implements PortsFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PortsFactory init() {
		try {
			PortsFactory thePortsFactory = (PortsFactory)EPackage.Registry.INSTANCE.getEFactory(PortsPackage.eNS_URI);
			if (thePortsFactory != null) {
				return thePortsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PortsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortsFactoryImpl() {
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
			case PortsPackage.PORT_MONITOR: return createPortMonitor();
			case PortsPackage.PORT_CONNECTION_MONITOR: return createPortConnectionMonitor();
			case PortsPackage.MONITOR_REGISTRY: return createMonitorRegistry();
			case PortsPackage.PORT_SUPPLIER_MONITOR: return createPortSupplierMonitor();
			case PortsPackage.PORT_STATISTICS_PROVIDER: return createPortStatisticsProvider();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortMonitor createPortMonitor() {
		PortMonitorImpl portMonitor = new PortMonitorImpl();
		return portMonitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortConnectionMonitor createPortConnectionMonitor() {
		PortConnectionMonitorImpl portConnectionMonitor = new PortConnectionMonitorImpl();
		return portConnectionMonitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitorRegistry createMonitorRegistry() {
		MonitorRegistryImpl monitorRegistry = new MonitorRegistryImpl();
		return monitorRegistry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortSupplierMonitor createPortSupplierMonitor() {
		PortSupplierMonitorImpl portSupplierMonitor = new PortSupplierMonitorImpl();
		return portSupplierMonitor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortStatisticsProvider createPortStatisticsProvider() {
		PortStatisticsProviderImpl portStatisticsProvider = new PortStatisticsProviderImpl();
		return portStatisticsProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortsPackage getPortsPackage() {
		return (PortsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PortsPackage getPackage() {
		return PortsPackage.eINSTANCE;
	}

} //PortsFactoryImpl
