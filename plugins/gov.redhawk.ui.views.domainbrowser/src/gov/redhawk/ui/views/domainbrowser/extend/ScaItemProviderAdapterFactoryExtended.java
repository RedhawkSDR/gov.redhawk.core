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
package gov.redhawk.ui.views.domainbrowser.extend;

import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;

public class ScaItemProviderAdapterFactoryExtended extends ScaItemProviderAdapterFactory {

	@Override
	public Adapter createScaDomainManagerAdapter() {
		return new ScaDomainManagerItemProviderExtended(this);
	}

	@Override
	public Adapter createScaDeviceManagerAdapter() {
		return new ScaDeviceManagerItemProviderExtended(this);
	}

	@Override
	public Adapter createScaDeviceAdapter() {
		return new ScaDeviceItemProviderExtended(this);
	}

	@Override
	public Adapter createScaExecutableDeviceAdapter() {
		return new ScaExecutableDeviceItemProviderExtended(this);
	}
}
