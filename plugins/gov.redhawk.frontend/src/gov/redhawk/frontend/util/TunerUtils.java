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
package gov.redhawk.frontend.util;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaDevice;

import org.eclipse.emf.common.notify.Notification;

public enum TunerUtils {
	INSTANCE;

	public static final String TUNER_CONTAINER_ID = TunerContainer.class.getCanonicalName();

	private TunerUtils() {
	}

	public void processChange(Notification notification) {

	}

	/**
	 * Checks device for tuners, and if found returns a TunerContainer object with an Object array
	 * 
	 * @param device
	 * @return container object for the devices tuners
	 */
	public TunerContainer getTunerContainer(final ScaDevice< ? > device) {
		return (TunerContainer) device.getFeatureData().get(TunerUtils.TUNER_CONTAINER_ID);
	}

	public static String getControlId(TunerStatus tuner) {
		String id = tuner.getAllocationID();
		if (id == null) {
			return null;
		}
		int index = id.indexOf(",");
		if (index > -1) {
			id = id.substring(0, index);
		}
		return id;
	}

}
