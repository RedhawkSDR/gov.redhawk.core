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

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class SharedPart.
 */
public abstract class SharedPart {
	private boolean enabled = true;

	/**
	 * Sets the enabled.
	 * 
	 * @param enabled the new enabled
	 */
	public void setEnabled(final boolean enabled) {
		if (enabled != this.enabled) {
			this.enabled = enabled;
			updateEnabledState();
		}
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param span the span
	 * @param toolkit the toolkit
	 */
	public abstract void createControl(Composite parent, int style, int span, FormToolkit toolkit);

	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}

	/**
	 * Update enabled state.
	 */
	protected void updateEnabledState() {
	}

	/**
	 * Creates the composite.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @return the composite
	 */
	protected Composite createComposite(final Composite parent, final FormToolkit toolkit) {
		if (toolkit == null) {
			return new Composite(parent, SWT.NULL);
		}
		return toolkit.createComposite(parent);
	}

	/**
	 * Creates the empty space.
	 * 
	 * @param parent the parent
	 * @param span the span
	 * @param toolkit the toolkit
	 * @return the label
	 */
	protected Label createEmptySpace(final Composite parent, final int span, final FormToolkit toolkit) {
		Label label;
		if (toolkit != null) {
			label = toolkit.createLabel(parent, null);
		} else {
			label = new Label(parent, SWT.NULL);
		}
		final GridData gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.horizontalSpan = span;
		gd.widthHint = 0;
		gd.heightHint = 0;
		label.setLayoutData(gd);
		return label;
	}
}
