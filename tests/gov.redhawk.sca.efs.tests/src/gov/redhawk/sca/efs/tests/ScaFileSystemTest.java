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
package gov.redhawk.sca.efs.tests;

import gov.redhawk.efs.sca.internal.ScaFileSystem;
import gov.redhawk.sca.efs.server.tests.OrbSession;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */
public class ScaFileSystemTest {

	private static OrbSession session;

	@BeforeClass
	public static void initOrb() throws Exception {
		ScaFileSystemTest.session = new OrbSession();
		ScaFileSystemTest.session.initOrb();

	}

	@AfterClass
	public static void shutdownOrb() throws Exception {
		ScaFileSystemTest.session.shutdownOrb();
	}

	private ScaFileSystem fileSystem;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.fileSystem = (ScaFileSystem) EFS.getFileSystem(ScaFileSystemConstants.SCHEME);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.internal.ScaFileSystem#canDelete()}.
	 */
	@Test
	public void testCanDelete() {
		Assert.assertTrue(this.fileSystem.canDelete());
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.internal.ScaFileSystem#canWrite()}.
	 */
	@Test
	public void testCanWrite() {
		Assert.assertTrue(this.fileSystem.canWrite());
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.internal.ScaFileSystem#getStore(java.net.URI)}
	 * .
	 * @throws Exception 
	 */
	//@Test
	public void testGetStoreURI_CORBA_NAME() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		Assert.fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetStoreURI_CORBA_IOR() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final URI corbaIOR = getCorbIorUri();

		final IFileStore corbaIORStore = this.fileSystem.getStore(corbaIOR);
		Assert.assertTrue(corbaIORStore.fetchInfo().exists());
	}

	private URI getCorbIorUri() throws Exception {
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ScaFileSystemTest.session.getFs().toString());
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}
}
