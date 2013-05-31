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

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.common.ui.parts.FormEntry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.ui.doc.HelpUtil;
import gov.redhawk.ui.util.SWTUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mil.jpeojtrs.sca.prf.ActionType;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 *
 */
public abstract class BasicSimplePropertyComposite extends AbstractPropertyComposite {
	protected static final int NUM_COLUMNS = 3;

	private static final GridDataFactory FACTORY = GridDataFactory.fillDefaults().span(2, 1).grab(true, false);
	private ComboViewer typeViewer;
	private FormEntry unitsEntry;
	private Label kindLabel;
	private CheckboxTableViewer kindViewer;
	private Label actionLabel;
	private ComboViewer actionViewer;
	private Button rangeButton;
	private FormEntry minText;
	private FormEntry maxText;

	private Label messageLabel;

	private Button messageButton;

	private Button editEnumButton;

	private Button removeEnumButton;

	private Button addEnumButton;

	private TableViewer enumViewer;

	private ComposedAdapterFactory adapterFactory;

	private FormToolkit toolkit;

	private Combo typeModifier;

	private static final String DEFAULT_ACTION = "external";

	/**
	 * @param parent
	 * @param style
	 */
	public BasicSimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
		this.toolkit = toolkit;
	}

	private void assignTooltip(final Control control, final String contextId) {
		HelpUtil.assignTooltip(control, contextId);
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected Button createRange(final Composite parent, final FormToolkit toolkit) {
		final Label label = toolkit.createLabel(parent, "Range:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.rangeButton = toolkit.createButton(parent, "Enable", SWT.CHECK);
		this.rangeButton.setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
		assignTooltip(this.rangeButton, HelpConstants.prf_properties_simple_range);
		toolkit.createLabel(parent, "");
		final Composite rangeGroup = toolkit.createComposite(parent);
		rangeGroup.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, BasicSimplePropertyComposite.NUM_COLUMNS));
		rangeGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rangeGroup.setLayoutData(BasicSimplePropertyComposite.FACTORY.create());

		createMinEntryField(toolkit, rangeGroup);
		createMaxEntryField(toolkit, rangeGroup);
		toolkit.paintBordersFor(rangeGroup);
		return this.rangeButton;
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected ComboViewer createActionViewer(final Composite parent, final FormToolkit toolkit) {
		// Action
		this.actionLabel = toolkit.createLabel(parent, "Action:");
		this.actionLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.actionLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		final ComboViewer viewer = new ComboViewer(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
		toolkit.adapt(viewer.getCombo());
		final List<Object> input = new ArrayList<Object>();
		input.addAll(ActionType.VALUES);
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.getControl().setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
		viewer.setInput(input);
		viewer.setComparator(new ViewerComparator(new Comparator<String>() {
			// List should be: "", Default, Everything else in alphanumeric
			// order
			public int compare(final String o1, final String o2) {
				int retVal = 0;
				if (o1.equalsIgnoreCase(BasicSimplePropertyComposite.DEFAULT_ACTION)) {
					retVal = Integer.MIN_VALUE;
				} else if (o2.equalsIgnoreCase(BasicSimplePropertyComposite.DEFAULT_ACTION)) {
					retVal = Integer.MAX_VALUE;
				} else {
					retVal = o1.compareTo(o2);
				}
				return retVal;
			}
		}));
		assignTooltip(viewer.getControl(), HelpConstants.prf_properties_simple_action);

		this.actionViewer = viewer;
		return viewer;
	}

	protected void createMessage(final Composite parent, final FormToolkit toolkit) {
		// Message checkbox
		this.messageLabel = toolkit.createLabel(parent, "Message:");
		this.messageLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.messageLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		this.messageButton = toolkit.createButton(parent, "Enable", SWT.CHECK);
		this.messageButton.setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 * @return 
	 */
	protected CheckboxTableViewer createKindViewer(final Composite parent, final FormToolkit toolkit) {
		// Kind
		this.kindLabel = toolkit.createLabel(parent, "Kind:");
		this.kindLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.kindLabel.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		final CheckboxTableViewer viewer = new CheckboxTableViewer(toolkit.createTable(parent, SWT.CHECK | SWT.READ_ONLY));
		viewer.setContentProvider(new ArrayContentProvider());
		viewer.setLabelProvider(new LabelProvider() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public String getText(final Object element) {
				if (element instanceof PropertyConfigurationType) {
					final PropertyConfigurationType type = (PropertyConfigurationType) element;
					if (type == PropertyConfigurationType.CONFIGURE) {
						return element.toString() + " (default)";
					}
				}
				return element == null ? "" : element.toString(); //$NON-NLS-1$ // SUPPRESS CHECKSTYLE AvoidInLine
			}
		});
		viewer.setInput(PropertyConfigurationType.values());
		viewer.getControl().setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
		assignTooltip(viewer.getControl(), HelpConstants.prf_properties_simple_kind);

		// In response to Bug 289 the Message property type was moved from the viewer into a stand alone checkbox so the Message type
		// must be filtered out of the viewer.
		viewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof PropertyConfigurationType && ((PropertyConfigurationType) element).equals(PropertyConfigurationType.MESSAGE)) {

					return false;
				}
				return true;
			}
		});

		this.kindViewer = viewer;
		return this.kindViewer;
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected FormEntry createUnitsEntry(final Composite parent, final FormToolkit toolkit) {
		// Units
		this.unitsEntry = new FormEntry(parent, toolkit, "Units:", SWT.SINGLE);
		assignTooltip(this.unitsEntry.getText(), HelpConstants.prf_properties_simple_units);
		this.unitsEntry.getText().setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
		return unitsEntry;
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected ComboViewer createTypeViewer(final Composite parent, final FormToolkit toolkit) {
		// Type
		final Label label = toolkit.createLabel(parent, "Type*:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.typeViewer = new ComboViewer(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		this.typeViewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
		this.typeViewer.setContentProvider(new ArrayContentProvider());
		this.typeViewer.setLabelProvider(new LabelProvider());
		this.typeViewer.setInput(PropertyValueType.values());
		this.typeViewer.setSorter(new ViewerSorter());
		toolkit.adapt(this.typeViewer.getCombo());

		this.typeViewer.getControl().setLayoutData(GridDataFactory.fillDefaults().span(1, 1).grab(true, false).create());
		assignTooltip(this.typeViewer.getControl(), HelpConstants.prf_properties_simple_type);

		this.typeModifier = new Combo(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		this.typeModifier.addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
		this.typeModifier.setItems(new String[] { "", "real", "complex" });
		toolkit.adapt(this.typeModifier);
		typeModifier.setLayoutData(GridDataFactory.fillDefaults().span(1, 1).grab(false, false).create());

		return this.typeViewer;
	}

	public Combo getTypeModifier() {
		return typeModifier;
	}

	/**
	 * Creates the min entry field.
	 *
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	protected void createMinEntryField(final FormToolkit toolkit, final Composite client) {
		this.minText = new FormEntry(client, toolkit, "Min:", SWT.SINGLE);
	}

	/**
	 * Creates the min entry field.
	 *
	 * @param toolkit the toolkit
	 * @param client the client
	 */
	protected void createMaxEntryField(final FormToolkit toolkit, final Composite client) {
		this.maxText = new FormEntry(client, toolkit, "Max:", SWT.SINGLE);
	}

	/**
	 * @return the typeViewer
	 */
	public ComboViewer getTypeViewer() {
		return this.typeViewer;
	}

	/**
	 * @return the unitsText
	 */
	public FormEntry getUnitsEntry() {
		return this.unitsEntry;
	}

	/**
	 * @return the kindViewer
	 */
	public CheckboxTableViewer getKindViewer() {
		return this.kindViewer;
	}

	/**
	 * @return the actionViewer
	 */
	public ComboViewer getActionViewer() {
		return this.actionViewer;
	}

	/**
	 * @return the rangeButton
	 */
	public Button getRangeButton() {
		return this.rangeButton;
	}

	/**
	 * @return the Message Checkbox Button
	 */
	public Label getMessageLabel() {
		return this.messageLabel;
	}

	/**
	 * @return the Message Checkbox Button
	 */
	public Button getMessageButton() {
		return this.messageButton;
	}

	/**
	 * @return the minText
	 */
	public FormEntry getMinText() {
		return this.minText;
	}

	/**
	 * @return the maxText
	 */
	public FormEntry getMaxText() {
		return this.maxText;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEditable(final boolean canEdit) {
		super.setEditable(canEdit);

		if (this.actionViewer != null) {
			this.actionViewer.getCombo().setEnabled(canEdit);
		}
		if (this.kindViewer != null) {
			this.kindViewer.getTable().setEnabled(canEdit);
		}
		if (this.messageButton != null) {
			this.messageButton.setEnabled(canEdit);
		}
		if (this.typeViewer != null) {
			this.typeViewer.getCombo().setEnabled(canEdit);
		}
		if (this.unitsEntry != null) {
			this.unitsEntry.setEditable(canEdit);
		}
		if (this.rangeButton != null) {
			this.rangeButton.setEnabled(canEdit);
		}
		if (this.enumViewer != null) {
			this.enumViewer.getControl().setEnabled(canEdit);
		}
		if (this.addEnumButton != null) {
			this.addEnumButton.setEnabled(canEdit);
		}
		if (this.removeEnumButton != null) {
			this.removeEnumButton.setEnabled(canEdit);
		}
		if (this.editEnumButton != null) {
			this.editEnumButton.setEnabled(canEdit);
		}
	}

	/**
	 * @return the kindLabel
	 */
	public Label getKindLabel() {
		return kindLabel;
	}

	/**
	 * @return the actionLabel
	 */
	public Label getActionLabel() {
		return actionLabel;
	}

	protected void createEnumerationsViewer(final Composite parent, final FormToolkit toolkit) {
		final Label label = toolkit.createLabel(parent, "Enumerations:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.LEFT, SWT.TOP).create());
		final Composite tableComp = toolkit.createComposite(parent, SWT.NULL);
		final GridLayout layout = SWTUtil.TABLE_ENTRY_LAYOUT_FACTORY.create();
		tableComp.setLayout(layout);
		tableComp.setLayoutData(GridDataFactory.fillDefaults().span(NUM_COLUMNS - 1, 1).grab(true, true).create());
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
				return new TextCellEditor(enumViewer.getTable());
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
				final TextCellEditor retVal = new TextCellEditor(enumViewer.getTable());
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
		this.enumViewer.setColumnProperties(new String[] { PrfPackage.Literals.ENUMERATION__LABEL.getName(), PrfPackage.Literals.ENUMERATION__VALUE.getName() });

		this.enumViewer.setFilters(createEnumerationViewerFilter());
		table.setLayoutData(GridDataFactory.fillDefaults().span(1, NUM_COLUMNS).hint(SWT.DEFAULT, 50).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber
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
				removeEnumButton.setEnabled(!event.getSelection().isEmpty());
				editEnumButton.setEnabled(!event.getSelection().isEmpty());
			}
		});
		HelpUtil.assignTooltip(this.enumViewer.getControl(), HelpConstants.prf_properties_simple_value);
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
}
