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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import CF.DataType;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperty;
import gov.redhawk.frontend.util.TunerProperties.TunerAllocationProperty;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.model.jobs.DeallocateJob;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class DeallocateAction extends FrontendAction {

	public DeallocateAction(FrontendSection theSection) {
		super(theSection, "Deallocate", "gov.redhawk.frontend.actions.deallocate", "gov.redhawk.frontend.commands.deallocate",
			"icons/remove.png");
	}
	
	@Override
	public void run() {
		EObject obj = getSection().getInput();
		boolean removeSelection = true;
		if (obj instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) obj;
			if (tuner.getTunerContainer() == null) {
				// already deallocated, probably still in a pinned properties view
				return;
			}
			boolean proceed = true;
			if (tuner.getAllocationID() != null && !tuner.getAllocationID().isEmpty() 
					&& tuner.getAllocationID().contains(",")) {
				if (confirmDeallocate(tuner) == 0) {
					proceed = false;
					removeSelection = false;
				}
			}
			if (proceed) {
				deallocateTuner(tuner);
			}
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			if (!confirmDeallocate(container)) {
				return;
			}
			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
			removeSelection = false;
		}
		if (obj instanceof ScaDevice) {
			ScaDevice< ? > device = (ScaDevice< ? >) obj;
			TunerContainer container = TunerUtils.INSTANCE.getTunerContainer(device);
			if (!confirmDeallocate(container)) {
				return;
			}
			for (TunerStatus tuner : container.getTunerStatus().toArray(new TunerStatus[0])) {
				deallocateTuner(tuner);
			}
		}
		if (obj instanceof ListenerAllocation) {
			final ListenerAllocation listener = (ListenerAllocation) obj;
			if (listener.getTunerStatus() == null) {
				// already deallocated, probably still in a pinned properties view
				return;
			}
			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(listener, ScaDevice.class);
			if (device == null) {
				return;
			}

			Struct allocProp = ListenerAllocationProperty.INSTANCE.createDeallocationStruct(listener);
			DataType dt = new DataType(allocProp.getId(), allocProp.toAny());

			Job job = new DeallocateJob(device, dt);
			job.setName("Deallocate FEI listener");
			job.setUser(true);
			job.addJobChangeListener(new JobChangeAdapter() {
				@Override
				public void done(IJobChangeEvent event) {
					IStatus result = event.getResult();
					if (result == null || !result.isOK()) {
						return;
					}

					final TunerStatus tunerStatus = listener.getTunerStatus();
					if (tunerStatus != null) {
						ScaModelCommand.execute(tunerStatus, new ScaModelCommand() {
							@Override
							public void execute() {
								tunerStatus.getListenerAllocations().remove(listener);
							}
						});
					}
				}
			});
			job.schedule();
		}
		if (removeSelection) {
			getSection().unsetPageSelection();
		}
	}

	private int confirmDeallocate(TunerStatus tuner) {
		MessageDialog warning = new MessageDialog(Display.getCurrent().getActiveShell(), "Deallocation Warning", null,
			"Some selected tuners have listeners.  Deallocating them will also deallocate all of their listeners.  Deallocate them anyway?", 
			MessageDialog.WARNING, new String[] { "Cancel", "Yes" }, 0);
		return warning.open();
	}
	
	private boolean confirmDeallocate(TunerContainer container) {
		for (TunerStatus tuner : container.getTunerStatus()) {
			if (tuner.getAllocationID() != null && !tuner.getAllocationID().isEmpty() 
					&& tuner.getAllocationID().contains(",")) {
				int response = confirmDeallocate(tuner);
				if (response == 0) {
					return false;
				}
				return true;
			}
		}
		return true;
	}

	private boolean deallocateTuner(TunerStatus tuner) {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
		Struct allocProp = TunerAllocationProperty.INSTANCE.createDeallocationStruct(tuner);
		final DataType prop = new DataType(allocProp.getId(), allocProp.toAny());

		Job job = new DeallocateJob(device, tuner.getAllocationID());
		job.setName("Deallocate FEI control");
		job.setUser(true);
		job.schedule();

		return true;
	}
}
