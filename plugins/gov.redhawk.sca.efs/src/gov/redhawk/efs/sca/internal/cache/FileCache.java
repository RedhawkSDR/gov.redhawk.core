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
import gov.redhawk.efs.sca.internal.ScaFileInputStream;
import gov.redhawk.efs.sca.internal.ScaFileStore;
import gov.redhawk.sca.efs.ScaFileSystemPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.omg.CORBA.SystemException;

import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;

public class FileCache implements IFileCache {

	private static final int DEFAULT_BUFFER_SIZE = 4096;
	private static File tempDir;
	private File localFile;
	private ScaFileStore store;
	private String[] names;
	private long namesTimestamp;
	private FileSystemCache parent;
	private long childInfosTimestamp;
	private IFileInfo[] childInfos;
	private Boolean directory;
	private long directoryTimestamp;

	public FileCache(final ScaFileStore store, FileSystemCache parent) {
		this.store = store;
		this.parent = parent;
	}

	@Override
	public void update() throws CoreException {
		final IFileInfo info = store.fetchInfo();
		if (!isValid(info)) {
			downloadFile(info);
		}
	}

	@Override
	public synchronized InputStream openInputStream() throws CoreException {
		if (isDirectory()) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Can not open input stream on directory.",
				new IOException().fillInStackTrace()));
		}
		update();
		try {
			return new FileInputStream(this.localFile);
		} catch (final FileNotFoundException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + this.localFile, e));
		}
	}

	private void downloadFile(IFileInfo info) throws CoreException {
		FileOutputStream fileStream = null;
		InputStream scaInputStream = null;
		File tmpFile = null;
		File parentDir = new File(FileCache.getTempDir() + "/" + parent.getRoot() + store.getEntry().getAbsolutePath()).getParentFile();
		if (!parentDir.exists()) {
			try {
				FileUtils.forceMkdir(parentDir);
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: Failed to create temporary file cache directory "
						+ parentDir, e));
			}
		}
		localFile = new File(parentDir, store.getEntry().getName());
		if (localFile.exists() && localFile.lastModified() == info.getLastModified()) {
			if ((localFile.isDirectory() && info.isDirectory()) || (localFile.length() == info.getLength())) {
				return;
			}
		}

		boolean error = false;
		try {
			if (info.isDirectory()) {
				if (!info.exists()) {
					FileUtils.forceMkdir(localFile);
				}
			} else {
				FileUtils.deleteQuietly(localFile);
				try {
					tmpFile = File.createTempFile(localFile.getName(), ".tmp", parentDir);
				} catch (IOException e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: Failed to create temporary file " + localFile,
						e));
				}
				tmpFile.deleteOnExit();
				scaInputStream = createScaInputStream();
				fileStream = new FileOutputStream(tmpFile);
				FileCache.copyLarge(scaInputStream, fileStream);
				localFile.setExecutable(info.getAttribute(EFS.ATTRIBUTE_EXECUTABLE));

				FileUtils.moveFile(tmpFile, localFile);
			}
			localFile.setLastModified(info.getLastModified());
		} catch (final FileNotFoundException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + localFile, e));
		} catch (final IOException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + localFile, e));
		} catch (CoreException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + localFile + "\n" + e.getStatus().getMessage(),
				e.getStatus().getException()));
		} catch (InterruptedException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File download interrupted: " + localFile, e));
		} catch (ExecutionException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + localFile, e));
		} catch (TimeoutException e) {
			error = true;
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File download time-out: " + localFile, e));
		} finally {
			IOUtils.closeQuietly(fileStream);
			IOUtils.closeQuietly(scaInputStream);
			FileUtils.deleteQuietly(tmpFile);
			if (error) {
				if (localFile != null && localFile.exists()) {
					FileUtils.deleteQuietly(localFile);
				}
				localFile = null;
			}
		}
	}

	private boolean isValid(IFileInfo info) {
		if (localFile == null || !localFile.exists() || localFile.lastModified() != info.getLastModified()) {
			return false;
		}
		return true;
	}

	private static long copyLarge(InputStream input, OutputStream output) throws IOException, InterruptedException, ExecutionException, TimeoutException {
		byte[] buffer = new byte[FileCache.DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = FileCache.readProtected(input, buffer))) {
			output.write(buffer, 0, n);
			count += n;
			if (Thread.currentThread().isInterrupted()) {
				throw new InterruptedException();
			}
		}
		return count;
	}

	private static int readProtected(final InputStream input, final byte[] buffer) throws IOException, InterruptedException, ExecutionException,
	TimeoutException {
		return ProtectedThreadExecutor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return input.read(buffer);
			}

		}, 20, TimeUnit.SECONDS);
	}

	public static synchronized File getTempDir() {
		if (FileCache.tempDir == null) {
			String systemPath;
			try {
				IPath location = ScaFileSystemPlugin.getDefault().getStateLocation();
				systemPath = location.toFile().getAbsolutePath();
			} catch (IllegalStateException e) {
				String basePath = System.getProperty("java.io.tmpdir");
				String user = System.getProperty("user.name");
				systemPath = basePath + "/.redhawk/" + user;
			}

			String tempDirPath = systemPath + "/" + "fileCache";
			FileCache.tempDir = new File(tempDirPath);
		}
		return FileCache.tempDir;
	}

	private InputStream createScaInputStream() throws CoreException {
		final String path = store.getEntry().getAbsolutePath();
		try {
			FileSystemOperations fs = getScaFileSystem();

			return new ScaFileInputStream(fs.open(path, true));
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(
				Messages.ScaFileStore__Open_Input_Stream_Error_Invalid_File_Name, path), e));
		} catch (final FileException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_System, path), e));
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error, path), e));
		}
	}

	@Override
	public synchronized String[] childNames(final int options, IProgressMonitor monitor) throws CoreException {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (names == null || namesTimestamp < timestamp) {
			namesTimestamp = timestamp;
			names = store.internalChildNames(options, monitor);
		}
		return names;
	}

	@Override
	public boolean isDirectory() {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (directory == null || directoryTimestamp < timestamp) {
			directoryTimestamp = timestamp;
			directory = store.fetchInfo().isDirectory();
		}
		return directory;
	}

	@Override
	public FileSystemOperations getScaFileSystem() throws CoreException {
		return parent.getScaFileSystem();
	}

	@Override
	public IFileInfo[] childInfos(int options, IProgressMonitor monitor) throws CoreException {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (childInfos == null || childInfosTimestamp < timestamp) {
			childInfosTimestamp = timestamp;
			childInfos = store.internalChildInfos(options, monitor);
		}
		return childInfos;
	}

	@Override
	public File toLocalFile() throws CoreException {
		update();
		if (isDirectory()) {
			IFileStore[] children = store.childStores(0, null);
			for (IFileStore child : children) {
				if (child instanceof ScaFileStore) {
					IFileCache cache = ScaFileCache.INSTANCE.getCache((ScaFileStore) child);
					cache.update();
				}
			}
		}
		return localFile;
	}
}
