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
package gov.redhawk.core.graphiti.sad.ui.internal.diagram.feature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.sad.ui.utils.SADUtils;
import gov.redhawk.core.graphiti.ui.diagram.features.AbstractDiagramUpdateFeature;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

/**
 * Performs updates of runtime waveform diagrams. Some code is duplicated in SADUpdateDiagramFeature.
 */
public class WaveformUpdateDiagramFeature extends AbstractDiagramUpdateFeature {

	public WaveformUpdateDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	protected List<EObject> getObjectsToAdd(Diagram diagram) {
		SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		// Find any component instantiations that are in the SAD but do not have an associated shape
		List<EObject> addedChildren = new ArrayList<EObject>();
		for (SadComponentPlacement placement : sad.getPartitioning().getComponentPlacement()) {
			for (SadComponentInstantiation instantiation : placement.getComponentInstantiation()) {
				if (!hasExistingShape(diagram, instantiation)) {
					addedChildren.add(instantiation);
				}
			}
		}
		// Likewise, check for host collocations that do not have an associated shape
		for (HostCollocation collocation : sad.getPartitioning().getHostCollocation()) {
			if (!hasExistingShape(diagram, collocation)) {
				addedChildren.add(collocation);
			}
		}

		return addedChildren;
	}

	protected List<ConnectInterface< ? , ? , ? >> getModelConnections(Diagram diagram) {
		SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		List<ConnectInterface< ? , ? , ? >> connections = new ArrayList<ConnectInterface< ? , ? , ? >>();
		if (sad.getConnections() != null) {
			connections.addAll(sad.getConnections().getConnectInterface());
		}
		return connections;
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			// Ensure assembly controller is set in case a component was deleted that used to be the assembly
			// controller, and re-assign the start orders. It is not necessary to check whether this made any
			// changes, because if it does, the child shapes will have to be updated and super.update() will
			// return true.
			Diagram diagram = (Diagram) pe;
			SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
			SADUtils.organizeStartOrder(sad, diagram, getFeatureProvider());

			// Defer to the base class for most updates
			return super.update(context);
		}
		return false;
	}

}
