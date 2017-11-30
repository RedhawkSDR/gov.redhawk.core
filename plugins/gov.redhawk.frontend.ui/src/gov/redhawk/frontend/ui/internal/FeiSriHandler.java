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
package gov.redhawk.frontend.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ListDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;
import gov.redhawk.bulkio.ui.views.SriDataView;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.TunerStatusUtil;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.model.jobs.AllocateJob;
import gov.redhawk.sca.model.jobs.DeallocateJob;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class FeiSriHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage activePage = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();

		// Launches from a context menu selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				return null;
			}
		}

		for (Object obj : selection.toList()) {
			if (obj instanceof TunerStatus) {
				// Get the tuner, device, port
				final TunerStatus tuner = (TunerStatus) obj;
				final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
				final ScaUsesPort usesPort = findUsesPort(HandlerUtil.getActiveShellChecked(event), device);
				if (usesPort == null) {
					continue;
				}

				String listenerID = "SRI_" + System.getProperty("user.name") + ":" + System.currentTimeMillis();

				// Create the allocation job
				final DataType[] props = TunerStatusUtil.createAllocationProperties(listenerID, tuner);
				Job allocationJob = new AllocateJob(device, props);
				allocationJob.setName("Allocate FEI listener for tuner " + tuner.getAllocationID());
				allocationJob.setUser(true);

				// Create the job to display the SRI view
				Job uiJob = new UIJob("Display SRI view") {
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						try {
							boolean opened = createSriView(activePage, device, tuner, usesPort, props, listenerID);
							if (!opened) {
								deallocate(device, props);
								return Status.CANCEL_STATUS;
							} else {
								return Status.OK_STATUS;
							}
						} catch (ExecutionException e) {
							deallocate(device, props);
							return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to open SRI Data View", e);
						}
					}
				};
				uiJob.setUser(false);
				uiJob.setSystem(true);

				// Successful allocation -> display SRI view
				allocationJob.addJobChangeListener(new JobChangeAdapter() {
					@Override
					public void done(IJobChangeEvent event) {
						if (event.getResult() != null && event.getResult().isOK()) {
							uiJob.schedule();
						}
					}
				});

				allocationJob.schedule();
			}
		}

		return null;
	}

	/**
	 * Determins which port to listen to on the device. The user will be prompted if multiple ports exist.
	 * @param shell
	 * @param device
	 * @return
	 */
	private ScaUsesPort findUsesPort(Shell shell, ScaDevice< ? > device) {
		// Get all the "supported" uses ports on the device
		List<ScaPort< ? , ? >> devicePorts;
		try {
			devicePorts = ScaModelCommand.runExclusive(device, new RunnableWithResult.Impl<List<ScaPort< ? , ? >>>() {
				@Override
				public void run() {
					setResult(new ArrayList<>(device.getPorts()));
				}
			});
		} catch (InterruptedException e) {
			return null;
		}
		List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
		for (ScaPort< ? , ? > port : devicePorts) {
			if (port instanceof ScaUsesPort && BulkIOType.isTypeSupported(port.getRepid())) {
				usesPorts.add((ScaUsesPort) port);
			}
		}

		// Pick the uses port that the SRI View will listen to
		if (usesPorts.size() == 1) {
			return usesPorts.get(0);
		} else if (usesPorts.size() > 1) {
			// If there is more than one uses port, let the user specify which one they want
			ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
			ListDialog dialog = new ListDialog(shell);
			dialog.setTitle("Display SRI");
			dialog.setMessage("Select output port to use:");
			dialog.setContentProvider(ArrayContentProvider.getInstance());
			dialog.setLabelProvider(new AdapterFactoryLabelProvider(factory));
			dialog.setInput(usesPorts);
			try {
				if (dialog.open() == Window.OK) {
					return (ScaUsesPort) dialog.getResult()[0];
				} else {
					return null;
				}
			} finally {
				factory.dispose();
			}
		} else {
			return null;
		}
	}

	/**
	 * Creates the SRI View...
	 * @param activePage The active workbench page
	 * @param props The allocation structure for the listener
	 * @param device The device that was allocated
	 * @param tuner The tuner status
	 * @param usesPort The port to attach to
	 * @return True if the view was displayed
	 */
	private boolean createSriView(IWorkbenchPage activePage, final ScaDevice< ? > device, final TunerStatus tuner, ScaUsesPort usesPort, final DataType[] props,
		String listenerID) throws ExecutionException {

		// Create unique ID for the view
		String viewID = SriDataView.createSecondaryId(usesPort, tuner.getAllocationID().split(",")[0]);

		// Display the SRI View
		SriDataView sriViewLocalRef = displaySriView(activePage, usesPort, listenerID, viewID);
		if (sriViewLocalRef == null) {
			return false;
		}

		// Deallocate listener if the view is closed first
		DisposeListener disposeListener = event -> {
			deallocate(device, props);
		};
		sriViewLocalRef.getTreeViewer().getTree().addDisposeListener(disposeListener);

		// Close the view if the listener is deallocated first
		ScaModelCommand.execute(tuner, new ScaModelCommand() {
			@Override
			public void execute() {
				for (ListenerAllocation a : tuner.getListenerAllocations()) {
					if (a.getListenerID().equals(listenerID)) {
						Adapter adapter = new AdapterImpl() {
							@Override
							public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {
								if (msg.isTouch()) {
									return;
								}
								switch (msg.getFeatureID(ListenerAllocation.class)) {
								case FrontendPackage.LISTENER_ALLOCATION__TUNER_STATUS:
									if (msg.getNewValue() == null) {
										((Notifier) msg.getNotifier()).eAdapters().remove(this);
										if (sriViewLocalRef.getTreeViewer().getTree().isDisposed()) {
											return;
										}

										sriViewLocalRef.getTreeViewer().getTree().getDisplay().asyncExec(() -> {
											sriViewLocalRef.getTreeViewer().getTree().removeDisposeListener(disposeListener);
											sriViewLocalRef.getViewSite().getPage().hideView(sriViewLocalRef);
										});
									}
									break;
								default:
									break;
								}
							};
						};
						a.eAdapters().add(adapter);
						return;
					}
				}
			}
		});

		return true;
	}

	/**
	 * Calls deallocateCapacity for the provided listener
	 * @param tuner - Containing tuner
	 * @param props - DataType[] containing the listener allocation properties
	 * @param device - Containing device
	 */
	private void deallocate(ScaDevice< ? > device, DataType[] props) {
		Job job = new DeallocateJob(device, props);
		job.setUser(false);
		job.setSystem(true);
		job.schedule();
	}

	/**
	 * Displays the SRI View...
	 * @param usesPort - Uses port that the SRI View is listening to
	 * @param connectionId - Existing connection ID for SRI View to piggyback on
	 * @param viewId - the unique ID used by the SRI view
	 * @return
	 */
	private SriDataView displaySriView(IWorkbenchPage activePage, ScaUsesPort usesPort, String connectionId, String viewId) {
		// Create the name and tooltip for the view tab
		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		final StringBuilder name = new StringBuilder();
		final StringBuilder tooltip = new StringBuilder();
		createTooltip(factory, name, tooltip, usesPort);
		factory.dispose();

		try {
			IViewPart view = activePage.showView(SriDataView.ID, viewId, IWorkbenchPage.VIEW_ACTIVATE);
			final SriDataView sriView = (SriDataView) view;
			if (name.length() > 0) {
				sriView.setPartName(name.toString());
			}
			if (tooltip.length() > 0) {
				sriView.setTitleToolTip(tooltip.toString());
			}

			Job job = new Job("SRI View setup...") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					SubMonitor progress = SubMonitor.convert(monitor, "Connect to port", 2);
					usesPort.fetchAttributes(progress.newChild(1));
					sriView.activateReceiver(usesPort, connectionId);
					progress.worked(1);
					return Status.OK_STATUS;
				}
			};
			job.schedule();

			return sriView;
		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to show SRI Data View", e),
				StatusManager.LOG | StatusManager.SHOW);
			return null;
		}
	}

	/**
	 * Creates the name and tooltip for the view
	 * @param factory
	 * @param name
	 * @param tooltip
	 * @param usesPort
	 */
	private void createTooltip(AdapterFactory factory, StringBuilder name, StringBuilder tooltip, ScaUsesPort usesPort) {
		// Build a tmp list containing the strings of all containing elements
		List<String> tmp = new LinkedList<String>();
		for (EObject eObj = usesPort; !(eObj instanceof ScaDomainManagerRegistry) && eObj != null; eObj = eObj.eContainer()) {
			Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
			if (adapter instanceof IItemLabelProvider) {
				IItemLabelProvider lp = (IItemLabelProvider) adapter;
				String text = lp.getText(eObj);
				if (text != null && !text.isEmpty()) {
					tmp.add(0, text);
				}
			}
		}

		// Create the view tab tooltip
		if (!tmp.isEmpty()) {
			for (Iterator<String> i = tmp.iterator(); i.hasNext();) {
				tooltip.append(i.next());
				if (i.hasNext()) {
					tooltip.append(" -> "); // seperator between elements
				} else {
					tooltip.append(" -> SRI ");
				}
			}
			tooltip.append("\n");
		}

		// Create the view tab text
		String nameStr = usesPort.getName();
		if (nameStr != null && !nameStr.isEmpty()) {
			name.append(nameStr + " SRI");
		}
	}
}
