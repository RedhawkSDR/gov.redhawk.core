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
package gov.redhawk.eclipsecorba.library.tests;

import gov.redhawk.eclipsecorba.library.LibraryFactory;
import gov.redhawk.eclipsecorba.library.URIPathSet;
import junit.framework.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>URI Path Set</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class URIPathSetTest extends PathTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(URIPathSetTest.class);
	}

	/**
	 * Constructs a new URI Path Set test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URIPathSetTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this URI Path Set test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected URIPathSet getFixture() {
		return (URIPathSet)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(LibraryFactory.eINSTANCE.createURIPathSet());
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
	public void testGetDerivedPath() {
		final URIPathSet pathSet = LibraryFactory.eINSTANCE.createURIPathSet();
		final URI uri = URI.createURI("virtual:/test.tmp");
		pathSet.getDirs().add(uri);
		Assert.assertEquals(pathSet.getDirs().size(), pathSet.getDerivedPath().size());
		Assert.assertEquals(pathSet.getDirs().get(0), pathSet.getDerivedPath().get(0));
		// TODO Auto-generated method stub

	}

} //URIPathSetTest
