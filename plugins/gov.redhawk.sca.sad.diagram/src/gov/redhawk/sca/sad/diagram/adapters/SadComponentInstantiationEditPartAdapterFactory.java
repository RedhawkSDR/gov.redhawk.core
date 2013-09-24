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

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Map;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.gef.GraphicalEditPart;

import CF.LifeCycleOperations;
import CF.ResourceOperations;

public class SadComponentInstantiationEditPartAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class< ? >[] { ScaComponent.class, ScaAbstractComponent.class, ScaPropertyContainer.class,
		ResourceOperations.class, LifeCycleOperations.class, IRefreshable.class, ScaPortContainer.class };

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adaptableObject instanceof GraphicalEditPart) {
			final GraphicalEditPart editPart = (GraphicalEditPart) adaptableObject;
			final SadComponentInstantiation ci = (SadComponentInstantiation) editPart.getAdapter(ComponentInstantiation.class);

			if (ci != null && ci.eResource() != null) {
				if (adapterType.isAssignableFrom(ScaComponent.class)) {
					final URI uri = ci.eResource().getURI();
					final Map<String, String> query = QueryParser.parseQuery(uri.query());
					final String wfRef = query.get(ScaFileSystemConstants.QUERY_PARAM_WF);
					final ScaWaveform waveform = ScaModelPlugin.getDefault().findEObject(ScaWaveform.class, wfRef);
					final String myId = ci.getId();
					if (waveform != null) {
						for (final ScaComponent component : AdapterUtil.safeFetchComponents(waveform)) {
							final String scaComponentId = component.identifier();
							if (scaComponentId.startsWith(myId)) {
								return component;
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
		return SadComponentInstantiationEditPartAdapterFactory.LIST;
	}

}
