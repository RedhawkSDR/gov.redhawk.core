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
package gov.redhawk.core.graphiti.ui.properties;

import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.ui.platform.AbstractPropertySectionFilter;
import org.eclipse.jface.viewers.IFilter;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

/**
 * This filter is designed for the "Advanced" tab in the properties view. It filters objects of the correct type(s)
 * which are from a runtime diagram.
 */
public class AdvancedFilter extends CompoundFilter {

	public AdvancedFilter() {
		super(CompoundFilter.BooleanOperator.FILTER_AND);

		IFilter boFilter = new BusinessObjectFilter(ComponentInstantiation.class, ProvidesPortStub.class, UsesPortStub.class);
		IFilter runtimeFilter = new AbstractPropertySectionFilter() {
			@Override
			protected boolean accept(PictogramElement pictogramElement) {
				Diagram diagram = Graphiti.getPeService().getDiagramForPictogramElement(pictogramElement);
				return DUtil.isDiagramRuntime(diagram);
			}
		};

		addFilter(boFilter);
		addFilter(runtimeFilter);
	}

}
