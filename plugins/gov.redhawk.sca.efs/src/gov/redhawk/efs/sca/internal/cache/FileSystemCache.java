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
package gov.redhawk.efs.sca.internal.cache;

import gov.redhawk.efs.sca.internal.ScaFileStore;
import gov.redhawk.sca.efs.ScaFileSystemPlugin;
import gov.redhawk.sca.util.OrbSession;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;

import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemOperations;

public class FileSystemCache {
	/**
	 * Map from absolute path to File Cache
	 */
	private final Map<String, FileCache> fileCacheMap = Collections.synchronizedMap(new HashMap<String, FileCache>());
	private FileSystem fs;
	private URI fsInitRef;
	private OrbSession session;

	public FileSystemCache(OrbSession session, URI fsInitRef) {
		this.fsInitRef = fsInitRef;
		this.session = session;
	}

	public synchronized FileCache getFileCache(final ScaFileStore store) {
		final String path = store.getEntry().getAbsolutePath();
		FileCache fileCache = this.fileCacheMap.get(path);
		if (fileCache == null) {
			fileCache = new FileCache(store, this);
			this.fileCacheMap.put(path, fileCache);
		}
		return fileCache;
	}

	public Map<String, FileCache> getFileCacheMap() {
		return Collections.unmodifiableMap(this.fileCacheMap);
	}

	public void clear() {
		this.fileCacheMap.clear();
		if (fs != null) {
			fs._release();
			fs = null;
		}
	}

	public FileSystemOperations getScaFileSystem() throws CoreException {
		if (fs == null) {
			return createFileSystemReference();
		}
		return fs;
	}

	private FileSystemOperations createFileSystemReference() throws CoreException {
		try {
			if (ScaFileSystemConstants.FS_SCHEME_CORBA_NAME.equals(fsInitRef.getScheme())) {
				final org.omg.CORBA.Object objRef = session.getOrb().string_to_object(fsInitRef.toString());
				fs = FileSystemHelper.narrow(objRef);
			} else if (ScaFileSystemConstants.FS_SCHEME_CORBA_IOR.equals(fsInitRef.getScheme())) {
				final String ior = fsInitRef.toString();

				// Length must be even for str to be valid
				if ((ior.length() & 1) == 1) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Invalid File System IOR: " + ior, null));
				}

				final org.omg.CORBA.Object objRef = session.getOrb().string_to_object(ior);
				fs = FileSystemHelper.narrow(objRef);
			} else {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Unknown File System Schema: " + fsInitRef.getScheme(), null));
			}
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to resolve File System: " + fsInitRef, e));
		}
		return fs;
	}
}
