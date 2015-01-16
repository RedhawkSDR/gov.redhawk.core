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

import gov.redhawk.sca.util.PropertyChangeSupport;
import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.ui.views.monitor.ports.Column;
import gov.redhawk.ui.views.monitor.ports.PortMonitorView;
import gov.redhawk.ui.views.monitor.ports.StatisticsColumns;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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

	private static class State {
		private long refresh = 10;
		private Set<Column> checked = new HashSet<Column>();
		private List<Column> input = new ArrayList<Column>();
		private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

		public void addPropertyChangeListener(java.beans.PropertyChangeListener listener) {
			pcs.addPropertyChangeListener(listener);
		}

		public void removePropertyChangeListener(java.beans.PropertyChangeListener listener) {
			pcs.removePropertyChangeListener(listener);
		}

		public long getRefresh() {
			return refresh;
		}

		public void setRefresh(long refresh) {
			long oldValue = this.refresh;
			this.refresh = refresh;
			pcs.firePropertyChange("refresh", oldValue, refresh);
		}

		public Set<Column> getChecked() {
			return checked;
		}

		public void setChecked(Set<Column> checked) {
			Set<Column> oldValue = this.checked;
			this.checked = checked;
			pcs.firePropertyChange("checked", oldValue, checked);
		}

		public List<Column> getInput() {
			return input;
		}

		public void setInput(List<Column> input) {
			List<Column> oldValue = input;
			this.input = input;
			pcs.firePropertyChange("input", oldValue, input);
		}

	}

	private final State state = new State();

	private final PortMonitorView view;

	private final MonitorRegistry data;

	private DataBindingContext context = new DataBindingContext();

	private Text refreshText;

	public PortMonitorViewConfigDialog(final Shell parentShell, final PortMonitorView view, final MonitorRegistry data) {
		super(parentShell);
		this.view = view;
		this.data = data;
		state.refresh = view.getRefreshDelta() / 1000;
		initializeColumnMap();
	}

	private void initializeColumnMap() {
		state.checked.clear();
		state.input.clear();
		for (final Column c : StatisticsColumns.DEFAULT_COLUMNS) {
			if (view.hasColumn(c)) {
				state.checked.add(c);
			}
			state.input.add(c);
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
		if (!state.input.contains(type.id)) {
			final DataTypeColumn dataTypeColumn = new DataTypeColumn(type);
			if (this.view.hasColumn(dataTypeColumn)) {
				state.checked.add(dataTypeColumn);
			}
			state.input.add(dataTypeColumn);
		}
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite composite = (Composite) super.createDialogArea(parent);
		// add controls to composite as necessary
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

		refreshText = new Text(group, SWT.BORDER);
		refreshText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Binding binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(refreshText), BeansObservables.observeValue(state, "refresh"));
		binding.getValidationStatus().addValueChangeListener(new IValueChangeListener() {
			
			@Override
			public void handleValueChange(ValueChangeEvent event) {
				IStatus status = (IStatus) event.getObservableValue().getValue();
				Button button = getButton(IDialogConstants.OK_ID);
				if (button != null) {
					button.setEnabled(status.isOK());
				}
			}
		});
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);
	}

	private void createColumnGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.None);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("Column Configuration");
		group.setLayout(new GridLayout(1, false));
		CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
		final Table table = viewer.getTable();
		final GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.minimumHeight = 200;
		table.setLayoutData(gd_table);
		viewer.getTable().setHeaderVisible(false);
		viewer.setSorter(new ViewerSorter() {
			@Override
			public int compare(final Viewer viewer, final Object e1, final Object e2) {
				final Column c1 = (Column) e1;
				final Column c2 = (Column) e2;
				return c1.getName().compareToIgnoreCase(c2.getName());
			}
		});
		ViewerSupport.bind(viewer, BeansObservables.observeList(state, "input"), PojoProperties.value("name"));
		context.bindSet(ViewerProperties.checkedElements(Column.class).observe(viewer), BeansObservables.observeSet(state, "checked"));
	}

	@Override
	protected void okPressed() {
		this.view.setRefreshDelta(state.refresh * 1000L);

		for (Column c : state.input) {
			if (state.checked.contains(c)) {
				view.addColumn(c);
			} else {
				view.removeColumn(c);
			}
		}
		super.okPressed();
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		refreshText.setText("10");
		initializeColumnMap();
		HashSet<Column> newSet = new HashSet<Column>(StatisticsColumns.DEFAULT_COLUMNS);
		state.setChecked(newSet);
	}
}
