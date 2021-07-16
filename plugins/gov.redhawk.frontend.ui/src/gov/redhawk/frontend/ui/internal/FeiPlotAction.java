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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.dialogs.ListSelectionDialog;
import org.eclipse.ui.progress.UIJob;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.frontend.ui.internal.section.FrontendSection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class FeiPlotAction extends FrontendAction {

	private static final AtomicInteger SECONDARY_ID = new AtomicInteger();

	public FeiPlotAction(FrontendSection theSection) {
		super(theSection, "Plot", "gov.redhawk.frontend.actions.plot", "gov.redhawk.ui.port.nxmplot.command.plot", "icons/plot.gif");
	}

	@Override
	public void run() {
		EObject obj = getSection().getInput();
		if (obj instanceof TunerStatus) {
			final TunerStatus tuner = (TunerStatus) obj;
			final ScaDevice< ? > device = ScaEcoreUtils.getEContainerOfType(tuner, ScaDevice.class);
			String connectionID = "Plot_" + System.getProperty("user.name") + ":" + System.currentTimeMillis();

			UIJob uiJob = new UIJob("Launching Plot View...") {

				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					try {
						IStatus retVal = createPlotView(connectionID, device, tuner);
						return retVal;
					} catch (ExecutionException e) {
						return new Status(IStatus.ERROR, FrontEndUIActivator.PLUGIN_ID, "Failed to open plot view", e);
					}
				}

			};
			uiJob.setUser(false);
			uiJob.setSystem(true);
			uiJob.schedule();
		}
	}

	private IStatus createPlotView(final String connectionID, final ScaDevice< ? > device, final TunerStatus tuner) throws ExecutionException {
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
		exParam.put(IPlotView.PARAM_CONNECTION_ID, connectionID);
		exParam.put(IPlotView.PARAM_SECONDARY_ID, createSecondaryId());

		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		ICommandService svc = (ICommandService) window.getService(ICommandService.class);
		Command comm = svc.getCommand(IPlotView.COMMAND_ID);
		ExecutionEvent ex = new ExecutionEvent(comm, exParam, null, exContext);
		final IPlotView view = PlotActivator.getDefault().showPlotView(ex);
		if (view != null) {
			view.setPartName(name.toString());
			view.setTitleToolTip(tooltip.toString());
		} else {
			return Status.CANCEL_STATUS;
		}

		return Status.OK_STATUS;
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
