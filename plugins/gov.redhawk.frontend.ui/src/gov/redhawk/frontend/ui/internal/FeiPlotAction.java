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

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
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
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
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
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.progress.UIJob;

import CF.DataType;
import CF.PropertiesHelper;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

/**
 * 
 */
public class FeiPlotAction extends FrontendAction {

	private static final AtomicInteger SECONDARY_ID = new AtomicInteger();
//	private IEvaluationContext context;

	public FeiPlotAction(FrontendSection theSection) {
		super(theSection, "Plot", "gov.redhawk.frontend.actions.plot", "gov.redhawk.ui.port.nxmplot.command.plot", "icons/plot.gif");
	}
	
	@Override
	public void run() {
		EObject obj = getSection().getInput();
		if (obj instanceof TunerStatus) {
			final TunerStatus tuner = (TunerStatus) obj;
			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
			final DataType[] props = createAllocationProperties(tuner);
			Job job = new Job("Plotting tuner " + tuner.getAllocationID()) {
				@Override
				protected IStatus run(IProgressMonitor parentMonitor) {
					final SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Plotting tuner " + tuner.getAllocationID(), IProgressMonitor.UNKNOWN);
					try {
						IStatus status = CorbaUtils.invoke(new Callable<IStatus>() {

							@Override
							public IStatus call() throws Exception {
								try {
									subMonitor.subTask("Allocating capacity...");
									if (device.allocateCapacity(props)) {
										return Status.OK_STATUS;
									} else {
										return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Allocation failed, plot could not launch.", null);
									}
								} catch (InvalidCapacity e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in plot allocation: " + e.msg, e);
								} catch (InvalidState e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in plot allocation: " + e.msg, e);
								} catch (InsufficientCapacity e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Insufficient Capacity in plot allocation: " + e.msg, e);
								}
							}

						}, subMonitor.newChild(1));
						if (!status.isOK()) {
							return status;
						}
						subMonitor.subTask("Refeshing device...");
						device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
					} catch (InterruptedException e) {
						return Status.CANCEL_STATUS;
					} catch (CoreException e) {
						return e.getStatus();
					} finally {
						subMonitor.done();
					}
					UIJob uiJob = new UIJob("Launching Plot View...") {

						@Override
						public IStatus runInUIThread(IProgressMonitor monitor) {
							try {
								IStatus retVal = createPlotView(props, device, tuner);
								if (!retVal.isOK()) {
									deallocate(tuner, props, device);
								}

								return retVal;
							} catch (ExecutionException e) {
								deallocate(tuner, props, device);
								return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to open plot view", e);
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
	
	private DataType[] createAllocationProperties(TunerStatus tuner) {
		List<DataType> listenerCapacity = new ArrayList<DataType>();
		ScaStructProperty struct;
		DataType dt = new DataType();
		struct = ScaFactory.eINSTANCE.createScaStructProperty();
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
				String listenerAllocationID = "Plot_" + System.getProperty("user.name") + ":" + System.currentTimeMillis();
				simple.setValue(listenerAllocationID);
				break;
			default:
			}
			struct.getSimples().add(simple);
		}
		dt.id = "FRONTEND::listener_allocation";
		dt.value = struct.toAny();
		listenerCapacity.add(dt);
		return listenerCapacity.toArray(new DataType[0]);
	}

	private IStatus createPlotView(final DataType[] props, final ScaDevice< ? > device, final TunerStatus tuner)
			throws ExecutionException {
		List<ScaPort< ? , ? >> devicePorts = device.getPorts();
		List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
		for (ScaPort< ? , ? > port : devicePorts) {
			if (port instanceof ScaUsesPort) {
				usesPorts.add((ScaUsesPort) port);
			}
		}
		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		final ScaUsesPort usesPort;
		if (usesPorts.size() == 1) {
			usesPort = usesPorts.get(0);
		} else if (usesPorts.size() > 1) {
			ListSelectionDialog dialog = new ListSelectionDialog(Display.getCurrent().getActiveShell(), usesPorts, new ArrayContentProvider(),
				new AdapterFactoryLabelProvider(factory), "Select output port to use:");
			if (dialog.open() == Window.OK) {
				Object[] result = dialog.getResult();
				if (result.length >= 1) {
					usesPort = (ScaUsesPort) result[0];
				} else {
					usesPort = null;
				}
			} else {
				usesPort = null;
			}
		} else {
			usesPort = null;
		}

		if (usesPort == null) {
			return Status.CANCEL_STATUS;
		}

		final StringBuilder name = new StringBuilder();
		final StringBuilder tooltip = new StringBuilder();

		createTooltip(factory, name, tooltip, usesPort);
		factory.dispose();

		// Connect the port
		final List<ScaUsesPort> ports = new ArrayList<ScaUsesPort>();
		ports.add(usesPort);
//		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
//		ICommandService svc = (ICommandService) window.getService(ICommandService.class);
//		Command comm = svc.getCommand(IPlotView.COMMAND_ID);
//		final ExecutionEvent evt;
//		evt = handlerService.createExecutionEvent(comm, null);
//		
//		EvaluationContext exContext = new EvaluationContext((IEvaluationContext) evt.getApplicationContext(), ports);
		EvaluationContext exContext = new EvaluationContext(null, ports);
		exContext.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(ports));
		exContext.addVariable(ISources.ACTIVE_MENU_SELECTION_NAME, new StructuredSelection(ports));
		exContext.addVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME, PlatformUI.getWorkbench().getActiveWorkbenchWindow());

		Map<String, Object> exParam = new HashMap<String, Object>();
		exParam.put(IPlotView.PARAM_PLOT_TYPE, "LINE");
		exParam.put(IPlotView.PARAM_ISFFT, "false");
		final String listenerID = getListenerID(props);
		exParam.put(IPlotView.PARAM_CONNECTION_ID, listenerID);
		exParam.put(IPlotView.PARAM_SECONDARY_ID, createSecondaryId());

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ICommandService svc = (ICommandService) window.getService(ICommandService.class);
		Command comm = svc.getCommand(IPlotView.COMMAND_ID);
		ExecutionEvent ex = new ExecutionEvent(comm, exParam, null, exContext);
		final IPlotView view = PlotActivator.getDefault().showPlotView(ex);
		if (view != null) {
			view.setPartName(name.toString());
			view.setTitleToolTip(tooltip.toString());
			view.getPlotPageBook().addDisposeListener(getDisposeListener(tuner, props, device));

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
											if (view.getPlotPageBook().isDisposed()) {
												return;
											}
											view.getPlotPageBook().getDisplay().asyncExec(new Runnable() {

												@Override
												public void run() {
													view.getPlotPageBook().dispose();
												}

											});
										}
										break;
									default:
									}
								}
							});
						}
					}
				}
			});
		} else {
			return Status.CANCEL_STATUS;
		}

		return Status.OK_STATUS;
	}

	private DisposeListener getDisposeListener(final TunerStatus tuner, final DataType[] props, final ScaDevice< ? > device) {
		DisposeListener disposeListener = new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (containsListener(tuner, props)) {
					deallocate(tuner, props, device);
				}
			}
		};
		return disposeListener;
	}

	private boolean containsListener(TunerStatus tuner, DataType[] props) {
		String listenerId = getListenerID(props);
		for (ListenerAllocation a : tuner.getListenerAllocations()) {
			if (a.getListenerID().equals(listenerId)) {
				return true;
			}
		}
		return false;
	}

	private void deallocate(final TunerStatus tuner, final DataType[] props, final ScaDevice< ? > device) {
		if (!containsListener(tuner, props)) {
			return;
		}
		Job job = new Job("Fei Deallocate Listener") {
			@Override
			protected IStatus run(IProgressMonitor parentMonitor) {
				if (!containsListener(tuner, props)) {
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
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in plot deallocation: " + e.msg, e);
								} catch (InvalidState e) {
									return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in plot deallocation: " + e.msg, e);
								}
							}

						}, subMonitor.newChild(1));
						device.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
					}
				} catch (InterruptedException e) {
					return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during plot deallocation", e);
				} catch (CoreException e) {
					return e.getStatus();
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(false);
		job.setSystem(true);
		job.schedule();
	}

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

	private String createSecondaryId() {
		return "FEI" + SECONDARY_ID.incrementAndGet();
	}

	private void createTooltip(final ScaItemProviderAdapterFactory factory, final StringBuilder name, final StringBuilder tooltip, ScaUsesPort usesPort) {
		List<String> tmpList4Tooltip = new LinkedList<String>();
		for (EObject eObj = usesPort; !(eObj instanceof ScaDomainManagerRegistry) && eObj != null; eObj = eObj.eContainer()) {
			Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
			if (adapter instanceof IItemLabelProvider) {
				IItemLabelProvider lp = (IItemLabelProvider) adapter;
				String text = lp.getText(eObj);
				if (text != null && !text.isEmpty()) {
					tmpList4Tooltip.add(0, text);
				}
			}
		}

		String nameStr = usesPort.getName();
		if (nameStr != null && !nameStr.isEmpty()) {
			name.append(nameStr).append(" ");
		}

		if (!tmpList4Tooltip.isEmpty()) {
			for (Iterator<String> i = tmpList4Tooltip.iterator(); i.hasNext();) {
				tooltip.append(i.next());
				if (i.hasNext()) {
					tooltip.append(" -> ");
				}
			}
			tooltip.append("\n");
		}
	}
}
