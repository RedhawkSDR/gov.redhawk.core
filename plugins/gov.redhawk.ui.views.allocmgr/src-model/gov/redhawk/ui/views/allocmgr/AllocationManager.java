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
package gov.redhawk.ui.views.allocmgr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Allocation Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.allocmgr.AllocationManager#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationManager()
 * @model
 * @generated
 */
public interface AllocationManager extends EObject {
	/**
	 * Returns the value of the '<em><b>Status</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.ui.views.allocmgr.AllocationStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' containment reference list.
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getAllocationManager_Status()
	 * @model containment="true"
	 * @generated
	 */
	EList<AllocationStatus> getStatus();

} // AllocationManager
