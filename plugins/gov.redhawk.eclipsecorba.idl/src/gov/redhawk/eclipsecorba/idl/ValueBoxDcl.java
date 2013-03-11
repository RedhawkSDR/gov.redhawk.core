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
package gov.redhawk.eclipsecorba.idl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Box Dcl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ValueBoxDcl#getTypeSpec <em>Type Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueBoxDcl()
 * @model
 * @generated
 */
public interface ValueBoxDcl extends ValueTypeDcl {

	/**
	 * Returns the value of the '<em><b>Type Spec</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Spec</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Spec</em>' reference.
	 * @see #setTypeSpec(IdlType)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueBoxDcl_TypeSpec()
	 * @model
	 * @generated
	 */
	IdlType getTypeSpec();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ValueBoxDcl#getTypeSpec <em>Type Spec</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Spec</em>' reference.
	 * @see #getTypeSpec()
	 * @generated
	 */
	void setTypeSpec(IdlType value);

} // ValueBoxDcl
