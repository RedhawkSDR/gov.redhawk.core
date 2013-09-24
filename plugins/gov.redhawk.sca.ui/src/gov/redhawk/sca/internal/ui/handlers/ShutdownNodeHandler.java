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

import gov.redhawk.sca.util.PluginUtil;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

import CF.DeviceManagerOperations;

// TODO: Auto-generated Javadoc
/**
 * The Class ShutdownNodeHandler.
 */
public class ShutdownNodeHandler extends AbstractHandler implements IHandler {

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
			for (final Object obj : ss.toArray()) {
				final DeviceManagerOperations op = PluginUtil.adapt(DeviceManagerOperations.class, obj);
				if (op != null) {
					final Job job = new Job("Shutting down Device Manager") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							monitor.beginTask("Shutting down: " + op.label(), IProgressMonitor.UNKNOWN);

							// Try to shutdown the Device Manager
							op.shutdown();
							return Status.OK_STATUS;
						}

					};
					job.setUser(true);
					job.schedule();
				}
			}
		}
		return null;
	}

}
