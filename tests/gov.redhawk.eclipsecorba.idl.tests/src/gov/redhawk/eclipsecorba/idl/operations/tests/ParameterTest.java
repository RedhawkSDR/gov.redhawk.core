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

import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.operations.Operation;
import gov.redhawk.eclipsecorba.idl.operations.OperationsFactory;
import gov.redhawk.eclipsecorba.idl.operations.Parameter;
import gov.redhawk.eclipsecorba.idl.tests.TypedElementTest;
import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Parameter</b></em>'. <!-- end-user-doc -->
 * @generated
 */
public class ParameterTest extends TypedElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ParameterTest.class);
	}

	/**
	 * Constructs a new Parameter test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Parameter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Parameter getFixture() {
		return (Parameter)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(OperationsFactory.eINSTANCE.createParameter());
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
		final Operation op = OperationsFactory.eINSTANCE.createOperation();
		op.setName("operation");
		op.getParameters().add((Parameter) id);

		final IdlInterfaceDcl dcl = IdlFactory.eINSTANCE.createIdlInterfaceDcl();
		dcl.setRepId(repId);
		dcl.getBody().add(op);
		return dcl;
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName()
	 * <em>Scoped Name</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName()
	 * @generated NOT
	 */
	@Override
	public void testGetScopedName() {
		final Identifiable id = getFixture();
		id.setName("Test");

		if (!isForward()) {
			Assert.assertEquals("Test", id.getScopedName());
		} else {
			Assert.assertEquals("TestForward", id.getScopedName());
		}

		final Identifiable container = addToContainer("IDL:PARENT:1.0", id);
		container.setName("PARENT");
		Assert.assertEquals("PARENT/operation/Test", id.getScopedName());
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId()
	 * <em>Rep Id</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getRepId()
	 * @generated NOT
	 */
	@Override
	public void testGetRepId() {
		final Identifiable id = getFixture();
		id.setName("Test");
		Assert.assertEquals("IDL:Test:1.0", id.getRepId());

		addToContainer("IDL:PARENT:1.0", id);
		Assert.assertEquals("IDL:PARENT/operation/Test:1.0", id.getRepId());

		id.setPrefix("PRE");
		Assert.assertEquals("IDL:PRE/PARENT/operation/Test:1.0", id.getRepId());

		id.setId("MY/Test");
		Assert.assertEquals("IDL:MY/Test:1.0", id.getRepId());

		id.setRepId("IDL:CF/Test:2.0");
		Assert.assertEquals("IDL:CF/Test:2.0", id.getRepId());
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId()
	 * <em>Id</em>}' feature getter. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getId()
	 * @generated NOT
	 */
	@Override
	public void testGetId() {
		final Identifiable id = getFixture();
		id.setName("Test");
		Assert.assertEquals("Test", id.getId());

		id.setPrefix("PRE");
		Assert.assertEquals("PRE/Test", id.getId());

		id.setPrefix(null);
		addToContainer("IDL:PARENT:2.0", id);
		Assert.assertEquals("PARENT/operation/Test", id.getId());

		id.setPrefix("PRE");
		addToContainer("IDL:PARENT:2.0", id);
		Assert.assertEquals("PRE/PARENT/operation/Test", id.getId());

		id.setId("CF/Device");
		Assert.assertEquals("CF/Device", id.getId());
	}

} //ParameterTest
