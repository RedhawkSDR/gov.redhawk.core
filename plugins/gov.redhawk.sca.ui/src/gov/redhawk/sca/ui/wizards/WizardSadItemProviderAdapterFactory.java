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
package gov.redhawk.sca.ui.wizards;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.sad.provider.SadItemProviderAdapterFactory;
import mil.jpeojtrs.sca.sad.provider.SoftwareAssemblyItemProvider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * @since 8.0
 * 
 */
public class WizardSadItemProviderAdapterFactory extends SadItemProviderAdapterFactory {

	private static class CustomSoftwareAssemblyItemProvider extends SoftwareAssemblyItemProvider {
		public CustomSoftwareAssemblyItemProvider(final AdapterFactory adapterFactory) {
			super(adapterFactory);
			// TODO Auto-generated constructor stub
		}

		@Override
		public String getText(final Object object) {
			return ((SoftwareAssembly) object).getName();
		}
	}

	@Override
	public Adapter createSoftwareAssemblyAdapter() {
		if (this.softwareAssemblyItemProvider == null) {
			this.softwareAssemblyItemProvider = new CustomSoftwareAssemblyItemProvider(this);
		}

		return this.softwareAssemblyItemProvider;
	}
}
