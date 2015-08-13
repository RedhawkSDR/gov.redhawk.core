package gov.redhawk.scd.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import gov.redhawk.scd.ui.util.PortsUtil;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.Interfaces;
import mil.jpeojtrs.sca.scd.provider.InterfacesItemProvider;

public class PortsEditorInterfacesItemProvider extends InterfacesItemProvider {

	public PortsEditorInterfacesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection, int index) {
		Interfaces interfaces = (Interfaces) owner;
		final Map<String, Interface> currentInterfaces = PortsUtil.getInterfaceMap(interfaces);
		List<Interface> addedInterfaces = new ArrayList<Interface>();
		for (Object object : collection) {
			Interface iface = (Interface) object;
			if (!currentInterfaces.containsKey(iface.getRepid())) {
				currentInterfaces.put(iface.getRepid(), iface);
				addedInterfaces.add(iface);
			}
		}
		return super.createAddCommand(domain, owner, feature, addedInterfaces, index);
	}

}
