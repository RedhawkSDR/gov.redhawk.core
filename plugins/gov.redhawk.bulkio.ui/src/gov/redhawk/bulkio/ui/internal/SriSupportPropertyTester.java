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
package gov.redhawk.bulkio.ui.internal;

import org.eclipse.core.expressions.PropertyTester;

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.model.sca.ScaUsesPort;

public class SriSupportPropertyTester extends PropertyTester {
	public static final String PROP_TYPE_SRI_SUPPORT = "sriSupported";

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (receiver instanceof ScaUsesPort) {
			ScaUsesPort port = (ScaUsesPort) receiver;
			if (BulkIOType.isTypeSupported(port.getRepid())) {
				return true;
			}

		}
		return false;
	}

}
