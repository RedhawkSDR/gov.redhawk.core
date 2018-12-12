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
package gov.redhawk.common.ui.widgets;

import gov.redhawk.common.ui.internal.widgets.DvalSliderDialog;
import gov.redhawk.common.ui.internal.widgets.UpDown;

import java.text.DecimalFormat;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

/**
 * A control that simulates a control similar to the Midas Dval Control.
 */
public class Dval extends Composite {

	private static final int SLIDER_DIALOG_SIZE = 50;
	
	private static final double ONE_HALF = 0.5;
	
	private static final double DEFAULT_VALUE = 0.0;
	
	private static final double DEFAULT_MINIMUM = 0.0;
	
	private static final double DEFAULT_MAXIMUM = 10.0;
	
	private static final double DEFAULT_INCREMENT = 0.1;
	
	private static final double DEFAULT_PAGE_INCREMENT = 1.0;
	
	private static final int DEFAULT_NUM_DIGITS = 3;

	/** The arrow. */
	private final Button arrow;

	/** The layout. */
	private final GridLayout layout;

	/** The textbox. */
	private final Text textbox;

	/** The updown. */
	private final UpDown updown;

	/** The digits. */
	private int digits;

	/** The maximum. */
	private double maximum;

	/** The minimum. */
	private double minimum;

	/** The increment. */
	private double increment;

	/** The pageincrement. */
	private double pageincrement;

	/** The form. */
	private final DecimalFormat form;

	/** The current value. */
	private Double currentValue;

	/** The sliderdlg. */
	private final DvalSliderDialog sliderdlg;

	/** The modify listeners. */
	private ListenerList<Listener> modifyListeners;

	/** The default listeners. */
	private final ListenerList<Listener> defaultListeners = new ListenerList<>(ListenerList.IDENTITY);

	/** The mouse down listener. */
	private final Listener mouseDownListener = new Listener() {

		@Override
		public void handleEvent(final Event e) {
			Dval.this.arrowMouseDown(e);
		}

	};

	/** The verify listener. */
	private final VerifyListener verifyListener = new VerifyListener() {
		@Override
		public void verifyText(final VerifyEvent e) {
			Dval.this.verifyText(e);
		}
	};

	/** The key listener. */
	private final KeyListener keyListener = new KeyListener() {

		@Override
		public void keyPressed(final KeyEvent e) {
			Dval.this.keyPressed(e);
		}

		@Override
		public void keyReleased(final KeyEvent e) {
			// Do nothing

		}

	};

	/** The selection listener. */
	private final SelectionListener selectionListener = new SelectionListener() {

		@Override
		public void widgetDefaultSelected(final SelectionEvent e) {
			// Do nothing

		}

		@Override
		public void widgetSelected(final SelectionEvent e) {
			if (e.detail == SWT.ARROW_UP) {
				Dval.this.increment();
			} else {
				Dval.this.decrement();
			}
			//make textbox gain focus, so user can indicate value commit by removing focus
			getTextBox().setFocus();
		}

	};

	/** The slider selection. */
	private final Listener sliderSelection = new Listener() {
		@Override
		public void handleEvent(final Event e) {
			final double value = Dval.this.sliderdlg.getSelection();
			Dval.this.setValue(value);
			fireModifiedEvent(new Event());
		}
	};

	/** The shell listener. */
	private final Listener shellListener = new Listener() {
		@Override
		public void handleEvent(final Event e) {
			Dval.this.shellMoved(e);
		}
	};

	/** The control listener. */
	private final ControlListener controlListener = new ControlListener() {

		@Override
		public void controlMoved(final ControlEvent e) {
		}

		@Override
		public void controlResized(final ControlEvent e) {
			Dval.this.controlResized(e);
		}

	};

	/** The modify listener. */
	private final Listener modifyListener = new Listener() {

		@Override
		public void handleEvent(final Event e) {
			Dval.this.controlModified(e);
		}

	};

	/** The focus listener. */
	private final FocusListener focusListener = new FocusListener() {

		@Override
		public void focusGained(final FocusEvent e) {
			// Nothing to do
		}

		@Override
		public void focusLost(final FocusEvent e) {
			// When focus is lost, if the value in the text-box is invalid
			// (i.e. it's greater than max or less then min) then make sure it
			// is valid	 
			final String widText = Dval.this.textbox.getText();
			try {
				Dval.this.setValue(Double.parseDouble(widText));
			} catch (final NumberFormatException excep) {
				// PASS
			}
		}

	};

	private final int precision;

	private Listener deferredListener;

	private Integer deferredListenerEventType;

	/**
	 * The Constructor.
	 * 
	 * @param composite Parent
	 */
	public Dval(final Composite composite) {
		this(composite, SWT.None);
	}

