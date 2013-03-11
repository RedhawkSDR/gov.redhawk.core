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
package gov.redhawk.eclipsecorba.library.util;

import gov.redhawk.eclipsecorba.library.RepositoryModule;

import java.util.regex.Pattern;

import org.eclipse.core.expressions.PropertyTester;

/**
 * @since 5.0
 */
public class RepositoryNameTester extends PropertyTester {

	public RepositoryNameTester() {
		//Default Constructor
	}

	/**
	 * Test the expected value against the the repository name.
	 * 
	 * @return <code> true </code> if the expectedValue matches the {@link RepositoryModule} name; <code> false </code> otherwise
	 */
	public boolean test(final Object receiver, final String property, final Object[] args, final Object expectedValue) {
		if (receiver instanceof RepositoryModule && expectedValue instanceof String) {
			return Pattern.matches((String) expectedValue, ((RepositoryModule) receiver).getName());
		}
		return false;
	}
}
