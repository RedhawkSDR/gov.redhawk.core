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

import java.math.BigInteger;

import org.omg.CORBA.Any;
import org.omg.CORBA.BAD_OPERATION;

import mil.jpeojtrs.sca.util.UnsignedUtils;

public class ULongLongDataTypeValueProvider extends DataTypeValueProvider<BigInteger> {

	public ULongLongDataTypeValueProvider(String id) {
		super(id);
	}

	@Override
	public BigInteger getValue(Object input) {
		Any any = getAny(input);
		try {
			return (any == null) ? null : UnsignedUtils.toSigned(any.extract_ulonglong());
		} catch (BAD_OPERATION e) {
			return null;
		}
	}

}