	/**
	 * The Constructor.
	 * 
	 * @param composite Parent
	 */
	public Dval(final Composite composite, final int style) {
		super(composite, style);
		this.getParent().addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(final DisposeEvent e) {
				dispose();
			}

		});

		/* Create the base widgets */
		final int NUM_COLUMNS = 3;
		this.layout = new GridLayout(NUM_COLUMNS, false);
		this.setLayout(this.layout);
		final GridDataFactory factory = GridDataFactory.fillDefaults().grab(true, true);
		this.textbox = new Text(this, SWT.RIGHT | SWT.BORDER | SWT.SINGLE);
		if (this.deferredListener != null && this.deferredListenerEventType != null) {
			this.textbox.addListener(this.deferredListenerEventType, this.deferredListener);
			this.deferredListener = null;
			this.deferredListenerEventType = null;
		}
		factory.applyTo(this.textbox);

		this.updown = new UpDown(this, 0);

		this.arrow = new Button(this, SWT.ARROW | SWT.DOWN | SWT.BORDER);

		this.form = new DecimalFormat();
		this.form.setGroupingUsed(false);
		this.sliderdlg = new DvalSliderDialog(this.getShell());
		this.precision = Integer.MIN_VALUE;

		/* Set default range */
		setValue(DEFAULT_VALUE);
		setMinimum(DEFAULT_MINIMUM);
		setMaximum(DEFAULT_MAXIMUM);
		setIncrement(DEFAULT_INCREMENT);
		setPageIncrement(DEFAULT_PAGE_INCREMENT);
		setDigits(DEFAULT_NUM_DIGITS);

		/* Handle Events */
		this.arrow.addListener(SWT.MouseDown, this.mouseDownListener);

		this.textbox.addVerifyListener(this.verifyListener);

		this.textbox.addKeyListener(this.keyListener);

		this.textbox.addListener(SWT.Modify, this.modifyListener);

		this.textbox.addFocusListener(this.focusListener);

		this.updown.addSelectionListener(this.selectionListener);

		getShell().addListener(SWT.Move, this.shellListener);

		addControlListener(this.controlListener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		this.arrow.removeListener(SWT.MouseDown, this.mouseDownListener);

		this.textbox.removeVerifyListener(this.verifyListener);

		this.textbox.removeKeyListener(this.keyListener);

		this.textbox.removeListener(SWT.Modify, this.modifyListener);

		this.textbox.removeFocusListener(this.focusListener);

		this.updown.removeSelectionListener(this.selectionListener);

		if (this.sliderdlg != null) {
			this.sliderdlg.close();
		}

		getShell().removeListener(SWT.Move, this.shellListener);

		removeControlListener(this.controlListener);
		super.dispose();
	}

	/**
	 * Arrow mouse down.
	 * 
	 * @param e the e
	 */
	protected void arrowMouseDown(final Event e) {
		if (this.sliderdlg != null) {
			if (!this.sliderdlg.isOpen()) {
				showSlider();
			} else {
				hideSlider();
				//make textbox gain focus, so user can indicate value commit by removing focus
				getTextBox().setFocus();
			}
		}
	}

	/**
	 * Increment.
	 */
	private void increment() {

		setValue(this.currentValue + this.increment);
		fireDefaultSelection();
	}

	/**
	 * Pageincrement.
	 */
	private void pageincrement() {
		setValue(this.currentValue + this.pageincrement);
		fireDefaultSelection();
	}

	/**
	 * Decrement.
	 */
	private void decrement() {
		setValue(this.currentValue - this.increment);
		fireDefaultSelection();
	}

	/**
	 * Pagedecrement.
	 */
	private void pagedecrement() {
		setValue(this.currentValue - this.pageincrement);
		fireDefaultSelection();
	}

	/**
	 * Checks if is text valid.
	 * 
	 * @return true, if is text valid
	 */
	protected boolean isTextValid() {
		try {
			final double value = Double.parseDouble(this.textbox.getText());
			return !((value > this.maximum) || (value < this.minimum));
		} catch (final NumberFormatException e) {
			// The verify input should prevent us from getting actual invalid
			// entries, an exception here implies that either the box is blank
			// or has started with a negative sign.
			return false;
		}
	}

	/**
	 * Apply a formating to the text within the dval.
	 * 
	 * @param pattern the pattern
	 */
	public void applyFormat(final String pattern) {
		this.form.applyPattern(pattern);
	}

	/**
	 * Sets the digits.
	 * 
	 * @param value Number of digits to display
	 */
	public void setDigits(final int value) {
		this.digits = value;
		this.form.setMaximumFractionDigits(this.digits);
		this.form.setMinimumFractionDigits(this.digits);
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setDigits(value);
		}
	}

	/**
	 * Sets the increment.
	 * 
	 * @param value Value to increment by
	 */
	public void setIncrement(final double value) {
		this.increment = Math.floor(value * this.precision + ONE_HALF) / this.precision;
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setIncrement(value);
		}
	}

	/**
	 * Sets the page increment.
	 * 
	 * @param value Value to large increment by
	 */
	public void setPageIncrement(final double value) {
		this.pageincrement = Math.floor(value * this.precision + ONE_HALF) / this.precision;
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setPageIncrement(value);
		}
	}

	/**
	 * Sets the maximum.
	 * 
	 * @param value Maximum value
	 */
	public void setMaximum(final double value) {
		this.maximum = Math.floor(value * this.precision + ONE_HALF) / this.precision;
		// Reset the value just in case it is now out of range
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setMaximum(value);
		}
		setValue(this.currentValue);
	}

	/**
	 * Sets the minimum.
	 * 
	 * @param value Minimum value
	 */
	public void setMinimum(final double value) {
		this.minimum = Math.floor(value * this.precision + ONE_HALF) / this.precision;
		// Reset the value just in case it is now out of range
		this.setValue(this.getValue());
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setMinimum(value);
		}
		setValue(this.currentValue);
	}

	/**
	 * Sets the value.
	 * 
	 * @param value Set the current value
	 */
	public void setValue(final Double value) {
		if (value == null) {
			throw new NullPointerException("Dval Value can not be null");
		}
		this.currentValue = Math.floor(value * this.precision + ONE_HALF) / this.precision;
		if (this.currentValue >= this.maximum) {
			this.updown.getUpButton().setEnabled(false);
			this.updown.getDownButton().setEnabled(true);
			this.currentValue = this.maximum;
		} else if (this.currentValue <= this.minimum) {
			this.updown.getUpButton().setEnabled(true);
			this.updown.getDownButton().setEnabled(false);
			this.currentValue = this.minimum;
		} else {
			this.updown.getUpButton().setEnabled(true);
			this.updown.getDownButton().setEnabled(true);
		}
		if (!this.textbox.isDisposed()) {
			this.textbox.setText(this.form.format(this.currentValue));
		}
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setSelection(this.currentValue);
		}
	}

	/**
	 * Gets the digits.
	 */
	public int getDigits() {
		return this.digits;
	}

	/**
	 * Gets the increment.
	 */
	public double getIncrement() {
		return this.increment;
	}

	/**
	 * Sets the page increment.
	 */
	public double getPageIncrement() {
		return this.pageincrement;
	}

	/**
	 * Gets the maximum.
	 * 
	 * @return Maximum value
	 */
	public double getMaximum() {
		return this.maximum;
	}

	/**
	 * Gets the minimum.
	 * 
	 * @return Minimum value
	 */
	public double getMinimum() {
		return this.minimum;
	}

	/**
	 * Gets the value.
	 * 
	 * @return Current Value
	 */
	public Double getValue() {
		return this.currentValue;
	}

	/**
	 * Fire default selection.
	 */
	private void fireDefaultSelection() {
		final Event e = new Event();
		e.type = SWT.DefaultSelection;
		e.doit = true;
		e.widget = this.textbox;
		e.display = e.widget.getDisplay();
		for (Listener listener : this.defaultListeners) {
			listener.handleEvent(e);
		}
	}

	@Override
	public void addListener(final int eventType, final Listener listener) {
		if (eventType == SWT.Modify) {
			if (this.modifyListeners == null) {
				this.modifyListeners = new ListenerList<>(ListenerList.IDENTITY);
			}
			this.modifyListeners.add(listener);
		} else {
			if (eventType == SWT.DefaultSelection) {
				this.defaultListeners.add(listener);
			}
			if (this.textbox != null) {
				this.textbox.addListener(eventType, listener);
			} else {
				this.deferredListener = listener;
				this.deferredListenerEventType = eventType;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeListener(final int eventType, final Listener handler) {
		if (eventType == SWT.Modify) {
			if (this.modifyListeners == null) {
				return;
			}
			this.modifyListeners.remove(handler);
		} else {
			this.textbox.removeListener(eventType, handler);
		}
	}

	/* Listener interface */
	/**
	 * Verify text.
	 * 
	 * @param e the e
	 */
	protected void verifyText(final VerifyEvent e) {
		final String string = e.text;

		final char[] chars = new char[string.length()];
		string.getChars(0, chars.length, chars, 0);

		for (int i = 0; i < chars.length; i++) {
			if (!(('.' == chars[i]) || ('-' == chars[i]) || ('0' <= chars[i] && chars[i] <= '9') || ('e' == chars[i]) || ('E' == chars[i]))) {
				e.doit = false;
				this.setValue(this.currentValue);
				return;
			}
		}

		/*
		 * if (string.contains(".") && text.contains(".")) { e.doit = false;
		 * return; }
		 */
	}

	/**
	 * Key pressed.
	 * 
	 * @param e the e
	 */
	protected void keyPressed(final KeyEvent e) {
		if (e.keyCode == SWT.ARROW_UP) {
			increment();
		} else if (e.keyCode == SWT.ARROW_DOWN) {
			decrement();
		} else if (e.keyCode == SWT.PAGE_UP) {
			pageincrement();
		} else if (e.keyCode == SWT.PAGE_DOWN) {
			pagedecrement();
		}
	}

	/**
	 * Sets the text background.
	 * 
	 * @param color the new text background
	 */
	public void setTextBackground(final Color color) {
		this.textbox.setBackground(color);
	}

	/**
	 * Control resized.
	 * 
	 * @param e the e
	 */
	protected void controlResized(final ControlEvent e) {
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.sliderdlg.setSize(this.getSize().x, Dval.SLIDER_DIALOG_SIZE);
		}
	}

	/**
	 * Shell moved.
	 * 
	 * @param e the e
	 */
	protected void shellMoved(final Event e) {
		if (this.isDisposed()) {
			return;
		}

		if ((this.sliderdlg != null) && (this.sliderdlg.isOpen())) {
			final Point pos = this.getDisplay().map(this, null, this.textbox.getLocation());
			pos.y += this.textbox.getSize().y;
			this.sliderdlg.getShell().setLocation(pos.x, pos.y);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(final boolean enabled) {
		super.setEnabled(enabled);
		this.textbox.setEnabled(enabled);
		this.updown.setEnabled(enabled);
		this.arrow.setEnabled(enabled);
		if (!enabled) {
			hideSlider();
		}
	}

	/**
	 * Control modified.
	 * 
	 * @param e the e
	 */
	protected void controlModified(final Event e) {
		final String widText = this.textbox.getText();
		try {
			this.currentValue = Double.parseDouble(widText);
			fireModifiedEvent(e);
		} catch (final NumberFormatException excep) {
			// PASS
		}
	}

	/**
	 * Fire modified event.
	 * 
	 * @param e the e
	 */
	protected void fireModifiedEvent(final Event e) {
		e.item = this;
		e.type = SWT.Modify;
		if (this.modifyListeners != null) {
			for (final Listener listener : this.modifyListeners) {
				listener.handleEvent(e);
			}
		}
	}

	/**
	 * Show slider.
	 * @since 1.1
	 */
	public void showSlider() {
		checkWidget();
		if (this.sliderdlg != null && !this.sliderdlg.isOpen()) {
			this.arrow.setAlignment(SWT.UP);
			final Point pos = this.getDisplay().map(this, null, this.textbox.getLocation());
			pos.y += this.textbox.getSize().y;
			final Point size = this.getSize();
			size.y = Dval.SLIDER_DIALOG_SIZE;
			this.sliderdlg.open(pos,
			        size,
			        this.getValue(),
			        this.getDigits(),
			        this.getMinimum(),
			        this.getMaximum(),
			        this.getIncrement(),
			        this.getPageIncrement());
			this.sliderdlg.getSlider().addListener(SWT.Selection, this.sliderSelection);
			this.sliderdlg.getSlider().addDisposeListener(new DisposeListener() {

				@Override
				public void widgetDisposed(final DisposeEvent e) {
					if (Dval.this.sliderdlg != null && Dval.this.sliderdlg.getSlider() != null && !Dval.this.sliderdlg.getSlider().isDisposed()) {
						Dval.this.sliderdlg.getSlider().removeListener(SWT.Selection, Dval.this.sliderSelection);
					}
				}

			});
		}
	}

	/**
	 * Hide slider.
	 * @since 1.1
	 */
	public void hideSlider() {
		checkWidget();
		if (this.sliderdlg != null && this.sliderdlg.isOpen()) {
			this.arrow.setAlignment(SWT.DOWN);
			this.sliderdlg.close();
		}
	}

	/**
	 * @since 2.0
	 */
	@Override
	public void setVisible(final boolean visible) {
		if (!visible) {
			hideSlider();
		}
		super.setVisible(visible);
	}

	public Control getTextBox() {
		return this.textbox;
	}

	public Composite getUpdown() {
		return this.updown;
	}

	public Button getArrow() {
		return this.arrow;
	}

	public void setParseIntegerOnly(final boolean parseIntegerOnly) {
		this.form.setParseIntegerOnly(parseIntegerOnly);
	}
}
