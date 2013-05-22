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
package gov.redhawk.sca.sad.diagram.sheet.properties;

import gov.redhawk.model.sca.ScaComponent;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IFilter;

/**
 * @since 3.0
 */
public class SadPropertyTabFilter implements IFilter {

	public boolean select(final Object toTest) {
		if (toTest instanceof IGraphicalEditPart) {
			final IGraphicalEditPart part = (IGraphicalEditPart) toTest;
			final Object obj = part.getAdapter(ComponentInstantiation.class);
			if (obj instanceof ComponentInstantiation) {
				final Object adapter = Platform.getAdapterManager().getAdapter(part, ScaComponent.class);
				return !(adapter instanceof ScaComponent);
			}
		}
		return false;
	}
}
