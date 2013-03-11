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

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * 
 */
public abstract class AbstractFileSystem implements FileSystemOperations {

	public static enum ScaFileInformationDataType {
		CREATED_TIME, MODIFIED_TIME, LAST_ACCESS_TIME, IOR_AVAILABLE, READ_ONLY
	}

	protected final POA poa;
	protected final ORB orb;

	public AbstractFileSystem(final ORB orb, final POA poa) {
		this.orb = orb;
		this.poa = poa;
	}

	/**
	 * {@inheritDoc}
	 */
	public void remove(final String fileName) throws FileException, InvalidFileName {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	public void move(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	public File create(final String fileName) throws InvalidFileName, FileException {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	public void mkdir(final String directoryName) throws InvalidFileName, FileException {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	public void rmdir(final String directoryName) throws InvalidFileName, FileException {
		throw new FileException(ErrorNumberType.CF_ENOTSUP, "Write operations not supported");
	}

	/**
	 * {@inheritDoc}
	 */
	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		// Do nothing

	}

}
