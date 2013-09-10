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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;
import org.omg.CORBA.SystemException;

import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;

public class FileCache {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	private static File tempDir;
	private long cacheTimestamp;
	private File tmpFile;
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

	public synchronized InputStream openInputStream() throws CoreException {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (tmpFile == null || cacheTimestamp < timestamp) {
			downloadFile();
			cacheTimestamp = timestamp;
		}
		try {
			return new FileInputStream(this.tmpFile);
		} catch (final FileNotFoundException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + this.tmpFile, e));
		}
	}

	private void downloadFile() throws CoreException {
		FileOutputStream fileStream = null;
		InputStream scaInputStream = null;
		if (tmpFile == null) {
			String prefix = store.getEntry().getName();
			if (prefix.length() < 3) {
				prefix = "sca_" + prefix;
			}
			try {
				File dir = getTempDir();
				if (!dir.exists()) {
					if (!dir.mkdir()) {
						throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: Failed to create temporary file cache directory " + dir, null));
					}
				}
				tmpFile = File.createTempFile(prefix, null, dir);
				tmpFile.deleteOnExit();
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + tmpFile, e));
			}
		}
		try {
			scaInputStream = createScaInputStream();
			fileStream = new FileOutputStream(tmpFile);
			IOUtils.copy(scaInputStream, fileStream);
		} catch (final FileNotFoundException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + tmpFile, e));
		} catch (final IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + tmpFile, e));
		} catch (CoreException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File cache error: " + tmpFile + "\n" + e.getStatus().getMessage(), e.getStatus().getException()));
		} finally {
			IOUtils.closeQuietly(fileStream);
			IOUtils.closeQuietly(scaInputStream);
		}
	}

	public static synchronized File getTempDir() {
		if (tempDir == null) {
			String systemPath = System.getProperty("java.io.tmpdir");
			Date date = Calendar.getInstance().getTime();
			String user = System.getProperty("user.name");
			String tempDirPath = systemPath + "/rhIDE-" + user + "-" + DATE_FORMAT.format(date);
			tempDir = new File(tempDirPath);
		}
		return tempDir;
	}

	private InputStream createScaInputStream() throws CoreException {
		final String path = store.getEntry().getAbsolutePath();
		try {
			FileSystemOperations fs = getScaFileSystem();
			return new ScaFileInputStream(fs.open(path, true));
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_Invalid_File_Name, path), e));
		} catch (final FileException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error_System, path), e));
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Input_Stream_Error, path), e));
		}
	}

	public synchronized String[] childNames(final int options, IProgressMonitor monitor) throws CoreException {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (names == null || namesTimestamp < timestamp) {
			namesTimestamp = timestamp;
			names = store.internalChildNames(options, monitor);
		}
		return names;
	}

	public boolean isDirectory() {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (directory == null || directoryTimestamp < timestamp) {
			directoryTimestamp = timestamp;
			directory = store.fetchInfo().isDirectory();
		}
		return directory;
	}

	public FileSystemOperations getScaFileSystem() throws CoreException {
		return parent.getScaFileSystem();
	}

	public IFileInfo[] childInfos(int options, IProgressMonitor monitor) throws CoreException {
		final IFileInfo info = store.fetchInfo();
		long timestamp = info.getLastModified();
		if (childInfos == null || childInfosTimestamp < timestamp) {
			childInfosTimestamp = timestamp;
			childInfos = store.internalChildInfos(options, monitor);
		}
		return childInfos;
	}
}
