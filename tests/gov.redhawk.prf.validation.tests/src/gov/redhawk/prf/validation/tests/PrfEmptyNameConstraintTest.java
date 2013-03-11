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

public class PrfEmptyNameConstraintTest {

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
	public void testPropertyWithNames() throws Exception {
		this.prf = Properties.Util.getProperties(this.resourceSet.getResource(
		        PrfEmptyNameConstraintTest.getURI("testFiles/PrfNotEmptyNameConstraints.prf.xml"), true));

		Assert.assertNotNull(this.prf);

		for (final Simple simple : this.prf.getSimple()) {
			Assert.assertNotNull(simple);

			Assert.assertEquals("simple property", simple.getName());
		}

		for (final SimpleSequence simple : this.prf.getSimpleSequence()) {
			Assert.assertNotNull(simple);

			Assert.assertEquals("simple sequence", simple.getName());
		}
	}

	@Test
	public void testPropertyWithoutNames() throws Exception {
		this.prf = Properties.Util.getProperties(this.resourceSet.getResource(PrfEmptyNameConstraintTest.getURI("testFiles/PrfEmptyNameConstraints.prf.xml"),
		        true));

		Assert.assertNotNull(this.prf);

		for (final Simple simple : this.prf.getSimple()) {
			Assert.assertNotNull(simple);

			Assert.assertNull(simple.getName());
		}

		for (final SimpleSequence simple : this.prf.getSimpleSequence()) {
			Assert.assertNotNull(simple);

			Assert.assertNull(simple.getName());
		}
	}

	public static URI getURI(final String filePath) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.prf.validation.tests"), new Path(filePath), null));
		return URI.createURI(url.toString());
	}

}
