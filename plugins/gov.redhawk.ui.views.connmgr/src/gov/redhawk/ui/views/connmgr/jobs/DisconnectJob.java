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
package gov.redhawk.ui.views.connmgr.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.SystemException;

import CF.PortPackage.InvalidPort;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.ui.views.connmgr.ConnMgrPlugin;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;
import mil.jpeojtrs.sca.util.CFErrorFormatter;

public class DisconnectJob extends Job {

	private ScaConnectionManager connMgr;
	private List<String> connectionRecordIDs;

	public DisconnectJob(ScaConnectionManager connMgr, List<String> connectionRecordIDs) {
		super("Disconnect");
		this.connMgr = connMgr;
		this.connectionRecordIDs = connectionRecordIDs;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, connectionRecordIDs.size() + 1);

		MultiStatus status = new MultiStatus(ConnMgrPlugin.ID, 0, "Disconnect errors", null);
		for (String connectionRecordID : connectionRecordIDs) {
			try {
				connMgr.disconnect(connectionRecordID);
				progress.worked(1);
			} catch (SystemException e) {
				String msg = String.format("Unable to disconnect connection record ID %s", connectionRecordID);
				status.add(new Status(IStatus.ERROR, ConnMgrPlugin.ID, msg, e));
			} catch (InvalidPort e) {
				String msg = CFErrorFormatter.format(e, String.format("connection record ID %s", connectionRecordID));
				return new Status(IStatus.ERROR, ConnMgrPlugin.ID, msg, e);
			}

			if (progress.isCanceled()) {
				return Status.CANCEL_STATUS;
			}
		}

		try {
			connMgr.refresh(progress.split(1), RefreshDepth.CHILDREN);
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}

		progress.done();
		return (status.isOK()) ? Status.OK_STATUS : status;
	}

}
