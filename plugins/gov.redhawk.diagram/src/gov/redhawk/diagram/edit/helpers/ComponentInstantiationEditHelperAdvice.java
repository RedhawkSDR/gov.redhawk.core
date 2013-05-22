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
package gov.redhawk.diagram.edit.helpers;

import org.eclipse.gmf.runtime.emf.type.core.edithelper.AbstractEditHelperAdvice;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.MoveRequest;

/**
 * @since 3.0
 * 
 */
public class ComponentInstantiationEditHelperAdvice extends AbstractEditHelperAdvice {
	@Override
	public boolean approveRequest(final IEditCommandRequest request) {
		if (request instanceof MoveRequest) {
			return false;
		}
		return super.approveRequest(request);
	}
}
