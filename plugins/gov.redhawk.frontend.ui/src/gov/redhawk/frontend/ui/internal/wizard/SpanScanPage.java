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
package gov.redhawk.frontend.ui.internal.wizard;

import java.util.stream.Collectors;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.Properties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.CellEditorProperties;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableValueEditingSupport;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import FRONTEND.ScanningTunerPackage.ScanSpanRange;

/* package */ class SpanScanPage extends SettingsWizardPage {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private class ColumnEditSupport extends ObservableValueEditingSupport {

		private DataBindingContext dbc;
		private String beanPropName;

		public ColumnEditSupport(ColumnViewer viewer, DataBindingContext dbc, String beanPropName) {
			super(viewer, dbc);
			this.dbc = dbc;
			this.beanPropName = beanPropName;
		}

		@Override
		protected IObservableValue< ? > doCreateCellEditorObservable(CellEditor cellEditor) {
			return CellEditorProperties.control().value(WidgetProperties.text(SWT.Modify)).observe(cellEditor);
		}

		@Override
		protected IObservableValue< ? > doCreateElementObservable(Object element, ViewerCell cell) {
			return BeanProperties.value(beanPropName).observe(element); // $NON-NLS-1$
		}

		@Override
		protected CellEditor getCellEditor(Object element) {
			return new TextCellEditor(spanTable.getTable());
		}

		@Override
		protected Binding createBinding(IObservableValue target, IObservableValue model) {
			return dbc.bindValue(target, model, getStringToDoubleFreqStrategy(UpdateValueStrategy.POLICY_CONVERT), getDoubleToStringFreqStrategy());
		}
	}

	private WritableList<SpanModel> spans = new WritableList<>();

	private TableViewer spanTable;

	SpanScanPage() {
		super(Messages.SpanScanPage_PageName);
	}

	@Override
	public void createControl(Composite parent) {
		setTitle(Messages.SpanScanPage_PageTitle);
		setDescription(Messages.SpanScanPage_PageDescription);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		createTable(composite);

		createButtons(composite);
		getAddButton().addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> {
			spans.add(new SpanModel(0, 0, 0));
		}));
		getRemoveButton().addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> {
			if (!spanTable.getStructuredSelection().isEmpty()) {
				spans.remove(spanTable.getStructuredSelection().getFirstElement());
			}
		}));
		spanTable.addSelectionChangedListener(event -> {
			getRemoveButton().setEnabled(!event.getStructuredSelection().isEmpty());
		});
	}

	@SuppressWarnings("unchecked")
	private void createTable(Composite parent) {
		// Table
		spanTable = new TableViewer(parent, SWT.BORDER);
		spanTable.getTable().setHeaderVisible(true);
		spanTable.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		DataBindingContext context = new DataBindingContext();

		// Low freq. column + data binding
		TableViewerColumn column = new TableViewerColumn(spanTable, SWT.NONE);
		column.getColumn().setText(Messages.SpanScanPage_Column_LowFreq);
		column.setEditingSupport(new ColumnEditSupport(spanTable, context, "lowFrequency")); //$NON-NLS-1$

		// High freq. column + data binding
		column = new TableViewerColumn(spanTable, SWT.NONE);
		column.getColumn().setText(Messages.SpanScanPage_Column_HighFreq);
		column.setEditingSupport(new ColumnEditSupport(spanTable, context, "highFrequency")); //$NON-NLS-1$

		// Step column + data binding
		column = new TableViewerColumn(spanTable, SWT.NONE);
		column.getColumn().setText(Messages.SpanScanPage_Column_Step);
		column.setEditingSupport(new ColumnEditSupport(spanTable, context, "step")); //$NON-NLS-1$

		// Table layout
		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(34));
		tableLayout.addColumnData(new ColumnWeightData(34));
		tableLayout.addColumnData(new ColumnWeightData(32));
		spanTable.getTable().setLayout(tableLayout);

		// Table data binding
		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		spanTable.setContentProvider(contentProvider);
		IBeanValueProperty[] properties = BeanProperties.values("lowFrequency", "highFrequency", "step"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		spanTable.setLabelProvider(new ObservableMapLabelProvider(Properties.observeEach(contentProvider.getKnownElements(), properties)) {
			@Override
			public String getColumnText(Object element, int columnIndex) {
				if (columnIndex >= 0 && columnIndex <= 2) {
					Object result = attributeMaps[columnIndex].get(element);
					if (result != null) {
						return (String) getDoubleToStringFreqStrategy().convert(result);
					}
				}
				return null;
			}
		});
		spanTable.setInput(spans);
	}

	@Override
	public void loadSettings() {
		IDialogSettings settings = getPageSettings();
		String[] values = settings.getArray("spans"); //$NON-NLS-1$
		if (values != null) {
			spans.clear();
			for (String strValue : values) {
				String[] parts = strValue.split(","); //$NON-NLS-1$
				if (parts.length != 3) {
					continue;
				}
				try {
					spans.add(new SpanModel(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2])));
				} catch (NumberFormatException e) {
					// PASS
				}
			}
		}
	}

	@Override
	public void saveSettings() {
		IDialogSettings settings = getPageSettings();
		String[] values = new String[spans.size()];
		for (int i = 0; i < spans.size(); i++) {
			SpanModel span = spans.get(i);
			values[i] = String.valueOf(span.getLowFrequency()) + "," + String.valueOf(span.getHighFrequency()) + "," + String.valueOf(span.getStep()); //$NON-NLS-1$ //$NON-NLS-2$
		}
		settings.put("spans", values); //$NON-NLS-1$
	}

	public ScanSpanRange[] getScanList() {
		return spans.stream() //
				.map(value -> new ScanSpanRange(value.getLowFrequency(), value.getHighFrequency(), value.getStep())) //
				.collect(Collectors.toList()) //
				.toArray(new ScanSpanRange[spans.size()]);
	}
}
