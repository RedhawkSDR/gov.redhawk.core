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

import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connection Status</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getConnectionRecordID <em>Connection Record
 * ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getConnectionID <em>Connection ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getRequesterID <em>Requester ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#isConnected <em>Connected</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getSourceIOR <em>Source IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getSourceEntityName <em>Source Entity
 * Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getSourcePortName <em>Source Port Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getSourceRepoID <em>Source Repo ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getTargetIOR <em>Target IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getTargetEntityName <em>Target Entity
 * Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getTargetPortName <em>Target Port Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl#getTargetRepoID <em>Target Repo ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectionStatusImpl extends MinimalEObjectImpl.Container implements ConnectionStatus {
	/**
	 * The default value of the '{@link #getConnectionRecordID() <em>Connection Record ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionRecordID()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_RECORD_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionRecordID() <em>Connection Record ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionRecordID()
	 * @generated
	 * @ordered
	 */
	protected String connectionRecordID = CONNECTION_RECORD_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getConnectionID() <em>Connection ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionID()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnectionID() <em>Connection ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionID()
	 * @generated
	 * @ordered
	 */
	protected String connectionID = CONNECTION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getRequesterID() <em>Requester ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequesterID()
	 * @generated
	 * @ordered
	 */
	protected static final String REQUESTER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRequesterID() <em>Requester ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequesterID()
	 * @generated
	 * @ordered
	 */
	protected String requesterID = REQUESTER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConnected()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONNECTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConnected()
	 * @generated
	 * @ordered
	 */
	protected boolean connected = CONNECTED_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceIOR() <em>Source IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceIOR()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceIOR() <em>Source IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceIOR()
	 * @generated
	 * @ordered
	 */
	protected String sourceIOR = SOURCE_IOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceEntityName() <em>Source Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEntityName()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_ENTITY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceEntityName() <em>Source Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceEntityName()
	 * @generated
	 * @ordered
	 */
	protected String sourceEntityName = SOURCE_ENTITY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourcePortName() <em>Source Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePortName()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_PORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourcePortName() <em>Source Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourcePortName()
	 * @generated
	 * @ordered
	 */
	protected String sourcePortName = SOURCE_PORT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceRepoID() <em>Source Repo ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRepoID()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_REPO_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceRepoID() <em>Source Repo ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRepoID()
	 * @generated
	 * @ordered
	 */
	protected String sourceRepoID = SOURCE_REPO_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetIOR() <em>Target IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetIOR()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_IOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetIOR() <em>Target IOR</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetIOR()
	 * @generated
	 * @ordered
	 */
	protected String targetIOR = TARGET_IOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetEntityName() <em>Target Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntityName()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_ENTITY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetEntityName() <em>Target Entity Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetEntityName()
	 * @generated
	 * @ordered
	 */
	protected String targetEntityName = TARGET_ENTITY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetPortName() <em>Target Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetPortName()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_PORT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetPortName() <em>Target Port Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetPortName()
	 * @generated
	 * @ordered
	 */
	protected String targetPortName = TARGET_PORT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetRepoID() <em>Target Repo ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRepoID()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_REPO_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetRepoID() <em>Target Repo ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRepoID()
	 * @generated
	 * @ordered
	 */
	protected String targetRepoID = TARGET_REPO_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionStatusImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConnMgrPackage.Literals.CONNECTION_STATUS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectionRecordID() {
		return connectionRecordID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionRecordID(String newConnectionRecordID) {
		String oldConnectionRecordID = connectionRecordID;
		connectionRecordID = newConnectionRecordID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID, oldConnectionRecordID,
				connectionRecordID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnectionID() {
		return connectionID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionID(String newConnectionID) {
		String oldConnectionID = connectionID;
		connectionID = newConnectionID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID, oldConnectionID, connectionID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRequesterID() {
		return requesterID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequesterID(String newRequesterID) {
		String oldRequesterID = requesterID;
		requesterID = newRequesterID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID, oldRequesterID, requesterID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnected(boolean newConnected) {
		boolean oldConnected = connected;
		connected = newConnected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__CONNECTED, oldConnected, connected));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceIOR() {
		return sourceIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceIOR(String newSourceIOR) {
		String oldSourceIOR = sourceIOR;
		sourceIOR = newSourceIOR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR, oldSourceIOR, sourceIOR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceEntityName() {
		return sourceEntityName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceEntityName(String newSourceEntityName) {
		String oldSourceEntityName = sourceEntityName;
		sourceEntityName = newSourceEntityName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME, oldSourceEntityName, sourceEntityName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourcePortName() {
		return sourcePortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourcePortName(String newSourcePortName) {
		String oldSourcePortName = sourcePortName;
		sourcePortName = newSourcePortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME, oldSourcePortName, sourcePortName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceRepoID() {
		return sourceRepoID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceRepoID(String newSourceRepoID) {
		String oldSourceRepoID = sourceRepoID;
		sourceRepoID = newSourceRepoID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID, oldSourceRepoID, sourceRepoID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetIOR() {
		return targetIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetIOR(String newTargetIOR) {
		String oldTargetIOR = targetIOR;
		targetIOR = newTargetIOR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR, oldTargetIOR, targetIOR));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetEntityName() {
		return targetEntityName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetEntityName(String newTargetEntityName) {
		String oldTargetEntityName = targetEntityName;
		targetEntityName = newTargetEntityName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME, oldTargetEntityName, targetEntityName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetPortName() {
		return targetPortName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetPortName(String newTargetPortName) {
		String oldTargetPortName = targetPortName;
		targetPortName = newTargetPortName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME, oldTargetPortName, targetPortName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetRepoID() {
		return targetRepoID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetRepoID(String newTargetRepoID) {
		String oldTargetRepoID = targetRepoID;
		targetRepoID = newTargetRepoID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID, oldTargetRepoID, targetRepoID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
			return getConnectionRecordID();
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
			return getConnectionID();
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
			return getRequesterID();
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			return isConnected();
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR:
			return getSourceIOR();
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
			return getSourceEntityName();
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
			return getSourcePortName();
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
			return getSourceRepoID();
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR:
			return getTargetIOR();
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
			return getTargetEntityName();
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
			return getTargetPortName();
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
			return getTargetRepoID();
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
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
			setConnectionRecordID((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
			setConnectionID((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
			setRequesterID((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			setConnected((Boolean) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR:
			setSourceIOR((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
			setSourceEntityName((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
			setSourcePortName((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
			setSourceRepoID((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR:
			setTargetIOR((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
			setTargetEntityName((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
			setTargetPortName((String) newValue);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
			setTargetRepoID((String) newValue);
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
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
			setConnectionRecordID(CONNECTION_RECORD_ID_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
			setConnectionID(CONNECTION_ID_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
			setRequesterID(REQUESTER_ID_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			setConnected(CONNECTED_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR:
			setSourceIOR(SOURCE_IOR_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
			setSourceEntityName(SOURCE_ENTITY_NAME_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
			setSourcePortName(SOURCE_PORT_NAME_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
			setSourceRepoID(SOURCE_REPO_ID_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR:
			setTargetIOR(TARGET_IOR_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
			setTargetEntityName(TARGET_ENTITY_NAME_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
			setTargetPortName(TARGET_PORT_NAME_EDEFAULT);
			return;
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
			setTargetRepoID(TARGET_REPO_ID_EDEFAULT);
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
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_RECORD_ID:
			return CONNECTION_RECORD_ID_EDEFAULT == null ? connectionRecordID != null : !CONNECTION_RECORD_ID_EDEFAULT.equals(connectionRecordID);
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTION_ID:
			return CONNECTION_ID_EDEFAULT == null ? connectionID != null : !CONNECTION_ID_EDEFAULT.equals(connectionID);
		case ConnMgrPackage.CONNECTION_STATUS__REQUESTER_ID:
			return REQUESTER_ID_EDEFAULT == null ? requesterID != null : !REQUESTER_ID_EDEFAULT.equals(requesterID);
		case ConnMgrPackage.CONNECTION_STATUS__CONNECTED:
			return connected != CONNECTED_EDEFAULT;
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_IOR:
			return SOURCE_IOR_EDEFAULT == null ? sourceIOR != null : !SOURCE_IOR_EDEFAULT.equals(sourceIOR);
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_ENTITY_NAME:
			return SOURCE_ENTITY_NAME_EDEFAULT == null ? sourceEntityName != null : !SOURCE_ENTITY_NAME_EDEFAULT.equals(sourceEntityName);
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_PORT_NAME:
			return SOURCE_PORT_NAME_EDEFAULT == null ? sourcePortName != null : !SOURCE_PORT_NAME_EDEFAULT.equals(sourcePortName);
		case ConnMgrPackage.CONNECTION_STATUS__SOURCE_REPO_ID:
			return SOURCE_REPO_ID_EDEFAULT == null ? sourceRepoID != null : !SOURCE_REPO_ID_EDEFAULT.equals(sourceRepoID);
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_IOR:
			return TARGET_IOR_EDEFAULT == null ? targetIOR != null : !TARGET_IOR_EDEFAULT.equals(targetIOR);
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_ENTITY_NAME:
			return TARGET_ENTITY_NAME_EDEFAULT == null ? targetEntityName != null : !TARGET_ENTITY_NAME_EDEFAULT.equals(targetEntityName);
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_PORT_NAME:
			return TARGET_PORT_NAME_EDEFAULT == null ? targetPortName != null : !TARGET_PORT_NAME_EDEFAULT.equals(targetPortName);
		case ConnMgrPackage.CONNECTION_STATUS__TARGET_REPO_ID:
			return TARGET_REPO_ID_EDEFAULT == null ? targetRepoID != null : !TARGET_REPO_ID_EDEFAULT.equals(targetRepoID);
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
		result.append(" (connectionRecordID: ");
		result.append(connectionRecordID);
		result.append(", connectionID: ");
		result.append(connectionID);
		result.append(", requesterID: ");
		result.append(requesterID);
		result.append(", connected: ");
		result.append(connected);
		result.append(", sourceIOR: ");
		result.append(sourceIOR);
		result.append(", sourceEntityName: ");
		result.append(sourceEntityName);
		result.append(", sourcePortName: ");
		result.append(sourcePortName);
		result.append(", sourceRepoID: ");
		result.append(sourceRepoID);
		result.append(", targetIOR: ");
		result.append(targetIOR);
		result.append(", targetEntityName: ");
		result.append(targetEntityName);
		result.append(", targetPortName: ");
		result.append(targetPortName);
		result.append(", targetRepoID: ");
		result.append(targetRepoID);
		result.append(')');
		return result.toString();
	}

} // ConnectionStatusImpl
