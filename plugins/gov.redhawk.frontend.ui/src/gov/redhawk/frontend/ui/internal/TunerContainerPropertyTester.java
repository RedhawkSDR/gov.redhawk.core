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

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.core.expressions.PropertyTester;

/**
 *
 */
public class TunerContainerPropertyTester extends PropertyTester {

	/**
	 * 
	 */
	public TunerContainerPropertyTester() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		TunerContainer container = (TunerContainer) receiver;
		if ("hasUnallocatedTuners".equals(property)) {
			for (ScaStructProperty tuner: container.getTuners()) {
				ScaSimpleProperty allocationID = tuner.getSimple("FRONTEND::tuner_status::allocation_id_csv");
				if (allocationID == null || allocationID.getValue() == null || "".equals(allocationID.getValue())) {
					return true;
				}
			}
		}
		if ("hasAllocatedTuners".equals(property)) {
			for (ScaStructProperty tuner: container.getTuners()) {
				ScaSimpleProperty allocationID = tuner.getSimple("FRONTEND::tuner_status::allocation_id_csv");
				if (!(allocationID == null || allocationID.getValue() == null || "".equals(allocationID.getValue()))) {
					return true;
				}
			}
		}
		return false;
	}

}
