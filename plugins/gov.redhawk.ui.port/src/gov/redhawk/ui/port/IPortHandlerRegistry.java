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
package gov.redhawk.ui.port;

/**
 * @deprecated Use commands
 */
@Deprecated
public interface IPortHandlerRegistry {
	String EXTENSION_POINT = "gov.redhawk.ui.portHandler";

	/**
	 * Find the specified porthandlers.
	 * 
	 * @return a particular porthandlers after finding it via it's reference id
	 */
	IPortHandler findPortHandler(String handlerid);
	
	/**
	 * Find porthandlers by type
	 * 
	 * @return a porthandler if the handler supports the provided type, otherwise null
	 * @since 5.0
	 */
	IPortHandler findPortHandler(String handlerid, String type);

	/**
	 * Find porthandlers by type
	 * 
	 * @return a porthandlers by looking it by it's type
	 */
	IPortHandler[] findPortHandlersByType(String type);

	/**
	 * Get all porthandlers.
	 */
	IPortHandler[] getPortHandlers();

	/**
	 * Get all of the registered id's
	 */
	String[] getIds();

}
