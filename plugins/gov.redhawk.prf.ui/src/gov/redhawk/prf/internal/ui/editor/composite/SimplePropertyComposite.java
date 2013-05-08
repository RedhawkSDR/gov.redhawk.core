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

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.ui.doc.HelpUtil;
import gov.redhawk.ui.util.SWTUtil;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 */
public class SimplePropertyComposite extends BasicSimplePropertyComposite {
	private static final int NUM_COLUMNS = 3;

	private final FormToolkit toolkit;
	private ComposedAdapterFactory adapterFactory;

	private TableViewer enumViewer;
	private Button addEnumButton;
	private Button editEnumButton;
	private Button removeEnumButton;
	private Text valueText;
	private boolean hiddenViewers;

	private Label valueLabel;

	/**
	 * @param parent
	 * @param style
	 * @param toolkit
	 */
	public SimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
		this.toolkit = toolkit;

		setLayout(FormLayoutFactory.createSectionClientGridLayout(false, SimplePropertyComposite.NUM_COLUMNS));
		setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		createIDEntryField(toolkit, this);

		createNameEntryField(toolkit, this);

		createTypeViewer(this, toolkit);

		createValue(this, toolkit);

		createUnits(this, toolkit);

		createMessage(this, toolkit);

		createKind(this, toolkit);

		createMode(this, toolkit);

		createAction(this, toolkit);

		createEnumerations(this, toolkit);

		createRange(this, toolkit);

		createDescription(this, toolkit);

