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
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Map;

import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public class SadConnectInterfaceAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] {
	        ScaUsesPort.class, ScaPort.class, ScaProvidesPort.class
	};

	@Override
	public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
		if (adaptableObject instanceof SadConnectInterfaceEditPart) {
			final SadConnectInterfaceEditPart editPart = (SadConnectInterfaceEditPart) adaptableObject;
			final SadConnectInterface connection = (SadConnectInterface) editPart.getAdapter(ConnectInterface.class);
			if (connection == null) {
				return null;
			}
			final UsesPortStub uses = connection.getSource();
			if (uses == null || uses.eResource() == null) {
				return null;
			}
			EObject container = null;
			String name = null;
			if (adapterType == ScaUsesPort.class) {
				container = uses.eContainer();
				name = uses.getName();
			} else if (adapterType == ScaPort.class || adapterType == ScaProvidesPort.class) {
				if (connection.getTarget() instanceof ProvidesPortStub) {
					container = connection.getTarget().eContainer();
					name = ((ProvidesPortStub) connection.getTarget()).getName();
				}
			}
			if (name != null && container != null && ScaPort.class.isAssignableFrom(adapterType)) {
				final URI uri = uses.eResource().getURI();
				final String q = uri.query();
				if (q == null) {
					return null;
				}
				final Map<String, String> query = QueryParser.parseQuery(q);
				final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
				final ScaWaveform waveform = ScaModelPlugin.getDefault().findEObject(ScaWaveform.class, wfRef);
				if (container instanceof SadComponentInstantiation) {
					final String myId = ((SadComponentInstantiation) container).getId();
					for (final ScaComponent component : AdapterUtil.safeFetchComponents(waveform)) {
						final String scaComponentId = component.identifier();
						if (scaComponentId.startsWith(myId)) {
							for (final ScaPort< ? , ? > port : AdapterUtil.safeFetchPorts(component)) {
								if (port.getName().startsWith(name)) {
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

	@Override
	public Class< ? >[] getAdapterList() {
		return SadConnectInterfaceAdapterFactory.LIST;
	}

}
