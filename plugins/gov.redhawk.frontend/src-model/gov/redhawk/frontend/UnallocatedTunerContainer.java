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
 * A representation of the model object '<em><b>Unallocated Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer <em>Tuner Container</em>}</li>
 * <li>{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerType <em>Tuner Type</em>}</li>
 * <li>{@link gov.redhawk.frontend.UnallocatedTunerContainer#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 * 
 * @see gov.redhawk.frontend.FrontendPackage#getUnallocatedTunerContainer()
 * @model
 * @generated
 */
public interface UnallocatedTunerContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Tuner Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerContainer#getUnallocatedContainer
	 * <em>Unallocated Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Container</em>' container reference.
	 * @see #setTunerContainer(TunerContainer)
	 * @see gov.redhawk.frontend.FrontendPackage#getUnallocatedTunerContainer_TunerContainer()
	 * @see gov.redhawk.frontend.TunerContainer#getUnallocatedContainer
	 * @model opposite="unallocatedContainer" transient="false"
	 * @generated
	 */
	TunerContainer getTunerContainer();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer <em>Tuner
	 * Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Container</em>' container reference.
	 * @see #getTunerContainer()
	 * @generated
	 */
	void setTunerContainer(TunerContainer value);

	/**
	 * Returns the value of the '<em><b>Tuner Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Type</em>' attribute.
	 * @see #setTunerType(String)
	 * @see gov.redhawk.frontend.FrontendPackage#getUnallocatedTunerContainer_TunerType()
	 * @model unique="false"
	 * @generated
	 */
	String getTunerType();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerType <em>Tuner Type</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tuner Type</em>' attribute.
	 * @see #getTunerType()
	 * @generated
	 */
	void setTunerType(String value);

	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see gov.redhawk.frontend.FrontendPackage#getUnallocatedTunerContainer_Count()
	 * @model unique="false"
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

} // UnallocatedTunerContainer