		toolkit.paintBordersFor(this);
	}

	private void createValue(final Composite parent, final FormToolkit toolkit) {
		// Value
		valueLabel = toolkit.createLabel(parent, "Value:");
		valueLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.valueText = toolkit.createText(parent, "", SWT.SINGLE);
		this.valueText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).create());
		HelpUtil.assignTooltip(this.valueText, HelpConstants.prf_properties_simple_value);
	}

	private void createEnumerations(final Composite parent, final FormToolkit toolkit) {
		final Label label = toolkit.createLabel(this, "Enumerations:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).create());
		final Composite tableComp = toolkit.createComposite(this, SWT.NULL);
		final GridLayout layout = SWTUtil.TABLE_ENTRY_LAYOUT_FACTORY.create();
		tableComp.setLayout(layout);
		tableComp.setLayoutData(GridDataFactory.fillDefaults().span(SimplePropertyComposite.NUM_COLUMNS - 1, 1).grab(true, true).create());
		final Table table = new Table(tableComp, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		final TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(20, 100, true)); // SUPPRESS CHECKSTYLE MagicNumber
		tableLayout.addColumnData(new ColumnWeightData(30, 40, true)); // SUPPRESS CHECKSTYLE MagicNumber
		table.setLayout(tableLayout);
		table.setLayoutData(GridDataFactory.fillDefaults().span(1, 3).hint(SWT.DEFAULT, 100).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber

		this.enumViewer = new TableViewer(table);
		this.enumViewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()));

		final TableViewerColumn enumLabelColumn = new TableViewerColumn(this.enumViewer, SWT.NULL);
		enumLabelColumn.getColumn().setText("Label");
		enumLabelColumn.setEditingSupport(new EditingSupport(this.enumViewer) {

			@Override
			protected void setValue(final Object element, final Object value) {
				final Enumeration e = (Enumeration) element;
				ScaModelCommand.execute(e, new ScaModelCommand() {

					public void execute() {
						e.setLabel((value == null) ? null : value.toString());
					}
				});
			}

			@Override
			protected Object getValue(final Object element) {
				final Enumeration e = (Enumeration) element;
				return (e.getLabel() == null) ? "" : e.getLabel();
			}

			@Override
			protected CellEditor getCellEditor(final Object element) {
				return new TextCellEditor(SimplePropertyComposite.this.enumViewer.getTable());
			}

			@Override
			protected boolean canEdit(final Object element) {
				return true;
			}
		});

		final TableViewerColumn enumValueColumn = new TableViewerColumn(this.enumViewer, SWT.NULL);
		enumValueColumn.getColumn().setText("Value");
		enumValueColumn.setEditingSupport(new EditingSupport(this.enumViewer) {

			@Override
			protected void setValue(final Object element, final Object value) {
				final Enumeration e = (Enumeration) element;
				ScaModelCommand.execute(e, new ScaModelCommand() {

					public void execute() {
						e.setValue((value == null) ? null : value.toString());
					}
				});
			}

			@Override
			protected Object getValue(final Object element) {
				final Enumeration e = (Enumeration) element;
				return (e.getValue() == null) ? "" : e.getValue();
			}

			@Override
			protected CellEditor getCellEditor(final Object obj) {
				final TextCellEditor retVal = new TextCellEditor(SimplePropertyComposite.this.enumViewer.getTable());
				retVal.setValidator(new ICellEditorValidator() {

					public String isValid(final Object value) {
						final Enumeration element = (Enumeration) obj;
						final Simple simple = (Simple) element.eContainer().eContainer();
						try {
							AnyUtils.convertString(value.toString(), simple.getType().getLiteral());
							return null;
						} catch (final Exception e) {
							return "Invalid value";
						}
					}
				});
				return retVal;
			}

			@Override
			protected boolean canEdit(final Object element) {
				return true;
			}
		});

		this.enumViewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()));
		this.enumViewer
		        .setColumnProperties(new String[] { PrfPackage.Literals.ENUMERATION__LABEL.getName(), PrfPackage.Literals.ENUMERATION__VALUE.getName() });

		this.enumViewer.setFilters(createEnumerationViewerFilter());
		table.setLayoutData(GridDataFactory.fillDefaults().span(1, SimplePropertyComposite.NUM_COLUMNS).hint(SWT.DEFAULT, 50).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber
		this.addEnumButton = this.toolkit.createButton(tableComp, "Add...", SWT.PUSH);
		this.addEnumButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		this.editEnumButton = this.toolkit.createButton(tableComp, "Edit", SWT.PUSH);
		this.editEnumButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		this.editEnumButton.setEnabled(false);
		this.removeEnumButton = this.toolkit.createButton(tableComp, "Remove", SWT.PUSH);
		this.removeEnumButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		this.removeEnumButton.setEnabled(!this.enumViewer.getSelection().isEmpty());
		this.enumViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				SimplePropertyComposite.this.removeEnumButton.setEnabled(!event.getSelection().isEmpty());
				SimplePropertyComposite.this.editEnumButton.setEnabled(!event.getSelection().isEmpty());
			}
		});
		HelpUtil.assignTooltip(this.enumViewer.getControl(), HelpConstants.prf_properties_simple_value);
	}

	/**
	 * @return the valueText
	 */
	public Text getValueText() {
		return this.valueText;
	}

	/**
	 * Used to override the standard "Value:" text displayed
	 * In the case of struct sequences, the text is "Default Value:"
	 * This method must be run in the UI thread.
	 * @param labelText The desired displayed text
	 */
	public void setValueLabelText(String labelText) {
		this.valueLabel.setText(labelText);
	}
	
	@Override
	public void setEditable(final boolean canEdit) {
		this.valueText.setEditable(canEdit);
		this.enumViewer.getTable().setEnabled(canEdit);
		this.addEnumButton.setEnabled(canEdit);
		super.setEditable(canEdit);
	}

	/**
	 * Gets the adapter factory.
	 * 
	 * @return the adapter factory
	 */
	protected AdapterFactory getAdapterFactory() {
		if (this.adapterFactory == null) {
			this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
			this.adapterFactory.addAdapterFactory(new PrfItemProviderAdapterFactory());
			this.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
			this.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		}
		return this.adapterFactory;
	}

	/**
	 * @return the propertiesViewer
	 */
	public TableViewer getEnumerationViewer() {
		return this.enumViewer;
	}

	/**
	 * @return the addPropertyButton
	 */
	public Button getAddEnumButton() {
		return this.addEnumButton;
	}

	/**
	 * @return the editPropertyButton
	 */
	public Button getEditEnumButton() {
		return this.editEnumButton;
	}

	/**
	 * @return the removePropertyButton
	 */
	public Button getRemoveEnumButton() {
		return this.removeEnumButton;
	}

	/**
	 * Creates the os viewer filter.
	 * 
	 * @return the viewer filter[]
	 */
	private ViewerFilter[] createEnumerationViewerFilter() {
		return new ViewerFilter[] { new ViewerFilter() {
			@Override
			public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
				return element instanceof Enumeration;
			}
		} };
	}

	/**
	 * Create a tabList with the
	 */
	public void createTabList() {
		this.tabList.clear();
		this.tabList.add(getIdEntry().getText());
		this.tabList.add(getTypeViewer().getControl());
		this.tabList.add(getValueText());
		this.tabList.add(getUnitsText());
		this.tabList.add(getMessageButton());
		this.tabList.add(getKindViewer().getControl());
		this.tabList.add(getModeViewer().getControl());
		this.tabList.add(getActionViewer().getControl());
		this.tabList.add(this.enumViewer.getControl().getParent());
		this.tabList.add(getRangeButton());
		this.tabList.add(getMinText().getText().getParent());
		this.tabList.add(getMaxText().getText().getParent());
		this.tabList.add(getDescriptionText());
	}

	private List<Control> getHideableControls() {
		List<Control> controls = new ArrayList<Control>();
		controls.add(this.getModeLabel());
		controls.add(this.getModeViewer().getControl());
		controls.add(this.getKindLabel());
		controls.add(this.getMessageLabel());
		controls.add(this.getMessageButton());
		controls.add(this.getKindViewer().getControl());
		controls.add(this.getActionLabel());
		controls.add(this.getActionViewer().getControl());
		return controls;
	}

	/**
	 * Hides or shows controls based on the current state and the parent of the simple.
	 */
	public void updateHiddenControls(Simple simple) {
		//If the viewers are hidden and this simple doesn't belong to a struct, show the viewers
		if (this.hiddenViewers && !(simple.eContainer() instanceof Struct)) {
			for (Control control : getHideableControls()) {
				((GridData) control.getLayoutData()).exclude = false;
				control.setVisible(true);
			}
			this.hiddenViewers = false;
		} else if (!this.hiddenViewers && simple.eContainer() instanceof Struct) {
			for (Control control : getHideableControls()) {
				((GridData) control.getLayoutData()).exclude = true;
				control.setVisible(false);
			}
			this.hiddenViewers = true;
		}
		
		if (simple.eContainer() instanceof Struct && simple.eContainer().eContainer() instanceof StructSequence) {
			setValueLabelText("Default Value:");
		} else {
			setValueLabelText("Value:");
		}
		
		this.layout();
		// If you don't recompute size with the correct width the section get's resized to be narrow
		this.getParent().setSize(this.getParent().computeSize(this.getParent().getSize().x, SWT.DEFAULT, true));
		this.getParent().layout();
	}
}
