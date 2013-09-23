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
package gov.redhawk.ui.views.domainbrowser.view.handlers;

import gov.redhawk.ui.views.domainbrowser.Activator;
import gov.redhawk.ui.views.domainbrowser.view.DomainBrowserView;
import gov.redhawk.ui.views.domainbrowser.view.DomainEntryWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class NewDomainHandler extends AbstractHandler implements IHandler {

	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Before opening up the Wizard, we grab an instance of the domabin browser view
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		DomainBrowserView domainBrowser = null;
		
		try {
			domainBrowser = (DomainBrowserView) window.getActivePage().showView(DomainBrowserView.ID);
		} catch (final PartInitException e) {
			Activator.logError("Failed to obtain domainBrowser view", e);
		}
		
		// Now we can launch the wizard
		final DomainEntryWizard wizard = new DomainEntryWizard();
		wizard.setWindowTitle("Connect to Domain Manager");

		final WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);

		if (dialog.open() == IStatus.OK) {
			final String domainName = wizard.getDomainName();
			final String domainServiceInitRef = wizard.getNameServiceInitRef();
			if (domainBrowser != null) {
				domainBrowser.setDomainAndConnect(domainName + "@" + domainServiceInitRef);
			}
		}
		
		return null;
	}

}