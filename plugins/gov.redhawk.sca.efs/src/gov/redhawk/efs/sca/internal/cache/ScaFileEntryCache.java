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

import gov.redhawk.efs.sca.internal.Messages;
import gov.redhawk.sca.efs.ScaFileSystemPlugin;

import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 
 */
public enum ScaFileEntryCache {
	INSTANCE;

	/** Map from URI to IFileInfo */
	private final Cache<URI, IFileInfo> fileEntryInfoCache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS)
			.build();

	public synchronized IFileInfo getCache(final URI uri, Callable< ? extends IFileInfo> callable) throws CoreException {
		try {
			return fileEntryInfoCache.get(uri, callable);
		} catch (ExecutionException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Fetch_Info_Error, uri.getPath()), e));
		}

	}

	public synchronized void remove(final URI uri) {
		this.fileEntryInfoCache.invalidate(uri);
	}

	public synchronized void clear() {
		this.fileEntryInfoCache.invalidateAll();
		this.fileEntryInfoCache.cleanUp();
	}

}
