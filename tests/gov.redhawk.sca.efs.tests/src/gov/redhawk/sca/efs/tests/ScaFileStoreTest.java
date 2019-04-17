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

import gov.redhawk.sca.efs.server.tests.TestServer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScaFileStoreTest {

	private static TestServer session;

	@BeforeClass
	public static void initOrb() throws Exception {
		session = new TestServer();
		session.initOrb();
	}

	@AfterClass
	public static void shutdownOrb() throws Exception {
		session.shutdownOrb();
	}

	private IFileSystem fileSystem;
	private IFileStore rootFileStore;
	private File deleteFile;
	private InputStream inputStream;
	private OutputStream outputStream;

	@Before
	public void setUp() throws Exception {
		this.fileSystem = EFS.getFileSystem(ScaFileSystemConstants.SCHEME);
		final URI corbaIOR = getCorbIorUri();

		this.rootFileStore = this.fileSystem.getStore(corbaIOR);
	}

	private URI getCorbIorUri() throws Exception {
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ScaFileStoreTest.session.getFs().toString());
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}

	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
		this.rootFileStore = null;
		if (this.deleteFile != null && this.deleteFile.exists()) {
			if (!this.deleteFile.isDirectory()) {
				FileUtils.forceDelete(this.deleteFile);
			} else {
				FileUtils.deleteDirectory(this.deleteFile);
			}
		}
		if (this.inputStream != null) {
			this.inputStream.close();
			this.inputStream = null;
		}
		if (this.outputStream != null) {
			this.outputStream.close();
			this.outputStream = null;
		}
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#childNames(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testChildNamesIntIProgressMonitor() throws Exception {
		final String[] names = this.rootFileStore.childNames(0, null);
		final String[] targetNames = ScaFileStoreTest.session.getRootFile().list();
		Arrays.sort(names, 0, names.length);
		Arrays.sort(targetNames, 0, names.length);
		Assert.assertArrayEquals(targetNames, names);
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#fetchInfo(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	public void testFetchInfoIntIProgressMonitor() {
		final IFileInfo info = this.rootFileStore.fetchInfo();
		Assert.assertEquals(ScaFileStoreTest.session.getRootFile().lastModified(), info.getLastModified());
		Assert.assertEquals(ScaFileStoreTest.session.getRootFile().getName(), info.getName());
		Assert.assertEquals(ScaFileStoreTest.session.getRootFile().length(), info.getLength());
		Assert.assertEquals(ScaFileStoreTest.session.getRootFile().isDirectory(), info.isDirectory());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#getChild(java.lang.String)}.
	 */
	@Test
	public void testGetChildString() {
		final IFileStore devChild = this.rootFileStore.getChild("dev");
		final File devFile = new File(ScaFileStoreTest.session.getRootFile(), "dev");
		final IFileInfo info = devChild.fetchInfo();
		Assert.assertEquals(devFile.getName(), info.getName());
		Assert.assertEquals(devFile.lastModified(), info.getLastModified());
		Assert.assertEquals(devFile.length(), info.getLength());
		Assert.assertEquals(devFile.isDirectory(), info.isDirectory());

	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#getName()}.
	 */
	@Test
	public void testGetName() {
		Assert.assertEquals("/", this.rootFileStore.getName());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#getParent()}.
	 */
	@Test
	public void testGetParent() {
		final IFileStore parent = this.rootFileStore.getParent();
		Assert.assertNull(parent);
		final IFileStore devChild = this.rootFileStore.getChild("dev");
		final IFileStore devicesChild = devChild.getChild("devices");
		final IFileStore devicesParent = devicesChild.getParent();
		Assert.assertEquals(devChild.toURI(), devicesParent.toURI());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#openInputStream(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testOpenInputStreamIntIProgressMonitor() throws Exception {
		this.deleteFile = new File(ScaFileStoreTest.session.getRootFile(), "openInTest");
		FileUtils.touch(this.deleteFile);
		final IFileStore fileStore = this.rootFileStore.getFileStore(new Path("openInTest"));
		this.inputStream = fileStore.openInputStream(0, null);
		this.inputStream.close();
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#openOutputStream(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 */
	@Test
	public void testOpenOutputStreamIntIProgressMonitor() throws Exception {
		this.deleteFile = new File(ScaFileStoreTest.session.getRootFile(), "openOutTest");
		FileUtils.touch(this.deleteFile);
		final IFileStore fileStore = this.rootFileStore.getFileStore(new Path("openOutTest"));
		this.outputStream = fileStore.openOutputStream(EFS.APPEND, null);
		this.outputStream.close();
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#toURI()}.
	 */
	@Test
	public void testToURI() throws Exception {
		Assert.assertNotNull(this.rootFileStore.toURI());
		Assert.assertEquals(this.getCorbIorUri(), this.rootFileStore.toURI());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#childInfos(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testChildInfosIntIProgressMonitor() throws Exception {
		final IFileInfo[] childInfos = this.rootFileStore.childInfos(0, null);
		Assert.assertTrue(childInfos.length > 0);
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#delete(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeleteIntIProgressMonitor() throws Exception {
		this.deleteFile = new File(ScaFileStoreTest.session.getRootFile(), "deleteTest");
		FileUtils.touch(this.deleteFile);
		final IFileStore child = this.rootFileStore.getChild("deleteTest");
		child.delete(0, null);
		Assert.assertTrue(!this.deleteFile.exists());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#mkdir(int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testMkdirIntIProgressMonitor() throws Exception {
		this.deleteFile = new File(ScaFileStoreTest.session.getRootFile(), "mkdirTest");
		final IFileStore child = this.rootFileStore.getChild("mkdirTest");
		child.mkdir(0, null);
		Assert.assertTrue(this.deleteFile.exists());
		this.deleteFile.delete();
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileStore#putInfo(org.eclipse.core.filesystem.IFileInfo, int, org.eclipse.core.runtime.IProgressMonitor)}.
	 * @throws Exception 
	 */
	@Test
	public void testPutInfoIFileInfoIntIProgressMonitor() throws Exception {
		// TODO Not supported yet
	}

}
