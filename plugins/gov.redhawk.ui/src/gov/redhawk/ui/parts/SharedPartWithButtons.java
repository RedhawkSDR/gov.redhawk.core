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
package gov.redhawk.ui.parts;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class SharedPartWithButtons.
 */
public abstract class SharedPartWithButtons extends SharedPart {

	// CHECKSTYLE:OFF Internal Class
	/** The button container. */
	protected Composite fButtonContainer;
	// CHECKSTYLE:ON

	private final String[] fButtonLabels;

	private Button[] fButtons;

	/**
	 * The Class SelectionHandler.
	 */
	private class SelectionHandler implements SelectionListener {

		/**
		 * {@inheritDoc}
		 */
		public void widgetSelected(final SelectionEvent e) {
			buttonSelected(e);
		}

		/**
		 * {@inheritDoc}
		 */
		public void widgetDefaultSelected(final SelectionEvent e) {
			buttonSelected(e);
		}

		/**
		 * Button selected.
		 * 
		 * @param e the e
		 */
		private void buttonSelected(final SelectionEvent e) {
			final Integer index = (Integer) e.widget.getData();
			SharedPartWithButtons.this.buttonSelected((Button) e.widget, index.intValue());
		}
	}

	/**
	 * Instantiates a new shared part with buttons.
	 * 
	 * @param buttonLabels the button labels
	 */
	public SharedPartWithButtons(final String[] buttonLabels) {
		this.fButtonLabels = buttonLabels;
	}

	/**
	 * Sets the button enabled.
	 * 
	 * @param index the index
	 * @param enabled the enabled
	 */
	public void setButtonEnabled(final int index, final boolean enabled) {
		if (this.fButtons != null && index >= 0 && this.fButtons.length > index) {
			this.fButtons[index].setEnabled(enabled);
		}
	}

	/**
	 * Set the specified button's visibility. Fix for defect 190717.
	 * 
	 * @param index The index of the button to be changed
	 * @param visible true if the button is to be shown, false if hidden
	 */
	public void setButtonVisible(final int index, final boolean visible) {
		if (this.fButtons != null && index >= 0 && this.fButtons.length > index) {
			this.fButtons[index].setVisible(visible);
		}
	}

	/**
	 * Creates the main control.
	 * 
	 * @param parent the parent
	 * @param style the style
	 * @param span the span
	 * @param toolkit the toolkit
	 */
	protected abstract void createMainControl(Composite parent, int style, int span, FormToolkit toolkit);

	/**
	 * Button selected.
	 * 
	 * @param button the button
	 * @param index the index
	 */
	protected abstract void buttonSelected(Button button, int index);

	/*
	 * @see SharedPart#createControl(Composite, FormWidgetFactory)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent, final int style, final int span, final FormToolkit toolkit) {
		createMainLabel(parent, span, toolkit);
		createMainControl(parent, style, span - 1, toolkit);
		createButtons(parent, toolkit);
	}

	/**
	 * Creates the buttons.
	 * 
	 * @param parent the parent
	 * @param toolkit the toolkit
	 */
	protected void createButtons(final Composite parent, final FormToolkit toolkit) {
		if (this.fButtonLabels != null && this.fButtonLabels.length > 0) {
			this.fButtonContainer = createComposite(parent, toolkit);
			final GridData gd = new GridData(GridData.FILL_VERTICAL);
			this.fButtonContainer.setLayoutData(gd);
			this.fButtonContainer.setLayout(createButtonsLayout());
			this.fButtons = new Button[this.fButtonLabels.length];
			final SelectionHandler listener = new SelectionHandler();
			for (int i = 0; i < this.fButtonLabels.length; i++) {
				final String label = this.fButtonLabels[i];
				if (label != null) {
					final Button button = createButton(this.fButtonContainer, label, i, toolkit);
					button.addSelectionListener(listener);
					this.fButtons[i] = button;
				} else {
					createEmptySpace(this.fButtonContainer, 1, toolkit);
				}
			}
		}
	}

	/**
	 * Creates the buttons layout.
	 * 
	 * @return the grid layout
	 */
	protected GridLayout createButtonsLayout() {
		final GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = layout.marginHeight;
		return layout;
	}

	/**
	 * Creates the button.
	 * 
	 * @param parent the parent
	 * @param label the label
	 * @param index the index
	 * @param toolkit the toolkit
	 * @return the button
	 */
	protected Button createButton(final Composite parent, final String label, final int index, final FormToolkit toolkit) {
		Button button;
		if (toolkit != null) {
			button = toolkit.createButton(parent, label, SWT.PUSH);
		} else {
			button = new Button(parent, SWT.PUSH);
			button.setText(label);
		}
		final GridData gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		button.setLayoutData(gd);
		button.setData(Integer.valueOf(index));
		return button;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void updateEnabledState() {
		for (int i = 0; i < this.fButtons.length; i++) {
			this.fButtons[i].setEnabled(isEnabled());
		}
	}

	/**
	 * Creates the main label.
	 * 
	 * @param parent the parent
	 * @param span the span
	 * @param toolkit the toolkit
	 */
	protected void createMainLabel(final Composite parent, final int span, final FormToolkit toolkit) {
	}

	/**
	 * Gets the button.
	 * 
	 * @param index the index
	 * @return the button
	 */
	public Button getButton(final int index) {
		//
		if ((this.fButtons == null) || (index < 0) || (index >= this.fButtons.length)) {
			return null;
		}
		//
		return this.fButtons[index];
	}
}
