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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.FileStoreEditorInput;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.logging.ui.editors.LogConfigEditor;
import gov.redhawk.logging.ui.preferences.LoggingPreferenceInitializer;
import mil.jpeojtrs.sca.util.CorbaUtils2;

public class EditLogConfig extends AbstractHandler {

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
				LogConfigurationOperations resource = (LogConfigurationOperations) Platform.getAdapterManager().getAdapter(obj,
					LogConfigurationOperations.class);
				if (resource == null) {
					continue;
				}

				if (resource != null) {
					handleEditLogConfiguration(resource, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage());
				}
			}
		}

		return null;
	}

	// Launch plain text editor for making live changes to a resource's log configuration
	public void handleEditLogConfiguration(final LogConfigurationOperations resource, final IWorkbenchPage activePage) {
		if (!(launchWarningDialog(activePage))) {
			return;
		}

		final Job editLogConfigJob = Job.create("Fetching Log Configuration File...", monitor -> {
			SubMonitor progress = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);
			String logConfig;
			try {
				// Retrieve the logging config from the running resource
				logConfig = CorbaUtils2.invoke(() -> {
					return resource.getLogConfig();
				}, progress);
			} catch (java.util.concurrent.ExecutionException e) {
				return new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, "Unable to retrieve logging configuration from resource", e.getCause());
			}

			try {
				// Create a temporary file to use as input for the editor; write the config to it
				File directory = LoggingUiPlugin.getDefault().getStateLocation().toFile();
				File file = File.createTempFile("logConfiguration", ".tmp.txt", directory);

				try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
					bw.write(logConfig);
				}

				final IPath path = new Path(file.getAbsolutePath());
				final IFileStore fileStore = EFS.getLocalFileSystem().getStore(path);
				final FileStoreEditorInput input = new FileStoreEditorInput(fileStore);

				Display.getDefault().asyncExec(() -> {
					try {
						LogConfigEditor editor = (LogConfigEditor) activePage.openEditor(input, LogConfigEditor.ID);
						editor.setResource(resource);
					} catch (PartInitException e) {
						ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Log Config Editor Error",
							"Failed to open LogConfigurationEditor",
							new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, "Log Config Editor could not open"));
					}
				});
			} catch (IOException e) {
				return new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, "Unable to write logging config to a temporary file", e);
			}

			return Status.OK_STATUS;
		});

		editLogConfigJob.setUser(true);
		editLogConfigJob.schedule();

	}

	/**
	 * Launch a toggle dialog that warns a user that edits to the log config will take place immediately on save.</br>
	 * Bail if the user selects 'No'</br>
	 * Has a toggle option for 'Don't show this warning again'.
	 */
	private boolean launchWarningDialog(IWorkbenchPage activePage) {
		IPreferenceStore prefStore = LoggingUiPlugin.getDefault().getPreferenceStore();
		boolean showWarning = prefStore.getBoolean(LoggingPreferenceInitializer.SHOW_LOG_CONFIG_WARNING);
		if (showWarning) {
			Shell shell = activePage.getActivePart().getSite().getShell();
			MessageDialogWithToggle dialog = MessageDialogWithToggle.openYesNoQuestion(shell, "Opening Error Log Config",
				"WARNING: This is a runtime editor.  Saving changes made in the Log Configuration Editor will immediately update the resource's logging"
					+ " configuration settings.  Do you wish to continue?",
				"Don't show this warning again", true, null, null);

			if (dialog.getReturnCode() == IDialogConstants.NO_ID) {
				return false;
			}

			if (dialog.getReturnCode() == IDialogConstants.YES_ID && dialog.getToggleState()) {
				LoggingUiPlugin.getDefault().getPreferenceStore().setValue(LoggingPreferenceInitializer.SHOW_LOG_CONFIG_WARNING, false);
			}

		}
		return true;
	}

}
