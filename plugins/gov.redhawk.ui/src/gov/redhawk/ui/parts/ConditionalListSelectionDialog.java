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
/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

// TODO: Auto-generated Javadoc
/**
 * The Class ConditionalListSelectionDialog.
 */
public class ConditionalListSelectionDialog extends ElementListSelectionDialog {

	private final String fButtonText;
	private Object[] fElements;
	private Object[] fConditionalElements;

	/**
	 * Instantiates a new conditional list selection dialog.
	 * 
	 * @param parent the parent
	 * @param renderer the renderer
	 * @param buttonText the button text
	 */
	public ConditionalListSelectionDialog(final Shell parent, final ILabelProvider renderer, final String buttonText) {
		super(parent, renderer);
		this.fButtonText = buttonText;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite comp = (Composite) super.createDialogArea(parent);
		int size = 0;
		if (this.fElements != null) {
			size += this.fElements.length;
		}
		if (this.fConditionalElements != null) {
			size += this.fConditionalElements.length;
		}
		final Object[] allElements = new Object[size];
		int conditionalStart = 0;
		if (this.fElements != null) {
			System.arraycopy(this.fElements, 0, allElements, 0, this.fElements.length);
			conditionalStart = this.fElements.length;
		}
		if (this.fConditionalElements != null) {
			System.arraycopy(this.fConditionalElements, 0, allElements, conditionalStart,
					this.fConditionalElements.length);
		}

		final Button button = new Button(comp, SWT.CHECK);
		Assert.isNotNull(this.fButtonText);
		button.setText(this.fButtonText);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (button.getSelection()) {
					setListElements(allElements);
				} else {
					setListElements(ConditionalListSelectionDialog.this.fElements);
				}
			}
		});
		return comp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setElements(final Object[] elements) {
		super.setElements(elements);
		this.fElements = elements;
	}

	/**
	 * Sets the conditional elements.
	 * 
	 * @param elements the new conditional elements
	 */
	public void setConditionalElements(final Object[] elements) {
		this.fConditionalElements = elements;
	}

}
