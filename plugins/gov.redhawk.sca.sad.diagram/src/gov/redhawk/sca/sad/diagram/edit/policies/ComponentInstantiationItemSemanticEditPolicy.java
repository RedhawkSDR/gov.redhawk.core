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

package gov.redhawk.sca.sad.diagram.edit.policies;

import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.sad.ExternalProperty;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.notation.View;

public class ComponentInstantiationItemSemanticEditPolicy extends mil.jpeojtrs.sca.sad.diagram.edit.policies.SadComponentInstantiationItemSemanticEditPolicy {

	private final gov.redhawk.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicyHelper editPolicyHelper = new gov.redhawk.diagram.edit.policies.ComponentInstantiationItemSemanticEditPolicyHelper(); // SUPPRESS CHECKSTYLE Line length

	@Override
	protected Command getCreateCommand(final CreateElementRequest req) {
		return this.editPolicyHelper.getCreateCommand(req);
	}

	@Override
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {
		final Command command = super.getDestroyElementCommand(req);
		final CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(getEditingDomain(), null);

		addDestroyExternalPortsCommand(cmd, (View) getHost().getModel());
		addDestoryExternalPropertiesCommand(cmd, (View) getHost().getModel());

		if (!cmd.isEmpty()) {
			return command.chain(getGEFWrapper(cmd.reduce()));
		} else {
			return command;
		}
	}

	private void addDestoryExternalPropertiesCommand(CompositeTransactionalCommand cmd, final View view) {
		final SadComponentInstantiation inst = (SadComponentInstantiation) view.getElement();
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(inst.eResource());

		if (sad.getExternalProperties() != null) {
			int removing = 0;
			for (ExternalProperty prop : sad.getExternalProperties().getProperties()) {
				if (PluginUtil.equals(prop.getCompRefID(), inst.getId())) {
					final DestroyElementRequest r = new DestroyElementRequest(prop, false);
					cmd.add(new DestroyElementCommand(r));
					removing++;
				}
			}
			if (removing == sad.getExternalProperties().getProperties().size()) {
				final DestroyElementRequest r = new DestroyElementRequest(sad.getExternalProperties(), false);
				cmd.add(new DestroyElementCommand(r));
			}
		}
	}

	private void addDestroyExternalPortsCommand(final CompositeTransactionalCommand cmd, final View view) {
		final SadComponentInstantiation inst = (SadComponentInstantiation) view.getElement();
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(inst.eResource());
		int ports = 0;

		if (sad.getExternalPorts() != null) {
			for (final Port port : sad.getExternalPorts().getPort()) {
				if (port.getComponentInstantiationRef().getRefid().equals(inst.getId())) {
					final DestroyElementRequest r = new DestroyElementRequest(port, false);
					cmd.add(new DestroyElementCommand(r));
					ports++;
				}
			}

			if (ports == sad.getExternalPorts().getPort().size()) {
				final DestroyElementRequest r = new DestroyElementRequest(sad.getExternalPorts(), false);
				cmd.add(new DestroyElementCommand(r));
			}
		}
	}
}
