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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IStatus Provider</b></em>'.
 * 
 * @since 14.0
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.IStatusProvider#getStatus <em>Status</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getIStatusProvider()
 * @model abstract="true"
 * @generated
 */
public interface IStatusProvider extends EObject {

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Status</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getIStatusProvider_Status()
	 * @model dataType="gov.redhawk.model.sca.IStatus" transient="true" changeable="false" volatile="true"
	 * derived="true"
	 * @generated
	 */
	IStatus getStatus();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model statusDataType="gov.redhawk.model.sca.IStatus"
	 * @generated
	 */
	void setStatus(EStructuralFeature feature, IStatus status);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void clearAllStatus();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="gov.redhawk.model.sca.IStatus"
	 * @generated
	 */
	IStatus getStatus(EStructuralFeature feature);

} // IStatusProvider
