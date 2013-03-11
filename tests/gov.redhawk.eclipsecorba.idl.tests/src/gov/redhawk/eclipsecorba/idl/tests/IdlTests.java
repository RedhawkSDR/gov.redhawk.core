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

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>idl</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdlTests extends TestSuite {

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
		TestSuite suite = new IdlTests("idl Tests");
		suite.addTestSuite(DeclaratorTest.class);
		suite.addTestSuite(ArrayDeclaratorTest.class);
		suite.addTestSuite(ModuleTest.class);
		suite.addTestSuite(IdlConstDclTest.class);
		suite.addTestSuite(IdlExceptionTest.class);
		suite.addTestSuite(MemberTest.class);
		suite.addTestSuite(ForwardDclTest.class);
		suite.addTestSuite(IdlInterfaceDclTest.class);
		suite.addTestSuite(NativeTypeDclTest.class);
		suite.addTestSuite(ValueTypeDclTest.class);
		suite.addTestSuite(ValueForwardDclTest.class);
		suite.addTestSuite(ValueDclTest.class);
		suite.addTestSuite(ValueBoxDclTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlTests(String name) {
		super(name);
	}

} //IdlTests
