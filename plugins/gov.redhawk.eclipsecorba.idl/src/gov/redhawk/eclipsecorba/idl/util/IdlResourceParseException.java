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

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.util;

import gov.redhawk.eclipsecorba.idl.internal.parser.ParseException;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * @since 5.0
 */
public class IdlResourceParseException extends IOException implements Resource.Diagnostic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5198815213771059907L;
	private final int line;
	private final int column;
	private final String location;

	public IdlResourceParseException(final String s, final Throwable cause) {
		this(s, cause, -1, -1, null);
	}

	public IdlResourceParseException(final Throwable cause) {
		this(cause.getMessage(), cause);
	}

	public IdlResourceParseException(final String s, final Throwable cause, final int column, final int line, final String location) {
		super(s);
		initCause(cause);
		this.column = column;
		this.line = line;
		this.location = location;
	}

	public IdlResourceParseException(final String s, final int column, final int line, final String location) {
		this(s, null, column, line, location);
	}

	public int getColumn() {
		return this.column;
	}

	public int getLine() {
		if (getCause() instanceof ParseException) {
			return ((ParseException) getCause()).getLine();
		}
		return this.line;
	}

	public String getLocation() {
		return this.location;
	}
}
