/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.diagram.providers;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.graph.EdgeList;
import org.eclipse.draw2d.graph.Node;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.CompositeLeftRightProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.draw2d.ui.graph.ConstrainedEdge;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class PartitioningLayoutProvider extends CompositeLeftRightProvider {

	private final PartitioningVisualIDRegistry visualIdRegistry;

	public PartitioningLayoutProvider(final PartitioningVisualIDRegistry visualIdRegistry) {
		this.visualIdRegistry = visualIdRegistry;
	}

	@Override
	public boolean provides(final IOperation operation) {
		// enable this provider only on our diagrams
		if (operation instanceof ILayoutNodeOperation) {
			final Iterator< ? > nodes = ((ILayoutNodeOperation) operation).getLayoutNodes().listIterator();
			if (nodes.hasNext()) {
				final View node = ((ILayoutNode) nodes.next()).getNode();
				final Diagram container = node.getDiagram();
				if (container == null || !(container.getType().equals(this.visualIdRegistry.getModelID()))) {
					return false;
				}
			}
		} else {
			return false;
		}
		final IAdaptable layoutHint = ((ILayoutNodeOperation) operation).getLayoutHint();
		final String layoutType = (String) layoutHint.getAdapter(String.class);
		return LayoutType.DEFAULT.equals(layoutType);
	}

	@Override
	protected boolean shouldHandleConnectableListItems() {
		return true;
	}

	@Override
	protected List< ? > getRelevantConnections(final Hashtable editPartToNodeDict) {
		final Enumeration< ? > enumeration = editPartToNodeDict.keys();
		final List< ? > connectionsToMove = new ArrayList<Object>();
		while (enumeration.hasMoreElements()) {
			final Object e = enumeration.nextElement();
			final GraphicalEditPart shapeEP = (GraphicalEditPart) e;

			final DiagramEditPart diagram = (DiagramEditPart) shapeEP.getViewer().getContents();
			return diagram.getConnections();
		}
		return connectionsToMove;
	}

	@Override
	protected EdgeList buildEdges(final List selectedObjects, final Map editPartToNodeDict) {

		final EdgeList edges = new EdgeList();

		// Do "topdown" relationships first. Since they traditionally
		// go upward on a diagram, they are reversed when placed in the graph
		// for
		// layout. Also, layout traverses the arcs from each node in the order
		// of their insertion when finding a spanning tree for its constructed
		// hierarchy. Therefore, arcs added early are less likely to be
		// reversed.
		// In fact, since there are no cycles in these arcs, adding
		// them to the graph first should guarantee that they are never
		// reversed,
		// i.e., the inheritance hierarchy is preserved graphically.
		final ArrayList<ConnectionEditPart> objects = new ArrayList<ConnectionEditPart>();
		objects.addAll(selectedObjects);
		ListIterator<ConnectionEditPart> li = objects.listIterator();
		final ArrayList<ConnectionEditPart> notTopDownEdges = new ArrayList<ConnectionEditPart>();
		final boolean shouldHandleListItems = shouldHandleConnectableListItems();
		while (li.hasNext()) {
			final EditPart gep = li.next();
			if (gep instanceof ConnectionEditPart) {
				final ConnectionEditPart poly = (ConnectionEditPart) gep;

				if (layoutTopDown(poly)) {
					EditPart from = poly.getSource();
					EditPart to = poly.getTarget();
					if (from instanceof IBorderItemEditPart && !editPartToNodeDict.containsKey(from)) {
						from = from.getParent();
					} else if (shouldHandleListItems && from instanceof ListItemEditPart) {
						from = getFirstAnscestorinNodesMap(from, editPartToNodeDict);
					}
					if (to instanceof IBorderItemEditPart && !editPartToNodeDict.containsKey(to)) {
						to = to.getParent();
					} else if (shouldHandleListItems && to instanceof ListItemEditPart) {
						to = getFirstAnscestorinNodesMap(to, editPartToNodeDict);
					}
					final Node fromNode = getNode(from, editPartToNodeDict);
					final Node toNode = getNode(to, editPartToNodeDict);

					if (fromNode != null && toNode != null && !checkSelfEdge(from, to, editPartToNodeDict)) {
						addEdge(edges, poly, toNode, fromNode);
					}
				} else {
					notTopDownEdges.add(poly);
				}
			}
		}

		// third pass for all other connections
		li = notTopDownEdges.listIterator();
		while (li.hasNext()) {
			final ConnectionEditPart poly = li.next();
			EditPart from = poly.getSource();
			EditPart to = poly.getTarget();
			if (from instanceof IBorderItemEditPart && !editPartToNodeDict.containsKey(from)) {
				from = from.getParent();
			} else if (shouldHandleListItems && from instanceof ListItemEditPart) {
				from = getFirstAnscestorinNodesMap(from, editPartToNodeDict);
			}
			if (to instanceof IBorderItemEditPart && !editPartToNodeDict.containsKey(to)) {
				to = to.getParent();
			} else if (shouldHandleListItems && to instanceof ListItemEditPart) {
				to = getFirstAnscestorinNodesMap(to, editPartToNodeDict);
			}
			final Node fromNode = getNode(from, editPartToNodeDict);
			final Node toNode = getNode(to, editPartToNodeDict);

			if (fromNode != null && toNode != null && !checkSelfEdge(from, to, editPartToNodeDict)) {
				addEdge(edges, poly, fromNode, toNode);
			}
		}
		return edges;
	}

	private Node getNode(final EditPart editPart, final Map editPartToNodeDict) {
		if (editPart == null) {
			return null;
		}
		final Object retVal = editPartToNodeDict.get(editPart);
		if (retVal == null) {
			return getNode(editPart.getParent(), editPartToNodeDict);
		}
		return (Node) retVal;
	}

	private EditPart getFirstAnscestorinNodesMap(final EditPart editPart, final Map editPartToNodeDict) {
		EditPart ancestor = editPart;
		while (ancestor != null) {
			if (editPartToNodeDict.get(ancestor) != null) {
				return ancestor;
			}
			ancestor = ancestor.getParent();
		}
		return null;
	}

	private boolean checkSelfEdge(final EditPart from, final EditPart to, final Map dictionary) {
		final Node graphSource = (from instanceof IBorderItemEditPart) ? (Node) dictionary.get(from.getParent()) : (Node) dictionary.get(from);
		final Node graphTarget = (to instanceof IBorderItemEditPart) ? (Node) dictionary.get(to.getParent()) : (Node) dictionary.get(to);
		// Fixes #163 If we are colocating a single component with feedback onto itself we will have a null graphSource & graphTarget but equal EditParts.
		if (graphSource == null || graphTarget == null) {
			return from.equals(to);
		}
		
		return graphSource.equals(graphTarget);
	}

	private void addEdge(final EdgeList edges, final ConnectionEditPart connectionEP, final Node fromNode, final Node toNode) {
		final ConstrainedEdge edge = new ConstrainedEdge(connectionEP, fromNode, toNode);
		initializeEdge(connectionEP, edge);

		edges.add(edge);
	}
}
