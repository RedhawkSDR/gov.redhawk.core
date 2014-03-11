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
package gov.redhawk.sca.sad.diagram.adapters;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.statushandlers.StatusManager;

import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

final class AdapterUtil {

	private AdapterUtil() {

	}

	public static List<ScaComponent> safeFetchComponents(final ScaWaveform waveform) {
		List<ScaComponent> retVal = Collections.emptyList();
		if (waveform.isSetComponents()) {
			retVal = waveform.getComponents();
		} else {

			if (Display.getCurrent() != null) {
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
				try {
					dialog.run(true, true, new IRunnableWithProgress() {
						@Override
						public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
							monitor.beginTask("Fetching components for " + waveform.getName(), IProgressMonitor.UNKNOWN);
							try {
								CorbaUtils.invoke(new Callable<List<ScaComponent>>() {

									@Override
									public List<ScaComponent> call() throws Exception {
										return waveform.fetchComponents(monitor);
									}

								}, monitor);
							} catch (CoreException e) {
								throw new InvocationTargetException(e);
							}
						}
					});
				} catch (InvocationTargetException e) {
					StatusManager.getManager().handle(
						new Status(Status.ERROR, RedhawkSadDiagramPlugin.PLUGIN_ID, "Failed to fetch components for " + waveform.getName(), e),
						StatusManager.SHOW | StatusManager.LOG);
				} catch (InterruptedException e) {
					// PASS
				}
				retVal = waveform.getComponents();
			} else {
				try {
					retVal = ProtectedThreadExecutor.submit(new Callable<List<ScaComponent>>() {

						public List<ScaComponent> call() throws Exception {
							return waveform.fetchComponents(null);
						}

					});
				} catch (final InterruptedException e) {
					// PASS
				} catch (final ExecutionException e) {
					StatusManager.getManager().handle(
						new Status(Status.ERROR, RedhawkSadDiagramPlugin.PLUGIN_ID, "Failed to fetch components for " + waveform.getName(), e),
						StatusManager.SHOW | StatusManager.LOG);
				} catch (final TimeoutException e) {
					StatusManager.getManager().handle(
						new Status(Status.WARNING, RedhawkSadDiagramPlugin.PLUGIN_ID, "Timed out trying to fetch components for " + waveform.getName(), e),
						StatusManager.SHOW | StatusManager.LOG);
				}
			}

			// Ensure the waveform components are "set" to avoid future zombie threads
			if (!waveform.isSetComponents()) {
				ScaModelCommand.execute(waveform, new ScaModelCommand() {

					@Override
					public void execute() {
						if (!waveform.isSetComponents()) {
							waveform.getComponents().clear();
						}
					}
				});
			}
		}
		return retVal;
	}

	public static List<ScaPort< ? , ? >> safeFetchPorts(final ScaComponent component) {
		List<ScaPort< ? , ? >> retVal = Collections.emptyList();
		if (component.isSetPorts()) {
			retVal = component.getPorts();
		} else {
			if (Display.getCurrent() != null) {
				ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
					try {
						dialog.run(true, true, new IRunnableWithProgress() {

							@Override
							public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
								monitor.beginTask("Fetching ports for " + component.getName(), IProgressMonitor.UNKNOWN);
								try {
									CorbaUtils.invoke(new Callable<List<ScaPort< ? , ? >>>() {

										public List<ScaPort< ? , ? >> call() throws Exception {
											return component.fetchPorts(monitor);
										}

									}, monitor);
								} catch (CoreException e) {
									throw new InvocationTargetException(e);
								}
							}
						});
					} catch (InvocationTargetException e) {
						StatusManager.getManager().handle(
							new Status(Status.ERROR, RedhawkSadDiagramPlugin.PLUGIN_ID, "Failed to fetch ports for " + component.getName(), e),
							StatusManager.SHOW | StatusManager.LOG);
					} catch (InterruptedException e) {
						// PASS
					}
					retVal = component.getPorts();
			} else {
				try {
					retVal = ProtectedThreadExecutor.submit(new Callable<List<ScaPort< ? , ? >>>() {

						public List<ScaPort< ? , ? >> call() throws Exception {
							return component.fetchPorts(null);
						}

					});
				} catch (final InterruptedException e) {
					// PASS
				} catch (final ExecutionException e) {
					StatusManager.getManager().handle(
						new Status(Status.ERROR, RedhawkSadDiagramPlugin.PLUGIN_ID, "Failed to fetch ports for " + component.getName(), e),
						StatusManager.SHOW | StatusManager.LOG);
				} catch (final TimeoutException e) {
					StatusManager.getManager().handle(
						new Status(Status.WARNING, RedhawkSadDiagramPlugin.PLUGIN_ID, "Timed out trying to fetch ports for " + component.getName(), e),
						StatusManager.SHOW | StatusManager.LOG);
				}
			}

			// Ensure the component's port are "set" to avoid future zombie threads
			if (!component.isSetPorts()) {
				ScaModelCommand.execute(component, new ScaModelCommand() {

					@Override
					public void execute() {
						if (!component.isSetPorts()) {
							component.getPorts().clear();
						}
					}
				});
			}
		}

		return retVal;
	}
}
