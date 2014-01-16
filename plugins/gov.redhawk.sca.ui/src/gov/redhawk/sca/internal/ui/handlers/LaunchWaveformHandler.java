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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformsContainerItemProvider;
import gov.redhawk.sca.internal.ui.wizards.LaunchWaveformWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class LaunchWaveformHandler extends AbstractHandler implements IHandler {

	@Override
	public void setEnabled(final Object evaluationContext) {
		if ((evaluationContext != null) && (evaluationContext instanceof IEvaluationContext)) {
			final IEvaluationContext context = (IEvaluationContext) evaluationContext;
			final Object sel = context.getVariable("selection");
			if (sel instanceof IStructuredSelection) {
				final IStructuredSelection ss = (IStructuredSelection) sel;
				boolean enabled = true;
				for (final Object obj : ss.toArray()) {
					if (obj instanceof ScaDomainManager) {
						final ScaDomainManager domMgr = (ScaDomainManager) obj;
						if (!domMgr.getState().equals(DomainConnectionState.CONNECTED)) {
							enabled = false;
						}
					}
				}
				this.setBaseEnabled(enabled);
			} else {
				super.setEnabled(evaluationContext);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			final Object element = ss.getFirstElement();
			ScaDomainManager domMgr = null;
			String sadId = null;
			if (element instanceof ScaDomainManager) {
				domMgr = ((ScaDomainManager) element);
			} else if (element instanceof ScaWaveformFactoriesContainerItemProvider) {
				domMgr = (ScaDomainManager) ((ScaWaveformFactoriesContainerItemProvider) element).getTarget();
			} else if (element instanceof ScaWaveformsContainerItemProvider) {
				domMgr = (ScaDomainManager) ((ScaWaveformsContainerItemProvider) element).getTarget();
			} else if (element instanceof ScaWaveformFactory) {
				final ScaWaveformFactory factory = (ScaWaveformFactory) element;
				domMgr = factory.getDomMgr();
				sadId = factory.getProfileObj().getId();
			} else {
				return null;
			}

			final LaunchWaveformWizard wizard = new LaunchWaveformWizard(domMgr);
			wizard.getWaveformPage().setSoftwareAssemblySelection(sadId);
			final WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			dialog.open();
		}
		// TODO Auto-generated method stub
		return null;
	}
}
