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
package gov.redhawk.core.internal.filemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.jacorb.JacorbUtil;
import org.omg.CORBA.Any;

import CF.DataType;
import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

/**
 * Represents a virtual, in-memory directory in the IDE's file manager. It contains other {@link Directory} or
 * {@link MountPoint} instances, forming a tree that represents a file system hierarchy. An operation on this class
 * traverses the tree of {@link Directory} until a {@link MountPoint} is encountered, at which point the operation is
 * delegated to the mount point's file system for the remaining portion of the path.
 * <p/>
 * The {@link Directory} class itself will act as a read-only directory. Only the {@link #mount(IPath, FileSystem)}
 * operation will create new {@link Directory} (as needed), and {@link #unmount(IPath)} will remove them.
 * <p/>
 * All methods assume they are called with an absolute {@link IPath}.
 */
public class Directory implements Node {

	/**
	 * The sub-directories / mounts inside this directory (name -> object)
	 */
	private final Map<String, Node> childNodes = new HashMap<String, Node>();

	public Directory() {
	}

	/**
	 * Returns true iff this {@link Directory} contains a child {@link Node} with the specified name.
	 * @param childName
	 * @return
	 */
	public boolean containsChildNode(String name) {
		return childNodes.containsKey(name);
	}

	@Override
	public void remove(IPath fileName) throws FileException, InvalidFileName {
		if (fileName.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
		}

		Node node = childNodes.get(fileName.segment(0));
		if (fileName.segmentCount() == 1) {
			if (node == null) {
				throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
			} else {
				throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
			}
		}

		if (node == null) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
		}
		node.remove(fileName.removeFirstSegments(1).makeAbsolute());
	}

	@Override
	public void rmdir(IPath directoryName) throws InvalidFileName, FileException {
		if (directoryName.segmentCount() == 0) {
			// Deny removal of this virtual directory
			throw new FileException(ErrorNumberType.CF_EACCES, "Directory is read-only");
		}

		Node node = childNodes.get(directoryName.segment(0));
		if (node == null) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such directory");
		}
		node.rmdir(directoryName.removeFirstSegments(1).makeAbsolute());
	}

	@Override
	public File create(IPath fileName) throws InvalidFileName, FileException {
		// With one or fewer segments, we're not going to have anything path left to pass to a mount point
		if (fileName.segmentCount() <= 1) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Write access to parent directory denied");
		}

		Node node = childNodes.get(fileName.segment(0));
		if (node == null) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such directory");
		}
		return node.create(fileName.removeFirstSegments(1).makeAbsolute());
	}

	@Override
	public void mkdir(IPath directoryName) throws InvalidFileName, FileException {
		// With one or fewer segments, we're not going to have anything path left to pass to a mount point
		if (directoryName.segmentCount() <= 1) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Parent directory is read-only");
		}

		Node node = childNodes.get(directoryName.segment(0));
		if (node == null) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such directory");
		}
		node.mkdir(directoryName.removeFirstSegments(1).makeAbsolute());
	}

	public void mount(final IPath mountPoint, final FileSystem fileSystem) throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		if (mountPoint.segmentCount() == 0) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid mount point");
		}

		Node node = childNodes.get(mountPoint.segment(0));
		if (mountPoint.segmentCount() == 1) {
			if (node == null) {
				this.childNodes.put(mountPoint.segment(0), new MountPoint(fileSystem));
			} else if (node instanceof Directory) {
				throw new InvalidFileName(ErrorNumberType.CF_EEXIST, "Target is a directory and mounting would shadow another mount");
			} else {
				throw new MountPointAlreadyExists();
			}
		} else {
			if (node == null) {
				Directory subDir = new Directory();
				this.childNodes.put(mountPoint.segment(0), subDir);
				try {
					subDir.mount(mountPoint.removeFirstSegments(1), fileSystem);
				} catch (InvalidFileName | InvalidFileSystem | MountPointAlreadyExists e) {
					this.childNodes.remove(mountPoint.segment(0));
				}
			} else if (node instanceof Directory) {
				((Directory) node).mount(mountPoint.removeFirstSegments(1), fileSystem);
			} else {
				throw new MountPointAlreadyExists(mountPoint.segment(0));
			}
		}
	}

	public void unmount(IPath mountPoint) throws NonExistentMount {
		if (mountPoint.segmentCount() == 0) {
			throw new NonExistentMount("Not a mount point");
		}

		Node node = childNodes.get(mountPoint.segment(0));
		if (mountPoint.segmentCount() == 1) {
			if (node == null || node instanceof Directory) {
				throw new NonExistentMount("Not a mount point");
			} else {
				this.childNodes.remove(mountPoint.segment(0));
			}
		} else {
			if (node == null) {
				throw new NonExistentMount("No such parent directory: " + mountPoint.segment(0));
			} else if (node instanceof Directory) {
				Directory dir = (Directory) node;
				dir.unmount(mountPoint.removeFirstSegments(1));
				if (dir.childNodes.isEmpty()) {
					this.childNodes.remove(mountPoint.segment(0));
				}
			} else {
				throw new NonExistentMount("Parent directory is a mount point: " + mountPoint.segment(0));
			}
		}
	}

	public List<MountType> getMounts() {
		List<MountType> mounts = new ArrayList<MountType>();
		getMounts(new Path("/"), mounts);
		return mounts;
	}

	/**
	 * Internal helper method for {@link #getMounts()}.
	 * @param parent Parent directory of this directory
	 * @param mounts A {@link List} to add mounts to
	 */
	private void getMounts(IPath parent, List<MountType> mounts) {
		for (final Entry<String, Node> entry : this.childNodes.entrySet()) {
			Node value = entry.getValue();
			if (value instanceof Directory) {
				((Directory) value).getMounts(parent.append(entry.getKey()), mounts);
			} else {
				mounts.add(new MountType(parent.append(entry.getKey()).toString(), ((MountPoint) value).getFileSystem()));
			}
		}
	}

	@Override
	public boolean exists(IPath fileName) throws InvalidFileName {
		if (fileName.segmentCount() == 0) {
			return true;
		}

		Node node = this.childNodes.get(fileName.segment(0));
		if (node == null) {
			return false;
		}
		return node.exists(fileName.removeFirstSegments(1).makeAbsolute());
	}

	@Override
	public List<FileInformationType> list(IPath pattern) throws FileException, InvalidFileName {
		if (pattern.segmentCount() > 1) {
			Node node = this.childNodes.get(pattern.segment(0));
			if (node == null) {
				throw new FileException(ErrorNumberType.CF_ENOENT, "No such directory");
			}
			return node.list(pattern.removeFirstSegments(1).makeAbsolute());
		}

		final String filePatternOnly = pattern.lastSegment();
		List<FileInformationType> fits = new ArrayList<FileInformationType>();
		for (final Entry<String, Node> entry : this.childNodes.entrySet()) {
			if (FilenameUtils.wildcardMatch(entry.getKey(), filePatternOnly)) {
				final FileInformationType fileInfo = new FileInformationType();
				fileInfo.name = entry.getKey();
				fileInfo.size = 0;
				if (entry.getValue() instanceof Directory) {
					fileInfo.kind = FileType.DIRECTORY;
					List<DataType> properties = new ArrayList<DataType>();

					Any readOnlyAny = JacorbUtil.init().create_any();
					readOnlyAny.insert_boolean(true);
					properties.add(new DataType("READ_ONLY", readOnlyAny));

					Any executableAny = JacorbUtil.init().create_any();
					executableAny.insert_boolean(true);
					properties.add(new DataType("EXECUTABLE", executableAny));

					fileInfo.fileProperties = properties.toArray(new DataType[properties.size()]);
				} else {
					fileInfo.kind = FileType.FILE_SYSTEM;
					fileInfo.fileProperties = new DataType[0];
				}
				fits.add(fileInfo);
			}
		}
		return fits;
	}

	@Override
	public File open(IPath fileName, final boolean readOnly) throws InvalidFileName, FileException {
		if (fileName.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
		}

		Node node = childNodes.get(fileName.segment(0));
		if (fileName.segmentCount() == 1) {
			if (node == null) {
				throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
			} else {
				throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
			}
		}

		if (node == null) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
		}
		return node.open(fileName.removeFirstSegments(1).makeAbsolute(), readOnly);
	}

}
