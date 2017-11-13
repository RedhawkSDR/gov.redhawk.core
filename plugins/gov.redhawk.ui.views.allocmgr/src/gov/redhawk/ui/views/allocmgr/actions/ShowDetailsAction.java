/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.allocmgr.actions;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import gov.redhawk.ui.views.allocmgr.AllocMgrPlugin;

public class ShowDetailsAction extends Action {

	private IWorkbenchPage page;

	public ShowDetailsAction(IWorkbenchPage page) {
		super("Show Details", IAction.AS_PUSH_BUTTON);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(AllocMgrPlugin.ID, "icons/details.png"));
		this.page = page;
	}

	@Override
	public void run() {
		try {
			page.showView(IPageLayout.ID_PROP_SHEET);
		} catch (PartInitException e) {
			IStatus status = new Status(IStatus.ERROR, AllocMgrPlugin.ID, "Unable to open properties view", e);
			ErrorDialog.openError(Display.getDefault().getActiveShell(), "Error", "Unable to open properties view", status);
		}
	}

}
