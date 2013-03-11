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

import mil.jpeojtrs.sca.diagram.DiagramPackage;
import mil.jpeojtrs.sca.diagram.EObjectContainerStyle;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.FindByStubContainer;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class FindByStubCreateCommand extends BaseCreateCommand {

	private EditPart host;

	/**
	* 
	*/
	public FindByStubCreateCommand(final CreateElementRequest req) {
		super(req);
	}

	/**
	* 
	*/
	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
		final FindByStub newElement = PartitioningFactory.eINSTANCE.createFindByStub();

		final View view = (View) this.host.getModel();
		final EObjectContainerStyle style = (EObjectContainerStyle) view.getStyle(DiagramPackage.eINSTANCE.getEObjectContainerStyle());
		FindByStubContainer value = (FindByStubContainer) style.getValue();
		if (value == null) {
			value = PartitioningFactory.eINSTANCE.createFindByStubContainer();
			style.setValue(value);
		}
		value.getStubs().add(newElement);

		doConfigure(newElement, monitor, info);

		((CreateElementRequest) getRequest()).setNewElement(newElement);
		return CommandResult.newOKCommandResult(newElement);
	}

	public void setEditPart(final EditPart host) {
		this.host = host;

	}

}
