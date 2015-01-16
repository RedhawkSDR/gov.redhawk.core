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
package gov.redhawk.monitor.model.ports.impl;

import gov.redhawk.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortsPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Connection Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link gov.redhawk.monitor.model.ports.impl.PortConnectionMonitorImpl#getPort <em>Port</em>}</li>
 * <li>{@link gov.redhawk.monitor.model.ports.impl.PortConnectionMonitorImpl#getConnectionId <em>Connection Id</em>}
 * </li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortConnectionMonitorImpl extends PortStatisticsProviderImpl implements PortConnectionMonitor {

	/**
	 * The default value of the '{@link #getConnectionId() <em>Connection Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionId()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getConnectionId() <em>Connection Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionId()
	 * @generated
	 * @ordered
	 */
	protected String connectionId = CONNECTION_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortConnectionMonitorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortsPackage.Literals.PORT_CONNECTION_MONITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PortMonitor getPort() {
		if (eContainerFeatureID() != PortsPackage.PORT_CONNECTION_MONITOR__PORT)
			return null;
		return (PortMonitor) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPort(PortMonitor newPort, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPort, PortsPackage.PORT_CONNECTION_MONITOR__PORT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPort(PortMonitor newPort) {
		if (newPort != eInternalContainer() || (eContainerFeatureID() != PortsPackage.PORT_CONNECTION_MONITOR__PORT && newPort != null)) {
			if (EcoreUtil.isAncestor(this, newPort))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPort != null)
				msgs = ((InternalEObject) newPort).eInverseAdd(this, PortsPackage.PORT_MONITOR__CONNECTIONS, PortMonitor.class, msgs);
			msgs = basicSetPort(newPort, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_CONNECTION_MONITOR__PORT, newPort, newPort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConnectionId() {
		return connectionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConnectionId(String newConnectionId) {
		String oldConnectionId = connectionId;
		connectionId = newConnectionId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_CONNECTION_MONITOR__CONNECTION_ID, oldConnectionId, connectionId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetPort((PortMonitor) otherEnd, msgs);
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
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			return basicSetPort(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			return eInternalContainer().eInverseRemove(this, PortsPackage.PORT_MONITOR__CONNECTIONS, PortMonitor.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			return getPort();
		case PortsPackage.PORT_CONNECTION_MONITOR__CONNECTION_ID:
			return getConnectionId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			setPort((PortMonitor) newValue);
			return;
		case PortsPackage.PORT_CONNECTION_MONITOR__CONNECTION_ID:
			setConnectionId((String) newValue);
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
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			setPort((PortMonitor) null);
			return;
		case PortsPackage.PORT_CONNECTION_MONITOR__CONNECTION_ID:
			setConnectionId(CONNECTION_ID_EDEFAULT);
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
		case PortsPackage.PORT_CONNECTION_MONITOR__PORT:
			return getPort() != null;
		case PortsPackage.PORT_CONNECTION_MONITOR__CONNECTION_ID:
			return CONNECTION_ID_EDEFAULT == null ? connectionId != null : !CONNECTION_ID_EDEFAULT.equals(connectionId);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (connectionId: ");
		result.append(connectionId);
		result.append(')');
		return result.toString();
	}

} // PortConnectionMonitorImpl
