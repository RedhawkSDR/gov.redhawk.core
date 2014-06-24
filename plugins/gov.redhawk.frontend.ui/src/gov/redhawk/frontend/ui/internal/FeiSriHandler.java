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

//TODO: reflection for access to the SriDataView code
import gov.redhawk.bulkio.ui.views.SriDataView;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.util.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;
import CF.PropertiesHelper;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

public class FeiSriHandler extends AbstractHandler implements IHandler {
	private SriDataView sriViewLocalRef; // Needed for declaration of dispose logic
	private IWorkbenchWindow window;
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		window = HandlerUtil.getActiveWorkbenchWindow(event);

		// Launches from the plot view menu
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		if (activePart instanceof IPlotView) {
			IPlotView plotView = (IPlotView) HandlerUtil.getActivePart(event);
			List<PlotSource> sources = plotView.getPlotPageBook().getSources();
			for (PlotSource source : sources) {
				final ScaUsesPort usesPort = source.getInput();
				final String connectionId = source.getBulkIOBlockSettings().getConnectionID();
				IStatus retVal = displaySriView(event, usesPort, connectionId);
				return retVal;
			}
		}

		// Launches from a context menu selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				return null;
			}
		}

		final List< ? > elements = selection.toList();
		if (elements.isEmpty()) {
			return null;
		}

		for (Object obj : elements) {
			if (obj instanceof TunerStatus) {
				// Get the tuner
				final TunerStatus tuner = (TunerStatus) obj;
				// Get the containing device for the tuner
				final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);

				// Create the allocation property structure
				final DataType[] props = createAllocationProperties(tuner);

				// Core Job that handles capacity allocation and displaying the view
				Job job = new Job("Displaying SRI Data for " + tuner.getAllocationID()) {
					@Override
					protected IStatus run(IProgressMonitor parentMonitor) {
						final SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Displaying SRI Data for " + tuner.getAllocationID(),
							IProgressMonitor.UNKNOWN);

						try {
							// First check to see if a SRI View is already listening on this tuner
							for (ListenerAllocation listener : tuner.getListenerAllocations()) {
								if (listener.getListenerID().startsWith("SRI")) {
									return Status.OK_STATUS;
								}
							}

							// Allocate capacity on the device for the listener if no SRI View is found
							IStatus status = CorbaUtils.invoke(new Callable<IStatus>() {
								@Override
								public IStatus call() throws Exception {
									try {
										subMonitor.subTask("Allocating capacity...");
										if (device.allocateCapacity(props)) {
											return Status.OK_STATUS;
										} else {
											return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Allocation failed, SRI data could not display.",
												null);
										}
									} catch (InvalidCapacity e) {
										return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in SRI View allocation: " + e.msg, e);
									} catch (InvalidState e) {
										return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in SRI View allocation: " + e.msg, e);
									} catch (InsufficientCapacity e) {
										return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Insufficient Capacity in SRI View allocation: "
											+ e.msg, e);
									}
								}
							}, subMonitor.newChild(1));
							if (!status.isOK()) {
								return status;
							}
							subMonitor.subTask("Refreshing device...");
							device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
						} catch (InterruptedException e) {
							return Status.CANCEL_STATUS;
						} catch (CoreException e) {
							return new Status(e.getStatus().getSeverity(), FrontEndUIActivator.PLUGIN_ID, "Failed to allocate for SRI", e);
						} finally {
							subMonitor.done();
						}

						// Display the SRI View
						UIJob uiJob = new UIJob("Launching SRI View...") {
							@Override
							public IStatus runInUIThread(IProgressMonitor monitor) {
								try {
									IStatus retVal = createSriView(event, props, elements, device, tuner);

									// If the view was not created successfully then deallocate
									if (!retVal.isOK()) {
										deallocate(tuner, props, device);
									}
									return retVal;
								} catch (ExecutionException e) {
									deallocate(tuner, props, device);
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to open SRI Data View", e);
								}
							}
						};
						uiJob.setUser(false);
						uiJob.setSystem(true);
						uiJob.schedule();
						return Status.OK_STATUS;
					}
				};
				job.setUser(true);
				job.schedule();
			}
		}
		return null;
	}

	/**
	 * Creates the SRI View...
	 * @param event - The original execution event
	 * @param props - The allocation structure for the listener
	 * @param elements - List containing the selection context
	 * @param device - Containing Device
	 * @param tuner - Containing Tuner
	 * @return IStatus for whether or not the view was created successfully
	 */
	private IStatus createSriView(final ExecutionEvent event, final DataType[] props, final List< ? > elements, final ScaDevice< ? > device,
		final TunerStatus tuner) throws ExecutionException {
		// Get all SCA Ports on the containing device
		List<ScaPort< ? , ? >> devicePorts = device.getPorts();
		// Get all the "supported" uses ports from the preceding list
		List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
		for (ScaPort< ? , ? > port : devicePorts) {
			if (port instanceof ScaUsesPort && BulkIOType.isTypeSupported(port.getRepid())) {
				usesPorts.add((ScaUsesPort) port);
			}
		}

		// Assign the uses port that the SRI View will listen to
		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		final ScaUsesPort usesPort;
		if (usesPorts.size() == 1) {
			usesPort = usesPorts.get(0);
		} else if (usesPorts.size() > 1) {
			// If there is more than one uses port, let the user specify which one they want
			ListSelectionDialog dialog = new ListSelectionDialog(HandlerUtil.getActiveShellChecked(event), usesPorts,
				ArrayContentProvider.getInstance(), new AdapterFactoryLabelProvider(factory), "Select output Port to use: ");
			if (dialog.open() == Window.OK) {
				Object[] result = dialog.getResult();
				if (result.length >= 1) {
					// Assume the first selected port
					usesPort = (ScaUsesPort) result[0];
				} else {
					// User did not select a uses port
					usesPort = null;
				}
			} else {
				// User selected Cancel
				usesPort = null;
			}
		} else {
			// There are no uses ports for this device
			usesPort = null;
		}

		if (usesPort == null) {
			return Status.CANCEL_STATUS;
		}

		// Display the SRI View
		final String listenerID = getListenerID(props);
		IStatus retVal = displaySriView(event, usesPort, listenerID);

		// Declare deallocate logic
		if (sriViewLocalRef != null) {
			// Deallocate listener if the view is closed first
			sriViewLocalRef.getTreeViewer().getTree().addDisposeListener(new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (containsListener(tuner, props)) {
						deallocate(tuner, props, device);

					}
				}
			});

			// Close the view if the listener is deallocated first
			ScaModelCommand.execute(tuner, new ScaModelCommand() {
				@Override
				public void execute() {
					for (ListenerAllocation a : tuner.getListenerAllocations()) {
						if (a.getListenerID().equals(listenerID)) {
							a.eAdapters().add(new AdapterImpl() {
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

											sriViewLocalRef.getTreeViewer().getTree().getDisplay().asyncExec(new Runnable() {
												@Override
												public void run() {
													sriViewLocalRef.getViewSite().getPage().hideView(sriViewLocalRef);
												}
											});
										}
										break;
									default:
										break;
									}
								};
							});
						}
					}
				}
			});
		}

		return retVal;
	}

	/**
	 * Creates the allocation structure that can be passed to the allocateCapacity(DataType[] capacities) method.
	 * Structure consists of a DataType containing a struct (cast to Any) which contains a single
	 * ScaSimpleProperty which defines the listener allocation properties.
	 * @param tuner - The containing tuner object to which the listener is tied
	 * @return listenerCapacity as a DataType array. Contains values for Existing_Allocation_ID and
	 * Listener_Allocation_ID
	 */
	private DataType[] createAllocationProperties(TunerStatus tuner) {
		List<DataType> listenerCapacity = new ArrayList<DataType>();
		DataType dt = new DataType();
		ScaStructProperty struct = ScaFactory.eINSTANCE.createScaStructProperty();

		// Cycle through enum of listener allocation properties and set values
		for (ListenerAllocationProperties allocProp : ListenerAllocationProperties.values()) {
			ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
			Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
			definition.setType(allocProp.getType());
			definition.setId(allocProp.getType().getLiteral());
			definition.setName(allocProp.getType().getName());
			simple.setDefinition(definition);
			simple.setId(allocProp.getId());

			switch (allocProp) {
			case EXISTING_ALLOCATION_ID:
				simple.setValue(TunerUtils.getControlId(tuner));
				break;
			case LISTENER_ALLOCATION_ID:
				String listenerAllocationID = "SRI_" + System.getProperty("user.name") + ":" + System.currentTimeMillis();
				simple.setValue(listenerAllocationID);
				break;
			default:
				break;
			}
			struct.getSimples().add(simple);
		}
		dt.id = "FRONTEND::listener_allocation";
		dt.value = struct.toAny();
		listenerCapacity.add(dt);
		return listenerCapacity.toArray(new DataType[0]);
	}

	/**
	 * Calls deallocateCapacity for the provided listener
	 * @param tuner - Containing tuner
	 * @param props - DataType[] containing the listener allocation properties
	 * @param device - Containing device
	 */
	private void deallocate(TunerStatus tuner, final DataType[] props, final ScaDevice< ? > device) {
		// First, confirm that the allocation properties contains a reference to a listener that is in the tuner
		if (!containsListener(tuner, props)) {
			return;
		}

		Job job = new Job("FEI  - Deallocate Listener") {
			@Override
			protected IStatus run(IProgressMonitor parentMonitor) {
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
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in SRI View deallocation: " + e.msg, e);
								} catch (InvalidState e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in SRI View deallocation: " + e.msg, e);
								}
							}
						}, subMonitor.newChild(1));
						device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
					}
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during SRI View deallocation", e);
				} catch (CoreException e) {
					return new Status(e.getStatus().getSeverity(), FrontEndUIActivator.PLUGIN_ID, "Failed to deallocate", e);
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(false);
		job.setSystem(true);
		job.schedule();
	}

	/**
	 * Convenience method that extracts the listener ID from the allocation properties structure
	 * @param props - The allocation structure for the listener
	 * @return listener allocation ID as a String or empty string is a listener allocation ID is not found
	 */
	private String getListenerID(DataType[] props) {
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

	/**
	 * Checks to see if the provided allocation properties contain a reference to
	 * a listener that is in the provided tuner
	 * @param tuner - Containing tuner
	 * @param props - DataType[] containing the allocation properties
	 * @return
	 */
	private boolean containsListener(TunerStatus tuner, DataType[] props) {
		String listenerId = getListenerID(props);
		for (ListenerAllocation a : tuner.getListenerAllocations()) {
			if (a.getListenerID().equals(listenerId)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Displays the SRI View...
	 * @param event - Original execution event
	 * @param usesPort - Uses port that the SRI View is listening to
	 * @param connectionId - Existing connection ID for SRI View to piggyback on
	 * @return
	 */
	public IStatus displaySriView(final ExecutionEvent event, final ScaUsesPort usesPort, final String connectionId) {
		// Create the name and tooltip for the view tab
		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		final StringBuilder name = new StringBuilder();
		final StringBuilder tooltip = new StringBuilder();
		createTooltip(factory, name, tooltip, usesPort);
		try {
			IViewPart view = window.getActivePage().showView(SriDataView.ID, SriDataView.createSecondaryId(usesPort), IWorkbenchPage.VIEW_ACTIVATE);

			final SriDataView sriView = (SriDataView) view;

			Job job = new Job("SRI View setup...") {
				@Override
				protected IStatus run(IProgressMonitor monitor) {
					SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching SRI...", IProgressMonitor.UNKNOWN);
					usesPort.fetchAttributes(subMonitor.newChild(1));

					// Connect to the port
					sriView.activateReceiver(usesPort, connectionId);

					// Set view name and tooltip
					if (name.length() > 0 || tooltip.length() > 0) {
						Display display = window.getWorkbench().getDisplay();
						display.asyncExec(new Runnable() {
							@Override
							public void run() {
								if (name.length() > 0) {
									sriView.setPartName(name.toString());
								}
								if (tooltip.length() > 0) {
									sriView.setTitleToolTip(tooltip.toString());
								}
							}
						});
					}
					return Status.OK_STATUS;
				}
			};
			job.schedule();

			// Set local reference to SRI Tree Viewer to give us a widget for dispose logic
			sriViewLocalRef = sriView;

		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to show SRI Data View", e),
				StatusManager.LOG | StatusManager.SHOW);
		}
		return Status.OK_STATUS;
	}

	/**
	 * Creates the name and tooltip for the view
	 * @param factory
	 * @param name
	 * @param tooltip
	 * @param usesPort
	 */
	private void createTooltip(ScaItemProviderAdapterFactory factory, StringBuilder name, StringBuilder tooltip, ScaUsesPort usesPort) {
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
