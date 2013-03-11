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

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.ui.parts.StructuredViewerPart;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class StructuredViewerSection.
 */
public abstract class StructuredViewerSection extends ScaSection {

	// CHECKSTYLE:OFF
	/** The viewer part. */
	protected StructuredViewerPart fViewerPart;
	// CHECKSTYLE:ON

	private boolean fDoSelection;

	/**
	 * Constructor for StructuredViewerSection.
	 * 
	 * @param formPage the form page
	 * @param parent the parent
	 * @param style the style
	 * @param buttonLabels the button labels
	 */
	public StructuredViewerSection(final ScaFormPage formPage, final Composite parent, final int style, final String[] buttonLabels) {
		this(formPage, parent, style, true, buttonLabels);
	}

	/**
	 * Constructor for StructuredViewerSection.
	 * 
	 * @param formPage the form page
	 * @param parent the parent
	 * @param style the style
	 * @param titleBar the title bar
	 * @param buttonLabels the button labels
	 */
	public StructuredViewerSection(final ScaFormPage formPage, final Composite parent, final int style, final boolean titleBar, final String[] buttonLabels) {
		super(formPage, parent, style, titleBar);
		this.fViewerPart = createViewerPart(buttonLabels);
		this.fViewerPart.setMinimumSize(50, 50); // SUPPRESS CHECKSTYLE MagicNumber
		final FormToolkit toolkit = formPage.getManagedForm().getToolkit();
		createClient(getSection(), toolkit);
		this.fDoSelection = true;
	}

	/**
	 * Creates the viewer part control.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param span the span
	 * @param toolkit the toolkit
	 */
	protected void createViewerPartControl(final Composite parent, final int style, final int span, final FormToolkit toolkit) {
		this.fViewerPart.createControl(parent, style, span, toolkit);
	}

	/**
	 * Creates the client container.
	 * 
	 * @param parent the parent
	 * @param span the span
	 * @param toolkit the toolkit
	 * @return the composite
	 */
	protected Composite createClientContainer(final Composite parent, final int span, final FormToolkit toolkit) {
		final Composite container = toolkit.createComposite(parent);
		container.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, span));
		return container;
	}

	/**
	 * Creates the viewer part.
	 * 
	 * @param buttonLabels the button labels
	 * @return the structured viewer part
	 */
	protected abstract StructuredViewerPart createViewerPart(String[] buttonLabels);

	/**
	 * Button selected.
	 * 
	 * @param index the index
	 */
	protected void buttonSelected(final int index) {
	}

	/**
	 * Gets the viewer selection.
	 * 
	 * @return the viewer selection
	 */
	protected ISelection getViewerSelection() {
		return this.fViewerPart.getViewer().getSelection();
	}

	/**
	 * Sets the focus.
	 */
	@Override
	public void setFocus() {
		this.fViewerPart.getControl().setFocus();
	}

	/**
	 * Gets the structured viewer part.
	 * 
	 * @return the structured viewer part
	 */
	public StructuredViewerPart getStructuredViewerPart() {
		return this.fViewerPart;
	}

	/**
	 * <p>
	 * Given the index of TreeViewer item and the size of the array of its
	 * immediate siblings, gets the index of the desired new selection as
	 * follows:
	 * <ul>
	 * <li>if this is the only item, return -1 (meaning select the parent)</li>
	 * <li>if this is the last item, return the index of the predecessor</li>
	 * <li>otherwise, return the index of the successor</li>
	 * </ul>
	 * </p>
	 * .
	 * 
	 * @param thisIndex the item's index
	 * @param length the array length
	 * @return new selection index or -1 for parent
	 */
	protected int getNewSelectionIndex(final int thisIndex, final int length) {
		if (thisIndex == length - 1) {
			return thisIndex - 1;
		}
		return thisIndex + 1;
	}

	/**
	 * Gets the array index.
	 * 
	 * @param array the array
	 * @param object the object
	 * @return the array index
	 */
	protected int getArrayIndex(final Object[] array, final Object object) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(object)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Do select.
	 * 
	 * @param select the select
	 */
	protected void doSelect(final boolean select) {
		this.fDoSelection = select;
	}

	/**
	 * Can select.
	 * 
	 * @return true, if successful
	 */
	protected boolean canSelect() {
		return this.fDoSelection;
	}

}
