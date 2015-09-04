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
package gov.redhawk.monitor.model.ports;

import gov.redhawk.model.sca.ScaPort;

import org.eclipse.emf.common.util.EList;

import BULKIO.PortUsageType;

/**
 * <!-- begin-user-doc -->
 * An object that monitors a REDHAWK port.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.monitor.model.ports.PortMonitor#getPort <em>Port</em>}</li>
 * <li>{@link gov.redhawk.monitor.model.ports.PortMonitor#getConnections <em>Connections</em>}</li>
 * <li>{@link gov.redhawk.monitor.model.ports.PortMonitor#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.monitor.model.ports.PortsPackage#getPortMonitor()
 * @model
 * @generated
 */
public interface PortMonitor extends Monitor, PortStatisticsProvider {

	/**
	 * Returns the value of the '<em><b>Port</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * This is the model object for the REDHAWK port being monitored.
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' reference.
	 * @see #setPort(ScaPort)
	 * @see gov.redhawk.monitor.model.ports.PortsPackage#getPortMonitor_Port()
	 * @model
	 * @generated
	 */
	ScaPort< ? , ? > getPort();

	/**
	 * Sets the value of the '{@link gov.redhawk.monitor.model.ports.PortMonitor#getPort <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * This is the model object for the REDHAWK port being monitored.
	 * </p>
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(ScaPort< ? , ? > value);

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.monitor.model.ports.PortConnectionMonitor}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.monitor.model.ports.PortConnectionMonitor#getPort
	 * <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see gov.redhawk.monitor.model.ports.PortsPackage#getPortMonitor_Connections()
	 * @see gov.redhawk.monitor.model.ports.PortConnectionMonitor#getPort
	 * @model opposite="port" containment="true"
	 * @generated
	 */
	EList<PortConnectionMonitor> getConnections();

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see #setState(PortUsageType)
	 * @see gov.redhawk.monitor.model.ports.PortsPackage#getPortMonitor_State()
	 * @model dataType="gov.redhawk.monitor.model.ports.PortUsageType" transient="true" derived="true"
	 * @generated
	 */
	PortUsageType getState();

	/**
	 * Sets the value of the '{@link gov.redhawk.monitor.model.ports.PortMonitor#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see #getState()
	 * @generated
	 */
	void setState(PortUsageType value);

} // PortMonitor
