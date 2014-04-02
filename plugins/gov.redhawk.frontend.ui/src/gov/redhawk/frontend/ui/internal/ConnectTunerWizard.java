/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.TunerStatusUtil;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.ui.ConnectPortWizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;

import CF.DataType;

/**
 * 
 */
public class ConnectTunerWizard extends ConnectPortWizard {
	private TunerStatus tuner;

	public void setTuner(TunerStatus tuner) {
		Assert.isNotNull(tuner);
		this.tuner = tuner;
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		setSourceInput(device);

		List<ScaPort< ? , ? >> devicePorts = device.getPorts();
		final List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
		for (ScaPort< ? , ? > port : devicePorts) {
			if (port instanceof ScaUsesPort) {
				usesPorts.add((ScaUsesPort) port);
			}
		}
		setSource(usesPorts.get(0));
	}

	public TunerStatus getTuner() {
		return tuner;
	}

	@Override
	protected void performFinish(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
		DataType[] props = TunerStatusUtil.createAllocationProperties(getConnectionID(), tuner);
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Connecting tuner " + tuner.getTunerID(), IProgressMonitor.UNKNOWN);
		try {
			IStatus status = TunerStatusUtil.allocateTuner(subMonitor.newChild(1), tuner, props);
			if (status.getSeverity() == IStatus.CANCEL) {
				throw new InterruptedException();
			} else if (!status.isOK()) {
				throw new InvocationTargetException(new CoreException(status).fillInStackTrace());
			}
			try {
				super.performFinish(subMonitor.newChild(1));
			} catch (InterruptedException e) {
				TunerStatusUtil.createDeallocationJob(tuner, props).schedule();
				throw e;
			} catch (InvocationTargetException e) {
				TunerStatusUtil.createDeallocationJob(tuner, props).schedule();
				throw e;
			}
		} finally {
			subMonitor.done();
		}
	}
}
