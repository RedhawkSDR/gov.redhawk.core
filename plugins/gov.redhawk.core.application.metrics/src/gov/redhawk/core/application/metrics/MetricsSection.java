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

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import gov.redhawk.model.sca.ScaWaveform;

public class MetricsSection extends AbstractPropertySection {

	private XViewer xviewer;
	private ScaWaveform waveform;
	private Job refreshJob;

	public MetricsSection() {
	}

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		xviewer = new XViewer(parent, SWT.NONE, new MetricsViewerFactory());
		xviewer.setContentProvider(new MetricsContentProvider());
		xviewer.setLabelProvider(new MetricsLabelProvider(xviewer));
		new MetricsGradient(xviewer);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		// Unwrap the selection; if it isn't an ScaWaveform, we can't use it
		if (!(selection instanceof IStructuredSelection)) {
			cancelRefreshJob();
			xviewer.setInput(null);
			return;
		}
		Object obj = ((IStructuredSelection) selection).getFirstElement();
		if (!(obj instanceof ScaWaveform)) {
			cancelRefreshJob();
			xviewer.setInput(null);
			return;
		}

		// Ignore double-set of same input object
		if (this.waveform == obj) {
			return;
		}

		this.waveform = (ScaWaveform) obj;
		cancelRefreshJob();
	}

	@Override
	public void refresh() {
		if (refreshJob == null) {
			refreshJob = new MetricsRefreshJob(xviewer, waveform);
			refreshJob.schedule();
		}
	}

	@Override
	public void aboutToBeHidden() {
		cancelRefreshJob();
	}

	@Override
	public void dispose() {
		if (refreshJob != null) {
			refreshJob.cancel();
		}
		if (xviewer != null) {
			xviewer.dispose();
		}
		super.dispose();
	}

	private void cancelRefreshJob() {
		if (refreshJob != null) {
			refreshJob.cancel();
			refreshJob = null;
		}
	}
}
