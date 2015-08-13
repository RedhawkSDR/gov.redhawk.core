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
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.provider.InterfacesItemProvider;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

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

	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection) {
		// Get the reference count for all interfaces in the SCD, then decrement the reference count for all removed
		// interfaces (recursively, so inherited interfaces may also be removed). This makes it easy for other item
		// providers to manage changes to the set of interfaces, because they can just unconditionally remove their
		// referenced interfaces.
		SoftwareComponent scd = ScaEcoreUtils.getEContainerOfType(owner, SoftwareComponent.class);
		final Map<Interface, Integer> refCount = PortsUtil.getInterfaceReferenceCount(scd);
		for (Object object : collection) {
			Interface iface = (Interface) object;
			PortsUtil.decrementReferenceCount(refCount, iface);
		}

		// Remove only interfaces whose reference count is now zero
		List<Interface> removedInterfaces = new ArrayList<Interface>();
		for (Map.Entry<Interface, Integer> entry : refCount.entrySet()) {
			if (entry.getValue() == 0) {
				removedInterfaces.add(entry.getKey());
			}
		}
		return super.createRemoveCommand(domain, owner, feature, removedInterfaces);
	}

}
