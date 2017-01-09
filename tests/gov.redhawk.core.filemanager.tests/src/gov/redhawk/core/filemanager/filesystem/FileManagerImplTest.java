package gov.redhawk.core.filemanager.filesystem;

import org.eclipse.core.runtime.CoreException;
import org.junit.Assert;
import org.junit.Test;

import CF.ErrorNumberType;
import CF.FileException;
import CF.FileManagerOperations;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.filemanager.filesystem.util.NoOpFileSystem;

/**
 * Because the {@link FileManagerImpl} is backed by the {@link JavaFileSystem}, we should be able to exercise the same
 * tests on it (they should pass-through to the the {@link JavaFileSystem}).
 * <p/>
 * We also test to make sure that commands make it through appropriately to the mounted file system.
 */
public class FileManagerImplTest extends JavaFileSystemTest {

	private FileManagerImpl fileManager;

	@Override
	public void before() throws CoreException {
		super.before();
		this.fileManager = new FileManagerImpl();
		this.fileManager.setLocalFileSystem(super.getFileSystem());
	}

	@Override
	public void after() {
		this.fileManager = null;
		super.after();
	}

	@Override
	protected FileSystemOperations getFileSystem() {
		return fileManager;
	}

	protected FileManagerOperations getFileManager() {
		return fileManager;
	}

	// Test proxying FileSystem calls through to a mount point

