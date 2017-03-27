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
package gov.redhawk.sca.dcd.diagram.adapters;

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.statushandlers.StatusManager;

public class DcdComponentInstantiationEditPartAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class< ? >[] { ScaDevice.class, ScaAbstractComponent.class, ScaPropertyContainer.class };

	@Override
	public <T> T getAdapter(final Object adaptableObject, final Class<T> adapterType) {
		if (adaptableObject instanceof GraphicalEditPart) {
			final GraphicalEditPart editPart = (GraphicalEditPart) adaptableObject;
			final DcdComponentInstantiation ci = (DcdComponentInstantiation) editPart.getAdapter(ComponentInstantiation.class);

			if (ci != null && ci.eResource() != null) {
				if (adapterType == ScaDevice.class || adapterType == ScaAbstractComponent.class || adapterType == ScaPropertyContainer.class) {
					final URI uri = ci.eResource().getURI();
					final Map<String, String> query = QueryParser.parseQuery(uri.query());
					final String ior = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
					final ScaDeviceManager manager = ScaModelPlugin.getDefault().findEObject(ScaDeviceManager.class, ior);
					final String deviceId = ci.getId();
					if (manager != null) {
						if (!manager.isSetDevices()) {
							if (Display.getCurrent() != null) {
								ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
								try {
									dialog.run(true, true, new IRunnableWithProgress() {

										@Override
										public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
											monitor.beginTask("Fetching devices for " + manager.getLabel(), IProgressMonitor.UNKNOWN);
											try {
												CorbaUtils.invoke(new Callable<Object>() {

													public List<ScaDevice< ? >> call() throws Exception {
														manager.fetchDevices(monitor, RefreshDepth.SELF);
														return null;
													}
												}, monitor);
											} catch (CoreException e) {
												throw new InvocationTargetException(e);
											}
										}
									});
								} catch (InvocationTargetException e) {
									StatusManager.getManager().handle(
										new Status(Status.ERROR, DcdDiagramPluginActivator.PLUGIN_ID, "Failed to fetch devices for " + manager.getLabel(), e),
										StatusManager.SHOW | StatusManager.LOG);
								} catch (InterruptedException e) {
									// PASS
								}

							} else {
								try {
									ProtectedThreadExecutor.submit(new Runnable() {

										public void run() {
											manager.fetchDevices(null, RefreshDepth.SELF);
										}
									});
								} catch (final InterruptedException e) {
									// PASS
								} catch (final ExecutionException e) {
									StatusManager.getManager().handle(
										new Status(Status.ERROR, DcdDiagramPluginActivator.PLUGIN_ID, "Failed to fetch devices for " + manager.getLabel(), e),
										StatusManager.SHOW | StatusManager.LOG);
								} catch (final TimeoutException e) {
									StatusManager.getManager().handle(
										new Status(Status.WARNING, DcdDiagramPluginActivator.PLUGIN_ID, "Timed out trying to fetch devices for " + manager.getLabel(), e),
										StatusManager.SHOW | StatusManager.LOG);
								}
							}

							// Ensure the manager devices are "set" to avoid future zombie threads
							if (!manager.isSetDevices()) {
								ScaModelCommand.execute(manager, new ScaModelCommand() {

									@Override
									public void execute() {
										if (!manager.isSetDevices()) {
											manager.getDevices().clear();
										}
									}
								});
							}
						}

						ScaDevice< ? > device = manager.getDevice(deviceId);
						return adapterType.cast(device);
					}
				}
			}
		}

		return null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return DcdComponentInstantiationEditPartAdapterFactory.LIST;
	}
}
