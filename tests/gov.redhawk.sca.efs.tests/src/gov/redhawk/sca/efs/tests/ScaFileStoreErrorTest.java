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

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CF.ErrorNumberType;
import CF.FileException;
import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemOperations;
import CF.FileSystemPOATie;
import CF.InvalidFileName;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CFErrorFormatter.FileOperation;
import mil.jpeojtrs.sca.util.CFErrorFormatter.FileOperation2;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

/**
 * Simulates CF file system errors for the SCA EFS ({@link gov.redhawk.efs.sca.internal.ScaFileStore}) and then makes
 * sure that the exception which are thrown have appropriate error details.
 */
public class ScaFileStoreErrorTest {

	private static OrbSession session;
	private static FileSystemOperations fileSystemImpl;
	private static FileSystem fileSystemCorba;

	private IFileSystem fileSystem;
	private IFileStore rootFileStore;

	@BeforeClass
	public static void initOrb() throws Exception {
		session = OrbSession.createSession();
		fileSystemImpl = new ErrorFileSystem();
		fileSystemCorba = FileSystemHelper.narrow(session.getPOA().servant_to_reference(new FileSystemPOATie(fileSystemImpl)));
	}

	@AfterClass
	public static void shutdownOrb() throws Exception {
		fileSystemCorba = null;
		fileSystemImpl = null;
		session.dispose();
	}

	@Before
	public void setUp() throws Exception {
		this.fileSystem = EFS.getFileSystem(ScaFileSystemConstants.SCHEME);
		final URI corbaIOR = getCorbIorUri();
		this.rootFileStore = this.fileSystem.getStore(corbaIOR);
	}

	private URI getCorbIorUri() throws Exception {
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(ScaFileSystemConstants.QUERY_PARAM_FS, ScaFileStoreErrorTest.fileSystemCorba.toString());
		return new URI(ScaFileSystemConstants.SCHEME + "://?" + QueryParser.createQuery(queryParams));
	}

	@After
	public void tearDown() throws Exception {
		this.fileSystem = null;
		this.rootFileStore = null;
	}

	@Test
	public void childNames() {
		// Trigger list()
		childNames("R", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EISDIR, "RST"), FileOperation.List, "/R/*"));
		childNames("S", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EMFILE, "STU"), FileOperation.List, "/S/*"));
	}

	private void childNames(String path, String errorMsg) {
		try {
			this.rootFileStore.getChild(path).childNames(EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}

	/**
	 * This tests our implementation of {@link FileStore#copyFile(IFileInfo, IFileStore, int, IProgressMonitor)}.
	 */
	@Test
	public void copy() {
		// Trigger move()
		copy("CC", "DD", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EAGAIN, "CCDE"), FileOperation2.Copy, "/CC", "/DD"));
		copy("DD", "CC", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EBADF, "DDEF"), FileOperation2.Copy, "/DD", "/CC"));
	}

	private void copy(String path1, String path2, String errorMsg) {
		try {
			IFileStore dest = this.rootFileStore.getChild(path2);
			this.rootFileStore.getChild(path1).copy(dest, EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}

	@Test
	public void delete() {
		// Trigger exists()
		delete("G", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_ECANCELED, "GHI"), FileOperation.Exists, "/G"));

		// Trigger remove() for directory
		delete("A", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_E2BIG, "ABC"), FileOperation.Delete, "/A"));
		delete("B", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EACCES, "BCD"), FileOperation.Delete, "/B"));

		// Trigger remove() for file
		delete("AA", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_E2BIG, "AABC"), FileOperation.Delete, "/AA"));
		delete("BB", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EACCES, "BBCD"), FileOperation.Delete, "/BB"));

		// Trigger rmdir() for directory
		delete("P", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EINVAL, "PQR"), FileOperation.Delete, "/P"));
		delete("Q", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EIO, "QRS"), FileOperation.Delete, "/Q"));
	}

	private void delete(String path, String errorMsg) {
		try {
			this.rootFileStore.getChild(path).delete(EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}

	@Test
	public void fetchInfo() {
		// Trigger list()
		fetchInfo("H", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_ECHILD, "HIJ"), FileOperation.List, "/H"));
		fetchInfo("I", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EDEADLK, "IJK"), FileOperation.List, "/I"));
	}

	private void fetchInfo(String path, String errorMsg) {
		try {
			this.rootFileStore.getChild(path).fetchInfo(EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertTrue(e.getStatus().getException().getMessage().contains(errorMsg));
			return;
		}
		Assert.fail("No error message received");
	}

	@Test
	public void mkdir() {
		// Trigger mkdir()
		mkdir("N", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EINPROGRESS, "NOP"), FileOperation.Mkdir, "/N"));
		mkdir("O", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EINTR, "OPQ"), FileOperation.Mkdir, "/O"));
	}

	private void mkdir(String path, String errorMsg) {
		try {
			this.rootFileStore.getChild(path).mkdir(EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}

	@Test
	public void move() {
		// Trigger move()
		move("E", "F", CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EBADMSG, "EFG"), FileOperation2.Move, "/E", "/F"));
		move("F", "E", CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EBUSY, "FGH"), FileOperation2.Move, "/F", "/E"));
	}

	private void move(String path1, String path2, String errorMsg) {
		try {
			IFileStore dest = this.rootFileStore.getChild(path2);
			this.rootFileStore.getChild(path1).move(dest, EFS.NONE, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}

	@Test
	public void openOutputStream() {
		// Trigger create()
		openOutputStream("JJ", EFS.NONE, CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EDOM, "JJKL"), FileOperation.Create, "/JJ"));
		openOutputStream("KK", EFS.NONE, CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EEXIST, "KKLM"), FileOperation.Create, "/KK"));

		// Trigger remove() -> FileException
		openOutputStream("AA", EFS.NONE, CFErrorFormatter.format(new FileException(ErrorNumberType.CF_E2BIG, "AABC"), FileOperation.Delete, "/AA"));
		openOutputStream("BB", EFS.NONE, CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EACCES, "BBCD"), FileOperation.Delete, "/BB"));

		// Trigger open() -> FileException
		openOutputStream("LL", EFS.APPEND, CFErrorFormatter.format(new FileException(ErrorNumberType.CF_EFAULT, "LLMN"), FileOperation.Open, "/LL"));
		openOutputStream("MM", EFS.APPEND, CFErrorFormatter.format(new InvalidFileName(ErrorNumberType.CF_EFBIG, "MMNO"), FileOperation.Open, "/MM"));
	}

	private void openOutputStream(String path, int options, String errorMsg) {
		try {
			this.rootFileStore.getChild(path).openOutputStream(options, null);
		} catch (CoreException e) {
			Assert.assertEquals(errorMsg, e.getMessage());
			return;
		}
		Assert.fail("No error message received");
	}
}
