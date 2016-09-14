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
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.services.Graphiti;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import gov.redhawk.core.graphiti.ui.util.UpdateUtil;

abstract class AbstractPortPattern< E extends EObject > extends AbstractPattern {

	protected static final int PORT_SHAPE_HEIGHT = 15;
	protected static final int PORT_SHAPE_WIDTH = AbstractPortPattern.PORT_SHAPE_HEIGHT;
	protected static final int PORT_NAME_HORIZONTAL_PADDING = 5;

	private Class<E> clazz;

	protected AbstractPortPattern(Class<E> clazz) {
		this.clazz = clazz;
	}

	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return clazz.isInstance(mainBusinessObject);
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		return getPortContainerShapeId().equals(Graphiti.getPeService().getPropertyValue(pictogramElement, DUtil.SHAPE_TYPE));
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		return false;
	}

	@Override
	public boolean update(IUpdateContext context) {
		ContainerShape usesPortShape = (ContainerShape) context.getPictogramElement();
		E port = getPortForPictogramElement(usesPortShape);
		boolean updateStatus = UpdateUtil.update(getPortText(usesPortShape), getPortName(port));
		Rectangle portRectangle = getPortRectangle(usesPortShape);
		String styleId = getStyleId(port);
		if (!styleId.equals(portRectangle.getStyle().getId())) {
			StyleUtil.setStyle(portRectangle, styleId);
			updateStatus = true;
		}
		return updateStatus;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		ContainerShape usesPortShape = (ContainerShape) context.getPictogramElement();
		E port = getPortForPictogramElement(usesPortShape);
		if (UpdateUtil.updateNeeded(getPortText(usesPortShape), getPortName(port))) {
			return Reason.createTrueReason("Uses port name needs update");
		}
		if (!StyleUtil.isStyleSet(getPortRectangle(usesPortShape), getStyleId(port))) {
			return Reason.createTrueReason("Uses port style needs update");
		}
		return Reason.createFalseReason();
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return isMainBusinessObjectApplicable(context.getNewObject());
	}

	@Override
	public PictogramElement add(IAddContext context) {
		ContainerShape parentShape = context.getTargetContainer();
		E portStub = clazz.cast(context.getNewObject());

		// Outer invisible container
		ContainerShape portContainerShape = Graphiti.getCreateService().createContainerShape(parentShape, true);
		Graphiti.getPeService().setPropertyValue(portContainerShape, DUtil.SHAPE_TYPE, getPortContainerShapeId());
		Rectangle providesPortContainerShapeRectangle = Graphiti.getCreateService().createPlainRectangle(portContainerShape);
		providesPortContainerShapeRectangle.setFilled(false);
		providesPortContainerShapeRectangle.setLineVisible(false);
		link(portContainerShape, portStub);

		// Port rectangle; this is created as its own shape because Anchors do not support decorators (for things
		// like highlighting)
		ContainerShape portShape = Graphiti.getPeService().createContainerShape(portContainerShape, true);
		Graphiti.getPeService().setPropertyValue(portShape, DUtil.SHAPE_TYPE, getPortRectangleShapeId());
		Rectangle providesPortRectangle = Graphiti.getCreateService().createPlainRectangle(portShape);
		StyleUtil.setStyle(providesPortRectangle, getStyleId(portStub));
		Graphiti.getGaLayoutService().setSize(providesPortRectangle, AbstractPortPattern.PORT_SHAPE_WIDTH, AbstractPortPattern.PORT_SHAPE_HEIGHT);
		link(portShape, portStub);

		// Port anchor
		Orientation orientation = getPortOrientation();
		int anchorX;
		if (Orientation.ALIGNMENT_LEFT.equals(orientation)) {
			anchorX = 0;
		} else {
			anchorX = AbstractPortPattern.PORT_SHAPE_WIDTH;
		}
		FixPointAnchor fixPointAnchor = createPortAnchor(portShape, anchorX);
		link(fixPointAnchor, portStub);

		// Port text
		Shape portTextShape = Graphiti.getPeService().createShape(portContainerShape, false);
		Text portText = Graphiti.getCreateService().createPlainText(portTextShape, getPortName(portStub));
		StyleUtil.setStyle(portText, StyleUtil.PORT_TEXT);
		portText.setHorizontalAlignment(orientation);
		// Based on orientation, set X position of text relative to port
		int textX;
		if (Orientation.ALIGNMENT_LEFT.equals(orientation)) {
			textX = AbstractPortPattern.PORT_SHAPE_WIDTH + AbstractPortPattern.PORT_NAME_HORIZONTAL_PADDING;
		} else {
			textX = 0;
		}
		Graphiti.getGaLayoutService().setLocation(portText, textX, 0);

		return portContainerShape;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape portContainerShape = (ContainerShape) context.getPictogramElement();

		// Resize the text
		Text portText = getPortText(portContainerShape);
		IDimension textSize = DUtil.calculateTextSize(portText);
		// Graphiti appears to underestimate the width for some strings (e.g., those ending in "r"), so add a small
		// amount of padding to ensure the entire letter is drawn
		int textWidth = textSize.getWidth() + 2;
		boolean layoutApplied = UpdateUtil.resizeIfNeeded(portText, textWidth, AbstractPortPattern.PORT_SHAPE_HEIGHT);

		// Move port rectangle
		int portX;
		if (Orientation.ALIGNMENT_RIGHT.equals(getPortOrientation())) {
			portX = portText.getWidth() + AbstractPortPattern.PORT_NAME_HORIZONTAL_PADDING;
		} else {
			portX = 0;
		}
		Rectangle portRectangle = getPortRectangle(portContainerShape);
		if (UpdateUtil.moveIfNeeded(portRectangle, portX, 0)) {
			layoutApplied = true;
		}

		// Resize invisible bounding rectangle
		int portWidth = textWidth + AbstractPortPattern.PORT_NAME_HORIZONTAL_PADDING + AbstractPortPattern.PORT_SHAPE_WIDTH;
		if (UpdateUtil.resizeIfNeeded(portContainerShape.getGraphicsAlgorithm(), portWidth, AbstractPortPattern.PORT_SHAPE_HEIGHT)) {
			layoutApplied = true;
		}

		return layoutApplied;
	}

	protected E getPortForPictogramElement(PictogramElement pictogramElement) {
		return clazz.cast(getBusinessObjectForPictogramElement(pictogramElement));
	}

	protected abstract String getPortContainerShapeId();

	protected abstract String getPortRectangleShapeId();

	protected abstract Orientation getPortOrientation();

	protected abstract String getStyleId(E port);

	protected abstract String getPortName(E port);

	protected Text getPortText(ContainerShape portContainerShape) {
		return (Text) portContainerShape.getChildren().get(1).getGraphicsAlgorithm();
	}

	protected Rectangle getPortRectangle(ContainerShape portContainerShape) {
		return (Rectangle) portContainerShape.getChildren().get(0).getGraphicsAlgorithm();
	}

	/**
	 * Create an anchor overlay for a port, with the anchor point vertically centered at horizontal position x.
	 * The returned anchor has an invisible rectangle for its graphics algorithm.
	 */
	protected FixPointAnchor createPortAnchor(ContainerShape portShape, int x) {
		FixPointAnchor fixPointAnchor = DUtil.createOverlayAnchor(portShape, x);
		Rectangle fixPointAnchorRectangle = Graphiti.getCreateService().createPlainRectangle(fixPointAnchor);
		Graphiti.getPeService().setPropertyValue(fixPointAnchorRectangle, DUtil.GA_TYPE, RHContainerShape.GA_FIX_POINT_ANCHOR_RECTANGLE);
		fixPointAnchorRectangle.setFilled(false);
		fixPointAnchorRectangle.setLineVisible(false);
		layoutAnchor(portShape);
		return fixPointAnchor;
	}

	private void layoutAnchor(Shape parentShape) {
		// Layout and resize anchor
		IDimension parentSize = Graphiti.getGaLayoutService().calculateSize(parentShape.getGraphicsAlgorithm());
		FixPointAnchor portAnchor = (FixPointAnchor) parentShape.getAnchors().get(0);
		Point anchorLocation = portAnchor.getLocation();
		anchorLocation.setY(parentSize.getHeight() / 2);
		Graphiti.getGaLayoutService().setLocationAndSize(portAnchor.getGraphicsAlgorithm(), -anchorLocation.getX(), -anchorLocation.getY(),
			parentSize.getWidth(), parentSize.getHeight());
	}
}
