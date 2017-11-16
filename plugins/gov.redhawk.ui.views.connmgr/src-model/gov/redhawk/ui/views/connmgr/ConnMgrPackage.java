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
package gov.redhawk.ui.views.connmgr;

import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.ui.views.connmgr.ConnMgrFactory
 * @model kind="package"
 * @generated
 */
public interface ConnMgrPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "connmgr";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://connmgr.views.ui.redhawk.gov/connmgr";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "connmgr";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ConnMgrPackage eINSTANCE = gov.redhawk.ui.views.connmgr.impl.ConnMgrPackageImpl.init();

	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.connmgr.impl.ScaConnectionManagerImpl <em>Sca Connection
	 * Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.connmgr.impl.ScaConnectionManagerImpl
	 * @see gov.redhawk.ui.views.connmgr.impl.ConnMgrPackageImpl#getScaConnectionManager()
	 * @generated
	 */
	int SCA_CONNECTION_MANAGER = 0;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__STATUS = ScaPackage.CORBA_OBJ_WRAPPER__STATUS;

	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__DISPOSED = ScaPackage.CORBA_OBJ_WRAPPER__DISPOSED;

	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__DATA_PROVIDERS = ScaPackage.CORBA_OBJ_WRAPPER__DATA_PROVIDERS;

	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__DATA_PROVIDERS_ENABLED = ScaPackage.CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;

	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__ENABLED_DATA_PROVIDERS = ScaPackage.CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;

	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__IOR = ScaPackage.CORBA_OBJ_WRAPPER__IOR;

	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__OBJ = ScaPackage.CORBA_OBJ_WRAPPER__OBJ;

	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__CORBA_OBJ = ScaPackage.CORBA_OBJ_WRAPPER__CORBA_OBJ;

	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__FEATURE_DATA = ScaPackage.CORBA_OBJ_WRAPPER__FEATURE_DATA;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER__CONNECTIONS = ScaPackage.CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sca Connection Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_MANAGER_FEATURE_COUNT = ScaPackage.CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl <em>Connection
	 * Status</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl
	 * @see gov.redhawk.ui.views.connmgr.impl.ConnMgrPackageImpl#getConnectionStatus()
	 * @generated
	 */
	int CONNECTION_STATUS = 1;

	/**
	 * The feature id for the '<em><b>Connection Record ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__CONNECTION_RECORD_ID = 0;

	/**
	 * The feature id for the '<em><b>Connection ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__CONNECTION_ID = 1;

	/**
	 * The feature id for the '<em><b>Requester ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__REQUESTER_ID = 2;

	/**
	 * The feature id for the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__CONNECTED = 3;

	/**
	 * The feature id for the '<em><b>Source IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__SOURCE_IOR = 4;

	/**
	 * The feature id for the '<em><b>Source Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__SOURCE_ENTITY_NAME = 5;

	/**
	 * The feature id for the '<em><b>Source Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__SOURCE_PORT_NAME = 6;

	/**
	 * The feature id for the '<em><b>Source Repo ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__SOURCE_REPO_ID = 7;

	/**
	 * The feature id for the '<em><b>Target IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__TARGET_IOR = 8;

	/**
	 * The feature id for the '<em><b>Target Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__TARGET_ENTITY_NAME = 9;

	/**
	 * The feature id for the '<em><b>Target Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__TARGET_PORT_NAME = 10;

	/**
	 * The feature id for the '<em><b>Target Repo ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS__TARGET_REPO_ID = 11;

	/**
	 * The number of structural features of the '<em>Connection Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS_FEATURE_COUNT = 12;

	/**
	 * The number of operations of the '<em>Connection Status</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_STATUS_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.connmgr.ScaConnectionManager <em>Sca Connection
	 * Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sca Connection Manager</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ScaConnectionManager
	 * @generated
	 */
	EClass getScaConnectionManager();

	/**
	 * Returns the meta object for the containment reference list
	 * '{@link gov.redhawk.ui.views.connmgr.ScaConnectionManager#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ScaConnectionManager#getConnections()
	 * @see #getScaConnectionManager()
	 * @generated
	 */
	EReference getScaConnectionManager_Connections();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus <em>Connection
	 * Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Status</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus
	 * @generated
	 */
	EClass getConnectionStatus();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionRecordID <em>Connection Record ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection Record ID</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionRecordID()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_ConnectionRecordID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionID
	 * <em>Connection ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connection ID</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionID()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_ConnectionID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getRequesterID
	 * <em>Requester ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Requester ID</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getRequesterID()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_RequesterID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#isConnected
	 * <em>Connected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connected</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#isConnected()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_Connected();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceIOR
	 * <em>Source IOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source IOR</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceIOR()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_SourceIOR();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceEntityName <em>Source Entity Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Entity Name</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceEntityName()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_SourceEntityName();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourcePortName
	 * <em>Source Port Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Port Name</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourcePortName()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_SourcePortName();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceRepoID
	 * <em>Source Repo ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Repo ID</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceRepoID()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_SourceRepoID();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetIOR
	 * <em>Target IOR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target IOR</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetIOR()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_TargetIOR();

	/**
	 * Returns the meta object for the attribute
	 * '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetEntityName <em>Target Entity Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Entity Name</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetEntityName()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_TargetEntityName();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetPortName
	 * <em>Target Port Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Port Name</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetPortName()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_TargetPortName();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetRepoID
	 * <em>Target Repo ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Repo ID</em>'.
	 * @see gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetRepoID()
	 * @see #getConnectionStatus()
	 * @generated
	 */
	EAttribute getConnectionStatus_TargetRepoID();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ConnMgrFactory getConnMgrFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each operation of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.connmgr.impl.ScaConnectionManagerImpl <em>Sca
		 * Connection Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.connmgr.impl.ScaConnectionManagerImpl
		 * @see gov.redhawk.ui.views.connmgr.impl.ConnMgrPackageImpl#getScaConnectionManager()
		 * @generated
		 */
		EClass SCA_CONNECTION_MANAGER = eINSTANCE.getScaConnectionManager();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCA_CONNECTION_MANAGER__CONNECTIONS = eINSTANCE.getScaConnectionManager_Connections();

		/**
		 * The meta object literal for the '{@link gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl <em>Connection
		 * Status</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.ui.views.connmgr.impl.ConnectionStatusImpl
		 * @see gov.redhawk.ui.views.connmgr.impl.ConnMgrPackageImpl#getConnectionStatus()
		 * @generated
		 */
		EClass CONNECTION_STATUS = eINSTANCE.getConnectionStatus();

		/**
		 * The meta object literal for the '<em><b>Connection Record ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__CONNECTION_RECORD_ID = eINSTANCE.getConnectionStatus_ConnectionRecordID();

		/**
		 * The meta object literal for the '<em><b>Connection ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__CONNECTION_ID = eINSTANCE.getConnectionStatus_ConnectionID();

		/**
		 * The meta object literal for the '<em><b>Requester ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__REQUESTER_ID = eINSTANCE.getConnectionStatus_RequesterID();

		/**
		 * The meta object literal for the '<em><b>Connected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__CONNECTED = eINSTANCE.getConnectionStatus_Connected();

		/**
		 * The meta object literal for the '<em><b>Source IOR</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__SOURCE_IOR = eINSTANCE.getConnectionStatus_SourceIOR();

		/**
		 * The meta object literal for the '<em><b>Source Entity Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__SOURCE_ENTITY_NAME = eINSTANCE.getConnectionStatus_SourceEntityName();

		/**
		 * The meta object literal for the '<em><b>Source Port Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__SOURCE_PORT_NAME = eINSTANCE.getConnectionStatus_SourcePortName();

		/**
		 * The meta object literal for the '<em><b>Source Repo ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__SOURCE_REPO_ID = eINSTANCE.getConnectionStatus_SourceRepoID();

		/**
		 * The meta object literal for the '<em><b>Target IOR</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__TARGET_IOR = eINSTANCE.getConnectionStatus_TargetIOR();

		/**
		 * The meta object literal for the '<em><b>Target Entity Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__TARGET_ENTITY_NAME = eINSTANCE.getConnectionStatus_TargetEntityName();

		/**
		 * The meta object literal for the '<em><b>Target Port Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__TARGET_PORT_NAME = eINSTANCE.getConnectionStatus_TargetPortName();

		/**
		 * The meta object literal for the '<em><b>Target Repo ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_STATUS__TARGET_REPO_ID = eINSTANCE.getConnectionStatus_TargetRepoID();

	}

} // ConnMgrPackage
