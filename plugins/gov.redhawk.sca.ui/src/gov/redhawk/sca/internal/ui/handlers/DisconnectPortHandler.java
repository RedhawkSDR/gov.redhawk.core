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

import gov.redhawk.model.sca.ScaConnection;
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
import org.omg.CORBA.SystemException;

import CF.PortPackage.InvalidPort;

/**
 * 
 */
public class DisconnectPortHandler extends AbstractHandler implements IHandler {

	private static class DisconnectJob extends Job {

		private final ScaConnection connection;

		public DisconnectJob(final ScaConnection connection) {
			super("Disconnect Connection Job");
			this.connection = connection;
			setPriority(Job.LONG);
		}

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			monitor.beginTask("Disconnect " + this.connection.getId(), IProgressMonitor.UNKNOWN);
			try {
				try {
					this.connection.getPort().disconnectPort(this.connection);
					return Status.OK_STATUS;
				} catch (final InvalidPort e) {
					return new Status(Status.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to diconnect " + this.connection.getId());
				} catch (final SystemException e) {
					return new Status(Status.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to diconnect " + this.connection.getId());
				}
			} finally {
				monitor.done();
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toArray()) {
				if (obj instanceof ScaConnection) {
					new DisconnectJob((ScaConnection) obj).schedule();
				}
			}
		}
		return null;
	}

}
