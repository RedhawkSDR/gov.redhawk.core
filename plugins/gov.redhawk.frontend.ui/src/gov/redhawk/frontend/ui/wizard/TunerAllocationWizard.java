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
package gov.redhawk.frontend.ui.wizard;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.AllocationMode;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.sca.model.jobs.AllocateJob;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class TunerAllocationWizard extends Wizard {

	private TunerStatus tuner;
	private TunerAllocationWizardPage allocatePage;
	private boolean listener;
	private String targetId;
	private ListenerAllocationWizardPage listenerPage;
	private ScaDevice< ? > feiDevice;

	public TunerAllocationWizard(TunerStatus tuner) {
		this(tuner, false, null);
	}

	public TunerAllocationWizard(TunerStatus tuner, boolean listener, String targetId) {
		this(tuner, listener, targetId, null);
	}

	public TunerAllocationWizard(TunerStatus tuner, ScaDevice< ? > device) {
		this(tuner, false, null, device);
	}

	public TunerAllocationWizard(TunerStatus tuner, boolean listener, String targetId, ScaDevice< ? > device) {
		this.tuner = tuner;
		this.listener = listener;
		this.targetId = targetId;
		this.setNeedsProgressMonitor(true);
		this.feiDevice = device;
		if (listener) {
			setWindowTitle("Allocate Listener");
		} else {
			setWindowTitle("Allocate Tuner");
		}
	}

	@Override
	public void addPages() {
		if (listener) {
			listenerPage = new ListenerAllocationWizardPage(targetId);
			addPage(listenerPage);
		} else {
			if (tuner != null) {
				allocatePage = new TunerAllocationWizardPage(tuner, feiDevice);
				addPage(allocatePage);
			} else {
				FrontEndUIActivator.getDefault().getLog().log(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID,
					"Unable to launch Allocation wizard because an empty array of tuners was provided."));
			}
		}
	}

	@Override
	public boolean performFinish() {
		ScaDevice< ? > tmpDevice = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		if (tmpDevice == null) {
			tmpDevice = this.feiDevice;
		}

		IStatus status = allocateTuner(tmpDevice);
		if (!status.isOK()) {
			ErrorDialog.openError(getShell(), "Tuner not Allocated", status.getMessage(), status);
		}
		return status.isOK();
	}

	/**
	 * Attempts an allocateCapacity call on the provided device. Checks user input to either run the allocate in the UI
	 * Thread or in a background job.
	 * @param device
	 * @return Status of the allocation
	 */
	private IStatus allocateTuner(ScaDevice< ? > device) {
		final DataType[] props = createAllocationProperties();
		final IStatus[] retVal = { Status.CANCEL_STATUS };

		try {
			AllocateJob allocationJob = new AllocateJob(device, props);
			if (allocatePage != null && allocatePage.isBackgroundJob()) {
				// Run allocation as a background job
				allocationJob.schedule();
				return Status.OK_STATUS;
			} else {
				// Run allocation directly
				getContainer().run(true, true, monitor -> {
					retVal[0] = allocationJob.run(monitor);
				});
			}
		} catch (InvocationTargetException e) {
			return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "An error occurred during the invocation of the allocation request.", e);
		} catch (InterruptedException e) {
			return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "The allocation request was cancelled");
		}

		return retVal[0];
	}

	private DataType[] createAllocationProperties() {
		List<DataType> props = new ArrayList<DataType>();
		ScaStructProperty struct;
		DataType dt = new DataType();
		if (listener) {
			ListenerAllocationWizardPage page = listenerPage;
			struct = page.getListenerAllocationStruct();
			dt.id = "FRONTEND::listener_allocation";
			dt.value = struct.toAny();
		} else {
			TunerAllocationWizardPage page = allocatePage;
			if (page.getAllocationMode() == AllocationMode.TUNER) {
				struct = page.getTunerAllocationStruct();
				dt.id = "FRONTEND::tuner_allocation";
			} else {
				struct = page.getListenerAllocationStruct();
				dt.id = "FRONTEND::listener_allocation";
			}
			dt.value = struct.toAny();
		}
		props.add(dt);
		return props.toArray(new DataType[0]);
	}

}
