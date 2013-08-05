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
package gov.redhawk.sca.internal.ui.wizards;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * 
 */
public class ConnectionPropertiesWizard extends Wizard {

	public static class ConnectionPropertiesWizardPage extends WizardPage {

		private final Map<String, String> stringMap = new HashMap<String, String>();
		private Text keyText;
		private Text valueText;
		private Button addButton;
		private TableViewer valuesViewer;
		private Button removeButton;

		protected ConnectionPropertiesWizardPage(final String pageName) {
			super(pageName);
			setTitle("Edit Connection Properties");
			setDescription("Edit the properties used by CORBA when creating the connection. \nIf the Domain is already connected you will need to reconnect for the changes to take place.");
		}

		public void createControl(final Composite parent) {
			final Composite main = new Composite(parent, SWT.None);
			main.setLayout(new GridLayout(1, false));
			final Composite newValueComposite = createNewValueComposite(main);
			newValueComposite.setLayoutData(GridDataFactory.fillDefaults().create());

			final Composite currentValuesComposite = createCurrentValuesComposite(main);
			currentValuesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
			setControl(main);
		}

		private Composite createNewValueComposite(final Composite main) {
			final Composite retVal = new Composite(main, SWT.None);
			retVal.setLayout(new GridLayout(5, false));
			Label label;
			label = new Label(retVal, SWT.None);
			label.setText("Key:");
			this.keyText = new Text(retVal, SWT.BORDER);
			this.keyText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

			label = new Label(retVal, SWT.None);
			label.setText("Value:");
			this.valueText = new Text(retVal, SWT.BORDER);
			this.valueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

			this.addButton = new Button(retVal, SWT.None);
			this.addButton.setText("&Add");
			this.addButton.addSelectionListener(new SelectionAdapter() {

				@Override
				public void widgetSelected(final SelectionEvent e) {
					ConnectionPropertiesWizardPage.this.stringMap.put(ConnectionPropertiesWizardPage.this.keyText.getText(),
					        ConnectionPropertiesWizardPage.this.valueText.getText());
					ConnectionPropertiesWizardPage.this.keyText.setText("");
					ConnectionPropertiesWizardPage.this.valueText.setText("");
					ConnectionPropertiesWizardPage.this.valuesViewer.refresh();
					ConnectionPropertiesWizardPage.this.keyText.setFocus();
				}
			});

			return retVal;
		}

		private Composite createCurrentValuesComposite(final Composite main) {
			final Composite retVal = new Composite(main, SWT.None);
			retVal.setLayout(new GridLayout(2, false));

			final Composite viewerComposite = createTableViewer(retVal);
			viewerComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());

			new Label(retVal, SWT.None).setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
			this.removeButton = new Button(retVal, SWT.PUSH);
			this.removeButton.setText("&Remove");
			this.removeButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					for (final Object obj : ((IStructuredSelection) ConnectionPropertiesWizardPage.this.valuesViewer.getSelection()).toArray()) {
						final Map.Entry< ? , ? > entry = (Entry< ? , ? >) obj;
						ConnectionPropertiesWizardPage.this.keyText.setText(entry.getKey().toString());
						ConnectionPropertiesWizardPage.this.valueText.setText(entry.getValue().toString());
						ConnectionPropertiesWizardPage.this.stringMap.remove(entry.getKey());
						ConnectionPropertiesWizardPage.this.valueText.setFocus();
					}
					ConnectionPropertiesWizardPage.this.valuesViewer.refresh();
				}
			});
			return retVal;
		}

		private Composite createTableViewer(final Composite retVal) {
			final TableLayout tableLayout = new TableLayout();
			tableLayout.addColumnData(new ColumnWeightData(50, 10, true)); // SUPPRESS CHECKSTYLE MagicNumber
			tableLayout.addColumnData(new ColumnWeightData(50, 10, true)); // SUPPRESS CHECKSTYLE MagicNumber
			this.valuesViewer = new TableViewer(retVal);
			this.valuesViewer.getTable().setLayout(tableLayout);
			this.valuesViewer.getTable().setHeaderVisible(true);
			this.valuesViewer.getTable().setLinesVisible(true);
			this.valuesViewer.setContentProvider(new ArrayContentProvider());

			final TableViewerColumn keyColumn = new TableViewerColumn(this.valuesViewer, SWT.LEFT);
			keyColumn.getColumn().setText("Key");
			keyColumn.getColumn().setResizable(true);
			keyColumn.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(final Object element) {
					return ((Map.Entry< ? , ? >) element).getKey().toString();
				}
			});

			final TableViewerColumn valueColumn = new TableViewerColumn(this.valuesViewer, SWT.LEFT);
			valueColumn.getColumn().setText("Value");
			valueColumn.getColumn().setResizable(true);
			valueColumn.setLabelProvider(new ColumnLabelProvider() {
				@Override
				public String getText(final Object element) {
					return ((Map.Entry< ? , ? >) element).getValue().toString();
				}
			});
			valueColumn.setEditingSupport(new EditingSupport(this.valuesViewer) {

				@Override
				protected CellEditor getCellEditor(final Object element) {
					return new TextCellEditor(ConnectionPropertiesWizardPage.this.valuesViewer.getTable());
				}

				@Override
				protected boolean canEdit(final Object element) {
					return true;
				}

				@Override
				protected Object getValue(final Object element) {
					final Map.Entry< ? , ? > entry = (Entry< ? , ? >) element;
					return entry.getValue();
				}

				@SuppressWarnings("unchecked")
				@Override
				protected void setValue(final Object element, final Object value) {
					final Map.Entry< ? , String> entry = (Entry< ? , String>) element;
					entry.setValue(value.toString());
					ConnectionPropertiesWizardPage.this.valuesViewer.refresh();
				}

			});

			this.valuesViewer.setInput(this.stringMap.entrySet());
			return this.valuesViewer.getTable();
		}

		public Map<String, String> getStringMap() {
			return this.stringMap;
		}

	}

	private final ConnectionPropertiesWizardPage page = new ConnectionPropertiesWizardPage("page1");

	public ConnectionPropertiesWizard() {
		setWindowTitle("Domain Connection Properties");
	}

	@Override
	public void addPages() {
		addPage(this.page);
	}

	public void setStringMap(final EMap< ? , ? > map) {
		this.page.getStringMap().clear();
		for (final Map.Entry< ? , ? > entry : map) {
			this.page.getStringMap().put(entry.getKey().toString(), entry.getValue().toString());
		}
		if (this.page.valuesViewer != null) {
			this.page.valuesViewer.refresh();
		}
	}

	public Map<String, String> getStringMap() {
		return this.page.getStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

}
