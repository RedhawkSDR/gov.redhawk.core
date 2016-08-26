/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.ui.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.scd.AbstractPort;

/**
 * Adapts a graphical port object (org.eclipse.graphiti.ui.internal.parts.AdvancedAnchorEditPart) to an SCA port model
 * object, or its profile.
 */
public class PortEditPartAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] LIST = new Class< ? >[] { ScaProvidesPort.class, ScaUsesPort.class, ScaPort.class, AbstractPort.class };

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		// EditPart is a parent class of AdvancedAnchorEditPart (and isn't private API)
		EditPart editPart = (EditPart) adaptableObject;
		PictogramElement pe = (PictogramElement) editPart.getModel();

		// Disallow context menu options for super ports
		if (DUtil.isSuperPort((ContainerShape) pe.eContainer())) {
			return null;
		}

		// Get the port name
		EObject port = DUtil.getBusinessObject(pe);
		String portName;
		if (port instanceof UsesPortStub) {
			portName = ((UsesPortStub) port).getName();
		} else if (port instanceof ProvidesPortStub) {
			portName = ((ProvidesPortStub) port).getName();
		} else {
			return null;
		}

		// Get the SCA model object, or the profile for it
		Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(pe);
		ScaPort< ? , ? > scaPort = getScaPort(diagram, port, portName);
		if (scaPort != null && AbstractPort.class.isAssignableFrom(adapterType)) {
			return adapterType.cast(scaPort.getProfileObj());
		} else if (adapterType.isInstance(scaPort)) {
			return adapterType.cast(scaPort);
		} else {
			return null;
		}
	}

	private ScaPort< ? , ? > getScaPort(Diagram diagram, EObject port, String name) {
		if (!(port.eContainer() instanceof ComponentInstantiation)) {
			return null;
		}

		ScaPortContainer portContainer = ContainerShapeAdapterFactory.adaptCompInstToScaModel(diagram, (ComponentInstantiation) port.eContainer(), ScaPortContainer.class);
		return (portContainer != null) ? portContainer.getScaPort(name) : null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return PortEditPartAdapterFactory.LIST;
	}
}
