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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
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

	@Override
	protected Command createReplaceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, Collection< ? > collection) {
		CompoundCommand command = new CompoundCommand();

		// Update reference count for new interfaces, adding any unseen ones to the add command
		SoftwareComponent scd = ScaEcoreUtils.getEContainerOfType(owner, SoftwareComponent.class);
		final Map<Interface, Integer> refCount = PortsUtil.getInterfaceReferenceCount(scd);
		final Map<String, Interface> currentInterfaces = PortsUtil.getInterfaceMap(scd.getInterfaces());
		List<Interface> addedInterfaces = new ArrayList<Interface>();
		for (Object object : collection) {
			Interface iface = (Interface) object;
			Interface existing = currentInterfaces.get(iface.getRepid());
			if (existing == null) {
				addedInterfaces.add(iface);
			} else {
				PortsUtil.incrementReferenceCount(refCount, existing);
			}
		}
		command.appendIfCanExecute(super.createAddCommand(domain, owner, feature, addedInterfaces, CommandParameter.NO_INDEX));

		// Decrement the reference count for the replaced interfaces, including inherited interfaces; this can result
		// in zero to many removed interfaces.
		Collection< ? > replacedInterfaces;
		if (value instanceof Collection< ? >) {
			replacedInterfaces = (Collection< ? >) value;
		} else {
			replacedInterfaces = Collections.singleton(value);
		}
		for (Object item : replacedInterfaces) {
			Interface replacedInterface = (Interface) item;
			PortsUtil.decrementReferenceCount(refCount, replacedInterface);
		}

		// Remove any interfaces whose reference count is now zero
		List<Interface> removedInterfaces = new ArrayList<Interface>();
		for (Map.Entry<Interface, Integer> entry : refCount.entrySet()) {
			if (entry.getValue() == 0) {
				removedInterfaces.add(entry.getKey());
			}
		}
		command.appendIfCanExecute(super.createRemoveCommand(domain, owner, feature, removedInterfaces));

		return command.unwrap();
	}

}
