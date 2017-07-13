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

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.datatypes.IDimension;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.features.custom.AbstractCustomFeature;
import org.eclipse.graphiti.internal.datatypes.impl.DimensionImpl;
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

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.LayoutUtil;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

@SuppressWarnings("restriction")
public class LayoutDiagramFeature extends AbstractCustomFeature {

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
		// get a map of the self connection anchor locations
		final Map<Connection, Point> selves = getSelfConnections();

		// Use the Horizontal Tree Layout
		LayoutAlgorithm layoutAlgorithm = new HorizontalTreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		if (layoutAlgorithm != null) {
			try {

				// Get the map of SimpleNode per Shapes (not including shapes contained in HostCollocations)
				Map<Shape, SimpleNode> map = getLayoutEntities();

				// Get the array of Connection LayoutRelationships
				LayoutRelationship[] connections = getConnectionEntities(map, null);

				// Setup the array of Shape LayoutEntity
				LayoutEntity[] entities = map.values().toArray(new LayoutEntity[0]);

				// Update layouts WITHIN HostCollocations
				// Need to do this first so we know the size and dimensions of the HostCollocations
				updateHostCollocationCoordinates();
				
				// Determine the dimensions required to house all of our shapes
				Point diagramBounds = LayoutUtil.calculateDiagramBounds(getDiagram());
				
				// Apply the LayoutAlgorithmn
				layoutAlgorithm.applyLayout(entities, connections, 0, 0, diagramBounds.getX(), diagramBounds.getY(), false, false);
				
				// Update all other Graphiti Shapes and Connections locations
				updateGraphCoordinates(((TreeLayoutAlgorithm) layoutAlgorithm).getRoots(), entities, connections);

				// Reposition the self connections bendpoints:
				adaptSelfBendPoints(selves);

			} catch (InvalidLayoutConfiguration e) {
				// TODO: NO, do NOT just print the stack trace here...
				e.printStackTrace(); // SUPPRESS CHECKSTYLE handle exception
			}
		}
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
	 * 	Build map for all diagram-level child shapes
	 * @return a {@link Map} of {@link SimpleNode} per {@link Shape}
	 */
	private Map<Shape, SimpleNode> getLayoutEntities() {
		Map<Shape, SimpleNode> map = new HashMap<Shape, SimpleNode>();
		EList<Shape> children = getDiagram().getChildren();
		for (Shape shape : children) {
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			SimpleNode entity = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
			map.put(shape, entity);
		}
		return map;
	}

	/**
	 * @param map a {@link Map} of {@link SimpleNode} per {@link Shape} - used to link {@link SimpleRelationship} to
	 * source and target entities
	 * @param hostCollocation 
	 * @return the array of {@link LayoutRelationship}s to compute
	 */
	private LayoutRelationship[] getConnectionEntities(Map<Shape, SimpleNode> map, ContainerShape hostCollocation) {
		// TODO: this needs to handle:
			// Connections between diagram-level components (with neither  within a HC)
			// Connections between components that are both within the same HC
			// Connections between a source that is outside a HC and a target that is inside a HC
			// Connections between a source that is inside a HC and a target that is outside a HC
			// Connections between a source that is inside one HC and a target that is inside a different HC
		
		List<LayoutRelationship> list = new ArrayList<LayoutRelationship>();
		EList<Connection> connections = getDiagram().getConnections();
		for (Connection connection : connections) {

			String label = null;
			EList<ConnectionDecorator> decorators = connection.getConnectionDecorators();
			for (ConnectionDecorator decorator : decorators) {
				if (decorator.getGraphicsAlgorithm() instanceof Text) {
					label = ((Text) decorator.getGraphicsAlgorithm()).getValue();
				}
			}

			// Modified from Spray code to handle the nested nature of the Component Shapes. Code prior to change looked
			// like: connection.getStart().getParent()
			// get the SimpleNode already created from the map:
			RHContainerShape source = ScaEcoreUtils.getEContainerOfType(connection.getStart(), RHContainerShape.class);
			RHContainerShape target = ScaEcoreUtils.getEContainerOfType(connection.getEnd(), RHContainerShape.class);

			// TODO: This is getting messy.  I should probably break this method up so I don't have to have checks like this in here
			// If we are operating on components in a HostCollocation, then don't consider connections that are only
			// partially contained in the HostCollocation.  Those are handled when assigning relationships on diagram children.
			if (hostCollocation != null && (!source.getContainer().equals(hostCollocation) || target.getContainer() != hostCollocation)) {
				continue;
			}
			
			HostCollocation sourceHostCo = ScaEcoreUtils.getEContainerOfType(DUtil.getBusinessObject(source.getContainer()), HostCollocation.class);
			HostCollocation targetHostCo = ScaEcoreUtils.getEContainerOfType(DUtil.getBusinessObject(target.getContainer()), HostCollocation.class);

			SimpleNode sourceEntity = null;
			SimpleNode targetEntity = null;
			if ((sourceHostCo == targetHostCo)) {
				// Both entities are in the same host collocation or in the diagram, normal relationship
				sourceEntity = map.get(source);
				targetEntity = map.get(target);
			} else if (sourceHostCo != null) {
				// Only source is in host collocation, make relationship between hostCo and target
				sourceEntity = map.get((Shape) source.getContainer());
				targetEntity = map.get(target);
			} else if (targetHostCo != null) {
				// Only target is in host collocation, make relationship between hostCo and source
				sourceEntity = map.get(source);
				targetEntity = map.get((Shape) target.getContainer());
			} 
			// TODO: What if the relationship is between two DIFFERENT HostCollocations?  Not this would be a likely use case. 

			// we don't add self relations to avoid Cycle errors
			if (source != target && sourceEntity != null && targetEntity != null) { 
				SimpleRelationship relationship = new SimpleRelationship(sourceEntity, targetEntity, (source != target));
				relationship.setGraphData(connection);
				relationship.clearBendPoints();
				relationship.setLabel(label);
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
				list.add(relationship);
				sourceEntity.addRelationship(relationship);
				targetEntity.addRelationship(relationship);
			}
		}
		return list.toArray(new LayoutRelationship[0]);
	}

