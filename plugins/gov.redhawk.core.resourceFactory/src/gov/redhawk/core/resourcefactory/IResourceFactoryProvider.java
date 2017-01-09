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
import java.util.Map;

import CF.FileSystem;

/**
 * Classes implementing this interface provide descriptions of resources they know about which can be launched in the
 * sandbox. They must emit notifications to change listeners if resources can no longer be launched, or if they become
 * aware of new resources which can be launched.
 * <p/>
 * Implementers are registered with the resource factory registry via extension point.
 */
public interface IResourceFactoryProvider {

	/**
	 * Used to indicate that the resource descriptors property of this object has changed. The value(s) passed with
	 * the notification are of type {@link ResourceDesc}.
	 * @since 2.0
	 */
	final String PROPERTY_RESOURCE_DESCRIPTORS = "resourceDescriptors";

	/**
	 * Used to indicate that the file system mounts of this object have changed. The value(s) passed with the
	 * notification are the mount point(s).
	 * @since 4.0
	 */
	final String PROPERTY_FILE_SYSTEM_MOUNTS = "fileSystemMounts";

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
	 * @return
	 * @since 4.0
	 */
	Map<String, FileSystem> getFileSystemMounts();

	/**
	 * @since 2.0
	 * @deprecated This attribute is deprecated and should not be used.
	 */
	@Deprecated
	int getPriority();

	void dispose();
}
