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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * Represents the details of a {@link ExtendedCF.TransportInfo}.
 * @since 21.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaTransport#getTransportType <em>Transport Type</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaTransport#getTransportProperties <em>Transport Properties</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaTransport()
 * @model
 * @generated
 */
public interface ScaTransport extends EObject {
	/**
	 * Returns the value of the '<em><b>Transport Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transport Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transport Type</em>' attribute.
	 * @see #setTransportType(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaTransport_TransportType()
	 * @model
	 * @generated
	 */
	String getTransportType();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaTransport#getTransportType <em>Transport Type</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Type</em>' attribute.
	 * @see #getTransportType()
	 * @generated
	 */
	void setTransportType(String value);

	/**
	 * Returns the value of the '<em><b>Transport Properties</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaAbstractProperty}<code>&lt;?&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transport Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transport Properties</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaTransport_TransportProperties()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ScaAbstractProperty< ? >> getTransportProperties();

} // ScaTransport
