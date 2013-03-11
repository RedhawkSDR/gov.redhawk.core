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
package gov.redhawk.sca.sad.diagram.edit.policies;

import mil.jpeojtrs.sca.sad.HostCollocation;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * 
 */
public class HostCollocationCompartmentItemSemanticEditPolicy extends
        mil.jpeojtrs.sca.sad.diagram.edit.policies.HostCollocationCompartmentItemSemanticEditPolicy {

	@Override
	public Command getCommand(final Request request) {
		if (request instanceof EditCommandRequestWrapper) {
			final EditCommandRequestWrapper wrapper = (EditCommandRequestWrapper) request;
			if (wrapper.getEditCommandRequest() instanceof DestroyElementRequest) {
				final DestroyElementRequest editRequest = (DestroyElementRequest) wrapper.getEditCommandRequest();

				if (editRequest.getElementToDestroy() instanceof HostCollocation) {
					final HostCollocation host = (HostCollocation) editRequest.getElementToDestroy();
					final DestroyElementRequest destroyReq = new DestroyElementRequest(host, false);
					return getHost().getParent().getCommand(new EditCommandRequestWrapper(destroyReq));
				}
			}
		}
		return super.getCommand(request);
	}

}
