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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.LogLevels;
import gov.redhawk.logging.ui.dialogs.LoggingDetailsDialog;
import gov.redhawk.logging.ui.jobs.CreateEventChannelLogger;
import gov.redhawk.logging.ui.jobs.TailEventChannelJob;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class TailLog extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// Selection
		ISelection sel = HandlerUtil.getActiveMenuSelection(event);
		if (sel == null) {
			sel = HandlerUtil.getCurrentSelection(event);
		}
		if (!(sel instanceof IStructuredSelection)) {
			return null;
		}
		IStructuredSelection ss = (IStructuredSelection) sel;

		// Shell
		Shell shell = HandlerUtil.getActiveShellChecked(event);

		// Adapt selection and perform the tail
		for (Object obj : ss.toList()) {
			CorbaObjWrapper< ? > wrapper = Platform.getAdapterManager().getAdapter(obj, CorbaObjWrapper.class);
			if (wrapper != null && (wrapper instanceof LogConfigurationOperations)) {
				ScaDomainManager domMgr = ScaEcoreUtils.getEContainerOfType(wrapper, ScaDomainManager.class);
				if (domMgr != null) {
					handleTailLog(shell, wrapper);
				}
			}
		}

		return null;
	}

	public void handleTailLog(Shell shell, final CorbaObjWrapper< ? > wrapper) {
		// Get a label for the model object
		ScaItemProviderAdapterFactory adapterFactory = new ScaItemProviderAdapterFactory();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		String label = labelProvider.getText(wrapper);
		labelProvider.dispose();
		adapterFactory.dispose();

		// Display dialog to get logger and log level
		LoggingDetailsDialog dialog = new LoggingDetailsDialog(shell);
		dialog.create();
		dialog.getShell().setText(Messages.bind(Messages.TailLog_0, label));
		if (dialog.open() != LoggingDetailsDialog.OK) {
			return;
		}
		String logger = dialog.getLogger();
		LogLevels logLevel = dialog.getLogLevel();

		// Adjust logging
		final CreateEventChannelLogger createJob = new CreateEventChannelLogger(wrapper, logger, logLevel);
		createJob.setUser(true);
		createJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				// Do nothing if we failed
				IStatus result = event.getResult();
				if (result == null || !result.isOK()) {
					return;
				}

				// Grab the new event channel, and create a cleanup job for later
				ScaEventChannel eventChannel = createJob.getEventChannel();
				Job destroyJob = createJob.createCleanupJob();

				// View the event channel log
				Job viewJob = new TailEventChannelJob(eventChannel, destroyJob);
				viewJob.setUser(true);
				viewJob.schedule();
			}
		});
		createJob.schedule();
	}

}
