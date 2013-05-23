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
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Map;

import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.URI;

/**
 * @since 1.1
 */
public class ProvidesPortStubAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] {
	        ScaProvidesPort.class, ScaPort.class
	};

	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof ProvidesPortStubEditPart) {
			final ProvidesPortStubEditPart editPart = (ProvidesPortStubEditPart) adaptableObject;
			final ProvidesPortStub provides = (ProvidesPortStub) editPart.getAdapter(ProvidesPortStub.class);
			if (provides == null || provides.eResource() == null || !(provides.eContainer() instanceof SadComponentInstantiation)) {
				return null;
			}
			if (ScaPort.class.isAssignableFrom(adapterType)) {
				final URI uri = provides.eResource().getURI();
				final Map<String, String> query = QueryParser.parseQuery(uri.query());
				final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
				final ScaWaveform waveform = ScaModelPlugin.getDefault().findEObject(ScaWaveform.class, wfRef);
				final String myId = ((SadComponentInstantiation) provides.eContainer()).getId();
				final String providesName = provides.getName();
				for (final ScaComponent component : AdapterUtil.safeFetchComponents(waveform)) {
					final String scaComponentId = component.identifier();
					if (scaComponentId != null && scaComponentId.startsWith(myId)) {
						for (final ScaPort< ? , ? > port : AdapterUtil.safeFetchPorts(component)) {
							if (port != null) {
								final String name = port.getName();
								if (name != null && name.startsWith(providesName)) {
									return port;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public Class< ? >[] getAdapterList() {
		return ProvidesPortStubAdapterFactory.LIST;
	}

}
