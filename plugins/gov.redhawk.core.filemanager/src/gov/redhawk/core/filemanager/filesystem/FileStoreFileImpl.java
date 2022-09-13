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

import gov.redhawk.core.filemanager.FileManagerPlugin;

import java.io.InputStream;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import CF.ErrorNumberType;
import CF.FileException;
import CF.FilePOA;
import CF.OctetSequenceHolder;
import CF.FilePackage.IOException;
import CF.FilePackage.InvalidFilePointer;

/**
 * 
 */
public class FileStoreFileImpl extends FilePOA {

	private final IFileStore store;
	private final InputStream inputStream;
	private int pointer;

	public FileStoreFileImpl(final IFileStore store, final boolean readOnly) throws CoreException {
		this.store = store;
		if (!readOnly) {
			throw new CoreException(new Status(IStatus.ERROR, FileManagerPlugin.ID, "File store file system does not support write opperations", null));
		}
		this.inputStream = store.openInputStream(0, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fileName() {
		return this.store.getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long filePointer() {
		return this.pointer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(final OctetSequenceHolder data, final long length) throws IOException {
		final byte[] buffer = new byte[new Long(length).intValue()];
		try {
			final int read = this.inputStream.read(buffer);
			if (read == -1) {
				data.value = new byte[0];
			} else if (read != length) {
				final byte[] subBuffer = new byte[read];
				System.arraycopy(buffer, 0, subBuffer, 0, subBuffer.length);
				data.value = subBuffer;
			} else {
				data.value = buffer;
			}
			this.pointer += length;
		} catch (final java.io.IOException e) {
			throw new IOException(ErrorNumberType.CF_EIO, e.getMessage());
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] data) throws IOException {
		throw new IOException(ErrorNumberType.CF_ENOTSUP, "Does not support write operations");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long sizeOf() throws FileException {
		return (long) this.store.fetchInfo().getLength();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws FileException {
		try {
			this.inputStream.close();
			this.pointer = -1;
		} catch (final java.io.IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFilePointer(final long filePointer) throws InvalidFilePointer, FileException {
		try {
			long actualSkiped = this.inputStream.skip(filePointer);
			this.pointer += actualSkiped;
		} catch (final java.io.IOException e) {
			throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
		}

	}

}
