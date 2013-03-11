/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.tests;

import gov.redhawk.eclipsecorba.idl.expressions.tests.ExpressionsTests;
import gov.redhawk.eclipsecorba.idl.operations.tests.OperationsTests;
import gov.redhawk.eclipsecorba.idl.types.tests.TypesTests;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>Idl</b></em>' model.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdlAllTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new IdlAllTests("Idl Tests");
		suite.addTest(IdlTests.suite());
		suite.addTest(OperationsTests.suite());
		suite.addTest(TypesTests.suite());
		suite.addTest(ExpressionsTests.suite());
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlAllTests(String name) {
		super(name);
	}

} //IdlAllTests
