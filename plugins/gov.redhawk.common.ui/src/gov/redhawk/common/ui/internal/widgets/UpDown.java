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

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * Not to be instantiated by clients
 * 
 */
public class UpDown extends Composite {
	private final Button up, down;

	private final Vector<SelectionListener> selectionListeners = new Vector<SelectionListener>();

	/**
	 * @param parent
	 * @param style
	 */
	public UpDown(final Composite parent, final int style) {
		super(parent, style);
		this.up = new Button(this, SWT.ARROW | SWT.UP | SWT.BORDER);
		this.down = new Button(this, SWT.ARROW | SWT.DOWN | SWT.BORDER);

		addListener(SWT.Resize, new Listener() {
			public void handleEvent(final Event e) {
				resize();
			}
		});

		this.up.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(final SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(final SelectionEvent e) {
				UpDown.this.widgetSelected(e);
			}

		});

		this.down.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(final SelectionEvent e) {
				// TODO Auto-generated method stub

			}

			public void widgetSelected(final SelectionEvent e) {
				UpDown.this.widgetSelected(e);
			}

		});
	}

	private void sendSelectionEvent(final SelectionEvent e) {
		final Event te = new Event();
		te.widget = this;
		final SelectionEvent se = new SelectionEvent(te);
		if (e.widget == this.up) {
			se.detail = SWT.ARROW_UP;
		} else {
			se.detail = SWT.ARROW_DOWN;
		}
		se.widget = this;
		final int size = this.selectionListeners.size();
		for (int i = 0; i < size; i++) {
			final SelectionListener listener = this.selectionListeners.elementAt(i);
			listener.widgetSelected(se);
		}
	}

	/**
	 * @return Button
	 */
	public Button getUpButton() {
		return this.up;
	}

	/**
	 * @return Button
	 */
	public Button getDownButton() {
		return this.down;
	}

	/**
	 * @param listener
	 */
	public void addSelectionListener(final SelectionListener listener) {
		this.selectionListeners.addElement(listener);
	}

	/**
	 * @param listener
	 */
	public void removeSelectionListener(final SelectionListener listener) {
		this.selectionListeners.removeElement(listener);
	}

	protected void widgetSelected(final SelectionEvent e) {
		sendSelectionEvent(e);
	}

	@Override
	public void setEnabled(final boolean enabled) {
		this.up.setEnabled(enabled);
		this.down.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	/**
	 * 
	 */
	public void resize() {
		final int buttonHeight = this.getSize().y / 2;
		this.up.setBounds(0, 0, this.getSize().x, buttonHeight);
		this.down.setBounds(0, buttonHeight, this.getSize().x, buttonHeight);
	}

	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
		final int width = 20, height = 20;
		return new Point(width + 2, height + 2);
	}
}
