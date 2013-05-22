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
package gov.redhawk.sca.sad.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningEditPartProvider;
import gov.redhawk.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.ProvidesPortStubNameEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.SadComponentPlacementCompartmentEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart;
import gov.redhawk.sca.sad.diagram.part.RedhawkSadVisualIdRegistry;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartment2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementName2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class SadEditPartProvider extends PartitioningEditPartProvider {

	public SadEditPartProvider() {
		super(RedhawkSadVisualIdRegistry.INSTANCE, RedhawkSadElementTypes.INSTANCE);
	}

	@Override
	protected Class< ? > getNodeEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (SadVisualIDRegistry.getVisualID(view)) {
		// Component Instantiation Edit Parts
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart.VISUAL_ID:
			return SadComponentInstantiationEditPart.class;

			// Component Placement Edit Parts			
		case ComponentPlacementEditPart.VISUAL_ID:
			return ComponentPlacementEditPart.class;
		case ComponentPlacementNameEditPart.VISUAL_ID:
		case ComponentPlacementName2EditPart.VISUAL_ID:
			return ComponentPlacementNameEditPart.class;
		case ComponentPlacementCompartmentEditPart.VISUAL_ID:
		case ComponentPlacementCompartment2EditPart.VISUAL_ID:
			return SadComponentPlacementCompartmentEditPart.class;

			// Component Supported Interface Edit Parts
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return ComponentSupportedInterfaceStubEditPart.class;

			// Host Collocation Edit Parts
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationCompartmentEditPart.VISUAL_ID:
			return HostCollocationCompartmentEditPart.class;
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart.VISUAL_ID:
			return HostCollocationEditPart.class;

			// Provides Edit Parts
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart.VISUAL_ID:
			return ProvidesPortStubEditPart.class;
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubNameEditPart.VISUAL_ID:
			return ProvidesPortStubNameEditPart.class;

			// Uses Edit Parts
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart.VISUAL_ID:
			return UsesPortStubEditPart.class;
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart.VISUAL_ID:
			return UsesPortStubNameEditPart.class;

		default:
			return super.getNodeEditPartClass(view);
		}
	}

	@Override
	protected Class< ? > getDiagramEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (SadVisualIDRegistry.getVisualID(view)) {
		// Software Assembly Eidt Part
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.VISUAL_ID:
			return SoftwareAssemblyEditPart.class;
		default:
			return super.getDiagramEditPartClass(view);
		}
	}

	@Override
	protected Class< ? > getEdgeEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (SadVisualIDRegistry.getVisualID(view)) {
		// Connect Interface Edit Part
		case mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart.VISUAL_ID:
			return SadConnectInterfaceEditPart.class;
		default:
			return super.getEdgeEditPartClass(view);
		}
	}
}
