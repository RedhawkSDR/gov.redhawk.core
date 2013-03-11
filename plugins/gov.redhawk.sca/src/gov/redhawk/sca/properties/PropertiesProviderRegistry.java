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
package gov.redhawk.sca.properties;

import gov.redhawk.sca.ScaPlugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;

/**
 * @since 5.0
 */
public enum PropertiesProviderRegistry implements IExtensionChangeHandler, IPropertiesProviderRegistry {
	INSTANCE;

	public static final String PROPERTIES_PROVIDER_EP_ID = "propertiesProvider";
	private final List<IPropertiesProviderDescriptor> propertiesProviderDescriptors = new ArrayList<IPropertiesProviderDescriptor>();
	private final List<IPropertiesProvider> propertiesProviders = new ArrayList<IPropertiesProvider>();
	private final Map<IPropertiesProvider, String> nameMap = new HashMap<IPropertiesProvider, String>();
	private final ExtensionTracker tracker;

	private PropertiesProviderRegistry() {
		final IExtensionRegistry reg = Platform.getExtensionRegistry();

		final IExtensionPoint ep = reg.getExtensionPoint(ScaPlugin.PLUGIN_ID, PropertiesProviderRegistry.PROPERTIES_PROVIDER_EP_ID);

		this.tracker = new ExtensionTracker(reg);

		if (ep != null) {
			final IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
			this.tracker.registerHandler(this, filter);
			final IExtension[] extensions = ep.getExtensions();
			for (final IExtension extension : extensions) {
				addExtension(this.tracker, extension);
			}
		}
	}

	public void addExtension(final IExtensionTracker tracker, final IExtension extension) {
		for (final IConfigurationElement element : extension.getConfigurationElements()) {
			final IPropertiesProviderDescriptor descriptor = new PropertiesProviderDescriptor(element);
			if (descriptor != null) {
				this.propertiesProviderDescriptors.add(descriptor);
				tracker.registerObject(extension, descriptor, IExtensionTracker.REF_SOFT);
			}
		}
	}

	public void removeExtension(final IExtension extension, final Object[] objects) {
		for (final Object obj : objects) {
			if (obj instanceof IPropertiesProviderDescriptor) {
				this.propertiesProviderDescriptors.remove(obj);
			}
		}
	}

	public void clearPropertiesProviders() {
		this.propertiesProviderDescriptors.clear();
	}

	public List<IPropertiesProviderDescriptor> getPropertiesProvidersDescriptors() {
		return Collections.unmodifiableList(this.propertiesProviderDescriptors);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<IPropertiesProvider> getPropertiesProviders() {
		this.propertiesProviders.clear();
		this.nameMap.clear();
		for (IPropertiesProviderDescriptor descriptor : this.propertiesProviderDescriptors) {
			this.propertiesProviders.add(descriptor.getProvider());
			this.nameMap.put(descriptor.getProvider(), descriptor.getName());
		}
		return this.propertiesProviders;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName(IPropertiesProvider provider) {
		return this.nameMap.get(provider);
	}

}
