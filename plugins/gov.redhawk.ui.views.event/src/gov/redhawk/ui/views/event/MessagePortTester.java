/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.views.event;

import org.eclipse.core.expressions.PropertyTester;

import gov.redhawk.model.sca.ScaUsesPort;

public class MessagePortTester extends PropertyTester {
	public static final String IS_MESSAGE_PORT = "isMessagePort";
	private static final String MESSAGE_EVENT_IDL = "IDL:ExtendedEvent/MessageEvent:1.0";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof ScaUsesPort) {
			ScaUsesPort scaPort = (ScaUsesPort) receiver;
			if (MESSAGE_EVENT_IDL.equals(scaPort.getRepid())) {
				return true;
			}
		}
		return false;
	}

}
