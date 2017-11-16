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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Status</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionRecordID <em>Connection Record ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionID <em>Connection ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getRequesterID <em>Requester ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#isConnected <em>Connected</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceIOR <em>Source IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceEntityName <em>Source Entity Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourcePortName <em>Source Port Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceRepoID <em>Source Repo ID</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetIOR <em>Target IOR</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetEntityName <em>Target Entity Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetPortName <em>Target Port Name</em>}</li>
 * <li>{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetRepoID <em>Target Repo ID</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus()
 * @model
 * @generated
 */
public interface ConnectionStatus extends EObject {
	/**
	 * Returns the value of the '<em><b>Connection Record ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Record ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Record ID</em>' attribute.
	 * @see #setConnectionRecordID(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_ConnectionRecordID()
	 * @model
	 * @generated
	 */
	String getConnectionRecordID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionRecordID <em>Connection
	 * Record ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Record ID</em>' attribute.
	 * @see #getConnectionRecordID()
	 * @generated
	 */
	void setConnectionRecordID(String value);

	/**
	 * Returns the value of the '<em><b>Connection ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection ID</em>' attribute.
	 * @see #setConnectionID(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_ConnectionID()
	 * @model
	 * @generated
	 */
	String getConnectionID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getConnectionID <em>Connection
	 * ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection ID</em>' attribute.
	 * @see #getConnectionID()
	 * @generated
	 */
	void setConnectionID(String value);

	/**
	 * Returns the value of the '<em><b>Requester ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requester ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requester ID</em>' attribute.
	 * @see #setRequesterID(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_RequesterID()
	 * @model
	 * @generated
	 */
	String getRequesterID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getRequesterID <em>Requester
	 * ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requester ID</em>' attribute.
	 * @see #getRequesterID()
	 * @generated
	 */
	void setRequesterID(String value);

	/**
	 * Returns the value of the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected</em>' attribute.
	 * @see #setConnected(boolean)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_Connected()
	 * @model
	 * @generated
	 */
	boolean isConnected();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#isConnected <em>Connected</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connected</em>' attribute.
	 * @see #isConnected()
	 * @generated
	 */
	void setConnected(boolean value);

	/**
	 * Returns the value of the '<em><b>Source IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source IOR</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source IOR</em>' attribute.
	 * @see #setSourceIOR(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_SourceIOR()
	 * @model
	 * @generated
	 */
	String getSourceIOR();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceIOR <em>Source IOR</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source IOR</em>' attribute.
	 * @see #getSourceIOR()
	 * @generated
	 */
	void setSourceIOR(String value);

	/**
	 * Returns the value of the '<em><b>Source Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Entity Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Entity Name</em>' attribute.
	 * @see #setSourceEntityName(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_SourceEntityName()
	 * @model
	 * @generated
	 */
	String getSourceEntityName();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceEntityName <em>Source Entity
	 * Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Entity Name</em>' attribute.
	 * @see #getSourceEntityName()
	 * @generated
	 */
	void setSourceEntityName(String value);

	/**
	 * Returns the value of the '<em><b>Source Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Port Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Port Name</em>' attribute.
	 * @see #setSourcePortName(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_SourcePortName()
	 * @model
	 * @generated
	 */
	String getSourcePortName();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourcePortName <em>Source Port
	 * Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Port Name</em>' attribute.
	 * @see #getSourcePortName()
	 * @generated
	 */
	void setSourcePortName(String value);

	/**
	 * Returns the value of the '<em><b>Source Repo ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Repo ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Repo ID</em>' attribute.
	 * @see #setSourceRepoID(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_SourceRepoID()
	 * @model
	 * @generated
	 */
	String getSourceRepoID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getSourceRepoID <em>Source Repo
	 * ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Repo ID</em>' attribute.
	 * @see #getSourceRepoID()
	 * @generated
	 */
	void setSourceRepoID(String value);

	/**
	 * Returns the value of the '<em><b>Target IOR</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target IOR</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target IOR</em>' attribute.
	 * @see #setTargetIOR(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_TargetIOR()
	 * @model
	 * @generated
	 */
	String getTargetIOR();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetIOR <em>Target IOR</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target IOR</em>' attribute.
	 * @see #getTargetIOR()
	 * @generated
	 */
	void setTargetIOR(String value);

	/**
	 * Returns the value of the '<em><b>Target Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Entity Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Entity Name</em>' attribute.
	 * @see #setTargetEntityName(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_TargetEntityName()
	 * @model
	 * @generated
	 */
	String getTargetEntityName();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetEntityName <em>Target Entity
	 * Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Entity Name</em>' attribute.
	 * @see #getTargetEntityName()
	 * @generated
	 */
	void setTargetEntityName(String value);

	/**
	 * Returns the value of the '<em><b>Target Port Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Port Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Port Name</em>' attribute.
	 * @see #setTargetPortName(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_TargetPortName()
	 * @model
	 * @generated
	 */
	String getTargetPortName();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetPortName <em>Target Port
	 * Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Port Name</em>' attribute.
	 * @see #getTargetPortName()
	 * @generated
	 */
	void setTargetPortName(String value);

	/**
	 * Returns the value of the '<em><b>Target Repo ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Repo ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Repo ID</em>' attribute.
	 * @see #setTargetRepoID(String)
	 * @see gov.redhawk.ui.views.connmgr.ConnMgrPackage#getConnectionStatus_TargetRepoID()
	 * @model
	 * @generated
	 */
	String getTargetRepoID();

	/**
	 * Sets the value of the '{@link gov.redhawk.ui.views.connmgr.ConnectionStatus#getTargetRepoID <em>Target Repo
	 * ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Repo ID</em>' attribute.
	 * @see #getTargetRepoID()
	 * @generated
	 */
	void setTargetRepoID(String value);

} // ConnectionStatus
