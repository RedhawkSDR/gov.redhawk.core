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
 * A representation of the model object '<em><b>IDisposable</b></em>'.
 * 
 * @since 9.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.IDisposable#isDisposed <em>Disposed</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getIDisposable()
 * @model interface="true" abstract="true"
 * extendedMetaData="name='IDisposable' kind='empty'"
 * @generated
 */
public interface IDisposable extends EObject {

	/**
	 * Returns the value of the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disposed</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disposed</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getIDisposable_Disposed()
	 * @model transient="true" changeable="false"
	 * extendedMetaData="kind='attribute' name='disposed'"
	 * @generated
	 */
	boolean isDisposed();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void dispose();

} // IDisposable
