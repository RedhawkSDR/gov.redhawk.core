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
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.DefaultUpdateDiagramFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.sca.util.Debug;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterface;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.FindBy;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.ProvidesPort;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesDeviceStub;
import mil.jpeojtrs.sca.partitioning.UsesPort;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

public abstract class AbstractDiagramUpdateFeature extends DefaultUpdateDiagramFeature {

	private static final Debug DEBUG = new Debug(GraphitiUIPlugin.PLUGIN_ID, "DiagramUpdate");

	public AbstractDiagramUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	protected FindByStub getFindByStub(FindBy findBy, Diagram diagram) {
		// We don't handle findbys at this level
		return null;
	}

	/**
	 * Removes invalid connections where start/end points no longer exist
	 * @param connectInterfaces
	 * @return
	 */
	protected < E extends ConnectInterface< ? , ? , ? > > void removeInvalidConnections(List<E> connectInterfaces) {
		for (Iterator<E> connIter = connectInterfaces.iterator(); connIter.hasNext();) {
			// delete connection in model if either uses port is present but the referenced component isn't,
			// or provides port is present but references component isn't
			E con = connIter.next();
			if ((con.getUsesPort() != null && con.getUsesPort().getComponentInstantiationRef() != null
				&& con.getUsesPort().getComponentInstantiationRef().getInstantiation() == null)
				|| (con.getProvidesPort() != null && con.getProvidesPort().getComponentInstantiationRef() != null
					&& con.getProvidesPort().getComponentInstantiationRef().getInstantiation() == null)) {
				// endpoint missing, delete connection
				connIter.remove();
				EcoreUtil.delete(con, true);
			}
		}
	}

	protected boolean hasExistingShape(Diagram diagram, EObject eObject) {
		return !Graphiti.getLinkService().getPictogramElements(diagram, eObject).isEmpty();
	}

	protected boolean shouldRemoveShape(Shape shape) {
		return !doesLinkedBusinessObjectExist(shape);
	}

	protected List<EObject> getStubsToRemove(Diagram diagram) {
		List<EObject> removedStubs = new ArrayList<EObject>();
		for (EObject object : diagram.eResource().getContents()) {
			if (!(object instanceof Diagram)) {
				if (!hasExistingShape(diagram, object)) {
					removedStubs.add(object);
				}
			}
		}
		return removedStubs;
	}

	protected boolean haveEndpointsChanged(Connection connection, Diagram diagram) {
		ConnectInterface< ? , ? , ? > connectInterface = DUtil.getBusinessObject(connection, ConnectInterface.class);
		Set<Anchor> anchors = new HashSet<Anchor>();
		anchors.add(connection.getStart());
		anchors.add(connection.getEnd());
		Anchor source = getSourceAnchor(connectInterface, diagram);
		if (source == null || !anchors.contains(source)) {
			return true;
		}
		Anchor target = getTargetAnchor(connectInterface, diagram);
		if (target == null || !anchors.contains(target)) {
			return true;
		}
		return false;
	}

	protected boolean doesModelObjectExist(EObject object) {
		if (object instanceof UsesDeviceStub) {
			// Check the uses device reference of the stub
			object = ((UsesDeviceStub) object).getUsesDevice();
		}
		if (object == null || object.eIsProxy()) {
			return false;
		}
		return true;
	}

	protected boolean doesLinkedBusinessObjectExist(PictogramElement pe) {
		EObject bo = Graphiti.getLinkService().getBusinessObjectForLinkedPictogramElement(pe);
		return doesModelObjectExist(bo);
	}

	protected abstract List<EObject> getObjectsToAdd(Diagram diagram);

	protected List<Shape> getShapesToRemove(Diagram diagram) {
		List<Shape> removedShapes = new ArrayList<Shape>();
		for (Shape shape : diagram.getChildren()) {
			if (shouldRemoveShape(shape)) {
				removedShapes.add(shape);
			}
		}
		return removedShapes;
	}

	protected List<Connection> getConnectionsToRemove(Diagram diagram) {
		List<Connection> removedConnections = new ArrayList<Connection>();
		for (Connection connection : diagram.getConnections()) {
			if (!doesLinkedBusinessObjectExist(connection)) {
				removedConnections.add(connection);
			} else if (haveEndpointsChanged(connection, diagram)) {
				removedConnections.add(connection);
			}
		}
		return removedConnections;
	}

