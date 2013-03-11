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
/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import gov.redhawk.ui.util.SWTUtil;
import gov.redhawk.ui.wizards.ListUtil;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class WizardCheckboxTablePart.
 */
public class WizardCheckboxTablePart extends CheckboxTablePart {
	private int selectAllIndex = -1;
	private int deselectAllIndex = -1;
	private final String tableName;
	private int counter;
	private Label counterLabel;

	/**
	 * Constructor for WizardCheckboxTablePart.
	 * 
	 * @param buttonLabels the button labels
	 * @param tableName the table name
	 */
	public WizardCheckboxTablePart(final String tableName, final String[] buttonLabels) {
		super(buttonLabels);
		this.tableName = tableName;
	}

	/**
	 * Instantiates a new wizard checkbox table part.
	 * 
	 * @param mainLabel the main label
	 */
	public WizardCheckboxTablePart(final String mainLabel) {
		this(mainLabel, new String[] { "&Select All", "D&eselect All" });
		setSelectAllIndex(0);
		setDeselectAllIndex(1);
	}

	/**
	 * Sets the select all index.
	 * 
	 * @param index the new select all index
	 */
	public void setSelectAllIndex(final int index) {
		this.selectAllIndex = index;
	}

	/**
	 * Sets the deselect all index.
	 * 
	 * @param index the new deselect all index
	 */
	public void setDeselectAllIndex(final int index) {
		this.deselectAllIndex = index;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void buttonSelected(final Button button, final int index) {
		if (index == this.selectAllIndex) {
			handleSelectAll(true);
		}
		if (index == this.deselectAllIndex) {
			handleSelectAll(false);
		}
	}

	/**
	 * Gets the selection.
	 * 
	 * @return the selection
	 */
	public Object[] getSelection() {
		final CheckboxTableViewer viewer = getTableViewer();
		return viewer.getCheckedElements();
	}

	/**
	 * Sets the selection.
	 * 
	 * @param selected the new selection
	 */
	public void setSelection(final Object[] selected) {
		final CheckboxTableViewer viewer = getTableViewer();
		viewer.setCheckedElements(selected);
		updateCounter(viewer.getCheckedElements().length);
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 */
	public void createControl(final Composite parent) {
		createControl(parent, 2);
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 * @param span the span
	 */
	public void createControl(final Composite parent, final int span) {
		createControl(parent, SWT.NULL, span, null);
		this.counterLabel = new Label(parent, SWT.NULL);
		final GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan = span;
		this.counterLabel.setLayoutData(gd);
		updateCounter(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Button createButton(final Composite parent, final String label, final int index, final FormToolkit toolkit) {
		final Button button = super.createButton(parent, label, index, toolkit);
		SWTUtil.setButtonDimensionHint(button);
		return button;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StructuredViewer createStructuredViewer(final Composite parent, final int style, final FormToolkit toolkit) {
		final StructuredViewer viewer = super.createStructuredViewer(parent, style, toolkit);
		viewer.setComparator(ListUtil.NAME_COMPARATOR);
		return viewer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createMainLabel(final Composite parent, final int span, final FormToolkit toolkit) {
		if (this.tableName == null) {
			return;
		}
		final Label label = new Label(parent, SWT.NULL);
		label.setText(this.tableName);
		final GridData gd = new GridData();
		gd.horizontalSpan = span;
		label.setLayoutData(gd);
	}

	/**
	 * Update counter.
	 * 
	 * @param amount the amount
	 */
	protected void updateCounter(final int amount) {
		this.counter = amount;
		updateCounterLabel();
	}

	/**
	 * Update counter label.
	 */
	protected void updateCounterLabel() {
		final String number = "" + getSelectionCount(); //$NON-NLS-1$
		final String totalNumber = "" + getTotalCount(); //$NON-NLS-1$
		final String message = NLS.bind("{0} of {1} selected.", (new String[] { number, totalNumber }));
		this.counterLabel.setText(message);
	}

	/**
	 * Gets the selection count.
	 * 
	 * @return the selection count
	 */
	public int getSelectionCount() {
		return this.counter;
	}

	/**
	 * Select all.
	 * 
	 * @param select the select
	 */
	public void selectAll(final boolean select) {
		handleSelectAll(select);
	}

	/**
	 * Gets the total count.
	 * 
	 * @return the total count
	 */
	private int getTotalCount() {
		final CheckboxTableViewer viewer = getTableViewer();
		return viewer.getTable().getItemCount();
	}

	/**
	 * Handle select all.
	 * 
	 * @param select the select
	 */
	protected void handleSelectAll(final boolean select) {
		final CheckboxTableViewer viewer = getTableViewer();
		viewer.setAllChecked(select);
		int selected;
		if (!select) {
			selected = 0;
		} else {
			selected = getTotalCount();
		}
		updateCounter(selected);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void elementChecked(final Object element, final boolean checked) {
		final int count = getSelectionCount();
		updateCounter((checked) ? count + 1 : count - 1); // SUPPRESS CHECKSTYLE AvoidInline
	}

	/**
	 * Gets the counter label.
	 * 
	 * @return the counter label
	 */
	public Label getCounterLabel() {
		return this.counterLabel;
	}
}
