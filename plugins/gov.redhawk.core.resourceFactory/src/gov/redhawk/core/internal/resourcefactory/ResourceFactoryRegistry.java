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
package gov.redhawk.core.internal.resourcefactory;

import gov.redhawk.core.filemanager.IFileManager;
import gov.redhawk.core.resourcefactory.IResourceFactoryProvider;
import gov.redhawk.core.resourcefactory.IResourceFactoryRegistry;
import gov.redhawk.core.resourcefactory.ResourceDesc;
import gov.redhawk.core.resourcefactory.ResourceFactoryPlugin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import CF.FileException;
import CF.InvalidFileName;

/**
 * 
 */
public enum ResourceFactoryRegistry implements IResourceFactoryRegistry {
	INSTANCE;

	private static final String EP_ID = "resourceFactories";
	private static final String ELM_PROVIDER = "factoryProvider";

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final List<ResourceDesc> registry = Collections.synchronizedList(new ArrayList<ResourceDesc>());
	private final List<IResourceFactoryProvider> providerRegistry = Collections.synchronizedList(new ArrayList<IResourceFactoryProvider>());
	private final ResourceFactoryRegistryFileManager fileManager = new ResourceFactoryRegistryFileManager();
	private final PropertyChangeListener listener = new PropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent evt) {
			if (IResourceFactoryProvider.PROPERTY_RESOURCE_DESCRIPTORS.equals(evt.getPropertyName())) {
				IResourceFactoryProvider provider = (IResourceFactoryProvider) evt.getSource();
				if (evt.getOldValue() instanceof ResourceDesc) {
					removeResourceDesc((ResourceDesc) evt.getOldValue());
				}
				if (evt.getNewValue() instanceof ResourceDesc) {
					ResourceDesc desc = (ResourceDesc) evt.getNewValue();
					try {
						addResourceDesc(desc, provider.getPriority());
					} catch (CoreException e) {
						ResourceFactoryPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID,
						                                                    "Failed to add resource descriptor: " + desc.getIdentifier(), e));
					}
				}
			}
		}
	};

	private ResourceFactoryRegistry() {
		for (final String s : new String[] { "components", "waveforms", "devices", "services" }) {
			try {
				this.fileManager.mkdir(s);
			} catch (final InvalidFileName e) {
				// PASS
			} catch (final FileException e) {
				// PASS
			}
		}

		final IExtensionRegistry reg = Platform.getExtensionRegistry();
		final IExtensionPoint ep = reg.getExtensionPoint(ResourceFactoryPlugin.ID, ResourceFactoryRegistry.EP_ID);

		if (ep != null) {
			final IExtension[] extensions = ep.getExtensions();
			for (final IExtension extension : extensions) {
				addExtension(extension);
			}
		}
	}

	private void addExtension(final IExtension extension) {
		for (final IConfigurationElement element : extension.getConfigurationElements()) {
			final String id = element.getAttribute("id");
			if (element.getName().equals(ResourceFactoryRegistry.ELM_PROVIDER)) {
				try {
					final IResourceFactoryProvider provider = (IResourceFactoryProvider) element.createExecutableExtension("class");
					provider.addPropertyChangeListener(listener);
					this.providerRegistry.add(provider);
					int priority = provider.getPriority();
					for (ResourceDesc desc : provider.getResourceDescriptors()) {
						addResourceDesc(desc, priority);
					}
				} catch (final CoreException e) {
					ResourceFactoryPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID,
					                                                    "Failed to add Factory Provider: " + id, e));
				}
			}
		}
	}

	private void addResourceDesc(final ResourceDesc desc, int priority) throws CoreException {
		mount(desc, priority);
		this.registry.add(desc);
		pcs.firePropertyChange(PROP_RESOURCES, null, desc);
	}

	private void mount(final ResourceDesc desc, int priority) throws CoreException {
		this.fileManager.mount(desc, priority);
	}

	private void unmount(final ResourceDesc desc) {
		this.fileManager.unmount(desc);
	}

	private void removeResourceDesc(final ResourceDesc desc) {
		if (registry.remove(desc)) {
			unmount(desc);
			desc.dispose();
		}
		pcs.firePropertyChange(PROP_RESOURCES, desc, null);
	}

	public void dispose() {
		for (final IResourceFactoryProvider provider : this.providerRegistry) {
			provider.dispose();
		}
		this.providerRegistry.clear();

		synchronized (this.registry) {
			for (final ResourceDesc desc : this.registry) {
				unmount(desc);
				desc.dispose();
			}
			this.registry.clear();
		}
	}

	public IFileManager getFileManager() {
		return this.fileManager;
	}

	public ResourceDesc[] getResourceDescriptors() {
		return registry.toArray(new ResourceDesc[registry.size()]);
	}

	public ResourceDesc getResourceDesc(final String profile) {
		if (profile == null) {
			return null;
		}
		return fileManager.getResourceDesc(profile);
	}

	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	public ResourceDesc getResourceDesc(String refID, String version) {
		synchronized (registry) {
			for (ResourceDesc desc : registry) {
				if (desc.getIdentifier().equals(refID) && desc.getVersion().equals(version)) {
					return desc;
				}
			}
		}
		return null;
	}

}
