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

import java.io.IOException;
import java.io.OutputStream;

import org.omg.CORBA.SystemException;

import CF.File;
import CF.FileException;
import CF.FilePackage.InvalidFilePointer;

public class ScaFileOutputStream extends OutputStream {
	private File file;

	public ScaFileOutputStream(final File file, final boolean append) throws InvalidFilePointer, FileException {
		this.file = file;
		if (append) {
			this.file.setFilePointer(file.sizeOf());
		}
	}

	/**
	 * Follow the semantics of other streams (i.e. FileOutputStream) where
	 * finalize() closes() and cleans up everything.
	 */
	@Override
	protected void finalize() throws Throwable {
		super.close();
		super.finalize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() throws IOException {
		try {
			if (this.file != null) {
				this.file.close();
			}
			super.close();
		} catch (final FileException e) {
			IOException ex = new IOException(e.getMessage() + ": " + e.msg);
			ex.initCause(e);
			throw ex;
		} catch (final SystemException e) {
			IOException ex = new IOException(e.getMessage());
			ex.initCause(e);
			throw ex;
		} finally {
			if (this.file != null) {
				this.file._release();
			}
			this.file = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final byte[] b, final int off, final int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return;
		}

		final byte[] data = copyOfRange(b, off, off + len);

		try {
			this.file.write(data);
		} catch (final CF.FilePackage.IOException e) {
			IOException ex = new IOException(e.getMessage() + ": " + e.msg);
			ex.initCause(e);
			throw ex;
		}
	}

	private byte[] copyOfRange(final byte[] original, final int from, final int to) {
		final int newLength = to - from;
		if (newLength < 0) {
			throw new IllegalArgumentException(from + " > " + to); //$NON-NLS-1$
		}
		final byte[] copy = new byte[newLength];
		System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
		return copy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(final int b) throws IOException {
		final byte[] data = new byte[1];
		data[0] = (byte) b;
		write(data, 0, 1);
	}

}
