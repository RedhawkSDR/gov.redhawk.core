package gov.redhawk.logging.ui.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Callable;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
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
import mil.jpeojtrs.sca.util.CorbaUtils;

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

		final Job editLogConfigJob = new Job("Fetching Log Configuration File...") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask(getName(), IProgressMonitor.UNKNOWN);
				try {
					CorbaUtils.invoke(new Callable<Object>() {
						public Object call() throws Exception {
							// Get the logConfig string
							String logConfig = resource.getLogConfig();

							// Create a temporary file to use as input for the editor
							File file = createTemporaryFile();

							BufferedWriter bw = new BufferedWriter(new FileWriter(file));
							bw.write(logConfig);
							bw.close();

							final IPath path = new Path(file.getAbsolutePath());
							final IFileStore fileStore = EFS.getLocalFileSystem().getStore(path);
							final FileStoreEditorInput input = new FileStoreEditorInput(fileStore);

							Display.getDefault().asyncExec(new Runnable() {

								@Override
								public void run() {
									try {
										LogConfigEditor editor = (LogConfigEditor) activePage.openEditor(input, LogConfigEditor.ID);
										editor.setResource(resource);
										editor.setFilePath(path);
									} catch (PartInitException e) {
										ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(), "Log Config Editor Error",
											"Failed to open LogConfigurationEditor",
											new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, "Log Config Editor could not open"));
									}
								}
							});

							return null;
						}


					}, monitor);
				} catch (CoreException e) {
					return new Status(e.getStatus().getSeverity(), LoggingUiPlugin.PLUGIN_ID, e.getLocalizedMessage(), e);
				} catch (InterruptedException e) {
					return Status.CANCEL_STATUS;
				} finally {
					monitor.done();
				}

				return Status.OK_STATUS;
			}
		};

		editLogConfigJob.setUser(true);
		editLogConfigJob.schedule();

	}
	
	/**
	 * Creates a temporary file that will be populated with then resources log config and serve as the input for the LogConfigEditor
	 * @throws IOException 
	 */
	private File createTemporaryFile() throws IOException {
		String tmpLocation = "/log_configuration_tmpfile";
		IPath tmpFilePath = Platform.getLocation();
		tmpFilePath = tmpFilePath.append(tmpLocation);
		File file = new File(tmpFilePath.toString());
		
		int appender = 1;
		while (file.exists()) {
			file = new File(tmpFilePath + "_" + appender++);
		}

		file.createNewFile();
		
		return file;
	};

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
				"WARNING: This is a real-time editor.  Saving changes made in the Log Configuration Editor will immediately update the resources logging"
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
