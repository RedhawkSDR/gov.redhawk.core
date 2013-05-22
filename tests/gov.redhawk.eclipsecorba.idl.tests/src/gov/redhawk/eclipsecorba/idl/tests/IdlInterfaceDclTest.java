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
package gov.redhawk.eclipsecorba.idl.tests;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.Module;
import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Interface Dcl</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isInstance(gov.redhawk.eclipsecorba.idl.IdlTypeDcl) <em>Is Instance</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class IdlInterfaceDclTest extends IdlTypeDclTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(IdlInterfaceDclTest.class);
	}

	/**
	 * Constructs a new Interface Dcl test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlInterfaceDclTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Interface Dcl test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected IdlInterfaceDcl getFixture() {
		return (IdlInterfaceDcl)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(IdlFactory.eINSTANCE.createIdlInterfaceDcl());
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
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isInstance(gov.redhawk.eclipsecorba.idl.IdlTypeDcl)
	 * <em>Is Instance</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isInstance(gov.redhawk.eclipsecorba.idl.IdlTypeDcl)
	 * @generated NOT
	 */
	public void testIsInstance__IdlTypeDcl() {
		// END GENERATED CODE
		final IdlInterfaceDcl dcl1 = IdlFactory.eINSTANCE.createIdlInterfaceDcl();
		dcl1.setRepId("IDL:CF/LoadableDevice:1.0");
		final IdlInterfaceDcl dcl2 = IdlFactory.eINSTANCE.createIdlInterfaceDcl();
		dcl2.setRepId("IDL:CF/Device:1.0");

		dcl1.getInheritedInterfaces().add(dcl2);

		Assert.assertFalse(dcl2.isInstance(dcl1));

		Assert.assertTrue(dcl1.isInstance(dcl2));
		// BEGIN GENERATED CODE
	}

	@Override
	protected Identifiable addToContainer(final String repId, final Identifiable id) {
		final Module module = IdlFactory.eINSTANCE.createModule();
		module.setRepId(repId);
		module.getDefinitions().add((Definition) id);
		return module;
	}

} //IdlInterfaceDclTest
