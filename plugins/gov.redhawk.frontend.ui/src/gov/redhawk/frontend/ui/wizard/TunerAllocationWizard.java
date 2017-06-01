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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.AllocationMode;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class TunerAllocationWizard extends Wizard {

	private TunerStatus[] tuners;
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
		this.tuners = new TunerStatus[] { tuner };
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
			if (tuners.length > 0) {
				allocatePage = new TunerAllocationWizardPage(tuners[0], feiDevice);
				addPage(allocatePage);
			} else {
				FrontEndUIActivator.getDefault().getLog().log(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID,
					"Unable to launch Allocation wizard because an empty array of tuners was provided."));
			}
		}
	}

	@Override
	public boolean performFinish() {
		ScaDevice< ? > tmpDevice = ScaEcoreUtils.getEContainerOfType(tuners[0], ScaDevice.class);
		if (tmpDevice == null) {
			tmpDevice = this.feiDevice;
		}

		final StringBuilder sb = new StringBuilder();
		final boolean retVal = allocateTuner(tmpDevice, sb);

		if (!retVal) {
			MessageDialog.openError(getShell(), "Tuner not Allocated", sb.toString());
		}

		return retVal;
	}

	/**
	 * Attempts an allocateCapacity call on the provided device. Checks user input to either run the allocate in the UI
	 * Thread or in a background job.
	 * @param device
	 * @param sb
	 * @return true is allocateCapacity succeeds, false otherwise
	 */
	private boolean allocateTuner(ScaDevice< ? > device, StringBuilder sb) {
		final DataType[] props = createAllocationProperties();
		final boolean[] retVal = { false };

		try {
			TunerAllocationJob allocationJob = new TunerAllocationJob("Allocating: " + device.getLabel(), device, props);
			if (allocatePage != null && allocatePage.isBackgroundJob()) {
				// Run allocation as a background job
				allocationJob.schedule();
				return true;
			} else {
				// Run allocation directly
				getContainer().run(true, true, new IRunnableWithProgress() {
					@Override
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						IStatus status = allocationJob.allocate(monitor, sb);
						retVal[0] = status.isOK();
					}
				});
			}
		} catch (InvocationTargetException e) {
			sb.append("An error occurred during the invocation of the allocation request. Message: " + e.getMessage());
			retVal[0] = false;
		} catch (InterruptedException e) {
			sb.append("The allocation request was cancelled");
			retVal[0] = false;
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
