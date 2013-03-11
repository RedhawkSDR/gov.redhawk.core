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
package gov.redhawk.sca.sad.diagram.factories;

import gov.redhawk.diagram.factories.IPartitioningViewFactory;
import gov.redhawk.diagram.factories.PartitioningViewFactory;
import gov.redhawk.sca.sad.diagram.part.RedhawkSadVisualIdRegistry;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;
import mil.jpeojtrs.sca.sad.diagram.providers.SadViewProvider;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class SadViewFactory extends SadViewProvider implements ViewFactory, IPartitioningViewFactory {

	private final PartitioningViewFactory partitioningViewFactory = new PartitioningViewFactory(RedhawkSadVisualIdRegistry.INSTANCE, this);

	public View createView(final IAdaptable semanticAdapter, final View containerView, final String semanticHint, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		final int visualId = SadVisualIDRegistry.getVisualID(semanticHint);
		final EObject domainElement = (EObject) semanticAdapter.getAdapter(EObject.class);
		View retVal = null;
		switch (visualId) {
		case HostCollocationEditPart.VISUAL_ID:
			retVal = createHostCollocation_3006(domainElement, containerView, index, persisted, preferencesHint);
			break;
		default:
			retVal = this.partitioningViewFactory.createView(semanticAdapter, containerView, semanticHint, index, persisted, preferencesHint);
			break;
		}
		return retVal;
	}

	public Edge basicCreateConnectInterface(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		return super.createSadConnectInterface_4001(domainElement, containerView, index, persisted, preferencesHint);
	}

	public View basicCreateUsesPortStub(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		return super.createUsesPortStub_3003(domainElement, containerView, index, persisted, preferencesHint);
	}

	public View basicCreateProvidesPortStub(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		return super.createProvidesPortStub_3004(domainElement, containerView, index, persisted, preferencesHint);
	}

	public View basicCreateComponentSupportedInterfaceStub(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		return super.createComponentSupportedInterfaceStub_3005(domainElement, containerView, index, persisted, preferencesHint);
	}

	public View basicCreateComponentPlacement(final EObject domainElement, final View containerView, final int index, final boolean persisted,
	        final PreferencesHint preferencesHint) {
		return super.createSadComponentPlacement_3001(domainElement, containerView, index, persisted, preferencesHint);
	}

}
