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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.omg.CORBA.SystemException;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.ui.views.connmgr.ConnMgrPlugin;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;

public class FetchConnectionManagerJob extends Job {

	private ScaDomainManager domMgr;
	private ScaConnectionManager connMgr;

	public FetchConnectionManagerJob(ScaDomainManager domMgr, ScaConnectionManager connMgr) {
		super("Get connection manager");
		this.domMgr = domMgr;
		this.connMgr = connMgr;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 1);

		CF.ConnectionManager connMgrObj;
		try {
			connMgrObj = domMgr.connectionMgr();
			progress.worked(1);
		} catch (SystemException e) {
			return new Status(IStatus.ERROR, ConnMgrPlugin.ID, "Unable to get connection manager", e);
		}

		connMgr.setCorbaObj(connMgrObj);
		return Status.OK_STATUS;
	}

}
