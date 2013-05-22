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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningEditPartProvider;
import gov.redhawk.sca.dcd.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.ProvidesPortStubNameEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.UsesPortStubEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;

import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class DcdEditPartProvider extends PartitioningEditPartProvider {

	public DcdEditPartProvider() {
		super(RedhawkDcdVisualIDRegistry.INSTANCE, RedhawkDcdElementTypes.INSTANCE);

	}

	@Override
	protected Class< ? > getNodeEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.MODEL_ID.equals(DcdVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		// Component Instantiation Edit Parts
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart.VISUAL_ID:
			return DcdComponentInstantiationEditPart.class;

			// Component Placement Edit Parts			
		case DcdComponentPlacementEditPart.VISUAL_ID:
			return DcdComponentPlacementEditPart.class;
		case DcdComponentPlacementNameEditPart.VISUAL_ID:
			return DcdComponentPlacementNameEditPart.class;
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
			return DcdComponentPlacementCompartmentEditPart.class;

			// Component Supported Interface Edit Parts
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return ComponentSupportedInterfaceStubEditPart.class;

			// Provides Edit Parts
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart.VISUAL_ID:
			return ProvidesPortStubEditPart.class;
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubNameEditPart.VISUAL_ID:
			return ProvidesPortStubNameEditPart.class;

			// Uses Edit Parts
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubEditPart.VISUAL_ID:
			return UsesPortStubEditPart.class;
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart.VISUAL_ID:
			return UsesPortStubNameEditPart.class;

		default:
			return super.getNodeEditPartClass(view);
		}
	}

	@Override
	protected Class< ? > getDiagramEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.MODEL_ID.equals(DcdVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		// Software Assembly Eidt Part
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.VISUAL_ID:
			return DeviceConfigurationEditPart.class;
		default:
			return super.getDiagramEditPartClass(view);
		}
	}

	@Override
	protected Class< ? > getEdgeEditPartClass(final View view) {
		if (!mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart.MODEL_ID.equals(DcdVisualIDRegistry.getModelID(view))) {
			return null;
		}
		switch (DcdVisualIDRegistry.getVisualID(view)) {
		// Connect Interface Edit Part
		case mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart.VISUAL_ID:
			return DcdConnectInterfaceEditPart.class;
		default:
			return super.getEdgeEditPartClass(view);
		}
	}
}
