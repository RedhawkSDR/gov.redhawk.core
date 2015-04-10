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

import org.anarres.cpp.InputLexerSource;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;

public class FileStoreSource extends InputLexerSource {
	private final IFileStore store;

	public FileStoreSource(final IFileStore store) throws IOException, CoreException {
		super(store.openInputStream(0, null));
		this.store = store;
	}

	/**
	 * @since 8.0
	 */
	@Override
	public String getPath() {
		super.getPath();
		return this.store.toURI().getPath();
	}

	/**
	 * @since 8.0
	 */
	@Override
	public String getName() {
		return this.store.getName();
	}

	/**
	 * @since 2.0
	 */
	public IFileStore getStore() {
		return this.store;
	}
}
