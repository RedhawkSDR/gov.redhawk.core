/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.application.metrics;

import org.eclipse.nebula.widgets.xviewer.IXViewerLabelProvider;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeItem;

public class MetricsGradient {

	private final XViewer xViewer;

	public MetricsGradient(XViewer xViewer) {
		this.xViewer = xViewer;
		this.xViewer.getTree().addListener(SWT.PaintItem, paintListener);
	}

	private final Listener paintListener = new Listener() {

		@Override
		public void handleEvent(Event event) {
			try {
				XViewerColumn xViewerColumn = ((IXViewerLabelProvider) xViewer.getLabelProvider()).getTreeColumnOffIndex(event.index);
				TreeItem item = (TreeItem) event.item;
				if (item.getData() == null) {
					return;
				}
				MetricsLabelProvider labelProvider = ((MetricsLabelProvider) xViewer.getLabelProvider()); 

				int percent = labelProvider.getColumnGradient(item.getData(), xViewerColumn, event.index);
				if (percent <= 0) {
					return;
				} else if (percent > 100) {
					percent = 100;
				}
				int color = labelProvider.getColumnGradientColor(item.getData(), xViewerColumn, event.index);

				// Clear the are of the cell that was already drawn on by the parent
				GC gc = event.gc;
				gc.setForeground(xViewer.getTree().getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
				gc.fillRectangle(event.x, event.y, event.width, event.height);

				// Draw a gradient bar
				gc.setForeground(xViewer.getTree().getDisplay().getSystemColor(color));
				gc.setBackground(xViewer.getTree().getDisplay().getSystemColor(SWT.COLOR_WHITE));
				int width = (xViewerColumn.getWidth() - 5) * percent / 100;
				gc.fillGradientRectangle(event.x, event.y, width, event.height, true);

				// Draw text
				gc.setForeground(xViewer.getTree().getDisplay().getSystemColor(SWT.COLOR_LIST_FOREGROUND));
				String text = ((IXViewerLabelProvider) xViewer.getLabelProvider()).getColumnText(item.getData(), xViewerColumn, event.index);
				Point size = event.gc.textExtent(text);
				int offset = Math.max(0, (event.height - size.y) / 2 + 1);
				gc.drawText(text, event.x + 5, event.y + offset, true);
			} catch (Exception ex) { // SUPPRESS CHECKSTYLE Blame XViewer which throws Exception
				return;
			}
		}
	};
}
