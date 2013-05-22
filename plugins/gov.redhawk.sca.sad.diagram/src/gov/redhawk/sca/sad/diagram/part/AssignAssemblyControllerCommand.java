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
package gov.redhawk.sca.sad.diagram.part;

import java.math.BigInteger;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

public class AssignAssemblyControllerCommand extends AbstractHandler implements IHandler {

	public Object execute(final ExecutionEvent event) throws ExecutionException {

		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		// TODO utilize SadDiagramHandlerUtil to leverage common behavior
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.size() != 1) {
				return null;
			}
			if (structuredSelection.getFirstElement() instanceof GraphicalEditPart
			        && ((GraphicalEditPart) structuredSelection.getFirstElement()).getModel() instanceof View) {
				final GraphicalEditPart editPart = (GraphicalEditPart) structuredSelection.getFirstElement();
				final TransactionalEditingDomain editingDomain = editPart.getEditingDomain();
				final View view = (View) editPart.getModel();
				
				SadComponentPlacement cp = null;

				if (view.getElement() instanceof ComponentPlacement) {
					cp = (SadComponentPlacement) view.getElement();
				} else {
					cp = (SadComponentPlacement) view.getElement().eContainer();
				}
				final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(cp.eResource());
				Assert.isNotNull(sad);
				final SadComponentInstantiation originalAssemblyController = SoftwareAssembly.Util.getAssemblyControllerInstantiation(sad);

				// Set the assembly controller to the specified component
				Assert.isNotNull(cp);
				Assert.isTrue(cp.getComponentInstantiation().size() == 1);
				final SadComponentInstantiation newAssemblyController = cp.getComponentInstantiation().get(0);
				final AssemblyController ac = SadFactory.eINSTANCE.createAssemblyController();
				final SadComponentInstantiationRef ref = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
				ref.setRefid(cp.getComponentInstantiation().get(0).getId());
				ac.setComponentInstantiationRef(ref);
				if (cp.eResource() == null) {
					return null;
				}
				
				CompoundCommand assignAssemblyControllerCmd = new CompoundCommand();
				 


				// The original assembly controller moves to the end
                if (originalAssemblyController != null) {
					int startOrder = SoftwareAssembly.Util.getLastStartOrder(sad);
					if ((newAssemblyController.getStartOrder() != null) && (newAssemblyController.getStartOrder().intValue() != startOrder)) {
						// if the new assembly controller happened to be the last in the start order, we don't need to add one
						// otherwise we must put the assembly controller at the end...because there isn't anywhere else to really put it
					    startOrder += 1;
					}
					assignAssemblyControllerCmd.append(SetCommand.create(editingDomain, originalAssemblyController, SadPackage.Literals.SAD_COMPONENT_INSTANTIATION__START_ORDER, BigInteger.valueOf(startOrder)));
				}
				
				// Set the start order of the assembly controller to zero
                assignAssemblyControllerCmd.append(SetCommand.create(editingDomain, newAssemblyController, SadPackage.Literals.SAD_COMPONENT_INSTANTIATION__START_ORDER, BigInteger.valueOf(0)));
                assignAssemblyControllerCmd.append(SetCommand.create(editingDomain, sad, SadPackage.Literals.SOFTWARE_ASSEMBLY__ASSEMBLY_CONTROLLER, ac));
				editingDomain.getCommandStack().execute(assignAssemblyControllerCmd);
				
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(final Object evaluationContext) {
		if (!(evaluationContext instanceof IEvaluationContext)) {
			return;
		}
		final IEvaluationContext context = (IEvaluationContext) evaluationContext;
		final Object obj = context.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
		if (obj instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) obj;
			if (structuredSelection.getFirstElement() instanceof GraphicalEditPart
			        && ((GraphicalEditPart) structuredSelection.getFirstElement()).getModel() instanceof View) {
				final GraphicalEditPart editPart = (GraphicalEditPart) structuredSelection.getFirstElement();
				final View view = (View) editPart.getModel();

				SadComponentPlacement cp = null;

				if (view.getElement() instanceof ComponentPlacement) {
					cp = (SadComponentPlacement) view.getElement();
				} else if (view.getElement() instanceof ComponentInstantiation) {
					cp = (SadComponentPlacement) view.getElement().eContainer();
				} else {
					setBaseEnabled(false);
					return;
				}

				if (cp == null || cp.eResource() == null) {
					setBaseEnabled(false);
					return;
				}
				final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(cp.eResource());
				if (!editPart.isEditModeEnabled()) {
					setBaseEnabled(false);
				} else {
					try {
						if (cp.getComponentInstantiation().isEmpty()) {
							setBaseEnabled(false);
						} else if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null
						        && sad.getAssemblyController().getComponentInstantiationRef().getRefid().equals(cp.getComponentInstantiation().get(0).getId())) {
							setBaseEnabled(false);
						} else {
							setBaseEnabled(true);
						}
					} catch (final NullPointerException e) {
						setBaseEnabled(true);
					}
				}
			} else {
				setBaseEnabled(false);
			}
		}
	}

}
