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
package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.ScannerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperty;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaStructProperty;

/**
 * @since 1.1
 */
public class FEIStructDefaults {

	private FEIStructDefaults() {
	}

	/**
	 * Create a tuner allocation struct with some default values for editing in the UI.
	 * @param allocationID The allocation ID to use
	 * @return
	 */
	public static ScaStructProperty defaultTunerAllocationStruct(String allocationID) {
		ScaStructProperty tunerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		tunerAllocationStruct.setId(TunerAllocationProperty.INSTANCE.getId());
		tunerAllocationStruct.setDefinition(TunerAllocationProperty.INSTANCE.createProperty());

		// Defaults
		tunerAllocationStruct.getSimple(TunerAllocationProperties.ALLOCATION_ID.getId()).setValue(allocationID);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.BANDWIDTH_TOLERANCE.getId()).setValue(20.0);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.SAMPLE_RATE_TOLERANCE.getId()).setValue(20.0);
		tunerAllocationStruct.getSimple(TunerAllocationProperties.RF_FLOW_ID.getId()).setValue(""); //$NON-NLS-1$
		tunerAllocationStruct.getSimple(TunerAllocationProperties.GROUP_ID.getId()).setValue(""); //$NON-NLS-1$

		return tunerAllocationStruct;
	}

	/**
	 * Create a listener allocation struct with some default values for editing in the UI.
	 * @return
	 */
	public static ScaStructProperty defaultListenerAllocationStruct() {
		ScaStructProperty listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		listenerAllocationStruct.setId(ListenerAllocationProperty.INSTANCE.getId());
		listenerAllocationStruct.setDefinition(ListenerAllocationProperty.INSTANCE.createProperty());

		return listenerAllocationStruct;
	}

	/**
	 * Create a scanner allocation struct with some default values for editing in the UI.
	 * @return
	 */
	public static ScaStructProperty defaultScannerAllocationStruct() {
		ScaStructProperty scannerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		scannerAllocationStruct.setId(ScannerAllocationProperty.INSTANCE.getId());
		scannerAllocationStruct.setDefinition(ScannerAllocationProperty.INSTANCE.createProperty());

		return scannerAllocationStruct;
	}

}
