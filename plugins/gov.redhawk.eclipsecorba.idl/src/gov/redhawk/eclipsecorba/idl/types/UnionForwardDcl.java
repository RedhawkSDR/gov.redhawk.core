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
package gov.redhawk.eclipsecorba.idl.types;

import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Forward Dcl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation <em>Implementation</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getUnionForwardDcl()
 * @model
 * @generated
 */
public interface UnionForwardDcl extends IdlTypeDcl {

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' reference.
	 * @see #setImplementation(UnionType)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getUnionForwardDcl_Implementation()
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl
	 * @model opposite="forwardDcl"
	 * @generated
	 */
	UnionType getImplementation();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(UnionType value);

} // UnionForwardDcl
