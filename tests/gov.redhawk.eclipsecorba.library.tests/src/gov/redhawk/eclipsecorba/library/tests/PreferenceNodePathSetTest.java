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
import gov.redhawk.eclipsecorba.library.PreferenceNodePathSet;
import junit.framework.Assert;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Preference Node Path Set</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PreferenceNodePathSetTest extends PathTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PreferenceNodePathSetTest.class);
	}

	/**
	 * Constructs a new Preference Node Path Set test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PreferenceNodePathSetTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Preference Node Path Set test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected PreferenceNodePathSet getFixture() {
		return (PreferenceNodePathSet)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(LibraryFactory.eINSTANCE.createPreferenceNodePathSet());
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
		InstanceScope.INSTANCE.getNode("gov.redhawk.eclipsecorba.library.tests").put("IdlIncludePath", "helloWorld");
		
		final PreferenceNodePathSet pathSet = this.getFixture();
		pathSet.setDelimiter(";");
		pathSet.setFileUri(true);
		pathSet.setKey("IdlIncludePath");
		pathSet.setQualifier("gov.redhawk.eclipsecorba.library.tests");
		pathSet.setReplaceEnv(true);
		final EList<URI> derrivedPath = pathSet.getDerivedPath();
		Assert.assertTrue(derrivedPath.size() > 0);

	}

} //PreferenceNodePathSetTest
