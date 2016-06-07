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

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;
import org.omg.CORBA.TRANSIENT;

import CF.LogConfigurationOperations;
import gov.redhawk.logging.ui.LoggingUiPlugin;
import gov.redhawk.logging.ui.config.Log4JConfigGenerator;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.util.CorbaUtils;

public class DestroyEventChannelLoggerJob extends Job {

	private OrbSession session;
	private LogConfigurationOperations resource;
	private String eventChannelName;

	protected DestroyEventChannelLoggerJob(OrbSession session, LogConfigurationOperations resource, String eventChannelName) {
		super(Messages.DestroyEventChannelLoggerJob_0);
		this.session = session;
		this.resource = resource;
		this.eventChannelName = eventChannelName;
	}

	@Override
	public boolean belongsTo(Object family) {
		// Make jobs of this class part of a family so they can be found from the job manager
		return family.equals(EventChannelCleanupFamily.class);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		final int WORK_ADJUST_LOG_CONFIG = 1;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_ADJUST_LOG_CONFIG);

		// Update the logging config
		try {
			CorbaUtils.invoke(new Callable<Object>() {
				@Override
				public String call() throws Exception {
					String logConfig = resource.getLogConfig();
					logConfig = purgeEventChannel(logConfig);
					resource.setLogConfig(logConfig);
					return null;
				}
			}, progress.newChild(WORK_ADJUST_LOG_CONFIG));
		} catch (CoreException e) {
			// Ignore CORBA transients that occur when shutting down. Normally these will be because the domain was
			// running in the IDE and shutdown before we could remove the logging
			if (e.getCause() instanceof TRANSIENT && PlatformUI.getWorkbench().isClosing()) {
				return Status.OK_STATUS;
			}
			return new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.DestroyEventChannelLoggerJob_1, e);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} finally {
			this.session.dispose();
		}

		return Status.OK_STATUS;
	}

	private String purgeEventChannel(String logConfig) throws CoreException {
		// Look for the start/end markers in the configuration
		String startKey = Log4JConfigGenerator.createStartTag(eventChannelName);
		String endKey = Log4JConfigGenerator.createEndTag(eventChannelName);
		int startIndex = logConfig.indexOf(startKey);
		int endIndex = logConfig.indexOf(endKey, startIndex);
		if (startIndex == -1 || endIndex == -1) {
			throw new CoreException(
				new Status(IStatus.ERROR, LoggingUiPlugin.PLUGIN_ID, Messages.DestroyEventChannelLoggerJob_2));
		}

		// Excise the configuration information now that we've located it
		StringBuilder sb = new StringBuilder();
		if (startIndex > 0) {
			sb.append(logConfig, 0, startIndex);
		}
		if (logConfig.length() > (endIndex + endKey.length())) {
			sb.append(logConfig, endIndex + endKey.length(), logConfig.length());
		}

		// Trim whitespace, but leave a final newline
		return sb.toString().trim() + '\n';
	}

}
