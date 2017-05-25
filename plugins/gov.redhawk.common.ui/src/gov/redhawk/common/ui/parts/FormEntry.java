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
package gov.redhawk.common.ui.parts;

import gov.redhawk.common.ui.editor.FormLayoutFactory;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * The helper class for creating entry fields with label and text. Optionally, a
 * button can be added after the text. The attached listener reacts to all the
 * events. Entering new text makes the entry 'dirty', but only when 'commit' is
 * called is 'valueChanged' method called (and only if 'dirty' flag is set).
 * This allows delayed commit.
 * @since 3.0
 */
public class FormEntry {

	/** The Constant F_DEFAULT_TEXT_WIDTH_HINT. */
	public static final int F_DEFAULT_TEXT_WIDTH_HINT = 100;
	private Control fLabel;
	private Text fText;
	private Button fBrowse;

	private IFormEntryListener fListener;

	/**
	 * The default constructor. Call 'createControl' to make it.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param labelText the label text
	 * @param style the style
	 */
	public FormEntry(final Composite parent, final FormToolkit toolkit, final String labelText, final int style) {
		createControl(parent, toolkit, labelText, style, null, false, 0, 0);
	}

	/**
	 * This constructor create all the controls right away.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param labelText the label text
	 * @param browseText the browse text
	 * @param linkLabel the link label
	 */
	public FormEntry(final Composite parent, final FormToolkit toolkit, final String labelText, final String browseText, final boolean linkLabel) {
		this(parent, toolkit, labelText, browseText, linkLabel, 0);
	}

	/**
	 * Instantiates a new form entry.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param labelText the label text
	 * @param browseText the browse text
	 * @param linkLabel the link label
	 * @param indent the indent
	 */
	public FormEntry(final Composite parent,
	        final FormToolkit toolkit,
	        final String labelText,
	        final String browseText,
	        final boolean linkLabel,
	        final int indent) {
		createControl(parent, toolkit, labelText, SWT.SINGLE, browseText, linkLabel, indent, 0);
	}

	/**
	 * Instantiates a new form entry.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param labelText the label text
	 * @param indent the indent
	 * @param tcolspan the tcolspan
	 */
	public FormEntry(final Composite parent, final FormToolkit toolkit, final String labelText, final int indent, final int tcolspan) {
		createControl(parent, toolkit, labelText, SWT.SINGLE, null, false, indent, tcolspan);
	}

	/**
	 * @param implementationComposite
	 * @param toolkit
	 * @param string
	 * @param i
	 * @param string2
	 * @param b
	 */
	public FormEntry(final Composite parent, final FormToolkit toolkit, final String label, final int style, final String browse, final boolean linkLabel) {
		createControl(parent, toolkit, label, style, browse, linkLabel, 0, 0);
	}

