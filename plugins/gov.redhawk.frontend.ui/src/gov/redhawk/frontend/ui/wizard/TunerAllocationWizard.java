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

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.FrontEndUIActivator.ALLOCATION_MODE;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaStructProperty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import CF.DataType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

public class TunerAllocationWizard extends Wizard {

	private TunerStatus[] tuners;
	private TunerAllocationWizardPage allocatePage;
	private boolean listener;
	private String targetId;
	private ListenerAllocationWizardPage listenerPage;

	public TunerAllocationWizard(TunerStatus tuner) {
		this(tuner, false, null);
	}

	public TunerAllocationWizard(TunerStatus tuner, boolean listener, String targetId) {
		this.tuners = new TunerStatus[] { tuner };
		this.listener = listener;
		this.targetId = targetId;
		this.setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		if (listener) {
			listenerPage = new ListenerAllocationWizardPage(targetId);
			addPage(listenerPage);
		} else {
			if (tuners.length > 0) {
				allocatePage = new TunerAllocationWizardPage(tuners[0]);
				addPage(allocatePage);
			} else {
				FrontEndUIActivator.getDefault().getLog().log(
					new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID,
							"Unable to launch Allocation wizard because an empty array of tuners was provided."));
			}
		}
	}

	@Override
	public boolean performFinish() {
		final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuners[0], ScaDevice.class);
		final StringBuilder sb = new StringBuilder();
		final DataType[] props = createAllocationProperties();
		final boolean[] retVal = {false};
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					try {
						retVal[0] = CorbaUtils.invoke(new Callable<Boolean>() {

							@Override
							public Boolean call() throws Exception {
								try {
									if (!device.allocateCapacity(props)) {
										sb.append("The allocation request was not accepted because resources matching"
												+ " all aspects of the request were not available.");
										return false;
									} else {
										device.refresh(null, RefreshDepth.SELF);
										return true;
									}
								} catch (InvalidCapacity e) {
									sb.append("The allocation request was invalid. Message: " + e.msg);
									return false;
								} catch (InvalidState e) {
									sb.append("The Allocation Request failed because the device is in an invalid state. Message: " + e.msg);
									return false;
								} catch (InsufficientCapacity e) {
									sb.append("The Allocation Request failed because the device has insufficient capacity. Message: " + e.msg);
									return false;
								} catch (InterruptedException e) {
									sb.append("Failed to refresh device after allocating capacity. Message: " + e.getMessage());
									//Only refresh will throw this exception
									return true;
								}
							}

						}, monitor);
					} catch (CoreException e) {
						sb.append("An error occurred during the invocation of the allocation request. Message: " + e.getMessage());
						retVal[0] = false;
					} catch (InterruptedException e) {
						throw e;
					}
				}
			});
			
		} catch (InvocationTargetException e) {
			sb.append("An error occurred during the invocation of the allocation request. Message: " + e.getMessage());
			retVal[0] = false;
		} catch (InterruptedException e) {
			sb.append("The allocation request was cancelled");
			retVal[0] = false;
		}
		
		if (!retVal[0]) {
			MessageDialog.openError(getShell(), "Tuner not Allocated", sb.toString());
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
			if (page.getAllocationMode() == ALLOCATION_MODE.TUNER) {
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
