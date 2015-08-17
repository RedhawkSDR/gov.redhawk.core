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
package gov.redhawk.scd.ui.provider;

import org.eclipse.emf.common.notify.Adapter;

import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;

public class PortsEditorScdItemProviderAdapterFactory extends ScdItemProviderAdapterFactory {

	@Override
	public Adapter createPortsAdapter() {
		if (portsItemProvider == null) {
			portsItemProvider = new PortsEditorPortsItemProvider(this);
		}
		return portsItemProvider;
	}

	@Override
	public Adapter createInterfacesAdapter() {
		if (interfacesItemProvider == null) {
			interfacesItemProvider = new PortsEditorInterfacesItemProvider(this);
		}
		return interfacesItemProvider;
	}

	@Override
	public Adapter createUsesAdapter() {
		if (usesItemProvider == null) {
			usesItemProvider = new PortsEditorUsesItemProvider(this);
		}
		return usesItemProvider;
	}

	@Override
	public Adapter createProvidesAdapter() {
		if (providesItemProvider == null) {
			providesItemProvider = new PortsEditorProvidesItemProvider(this);
		}
		return providesItemProvider;
	}

}
