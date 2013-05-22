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
package gov.redhawk.eclipsecorba.idl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Dcl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getInheritedValues <em>Inherited Values</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getSupportsInterface <em>Supports Interface</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ValueDcl#isCustom <em>Custom</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getForwardDcl <em>Forward Dcl</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueDcl()
 * @model
 * @generated
 */
public interface ValueDcl extends ValueTypeDcl, ExportContainer, DefinitionContainer {

	/**
	 * Returns the value of the '<em><b>Inherited Values</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.ValueDcl}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherited Values</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherited Values</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueDcl_InheritedValues()
	 * @model
	 * @generated
	 */
	EList<ValueDcl> getInheritedValues();

	/**
	 * Returns the value of the '<em><b>Supports Interface</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Supports Interface</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Supports Interface</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueDcl_SupportsInterface()
	 * @model
	 * @generated
	 */
	EList<IdlInterfaceDcl> getSupportsInterface();

	/**
	 * Returns the value of the '<em><b>Custom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Custom</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Custom</em>' attribute.
	 * @see #setCustom(boolean)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueDcl_Custom()
	 * @model
	 * @generated
	 */
	boolean isCustom();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#isCustom <em>Custom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Custom</em>' attribute.
	 * @see #isCustom()
	 * @generated
	 */
	void setCustom(boolean value);

	/**
	 * Returns the value of the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Dcl</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Dcl</em>' reference.
	 * @see #setForwardDcl(ValueForwardDcl)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getValueDcl_ForwardDcl()
	 * @model
	 * @generated
	 */
	ValueForwardDcl getForwardDcl();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getForwardDcl <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Dcl</em>' reference.
	 * @see #getForwardDcl()
	 * @generated
	 */
	void setForwardDcl(ValueForwardDcl value);

} // ValueDcl
