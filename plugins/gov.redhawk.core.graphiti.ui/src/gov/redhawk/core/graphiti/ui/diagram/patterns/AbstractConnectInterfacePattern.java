/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.core.graphiti.ui.diagram.patterns;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.context.IAddConnectionContext;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IConnectionContext;
import org.eclipse.graphiti.features.context.ICreateConnectionContext;
import org.eclipse.graphiti.features.context.impl.AddConnectionContext;
import org.eclipse.graphiti.mm.MmFactory;
import org.eclipse.graphiti.mm.Property;
import org.eclipse.graphiti.mm.algorithms.Polygon;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.pattern.AbstractConnectionPattern;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;

import gov.redhawk.core.graphiti.ui.diagram.providers.AbstractToolBehaviorProvider;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import gov.redhawk.sca.util.StringUtil;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.Connections;
import mil.jpeojtrs.sca.partitioning.FindBy;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public abstract class AbstractConnectInterfacePattern extends AbstractConnectionPattern {

	public static final String NAME = "Connection";
	public static final String SHAPE_IMG_CONNECTION_DECORATOR = "imgConnectionDecorator";
	public static final String SHAPE_TEXT_CONNECTION_DECORATOR = "textConnectionDecorator";
	public static final String OVERRIDE_CONNECTION_ID = "OverrideConnectionId";
	public static final String CONNECT_INTERFACE_ID = "connect_interface_id";

	private Anchor sourceAnchor;

	@Override
	public String getCreateName() {
		return NAME;
	}

	@Override
	public String getCreateDescription() {
		return "Create new Connect Interface";
	}

	@Override
	public boolean canStartConnection(ICreateConnectionContext context) {
		// Not allowed in the explorer or if read-only
		Diagram diagram = getDiagram();
		if (DUtil.isDiagramExplorer(diagram) || DUtil.isDiagramReadOnly(diagram)) {
			return false;
		}

		// Check if the source anchor belongs to a component, and disallow the connection if it's disabled
		RHContainerShape component = ScaEcoreUtils.getEContainerOfType(context.getSourceAnchor(), RHContainerShape.class);
		if (component != null && !component.isEnabled()) {
			return false;
		}

		// We must be able to find a UsesPortStub or ConnectionTarget
		if (getUsesPortStub(context) != null || getConnectionTarget(context) != null) {
			sourceAnchor = context.getSourceAnchor();
			return true;
		}

		return false;
	}

	protected AbstractToolBehaviorProvider getToolBehaviorProvider() {
		return (AbstractToolBehaviorProvider) getFeatureProvider().getDiagramTypeProvider().getCurrentToolBehaviorProvider();
	}

	@Override
	public void startConnecting() {
		// Highlight ports that may be valid for completing the connection
		getToolBehaviorProvider().startConnectionHighlighting(sourceAnchor);
	}

	@Override
	public boolean canCreate(ICreateConnectionContext context) {
		// Check if the target anchor belongs to a component, and disallow the connection if it's disabled; the source
		// was already checked in canStart().
		RHContainerShape component = ScaEcoreUtils.getEContainerOfType(context.getTargetAnchor(), RHContainerShape.class);
		if (component != null && !component.isEnabled()) {
			return false;
		}

		// In any other case, assume it's a valid connection so that the mouse pointer doesn't give negative feedback.
		return true;
	}

	@Override
	public PictogramElement add(IAddContext addContext) {
		IAddConnectionContext context = (IAddConnectionContext) addContext;

		// source and target
		UsesPortStub source = getUsesPortStub(context);
		ConnectionTarget target = getConnectionTarget(context);

		// Create connection (handle user selecting source or target)
		Connection connectionPE = Graphiti.getPeCreateService().createFreeFormConnection(getFeatureProvider().getDiagramTypeProvider().getDiagram());
		if (source == getUsesPortStub(context.getSourceAnchor()) && target == getConnectionTarget(context.getTargetAnchor())) {
			connectionPE.setStart(context.getSourceAnchor());
			connectionPE.setEnd(context.getTargetAnchor());
		} else if (source == getUsesPortStub(context.getTargetAnchor()) && target == getConnectionTarget(context.getSourceAnchor())) {
			connectionPE.setStart(context.getTargetAnchor());
			connectionPE.setEnd(context.getSourceAnchor());
		}

		// create line
		IGaService gaService = Graphiti.getGaService();
		Polyline line = gaService.createPlainPolyline(connectionPE);
		StyleUtil.setStyle(line, StyleUtil.CONNECTION);

		// Add arrow (as a decorator) to the end of the line
		ConnectionDecorator arrowDecorator = Graphiti.getPeCreateService().createConnectionDecorator(connectionPE, false, 1.0, true);
		Polygon polyArrow = gaService.createPlainPolygon(arrowDecorator, new int[] { -10, 5, 0, 0, -10, -5 });
		StyleUtil.setStyle(polyArrow, StyleUtil.CONNECTION);

		// link ports to connection
		getFeatureProvider().link(connectionPE, new Object[] { context.getNewObject(), source, target });

		return connectionPE;
	}

	/**
	 * Override in subclass to return an instance of the specific ConnectInterface type
	 */
	protected abstract ConnectInterface< ? , ? , ? > createConnectInterface();

	/**
	 * Override in subclass to return a unique connection ID for the SAD/DCD
	 */
	protected abstract String createConnectionId();

	/**
	 * Override in subclass to add the ConnectInterface to the SAD/DCD
	 */
	protected abstract void addConnectInterface(ConnectInterface< ? , ? , ? > connection);

	protected ConnectInterface< ? , ? , ? > createModelConnection(Anchor source, Anchor target) {
		ConnectInterface< ? , ? , ? > connection = DUtil.assignAnchorObjectsToConnection(createConnectInterface(), source, target);
		if (connection == null) {
			// Switch source/target direction and try again
			connection = DUtil.assignAnchorObjectsToConnection(createConnectInterface(), target, source);
		}

		return connection;
	}

	/**
	 * Creates a new connection between the selected usesPortStub and ConnectionTarget
	 */
	@Override
	public Connection create(ICreateConnectionContext context) {
		// There appears to be a bug in Graphiti/GMF where endConnecting() does not get called if this method opens a
		// dialog (e.g., a connection to/from a super port with more than one potential match); explicitly calling it
		// here will clear the port highlighting.
		endConnecting();

		// Attempt to create the connection, trying both directions if necessary
		final ConnectInterface< ? , ? , ? > modelConnection = createModelConnection(context.getSourceAnchor(), context.getTargetAnchor());
		if (modelConnection == null) {
			return null;
		}

		// Allow the caller to override the connection ID (e.g, building a diagram from runtime state)
		String connectionId = (String) context.getProperty(OVERRIDE_CONNECTION_ID);
		if (connectionId == null) {
			connectionId = createConnectionId();
		}
		modelConnection.setId(connectionId);

		// If we just created some findBy model objects, make sure they are referenced by the pictogram element
		FindBy fb = null;
		RHContainerShape findByPE = null;

		if (modelConnection.getProvidesPort() == null) {
			fb = modelConnection.getComponentSupportedInterface().getFindBy();
		} else {
			fb = modelConnection.getProvidesPort().getFindBy();
		}
		if (fb == null) {
			fb = modelConnection.getUsesPort().getFindBy();
			if (fb != null) {
				findByPE = DUtil.getParentRhContainerShape(context.getSourceAnchor());
			}
		} else {
			findByPE = DUtil.getParentRhContainerShape(context.getTargetAnchor());
		}

		if (fb != null) {
			Property connectInterfaceProp = MmFactory.eINSTANCE.createProperty();
			connectInterfaceProp.setKey(CONNECT_INTERFACE_ID);
			connectInterfaceProp.setValue(modelConnection.getId());
			findByPE.getProperties().add(connectInterfaceProp);
		}

		// Add the connection to the SAD/DCD model
		TransactionalEditingDomain editingDomain = getDiagramBehavior().getEditingDomain();
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				addConnectInterface(modelConnection);
			}
		});

		// Add diagram connection for business object
		AddConnectionContext addContext = new AddConnectionContext(context.getSourceAnchor(), context.getTargetAnchor());
		addContext.setNewObject(modelConnection);
		return (Connection) getFeatureProvider().addIfPossible(addContext);
	}

	@Override
	public void endConnecting() {
		// Turns off highlighting ports for the connection
		getToolBehaviorProvider().endConnectionHighlighting();

		super.endConnecting();
	}

	/**
	 * Return UsesPortStub from either the source or target anchor. Depends on how user drew connection.
	 * @param context
	 * @return
	 */
	protected UsesPortStub getUsesPortStub(IConnectionContext context) {
		UsesPortStub source = getUsesPortStub(context.getSourceAnchor());
		if (source != null) {
			return source;
		}
		source = getUsesPortStub(context.getTargetAnchor());
		return source;
	}

	protected UsesPortStub getUsesPortStub(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor.getParent());
			if (object instanceof UsesPortStub) {
				return (UsesPortStub) object;
			}
		}
		return null;
	}

	/**
	 * Return ConnectionTarget from either the source or target anchor. Depends on how user drew connection.
	 * @param context
	 * @return
	 */
	protected ConnectionTarget getConnectionTarget(IConnectionContext context) {
		ConnectionTarget connectionTarget = getConnectionTarget(context.getSourceAnchor());
		if (connectionTarget != null) {
			return connectionTarget;
		}
		connectionTarget = getConnectionTarget(context.getTargetAnchor());
		return connectionTarget;
	}

	protected ConnectionTarget getConnectionTarget(Anchor anchor) {
		if (anchor != null) {
			Object object = getBusinessObjectForPictogramElement(anchor.getParent());
			if (object instanceof ConnectionTarget) {
				return (ConnectionTarget) object;
			}
		}
		return null;
	}

	/**
	 * Returns the next available connection id
	 */
	protected String createConnectionId(Connections< ? > connections) {
		final List<String> ids = new ArrayList<String>();
		if (connections != null) {
			for (final ConnectInterface< ? , ? , ? > connection : connections.getConnectInterface()) {
				ids.add(connection.getId());
			}
		}
		return StringUtil.defaultCreateUniqueString("connection_1", ids);
	}

}
