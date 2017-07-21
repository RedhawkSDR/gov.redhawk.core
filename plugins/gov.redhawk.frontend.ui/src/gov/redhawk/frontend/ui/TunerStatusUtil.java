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

import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;

import CF.DataType;
import CF.PropertiesHelper;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaStructProperty;
import mil.jpeojtrs.sca.util.CorbaUtils;
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
		Job job = new Job("FEI Deallocate Listener") {
			@Override
			protected IStatus run(IProgressMonitor parentMonitor) {
				if (!TunerStatusUtil.containsListener(tuner, props)) {
					return Status.CANCEL_STATUS;
				}
				try {
					SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Deallocating listener...", 2);
					if (device != null && !device.isDisposed()) {
						CorbaUtils.invoke(new Callable<IStatus>() {

							@Override
							public IStatus call() throws Exception {
								try {
									device.deallocateCapacity(props);
									return Status.OK_STATUS;
								} catch (InvalidCapacity e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in deallocation: " + e.msg, e);
								} catch (InvalidState e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in deallocation: " + e.msg, e);
								}
							}

						}, subMonitor.newChild(1));
						device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
					}
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during deallocation", e);
				} catch (CoreException e) {
					return new Status(e.getStatus().getSeverity(), FrontEndUIActivator.PLUGIN_ID, "Failed to deallocate tuner", e);
				}
				return Status.OK_STATUS;
			}
		};
		return job;
	}

	public static IStatus allocateTuner(IProgressMonitor monitor, final TunerStatus tuner, final DataType[] props) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Allocating tuner " + tuner.getAllocationID(), IProgressMonitor.UNKNOWN);
		try {
			IStatus status = CorbaUtils.invoke(new Callable<IStatus>() {

				@Override
				public IStatus call() throws Exception {
					try {
						subMonitor.subTask("Allocating capacity...");
						if (device.allocateCapacity(props)) {
							return Status.OK_STATUS;
						} else {
							return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Allocation failed.", null);
						}
					} catch (InvalidCapacity e) {
						return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in allocation: " + e.msg, e);
					} catch (InvalidState e) {
						return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in allocation: " + e.msg, e);
					} catch (InsufficientCapacity e) {
						return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Insufficient Capacity in allocation: " + e.msg, e);
					}
				}

			}, subMonitor.newChild(1));
			if (!status.isOK()) {
				return status;
			}
			subMonitor.subTask("Refeshing device...");
			device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
			return Status.OK_STATUS;
		} catch (InterruptedException e) {
			return Status.CANCEL_STATUS;
		} catch (CoreException e) {
			return new Status(e.getStatus().getSeverity(), FrontEndUIActivator.PLUGIN_ID, "Failed to allocate tuner.", e);
		} finally {
			subMonitor.done();
		}
	}

	public static Job createAllocationJob(final TunerStatus tuner, final DataType[] props) {
		return new Job("Allocating tuner " + tuner.getAllocationID()) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				return TunerStatusUtil.allocateTuner(monitor, tuner, props);
			}
		};

	}
}
