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
package gov.redhawk.monitor.model.ports.util;

import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.monitor.model.ports.PortsPackage;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.monitor.model.ports.PortsPackage
 * @generated
 */
public class PortsAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PortsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PortsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance
	 * object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortsSwitch<Adapter> modelSwitch = new PortsSwitch<Adapter>() {
		@Override
		public Adapter casePortMonitor(PortMonitor object) {
			return createPortMonitorAdapter();
		}

		@Override
		public Adapter casePortConnectionMonitor(PortConnectionMonitor object) {
			return createPortConnectionMonitorAdapter();
		}

		@Override
		public Adapter caseMonitorRegistry(MonitorRegistry object) {
			return createMonitorRegistryAdapter();
		}

		@Override
		public Adapter casePortSupplierMonitor(PortSupplierMonitor object) {
			return createPortSupplierMonitorAdapter();
		}

		@Override
		public Adapter caseMonitor(Monitor object) {
			return createMonitorAdapter();
		}

		@Override
		public Adapter casePortStatisticsProvider(PortStatisticsProvider object) {
			return createPortStatisticsProviderAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.PortMonitor <em>Port
	 * Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.PortMonitor
	 * @generated
	 */
	public Adapter createPortMonitorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.PortConnectionMonitor
	 * <em>Port Connection Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.PortConnectionMonitor
	 * @generated
	 */
	public Adapter createPortConnectionMonitorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.MonitorRegistry <em>Monitor
	 * Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.MonitorRegistry
	 * @generated
	 */
	public Adapter createMonitorRegistryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.PortSupplierMonitor <em>Port
	 * Supplier Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.PortSupplierMonitor
	 * @generated
	 */
	public Adapter createPortSupplierMonitorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.Monitor <em>Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.Monitor
	 * @generated
	 */
	public Adapter createMonitorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.monitor.model.ports.PortStatisticsProvider
	 * <em>Port Statistics Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.monitor.model.ports.PortStatisticsProvider
	 * @generated
	 */
	public Adapter createPortStatisticsProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // PortsAdapterFactory
