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
package gov.redhawk.eclipsecorba.idl.internal.parser;

/**
 * 
 */
public class ScopeException extends ParseException {
	private final String errorMessage;

	public ScopeException(final String errorMessage, final Token currentTokenVal) {
		this.currentToken = currentTokenVal;
		this.errorMessage = errorMessage;
	}

	@Override
	public String getMessage() {
		String retval = "Encountered \"" + this.errorMessage;
		retval += "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn;
		retval += "." + this.eol;
		return retval;
	}
}
