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
package gov.redhawk.eclipsecorba.idl.expressions;

import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getScopeLiteral()
 * @model
 * @generated
 */
public interface ScopeLiteral extends Expression {

	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(IdlTypeDcl)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getScopeLiteral_Value()
	 * @model
	 * @generated
	 */
	IdlTypeDcl getValue();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(IdlTypeDcl value);

} // ScopeLiteral
