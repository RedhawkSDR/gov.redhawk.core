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
package gov.redhawk.core.internal.resourcefactory;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import gov.redhawk.core.resourcefactory.ResourceDesc;
import CF.File;
import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

public class ProxyFileSystem implements FileSystemOperations {

	private static class MountPoint implements Comparable<MountPoint> {
		private final ResourceDesc desc;
		private final int priority;

		public MountPoint(ResourceDesc desc, int priority) {
			super();
			this.desc = desc;
			this.priority = priority;
		}

		public int compareTo(MountPoint o) {
			return Integer.valueOf(priority).compareTo(o.priority);
		}
	}

	private SortedSet<MountPoint> mounts = Collections.synchronizedSortedSet(new TreeSet<ProxyFileSystem.MountPoint>());

	public ProxyFileSystem() {
	}

	public void mount(ResourceDesc desc, int priority) {
		synchronized (mounts) {
			for (MountPoint m : mounts) {
				if (m.desc == desc) {
					return;
				}
			}
			mounts.add(new MountPoint(desc, priority));
		}
	}

	public void unmount(ResourceDesc desc) {
		synchronized (mounts) {
			for (MountPoint m : mounts) {
				if (m.desc == desc) {
					mounts.remove(m);
					return;
				}
			}
		}
	}

	public void remove(String fileName) throws FileException, InvalidFileName {
		mounts.first().desc.getFileSystem().remove(fileName);
	}

	public void copy(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		mounts.first().desc.getFileSystem().copy(sourceFileName, destinationFileName);
	}

	public void move(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		mounts.first().desc.getFileSystem().move(sourceFileName, destinationFileName);
	}

	public boolean exists(String fileName) throws InvalidFileName {
		return mounts.first().desc.getFileSystem().exists(fileName);
	}

	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		return mounts.first().desc.getFileSystem().list(pattern);
	}

	public File create(String fileName) throws InvalidFileName, FileException {
		return mounts.first().desc.getFileSystem().create(fileName);
	}

	public File open(String fileName, boolean readOnly) throws InvalidFileName, FileException {
		return mounts.first().desc.getFileSystem().open(fileName, readOnly);
	}

	public void mkdir(String directoryName) throws InvalidFileName, FileException {
		mounts.first().desc.getFileSystem().mkdir(directoryName);
	}

	public void rmdir(String directoryName) throws InvalidFileName, FileException {
		mounts.first().desc.getFileSystem().rmdir(directoryName);
	}

	public void query(PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		mounts.first().desc.getFileSystem().query(fileSystemProperties);
	}

	public boolean isEmpty() {
		return mounts.isEmpty();
	}

	public ResourceDesc getResourceDesc() {
		return mounts.first().desc;
	}

}
