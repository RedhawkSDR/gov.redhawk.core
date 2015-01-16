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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each
 * class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see gov.redhawk.monitor.model.ports.PortsPackage
 * @generated
 */
public class PortsSwitch< T > extends Switch<T> {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PortsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortsSwitch() {
		if (modelPackage == null) {
			modelPackage = PortsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case PortsPackage.PORT_MONITOR: {
			PortMonitor portMonitor = (PortMonitor) theEObject;
			T result = casePortMonitor(portMonitor);
			if (result == null)
				result = caseMonitor(portMonitor);
			if (result == null)
				result = casePortStatisticsProvider(portMonitor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PortsPackage.PORT_CONNECTION_MONITOR: {
			PortConnectionMonitor portConnectionMonitor = (PortConnectionMonitor) theEObject;
			T result = casePortConnectionMonitor(portConnectionMonitor);
			if (result == null)
				result = casePortStatisticsProvider(portConnectionMonitor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PortsPackage.MONITOR_REGISTRY: {
			MonitorRegistry monitorRegistry = (MonitorRegistry) theEObject;
			T result = caseMonitorRegistry(monitorRegistry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PortsPackage.PORT_SUPPLIER_MONITOR: {
			PortSupplierMonitor portSupplierMonitor = (PortSupplierMonitor) theEObject;
			T result = casePortSupplierMonitor(portSupplierMonitor);
			if (result == null)
				result = caseMonitor(portSupplierMonitor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PortsPackage.MONITOR: {
			Monitor monitor = (Monitor) theEObject;
			T result = caseMonitor(monitor);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PortsPackage.PORT_STATISTICS_PROVIDER: {
			PortStatisticsProvider portStatisticsProvider = (PortStatisticsProvider) theEObject;
			T result = casePortStatisticsProvider(portStatisticsProvider);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Monitor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Monitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortMonitor(PortMonitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Connection Monitor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Connection Monitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortConnectionMonitor(PortConnectionMonitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Monitor Registry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Monitor Registry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMonitorRegistry(MonitorRegistry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Supplier Monitor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Supplier Monitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortSupplierMonitor(PortSupplierMonitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Monitor</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Monitor</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMonitor(Monitor object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Statistics Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Statistics Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePortStatisticsProvider(PortStatisticsProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // PortsSwitch
