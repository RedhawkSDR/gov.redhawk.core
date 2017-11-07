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

import org.eclipse.core.expressions.PropertyTester;

import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.ScaDomainManager;

public class ScaDomainManagerTester extends PropertyTester {

	public ScaDomainManagerTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (!(receiver instanceof ScaDomainManager)) {
			return false;
		}
		ScaDomainManager domMgr = (ScaDomainManager) receiver;

		switch (property) {
		case "connected":
			boolean connected = DomainConnectionState.CONNECTED.equals(domMgr.getState());
			if (expectedValue instanceof Boolean) {
				return ((Boolean) expectedValue).booleanValue() == connected;
			} else {
				return Boolean.parseBoolean(expectedValue.toString()) == connected;
			}
		default:
			return false;
		}
	}

}
