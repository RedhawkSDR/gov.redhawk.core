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
package gov.redhawk.core.graphiti.sad.ui.diagram.feature;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.AdvancedAnchor;
import org.eclipse.graphiti.mm.pictograms.CompositeConnection;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.ui.util.DUtil;

/**
 * Customized remove feature that uses DUtil.fastDeletePictogramElement() instead of the PictogramElement service's
 * deletePictogramElement() to greatly reduce the time spent removing objects from the graph.
 */
public class FastRemoveFeature extends DefaultRemoveFeature {
	public FastRemoveFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public void remove(IRemoveContext context) {
		if (!getUserDecision()) {
			return;
		}
		preRemove(context);
		if (isRemoveAbort()) {
			throw new OperationCanceledException();
		}

		PictogramElement pe = context.getPictogramElement();

		if (pe instanceof Shape) {
			Shape shape = (Shape) pe;
			removeAllConnections(shape);
		} else if (pe instanceof AdvancedAnchor) {
			removeAllConnections((AdvancedAnchor) pe);
		} else if (pe instanceof CompositeConnection) {
			removeCompositeConnections((CompositeConnection) pe);
		}

		DUtil.fastDeletePictogramElement(pe);

		postRemove(context);
	}

	// Copied from superclass for visibility reasons
	private void removeCompositeConnections(CompositeConnection composite) {
		List<Connection> children = new ArrayList<Connection>(composite.getChildren());
		for (Connection childConnection : children) {
			RemoveContext context = new RemoveContext(childConnection);
			remove(context);
		}
	}
}
