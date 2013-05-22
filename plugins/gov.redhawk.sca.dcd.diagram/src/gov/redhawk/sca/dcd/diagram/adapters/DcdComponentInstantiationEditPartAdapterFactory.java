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

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPropertyContainer;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.GraphicalEditPart;

@SuppressWarnings("rawtypes")
public class DcdComponentInstantiationEditPartAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class< ? >[] {
	        ScaDevice.class, ScaAbstractComponent.class, ScaPropertyContainer.class
	};

	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
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
						final Callable<List<ScaDevice< ? >>> callable = new Callable<List<ScaDevice< ? >>>() {

							public List<ScaDevice< ? >> call() throws Exception {
								return manager.fetchDevices(null);
							}
						};
						try {
							for (final ScaDevice< ? > device : ProtectedThreadExecutor.submit(callable)) {
								if (deviceId.equals(device.getIdentifier())) {
									return device;
								}
							}
						} catch (final InterruptedException e) {
							// PASS
						} catch (final ExecutionException e) {
							// PASS
						} catch (final TimeoutException e) {
							// PASS
						}
					}
				}
			}
		}

		return null;
	}

	public Class< ? >[] getAdapterList() {
		return DcdComponentInstantiationEditPartAdapterFactory.LIST;
	}
}
