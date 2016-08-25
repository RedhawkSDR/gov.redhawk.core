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
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class UsesPortPattern extends AbstractPortPattern<UsesPortStub> {

	public static final String SHAPE_USES_PORT_CONTAINER = "usesPortContainerShape";
	private static final String SHAPE_USES_PORT_RECTANGLE = "usesPortRectangleShape";

	public UsesPortPattern() {
		super(UsesPortStub.class);
	}

	@Override
	protected String getStyleId(UsesPortStub usesPortStub) {
		if (isExternalPort(usesPortStub)) {
			return StyleUtil.EXTERNAL_USES_PORT;
		} else {
			return StyleUtil.USES_PORT;
		}
	}

	@Override
	protected String getPortName(UsesPortStub usesPortStub) {
		return usesPortStub.getName();
	}

	protected boolean isExternalPort(UsesPortStub usesPortStub) {
		EObject container = usesPortStub.eContainer();
		if (container instanceof SadComponentInstantiation) {
			SadComponentInstantiation instantiation = (SadComponentInstantiation) container;
			SoftwareAssembly sad = ScaEcoreUtils.getEContainerOfType(instantiation, SoftwareAssembly.class);
			if (sad != null && sad.getExternalPorts() != null) {
				for (Port externalPort : sad.getExternalPorts().getPort()) {
					if (instantiation == externalPort.getComponentInstantiationRef().getInstantiation()
						&& usesPortStub.getName().equals(externalPort.getUsesIdentifier())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	protected String getPortContainerShapeId() {
		return UsesPortPattern.SHAPE_USES_PORT_CONTAINER;
	}

	@Override
	protected String getPortRectangleShapeId() {
		return UsesPortPattern.SHAPE_USES_PORT_RECTANGLE;
	}

	@Override
	protected Orientation getPortOrientation() {
		return Orientation.ALIGNMENT_RIGHT;
	}

}
