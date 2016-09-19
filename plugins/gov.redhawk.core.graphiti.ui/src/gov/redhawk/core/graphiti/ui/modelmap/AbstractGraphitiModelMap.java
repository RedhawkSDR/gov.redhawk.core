/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.core.graphiti.ui.modelmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.dt.IDiagramTypeProvider;
import org.eclipse.graphiti.features.ICreateConnectionFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.impl.CreateConnectionContext;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Connection;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.UIJob;

import CF.DataType;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;
import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractConnectInterfacePattern;
import gov.redhawk.core.graphiti.ui.editor.AbstractGraphitiMultiPageEditor;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.NonDirtyingCommand;
import gov.redhawk.sca.util.SubMonitor;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

public abstract class AbstractGraphitiModelMap {

	/**
	 * The EStructuralFeature path from a {@link ComponentInstantiation} to a {@link SoftPkg}.
	 */
	protected static final EStructuralFeature[] COMP_INST_TO_SPD_PATH = new EStructuralFeature[] {
		PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT, PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF,
		PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE, PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG };

	/**
	 * The EStructuralFeature path from a {@link ConnectInterface} to the uses port to the
	 * {@link ComponentInstantiation} for the uses port.
	 */
	private static final EStructuralFeature[] CONN_INST_PATH = new EStructuralFeature[] { PartitioningPackage.Literals.CONNECT_INTERFACE__USES_PORT,
		PartitioningPackage.Literals.USES_PORT__COMPONENT_INSTANTIATION_REF, PartitioningPackage.Literals.COMPONENT_INSTANTIATION_REF__INSTANTIATION };

	private AbstractGraphitiMultiPageEditor editor;

	private final Map<String, ConnectionMapEntry> connections = Collections.synchronizedMap(new HashMap<String, ConnectionMapEntry>());

	protected AbstractGraphitiModelMap(AbstractGraphitiMultiPageEditor editor) {
		Assert.isNotNull(editor, "Editor must not be null");
		this.editor = editor;
	}

	protected AbstractGraphitiMultiPageEditor getEditor() {
		return this.editor;
	}

	protected TransactionalEditingDomain getDiagramEditingDomain() {
		return this.editor.getDiagramEditor().getEditingDomain();
	}

	////////////////////////////////////////////////////
	// Components/devices/services section
	////////////////////////////////////////////////////

	/**
	 * Create a {@link ComponentInstantiation} from the provided SCA model object and add it to the diagram.
	 * @param newComponent
	 * @param monitor
	 * @return
	 * @throws CoreException
	 */
	protected ComponentInstantiation create(final ScaPropertyContainer< ? , SoftPkg> newObject, IProgressMonitor monitor) throws CoreException {
		final int WORK_FETCH_ATTR = 1;
		final int WORK_FETCH_PROFILE = 2;
		final int WORK_CREATE_OBJECT = 1;
		SubMonitor progress = SubMonitor.convert(monitor, WORK_FETCH_ATTR + WORK_FETCH_PROFILE);

		// get SoftPkg
		newObject.fetchAttributes(progress.newChild(WORK_FETCH_ATTR));
		final SoftPkg spd = newObject.fetchProfileObject(progress.newChild(WORK_FETCH_PROFILE));
		if (spd == null) {
			throw new IllegalStateException("Unable to load new object's SPD");
		}

		// Setup for transaction in diagram
		final IDiagramTypeProvider provider = getEditor().getDiagramEditor().getDiagramTypeProvider();
		final IFeatureProvider featureProvider = provider.getFeatureProvider();
		final Diagram diagram = provider.getDiagram();
		final TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) getEditor().getEditingDomain();

		// Create EMF commands
		final CreateComponentInstantiationCommand compInstCommand = createComponentInstantiationCommand(editingDomain, featureProvider, newObject);
		Command setEnabledCommand = new RecordingCommand(editingDomain) {

			@Override
			protected void doExecute() {
				// The object already exists, so enable it.
				RHContainerShape shape = DUtil.getPictogramElementForBusinessObject(diagram, compInstCommand.getComponentInstantiation(),
					RHContainerShape.class);
				shape.setEnabled(true);
			}
		};
		CompoundCommand compoundCommand = new CompoundCommand(Arrays.asList(compInstCommand, setEnabledCommand));

		// Execute the commands
		editingDomain.getCommandStack().execute(compoundCommand);
		progress.worked(WORK_CREATE_OBJECT);

