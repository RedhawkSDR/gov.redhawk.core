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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaFactory;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;
import mil.jpeojtrs.sca.util.DceUuidUtil;
import ExtendedCF.UsesConnection;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaConnection#getId() <em>Id</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaConnectionTest extends TestCase {

	/**
	 * The fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnection fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaConnectionTest.class);
	}

	/**
	 * Constructs a new Connection test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaConnectionTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScaConnection fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Connection test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnection getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ScaFactory.eINSTANCE.createScaConnection());
		getFixture().setData(new UsesConnection(DceUuidUtil.createDceUUID(), null));
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaConnection#getId() <em>Id</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaConnection#getId()
	 * @generated NOT
	 */
	public void testGetId() {
		// END GENERATED CODE
		Assert.assertEquals(getFixture().getData().connectionId, getFixture().getId());
		// BEGIN GENERATED CODE
	}

} //ScaConnectionTest
