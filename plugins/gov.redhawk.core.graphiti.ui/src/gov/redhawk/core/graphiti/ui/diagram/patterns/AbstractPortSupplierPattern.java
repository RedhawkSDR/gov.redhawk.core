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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Image;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Rectangle;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.FixPointAnchor;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.config.IPatternConfiguration;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaLayoutService;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.ext.RHGxFactory;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public abstract class AbstractPortSupplierPattern extends AbstractContainerPattern {
	// Shape size constants
	private static final int OUTER_CONTAINER_SHAPE_TITLE_HORIZONTAL_RIGHT_PADDING = 10;
	private static final int INNER_CONTAINER_SHAPE_TOP_PADDING = 20;
	private static final int INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING = 15;
	private static final int PROVIDES_PORTS_LEFT_PADDING = 5;
	private static final int INNER_CONTAINER_SHAPE_HORIZONTAL_LEFT_PADDING = INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING + PROVIDES_PORTS_LEFT_PADDING;
	private static final int OUTER_IMAGE_LEFT_PADDING = INNER_CONTAINER_SHAPE_HORIZONTAL_LEFT_PADDING;
	private static final int PORTS_CONTAINER_SHAPE_TOP_PADDING = 60;
	private static final int INNER_ROUNDED_RECTANGLE_TEXT_TOP_PADDING = 8;
	private static final int INTERFACE_SHAPE_WIDTH = INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING + PROVIDES_PORTS_LEFT_PADDING;
	private static final int INTERFACE_SHAPE_HEIGHT = 10;
	private static final int ICON_IMAGE_WIDTH = 16;
	private static final int ICON_IMAGE_HEIGHT = ICON_IMAGE_WIDTH;
	private static final int INNER_TITLE_IMAGE_PADDING = 5;
	private static final int SUPER_PORT_SHAPE_WIDTH = 10;
	private static final int SUPER_PORT_SHAPE_HEIGHT_MARGIN = 5;
	private static final int LOLLIPOP_ELLIPSE_DIAMETER = 10;

	protected static final int PORT_ROW_PADDING_HEIGHT = 5;
	protected static final int REQ_PADDING_BETWEEN_PORT_TYPES = 10;

	public AbstractPortSupplierPattern(IPatternConfiguration patternConfiguration) {
		super(patternConfiguration);
	}

	/**
	 * Layout children of component
	 */
	@Override
	public boolean layout(ILayoutContext context) {
		boolean layoutApplied = false;

		RHContainerShape portSupplierShape = (RHContainerShape) context.getPictogramElement();
		int minimumHeight = 0;
		int minimumWidth = 0;
		if (!portSupplierShape.isCollapsed()) {
			// Layout provides ports
			ContainerShape providesPortsContainer = portSupplierShape.getProvidesPortsContainerShape();
			layoutProvidesPorts(providesPortsContainer);
			int providesHeight = providesPortsContainer.getGraphicsAlgorithm().getHeight();
			int providesWidth = Math.max(providesPortsContainer.getGraphicsAlgorithm().getWidth(), INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING);

			// Layout uses ports
			ContainerShape usesPortsContainer = portSupplierShape.getUsesPortsContainerShape();
			layoutUsesPorts(usesPortsContainer);
			adjustUsesPortsPosition(usesPortsContainer);
			int usesHeight = usesPortsContainer.getGraphicsAlgorithm().getHeight();
			int usesWidth = usesPortsContainer.getGraphicsAlgorithm().getWidth();

			// Account for port containers in outer sizing
			minimumHeight = Math.max(providesHeight, usesHeight) + 10;
			minimumWidth = providesWidth + usesWidth + AbstractPortSupplierPattern.REQ_PADDING_BETWEEN_PORT_TYPES;
		}

		// Resize height if necessary to accommodate contents (always requires padding)
		RoundedRectangle outerRoundedRectangle = (RoundedRectangle) portSupplierShape.getGraphicsAlgorithm();
		minimumHeight += PORTS_CONTAINER_SHAPE_TOP_PADDING;
		if (outerRoundedRectangle.getHeight() < minimumHeight) {
			outerRoundedRectangle.setHeight(minimumHeight);
		}
		// Resize width as well
		minimumWidth = Math.max(minimumWidth, getMinimumWidth(portSupplierShape));
		if (outerRoundedRectangle.getWidth() < minimumWidth) {
			outerRoundedRectangle.setWidth(minimumWidth);

			// If the width changes, move the uses ports container
			ContainerShape usesPortsContainer = portSupplierShape.getUsesPortsContainerShape();
			adjustUsesPortsPosition(usesPortsContainer);
		}

		// Outer text and image
		if (layoutOuterShape(portSupplierShape)) {
			layoutApplied = true;
		}

		// Layout inner rounded rectangle
		if (layoutInnerShape(portSupplierShape)) {
			layoutApplied = true;
		}

		if (portSupplierShape.isCollapsed()) {
			ContainerShape superProvidesPortsContainerShape = portSupplierShape.getSuperProvidesPortsContainerShape();
			if (superProvidesPortsContainerShape != null) {
				layoutSuperProvidesPorts(superProvidesPortsContainerShape);
			}

			ContainerShape superUsesPortsContainerShape = portSupplierShape.getSuperUsesPortsContainerShape();
			if (superUsesPortsContainerShape != null) {
				layoutSuperUsesPorts(superUsesPortsContainerShape);
			}
		}

		return layoutApplied;
	}

	@Override
	public boolean update(IUpdateContext context) {
		RHContainerShape containerShape = (RHContainerShape) context.getPictogramElement();
		EObject businessObject = (EObject) getBusinessObjectForPictogramElement(containerShape);

		// Check outer and inner titles
		boolean updateStatus = UpdateUtil.update(containerShape.getOuterText(), getOuterTitle(businessObject));
		if (UpdateUtil.update(containerShape.getInnerText(), getInnerTitle(businessObject))) {
			updateStatus = true;
		}

		// Use legacy update method to ensure ports containers are created
		if (((RHContainerShape) context.getPictogramElement()).update()) {
			updateStatus = true;
		}

		ContainerShape lollipopShape = containerShape.getLollipop();
		ContainerShape superProvidesPortsShape = containerShape.getSuperProvidesPortsContainerShape();
		ContainerShape superUsesPortsShape = containerShape.getSuperUsesPortsContainerShape();
		ContainerShape providesPortsShape = containerShape.getProvidesPortsContainerShape();
		ContainerShape usesPortsShape = containerShape.getUsesPortsContainerShape();
		if (containerShape.isCollapsed()) {
			if (UpdateUtil.deleteIfNeeded(lollipopShape, providesPortsShape, usesPortsShape)) {
				updateStatus = true;
			}

			if (updateSuperProvidesPorts(superProvidesPortsShape, getInterface(businessObject), getProvides(businessObject))) {
				updateStatus = true;
			}

			if (updateSuperUsesPorts(superUsesPortsShape, getUses(businessObject))) {
				updateStatus = true;
			}
		} else {
			// Add lollipop shape
			if (lollipopShape == null) {
				addLollipop(containerShape, getInterface(businessObject));
				updateStatus = true;
			}

			updatePorts(providesPortsShape, getProvides(businessObject));
			updatePorts(usesPortsShape, getUses(businessObject));

			if (UpdateUtil.deleteIfNeeded(superProvidesPortsShape, superUsesPortsShape)) {
				updateStatus = true;
			}
		}

		// Show/hide inner line
		Polyline innerLine = containerShape.getInnerPolyline();
		boolean innerLineVisible = !containerShape.isCollapsed();
		if (innerLine.getLineVisible() != innerLineVisible) {
			innerLine.setLineVisible(innerLineVisible);
			updateStatus = true;
		}

		// if we updated redraw
		if (updateStatus) {
			layoutPictogramElement(containerShape);
		}

		return updateStatus;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		RHContainerShape containerShape = (RHContainerShape) context.getPictogramElement();
		EObject businessObject = (EObject) getBusinessObjectForPictogramElement(containerShape);

		// Check outer and inner titles
		if (UpdateUtil.updateNeeded(containerShape.getOuterText(), getOuterTitle(businessObject))) {
			return Reason.createTrueReason("Outer title requires update");
		}
		if (UpdateUtil.updateNeeded(containerShape.getInnerText(), getInnerTitle(businessObject))) {
			return Reason.createTrueReason("Inner title requires update");
		}

		boolean innerLineVisible = containerShape.getInnerPolyline().getLineVisible();
		boolean hasLollipop = containerShape.getLollipop() != null;
		ContainerShape superProvidesShape = containerShape.getSuperProvidesPortsContainerShape();
		ContainerShape superUsesShape = containerShape.getSuperUsesPortsContainerShape();
		ContainerShape providesPortsContainer = containerShape.getProvidesPortsContainerShape();
		ContainerShape usesPortsContainer = containerShape.getUsesPortsContainerShape();
		if (containerShape.isCollapsed()) {
			if (innerLineVisible) {
				return Reason.createTrueReason("Inner line should be invisible");
			} else if (hasLollipop) {
				return Reason.createTrueReason("Interface lollipop needs to be deleted");
			}
			if (updateSuperProvidesPortsNeeded(superProvidesShape, getInterface(businessObject), getProvides(businessObject))) {
				return Reason.createTrueReason("Super provides port shape needs to be updated");
			} else if (updateSuperUsesPortsNeeded(superUsesShape, getUses(businessObject))) {
				return Reason.createTrueReason("Super uses port shape needs to be updated");
			} else if (providesPortsContainer != null || usesPortsContainer != null) {
				return Reason.createTrueReason("Port containers need to be deleted");
			}
		} else {
			if (!innerLineVisible) {
				return Reason.createTrueReason("Inner line should be visible");
			} else if (!hasLollipop) {
				return Reason.createTrueReason("Interface lollipop needs to be created");
			} else if (superUsesShape != null || superProvidesShape != null) {
				return Reason.createTrueReason("Super port shapes need to be deleted");
			} else if (updatePortsNeeded(providesPortsContainer, getProvides(businessObject))) {
				return Reason.createTrueReason("Provides ports need update");
			} else if (updatePortsNeeded(usesPortsContainer, getUses(businessObject))) {
				return Reason.createTrueReason("Uses ports need update");
			}
		}
		return Reason.createFalseReason();
	}

	/**
	 * Returns business objects that should be linked to shape
	 */
	protected abstract List<EObject> getBusinessObjectsToLink(EObject obj);

	protected boolean updatePorts(ContainerShape portsContainer, List< ? extends EObject> modelPorts) {
		Map<EObject, UpdateAction> portActions = getChildrenToUpdate(portsContainer, modelPorts);
		updateChildren(portsContainer, portActions);
		boolean updatePerformed = !portActions.isEmpty();

		if (sortByBusinessObject(portsContainer.getChildren(), modelPorts)) {
			updatePerformed = true;
		}

		// Link the container to all of the ports for compatibility with 2.0.0. As of 2.0.1, these links are not used.
		link(portsContainer, modelPorts.toArray());
		return updatePerformed;
	}

	protected boolean updatePortsNeeded(ContainerShape portsContainer, List< ? extends EObject> modelPorts) {
		if (portsContainer == null) {
			return true;
		}

		// Check that the container is linked to the port business objects (see above).
		if (!isLinked(portsContainer, modelPorts)) {
			return true;
		}

		Map<EObject, UpdateAction> portActions = getChildrenToUpdate(portsContainer, modelPorts);
		if (!portActions.isEmpty()) {
			return true;
		}
		return !isSortedByBusinessObject(portsContainer.getChildren(), modelPorts);
	}

	protected boolean updateSuperUsesPortsNeeded(ContainerShape superPort, List< ? extends EObject> modelStubs) {
		if (superPort == null) {
			return true;
		}
		return !isLinked(superPort, modelStubs);
	}

	protected boolean updateSuperUsesPorts(ContainerShape superPort, List< ? extends EObject> modelStubs) {
		link(superPort, modelStubs.toArray());
		link(superPort.getAnchors().get(0), modelStubs.toArray());
		return DUtil.setVisible(superPort.getGraphicsAlgorithm(), !modelStubs.isEmpty());
	}

	protected boolean updateSuperProvidesPorts(ContainerShape superPort, EObject interfaceStub, List< ? extends EObject> modelStubs) {
		List<Object> businessObjects = new ArrayList<Object>(modelStubs);
		businessObjects.add(interfaceStub);
		link(superPort, businessObjects.toArray());
		link(superPort.getAnchors().get(0), businessObjects.toArray());
		return false;
	}

	protected boolean updateSuperProvidesPortsNeeded(ContainerShape superPort, EObject interfaceStub, List< ? extends EObject> modelStubs) {
		if (superPort == null || superPort.getLink() == null) {
			return true;
		}
		List<Object> businessObjects = Arrays.asList(getMappingProvider().getAllBusinessObjectsForPictogramElement(superPort));
		if (!businessObjects.contains(interfaceStub)) {
			return true;
		}
		if (businessObjects.size() != (modelStubs.size() + 1)) {
			return true;
		}
		return !businessObjects.containsAll(modelStubs);
	}

	/**
	 * Provides list of UsesPortStubs (if applicable)
	 * @param obj
	 * @return
	 */
	protected abstract EList<UsesPortStub> getUses(EObject obj);

	/**
	 * Provides list of ProvidesPortStubs (if applicable)
	 * @param obj
	 * @return
	 */
	protected abstract EList<ProvidesPortStub> getProvides(EObject obj);

	/**
	 * Provides interface (if applicable)
	 * @param obj
	 * @return
	 */
	protected abstract ComponentSupportedInterfaceStub getInterface(EObject obj);

	/**
	 * Provides the title of the outer shape
	 * @param findByStub
	 * @return
	 */
	protected abstract String getOuterTitle(EObject obj);

	/**
	 * Provides outer image ID for graphical representation
	 * @return
	 */
	protected abstract String getOuterImageId();

	/**
	 * Provides outer container style for graphical representation
	 * @return
	 */
	protected abstract String getStyleForOuter();

	/**
	 * Provides the title of the inner shape
	 * @param findByStub
	 * @return
	 */
	protected abstract String getInnerTitle(EObject obj);

	/**
	 * Provides inner image ID for graphical representation
	 * @param
	 * @return
	 */
	protected abstract String getInnerImageId();

	/**
	 * Provides inner container style for graphical representation
	 * @param
	 * @return
	 */
	protected abstract String getStyleForInner();

	/**
	 * Sets a new inner title on the underlying business object.
	 * @param businessObject
	 * @param value
	 */
	protected abstract void setInnerTitle(EObject businessObject, String value);

	@Override
	public PictogramElement add(IAddContext context) {
		// Create the basic shape, and set attributes that may not get set by directly constructing a shape (versus
		// using Graphiti's PeCreateService)
		RHContainerShape containerShape = createContainerShape();
		containerShape.setVisible(true);
		containerShape.setActive(true);
		containerShape.setContainer(context.getTargetContainer());

		// Set default configuration preferences
		boolean hideDetailsPref = GraphitiUIPlugin.getDefault().getPreferenceStore().getBoolean(DiagramPreferenceConstants.HIDE_DETAILS);
		boolean hidePortsPref = GraphitiUIPlugin.getDefault().getPreferenceStore().getBoolean(DiagramPreferenceConstants.HIDE_UNUSED_PORTS);
		containerShape.setCollapsed(hideDetailsPref);
		containerShape.setHideUnusedPorts(hidePortsPref);

		// Link all top-level business objects
		EObject newObject = (EObject) context.getNewObject();
		link(containerShape, getBusinessObjectsToLink(newObject).toArray());

		// Initialize shape contents
		containerShape.init();

		// Initialize elements that only require a one-time setup
		StyleUtil.setStyle(containerShape.getGraphicsAlgorithm(), getStyleForOuter());
		containerShape.getOuterImage().setId(getOuterImageId());
		containerShape.getInnerImage().setId(getInnerImageId());

		// Set default inner style
		String innerStyle = getStyleForInner();
		StyleUtil.setStyle(containerShape.getInnerContainerShape().getGraphicsAlgorithm(), innerStyle);
		StyleUtil.setStyle(containerShape.getInnerPolyline(), innerStyle);

		if (!containerShape.isCollapsed()) {
			addLollipop(containerShape, getInterface(newObject));
		}

		// Allow subclasses to do additional initialization
		initializeShape(containerShape, context);

		// Defer to update to handle child object setup
		updatePictogramElement(containerShape);

		// Layout the shape
		layoutPictogramElement(containerShape);

		// Set shape location to user's selection
		Graphiti.getGaLayoutService().setLocation(containerShape.getGraphicsAlgorithm(), context.getX(), context.getY());

		// Check for any needed location adjustments, avoids accidentally stacking shapes
		adjustShapeLocation(containerShape, context.getTargetContainer());

		return containerShape;
	}

	/**
	 * Creates an instance of the pattern's container shape. Subclasses may override to return a more specific type.
	 * @return new container shape instance
	 */
	protected RHContainerShape createContainerShape() {
		return RHGxFactory.eINSTANCE.createRHContainerShape();
	}

	/**
	 * Performs additional initialization of the shape. Default implementation does nothing, but subclasses may
	 * override if needed.
	 * @param shape
	 * @param context
	 */
	protected void initializeShape(RHContainerShape shape, IAddContext context) {
	}

	/**
	 * Checks to make sure the new shape is not being stacked on top of an existing one.
	 * @param shape
	 */
	protected void adjustShapeLocation(RHContainerShape shape, ContainerShape container) {
		final int BUFFER_WIDTH = 20;

		// if any overlap occurs (can happen when launching using the REDHAWK Explorer) adjust x/y-coords
		for (Shape child : container.getChildren()) {
			// Avoid infinite loop by checking a shape against itself
			if (child.equals(shape)) {
				continue;
			}

			boolean xAdjusted = false;
			int xAdjustment = 0;

			GraphicsAlgorithm childGa = child.getGraphicsAlgorithm();
			int componentWidth = shape.getGraphicsAlgorithm().getWidth();
			int componentHeight = shape.getGraphicsAlgorithm().getHeight();

			int componentX = shape.getGraphicsAlgorithm().getX();
			int componentY = shape.getGraphicsAlgorithm().getY();

			boolean xOverlapped = componentX >= childGa.getX() && componentX <= (childGa.getX() + childGa.getWidth())
				|| childGa.getX() >= componentX && childGa.getX() <= componentX + componentWidth;
			boolean yOverlapped = componentY >= childGa.getY() && componentY <= (childGa.getY() + childGa.getHeight())
				|| childGa.getY() >= componentY && childGa.getY() <= componentY + componentHeight;
			// If there is any overlap, then move new component all the way to the right of the old component.
			if (xOverlapped && yOverlapped) {
				xAdjustment += childGa.getX() + childGa.getWidth() + BUFFER_WIDTH;
				xAdjusted = true;
			}
			if (xAdjusted) {
				shape.getGraphicsAlgorithm().setX(xAdjustment);
				// If we've made any adjustments, make a recursive call to make sure we do not create a new collision
				adjustShapeLocation(shape, container);
			}
		}
	}

	/**
	 * Adds interface lollipop to a ContainerShape. Its anchor will link to the provided business object.
	 */
	protected ContainerShape addLollipop(RHContainerShape container, Object anchorBusinessObject) {
		// Interface container
		ContainerShape interfaceContainerShape = Graphiti.getCreateService().createContainerShape(container, true);
		Graphiti.getPeService().setPropertyValue(interfaceContainerShape, DUtil.GA_TYPE, RHContainerShape.SHAPE_INTERFACE_CONTAINER);
		Rectangle interfaceRectangle = Graphiti.getCreateService().createPlainRectangle(interfaceContainerShape);
		interfaceRectangle.setFilled(false);
		interfaceRectangle.setLineVisible(false);
		Graphiti.getGaLayoutService().setLocationAndSize(interfaceRectangle, 0, 25, INTERFACE_SHAPE_WIDTH, INTERFACE_SHAPE_HEIGHT);

		// Interface lollipop line
		Shape lollipopLineShape = Graphiti.getCreateService().createShape(interfaceContainerShape, false);
		int[] linePoints = new int[] { LOLLIPOP_ELLIPSE_DIAMETER - 1, LOLLIPOP_ELLIPSE_DIAMETER / 2, INTERFACE_SHAPE_WIDTH, LOLLIPOP_ELLIPSE_DIAMETER / 2 };
		Polyline lollipopLine = Graphiti.getCreateService().createPlainPolyline(lollipopLineShape, linePoints);
		StyleUtil.setStyle(lollipopLine, StyleUtil.LOLLIPOP);

		// Interface lollipop ellipse
		Shape lollipopEllipseShape = Graphiti.getPeCreateService().createContainerShape(interfaceContainerShape, true);
		Ellipse lollipopEllipse = Graphiti.getCreateService().createPlainEllipse(lollipopEllipseShape);
		StyleUtil.setStyle(lollipopEllipse, StyleUtil.LOLLIPOP);
		Graphiti.getGaLayoutService().setLocationAndSize(lollipopEllipse, 0, 0, LOLLIPOP_ELLIPSE_DIAMETER, LOLLIPOP_ELLIPSE_DIAMETER);
		link(lollipopEllipseShape, anchorBusinessObject);

		// Overlay invisible ellipse
		FixPointAnchor fixPointAnchor = DUtil.createOverlayAnchor(lollipopEllipseShape, 0);
		link(fixPointAnchor, anchorBusinessObject);
		Ellipse anchorEllipse = Graphiti.getCreateService().createPlainEllipse(fixPointAnchor);
		anchorEllipse.setFilled(false);
		anchorEllipse.setLineVisible(false);
		UpdateUtil.layoutOverlayAnchor(lollipopEllipseShape, Orientation.ALIGNMENT_LEFT);

		return interfaceContainerShape;
	}

	protected int getMinimumWidth(RHContainerShape shape) {
		int innerWidth = getMinimumInnerWidth(shape);
		int outerWidth = getMinimumOuterWidth(shape);
		return Math.max(innerWidth, outerWidth) + INTERFACE_SHAPE_WIDTH;
	}

	protected int getMinimumInnerWidth(RHContainerShape shape) {
		Text innerTitle = shape.getInnerText();
		IDimension innerTitleDimension = DUtil.calculateTextSize(innerTitle);
		return ICON_IMAGE_WIDTH + INNER_TITLE_IMAGE_PADDING + innerTitleDimension.getWidth() + 2 * INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING;
	}

	protected int getMinimumOuterWidth(RHContainerShape shape) {
		Text outerTitle = shape.getOuterText();
		IDimension outerTitleDimension = DUtil.calculateTextSize(outerTitle);
		return OUTER_IMAGE_LEFT_PADDING + outerTitleDimension.getWidth() + OUTER_CONTAINER_SHAPE_TITLE_HORIZONTAL_RIGHT_PADDING + 4;
	}

	protected boolean layoutInnerShape(RHContainerShape shape) {
		// Inner shape has already been resized to fit outer shape
		ContainerShape innerContainerShape = shape.getInnerContainerShape();
		int innerWidth = innerContainerShape.getGraphicsAlgorithm().getWidth();

		// Ensure image is correctly sized
		Image innerImage = shape.getInnerImage();
		boolean layoutApplied = UpdateUtil.resizeIfNeeded(innerImage, ICON_IMAGE_WIDTH, ICON_IMAGE_HEIGHT);

		// Layout image and text so that they are roughly centered
		Text innerText = shape.getInnerText();
		IDimension innerTextSize = DUtil.calculateTextSize(innerText);
		int imageX = (innerWidth - (innerTextSize.getWidth() + innerImage.getWidth() + 5)) / 2;
		if (UpdateUtil.moveIfNeeded(innerImage, imageX, INNER_ROUNDED_RECTANGLE_TEXT_TOP_PADDING)) {
			layoutApplied = true;
		}
		int textX = imageX + innerImage.getWidth() + INNER_TITLE_IMAGE_PADDING;
		int textWidth = innerTextSize.getWidth() + 10;
		if (UpdateUtil.moveAndResizeIfNeeded(innerText, textX, INNER_ROUNDED_RECTANGLE_TEXT_TOP_PADDING, textWidth, innerTextSize.getHeight())) {
			layoutApplied = true;
		}

		// Layout the inner separator under the image and text, to go across the entire inner shape
		int lineY = Math.max(innerImage.getY() + innerImage.getHeight(), innerText.getY() + innerText.getHeight()) + 2;
		if (UpdateUtil.movePoints(shape.getInnerPolyline().getPoints(), 0, lineY, innerWidth, lineY)) {
			layoutApplied = true;
		}

		return layoutApplied;
	}

	protected boolean layoutOuterShape(RHContainerShape shape) {
		// Layout image in upper left
		Image outerImage = shape.getOuterImage();
		boolean layoutApplied = UpdateUtil.moveAndResizeIfNeeded(outerImage, OUTER_IMAGE_LEFT_PADDING, 0, ICON_IMAGE_WIDTH, ICON_IMAGE_HEIGHT);

		// Layout text to the right of image, taking up the remaining horizontal space
		Text outerText = shape.getOuterText();
		int textX = outerImage.getX() + outerImage.getWidth() + 4;
		int textWidth = shape.getGraphicsAlgorithm().getWidth() - textX;
		if (UpdateUtil.moveAndResizeIfNeeded(outerText, textX, 0, textWidth, 20)) {
			layoutApplied = true;
		}

		// Resize the inner shape relative to the outer, positioned just under the text
		ContainerShape innerContainerShape = shape.getInnerContainerShape();
		IGaLayoutService gaLayoutService = Graphiti.getGaLayoutService();
		IDimension parentSize = gaLayoutService.calculateSize(shape.getGraphicsAlgorithm());
		int width = parentSize.getWidth() - INNER_CONTAINER_SHAPE_HORIZONTAL_PADDING * 2 - PROVIDES_PORTS_LEFT_PADDING;
		int height = parentSize.getHeight() - INNER_CONTAINER_SHAPE_TOP_PADDING;
		int rectX = INTERFACE_SHAPE_WIDTH;
		int rectY = outerText.getHeight();
		if (UpdateUtil.moveAndResizeIfNeeded(innerContainerShape.getGraphicsAlgorithm(), rectX, rectY, width, height)) {
			layoutApplied = true;
		}
		return layoutApplied;
	}

	protected boolean layoutProvidesPorts(ContainerShape providesPortsContainer) {
		boolean layoutApplied = false;
		int currentY = 0;
		int maxWidth = 0;
		for (Shape shape : providesPortsContainer.getChildren()) {
			if (DUtil.layoutShapeViaFeature(getFeatureProvider(), shape)) {
				layoutApplied = true;
			}

			// Place the container at the next Y position
			if (UpdateUtil.moveIfNeeded(shape.getGraphicsAlgorithm(), 0, currentY)) {
				layoutApplied = true;
			}
			currentY += shape.getGraphicsAlgorithm().getHeight() + PORT_ROW_PADDING_HEIGHT;
			maxWidth = Math.max(maxWidth, shape.getGraphicsAlgorithm().getWidth());
		}
		// Resize container to contents and adjust position so that ports are aligned to the outer edge
		currentY = Math.max(currentY - 5, 0); // remove extra spacing, if it was added above
		if (UpdateUtil.resizeIfNeeded(providesPortsContainer.getGraphicsAlgorithm(), maxWidth, currentY)) {
			layoutApplied = true;
		}
		// NB: For FindBy shapes and the like, the normal layout was not occurring for the provides port container
		if (UpdateUtil.moveIfNeeded(providesPortsContainer.getGraphicsAlgorithm(), PROVIDES_PORTS_LEFT_PADDING, PORTS_CONTAINER_SHAPE_TOP_PADDING)) {
			layoutApplied = true;
		}
		return layoutApplied;
	}

	protected boolean layoutUsesPorts(ContainerShape usesPortsContainer) {
		boolean layoutApplied = false;
		int maxWidth = 0;
		// First pass: resize and layout contained ports, remembering max width
		for (Shape shape : usesPortsContainer.getChildren()) {
			if (DUtil.layoutShapeViaFeature(getFeatureProvider(), shape)) {
				layoutApplied = true;
			}
			maxWidth = Math.max(maxWidth, shape.getGraphicsAlgorithm().getWidth());
		}

		// Second pass: layout vertically and adjust X coordinates so that right edges line up (depends on max width)
		int currentY = 0;
		for (Shape shape : usesPortsContainer.getChildren()) {
			int xOffset = maxWidth - shape.getGraphicsAlgorithm().getWidth();
			if (UpdateUtil.moveIfNeeded(shape.getGraphicsAlgorithm(), xOffset, currentY)) {
				layoutApplied = true;
			}
			currentY += shape.getGraphicsAlgorithm().getHeight() + PORT_ROW_PADDING_HEIGHT;
		}

		// Resize container to contents
		currentY = Math.max(currentY - 5, 0); // remove extra spacing, if it was added above
		if (UpdateUtil.resizeIfNeeded(usesPortsContainer.getGraphicsAlgorithm(), maxWidth, currentY)) {
			layoutApplied = true;
		}
		return layoutApplied;
	}

	/**
	 * Adjusts the position of the uses ports container so that ports are aligned to the outer edge
	 */
	protected boolean adjustUsesPortsPosition(ContainerShape usesPortsContainer) {
		if (usesPortsContainer != null) {
			int parentWidth = usesPortsContainer.getContainer().getGraphicsAlgorithm().getWidth();
			int xOffset = parentWidth - usesPortsContainer.getGraphicsAlgorithm().getWidth();
			return UpdateUtil.moveIfNeeded(usesPortsContainer.getGraphicsAlgorithm(), xOffset, PORTS_CONTAINER_SHAPE_TOP_PADDING);
		}
		return false;
	}

	private boolean layoutSuperPort(ContainerShape superPortContainerShape, Orientation hAlign) {
		boolean layoutApplied = false;
		// Resize relative to inner shape
		RHContainerShape parent = (RHContainerShape) superPortContainerShape.getContainer();
		GraphicsAlgorithm innerGa = parent.getInnerContainerShape().getGraphicsAlgorithm();
		int height = innerGa.getHeight() - SUPER_PORT_SHAPE_HEIGHT_MARGIN * 2;
		if (UpdateUtil.resizeIfNeeded(superPortContainerShape.getGraphicsAlgorithm(), SUPER_PORT_SHAPE_WIDTH, height)) {
			UpdateUtil.layoutOverlayAnchor(superPortContainerShape, hAlign);
			layoutApplied = true;
		}

		// Position at edge of inner shape
		int portX = innerGa.getX();
		if (Orientation.ALIGNMENT_LEFT.equals(hAlign)) {
			portX -= superPortContainerShape.getGraphicsAlgorithm().getWidth();
		} else {
			portX = innerGa.getX() + innerGa.getWidth();
		}
		int portY = innerGa.getY() + SUPER_PORT_SHAPE_HEIGHT_MARGIN;
		if (UpdateUtil.moveIfNeeded(superPortContainerShape.getGraphicsAlgorithm(), portX, portY)) {
			layoutApplied = true;
		}
		return layoutApplied;
	}

	protected void layoutSuperProvidesPorts(ContainerShape superProvidesPortsContainerShape) {
		layoutSuperPort(superProvidesPortsContainerShape, Orientation.ALIGNMENT_LEFT);
	}

	protected void layoutSuperUsesPorts(ContainerShape superUsesPortsContainerShape) {
		layoutSuperPort(superUsesPortsContainerShape, Orientation.ALIGNMENT_RIGHT);
	}

	/**
	 * Returns the root container shape for the given {@link PictogramElement}.
	 * @param pictogramElement
	 * @return
	 */
	protected RHContainerShape getRootContainerShape(PictogramElement pictogramElement) {
		if (pictogramElement instanceof RHContainerShape) {
			return (RHContainerShape) pictogramElement;
		}
		return ScaEcoreUtils.getEContainerOfType(pictogramElement, RHContainerShape.class);
	}

	@Override
	public boolean canDirectEdit(IDirectEditingContext context) {
		RHContainerShape containerShape = getRootContainerShape(context.getPictogramElement());
		Object obj = getBusinessObjectForPictogramElement(containerShape);
		if (isMainBusinessObjectApplicable(obj)) {
			// Allow editing only on the inner title
			return context.getGraphicsAlgorithm() == containerShape.getInnerText();
		}
		return false;
	}

	@Override
	public int getEditingType() {
		return TYPE_TEXT;
	}

	@Override
	public String getInitialValue(IDirectEditingContext context) {
		RHContainerShape containerShape = getRootContainerShape(context.getPictogramElement());
		return getInnerTitle((EObject) getBusinessObjectForPictogramElement(containerShape));
	}

	@Override
	public void setValue(final String value, IDirectEditingContext context) {
		RHContainerShape containerShape = getRootContainerShape(context.getPictogramElement());
		final EObject businessObject = (EObject) getBusinessObjectForPictogramElement(containerShape);

		// Editing domain for our transaction
		TransactionalEditingDomain editingDomain = getDiagramBehavior().getEditingDomain();

		// Perform business object manipulation in a Command
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// set usage name
				setInnerTitle(businessObject, value);
			}
		});

		// perform update, redraw
		updatePictogramElement(containerShape);
	}

	/**
	 * Updates the set of UsesPortStub objects to match the provided names
	 * @param portSupplier
	 * @param usesPortNames
	 */
	protected void updateUsesPortStubs(EObject portSupplier, List<String> usesPortNames) {
		// Mark the ports to delete
		List<UsesPortStub> portsToDelete = new ArrayList<UsesPortStub>();
		Set<String> portsToAdd = new HashSet<String>(usesPortNames);
		for (UsesPortStub uses : getUses(portSupplier)) {
			if (!portsToAdd.remove(uses.getName())) {
				portsToDelete.add(uses);
			}
		}

		// Capture the existing connection information and delete the connection
		for (UsesPortStub portStub : portsToDelete) {
			Anchor portStubPe = DUtil.getPictogramElementForBusinessObject(getDiagram(), portStub, Anchor.class);
			RHContainerShape rhShape = ScaEcoreUtils.getEContainerOfType(portStubPe, RHContainerShape.class);
			for (Connection connection : portStubPe.getOutgoingConnections()) {
				Object connectionObject = getBusinessObjectForPictogramElement(connection);
				if (connectionObject instanceof SadConnectInterface) {
					SadConnectInterface ci = (SadConnectInterface) connectionObject;
					// Remove any property reference to the connection id (this exists for FindBys)
					EList<Property> properties = rhShape.getProperties();
					for (int i = 0; i < properties.size(); i++) {
						if (ci.getId().equals(properties.get(i).getValue())) {
							properties.remove(i);
						}
					}
					EcoreUtil.delete((SadConnectInterface) connectionObject);
				}
			}
		}

		// Remove deleted ports
		getUses(portSupplier).removeAll(portsToDelete);

		// Add new ports to the element
		for (String usesPortName : portsToAdd) {
			// Add the new port to the Domain model
			UsesPortStub usesPortStub = PartitioningFactory.eINSTANCE.createUsesPortStub();
			usesPortStub.setName(usesPortName);
			getUses(portSupplier).add(usesPortStub);
		}
	}

	/**
	 * Updates the set of ProvidesPortStub objects to match the provides names
	 * @param portSupplier
	 * @param providesPortNames
	 */
	protected void updateProvidesPortStubs(EObject portSupplier, List<String> providesPortNames) {
		// Mark the ports to delete
		List<ProvidesPortStub> portsToDelete = new ArrayList<ProvidesPortStub>();
		Set<String> portsToAdd = new HashSet<String>(providesPortNames);
		for (ProvidesPortStub provides : getProvides(portSupplier)) {
			if (!portsToAdd.remove(provides.getName())) {
				portsToDelete.add(provides);
			}
		}

		// Capture the existing connection information and delete the connection
		for (ProvidesPortStub portStub : portsToDelete) {
			Anchor portStubPe = DUtil.getPictogramElementForBusinessObject(getDiagram(), portStub, Anchor.class);
			RHContainerShape rhShape = ScaEcoreUtils.getEContainerOfType(portStubPe, RHContainerShape.class);
			for (Connection connection : portStubPe.getIncomingConnections()) {
				Object connectionObject = getBusinessObjectForPictogramElement(connection);
				if (connectionObject instanceof SadConnectInterface) {
					SadConnectInterface ci = (SadConnectInterface) connectionObject;
					// Remove any property reference to the connection id (this exists for FindBys)
					EList<Property> properties = rhShape.getProperties();
					for (int i = 0; i < properties.size(); i++) {
						if (ci.getId().equals(properties.get(i).getValue())) {
							properties.remove(i);
						}
					}
					EcoreUtil.delete((SadConnectInterface) connectionObject);
				}
			}
		}

		// Remove deleted ports
		getProvides(portSupplier).removeAll(portsToDelete);

		// Add new ports to the element
		for (String providesPortName : portsToAdd) {
			// Add the new port to the Domain model
			ProvidesPortStub providesPortStub = PartitioningFactory.eINSTANCE.createProvidesPortStub();
			providesPortStub.setName(providesPortName);
			getProvides(portSupplier).add(providesPortStub);
		}
	}
}
