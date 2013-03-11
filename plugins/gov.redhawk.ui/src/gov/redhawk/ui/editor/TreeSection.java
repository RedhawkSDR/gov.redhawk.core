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
package gov.redhawk.ui.editor;

import gov.redhawk.ui.parts.StructuredViewerPart;
import gov.redhawk.ui.parts.TreePart;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class TreeSection.
 */
public abstract class TreeSection extends StructuredViewerSection {

	// CHECKSTYLE:OFF
	/** The handle default button. */
	protected boolean fHandleDefaultButton = true;

	// CHECKSTYLE:ON

	/**
	 * The Class PartAdapter.
	 */
	class PartAdapter extends TreePart {

		/**
		 * Instantiates a new part adapter.
		 * 
		 * @param buttonLabels the button labels
		 */
		public PartAdapter(final String[] buttonLabels) {
			super(buttonLabels);
		}

		/**
		 * Selection changed.
		 * 
		 * @param selection the selection
		 */
		@Override
		public void selectionChanged(final IStructuredSelection selection) {
			getManagedForm().fireSelectionChanged(TreeSection.this, selection);
			TreeSection.this.selectionChanged(selection);
		}

		/**
		 * Handle double click.
		 * 
		 * @param selection the selection
		 */
		@Override
		public void handleDoubleClick(final IStructuredSelection selection) {
			TreeSection.this.handleDoubleClick(selection);
		}

		/**
		 * Button selected.
		 * 
		 * @param button the button
		 * @param index the index
		 */
		@Override
		public void buttonSelected(final Button button, final int index) {
			TreeSection.this.buttonSelected(index);
			if (TreeSection.this.fHandleDefaultButton) {
				button.getShell().setDefaultButton(null);
			}
		}

		/**
		 * Creates the buttons.
		 * 
		 * @param parent the parent
		 * @param toolkit the toolkit
		 */
		@Override
		protected void createButtons(final Composite parent, final FormToolkit toolkit) {
			super.createButtons(parent, toolkit);
			enableButtons();
			if (parent.getData("filtered") != null) { //$NON-NLS-1$
				final GridLayout layout = (GridLayout) this.fButtonContainer.getLayout();
				layout.marginHeight = 28; // SUPPRESS CHECKSTYLE MagicNumber
			}
		}

		/**
		 * Creates the tree viewer.
		 * 
		 * @param parent the parent
		 * @param style the style
		 * @return the tree viewer
		 */
		@Override
		protected TreeViewer createTreeViewer(final Composite parent, final int style) {
			final TreeViewer viewer = TreeSection.this.createTreeViewer(parent, style);
			getPage().getEditor().createContextMenuFor(viewer);
			return viewer;
		}

	}

	/**
	 * Constructor for TableSection.
	 * 
	 * @param formPage the form page
	 * @param parent the parent
	 * @param style the style
	 * @param buttonLabels the button labels
	 */
	public TreeSection(final ScaFormPage formPage, final Composite parent, final int style, final String[] buttonLabels) {
		super(formPage, parent, style, buttonLabels);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StructuredViewerPart createViewerPart(final String[] buttonLabels) {
		return new PartAdapter(buttonLabels);
	}

	/**
	 * Gets the tree part.
	 * 
	 * @return the tree part
	 */
	protected TreePart getTreePart() {
		return (TreePart) this.fViewerPart;
	}

	/**
	 * Creates the tree viewer.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @return the tree viewer
	 */
	protected TreeViewer createTreeViewer(final Composite parent, final int style) {
		final TreeViewer viewer = new TreeViewer(parent, style);
		return viewer;
	}

	/**
	 * Selection changed.
	 * 
	 * @param selection the selection
	 */
	protected abstract void selectionChanged(final IStructuredSelection selection);

	/**
	 * Expands or collapsed selected node according to its current state.
	 * 
	 * @param selection the selection
	 */
	protected void handleDoubleClick(final IStructuredSelection selection) {
		final TreeViewer viewer = (TreeViewer) this.fViewerPart.getViewer();
		final boolean expandedState = viewer.getExpandedState(selection.getFirstElement());
		viewer.setExpandedState(selection.getFirstElement(), !expandedState);
	}

	/**
	 * Enable buttons.
	 */
	protected void enableButtons() {
	}
}
