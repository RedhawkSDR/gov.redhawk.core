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
package gov.redhawk.efs.sca.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.osgi.util.NLS;
import org.omg.CORBA.SystemException;

import CF.FileException;
import CF.FileSystem;
import CF.InvalidFileName;

/**
 * 
 */
public class ScaFileCache {

	public static class FileCache {
		private volatile IFileInfo fileInfo = null;
		private final File tmpFile;

		public FileCache(final File tmpFile) {
			this.tmpFile = tmpFile;
			this.tmpFile.deleteOnExit();
		}

		public InputStream openInputStream(final ScaFileStore store) throws CoreException {
			final IFileInfo info = store.fetchInfo();

			// Is the cache invalid?
			if (this.fileInfo == null || this.fileInfo.getLastModified() < info.getLastModified()) {
				// Download the state of the file into a buffer
				final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				final InputStream scaInputStream = FileCache.createScaInputStream(store);
				try {
					IOUtils.copy(scaInputStream, buffer);
				} catch (final IOException e) {
					final String path = store.getEntry().getAbsolutePath();
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_System,
					        path), e));
				} finally {
					IOUtils.closeQuietly(scaInputStream);
					IOUtils.closeQuietly(buffer);
				}

				// Lock the cache and push the buffer to the file if necessary
				synchronized (this) {
					// Is the cache still invalid?
					if (this.fileInfo == null || this.fileInfo.getLastModified() < info.getLastModified()) {
						FileOutputStream fileStream = null;
						try {
							fileStream = new FileOutputStream(this.tmpFile);
							fileStream.write(buffer.toByteArray());
						} catch (final FileNotFoundException e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + this.tmpFile, e));
						} catch (final IOException e) {
							throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + this.tmpFile, e));
						} finally {
							if (fileStream != null) {
								IOUtils.closeQuietly(fileStream);
							}
						}
						this.fileInfo = info;
					}
				}
			}
			try {
				return new FileInputStream(this.tmpFile);
			} catch (final FileNotFoundException e) {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + this.tmpFile, e));
			}
		}

		private static InputStream createScaInputStream(final ScaFileStore store) throws CoreException {
			final String path = store.getEntry().getAbsolutePath();
			FileSystem fs = null;
			try {
				fs = store.createScaFileSystem();
				return new ScaFileInputStream(fs.open(path, true));
			} catch (final InvalidFileName e) {
				throw new CoreException(new Status(IStatus.ERROR,
				        ScaFileSystemPlugin.ID,
				        NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_Invalid_File_Name, path),
				        e));
			} catch (final FileException e) {
				throw new CoreException(new Status(IStatus.ERROR,
				        ScaFileSystemPlugin.ID,
				        NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_System, path),
				        e));
			} catch (final SystemException e) {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error, path), e));
			} finally {
				if (fs != null) {
					fs._release();
				}
			}
		}
	}

	public static class FileSystemCache {
		/**
		 * Map from absolute path to File Cache
		 */
		private final Map<String, FileCache> fileCacheMap = Collections.synchronizedMap(new HashMap<String, FileCache>());

		public InputStream openInputStream(final ScaFileStore store) throws CoreException {
			final String path = store.getEntry().getAbsolutePath();
			FileCache fileCache = this.fileCacheMap.get(path);
			if (fileCache == null) {
				synchronized (this.fileCacheMap) {
					fileCache = this.fileCacheMap.get(path);
					if (fileCache == null) {
						try {
							String prefix = store.getEntry().getName();
							if (prefix.length() < 3) {
								prefix = "sca";
							}
							final File tmpFile = File.createTempFile(prefix, null);
							tmpFile.deleteOnExit();
							fileCache = new FileCache(tmpFile);
							this.fileCacheMap.put(path, fileCache);
						} catch (final IOException e) {
							// Failed to create temporary file return the sca Input Stream directly
							return FileCache.createScaInputStream(store);
						}
					}
				}
			}
			return fileCache.openInputStream(store);
		}

		public Map<String, FileCache> getFileCacheMap() {
			return Collections.unmodifiableMap(this.fileCacheMap);
		}

		public void clear() {
			this.fileCacheMap.clear();
		}
	}

	/** Map from FileSystem URI to FileSystemCache **/
	private final Map<String, FileSystemCache> fileSystemCacheMap = Collections.synchronizedMap(new HashMap<String, FileSystemCache>());

	public InputStream openInputStream(final ScaFileStore store, IProgressMonitor monitor) throws CoreException {
		final String path = store.getEntry().getAbsolutePath();
		final String queryStr = store.toURI().getQuery();
		final Map<String, String> queryMap = QueryParser.parseQuery(queryStr);
		final String fileSystemUri = queryMap.get(ScaFileSystemConstants.QUERY_PARAM_FS);
		monitor = SubMonitor.convert(monitor, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Task_Name, path), IProgressMonitor.UNKNOWN);
		try {
			FileSystemCache fileSystemCache = this.fileSystemCacheMap.get(fileSystemUri);
			if (fileSystemCache == null) {
				synchronized (this.fileSystemCacheMap) {
					fileSystemCache = this.fileSystemCacheMap.get(fileSystemUri);
					if (fileSystemCache == null) {
						fileSystemCache = new FileSystemCache();
						this.fileSystemCacheMap.put(fileSystemUri, fileSystemCache);
					}
				}
			}
			return fileSystemCache.openInputStream(store);
		} finally {
			monitor.done();
		}
	}

	public Map<String, FileSystemCache> getFileSystemCacheMap() {
		return Collections.unmodifiableMap(this.fileSystemCacheMap);
	}

	public void clear() {
		synchronized (this.fileSystemCacheMap) {
			for (final FileSystemCache cache : this.fileSystemCacheMap.values()) {
				cache.clear();
			}
			this.fileSystemCacheMap.clear();
		}
	}
}
