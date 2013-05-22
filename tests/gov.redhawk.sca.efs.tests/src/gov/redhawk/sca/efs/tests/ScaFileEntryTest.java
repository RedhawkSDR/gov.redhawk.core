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
package gov.redhawk.sca.efs.tests;

import gov.redhawk.efs.sca.internal.ScaFileEntry;

import java.net.URI;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */
public class ScaFileEntryTest {

	private ScaFileEntry entry;
	private URI entryUri;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.entryUri = URI.create("file:/testDir/testFile1.spd");
		this.entry = new ScaFileEntry(this.entryUri);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.entry = null;
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileEntry#getUri()}.
	 */
	@Test
	public void testGetUri() {
		Assert.assertEquals(this.entryUri, this.entry.getUri());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileEntry#getName()}.
	 */
	@Test
	public void testGetName() {
		final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(this.entryUri.toString());
		Assert.assertEquals(uri.lastSegment(), this.entry.getName());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileEntry#getAbsolutePath()}.
	 */
	@Test
	public void testGetAbsolutePath() {
		Assert.assertTrue(this.entry.getAbsolutePath().charAt(0) == '/');
		Assert.assertTrue(this.entry.getAbsolutePath().charAt(this.entry.getAbsolutePath().length() - 1) != '/');
		final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(this.entryUri.toString());
		Assert.assertEquals(uri.path(), this.entry.getAbsolutePath());
	}

}