	/**
	 * Reposition the Graphiti {@link PictogramElement}s and {@link Connection}s based on the
	 * Zest {@link LayoutAlgorithm} computed locations
	 * @param entities
	 * @param connections
	 */
	private void updateGraphCoordinates(List< ? > roots, LayoutEntity[] entities, LayoutRelationship[] connections) {
		for (LayoutEntity entity : entities) {
			SimpleNode node = (SimpleNode) entity;
			Shape shape = (Shape) node.getRealObject();
			Double x = node.getX();
			Double y = node.getY();
			shape.getGraphicsAlgorithm().setX(x.intValue());
			shape.getGraphicsAlgorithm().setY(y.intValue());
			
			// TODO: This is the wrong place to do this check
			if (DUtil.getBusinessObject(shape) instanceof HostCollocation) {
				continue;
			}
			Double width = node.getWidth();
			Double height = node.getHeight();
			shape.getGraphicsAlgorithm().setWidth(width.intValue());
			shape.getGraphicsAlgorithm().setHeight(height.intValue());
		}

		IGaService gaService = Graphiti.getGaService();
		for (LayoutRelationship relationship : connections) {
			SimpleRelationship rel = (SimpleRelationship) relationship;
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
	
	private void updateHostCollocationCoordinates() throws InvalidLayoutConfiguration {
		// TODO: clean up
		LayoutAlgorithm hostCoLayoutAlgorithm = new HorizontalTreeLayoutAlgorithm(LayoutStyles.ENFORCE_BOUNDS | LayoutStyles.NO_LAYOUT_NODE_RESIZING);
//		TmpLayoutAlg hostCoLayoutAlgorithm = new TmpLayoutAlg(LayoutStyles.ENFORCE_BOUNDS | LayoutStyles.NO_LAYOUT_NODE_RESIZING);
		
		
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
			Map<Shape, SimpleNode> hostCoMap = getLayoutEntitiesInHostCollocation(hostCollocation);
			LayoutRelationship[] hostCoConnections = getConnectionEntities(hostCoMap, hostCollocation);
			
			LayoutEntity[] hostCoEntities = hostCoMap.values().toArray(new LayoutEntity[0]);
			GraphicsAlgorithm hostCoGA = hostCollocation.getGraphicsAlgorithm();
			
			// Set host collocation boundaries
			// TODO: rename DUtil method to be more generic
			Point bounds = LayoutUtil.calculateDiagramBounds(hostCollocation);
			
			// TODO: make final and move outside of this method
			int xBuffer = 100;
			int yBuffer = 50;
			hostCollocation.getGraphicsAlgorithm().setWidth(bounds.getX() + xBuffer);
			hostCollocation.getGraphicsAlgorithm().setHeight(bounds.getY() + yBuffer);
			IDimension hostCoBounds = new DimensionImpl(hostCoGA.getWidth(), hostCoGA.getHeight());
			
			// Apply the layout, and then update the shape coordinates
			hostCoLayoutAlgorithm.applyLayout(hostCoEntities, hostCoConnections, 15, 0, hostCoBounds.getWidth(), hostCoBounds.getHeight(), false, false);
			updateGraphCoordinates(((TreeLayoutAlgorithm) hostCoLayoutAlgorithm).getRoots(), hostCoEntities, hostCoConnections);
		}
		
	}

	/**
	 * Returns a map of Host Collocation children, and updated the Host Collocation GA height and width to fit its
	 * children
	 * @param hostCollocation
	 * @return Map<Shape, SimpleNode> of all host collocation contained shapes and their representative
	 * {@link SimpleNode}
	 */
	private Map<Shape, SimpleNode> getLayoutEntitiesInHostCollocation(Shape hostCollocation) {
		Map<Shape, SimpleNode> map = new HashMap<Shape, SimpleNode>();
		EList<Shape> children = ((ContainerShape) hostCollocation).getChildren();
		for (Shape shape : children) {
			GraphicsAlgorithm ga = shape.getGraphicsAlgorithm();
			SimpleNode entity = new SimpleNode(shape, ga.getX(), ga.getY(), ga.getWidth(), ga.getHeight());
			map.put(shape, entity);
		}

		return map;
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
	 * A {@link org.eclipse.zest.layouts.exampleStructures.SimpleRelationship} subclass
	 * used to hold the Graphiti connection reference
	 */
	private class SimpleRelationship extends org.eclipse.zest.layouts.exampleStructures.SimpleRelationship {

		private Object graphData;

		public SimpleRelationship(LayoutEntity sourceEntity, LayoutEntity destinationEntity, boolean bidirectional) {
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
