/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.efs.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import gov.redhawk.efs.sca.internal.ScaFileOutputStream;
import gov.redhawk.sca.efs.server.tests.TestServer;

public class ScaFileOutputStreamTest {

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

	private File tempFile;
	private CF.File file;
	private OutputStream outputStream;

	@Before
	public void setUp() throws Exception {
		this.tempFile = new File(ScaFileOutputStreamTest.session.getRootFile(), "/dev/devices/GPP/testFile.xml");
		FileUtils.deleteQuietly(this.tempFile);

		this.file = ScaFileOutputStreamTest.session.getFs().create("/dev/devices/GPP/testFile.xml");
		this.outputStream = new ScaFileOutputStream(file, false);
	}

	@After
	public void tearDown() throws Exception {
		this.outputStream.close();
		this.file.close();
		FileUtils.deleteQuietly(this.tempFile);
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
				if (input != null) {
					input.close();
				}
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
		try {
			this.outputStream.write(51); // SUPPRESS CHECKSTYLE MagicNumber
			Assert.fail("Output stream should be closed");
		} catch (IOException e) {
			// PASS
		}
		try {
			byte[] tmpArray = new byte[] { 1 };
			this.outputStream.write(tmpArray);
			Assert.fail("Output stream should be closed");
		} catch (IOException e) {
			// PASS
		}
		try {
			byte[] tmpArray = new byte[] { 1 };
			this.outputStream.write(tmpArray, 0, 1);
			Assert.fail("Output stream should be closed");
		} catch (IOException e) {
			// PASS
		}
	}

}
