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

import java.beans.PropertyChangeListener;
import java.util.List;

public interface IResourceFactoryProvider {
	
	/**
     * @since 2.0
     */
	final String PROPERTY_RESOURCE_DESCRIPTORS = "resourceDescriptors";

	/**
	 * @since 2.0
	 */
	void addPropertyChangeListener(PropertyChangeListener listener);

	/**
	 * @since 2.0
	 */
	void removePropertyChangeListener(PropertyChangeListener listener);
	
	/**
	 * @since 2.0
	 */
	List<ResourceDesc> getResourceDescriptors();

	/**
	 * @since 2.0
	 */
	int getPriority();

	void dispose();
}
