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
package gov.redhawk.sca.model.internal.expressions;

import java.util.regex.Pattern;

import org.eclipse.core.expressions.PropertyTester;

import gov.redhawk.model.sca.ScaPort;

public class PortPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		ScaPort< ? , ? > port = (ScaPort< ? , ? >) receiver;
		switch (property) {
		case "interface":
			return testInterface(port, expectedValue);
		default:
			return false;
		}
	}

	private boolean testInterface(ScaPort< ? , ? > port, Object expectedValue) {
		if (expectedValue == null) {
			return false;
		}
		return Pattern.matches(expectedValue.toString(), port.getRepid());
	}
}
