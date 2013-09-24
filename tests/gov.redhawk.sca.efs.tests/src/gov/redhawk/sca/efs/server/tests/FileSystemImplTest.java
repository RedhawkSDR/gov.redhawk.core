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

import gov.redhawk.efs.sca.server.internal.FileSystemImpl;
import gov.redhawk.sca.util.OrbSession;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.osgi.framework.Bundle;

import CF.DataType;
import CF.FileException;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

public class FileSystemImplTest {
	private static final String TEMP_FILE_NAME = "tempFile";
	private static final String TEMP_DIR_NAME = "tempDir";
	private static OrbSession session;

	private FileSystemImpl fileSystem;
	private static File root;

	@BeforeClass
	public static void onlyOnce() throws Exception {
		FileSystemImplTest.session = OrbSession.createSession();

		final Bundle bundle = Platform.getBundle("gov.redhawk.sca.efs.tests");
		FileSystemImplTest.root = null;
		try {
			FileSystemImplTest.root = new File(FileLocator.toFileURL(FileLocator.find(bundle, new Path("testFiles"), null)).toURI());
		} catch (final URISyntaxException e) {
			FileSystemImplTest.root = null;
		} catch (final IOException e) {
			FileSystemImplTest.root = null;
		}
	}
	
	@AfterClass
	public static void shutdown() throws Exception {
		if (session != null) {
			session.dispose();
			session = null;
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.fileSystem = new FileSystemImpl(FileSystemImplTest.root, session.getOrb(), session.getPOA());
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
		final File tempFile = new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_FILE_NAME);
		if (tempFile.exists()) {
			FileUtils.forceDelete(tempFile);
		}

		final File tempDir = new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_DIR_NAME);
		if (tempDir.exists()) {
			FileUtils.deleteDirectory(tempDir);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#copy(java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCopy() {
		try {
			this.fileSystem.copy("fail", FileSystemImplTest.TEMP_FILE_NAME);
			Assert.fail("Should raise file exception.");
		} catch (final InvalidFileName e) {
			Assert.fail("Should raise file exception.");
		} catch (final FileException e) {
			// PASS
		}

		try {
			this.fileSystem.copy("testFile.txt", "testFile.txt");
			Assert.fail("Should raise invalid file name.");
		} catch (final InvalidFileName e) {
			// PASS
		} catch (final FileException e) {
			Assert.fail("Should raise invalid file name.");
		}

		try {
			this.fileSystem.copy("testFile.txt", FileSystemImplTest.TEMP_FILE_NAME);
			Assert.assertTrue(new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_FILE_NAME).exists());
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#create(java.lang.String)}
	 * .
	 */
	@Test
	public void testCreate() {
		try {
			this.fileSystem.create("testFile.txt");
			Assert.fail("Did not throw FileException on file create of an already existing file.");
		} catch (final InvalidFileName e1) {
			Assert.fail("Did not throw FileException on file create of an already existing file.");
		} catch (final FileException e1) {
			// PASS
		}
		try {
			final CF.File file = this.fileSystem.create(FileSystemImplTest.TEMP_FILE_NAME);
			Assert.assertEquals(file.fileName(), FileSystemImplTest.TEMP_FILE_NAME);
			Assert.assertTrue(new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_FILE_NAME).exists());
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#exists(java.lang.String)}
	 * .
	 */
	@Test
	public void testExists() {
		try {
			Assert.assertFalse(this.fileSystem.exists("fail"));
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
		try {
			Assert.assertTrue(this.fileSystem.exists("testFile.txt"));
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#list(java.lang.String)}
	 * .
	 */
	@Test
	public void testList() {

		try {
			Assert.assertEquals(0, this.fileSystem.list("empty").length);

			FileInformationType[] result = this.fileSystem.list("*");
			Assert.assertEquals(FileSystemImplTest.root.list().length, result.length);
			for (final FileInformationType type : result) {
				final File subFile = new File(FileSystemImplTest.root, type.name);
				if (subFile.isDirectory()) {
					Assert.assertEquals(FileType.DIRECTORY, type.kind);
				} else {
					Assert.assertEquals(FileType.PLAIN, type.kind);
				}
			}

			result = this.fileSystem.list("subFolder/test*");
			final File subRoot = new File(FileSystemImplTest.root, "subFolder");
			Assert.assertEquals(subRoot.list(new FilenameFilter() {

				@Override
				public boolean accept(final File dir, final String name) {
					if (!dir.equals(subRoot)) {
						return false;
					}
					return FilenameUtils.wildcardMatch(name, "test*");
				}
			}).length, result.length);
			for (final FileInformationType type : result) {
				final File subFile = new File(subRoot, type.name);
				if (subFile.isDirectory()) {
					Assert.assertEquals(FileType.DIRECTORY, type.kind);
				} else {
					Assert.assertEquals(FileType.PLAIN, type.kind);
				}
			}

			result = this.fileSystem.list("testFile.t??");
			Assert.assertEquals(1, result.length);
			Assert.assertEquals(FileType.PLAIN, result[0].kind);
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#mkdir(java.lang.String)}
	 * .
	 */
	@Test
	public void testMkdir() {
		try {
			this.fileSystem.mkdir(FileSystemImplTest.TEMP_DIR_NAME);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#open(java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testOpen() throws IOException {
		try {
			this.fileSystem.open("badFile", false);
			Assert.fail("Should raise File Exception");
		} catch (final InvalidFileName e) {
			Assert.fail("Should raise File Exception");
		} catch (final FileException e) {
			// PASS
		}
		FileUtils.touch(new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_FILE_NAME));

		CF.File readOnlyFile;
		try {
			readOnlyFile = this.fileSystem.open(FileSystemImplTest.TEMP_FILE_NAME, true);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
			// Will not get here
			return;
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
			// Will not get here
			return;
		}

		try {
			readOnlyFile.write(new byte[10]); // SUPPRESS CHECKSTYLE MagicNumber
			Assert.fail("Should have IOException for writing to a read only file.");
		} catch (final CF.FilePackage.IOException e) {
			// PASS
		} finally {
			try {
				readOnlyFile.close();
			} catch (final FileException e) {
				Assert.fail("Should have IOException for writing to a read only file.");
			}
		}

		CF.File readWriteFile = null;
		try {
			readWriteFile = this.fileSystem.open(FileSystemImplTest.TEMP_FILE_NAME, false);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} finally {
			try {
				if (readWriteFile != null) {
					readWriteFile.close();
				}
			} catch (final FileException e) {
				Assert.fail(e.getMessage() + ": " + e.msg);
			}
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#query(mil.jpeojtrs.sca.cf.PropertiesHolder)}
	 * .
	 */
	@Test
	public void testQuery() {
		DataType[] props = new DataType[] { new DataType("SIZE", null), new DataType("AVAILABLE SPACE", null) };
		PropertiesHolder fileSystemProperties = new PropertiesHolder(props);
		try {
			this.fileSystem.query(fileSystemProperties);
		} catch (final UnknownFileSystemProperties e) {
			Assert.fail(e.getMessage() + ": " + Arrays.toString(e.invalidProperties));
		}

		props = new DataType[] { new DataType("BAD", null) };
		fileSystemProperties = new PropertiesHolder(props);
		try {
			this.fileSystem.query(fileSystemProperties);
			Assert.fail("Should raise a UnknownFileSystemProperties Exception.");
		} catch (final UnknownFileSystemProperties e) {
			// PASS
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#remove(java.lang.String)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testRemove() throws IOException {
		final File file = new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_FILE_NAME);
		FileUtils.touch(file);

		Assert.assertTrue(file.exists());

		try {
			this.fileSystem.remove(FileSystemImplTest.TEMP_FILE_NAME);
			Assert.assertFalse(file.exists());
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

	/**
	 * Test method for
	 * {@link gov.redhawk.efs.sca.server.internal.FileSystemImpl#rmdir(java.lang.String)}
	 * .
	 */
	@Test
	public void testRmdir() {
		final File file = new File(FileSystemImplTest.root, FileSystemImplTest.TEMP_DIR_NAME);
		file.mkdir();

		Assert.assertTrue(file.exists());

		try {
			this.fileSystem.rmdir(FileSystemImplTest.TEMP_DIR_NAME);
			Assert.assertFalse(file.exists());
		} catch (final FileException e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		} catch (final InvalidFileName e) {
			Assert.fail(e.getMessage() + ": " + e.msg);
		}
	}

}
