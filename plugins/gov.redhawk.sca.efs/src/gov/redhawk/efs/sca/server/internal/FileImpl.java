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
package gov.redhawk.efs.sca.server.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import CF.ErrorNumberType;
import CF.FileException;
import CF.FilePOA;
import CF.OctetSequenceHolder;
import CF.FilePackage.IOException;
import CF.FilePackage.InvalidFilePointer;

// TODO: Auto-generated Javadoc
/**
 * The Class FileImpl.
 */
public class FileImpl extends FilePOA {

	/** The Constant EMPTY_BYTE_ARRAY. */
	private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

	/** The file. */
	private final File file;

	/** The file access. */
	private RandomAccessFile fileAccess;

	/**
	 * Instantiates a new file impl.
	 * 
	 * @param file the file
	 * @param readOnly the read only
	 * 
	 * @throws FileNotFoundException the file not found exception
	 */
	public FileImpl(final File file, final boolean readOnly) throws FileNotFoundException {
		this.file = file;
		if (readOnly) {
			this.fileAccess = new RandomAccessFile(this.file, "r");
		} else {
			this.fileAccess = new RandomAccessFile(this.file, "rw");
		}

	}

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#close()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws FileException {
		if (this.fileAccess != null) {
			try {
				this.fileAccess.close();
			} catch (final java.io.IOException e) {
				throw new FileException(ErrorNumberType.CF_EIO, e.getMessage());
			}
			this.fileAccess = null;
		}
	}

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#fileName()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String fileName() {
		return this.file.getName();
	}

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#filePointer()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int filePointer() {
		if (this.fileAccess != null) {
			try {
				return (int) this.fileAccess.getFilePointer();
			} catch (final java.io.IOException e) {
				// PASS
			}
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#read(mil.jpeojtrs.sca.cf.OctetSequenceHolder, int)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void read(final OctetSequenceHolder data, final int length) throws IOException {
		if (this.fileAccess != null) {
			final byte[] buffer = new byte[length];
			try {
				final int read = this.fileAccess.read(buffer);
				if (read == -1) {
					data.value = FileImpl.EMPTY_BYTE_ARRAY;
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

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#setFilePointer(int)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFilePointer(final int filePointer) throws InvalidFilePointer, FileException {
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

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#sizeOf()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int sizeOf() throws FileException {
		return (int) this.file.length();
	}

	/* (non-Javadoc)
	 * @see mil.jpeojtrs.sca.cf.FileOperations#write(byte[])
	 */
	/**
	 * {@inheritDoc}
	 */
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
