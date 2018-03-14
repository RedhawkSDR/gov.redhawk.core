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
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.AllocationMode;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.ScannerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperties;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperty;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.sca.model.jobs.AllocateJob;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class TunerAllocationWizard extends Wizard {

	private TunerStatus tuner;
	private ScaDevice< ? > feiDevice;

	private TunerAllocationWizardPage allocatePage;
	private ListenerAllocationWizardPage listenerPage;
	private ScannerAllocationWizardPage scannerPage;
	private boolean listener;
	private String targetId;

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
				scannerPage = new ScannerAllocationWizardPage(tuner);
				addPage(scannerPage);
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
		if (status.getSeverity() == IStatus.CANCEL) {
			return false;
		} else if (!status.isOK()) {
			ErrorDialog.openError(getShell(), "Tuner Not Allocated", "Tuner Not Allocated", status);
			return false;
		}
		return true;
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
			return Status.CANCEL_STATUS;
		}

		return retVal[0];
	}

	private DataType[] createAllocationProperties() {
		// If the listener-only page was shown
		if (listener) {
			ScaStructProperty struct = listenerPage.getListenerAllocationStruct();
			DataType dt = new DataType(ListenerAllocationProperty.INSTANCE.getId(), struct.toAny());
			return new DataType[] { dt };
		}

		// If the full page was shown (allocate or listen), and the user chose listener
		if (allocatePage.getAllocationMode() == AllocationMode.LISTENER) {
			ScaStructProperty struct = allocatePage.getListenerAllocationStruct();
			DataType dt = new DataType(ListenerAllocationProperty.INSTANCE.getId(), struct.toAny());
			return new DataType[] { dt };
		}

		// The full page was shown (allocate or listen), and the user chose allocate
		List<DataType> props = new ArrayList<DataType>();
		ScaStructProperty struct = allocatePage.getTunerAllocationStruct();
		DataType dt = new DataType(TunerAllocationProperty.INSTANCE.getId(), struct.toAny());
		props.add(dt);

		// Did they choose a scanner allocation? Add the property for it if so.
		String tunerType = (String) struct.getSimple(TunerAllocationProperties.TUNER_TYPE.getId()).getValue();
		if (FRONTEND.TUNER_TYPE_RX_SCANNER_DIGITIZER.value.equals(tunerType)) {
			struct = scannerPage.getScannerAllocationStruct();
			dt = new DataType(ScannerAllocationProperty.INSTANCE.getId(), struct.toAny());
			props.add(dt);
		}

		return props.toArray(new DataType[0]);
	}

	private boolean isScannerAllocation() {
		return !listener && allocatePage.isScannerAllocation();
	}

	@Override
	public boolean canFinish() {
		if (listener) {
			return listenerPage.isPageComplete();
		}

		if (!allocatePage.isPageComplete()) {
			return false;
		}
		return (isScannerAllocation()) ? scannerPage.isPageComplete() : true;
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		// If we're on the allocate tuner page, and they haven't chosen a scanner, there isn't a next page
		if (page == allocatePage && !isScannerAllocation()) {
			return null;
		}

		return super.getNextPage(page);
	}

	@Override
	public int getPageCount() {
		int count = super.getPageCount();

		// Report 1 fewer page if using the allocate wizard pages, and scanner isn't chosen
		return (!listener && !isScannerAllocation()) ? count - 1 : count;
	}

}
