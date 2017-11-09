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

import CF.AllocationManager;

import CF.AllocationManagerOperations;
import gov.redhawk.model.sca.CorbaObjWrapper;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sca Allocation Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.allocmgr.ScaAllocationManager#getAllocations <em>Allocations</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getScaAllocationManager()
 * @model superTypes="gov.redhawk.model.sca.CorbaObjWrapper&lt;mil.jpeojtrs.sca.cf.AllocationManager&gt;
 * mil.jpeojtrs.sca.cf.AllocationManagerOperations"
 * @generated
 */
public interface ScaAllocationManager extends CorbaObjWrapper<AllocationManager>, AllocationManagerOperations {
	/**
	 * Returns the value of the '<em><b>Allocations</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.ui.views.allocmgr.AllocationStatus}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocations</em>' containment reference list.
	 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage#getScaAllocationManager_Allocations()
	 * @model containment="true"
	 * @generated
	 */
	EList<AllocationStatus> getAllocations();

} // ScaAllocationManager
