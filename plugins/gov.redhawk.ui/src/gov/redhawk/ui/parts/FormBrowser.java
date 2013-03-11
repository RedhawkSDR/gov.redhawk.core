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
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package gov.redhawk.ui.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledFormText;

/**
 * The Class FormBrowser.
 */
public class FormBrowser {
	/** The toolkit. */
	private FormToolkit toolkit;

	/** The container. */
	private Composite container;

	/** The form text. */
	private ScrolledFormText formText;

	/** The text. */
	private String text;

	/**
	 * Instantiates a new form browser.
	 * 
	 * @param style the style
	 */
	public FormBrowser(final int style) {
	}

	/**
	 * Creates the control.
	 * 
	 * @param parent the parent
	 */
	public void createControl(final Composite parent) {
		this.toolkit = new FormToolkit(parent.getDisplay());
		final int borderStyle = (this.toolkit.getBorderStyle() == SWT.BORDER) ? SWT.NULL : SWT.BORDER; // SUPPRESS CHECKSTYLE AvoidInLine
		this.container = new Composite(parent, borderStyle);
		final FillLayout flayout = new FillLayout();
		flayout.marginWidth = 1;
		flayout.marginHeight = 1;
		this.container.setLayout(flayout);
		this.formText = new ScrolledFormText(this.container, SWT.V_SCROLL | SWT.H_SCROLL, false);
		if (borderStyle == SWT.NULL) {
			this.formText.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TREE_BORDER);
			this.toolkit.paintBordersFor(this.container);
		}
		final FormText ftext = this.toolkit.createFormText(this.formText, false);
		this.formText.setFormText(ftext);
		this.formText.setExpandHorizontal(true);
		this.formText.setExpandVertical(true);
		this.formText.setBackground(this.toolkit.getColors().getBackground());
		this.formText.setForeground(this.toolkit.getColors().getForeground());
		ftext.marginWidth = 2;
		ftext.marginHeight = 2;
		ftext.setHyperlinkSettings(this.toolkit.getHyperlinkGroup());
		this.formText.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(final DisposeEvent e) {
				if (FormBrowser.this.toolkit != null) {
					FormBrowser.this.toolkit.dispose();
					FormBrowser.this.toolkit = null;
				}
			}
		});
		if (this.text != null) {
			this.formText.setText(this.text);
		}
	}

	/**
	 * Gets the control.
	 * 
	 * @return the control
	 */
	public Control getControl() {
		return this.container;
	}

	/**
	 * Sets the text.
	 * 
	 * @param text the new text
	 */
	public void setText(final String text) {
		this.text = text;
		if (this.formText != null) {
			this.formText.setText(text);
		}
	}
}
