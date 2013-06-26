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
package gov.redhawk.sca.ui.parts;

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.common.ui.parts.FormEntry;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class FormFilteredTree.
 * @since 9.1
 */
public class FormFilteredTree extends FilteredTree {

	private FormToolkit toolkit;

	private FormEntry fEntryFilter;

	/**
	 * Instantiates a new form filtered tree.
	 * 
	 * @param parent the parent
	 * @param treeStyle the tree style
	 * @param filter the filter
	 */
	/** RAP DEPENDENCY ISSUE **/
	//This is an internal API, and the four-argument constructor is not available in RAP
	//	public FormFilteredTree(final Composite parent, final int treeStyle, final PatternFilter filter) {
	//		super(parent, treeStyle, filter, true);
	//	}
	public FormFilteredTree(final Composite parent, final int treeStyle, final PatternFilter filter) {
		super(parent, treeStyle, filter);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createControl(final Composite parent, final int treeStyle) {
		this.toolkit = new FormToolkit(parent.getDisplay());
		final GridLayout layout = FormLayoutFactory.createClearGridLayout(false, 1);
		// Space between filter text field and tree viewer
		layout.verticalSpacing = 3; // SUPPRESS CHECKSTYLE MagicNumber
		setLayout(layout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		if (this.showFilterControls) {
			this.filterComposite = new Composite(this, SWT.NONE);
			final GridLayout filterLayout = FormLayoutFactory.createClearGridLayout(false, 2);
			filterLayout.horizontalSpacing = 5; // SUPPRESS CHECKSTYLE MagicNumber
			this.filterComposite.setLayout(filterLayout);
			this.filterComposite.setFont(parent.getFont());
			createFilterControls(this.filterComposite);
			this.filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		}

		this.treeComposite = new Composite(this, SWT.NONE);
		this.treeComposite.setLayout(FormLayoutFactory.createClearGridLayout(false, 1));
		final GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		this.treeComposite.setLayoutData(data);
		createTreeControl(this.treeComposite, treeStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		if (this.toolkit != null) {
			this.toolkit.dispose();
			this.toolkit = null;
		}
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Text doCreateFilterText(final Composite parent) {
		final int style = SWT.SINGLE | this.toolkit.getBorderStyle();
		this.fEntryFilter = new FormEntry(parent, this.toolkit, null, style);
		// Needed otherwise borders are missing on Windows Classic Theme
		/** RAP DEPENDENCY ISSUE **/
		//Method not available in RAP
		//this.toolkit.paintBordersFor(parent);
		setBackground(this.toolkit.getColors().getBackground());
		return this.fEntryFilter.getText();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TreeViewer doCreateTreeViewer(final Composite parent, final int style) {
		final TreeViewer viewer = super.doCreateTreeViewer(parent, this.toolkit.getBorderStyle() | style);
		/** RAP DEPENDENCY ISSUE **/
		//Method not available in RAP
		//this.toolkit.paintBordersFor(viewer.getTree().getParent());
		return viewer;
	}

	/**
	 * Checks if is filtered.
	 * 
	 * @return a boolean indicating whether the tree is filtered or not.
	 */
	public boolean isFiltered() {
		final Text filterText = getFilterControl();
		if (filterText != null) {
			final String filterString = filterText.getText();
			final boolean filtered = (filterString != null && filterString.length() > 0 && !filterString.equals(getInitialText()));
			return filtered;
		}
		return false;
	}
}
