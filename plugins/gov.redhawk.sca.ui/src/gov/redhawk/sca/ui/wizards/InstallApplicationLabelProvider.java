/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.ui.wizards;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;

import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;

/**
 * @since 10.1
 */
public class InstallApplicationLabelProvider extends AdapterFactoryLabelProvider {

	public InstallApplicationLabelProvider() {
		super(InstallApplicationLabelProvider.createAdapterFactory());
	}

	/**
	 * Creates the adapter factory.
	 * 
	 * @return the adapter factory
	 */
	private static AdapterFactory createAdapterFactory() {
		// Create an adapter factory that yields item providers.
		//
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new WizardSadItemProviderAdapterFactory()); // For SoftwareAssembly
		adapterFactory.addAdapterFactory(new ScaItemProviderAdapterFactory()); // For WaveformContainers
		return adapterFactory;
	}
}
