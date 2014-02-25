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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;

import org.eclipse.core.expressions.PropertyTester;

/**
 *
 */
public class AllocationPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		TunerStatus theTuner = (TunerStatus) receiver;
		if ("hasAllocationID".equals(property)) {
			String allocationID = theTuner.getAllocationID();
			if (!(allocationID == null || "".equals(allocationID))) {
				return true;
			}
		} else if ("supportedTunerType".equals(property)) {
			if (FrontEndUIActivator.SUPPORTED_TUNER_TYPES.contains(theTuner.getTunerType())) {
				return true;
			}
		}
		return false;
	}

}
