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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.ui.views.allocmgr.AllocMgrPackage
 * @generated
 */
public interface AllocMgrFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AllocMgrFactory eINSTANCE = gov.redhawk.ui.views.allocmgr.impl.AllocMgrFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sca Allocation Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sca Allocation Manager</em>'.
	 * @generated
	 */
	ScaAllocationManager createScaAllocationManager();

	/**
	 * Returns a new object of class '<em>Allocation Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Allocation Status</em>'.
	 * @generated
	 */
	AllocationStatus createAllocationStatus();

	// END GENERATED CODE

	AllocationStatus createAllocationStatus(CF.AllocationManagerPackage.AllocationStatusType cfStatus);

	// BEGIN GENERATED CODE

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AllocMgrPackage getAllocMgrPackage();

} // AllocMgrFactory
