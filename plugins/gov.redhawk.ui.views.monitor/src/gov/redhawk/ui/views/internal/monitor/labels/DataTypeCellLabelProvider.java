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
package gov.redhawk.ui.views.internal.monitor.labels;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import CF.DataType;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;
import mil.jpeojtrs.sca.util.AnyUtils;

public class DataTypeCellLabelProvider extends CellLabelProvider {

	private final String id;

	public DataTypeCellLabelProvider(final String id) {
		this.id = id;
	}

	@Override
	public void update(final ViewerCell cell) {
		final PortStatisticsProvider statProvider = (PortStatisticsProvider) cell.getElement();
		if (statProvider == null || statProvider.getData() == null) {
			cell.setText("");
			return;
		}
		final DataType[] keywords = statProvider.getData().keywords;
		for (final DataType keyword : keywords) {
			if (keyword.id.equals(this.id)) {
				cell.setText(String.valueOf(AnyUtils.convertAny(keyword.value)));
			}
		}
	}
}
