/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.diagram.edit.commands;

import gov.redhawk.sca.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @since 3.0
 * 
 */
public class FindByStubProvidesPortStubCreateCommand extends BaseCreateCommand {

	public FindByStubProvidesPortStubCreateCommand(final CreateElementRequest req) {
		super(req);
	}

	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final ProvidesPortStub newElement = PartitioningFactory.eINSTANCE.createProvidesPortStub();

		final FindByStub owner = (FindByStub) getElementToEdit();
		owner.getProvides().add(newElement);

		doConfigure(newElement, monitor, info);
		newElement.setName(getUniqueName(owner.getProvides()));

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	private String getUniqueName(final EList<ProvidesPortStub> eList) {
		String name = "Provides";
		final List<String> names = new ArrayList<String>();

		for (final ProvidesPortStub stub : eList) {
			if (stub.getName() != null) {
				names.add(stub.getName());
			}
		}

		name = StringUtil.createUniqueString(name, names, StringUtil.getDefaultComparator(), StringUtil.getDefaultUpdateStrategy());

		return name;
	}
}
