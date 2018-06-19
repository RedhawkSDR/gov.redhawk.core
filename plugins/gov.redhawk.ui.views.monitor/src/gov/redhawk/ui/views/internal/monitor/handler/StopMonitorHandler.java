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
package gov.redhawk.ui.views.internal.monitor.handler;

import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.ui.views.internal.monitor.ports.PortMonitorView;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class StopMonitorHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);

			final IViewReference[] viewReferences = window.getActivePage().getViewReferences();
			for (final IViewReference viewReference : viewReferences) {
				if (viewReference.getId().equals(PortMonitorView.ID)) {
					final PortMonitorView view = (PortMonitorView) viewReference.getView(true);
					if (view != null) {
						for (final Object obj : ss.toList()) {
							if (obj instanceof Monitor) {
								view.removeMonitor((Monitor) obj);
							}
						}
					}
				}
			}

		}
		return null;
	}

}
