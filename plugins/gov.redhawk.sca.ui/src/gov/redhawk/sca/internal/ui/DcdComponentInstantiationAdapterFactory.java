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

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaModelPlugin;

import java.util.Map;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;

public class DcdComponentInstantiationAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] SUPPORTED_TYPES = new Class[] { ScaDevice.class }; // SUPPRESS CHECKSTYLE Name

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adapterType == ScaDevice.class && adaptableObject instanceof DcdComponentInstantiation) {
			final DcdComponentInstantiation compInst = (DcdComponentInstantiation) adaptableObject;
			if (compInst.eResource() == null) {
				return null;
			}

			final URI uri = compInst.eResource().getURI();
			final Map<String, String> query = QueryParser.parseQuery(uri.query());
			final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
			final ScaDeviceManager manager = ScaModelPlugin.getDefault().findEObject(ScaDeviceManager.class, wfRef);
			final String deviceId = compInst.getId();
			if (manager != null) {
				for (final ScaDevice< ? > device : manager.fetchDevices(new NullProgressMonitor())) {
					if (deviceId.equals(device.getIdentifier())) {
						return device;
					}
				}
			}
		}

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class< ? >[] getAdapterList() {
		return DcdComponentInstantiationAdapterFactory.SUPPORTED_TYPES;
	}
}
