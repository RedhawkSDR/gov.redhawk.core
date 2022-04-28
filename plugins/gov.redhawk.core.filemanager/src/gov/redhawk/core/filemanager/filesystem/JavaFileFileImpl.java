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
import java.io.RandomAccessFile;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;

import CF.ErrorNumberType;
import CF.FileException;
import CF.FilePOA;
import CF.OctetSequenceHolder;
import CF.FilePackage.IOException;
import CF.FilePackage.InvalidFilePointer;
import gov.redhawk.core.filemanager.FileManagerPlugin;

public class JavaFileFileImpl extends FilePOA {

	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	private final File file;

	private RandomAccessFile fileAccess;

	public JavaFileFileImpl(final File file, final boolean readOnly) throws FileNotFoundException {
		this.file = file;
		if (readOnly) {
			this.fileAccess = new RandomAccessFile(this.file, "r");
		} else {
			this.fileAccess = new RandomAccessFile(this.file, "rw");
		}

	}

	@Override
	public void close() throws FileException {
		// Deactivate the object so it will stop handling CORBA requests
		try {
			_poa().deactivate_object(_object_id());
		} catch (UserException e) {
			FileManagerPlugin.logError("Unable to deactivate file object", e);
		} catch (SystemException e) {
			FileManagerPlugin.logError("Unable to deactivate file object", e);
		}

		if (this.fileAccess != null) {
			try {
				this.fileAccess.close();
			} catch (final java.io.IOException e) {
				throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
			}
			this.fileAccess = null;
		}
	}

	@Override
	public String fileName() {
		return this.file.getName();
	}

	@Override
	public long filePointer() {
		if (this.fileAccess != null) {
			try {
				return (long) this.fileAccess.getFilePointer();
			} catch (final java.io.IOException e) {
				// PASS
			}
		}
		return 0;
	}

	@Override
	public void read(final OctetSequenceHolder data, final long length) throws IOException {
		if (this.fileAccess != null) {
			final byte[] buffer = new byte[new Long(length).intValue()];
			try {
				final int read = this.fileAccess.read(buffer);
				if (read == -1) {
					data.value = JavaFileFileImpl.EMPTY_BYTE_ARRAY;
				} else {
					final byte[] readData = new byte[read];
					System.arraycopy(buffer, 0, readData, 0, read);
					data.value = readData;
				}
			} catch (final java.io.IOException e) {
				throw new IOException(ErrorNumberType.CF_EIO, e.getMessage());
			}
		} else {
			throw new IOException(ErrorNumberType.CF_EIO, "File is closed.");
		}
	}

	@Override
	public void setFilePointer(final long filePointer) throws InvalidFilePointer, FileException {
		if (this.fileAccess != null) {
			try {
				this.fileAccess.seek(filePointer);
			} catch (final java.io.IOException e) {
				throw new InvalidFilePointer(e.getMessage());
			}
		} else {
			throw new InvalidFilePointer("File is closed.");
		}
	}

	@Override
	public long sizeOf() throws FileException {
		return (long) this.file.length();
	}

	@Override
	public void write(final byte[] data) throws IOException {
		if (this.fileAccess != null) {
			try {
				this.fileAccess.write(data);
			} catch (final java.io.IOException e) {
				throw new IOException(ErrorNumberType.CF_EIO, e.getMessage());
			}
		} else {
			throw new IOException(ErrorNumberType.CF_EIO, "File is closed.");
		}
	}

}
