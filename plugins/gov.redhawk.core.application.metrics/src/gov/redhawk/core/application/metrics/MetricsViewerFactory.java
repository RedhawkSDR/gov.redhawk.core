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

import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.nebula.widgets.xviewer.core.model.SortDataType;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerAlign;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;

class MetricsViewerFactory extends XViewerFactory {

	private static final String NAMESPACE = "gov.redhawk.core.application.metrics.MetricsViewerFactory";

	static final String ID_ID = "gov.redhawk.core.application.metrics.MetricsViewerFactory.id";
	static final String ID_CORES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.cores";
	static final String ID_MEMORY = "gov.redhawk.core.application.metrics.MetricsViewerFactory.memory";
	static final String ID_VALID = "gov.redhawk.core.application.metrics.MetricsViewerFactory.valid";
	static final String ID_SHARED = "gov.redhawk.core.application.metrics.MetricsViewerFactory.shared";
	static final String ID_PROCESSES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.processes";
	static final String ID_THREADS = "gov.redhawk.core.application.metrics.MetricsViewerFactory.threads";
	static final String ID_FILES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.files";
	static final String ID_COMPONENT_HOST = "gov.redhawk.core.application.metrics.MetricsViewerFactory.componenthost";

	private static final XViewerColumn COL_ID = new XViewerColumn(ID_ID, "ID", 250, XViewerAlign.Left, true, SortDataType.String, false, "ID");
	private static final XViewerColumn COL_CORES = new XViewerColumn(ID_CORES, "Cores", 80, XViewerAlign.Left, true, SortDataType.Float, false, "CPU Cores");
	private static final XViewerColumn COL_MEMORY = new XViewerColumn(ID_MEMORY, "Memory", 80, XViewerAlign.Left, true, SortDataType.Float, false,
		"Memory (MBs)");
	private static final XViewerColumn COL_VALID = new XViewerColumn(ID_VALID, "Valid", 50, XViewerAlign.Left, false, SortDataType.Boolean, false,
		"Valid metric");
	private static final XViewerColumn COL_SHARED = new XViewerColumn(ID_SHARED, "Shared", 50, XViewerAlign.Left, true, SortDataType.Boolean, false,
		"Shared process space");
	private static final XViewerColumn COL_PROCESSES = new XViewerColumn(ID_PROCESSES, "Processes", 80, XViewerAlign.Left, true, SortDataType.Long, false,
		"Process count");
	private static final XViewerColumn COL_THREADS = new XViewerColumn(ID_THREADS, "Threads", 80, XViewerAlign.Left, true, SortDataType.Long, false,
		"Thread count");
	private static final XViewerColumn COL_FILES = new XViewerColumn(ID_FILES, "Files", 80, XViewerAlign.Left, true, SortDataType.Long, false,
		"Open file descriptors");
	private static final XViewerColumn COL_COMPONENT_HOST = new XViewerColumn(ID_COMPONENT_HOST, "Component Host", 250, XViewerAlign.Left, false,
		SortDataType.String, false, "Component Host");

	public MetricsViewerFactory() {
		super(NAMESPACE);
		registerColumns(COL_ID, COL_CORES, COL_MEMORY, COL_PROCESSES, COL_THREADS, COL_FILES, COL_SHARED, COL_VALID, COL_COMPONENT_HOST);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public boolean isFilterUiAvailable() {
		return false;
	}

	@Override
	public boolean isLoadedStatusLabelAvailable() {
		return false;
	}

	@Override
	public boolean isSearchUiAvailable() {
		return false;
	}
}
