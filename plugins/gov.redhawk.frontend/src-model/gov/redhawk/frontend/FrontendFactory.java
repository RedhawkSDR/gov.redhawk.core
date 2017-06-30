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
package gov.redhawk.frontend;

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.frontend.FrontendPackage
 * @generated
 */
public interface FrontendFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FrontendFactory eINSTANCE = gov.redhawk.frontend.impl.FrontendFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Tuner Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuner Container</em>'.
	 * @generated
	 */
	TunerContainer createTunerContainer();

	/**
	 * Returns a new object of class '<em>Unallocated Tuner Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unallocated Tuner Container</em>'.
	 * @generated
	 */
	UnallocatedTunerContainer createUnallocatedTunerContainer();

	/**
	 * Returns a new object of class '<em>Tuner Status</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tuner Status</em>'.
	 * @generated
	 */
	TunerStatus createTunerStatus();

	/**
	 * Returns a new object of class '<em>Listener Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Listener Allocation</em>'.
	 * @generated
	 */
	ListenerAllocation createListenerAllocation();

	/**
	 * Returns an instance of data type '<em>Sca Struct Property</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	ScaStructProperty createScaStructProperty(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>Sca Struct Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param instanceValue an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertScaStructProperty(ScaStructProperty instanceValue);

	/**
	 * Returns an instance of data type '<em>Sca Simple Property</em>' corresponding the given literal.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal a literal of the data type.
	 * @return a new instance value of the data type.
	 * @generated
	 */
	ScaSimpleProperty createScaSimpleProperty(String literal);

	/**
	 * Returns a literal representation of an instance of data type '<em>Sca Simple Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param instanceValue an instance value of the data type.
	 * @return a literal representation of the instance value.
	 * @generated
	 */
	String convertScaSimpleProperty(ScaSimpleProperty instanceValue);

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	FrontendPackage getFrontendPackage();

} // FrontendFactory
