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
package gov.redhawk.frontend.jobs;

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import BULKIO.PrecisionUTCTime;
import FRONTEND.BadParameterException;
import FRONTEND.FrontendException;
import FRONTEND.NotSupportedException;
import FRONTEND.ScanningTuner;
import FRONTEND.ScanningTunerHelper;
import FRONTEND.ScanningTunerPackage.ScanStrategy;
import gov.redhawk.frontend.FEIErrorFormatter;
import gov.redhawk.frontend.FrontendPlugin;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.util.CorbaUtils2;

/**
 * Calls setScanStrategy(...) and setScanStartTime(...) to begin an FEI tuner scan.
 * @since 2.0
 */
public class ScanJob extends Job {

	private ScaDevice< ? > device;
	private String allocationID;
	private ScanStrategy scanStrategy;
	private PrecisionUTCTime startTime;

	public ScanJob(ScaDevice< ? > device, String allocationID, ScanStrategy scanStrategy, PrecisionUTCTime startTime) {
		super(Messages.ScanJob_JobName);
		this.device = device;
		this.allocationID = allocationID;
		this.scanStrategy = scanStrategy;
		this.startTime = startTime;
	}

	@Override
	public IStatus run(IProgressMonitor monitor) {
		final int WORK_NARROW = 1, WORK_CORBA_CALL = 4;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_NARROW + WORK_CORBA_CALL);

		for (ScaPort< ? , ? > port : device.getPorts()) {
			// Ensure this is the right port type and narrow it
			if (!(port instanceof ScaProvidesPort)) {
				continue;
			}
			ScaProvidesPort providesPort = (ScaProvidesPort) port;
			if (!providesPort._is_a(ScanningTunerHelper.id())) {
				continue;
			}
			ScanningTuner scanningTuner = ScanningTunerHelper.unchecked_narrow(providesPort.getObj());
			progress.worked(WORK_NARROW);

			// Setup and schedule the scan
			SubMonitor childProgress = progress.split(WORK_CORBA_CALL).setWorkRemaining(2);
			final String RESOURCE_NAME = Messages.bind(Messages.ScanJob_ResourceName, device.getLabel(), allocationID);
			try {
				return CorbaUtils2.invoke(() -> {
					try {
						scanningTuner.setScanStrategy(allocationID, scanStrategy);
						childProgress.worked(1);
						if (progress.isCanceled()) {
							return Status.CANCEL_STATUS;
						}

						scanningTuner.setScanStartTime(allocationID, startTime);
						childProgress.worked(1);
						return Status.OK_STATUS;
					} catch (FrontendException e) {
						return new Status(IStatus.ERROR, FrontendPlugin.PLUGIN_ID, FEIErrorFormatter.format(e, RESOURCE_NAME));
					} catch (BadParameterException e) {
						return new Status(IStatus.ERROR, FrontendPlugin.PLUGIN_ID, FEIErrorFormatter.format(e, RESOURCE_NAME));
					} catch (NotSupportedException e) {
						return new Status(IStatus.ERROR, FrontendPlugin.PLUGIN_ID, FEIErrorFormatter.format(e, RESOURCE_NAME));
					}
				}, childProgress);
			} catch (ExecutionException e) {
				return new Status(IStatus.ERROR, FrontendPlugin.PLUGIN_ID, Messages.ScanJob_Error_GeneralFailure, e.getCause());
			}
		}
		return new Status(IStatus.ERROR, FrontendPlugin.PLUGIN_ID, Messages.bind(Messages.ScanJob_Error_NoScannerPort, device.getLabel()));
	}

}
