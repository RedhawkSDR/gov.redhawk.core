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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mil.jpeojtrs.sca.partitioning.ComponentPlacement;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.DropObjectsRequest;

/**
 * 
 */
public class HostCollocationCompartmentCanonicalEditPolicy extends CanonicalEditPolicy {

	private Set<EStructuralFeature> myFeaturesToSynchronize;

	@Override
	protected Set<EStructuralFeature> getFeaturesToSynchronize() {
		if (this.myFeaturesToSynchronize == null) {
			this.myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
			this.myFeaturesToSynchronize.add(SadPackage.eINSTANCE.getHostCollocation_ComponentPlacement());
		}
		return this.myFeaturesToSynchronize;
	}

	@Override
	protected String getFactoryHint(final IAdaptable elementAdapter) {
		final Object adapter = elementAdapter.getAdapter(EObject.class);
		if (adapter instanceof ComponentPlacement) {
			return SadVisualIDRegistry.getType(ComponentPlacementEditPart.VISUAL_ID);
		}
		return super.getFactoryHint(elementAdapter);
	}

	@Override
	protected List< ? extends EObject> getSemanticChildrenList() {
		return getSemanticHost().getComponentPlacement();
	}

	@Override
	public HostCollocation getSemanticHost() {
		return (HostCollocation) super.getSemanticHost();
	}
	
	@Override
	public Command getCommand(final Request request) {
		if (understandsRequest(request)) {
			if (isEnabled() && request instanceof DropObjectsRequest) {
				return null;
			}
		}
		return super.getCommand(request);
	}
}
