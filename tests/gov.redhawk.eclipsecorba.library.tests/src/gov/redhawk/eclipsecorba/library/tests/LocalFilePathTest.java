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
import gov.redhawk.eclipsecorba.library.LocalFilePath;
import junit.framework.Assert;
import junit.textui.TestRunner;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Local File Path</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LocalFilePathTest extends PathTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LocalFilePathTest.class);
	}

	/**
	 * Constructs a new Local File Path test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocalFilePathTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Local File Path test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected LocalFilePath getFixture() {
		return (LocalFilePath)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(LibraryFactory.eINSTANCE.createLocalFilePath());
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
		final Path path = new Path("/usr/test");
		final Path path1 = new Path("./relative");
		this.getFixture().getLocalPaths().add(path);
		this.getFixture().getLocalPaths().add(path1);
		final EList<URI> derrivedPath = this.getFixture().getDerivedPath();
		Assert.assertEquals(2, derrivedPath.size());
		Assert.assertEquals(URI.createURI(path.toFile().toURI().toString()), derrivedPath.get(0));
		Assert.assertEquals(URI.createURI(path1.toFile().toURI().toString()), derrivedPath.get(1));

	}

} //LocalFilePathTest
