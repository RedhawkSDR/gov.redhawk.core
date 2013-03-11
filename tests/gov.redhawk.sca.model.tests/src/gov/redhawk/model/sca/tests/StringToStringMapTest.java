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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;

import java.util.Map;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>String To String Map</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StringToStringMapTest extends TestCase {
	/**
	 * The fixture for this String To String Map test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map.Entry<String, String> fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StringToStringMapTest.class);
	}

	/**
	 * Constructs a new String To String Map test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringToStringMapTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this String To String Map test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Map.Entry<String, String> fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this String To String Map test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Map.Entry<String, String> getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void setUp() throws Exception {
		setFixture((Map.Entry<String, String>)ScaFactory.eINSTANCE.create(ScaPackage.Literals.STRING_TO_STRING_MAP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	/**
	 * A placeholder until real tests are implemented.
	 * @generated NOT
	 */
	public void testPlaceholder() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture());
		// BEGIN GENERATED CODE
	}

} //StringToStringMapTest
