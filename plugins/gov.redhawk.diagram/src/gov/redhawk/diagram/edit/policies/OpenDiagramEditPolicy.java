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

// BEGIN GENERATED CODE
package gov.redhawk.diagram.edit.policies;

import gov.redhawk.sca.ui.ScaUI;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * @since 3.0
 */
public class OpenDiagramEditPolicy extends OpenEditPolicy {

	@Override
	protected Command getOpenCommand(final Request request) {
		final EditPart targetEditPart = getTargetEditPart(request);
		if (false == targetEditPart.getModel() instanceof View) {
			return null;
		}
		final View view = (View) targetEditPart.getModel();
		final EObject element = view.getElement();
		return new ICommandProxy(new OpenDiagramCommand(element));
	}

	private static class OpenDiagramCommand extends AbstractTransactionalCommand {

		private final EObject obj;

		OpenDiagramCommand(final EObject obj) {
			// editing domain is taken for original diagram,
			// if we open diagram from another file, we should use another
			// editing domain
			super(TransactionUtil.getEditingDomain(obj), "Open", null);
			this.obj = obj;
		}

		@Override
		protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {

			try {
				final EObject target = getTarget(this.obj);
				final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				ScaUI.openEditorOnEObject(page, target, true);
				return CommandResult.newOKCommandResult();
			} catch (final Exception ex) {
				throw new ExecutionException("Can't open editor", ex);
			}
		}

		private EObject getTarget(final EObject input) {
			EObject target = input;
			// TODO?
			//			final CorbaObjWrapper< ? > wrapper = PluginUtil.adapt(CorbaObjWrapper.class, this.obj);
			//			if (wrapper != null) {
			//				target = wrapper;
			//			}
			if (target instanceof ComponentInstantiation) {
				target = ((ComponentInstantiation) input).getPlacement().getComponentFileRef().getFile().getSoftPkg();
			}
			return target;
		}

		@Override
		public boolean canExecute() {
			final EObject target = getTarget(this.obj);
			return ScaUI.editorExistsFor(target, true);
		}
	}

}
