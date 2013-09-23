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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.osgi.framework.Bundle;

import CF.DataType;
import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileHelper;
import CF.InvalidFileName;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

public class BundleFileSystem extends AbstractFileSystem {

	private final Bundle bundle;
	private final IPath basePath;

	public BundleFileSystem(final ORB orb, final POA poa, final Bundle bundle, final IPath path) {
		super(orb, poa);
		this.bundle = bundle;
		this.basePath = path;
	}

	@Override
	public boolean exists(final String fileName) throws InvalidFileName {
		return FileLocator.find(this.bundle, this.basePath.append(fileName), null) != null;
	}

	@Override
	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		if (pattern == null) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File does not exist");
		}
		if (pattern.length() == 0 || pattern.equals("/")) {
			final FileInformationType info = new FileInformationType();
			java.io.File file;
			try {
				file = getFile(this.basePath);
			} catch (final URISyntaxException e) {
				throw new FileException(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
			} catch (final IOException e) {
				throw new FileException(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
			}
			info.fileProperties = createDataTypeArray(file);
			info.kind = FileType.FILE_SYSTEM;
			info.name = "/";
			info.size = 0;
			return new FileInformationType[] {
				info
			};
		} else {
			if (pattern.charAt(0) == '/') {
				pattern = pattern.substring(1);
			}
			final List<FileInformationType> retVal = list(Arrays.asList(pattern.split("/")), this.basePath);
			return retVal.toArray(new FileInformationType[retVal.size()]);
		}

	}

	private java.io.File getFile(final IPath path) throws URISyntaxException, IOException {
		return new java.io.File(FileLocator.toFileURL(FileLocator.find(this.bundle, path, null)).toURI());
	}

	public List<FileInformationType> list(final List<String> pattern, final IPath node) throws FileException, InvalidFileName {
		try {
			if (pattern.isEmpty()) {
				return Collections.singletonList(createFileInformationType(getFile(node)));
			}
			final String regex = pattern.get(0);
			final Enumeration< ? > entries = this.bundle.findEntries(node.toPortableString(), regex, false);

			final List<FileInformationType> retVal = new ArrayList<FileInformationType>();
			while (entries.hasMoreElements()) {
				final URL url = (URL) entries.nextElement();
				final Path path = new Path(url.getPath());
				if (pattern.size() == 1) {
					retVal.add(createFileInformationType(getFile(path)));
				} else {
					retVal.addAll(list(pattern.subList(1, pattern.size()), path));
				}
			}
			return retVal;
		} catch (final URISyntaxException e) {
			throw new FileException(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		} catch (final IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		}
	}

	private DataType[] createDataTypeArray(final java.io.File file) {

		final int lastModified = (int) (file.lastModified() / 1000);
		final Any anyLastModified = this.orb.create_any();
		anyLastModified.insert_long(lastModified);
		final Any readOnly = this.orb.create_any();
		readOnly.insert_boolean(!file.canWrite());

		return new DataType[] {
		        new DataType(ScaFileInformationDataType.MODIFIED_TIME.name(), anyLastModified),
		        new DataType(ScaFileInformationDataType.READ_ONLY.name(), readOnly)
		};

	}

	private FileInformationType createFileInformationType(final java.io.File file) {
		final FileInformationType info = new FileInformationType();
		info.fileProperties = createDataTypeArray(file);
		if (file.isDirectory()) {
			info.kind = FileType.DIRECTORY;
		} else {
			info.kind = FileType.PLAIN;
		}
		info.name = file.getName();
		info.size = file.length();

		return info;
	}

	@Override
	public File open(final String fileName, final boolean readOnly) throws InvalidFileName, FileException {
		try {
			final JavaFileFileImpl impl = new JavaFileFileImpl(new java.io.File(FileLocator.toFileURL(FileLocator.find(this.bundle,
			        this.basePath.append(fileName),
			        null)).toURI()), readOnly);
			final org.omg.CORBA.Object ref = this.poa.servant_to_reference(impl);
			return FileHelper.narrow(ref);
		} catch (final URISyntaxException e) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "Invalid file URI: " + e.getMessage());
		} catch (final IOException e) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		} catch (final ServantNotActive e) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		} catch (final WrongPolicy e) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		}
	}

}
