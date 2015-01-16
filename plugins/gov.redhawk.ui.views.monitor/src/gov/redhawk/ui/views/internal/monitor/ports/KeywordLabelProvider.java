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
package gov.redhawk.ui.views.internal.monitor.ports;

import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import BULKIO.PortStatistics;
import CF.DataType;

/**
 * 
 */
public class KeywordLabelProvider extends CellLabelProvider {

	private final String id;

	public KeywordLabelProvider(final String id) {
		this.id = id;
	}

	@Override
	public void update(final ViewerCell cell) {
		PortStatistics stat = null;
		if (cell.getElement() instanceof PortStatisticsProvider) {
			stat = ((PortStatisticsProvider) cell.getElement()).getData();
		}
		if (stat == null) {
			return;
		}
		for (final DataType data : stat.keywords) {
			if (data.id.equals(this.id)) {
				cell.setText(String.valueOf(AnyUtils.convertAny(data.value)));
				return;
			}
		}

		cell.setText("");
	}
}
