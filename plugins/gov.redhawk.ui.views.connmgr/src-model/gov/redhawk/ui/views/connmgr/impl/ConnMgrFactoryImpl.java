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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import gov.redhawk.ui.views.connmgr.ConnMgrFactory;
import gov.redhawk.ui.views.connmgr.ConnMgrPackage;
import gov.redhawk.ui.views.connmgr.ConnectionStatus;
import gov.redhawk.ui.views.connmgr.ScaConnectionManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnMgrFactoryImpl extends EFactoryImpl implements ConnMgrFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ConnMgrFactory init() {
		try {
			ConnMgrFactory theConnMgrFactory = (ConnMgrFactory) EPackage.Registry.INSTANCE.getEFactory(ConnMgrPackage.eNS_URI);
			if (theConnMgrFactory != null) {
				return theConnMgrFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConnMgrFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnMgrFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ConnMgrPackage.SCA_CONNECTION_MANAGER:
			return createScaConnectionManager();
		case ConnMgrPackage.CONNECTION_STATUS:
			return createConnectionStatus();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaConnectionManager createScaConnectionManager() {
		ScaConnectionManagerImpl scaConnectionManager = new ScaConnectionManagerImpl();
		return scaConnectionManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionStatus createConnectionStatus() {
		ConnectionStatusImpl connectionStatus = new ConnectionStatusImpl();
		return connectionStatus;
	}

	// END GENERATED CODE

	public ConnectionStatus createConnectionStatus(CF.ConnectionManagerPackage.ConnectionStatusType cfStatus) {
		ConnectionStatus connectionStatus = createConnectionStatus();
		connectionStatus.setConnectionRecordID(cfStatus.connectionRecordId);
		connectionStatus.setConnectionID(cfStatus.connectionId);
		connectionStatus.setRequesterID(cfStatus.requesterId);
		connectionStatus.setConnected(cfStatus.connected);
		connectionStatus.setSourceIOR(cfStatus.usesEndpoint.endpointObject.toString());
		connectionStatus.setSourceEntityName(cfStatus.usesEndpoint.entityId);
		connectionStatus.setSourcePortName(cfStatus.usesEndpoint.portName);
		connectionStatus.setSourceRepoID(cfStatus.usesEndpoint.repositoryId);
		connectionStatus.setTargetIOR(cfStatus.providesEndpoint.endpointObject.toString());
		connectionStatus.setTargetEntityName(cfStatus.providesEndpoint.entityId);
		connectionStatus.setTargetPortName(cfStatus.providesEndpoint.portName);
		connectionStatus.setTargetRepoID(cfStatus.providesEndpoint.repositoryId);
		return connectionStatus;
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnMgrPackage getConnMgrPackage() {
		return (ConnMgrPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ConnMgrPackage getPackage() {
		return ConnMgrPackage.eINSTANCE;
	}

} // ConnMgrFactoryImpl
