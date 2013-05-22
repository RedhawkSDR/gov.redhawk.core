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
package gov.redhawk.diagram.factories;

import mil.jpeojtrs.sca.diagram.DiagramPackage;
import mil.jpeojtrs.sca.diagram.EObjectContainerStyle;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * @since 3.0
 * 
 */
public class PartitioningDiagramFactory {

	private final IPartitioningViewFactory viewFactory;

	public PartitioningDiagramFactory(final IPartitioningViewFactory viewFactory) {
		this.viewFactory = viewFactory;
	}

	public Diagram createDiagram(final IAdaptable semanticAdapter, final String diagramKind, final PreferencesHint preferencesHint) {
		final Diagram diagram = this.viewFactory.createDiagram(semanticAdapter, diagramKind, preferencesHint);
		final EObjectContainerStyle style = (EObjectContainerStyle) diagram.createStyle(DiagramPackage.eINSTANCE.getEObjectContainerStyle());
		style.setName("FindByStubContainer");
		return diagram;
	}
}
