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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Monitor Registry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry#getMonitors <em>Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getMonitorRegistry()
 * @model
 * @generated
 */
public interface MonitorRegistry extends EObject {

	/**
	 * Returns the value of the '<em><b>Monitors</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.ui.views.monitor.model.ports.Monitor}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitors</em>' containment reference list.
	 * @see gov.redhawk.ui.views.monitor.model.ports.PortsPackage#getMonitorRegistry_Monitors()
	 * @model containment="true"
	 * @generated
	 */
	EList<Monitor> getMonitors();

} // MonitorRegistry
