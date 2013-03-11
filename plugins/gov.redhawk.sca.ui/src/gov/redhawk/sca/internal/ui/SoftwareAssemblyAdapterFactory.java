/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Map;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.URI;

/**
 * 
 */
public class SoftwareAssemblyAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class< ? >[] {
		ScaWaveform.class
	};

	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adaptableObject instanceof SoftwareAssembly) {
			final SoftwareAssembly sad = (SoftwareAssembly) adaptableObject;
			if (sad.eResource() == null) {
				return null;
			}
			if (ScaWaveform.class.isAssignableFrom(adapterType)) {
				final URI uri = sad.eResource().getURI();
				final Map<String, String> query = QueryParser.parseQuery(uri.query());
				final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
				final ScaWaveform waveform = ScaModelPlugin.getDefault().findEObject(ScaWaveform.class, wfRef);
				return waveform;
			}
		}
		return null;
	}

	public Class< ? >[] getAdapterList() {
		return SoftwareAssemblyAdapterFactory.LIST;
	}

}
