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
package gov.redhawk.core.graphiti.sad.ui.diagram.providers;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.ILayoutFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.sad.ui.internal.diagram.feature.SADConnectionInterfaceDeleteFeature;
import gov.redhawk.core.graphiti.sad.ui.internal.diagram.feature.SADConnectionInterfaceUpdateFeature;
import gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns.ComponentPattern;
import gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns.SADConnectInterfacePattern;
import gov.redhawk.core.graphiti.ui.diagram.providers.AbstractGraphitiFeatureProvider;

public abstract class SADGraphitiFeatureProvider extends AbstractGraphitiFeatureProvider {

	public SADGraphitiFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);

		// Add component and connections
		addPattern(new ComponentPattern());
		addConnectionPattern(new SADConnectInterfacePattern());
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		if (context.getPictogramElement() instanceof Diagram) {
			return new GraphitiWaveformDiagramUpdateFeature(this);
		} else if (context.getPictogramElement() instanceof Connection) {
			return new SADConnectionInterfaceUpdateFeature(this);
		}

		return super.getUpdateFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		// If the element to be deleted is a connection, return the proper feature
		final PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Connection) {
			return new SADConnectionInterfaceDeleteFeature(this);
		}

		return super.getDeleteFeature(context);
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return new FastRemoveFeature(this) {
			// overriding the method below causes Remove to NOT show up in context menus but still allows
			// us to getRemoveFeature and execute it.
			public boolean isAvailable(IContext context) {
				return false;
			}
		};
	}

	@Override
	public ILayoutFeature getLayoutFeature(ILayoutContext context) {

		if (context == null) {
			throw new IllegalArgumentException("Argument context must not be null."); //$NON-NLS-1$
		}

		return super.getLayoutFeature(context);
	}
}
