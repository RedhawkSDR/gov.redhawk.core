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

import gov.redhawk.diagram.edit.parts.FindByStubEditPart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;

/**
 * @since 3.0
 * 
 */
public class PartitioningModelingAssistantProvider extends ModelingAssistantProvider {

	private final PartitioningElementTypes elementTypes;

	public PartitioningModelingAssistantProvider(final PartitioningElementTypes elementTypes) {
		this.elementTypes = elementTypes;
	}

	@Override
	public List< ? > getRelTypesOnSource(final IAdaptable source) {
		final EObject semanticObject = (EObject) source.getAdapter(EObject.class);
		if (semanticObject instanceof UsesPortStub) {
			return Collections.singletonList(this.elementTypes.getConnectInterfaceElementType());
		}
		return super.getRelTypesOnSource(source);
	}

	@Override
	public List< ? > getRelTypesOnTarget(final IAdaptable target) {
		final EObject semanticObject = (EObject) target.getAdapter(EObject.class);
		if (semanticObject instanceof ConnectionTarget) {
			return Collections.singletonList(this.elementTypes.getConnectInterfaceElementType());
		}
		return super.getRelTypesOnTarget(target);
	}

	@Override
	public List< ? > getRelTypesOnSourceAndTarget(final IAdaptable source, final IAdaptable target) {
		final EObject semanticSource = (EObject) source.getAdapter(EObject.class);
		final EObject semanticTarget = (EObject) target.getAdapter(EObject.class);
		if (semanticSource instanceof UsesPortStub && semanticTarget instanceof ConnectionTarget) {
			return Collections.singletonList(this.elementTypes.getConnectInterfaceElementType());
		}
		return super.getRelTypesOnSourceAndTarget(source, target);
	}

	@Override
	public List< ? > getTypesForSource(final IAdaptable target, final IElementType relationshipType) {
		final IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof FindByStubEditPart) {
			return ((FindByStubEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		return super.getTypesForSource(target, relationshipType);
	}

	@Override
	public List< ? > getTypesForPopupBar(final IAdaptable element) {
		final EObject semanticObject = (EObject) element.getAdapter(EObject.class);
		if (semanticObject instanceof FindByStub) {
			final List<IElementType> retVal = new ArrayList<IElementType>();
			retVal.add(PartitioningElementTypes.DomainFinder);
			retVal.add(PartitioningElementTypes.NamingService);
			retVal.add(this.elementTypes.getComponentSupportedInterfaceStubElementType());
			retVal.add(this.elementTypes.getUsesPortStubElementType());
			retVal.add(this.elementTypes.getProvidesPortStubElementType());
		}
		return super.getTypesForPopupBar(element);
	}
}
