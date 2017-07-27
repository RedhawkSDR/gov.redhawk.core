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

import mil.jpeojtrs.sca.prf.StructSequence;

import mil.jpeojtrs.sca.prf.StructSequenceRef;
import org.eclipse.emf.common.util.EList;

import CF.PropertySetOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Struct Sequence Property</b></em>'.
 * 
 * @since 11.0
 *        <!-- end-user-doc -->
 *
 *        <p>
 *        The following features are supported:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.ScaStructSequenceProperty#getStructs <em>Structs</em>}</li>
 *        </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaStructSequenceProperty()
 * @model superTypes="gov.redhawk.model.sca.ScaAbstractProperty&lt;mil.jpeojtrs.sca.prf.StructSequence&gt;
 *        mil.jpeojtrs.sca.cf.PropertySetOperations"
 *        extendedMetaData="name='ScaStructSequenceProperty' kind='empty'"
 * @generated
 */
public interface ScaStructSequenceProperty extends ScaAbstractProperty<StructSequence>, PropertySetOperations {

	/**
	 * Returns the value of the '<em><b>Structs</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaStructProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Structs</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Structs</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaStructSequenceProperty_Structs()
	 * @model containment="true" resolveProxies="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='structs'"
	 * @generated
	 */
	EList<ScaStructProperty> getStructs();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	ScaStructProperty createScaStructProperty();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	@Override
	StructSequenceRef createPropertyRef();

} // ScaStructSequenceProperty
