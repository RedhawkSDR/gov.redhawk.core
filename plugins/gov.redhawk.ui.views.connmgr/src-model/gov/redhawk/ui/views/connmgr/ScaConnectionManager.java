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
package gov.redhawk.ui.views.connmgr;

import CF.ConnectionManager;

import CF.ConnectionManagerOperations;
import gov.redhawk.model.sca.CorbaObjWrapper;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sca Connection Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.connmgr.ScaConnectionManager#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getScaConnectionManager()
 * @model superTypes="gov.redhawk.model.sca.CorbaObjWrapper&lt;mil.jpeojtrs.sca.cf.ConnectionManager&gt;
 * mil.jpeojtrs.sca.cf.ConnectionManagerOperations"
 * @generated
 */
public interface ScaConnectionManager extends CorbaObjWrapper<ConnectionManager>, ConnectionManagerOperations {
	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.ui.views.connmgr.ConnectionStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getScaConnectionManager_Connections()
	 * @model containment="true"
	 * @generated
	 */
	EList<ConnectionStatus> getConnections();

} // ScaConnectionManager
