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
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.AdapterFactoryCellLabelProvider;
import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.ui.doc.HelpUtil;

import java.util.ArrayList;

import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SimpleSequencePropertyComposite extends BasicSimplePropertyComposite {
	protected static final int NUM_COLUMNS = 3;

	private Label valuesLabel;
	private TableViewer valuesViewer;
	private final ComposedAdapterFactory adapterFactory;
	private Button addValueButton;
	private Button removeValueButton;

	private TableViewerColumn valueColumn;
	private Object lastSelection;

	public SimpleSequencePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		this.adapterFactory = createAdapterFactory();

		createControls(this, toolkit);
	}
	
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, SimpleSequencePropertyComposite.NUM_COLUMNS));
		
		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createTypeViewer(parent, toolkit);
		createValues(parent, toolkit);
		createUnitsEntry(parent, toolkit);
		createKindViewer(parent, toolkit);
		createModeViewer(parent, toolkit);
		createActionViewer(parent, toolkit);
		createRange(parent, toolkit);
		createDescription(parent, toolkit);
		
		ArrayList<Control> tabList = new ArrayList<Control>();
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getTypeViewer().getControl());
		tabList.add(getValuesViewer().getControl().getParent());
		tabList.add(getUnitsEntry().getText());
		tabList.add(getKindViewer().getControl());
		tabList.add(getModeViewer().getControl());
		tabList.add(getActionViewer().getControl());
		tabList.add(getRangeButton());
		tabList.add(getMinText().getText().getParent());
		tabList.add(getMaxText().getText().getParent());
		tabList.add(getDescriptionText());
		
		parent.setTabList(tabList.toArray(new Control[tabList.size()]));
		
		toolkit.paintBordersFor(parent);
	}
	
	@Override
	public void dispose() {
		this.adapterFactory.dispose();
		super.dispose();
	}

	/**
	 * Create an adapter factory that yields item providers.
	 * 
	 * @return the composed adapter factory
	 */
	private ComposedAdapterFactory createAdapterFactory() {
		final ComposedAdapterFactory newAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		newAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		newAdapterFactory.addAdapterFactory(new PrfItemProviderAdapterFactory());
		newAdapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		newAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return newAdapterFactory;
	}

	@Override
	protected ComboViewer createKindViewer(Composite parent, FormToolkit toolkit) {
		ComboViewer retVal = super.createKindViewer(parent, toolkit);
		// Hide EXECPARAM since only SimpleProps can be exec params.
		this.getKindViewer().addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof PropertyConfigurationType && ((PropertyConfigurationType) element).equals(PropertyConfigurationType.EXECPARAM)) {

					return false;
				}
				return true;
			}
		});
		return retVal;
	}

	protected void createValues(final Composite parent, final FormToolkit toolkit) {
		// Value
		this.valuesLabel = toolkit.createLabel(parent, "Values:");
		this.valuesLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.valuesLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		final Composite valuesComp = toolkit.createComposite(parent, SWT.NULL);
		valuesComp.setLayout(GridLayoutFactory.fillDefaults().margins(0, 0).numColumns(2).create());
		valuesComp.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).grab(true, true).create());

		final Table table = toolkit.createTable(valuesComp, SWT.BORDER);
		table.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 150).span(1, 2).grab(true, true).create());

		this.valuesViewer = new TableViewer(table);
		final TableLayout layout = new TableLayout();
		this.valuesViewer.getTable().setLayout(layout);
		this.valuesViewer.getTable().setHeaderVisible(false);
		this.valuesViewer.getTable().setLinesVisible(true);

		this.valuesViewer.setContentProvider(new AdapterFactoryContentProvider(this.adapterFactory));

		this.valueColumn = new TableViewerColumn(this.valuesViewer, SWT.LEFT);
		this.valueColumn.setLabelProvider(new AdapterFactoryCellLabelProvider(this.adapterFactory) {
			@Override
			public void update(ViewerCell cell) {
				super.update(cell);

				// Represent empty string as "" in the value table
				Object obj = this.getElement(cell);
				if ("".equals(this.getText(obj))) {
					cell.setText("\"\"");
				}

			}
		});
		layout.addColumnData(new ColumnWeightData(1, 20, true));
		this.valuesViewer.getTable().setToolTipText("For empty string enter a value of \"\"");

		this.addValueButton = toolkit.createButton(valuesComp, "Add...", SWT.PUSH);
		this.addValueButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());

		this.removeValueButton = toolkit.createButton(valuesComp, "Remove", SWT.PUSH);
		this.removeValueButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());
		this.valuesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				if (event.getSelection() instanceof StructuredSelection) {
					if (((StructuredSelection) event.getSelection()).getFirstElement() instanceof AttributeValueWrapperItemProvider) {
						final AttributeValueWrapperItemProvider provider = (AttributeValueWrapperItemProvider) ((StructuredSelection) event.getSelection()).getFirstElement();
						SimpleSequencePropertyComposite.this.lastSelection = provider.getValue();
					}
				}
			}
		});

		this.removeValueButton.setEnabled(false);

		HelpUtil.assignTooltip(this.valuesViewer.getControl(), HelpConstants.prf_properties_simplesequence_values);

	}

	public TableViewerColumn getValueColumn() {
		return this.valueColumn;
	}

	public TableViewer getValuesViewer() {
		return this.valuesViewer;
	}

	public Button getRemoveValueButton() {
		return this.removeValueButton;
	}

	public Button getAddValueButton() {
		return this.addValueButton;
	}

	@Override
	public void setEditable(final boolean canEdit) {
		super.setEditable(canEdit);

		this.valuesLabel.setEnabled(canEdit);
		this.addValueButton.setEnabled(canEdit);
	}

	/**
	 * @return The last selected object from the TableViewer
	 */
	public Object getLastSelection() {
		return this.lastSelection;
	}
}
