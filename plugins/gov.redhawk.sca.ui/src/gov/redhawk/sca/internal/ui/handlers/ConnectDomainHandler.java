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

import java.util.Iterator;

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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ui.ScaUiPlugin;

public class ConnectDomainHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelectionChecked(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}
		IStructuredSelection ss = (IStructuredSelection) selection;

		for (Iterator< ? > iter = ss.iterator(); iter.hasNext();) {
			Object obj = iter.next();
			if (!(obj instanceof ScaDomainManager)) {
				continue;
			}
			final ScaDomainManager domMgr = (ScaDomainManager) obj;

			Job job = Job.create("Connecting Domain", monitor -> {
				monitor.beginTask("Connecting to domain " + domMgr.getLabel(), IProgressMonitor.UNKNOWN);
				try {
					domMgr.connect(monitor, RefreshDepth.SELF);
					return Status.OK_STATUS;
				} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
					return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to connect to Domain " + domMgr.getLabel(), e);
				}
			});
			job.setPriority(Job.LONG);
			job.setUser(true);
			job.schedule();
		}

		return null;
	}

}
