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
package gov.redhawk.sca.sad.diagram.edit;

import gov.redhawk.sca.sad.diagram.edit.commands.ComponentPlacementCreateCommand;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.diagram.providers.SadElementTypes;

import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

public class HostCollocationEditHelperAdvice extends AbstractEditHelperAdvice implements IEditHelperAdvice {

	@Override
	protected ICommand getBeforeCreateCommand(final CreateElementRequest request) {
		if (SadElementTypes.SadComponentPlacement_3001 == request.getElementType()) {
			if (request.getContainmentFeature() == null) {
				request.setContainmentFeature(SadPackage.eINSTANCE.getHostCollocation_ComponentPlacement());
			}
			return new ComponentPlacementCreateCommand(request);
		}
		return super.getBeforeCreateCommand(request);
	}
}
