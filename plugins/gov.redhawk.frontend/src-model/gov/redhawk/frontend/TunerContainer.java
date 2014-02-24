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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuner Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.frontend.TunerContainer#getTunerStatus <em>Tuner Status</em>}</li>
 * <li>{@link gov.redhawk.frontend.TunerContainer#getUnallocatedContainer <em>Unallocated Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer()
 * @model
 * @generated
 */
public interface TunerContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Tuner Status</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.frontend.TunerStatus}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.TunerStatus#getTunerContainer <em>Tuner
	 * Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tuner Status</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tuner Status</em>' containment reference list.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_TunerStatus()
	 * @see gov.redhawk.frontend.TunerStatus#getTunerContainer
	 * @model opposite="tunerContainer" containment="true"
	 * @generated
	 */
	EList<TunerStatus> getTunerStatus();

	/**
	 * Returns the value of the '<em><b>Unallocated Container</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.frontend.UnallocatedTunerContainer}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer
	 * <em>Tuner Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unallocated Container</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unallocated Container</em>' containment reference list.
	 * @see gov.redhawk.frontend.FrontendPackage#getTunerContainer_UnallocatedContainer()
	 * @see gov.redhawk.frontend.UnallocatedTunerContainer#getTunerContainer
	 * @model opposite="tunerContainer" containment="true"
	 * @generated
	 */
	EList<UnallocatedTunerContainer> getUnallocatedContainer();

} // TunerContainer
