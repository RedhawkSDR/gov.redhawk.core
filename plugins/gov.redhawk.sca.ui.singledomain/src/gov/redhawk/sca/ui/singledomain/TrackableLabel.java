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
package gov.redhawk.sca.ui.singledomain;


import org.eclipse.core.runtime.ListenerList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class TrackableLabel extends Composite {

	/*
	 * Used only for RAP, since we need to call each custom listener when
	 * the JS client sends a request based on a MouseTrack event.
	 * For RCP, we add regular SWT Mouse Track listeners (external to this class),
	 * which respond to local mouse events.
	 */
	private ListenerList<CustomMouseTrackListener> listeners = new ListenerList<CustomMouseTrackListener>();

	private String mouseEnter;
	private String mouseExit;
	protected Control label; // SUPPRESS CHECKSTYLE Event Object

	private boolean button;

	public TrackableLabel(final Composite parent, String text, int style, boolean button) {
		super(parent, SWT.NONE);
		this.button = button;
		if (button) {
			this.label = new Button(this, style);
			((Button) label).setText(text);
		} else {
			this.label = new Label(this, style);
			((Label) label).setText(text);
		}
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent event) {
				if (label != null) {
					label.dispose();
				}
			}

		});
		addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				TrackableLabel.this.controlResized(e);
			}
		});
		resize();
	}



	protected void controlResized(ControlEvent e) {
		resize();
	}

	public void resize() {
		resize(false);
	}

	public void resize(boolean changed) {
		Point lExtent = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, changed);
		label.setBounds(1, 1, lExtent.x, lExtent.y);
		this.layout();
		this.setSize(this.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	@Override
	public Point getSize() {
		//resize();
		return computeSize(SWT.DEFAULT, SWT.DEFAULT);
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		Point lExtent = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		int width = lExtent.x + 4;
		int height = lExtent.y + 4;
		if (wHint != SWT.DEFAULT) {
			width = wHint;
		}
		if (hHint != SWT.DEFAULT) {
			height = hHint;
		}
		//If you want the entire label to show, you have to set the composite width to be at least 14 points wider than it. I don't know why.
		return new Point(width, height);
	}

	public Control getLabel() {
		resize();
		return label;
	}

	public void addMouseTrackListener(CustomMouseTrackListener listener) {
		this.listeners.add(listener);
	}

	public void removeMouseTrackListener(CustomMouseTrackListener listener) {
		this.listeners.remove(listener);
	}

	@Override
	public void setFont(Font font) {
		this.label.setFont(font);
	}

	@Override
	public Font getFont() {
		return this.label.getFont();
	}

	@Override
	public void setForeground(Color color) {
		this.label.setForeground(color);
	}

	@Override
	public Color getForeground() {
		return this.label.getForeground();
	}

	@Override
	public void setBackground(Color color) {
		this.label.setBackground(color);
		super.setBackground(color);
	}

	@Override
	public Color getBackground() {
		return this.label.getBackground();
	}


	@Override
	public void setLayoutData(Object layoutData) {
		this.label.setLayoutData(layoutData);
	}

	@Override
	public Object getLayoutData() {
		return this.label.getLayoutData();
	}

	public void mouseEnter(Point location) {
		for (Object listener : listeners.getListeners()) {
			CustomMouseTrackListener mtl = (CustomMouseTrackListener) listener;
			CustomMouseEvent event = new CustomMouseEvent(this, location);
			mtl.mouseEnter(event);
		}
	}

	public void mouseExit(Point location) {
		for (Object listener : listeners.getListeners()) {
			CustomMouseTrackListener mtl = (CustomMouseTrackListener) listener;
			CustomMouseEvent event = new CustomMouseEvent(this, location);
			mtl.mouseExit(event);
		}
	}

	/*
	 * Invoked by Javascript implementation of custom widget on client side
	 * when mouse enters widget area
	 */
	public void setMouseEnter(String mouseEnter) {
		if (mouseEnter != null && !"".equals(mouseEnter)) {
			String[] xy = mouseEnter.split(":");
			Integer x;
			Integer y;
			try {
				x = new Integer(xy[0]);
				y = new Integer(xy[1]);
			} catch (NumberFormatException e) {
				return;
			}
			mouseEnter(new Point(x, y));
		}
	}

	/*
	 * Invoked by Javascript implementation of custom widget on client side
	 * when mouse exits widget area
	 */
	public void setMouseExit(String mouseExit) {
		if (mouseExit != null && !"".equals(mouseExit)) {
			String[] xy = mouseExit.split(":");
			Integer x;
			Integer y;
			try {
				x = new Integer(xy[0]);
				y = new Integer(xy[1]);
			} catch (NumberFormatException e) {
				return;
			}
			mouseExit(new Point(x, y));
			/*
			 * Previously needed because of extraneous exit events passed from client.
			 * Seems to be fixed; if so, remove this code
			 */
			//			if (!getBounds().contains(getParent().toControl(getDisplay().getCursorLocation()))*/) {
			//				mouseExit(new Point(x, y));
			//			}
		}
	}

	@Override
	public void addMouseListener(MouseListener listener) {
		this.label.addMouseListener(listener);
	}

	@Override
	public void removeMouseListener(MouseListener listener) {
		this.label.removeMouseListener(listener);
	}

	public String getMouseEnter() {
		return mouseEnter;
	}

	public String getMouseExit() {
		return mouseExit;
	}


	public String getLabelText() {
		if (this.button) {
			return ((Button) this.label).getText();
		} else {
			return ((Label) this.label).getText();
		}
	}

	public void setLabelText(String text) {
		if (this.button) {
			((Button) this.label).setText(text);
		} else {
			((Label) this.label).setText(text);
		}
	}
}
