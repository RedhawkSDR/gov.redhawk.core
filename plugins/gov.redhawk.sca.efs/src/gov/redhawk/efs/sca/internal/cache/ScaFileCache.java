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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import gov.redhawk.efs.sca.internal.ScaFileStore;
import gov.redhawk.sca.util.OrbSession;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

/**
 * 
 */
public enum ScaFileCache {
	INSTANCE;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private final RemovalListener<String, FileSystemCache> cacheRemovalListener = new RemovalListener<String, FileSystemCache>() {
		@Override
		public void onRemoval(RemovalNotification<String, FileSystemCache> removed) {
			FileSystemCache removedFsc = removed.getValue();
			removedFsc.clear();
		}
	};

	/** Map from FileSystem URI to FileSystemCache **/
	private final Cache<String, FileSystemCache> fileSystemCacheMap = CacheBuilder.newBuilder()
			.expireAfterAccess(1, TimeUnit.MINUTES)
			.removalListener(cacheRemovalListener)
			.build();

	private OrbSession session;

	private ScaFileCache() {
		this.scheduler.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				fileSystemCacheMap.cleanUp();
			}
		}, 1, 1, TimeUnit.MINUTES);
	}

	public synchronized IFileCache getCache(final ScaFileStore store) {
		if (session == null) {
			session = OrbSession.createSession();
		}
		final String queryStr = store.toURI().getQuery();
		final Map<String, String> queryMap = QueryParser.parseQuery(queryStr);
		final String fileSystemUri = queryMap.get(ScaFileSystemConstants.QUERY_PARAM_FS);

		FileSystemCache fileSystemCache = this.fileSystemCacheMap.getIfPresent(fileSystemUri);
		if (fileSystemCache == null) {
			fileSystemCache = new FileSystemCache(session, store);
			this.fileSystemCacheMap.put(fileSystemUri, fileSystemCache);
		}
		return fileSystemCache.getFileCache(store);
	}

	public synchronized void clear() {
		for (final FileSystemCache cache : this.fileSystemCacheMap.asMap().values()) {
			cache.clear();
		}
		this.fileSystemCacheMap.invalidateAll();
		if (session != null) {
			session.dispose();
			session = null;
		}
	}
}
