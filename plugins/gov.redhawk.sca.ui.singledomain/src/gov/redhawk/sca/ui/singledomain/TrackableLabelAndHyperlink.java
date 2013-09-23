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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class TrackableLabelAndHyperlink extends TrackableLabel {

	private Hyperlink link;
	private Color background;

	public TrackableLabelAndHyperlink(Composite parent, String labelText, String linkText, Color background, int style, boolean button) {
		super(parent, labelText, style, button);
		this.link = new Hyperlink(this, SWT.NONE);
		this.background = background;
		link.setText(linkText);
		hideLink();
		addDisposeListener(new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent event) {
				if (link != null) {
					link.dispose();
				}
			}

		});
	}

	public void showLink() {
		link.setForeground(link.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		link.redraw();
		link.update();
		layout();
		resize();
	}
	
	public void addHyperlinkListener(IHyperlinkListener listener) {
		this.link.addHyperlinkListener(listener);
	}
	
	public void removeHyperlinkListener(IHyperlinkListener listener) {
		this.link.removeHyperlinkListener(listener);
	}

	public void hideLink() {
		this.link.setForeground(background);
		link.redraw();
		link.update();
		layout();
		resize();
	}
	
	public Hyperlink getLink() {
		return this.link;
	}

	@Override
	public void resize() {
		Point labelExtent = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		label.setBounds(1, 1, labelExtent.x, labelExtent.y);

		if (link != null) {
			Point linkExtent = link.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
			link.setBounds(1 + labelExtent.x + 5, 1, linkExtent.x, linkExtent.y);
		}
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		Point labelExtent = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		int width = labelExtent.x;
		int height = labelExtent.y;
		if (link != null) {
			Point linkExtent = link.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
			width = labelExtent.x + 7 + linkExtent.x;
		}
		if (wHint != SWT.DEFAULT) {
			width = wHint;
		}
		if (hHint != SWT.DEFAULT) {
			height = hHint;
		}
		//If you want the entire label to show, you have to set the composite width to be 14 points wider than it. Idon't know why.
		return new Point(width + 14, height);
	}

	@Override
	public void setFont(Font font) {
		if (link != null) {
			this.link.setFont(font);
		}
		super.setFont(font);
	}
	
	
	@Override
	public void setForeground(Color color) {
		if (link != null) {
			this.link.setForeground(color);
		}
	    super.setForeground(color);
	}
	
	
	@Override
	public void setBackground(Color color) {
		if (link != null) {
			this.link.setBackground(color);
		}
	    super.setBackground(color);
	}
	/*
	 * Override behavior needed for RCP, otherwise the "delete" hyperlink is hidden when user
	 * hovers over it before clicking. Override behavior not needed for RAP, and if included
	 * the widget is rendered as if it has no layout data.
	 */
	@Override
	public Rectangle getBounds() {
		if (SWT.getPlatform().startsWith("rap")) {
			return super.getBounds();
		}
		Point labelExtent = label.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
		int width = labelExtent.x;
		int height = labelExtent.y;
		if (link != null) {
			Point linkExtent = link.computeSize(SWT.DEFAULT, SWT.DEFAULT, false);
			width = labelExtent.x + 7 + linkExtent.x;
		}
		return new Rectangle(0, 0, width + 14, height);
	}

}
