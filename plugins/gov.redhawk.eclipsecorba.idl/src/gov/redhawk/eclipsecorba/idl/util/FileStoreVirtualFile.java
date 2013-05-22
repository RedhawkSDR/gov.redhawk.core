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
package gov.redhawk.eclipsecorba.idl.util;

import java.io.IOException;

import org.anarres.cpp.Source;
import org.anarres.cpp.VirtualFile;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;

public class FileStoreVirtualFile implements VirtualFile {

	private final IFileStore store;

	public FileStoreVirtualFile(final IFileStore store) {
		this.store = store;
	}

	public VirtualFile getChildFile(final String arg0) {
		final IFileStore child = this.store.getChild(arg0);
		return new FileStoreVirtualFile(child);
	}

	public String getName() {
		return this.store.getName();
	}

	public VirtualFile getParentFile() {
		return new FileStoreVirtualFile(this.store.getParent());
	}

	public String getPath() {
		final URI uri = URI.createURI(this.store.toURI().toString());
		return uri.devicePath();
	}

	/**
	 * @since 2.0
	 */
	public IFileStore getStore() {
		return this.store;
	}

	public Source getSource() throws IOException {
		try {
			return new FileStoreSource(this.store);
		} catch (final CoreException e) {
			throw new IdlResourceParseException(e);
		}
	}

	public boolean isFile() {
		if (!this.store.fetchInfo().exists()) {
			return false;
		}
		return !this.store.fetchInfo().isDirectory();
	}

	@Override
	public String toString() {
		return this.store.toString();
	}

}
