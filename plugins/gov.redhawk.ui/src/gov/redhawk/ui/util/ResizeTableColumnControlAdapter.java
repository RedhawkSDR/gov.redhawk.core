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
package gov.redhawk.ui.util;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @since 4.0
 */
public class ResizeTableColumnControlAdapter extends ControlAdapter {
	
	private static final int MIN_COLUMN_WIDTH = 7;

	@Override
	public void controlResized(ControlEvent e) {
		Object source = e.getSource();
		if (source instanceof TableColumn) {
			TableColumn column = (TableColumn) source;
			if (column.getWidth() < MIN_COLUMN_WIDTH) {
				column.setWidth(MIN_COLUMN_WIDTH);
			}
		}
	}
}
