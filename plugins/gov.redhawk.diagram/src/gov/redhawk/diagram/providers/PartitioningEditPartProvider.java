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
import gov.redhawk.diagram.edit.parts.DomainFinderNameEditPart;
import gov.redhawk.diagram.edit.parts.DomainFinderTypeEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubCompartmentEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubNameEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceNameEditPart;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;

import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.AbstractEditPartProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.CreateGraphicEditPartOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpart.IEditPartOperation;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public abstract class PartitioningEditPartProvider extends AbstractEditPartProvider {
	private final PartitioningVisualIDRegistry visualIdRegistry;
	private final PartitioningElementTypes elementTypes;

	public PartitioningEditPartProvider(final PartitioningVisualIDRegistry visualIdRegistry, final PartitioningElementTypes elementTypes) {
		super();
		this.visualIdRegistry = visualIdRegistry;
		this.elementTypes = elementTypes;
	}

	@Override
	public IGraphicalEditPart createGraphicEditPart(final View view) {
		final int visualId = this.visualIdRegistry.getVisualID(view);
		switch (visualId) {
		case FindByStubEditPart.VISUAL_ID:
			return new FindByStubEditPart(view, this.visualIdRegistry, this.elementTypes);
		case FindByStubNameEditPart.VISUAL_ID:
			return new FindByStubNameEditPart(view, this.elementTypes);
		case DomainFinderEditPart.VISUAL_ID:
			return new DomainFinderEditPart(view, this.visualIdRegistry, this.elementTypes);
		case DomainFinderNameEditPart.VISUAL_ID:
			return new DomainFinderNameEditPart(view, this.elementTypes, this.visualIdRegistry);
		case DomainFinderTypeEditPart.VISUAL_ID:
			return new DomainFinderTypeEditPart(view, this.elementTypes, this.visualIdRegistry);
		case NamingServiceEditPart.VISUAL_ID:
			return new NamingServiceEditPart(view, this.elementTypes, this.visualIdRegistry);
		case NamingServiceNameEditPart.VISUAL_ID:
			return new NamingServiceNameEditPart(view, this.elementTypes, this.visualIdRegistry);
		case FindByStubCompartmentEditPart.VISUAL_ID:
			return new FindByStubCompartmentEditPart(view, this.visualIdRegistry, this.elementTypes);
		default:
			return basicCreateGraphicEditPart(view);
		}

	}

	@Override
	public boolean provides(final IOperation operation) {
		if (operation instanceof CreateGraphicEditPartOperation) {
			final View view = ((IEditPartOperation) operation).getView();
			final int visualId = this.visualIdRegistry.getVisualID(view);
			switch (visualId) {
			case FindByStubEditPart.VISUAL_ID:
			case FindByStubNameEditPart.VISUAL_ID:
			case DomainFinderEditPart.VISUAL_ID:
			case DomainFinderNameEditPart.VISUAL_ID:
			case DomainFinderTypeEditPart.VISUAL_ID:
			case NamingServiceEditPart.VISUAL_ID:
			case NamingServiceNameEditPart.VISUAL_ID:
			case FindByStubCompartmentEditPart.VISUAL_ID:
				return true;
			default:
				break;
			}
		}
		return super.provides(operation);
	}

	public IGraphicalEditPart basicCreateGraphicEditPart(final View view) {
		return super.createGraphicEditPart(view);
	}
}
