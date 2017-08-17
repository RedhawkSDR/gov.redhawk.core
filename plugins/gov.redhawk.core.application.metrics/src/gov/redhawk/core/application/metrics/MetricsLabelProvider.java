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

import java.text.NumberFormat;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.XViewerLabelProvider;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;

import mil.jpeojtrs.sca.util.metrics.Metric;

class MetricsLabelProvider extends XViewerLabelProvider {

	private NumberFormat decimalFormat;

	public MetricsLabelProvider(XViewer viewer) {
		super(viewer);
		decimalFormat = NumberFormat.getNumberInstance();
		decimalFormat.setMinimumFractionDigits(1);
		decimalFormat.setMaximumFractionDigits(1);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
	}

	@Override
	public Image getColumnImage(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		return null;
	}

	@Override
	public String getColumnText(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		Metric metric = (Metric) element;
		switch (xCol.getId()) {
		case MetricsViewerFactory.ID_ID:
			return metric.getId();
		case MetricsViewerFactory.ID_CORES:
			return (metric.isSetCores()) ? decimalFormat.format(metric.getCores()) : "";
		case MetricsViewerFactory.ID_MEMORY:
			return (metric.isSetMemory()) ? decimalFormat.format(metric.getMemory()) : "";
		case MetricsViewerFactory.ID_VALID:
			return (metric.isSetValid()) ? Boolean.toString(metric.isValid()) : "";
		case MetricsViewerFactory.ID_SHARED:
			return (metric.isSetShared()) ? Boolean.toString(metric.isShared()) : "";
		case MetricsViewerFactory.ID_PROCESSES:
			return (metric.isSetProcesses()) ? Long.toString(metric.getProcesses()) : "";
		case MetricsViewerFactory.ID_THREADS:
			return (metric.isSetThreads()) ? Long.toString(metric.getThreads()) : "";
		case MetricsViewerFactory.ID_FILES:
			return (metric.isSetFiles()) ? Long.toString(metric.getFiles()) : "";
		case MetricsViewerFactory.ID_COMPONENT_HOST:
			return metric.getComponentHost();
		default:
			return "";
		}
	}

	public int getColumnGradientColor(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		switch (xCol.getId()) {
		case MetricsViewerFactory.ID_CORES:
			return SWT.COLOR_DARK_BLUE;
		case MetricsViewerFactory.ID_MEMORY:
			return SWT.COLOR_DARK_MAGENTA;
		default:
			return 0;
		}
	}

	@Override
	public int getColumnGradient(Object element, XViewerColumn xCol, int columnIndex) throws Exception {
		Metric metric = (Metric) element;
		if (Metric.APP_UTIL.equals(metric.getId())) {
			return 0;
		}
		switch (xCol.getId()) {
		case MetricsViewerFactory.ID_CORES:
			return coreGradient(metric, xCol);
		case MetricsViewerFactory.ID_MEMORY:
			return memoryGradient(metric, xCol);
		default:
			return 0;
		}
	}

	private int coreGradient(Metric element, XViewerColumn xCol) {
		Metric[] metrics = (Metric[]) ((XViewer) xCol.getXViewer()).getInput();
		for (Metric metric : metrics) {
			if (Metric.APP_UTIL.equals(metric.getId())) {
				if (metric.getCores() == 0) {
					return 0;
				}
				return (int) (element.getCores() / metric.getCores() * 100);
			}
		}
		return 0;
	}

	private int memoryGradient(Metric element, XViewerColumn xCol) {
		Metric[] metrics = (Metric[]) ((XViewer) xCol.getXViewer()).getInput();
		for (Metric metric : metrics) {
			if (Metric.APP_UTIL.equals(metric.getId())) {
				if (metric.getMemory() == 0) {
					return 0;
				}
				return (int) (element.getMemory() / metric.getMemory() * 100);
			}
		}
		return 0;
	}
}
