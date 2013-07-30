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

import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;
import gov.redhawk.eclipsecorba.idl.MemberContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Struct Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDeclaration <em>Forward Declaration</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDcl <em>Forward Dcl</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getStructType()
 * @model
 * @generated
 */
public interface StructType extends IdlTypeDcl, MemberContainer {

	/**
	 * Returns the value of the '<em><b>Forward Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Declaration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Declaration</em>' containment reference.
	 * @see #setForwardDeclaration(ForwardDcl)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getStructType_ForwardDeclaration()
	 * @model containment="true"
	 * @generated
	 */
	ForwardDcl getForwardDeclaration();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDeclaration <em>Forward Declaration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Declaration</em>' containment reference.
	 * @see #getForwardDeclaration()
	 * @generated
	 */
	void setForwardDeclaration(ForwardDcl value);

	/**
	 * Returns the value of the '<em><b>Forward Dcl</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Dcl</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Dcl</em>' reference.
	 * @see #setForwardDcl(StructForwardDcl)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getStructType_ForwardDcl()
	 * @see gov.redhawk.eclipsecorba.idl.types.StructForwardDcl#getImplementation
	 * @model opposite="implementation"
	 * @generated
	 */
	StructForwardDcl getForwardDcl();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDcl <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Dcl</em>' reference.
	 * @see #getForwardDcl()
	 * @generated
	 */
	void setForwardDcl(StructForwardDcl value);

} // StructType
