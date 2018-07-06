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

/**
 * <!-- begin-user-doc -->
 * A negotiated connection is retrieved from a {@link ExtendedCF.NegotiableUsesPort}.
 * @since 22.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaNegotiatedConnection#isAlive <em>Alive</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaNegotiatedConnection#getTransportType <em>Transport Type</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaNegotiatedConnection#getTransportInfo <em>Transport Info</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaNegotiatedConnection()
 * @model
 * @generated
 */
public interface ScaNegotiatedConnection extends ScaConnection {
	/**
	 * Returns the value of the '<em><b>Alive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alive</em>' attribute.
	 * @see #isSetAlive()
	 * @see #unsetAlive()
	 * @see #setAlive(boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaNegotiatedConnection_Alive()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isAlive();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#isAlive <em>Alive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alive</em>' attribute.
	 * @see #isSetAlive()
	 * @see #unsetAlive()
	 * @see #isAlive()
	 * @generated
	 */
	void setAlive(boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#isAlive <em>Alive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAlive()
	 * @see #isAlive()
	 * @see #setAlive(boolean)
	 * @generated
	 */
	void unsetAlive();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#isAlive <em>Alive</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Alive</em>' attribute is set.
	 * @see #unsetAlive()
	 * @see #isAlive()
	 * @see #setAlive(boolean)
	 * @generated
	 */
	boolean isSetAlive();

	/**
	 * Returns the value of the '<em><b>Transport Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transport Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transport Type</em>' attribute.
	 * @see #isSetTransportType()
	 * @see #unsetTransportType()
	 * @see #setTransportType(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaNegotiatedConnection_TransportType()
	 * @model unsettable="true"
	 * @generated
	 */
	String getTransportType();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#getTransportType <em>Transport
	 * Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transport Type</em>' attribute.
	 * @see #isSetTransportType()
	 * @see #unsetTransportType()
	 * @see #getTransportType()
	 * @generated
	 */
	void setTransportType(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#getTransportType <em>Transport
	 * Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransportType()
	 * @see #getTransportType()
	 * @see #setTransportType(String)
	 * @generated
	 */
	void unsetTransportType();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaNegotiatedConnection#getTransportType
	 * <em>Transport Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transport Type</em>' attribute is set.
	 * @see #unsetTransportType()
	 * @see #getTransportType()
	 * @see #setTransportType(String)
	 * @generated
	 */
	boolean isSetTransportType();

	/**
	 * Returns the value of the '<em><b>Transport Info</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaAbstractProperty}<code>&lt;?&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transport Info</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transport Info</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaNegotiatedConnection_TransportInfo()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ScaAbstractProperty< ? >> getTransportInfo();

} // ScaNegotiatedConnection
