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
package gov.redhawk.model.sca;

public class DomainConnectionException extends Exception {

	/**
	 * Required attribute for serialization
	 */
	private static final long serialVersionUID = -6078887155231178487L;

	public DomainConnectionException() {
		super();
	}

	public DomainConnectionException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DomainConnectionException(final String message) {
		super(message);
	}

	public DomainConnectionException(final Throwable cause) {
		super(cause);
	}

}
