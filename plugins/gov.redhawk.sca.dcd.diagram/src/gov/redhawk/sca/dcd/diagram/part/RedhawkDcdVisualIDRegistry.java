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
package gov.redhawk.sca.dcd.diagram.part;

import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdConnectInterfaceEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningCompartmentEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdPartitioningEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public enum RedhawkDcdVisualIDRegistry implements PartitioningVisualIDRegistry {
	INSTANCE;

	/**
	 * {@inheritDoc}
	 */
	public MAPPING_ID getMappingID(final View view) {
		return getMappingID(getVisualID(view));
	}

	/**
	 * {@inheritDoc}
	 */
	public MAPPING_ID getMappingID(final String type) {
		return getMappingID(getVisualID(type));
	}

	/**
	 * {@inheritDoc}
	 */
	public MAPPING_ID getMappingID(final int visualId) {
		switch (visualId) {
		case DcdComponentInstantiationEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentInstantiationEditPart;
		case DcdComponentPlacementCompartmentEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentPlacementCompartmentEditPart;
		case DcdComponentPlacementEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentPlacementEditPart;
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return MAPPING_ID.ComponentSupportedInterfaceStubEditPart;
		case DcdConnectInterfaceEditPart.VISUAL_ID:
			return MAPPING_ID.ConnectInterfaceEditPart;
		case FindByStubEditPart.VISUAL_ID:
			return MAPPING_ID.FindByStubEditPart;
		case DcdPartitioningCompartmentEditPart.VISUAL_ID:
			return MAPPING_ID.PartitioningCompartmentEditPart;
		case DcdPartitioningEditPart.VISUAL_ID:
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
			return DcdComponentInstantiationEditPart.VISUAL_ID;
		case ComponentPlacementCompartmentEditPart:
			return DcdComponentPlacementCompartmentEditPart.VISUAL_ID;
		case ComponentPlacementEditPart:
			return DcdComponentPlacementEditPart.VISUAL_ID;
		case ComponentSupportedInterfaceStubEditPart:
			return ComponentSupportedInterfaceStubEditPart.VISUAL_ID;
		case ConnectInterfaceEditPart:
			return DcdConnectInterfaceEditPart.VISUAL_ID;
		case FindByStubEditPart:
			return FindByStubEditPart.VISUAL_ID;
		case PartitioningCompartmentEditPart:
			return DcdPartitioningCompartmentEditPart.VISUAL_ID;
		case PartitioningEditPart:
			return DcdPartitioningEditPart.VISUAL_ID;
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

	/**
	 * {@inheritDoc}
	 */
	public boolean canCreateNode(final View containerView, final int nodeVisualID) {
		return DcdVisualIDRegistry.canCreateNode(containerView, nodeVisualID);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getDiagramVisualID(final EObject domainElement) {
		return DcdVisualIDRegistry.getDiagramVisualID(domainElement);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getLinkWithClassVisualID(final EObject domainElement) {
		return DcdVisualIDRegistry.getLinkWithClassVisualID(domainElement);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getModelID(final View view) {
		return DcdVisualIDRegistry.getModelID(view);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNodeVisualID(final View containerView, final EObject domainElement) {
		return DcdVisualIDRegistry.getNodeVisualID(containerView, domainElement);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getType(final int visualID) {
		return DcdVisualIDRegistry.getType(visualID);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getVisualID(final String type) {
		return DcdVisualIDRegistry.getVisualID(type);
	}

	/**
	 * {@inheritDoc}
	 */
	public int getVisualID(final View view) {
		return DcdVisualIDRegistry.getVisualID(view);
	}

	/**
	 * {@inheritDoc}
	 */
	public String getModelID() {
		return DeviceConfigurationEditPart.MODEL_ID;
	}

}
