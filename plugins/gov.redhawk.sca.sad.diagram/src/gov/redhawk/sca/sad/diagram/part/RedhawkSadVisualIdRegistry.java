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
package gov.redhawk.sca.sad.diagram.part;

import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacement2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;

public enum RedhawkSadVisualIdRegistry implements PartitioningVisualIDRegistry {
	INSTANCE;

	public MAPPING_ID getMappingID(final View view) {
		return getMappingID(getVisualID(view));
	}

	public MAPPING_ID getMappingID(final int visualId) {
		switch (visualId) {
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentInstantiationEditPart;
		case ComponentPlacementCompartmentEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentPlacementCompartmentEditPart;
		case ComponentPlacement2EditPart.VISUAL_ID:
		case ComponentPlacementEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentPlacementEditPart;
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentSupportedInterfaceStubEditPart;
		case SadConnectInterfaceEditPart.VISUAL_ID:
			return MAPPING_ID.ConnectInterfaceEditPart;
		case FindByStubEditPart.VISUAL_ID:
			return MAPPING_ID.FindByStubEditPart;
		case PartitioningCompartmentEditPart.VISUAL_ID:
			return MAPPING_ID.PartitioningCompartmentEditPart;
		case PartitioningEditPart.VISUAL_ID:
			return MAPPING_ID.PartitioningEditPart;
		case ProvidesPortStubEditPart.VISUAL_ID:
			return MAPPING_ID.ProvidesPortStubEditPart;
		case ProvidesPortStubNameEditPart.VISUAL_ID:
			return MAPPING_ID.ProvidesPortStubNameEditPart;
		case UsesPortStubEditPart.VISUAL_ID:
			return MAPPING_ID.UsesPortStubEditPart;
		case UsesPortStubNameEditPart.VISUAL_ID:
			return MAPPING_ID.UsesPortStubNameEditPart;
		default:
			return null;
		}
	}

	public int getVisualId(final MAPPING_ID mappingId) {
		switch (mappingId) {
		case ComponentInstantiationEditPart:
			return SadComponentInstantiationEditPart.VISUAL_ID;
		case ComponentPlacementCompartmentEditPart:
			return ComponentPlacementCompartmentEditPart.VISUAL_ID;
		case ComponentPlacementEditPart:
			return ComponentPlacementEditPart.VISUAL_ID;
		case ComponentSupportedInterfaceStubEditPart:
			return ComponentSupportedInterfaceStubEditPart.VISUAL_ID;
		case ConnectInterfaceEditPart:
			return SadConnectInterfaceEditPart.VISUAL_ID;
		case FindByStubEditPart:
			return FindByStubEditPart.VISUAL_ID;
		case PartitioningCompartmentEditPart:
			return PartitioningCompartmentEditPart.VISUAL_ID;
		case PartitioningEditPart:
			return PartitioningEditPart.VISUAL_ID;
		case ProvidesPortStubEditPart:
			return ProvidesPortStubEditPart.VISUAL_ID;
		case ProvidesPortStubNameEditPart:
			return ProvidesPortStubNameEditPart.VISUAL_ID;
		case UsesPortStubEditPart:
			return UsesPortStubEditPart.VISUAL_ID;
		case UsesPortStubNameEditPart:
			return UsesPortStubNameEditPart.VISUAL_ID;
		default:
			throw new UnsupportedOperationException("Mapping not implemented for mapping Id: " + mappingId);
		}
	}

	public MAPPING_ID getMappingID(final String semanticHint) {
		return getMappingID(getVisualID(semanticHint));
	}

	public boolean canCreateNode(final View containerView, final int nodeVisualID) {
		return SadVisualIDRegistry.canCreateNode(containerView, nodeVisualID);
	}

	public int getDiagramVisualID(final EObject domainElement) {
		return SadVisualIDRegistry.getDiagramVisualID(domainElement);
	}

	public int getLinkWithClassVisualID(final EObject domainElement) {
		return SadVisualIDRegistry.getLinkWithClassVisualID(domainElement);
	}

	public String getModelID(final View view) {
		return SadVisualIDRegistry.getModelID(view);
	}

	public int getNodeVisualID(final View containerView, final EObject domainElement) {
		return SadVisualIDRegistry.getNodeVisualID(containerView, domainElement);
	}

	public String getType(final int visualID) {
		return SadVisualIDRegistry.getType(visualID);
	}

	public int getVisualID(final String type) {
		return SadVisualIDRegistry.getVisualID(type);
	}

	public int getVisualID(final View view) {
		return SadVisualIDRegistry.getVisualID(view);
	}

	public String getModelID() {
		return SoftwareAssemblyEditPart.MODEL_ID;
	}

}
