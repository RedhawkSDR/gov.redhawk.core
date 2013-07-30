/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.types;

import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl <em>Forward Dcl</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getIdlSwitch <em>Idl Switch</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getUnionType()
 * @model
 * @generated
 */
public interface UnionType extends IdlTypeDcl {

	/**
	 * Returns the value of the '<em><b>Forward Dcl</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Dcl</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Dcl</em>' reference.
	 * @see #setForwardDcl(UnionForwardDcl)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getUnionType_ForwardDcl()
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation
	 * @model opposite="implementation"
	 * @generated
	 */
	UnionForwardDcl getForwardDcl();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Dcl</em>' reference.
	 * @see #getForwardDcl()
	 * @generated
	 */
	void setForwardDcl(UnionForwardDcl value);

	/**
	 * Returns the value of the '<em><b>Idl Switch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idl Switch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idl Switch</em>' containment reference.
	 * @see #setIdlSwitch(Switch)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getUnionType_IdlSwitch()
	 * @model containment="true"
	 * @generated
	 */
	Switch getIdlSwitch();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getIdlSwitch <em>Idl Switch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Idl Switch</em>' containment reference.
	 * @see #getIdlSwitch()
	 * @generated
	 */
	void setIdlSwitch(Switch value);

} // UnionType
