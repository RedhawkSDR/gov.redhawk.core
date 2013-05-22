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

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISources;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @since 2.0
 */
public class MoveComponentLaterInStartOrder extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		final SadComponentPlacement cp = SadDiagramHandlerUtil.getComponentPlacementFromSelection(selection);
		final TransactionalEditingDomain editingDomain = SadDiagramHandlerUtil.getEditingDomain(selection);

		Assert.isTrue(cp.getComponentInstantiation().size() == 1); // currently REDHAWK doesn't support more than one anyways
		final SadComponentInstantiation selectedCi = cp.getComponentInstantiation().get(0);
		Assert.isNotNull(selectedCi.getId());

		final SoftwareAssembly sa = SoftwareAssembly.Util.getSoftwareAssembly(cp.eResource());
		if (sa != null) {
			Assert.isNotNull(selectedCi.getStartOrder()); // should be prevented by enablement
			// otherwise, move all the start orders without creating new numbers
			// by swapping existing start order numbers
			EList<SadComponentInstantiation> cis = sa.getComponentInstantiationsInStartOrder();
			int selectedPosition = cis.indexOf(selectedCi);
			if ((selectedPosition + 1) < cis.size()) {
				SadComponentInstantiation laterComponentInstantiation = cis.get(selectedPosition + 1);
				if ((laterComponentInstantiation.getStartOrder() != null) && (laterComponentInstantiation.getStartOrder().intValue() != 0)) {
					// there is later to go...as long as cis is sorted correctly
					BigInteger earlierStartOrder = selectedCi.getStartOrder();
					BigInteger laterStartOrder = laterComponentInstantiation.getStartOrder();

					if (editingDomain != null) {
						CompoundCommand changeOrderCmd = new CompoundCommand("Move Earlier");
						changeOrderCmd.append(SetCommand.create(editingDomain, laterComponentInstantiation, SadPackage.Literals.SAD_COMPONENT_INSTANTIATION__START_ORDER,
						        earlierStartOrder));
						changeOrderCmd.append(SetCommand.create(editingDomain, selectedCi, SadPackage.Literals.SAD_COMPONENT_INSTANTIATION__START_ORDER, laterStartOrder));
						editingDomain.getCommandStack().execute(changeOrderCmd);
					} else {
						laterComponentInstantiation.setStartOrder(earlierStartOrder);
						selectedCi.setStartOrder(laterStartOrder);
					}
				}
			}
		}
		return null;
	}

	@Override
	public void setEnabled(Object evaluationContext) {
		if (!(evaluationContext instanceof IEvaluationContext)) {
			return;
		}
		final IEvaluationContext context = (IEvaluationContext) evaluationContext;
		final Object obj = context.getVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME);
		if (obj instanceof IStructuredSelection) {
			final IStructuredSelection structuredSelection = (IStructuredSelection) obj;
			final SadComponentPlacement cp = SadDiagramHandlerUtil.getComponentPlacementFromSelection(structuredSelection);

			Assert.isTrue(cp.getComponentInstantiation().size() == 1); // currently REDHAWK doesn't support more than one anyways
			final SadComponentInstantiation selectedCi = cp.getComponentInstantiation().get(0);
			Assert.isNotNull(selectedCi.getId());

			setBaseEnabled((selectedCi.getStartOrder() != null) && !SoftwareAssembly.Util.isAssemblyController(selectedCi));
		}
	}

}
