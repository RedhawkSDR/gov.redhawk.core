/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.AdapterFactoryCellLabelProvider;
import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.ui.doc.HelpUtil;
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
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class SimpleSequencePropertyComposite extends BasicSimplePropertyComposite {
	private static final int NUM_COLUMNS = 3;

	private TableViewer valuesViewer;
	private final ComposedAdapterFactory adapterFactory;
	private Button addValueButton;
	private Button removeValueButton;

	private TableViewerColumn valueColumn;
	private Object lastSelection;

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public SimpleSequencePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);

		this.adapterFactory = createAdapterFactory();

		setLayout(FormLayoutFactory.createSectionClientGridLayout(false, SimpleSequencePropertyComposite.NUM_COLUMNS));
		setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createIDEntryField(toolkit, this);

		createNameEntryField(toolkit, this);

		createTypeViewer(this, toolkit);

		createValues(this, toolkit);

		createUnits(this, toolkit);
		
		createMessage(this, toolkit);

		createKind(this, toolkit);

		createMode(this, toolkit);

		createAction(this, toolkit);

		createRange(this, toolkit);

		createDescription(this, toolkit);

		toolkit.paintBordersFor(this);
	}
	/**
	 * {@inheritDoc}
	 */
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
	protected void createKind(Composite parent, FormToolkit toolkit) {
		super.createKind(parent, toolkit);
		// We want everything from the SimpleProperty base class but we want the 
		// kind viewer to show all values except EXECPARAM since only SimpleProps can be exec params.
		// This fixes bug # 264
		this.getKindViewer().addFilter(new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof PropertyConfigurationType 
						&& ((PropertyConfigurationType) element).equals(PropertyConfigurationType.EXECPARAM)) {
					
					return false;
				}
				return true;
			}
		});
		
	}
	
	protected void createValues(final Composite parent, final FormToolkit toolkit) {
		// Value
		final Label label = toolkit.createLabel(parent, "Values:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
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
		this.valueColumn.setLabelProvider(new AdapterFactoryCellLabelProvider(this.adapterFactory));
		layout.addColumnData(new ColumnWeightData(1, 20, true));

		this.addValueButton = toolkit.createButton(valuesComp, "Add...", SWT.PUSH);
		this.addValueButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());

		this.removeValueButton = toolkit.createButton(valuesComp, "Remove", SWT.PUSH);
		this.removeValueButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.BEGINNING).create());
		this.valuesViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				if (event.getSelection() instanceof StructuredSelection) {
					if (((StructuredSelection) event.getSelection()).getFirstElement() instanceof AttributeValueWrapperItemProvider) {
						final AttributeValueWrapperItemProvider provider = (AttributeValueWrapperItemProvider) ((StructuredSelection) event.getSelection())
						        .getFirstElement();
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

	/**
	 * @return the valuesViewer
	 */
	public TableViewer getValuesViewer() {
		return this.valuesViewer;
	}

	/**
	 * @return the removeValueButton
	 */
	public Button getRemoveValueButton() {
		return this.removeValueButton;
	}

	/**
	 * @return the addValueButton
	 */
	public Button getAddValueButton() {
		return this.addValueButton;
	}

	@Override
	public void setEditable(final boolean canEdit) {
		this.addValueButton.setEnabled(canEdit);
		super.setEditable(canEdit);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void createTabList() {
		this.tabList.clear();
		this.tabList.add(getIdEntry().getText());
		this.tabList.add(getTypeViewer().getControl());
		this.tabList.add(getValuesViewer().getControl().getParent());
		this.tabList.add(getUnitsText());
		this.tabList.add(getKindViewer().getControl());
		this.tabList.add(getModeViewer().getControl());
		this.tabList.add(getActionViewer().getControl());
		this.tabList.add(getRangeButton());
		this.tabList.add(getMinText().getText().getParent());
		this.tabList.add(getMaxText().getText().getParent());
		this.tabList.add(getDescriptionText());
	}

	/**
	 * @return The last selected object from the TableViewer
	 */
	public Object getLastSelection() {
		return this.lastSelection;
	}
}
