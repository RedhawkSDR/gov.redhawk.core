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

import mil.jpeojtrs.sca.prf.Struct;

import org.eclipse.emf.common.util.EList;

import CF.PropertySetOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Struct Property</b></em>'.
 * 
 * @since 9.0
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSimples <em>Simples</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaStructProperty#getSequences <em>Sequences</em>}</li>
 *              </ul>
 *              </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaStructProperty()
 * @model superTypes=
 *        "gov.redhawk.model.sca.ScaAbstractProperty<mil.jpeojtrs.sca.prf.Struct> mil.jpeojtrs.sca.cf.PropertySetOperations"
 *        extendedMetaData="name='ScaStructProperty' kind='empty'"
 * @generated
 */
public interface ScaStructProperty extends ScaAbstractProperty<Struct>, PropertySetOperations {

	/**
	 * Returns the value of the '<em><b>Simples</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaSimpleProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Simples</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Simples</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaStructProperty_Simples()
	 * @model containment="true" resolveProxies="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='simples'"
	 * @generated
	 */
	EList<ScaSimpleProperty> getSimples();

	/**
	 * Returns the value of the '<em><b>Sequences</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaSimpleSequenceProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequences</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @return the value of the '<em>Sequences</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaStructProperty_Sequences()
	 * @model containment="true" resolveProxies="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='sequences'"
	 * @generated
	 */
	EList<ScaSimpleSequenceProperty> getSequences();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	ScaSimpleProperty getSimple(String id);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaAbstractProperty< ? > getField(String id);

} // ScaStructProperty
