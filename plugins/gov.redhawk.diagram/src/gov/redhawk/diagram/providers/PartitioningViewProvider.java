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
package gov.redhawk.diagram.providers;

import gov.redhawk.diagram.edit.parts.DomainFinderEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public abstract class PartitioningViewProvider extends AbstractViewProvider {

	private final PartitioningVisualIDRegistry visualIdRegistry;

	public PartitioningViewProvider(final PartitioningVisualIDRegistry visualIdRegistry) {
		this.visualIdRegistry = visualIdRegistry;
	}

	@Override
	protected Class< ? > getDiagramViewClass(final IAdaptable semanticAdapter, final String diagramKind) {
		if (this.visualIdRegistry.getModelID().equals(diagramKind)) {
			return getPartitioningDiagramFactoryClass();
		}
		return null;
	}

	protected abstract Class< ? > getPartitioningDiagramFactoryClass();

	@Override
	protected Class< ? extends ViewFactory> getNodeViewClass(final IAdaptable semanticAdapter, final View containerView, final String semanticHint) {
		if (!this.visualIdRegistry.getModelID().equals(this.visualIdRegistry.getModelID(containerView))) {
			return null;
		}
		final int visualId = this.visualIdRegistry.getVisualID(semanticHint);
		switch (this.visualIdRegistry.getVisualID(semanticHint)) {
		case DomainFinderEditPart.VISUAL_ID:
		case NamingServiceEditPart.VISUAL_ID:
			return getPartitioningViewFactoryClass();
		default:
			final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(visualId);
			if (mappingId != null) {
				switch (mappingId) {
				case ConnectInterfaceEditPart:
				case ComponentPlacementEditPart:
				case FindByStubEditPart:
				case ComponentSupportedInterfaceStubEditPart:
				case ProvidesPortStubEditPart:
				case UsesPortStubEditPart:
					return getPartitioningViewFactoryClass();
				default:
					break;
				}
			}
			return null;
		}

	}

	protected abstract Class< ? extends ViewFactory> getPartitioningViewFactoryClass();

	@Override
	protected Class< ? extends ViewFactory> getEdgeViewClass(final IAdaptable semanticAdapter, final View containerView, final String semanticHint) {
		if (!this.visualIdRegistry.getModelID().equals(this.visualIdRegistry.getModelID(containerView))) {
			return null;
		}
		final MAPPING_ID mappingId = this.visualIdRegistry.getMappingID(semanticHint);
		if (mappingId != null) {
			switch (mappingId) {
			case ConnectInterfaceEditPart:
				return getPartitioningViewFactoryClass();
			default:

			}
		}
		return null;
	}
}
