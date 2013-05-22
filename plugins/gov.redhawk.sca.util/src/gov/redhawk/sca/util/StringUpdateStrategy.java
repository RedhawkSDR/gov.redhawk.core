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
package gov.redhawk.sca.util;

/**
 * An interface to provide a custom string update implementation.  Used for creating unique strings.
 * @since 2.1
 */
public interface StringUpdateStrategy {

	/**
	 * Update the given string.
	 * 
	 * @param string the string to update
	 * @return the updated string
	 */
	String update(String string);
}