	@Test
	public void remove_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().remove("/mountpoint/removeme");
		Assert.assertEquals("/removeme", fs.getRemoved());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().remove("/mountpoint2/level2/removeme2");
		Assert.assertEquals("/removeme2", fs.getRemoved());
	}

	@Test
	public void copy_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		try {
			getFileManager().copy("/mountpoint/copy1", "/mountpoint/copy2");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOTSUP from attempt to copy a file using mounted file systems", ErrorNumberType.CF_ENOTSUP, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	@Test
	public void exists_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().exists("/mountpoint/checkexists");
		Assert.assertEquals("/checkexists", fs.getExists());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().exists("/mountpoint2/level2/checkexists2");
		Assert.assertEquals("/checkexists2", fs.getExists());
	}

	@Test
	public void list_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().list("/mountpoint/listme");
		Assert.assertEquals("/listme", fs.getListed());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().list("/mountpoint2/level2/listme2");
		Assert.assertEquals("/listme2", fs.getListed());
	}

	@Test
	public void create_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().create("/mountpoint/createme");
		Assert.assertEquals("/createme", fs.getCreated());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().create("/mountpoint2/level2/createme2");
		Assert.assertEquals("/createme2", fs.getCreated());
	}

	@Test
	public void open_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().open("/mountpoint/openme", true);
		Assert.assertEquals("/openme as read-only", fs.getOpened());
		getFileManager().open("/mountpoint/openme", false);
		Assert.assertEquals("/openme as read-write", fs.getOpened());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().open("/mountpoint2/level2/openme2", true);
		Assert.assertEquals("/openme2 as read-only", fs.getOpened());
		getFileManager().open("/mountpoint2/level2/openme2", false);
		Assert.assertEquals("/openme2 as read-write", fs.getOpened());
	}

	@Test
	public void mkdir_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().mkdir("/mountpoint/newdir");
		Assert.assertEquals("/newdir", fs.getMkdired());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().mkdir("/mountpoint2/level2/newdir2");
		Assert.assertEquals("/newdir2", fs.getMkdired());
	}

	@Test
	public void rmdir_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		getFileManager().rmdir("/mountpoint/rmdirme");
		Assert.assertEquals("/rmdirme", fs.getRmdired());

		getFileManager().mount("/mountpoint2/level2", fs);
		getFileManager().rmdir("/mountpoint2/level2/rmdirme2");
		Assert.assertEquals("/rmdirme2", fs.getRmdired());
	}

	@Test
	public void move_fromMount() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, FileException {
		NoOpFileSystem fs = new NoOpFileSystem();
		getFileManager().mount("/mountpoint", fs);
		try {
			getFileManager().move("/mountpoint/move1", "/mountpoint/move22");
		} catch (FileException e) {
			Assert.assertEquals("Expected ENOTSUP from attempt to move a file using mounted file systems", ErrorNumberType.CF_ENOTSUP, e.errorNumber);
			return;
		}
		Assert.fail("Expected a FileException");
	}

	// mount tests

	@Test(expected = InvalidFileName.class)
	public void mount_null() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		getFileManager().mount(null, new NoOpFileSystem());
	}

	@Test(expected = InvalidFileName.class)
	public void mount_empty() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		getFileManager().mount("   ", new NoOpFileSystem());
	}

	@Test(expected = InvalidFileName.class)
	public void mount_relative() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		getFileManager().mount("relative/path", new NoOpFileSystem());
	}

	@Test(expected = InvalidFileName.class)
	public void mount_root() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		getFileManager().mount("/", new NoOpFileSystem());
	}

	@Test(expected = InvalidFileSystem.class)
	public void mount_noFileSystem() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		getFileManager().mount("/mountpoint", null);
	}

	// unmount tests

	@Test(expected = NonExistentMount.class)
	public void unmount_null() throws NonExistentMount {
		getFileManager().unmount(null);
	}

	@Test(expected = NonExistentMount.class)
	public void unmount_empty() throws NonExistentMount {
		getFileManager().unmount("   ");
	}

	@Test(expected = NonExistentMount.class)
	public void unmount_relative() throws NonExistentMount {
		getFileManager().unmount("relative/path");
	}

	@Test(expected = NonExistentMount.class)
	public void unmount_root() throws NonExistentMount {
		getFileManager().unmount("/");
	}

	@Test(expected = NonExistentMount.class)
	public void unmount_nonexistent() throws NonExistentMount {
		getFileManager().unmount("/nonexistent");
	}

	// mount + unmount + getMounts

	@Test
	public void mount_unmount_getMounts_oneLevel() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, NonExistentMount, FileException {
		MountType[] mounts = getFileManager().getMounts();
		Assert.assertEquals(0, mounts.length);

		// After mounting, getMounts() should show the mount
		getFileManager().mount("/mountpoint", new NoOpFileSystem());
		mounts = getFileManager().getMounts();
		Assert.assertEquals(1, mounts.length);
		Assert.assertEquals("/mountpoint", mounts[0].mountPoint);
		Assert.assertNotNull(mounts[0].fs);

		// The root listing should have the mount plus other stuff
		FileInformationType[] infos = getFileManager().list("/");
		Assert.assertTrue(infos.length > 1);
		boolean found = false;
		for (FileInformationType info : infos) {
			if ("mountpoint".equals(info.name)) {
				Assert.assertEquals(FileType.FILE_SYSTEM, info.kind);
				found = true;
				break;
			}
		}
		Assert.assertTrue("Did not find mountpoint in root directory listing", found);

		// After ummounting, getMounts() should no longer show the mount
		getFileManager().unmount("/mountpoint");
		mounts = getFileManager().getMounts();
		Assert.assertEquals(0, getFileManager().getMounts().length);

		// The root listing should no longer include the mount
		infos = getFileManager().list("/");
		for (FileInformationType info : infos) {
			if ("mountpoint".equals(info.name)) {
				Assert.fail("mountpoint should no longer be in the root directory listing");
			}
		}
	}

	@Test
	public void mount_unmount_getMounts_twoLevels() throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, NonExistentMount, FileException {
		// Mount two levels deep; getMounts() should show the mount
		getFileManager().mount("/mountpoint2/level2", new NoOpFileSystem());
		MountType[] mounts = getFileManager().getMounts();
		Assert.assertEquals(1, mounts.length);
		Assert.assertEquals("/mountpoint2/level2", mounts[0].mountPoint);
		Assert.assertNotNull(mounts[0].fs);

		// The root listing should show a directory for the mount, and that should contain the mount
		FileInformationType[] infos = getFileManager().list("/");
		Assert.assertTrue(infos.length > 1);
		boolean found = false;
		for (FileInformationType info : infos) {
			if ("mountpoint2".equals(info.name)) {
				Assert.assertEquals(FileType.DIRECTORY, info.kind);
				found = true;
				break;
			}
		}
		Assert.assertTrue("Did not find mountpoint2 in root directory listing", found);
		infos = getFileManager().list("/mountpoint2/");
		found = false;
		for (FileInformationType info : infos) {
			if ("level2".equals(info.name)) {
				Assert.assertEquals(FileType.FILE_SYSTEM, info.kind);
				found = true;
				break;
			}
		}
		Assert.assertTrue("Did not find level2 in mountpoint2 directory listing", found);

		// After ummounting, getMounts() should no longer show the mount
		getFileManager().unmount("/mountpoint2/level2");
		mounts = getFileManager().getMounts();
		Assert.assertEquals(0, getFileManager().getMounts().length);

		// The root listing should no longer include the directory containing the mount
		infos = getFileManager().list("/");
		for (FileInformationType info : infos) {
			if ("mountpoint2".equals(info.name)) {
				Assert.fail("mountpoint2 should no longer be in the root directory listing");
			}
		}

	}
}
