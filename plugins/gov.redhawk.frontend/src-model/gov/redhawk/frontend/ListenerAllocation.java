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
package gov.redhawk.frontend;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Listener Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.frontend.ListenerAllocation#getTunerStatus <em>Tuner Status</em>}</li>
 * <li>{@link gov.redhawk.frontend.ListenerAllocation#getListenerID <em>Listener ID</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getListenerAllocation()
 * @model
 * @generated
 */
public interface ListenerAllocation extends EObject {
	/**
	 * Returns the value of the '<em><b>Tuner Status</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerStatus#getListenerAllocations
	 * <em>Listener Allocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Status</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Status</em>' container reference.
	 * @see #setTunerStatus(TunerStatus)
	 * @see gov.redhawk.frontend.FrontendPackage#getListenerAllocation_TunerStatus()
	 * @see gov.redhawk.frontend.TunerStatus#getListenerAllocations
	 * @model opposite="listenerAllocations" transient="false"
	 * @generated
	 */
	TunerStatus getTunerStatus();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.ListenerAllocation#getTunerStatus <em>Tuner Status</em>}'
	 * container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Status</em>' container reference.
	 * @see #getTunerStatus()
	 * @generated
	 */
	void setTunerStatus(TunerStatus value);

	/**
	 * Returns the value of the '<em><b>Listener ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listener ID</em>' attribute.
	 * @see #setListenerID(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getListenerAllocation_ListenerID()
	 * @model unique="false"
	 * @generated
	 */
	String getListenerID();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.ListenerAllocation#getListenerID <em>Listener ID</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listener ID</em>' attribute.
	 * @see #getListenerID()
	 * @generated
	 */
	void setListenerID(String value);

} // ListenerAllocation
