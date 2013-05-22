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

import gov.redhawk.model.sca.ScaPortContainer;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port Supplier Monitor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getPortContainer <em>Port Container</em>}</li>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getMonitors <em>Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortSupplierMonitor()
 * @model
 * @generated
 */
public interface PortSupplierMonitor extends Monitor {

	/**
	 * Returns the value of the '<em><b>Port Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port Container</em>' reference.
	 * @see #setPortContainer(ScaPortContainer)
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortSupplierMonitor_PortContainer()
	 * @model
	 * @generated
	 */
	ScaPortContainer getPortContainer();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor#getPortContainer <em>Port Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port Container</em>' reference.
	 * @see #getPortContainer()
	 * @generated
	 */
	void setPortContainer(ScaPortContainer value);

	/**
	 * Returns the value of the '<em><b>Monitors</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.ui.views.monitor.model.ports.PortMonitor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitors</em>' containment reference list.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getPortSupplierMonitor_Monitors()
	 * @model containment="true"
	 * @generated
	 */
	EList<PortMonitor> getMonitors();

} // PortSupplierMonitor
