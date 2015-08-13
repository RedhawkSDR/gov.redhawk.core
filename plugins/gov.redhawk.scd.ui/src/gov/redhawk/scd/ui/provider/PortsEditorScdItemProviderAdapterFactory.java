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