	/**
	 * Create all the controls in the provided parent.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 * @param labelText the label text
	 * @param browseText the browse text
	 * @param linkLabel the link label
	 * @param style the style
	 * @param indent the indent
	 * @param tcolspan the tcolspan
	 */
	private void createControl(final Composite parent, final FormToolkit toolkit, final String labelText, final int style, final String browseText, // SUPPRESS CHECKSTYLE Parameters
	        final boolean linkLabel,
	        final int indent,
	        final int tcolspan) {
		if (linkLabel) {
			final Hyperlink link = toolkit.createHyperlink(parent, labelText, SWT.NULL);
			this.fLabel = link;
		} else {
			if (labelText != null) {
				this.fLabel = toolkit.createLabel(parent, labelText);
				this.fLabel.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
			}
		}
		this.fText = toolkit.createText(parent, "", style); //$NON-NLS-1$

		if (browseText != null) {
			this.fBrowse = toolkit.createButton(parent, browseText, SWT.PUSH);
			this.fBrowse.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					if (FormEntry.this.fListener != null) {
						FormEntry.this.fListener.buttonSelected(FormEntry.this);
					}
				}
			});
		}
		fillIntoGrid(parent, indent, tcolspan);
		// Set the default text width hint and let clients modify accordingly
		// after the fact
		setTextWidthHint(FormEntry.F_DEFAULT_TEXT_WIDTH_HINT);
	}

	/**
	 * Sets the editable.
	 * 
	 * @param editable the new editable
	 */
	public void setEditable(final boolean editable) {
		this.fText.setEditable(editable);
		this.fLabel.setEnabled(editable);
		if (this.fLabel instanceof Hyperlink) {
			((Hyperlink) this.fLabel).setUnderlined(editable);
		}

		if (this.fBrowse != null) {
			this.fBrowse.setEnabled(editable);
		}
	}

	/**
	 * Fill into grid.
	 * 
	 * @param parent the parent
	 * @param indent the indent
	 * @param tcolspan the tcolspan
	 */
	private void fillIntoGrid(final Composite parent, final int indent, final int tcolspan) {
		final Layout layout = parent.getLayout();
		int tspan;
		if (layout instanceof GridLayout) {
			final int span = ((GridLayout) layout).numColumns;
			if (tcolspan > 0) {
				tspan = tcolspan;
			} else {
				if (this.fBrowse != null) {
					tspan = span - 2;
				} else {
					tspan = span - 1;
				}
			}
			GridData gd;
			if (this.fLabel != null) {
				gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
				gd.horizontalIndent = indent;
				this.fLabel.setLayoutData(gd);
			}
			gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			gd.horizontalSpan = tspan;
			if (this.fLabel != null) {
				gd.horizontalIndent = FormLayoutFactory.CONTROL_HORIZONTAL_INDENT;
			}
			// gd.grabExcessHorizontalSpace = (tspan == 1);
			gd.grabExcessHorizontalSpace = true;
			gd.widthHint = 10; // SUPPRESS CHECKSTYLE MagicNumber
			this.fText.setLayoutData(gd);
			if (this.fBrowse != null) {
				gd = new GridData(GridData.VERTICAL_ALIGN_CENTER);
				this.fBrowse.setLayoutData(gd);
			}
		} else if (layout instanceof TableWrapLayout) {
			final int span = ((TableWrapLayout) layout).numColumns;
			if (tcolspan > 0) {
				tspan = tcolspan;
			} else {
				if (this.fBrowse != null) {
					tspan = span - 2;
				} else {
					tspan = span - 1;
				}
			}
			TableWrapData td;
			if (this.fLabel != null) {
				td = new TableWrapData();
				td.valign = TableWrapData.MIDDLE;
				td.indent = indent;
				this.fLabel.setLayoutData(td);
			}
			td = new TableWrapData(TableWrapData.FILL);
			td.colspan = tspan;
			if (this.fLabel != null) {
				td.indent = FormLayoutFactory.CONTROL_HORIZONTAL_INDENT;
			}
			td.grabHorizontal = (tspan == 1);
			td.valign = TableWrapData.MIDDLE;
			this.fText.setLayoutData(td);
			if (this.fBrowse != null) {
				td = new TableWrapData(TableWrapData.FILL);
				td.valign = TableWrapData.MIDDLE;
				this.fBrowse.setLayoutData(td);
			}
		}
	}

	/**
	 * Attaches the listener for the entry.
	 * 
	 * @param listener the listener
	 */
	public void setFormEntryListener(final IFormEntryListener listener) {
		if (this.fLabel != null && this.fLabel instanceof Hyperlink) {
			if (this.fListener != null) {
				((Hyperlink) this.fLabel).removeHyperlinkListener(this.fListener);
			}
			if (listener != null) {
				((Hyperlink) this.fLabel).addHyperlinkListener(listener);
			}
		}
		this.fListener = listener;
	}
	
	/**
     * @since 3.1
     */
	public void setLabel(String label) {
		if (this.fLabel instanceof Label) {
			((Label) this.fLabel).setText(label);
		} else if (this.fLabel instanceof Hyperlink) {
			((Hyperlink) this.fLabel).setText(label);
		}
	}

	/**
	 * @since 3.2
	 * @param tooltip
	 */
	public void setTooltip(String tooltip) {
		if (this.fLabel != null) {
			this.fLabel.setToolTipText(tooltip);
		}
		this.fText.setToolTipText(tooltip);
	}

	/**
	 * Returns the text control.
	 * 
	 * @return the text
	 */
	public Text getText() {
		return this.fText;
	}

	/**
	 * Gets the label.
	 * 
	 * @return the label
	 */
	public Control getLabel() {
		return this.fLabel;
	}

	/**
	 * Returns the browse button control.
	 * 
	 * @return the button
	 */
	public Button getButton() {
		return this.fBrowse;
	}

	/**
	 * Sets the visible.
	 * 
	 * @param visible the new visible
	 */
	public void setVisible(final boolean visible) {
		if (this.fLabel != null) {
			this.fLabel.setVisible(visible);
		}
		if (this.fText != null) {
			this.fText.setVisible(visible);
		}
		if (this.fBrowse != null) {
			this.fBrowse.setVisible(visible);
		}
	}

	/**
	 * If GridData was used, set the width hint. If TableWrapData was used set
	 * the max width. If no layout data was specified, this method does nothing.
	 * 
	 * @param width the width
	 */
	public void setTextWidthHint(final int width) {
		final Object data = getText().getLayoutData();
		if (data == null) {
			return;
		} else if (data instanceof GridData) {
			((GridData) data).widthHint = width;
		} else if (data instanceof TableWrapData) {
			((TableWrapData) data).maxWidth = width;
		}
	}

	/**
	 * @param literal
	 * @param b
	 */
	public void setValue(String str) {
		if (str == null) {
			str = ""; //$NON-NLS-1$
		}
		this.fText.setText(str);
	}
}
