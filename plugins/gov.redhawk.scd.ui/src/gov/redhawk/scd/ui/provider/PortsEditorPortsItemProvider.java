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
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;

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
