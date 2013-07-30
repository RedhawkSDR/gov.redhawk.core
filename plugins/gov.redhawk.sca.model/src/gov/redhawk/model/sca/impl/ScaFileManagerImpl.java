/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaFileManager;
import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.emf.ecore.EClass;

import CF.File;
import CF.FileException;
import CF.FileManager;
import CF.FileManagerHelper;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Manager</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class ScaFileManagerImpl extends ScaFileSystemImpl<FileManager> implements ScaFileManager {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaFileManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_FILE_MANAGER;
	}

	@Override
	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.copy(sourceFileName, destinationFileName);
		// BEGIN GENERATED CODE
	}

	@Override
	public File create(final String fileName) throws InvalidFileName, FileException {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		return fileMgr.create(fileName);
		// BEGIN GENERATED CODE
	}

	@Override
	public boolean exists(final String fileName) throws InvalidFileName {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		return fileMgr.exists(fileName);
		// BEGIN GENERATED CODE
	}

	@Override
	public FileInformationType[] list(final String pattern) throws FileException, InvalidFileName {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		return fileMgr.list(pattern);
		// BEGIN GENERATED CODE
	}

	@Override
	public void mkdir(final String directoryName) throws InvalidFileName, FileException {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.mkdir(directoryName);
		// BEGIN GENERATED CODE
	}

	@Override
	public File open(final String fileName, final boolean read_Only) throws InvalidFileName, FileException {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		return fileMgr.open(fileName, read_Only);
		// BEGIN GENERATED CODE
	}

	@Override
	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.query(fileSystemProperties);
		// BEGIN GENERATED CODE

	}

	@Override
	public void remove(final String fileName) throws FileException, InvalidFileName {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.remove(fileName);
		// BEGIN GENERATED CODE
	}

	@Override
	public void rmdir(final String directoryName) throws InvalidFileName, FileException {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.rmdir(directoryName);
		// BEGIN GENERATED CODE
	}

	/**
     * @since 14.0
     */
	public MountType[] getMounts() {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		return fileMgr.getMounts();
		// BEGIN GENERATED CODE
	}

	/**
     * @since 14.0
     */
	public void mount(final String mountPoint, final FileSystem file_System) throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.mount(mountPoint, file_System);
		// BEGIN GENERATED CODE
	}

	public void unmount(final String mountPoint) throws NonExistentMount {
		// END GENERATED CODE
		final FileManager fileMgr = fetchNarrowedObject(null);
		if (fileMgr == null) {
			throw new IllegalStateException();
		}
		fileMgr.unmount(mountPoint);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 * {@inheritDoc}
	 */
	@Override
	protected FileManager narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return FileManagerHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}
	
	@Override
	protected Class<FileManager> getCorbaType() {
	    return FileManager.class;
	}
} //ScaFileManagerImpl
