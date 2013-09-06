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

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

/**
 * @since 4.0
 * @deprecated
 */
@Deprecated
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
	 * @since 5.1
	 */
	public static void refreshPort(final IRefreshable refreshable, IProgressMonitor monitor) {
		new Job("Refreshing Port...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					refreshable.refresh(null, RefreshDepth.FULL);
				} catch (InterruptedException e) {
					// PASS - ignore
				}
				return Status.OK_STATUS;
			}

		} .schedule();
	}

	/**
	 * @param monitor Monitor to report status to, may be null.
	 * @since 5.1
	 */
	public static void refreshPorts(Collection< ? > ports, IProgressMonitor monitor) {
		for (Object port : ports) {
			if (port instanceof IRefreshable) {
				refreshPort((IRefreshable) port, monitor);
			}
		}
	}
}
