/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.internal.ui.diagram.providers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.tb.ColorDecorator;
import org.eclipse.graphiti.tb.IDecorator;

import gov.redhawk.core.graphiti.ui.diagram.providers.IDecoratorProvider;
import gov.redhawk.core.graphiti.ui.util.InterfacesUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

public class ConnectionHighlightingDecoratorProvider implements IDecoratorProvider {

	private Anchor source;

	public void startHighlighting(Anchor source) {
		this.source = source;
	}

	public void endHighlighting() {
		source = null;
	}

	public void dispose() {
		source = null;
	}

	@Override
	public IDecorator[] getDecorators(PictogramElement pe) {
		if (source != null) {
			// Ports always have an invisible anchor overlaid on top of them; ignore everything else
			if (pe instanceof AnchorContainer && !((AnchorContainer) pe).getAnchors().isEmpty()) {
				if (isMatch(((AnchorContainer) pe).getAnchors().get(0))) {
					return new IDecorator[] { new ColorDecorator(null, StyleUtil.COLOR_OK) };
				}
			}
		}
		return new IDecorator[0];
	}

	protected boolean isMatch(Anchor target) {
		for (EObject sourceObject : source.getLink().getBusinessObjects()) {
			for (EObject targetObject : target.getLink().getBusinessObjects()) {
				if (isMatch(sourceObject, targetObject)) {
					return true;
				}
			}
		}
		return false;
	}

	protected boolean isMatch(EObject source, EObject target) {
		if (source.getClass().equals(target.getClass())) {
			return false;
		} else if (source instanceof UsesPortStub) {
			return InterfacesUtil.areSuggestedMatch((UsesPortStub) source, target);
		} else if (target instanceof UsesPortStub) {
			return InterfacesUtil.areSuggestedMatch((UsesPortStub) target, source);
		}
		return false;
	}
}
