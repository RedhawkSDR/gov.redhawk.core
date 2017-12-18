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

import org.eclipse.core.runtime.CoreException;
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
import mil.jpeojtrs.sca.util.CorbaUtils;

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
			ecm = CorbaUtils.invoke(() -> {
				return domMgr.eventChannelMgr();
			}, progress.split(1));
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			String msg = Messages.bind(Messages.ReleaseEventChannelJob_CannotGetECM, name);
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, msg, e);
		}

		try {
			CorbaUtils.invoke(() -> {
				String msg = null;
				Exception ex = null;
				try {
					ecm.release(name);
				} catch (ChannelDoesNotExist e) {
					msg = Messages.bind(Messages.ReleaseEventChannelJob_ChannelDoesNotExist, name);
					ex = e;
				} catch (RegistrationsExists e) {
					msg = Messages.bind(Messages.ReleaseEventChannelJob_RegistrationExists, name);
					ex = e;
				} catch (OperationNotAllowed | OperationFailed | ServiceUnavailable e) {
					msg = Messages.bind(Messages.ReleaseEventChannelJob_OtherECMError, e.getClass().getName());
					ex = e;
				}
				if (msg != null) {
					IStatus status = new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, msg, ex);
					throw new CoreException(status);
				}

				return null;
			}, progress.split(1));
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			String msg = Messages.bind(Messages.ReleaseEventChannelJob_ErrorStatusMessage, name);
			return new Status(e.getStatus().getSeverity(), ScaUiPlugin.PLUGIN_ID, msg, e);
		}

		// Remove the ScaEventChannel from the model
		ScaModelCommand.execute(eventChannel, () -> {
			EcoreUtil.delete(eventChannel);
		});

		return Status.OK_STATUS;
	}

}
