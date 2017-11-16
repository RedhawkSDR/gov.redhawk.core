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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage
 * @generated
 */
public interface ConnMgrFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConnMgrFactory eINSTANCE = gov.redhawk.ui.views.connmgr.impl.ConnMgrFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sca Connection Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sca Connection Manager</em>'.
	 * @generated
	 */
	ScaConnectionManager createScaConnectionManager();

	/**
	 * Returns a new object of class '<em>Connection Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Status</em>'.
	 * @generated
	 */
	ConnectionStatus createConnectionStatus();

	// END GENERATED CODE

	ConnectionStatus createConnectionStatus(CF.ConnectionManagerPackage.ConnectionStatusType cfStatus);

	// BEGIN GENERATED CODE

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ConnMgrPackage getConnMgrPackage();

} // ConnMgrFactory
