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

import java.io.File;

import org.anarres.cpp.VirtualFile;
import org.anarres.cpp.VirtualFileSystem;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.Path;

public class FileStoreVirtualFileSystem implements VirtualFileSystem {

	private final IFileSystem fileSystem;

	public FileStoreVirtualFileSystem(final IFileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	public VirtualFile getFile(final String arg0) {
		final IFileStore store = this.fileSystem.getStore(new Path(arg0));
		return getFile(store);
	}

	public VirtualFile getFile(final IFileStore store) {
		return new FileStoreVirtualFile(store);
	}

	public VirtualFile getFile(final String arg0, final String arg1) {
		final IFileStore store = this.fileSystem.getStore(new Path(arg0 + File.separator + arg1));
		return getFile(store);
	}

}