		progress.done();
		return compInstCommand.getComponentInstantiation();
	}

	/**
	 * Creates an appropriate {@link CreateComponentInstantiationCommand}. See that class for details.
	 * @param editingDomain
	 * @param featureProvider
	 * @param newObject
	 * @return
	 */
	protected abstract CreateComponentInstantiationCommand createComponentInstantiationCommand(TransactionalEditingDomain editingDomain,
		IFeatureProvider featureProvider, ScaPropertyContainer< ? , SoftPkg> newObject);

	/**
	 * Get the SCA model object for a {@link ComponentInstantiation} as a {@link CorbaObjWrapper}
	 * @param compInst
	 * @return
	 */
	protected abstract CorbaObjWrapper< ? > getCorbaObjWrapper(ComponentInstantiation compInst);

	/**
	 * Get the SCA model object for a {@link ComponentInstantiation} as a {@link ScaPortContainer}
	 * @param portContainer
	 * @return
	 */
	protected abstract ScaPortContainer getPortContainer(ComponentInstantiation portContainer);

	////////////////////////////////////////////////////
	// Connections section
	////////////////////////////////////////////////////

	/**
	 * Called when a new {@link ConnectInterface} is added to the diagram's XML model. Asynchronously makes the actual
	 * connection.
	 * @param connIntf
	 */
	public void add(final ConnectInterface< ? , ? , ? > connIntf) {
		final ConnectionMapEntry connectionMap = new ConnectionMapEntry();
		connectionMap.setProfile(connIntf);
		synchronized (connections) {
			if (connections.get(connectionMap.getKey()) != null) {
				return;
			} else {
				connections.put(connectionMap.getKey(), connectionMap);
			}
		}
		Job job = new Job("Creating connection " + connIntf.getId()) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor subMonitor = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);
				try {
					ScaConnection newConnection = AbstractGraphitiModelMap.this.create(connIntf);
					connectionMap.setScaConnection(newConnection);
					return Status.OK_STATUS;
				} catch (final InvalidPort e) {
					delete(connIntf);
					connections.remove(connectionMap.getKey());
					String errorMsg = "Failed to create connection. " + CFErrorFormatter.format(e, connIntf.getId());
					return new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, errorMsg, e);
				} catch (final OccupiedPort e) {
					delete(connIntf);
					connections.remove(connectionMap.getKey());
					String errorMsg = "Failed to create connection. " + CFErrorFormatter.format(e, connIntf.getId());
					return new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, errorMsg, e);
				} finally {
					if (connections.get(connectionMap.getKey()) == null) {
						delete(connIntf);
					}
					subMonitor.done();
				}
			}

		};
		job.schedule();
	}

	/**
	 * Called when a new {@link ScaConnection} is added to the SCA model. Asynchronously updates the diagram.
	 * @param connection
	 */
	public void add(final ScaConnection connection) {
		// Ignore connections at the waveform / device manager level
		EObject portContainer = connection.getPort().eContainer();
		if (portContainer instanceof ScaWaveform || portContainer instanceof ScaDeviceManager) {
			return;
		}

		final ConnectionMapEntry connectionMap = new ConnectionMapEntry();
		connectionMap.setScaConnection(connection);
		synchronized (connections) {
			if (connections.get(connectionMap.getKey()) != null) {
				return;
			} else {
				connections.put(connectionMap.getKey(), connectionMap);
			}
		}
		Job job = new Job("Display new connection " + connection.getId()) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				SubMonitor subMonitor = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);
				ConnectInterface< ? , ? , ? > newConnIntf = null;
				try {
					newConnIntf = AbstractGraphitiModelMap.this.create(connection);
					if (newConnIntf == null) {
						connections.remove(connectionMap.getKey());
						return Status.CANCEL_STATUS;
					}
					connectionMap.setProfile(newConnIntf);
					return Status.OK_STATUS;
				} catch (CoreException e) {
					connections.remove(connectionMap.getKey());
					return new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, "Failed to display new connection " + connection.getId(), e);
				} finally {
					if (connections.get(connectionMap.getKey()) == null) {
						delete(newConnIntf);
					}
					subMonitor.done();
				}
			}

		};
		job.schedule();
	}

	/**
	 * Creates a {@link ScaConnection} for a corresponding {@link ConnectInterface}. This triggers a blocking CORBA
	 * call.
	 * @param conn
	 * @return
	 * @throws InvalidPort
	 * @throws OccupiedPort
	 */
	private ScaConnection create(final ConnectInterface< ? , ? , ? > conn) throws InvalidPort, OccupiedPort {
		ComponentInstantiation inst = ScaEcoreUtils.getFeature(conn, CONN_INST_PATH);
		final ScaPortContainer sourceComp = getPortContainer(inst);
		if (sourceComp == null) {
			return null;
		}
		sourceComp.fetchPorts(null);
		final ScaUsesPort usesPort = (ScaUsesPort) sourceComp.getScaPort(conn.getUsesPort().getUsesIdentifier());
		org.omg.CORBA.Object targetObj = null;
		if (conn.getComponentSupportedInterface() != null) {
			final CorbaObjWrapper< ? > targetComp = getCorbaObjWrapper(conn.getComponentSupportedInterface().getComponentInstantiationRef().getInstantiation());
			if (targetComp != null) {
				targetObj = targetComp.getCorbaObj();
			}
		} else if (conn.getProvidesPort() != null) {
			final ScaPortContainer targetComp = getPortContainer(conn.getProvidesPort().getComponentInstantiationRef().getInstantiation());
			if (targetComp != null) {
				targetComp.fetchPorts(null);
				final ScaPort< ? , ? > targetPort = targetComp.getScaPort(conn.getProvidesPort().getProvidesIdentifier());
				if (targetPort != null) {
					targetObj = targetPort.getCorbaObj();
				}
			}
		}
		final String connId = conn.getId();

		if (connId != null) {
			if (targetObj != null) {
				usesPort.connectPort(targetObj, connId);
			}
			for (final ScaConnection newConn : usesPort.fetchConnections(null)) {
				if (connId.equals(newConn.getId())) {
					return newConn;
				}
			}
		}
		return null;
	}

	/**
	 * Create a {@link ConnectInterface} in the diagram's XML model for a corresponding {@link ScaConnection}. This
	 * operation may make blocking CORBA calls.
	 * @param newValue
	 * @return
	 * @throws CoreException
	 */
	private ConnectInterface< ? , ? , ? > create(final ScaConnection newValue) throws CoreException {
		UsesPortStub source = findSource(newValue);
		ConnectionTarget target = findTarget(newValue);

		// setup for transaction in diagram
		final IDiagramTypeProvider provider = editor.getDiagramEditor().getDiagramTypeProvider();
		final IFeatureProvider featureProvider = provider.getFeatureProvider();
		final Diagram diagram = provider.getDiagram();
		final TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();

		// get anchors from business objects
		final Anchor sourceAnchor = (Anchor) DUtil.getPictogramElementForBusinessObject(diagram, source, Anchor.class);
		final Anchor targetAnchor = (Anchor) DUtil.getPictogramElementForBusinessObject(diagram, target, Anchor.class);

		// Create Component in transaction
		final ConnectInterface< ? , ? , ? >[] connectInterfaces = new ConnectInterface< ? , ? , ? >[1];
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// create connection feature
				CreateConnectionContext createConnectionContext = new CreateConnectionContext();
				createConnectionContext.putProperty(AbstractConnectInterfacePattern.OVERRIDE_CONNECTION_ID, newValue.getId());
				createConnectionContext.setSourceAnchor(sourceAnchor);
				createConnectionContext.setTargetAnchor(targetAnchor);
				ICreateConnectionFeature[] createConnectionFeatures = featureProvider.getCreateConnectionFeatures();
				for (ICreateConnectionFeature createConnectionFeature : createConnectionFeatures) {
					if (createConnectionFeature.canCreate(createConnectionContext)) {
						Connection connection = createConnectionFeature.create(createConnectionContext);
						// get business object for newly created diagram connection
						connectInterfaces[0] = (ConnectInterface< ? , ? , ? >) DUtil.getBusinessObject(connection);
						break;
					}
				}

			}
		});

		return connectInterfaces[0];
	}

	/**
	 * Find the {@link UsesPortStub} in the diagram's model for a {@link ScaConnection}.
	 * @param newValue
	 * @return
	 */
	protected abstract UsesPortStub findSource(ScaConnection newValue);

	/**
	 * Find the {@link ConnectionTarget} in the diagram's model for a {@link ScaConnection}.
	 * @param newValue
	 * @return
	 */
	protected abstract ConnectionTarget findTarget(ScaConnection newValue);

	/**
	 * Get the {@link ScaConnection} for a {@link ConnectInterface}.
	 * @param connIntf
	 * @return
	 */
	public ScaConnection get(final ConnectInterface< ? , ? , ? > connIntf) {
		if (connIntf == null) {
			return null;
		}
		ConnectionMapEntry connectionMap = connections.get(ConnectionMapEntry.getKey(connIntf));
		if (connectionMap != null) {
			return connectionMap.getScaConnection();
		} else {
			return null;
		}
	}

	/**
	 * Get the {@link ConnectInterface} for a {@link ScaConnection}.
	 * @param connection
	 * @return
	 */
	public ConnectInterface< ? , ? , ? > get(final ScaConnection connection) {
		if (connection == null) {
			return null;
		}
		ConnectionMapEntry connectionMap = connections.get(ConnectionMapEntry.getKey(connection));
		if (connectionMap != null) {
			return connectionMap.getProfile();
		} else {
			return null;
		}
	}

	public void put(ScaConnection con, ConnectInterface< ? , ? , ? > connectInterface) {
		ConnectionMapEntry connectionMap = new ConnectionMapEntry();
		connectionMap.setScaConnection(con);
		connectionMap.setProfile(connectInterface);
		connections.put(connectionMap.getKey(), connectionMap);
	}

	/**
	 * Called when a {@link ConnectInterface} is removed from the diagram's XML model. Asynchronously performs the
	 * disconnection.
	 * @param connIntf
	 */
	public void remove(final ConnectInterface< ? , ? , ? > connIntf) {
		final ConnectionMapEntry connectionMap = connections.remove(ConnectionMapEntry.getKey(connIntf));
		if (connectionMap == null) {
			return;
		}
		final ScaConnection oldConnection = connectionMap.getScaConnection();
		if (oldConnection != null) {
			Job job = new Job("Disconnecting connection " + connIntf.getId()) {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					SubMonitor subMonitor = SubMonitor.convert(monitor, IProgressMonitor.UNKNOWN);
					try {
						disconnect(oldConnection);
						return Status.OK_STATUS;
					} catch (InvalidPort e) {
						return new Status(IStatus.WARNING, GraphitiUIPlugin.PLUGIN_ID, "Problems while removing connection " + connIntf.getId(), e);
					} finally {
						subMonitor.done();
					}
				}

			};
			job.schedule();
		}
	}

	/**
	 * Called when an existing {@link ScaConnection} is removed from the SCA model. Asynchronously updates the diagram.
	 * @param connIntf
	 */
	public void remove(final ScaConnection connIntf) {
		final ConnectionMapEntry connectionMap = connections.remove(ConnectionMapEntry.getKey(connIntf));
		if (connectionMap == null) {
			return;
		}
		final ConnectInterface< ? , ? , ? > oldConnectInterface = connectionMap.getProfile();
		if (oldConnectInterface != null) {
			delete(oldConnectInterface);
		}

	}

	/**
	 * Disconnect the specified {@link ScaConnection}. This triggers a blocking CORBA call.
	 * @param oldConnection
	 * @throws InvalidPort
	 */
	private void disconnect(final ScaConnection oldConnection) throws InvalidPort {
		if (oldConnection == null) {
			return;
		}
		if (oldConnection.getPort() != null && !oldConnection.getPort().isDisposed()) {
			oldConnection.getPort().disconnectPort(oldConnection);
		}
	}

	////////////////////////////////////////////////////
	// Reflecting status
	////////////////////////////////////////////////////

	/**
	 * Sets the enabled state of the pictogram element. This affects its color (grey/blue), as well as whether
	 * operations
	 * are permitted on it.
	 * @param componentInstantiation
	 * @param enabled
	 */
	protected void updateEnabledState(final ComponentInstantiation componentInstantiation, final boolean enabled) {
		getDiagramEditingDomain().getCommandStack().execute(new NonDirtyingCommand() {
			@Override
			public void execute() {
				Diagram diagram = getEditor().getDiagramEditor().getDiagramTypeProvider().getDiagram();
				RHContainerShape componentShape = DUtil.getPictogramElementForBusinessObject(diagram, componentInstantiation, RHContainerShape.class);
				if (componentShape != null) {
					componentShape.setEnabled(enabled);
				}
			}
		});
	}

	/**
	 * Asynchronously sets the {@link IStatus} on the pictogram element so it can reflect error / non-errored state
	 * @param componentInstantiation
	 * @param status
	 */
	protected void updateErrorState(final ComponentInstantiation componentInstantiation, final IStatus status) {
		Assert.isNotNull(componentInstantiation);
		Job job = new UIJob("Update error state for " + componentInstantiation.getUsageName()) {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				final IDiagramTypeProvider provider = getEditor().getDiagramEditor().getDiagramTypeProvider();
				final Diagram diagram = provider.getDiagram();
				final RHContainerShape componentShape = DUtil.getPictogramElementForBusinessObject(diagram, componentInstantiation, RHContainerShape.class);
				if (componentShape == null) {
					return Status.CANCEL_STATUS;
				}

				NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
					@Override
					public void execute() {
						componentShape.setIStatusSeverity(status.getSeverity());
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.schedule();
	}

	/**
	 * Asynchronously sets the started/stopped state on the pictogram element so it can reflect that
	 * @param componentInstantiation
	 * @param status
	 */
	protected void updateStateStopState(final ComponentInstantiation componentInstantiation, final boolean started) {
		Assert.isNotNull(componentInstantiation);
		Job job = new UIJob("Update start/stop state for " + componentInstantiation.getUsageName()) {
			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				final IDiagramTypeProvider provider = getEditor().getDiagramEditor().getDiagramTypeProvider();
				final Diagram diagram = provider.getDiagram();
				final RHContainerShape componentShape = DUtil.getPictogramElementForBusinessObject(diagram, componentInstantiation, RHContainerShape.class);
				if (componentShape == null) {
					return Status.CANCEL_STATUS;
				}

				NonDirtyingCommand.execute(diagram, new NonDirtyingCommand() {
					@Override
					public void execute() {
						componentShape.setStarted(started);
					}
				});
				return Status.OK_STATUS;
			}
		};
		job.setSystem(true);
		job.schedule();
	}

	////////////////////////////////////////////////////
	// Launching section
	////////////////////////////////////////////////////

	protected static DataType[] getInitialProperties(ComponentInstantiation compInst) {
		if (compInst.getComponentProperties() == null) {
			return null;
		}
		final List<DataType> params = new ArrayList<DataType>();
		for (final Entry entry : compInst.getComponentProperties().getProperties()) {
			if (entry.getValue() instanceof AbstractPropertyRef) {
				final AbstractPropertyRef< ? > ref = (AbstractPropertyRef< ? >) entry.getValue();
				params.add(new DataType(ref.getRefID(), ref.toAny()));
			}
		}
		return params.toArray(new DataType[params.size()]);
	}

	////////////////////////////////////////////////////
	// Deleting PictogramElements section
	////////////////////////////////////////////////////

	/**
	 * Deletes a {@link PictogramElement} corresponding to a {@link ComponentInstantiation} from the diagram.
	 * @param componentInstantiation
	 */
	protected void delete(final ComponentInstantiation componentInstantiation) {
		delete(componentInstantiation, RHContainerShape.class);
	}

	/**
	 * Delete a {@link PictogramElement} corresponding to a {@link ConnectInterface} from the diagram.
	 * @param connection
	 */
	protected void delete(final ConnectInterface< ? , ? , ? > connection) {
		delete(connection, Connection.class);
	}

	/**
	 * Deletes a {@link PictogramElement} from the diagram.
	 * @param deleteObj The business model object being deleted
	 * @param peClass The type of the pictogram element for the model object
	 */
	private < T extends PictogramElement > void delete(final EObject deleteObj, final Class<T> peClass) {
		if (deleteObj == null || editor.isDisposed()) {
			return;
		}

		// Run Graphiti model commands in the UI thread
		if (Display.getCurrent() == null) {
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					delete(deleteObj, peClass);
				}
			});
			return;
		}

		// setup to perform diagram operations
		final IDiagramTypeProvider provider = editor.getDiagramEditor().getDiagramTypeProvider();
		final IFeatureProvider featureProvider = provider.getFeatureProvider();
		final Diagram diagram = provider.getDiagram();
		boolean runtime = DUtil.isDiagramRuntime(diagram);

		// Get the PictogramElement
		final PictogramElement pictogramElement = DUtil.getPictogramElementForBusinessObject(diagram, deleteObj, peClass);
		if (pictogramElement == null) {
			return;
		}

		// Run the delete feature in a transaction
		final TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new DeleteCommand(editingDomain, pictogramElement, featureProvider, runtime));
	}
}
