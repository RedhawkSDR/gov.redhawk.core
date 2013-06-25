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
package gov.redhawk.core.resourcefactory;

import gov.redhawk.core.filemanager.IFileManager;

import java.beans.PropertyChangeListener;

/**
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IResourceFactoryRegistry {
	/**
	 * @since 2.0
	 */
	String PROP_RESOURCES = "resources";

	/**
	 * @since 2.0
	 */
	void addListener(PropertyChangeListener listener);

	/**
	 * @since 2.0
	 */
	void removeListener(PropertyChangeListener listener);


	IFileManager getFileManager();

	/**
	 * @since 2.0
	 */
	ResourceDesc getResourceDesc(String refID, String version);

	/**
	 * @since 2.0
	 */
	ResourceDesc getResourceDesc(String profile);

	/**
	 * @since 2.0
	 */
	ResourceDesc[] getResourceDescriptors();

	void dispose();
}
