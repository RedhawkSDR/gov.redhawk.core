/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.views.internal.monitor.ports;

import gov.redhawk.ui.views.monitor.model.ports.Monitor;
import gov.redhawk.ui.views.monitor.model.ports.MonitorRegistry;
import gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.ui.views.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.ui.views.monitor.ports.Column;
import gov.redhawk.ui.views.monitor.ports.PortMonitorView;
import gov.redhawk.ui.views.monitor.ports.StatisticsColumns;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.preferences.ViewSettingsDialog;

import BULKIO.PortStatistics;
import CF.DataType;

/**
 * 
 */
public class PortMonitorViewConfigDialog extends ViewSettingsDialog {

	private final PortMonitorView view;

	private CheckboxTableViewer viewer;

	private final MonitorRegistry data;

	private Text refreshText;

	private final Map<Column, Boolean> columnMap = new HashMap<Column, Boolean>();

	public PortMonitorViewConfigDialog(final Shell parentShell, final PortMonitorView view, final MonitorRegistry data) {
		super(parentShell);
		this.view = view;
		this.data = data;
		initializeColumnMap();
	}

	private void initializeColumnMap() {
		this.columnMap.clear();
		for (final Column c : StatisticsColumns.DEFAULT_COLUMNS) {
			this.columnMap.put(c, this.view.hasColumn(c));
		}
		for (final Monitor m : this.data.getMonitors()) {
			if (m instanceof PortSupplierMonitor) {
				final PortSupplierMonitor psm = (PortSupplierMonitor) m;
				for (final PortMonitor pm : psm.getMonitors()) {
					addMonitor(pm);
				}
			} else if (m instanceof PortMonitor) {
				addMonitor((PortMonitor) m);
			}
		}

	}

	private void addMonitor(final PortMonitor monitor) {
		addPortStatisticsProvider(monitor);
		for (final PortConnectionMonitor connection : monitor.getConnections()) {
			addPortStatisticsProvider(connection);
		}
	}

	private void addPortStatisticsProvider(final PortStatisticsProvider provider) {
		if (provider != null) {
			final PortStatistics stats = provider.getData();
			if (stats != null) {
				final DataType[] keywords = stats.keywords;
				if (keywords != null) {
					for (final DataType type : keywords) {
						addDataType(type);
					}
				}
			}
		}
	}

	private void addDataType(final DataType type) {
		if (!this.columnMap.containsKey(type.id)) {
			final DataTypeColumn dataTypeColumn = new DataTypeColumn(type);
			this.columnMap.put(dataTypeColumn, this.view.hasColumn(dataTypeColumn));
		}
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);
		//add controls to composite as necessary
		createRefreshGroup(composite);
		createColumnGroup(composite);

		getShell().setText("Port Monitor Configuration");
		return composite;
	}

	private void createRefreshGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.None);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setText("Preferences");
		group.setLayout(new GridLayout(2, false));

		final Label label = new Label(group, SWT.None);
		label.setText("Refresh Interval (sec):");

		this.refreshText = new Text(group, SWT.BORDER);
		this.refreshText.setText(String.valueOf(this.view.getRefreshDelta() / 1000));
		this.refreshText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		this.refreshText.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(final VerifyEvent e) {
				e.doit = Character.isDigit(e.character);
			}
		});
	}

	private void createColumnGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.None);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("Column Configuration");
		group.setLayout(new GridLayout(1, false));
		this.viewer = CheckboxTableViewer.newCheckList(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
		final Table table = this.viewer.getTable();
		final GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.minimumHeight = 200;
		table.setLayoutData(gd_table);
		this.viewer.getTable().setHeaderVisible(false);
		this.viewer.setContentProvider(new ArrayContentProvider());
		this.viewer.setLabelProvider(new LabelProvider());
		this.viewer.setSorter(new ViewerSorter() {
			@Override
			public int compare(final Viewer viewer, final Object e1, final Object e2) {
				final Column c1 = (Column) e1;
				final Column c2 = (Column) e2;
				return c1.getName().compareToIgnoreCase(c2.getName());
			}
		});
		this.viewer.setCheckStateProvider(new ICheckStateProvider() {

			@Override
			public boolean isGrayed(final Object element) {
				return false;
			}

			@Override
			public boolean isChecked(final Object element) {
				final Column c = (Column) element;
				return PortMonitorViewConfigDialog.this.columnMap.get(c);
			}
		});
		this.viewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				PortMonitorViewConfigDialog.this.columnMap.put(((Column) event.getElement()), event.getChecked());
			}
		});
		this.viewer.setInput(this.columnMap.keySet());
	}

	@Override
	protected void okPressed() {
		this.view.setRefreshDelta(Long.valueOf(this.refreshText.getText()) * 1000L);
		for (final Entry<Column, Boolean> entry : this.columnMap.entrySet()) {
			if (entry.getValue()) {
				this.view.addColumn(entry.getKey());
			} else {
				this.view.removeColumn(entry.getKey());
			}
		}
		super.okPressed();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		this.refreshText.setText("10");
		initializeColumnMap();
		for (final Column c : StatisticsColumns.DEFAULT_COLUMNS) {
			this.columnMap.put(c, true);
		}
		this.viewer.refresh();
	}
}
