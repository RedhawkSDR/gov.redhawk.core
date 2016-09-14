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
package gov.redhawk.core.graphiti.dcd.ui.diagram.providers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IReconnectionFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IReconnectionContext;
import org.eclipse.graphiti.features.context.impl.CustomContext;
import org.eclipse.graphiti.features.custom.ICustomFeature;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.ui.diagram.features.StartFeature;
import gov.redhawk.core.graphiti.ui.diagram.features.StopFeature;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

public class DevMgrExplorerFeatureProvider extends DCDGraphitiFeatureProvider {

	public DevMgrExplorerFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);
	}

	@Override
	public ICustomFeature[] getContextButtonPadFeatures(CustomContext context) {
		// Check the selection to make sure it's appropriate
		for (PictogramElement pe : context.getPictogramElements()) {
			if (!(pe instanceof RHContainerShape) || DUtil.getBusinessObject(pe, ComponentInstantiation.class) == null) {
				return super.getContextButtonPadFeatures(context);
			}
		}

		List<ICustomFeature> features = new ArrayList<>(Arrays.asList(super.getContextButtonPadFeatures(context)));
		features.add(new StartFeature(this));
		features.add(new StopFeature(this));
		return features.toArray(new ICustomFeature[features.size()]);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		if (this.getClass().equals(DevMgrExplorerFeatureProvider.class)) {
			// Prevent delete in the explorer diagrams
			return null;
		} else {
			// Allow derived classes (like sandbox) to inherit the parent class's functionality
			return super.getDeleteFeature(context);
		}
	}

	@Override
	public IReconnectionFeature getReconnectionFeature(IReconnectionContext context) {
		// We don't currently support reconnect actions for runtime
		return null;
	}
}
