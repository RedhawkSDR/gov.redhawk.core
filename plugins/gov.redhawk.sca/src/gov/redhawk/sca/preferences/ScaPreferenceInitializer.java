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
package gov.redhawk.sca.preferences;

import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.OrbSession;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.osgi.service.datalocation.Location;

public class ScaPreferenceInitializer extends AbstractPreferenceInitializer {

	private static ScaDomainManagerRegistry scaDomainManagerRegistry;

	public ScaPreferenceInitializer() {
	}

	@Override
	public void initializeDefaultPreferences() {
		final IPreferenceAccessor accessor = ScaPlugin.getDefault().getScaPreferenceAccessor();

		accessor.setDefault(ScaPreferenceConstants.SCA_CORBA_AUTOCONNECT_PREFERENCE, true);
		
		String defaultNameService = "corbaname::127.0.0.1:2809";
		Map<String, String> initRefs = OrbSession.getOmniORBInitRefs();
		if (initRefs.containsKey("NameService")) {
			defaultNameService = initRefs.get("NameService");
		}
		accessor.setDefault(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE, defaultNameService);
		accessor.setDefault(ScaPreferenceConstants.SCA_DOMAIN_WAVEFORMS_SEARCH_PATH, ScaPreferenceConstants.createPath(new String[] { "waveforms" }));
	}

	/**
	 * Loads a default configuration of domains.  This is loaded from the domains.sca file located in the configuration.
	 * It is also populated with the values from the preference store to support backwards compatibility.
	 * 
	 * We do not need to create the file if it doesn't exist, simple create one in the memory file store.
	 * @since 3.0
	 */
	public static ScaDomainManagerRegistry getDefaultScaDomainManagerRegistry() {
		if (ScaPreferenceInitializer.scaDomainManagerRegistry == null) {
			synchronized (ScaPreferenceInitializer.class) {
				if (ScaPreferenceInitializer.scaDomainManagerRegistry == null) { // SUPPRESS CHECKSTYLE DoubleCheck
					final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
					try {
						// First, try the user's config area
						final URL configUrl = ScaPreferenceInitializer.getDomainManagerRegistryConfigURL();
						final org.eclipse.emf.common.util.URI configUri = org.eclipse.emf.common.util.URI.createURI(configUrl.toString());
						final Resource configResource = resourceSet.getResource(configUri, true);
						ScaPreferenceInitializer.scaDomainManagerRegistry = ScaDomainManagerRegistry.Util.getScaDomainManagerRegistry(configResource);
					} catch (final Exception e1) { // SUPPRESS CHECKSTYLE Fallback
						// Second, try the shared config area
						try {
							final URL sharedConfigUrl = ScaPreferenceInitializer.getDomainManagerRegistrySharedConfigURL();
							if (sharedConfigUrl != null) {
								final org.eclipse.emf.common.util.URI sharedConfigUri = org.eclipse.emf.common.util.URI.createURI(sharedConfigUrl.toString());
								final Resource sharedConfigResource = resourceSet.getResource(sharedConfigUri, true);
								ScaPreferenceInitializer.scaDomainManagerRegistry = ScaDomainManagerRegistry.Util.getScaDomainManagerRegistry(sharedConfigResource);
							}
						} catch (final Exception e2) { // SUPPRESS CHECKSTYLE Fallback
							// PASS
						}
					}

					// If we still don't have a registry, create a new empty one
					if (ScaPreferenceInitializer.scaDomainManagerRegistry == null) {
						ScaPreferenceInitializer.scaDomainManagerRegistry = ScaFactory.eINSTANCE.createScaDomainManagerRegistry();
						final Resource resource = resourceSet.createResource(org.eclipse.emf.common.util.URI.createURI("virtual://domains.sca"));
						resource.getContents().add(ScaPreferenceInitializer.scaDomainManagerRegistry);
					}
				}
			}

		}
		return ScaPreferenceInitializer.scaDomainManagerRegistry;
	}

	/**
	 * Gets the URL for the domain manager registry within the user's configuration
	 * @return
	 */
	private static URL getDomainManagerRegistryConfigURL() {
		final Location configurationLocation = Platform.getConfigurationLocation();
		if (configurationLocation == null) {
			return null;
		}
		try {
			return configurationLocation.getDataArea(ScaPlugin.PLUGIN_ID + "/domains.sca");
		} catch (final IOException e) {
			return null;
		}
	}

	/**
	 * Gets the URL for the domain manager registry with the application's configuration
	 * @return
	 */
	private static URL getDomainManagerRegistrySharedConfigURL() {
		final Location configLocation = Platform.getConfigurationLocation();
		if (configLocation == null) {
			return null;
		}
		final Location sharedConfigLocation = configLocation.getParentLocation();
		if (sharedConfigLocation == null) {
			return null;
		}
		try {
			return sharedConfigLocation.getDataArea(ScaPlugin.PLUGIN_ID + "/domains.sca");
		} catch (final IOException e) {
			return null;
		}
	}
}
