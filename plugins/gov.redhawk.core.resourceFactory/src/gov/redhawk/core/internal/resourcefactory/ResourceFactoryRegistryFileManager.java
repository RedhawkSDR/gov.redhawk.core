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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.NonExistentMount;
import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.filemanager.filesystem.JavaFileSystem;
import gov.redhawk.core.resourcefactory.ResourceDesc;
import gov.redhawk.core.resourcefactory.ResourceFactoryPlugin;
import gov.redhawk.sca.util.OrbSession;

/**
 * Extends {@link FileManagerImpl} to provide the ability to mount the file systems of {@link ResourceDesc} into the
 * virtual file system.
 */
public class ResourceFactoryRegistryFileManager extends FileManagerImpl {

	private Map<String, ResourceDesc> profileToResourceDesc = new HashMap<String, ResourceDesc>();
	private OrbSession session = OrbSession.createSession();

	public ResourceFactoryRegistryFileManager() throws CoreException {
		setLocalFileSystem(new JavaFileSystem(session.getOrb(), session.getPOA(), new File("/")));
	}

	/**
	 * Mount the file system for a {@ResouceDesc} to its profile path
	 * @param desc
	 * @throws CoreException
	 */
	public synchronized void mount(ResourceDesc desc) throws CoreException {
		String profile = desc.getProfile();
		IPath mountPoint = new Path(profile).removeLastSegments(1);

		try {
			// We don't have to mount anything if the local file system will be used
			if (desc.getFileSystem() != null) {
				mount(mountPoint.toString(), desc.getFileSystemRef());
			}
		} catch (InvalidFileName e) {
			String errorMsg = "InvalidFileName";
			if (e.msg != null && e.msg.length() > 0) {
				errorMsg += ": " + e.msg;
			}
			throw new CoreException(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, errorMsg, e));
		} catch (InvalidFileSystem e) {
			throw new CoreException(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "InvalidFileSystem", e));
		} catch (MountPointAlreadyExists e) {
			throw new CoreException(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "MountPointAlreadyExists", e));
		}

		profileToResourceDesc.put(mountPoint.toString(), desc);
	}

	/**
	 * Unmount the file system for a {@ResourceDesc}
	 * @param desc
	 */
	public synchronized void unmount(ResourceDesc desc) {
		String profile = desc.getProfile();
		IPath mountPoint = new Path(profile).removeLastSegments(1);
		profileToResourceDesc.remove(mountPoint.toString());

		try {
			// We don't have to unmount anything if the local file system is being used
			if (desc.getFileSystem() != null) {
				unmount(mountPoint.toString());
			}
		} catch (NonExistentMount e) {
			ResourceFactoryPlugin.logError("NonExistentMount", e);
		}
	}

	public ResourceDesc getResourceDesc(String profile) {
		IPath mountPoint = new Path(profile).removeLastSegments(1);
		return profileToResourceDesc.get(mountPoint.toString());
	}
}
