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
package gov.redhawk.eclipsecorba.idl.operations.tests;

import gov.redhawk.eclipsecorba.idl.Export;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.operations.Attribute;
import gov.redhawk.eclipsecorba.idl.operations.OperationsFactory;
import gov.redhawk.eclipsecorba.idl.tests.ExportTest;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Attribute</b></em>'. <!-- end-user-doc -->
 * @generated
 */
public class AttributeTest extends ExportTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(AttributeTest.class);
	}

	/**
	 * Constructs a new Attribute test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Attribute test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Attribute getFixture() {
		return (Attribute)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(OperationsFactory.eINSTANCE.createAttribute());
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

	@Override
	protected Identifiable addToContainer(final String repId, final Identifiable id) {
		final IdlInterfaceDcl dcl = IdlFactory.eINSTANCE.createIdlInterfaceDcl();
		dcl.setRepId(repId);
		dcl.getBody().add((Export) id);
		return dcl;
	}

} //AttributeTest
