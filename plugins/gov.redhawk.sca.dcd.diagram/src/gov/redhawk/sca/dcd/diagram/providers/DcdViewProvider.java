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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningViewProvider;
import gov.redhawk.sca.dcd.diagram.factories.DcdDiagramFactory;
import gov.redhawk.sca.dcd.diagram.factories.DcdViewFactory;
import gov.redhawk.sca.dcd.diagram.part.RedhawkDcdVisualIDRegistry;

import org.eclipse.gmf.runtime.diagram.core.view.factories.ViewFactory;

/**
 * 
 */
public class DcdViewProvider extends PartitioningViewProvider {

	public DcdViewProvider() {
		super(RedhawkDcdVisualIDRegistry.INSTANCE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class< ? extends ViewFactory> getPartitioningViewFactoryClass() {
		return DcdViewFactory.class;
	}

	@Override
	protected Class< ? > getPartitioningDiagramFactoryClass() {
		return DcdDiagramFactory.class;
	}

}
