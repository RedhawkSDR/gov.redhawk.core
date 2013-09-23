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

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.scd.Uses;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

import CF.Port;
import CF.PortHelper;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;
import ExtendedCF.QueryablePort;
import ExtendedCF.QueryablePortHelper;
import ExtendedCF.UsesConnection;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Uses Port</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaUsesPortImpl#getConnections <em>Connections</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaUsesPortImpl extends ScaPortImpl<Uses, Port> implements ScaUsesPort {
	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaConnection> connections;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaUsesPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_USES_PORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setProfileObj(Uses newProfileObj) {
		super.setProfileObj(newProfileObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ScaConnection> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentWithInverseEList.Unsettable.Resolving<ScaConnection>(ScaConnection.class, this, ScaPackage.SCA_USES_PORT__CONNECTIONS, ScaPackage.SCA_CONNECTION__PORT);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetConnections() {
		if (connections != null) ((InternalEList.Unsettable<?>)connections).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetConnections() {
		return connections != null && ((InternalEList.Unsettable<?>)connections).isSet();
	}

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 8.0
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated NOT
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = "uses";

	/**
     * @since 14.0
     */
	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		fetchConnections(monitor);
    }

	private final VersionedFeature connectionsFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_USES_PORT__CONNECTIONS);
	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaConnection> fetchConnections(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		Port portObj = fetchNarrowedObject(subMonitor.newChild(1));
		if (portObj instanceof QueryablePort) {
			Transaction transaction = connectionsFeature.createTransaction();
			QueryablePort queryPort = (QueryablePort) portObj;
			IStatus tmpStatus = Status.OK_STATUS;
			UsesConnection[] tmpConnections = new UsesConnection[0];
			try {
				tmpConnections = queryPort.connections();
			} catch (SystemException e) {
				tmpStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port connections.", e);
			}
			final IStatus fetchStatus = tmpStatus;
			final UsesConnection[] newConnections = tmpConnections;
			transaction.addCommand(new ScaModelCommand() {

				@Override
				public void execute() {
					Map<String, ScaConnection> currentConnections = new HashMap<String, ScaConnection>();
					for (ScaConnection connection : getConnections()) {
						currentConnections.put(connection.getId(), connection);
					}

					Map<String, ScaConnection> connectionToRemove = new HashMap<String, ScaConnection>();
					connectionToRemove.putAll(currentConnections);

					Map<String, ScaConnection> newConnectionMaps = Collections.emptyMap();
					if (newConnections != null) {
						newConnectionMaps = new HashMap<String, ScaConnection>();
						for (UsesConnection connection : newConnections) {
							ScaConnection newConnection = ScaFactory.eINSTANCE.createScaConnection();
							newConnection.setData(connection);
							newConnectionMaps.put(connection.connectionId, newConnection);
						}
					}
					connectionToRemove.keySet().removeAll(newConnectionMaps.keySet());

					// Remove old connections
					getConnections().removeAll(connectionToRemove.values());

					// Remove duplicates
					newConnectionMaps.keySet().removeAll(currentConnections.keySet());

					// Add new connections
					getConnections().addAll(newConnectionMaps.values());

					setStatus(ScaPackage.Literals.SCA_USES_PORT__CONNECTIONS, fetchStatus);
				}
			});
			transaction.commit();
		}

		subMonitor.done();
		return getConnections();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * 
	 */
	@Override
	public void disconnectPort(ScaConnection connection) throws InvalidPort {
		// END GENERATED CODE
		if (connection != null) {
			disconnectPort(connection.getData().connectionId);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void connectPort(org.omg.CORBA.Object connection, String connectionId) throws InvalidPort, OccupiedPort {
		// END GENERATED CODE
		Port usesPort = fetchNarrowedObject(null);
		if (usesPort == null) {
			throw new IllegalStateException("Not connected to a CORBA Object instance.");
		}
		usesPort.connectPort(connection, connectionId);
		fetchConnections(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void disconnectPort(String connectionId) throws InvalidPort {
		// END GENERATED CODE
		Port usesPort = fetchNarrowedObject(null);
		if (usesPort == null) {
			throw new IllegalStateException("Not connected to a CORBA Object instance.");
		}
		usesPort.disconnectPort(connectionId);
		fetchConnections(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnections()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
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
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends ScaConnection>)newValue);
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
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				unsetConnections();
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
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				return isSetConnections();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getType() {
		// END GENERATED CODE
		return ScaUsesPortImpl.TYPE_EDEFAULT;
		// BEGIN GENERATED CODE
	}


	/**
	 * @since 14.0
	 */
	@Override
	protected Port narrow(final org.omg.CORBA.Object obj) {
		if (obj == null) {
			return null;
		} else if (_is_a(QueryablePortHelper.id())) {
			return QueryablePortHelper.narrow(obj);
		} else {
			return PortHelper.narrow(obj);
		}
	}

} //ScaUsesPortImpl
