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

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.TunerStatusUtil;
import gov.redhawk.internal.ui.port.nxmplot.handlers.PlotPortHandler;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.ui.ConnectPortWizard;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotActivator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.statushandlers.StatusManager;

import CF.DataType;

public class FeiPlotHandler extends AbstractHandler implements IHandler {
	private static final AtomicInteger SECONDARY_ID = new AtomicInteger();

	private IEvaluationContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				return null;
			}
		}

		final List< ? > elements = selection.toList();
		String listenerAllocationID = "Plot_" + ConnectPortWizard.generateDefaultConnectionID();

		for (Object obj : elements) {
			if (obj instanceof TunerStatus) {
				final TunerStatus tuner = (TunerStatus) obj;
				final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
				List<ScaPort< ? , ? >> devicePorts = device.getPorts();
				final List<ScaUsesPort> usesPorts = new ArrayList<ScaUsesPort>();
				for (ScaPort< ? , ? > port : devicePorts) {
					if (port instanceof ScaUsesPort && PlotPortHandler.isBulkIOPortSupported(port.getRepid())) {
						usesPorts.add((ScaUsesPort) port);
					}
				}
				if (usesPorts.isEmpty()) {
					Status status = new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "No valid BulkIO output ports available, can not plot from "
							+ tuner.getTunerID(), new Exception().fillInStackTrace());
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					continue;
				}

				final DataType[] props = TunerStatusUtil.createAllocationProperties(listenerAllocationID, tuner);

				final UIJob uiJob = new UIJob("Launching Plot View...") {

					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						try {
							IStatus retVal = createPlotView(event, props, device, tuner, usesPorts);
							if (!retVal.isOK()) {
								TunerStatusUtil.createDeallocationJob(tuner, props).schedule();
							}

							return retVal;
						} catch (ExecutionException e) {
							TunerStatusUtil.createDeallocationJob(tuner, props).schedule();
							return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to open plot view", e);
						}
					}

				};

				Job allocJob = TunerStatusUtil.createAllocationJob(tuner, props);
				allocJob.addJobChangeListener(new JobChangeAdapter() {
					@Override
					public void done(IJobChangeEvent event) {
						if (event.getResult().isOK()) {
							uiJob.setUser(false);
							uiJob.setSystem(true);
							uiJob.schedule();
						}
					}
				});
				allocJob.setUser(true);
				allocJob.schedule();

			}
		}

		return null;
	}

	private IStatus createPlotView(final ExecutionEvent event, final DataType[] props, final ScaDevice< ? > device, final TunerStatus tuner,
		final List<ScaUsesPort> usesPorts) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		String plotType = event.getParameter(IPlotView.PARAM_PLOT_TYPE);
		String isFft = event.getParameter(IPlotView.PARAM_ISFFT);

		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		if (usesPorts.size() > 1) {
			ListSelectionDialog dialog = new ListSelectionDialog(HandlerUtil.getActiveShellChecked(event), usesPorts, new ArrayContentProvider(),
				new AdapterFactoryLabelProvider(factory), "Select output port to use:");
			dialog.setTitle("Ambiguous Data Port");
			if (dialog.open() == Window.OK) {
				Object[] result = dialog.getResult();
				if (result.length >= 1) {
					usesPorts.retainAll(Arrays.asList(result));
				} else {
					return Status.CANCEL_STATUS;
				}
			} else {
				return Status.CANCEL_STATUS;
			}
		} else if (usesPorts.isEmpty()) {
			return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to find port to plot.");
		}

		EvaluationContext exContext = new EvaluationContext(context, usesPorts);
		exContext.addVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME, window);
		Map<String, Object> exParam = new HashMap<String, Object>();
		exParam.put(IPlotView.PARAM_PLOT_TYPE, plotType);
		exParam.put(IPlotView.PARAM_ISFFT, isFft);
		final String listenerID = TunerStatusUtil.getListenerID(props);
		exParam.put(IPlotView.PARAM_CONNECTION_ID, listenerID);
		exParam.put(IPlotView.PARAM_SECONDARY_ID, createSecondaryId());
		ICommandService svc = (ICommandService) window.getService(ICommandService.class);
		Command comm = svc.getCommand(IPlotView.COMMAND_ID);
		ExecutionEvent ex = new ExecutionEvent(comm, exParam, null, exContext);
		exContext.addVariable(ISources.ACTIVE_CURRENT_SELECTION_NAME, new StructuredSelection(usesPorts));
		exContext.addVariable(ISources.ACTIVE_MENU_SELECTION_NAME, new StructuredSelection(usesPorts));

		final StringBuilder name = new StringBuilder();
		final StringBuilder tooltip = new StringBuilder();

		createTooltip(factory, name, tooltip, usesPorts);
		factory.dispose();

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

	@Override
	public void setEnabled(Object evaluationContext) {
		if (evaluationContext instanceof IEvaluationContext) {
			this.context = (IEvaluationContext) evaluationContext;
		}
		super.setEnabled(evaluationContext);
	}

	/**
	 * Calls deallocateCapacity(DataType[] capacities) when the plot view is disposed
	 * @param props Listener Capacity to pass to deallocateCapacity()
	 * @return
	 */
	private DisposeListener getDisposeListener(final TunerStatus tuner, final DataType[] props, final ScaDevice< ? > device) {
		DisposeListener disposeListener = new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				if (TunerStatusUtil.containsListener(tuner, props)) {
					TunerStatusUtil.createDeallocationJob(tuner, props).schedule();
				}
			}
		};
		return disposeListener;
	}

	private String createSecondaryId() {
		return "FEI" + FeiPlotHandler.SECONDARY_ID.incrementAndGet();
	}

	private void createTooltip(final ScaItemProviderAdapterFactory factory, final StringBuilder name, final StringBuilder tooltip, List<ScaUsesPort> usesPorts) {
		List<String> tmpList4Tooltip = new LinkedList<String>();
		int loopCount = 0;
		for (ScaUsesPort usesPort : usesPorts) {
			loopCount++;
			if (loopCount >= 2) {
				tmpList4Tooltip.add(0, "\n");
			}
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
		}
		String delim = "";
		if (!tmpList4Tooltip.isEmpty()) {
			tooltip.append(delim);
			delim = "\n";
			for (Iterator<String> i = tmpList4Tooltip.iterator(); i.hasNext();) {
				String s = i.next();
				tooltip.append(s);
				if (i.hasNext() && !("\n").equals(s)) {
					tooltip.append(" -> ");
				}
			}

		}
	}
}
