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
package gov.redhawk.core.graphiti.ui.diagram.patterns;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;

import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class ProvidesPortPattern extends AbstractPortPattern<ProvidesPortStub> {

	public static final String SHAPE_PROVIDES_PORT_CONTAINER = "providesPortContainerShape";
	private static final String SHAPE_PROVIDES_PORT_RECTANGLE = "providesPortRectangleShape";

	public ProvidesPortPattern() {
		super(ProvidesPortStub.class);
	}

	@Override
	protected String getStyleId(ProvidesPortStub port) {
		if (isExternalPort(port)) {
			return StyleUtil.EXTERNAL_PROVIDES_PORT;
		} else {
			return StyleUtil.PROVIDES_PORT;
		}
	}

	@Override
	protected String getPortName(ProvidesPortStub port) {
		return port.getName();
	}

	protected boolean isExternalPort(ProvidesPortStub providesPortStub) {
		EObject container = providesPortStub.eContainer();
		if (container instanceof SadComponentInstantiation) {
			SadComponentInstantiation instantiation = (SadComponentInstantiation) container;
			SoftwareAssembly sad = ScaEcoreUtils.getEContainerOfType(instantiation, SoftwareAssembly.class);
			if (sad != null && sad.getExternalPorts() != null) {
				for (Port externalPort : sad.getExternalPorts().getPort()) {
					if (instantiation == externalPort.getComponentInstantiationRef().getInstantiation()
						&& providesPortStub.getName().equals(externalPort.getProvidesIdentifier())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	protected String getPortContainerShapeId() {
		return ProvidesPortPattern.SHAPE_PROVIDES_PORT_CONTAINER;
	}

	@Override
	protected String getPortRectangleShapeId() {
		return ProvidesPortPattern.SHAPE_PROVIDES_PORT_RECTANGLE;
	}

	@Override
	protected Orientation getPortOrientation() {
		return Orientation.ALIGNMENT_LEFT;
	}
}
