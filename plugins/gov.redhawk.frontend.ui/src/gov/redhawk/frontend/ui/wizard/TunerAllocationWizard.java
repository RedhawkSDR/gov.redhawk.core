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

import javax.print.attribute.standard.JobState;
import javax.swing.ProgressMonitor;

import org.eclipse.core.internal.jobs.JobStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;

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
						new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Unable to launch Allocation wizard because an empty array of tuners was provided."));
			}
		}
	}

	@Override
	public boolean performFinish() {
		final ScaDevice< ? > device = tuners[0].getTunerContainer().getModelDevice().getScaDevice();
		final Boolean[] result = new Boolean[1];
		final StringBuilder sb = new StringBuilder();
		final DataType[] props = createAllocationProperties();

		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Allocate Capacity", 1);


					Job allocateJob = new Job("Allocate Capacity") {

						@Override
						protected IStatus run(IProgressMonitor monitor) {
							String delim = "";
							try {
								if (!device.allocateCapacity(props)) {
									sb.append(delim + "The allocation request was not accepted because resources matching"
											+ " all aspects of the request were not available.");
									delim = "\n\n";
									result[0] = false;
								} else {
									result[0] = true;
								}
								device.refresh(null, RefreshDepth.SELF);
							} catch (InvalidCapacity e) {
								sb.append(delim + "The allocation request was invalid. Message: " + e.msg);
								delim = "\n\n";
								result[0] = false;
							} catch (InvalidState e) {
								sb.append(delim + "The Allocation Request failed because the device is in an invalid state. Message: " + e.msg);
								delim = "\n\n";
								result[0] = false;
							} catch (InsufficientCapacity e) {
								sb.append(delim + "The Allocation Request failed because the device has insufficient capacity. Message: " + e.msg);
								delim = "\n\n";
								result[0] = false;
							} catch (InterruptedException e) {
								sb.append(delim + "Failed to refresh device after allocating capacity. Message: " + e.getMessage());
								delim = "\n\n";
								//because only refresh will throw this exception, keep the result set by the return from allocateCapacity
								//but just in case, make sure it got set, since otherwise 
								if (result[0] == null) {
									result[0] = true;
								}
							} finally {
								monitor.worked(1);
							}
							return Status.OK_STATUS;
						}

					};
					allocateJob.setUser(false);
					allocateJob.setSystem(true);
					allocateJob.schedule();
					
					while (allocateJob.getState() == Job.RUNNING) {
						if (monitor.isCanceled()) {
							return;
						}
						Thread.sleep(500);
					}
				}
			});
		} catch (InvocationTargetException e) {
			FrontEndUIActivator.getDefault().getLog().log(
					new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to allocate capacity on Fronted Interface Device", e));
			return false;
		} catch (InterruptedException e) {
			FrontEndUIActivator.getDefault().getLog().log(
					new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to allocate capacity on Fronted Interface Device", e));
			return false;
		}

		if (result[0] == null) {
			//user cancelled the job
			return false;
		}

		if (result[0] == false) {
			MessageDialog.openError(getShell(), "The Allocation was not successful", sb.toString());
		}
		return result[0];
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