	protected abstract List<ConnectInterface< ? , ? , ? >> getModelConnections(Diagram diagram);

	protected List<ConnectInterface< ? , ? , ? >> getConnectionsToAdd(Diagram diagram) {
		List<ConnectInterface< ? , ? , ? >> addedConnections = new ArrayList<ConnectInterface< ? , ? , ? >>();
		for (ConnectInterface< ? , ? , ? > connectInterface : getModelConnections(diagram)) {
			if (!hasExistingShape(diagram, connectInterface)) {
				addedConnections.add(connectInterface);
			}
		}
		return addedConnections;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			Diagram diagram = (Diagram) pe;

			// Check for stale shapes
			List<Shape> removedChildren = getShapesToRemove(diagram);
			if (!removedChildren.isEmpty()) {
				return Reason.createTrueReason("Need to remove " + removedChildren.size() + " shape(s)");
			}

			// Check for unused stubs
			List<EObject> removedStubs = getStubsToRemove(diagram);
			if (!removedStubs.isEmpty()) {
				return Reason.createTrueReason("Diagram resource contents need pruning");
			}

			// Check for SAD objects that do not have shapes
			List<EObject> addedChildren = getObjectsToAdd(diagram);
			if (!addedChildren.isEmpty()) {
				return Reason.createTrueReason("Missing " + addedChildren.size() + " shape(s)");
			}

			// Check for stale connections
			List<Connection> removedConnections = getConnectionsToRemove(diagram);
			if (!removedConnections.isEmpty()) {
				return Reason.createTrueReason("Need to remove " + removedConnections.size() + " connection(s)");
			}

			// Check for SAD connections that do not have a diagram connection
			List<ConnectInterface< ? , ? , ? >> addedConnections = getConnectionsToAdd(diagram);
			if (!addedConnections.isEmpty()) {
				return Reason.createTrueReason("Need to add " + addedConnections.size() + " connection(s)");
			}
		}

