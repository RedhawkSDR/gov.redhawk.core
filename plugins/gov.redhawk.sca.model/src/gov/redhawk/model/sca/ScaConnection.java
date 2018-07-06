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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * 
 * @since 14.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaConnection#getId <em>Id</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaConnection#getTargetPort <em>Target Port</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaConnection#getPort <em>Port</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection()
 * @model
 * @generated
 */
public interface ScaConnection extends EObject {

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p/>
	 * The connection ID.
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection_Id()
	 * @model transient="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaConnection#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 22.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Target Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p/>
	 * The provides port on the other end of this connection.
	 * @since 22.0
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Port</em>' attribute.
	 * @see #setTargetPort(org.omg.CORBA.Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaConnection_TargetPort()
	 * @model dataType="gov.redhawk.model.sca.Object"
	 * @generated
	 */
	org.omg.CORBA.Object getTargetPort();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaConnection#getTargetPort <em>Target Port</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 22.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Port</em>' attribute.
	 * @see #getTargetPort()
	 * @generated
	 */
	void setTargetPort(org.omg.CORBA.Object value);

	/**
	 * Returns the value of the '<em><b>Port</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections
	 * <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <p/>
	 * Returns the uses port for this connection.
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
