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
package gov.redhawk.ui.views.event.handlers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.ui.views.event.jobs.ReleaseEventChannelJob;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class ReleaseEventChannelHandler extends AbstractHandler {

	private static final String IDM_CHANNEL = "IDM_Channel"; //$NON-NLS-1$
	private static final String ODM_CHANNEL = "ODM_Channel"; //$NON-NLS-1$

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShellChecked(event);
		IStructuredSelection ss = (IStructuredSelection) HandlerUtil.getActiveMenuSelectionChecked(event);

		List<Job> jobs = new ArrayList<>();
		for (Object obj : ss.toArray()) {
			ScaEventChannel eventChannel = (ScaEventChannel) obj;

			ScaDomainManager domMgr = ScaEcoreUtils.getEContainerOfType(eventChannel, ScaDomainManager.class);
			if (domMgr == null) {
				String msg = Messages.bind(Messages.ReleaseEventChannelHandler_NoDomMgrError_Description, eventChannel.getName());
				MessageDialog.openError(shell, Messages.ReleaseEventChannelHandler_NoDomMgrError_Title, msg);
				return null;
			}

			if (IDM_CHANNEL.equals(eventChannel.getName()) || ODM_CHANNEL.equals(eventChannel.getName())) {
				MessageDialog.openError(shell, Messages.ReleaseEventChannelHandler_ReservedEventChannel_Title,
					Messages.ReleaseEventChannelHandler_ReservedEventChannel_Description);
				return null;
			}

			Job job = new ReleaseEventChannelJob(domMgr, eventChannel);
			job.setUser(true);
			jobs.add(job);
		}

		for (Job job : jobs) {
			job.schedule();
		}
		return null;
	}

}
