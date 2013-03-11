/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import mil.jpeojtrs.sca.prf.Simple;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Property</b></em>'.
 * @since 9.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaSimpleProperty#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaSimpleProperty()
 * @model extendedMetaData="name='ScaSimpleProperty' kind='empty'"
 * @generated
 */
public interface ScaSimpleProperty extends ScaAbstractProperty<Simple> {

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(Object)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaSimpleProperty_Value()
	 * @model transient="true"
	 *        extendedMetaData="kind='attribute' name='currentValue'"
	 * @generated
	 */
	Object getValue();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaSimpleProperty#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Object value);

} // ScaSimpleProperty
