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

	private static final String NAMESPACE = "gov.redhawk.core.application.metrics.MetricsViewerFactory"; //$NON-NLS-1$

	static final String ID_ID = "gov.redhawk.core.application.metrics.MetricsViewerFactory.id"; //$NON-NLS-1$
	static final String ID_CORES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.cores"; //$NON-NLS-1$
	static final String ID_MEMORY = "gov.redhawk.core.application.metrics.MetricsViewerFactory.memory"; //$NON-NLS-1$
	static final String ID_VALID = "gov.redhawk.core.application.metrics.MetricsViewerFactory.valid"; //$NON-NLS-1$
	static final String ID_SHARED = "gov.redhawk.core.application.metrics.MetricsViewerFactory.shared"; //$NON-NLS-1$
	static final String ID_PROCESSES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.processes"; //$NON-NLS-1$
	static final String ID_THREADS = "gov.redhawk.core.application.metrics.MetricsViewerFactory.threads"; //$NON-NLS-1$
	static final String ID_FILES = "gov.redhawk.core.application.metrics.MetricsViewerFactory.files"; //$NON-NLS-1$
	static final String ID_COMPONENT_HOST = "gov.redhawk.core.application.metrics.MetricsViewerFactory.componenthost"; //$NON-NLS-1$

	private static final XViewerColumn COL_ID = new XViewerColumn(ID_ID, Messages.MetricsViewerFactory_ColumnTitle_ID, 250, XViewerAlign.Left, true,
		SortDataType.String, false, Messages.MetricsViewerFactory_ColumnDesc_ID);
	private static final XViewerColumn COL_CORES = new XViewerColumn(ID_CORES, Messages.MetricsViewerFactory_ColumnTitle_Cores, 80, XViewerAlign.Left, true,
		SortDataType.Float, false, Messages.MetricsViewerFactory_ColumnDesc_Cores);
	private static final XViewerColumn COL_MEMORY = new XViewerColumn(ID_MEMORY, Messages.MetricsViewerFactory_ColumnTitle_Memory, 80, XViewerAlign.Left, true,
		SortDataType.Float, false, Messages.MetricsViewerFactory_ColumnDesc_Memory);
	private static final XViewerColumn COL_VALID = new XViewerColumn(ID_VALID, Messages.MetricsViewerFactory_ColumnTitle_Valid, 50, XViewerAlign.Left, false,
		SortDataType.Boolean, false, Messages.MetricsViewerFactory_ColumnDesc_Valid);
	private static final XViewerColumn COL_SHARED = new XViewerColumn(ID_SHARED, Messages.MetricsViewerFactory_ColumnTitle_Shared, 50, XViewerAlign.Left, true,
		SortDataType.Boolean, false, Messages.MetricsViewerFactory_ColumnDesc_Shared);
	private static final XViewerColumn COL_PROCESSES = new XViewerColumn(ID_PROCESSES, Messages.MetricsViewerFactory_ColumnTitle_Processes, 80,
		XViewerAlign.Left, true, SortDataType.Long, false, Messages.MetricsViewerFactory_ColumnDesc_Processes);
	private static final XViewerColumn COL_THREADS = new XViewerColumn(ID_THREADS, Messages.MetricsViewerFactory_ColumnTitle_Threads, 80, XViewerAlign.Left,
		true, SortDataType.Long, false, Messages.MetricsViewerFactory_ColumnDesc_Threads);
	private static final XViewerColumn COL_FILES = new XViewerColumn(ID_FILES, Messages.MetricsViewerFactory_ColumnTitle_Files, 80, XViewerAlign.Left, true,
		SortDataType.Long, false, Messages.MetricsViewerFactory_ColumnDesc_Files);
	private static final XViewerColumn COL_COMPONENT_HOST = new XViewerColumn(ID_COMPONENT_HOST, Messages.MetricsViewerFactory_ColumnTitle_ComponentHost, 250,
		XViewerAlign.Left, false, SortDataType.String, false, Messages.MetricsViewerFactory_ColumnDesc_ComponentHost);

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
