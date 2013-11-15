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
package gov.redhawk.sca.compatibility;

import java.io.File;


/**
 * @since 7.0
 */
public interface ICompatibilityUtil {

	/**
	 * Returns a String that is uniquely associated with the current user
	 * (RAP) or an empty String (RCP), to be used as a path segment while
	 * persisting preferences.
	 * @param context
	 * 					The current Display (meaningful for RAP only), used
	 * to obtain a user-specific context.
	 * @return
	 * 			A String uniquely associated with the current user (RAP), or
	 * an empty String.
	 */
	public String getUserSpecificPath(Object context);

	/**
	 * Initialize the setting store. Used only in RAP, to initialize the
	 * SettingStore with preference values scoped and persisted for the
	 * current user.
	 * 
	 * @param context
	 *            the current Display
	 */
	public void initializeSettingStore(Object context);

	/**
	 * Gets the RAP SettingStore work directory.
	 * 
	 * @return 
	 * 			the directory in which the configured SettingStore is storing
	 * user-specific preferences
	 */
	public File getSettingStoreWorkDir();
}
