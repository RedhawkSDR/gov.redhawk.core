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

package gov.redhawk.diagram.edit.parts;

import java.util.Collections;
import java.util.List;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.handles.MoveHandle;

class NodeLabelDragPolicy extends NonResizableEditPolicy {

	/**
	* 
	*/
	@Override
	protected List< ? > createSelectionHandles() {
		final MoveHandle h = new MoveHandle((GraphicalEditPart) getHost());
		h.setBorder(null);
		return Collections.singletonList(h);
	}

	/**
	* 
	*/
	@Override
	public Command getCommand(final Request request) {
		return null;
	}

	/**
	* 
	*/
	@Override
	public boolean understandsRequest(final Request request) {
		return false;
	}
}
