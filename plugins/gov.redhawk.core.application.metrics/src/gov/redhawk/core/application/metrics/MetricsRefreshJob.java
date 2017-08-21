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
package gov.redhawk.core.application.metrics;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Display;
import org.omg.CORBA.BAD_PARAM;

import CF.DataType;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.metrics.Metric;

public class MetricsRefreshJob extends Job {

	private static final long RESCHEDULE_DELAY = 10000;

	private XViewer xviewer;
	private ScaWaveform waveform;

	public MetricsRefreshJob(XViewer xviewer, ScaWaveform waveform) {
		super(Messages.MetricsRefreshJob_JobName);
		this.xviewer = xviewer;
		this.waveform = waveform;
		setUser(false);
		setSystem(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 10);

		// Gather metrics
		boolean unsupported = false;
		List<Metric> metrics = new ArrayList<>();
		try {
			DataType[] props = CorbaUtils.invoke(() -> {
				return this.waveform.metrics(new String[0], new String[0]);
			}, progress.newChild(9));
			for (int i = 0; i < props.length; i++) {
				metrics.add(new Metric(props[i]));
			}
		} catch (CoreException e) {
			if (e.getCause() instanceof BAD_PARAM) {
				unsupported = true;
				Metric metric = new Metric();
				metric.setId(Messages.MetricsRefreshJob_NotSupported);
				metrics.add(metric);
			} else if (!progress.isCanceled()) {
				Metric metric = new Metric();
				metric.setId(Messages.MetricsRefreshJob_ErrorRetrieving);
				metrics.add(metric);
			} else {
				return Status.CANCEL_STATUS;
			}
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}

		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Send to viewer
		Display display;
		try {
			display = xviewer.getTree().getDisplay();
		} catch (SWTException e) {
			return Status.CANCEL_STATUS;
		}
		display.syncExec(() -> {
			if (display.isDisposed() || xviewer.getTree().isDisposed() || progress.isCanceled()) {
				return;
			}
			boolean requestLayout = false;
			if (xviewer.getInput() == null) {
				requestLayout = true;
			} else if (((Object[]) xviewer.getInput()).length < metrics.size()) {
				requestLayout = true;
			}
			xviewer.setInput(metrics.toArray(new Metric[metrics.size()]));
			if (requestLayout) {
				xviewer.getTree().requestLayout();
			}
		});
		progress.worked(1);

		if (unsupported) {
			return Status.OK_STATUS;
		}
		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Reschedule
		schedule(RESCHEDULE_DELAY);
		return Status.OK_STATUS;
	}

}
