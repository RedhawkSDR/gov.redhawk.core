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
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class InitHandler.
 */
public class RemoveDomainHandler extends AbstractHandler implements IHandler {

	/**
	 * {@inheritDoc}
	 */

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final MessageDialog dialog = new MessageDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Delete Domain Connection", null,
			        "Are you sure you want to remove the selected domains?", MessageDialog.QUESTION, new String[] { "OK", "Cancel" }, 0);
			if (dialog.open() == Window.OK) {
				for (final Object obj : ss.toArray()) {
					if (obj instanceof ScaDomainManager) {
						final ScaDomainManager domMgr = (ScaDomainManager) obj;
						domMgr.disconnect();
						ScaModelCommand.execute(domMgr, new ScaModelCommand() {

							@Override
							public void execute() {
								ScaPlugin.getDefault().getDomainManagerRegistry().getDomains().remove(domMgr);
							}

						});
					}
				}
			}
		}
		return null;
	}

}
