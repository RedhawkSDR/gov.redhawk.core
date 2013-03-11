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
* Identification: $Revision: 4927 $
*/

package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.edit.commands.DomainFinderCreateCommand;
import gov.redhawk.diagram.edit.commands.NamingServiceCreateCommand;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.providers.PartitioningElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

/**
 * @since 3.0
* 
*/
public class FindByStubCompartmentItemSemanticEditPolicy extends PartitioningBaseItemSemanticEditPolicy {

	/**
	* 
	*/
	public FindByStubCompartmentItemSemanticEditPolicy(final PartitioningElementTypes elementTypes, final PartitioningVisualIDRegistry visualIdRegistry) {
		super(PartitioningElementTypes.FindByStub, elementTypes, visualIdRegistry);
	}

	/**
	* 
	*/
	@Override
    public Command getCreateCommand(final CreateElementRequest req) {
		if (PartitioningElementTypes.NamingService == req.getElementType()) {
			return getGEFWrapper(new NamingServiceCreateCommand(req));
		}
		if (PartitioningElementTypes.DomainFinder == req.getElementType()) {
			return getGEFWrapper(new DomainFinderCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
