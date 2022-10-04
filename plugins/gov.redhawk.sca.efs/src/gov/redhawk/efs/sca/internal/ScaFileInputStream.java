/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.efs.sca.internal;

import gov.redhawk.sca.util.ORBUtil;

import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA.SystemException;

import CF.File;
import CF.FileException;
import CF.OctetSequenceHolder;

public class ScaFileInputStream extends InputStream {

	private File file;

	public ScaFileInputStream(final File file) {
		if (file == null) {
			throw new NullPointerException();
		}
		this.file = file;
	}

	@Override
	protected void finalize() throws Throwable {
		// Follow the semantics of other streams (i.e. FileOutputStream) where finalize() closes() and cleans up
		// everything.
		try {
			close();
		} finally {
			super.finalize();
		}
	}

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
				ORBUtil.release(this.file);
			}
			this.file = null;
		}
	}

	@Override
	public int read(final byte[] b, final int off, final int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		} else if (this.file == null) {
			throw new IOException("Stream Closed");
		}

		final OctetSequenceHolder buffer = new OctetSequenceHolder(new byte[0]);

		try {
			this.file.read(buffer, len);
			if (buffer.value.length == 0) {
				return -1;
			} else {
				System.arraycopy(buffer.value, 0, b, off, buffer.value.length);
				return buffer.value.length;
			}
		} catch (final CF.FilePackage.IOException e) {
			IOException ex = new IOException(e.getMessage() + ": " + e.msg);
			ex.initCause(e);
			throw ex;
		}
	}

	@Override
	public int available() throws IOException {
		try {
			final long value = this.file.sizeOf();
			if (value < 0) {
				return Integer.MAX_VALUE;
			} else {
				return new Long(value).intValue();
			}
		} catch (final FileException e) {
			IOException ex = new IOException(e.getMessage() + ": " + e.msg);
			ex.initCause(e);
			throw ex;
		}
	}

	@Override
	public int read() throws IOException {
		if (this.file == null) {
			throw new IOException("Stream Closed");
		}

		try {
			final OctetSequenceHolder buffer = new OctetSequenceHolder(new byte[1]);
			this.file.read(buffer, 1);
			if (buffer.value.length > 0) {
				return buffer.value[0];
			} else {
				return -1;
			}
		} catch (final CF.FilePackage.IOException e) {
			IOException ex = new IOException(e.getMessage() + ": " + e.msg);
			ex.initCause(e);
			throw ex;
		}
	}
}
