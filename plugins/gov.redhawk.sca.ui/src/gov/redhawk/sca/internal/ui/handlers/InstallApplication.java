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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.sca.internal.ui.wizards.app.InstallApplicationWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class InstallApplication.
 */
public class InstallApplication extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;
			final Object element = ss.getFirstElement();
			ScaDomainManager domMgr = null;
			if (element instanceof ScaDomainManager) {
				domMgr = (ScaDomainManager) element;
			}
			if (element instanceof ScaWaveformFactoriesContainerItemProvider) {
				final ScaWaveformFactoriesContainerItemProvider provider = (ScaWaveformFactoriesContainerItemProvider) element;
				domMgr = (ScaDomainManager) provider.getTarget();
			}
			if (domMgr != null) {
				final InstallApplicationWizard wizard = new InstallApplicationWizard(domMgr);
				final WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
				dialog.open();
			}
		}
		return null;
	}

}
