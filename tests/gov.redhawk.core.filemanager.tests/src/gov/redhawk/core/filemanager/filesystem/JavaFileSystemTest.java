package gov.redhawk.core.filemanager.filesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;

import CF.DataType;
import CF.ErrorNumberType;
import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import CF.FileSystemPackage.UnknownFileSystemProperties;
import gov.redhawk.sca.util.OrbSession;

/**
 * These tests assume that the {@link JavaFileSystem} is used on a Linux system.
 */
public class JavaFileSystemTest {

	private OrbSession session;
	private JavaFileSystem fileSystem;

	@Before
	public void before() throws CoreException {
		// Enforce the assumption that we're running on Linux. Necessary for many of the file system checks.
		Assume.assumeTrue(Platform.OS_LINUX.equals(Platform.getOS()));

		this.session = OrbSession.createSession();
		this.fileSystem = new JavaFileSystem(session.getOrb(), session.getPOA(), new File("/"));
	}

	@After
	public void after() {
		if (this.fileSystem != null) {
			this.fileSystem.dispose();
			this.fileSystem = null;
		}
		if (this.session != null) {
			this.session.dispose();
			this.session = null;
		}
	}

	protected FileSystemOperations getFileSystem() {
		return fileSystem;
	}

	@Test(expected = InvalidFileName.class)
	public void copy_null1() throws InvalidFileName, FileException {
		getFileSystem().copy(null, "/var");
	}

	@Test(expected = InvalidFileName.class)
	public void copy_null2() throws InvalidFileName, FileException {
		this.fileSystem.copy("/var", null);
	}

	@Test(expected = InvalidFileName.class)
	public void copy_empty1() throws InvalidFileName, FileException {
		getFileSystem().copy("   ", "/var");
	}

	@Test(expected = InvalidFileName.class)
	public void copy_empty2() throws InvalidFileName, FileException {
		getFileSystem().copy("/var", "   ");
	}

	@Test(expected = InvalidFileName.class)
	public void copy_relativePath1() throws InvalidFileName, FileException {
		getFileSystem().copy("relative/path", "/absolute/path");
	}

	@Test(expected = InvalidFileName.class)
	public void copy_relativePath2() throws InvalidFileName, FileException {
		getFileSystem().copy("/absolute/path", "relative/path");
	}

