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

import org.eclipse.core.expressions.PropertyTester;

import gov.redhawk.frontend.TunerStatus;

public class TunerTypeTester extends PropertyTester {

	private static final String PROP_TUNER_TYPE = "tunerType";
	private static final String PROP_IS_RX_DIGITIZER = "isRxDigitizer";
	private static final String PROP_SUPPORTS_ADD_LISTENER_ACTION = "supportsAddListenerAction";
	private static final String PROP_SUPPORTS_PLOT_ACTIONS = "supportsPlotActions";

	public TunerTypeTester() {
	}

	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		TunerStatus tuner = (TunerStatus) receiver;
		if (property == null) {
			return false;
		}
		switch (property) {
		case PROP_TUNER_TYPE:
			if (expectedValue == null) {
				return false;
			}
			return expectedValue.equals(tuner.getTunerType());
		case PROP_IS_RX_DIGITIZER:
			return FRONTEND.TUNER_TYPE_RX_DIGITIZER.value.equals(tuner.getTunerType());
		case PROP_SUPPORTS_ADD_LISTENER_ACTION:
			return FRONTEND.TUNER_TYPE_RX_DIGITIZER.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX_SCANNER_DIGITIZER.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_DDC.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX_DIGITIZER_CHANNELIZER.value.equals(tuner.getTunerType());
		case PROP_SUPPORTS_PLOT_ACTIONS:
			return FRONTEND.TUNER_TYPE_RX_DIGITIZER.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX_SCANNER_DIGITIZER.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_DDC.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX.value.equals(tuner.getTunerType())
					|| FRONTEND.TUNER_TYPE_RX_DIGITIZER_CHANNELIZER.value.equals(tuner.getTunerType());
		default:
			return false;
		}
	}

}
