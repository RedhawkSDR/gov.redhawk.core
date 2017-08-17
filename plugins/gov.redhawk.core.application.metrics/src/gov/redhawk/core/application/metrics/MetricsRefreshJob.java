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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.widgets.Display;

import CF.DataType;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.metrics.Metric;

public class MetricsRefreshJob extends Job {

	private static final long RESCHEDULE_DELAY = 10000;

	private XViewer xviewer;
	private ScaWaveform waveform;

	public MetricsRefreshJob(XViewer xviewer, ScaWaveform waveform) {
		super("Metrics refresh");
		this.xviewer = xviewer;
		this.waveform = waveform;
		setUser(false);
		setSystem(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 10);

		// Gather metrics
		DataType[] props;
		try {
			props = CorbaUtils.invoke(() -> {
				return this.waveform.metrics(new String[0], new String[0]);
			}, progress.newChild(9));
		} catch (CoreException e) {
			schedule(RESCHEDULE_DELAY);
			return Status.OK_STATUS;
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		}
		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}

		// Demarshal & send to viewer
		Metric[] metrics = new Metric[props.length];
		for (int i = 0; i < props.length; i++) {
			metrics[i] = new Metric(props[i]);
		}
		Display display = xviewer.getTree().getDisplay();
		display.syncExec(() -> {
			if (display.isDisposed() || xviewer.getControl().isDisposed()) {
				return;
			}
			boolean requestLayout = false;
			if (xviewer.getInput() == null) {
				requestLayout = true;
			} else if (((Object[]) xviewer.getInput()).length < metrics.length) {
				requestLayout = true;
			}
			xviewer.setInput(metrics);
			if (requestLayout) {
				xviewer.getControl().requestLayout();
			}
		});
		if (progress.isCanceled()) {
			return Status.CANCEL_STATUS;
		}
		progress.worked(1);

		// Reschedule
		schedule(RESCHEDULE_DELAY);
		return Status.OK_STATUS;
	}

}
