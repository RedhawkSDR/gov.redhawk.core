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

import java.math.BigInteger;
import java.text.NumberFormat;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

import gov.redhawk.ui.views.internal.monitor.values.ValueProvider;

public class ULongLongValueCellLabelProvider extends CellLabelProvider {

	private ValueProvider<BigInteger> valueProvider;

	public ULongLongValueCellLabelProvider(ValueProvider<BigInteger> valueProvider) {
		this.valueProvider = valueProvider;
	}

	@Override
	public void update(ViewerCell cell) {
		BigInteger value = valueProvider.getValue(cell.getElement());
		String strValue = (value == null) ? "" : NumberFormat.getNumberInstance().format(value);
		cell.setText(strValue);
	}

}