		return super.updateNeeded(context);
	}

	@Override
	public boolean update(IUpdateContext context) {
		PictogramElement pe = context.getPictogramElement();
		if (pe instanceof Diagram) {
			DEBUG.trace("Updating diagram");
			boolean updateStatus = false;
			boolean layoutNeeded = false;

			Diagram diagram = (Diagram) pe;

			// Remove children
			List<Shape> removedShapes = getShapesToRemove(diagram);
			DEBUG.trace("Removing {0,number} shapes", removedShapes.size());
			for (Shape shape : removedShapes) {
				DUtil.removeShapeViaFeature(getFeatureProvider(), shape);
				updateStatus = true;
			}

			// Prune unused stubs
			List<EObject> removedStubs = getStubsToRemove(diagram);
			if (!removedStubs.isEmpty()) {
				DEBUG.trace("Removing {0,number} diagram stub(s)", removedStubs.size());
				diagram.eResource().getContents().removeAll(removedStubs);
				updateStatus = true;
			}

			// Remove stale connections
			List<Connection> removedConnections = getConnectionsToRemove(diagram);
			DEBUG.trace("Removing {0,number} diagram connection(s)", removedConnections.size());
			for (Connection connection : getConnectionsToRemove(diagram)) {
				DUtil.fastDeleteConnection(connection);
				updateStatus = true;
			}

			// Add missing connection endpoints
			List<ConnectInterface< ? , ? , ? >> addedConnections = getConnectionsToAdd(diagram);
			for (ConnectInterface< ? , ? , ? > connectInterface : addedConnections) {
				if (getUsesPortStub(connectInterface.getUsesPort(), diagram) == null) {
					DEBUG.trace("Adding uses port for connection {0}", connectInterface.getId());
					addUsesPort(connectInterface, diagram);
				}
				if (getConnectionTarget(connectInterface, diagram) == null) {
					DEBUG.trace("Adding target for connection {0}", connectInterface.getId());
					addConnectionTarget(connectInterface, diagram);
				}
				updateStatus = true;
			}

			// Update components
			if (super.update(context)) {
				updateStatus = true;
			}

			// Add shapes for SAD objects that do not have them
			List<EObject> addedObjects = getObjectsToAdd(diagram);
			DEBUG.trace("Adding {0,number} shape(s)", addedObjects.size());
			for (EObject object : addedObjects) {
				// New stubs will not belong to a resource; store them in the diagram's resource instead
				if (object.eResource() == null) {
					diagram.eResource().getContents().add(object);
				}
				DUtil.addShapeViaFeature(getFeatureProvider(), diagram, object);
				updateStatus = true;
				layoutNeeded = true;
			}

			// Check for FindBy stubs stored in the diagram's resource that do not have corresponding shapes, which
			// were most likely created for the connection endpoints above
			for (FindByStub findByStub : getDiagramStubs(diagram, FindByStub.class)) {
				if (!hasExistingShape(diagram, findByStub)) {
					DEBUG.trace("Adding FindBy shape");
					DUtil.addShapeViaFeature(getFeatureProvider(), diagram, findByStub);
					updateStatus = true;
					layoutNeeded = true;
				}
			}

			// Update components
			if (super.update(context)) {
				updateStatus = true;
			}

			// Add missing connections
			if (DEBUG.enabled) {
				DEBUG.trace("Adding " + addedConnections.size() + " connection(s)");
			}
			for (ConnectInterface< ? , ? , ? > connectInterface : addedConnections) {
				Anchor source = getSourceAnchor(connectInterface, diagram);
				if (source == null) {
					DEBUG.trace("Failed to get source anchor for connection " + connectInterface.getId());
				}
				Anchor target = getTargetAnchor(connectInterface, diagram);
				if (target == null) {
					DEBUG.trace("Failed to get target anchor for connection " + connectInterface.getId());
				}
				if (source != null && target != null) {
					DEBUG.trace("Adding connection " + connectInterface.getId());
					DUtil.addConnectionViaFeature(getFeatureProvider(), connectInterface, source, target);
				}
				updateStatus = true;
			}

			if (layoutNeeded) {
				DEBUG.trace("Laying out diagram");
				LayoutDiagramFeature layoutFeature = new LayoutDiagramFeature(getFeatureProvider());
				layoutFeature.execute(null);
			}

			return updateStatus;
		}
		return false;
	}

	protected List<UsesPortStub> getUsesPortStubs(UsesPort< ? > usesPort, Diagram diagram) {
		if (usesPort.getFindBy() != null) {
			FindByStub findByStub = getFindByStub(usesPort.getFindBy(), diagram);
			if (findByStub != null) {
				return findByStub.getUses();
			}
		} else if (usesPort.getComponentInstantiationRef() != null) {
			// It's possible that the referenced component instantiation no longer exists, such as when the component
			// was deleted via the XML editor
			ComponentInstantiation instantiation = usesPort.getComponentInstantiationRef().getInstantiation();
			if (instantiation != null) {
				return instantiation.getUses();
			}
		}
		return Collections.emptyList();
	}

	protected UsesPortStub getUsesPortStub(UsesPort< ? > usesPort, Diagram diagram) {
		for (UsesPortStub usesPortStub : getUsesPortStubs(usesPort, diagram)) {
			if (usesPortStub.getName().equals(usesPort.getUsesIdentifier())) {
				return usesPortStub;
			}
		}
		return null;
	}

	protected Anchor getSourceAnchor(ConnectInterface< ? , ? , ? > connectInterface, Diagram diagram) {
		// This implementation specifically ignores findby's
		Anchor source = DUtil.getPictogramElementForBusinessObject(diagram, connectInterface.getSource(), Anchor.class);
		if (source == null && connectInterface.getUsesPort() != null) {
			UsesPortStub usesPortStub = getUsesPortStub(connectInterface.getUsesPort(), diagram);
			if (usesPortStub != null) {
				return DUtil.getPictogramElementForBusinessObject(diagram, usesPortStub, Anchor.class);
			}
		}
		return source;
	}

	protected void addUsesPort(ConnectInterface< ? , ? , ? > connectInterface, Diagram diagram) {
		if (connectInterface.getUsesPort().getFindBy() != null) {
			FindByStub findByStub = getFindByStub(connectInterface.getUsesPort().getFindBy(), diagram);
			if (findByStub == null) {
				findByStub = addFindByStub(connectInterface.getUsesPort().getFindBy(), diagram);
			}
			if (findByStub == null) {
				return;
			}
			UsesPortStub usesPortStub = PartitioningFactory.eINSTANCE.createUsesPortStub();
			usesPortStub.setName(connectInterface.getUsesPort().getUsesIdentifier());
			findByStub.getUses().add(usesPortStub);
		}
	}

	protected ComponentSupportedInterfaceStub getComponentSupportedInterface(ComponentSupportedInterface componentSupportedInterface, Diagram diagram) {
		if (componentSupportedInterface.getFindBy() != null) {
			FindByStub findByStub = getFindByStub(componentSupportedInterface.getFindBy(), diagram);
			if (findByStub != null) {
				return findByStub.getInterface();
			}
		}
		return null;
	}

	protected List<ProvidesPortStub> getProvidesPortStubs(ProvidesPort< ? > providesPort, Diagram diagram) {
		if (providesPort.getFindBy() != null) {
			FindByStub findByStub = getFindByStub(providesPort.getFindBy(), diagram);
			if (findByStub != null) {
				return findByStub.getProvides();
			}
		} else if (providesPort.getComponentInstantiationRef() != null) {
			// It's possible that the referenced component instantiation no longer exists, such as when the component
			// was deleted via the XML editor
			ComponentInstantiation instantiation = providesPort.getComponentInstantiationRef().getInstantiation();
			if (instantiation != null) {
				return instantiation.getProvides();
			}
		}
		return Collections.emptyList();
	}

	protected FindByStub addFindByStub(FindBy findBy, Diagram diagram) {
		// We don't handle findbys at this level
		return null;
	}

	protected void addProvidesPort(ProvidesPort< ? > providesPort, Diagram diagram) {
		if (providesPort.getFindBy() != null) {
			FindByStub findByStub = getFindByStub(providesPort.getFindBy(), diagram);
			if (findByStub == null) {
				findByStub = addFindByStub(providesPort.getFindBy(), diagram);
			}
			if (findByStub == null) {
				return;
			}
			ProvidesPortStub providesPortStub = PartitioningFactory.eINSTANCE.createProvidesPortStub();
			providesPortStub.setName(providesPort.getProvidesIdentifier());
			findByStub.getProvides().add(providesPortStub);
		}
	}

	protected ProvidesPortStub getProvidesPortStub(ProvidesPort< ? > providesPort, Diagram diagram) {
		for (ProvidesPortStub providesPortStub : getProvidesPortStubs(providesPort, diagram)) {
			if (providesPortStub.getName().equals(providesPort.getProvidesIdentifier())) {
				return providesPortStub;
			}
		}
		return null;
	}

	protected ConnectionTarget getConnectionTarget(ConnectInterface< ? , ? , ? > connectInterface, Diagram diagram) {
		if (connectInterface.getProvidesPort() != null) {
			return getProvidesPortStub(connectInterface.getProvidesPort(), diagram);
		} else if (connectInterface.getComponentSupportedInterface() != null) {
			return getComponentSupportedInterface(connectInterface.getComponentSupportedInterface(), diagram);
		}
		return null;
	}

	protected Anchor getTargetAnchor(ConnectInterface< ? , ? , ? > connectInterface, Diagram diagram) {
		Anchor targetAnchor = DUtil.getPictogramElementForBusinessObject(diagram, connectInterface.getTarget(), Anchor.class);
		if (targetAnchor == null) {
			ConnectionTarget target = getConnectionTarget(connectInterface, diagram);
			if (target != null) {
				return DUtil.getPictogramElementForBusinessObject(diagram, target, Anchor.class);
			}
		}
		return targetAnchor;
	}

	protected void addConnectionTarget(ConnectInterface< ? , ? , ? > connectInterface, Diagram diagram) {
		if (connectInterface.getProvidesPort() != null) {
			addProvidesPort(connectInterface.getProvidesPort(), diagram);
		} else if (connectInterface.getComponentSupportedInterface() != null) {
			if (connectInterface.getComponentSupportedInterface().getFindBy() != null) {
				addFindByStub(connectInterface.getComponentSupportedInterface().getFindBy(), diagram);
			}
		}
	}

	protected < E > List<E> getDiagramStubs(Diagram diagram, Class<E> clazz) {
		List<E> stubs = new ArrayList<E>();
		for (EObject object : diagram.eResource().getContents()) {
			if (clazz.isInstance(object)) {
				stubs.add(clazz.cast(object));
			}
		}
		return stubs;
	}

}
