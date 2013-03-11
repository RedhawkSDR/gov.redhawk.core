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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.framework.Assert;
import junit.framework.TestCase;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDocumentRoot#getDomainManagerRegistry() <em>Domain Manager Registry</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaDocumentRootTest extends TestCase {
	/**
	 * The fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDocumentRoot fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDocumentRootTest.class);
	}

	/**
	 * Constructs a new Document Root test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDocumentRootTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScaDocumentRoot fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Document Root test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDocumentRoot getFixture() {
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
		setFixture((ScaDocumentRoot) resource.getEObject("/"));
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDocumentRoot#getDomainManagerRegistry() <em>Domain Manager Registry</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#getDomainManagerRegistry()
	 * @generated NOT
	 */
	public void testGetDomainManagerRegistry() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDomainManagerRegistry());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDocumentRoot#setDomainManagerRegistry(gov.redhawk.model.sca.ScaDomainManagerRegistry) <em>Domain Manager Registry</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#setDomainManagerRegistry(gov.redhawk.model.sca.ScaDomainManagerRegistry)
	 * @generated NOT
	 */
	public void testSetDomainManagerRegistry() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDomainManagerRegistry());
		getFixture().setDomainManagerRegistry(null);
		Assert.assertNull(getFixture().getDomainManagerRegistry());
		// END GENERATED CODE
	}

} //ScaDocumentRootTest
