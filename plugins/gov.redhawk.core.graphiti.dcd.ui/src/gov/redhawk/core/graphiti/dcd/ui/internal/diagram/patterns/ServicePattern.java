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
import gov.redhawk.core.graphiti.dcd.ui.ext.RHDeviceGxFactory;
import gov.redhawk.core.graphiti.dcd.ui.ext.ServiceShape;
import gov.redhawk.core.graphiti.ui.diagram.providers.ImageProvider;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.spd.SoftPkg;

public class ServicePattern extends AbstractNodeComponentPattern implements IPattern {

	public ServicePattern() {
		super();
	}

	@Override
	public String getCreateName() {
		return "Service";
	}

	@Override
	protected boolean isInstantiationApplicable(DcdComponentInstantiation instantiation) {
		SoftPkg spd = instantiation.getPlacement().getComponentFileRef().getFile().getSoftPkg();
		if (spd == null) {
			return true;
		}

		if (spd.getDescriptor().getComponent().getComponentType().equals(mil.jpeojtrs.sca.scd.ComponentType.SERVICE.getLiteral())) {
			return true;
		}
		return false;
	}

	@Override
	protected ServiceShape createContainerShape() {
		return RHDeviceGxFactory.eINSTANCE.createServiceShape();
	}

	/**
	 * Return all ServiceShape in Diagram (recursively)
	 * @param containerShape
	 * @return
	 */
	public static List<ServiceShape> getAllServiceShapes(ContainerShape containerShape) {
		List<ServiceShape> children = new ArrayList<ServiceShape>();
		if (containerShape instanceof ServiceShape) {
			children.add((ServiceShape) containerShape);
		} else {
			for (Shape s : containerShape.getChildren()) {
				if (s instanceof ContainerShape) {
					children.addAll(getAllServiceShapes((ContainerShape) s));
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
		return DeviceManagerImageProvider.IMG_SERVICE;
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
	 * Returns service, dcd, ports. Order does matter.
	 */
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
