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

import java.io.IOException;
import java.io.InputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.omg.CORBA.SystemException;

import CF.File;
import gov.redhawk.efs.sca.internal.ScaFileInputStream;
import gov.redhawk.sca.efs.server.tests.TestServer;

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

	private File file;
	private InputStream inputStream;

	@Before
	public void setUp() throws Exception {
		this.file = ScaFileInputStreamTest.session.getFs().open("/dev/devices/GPP/GPP.spd.xml", true);
		this.inputStream = new ScaFileInputStream(file);
	}

	@After
	public void tearDown() throws Exception {
		this.inputStream.close();
		try {
			this.file.close();
		} catch (SystemException e) {
			// PASS
		}
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
		try {
			this.inputStream.read();
			Assert.fail("Input stream should be closed");
		} catch (IOException e) {
			// PASS
		}
		try {
			byte[] tmpArray = new byte[1];
			this.inputStream.read(tmpArray);
			Assert.fail("Input stream should be closed");
		} catch (IOException e) {
			// PASS
		}
		try {
			byte[] tmpArray = new byte[1];
			this.inputStream.read(tmpArray, 0, 1);
			Assert.fail("Input stream should be closed");
		} catch (IOException e) {
			// PASS
		}
	}
}
