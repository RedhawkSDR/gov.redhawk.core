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

import java.text.NumberFormat;

public class Formatters {

	private Formatters() {
	}

	protected static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance();

	protected static final NumberFormat DECIMAL_FORMATTER;
	static {
		DECIMAL_FORMATTER = NumberFormat.getNumberInstance();
		DECIMAL_FORMATTER.setGroupingUsed(true);
		DECIMAL_FORMATTER.setMinimumFractionDigits(1);
		DECIMAL_FORMATTER.setMaximumFractionDigits(1);
		DECIMAL_FORMATTER.setMinimumIntegerDigits(1);
	}

}
