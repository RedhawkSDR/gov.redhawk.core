/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.internal.ui.jobs;

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.util.EcoreUtil;

import CF.EventChannelManager;
import CF.EventChannelManagerPackage.ChannelDoesNotExist;
import CF.EventChannelManagerPackage.OperationFailed;
import CF.EventChannelManagerPackage.OperationNotAllowed;
import CF.EventChannelManagerPackage.RegistrationsExists;
import CF.EventChannelManagerPackage.ServiceUnavailable;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.util.CorbaUtils2;

public class ReleaseEventChannelJob extends Job {

	private ScaDomainManager domMgr;
	private ScaEventChannel eventChannel;

	public ReleaseEventChannelJob(ScaDomainManager domMgr, ScaEventChannel eventChannel) {
		super(Messages.bind(Messages.ReleaseEventChannelJob_JobName, eventChannel.getName()));
		this.domMgr = domMgr;
		this.eventChannel = eventChannel;
	}

	@Override
	public boolean shouldRun() {
		return !eventChannel.isDisposed();
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 2);
		String name = eventChannel.getName();

		EventChannelManager ecm;
		try {
			ecm = CorbaUtils2.invoke(() -> {
				return domMgr.eventChannelMgr();
			}, progress.split(1));
		} catch (ExecutionException e) {
			String msg = Messages.bind(Messages.ReleaseEventChannelJob_CannotGetECM, name);
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e.getCause());
		}

		try {
			IStatus status = CorbaUtils2.invoke(() -> {
				try {
					ecm.release(name);
					return Status.OK_STATUS;
				} catch (ChannelDoesNotExist e) {
					String msg = Messages.bind(Messages.ReleaseEventChannelJob_ChannelDoesNotExist, name);
					return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e);
				} catch (RegistrationsExists e) {
					String msg = Messages.bind(Messages.ReleaseEventChannelJob_RegistrationExists, name);
					return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e);
				} catch (OperationNotAllowed | OperationFailed | ServiceUnavailable e) {
					String msg = Messages.bind(Messages.ReleaseEventChannelJob_OtherECMError, name);
					return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e);
				}
			}, progress.split(1));
			if (!status.isOK()) {
				return status;
			}
		} catch (ExecutionException e) {
			String msg = Messages.bind(Messages.ReleaseEventChannelJob_ErrorStatusMessage, name);
			return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, e.getCause());
		}

		// Remove the ScaEventChannel from the model
		ScaModelCommand.execute(eventChannel, () -> {
			EcoreUtil.delete(eventChannel);
		});

		return Status.OK_STATUS;
	}

}
