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

import java.util.List;

import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultRemoveFeature;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.AddFeatureForPattern;
import org.eclipse.graphiti.pattern.IPattern;

import gov.redhawk.core.graphiti.dcd.ui.diagram.feature.DeviceCreateFeature;
import gov.redhawk.core.graphiti.dcd.ui.diagram.feature.ServiceCreateFeature;
import gov.redhawk.core.graphiti.dcd.ui.internal.diagram.feature.DCDConnectionInterfaceDeleteFeature;
import gov.redhawk.core.graphiti.dcd.ui.internal.diagram.feature.DCDConnectionInterfaceUpdateFeature;
import gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns.DCDConnectInterfacePattern;
import gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns.DevicePattern;
import gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns.ServicePattern;
import gov.redhawk.core.graphiti.ui.diagram.providers.AbstractGraphitiFeatureProvider;

public abstract class DCDGraphitiFeatureProvider extends AbstractGraphitiFeatureProvider {

	public DCDGraphitiFeatureProvider(IDiagramTypeProvider diagramTypeProvider) {
		super(diagramTypeProvider);

		// Add device, service and connections
		addPattern(new DevicePattern());
		addPattern(new ServicePattern());
		addConnectionPattern(new DCDConnectInterfacePattern());
	}

	@Override
	public IAddFeature getAddFeature(IAddContext context) {
		String shapeType = (String) context.getProperty("shapeType");
		if (shapeType != null) {
			List<IPattern> patterns = getPatterns();

			// Ensures the correct add feature is returned based on selection
			switch (shapeType) {
			case DeviceCreateFeature.SHAPE_TYPE:
				for (IPattern pattern : patterns) {
					if (pattern instanceof DevicePattern) {
						return new AddFeatureForPattern(this, pattern);
					}
				}
				break;
			case ServiceCreateFeature.SHAPE_TYPE:
				for (IPattern pattern : patterns) {
					if (pattern instanceof ServicePattern) {
						return new AddFeatureForPattern(this, pattern);
					}
				}
				break;
			default:
				break;
			}
		}

		return super.getAddFeature(context);
	}

	@Override
	public IUpdateFeature getUpdateFeature(IUpdateContext context) {
		if (context.getPictogramElement() instanceof Connection) {
			return new DCDConnectionInterfaceUpdateFeature(this);
		} else if (context.getPictogramElement() instanceof Diagram) {
			return new GraphitiDcdDiagramUpdateFeature(this);
		}

		return super.getUpdateFeature(context);
	}

	@Override
	public IDeleteFeature getDeleteFeature(IDeleteContext context) {
		// If the element to be deleted is a connection, return the proper feature
		final PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Connection) {
			return new DCDConnectionInterfaceDeleteFeature(this);
		}

		return super.getDeleteFeature(context);
	}

	@Override
	public IRemoveFeature getRemoveFeature(IRemoveContext context) {
		return new DefaultRemoveFeature(this) {
			public boolean isAvailable(IContext context) {
				return false;
			}
		};
	}
}
