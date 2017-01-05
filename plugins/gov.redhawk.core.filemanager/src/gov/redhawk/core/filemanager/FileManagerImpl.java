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
package gov.redhawk.core.filemanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.UnknownFileSystemProperties;
import gov.redhawk.core.internal.filemanager.Directory;

/**
 * An IDE implementation of a {@link CF.FileManager}. The file system is essentially a union file system with two
 * layers. The base file system is the host's file system (since gov.redhawk.core.filemanager 2.0.0; see
 * {@link #setLocalFileSystem(FileSystemOperations)}). Virtual directories and mounts ({@link CF.FileSystem}) are
 * overlaid on top, taking precedence over the host's file system.
 */
public class FileManagerImpl implements IFileManager {

	/**
	 * This represents the root of the virtual file system. Any contents take precedence over the root of the local
	 * file system.
	 */
	private final Directory virtualRoot = new Directory();

	/**
	 * The local file system.
	 */
	private FileSystemOperations localFileSystem;

	/**
	 * Create the file manager. The local file system <b>MUST</b> be set before use (see
	 * {@link #setLocalFileSystem(FileSystemOperations)}).
	 */
	public FileManagerImpl() {
	}

	/**
	 * @since 2.0
	 */
	public void setLocalFileSystem(final FileSystemOperations localFileSystem) {
		this.localFileSystem = localFileSystem;
	}

	@Override
	public void remove(final String fileName) throws FileException, InvalidFileName {
		IPath file = precheck(fileName);
		if (file.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
		}
		if (this.virtualRoot.containsChildNode(file.segment(0))) {
			this.virtualRoot.remove(file);
		} else {
			this.localFileSystem.remove(file.toString());
		}
	}

