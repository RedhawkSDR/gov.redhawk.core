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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IPath;

import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.FileSystemPackage.FileInformationType;

/**
 * Represents a "mount point" where a {@link CF.FileSystem} has been "mounted" as a directory in the IDE file manager.
 */
public class MountPoint implements Node {

	private final FileSystem fileSystem;

	public MountPoint(final FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public FileSystem getFileSystem() {
		return this.fileSystem;
	}

	public void remove(IPath fileName) throws FileException, InvalidFileName {
		this.fileSystem.remove(fileName.toString());
	}

	public void rmdir(IPath directoryName) throws InvalidFileName, FileException {
		if (directoryName.segmentCount() == 0) {
			// Can't remove the mount point
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such file or directory");
		}
		this.fileSystem.rmdir(directoryName.toString());
	}

	public File create(IPath fileName) throws InvalidFileName, FileException {
		return this.fileSystem.create(fileName.toString());
	}

	public void mkdir(IPath directoryName) throws InvalidFileName, FileException {
		this.fileSystem.mkdir(directoryName.toString());
	}

	public boolean exists(IPath fileName) throws InvalidFileName {
		return this.fileSystem.exists(fileName.toString());
	}

	public List<FileInformationType> list(IPath pattern) throws FileException, InvalidFileName {
		FileInformationType[] infos = this.fileSystem.list(pattern.toString());
		return Arrays.asList(infos);
	}

	public File open(IPath fileName, final boolean readOnly) throws InvalidFileName, FileException {
		return this.fileSystem.open(fileName.toString(), readOnly);
	}

}
