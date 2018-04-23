/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.logging.ui.handlers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.TRANSIENT;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.dialogs.SetLogLevelDialog;
import mil.jpeojtrs.sca.util.CorbaUtils2;

public class SetLoggingLevel extends AbstractHandler {

	private int currentLogLevel = -1;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		if (sel == null) {
			sel = HandlerUtil.getCurrentSelection(event);
		}

		// Get the object the user was selecting at the time.
		if (sel instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) sel;

			// There should really only be a single selected object since our extension point limits the enabled state
			// of this to a selection of 1.
			for (final Object obj : ss.toList()) {
				// Our extension point also ensures that the object is adaptable to LogConfigurationOperations so we're
				// confident this cast will succeed and be non-null.
				LogConfigurationOperations resource = (LogConfigurationOperations) Platform.getAdapterManager().getAdapter(obj,
					LogConfigurationOperations.class);

				if (resource != null) {
					handleSetLoggingLevel(resource, HandlerUtil.getActiveShell(event));
				}
			}
		}

		return null;
	}

	/**
	 * Uses a Progress Monitor Dialog that the user can cancel to fetch the
	 * current log level of the resource. Then opens up the custom dialog so
	 * the user can change the log value. It then displays a Progress Monitor
	 * Dialog as the value is changed on the resource.
	 * @param resource The resource, component, device, waveform, whatever which inherits from the
	 * LogConfigurationOperations interface
	 * @param activeShell The active shell so that additional UI dialogs can be brought up
	 * @throws CoreException
	 */
	public void handleSetLoggingLevel(final LogConfigurationOperations resource, Shell activeShell) {

		/**
		 * This is the first progress monitor dialog, fetching the current log level of the resource.
		 */
		try {
			new ProgressMonitorDialog(activeShell).run(true, true, monitor -> {
				monitor.beginTask("Fetching current log level...", IProgressMonitor.UNKNOWN);
				currentLogLevel = CorbaUtils2.invokeUI(() -> {
					return resource.log_level();
				}, monitor);
			});
		} catch (InvocationTargetException e) {
			// Determine what caused the error via getErrorCause.
			MessageDialog warnDialog = new MessageDialog(activeShell, "Cannot set logging level", null,
				"The IDE failed to fetch the current logging level from the resource.  " + getErrorCause(e), MessageDialog.WARNING, new String[] { "OK" }, 0);

			warnDialog.open();
			return;
		} catch (InterruptedException e) {
			return;
		}

		// This is our custom dialog to set the log level of the resource.
		SetLogLevelDialog dialog = new SetLogLevelDialog(activeShell, currentLogLevel);
		if (dialog.open() == Dialog.OK) {
			final int desiredLogLevel = dialog.getDesiredLogLevel();

			// Our third and final dialog, showing progress as the logging level is set.
			try {
				new ProgressMonitorDialog(activeShell).run(true, true, monitor -> {
					monitor.beginTask("Setting log level...", IProgressMonitor.UNKNOWN);
					CorbaUtils2.invokeUI(() -> {
						try {
							resource.log_level(desiredLogLevel);
							return null;
						} catch (IllegalStateException e) {
							throw new Exception("The resource is no longer available.");
						}
					}, monitor);
				});
			} catch (InvocationTargetException e) {
				MessageDialog warnDialog = new MessageDialog(activeShell, "Could not set log level", null, getErrorCause(e), MessageDialog.ERROR,
					new String[] { "OK" }, 0);

				warnDialog.open();
				return;
			} catch (InterruptedException e) {
				return;
			}
		}
	}

	private String getErrorCause(InvocationTargetException e) {
		StringBuffer sb = new StringBuffer();
		if (e.getCause() instanceof BAD_OPERATION) {
			sb.append("The resource is responding but does not appear to support the logging API (Received BAD_OPERATION exception)");
		} else if (e.getCause() instanceof TRANSIENT) {
			sb.append("The resource is not responding: " + e.getCause().getMessage());
		} else {
			sb.append("An unexpected exception occured: " + e.getCause().getMessage());
		}
		return sb.toString();
	}

}
