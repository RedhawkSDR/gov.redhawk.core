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

import gov.redhawk.sca.efs.server.tests.OrbSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */
public class ScaFileOutputStreamTest {

	private static OrbSession session;

	@BeforeClass
	public static void initOrb() throws Exception {
		ScaFileOutputStreamTest.session = new OrbSession();
		ScaFileOutputStreamTest.session.initOrb();

	}

	@AfterClass
	public static void shutdownOrb() throws Exception {
		ScaFileOutputStreamTest.session.shutdownOrb();
	}

	private IFileSystem fileSystem;
	private IFileStore rootFileStore;
	private IFileStore rootFileStoreFile;
	private OutputStream outputStream;
	private File tempFile;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.fileSystem = EFS.getFileSystem(ScaFileSystemConstants.SCHEME);
		final URI corbaIOR = getCorbIorUri();

		this.rootFileStore = this.fileSystem.getStore(corbaIOR);
		this.tempFile = new File(ScaFileOutputStreamTest.session.getRootFile(), "dev/devices/GPP/testFile.xml");
		this.rootFileStoreFile = this.rootFileStore.getFileStore(new Path("dev/devices/GPP/testFile.xml"));
		this.outputStream = this.rootFileStoreFile.openOutputStream(0, null);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
		this.rootFileStore = null;
		this.rootFileStoreFile = null;
		this.outputStream.close();
		FileUtils.forceDelete(this.tempFile);
	}

	private URI getCorbIorUri() throws Exception {
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ScaFileOutputStreamTest.session.getFs().toString());
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileOutputStream#write(int)}.
	 */
	@Test
	public void testWriteInt() throws Exception {
		this.outputStream.write(10); // SUPPRESS CHECKSTYLE MagicNumber
		FileInputStream input = null;
		try {
			input = new FileInputStream(this.tempFile);
			Assert.assertEquals(10, input.read()); // SUPPRESS CHECKSTYLE MagicNumber
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// PASS
			}
		}
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileOutputStream#write(byte[], int, int)}.
	 */
	@Test
	public void testWriteByteArrayIntInt() throws Exception {
		final byte[] data = new byte[] { 1, 2, 3, 4, 5 };
		this.outputStream.write(data);
		FileInputStream input = null;
		try {
			input = new FileInputStream(this.tempFile);
			final byte[] buffer = new byte[5]; // SUPPRESS CHECKSTYLE MagicNumber
			input.read(buffer);
			Assert.assertArrayEquals(data, buffer);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				// PASS
			}
		}
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.internal.ScaFileOutputStream#close()}.
	 */
	@Test
	public void testClose() throws Exception {
		this.outputStream.close();
	}

}
