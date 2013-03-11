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
package gov.redhawk.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 */
public interface PartitioningVisualIDRegistry {

	// BEGIN GENERATED CODE
	public static enum MAPPING_ID {
		ProvidesPortStubEditPart,
		    UsesPortStubEditPart,
		    ComponentSupportedInterfaceStubEditPart,
		    ComponentInstantiationEditPart,
		    ComponentPlacementEditPart,
		    ConnectInterfaceEditPart,
		    FindByStubEditPart,
		    PartitioningEditPart,
		    PartitioningCompartmentEditPart,
		    ComponentPlacementCompartmentEditPart,
		    ProvidesPortStubNameEditPart,
		    UsesPortStubNameEditPart;
	}

	// END GENERATED CODE
	public MAPPING_ID getMappingID(View view);

	public MAPPING_ID getMappingID(int visualId);

	public MAPPING_ID getMappingID(String semanticHint);

	public int getVisualId(MAPPING_ID mappingId);

	public boolean canCreateNode(View containerView, int nodeVisualID);

	public int getDiagramVisualID(EObject domainElement);

	public int getLinkWithClassVisualID(EObject domainElement);

	public String getModelID(View view);

	public int getNodeVisualID(View containerView, EObject domainElement);

	public String getType(int visualID);

	public int getVisualID(String type);

	public int getVisualID(View view);

	public String getModelID();
}
