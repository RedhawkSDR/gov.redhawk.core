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

import gov.redhawk.sca.util.PropertyChangeSupport;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

import CF.FileSystem;

public abstract class AbstractResourceFactoryProvider implements IResourceFactoryProvider, IExecutableExtension {
	private int priority;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private List<ResourceDesc> descriptors = new ArrayList<>();
	private Map<String, FileSystem> fileSystemMounts = new HashMap<>();

	/**
	 * @since 2.0
	 * @deprecated This attribute is deprecated and should not be used.
	 */
	@Override
	@Deprecated
	public int getPriority() {
		return this.priority;
	}

	@Override
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		String propVal = config.getAttribute("priority");
		int value = Integer.MAX_VALUE;
		if (propVal != null) {
			try {
				value = Integer.valueOf(propVal);
			} catch (final NumberFormatException e) {
				// PASS
			}
		}
		this.priority = value;
	}

	/**
	 * @since 2.0
	 */
	public void addResourceDesc(ResourceDesc desc) {
		descriptors.add(desc);
		fireAddResourceDescriptor(desc);
	}

	/**
	 * @since 2.0
	 */
	public void removeResourceDesc(ResourceDesc desc) {
		descriptors.remove(desc);
		fireRemoveResourceDescriptor(desc);
	}

	/**
	 * @param fs
	 * @param mountPoint
	 * @since 4.0
	 */
	protected void addFileSystemMount(FileSystem fs, String mountPoint) {
		fileSystemMounts.put(mountPoint, fs);
		pcs.firePropertyChange(IResourceFactoryProvider.PROPERTY_FILE_SYSTEM_MOUNTS, null, mountPoint);
	}

	/**
	 * @param mountPoint
	 * @since 4.0
	 */
	protected void removeFileSystemMount(String mountPoint) {
		if (fileSystemMounts.remove(mountPoint) != null) {
			pcs.firePropertyChange(IResourceFactoryProvider.PROPERTY_FILE_SYSTEM_MOUNTS, mountPoint, null);
		}
	}

	/**
	 * @since 2.0
	 */
	protected void fireRemoveResourceDescriptor(ResourceDesc desc) {
		pcs.firePropertyChange(IResourceFactoryProvider.PROPERTY_RESOURCE_DESCRIPTORS, desc, null);
	}

	/**
	 * @since 2.0
	 */
	protected void fireAddResourceDescriptor(ResourceDesc desc) {
		pcs.firePropertyChange(IResourceFactoryProvider.PROPERTY_RESOURCE_DESCRIPTORS, null, desc);
	}

	/**
	 * @since 2.0
	 */
	@Override
	public List<ResourceDesc> getResourceDescriptors() {
		return Collections.unmodifiableList(new ArrayList<ResourceDesc>(descriptors));
	}

	/**
	 * @since 4.0
	 */
	@Override
	public Map<String, FileSystem> getFileSystemMounts() {
		return Collections.unmodifiableMap(new HashMap<>(fileSystemMounts));
	}

	/**
	 * @since 2.0
	 */
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * @since 2.0
	 */
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}
