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
import java.io.InputStream;

import org.anarres.cpp.InputLexerSource;
import org.eclipse.emf.common.util.URI;

public class UriStoreSource extends InputLexerSource {
	private final URI uri;

	public UriStoreSource(final URI uri, final InputStream input) throws IOException {
		super(input);
		this.uri = uri;
	}

	@Override
	protected String getPath() {
		final URI tmpUri = URI.createURI(this.uri.toString());
		return tmpUri.devicePath();
	}

	/**
	 * @since 2.0
	 */
	public URI getUri() {
		return this.uri;
	}

	@Override
	protected String getName() {
		return this.uri.lastSegment();
	}
}
