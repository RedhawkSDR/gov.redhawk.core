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
package gov.redhawk.sca.properties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import gov.redhawk.sca.ScaPlugin;

/**
 * @since 5.0
 */
public enum PropertiesProviderRegistry implements IPropertiesProviderRegistry {
	INSTANCE;

	public static final String PROPERTIES_PROVIDER_EP_ID = "propertiesProvider";
	private final List<IPropertiesProviderDescriptor> propertiesProviderDescriptors = new ArrayList<IPropertiesProviderDescriptor>();
	private final Map<IPropertiesProvider, String> nameMap = new HashMap<IPropertiesProvider, String>();

	private PropertiesProviderRegistry() {
		IConfigurationElement[] configElements = Platform.getExtensionRegistry().getConfigurationElementsFor(ScaPlugin.PLUGIN_ID,
			PropertiesProviderRegistry.PROPERTIES_PROVIDER_EP_ID);
		for (IConfigurationElement configElement : configElements) {
			final IPropertiesProviderDescriptor descriptor = new PropertiesProviderDescriptor(configElement);
			this.propertiesProviderDescriptors.add(descriptor);
		}
	}

	@Override
	public List<IPropertiesProviderDescriptor> getPropertiesProvidersDescriptors() {
		return Collections.unmodifiableList(this.propertiesProviderDescriptors);
	}

	@Override
	public List<IPropertiesProvider> getPropertiesProviders() {
		List<IPropertiesProvider> propertiesProviders = new ArrayList<IPropertiesProvider>();
		this.nameMap.clear();
		for (IPropertiesProviderDescriptor descriptor : this.propertiesProviderDescriptors) {
			propertiesProviders.add(descriptor.getProvider());
			this.nameMap.put(descriptor.getProvider(), descriptor.getName());
		}
		return propertiesProviders;
	}

	@Override
	public String getName(IPropertiesProvider provider) {
		return this.nameMap.get(provider);
	}

}
