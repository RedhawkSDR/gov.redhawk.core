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
package gov.redhawk.core.filemanager.filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.DataType;
import CF.ErrorNumberType;
import CF.FileException;
import CF.FileHelper;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import CF.FileSystemPackage.UnknownFileSystemProperties;
import gov.redhawk.core.filemanager.FileManagerPlugin;

/**
 * A {@link CF.FileSystem} for the local file system (using <code>java.io.file</code>, <code>java.nio.file</code>).
 */
public class JavaFileSystem extends AbstractFileSystem {

	private final File root;

	public JavaFileSystem(final ORB orb, final POA poa, final File root) {
		super(orb, poa);
		this.root = root;
	}

	@Override
	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		File sourceFile = precheck(sourceFileName);
		File destinationFile = precheck(destinationFileName);
		try {
			if (!sourceFile.exists()) {
				if (Files.notExists(sourceFile.toPath())) {
					throw new FileException(ErrorNumberType.CF_ENOENT, "No such source file or directory");
				} else {
					throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
				}
			}
			if (sourceFile.isDirectory()) {
				throw new FileException(ErrorNumberType.CF_EISDIR, "Source is a directory");
			}
			Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (DirectoryNotEmptyException e) {
			throw new FileException(ErrorNumberType.CF_ENOTEMPTY, "Target directory not empty");
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (final IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		}
	}

	@Override
	public void move(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		File sourceFile = precheck(sourceFileName);
		File destinationFile = precheck(destinationFileName);
		try {
			if (!sourceFile.exists()) {
				if (Files.notExists(sourceFile.toPath())) {
					throw new FileException(ErrorNumberType.CF_ENOENT, "No such source file or directory");
				} else {
					throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
				}
			}
			Files.move(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (DirectoryNotEmptyException e) {
			throw new FileException(ErrorNumberType.CF_ENOTEMPTY, "Target directory not empty");
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		}
	}

	@Override
	public CF.File create(final String fileName) throws InvalidFileName, FileException {
		File file = precheck(fileName);
		try {
			Files.createFile(file.toPath());
			final JavaFileFileImpl impl = new JavaFileFileImpl(file, false);
			final byte[] id = this.poa.activate_object(impl);
			return FileHelper.narrow(this.poa.id_to_reference(id));
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (FileAlreadyExistsException e) {
			throw new FileException(ErrorNumberType.CF_EEXIST, "File already exists");
		} catch (final IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		} catch (final ServantAlreadyActive | WrongPolicy | ObjectNotActive e) {
			throw new FileException(ErrorNumberType.CF_NOTSET, e.getMessage());
		}
	}

	@Override
	public boolean exists(final String fileName) throws InvalidFileName {
		File file = precheck(fileName);
		try {
			return file.exists();
		} catch (SecurityException e) {
			throw new InvalidFileName(ErrorNumberType.CF_EACCES, e.getMessage());
		}
	}

	@Override
	public FileInformationType[] list(final String pattern) throws FileException, InvalidFileName {
		if (pattern == null) {
			throw new InvalidFileName(ErrorNumberType.CF_ENOENT, "No such file");
		}

		// Special case - an empty pattern is the way you get info for the root directory itself
		if (pattern.trim().isEmpty()) {
			return new FileInformationType[] { getFileInformationType(this.root.toPath()) };
		}

		IPath pathWithPattern = new Path(pattern).makeUNC(false);
		if (!pathWithPattern.isAbsolute()) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Path is not absolute");
		}

		// If they've asked for the root, or a path ending with a separator, give the contents of that directory
		if (pathWithPattern.segmentCount() == 0 || pathWithPattern.hasTrailingSeparator()) {
			pathWithPattern = pathWithPattern.append("*");
		}

		// Find files/dirs matching the pattern in the appropriate directory
		final File parentPath = new File(this.root, pathWithPattern.removeLastSegments(1).toString());
		final String filePatternOnly = escapePattern(pathWithPattern.lastSegment());
		List<FileInformationType> fileInfos = new ArrayList<FileInformationType>();
		try (DirectoryStream<java.nio.file.Path> directory = Files.newDirectoryStream(parentPath.toPath(), filePatternOnly)) {
			for (java.nio.file.Path directoryEntry : directory) {
				fileInfos.add(getFileInformationType(directoryEntry));
			}
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		}
		return fileInfos.toArray(new FileInformationType[fileInfos.size()]);
	}

	/**
	 * Escapes characters which are special file glob characters for
	 * {@link Files#newDirectoryStream(java.nio.file.Path, String)} but are normal characters for
	 * {@link FileSystem#list(String)}.
	 * @return
	 */
	private String escapePattern(String pattern) {
		// Escape these characters: [ ] { }
		return pattern.replaceAll("(\\[|\\]|\\{|\\})", "\\\\$1");
	}

	/**
	 * Gets a {@link CF.FileSystemPackage.FileInformationType} for the given path. If the target is unreadable, as
	 * much informatino as possible will be returned.
	 * @param file The file to return information for
	 * @return
	 */
	private FileInformationType getFileInformationType(java.nio.file.Path file) {
		// Basic info
		final FileInformationType fileInfo = new FileInformationType();
		if (file.getNameCount() == 0) {
			fileInfo.name = "/";
		} else {
			fileInfo.name = file.getFileName().toString();
		}

		// Get file attributes
		BasicFileAttributes attrs = null;
		try {
			attrs = Files.readAttributes(file, BasicFileAttributes.class);
		} catch (AccessDeniedException | NoSuchFileException e) {
			// Try to read without following symlinks. If that works, the file is a symlink that points at something
			// we either can't read (AccessDeniedException), or doesn't exist (NoSuchFileException). We'll try to
			// report on the symlink itself.
			try {
				attrs = Files.readAttributes(file, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			} catch (IOException e2) {
				// Just return basic information
				fileInfo.kind = FileType.PLAIN;
				fileInfo.size = 0;
				return fileInfo;
			}
		} catch (IOException e) {
			// Just return basic information
			fileInfo.kind = FileType.PLAIN;
			fileInfo.size = 0;
			return fileInfo;
		}

		// Basic attributes
		if (attrs.isDirectory()) {
			fileInfo.kind = FileType.DIRECTORY;
		} else {
			fileInfo.kind = FileType.PLAIN;
		}
		fileInfo.size = attrs.size();

		// Properties
		List<DataType> properties = new ArrayList<DataType>();
		long time = attrs.creationTime().to(TimeUnit.SECONDS);
		if (time > 0) {
			Any any = this.orb.create_any();
			any.insert_ulonglong(time);
			properties.add(new DataType("CREATED_TIME", any));
		}
		time = attrs.lastModifiedTime().to(TimeUnit.SECONDS);
		if (time > 0) {
			Any any = this.orb.create_any();
			any.insert_ulonglong(time);
			properties.add(new DataType("MODIFIED_TIME", any));
		}
		time = attrs.lastAccessTime().to(TimeUnit.SECONDS);
		if (time > 0) {
			Any any = this.orb.create_any();
			any.insert_ulonglong(time);
			properties.add(new DataType("LAST_ACCESS_TIME", any));
		}

		boolean writeable = Files.isWritable(file);
		Any writeableAny = this.orb.create_any();
		writeableAny.insert_boolean(!writeable);
		properties.add(new DataType("READ_ONLY", writeableAny));

		boolean executable = Files.isExecutable(file);
		Any executableAny = this.orb.create_any();
		executableAny.insert_boolean(executable);
		properties.add(new DataType("EXECUTABLE", executableAny));

		fileInfo.fileProperties = properties.toArray(new DataType[properties.size()]);
		return fileInfo;
	}

	@Override
	public void mkdir(final String directoryName) throws InvalidFileName, FileException {
		File directory = precheck(directoryName);
		try {
			Files.createDirectory(directory.toPath());
		} catch (FileAlreadyExistsException e) {
			throw new FileException(ErrorNumberType.CF_EEXIST, "File exists");
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (IOException e) {
			throw new FileException(ErrorNumberType.CF_NOTSET, e.getMessage());
		}
		new File(this.root, directoryName).mkdir();
	}

	@Override
	public CF.File open(final String fileName, final boolean readOnly) throws InvalidFileName, FileException {
		File file = precheck(fileName);
		try {
			if (!file.exists()) {
				if (Files.notExists(file.toPath())) {
					throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
				} else {
					throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
				}
			}
			if (file.isDirectory()) {
				throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
			}
			if (!file.canRead() || (!readOnly && !file.canWrite())) {
				throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
			}
			final JavaFileFileImpl impl = new JavaFileFileImpl(file, readOnly);
			final byte[] id = this.poa.activate_object(impl);
			return FileHelper.narrow(this.poa.id_to_reference(id));
		} catch (FileNotFoundException e) {
			throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (final ServantAlreadyActive | WrongPolicy | ObjectNotActive e) {
			throw new FileException(ErrorNumberType.CF_NOTSET, e.getMessage());
		}
	}

	@Override
	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		if (fileSystemProperties.value.length == 0) {
			fileSystemProperties.value = new DataType[] { new DataType("SIZE", null), new DataType("AVAILABLE_SPACE", null) };
		}
		final List<DataType> unknownProperties = new ArrayList<DataType>();
		for (final DataType dataType : fileSystemProperties.value) {
			if (dataType.id.equals("SIZE")) {
				final Any any = this.orb.create_any();
				try {
					FileStore fileStore = Files.getFileStore(this.root.toPath());
					any.insert_ulonglong(fileStore.getTotalSpace());
				} catch (IOException e) {
					FileManagerPlugin.logError("Unable to get capacity of root " + this.root.toString(), e);
					any.insert_ulonglong(0);
				}
				dataType.value = any;
			} else if (dataType.id.equals("AVAILABLE_SPACE")) {
				final Any any = this.orb.create_any();
				try {
					FileStore fileStore = Files.getFileStore(this.root.toPath());
					any.insert_ulonglong(fileStore.getUsableSpace());
				} catch (final IOException e) {
					FileManagerPlugin.logError("Unable to get available space of root " + this.root.toString(), e);
					any.insert_ulonglong(0);
				}
				dataType.value = any;
			} else {
				unknownProperties.add(dataType);
			}
		}
		if (unknownProperties.size() > 0) {
			throw new UnknownFileSystemProperties(unknownProperties.toArray(new DataType[unknownProperties.size()]));
		}
	}

	@Override
	public void remove(final String fileName) throws FileException, InvalidFileName {
		File file = precheck(fileName);
		try {
			if (!file.exists()) {
				if (Files.notExists(file.toPath())) {
					throw new FileException(ErrorNumberType.CF_ENOENT, "No such file");
				} else {
					throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
				}
			}
			if (file.isDirectory()) {
				throw new FileException(ErrorNumberType.CF_EISDIR, "Is a directory");
			}
			Files.delete(file.toPath());
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (IOException e) {
			throw new FileException(ErrorNumberType.CF_NOTSET, e.getMessage());
		}
	}

	@Override
	public void rmdir(final String directoryName) throws InvalidFileName, FileException {
		File directory = precheck(directoryName);
		try {
			if (!directory.exists()) {
				if (Files.notExists(directory.toPath())) {
					throw new FileException(ErrorNumberType.CF_ENOENT, "No such directory");
				} else {
					throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
				}
			}
			if (!directory.isDirectory()) {
				throw new FileException(ErrorNumberType.CF_ENOTDIR, "Not a directory");
			}
			Files.delete(directory.toPath());
		} catch (DirectoryNotEmptyException e) {
			throw new FileException(ErrorNumberType.CF_ENOTEMPTY, "Directory not empty");
		} catch (AccessDeniedException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, "Permission denied");
		} catch (SecurityException e) {
			throw new FileException(ErrorNumberType.CF_EACCES, e.getMessage());
		} catch (IOException e) {
			throw new FileException(ErrorNumberType.CF_NOTSET, e.getMessage());
		}
	}

	public void dispose() {
	}

	/**
	 * Checks that the path is non-empty and absolute. Returns a {@link File}.
	 * @param path The path to check
	 * @return A {@link java.io.File} object
	 * @throws InvalidFileName The path is null, empty, or not absolute
	 */
	private File precheck(String path) throws InvalidFileName {
		if (path == null || path.trim().isEmpty()) {
			throw new InvalidFileName(ErrorNumberType.CF_ENOENT, "No such file or directory");
		}
		File file = new File(path);
		if (!file.isAbsolute()) {
			throw new InvalidFileName(ErrorNumberType.CF_EINVAL, "Path is not absolute");
		}
		return new File(this.root, path);
	}

}
