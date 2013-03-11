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

import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.ORBUtil;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.provider.FileInfo;
import org.eclipse.core.filesystem.provider.FileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.TCKind;

import CF.DataType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.FileSystemHelper;
import CF.InvalidFileName;
import CF.FilePackage.InvalidFilePointer;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

/**
 * 
 */
public class ScaFileStore extends FileStore {

	private static enum ScaFileInformationDataType {
		CREATED_TIME, MODIFIED_TIME, LAST_ACCESS_TIME, IOR_AVAILABLE, READ_ONLY
	}

	private static final Debug DEBUG = new Debug(ScaFileSystemPlugin.ID, "fileStore");

	private final URI fsInitRef;
	private final ScaFileEntry entry;
	private Boolean directory;
	private FileSystem scaFileSystem;

	public ScaFileStore(final URI fsInitRef, final ScaFileEntry entry) {
		this.fsInitRef = fsInitRef;
		this.entry = entry;
	}

	public ScaFileEntry getEntry() {
		return this.entry;
	}

	public FileSystem getScaFileSystem() throws CoreException {
		if (this.scaFileSystem == null) {
			this.scaFileSystem = initScaFileSystem();
		}
		return this.scaFileSystem;
	}
	
	private static volatile ORB sharedORB;
	public static ORB getSharedORB() {
		if (sharedORB == null) {
			synchronized (ScaFileStore.class) {
	            if (sharedORB == null) {
	            	sharedORB = ORBUtil.init(null);
	            }
            }
		}
		return sharedORB;
	}
	
	public static void disposeORB() {
		synchronized (ScaFileStore.class) {
			if (sharedORB != null) {
				sharedORB.destroy();
				sharedORB = null;
			}
		}
	}

