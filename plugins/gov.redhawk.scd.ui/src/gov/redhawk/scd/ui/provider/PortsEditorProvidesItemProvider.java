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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import gov.redhawk.scd.ui.util.PortsUtil;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.provider.ProvidesItemProvider;

public class PortsEditorProvidesItemProvider extends ProvidesItemProvider {

	public PortsEditorProvidesItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) {
		if (feature == ScdPackage.Literals.ABSTRACT_PORT__REP_ID) {
			CompoundCommand command = new CompoundCommand(0);
			command.append(super.createSetCommand(domain, owner, feature, value, index));
			command.appendIfCanExecute(PortsUtil.createReplaceInterfaceCommand(domain, (AbstractPort) owner, (String) value));
			return command.unwrap();
		}
		return super.createSetCommand(domain, owner, feature, value, index);
	}

}
