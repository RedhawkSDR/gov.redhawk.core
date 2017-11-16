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
// BEGIN GENERATED CODE
package gov.redhawk.ui.views.connmgr.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

import CF.ConnectionManager;
import CF.ConnectionManagerHelper;
import CF.ConnectionStatusIteratorHolder;
import CF.ConnectionManagerPackage.ConnectionStatusSequenceHolder;
import CF.ConnectionManagerPackage.ConnectionStatusType;
import CF.ConnectionManagerPackage.EndpointRequest;
import CF.PortPackage.InvalidPort;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.model.sca.impl.CorbaObjWrapperImpl;
import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sca Connection Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ScaConnectionManagerImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaConnectionManagerImpl extends CorbaObjWrapperImpl<ConnectionManager> implements ScaConnectionManager {
	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectionStatus> connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaConnectionManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConnMgrPackage.Literals.SCA_CONNECTION_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setObj(ConnectionManager newObj) {
		super.setObj(newObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectionStatus> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentEList<ConnectionStatus>(ConnectionStatus.class, this, ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String connect(EndpointRequest usesEndpoint, EndpointRequest providesEndpoint, String requesterId, String connectionId) throws InvalidPort {
		// END GENERATED CODE
		ConnectionManager connMgr = fetchNarrowedObject(null);
		if (connMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return connMgr.connect(usesEndpoint, providesEndpoint, requesterId, connectionId);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void disconnect(String connectionRecordId) throws InvalidPort {
		// END GENERATED CODE
		ConnectionManager connMgr = fetchNarrowedObject(null);
		if (connMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		connMgr.disconnect(connectionRecordId);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ConnectionStatusType[] connections() {
		// END GENERATED CODE
		ConnectionManager connMgr = fetchNarrowedObject(null);
		if (connMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return connMgr.connections();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void listConnections(int how_many, ConnectionStatusSequenceHolder connections, ConnectionStatusIteratorHolder iter) {
		// END GENERATED CODE
		ConnectionManager connMgr = fetchNarrowedObject(null);
		if (connMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		connMgr.listConnections(how_many, connections, iter);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS:
			return ((InternalEList< ? >) getConnections()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS:
			return getConnections();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS:
			getConnections().clear();
			getConnections().addAll((Collection< ? extends ConnectionStatus>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS:
			getConnections().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER__CONNECTIONS:
			return connections != null && !connections.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	// END GENERATED CODE

	@Override
	protected ConnectionManager narrow(org.omg.CORBA.Object obj) {
		return ConnectionManagerHelper.narrow(obj);
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		internalFetchConnections(monitor);
	}

	private VersionedFeature connectionsFeature = new VersionedFeature(this, ConnMgrPackage.Literals.SCA_CONNECTION_MANAGER__CONNECTIONS);

	private void internalFetchConnections(IProgressMonitor monitor) {
		if (isDisposed()) {
			return;
		}

		final SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		final ConnectionManager connMgr = fetchNarrowedObject(subMonitor.split(1));
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}

		Transaction transaction = connectionsFeature.createTransaction();
		if (connMgr != null) {
			ConnectionStatusType[] cfConnectionStatuses;
			try {
				cfConnectionStatuses = connMgr.connections();
				transaction.addCommand(new ScaConnectionManagerMergeConnectionsCommand(this, cfConnectionStatuses));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch allocations.", e);
				transaction.addCommand(new SetStatusCommand<>(this, ConnMgrPackage.Literals.SCA_CONNECTION_MANAGER__CONNECTIONS, status));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ConnMgrPackage.Literals.SCA_CONNECTION_MANAGER__CONNECTIONS));
		}

		// Perform Actions
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
	}

	// BEGIN GENERATED CODE

} // ScaConnectionManagerImpl