	@Test
	public void copy_directory() throws InvalidFileName, FileException, IOException {
		Path pathDir1 = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Path pathDir2 = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Files.delete(pathDir2);

		try {
			getFileSystem().copy(pathDir1.toString(), pathDir2.toString());
		} catch (FileException e) {
			Assert.assertEquals("Expected EISDIR from attempt to copy a directory", ErrorNumberType.CF_EISDIR, e.errorNumber);
			return;
		} finally {
			Files.deleteIfExists(pathDir1);
			Files.deleteIfExists(pathDir2);
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void copy_nonexistent() throws InvalidFileName, FileException {
		try {
			getFileSystem().copy("/nonexistent", "/tmp/newfile");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOENT from attempt to copy a non-existent file", ErrorNumberType.CF_ENOENT, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void copy_accessDenied() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().copy("/root/noaccess", "/tmp/newfile");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to copy a file without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void copy_validFileOverFile() throws InvalidFileName, FileException, IOException {
		// Create two files (one will replace the other)
		Path pathFile1 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Path pathFile2 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");

		try {
			getFileSystem().copy(pathFile1.toString(), pathFile2.toString());
			Assert.assertTrue(Files.exists(pathFile1));
			Assert.assertTrue(Files.exists(pathFile2));
		} finally {
			Files.deleteIfExists(pathFile1);
			Files.deleteIfExists(pathFile2);
		}
	}

	@Test
	public void copy_valid() throws InvalidFileName, FileException, IOException {
		// Create a file we can copy
		Path pathFile1 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Path pathFile2 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Files.delete(pathFile2);

		try {
			getFileSystem().copy(pathFile1.toString(), pathFile2.toString());
			Assert.assertTrue(Files.exists(pathFile1));
			Assert.assertTrue(Files.exists(pathFile2));
		} finally {
			Files.deleteIfExists(pathFile1);
			Files.deleteIfExists(pathFile2);
		}
	}

	@Test(expected = InvalidFileName.class)
	public void move_null1() throws InvalidFileName, FileException {
		getFileSystem().move(null, "/var");
	}

	@Test(expected = InvalidFileName.class)
	public void move_null2() throws InvalidFileName, FileException {
		getFileSystem().move("/var", null);
	}

	@Test(expected = InvalidFileName.class)
	public void move_empty1() throws InvalidFileName, FileException {
		getFileSystem().move("   ", "/var");
	}

	@Test(expected = InvalidFileName.class)
	public void move_empty2() throws InvalidFileName, FileException {
		getFileSystem().move("/var", "   ");
	}

	@Test(expected = InvalidFileName.class)
	public void move_relativePath1() throws InvalidFileName, FileException {
		getFileSystem().move("relative/path", "/absolute/path");
	}

	@Test(expected = InvalidFileName.class)
	public void move_relativePath2() throws InvalidFileName, FileException {
		getFileSystem().move("/absolute/path", "relative/path");
	}

	@Test
	public void move_nonexistent() throws InvalidFileName, FileException {
		try {
			getFileSystem().move("/nonexistent", "/tmp");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOENT from attempt to move a non-existent file/dir", ErrorNumberType.CF_ENOENT, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void move_accessDenied() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().move("/root", "/newroot");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to move a file/dir without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void move_targetDirNotEmpty() throws InvalidFileName, FileException, IOException {
		// Create a directory and a non-empty directory
		Path pathDir1 = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Path pathDir2 = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Path pathFile = Files.createTempFile(pathDir2, JavaFileSystemTest.class.getSimpleName(), ".tmp");

		try {
			getFileSystem().move(pathDir1.toString(), pathDir2.toString());
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOTEMPTY from attempt to move a dir over a non-empty dir", ErrorNumberType.CF_ENOTEMPTY, e.errorNumber);
			return;
		} finally {
			Files.deleteIfExists(pathFile);
			Files.deleteIfExists(pathDir2);
			Files.deleteIfExists(pathDir1);
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void move_validFileOverFile() throws InvalidFileName, FileException, IOException {
		// Create two files (one will replace the other)
		Path pathFile1 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Path pathFile2 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");

		try {
			getFileSystem().move(pathFile1.toString(), pathFile2.toString());
			Assert.assertFalse(Files.exists(pathFile1));
			Assert.assertTrue(Files.exists(pathFile2));
		} finally {
			Files.deleteIfExists(pathFile1);
			Files.deleteIfExists(pathFile2);
		}
	}

	@Test
	public void move_rename() throws InvalidFileName, FileException, IOException {
		// Create a file that we'll rename
		Path pathFile1 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Path pathFile2 = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Files.delete(pathFile2);

		try {
			getFileSystem().move(pathFile1.toString(), pathFile2.toString());
			Assert.assertFalse(Files.exists(pathFile1));
			Assert.assertTrue(Files.exists(pathFile2));
		} finally {
			Files.deleteIfExists(pathFile1);
			Files.deleteIfExists(pathFile2);
		}
	}

	@Test(expected = InvalidFileName.class)
	public void create_null() throws InvalidFileName, FileException {
		getFileSystem().create(null);
	}

	@Test(expected = InvalidFileName.class)
	public void create_empty() throws InvalidFileName, FileException {
		getFileSystem().create("   ");
	}

	@Test(expected = InvalidFileName.class)
	public void create_relativePath() throws InvalidFileName, FileException {
		getFileSystem().create("relative/path");
	}

	@Test
	public void create_alreadyExists() throws InvalidFileName, FileException {
		try {
			getFileSystem().create("/bin/echo");
		} catch (FileException e) {
			Assert.assertEquals("Expected EEXIST from attempt to create a file where the target already exists", ErrorNumberType.CF_EEXIST, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void create_accessDenied() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().create("/root/noaccess");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCES from attempt to create a file without appropriate permissions for the parent directory",
				ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void create_valid() throws InvalidFileName, FileException, IOException {
		// Get a temporary file path
		Path path = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Files.delete(path);
		Assert.assertFalse(Files.exists(path));

		CF.File file = null;
		try {
			file = getFileSystem().create(path.toString());
			Assert.assertTrue(Files.exists(path));
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (FileException e) {
					// PASS
				}
			}
			Files.deleteIfExists(path);
		}
	}

	@Test(expected = InvalidFileName.class)
	public void exists_null() throws InvalidFileName {
		getFileSystem().exists(null);
	}

	@Test(expected = InvalidFileName.class)
	public void exists_empty() throws InvalidFileName {
		getFileSystem().exists("   ");
	}

	@Test(expected = InvalidFileName.class)
	public void exists_relativePath() throws InvalidFileName {
		getFileSystem().exists("relative/path");
	}

	@Test
	public void exists_root() throws InvalidFileName {
		Assert.assertTrue(getFileSystem().exists("/"));
	}

	@Test
	public void exists_file() throws InvalidFileName {
		Assert.assertTrue(getFileSystem().exists("/bin/echo"));
	}

	@Test
	public void exists_directory() throws InvalidFileName {
		Assert.assertTrue(getFileSystem().exists("/var"));
	}

	@Test
	public void exists_nonexistent() throws InvalidFileName {
		Assert.assertFalse(getFileSystem().exists("/nonexistent"));
	}

	@Test
	public void exists_accessDenied() throws InvalidFileName {
		assumeNonRootUser();

		Assert.assertFalse(getFileSystem().exists("/root/noaccess"));
	}

	@Test(expected = InvalidFileName.class)
	public void list_null() throws FileException, InvalidFileName {
		getFileSystem().list(null);
	}

	/**
	 * Listing an empty string should return info for the root
	 */
	@Test
	public void list_empty() throws FileException, InvalidFileName {
		// We expect the root directory's info
		FileInformationType[] info = getFileSystem().list("   ");
		Assert.assertNotNull(info);
		Assert.assertEquals(1, info.length);
		assertFileInformation(info[0], "/", FileType.DIRECTORY, !isRoot(), true);
	}

	@Test(expected = InvalidFileName.class)
	public void list_relativePath() throws FileException, InvalidFileName {
		getFileSystem().list("relative/path");
	}

	/**
	 * Sanity check that listing the root directory ( / ) seems okay
	 */
	@Test
	public void list_root() throws FileException, InvalidFileName {
		FileInformationType[] infos = getFileSystem().list("/");
		Assert.assertNotNull(infos);

		boolean foundBin = false;
		boolean foundVar = false;
		for (FileInformationType info : infos) {
			if ("bin".equals(info.name)) {
				foundBin = true;
			} else if ("var".equals(info.name)) {
				foundVar = true;
			}
		}

		Assert.assertTrue("Didn't find bin directory", foundBin);
		Assert.assertTrue("Didn't find var directory", foundVar);
	}

	/**
	 * Sanity check that listing the root directory using wildcards seems okay
	 */
	@Test
	public void list_rootWithWildcard() throws FileException, InvalidFileName {
		FileInformationType[] infos = getFileSystem().list("/v*r");
		Assert.assertNotNull(infos);
		Assert.assertEquals(1, infos.length);
		assertFileInformation(infos[0], "var", FileType.DIRECTORY, !isRoot(), true);

		infos = getFileSystem().list("/v?r");
		Assert.assertNotNull(infos);
		Assert.assertEquals(1, infos.length);
		assertFileInformation(infos[0], "var", FileType.DIRECTORY, !isRoot(), true);
	}

	@Test
	public void list_directory() throws FileException, InvalidFileName {
		FileInformationType[] infos = getFileSystem().list("/tmp");
		Assert.assertNotNull(infos);
		Assert.assertEquals(1, infos.length);
		assertFileInformation(infos[0], "tmp", FileType.DIRECTORY, false, true);
	}

	@Test
	public void list_file() throws FileException, InvalidFileName {
		FileInformationType[] infos = getFileSystem().list("/bin/echo");
		Assert.assertNotNull(infos);
		Assert.assertEquals(1, infos.length);
		assertFileInformation(infos[0], "echo", FileType.PLAIN, !isRoot(), true);
	}

	@Test
	public void list_accessDenied() throws FileException, InvalidFileName {
		assumeNonRootUser();

		try {
			getFileSystem().list("/root/");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to read a directory without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test(expected = InvalidFileName.class)
	public void mkdir_null() throws InvalidFileName, FileException {
		getFileSystem().mkdir(null);
	}

	@Test(expected = InvalidFileName.class)
	public void mkdir_empty() throws InvalidFileName, FileException {
		getFileSystem().mkdir("   ");
	}

	@Test(expected = InvalidFileName.class)
	public void mkdir_relativePath() throws InvalidFileName, FileException {
		getFileSystem().mkdir("relative/path");
	}

	@Test
	public void mkdir_accessDenied() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().mkdir("/noaccess");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to mkdir without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void mkdir_valid() throws InvalidFileName, FileException, IOException {
		// Get a temporary directory path
		Path path = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Files.delete(path);
		Assert.assertFalse(Files.exists(path));

		try {
			getFileSystem().mkdir(path.toString());
			Assert.assertTrue(Files.exists(path));
		} finally {
			Files.deleteIfExists(path);
		}
	}

	@Test(expected = InvalidFileName.class)
	public void open_null() throws InvalidFileName, FileException {
		getFileSystem().open(null, true);
	}

	@Test(expected = InvalidFileName.class)
	public void open_empty() throws InvalidFileName, FileException {
		getFileSystem().open("   ", true);
	}

	@Test(expected = InvalidFileName.class)
	public void open_relativePath() throws InvalidFileName, FileException {
		getFileSystem().open("relative/path", true);
	}

	@Test
	public void open_fileRead() throws InvalidFileName, FileException, IOException {
		CF.File file = null;
		try {
			file = getFileSystem().open("/bin/echo", true);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (FileException e) {
					// PASS
				}
			}
		}

	}

	@Test
	public void open_fileReadWrite() throws InvalidFileName, FileException, IOException {
		Path path = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");

		CF.File file = null;
		try {
			file = getFileSystem().open(path.toString(), false);
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (FileException e) {
					// PASS
				}
			}
			Files.deleteIfExists(path);
		}

	}

	@Test
	public void open_directory() throws InvalidFileName, FileException, IOException {
		try {
			getFileSystem().open("/tmp", true);
		} catch (FileException e) {
			Assert.assertEquals("Expected EISDIR from attempt to copy a directory", ErrorNumberType.CF_EISDIR, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void open_nonexistent() throws InvalidFileName, FileException {
		try {
			getFileSystem().open("/nonexistent", true);
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOENT from attempt to open a non-existent file", ErrorNumberType.CF_ENOENT, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void open_accessDeniedDirectory() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().open("/root/noaccess", true);
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to open a file without appropriate permissions on the parent directory",
				ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void open_accessDeniedReadFile() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().open("/var/log/messages", true);
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to open a file for read without appropriate permissions", ErrorNumberType.CF_EACCES,
				e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void open_accessDeniedWriteFile() throws InvalidFileName, FileException {
		assumeNonRootUser();

		try {
			getFileSystem().open("/bin/echo", false);
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCESS from attempt to open a file for write without appropriate permissions", ErrorNumberType.CF_EACCES,
				e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void query() throws UnknownFileSystemProperties {
		PropertiesHolder ph = new PropertiesHolder(new DataType[0]);
		getFileSystem().query(ph);

		boolean hasSize = false, hasAvailableSpace = false;
		for (DataType dt : ph.value) {
			if ("SIZE".equals(dt.id)) {
				dt.value.extract_ulonglong();
				hasSize = true;
			} else if ("AVAILABLE_SPACE".equals(dt.id)) {
				dt.value.extract_ulonglong();
				hasAvailableSpace = true;
			}
		}
		Assert.assertTrue("Missing size property", hasSize);
		Assert.assertTrue("Missing avilable space property", hasAvailableSpace);
	}

	@Test(expected = InvalidFileName.class)
	public void remove_null() throws FileException, InvalidFileName {
		getFileSystem().remove(null);
	}

	@Test(expected = InvalidFileName.class)
	public void remove_empty() throws FileException, InvalidFileName {
		getFileSystem().remove("   ");
	}

	@Test(expected = InvalidFileName.class)
	public void remove_relativePath() throws FileException, InvalidFileName {
		getFileSystem().remove("relative/path");
	}

	@Test
	public void remove_file() throws FileException, InvalidFileName, IOException {
		Path path = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Assert.assertTrue(Files.exists(path));

		getFileSystem().remove(path.toString());
		Assert.assertFalse(Files.exists(path));
	}

	@Test
	public void remove_directory() throws FileException, InvalidFileName, IOException {
		Path path = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Assert.assertTrue(Files.exists(path));

		try {
			getFileSystem().remove(path.toString());
		} catch (FileException e) {
			Assert.assertEquals("Expected EISDIR from attempt to remove a directory", ErrorNumberType.CF_EISDIR, e.errorNumber);
			return;
		} finally {
			Files.deleteIfExists(path);
		}
		Assert.fail("Expected an FileException");
	}

	@Test
	public void remove_nonexistent() throws FileException, InvalidFileName {
		try {
			getFileSystem().remove("/nonexistent");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOENT from attempt to remove a non-existent file", ErrorNumberType.CF_ENOENT, e.errorNumber);
			return;
		}
		Assert.fail("Expected an FileException");
	}

	@Test
	public void remove_accessDenied() throws FileException, InvalidFileName {
		assumeNonRootUser();

		try {
			getFileSystem().remove("/bin/echo");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCES from attempt to remove a file without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected an FileException");
	}

	@Test(expected = InvalidFileName.class)
	public void rmdir_null() throws FileException, InvalidFileName {
		getFileSystem().rmdir(null);
	}

	@Test(expected = InvalidFileName.class)
	public void rmdir_empty() throws FileException, InvalidFileName {
		getFileSystem().rmdir("   ");
	}

	@Test(expected = InvalidFileName.class)
	public void rmdir_relativePath() throws FileException, InvalidFileName {
		getFileSystem().rmdir("relative/path");
	}

	@Test
	public void rmdir_file() throws FileException, InvalidFileName, IOException {
		Path path = Files.createTempFile(JavaFileSystemTest.class.getSimpleName(), ".tmp");
		Assert.assertTrue(Files.exists(path));

		try {
			getFileSystem().rmdir(path.toString());
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOTDIR from attempt to rmdir a file", ErrorNumberType.CF_ENOTDIR, e.errorNumber);
			return;
		} finally {
			Files.deleteIfExists(path);
		}
		Assert.fail("Expected an FileException");

	}

	@Test
	public void rmdir_directory() throws FileException, InvalidFileName, IOException {
		Path path = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Assert.assertTrue(Files.exists(path));

		getFileSystem().rmdir(path.toString());
		Assert.assertFalse(Files.exists(path));
	}

	@Test
	public void rmdir_nonexistent() throws FileException, InvalidFileName {
		try {
			getFileSystem().rmdir("/nonexistent");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOENT from attempt to rmdir a non-existent directory", ErrorNumberType.CF_ENOENT, e.errorNumber);
			return;
		}
		Assert.fail("Expected an FileException");
	}

	@Test
	public void rmdir_accessDenied() throws FileException, InvalidFileName {
		assumeNonRootUser();

		try {
			getFileSystem().rmdir("/root");
		} catch (FileException e) {
			Assert.assertEquals("Expected EACCES from attempt to rmdir a directory without appropriate permissions", ErrorNumberType.CF_EACCES, e.errorNumber);
			return;
		}
		Assert.fail("Expected an FileException");
	}

	@Test
	public void rmdir_notEmpty() throws FileException, InvalidFileName, IOException {
		Path pathDir = Files.createTempDirectory(JavaFileSystemTest.class.getSimpleName());
		Path pathFile = Files.createTempFile(pathDir, JavaFileSystemTest.class.getSimpleName(), ".tmp");

		try {
			getFileSystem().rmdir(pathDir.toString());
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOTEMPTY from attempt to rmdir a non-empty directory", ErrorNumberType.CF_ENOTEMPTY, e.errorNumber);
			return;
		} finally {
			Files.deleteIfExists(pathFile);
			Files.deleteIfExists(pathDir);
		}
		Assert.fail("Expected an FileException");

	}

	private void assertFileInformation(FileInformationType info, String name, FileType type, boolean readonly, boolean executable) {
		Assert.assertEquals(name, info.name);
		Assert.assertEquals(type, info.kind);

		boolean foundReadOnly = false;
		boolean foundExecutable = false;
		boolean foundLastAccessTime = false;
		boolean foundLastModifiedTime = false;
		boolean foundLastCreatedTime = false;
		for (DataType dt : info.fileProperties) {
			if ("READ_ONLY".equals(dt.id)) {
				foundReadOnly = true;
				Assert.assertEquals("READ_ONLY value was incorrect", readonly, dt.value.extract_boolean());
			} else if ("EXECUTABLE".equals(dt.id)) {
				foundExecutable = true;
				Assert.assertEquals("EXECUTABLE value was incorrect", executable, dt.value.extract_boolean());
			} else if ("LAST_ACCESS_TIME".equals(dt.id)) {
				foundLastAccessTime = true;
			} else if ("MODIFIED_TIME".equals(dt.id)) {
				foundLastModifiedTime = true;
			} else if ("CREATED_TIME".equals(dt.id)) {
				foundLastCreatedTime = true;
			}
		}

		Assert.assertTrue("Didn't find READ_ONLY", foundReadOnly);
		Assert.assertTrue("Didn't find EXECUTABLE", foundExecutable);
		Assert.assertTrue("Didn't find LAST_ACCESS_TIME", foundLastAccessTime);
		Assert.assertTrue("Didn't find MODIFIED_TIME", foundLastModifiedTime);
		Assert.assertTrue("Didn't find CREATED_TIME", foundLastCreatedTime);
	}

	/**
	 * Enforce the assumption that we're running as a non-root user. This is necessary for tests that need to
	 * encounter a permissions issue.
	 */
	private void assumeNonRootUser() {
		Assume.assumeFalse(isRoot());
	}

	private boolean isRoot() {
		return "root".equals(System.getProperty("user.name")) || new File("/root").canRead();
	}
}
