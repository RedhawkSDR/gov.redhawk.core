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

import gov.redhawk.sca.ui.ScaUiPlugin;

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

import CF.LifeCycleOperations;
import CF.LifeCyclePackage.InitializeError;

// TODO: Auto-generated Javadoc
/**
 * The Class InitHandler.
 */
public class InitHandler extends AbstractHandler implements IHandler {

	/**
	 * {@inheritDoc}
	 */

	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toArray()) {
				if (obj instanceof LifeCycleOperations) {
					final LifeCycleOperations op = (LifeCycleOperations) obj;
					final Job job = new Job("Initializing") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							monitor.beginTask("Initializing: " + op, IProgressMonitor.UNKNOWN);
							try {
								op.initialize();
								return Status.OK_STATUS;
							} catch (final InitializeError e) {
								return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to initialize: " + op, e);
							}
						}

					};
					job.schedule();
				}
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

}
