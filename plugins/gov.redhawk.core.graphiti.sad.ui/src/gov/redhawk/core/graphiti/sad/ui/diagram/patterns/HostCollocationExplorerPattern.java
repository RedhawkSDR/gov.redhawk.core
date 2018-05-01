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
package gov.redhawk.core.graphiti.sad.ui.diagram.patterns;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import gov.redhawk.core.graphiti.sad.ui.diagram.providers.WaveformImageProvider;
import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractContainerPattern;
import gov.redhawk.core.graphiti.ui.diagram.patterns.UpdateAction;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import gov.redhawk.core.graphiti.ui.util.UpdateUtil;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;

/**
 * Provides a pattern for host collocations that disallows most editing.
 * @since 1.1
 */
public class HostCollocationExplorerPattern extends AbstractContainerPattern {

	// Property to identify a shape as a host collocation
	protected static final String HOST_COLLOCATION_OUTER_CONTAINER_SHAPE = "hostCollocationOuterContainerShape"; //$NON-NLS-1$

	// These are properties that help us resize an existing shape by properly identifying graphicsAlgorithms
	protected static final String GA_OUTER_ROUNDED_RECTANGLE = "outerRoundedRectangle"; //$NON-NLS-1$
	protected static final String GA_OUTER_ROUNDED_RECTANGLE_TEXT = "outerRoundedRectangleText"; //$NON-NLS-1$
	protected static final String GA_OUTER_ROUNDED_RECTANGLE_IMAGE = "outerRoundedRectangleImage"; //$NON-NLS-1$

	private static final int OUTER_IMAGE_LEFT_PADDING = 20;
	private static final int OUTER_TITLE_IMAGE_PADDING = 4;
	private static final int ICON_IMAGE_WIDTH = 16;
	private static final int ICON_IMAGE_HEIGHT = ICON_IMAGE_WIDTH;

	public HostCollocationExplorerPattern() {
		super(null);
	}

	@Override
	public boolean canAdd(IAddContext context) {
		return context.getNewObject() instanceof HostCollocation && context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public PictogramElement add(IAddContext context) {
		HostCollocation hostCollocation = (HostCollocation) context.getNewObject();

		// Create and link shape
		ContainerShape outerContainerShape = addOuterRectangle(context.getTargetContainer(), hostCollocation.getName(), WaveformImageProvider.IMG_HOST_COLLOCATION);
		link(outerContainerShape, hostCollocation);

		// Resize outer rounded rectangle
		int minWidth = Math.max(context.getWidth(), 300);
		int minHeight = Math.max(context.getHeight(), 300);
		Graphiti.getGaLayoutService().setLocationAndSize(outerContainerShape.getGraphicsAlgorithm(), context.getX(), context.getY(), minWidth, minHeight);

		// Add components inside host collocation model into the newly added shape
		updatePictogramElement(outerContainerShape);

		return outerContainerShape;
	}

	/**
	 * Creates the shape to represent a host collocation
	 * @param targetContainerShape
	 * @param text
	 * @param imageId
	 * @return
	 */
	private ContainerShape addOuterRectangle(ContainerShape targetContainerShape, String text, String imageId) {
		ContainerShape outerContainerShape = Graphiti.getCreateService().createContainerShape(targetContainerShape, true);
		Graphiti.getPeService().setPropertyValue(outerContainerShape, DUtil.SHAPE_TYPE, HOST_COLLOCATION_OUTER_CONTAINER_SHAPE);
		RoundedRectangle outerRoundedRectangle = Graphiti.getCreateService().createRoundedRectangle(outerContainerShape, 5, 5);
		StyleUtil.setStyle(outerRoundedRectangle, StyleUtil.HOST_COLLOCATION);
		Graphiti.getPeService().setPropertyValue(outerRoundedRectangle, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE);
		// image
		Image imgIcon = Graphiti.getGaCreateService().createImage(outerRoundedRectangle, imageId);
		Graphiti.getPeService().setPropertyValue(imgIcon, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE_IMAGE); // ref helps
		// text
		Text outerText = Graphiti.getCreateService().createText(outerRoundedRectangle, text);
		StyleUtil.setStyle(outerText, StyleUtil.OUTER_TEXT);
		Graphiti.getPeService().setPropertyValue(outerText, DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE_TEXT);

		return outerContainerShape;
	}

	// THE FOLLOWING THREE METHODS DETERMINE IF PATTERN IS APPLICABLE TO OBJECT
	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof HostCollocation;
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		// Disallow if shapes in the diagram would be added to this hostcollocation. The only shape in the diagram in
		// the provided area should be the hostcollocation itself.
		List<Shape> shapesInsideHostCollocation = DUtil.getShapesInArea(getDiagram(), context.getWidth(), context.getHeight(), context.getX(), context.getY());
		return shapesInsideHostCollocation.size() <= 1;
	}

