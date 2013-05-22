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
package gov.redhawk.sca.model.internal;

import gov.redhawk.model.sca.IDataProviderServiceRegistry;
import gov.redhawk.model.sca.IScaDataProviderServiceDescriptor;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.sca.model.preferences.ScaModelPreferenceContants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.core.runtime.preferences.InstanceScope;

/**
 * @since 9.0
 */
public enum DataProviderServicesRegistry implements IExtensionChangeHandler, IDataProviderServiceRegistry {
	INSTANCE;

	public static final String DATA_PROVIDER_EP_ID = "dataProviderService";
	private final List<IScaDataProviderServiceDescriptor> dataProviderServiceDescriptors = new ArrayList<IScaDataProviderServiceDescriptor>();
	private final ExtensionTracker tracker;
	private IPreferenceChangeListener listener = new IPreferenceChangeListener() {

		public void preferenceChange(PreferenceChangeEvent event) {
			if (event.getKey().equals(ScaModelPreferenceContants.DISABLED_DATA_PROVIDERS)) {
				Object obj = event.getNewValue();
				if (obj instanceof String) {
					String value = (String) obj;
					for (IScaDataProviderServiceDescriptor desc : dataProviderServiceDescriptors) {
						if (value.contains(desc.getId())) {
							((ScaDataProviderServiceDescriptor) desc).setEnabled(false);
						} else {
							((ScaDataProviderServiceDescriptor) desc).setEnabled(true);
						}
					}
				} else {
					for (IScaDataProviderServiceDescriptor desc : dataProviderServiceDescriptors) {
						((ScaDataProviderServiceDescriptor) desc).setEnabled(true);
					}
				}
			}
		}
	};
	private IEclipsePreferences node;

	private DataProviderServicesRegistry() {
		final IExtensionRegistry reg = Platform.getExtensionRegistry();

		final IExtensionPoint ep = reg.getExtensionPoint(ScaModelPlugin.ID, DataProviderServicesRegistry.DATA_PROVIDER_EP_ID);

		this.tracker = new ExtensionTracker(reg);

		if (ep != null) {
			final IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
			this.tracker.registerHandler(this, filter);
			final IExtension[] extensions = ep.getExtensions();
			for (final IExtension extension : extensions) {
				addExtension(this.tracker, extension);
			}
		}

		node = InstanceScope.INSTANCE.getNode(ScaModelPlugin.ID);
		node.addPreferenceChangeListener(listener);
	}

	public void addExtension(final IExtensionTracker tracker, final IExtension extension) {
		String disabledDataProviders = Platform.getPreferencesService().getString(ScaModelPlugin.ID,
		        ScaModelPreferenceContants.DISABLED_DATA_PROVIDERS,
		        "",
		        null);
		for (final IConfigurationElement element : extension.getConfigurationElements()) {
			final ScaDataProviderServiceDescriptor descriptor = createDescriptor(element, disabledDataProviders);
			this.dataProviderServiceDescriptors.add(descriptor);
			tracker.registerObject(extension, descriptor, IExtensionTracker.REF_SOFT);
		}
	}

	private ScaDataProviderServiceDescriptor createDescriptor(IConfigurationElement element, String disabledDataProviders) {
		ScaDataProviderServiceDescriptor desc = new ScaDataProviderServiceDescriptor(element, disabledDataProviders);
		return desc;
	}

	public void removeExtension(final IExtension extension, final Object[] objects) {
		for (final Object obj : objects) {
			if (obj instanceof IScaDataProviderServiceDescriptor) {
				this.dataProviderServiceDescriptors.remove(obj);
			}
		}
	}

	public void clearDataProviders() {
		this.dataProviderServiceDescriptors.clear();
	}

	public List<IScaDataProviderServiceDescriptor> getDataProvidersDescriptors() {
		return Collections.unmodifiableList(this.dataProviderServiceDescriptors);
	}

	public void dispose() {
		node.removePreferenceChangeListener(listener);
	}
}