	@Override
	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		IPath sourceFile = precheck(sourceFileName);
		IPath destinationFile = precheck(destinationFileName);
		boolean virtualSource = sourceFile.segmentCount() > 0 && this.virtualRoot.containsChildNode(sourceFile.segment(0));
		boolean virtualDestination = destinationFile.segmentCount() > 0 && this.virtualRoot.containsChildNode(destinationFile.segment(0));
		if (virtualSource || virtualDestination) {
			throw new FileException(ErrorNumberType.CF_ENOTSUP, "Operation not supported");
		} else {
			this.localFileSystem.copy(sourceFile.toString(), destinationFile.toString());
		}
	}

	@Override
	public boolean exists(final String fileName) throws InvalidFileName {
		IPath file = precheck(fileName);

		// We check the virtual root first since the local file system could throw due to a permissions error
		return this.virtualRoot.exists(file) || this.localFileSystem.exists(file.toString());
	}

	@Override
	public FileInformationType[] list(final String pattern) throws FileException, InvalidFileName {
		if (pattern == null) {
			throw new InvalidFileName(ErrorNumberType.CF_ENOENT, "No such file");
		}

		// Special case - an empty pattern is the way you get info for the root directory itself
		if (pattern.trim().isEmpty()) {
			return this.localFileSystem.list("");
		}

		IPath pathWithPattern = precheck(pattern);

		// If they've asked for the root, or a path ending with a separator, give the contents of the directory
		if (pathWithPattern.segmentCount() == 0 || pathWithPattern.hasTrailingSeparator()) {
			pathWithPattern = pathWithPattern.append("*");
		}

		if (pathWithPattern.segmentCount() == 1) {
			// We have to combine the local and virtual root file listings, with preference to the virtual
			List<FileInformationType> virtualFiles = this.virtualRoot.list(pathWithPattern);
			FileInformationType[] localFiles = this.localFileSystem.list(pathWithPattern.toString());
			Map<String, FileInformationType> combinedFiles = new HashMap<String, FileInformationType>();
			for (FileInformationType fit : localFiles) {
				combinedFiles.put(fit.name, fit);
			}
			for (FileInformationType fit : virtualFiles) {
				combinedFiles.put(fit.name, fit);
			}
			return combinedFiles.values().toArray(new FileInformationType[combinedFiles.size()]);
		} else if (this.virtualRoot.containsChildNode(pathWithPattern.segment(0))) {
			List<FileInformationType> fits = this.virtualRoot.list(pathWithPattern);
			return fits.toArray(new FileInformationType[fits.size()]);
		} else {
			return this.localFileSystem.list(pathWithPattern.toString());
		}
	}

	@Override
	public File create(final String fileName) throws InvalidFileName, FileException {
		IPath file = precheck(fileName);
		if (file.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
		} else if (file.segmentCount() == 1) {
			// At the root level, we only allow attempting to create files in the local file system
			return this.localFileSystem.create(file.toString());
		} else if (this.virtualRoot.containsChildNode(file.segment(0))) {
			return this.virtualRoot.create(file);
		} else {
			return this.localFileSystem.create(file.toString());
		}
	}

	@Override
	public File open(final String fileName, final boolean readOnly) throws InvalidFileName, FileException {
		IPath file = precheck(fileName);
		if (file.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
		} else if (file.segmentCount() == 1) {
			// At the root level, we only allow attempting to open files in the local file system
			return this.localFileSystem.open(file.toString(), readOnly);
		} else if (this.virtualRoot.containsChildNode(file.segment(0))) {
			return this.virtualRoot.open(file, readOnly);
		} else {
			return this.localFileSystem.open(file.toString(), readOnly);
		}
	}

	@Override
	public void mkdir(final String directoryName) throws InvalidFileName, FileException {
		IPath directory = precheck(directoryName);
		if (directory.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EEXIST, "Directory exists");
		} else if (this.virtualRoot.containsChildNode(directory.segment(0))) {
			this.virtualRoot.mkdir(directory);
		} else {
			this.localFileSystem.mkdir(directory.toString());
		}
	}

	@Override
	public void rmdir(final String directoryName) throws InvalidFileName, FileException {
		IPath directory = precheck(directoryName);
		if (directory.segmentCount() == 0) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Access denied");
		} else if (this.virtualRoot.containsChildNode(directory.segment(0))) {
			this.virtualRoot.rmdir(directory);
		} else {
			this.localFileSystem.rmdir(directory.toString());
		}
	}

	@Override
	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		this.localFileSystem.query(fileSystemProperties);
	}

	@Override
	public void mount(final String mountPoint, final FileSystem fileSystem) throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		IPath mountPointPath = precheck(mountPoint);
		if (mountPointPath.segmentCount() == 0) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid mount point");
		}
		if (fileSystem == null) {
			throw new InvalidFileSystem("Null file system");
		}

		// Mount requests go right to the virtual root after initial validation
		this.virtualRoot.mount(mountPointPath, fileSystem);
	}

	@Override
	public void unmount(final String mountPoint) throws NonExistentMount {
		IPath mountPointPath;
		try {
			mountPointPath = precheck(mountPoint);
		} catch (InvalidFileName e) {
			throw new NonExistentMount(e.msg);
		}
		if (mountPointPath.segmentCount() == 0) {
			throw new NonExistentMount("Invalid mount point");
		}

		// Mount requests go right to the virtual root after initial validation
		this.virtualRoot.unmount(mountPointPath);
	}

	@Override
	public MountType[] getMounts() {
		final List<MountType> retVal = this.virtualRoot.getMounts();
		return retVal.toArray(new MountType[retVal.size()]);
	}

	@Override
	public void move(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		IPath sourceFile = precheck(sourceFileName);
		IPath destinationFile = precheck(destinationFileName);
		boolean virtualSource = sourceFile.segmentCount() > 0 && this.virtualRoot.containsChildNode(sourceFile.segment(0));
		boolean virtualDestination = destinationFile.segmentCount() > 0 && this.virtualRoot.containsChildNode(destinationFile.segment(0));
		if (virtualSource || virtualDestination) {
			throw new FileException(ErrorNumberType.CF_ENOTSUP, "Operation not supported");
		} else {
			this.localFileSystem.move(sourceFile.toString(), destinationFile.toString());
		}
	}

	/**
	 * Checks that the path is non-empty and absolute. Returns an {@link IPath}.
	 * @param path The path to check
	 * @return An {@link IPath} object
	 * @throws InvalidFileName The path is null, empty, or not absolute
	 */
	private IPath precheck(final String path) throws InvalidFileName {
		if (path == null || path.trim().isEmpty()) {
			throw new InvalidFileName(ErrorNumberType.CF_ENOENT, "No such file or directory");
		}
		IPath iPath = new Path(path).makeUNC(false);
		if (!iPath.isAbsolute()) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Path is not absolute");
		}
		return iPath;
	}

}
