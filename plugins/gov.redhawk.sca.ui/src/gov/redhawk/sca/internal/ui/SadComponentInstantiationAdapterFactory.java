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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.CorbaUtils;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.statushandlers.StatusManager;

public class SadComponentInstantiationAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] SUPPORTED_TYPES = new Class[] { ScaComponent.class }; // SUPPRESS CHECKSTYLE Name

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adapterType == ScaComponent.class && adaptableObject instanceof SadComponentInstantiation) {
			final SadComponentInstantiation compInst = (SadComponentInstantiation) adaptableObject;
			if (compInst.eResource() == null) {
				return null;
			}

			final URI uri = compInst.eResource().getURI();
			final Map<String, String> query = QueryParser.parseQuery(uri.query());
			final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
			final ScaWaveform waveform = ScaModelPlugin.getDefault().findEObject(ScaWaveform.class, wfRef);
			final String myId = compInst.getId();
			if (waveform != null) {
				List<ScaComponent> components = Collections.emptyList();
				if (waveform.isSetComponents()) {
					components = waveform.getComponents();
				} else {
					if (Display.getCurrent() != null) {
						ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
						try {
							dialog.run(true, true, new IRunnableWithProgress() {

								@Override
								public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
									monitor.beginTask("Fetching components...", IProgressMonitor.UNKNOWN);
									try {
										CorbaUtils.invoke(new Callable<Object>() {

											@Override
											public Object call() throws Exception {
												waveform.fetchComponents(monitor);
												return null;
											}

										}, monitor);
									} catch (CoreException e) {
										throw new InvocationTargetException(e);
									} catch (InterruptedException e) {
										// PASS
									}
								}
							});
						} catch (InvocationTargetException e) {
							StatusManager.getManager().handle(new Status(Status.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to fetch components.", e),
								StatusManager.LOG | StatusManager.SHOW);
						} catch (InterruptedException e) {
							// PASS
						}
					} else {
						try {
							ProtectedThreadExecutor.submit(new Callable<Object>() {

								@Override
								public Object call() throws Exception {
									waveform.fetchComponents(null);
									return null;
								}

							});
						} catch (InterruptedException e) {
							// PASS
						} catch (ExecutionException e) {
							StatusManager.getManager().handle(new Status(Status.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to fetch components.", e),
								StatusManager.LOG | StatusManager.SHOW);
						} catch (TimeoutException e) {
							StatusManager.getManager().handle(
								new Status(Status.WARNING, ScaUiPlugin.PLUGIN_ID, "Timed out while trying to fetch components.", e),
								StatusManager.LOG | StatusManager.SHOW);
						}
					}
				}
				for (final ScaComponent component : components) {
					final String scaComponentId = component.identifier();
					if (scaComponentId.startsWith(myId)) {
						return component;
					}
				}
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class< ? >[] getAdapterList() {
		return SadComponentInstantiationAdapterFactory.SUPPORTED_TYPES;
	}

}
