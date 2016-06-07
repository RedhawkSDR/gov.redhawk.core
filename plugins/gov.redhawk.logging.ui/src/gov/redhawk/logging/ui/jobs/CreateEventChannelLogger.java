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
package gov.redhawk.logging.ui.jobs;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.BAD_PARAM;

import CF.LogConfiguration;
import CF.LogConfigurationHelper;
import gov.redhawk.logging.ui.LogLevels;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.logging.ui.config.Log4JConfigGenerator;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class CreateEventChannelLogger extends Job {

	private CorbaObjWrapper< ? > modelObject;
	private LogConfiguration corbaObject;
	private String logger;
	private LogLevels logLevel;
	private ScaDomainManager domMgr;

	private String eventChannelName = null;
	private String oldLogConfig = null;
	private ScaEventChannel eventChannel = null;
	private OrbSession session;

	public CreateEventChannelLogger(CorbaObjWrapper< ? > modelObject, String logger, LogLevels logLevel) {
		super(Messages.CreateEventChannelLogger_0);
		this.modelObject = modelObject;
		this.logger = logger;
		this.logLevel = logLevel;

		this.domMgr = ScaEcoreUtils.getEContainerOfType((EObject) modelObject, ScaDomainManager.class);
		if (this.domMgr == null) {
			throw new IllegalArgumentException(Messages.CreateEventChannelLogger_1);
		}
	}

	/**
	 * Create a job to undo the logging configuration changes made by this job. This should only be called if this job
	 * has already completed successfully.
	 * @return
	 */
	public Job createCleanupJob() {
		return new DestroyEventChannelLoggerJob(session, corbaObject, eventChannelName);
	}

	/**
	 * Gets the event channel created to receive logging events
	 * @return the event channel, or null if not created
	 */
	public ScaEventChannel getEventChannel() {
		return eventChannel;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_NARROW = 1;
		final int WORK_ADJUST_LOG_CONFIG = 2;
		final int WORK_FETCH_EVENT_CHANNELS = 2;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_NARROW + WORK_ADJUST_LOG_CONFIG + WORK_FETCH_EVENT_CHANNELS);

		// Generate event channel name, new logging configuration
		eventChannelName = createEventChannelName();

		// Create a new session
		session = OrbSession.createSession("gov.redhawk.logging.ui"); //$NON-NLS-1$

		// Get a narrowed CORBA object using our own ORB (so we're independent of the model)
		// We use an unchecked narrow, but we specifically handle a BAD_PARAM exception below
		String ior = modelObject.getIor();
		org.omg.CORBA.Object obj = session.getOrb().string_to_object(ior);
		corbaObject = LogConfigurationHelper.unchecked_narrow(obj);

		// Update the logging config
		try {
			oldLogConfig = CorbaUtils.invoke(new Callable<String>() {
				@Override
				public String call() throws Exception {
					String logConfig = corbaObject.getLogConfig();
					List<String> oldAppenders = Log4JConfigGenerator.getExistingAppenders(logConfig, logger);
					String appendToConfig = Log4JConfigGenerator.createLog4jConfig(domMgr.getName(), eventChannelName, logger, logLevel, oldAppenders);
					corbaObject.setLogConfig(logConfig + appendToConfig);
					return logConfig;

				}
			}, progress.newChild(WORK_ADJUST_LOG_CONFIG));
		} catch (CoreException e) {
			if (e.getCause() instanceof BAD_PARAM) {
				Status status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.CreateEventChannelLogger_3, e);
				return rollback(progress, status);
			}
			Status status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.CreateEventChannelLogger_4, e);
			return rollback(progress, status);
		} catch (InterruptedException e) {
			return rollback(progress, null);
		}

		// Refresh event channel list
		domMgr.fetchEventChannels(progress.newChild(WORK_FETCH_EVENT_CHANNELS), RefreshDepth.NONE);
		if (progress.isCanceled()) {
			return rollback(progress, null);
		}

		// Locate event channel by name
		try {
			eventChannel = ScaModelCommandWithResult.runExclusive(domMgr, new RunnableWithResult.Impl<ScaEventChannel>() {
				@Override
				public void run() {
					for (ScaEventChannel eventChannelCandidate : domMgr.getEventChannels()) {
						if (eventChannelName.equals(eventChannelCandidate.getName())) {
							setResult(eventChannelCandidate);
							return;
						}
					}
				}
			});
		} catch (InterruptedException e1) {
			IStatus status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.CreateEventChannelLogger_5);
			return rollback(progress, status);
		}
		if (eventChannel == null) {
			String msg = Messages.bind(Messages.CreateEventChannelLogger_6, eventChannelName);
			IStatus status = new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, msg);
			return rollback(progress, status);
		}

		return Status.OK_STATUS;
	}

	private String createEventChannelName() {
		try {
			String hostname = InetAddress.getLocalHost().getHostName().replace('.', '_');
			return "IDE_" + hostname + "_" + System.currentTimeMillis(); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (UnknownHostException e) {
			return "IDE_" + System.currentTimeMillis(); //$NON-NLS-1$
		}
	}

	/**
	 * Performs rollback of the log configuration changes, and finalizes work on the monitor.
	 * @param monitor The progress monitor from {@link #run(IProgressMonitor)}
	 * @param errorStatus The error causing a rollback, or null if rollback is due to
	 * {@link IProgressMonitor#isCanceled()}
	 * @return An appropriate return status for {@link #run(IProgressMonitor)}
	 */
	private IStatus rollback(IProgressMonitor monitor, IStatus errorStatus) {
		// If we were cancelled, clear it so we can try rollback, but possibly be cancelled again
		boolean cancelled = monitor.isCanceled();
		if (cancelled) {
			monitor.setCanceled(false);
		}
		monitor.setTaskName(Messages.CreateEventChannelLogger_10);

		List<IStatus> statuses = new ArrayList<IStatus>();
		if (errorStatus != null) {
			statuses.add(errorStatus);
		}

		// Attempt rollback of log config
		try {
			if (oldLogConfig != null) {
				CorbaUtils.invoke(new Callable<Object>() {
					@Override
					public String call() throws Exception {
						corbaObject.setLogConfig(oldLogConfig);
						return null;
					}
				}, monitor);
			}
		} catch (CoreException e) {
			statuses.add(new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.CreateEventChannelLogger_11, e));
		} catch (InterruptedException e) {
			statuses.add(new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.CreateEventChannelLogger_12));
		}

		// Release the CORBA object
		if (corbaObject != null) {
			CorbaUtils.release(corbaObject);
		}

		// Dispose the session
		if (session != null) {
			session.dispose();
		}

		// Restore cancellation status if we cleared it
		if (cancelled) {
			monitor.setCanceled(true);
		}

		monitor.done();
		switch (statuses.size()) {
		case 0:
			return Status.CANCEL_STATUS;
		case 1:
			return statuses.get(0);
		default:
			MultiStatus multiStatus = new MultiStatus(LoggingUiPlugin.PLUGIN_ID, 0, Messages.CreateEventChannelLogger_13, null);
			for (IStatus status : statuses) {
				multiStatus.add(status);
			}
			return multiStatus;
		}
	}
}
