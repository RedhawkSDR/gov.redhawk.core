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
package gov.redhawk.core.graphiti.ui.diagram.features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Point;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.AnchorContainer;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.zest.layouts.InvalidLayoutConfiguration;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutBendPoint;
import org.eclipse.zest.layouts.LayoutEntity;
import org.eclipse.zest.layouts.LayoutRelationship;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.eclipse.zest.layouts.dataStructures.BendPoint;
import org.eclipse.zest.layouts.exampleStructures.SimpleNode;
import org.eclipse.zest.layouts.exampleStructures.SimpleRelationship;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.LayoutUtil;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public class LayoutDiagramFeature extends AbstractCustomFeature {

	private static final int HORIZONTAL_PADDING = 100;
	private static final int VERTICAL_PADDING = 50;

	public LayoutDiagramFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getDescription() {
		return "Arrange all diagram elements using a horizontal tree layout";
	}

	@Override
	public String getName() {
		return "&Arrange All";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		PictogramElement[] pes = context.getPictogramElements();
		if (pes != null && pes.length == 1 && pes[0] instanceof Diagram) {
			return true;
		}
		return false;
	}

	@Override
	public void execute(ICustomContext context) {
		try {

			// Updates the layout for all shapes within a HostCollocation. Need to do this first so we know the size
			// and dimensions of the HostCollocations for the next step.
			layoutHostCollocationContents();

			layoutDiagramContents();

		} catch (InvalidLayoutConfiguration e) {
			e.printStackTrace(); // SUPPRESS CHECKSTYLE handle exception
		}
	}

	private void layoutHostCollocationContents() throws InvalidLayoutConfiguration {
		TreeLayoutAlgorithm hostCoLayoutAlgorithm = new HorizontalTreeLayoutAlgorithm(LayoutStyles.ENFORCE_BOUNDS | LayoutStyles.NO_LAYOUT_NODE_RESIZING);

		// Get a list of all host collocations
		List<ContainerShape> hostCoList = new ArrayList<ContainerShape>();
		EList<Shape> children = getDiagram().getChildren();
		for (Shape shape : children) {
			if (DUtil.getBusinessObject(shape) instanceof HostCollocation) {
				hostCoList.add((ContainerShape) shape);
			}
		}

		for (ContainerShape hostCollocation : hostCoList) {
			// Get all shapes and connections contained in the host collocation
			Map<Shape, SimpleNode> hostCoMap = getLayoutEntities(hostCollocation);

			EList<Connection> connections = getDiagram().getConnections();

			EList<Connection> hostCoConnections = new BasicEList<>();
			for (Connection connection : connections) {
				RHContainerShape source = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
				RHContainerShape target = ScaEcoreUtils.getEContainerOfType(connection.getEnd(), RHContainerShape.class);

				// We only care about connections where BOTH shapes are contained within the HostCollocation.
				// Any connections that originate/terminate outside the HostCollocation will be handled at the diagram
				// level
				// and treated as connections to/from the HostCollocation itself (as opposed to to/from the contained
				// shape)
				if (source.getContainer() == hostCollocation && target.getContainer() == hostCollocation) {
					hostCoConnections.add(connection);
				}
			}
			LayoutRelationship[] hostCoRelationships = getLayoutRelationships(hostCoMap, hostCoConnections);

			// Get all shapes to be considered by the layout algorithm
			LayoutEntity[] hostCoEntities = hostCoMap.values().toArray(new LayoutEntity[0]);

			// Set host collocation boundaries
			Point bounds = LayoutUtil.calculateContainerBounds(hostCollocation);
			hostCollocation.getGraphicsAlgorithm().setWidth(bounds.getX() + HORIZONTAL_PADDING);
			hostCollocation.getGraphicsAlgorithm().setHeight(bounds.getY() + VERTICAL_PADDING);

			// Apply the layout, and then update the shape coordinates
			GraphicsAlgorithm hostCoGA = hostCollocation.getGraphicsAlgorithm();
			hostCoLayoutAlgorithm.applyLayout(hostCoEntities, hostCoRelationships, 15, 0, hostCoGA.getWidth(), hostCoGA.getHeight(), false, false);

			// Update all Graphiti Shapes and Connections with their new coordinates
			updateShapeCoordinates(hostCoLayoutAlgorithm.getRoots(), hostCoEntities, hostCoRelationships);
		}
	}

	/**
	 * Update the layout for all top-level shapes in the diagram. HostCollocations are treated as a single shape.
	 * @throws InvalidLayoutConfiguration
	 */
	private void layoutDiagramContents() throws InvalidLayoutConfiguration {
		TreeLayoutAlgorithm layoutAlgorithm = new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);

		// Get the map of SimpleNode per Shapes (not including shapes contained in HostCollocations)
		Map<Shape, SimpleNode> map = getLayoutEntities(getDiagram());

		// Get LayoutRelationships (i.e. connections) which will be used to sort shapes into different trees
		EList<Connection> connections = getDiagram().getConnections();
		EList<Connection> diagramConnections = new BasicEList<>();
		for (Connection connection : connections) {
			RHContainerShape source = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
			RHContainerShape target = ScaEcoreUtils.getEContainerOfType(connection.getEnd(), RHContainerShape.class);

			// We only care about connections where both shapes are NOT contained within the SAME HostCollocation.
			// Any connections that originate/terminate within the same HostCollocation will be handled at the
			// HostCollocation level and ignored at this point.
			if (DUtil.getBusinessObject(source.getContainer()) instanceof HostCollocation
				&& DUtil.getBusinessObject(target.getContainer()) instanceof HostCollocation) {
				if (source.getContainer() == target.getContainer()) {
					continue;
				}
			}
			diagramConnections.add(connection);
		}
		LayoutRelationship[] diagramRelationships = getLayoutRelationships(map, diagramConnections);

		// Get all shapes to be considered by the layout algorithm
		LayoutEntity[] entities = map.values().toArray(new LayoutEntity[0]);

		// Get a map of the self connection anchor locations
		final Map<Connection, Point> selves = getSelfConnections();

		// Determine the dimensions required to house all of our shapes
		Point diagramBounds = LayoutUtil.calculateContainerBounds(getDiagram());

		// Apply the LayoutAlgorithmn
		layoutAlgorithm.applyLayout(entities, diagramRelationships, 0, 0, diagramBounds.getX(), diagramBounds.getY(), false, false);

		// Update all Graphiti Shapes and Connections with their new coordinates
		updateShapeCoordinates(layoutAlgorithm.getRoots(), entities, diagramRelationships);

		// Reposition the self connections bendpoints
		adaptSelfBendPoints(selves);
	}

	/**
	 * Build map for all contained shapes. Creates a new {@link SimpleNode} for
	 * @return a {@link Map} of {@link SimpleNode} per {@link Shape}
	 */
	private Map<Shape, SimpleNode> getLayoutEntities(ContainerShape containerShape) {
		Map<Shape, SimpleNode> map = new HashMap<Shape, SimpleNode>();
		EList<Shape> children = containerShape.getChildren();
		for (Shape shape : children) {
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			SimpleNode entity = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
			map.put(shape, entity);
		}
		return map;
	}

	/**
	 * @param map a {@link Map} of {@link SimpleNode} per {@link Shape} - used to link {@link ConnectionRelationship} to
	 * source and target entities
	 * @return an array of {@link LayoutRelationship}s
	 */
	private LayoutRelationship[] getLayoutRelationships(Map<Shape, SimpleNode> map, EList<Connection> connections) {
		List<LayoutRelationship> list = new ArrayList<LayoutRelationship>();
		for (Connection connection : connections) {
			RHContainerShape source = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
			RHContainerShape target = ScaEcoreUtils.getEContainerOfType(connection.getEnd(), RHContainerShape.class);

			// Create a relationship
			ConnectionRelationship relationship = createLayoutRelationship(map, source, target);
			if (relationship != null) {
				list.add(populateLayoutRelationship(relationship, connection));
			}
		}

		return list.toArray(new LayoutRelationship[0]);
	}

	/**
	 * Create a {@link ConnectionRelationship} relationships between the source and target, if appropriate.
	 * @param map - a map of {@link SimpleNode} to {@link Shape}, which has been populated with all the shapes that the
	 * root ContainerShape cares about
	 * @param source - the true {@link RHContainerShape} that is the source of the connection
	 * @param target - the true {@link RHContainerShape} that is the target of the connection
	 * 
	 * @param targetNode - the {@link SimpleNode} that we will treat as the the target of the connection (could refer to
	 * a {@link RHContainerShape} or a {@link ContainerShape} representing a HostCollocation
	 * @return {@link ConnectionRelationship} if one is created, null otherwise.
	 */
	private ConnectionRelationship createLayoutRelationship(Map<Shape, SimpleNode> map, RHContainerShape source, RHContainerShape target) {

		// The SimpleNode that we will treat as the connection source, either a ComponentShape or HostCollocation
		SimpleNode sourceNode = null;

		// The SimpleNode that we will treat as the connection target, either a ComponentShape or HostCollocation
		SimpleNode targetNode = null;

		// Check to see if the source/target are inside a HostCollocation
		HostCollocation sourceHostCo = ScaEcoreUtils.getEContainerOfType(DUtil.getBusinessObject(source.getContainer()), HostCollocation.class);
		HostCollocation targetHostCo = ScaEcoreUtils.getEContainerOfType(DUtil.getBusinessObject(target.getContainer()), HostCollocation.class);

		/**
		 * Possible relationships:
		 * - Both entities are in the same container and thus have a normal relationship.
		 * - Both entities are in a HostCollocation, but in DIFFERENT HostCollocations. Treat each HostCollocation as an
		 * end-point for the relationship.
		 * - Only the source is in a HostCollocation. Make the relationship between the HostCollocation and the target.
		 * - Only target is in a HostCollocation. Make relationship between the HostCollocation and the source.
		 */
		if ((sourceHostCo == targetHostCo)) {
			sourceNode = map.get(source);
			targetNode = map.get(target);
		} else if (sourceHostCo != null && targetHostCo != null) {
			sourceNode = map.get((Shape) source.getContainer());
			targetNode = map.get((Shape) target.getContainer());
		} else if (sourceHostCo != null) {
			sourceNode = map.get((Shape) source.getContainer());
			targetNode = map.get(target);
		} else if (targetHostCo != null) {
			sourceNode = map.get(source);
			targetNode = map.get((Shape) target.getContainer());
		}

		// we don't add self relations to avoid Cycle errors
		if (source != target) {
			return new ConnectionRelationship(sourceNode, targetNode, (source != target));
		}

		return null;
	}

	/**
	 * Populates {@link ConnectionRelationship} with connection specific data
	 * @param relationship
	 * @param connection
	 * @param label
	 * @return
	 */
	private ConnectionRelationship populateLayoutRelationship(ConnectionRelationship relationship, Connection connection) {
		relationship.setGraphData(connection);
		relationship.clearBendPoints();
		EList<ConnectionDecorator> decorators = connection.getConnectionDecorators();
		for (ConnectionDecorator decorator : decorators) {
			if (decorator.getGraphicsAlgorithm() instanceof Text) {
				String label = ((Text) decorator.getGraphicsAlgorithm()).getValue();
				relationship.setLabel(label);
			}
		}

		FreeFormConnection ffcon = (FreeFormConnection) connection;
		EList<Point> pointList = ffcon.getBendpoints();
		List<LayoutBendPoint> bendPoints = new ArrayList<LayoutBendPoint>();
		for (int i = 0; i < pointList.size(); i++) {
			Point point = pointList.get(i);
			boolean isControlPoint = (i != 0) && (i != pointList.size() - 1);
			LayoutBendPoint bendPoint = new BendPoint(point.getX(), point.getY(), isControlPoint);
			bendPoints.add(bendPoint);
		}
		relationship.setBendPoints(bendPoints.toArray(new LayoutBendPoint[0]));

		SimpleNode sourceNode = (SimpleNode) relationship.getSourceInLayout();
		SimpleNode targetNode = (SimpleNode) relationship.getDestinationInLayout();
		sourceNode.addRelationship(relationship);
		targetNode.addRelationship(relationship);

		return relationship;
	}

	/**
	 * Used to keep track of the initial Connection locations for self connections<br/>
	 * The self connections cannot be computed by the LayoutAlgorithmn but the Nodes will probably be moved<br/>
	 * So we need to recompute the bend points locations based on the offset of the Anchor from the initial location
	 * @return a {@link Map} of initial {@link org.eclipse.graphiti.mm.pictograms.Anchor Anchor} location {@link Point}
	 * per {@link Connection}s
	 */
	private Map<Connection, Point> getSelfConnections() {
		IGaService gaService = Graphiti.getGaService();
		Map<Connection, Point> selves = new HashMap<Connection, Point>();
		EList<Connection> connections = getDiagram().getConnections();
		for (Connection connection : connections) {
			AnchorContainer source = connection.getStart().getParent();
			AnchorContainer target = connection.getEnd().getParent();
			if (source == target) {
				GraphicsAlgorithm p = source.getGraphicsAlgorithm();
				Point start = gaService.createPoint(p.getX(), p.getY());
				selves.put(connection, start);
			}
		}
		return selves;
	}

	/**
	 * Reposition the Graphiti {@link PictogramElement}s and {@link Connection}s based on the
	 * Zest {@link LayoutAlgorithm} computed locations
	 * @param entities
	 * @param connections
	 */
	private void updateShapeCoordinates(List< ? > roots, LayoutEntity[] entities, LayoutRelationship[] connections) {
		for (LayoutEntity entity : entities) {
			SimpleNode node = (SimpleNode) entity;
			Shape shape = (Shape) node.getRealObject();
			Double x = node.getX();
			Double y = node.getY();
			shape.getGraphicsAlgorithm().setX(x.intValue());
			shape.getGraphicsAlgorithm().setY(y.intValue());
		}

		IGaService gaService = Graphiti.getGaService();
		for (LayoutRelationship relationship : connections) {
			ConnectionRelationship rel = (ConnectionRelationship) relationship;
			// Using FreeFormConnections with BendPoints, we reset them to the Zest computed locations
			FreeFormConnection connection = (FreeFormConnection) rel.getGraphData();
			connection.getBendpoints().clear();
			LayoutBendPoint[] bendPoints = rel.getBendPoints();
			for (LayoutBendPoint bendPoint : bendPoints) {
				Double x = bendPoint.getX();
				Double y = bendPoint.getY();
				Point p = gaService.createPoint(x.intValue(), y.intValue());
				connection.getBendpoints().add(p);
			}
		}
	}

	/**
	 * Reposition the bendpoints based on the offset from the initial {@link Anchor} location to the new location
	 * @param selves
	 * The {@link Map} of initial {@link Anchor} location {@link Point} per {@link Connection}s
	 */
	private void adaptSelfBendPoints(Map<Connection, Point> selves) {
		for (Connection connection : selves.keySet()) {
			Point p = selves.get(connection);
			FreeFormConnection ffcon = (FreeFormConnection) connection;
			EList<Point> pointList = ffcon.getBendpoints();
			AnchorContainer source = connection.getStart().getParent();
			GraphicsAlgorithm start = source.getGraphicsAlgorithm();
			int deltaX = start.getX() - p.getX();
			int deltaY = start.getY() - p.getY();
			for (int i = 0; i < pointList.size(); i++) {
				Point bendPoint = pointList.get(i);
				int x = bendPoint.getX();
				bendPoint.setX(x + deltaX);
				int y = bendPoint.getY();
				bendPoint.setY(y + deltaY);
			}
		}
	}

	/**
	 * A {@link SimpleRelationship} subclass used to hold the Graphiti connection reference
	 */
	private class ConnectionRelationship extends SimpleRelationship {

		private Object graphData;

		public ConnectionRelationship(LayoutEntity sourceEntity, LayoutEntity destinationEntity, boolean bidirectional) {
			super(sourceEntity, destinationEntity, bidirectional);
		}

		@Override
		public Object getGraphData() {
			return graphData;
		}

		@Override
		public void setGraphData(Object o) {
			this.graphData = o;
		}
	}
}
