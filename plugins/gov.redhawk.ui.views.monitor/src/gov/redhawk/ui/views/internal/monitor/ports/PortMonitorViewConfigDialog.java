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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
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
import gov.redhawk.monitor.model.ports.Monitor;
import gov.redhawk.monitor.model.ports.MonitorRegistry;
import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.sca.util.PropertyChangeSupport;

/**
 * 
 */
public class PortMonitorViewConfigDialog extends ViewSettingsDialog {

	@SuppressWarnings("unused")
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
			List<Column> oldValue = this.input;
			this.input = input;
			pcs.firePropertyChange("input", oldValue, input);
		}

		public boolean inputHasColumn(String id) {
			for (Column column : input) {
				if (column.getId().equals(id)) {
					return true;
				}
			}
			return false;
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
		state.getChecked().clear();
		state.getInput().clear();
		for (final Column c : StatisticsColumns.DEFAULT_COLUMNS) {
			if (view.hasColumn(c)) {
				state.getChecked().add(c);
			}
			state.getInput().add(c);
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
		if (!state.inputHasColumn(type.id)) {
			Column dataTypeColumn = StatisticsColumns.DATA_TYPE_COLUMNS.get(type.id);
			if (dataTypeColumn == null) {
				dataTypeColumn = new DataTypeColumn(type);
			}
			if (this.view.hasColumn(dataTypeColumn)) {
				state.getChecked().add(dataTypeColumn);
			}
			state.getInput().add(dataTypeColumn);
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

	@SuppressWarnings("unchecked")
	private void createRefreshGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		group.setText("Preferences");
		group.setLayout(new GridLayout(2, false));

		Label label = new Label(group, SWT.NONE);
		label.setText("Refresh Interval (sec):");

		refreshText = new Text(group, SWT.BORDER);
		refreshText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Binding binding = context.bindValue(WidgetProperties.text(SWT.Modify).observe(refreshText),
			BeanProperties.value(state.getClass(), "refresh").observe(state));
		binding.getValidationStatus().addValueChangeListener(event -> {
			IStatus status = (IStatus) event.getObservableValue().getValue();
			Button button = getButton(IDialogConstants.OK_ID);
			if (button != null) {
				button.setEnabled(status.isOK());
			}
		});
		ControlDecorationSupport.create(binding, SWT.TOP | SWT.LEFT);
	}

	@SuppressWarnings("unchecked")
	private void createColumnGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.NONE);
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		group.setText("Column Configuration");
		group.setLayout(new GridLayout(1, false));
		CheckboxTableViewer viewer = CheckboxTableViewer.newCheckList(group, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL);
		final Table table = viewer.getTable();
		final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gdTable.heightHint = 350;
		table.setLayoutData(gdTable);
		viewer.getTable().setHeaderVisible(false);
		viewer.setComparator(new ViewerComparator() {
			@Override
			protected Comparator< ? super String> getComparator() {
				return (s1, s2) -> ((String) s1).compareToIgnoreCase((String) s2);
			}
		});
		ViewerSupport.bind(viewer, BeanProperties.list(state.getClass(), "input").observe(state), PojoProperties.value("name"));
		context.bindSet(ViewerProperties.checkedElements(Column.class).observe(viewer), BeanProperties.set(state.getClass(), "checked").observe(state));

		final ViewerFilter filter = new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				return !(element instanceof DataTypeColumn);
			}
		};
		viewer.setFilters(filter);

		Button checkbox = new Button(group, SWT.CHECK);
		checkbox.setText("Show advanced");
		checkbox.addListener(SWT.Selection, event -> {
			if (checkbox.getSelection()) {
				viewer.setFilters();
			} else {
				viewer.setFilters(filter);
			}
		});
	}

	@Override
	protected void okPressed() {
		this.view.setRefreshDelta(state.refresh * 1000L);

		for (Column c : state.getInput()) {
			if (state.getChecked().contains(c)) {
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
