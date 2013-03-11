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
package gov.redhawk.core.filemanager.filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.DataType;
import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileHelper;
import CF.InvalidFileName;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

/**
 * 
 */
public class FileStoreFileSystem extends AbstractFileSystem {

	private final IFileStore root;

	public FileStoreFileSystem(final ORB orb, final POA poa, final IFileStore root) {
		super(orb, poa);
		this.root = root;
	}

	public boolean exists(final String fileName) throws InvalidFileName {
		final IFileStore store = this.root.getFileStore(new Path(fileName));
		if (store.fetchInfo().exists()) {
			return true;
		}
		return false;
	}

	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		if (pattern == null) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "File does not exist");
		}
		if (pattern.length() == 0 || pattern.equals("/")) {
			final FileInformationType info = new FileInformationType();
			info.fileProperties = createDataTypeArray(this.root);
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
			final List<FileInformationType> retVal = list(Arrays.asList(pattern.split("/")), this.root);
			return retVal.toArray(new FileInformationType[retVal.size()]);
		}

	}

	public List<FileInformationType> list(final List<String> pattern, final IFileStore node) throws FileException, InvalidFileName {
		if (pattern.isEmpty()) {
			return Collections.singletonList(createFileInformationType(node));
		}
		try {
			final IFileStore[] children = node.childStores(0, null);
			final String regex = pattern.get(0).replaceAll("\\*", ".*");
			final List<FileInformationType> retVal = new ArrayList<FileInformationType>();
			for (final IFileStore store : children) {
				if (store.getName().matches(regex)) {
					if (pattern.size() == 1) {
						retVal.add(createFileInformationType(store));
					} else {
						retVal.addAll(list(pattern.subList(1, pattern.size()), store));
					}
				}
			}
			return retVal;
		} catch (final CoreException e) {
			throw new FileException(ErrorNumberType.CF_EIO, "File error: " + e.getMessage());
		}
	}

	private DataType[] createDataTypeArray(final IFileStore store) {
		final IFileInfo info = store.fetchInfo();
		final int lastModified = (int) (info.getLastModified() / 1000);
		final Any anyLastModified = this.orb.create_any();
		anyLastModified.insert_long(lastModified);
		final Any readOnly = this.orb.create_any();
		readOnly.insert_boolean(info.getAttribute(EFS.ATTRIBUTE_READ_ONLY));

		return new DataType[] {
		        new DataType(ScaFileInformationDataType.MODIFIED_TIME.name(), anyLastModified),
		        new DataType(ScaFileInformationDataType.READ_ONLY.name(), readOnly)
		};

	}

	private FileInformationType createFileInformationType(final IFileStore store) {
		final FileInformationType info = new FileInformationType();
		info.fileProperties = createDataTypeArray(store);
		final IFileInfo storeInfo = store.fetchInfo();
		if (storeInfo.isDirectory()) {
			info.kind = FileType.DIRECTORY;
		} else {
			info.kind = FileType.PLAIN;
		}
		info.name = store.getName();
		info.size = storeInfo.getLength();

		return info;
	}

	public File open(final String fileName, final boolean readOnly) throws InvalidFileName, FileException {
		final IFileStore store = this.root.getFileStore(new Path(fileName));
		try {
			final FileStoreFileImpl stub = new FileStoreFileImpl(store, readOnly);
			return FileHelper.narrow(this.poa.servant_to_reference(stub));
		} catch (final CoreException e) {
			throw new FileException(ErrorNumberType.CF_EIO, "Failed to open: " + e.getMessage());
		} catch (final ServantNotActive e) {
			throw new FileException(ErrorNumberType.CF_EIO, "Failed to open: " + e.getMessage());
		} catch (final WrongPolicy e) {
			throw new FileException(ErrorNumberType.CF_EIO, "Failed to open: " + e.getMessage());
		}
	}

}
