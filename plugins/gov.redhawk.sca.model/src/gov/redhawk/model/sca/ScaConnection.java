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
package gov.redhawk.model.sca;

import org.eclipse.emf.ecore.EObject;

import ExtendedCF.UsesConnection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * @since 14.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaConnection#getData <em>Data</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaConnection#getId <em>Id</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaConnection#getPort <em>Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection()
 * @model
 * @generated
 */
public interface ScaConnection extends EObject {

	/**
	 * Returns the value of the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data</em>' attribute.
	 * @see #setData(UsesConnection)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection_Data()
	 * @model dataType="mil.jpeojtrs.sca.cf.extended.UsesConnection" transient="true"
	 * @generated
	 */
	UsesConnection getData();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaConnection#getData <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data</em>' attribute.
	 * @see #getData()
	 * @generated
	 */
	void setData(UsesConnection value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection_Id()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getId();

	/**
	 * Returns the value of the '<em><b>Port</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' container reference.
	 * @see #setPort(ScaUsesPort)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection_Port()
	 * @see gov.redhawk.model.sca.ScaUsesPort#getConnections
	 * @model opposite="connections" required="true"
	 * @generated
	 */
	ScaUsesPort getPort();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaConnection#getPort <em>Port</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Port</em>' container reference.
	 * @see #getPort()
	 * @generated
	 */
	void setPort(ScaUsesPort value);

} // ScaConnection
