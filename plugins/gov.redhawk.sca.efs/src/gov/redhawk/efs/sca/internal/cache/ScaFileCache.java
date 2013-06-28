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
import gov.redhawk.sca.util.OrbSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

/**
 * 
 */
public class ScaFileCache {

	/** Map from FileSystem URI to FileSystemCache **/
	private final Map<String, FileSystemCache> fileSystemCacheMap = Collections.synchronizedMap(new HashMap<String, FileSystemCache>());
	private OrbSession session;

	public synchronized FileCache getCache(final ScaFileStore store) {
		if (session == null) {
			session = OrbSession.createSession();
		}
		final String queryStr = store.toURI().getQuery();
		final Map<String, String> queryMap = QueryParser.parseQuery(queryStr);
		final String fileSystemUri = queryMap.get(ScaFileSystemConstants.QUERY_PARAM_FS);

		FileSystemCache fileSystemCache = this.fileSystemCacheMap.get(fileSystemUri);
		if (fileSystemCache == null) {
			fileSystemCache = new FileSystemCache(session, store.getFsInitRef());
			this.fileSystemCacheMap.put(fileSystemUri, fileSystemCache);
		}
		return fileSystemCache.getFileCache(store);

	}

	public Map<String, FileSystemCache> getFileSystemCacheMap() {
		return Collections.unmodifiableMap(this.fileSystemCacheMap);
	}

	public synchronized void clear() {
		for (final FileSystemCache cache : this.fileSystemCacheMap.values()) {
			cache.clear();
		}
		this.fileSystemCacheMap.clear();
		if (session != null) {
			session.dispose();
			session = null;
		}

	}
}
