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
package gov.redhawk.common.ui.internal.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

public class DvalSliderDialog extends Dialog {

	private Scale slider;

	private Shell shell;

	private Point size;

	private boolean isopen;

	private double maximum;

	private double minimum;

	private int digits;

	/**
	 * @param parent
	 * @param style
	 */
	public DvalSliderDialog(final Shell parent, final int style) {
		super(parent, style);
		this.size = new Point(SWT.DEFAULT, SWT.DEFAULT);
	}

	/**
	 * @param parent
	 */
	public DvalSliderDialog(final Shell parent) {
		this(parent, 0);
	}

	/**
	 * @return True if open
	 */
	public boolean isOpen() {
		return this.isopen;
	}

	/**
	 * @return SHell of the dialog
	 */
	public Shell getShell() {
		return this.shell;
	}

	/**
	 * @return Slider of the dialog
	 */
	public Scale getSlider() {
		return this.slider;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setSize(final int x, final int y) {
		this.size = new Point(x, y);
	}

	/**
	 * 
	 * @param value
	 */
	public void setSize(final Point value) {
		this.size = value;
		this.getShell().setSize(value);
	}

	public Point getSize() {
		return this.size;
	}

	/**
	 * 
	 * @param value
	 */
	public void setMaximum(final double value) {
		this.maximum = value;
		this.slider.setMaximum(Integer.MAX_VALUE);
	}

	/**
	 * 
	 * @param value
	 */
	public void setMinimum(final double value) {
		this.minimum = value;
		this.slider.setMinimum(0);
	}

	/**
	 * 
	 * @param value
	 */
	public void setIncrement(final double value) {
		final int int_inc = (int) ((value - this.minimum) / (this.maximum - this.minimum) * Integer.MAX_VALUE);
		this.slider.setIncrement(int_inc);

	}

	/**
	 * 
	 * @param value
	 */
	public void setPageIncrement(final double value) {
		final int int_inc = (int) ((value - this.minimum) / (this.maximum - this.minimum) * Integer.MAX_VALUE);
		this.slider.setPageIncrement(int_inc);
	}

	/**
	 * 
	 * @param value
	 */
	public void setDigits(final int value) {
		this.digits = value;
	}
	
	public int getDigits() {
		return digits;
	}

	/**
	 * 
	 * @param value
	 */
	public void setSelection(final double value) {
		final int int_val = (int) ((value - this.minimum) / (this.maximum - this.minimum) * Integer.MAX_VALUE);
		this.slider.setSelection(int_val);
	}

	/**
	 * 
	 * @return Current selection of the dialog
	 */
	public double getSelection() {
		final double result = this.slider.getSelection() / (double) (Integer.MAX_VALUE) * (this.maximum - this.minimum) + this.minimum;
		return result;
	}

	/**
	 * OPen the dialog
	 */
	public void open(final Point location, // SUPPRESS CHECKSTYLE Number of arguments 
	        final Point size,
	        final double value,
	        final int digits,
	        final double minimum,
	        final double maximum,
	        final double increment,
	        final double pageIncrement) {
		this.shell = new Shell(this.getParent(), SWT.TOOL);

		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginBottom = 0;
		layout.marginTop = 0;
		this.shell.setLayout(layout);

		this.slider = new Scale(this.shell, SWT.NONE);
		final GridData gd = new GridData(GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL | GridData.FILL_HORIZONTAL);
		gd.verticalIndent = 0;
		gd.horizontalIndent = 0;
		gd.horizontalSpan = 2;
		this.slider.setLayoutData(gd);
		this.setDigits(digits);
		this.setMinimum(minimum);
		this.setMaximum(maximum);
		this.setIncrement(increment);
		this.setPageIncrement(pageIncrement);
		this.setSelection(value);

		// slider.setThumb(1);

		this.shell.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(final FocusEvent e) {
				// Do nothing
			}

			@Override
			public void focusLost(final FocusEvent e) {
				DvalSliderDialog.this.focusLost(e);
			}

		});

		this.shell.pack();
		this.shell.setLocation(location);
		this.setSize(size);
		this.shell.open();

		this.isopen = true;
	}

	/**
	 * Close the dialog
	 */
	public void close() {
		if ((this.shell != null) && (!this.shell.isDisposed())) {
			this.shell.setVisible(false);
		}
		this.isopen = false;
	}

	protected void focusLost(final FocusEvent e) {
		if ((this.shell != null) && (!this.shell.isDisposed())) {
			this.shell.moveAbove(this.getParent());
		}
	}
}
