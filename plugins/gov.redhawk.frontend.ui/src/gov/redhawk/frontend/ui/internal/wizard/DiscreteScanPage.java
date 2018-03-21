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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
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

/* package */ class DiscreteScanPage extends SettingsWizardPage {

	/**
	 * Model used by the table
	 */
	@SuppressWarnings("unused")
	private class Frequency {

		private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

		private double frequency;

		Frequency(double frequency) {
			this.frequency = frequency;
		}

		public double getFrequency() {
			return frequency;
		}

		public void setFrequency(double frequency) {
			double oldValue = this.frequency;
			this.frequency = frequency;
			pcs.firePropertyChange("frequency", oldValue, frequency); //$NON-NLS-1$
		}

		public void addPropertyChangeListener(final PropertyChangeListener listener) {
			pcs.addPropertyChangeListener(listener);
		}

		public void removePropertyChangeListener(final PropertyChangeListener listener) {
			pcs.removePropertyChangeListener(listener);
		}
	}

	private WritableList<Frequency> frequencies = new WritableList<>();

	private TableViewer freqTable;

	DiscreteScanPage() {
		super(Messages.DiscreteScanPage_PageName);
	}

	@Override
	public void createControl(Composite parent) {
		setTitle(Messages.DiscreteScanPage_PageTitle);
		setDescription(Messages.DiscreteScanPage_PageDescription);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		setControl(composite);

		createTable(composite);

		createButtons(composite);
		getAddButton().addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> {
			frequencies.add(new Frequency(0));
		}));
		getRemoveButton().addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> {
			if (!freqTable.getStructuredSelection().isEmpty()) {
				frequencies.remove(freqTable.getStructuredSelection().getFirstElement());
			}
		}));
		freqTable.addSelectionChangedListener(event -> {
			getRemoveButton().setEnabled(!event.getStructuredSelection().isEmpty());
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void createTable(Composite parent) {
		// Table
		freqTable = new TableViewer(parent, SWT.BORDER);
		freqTable.getTable().setHeaderVisible(true);
		freqTable.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// Column
		TableViewerColumn column = new TableViewerColumn(freqTable, SWT.NONE);
		column.getColumn().setText(Messages.DiscreteScanPage_Column_CenterFreq);

		// Column data binding
		DataBindingContext context = new DataBindingContext();
		column.setEditingSupport(new ObservableValueEditingSupport(freqTable, context) {

			@Override
			protected IObservableValue< ? > doCreateCellEditorObservable(CellEditor cellEditor) {
				return CellEditorProperties.control().value(WidgetProperties.text(SWT.Modify)).observe(cellEditor);
			}

			@Override
			protected IObservableValue< ? > doCreateElementObservable(Object element, ViewerCell cell) {
				return BeanProperties.value("frequency").observe(element); //$NON-NLS-1$
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return new TextCellEditor(freqTable.getTable());
			}

			@Override
			protected Binding createBinding(IObservableValue target, IObservableValue model) {
				return context.bindValue(target, model, getStringToDoubleFreqStrategy(UpdateValueStrategy.POLICY_CONVERT), getDoubleToStringFreqStrategy());
			}
		});

		// Table layout
		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(100));
		freqTable.getTable().setLayout(tableLayout);

		// Table data binding
		ObservableListContentProvider contentProvider = new ObservableListContentProvider();
		freqTable.setContentProvider(contentProvider);
		freqTable.setLabelProvider(
			new ObservableMapLabelProvider(Properties.observeEach(contentProvider.getKnownElements(), BeanProperties.value("frequency"))) { //$NON-NLS-1$
				@Override
				public String getColumnText(Object element, int columnIndex) {
					if (columnIndex == 0) {
						Object result = attributeMaps[columnIndex].get(element);
						if (result != null) {
							return (String) getDoubleToStringFreqStrategy().convert(result);
						}
					}
					return null;
				}
			});
		freqTable.setInput(frequencies);
	}

	@Override
	public void loadSettings() {
		IDialogSettings settings = getPageSettings();
		String[] values = settings.getArray("frequencies"); //$NON-NLS-1$
		if (values != null) {
			frequencies.clear();
			for (String strValue : values) {
				try {
					frequencies.add(new Frequency(Double.parseDouble(strValue)));
				} catch (NumberFormatException e) {
					// PASS
				}
			}
		}
	}

	@Override
	public void saveSettings() {
		IDialogSettings settings = getPageSettings();
		String[] values = new String[frequencies.size()];
		for (int i = 0; i < frequencies.size(); i++) {
			values[i] = String.valueOf(frequencies.get(i).getFrequency());
		}
		settings.put("frequencies", values); //$NON-NLS-1$
	}

	public double[] getFrequencies() {
		return frequencies.stream().mapToDouble(value -> value.getFrequency()).toArray();
	}
}
