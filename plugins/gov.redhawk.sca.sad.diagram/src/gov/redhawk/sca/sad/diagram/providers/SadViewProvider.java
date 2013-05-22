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
package gov.redhawk.sca.sad.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningViewProvider;
import gov.redhawk.sca.sad.diagram.factories.SadDiagramFactory;
import gov.redhawk.sca.sad.diagram.factories.SadViewFactory;
import gov.redhawk.sca.sad.diagram.part.RedhawkSadVisualIdRegistry;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class SadViewProvider extends PartitioningViewProvider {

	public SadViewProvider() {
		super(RedhawkSadVisualIdRegistry.INSTANCE);
	}

	@Override
	protected Class< ? extends ViewFactory> getNodeViewClass(final IAdaptable semanticAdapter, final View containerView, final String semanticHint) {
		if (!SoftwareAssemblyEditPart.MODEL_ID.equals(SadVisualIDRegistry.getModelID(containerView))) {
			return null;
		}
		final int visualId = SadVisualIDRegistry.getVisualID(semanticHint);
		switch (visualId) {
		case HostCollocationEditPart.VISUAL_ID:
			return getPartitioningViewFactoryClass();
		default:
			return super.getNodeViewClass(semanticAdapter, containerView, semanticHint);
		}
	}

	@Override
	protected Class< ? extends ViewFactory> getPartitioningViewFactoryClass() {
		return SadViewFactory.class;
	}

	@Override
	protected Class< ? > getPartitioningDiagramFactoryClass() {
		return SadDiagramFactory.class;
	}

}
