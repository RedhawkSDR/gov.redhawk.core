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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Connection Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getPort <em>Port</em>}</li>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getConnectionId <em>Connection Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortConnectionMonitor()
 * @model
 * @generated
 */
public interface PortConnectionMonitor extends PortStatisticsProvider {

	/**
	 * Returns the value of the '<em><b>Port</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' container reference.
	 * @see #setPort(PortMonitor)
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortConnectionMonitor_Port()
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortMonitor#getConnections
	 * @model opposite="connections" transient="false"
	 * @generated
	 */
	PortMonitor getPort();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getPort <em>Port</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' container reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(PortMonitor value);

	/**
	 * Returns the value of the '<em><b>Connection Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Id</em>' attribute.
	 * @see #setConnectionId(String)
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortConnectionMonitor_ConnectionId()
	 * @model
	 * @generated
	 */
	String getConnectionId();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor#getConnectionId <em>Connection Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Id</em>' attribute.
	 * @see #getConnectionId()
	 * @generated
	 */
	void setConnectionId(String value);

} // PortConnectionMonitor
