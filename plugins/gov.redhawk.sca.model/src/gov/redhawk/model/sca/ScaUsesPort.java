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

import mil.jpeojtrs.sca.scd.Uses;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.Port;
import CF.PortOperations;
import CF.PortPackage.InvalidPort;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uses Port</b></em>'.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}</li>
 *              </ul>
 *              </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaUsesPort()
 * @model superTypes=
 *        "gov.redhawk.model.sca.ScaPort<mil.jpeojtrs.sca.scd.Uses, mil.jpeojtrs.sca.cf.Port> mil.jpeojtrs.sca.cf.PortOperations"
 *        extendedMetaData="name='ScaUsesPort' kind='empty'"
 * @generated
 */
public interface ScaUsesPort extends ScaPort<Uses, Port>, PortOperations {

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaConnection}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaConnection#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see #isSetConnections()
	 * @see #unsetConnections()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaUsesPort_Connections()
	 * @see gov.redhawk.model.sca.ScaConnection#getPort
	 * @model opposite="port" containment="true" resolveProxies="true" unsettable="true" transient="true"
	 * @generated
	 */
	EList<ScaConnection> getConnections();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'
	 * containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetConnections()
	 * @see #getConnections()
	 * @generated
	 */
	void unsetConnections();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'
	 * containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Connections</em>' containment reference list is set.
	 * @see #unsetConnections()
	 * @see #getConnections()
	 * @generated
	 */
	boolean isSetConnections();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaConnection> fetchConnections(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model exceptions="mil.jpeojtrs.sca.cf.InvalidPort"
	 * @generated
	 */
	void disconnectPort(ScaConnection connection) throws InvalidPort;
} // ScaUsesPort
