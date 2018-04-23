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
package gov.redhawk.frontend.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import CF.DataType;
import CF.PropertiesHelper;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.sca.model.jobs.AllocateJob;
import gov.redhawk.sca.model.jobs.DeallocateJob;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public final class TunerStatusUtil {

	private TunerStatusUtil() {
	}

	public static DataType[] createAllocationProperties(String listenerAllocationID, TunerStatus tuner) {
		ScaStructProperty struct = ScaFactory.eINSTANCE.createScaStructProperty();
		struct.setDefinition(ListenerAllocationProperty.INSTANCE.createProperty());
		struct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId()).setValue(TunerUtils.getControlId(tuner));
		struct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId()).setValue(listenerAllocationID);

		DataType dt = new DataType();
		dt.id = ListenerAllocationProperty.INSTANCE.getId();
		dt.value = struct.toAny();
		return new DataType[] { dt };
	}

	public static String getListenerID(DataType[] props) {
		for (DataType prop : props) {
			DataType[] dt = PropertiesHelper.extract(prop.value);
			for (DataType d : dt) {
				if (d.id.equals(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())) {
					return (d.value.toString());
				}
			}
		}
		return "";
	}

	public static boolean containsListener(TunerStatus tuner, DataType[] props) {
		String listenerId = TunerStatusUtil.getListenerID(props);
		for (ListenerAllocation a : tuner.getListenerAllocations()) {
			if (a.getListenerID().equals(listenerId)) {
				return true;
			}
		}
		return false;
	}

	public static Job createDeallocationJob(final TunerStatus tuner, final DataType[] props) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		DeallocateJob job = new DeallocateJob(device, props);
		job.setName("FEI Deallocate Listener");
		return job;
	}

	public static IStatus allocateTuner(IProgressMonitor monitor, final TunerStatus tuner, final DataType[] props) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		AllocateJob job = new AllocateJob(device, props);
		return job.run(monitor);
	}

	public static Job createAllocationJob(final TunerStatus tuner, final DataType[] props) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		AllocateJob job = new AllocateJob(device, props);
		job.setName("Allocating tuner " + tuner.getAllocationID());
		return job;
	}
}
