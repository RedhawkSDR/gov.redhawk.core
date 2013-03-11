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

import gov.redhawk.diagram.factories.PartitioningDiagramFactory;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.notation.Diagram;

/**
 * 
 */
public class SadDiagramFactory {

	private final PartitioningDiagramFactory diagramFactory = new PartitioningDiagramFactory(new SadViewFactory());

	public Diagram createDiagram(final IAdaptable semanticAdapter, final String diagramKind, final PreferencesHint preferencesHint) {
		return this.diagramFactory.createDiagram(semanticAdapter, diagramKind, preferencesHint);
	}
}
