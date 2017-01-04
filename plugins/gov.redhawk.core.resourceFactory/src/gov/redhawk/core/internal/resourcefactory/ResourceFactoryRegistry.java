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
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.PropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;

/**
 * The resource factory registry instantiates instances of resource factories ({@link IResourceFactoryProvider})
 * declared via extension point (see {@link #EP_ID}). The registry receives resource descriptions
 * ({@link ResourceDesc}) from the factories, which it then fuses into a unified file system ({@link IFileManager}).
 */
public enum ResourceFactoryRegistry implements IResourceFactoryRegistry {
	INSTANCE;

	private static final String EP_ID = "resourceFactories";
	private static final String ELM_PROVIDER = "factoryProvider";

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final List<ResourceDesc> registry = Collections.synchronizedList(new ArrayList<ResourceDesc>());
	private final List<IResourceFactoryProvider> providerRegistry = Collections.synchronizedList(new ArrayList<IResourceFactoryProvider>());
	private ResourceFactoryRegistryFileManager fileManager = null;
	private OrbSession session = OrbSession.createSession(ResourceFactoryPlugin.ID);

	private final PropertyChangeListener listener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (IResourceFactoryProvider.PROPERTY_RESOURCE_DESCRIPTORS.equals(evt.getPropertyName())) {
				if (evt.getOldValue() instanceof ResourceDesc) {
					removeResourceDesc((ResourceDesc) evt.getOldValue());
				}
				if (evt.getNewValue() instanceof ResourceDesc) {
					ResourceDesc desc = (ResourceDesc) evt.getNewValue();
					try {
						addResourceDesc(desc);
					} catch (CoreException e) {
						ResourceFactoryPlugin.getDefault().getLog().log(
							new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add resource descriptor: " + desc.getIdentifier(), e));
					}
				}
			}
		}
	};

	private ResourceFactoryRegistry() {
		try {
			fileManager = new ResourceFactoryRegistryFileManager(session.getOrb(), session.getPOA());
		} catch (CoreException e) {
			ResourceFactoryPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Unable to initialize resource factory file manager", e));
		}

		// Find resource factories declared in extension points
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
				SafeRunner.run(new ISafeRunnable() {

					@Override
					public void run() throws Exception {
						try {
							final IResourceFactoryProvider provider = (IResourceFactoryProvider) element.createExecutableExtension("class");
							provider.addPropertyChangeListener(listener);
							providerRegistry.add(provider);
							for (ResourceDesc desc : provider.getResourceDescriptors()) {
								addResourceDesc(desc);
							}
						} catch (final CoreException e) {
							ResourceFactoryPlugin.getDefault().getLog().log(
								new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add Factory Provider: " + id, e));
						}
					}

					@Override
					public void handleException(Throwable exception) {
						// TODO Auto-generated method stub

					}
				});

			}
		}
	}

	private void addResourceDesc(final ResourceDesc desc) throws CoreException {
		this.fileManager.mount(desc);
		this.registry.add(desc);
		pcs.firePropertyChange(IResourceFactoryRegistry.PROP_RESOURCES, null, desc);
	}

	private void removeResourceDesc(final ResourceDesc desc) {
		if (registry.remove(desc)) {
			this.fileManager.unmount(desc);
			desc.dispose();
		}
		pcs.firePropertyChange(IResourceFactoryRegistry.PROP_RESOURCES, desc, null);
	}

	@Override
	public void dispose() {
		for (final IResourceFactoryProvider provider : this.providerRegistry) {
			provider.dispose();
		}
		this.providerRegistry.clear();

		synchronized (this.registry) {
			for (final ResourceDesc desc : this.registry) {
				this.fileManager.unmount(desc);
				desc.dispose();
			}
			this.registry.clear();
		}

		if (session != null) {
			session.dispose();
			session = null;
		}
	}

	@Override
	public IFileManager getFileManager() {
		return this.fileManager;
	}

	@Override
	public ResourceDesc[] getResourceDescriptors() {
		return registry.toArray(new ResourceDesc[registry.size()]);
	}

	@Override
	public ResourceDesc getResourceDesc(final String profile) {
		if (profile == null) {
			return null;
		}
		return fileManager.getResourceDesc(profile);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	@Override
	public void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@Override
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
