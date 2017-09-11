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
package gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.IPattern;

import gov.redhawk.core.graphiti.dcd.ui.diagram.providers.DeviceManagerImageProvider;
import gov.redhawk.core.graphiti.dcd.ui.ext.DeviceShape;
import gov.redhawk.core.graphiti.dcd.ui.ext.RHDeviceGxFactory;
import gov.redhawk.core.graphiti.ui.diagram.providers.ImageProvider;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.scd.SoftwareComponent;

public class DevicePattern extends AbstractNodeComponentPattern implements IPattern {

	public DevicePattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "Device";
	}

	@Override
	protected boolean isInstantiationApplicable(DcdComponentInstantiation instantiation) {
		SoftwareComponent scd = ComponentInstantiation.Util.getScd(instantiation);

		if (scd == null) {
			return true;
		}

		switch (SoftwareComponent.Util.getWellKnownComponentType(scd)) {
		case DEVICE:
			return true;
		default:
			return false;
		}
	}

	@Override
	protected DeviceShape createContainerShape() {
		return RHDeviceGxFactory.eINSTANCE.createDeviceShape();
	}

	/**
	 * Return all DeviceShape in Diagram (recursively)
	 * @param containerShape
	 * @return
	 */
	public static List<DeviceShape> getAllDeviceShapes(ContainerShape containerShape) {
		List<DeviceShape> children = new ArrayList<DeviceShape>();
		if (containerShape instanceof DeviceShape) {
			children.add((DeviceShape) containerShape);
		} else {
			for (Shape s : containerShape.getChildren()) {
				if (s instanceof ContainerShape) {
					children.addAll(getAllDeviceShapes((ContainerShape) s));
				}
			}
		}
		return children;
	}

	@Override
	protected String getOuterImageId() {
		return ImageProvider.IMG_SPD;
	}

	@Override
	protected String getInnerImageId() {
		return DeviceManagerImageProvider.IMG_DEVICE;
	}

	@Override
	protected String getStyleForOuter() {
		return StyleUtil.OUTER_SHAPE;
	}

	@Override
	protected String getStyleForInner() {
		return StyleUtil.COMPONENT_INNER;
	}

	/**
	 * Returns device, dcd, ports. Order does matter.
	 */
	@Override
	protected List<EObject> getBusinessObjectsToLink(EObject componentInstantiation) {
		// get dcd from diagram, we need to link it to all shapes so the diagram will update when changes occur
		List<EObject> businessObjectsToLink = new ArrayList<EObject>();
		DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());
		// ORDER MATTERS, CI must be first
		businessObjectsToLink.add(componentInstantiation);
		businessObjectsToLink.add(dcd);

		return businessObjectsToLink;
	}
}
