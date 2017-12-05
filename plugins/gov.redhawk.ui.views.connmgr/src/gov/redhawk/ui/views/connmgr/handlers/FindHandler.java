/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.connmgr.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.ui.progress.UIJob;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.ui.views.connmgr.ConnMgrPlugin;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

public class FindHandler extends AbstractHandler {

	private static final String EXPLORER_VIEW_ID = "gov.redhawk.ui.sca_explorer";

	/**
	 * A parameter that must be supplied to the handler indicating whether to locate the source or the target of the
	 * connection.
	 */
	private static final String PARAM_SOURCE_OR_TARGET = "gov.redhawk.ui.views.connmgr.sourceOrTarget";
	private static final String PARAM_VALUE_SOURCE = "source";
	private static final String PARAM_VALUE_TARGET = "target";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = HandlerUtil.getActiveShellChecked(event);
		IWorkbenchPage activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event).getActivePage();
		IStructuredSelection ss = HandlerUtil.getCurrentStructuredSelection(event);
		ConnectionStatus connectStatus = (ConnectionStatus) ss.getFirstElement();

		// Based on whether we're finding source or target grab the right info
		String entityName, portName, endpointIor;
		switch (event.getParameter(PARAM_SOURCE_OR_TARGET)) {
		case PARAM_VALUE_SOURCE:
			entityName = connectStatus.getSourceEntityName();
			portName = connectStatus.getSourcePortName();
			endpointIor = connectStatus.getSourceIOR();
			if (endpointIor == null) {
				MessageDialog.open(MessageDialog.ERROR, shell, "Source unavailable", "This connection does not have a source.", SWT.NONE);
				return null;
			}
			break;
		case PARAM_VALUE_TARGET:
			entityName = connectStatus.getTargetEntityName();
			portName = connectStatus.getTargetPortName();
			endpointIor = connectStatus.getTargetIOR();
			if (endpointIor == null) {
				MessageDialog.open(MessageDialog.ERROR, shell, "Target unavailable", "This connection does not have a target.", SWT.NONE);
				return null;
			}
			break;
		default:
			throw new ExecutionException("Required parameter " + PARAM_SOURCE_OR_TARGET + " was not provided");
		}

		Job findDeviceJob = Job.create("Find object in explorer view", monitor -> {
			final int WORK_QUICK = 10;
			final int WORK_LONG = 90;
			SubMonitor progress = SubMonitor.convert(monitor, WORK_QUICK + WORK_LONG);

			// Quick search - find only if it's already in the model
			ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(null);
			List<ScaDomainManager> domMgrs = getConnectedDomains(registry);
			CorbaObjWrapper< ? > objWrapper = search(progress.split(WORK_QUICK), domMgrs, entityName, portName, endpointIor);
			if (objWrapper != null) {
				setExplorerSelectionAsync(shell.getDisplay(), activeWorkbenchWindow, objWrapper);
				return Status.OK_STATUS;
			}

			// Long search - perform fetches for the model and find the object
			objWrapper = fetchAndSearch(progress.split(WORK_LONG), domMgrs, entityName, portName, endpointIor);
			if (objWrapper != null) {
				setExplorerSelectionAsync(shell.getDisplay(), activeWorkbenchWindow, objWrapper);
				return Status.OK_STATUS;
			}

			return new Status(IStatus.ERROR, ConnMgrPlugin.ID, "The object associated with the connection cannot be found in any of the connected domains.");
		});
		findDeviceJob.setUser(true);
		findDeviceJob.schedule();

		return null;
	}

	/**
	 * Search the model for the requested object.
	 * @param progress
	 * @param domMgrs The connected domain managers to search
	 * @param entityName
	 * @param portName
	 * @param endpointIor
	 * @return The model object or null if not found
	 */
	private CorbaObjWrapper< ? > search(SubMonitor progress, List<ScaDomainManager> domMgrs, String entityName, String portName, String endpointIor) {
		progress.setWorkRemaining(domMgrs.size());

		// This EMF switch is used to match/locate the object.
		ConnMgrEntrySwitch scaSwitch = new ConnMgrEntrySwitch(entityName, portName, endpointIor);

		for (ScaDomainManager domMgr : domMgrs) {
			SubMonitor domMgrProgress = progress.split(1);

			CorbaObjWrapper< ? > objToSelect = ScaModelCommand.runExclusive(domMgr, () -> {
				CorbaObjWrapper< ? > result = scaSwitch.doSwitch(domMgr);
				if (result != null) {
					return result;
				}

				for (ScaDeviceManager devMgr : domMgr.getDeviceManagers()) {
					result = scaSwitch.doSwitch(devMgr);
					if (result != null) {
						return result;
					}

					for (ScaDevice< ? > device : new FeatureMapList<>(devMgr.getDevices(), ScaDevice.class)) {
						result = scaSwitch.doSwitch(device);
						if (result != null) {
							return result;
						}
					}

					for (ScaService service : devMgr.getServices()) {
						result = scaSwitch.doSwitch(service);
						if (result != null) {
							return result;
						}
					}
				}

				for (ScaWaveform waveform : domMgr.getWaveforms()) {
					result = scaSwitch.doSwitch(waveform);
					if (result != null) {
						return result;
					}

					for (ScaComponent component : waveform.getComponents()) {
						result = scaSwitch.doSwitch(component);
						if (result != null) {
							return result;
						}
					}
				}

				for (ScaEventChannel eventChannel : domMgr.getEventChannels()) {
					result = scaSwitch.doSwitch(eventChannel);
					if (result != null) {
						return result;
					}
				}

				return null;
			});
			domMgrProgress.done();

			if (objToSelect != null) {
				return objToSelect;
			}
		}

		return null;
	}

	/**
	 * Perform an aggressive fetch and search across the model looking for the requested object. This may take a while.
	 * @param progress
	 * @param domMgrs The connected domain managers to search
	 * @param entityName
	 * @param portName
	 * @param endpointIor
	 * @return
	 */
	private CorbaObjWrapper< ? > fetchAndSearch(SubMonitor progress, List<ScaDomainManager> domMgrs, String entityName, String portName, String endpointIor) {
		progress.setWorkRemaining(domMgrs.size());

		// This EMF switch is used to match/locate the object.
		ConnMgrEntrySwitch scaSwitch = new ConnMgrEntrySwitch(entityName, portName, endpointIor);

		for (ScaDomainManager domMgr : domMgrs) {
			SubMonitor domMgrProgress = progress.split(1);

			// Check domain manager
			CorbaObjWrapper< ? > domMgrResult = ScaModelCommand.runExclusive(domMgr, () -> {
				return scaSwitch.doSwitch(domMgr);
			});
			if (domMgrResult != null) {
				return domMgrResult;
			}

			// If requested, fetch children and attributes we care about
			domMgrProgress.setWorkRemaining(100);

			for (ScaDeviceManager devMgr : domMgr.fetchDeviceManagers(domMgrProgress.split(1), RefreshDepth.NONE)) {
				try {
					devMgr.refresh(domMgrProgress.split(1), RefreshDepth.FULL);
				} catch (InterruptedException e) {
					throw new OperationCanceledException();
				}

				CorbaObjWrapper< ? > objToSelect = ScaModelCommand.runExclusive(devMgr, () -> {
					CorbaObjWrapper< ? > result = scaSwitch.doSwitch(devMgr);
					if (result != null) {
						return result;
					}

					for (ScaDevice< ? > device : new FeatureMapList<>(devMgr.getDevices(), ScaDevice.class)) {
						result = scaSwitch.doSwitch(device);
						if (result != null) {
							return result;
						}
					}

					for (ScaService service : devMgr.getServices()) {
						result = scaSwitch.doSwitch(service);
						if (result != null) {
							return result;
						}
					}

					return null;
				});
				if (objToSelect != null) {
					return objToSelect;
				}
			}

			for (ScaWaveform waveform : domMgr.fetchWaveforms(domMgrProgress.split(1), RefreshDepth.NONE)) {
				try {
					waveform.refresh(domMgrProgress.split(1), RefreshDepth.FULL);
				} catch (InterruptedException e) {
					throw new OperationCanceledException();
				}

				CorbaObjWrapper< ? > objToSelect = ScaModelCommand.runExclusive(waveform, () -> {
					CorbaObjWrapper< ? > result = scaSwitch.doSwitch(waveform);
					if (result != null) {
						return result;
					}

					for (ScaComponent component : waveform.getComponents()) {
						result = scaSwitch.doSwitch(component);
						if (result != null) {
							return result;
						}
					}

					return null;
				});
				if (objToSelect != null) {
					return objToSelect;
				}
			}

			for (ScaEventChannel eventChannel : domMgr.fetchEventChannels(domMgrProgress.split(1), RefreshDepth.SELF)) {
				CorbaObjWrapper< ? > objToSelect = ScaModelCommand.runExclusive(eventChannel, () -> {
					CorbaObjWrapper< ? > result = scaSwitch.doSwitch(eventChannel);
					if (result != null) {
						return result;
					}

					return null;
				});
				if (objToSelect != null) {
					return objToSelect;
				}
			}
		}

		return null;
	}

	/**
	 * @return A list of all connected domains
	 */
	protected List<ScaDomainManager> getConnectedDomains(ScaDomainManagerRegistry registry) {
		List<ScaDomainManager> domMgrs = ScaModelCommand.runExclusive(registry, () -> {
			return registry.getDomains().stream() //
					.filter(domMgr -> domMgr.isConnected()) //
					.collect(Collectors.toList());
		});
		return domMgrs;
	}

	/**
	 * Show the explorer view and set its selection.
	 * @param display
	 * @param activeWorkbenchWindow
	 * @param selection The object to select
	 */
	protected void setExplorerSelectionAsync(Display display, IWorkbenchPage activeWorkbenchWindow, EObject selection) {
		Job job = new UIJob(display, "Show device in explorer view") {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				try {
					ISetSelectionTarget view = (ISetSelectionTarget) activeWorkbenchWindow.showView(EXPLORER_VIEW_ID, null, IWorkbenchPage.VIEW_ACTIVATE);
					view.selectReveal(new StructuredSelection(selection));
				} catch (PartInitException e) {
					IStatus status = new Status(IStatus.ERROR, ConnMgrPlugin.ID, "Unable to show explorer view", e);
					return status;
				}
				return Status.OK_STATUS;
			}
		};
		job.setUser(true);
		job.setPriority(Job.INTERACTIVE);
		job.schedule();
	}
}
