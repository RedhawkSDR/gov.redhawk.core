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
package gov.redhawk.core.graphiti.dcd.ui.internal.diagram.feature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.dcd.ui.utils.DCDUtils;
import gov.redhawk.core.graphiti.ui.diagram.features.AbstractDiagramUpdateFeature;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;

/**
 * Performs updates of runtime device manager diagrams. Some code is duplicated in DCDUpdateDiagramFeature.
 */
public class DeviceManagerUpdateDiagramFeature extends AbstractDiagramUpdateFeature {

	public DeviceManagerUpdateDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	protected List<EObject> getObjectsToAdd(Diagram diagram) {
		List<EObject> addedObjects = new ArrayList<EObject>();
		DeviceConfiguration dcd = DUtil.getDiagramDCD(diagram);
		for (DcdComponentPlacement placement : dcd.getPartitioning().getComponentPlacement()) {
			for (DcdComponentInstantiation instantiation : placement.getComponentInstantiation()) {
				if (!hasExistingShape(diagram, instantiation)) {
					addedObjects.add(instantiation);
				}
			}
		}
		return addedObjects;
	}

	@Override
	protected List<ConnectInterface< ? , ? , ? >> getModelConnections(Diagram diagram) {
		DeviceConfiguration dcd = DUtil.getDiagramDCD(diagram);
		List<ConnectInterface< ? , ? , ? >> connections = new ArrayList<ConnectInterface< ? , ? , ? >>();
		if (dcd != null && dcd.getConnections() != null) {
			connections.addAll(dcd.getConnections().getConnectInterface());
		}
		return connections;
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			Diagram diagram = (Diagram) pe;
			DeviceConfiguration dcd = DUtil.getDiagramDCD(diagram);
			DCDUtils.organizeStartOrder(dcd, diagram, getFeatureProvider());

			// Defer to the base class for most updates
			return super.update(context);
		}
		return false;
	}

}
