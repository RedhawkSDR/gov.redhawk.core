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
package gov.redhawk.ui.views.internal.monitor.values;

import org.omg.CORBA.Any;
import org.omg.CORBA.BAD_OPERATION;

public class DoubleDataTypeValueProvider extends DataTypeValueProvider<Double> {

	public DoubleDataTypeValueProvider(String id) {
		super(id);
	}

	@Override
	public Double getValue(Object input) {
		Any any = getAny(input);
		try {
			return (any == null) ? null : any.extract_double();
		} catch (BAD_OPERATION e) {
			return null;
		}
	}

}
