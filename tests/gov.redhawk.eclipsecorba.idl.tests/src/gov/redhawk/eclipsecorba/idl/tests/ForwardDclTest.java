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

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.Module;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Forward Dcl</b></em>'. <!-- end-user-doc -->
 * @generated
 */
public class ForwardDclTest extends IdlTypeDclTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ForwardDclTest.class);
	}

	/**
	 * Constructs a new Forward Dcl test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForwardDclTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Forward Dcl test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ForwardDcl getFixture() {
		return (ForwardDcl)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(IdlFactory.eINSTANCE.createForwardDcl());
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
		final Module module = IdlFactory.eINSTANCE.createModule();
		module.setRepId(repId);
		module.getDefinitions().add((Definition) id);
		return module;

	}

	@Override
	protected boolean isForward() {
		return true;
	}

} //ForwardDclTest
