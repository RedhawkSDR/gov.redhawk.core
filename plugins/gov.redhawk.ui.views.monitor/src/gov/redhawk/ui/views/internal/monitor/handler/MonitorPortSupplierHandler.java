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

import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.ui.views.monitor.MonitorViewPlugin;
import gov.redhawk.ui.views.monitor.ports.PortMonitorView;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 */
public class MonitorPortSupplierHandler extends AbstractHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
			try {
				final PortMonitorView view = (PortMonitorView) window.getActivePage().showView(PortMonitorView.ID);
				for (final Object obj : ss.toList()) {
					final ScaPortContainer scaObj = PluginUtil.adapt(ScaPortContainer.class, obj, true);
					if (scaObj != null) {
						if (scaObj.isSetPorts()) {
							view.addMonitor(scaObj);
						} else {
							final Job fetchPorts = new Job("Fetching Ports") {

								@Override
								protected IStatus run(final IProgressMonitor monitor) {
									scaObj.fetchPorts(monitor);
									return Status.OK_STATUS;
								}

							};
							fetchPorts.schedule();
							view.addMonitor(scaObj);
						}

					}
				}
			} catch (final PartInitException e) {
				MonitorViewPlugin.getDefault().getLog().log(new Status(e.getStatus().getSeverity(), MonitorViewPlugin.PLUGIN_ID, e.getLocalizedMessage(), e));
			}
		}
		return null;
	}

}
