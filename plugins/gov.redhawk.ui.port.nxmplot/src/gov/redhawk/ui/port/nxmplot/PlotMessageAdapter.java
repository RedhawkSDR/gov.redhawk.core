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
package gov.redhawk.ui.port.nxmplot;

import nxm.sys.inc.Commandable;
import nxm.sys.inc.MessageHandler;
import nxm.sys.lib.Message;
import nxm.sys.lib.Position;
import nxm.sys.lib.Table;
import nxm.sys.libg.DragBox;

/**
 * Converts known PLOT messages received via {@link MessageHandler#processMessage(Message)} to IPlotWidgetListener callbacks.
 * @since 3.0
 */
public class PlotMessageAdapter implements MessageHandler {

	private IPlotWidgetListener listener;

	public PlotMessageAdapter(IPlotWidgetListener listener) {
		this.listener = listener;
	}

	@Override
	public int processMessage(final Message msg) {
		int retVal = Commandable.NORMAL;

		if ("MARK".equals(msg.name) && msg.info == 1) { // leftclick
			final Position p;
			if (msg.data instanceof Position) {
				p = (Position) msg.data;
			} else if (msg.data instanceof Table) {
				Table tbl = (Table) msg.data;
				p = new Position();
				p.x = tbl.getD("X");
				p.y = tbl.getD("Y");
				p.t = tbl.getD("T");
			} else {
				return Commandable.NOOP; // unknown data in MARK msg
			}
			this.listener.click(p.x, p.y, p.t);
		} else if ("MARK".equals(msg.name) && msg.info == 0) { // mouse move
			final Position p;
			if (msg.data instanceof Position) {
				p = (Position) msg.data;
			} else if (msg.data instanceof Table) {
				Table tbl = (Table) msg.data;
				p = new Position();
				p.x = tbl.getD("X");
				p.y = tbl.getD("Y");
				p.t = tbl.getD("T");
			} else {
				return Commandable.NOOP; // unknown data in MARK msg
			}
			this.listener.motion(p.x, p.y, p.t);
		} else if ("ZOOM".equals(msg.name)) { // leftclick-drag
			if (msg.data instanceof DragBox) {
				final DragBox d = (DragBox) msg.data;
				this.listener.zoomX(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax(), d);
			}
		} else if ("ZOOMIN".equals(msg.name)) { // mousewheel
			if (msg.data instanceof DragBox) {
				final DragBox d = (DragBox) msg.data;
				this.listener.zoomIn(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax(), d);
			}
		} else if ("DRAGBOX".equals(msg.name)) { // Rightclick-drag
			final DragBox d;
			if (msg.data instanceof DragBox) {
				d = (DragBox) msg.data;
			} else if (msg.data instanceof Table) {
				Table tbl = (Table) msg.data;
				double xmin = tbl.getD("XMIN");
				double xmax = tbl.getD("XMAX");
				double ymin = tbl.getD("YMIN");
				double ymax = tbl.getD("YMAX");
				d = new DragBox(xmin, xmax, ymin, ymax);
			} else {
				return Commandable.NOOP;
			}
			this.listener.dragBox(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax());
		} else if ("UNZOOM".equals(msg.name)) { // rightclick
			if (msg.data instanceof DragBox) {
				final DragBox d = (DragBox) msg.data;
				this.listener.unzoom(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax(), d);
			}
		} else if ("ZOOMOUT".equals(msg.name)) { // mousewheel
			if (msg.data instanceof DragBox) {
				final DragBox d = (DragBox) msg.data;
				this.listener.zoomOut(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax(), d);
			}
		} else if ("PANXY".equals(msg.name)) { // middle-drag
			if (msg.data instanceof DragBox) {
				final DragBox d = (DragBox) msg.data;
				this.listener.pan(d.getXMin(), d.getYMin(), d.getXMax(), d.getYMax());
			}
		} else {
			retVal = Commandable.NOOP;
		}

		return retVal;
	}

}
