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

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.ui.views.monitor.ports.PortMonitorView;
import gov.redhawk.ui.views.monitor.provider.PortsEditPlugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

public class MonitorPortHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
			try {
				final PortMonitorView view = (PortMonitorView) window.getActivePage().showView(PortMonitorView.ID);
				for (final Object obj : ss.toList()) {
					final ScaPort< ? , ? > port = PluginUtil.adapt(ScaPort.class, obj, true);
					if (port != null) {
						view.addMonitor(port);
					}
				}
			} catch (final PartInitException e) {
				PortsEditPlugin.getPlugin().getLog().log(new Status(e.getStatus().getSeverity(), "gov.redhawk.ui.views.monitor.ports", e.getLocalizedMessage(), e));
			}

		}
		// TODO Auto-generated method stub
		return null;
	}

}
