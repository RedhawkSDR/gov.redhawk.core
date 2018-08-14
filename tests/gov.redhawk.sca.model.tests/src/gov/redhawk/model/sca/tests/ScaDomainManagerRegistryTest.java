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

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;
import junit.textui.TestRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Domain Manager Registry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#findDomain(java.lang.String) <em>Find Domain</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#createDomain(java.lang.String, java.lang.String, boolean, java.util.Map)
 * <em>Create Domain</em>}</li>
 * <li>{@link gov.redhawk.model.sca.IDisposable#dispose() <em>Dispose</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaDomainManagerRegistryTest extends TestCase {
	/**
	 * The fixture for this Domain Manager Registry test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDomainManagerRegistry fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDomainManagerRegistryTest.class);
	}

	/**
	 * Constructs a new Domain Manager Registry test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManagerRegistryTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Domain Manager Registry test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScaDomainManagerRegistry fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Domain Manager Registry test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDomainManagerRegistry getFixture() {
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
		final ResourceSet resourceSet = new ResourceSetImpl();
		final Resource resource = resourceSet.getResource(org.eclipse.emf.common.util.URI.createURI(ScaTestConstaints.SCA_DOMAINS_URI), true);
		final ScaDocumentRoot root = (ScaDocumentRoot) resource.getEObject("/");

		setFixture(root.getDomainManagerRegistry());
	}

	public void testLoadOldModel() {
		final ResourceSet resourceSet = new ResourceSetImpl();
		final Resource resource = resourceSet.getResource(
			org.eclipse.emf.common.util.URI.createURI("platform:/plugin/gov.redhawk.sca.model.tests/oldDomains.sca"), true);
		final ScaDocumentRoot root = (ScaDocumentRoot) resource.getEObject("/");
		Assert.assertNotNull("Domain manager registry is null", root.getDomainManagerRegistry());
		Assert.assertFalse(root.getDomainManagerRegistry().getDomains().isEmpty());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		getFixture().dispose();
		setFixture(null);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#getDomains() <em>Domains</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#getDomains()
	 * @generated NOT
	 */
	public void testGetDomains() {
		// END GENERATED CODE
		Assert.assertFalse(getFixture().getDomains().isEmpty());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#findDomain(java.lang.String) <em>Find
	 * Domain</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#findDomain(java.lang.String)
	 * @generated NOT
	 */
	public void testFindDomain__String() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().findDomain(ScaTestConstaints.DOMAIN_DISPLAY_NAME));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#dispose() <em>Dispose</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#dispose()
	 * @generated NOT
	 */
	public void testDispose() {
		// END GENERATED CODE
		// TODO
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManagerRegistry#createDomain(java.lang.String, boolean, java.util.Map)
	 * <em>Create Domain</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#createDomain(java.lang.String, boolean, java.util.Map)
	 * @generated NOT
	 */
	@Deprecated
	public void testCreateDomain__String_boolean_Map() {
		// END GENERATED CODE

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManagerRegistry#createDomain(java.lang.String, java.lang.String, boolean, java.util.Map)
	 * <em>Create Domain</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#createDomain(java.lang.String, java.lang.String, boolean,
	 * java.util.Map)
	 * @generated NOT
	 */
	public void testCreateDomain__String_String_boolean_Map() {
		// END GENERATED CODE

		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	/**
	 * IDE-2203 Ensure event channels aren't saved when saving the registry.
	 * @throws IOException
	 */
	public void testSaveNoEventChannels() throws IOException {
		// Add an event channel to the domain manager in the model
		ScaEventChannel eventChannel = ScaFactory.eINSTANCE.createScaEventChannel();
		eventChannel.setName("nosave");
		getFixture().getDomains().get(0).getEventChannels().add(eventChannel);

		// Save to XML and check for the event channel
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		getFixture().eResource().save(outputStream, null);
		String xml = outputStream.toString();
		Assert.assertFalse(xml.contains("nosave"));
	}

} // ScaDomainManagerRegistryTest
