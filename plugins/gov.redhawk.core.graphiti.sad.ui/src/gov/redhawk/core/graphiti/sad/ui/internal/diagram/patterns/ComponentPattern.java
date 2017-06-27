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
package gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.ILayoutContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Ellipse;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import gov.redhawk.core.graphiti.sad.ui.diagram.providers.WaveformImageProvider;
import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxFactory;
import gov.redhawk.core.graphiti.sad.ui.utils.SADUtils;
import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractPortSupplierPattern;
import gov.redhawk.core.graphiti.ui.diagram.providers.ImageProvider;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import gov.redhawk.core.graphiti.ui.util.UpdateUtil;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.ExternalPorts;
import mil.jpeojtrs.sca.sad.ExternalProperty;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class ComponentPattern extends AbstractPortSupplierPattern {

	// Property key/value pairs help us identify Shapes to enable/disable user actions (move, resize, delete, remove
	// etc.)
	public static final String SHAPE_START_ORDER_ELLIPSE_SHAPE = "startOrderEllipseShape";

	// These are property key/value pairs that help us resize an existing shape by properly identifying
	// graphicsAlgorithms
	public static final String GA_START_ORDER_TEXT = "startOrderText";
	public static final String GA_START_ORDER_ELLIPSE = "startOrderEllipse";

	// Shape size constants
	private static final int START_ORDER_ELLIPSE_DIAMETER = 17;
	private static final int START_ORDER_TOP_TEXT_PADDING = 0;
	private static final int START_ORDER_ELLIPSE_LEFT_PADDING = 20;
	private static final int START_ORDER_ELLIPSE_RIGHT_PADDING = 5;
	private static final int START_ORDER_ELLIPSE_TOP_PADDING = 5;

	// Default start order text value for components that do not have a start order declared
	private static final String NO_START_ORDER_STRING = "";

	private URI spdUri = null;

	public ComponentPattern() {
		super(null);
	}

	public URI getSpdUri() {
		return spdUri;
	}

	public void setSpdUri(URI spdUri) {
		this.spdUri = spdUri;
	}

	@Override
	public String getCreateName() {
		return "Component";
	}

	// THE FOLLOWING THREE METHODS DETERMINE IF PATTERN IS APPLICABLE TO OBJECT
	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof SadComponentInstantiation;
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

	// DIAGRAM FEATURES

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof SadComponentInstantiation) {
			if (context.getTargetContainer() instanceof Diagram || DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class) != null) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		// TODO: this used to return false, doing this so we can remove components during the
		// RHDiagramUpdateFeature...might be negative consequences
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof SadComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if the user has selected a pictogram element that is linked with
	 * a SADComponentInstantiation instance
	 */
	@Override
	public boolean canDelete(IDeleteContext context) {
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof SadComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Delete the SadComponentInstantiation linked to the PictogramElement.
	 */
	@Override
	public void delete(IDeleteContext context) {
		final SadComponentInstantiation ciToDelete = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());
		TransactionalEditingDomain editingDomain = getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// delete component from SoftwareAssembly
				deleteComponentInstantiation(ciToDelete, sad);

				// re-organize start order
				SADUtils.organizeStartOrder(sad, getDiagram(), getFeatureProvider());

			}
		});

		// delete graphical component for component as well as removing all connections
		IRemoveContext rc = new RemoveContext(context.getPictogramElement());
		IFeatureProvider featureProvider = getFeatureProvider();
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
		if (removeFeature != null) {
			removeFeature.remove(rc);
		}

		// redraw start order
		// DUtil.organizeDiagramStartOrder(diagram);
	}

	/**
	 * Delete SadComponentInstantiation and corresponding SadComponentPlacement business object from SoftwareAssembly
	 * This method should be executed within a RecordingCommand.
	 * @param ciToDelete
	 * @param sad
	 */
	public static void deleteComponentInstantiation(final SadComponentInstantiation ciToDelete, final SoftwareAssembly sad) {

		// assembly controller may reference componentInstantiation
		// delete reference if applicable
		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null
			&& sad.getAssemblyController().getComponentInstantiationRef().getInstantiation().equals(ciToDelete)) {
			EcoreUtil.delete(sad.getAssemblyController().getComponentInstantiationRef());
			sad.getAssemblyController().setComponentInstantiationRef(null);
		}

		// get placement for instantiation and delete it from sad partitioning after we look at removing the component
		// file ref.
		SadComponentPlacement placement = (SadComponentPlacement) ciToDelete.getPlacement();

		// find and remove any attached connections
		// gather connections
		List<SadConnectInterface> connectionsToRemove = new ArrayList<SadConnectInterface>();
		if (sad.getConnections() != null) {
			for (SadConnectInterface connectionInterface : sad.getConnections().getConnectInterface()) {
				// we need to do thorough null checks here because of the many connection possibilities. Firstly a
				// connection requires only a usesPort and either (providesPort || componentSupportedInterface)
				// and therefore null checks need to be performed.
				// FindBy connections don't have ComponentInstantiationRefs and so they can also be null
				if ((connectionInterface.getComponentSupportedInterface() != null
					&& connectionInterface.getComponentSupportedInterface().getComponentInstantiationRef() != null
					&& ciToDelete.getId().equals(connectionInterface.getComponentSupportedInterface().getComponentInstantiationRef().getRefid()))
					|| (connectionInterface.getUsesPort() != null && connectionInterface.getUsesPort().getComponentInstantiationRef() != null
						&& ciToDelete.getId().equals(connectionInterface.getUsesPort().getComponentInstantiationRef().getRefid()))
					|| (connectionInterface.getProvidesPort() != null && connectionInterface.getProvidesPort().getComponentInstantiationRef() != null
						&& ciToDelete.getId().equals(connectionInterface.getProvidesPort().getComponentInstantiationRef().getRefid()))) {
					connectionsToRemove.add(connectionInterface);
				}
			}
		}
		// remove gathered connections
		if (sad.getConnections() != null) {
			sad.getConnections().getConnectInterface().removeAll(connectionsToRemove);
		}

		// remove any associated external ports
		if (sad.getExternalPorts() != null) {
			List<Port> externalPortsToRemove = new ArrayList<Port>();
			for (Port port : sad.getExternalPorts().getPort()) {
				if (port.getComponentInstantiationRef().getRefid().equals(ciToDelete.getId())) {
					externalPortsToRemove.add(port);
				}
			}

			for (Port port : externalPortsToRemove) {
				sad.getExternalPorts().getPort().remove(port);
			}

			if (sad.getExternalPorts().getPort().isEmpty()) {
				sad.setExternalPorts(null);
			}
		}

		// remove any associated external properties
		if (sad.getExternalProperties() != null) {
			List<ExternalProperty> externalPropertiesToRemove = new ArrayList<ExternalProperty>();
			for (ExternalProperty property : sad.getExternalProperties().getProperties()) {
				if (property.getCompRefID().equals(ciToDelete.getId())) {
					externalPropertiesToRemove.add(property);
				}
			}

			for (ExternalProperty property : externalPropertiesToRemove) {
				sad.getExternalProperties().getProperties().remove(property);
			}

			if (sad.getExternalProperties().getProperties().isEmpty()) {
				sad.setExternalProperties(null);
			}
		}
		// delete component file if applicable
		// figure out which component file we are using and if no other component placements using it then remove it.
		ComponentFile componentFileToRemove = placement.getComponentFileRef().getFile();
		// check components (not in host collocation)
		for (SadComponentPlacement p : sad.getPartitioning().getComponentPlacement()) {
			if (p != placement && p.getComponentFileRef().getRefid().equals(placement.getComponentFileRef().getRefid())) {
				componentFileToRemove = null;
				break;
			}
		}
		// check components in host collocation
		for (HostCollocation hc : sad.getPartitioning().getHostCollocation()) {
			for (SadComponentPlacement p : hc.getComponentPlacement()) {
				if (p != placement && p.getComponentFileRef().getRefid().equals(placement.getComponentFileRef().getRefid())) {
					componentFileToRemove = null;
					break;
				}
			}
			if (componentFileToRemove == null) {
				break;
			}
		}
		if (componentFileToRemove != null) {
			sad.getComponentFiles().getComponentFile().remove(componentFileToRemove);
		}

		// delete component placement
		EcoreUtil.delete(placement);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		return true;
	}

	@Override
	protected RHContainerShape createContainerShape() {
		return RHSadGxFactory.eINSTANCE.createComponentShape();
	}

	@Override
	protected void initializeShape(RHContainerShape shape, IAddContext context) {
		if (!DUtil.isDiagramRuntime(getDiagram())) {
			createStartOrderEllipse(shape.getInnerContainerShape(), (SadComponentInstantiation) context.getNewObject());
		}
	}

	protected ContainerShape createStartOrderEllipse(ContainerShape parentShape, SadComponentInstantiation instantiation) {
		// Create ellipse shape to display component start order
		ContainerShape startOrderEllipseShape = Graphiti.getCreateService().createContainerShape(parentShape, false);
		Graphiti.getPeService().setPropertyValue(startOrderEllipseShape, DUtil.SHAPE_TYPE, ComponentPattern.SHAPE_START_ORDER_ELLIPSE_SHAPE);
		Ellipse startOrderEllipse = Graphiti.getCreateService().createEllipse(startOrderEllipseShape);
		StyleUtil.setStyle(startOrderEllipse, getStartOrderStyle(instantiation));
		Graphiti.getPeService().setPropertyValue(startOrderEllipse, DUtil.GA_TYPE, ComponentPattern.GA_START_ORDER_ELLIPSE);
		Graphiti.getGaLayoutService().setSize(startOrderEllipse, START_ORDER_ELLIPSE_DIAMETER, START_ORDER_ELLIPSE_DIAMETER);

		// Create text shape to display start order
		Shape startOrderTextShape = Graphiti.getPeCreateService().createShape(startOrderEllipseShape, false);
		Text startOrderText = Graphiti.getCreateService().createText(startOrderTextShape, getStartOrderValue(instantiation));
		Graphiti.getPeService().setPropertyValue(startOrderText, DUtil.GA_TYPE, ComponentPattern.GA_START_ORDER_TEXT);
		StyleUtil.setStyle(startOrderText, StyleUtil.START_ORDER);
		Graphiti.getGaLayoutService().setSize(startOrderText, START_ORDER_ELLIPSE_DIAMETER, START_ORDER_ELLIPSE_DIAMETER);

		return startOrderEllipseShape;
	}

	@Override
	public boolean layout(ILayoutContext context) {
		boolean layoutApplied = super.layout(context);

		// Layout the start order ellipse, if any
		ComponentShape componentShape = (ComponentShape) context.getPictogramElement();
		ContainerShape startOrderEllipse = componentShape.getStartOrderEllipseShape();
		if (startOrderEllipse != null) {
			// Move the ellipse to the upper right corner of its parent
			int xOffset = startOrderEllipse.getContainer().getGraphicsAlgorithm().getWidth()
				- (START_ORDER_ELLIPSE_DIAMETER + START_ORDER_ELLIPSE_RIGHT_PADDING);
			if (UpdateUtil.moveIfNeeded(startOrderEllipse.getGraphicsAlgorithm(), xOffset, START_ORDER_ELLIPSE_TOP_PADDING)) {
				layoutApplied = true;
			}

			// Position the text in the center of the ellipse
			Text startOrderText = componentShape.getStartOrderText();
			IDimension textDimension = DUtil.calculateTextSize(startOrderText);
			int textX = START_ORDER_ELLIPSE_DIAMETER / 2 - textDimension.getWidth() / 2;
			if (UpdateUtil.moveIfNeeded(startOrderText, textX, START_ORDER_TOP_TEXT_PADDING)) {
				layoutApplied = true;
			}
		}
		return layoutApplied;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		IReason reason = super.updateNeeded(context);
		if (reason.toBoolean()) {
			return reason;
		}

		// Check the start order ellipse, if any
		ComponentShape componentShape = (ComponentShape) context.getPictogramElement();
		ContainerShape startOrderEllipse = componentShape.getStartOrderEllipseShape();
		if (startOrderEllipse != null) {
			// Check the ellipse style
			SadComponentInstantiation instantiation = (SadComponentInstantiation) getBusinessObjectForPictogramElement(context.getPictogramElement());
			String startOrderStyle = getStartOrderStyle(instantiation);
			if (!StyleUtil.isStyleSet(startOrderEllipse.getGraphicsAlgorithm(), startOrderStyle)) {
				return Reason.createTrueReason("Start order ellipse needs update");
			}

			// Check the text value
			Text startOrderText = componentShape.getStartOrderText();
			if (!startOrderText.getValue().equals(getStartOrderValue(instantiation))) {
				return Reason.createTrueReason("Start order number needs update");
			}
		}

		return Reason.createFalseReason();
	}

	@Override
	public boolean update(IUpdateContext context) {
		boolean updateStatus = super.update(context);

		// Check the start order ellipse, if any
		ComponentShape componentShape = (ComponentShape) context.getPictogramElement();
		ContainerShape startOrderEllipse = componentShape.getStartOrderEllipseShape();
		if (startOrderEllipse != null) {
			// Check the ellipse style
			SadComponentInstantiation instantiation = (SadComponentInstantiation) getBusinessObjectForPictogramElement(context.getPictogramElement());
			String startOrderStyle = getStartOrderStyle(instantiation);
			if (!StyleUtil.isStyleSet(startOrderEllipse.getGraphicsAlgorithm(), startOrderStyle)) {
				StyleUtil.setStyle(startOrderEllipse.getGraphicsAlgorithm(), startOrderStyle);
				updateStatus = true;
			}

			// Check the text value
			Text startOrderText = componentShape.getStartOrderText();
			String startOrderValue = getStartOrderValue(instantiation);
			if (!startOrderText.getValue().equals(startOrderValue)) {
				startOrderText.setValue(startOrderValue);
				updateStatus = true;
			}
		}

		return updateStatus;
	}

	public boolean canMoveShape(IMoveShapeContext context) {

		SadComponentInstantiation sadComponentInstantiation = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());
		if (sadComponentInstantiation == null) {
			return false;
		}

		// if moving to HostCollocation to Sad Partitioning
		if (context.getTargetContainer() instanceof Diagram || DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class) != null) {
			return true;
		}
		return false;

	}

	/**
	 * Moves Component shape.
	 * if moving to HostCollocation or away from one modify underlying model and allow parent class to perform graphical
	 * move
	 * if moving within the same container allow parent class to perform graphical move
	 */
	public void moveShape(IMoveShapeContext context) {
		SadComponentInstantiation ci = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());

		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// if moving inside the same container
		if (context.getSourceContainer() == context.getTargetContainer()) {
			super.moveShape(context);
		}

		HostCollocation sourceHostCollocation = DUtil.getBusinessObject(context.getSourceContainer(), HostCollocation.class);
		HostCollocation targetHostCollocation = DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class);

		if (sourceHostCollocation != null && targetHostCollocation != null && sourceHostCollocation != targetHostCollocation) {
			// Moving from one host collocation to another
			sourceHostCollocation.getComponentPlacement().remove((SadComponentPlacement) ci.getPlacement());
			targetHostCollocation.getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		} else if (targetHostCollocation != null && context.getSourceContainer() instanceof Diagram) {
			// Moving from top-level partitioning to a host collocation
			sad.getPartitioning().getComponentPlacement().remove(ci.getPlacement());
			targetHostCollocation.getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		} else if (sourceHostCollocation != null && context.getTargetContainer() instanceof Diagram) {
			// Moving from a host collocation to top-level partitioning
			sad.getPartitioning().getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			sourceHostCollocation.getComponentPlacement().remove((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		}
	}

	/**
	 * Return the highest start order for all components in the SAD.
	 * Returns null if no components are found
	 * @param sad
	 * @return
	 */
	public static BigInteger determineHighestStartOrder(final SoftwareAssembly sad) {

		BigInteger highestStartOrder = null;
		List<SadComponentInstantiation> cis = getAllComponents(sad);
		if (cis != null && cis.size() > 0) {
			highestStartOrder = cis.get(0).getStartOrder();

		}
		for (int i = 1; i < cis.size(); i++) {
			SadComponentInstantiation c = cis.get(i);

			// If a component is found, and it's start order is null, assume it is the assembly controller
			// Assembly controllers should always be at the beginning of the start order, so mark highest start order as
			// zero
			if (highestStartOrder == null) {
				highestStartOrder = BigInteger.ZERO;
			}

			// check for higher start order
			if (c.getStartOrder() != null && c.getStartOrder().compareTo(highestStartOrder) >= 0) {
				highestStartOrder = c.getStartOrder();
			}
		}

		// If there are no components, highestStartOrder will be null
		return highestStartOrder;
	}

	/**
	 * Get all components in sad
	 * @param sad
	 * @return
	 */
	public static List<SadComponentInstantiation> getAllComponents(final SoftwareAssembly sad) {
		final List<SadComponentInstantiation> retVal = new ArrayList<SadComponentInstantiation>();
		if (sad.getPartitioning() != null) {
			for (final SadComponentPlacement cp : sad.getPartitioning().getComponentPlacement()) {
				retVal.addAll(cp.getComponentInstantiation());
			}
			for (final HostCollocation h : sad.getPartitioning().getHostCollocation()) {
				for (final SadComponentPlacement cp : h.getComponentPlacement()) {
					retVal.addAll(cp.getComponentInstantiation());
				}
			}
		}

		return retVal;
	}

	@Override
	protected String getOuterTitle(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return getOuterTitle((SadComponentInstantiation) obj);
		}
		return null;
	}

	@Override
	protected String getInnerTitle(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return getInnerTitle((SadComponentInstantiation) obj);
		}
		return null;
	}

	/**
	 * Provides the title of the outer shape
	 * @param ci
	 * @return
	 */
	public String getOuterTitle(SadComponentInstantiation ci) {
		try {
			return ci.getPlacement().getComponentFileRef().getFile().getSoftPkg().getName();
		} catch (NullPointerException e) {
			return "< Component Bad Reference >";
		}
	}

	/**
	 * Provides the title of the inner shape
	 * @param ci
	 * @return
	 */
	public String getInnerTitle(SadComponentInstantiation ci) {
		String usageName = ci.getUsageName();
		return (usageName != null) ? usageName : ci.getId();
	}

	@Override
	protected void setInnerTitle(EObject businessObject, String value) {
		((SadComponentInstantiation) businessObject).setUsageName(value);
		FindComponent fc = ((SadComponentInstantiation) businessObject).getFindComponent();
		if (fc != null && fc.getNamingService() != null) {
			fc.getNamingService().setName(value);
		} else {
			fc = SadFactory.eINSTANCE.createFindComponent();
			NamingService ns = PartitioningFactory.eINSTANCE.createNamingService();
			ns.setName(value);
			fc.setNamingService(ns);
			((SadComponentInstantiation) businessObject).setFindComponent(fc);
		}
	}

	@Override
	protected EList<UsesPortStub> getUses(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getUses();
		}
		return null;
	}

	@Override
	protected EList<ProvidesPortStub> getProvides(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getProvides();
		}
		return null;
	}

	@Override
	protected ComponentSupportedInterfaceStub getInterface(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getInterfaceStub();
		}
		return null;
	}

	@Override
	protected String getOuterImageId() {
		return ImageProvider.IMG_SPD;
	}

	@Override
	protected String getInnerImageId() {
		return WaveformImageProvider.IMG_COMPONENT;
	}

	@Override
	protected String getStyleForOuter() {
		return StyleUtil.OUTER_SHAPE;
	}

	@Override
	protected String getStyleForInner() {
		return StyleUtil.COMPONENT_INNER;
	}

	protected String getStartOrderStyle(SadComponentInstantiation instantiation) {
		if (SoftwareAssembly.Util.isAssemblyController(instantiation)) {
			return StyleUtil.ASSEMBLY_CONTROLLER_ELLIPSE;
		} else {
			return StyleUtil.START_ORDER_ELLIPSE;
		}
	}

	protected String getStartOrderValue(SadComponentInstantiation instantiation) {
		if (instantiation.getStartOrder() == null) {
			return ComponentPattern.NO_START_ORDER_STRING;
		} else {
			return instantiation.getStartOrder().toString();
		}
	}

	@Override
	protected int getMinimumInnerWidth(RHContainerShape shape) {
		int minimumWidth = super.getMinimumInnerWidth(shape);

		// If the component shape has a start order ellipse, take the required size and padding into account
		ContainerShape ellipseShape = ((ComponentShape) shape).getStartOrderEllipseShape();
		if (ellipseShape != null) {
			minimumWidth += ComponentPattern.START_ORDER_ELLIPSE_LEFT_PADDING + ellipseShape.getGraphicsAlgorithm().getWidth()
				+ ComponentPattern.START_ORDER_ELLIPSE_RIGHT_PADDING;
		}
		return minimumWidth;
	}

	/**
	 * Returns component, sad, and external ports. Order does matter.
	 */
	protected List<EObject> getBusinessObjectsToLink(EObject componentInstantiation) {
		// get external ports
		ExternalPorts externalPorts = DUtil.getDiagramSAD(getDiagram()).getExternalPorts();

		// get sad from diagram, we need to link it to all shapes so the diagram will update when changes occur to
		// assembly controller and external ports
		List<EObject> businessObjectsToLink = new ArrayList<EObject>();
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());
		// ORDER MATTERS, CI must be first
		businessObjectsToLink.add(componentInstantiation);
		businessObjectsToLink.add(sad);
		if (externalPorts != null) {
			businessObjectsToLink.add(externalPorts);
		}

		return businessObjectsToLink;
	}

}
