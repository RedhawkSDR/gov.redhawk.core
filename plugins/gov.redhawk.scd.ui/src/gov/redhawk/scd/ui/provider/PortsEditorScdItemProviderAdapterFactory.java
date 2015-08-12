package gov.redhawk.scd.ui.provider;

import org.eclipse.emf.common.notify.Adapter;

import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;

public class PortsEditorScdItemProviderAdapterFactory extends ScdItemProviderAdapterFactory {

	@Override
	public Adapter createPortsAdapter() {
		return new PortsEditorPortsItemProvider(this);
	}

}
