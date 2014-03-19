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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerStatus;

import org.eclipse.core.expressions.PropertyTester;

/**
 * 
 */
public class TunerTypeTester extends PropertyTester {

	private static final String PROP_IS_RX_DIGITIZER = "isRxDigitizer";
	
	/**
	 * 
	 */
	public TunerTypeTester() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (PROP_IS_RX_DIGITIZER.equals(property)) {
			TunerStatus tuner = (TunerStatus) receiver;
			if (FRONTEND.TUNER_TYPE_RX_DIGITIZER.value.equals(tuner.getTunerType())) {
				return true;
			}
		}
		return false;
	}

}