	@Override
	public void resizeShape(IResizeShapeContext context) {
		// Adjust children x/y so they remain in the same relative position after resize
		DUtil.shiftChildrenRelativeToParentResize((ContainerShape) context.getShape(), context);

		// Adjust host collocation
		super.resizeShape(context);
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		return false;
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		return false;
	}

	/**
	 * Determines whether we need to update the diagram from the model.
	 */
	@Override
	public IReason updateNeeded(IUpdateContext context) {
		ContainerShape collocationShape = (ContainerShape) context.getPictogramElement();
		HostCollocation collocation = (HostCollocation) getBusinessObjectForPictogramElement(collocationShape);

		if (UpdateUtil.updateNeeded(getOuterText(collocationShape), collocation.getName())) {
			return Reason.createTrueReason("Need to update name");
		}

		List<SadComponentInstantiation> expectedComponents = getComponentInstantiations(collocation);
		Map<EObject, UpdateAction> actions = getChildrenToUpdate(collocationShape, expectedComponents);
		if (!actions.isEmpty()) {
			return Reason.createTrueReason("Need to update component shape(s)");
		}

		if (!isSortedByBusinessObject(collocationShape.getChildren(), expectedComponents)) {
			return Reason.createTrueReason("Need to sort component shape(s)");
		}

		return Reason.createFalseReason();
	}

	protected Text getOuterText(ContainerShape containerShape) {
		return (Text) DUtil.findChildGraphicsAlgorithmByProperty(containerShape.getGraphicsAlgorithm(), DUtil.GA_TYPE, GA_OUTER_ROUNDED_RECTANGLE_TEXT);
	}

	/**
	 * Updates the host collocation if needed
	 */
	@Override
	public boolean update(IUpdateContext context) {
		ContainerShape collocationShape = (ContainerShape) context.getPictogramElement();
		HostCollocation collocation = (HostCollocation) getBusinessObjectForPictogramElement(collocationShape);

		// Update collocation name
		boolean updatePerformed = UpdateUtil.update(getOuterText(collocationShape), collocation.getName());

		List<SadComponentInstantiation> expectedComponents = getComponentInstantiations(collocation);
		Map<EObject, UpdateAction> actions = getChildrenToUpdate(collocationShape, expectedComponents);
		updateChildren(collocationShape, actions);
		if (!actions.isEmpty()) {
			updatePerformed = true;
		}

		// Sort the component shapes to match the order in the XML (not strictly necessary)
		if (sortByBusinessObject(collocationShape.getChildren(), expectedComponents)) {
			updatePerformed = true;
		}

		if (updatePerformed) {
			layoutPictogramElement(collocationShape);
			return true;
		}
		return false;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		ContainerShape collocationShape = (ContainerShape) context.getPictogramElement();

		// Lay out the icon in the upper left
		Image outerImage = (Image) DUtil.findChildGraphicsAlgorithmByProperty(collocationShape.getGraphicsAlgorithm(), DUtil.GA_TYPE,
			GA_OUTER_ROUNDED_RECTANGLE_IMAGE);
		boolean layoutApplied = UpdateUtil.moveAndResizeIfNeeded(outerImage, HostCollocationExplorerPattern.OUTER_IMAGE_LEFT_PADDING, 0,
			HostCollocationExplorerPattern.ICON_IMAGE_WIDTH, HostCollocationExplorerPattern.ICON_IMAGE_HEIGHT);

		// Lay out the text following the image
		IDimension outerSize = Graphiti.getLayoutService().calculateSize(collocationShape.getGraphicsAlgorithm());
		Text outerText = getOuterText(collocationShape);
		int textX = outerImage.getX() + outerImage.getWidth() + OUTER_TITLE_IMAGE_PADDING;
		int textWidth = outerSize.getWidth() - textX;
		int textHeight = DUtil.calculateTextSize(outerText).getHeight();
		if (UpdateUtil.moveAndResizeIfNeeded(outerText, textX, 0, textWidth, textHeight)) {
			layoutApplied = true;
		}

		return layoutApplied;
	}

	protected List<SadComponentInstantiation> getComponentInstantiations(HostCollocation collocation) {
		List<SadComponentInstantiation> instantiations = new ArrayList<SadComponentInstantiation>();
		for (SadComponentPlacement placement : collocation.getComponentPlacement()) {
			instantiations.addAll(placement.getComponentInstantiation());
		}
		return instantiations;
	}
}
