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

import gov.redhawk.efs.sca.internal.ScaFileCache.FileSystemCache;
import gov.redhawk.efs.sca.internal.ScaFileSystemPlugin;
import gov.redhawk.sca.efs.server.tests.TestServer;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */
public class ScaFileInputStreamTest {
	private static TestServer session;

	@BeforeClass
	public static void initOrb() throws Exception {
		ScaFileInputStreamTest.session = new TestServer();
		ScaFileInputStreamTest.session.initOrb();
	}

	@AfterClass
	public static void shutdownOrb() throws Exception {
		ScaFileInputStreamTest.session.shutdownOrb();
	}

	private IFileSystem fileSystem;
	private IFileStore rootFileStore;
	private InputStream inputStream;
	private IFileStore rootFileStoreFile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ScaFileSystemPlugin.getDefault().getFileCache().clear();
		this.fileSystem = EFS.getFileSystem(ScaFileSystemConstants.SCHEME);
		final URI corbaIOR = getCorbIorUri();

		this.rootFileStore = this.fileSystem.getStore(corbaIOR);
		this.rootFileStoreFile = this.rootFileStore.getFileStore(new Path("dev/devices/GPP/GPP.spd.xml"));
		this.inputStream = this.rootFileStoreFile.openInputStream(0, null);
	}

	private URI getCorbIorUri() throws Exception {
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ScaFileInputStreamTest.session.getFs().toString());
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
		this.rootFileStore = null;
		this.rootFileStoreFile = null;
		this.inputStream.close();
	}

	/**
	 * Test method to test the File cache works correctly
	 * @throws Exception 
	 */
	@Test
	public void testFileCache() throws Exception {
		final Map<String, FileSystemCache> fileSystemCache = ScaFileSystemPlugin.getDefault().getFileCache().getFileSystemCacheMap();
		Assert.assertEquals(1, fileSystemCache.size());
		final FileSystemCache fileCache = fileSystemCache.values().iterator().next();
		Assert.assertEquals(1, fileCache.getFileCacheMap().size());
		final URI corbaIOR = getCorbIorUri();
		final IFileStore localStore = this.fileSystem.getStore(corbaIOR);
		localStore.getFileStore(new Path("dev/devices/GPP/GPP.spd.xml"));
		Assert.assertEquals(1, fileSystemCache.size());
		Assert.assertEquals(1, fileCache.getFileCacheMap().size());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileInputStream#read()}.
	 */
	@Test
	public void testRead() throws Exception {
		this.inputStream.read();
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileInputStream#read(byte[], int, int)}.
	 */
	@Test
	public void testReadByteArrayIntInt() throws Exception {
		final byte[] buffer = new byte[1024]; // SUPPRESS CHECKSTYLE MagicNumber
		this.inputStream.read(buffer);
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileInputStream#close()}.
	 */
	@Test
	public void testClose() throws Exception {
		this.inputStream.close();
	}

}
