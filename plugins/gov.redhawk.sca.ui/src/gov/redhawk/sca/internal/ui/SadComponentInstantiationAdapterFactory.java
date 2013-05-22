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

import java.util.Map;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;

public class SadComponentInstantiationAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] SUPPORTED_TYPES = new Class[] { ScaComponent.class }; // SUPPRESS CHECKSTYLE Name

	/**
	 * {@inheritDoc}
	 */
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
				for (final ScaComponent component : waveform.fetchComponents(new NullProgressMonitor())) {
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
	public Class< ? >[] getAdapterList() {
		return SadComponentInstantiationAdapterFactory.SUPPORTED_TYPES;
	}

}
