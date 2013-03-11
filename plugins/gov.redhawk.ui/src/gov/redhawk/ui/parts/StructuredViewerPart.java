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
package gov.redhawk.ui.parts;

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class StructuredViewerPart.
 */
public abstract class StructuredViewerPart extends SharedPartWithButtons {

	private StructuredViewer fViewer;

	private Point fMinSize;

	/**
	 * Instantiates a new structured viewer part.
	 * 
	 * @param buttonLabels the button labels
	 */
	public StructuredViewerPart(final String[] buttonLabels) {
		super(buttonLabels);
	}

	/**
	 * Gets the viewer.
	 * 
	 * @return the viewer
	 */
	public StructuredViewer getViewer() {
		return this.fViewer;
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	public Control getControl() {
		return this.fViewer.getControl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createMainControl(final Composite parent, final int style, final int span, final FormToolkit toolkit) {
		this.fViewer = createStructuredViewer(parent, style, toolkit);
		final Control control = this.fViewer.getControl();
		final GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = span;
		control.setLayoutData(gd);
		applyMinimumSize();
	}

	/**
	 * Sets the minimum size.
	 * 
	 * @param width the width
	 * @param height the height
	 */
	public void setMinimumSize(final int width, final int height) {
		this.fMinSize = new Point(width, height);
		if (this.fViewer != null) {
			applyMinimumSize();
		}
	}

	/**
	 * Apply minimum size.
	 */
	private void applyMinimumSize() {
		if (this.fMinSize != null) {
			final GridData gd = (GridData) this.fViewer.getControl().getLayoutData();
			gd.widthHint = this.fMinSize.x;
			gd.heightHint = this.fMinSize.y;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateEnabledState() {
		getControl().setEnabled(isEnabled());
		super.updateEnabledState();
	}

	/**
	 * Creates the structured viewer.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param toolkit the toolkit
	 * @return the structured viewer
	 */
	protected abstract StructuredViewer createStructuredViewer(Composite parent, int style, FormToolkit toolkit);
}
