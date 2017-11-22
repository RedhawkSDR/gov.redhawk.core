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

import org.omg.CosEventChannelAdmin.EventChannel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Channel</b></em>'.
 * 
 * @since 19.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaEventChannel#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaEventChannel()
 * @model superTypes="gov.redhawk.model.sca.CorbaObjWrapper&lt;gov.redhawk.model.sca.EventChannel&gt;"
 * @generated
 */
public interface ScaEventChannel extends CorbaObjWrapper<EventChannel> {

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaEventChannel_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaEventChannel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);
} // ScaEventChannel
