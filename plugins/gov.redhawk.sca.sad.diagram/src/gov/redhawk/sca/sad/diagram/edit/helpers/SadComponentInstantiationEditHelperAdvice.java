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
package gov.redhawk.sca.sad.diagram.edit.helpers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * 
 */
public class SadComponentInstantiationEditHelperAdvice extends gov.redhawk.diagram.edit.helpers.ComponentInstantiationEditHelperAdvice {

	@Override
	protected ICommand getAfterDestroyElementCommand(final DestroyElementRequest request) {
		return createReassignAssemblyControllerCommand(request);
	}

	private ICommand createReassignAssemblyControllerCommand(final DestroyElementRequest request) {
		final EObject container = request.getContainer();
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(container.eResource());
		final SadComponentInstantiation ci = (SadComponentInstantiation) request.getElementToDestroy();

		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null
		        && !sad.getAssemblyController().getComponentInstantiationRef().getRefid().equals(ci.getId())) {
			return null;
		}

		final List<SadComponentInstantiation> allComponents = getAllComponents(sad);
		allComponents.remove(ci);

		if (allComponents.size() > 0) {
			final SadComponentInstantiation newAssem = allComponents.get(0);
			final AbstractTransactionalCommand command = new AbstractTransactionalCommand(request.getEditingDomain(), "Reassign Assembly Controller", null) {

				@Override
				protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
					final AssemblyController ac = SadFactory.eINSTANCE.createAssemblyController();
					final SadComponentInstantiationRef ref = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
					ref.setInstantiation(newAssem);
					newAssem.setStartOrder(BigInteger.valueOf(0)); // assembly controller always has start-order 0
					ac.setComponentInstantiationRef(ref);
					sad.setAssemblyController(ac);
					return CommandResult.newOKCommandResult();
				}
			};

			return command;
		} else {
			final AbstractTransactionalCommand command = new AbstractTransactionalCommand(request.getEditingDomain(), "Unassign Assembly Controller", null) {

				@Override
				protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
					sad.setAssemblyController(null);
					return CommandResult.newOKCommandResult();
				}
			};

			return command;
		}

	}

	private List<SadComponentInstantiation> getAllComponents(final SoftwareAssembly sad) {
		final List<SadComponentInstantiation> retVal = new ArrayList<SadComponentInstantiation>();
		if (sad.getPartitioning() != null) {
			for (final SadComponentPlacement cp : sad.getPartitioning().getComponentPlacement()) {
				retVal.addAll(cp.getComponentInstantiation());
			}
			for (final HostCollocation h : sad.getPartitioning().getHostCollocation()) {
				for (final SadComponentPlacement cp : h.getComponentPlacement()) {
					retVal.addAll(cp.getComponentInstantiation());
				}
			}
		}

		return retVal;
	}
}
