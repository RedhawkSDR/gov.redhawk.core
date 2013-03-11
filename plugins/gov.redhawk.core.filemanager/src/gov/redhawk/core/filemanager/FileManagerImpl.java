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
package gov.redhawk.core.filemanager;

import gov.redhawk.core.internal.filemanager.Directory;

import java.util.Arrays;
import java.util.List;

import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * 
 */
public class FileManagerImpl implements IFileManager {

	private final Directory root = new Directory();

	public FileManagerImpl() {

	}

	public void remove(String fileName) throws FileException, InvalidFileName {
		if ("".equals(fileName) || fileName == null) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "");
		}
		if (fileName == null || fileName.length() == 0 || fileName.equals("/")) {
			throw new InvalidFileName();
		}
		if (fileName.charAt(0) == '/') {
			fileName = fileName.substring(1);
		}
		this.root.remove(Arrays.asList(fileName.split("/")));
	}

	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		// TODO
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Operation not supported");
	}

	public boolean exists(String fileName) throws InvalidFileName {
		if (fileName == null || fileName.length() == 0 || fileName.equals("/")) {
			return true;
		}
		if (fileName.charAt(0) == '/') {
			fileName = fileName.substring(1);
		}
		return this.root.exists(Arrays.asList(fileName.split("/")));
	}

	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		if (pattern == null) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File must not be null");
		}
		if (pattern.length() == 0 || pattern.equals("/")) {
			final FileInformationType info = this.root.createFileInformationType();
			info.kind = FileType.FILE_SYSTEM;
			info.name = "/";
			return new FileInformationType[] {
				info
			};
		} else {
			if (pattern.charAt(0) == '/') {
				pattern = pattern.substring(1);
			}
			final List<FileInformationType> retVal = this.root.list(Arrays.asList(pattern.split("/")));
			return retVal.toArray(new FileInformationType[retVal.size()]);
		}
	}

	public File create(String fileName) throws InvalidFileName, FileException {
		if (fileName == null || fileName.length() == 0 || fileName.equals("/")) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "");
		}
		if (fileName.charAt(0) == '/') {
			fileName = fileName.substring(1);
		}
		return this.root.create(Arrays.asList(fileName.split("/")));
	}

	public File open(String fileName, final boolean readOnly) throws InvalidFileName, FileException {
		if (fileName == null || fileName.length() == 0 || fileName.equals("/")) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid file: " + fileName);
		}
		if (fileName.charAt(0) == '/') {
			fileName = fileName.substring(1);
		}
		return this.root.open(Arrays.asList(fileName.split("/")), readOnly);
	}

	public void mkdir(String directoryName) throws InvalidFileName, FileException {
		if (directoryName == null || directoryName.length() == 0 || directoryName.equals("/")) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid directory Name: " + directoryName);
		}
		if (directoryName.charAt(0) == '/') {
			directoryName = directoryName.substring(1);
		}
		this.root.mkdir(Arrays.asList(directoryName.split("/")));
	}

	public void rmdir(String directoryName) throws InvalidFileName, FileException {
		if (directoryName == null || directoryName.length() == 0 || directoryName.equals("/")) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid directory Name: " + directoryName);
		}
		if (directoryName.charAt(0) == '/') {
			directoryName = directoryName.substring(1);
		}
		this.root.rmdir(Arrays.asList(directoryName.split("/")));
	}

	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		// No properties
	}

	public void mount(String mp, final FileSystem fileSystem) throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		if (mp == null || mp.length() == 0 || mp.equals("/")) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Invalid mount point: " + mp);
		}
		if (mp.charAt(0) == '/') {
			mp = mp.substring(1);
		}
		this.root.mount(Arrays.asList(mp.split("/")), fileSystem);
	}

	public void unmount(String mp) throws NonExistentMount {
		if (mp == null || mp.length() == 0 || mp.equals("/")) {
			throw new NonExistentMount("Invalid mount Name: " + mp);
		}
		if (mp.charAt(0) == '/') {
			mp = mp.substring(1);
		}
		this.root.unmount(Arrays.asList(mp.split("/")));
	}

	public MountType[] getMounts() {
		final List<MountType> retVal = this.root.getMounts();
		return retVal.toArray(new MountType[retVal.size()]);
	}

	public void move(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		// TODO Auto-generated method stub
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Operation not supported");
	}

}
