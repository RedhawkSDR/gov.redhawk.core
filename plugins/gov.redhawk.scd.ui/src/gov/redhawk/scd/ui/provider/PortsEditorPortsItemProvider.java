package gov.redhawk.scd.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;

import gov.redhawk.scd.ui.util.PortsUtil;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.Interfaces;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.provider.PortsItemProvider;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class PortsEditorPortsItemProvider extends PortsItemProvider {

	public PortsEditorPortsItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeature() == ScdPackage.Literals.PORTS__GROUP) {
			// Override notification just for changes to the group feature to handle the way bi-directional ports are
			// handled in the editor. Both the content--new or removed children--and the labels--in/out/bidir--must be
			// updated.
			updateChildren(notification);
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
			return;
		}
		super.notifyChanged(notification);
	}

	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection, int index) {
		CompoundCommand command = new CompoundCommand(0);
		command.append(super.createAddCommand(domain, owner, feature, collection, index));

		// Collect the set of interfaces used by the added ports
		List<Interface> addedInterfaces = new ArrayList<Interface>();
		for (Object object : collection) {
			AbstractPort port = (AbstractPort) AdapterFactoryEditingDomain.unwrap(object);
			PortsUtil.createRequiredInterfaces(port.getRepID(), addedInterfaces);
		}

		// Create a command to add the requisite interfaces, if necessary
		SoftwareComponent scd = ScaEcoreUtils.getEContainerOfType(owner, SoftwareComponent.class);
		Interfaces interfaces = scd.getInterfaces();
		command.appendIfCanExecute(AddCommand.create(domain, interfaces, ScdPackage.Literals.INTERFACES__INTERFACE, addedInterfaces));
		return command.unwrap();
	}

	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection) {
		CompoundCommand command = new CompoundCommand(0);
		command.append(super.createRemoveCommand(domain, owner, feature, collection));

		// Collect the set of interfaces used by the removed ports; duplicates are preserved because the Interfaces
		// item provider manages removal by reference counting, so it needs to know how many times to decrement the
		// reference count for each interface.
		List<Interface> removedInterfaces = new ArrayList<Interface>();
		for (Object object : collection) {
			AbstractPort port = (AbstractPort) AdapterFactoryEditingDomain.unwrap(object);
			removedInterfaces.add(port.getInterface());
		}

		// Create a command to remove the referenced interfaces, if necessary
		SoftwareComponent scd = ScaEcoreUtils.getEContainerOfType(owner, SoftwareComponent.class);
		Interfaces interfaces = scd.getInterfaces();
		command.appendIfCanExecute(RemoveCommand.create(domain, interfaces, ScdPackage.Literals.INTERFACES__INTERFACE, removedInterfaces));
		return command.unwrap();
	}

}
