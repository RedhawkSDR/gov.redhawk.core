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
package gov.redhawk.sca.efs.server.tests;

import gov.redhawk.efs.sca.server.internal.FileImpl;
import gov.redhawk.sca.efs.ScaFileSystemPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Assert;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;

import CF.FileException;
import CF.OctetSequenceHolder;
import CF.FilePackage.InvalidFilePointer;

public class FileImplTest {

	private static final File TEST_FILE;

	static {
		final Bundle bundle = Platform.getBundle("gov.redhawk.sca.efs.tests");
		File temp = null;
		try {
			temp = new File(FileLocator.toFileURL(FileLocator.find(bundle, new Path("testFiles/testFile.txt"), null)).toURI());
		} catch (final URISyntaxException e) {
			// PASS
		} catch (final IOException e) {
			// PASS
		}
		TEST_FILE = temp;
	}

	private FileImpl file;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.file = new FileImpl(FileImplTest.TEST_FILE, false);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.file.close();
		this.file = null;
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#close()}.
	 */
	@Test
	public void testClose() throws Exception {
		this.file.close();
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#fileName()}.
	 */
	@Test
	public void testFileName() {
		Assert.assertEquals("testFile.txt", this.file.fileName());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#filePointer()}.
	 */
	@Test
	public void testFilePointer() {
		final int value = this.file.filePointer();
		Assert.assertEquals(0, value);
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#read(mil.jpeojtrs.sca.cf.OctetSequenceHolder, int)}.
	 */
	@Test
	public void testRead() {
		final OctetSequenceHolder holder = new OctetSequenceHolder();
		try {
			this.file.read(holder, (int) FileImplTest.TEST_FILE.length());
		} catch (final CF.FilePackage.IOException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}

		Assert.assertEquals("Hello world", new String(holder.value));
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#setFilePointer(int)}.
	 */
	@Test
	public void testSetFilePointer() {
		final int value = this.file.filePointer();
		Assert.assertEquals(0, value);
		try {
			this.file.setFilePointer(2);
		} catch (final InvalidFilePointer e) {
			Assert.fail(e.getMessage());
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
		Assert.assertEquals(2, this.file.filePointer());
	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#sizeOf()}.
	 */
	@Test
	public void testSizeOf() {
		try {
			Assert.assertEquals(FileImplTest.TEST_FILE.length(), this.file.sizeOf());
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}

	}

	/**
	 * Test method for {@link gov.redhawk.efs.sca.server.internal.FileImpl#write(byte[])}.
	 */
	@Test
	public void testWrite() {
		try {
			File tempDir = ScaFileSystemPlugin.getDefault().getTempDirectory();
			final File tmpFile = File.createTempFile("cfTest", "testWrite", tempDir);
			tmpFile.deleteOnExit();
			this.file.close();
			this.file = new FileImpl(tmpFile, false);
			final String testString = "Test String";
			this.file.write(testString.getBytes());
			final String result = FileUtils.readFileToString(tmpFile);
			Assert.assertEquals(testString, result);
		} catch (final IOException e) {
			Assert.fail(e.getMessage());
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final CF.FilePackage.IOException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

}