	private FileSystem initScaFileSystem() throws CoreException {
		try {
			ORB orb = getSharedORB();
			if (ScaFileSystemConstants.FS_SCHEME_CORBA_NAME.equals(this.fsInitRef.getScheme())) {
				final org.omg.CORBA.Object objRef = orb.string_to_object(this.fsInitRef.toString());
				final CF.FileSystem fileSys = FileSystemHelper.narrow(objRef);
				return fileSys;
			} else if (ScaFileSystemConstants.FS_SCHEME_CORBA_IOR.equals(this.fsInitRef.getScheme())) {
				final String ior = this.fsInitRef.toString();

				// Length must be even for str to be valid
				if ((ior.length() & 1) == 1) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Invalid File System IOR: " + ior, null));
				}

				final org.omg.CORBA.Object objRef = orb.string_to_object(ior);
				final CF.FileSystem fileSys = FileSystemHelper.narrow(objRef);
				return fileSys;
			} else {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Unknown File System Schema: " + this.fsInitRef.getScheme(), null));
			}
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to resolve File System: " + this.entry.getUri(), e));
		} 
	}

	private boolean exists() throws CoreException {
		try {
			return getScaFileSystem().exists(this.entry.getAbsolutePath());
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "File System Exception: " + this.entry.getUri(), e));
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind("Invalid file name: {0}", this.entry.getAbsolutePath()), e));
		}
	}

	private boolean isDirectory() {
		if (this.directory == null) {
			this.directory = fetchInfo().isDirectory();
		}
		return this.directory;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] childNames(final int options, IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		monitor = SubMonitor.convert(monitor, Messages.ScaFileStore__Child_Names_Task_Name, IProgressMonitor.UNKNOWN);
		try {
			if (!isDirectory()) {
				return FileStore.EMPTY_STRING_ARRAY;
			}
			final FileInformationType[] result = ProtectedThreadExecutor.submit(new Callable<FileInformationType[]>() {

				public FileInformationType[] call() throws Exception {
					return getScaFileSystem().list(path + "/*"); //$NON-NLS-1$
				}
			});
			final String[] children = new String[result.length];
			for (int i = 0; i < result.length; i++) {
				final FileInformationType file = result[i];
				children[i] = file.name;
			}
			return children;
		} catch (final InterruptedException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Names_Error, path), e));
		} catch (final ExecutionException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Names_Error, path), e));
		} catch (final TimeoutException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Names_Error, path), e));
		} finally {
			monitor.done();
		}
	}

	private IFileInfo translate(final FileInformationType typeInfo) {
		final FileInfo info = new FileInfo(typeInfo.name);

		// If path is a directory we will get the contents of the
		// directory
		info.setLength(typeInfo.size);
		for (final DataType t : typeInfo.fileProperties) {
			ScaFileInformationDataType scaDataType;
			try {
				scaDataType = ScaFileInformationDataType.valueOf(t.id);
			} catch (final IllegalArgumentException e) {
				continue;
			}
			switch (scaDataType) {
			case MODIFIED_TIME:
				switch (t.value.type().kind().value()) {
				case TCKind._tk_long:
					info.setLastModified(t.value.extract_long() * 1000L); // SUPPRESS CHECKSTYLE MagicNumber
					break;
				case TCKind._tk_ulong:
					info.setLastModified(t.value.extract_ulong() * 1000L); // SUPPRESS CHECKSTYLE MagicNumber
					break;
				case TCKind._tk_ulonglong:
					info.setLastModified(t.value.extract_ulonglong() * 1000L); // SUPPRESS CHECKSTYLE MagicNumber
					break;
				default:
					break;
				}
				break;
			case READ_ONLY:
				switch (t.value.type().kind().value()) {
				case TCKind._tk_boolean:
					info.setAttribute(EFS.ATTRIBUTE_READ_ONLY, t.value.extract_boolean());
					break;
				default:
					break;
				}
				break;
			default:
				// TODO Handle DataType
				break;
			}
		}

		// XXX: Set all Files and Directory to read only for now
		//			info.setAttribute(EFS.ATTRIBUTE_READ_ONLY, true);

		info.setExists(true);
		switch (typeInfo.kind.value()) {
		case FileType._FILE_SYSTEM:
		case FileType._DIRECTORY:
			info.setDirectory(true);
			break;
		case FileType._PLAIN:
			info.setDirectory(false);
			break;
		default:
			break;
		}
		return info;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileInfo fetchInfo(final int options, IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		monitor = SubMonitor.convert(monitor, Messages.ScaFileStore__Fetch_Info_Task_Name, IProgressMonitor.UNKNOWN);
		try {
			IFileInfo retVal;
			final FileInformationType[] result = ProtectedThreadExecutor.submit(new Callable<FileInformationType[]>() {

				public FileInformationType[] call() throws Exception {
					return getScaFileSystem().list(path);
				}
			});

			if (result.length == 0) {
				final FileInfo info = new FileInfo(getName());
				info.setExists(false);
				retVal = info;
			} else {
				retVal = translate(result[0]);
			}

			return retVal;
		} catch (final InterruptedException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Fetch_Info_Error, path), e));
		} catch (final ExecutionException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Fetch_Info_Error, path), e));
		} catch (final TimeoutException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Fetch_Info_Error, path), e));
		} finally {
			monitor.done();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileStore getChild(final String name) {
		final URI uri = this.entry.getUri();
		org.eclipse.emf.common.util.URI tmp = org.eclipse.emf.common.util.URI.createURI(uri.toString());
		String encName;
		try {
			encName = URLEncoder.encode(name, "UTF-8"); //$NON-NLS-1$
			tmp = tmp.appendSegment(encName);
			final URI childUri = URI.create(tmp.toString());
			final ScaFileStore child = new ScaFileStore(this.fsInitRef, new ScaFileEntry(childUri));
			return child;
		} catch (final UnsupportedEncodingException e) {
			ScaFileStore.DEBUG.catching(e);
			return EFS.getNullFileSystem().getStore(uri);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return this.entry.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileStore getParent() {
		final URI uri = this.entry.getParentUri();
		if (uri == null) {
			return null;
		}
		try {
			return EFS.getStore(uri);
		} catch (final CoreException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream openInputStream(final int options, final IProgressMonitor monitor) throws CoreException {
		return ScaFileSystemPlugin.getDefault().getFileCache().openInputStream(this, monitor);
	}

	@Override
	public OutputStream openOutputStream(final int options, IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		monitor = SubMonitor.convert(monitor, NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Task_Name, path), IProgressMonitor.UNKNOWN);
		try {
			final IFileInfo info = this.fetchInfo();
			boolean exists = info.exists();
			final boolean append = EFS.APPEND == (EFS.APPEND & options);

			if (info.getAttribute(EFS.ATTRIBUTE_READ_ONLY)) {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Error_Read_Only,
				        path)));
			}

			if (exists && !append) {
				this.delete(options, new SubProgressMonitor(monitor, 1));
				exists = false;
			}

			File file;
			if (!exists) {
				file = getScaFileSystem().create(path);
			} else {
				file = getScaFileSystem().open(this.entry.getAbsolutePath(), false);
			}

			return new ScaFileOutputStream(file, append);
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR,
			        ScaFileSystemPlugin.ID,
			        NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Error_Invalid_File_Name, path),
			        e));
		} catch (final FileException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Error_File_System,
			        path), e));
		} catch (final InvalidFilePointer e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Error_IO, path), e));
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Open_Output_Stream_Error_System, path), e));
		} finally {
			monitor.done();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI toURI() {
		return this.entry.getUri();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFileInfo[] childInfos(final int options, IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		monitor = SubMonitor.convert(monitor, Messages.ScaFileStore__Getting_Child_Info_Task, IProgressMonitor.UNKNOWN);
		try {
			if (!isDirectory()) {
				return FileStore.EMPTY_FILE_INFO_ARRAY;
			}
			final FileInformationType[] result = ProtectedThreadExecutor.submit(new Callable<FileInformationType[]>() {

				public FileInformationType[] call() throws Exception {
					return getScaFileSystem().list(path + "/*"); //$NON-NLS-1$
				}
			});
			final IFileInfo[] retVal = new IFileInfo[result.length];
			for (int i = 0; i < result.length; i++) {
				retVal[i] = translate(result[i]);
			}
			return retVal;
		} catch (final InterruptedException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Info_Error, path), e));
		} catch (final ExecutionException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Info_Error, path), e));
		} catch (final TimeoutException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Child_Info_Error, path), e));
		} finally {
			monitor.done();
		}
	}

	@Override
	public void delete(final int options, final IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		final SubMonitor subMonitor = SubMonitor.convert(monitor, NLS.bind(Messages.ScaFileStore__Deleting_Task, path), IProgressMonitor.UNKNOWN);
		try {
			if (!exists()) {
				return;
			}

			if (fetchInfo().isDirectory()) {
				final IFileStore[] childStore = childStores(EFS.NONE, subMonitor.newChild(1));
				final SubMonitor deleteChildrenMonitor = subMonitor.newChild(1);
				deleteChildrenMonitor.beginTask("Deleting contained items...", childStore.length);
				for (final IFileStore store : childStore) {
					store.delete(options, deleteChildrenMonitor.newChild(1));
				}
				getScaFileSystem().rmdir(path);
				subMonitor.worked(1);
			} else {
				getScaFileSystem().remove(path);
			}
		} catch (final FileException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Deleting_Error, path) + " " + e.msg, e));
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Deleting_Error_Invalid_File_Name, path)
			        + " " + e.msg, e));
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Deleting_Error_System, path), e));
		} finally {
			subMonitor.done();
		}
	}

	@Override
	public IFileStore mkdir(final int options, IProgressMonitor monitor) throws CoreException {
		final String path = this.entry.getAbsolutePath();
		final IFileInfo info = fetchInfo();
		if (info.exists()) {
			if (info.isDirectory()) {
				return this;
			} else {
				throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Mkdir_Error_File_Exists, path), null));
			}
		}
		monitor = SubMonitor.convert(monitor, NLS.bind(Messages.ScaFileStore__Mkdir_Task_Name, path), IProgressMonitor.UNKNOWN);
		try {
			getScaFileSystem().mkdir(path);
			return this;
		} catch (final FileException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Mkdir_Error_File_System, path), e));
		} catch (final InvalidFileName e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Mkdir_Error_Invalid_Name, path), e));
		} catch (final SystemException e) {
			throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, NLS.bind(Messages.ScaFileStore__Mkdi_Error_System, path), e));
		} finally {
			monitor.done();
		}
	}

	@Override
	public void putInfo(final IFileInfo info, final int options, final IProgressMonitor monitor) throws CoreException {
		ScaFileStore.DEBUG.enteringMethod(info, options, monitor);
		// Nothin to do here
	}

	@Override
	public java.io.File toLocalFile(final int options, final IProgressMonitor monitor) throws CoreException {
		return super.toLocalFile(options | EFS.CACHE, monitor);
	}

	@Override
	public void move(final IFileStore destination, final int options, final IProgressMonitor monitor) throws CoreException {
		if (destination instanceof ScaFileStore) {
			final ScaFileStore scaDest = (ScaFileStore) destination;
			final FileSystem fs = getScaFileSystem();
			final FileSystem destFs = scaDest.getScaFileSystem();
			if (fs != null && destFs != null && fs._is_equivalent(destFs)) {
				final String path = this.entry.getAbsolutePath();
				final String destPath = scaDest.entry.getAbsolutePath();
				try {
					fs.move(path, destPath);
				} catch (final InvalidFileName e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to move: " + path + " to " + destPath, e));
				} catch (final FileException e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to move: " + path + " to " + destPath, e));
				}
			}
		} else {
			super.move(destination, options, monitor);
		}
	}

	@Override
	protected void copyFile(final IFileInfo sourceInfo, final IFileStore destination, final int options, final IProgressMonitor monitor) throws CoreException {
		if (destination instanceof ScaFileStore) {
			final ScaFileStore scaDest = (ScaFileStore) destination;
			final FileSystem fs = getScaFileSystem();
			final FileSystem destFs = scaDest.getScaFileSystem();
			if (fs != null && destFs != null && fs._is_equivalent(destFs)) {
				final String path = this.entry.getAbsolutePath();
				final String destPath = scaDest.entry.getAbsolutePath();
				try {
					fs.copy(path, destPath);
				} catch (final InvalidFileName e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to copy: " + path + " to " + destPath, e));
				} catch (final FileException e) {
					throw new CoreException(new Status(IStatus.ERROR, ScaFileSystemPlugin.ID, "Failed to copy: " + path + " to " + destPath, e));
				}
			}
		} else {
			super.copyFile(sourceInfo, destination, options, monitor);
		}
	}

}
