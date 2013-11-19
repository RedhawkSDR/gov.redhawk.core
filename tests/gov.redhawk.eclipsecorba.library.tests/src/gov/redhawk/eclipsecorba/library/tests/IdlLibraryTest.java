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

import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.library.IdlLibrary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Idl Library</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications() <em>Specifications</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.IdlLibrary#load(org.eclipse.core.runtime.IProgressMonitor) <em>Load</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class IdlLibraryTest extends RepositoryModuleTest {
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(IdlLibraryTest.class);
	}

	/**
	 * Constructs a new Idl Library test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlLibraryTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Idl Library test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected IdlLibrary getFixture() {
		return (IdlLibrary)fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		// END GENERATED CODE
		super.setUp();
		// BEGIN GENERATED CODE
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
	 * {@link gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications()
	 * <em>Specifications</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications()
	 * @generated NOT
	 */
	public void testGetSpecifications() throws Exception {
		// END GENERATED CODE
		final Collection<String> idls = new HashSet<String>();
		for (final Specification spec : getFixture().getSpecifications()) {
			idls.add(spec.getName());
		}
		final List<String> idlsList = new ArrayList<String>(idls);
		Collections.sort(idlsList);
		for (final URI idlUri : LibraryTestUtil.getIdlURIs()) {
			final String idl = idlUri.lastSegment();
			Assert.assertTrue("The idl : " + idlUri + " exists in the bundle but does not have a corresponding Specification.", idls.contains(idl));
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.library.IdlLibrary#load(org.eclipse.core.runtime.IProgressMonitor) <em>Load</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary#load(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testLoad__IProgressMonitor() throws Exception {
		// END GENERATED CODE

		getFixture().load(null);
		// BEGIN GENERATED CODE
	}

} //IdlLibraryTest
