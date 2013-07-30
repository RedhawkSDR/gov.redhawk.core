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
package gov.redhawk.eclipsecorba.idl.types.tests;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>types</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesTests extends TestSuite {

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
		TestSuite suite = new TypesTests("types Tests");
		suite.addTestSuite(TypeDefTest.class);
		suite.addTestSuite(UnionTypeTest.class);
		suite.addTestSuite(EnumTypeTest.class);
		suite.addTestSuite(StructTypeTest.class);
		suite.addTestSuite(UnionForwardDclTest.class);
		suite.addTestSuite(StructForwardDclTest.class);
		suite.addTestSuite(EnumerationTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesTests(String name) {
		super(name);
	}

} //TypesTests
