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
package gov.redhawk.eclipsecorba.library.tests;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryFactory;
import gov.redhawk.eclipsecorba.library.RepositoryModule;
import gov.redhawk.eclipsecorba.library.URIPathSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Repository Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions() <em>Definitions</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.RepositoryModule#find(java.lang.String) <em>Find</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class RepositoryModuleTest extends TestCase {

	/**
	 * The fixture for this Repository Module test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryModule fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RepositoryModuleTest.class);
	}

	/**
	 * Constructs a new Repository Module test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RepositoryModuleTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Repository Module test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(RepositoryModule fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Repository Module test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryModule getFixture() {
		return fixture;
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
		final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final Resource libraryResource = resourceSet.createResource(URI.createFileURI(".library"));

		final IdlLibrary library = LibraryFactory.eINSTANCE.createIdlLibrary();
		setFixture(library);
		final URIPathSet uriPath = LibraryFactory.eINSTANCE.createURIPathSet();
		uriPath.getDirs().add(LibraryTestUtil.getURI("idl"));
		library.getPaths().add(uriPath);

		editingDomain.getCommandStack().execute(new AddCommand(editingDomain, libraryResource.getContents(), this.fixture));

		library.load(null);

		Assert.assertTrue(library.getLoadStatus().getMessage(), library.getLoadStatus().isOK());
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
	 * Tests the '{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions() <em>Definitions</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions()
	 * @generated NOT
	 */
	public void testGetDefinitions() {
		// END GENERATED CODE
		Assert.assertFalse(this.fixture.getDefinitions().isEmpty());
		for (final Definition def : this.fixture.getDefinitions()) {
			Assert.assertFalse(getFixture().eResource().getURI().equals(def.eResource().getURI()));
		}

		final Collection<String> expectedDefinitions = new HashSet<String>(Arrays.asList((new String[] {
		        "CosEventComm", "LAB", "CosNaming", "CosEventChannelAdmin"
		})));
		final Collection<String> actualDefinitions = new HashSet<String>();
		for (final Definition definition : this.fixture.getDefinitions()) {
			actualDefinitions.add(definition.getName());
		}
		Assert.assertEquals("The IDL Library does not contain the expected definitions: " + expectedDefinitions + "!=" + actualDefinitions,
		        expectedDefinitions.size(),
		        actualDefinitions.size());
		Assert.assertEquals("All of the expected Definitions are not present: " + expectedDefinitions + " != " + actualDefinitions,
		        true,
		        expectedDefinitions.containsAll(actualDefinitions));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.library.RepositoryModule#find(java.lang.String) <em>Find</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule#find(java.lang.String)
	 * @generated NOT
	 */
    public void testFind__String() {
		// END GENERATED CODE
		Assert.assertNotNull(this.fixture.find("IDL:CF/File:1.0"));
		// BEGIN GENERATED CODE
	}

} //RepositoryModuleTest
