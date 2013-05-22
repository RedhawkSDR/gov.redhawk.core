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
package gov.redhawk.prf.validation.tests;

import java.io.IOException;
import java.net.URL;

import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DuplicatePropertyNameConstraintTest {

	private ResourceSet resourceSet;
	private Properties prf;

	@Before
	public void setUp() throws Exception {
		final ResourceSet set = new ResourceSetImpl();
		this.resourceSet = set;
	}

	@After
	public void tearDown() throws Exception {
		this.resourceSet = null;
		this.prf = null;
	}

	@Test
	public void testDuplicateNames() throws Exception {
		this.prf = Properties.Util.getProperties(this.resourceSet.getResource(DuplicatePropertyNameConstraintTest
		        .getURI("testFiles/DuplicatePropertyName.prf.xml"), true));

		Assert.assertNotNull(this.prf);

		final Simple simple1 = this.prf.getSimple().get(0);
		final Simple simple2 = this.prf.getSimple().get(1);

		Assert.assertEquals(simple1.getName(), simple2.getName());

		final SimpleSequence ss1 = this.prf.getSimpleSequence().get(0);
		final SimpleSequence ss2 = this.prf.getSimpleSequence().get(1);

		Assert.assertEquals(ss1.getName(), ss2.getName());
	}

	@Test
	public void testNonDuplicateNames() throws Exception {
		this.prf = Properties.Util.getProperties(this.resourceSet.getResource(DuplicatePropertyNameConstraintTest
		        .getURI("testFiles/NotDuplicatePropertyName.prf.xml"), true));

		Assert.assertNotNull(this.prf);

		final Simple simple1 = this.prf.getSimple().get(0);
		final Simple simple2 = this.prf.getSimple().get(1);

		Assert.assertNotSame(simple1.getName(), simple2.getName());

		final SimpleSequence ss1 = this.prf.getSimpleSequence().get(0);
		final SimpleSequence ss2 = this.prf.getSimpleSequence().get(1);

		Assert.assertNotSame(ss1.getName(), ss2.getName());
	}

	public static URI getURI(final String filePath) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.prf.validation.tests"), new Path(filePath), null));
		return URI.createURI(url.toString());
	}

}
