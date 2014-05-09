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
package gov.redhawk.ui.port;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.util.CorbaUtils;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @since 4.0
 */
public class PortHelper {

	private PortHelper() {
	}

	public static boolean executeCommand(final IWorkbenchPartSite site, final String commandId) throws CommandException {
		return PortHelper.executeCommand(site, commandId, null, null, null);
	}

	public static boolean executeCommand(final IWorkbenchPartSite site, final String commandId, final Map<String, String> paramMap) throws CommandException {
		return PortHelper.executeCommand(site, commandId, paramMap, null, null);
	}

	public static boolean executeCommand(final IWorkbenchPartSite site, final String commandId, final Map<String, String> paramMap, final String varName,
			final Object variable) throws CommandException {
		final IHandlerService handlerService = (IHandlerService) site.getService(IHandlerService.class);
		final ICommandService commandService = (ICommandService) site.getService(ICommandService.class);
		if ((handlerService == null) || (commandService == null)) {
			return false;
		}

		final ParameterizedCommand pcmd;
		final ExecutionEvent evt;
		final Command cmd = commandService.getCommand(commandId);
		if (cmd == null) {
			return false;
		}

		if (paramMap != null) {
			pcmd = ParameterizedCommand.generateCommand(cmd, paramMap);
			evt = handlerService.createExecutionEvent(pcmd, null);
		} else {
			pcmd = null;
			evt = handlerService.createExecutionEvent(cmd, null);
		}

		if (varName != null) {
			((IEvaluationContext) evt.getApplicationContext()).addVariable(varName, variable);
		}

		if (pcmd != null) {
			pcmd.executeWithChecks(evt.getTrigger(), evt.getApplicationContext());
		} else {
			cmd.executeWithChecks(evt);
		}

		return true;
	}

	/**
	 * @param monitor Monitor to report status to, may be null.
	 * @since 5.2
	 */
	public static void refreshPort(final IRefreshable refreshable, IProgressMonitor monitor) {
		refreshPort(refreshable, monitor, 0);
	}

	/** utility method to refresh Port in background job.
	 * @param monitor Monitor to report status to, may be null.
	 * @since 5.2
	 */
	public static void refreshPort(final IRefreshable refreshable, IProgressMonitor monitor, long delay) {
		if (!PlatformUI.isWorkbenchRunning()) {
			return;
		}
		new Job("Refreshing Port...") {
			
			public boolean shouldSchedule() {
				return super.shouldSchedule() && PlatformUI.isWorkbenchRunning();
			}
			
			public boolean shouldRun() {
				return super.shouldRun() && PlatformUI.isWorkbenchRunning();
			}
			
			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					CorbaUtils.invoke(new Callable<Object>() {

						@Override
						public Object call() throws Exception {
							try {
								refreshable.refresh(monitor, RefreshDepth.FULL);
							} catch (InterruptedException e) {
								// PASS - ignore
							}
							return null;
						}
						
					}, monitor);
				} catch (CoreException e1) {
					return e1.getStatus();
				} catch (InterruptedException e1) {
					return Status.CANCEL_STATUS;
				}
				
				return Status.OK_STATUS;
			}

		} .schedule(delay);
	}

	/**
	 * @param monitor Monitor to report status to, may be null.
	 * @since 5.2
	 */
	public static void refreshPorts(Collection< ? > ports, IProgressMonitor monitor) {
		for (Object port : ports) {
			if (port instanceof IRefreshable) {
				refreshPort((IRefreshable) port, monitor);
			}
		}
	}
}
