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
import gov.redhawk.ui.doc.HelpUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mil.jpeojtrs.sca.prf.ActionType;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 *
 */
public abstract class BasicSimplePropertyComposite extends AbstractPropertyComposite {
	private static final int NUM_COLUMNS = 3;

	private static final GridDataFactory FACTORY = GridDataFactory.fillDefaults().span(2, 1).grab(true, false);
	private Label typeLabel;
	private ComboViewer typeViewer;
	private Label unitsLabel;
	private Text unitsText;
	private Label kindLabel;
	private CheckboxTableViewer kindViewer;
	private Label actionLabel;
	private ComboViewer actionViewer;
	private Label rangeLabel;
	private Button rangeButton;
	private FormEntry minText;
	private FormEntry maxText;

	private static final String DEFAULT_ACTION = "external";

	/**
	 * @param parent
	 * @param style
	 */
	public BasicSimplePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
	}

	private void assignTooltip(final Control control, final String contextId) {
		HelpUtil.assignTooltip(control, contextId);
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createRange(final Composite parent, final FormToolkit toolkit) {
		this.rangeLabel = toolkit.createLabel(parent, "Range:");
		this.rangeLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
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
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createAction(final Composite parent, final FormToolkit toolkit) {
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
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createKind(final Composite parent, final FormToolkit toolkit) {
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
		this.kindViewer = viewer;
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createUnits(final Composite parent, final FormToolkit toolkit) {
		// Units
		this.unitsLabel = toolkit.createLabel(parent, "Units:");
		this.unitsLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		this.unitsText = toolkit.createText(parent, "", SWT.SINGLE);
		assignTooltip(this.unitsText, HelpConstants.prf_properties_simple_units);
		this.unitsText.setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
	}

	/**
	 * @param propertyComposite
	 * @param toolkit
	 */
	protected void createTypeViewer(final Composite parent, final FormToolkit toolkit) {
		// Type
		this.typeLabel = toolkit.createLabel(parent, "Type*:");
		this.typeLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
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
		this.typeViewer.getControl().setLayoutData(BasicSimplePropertyComposite.FACTORY.create());
		assignTooltip(this.typeViewer.getControl(), HelpConstants.prf_properties_simple_type);
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
	public Text getUnitsText() {
		return this.unitsText;
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

	@Override
	public void setEditable(final boolean canEdit) {
		super.setEditable(canEdit);

		this.typeLabel.setEnabled(canEdit);
		this.typeViewer.getCombo().setEnabled(canEdit);
		this.unitsLabel.setEnabled(canEdit);
		this.unitsText.setEditable(canEdit);
		if (this.kindViewer != null) {
			this.kindLabel.setEnabled(canEdit);
			this.kindViewer.getTable().setEnabled(canEdit);
		}
		if (this.actionViewer != null) {
			this.actionLabel.setEnabled(canEdit);
			this.actionViewer.getCombo().setEnabled(canEdit);
		}
		this.rangeLabel.setEnabled(canEdit);
		this.rangeButton.setEnabled(canEdit);
		// These are data-bound, so only disable
		if (!canEdit) {
			this.minText.setEditable(false);
			this.maxText.setEditable(false);
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
}
