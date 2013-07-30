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
 * A representation of the model object '<em><b>Interface Dcl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getInheritedInterfaces <em>Inherited Interfaces</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isLocal <em>Local</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getForwardDcl <em>Forward Dcl</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdlInterfaceDcl()
 * @model
 * @generated
 */
public interface IdlInterfaceDcl extends IdlTypeDcl, DefinitionContainer, ExportContainer {

	/**
	 * Returns the value of the '<em><b>Inherited Interfaces</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inherited Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inherited Interfaces</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdlInterfaceDcl_InheritedInterfaces()
	 * @model
	 * @generated
	 */
	EList<IdlInterfaceDcl> getInheritedInterfaces();

	/**
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdlInterfaceDcl_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Local</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Local</em>' attribute.
	 * @see #setLocal(boolean)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdlInterfaceDcl_Local()
	 * @model
	 * @generated
	 */
	boolean isLocal();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #isLocal()
	 * @generated
	 */
	void setLocal(boolean value);

	/**
	 * Returns the value of the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Forward Dcl</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Forward Dcl</em>' reference.
	 * @see #setForwardDcl(ForwardDcl)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getIdlInterfaceDcl_ForwardDcl()
	 * @model
	 * @generated
	 */
	ForwardDcl getForwardDcl();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getForwardDcl <em>Forward Dcl</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Forward Dcl</em>' reference.
	 * @see #getForwardDcl()
	 * @generated
	 */
	void setForwardDcl(ForwardDcl value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines if the specified <code>IdlTypeDcl</code> is assignment-compatible
	 * with the object represented by this <code>IdlInterfaceDcl</code>.  This method is
	 * the dynamic equivalent of the Java language <code>instanceof</code>
	 * operator. The method returns <code>true</code> if the specified
	 * <code>IdlTypeDcl</code> argument is non-null and can be cast to the
	 * reference type represented by this <code>IdlInterfaceDcl</code> object without
	 * raising a <code>ClassCastException.</code> It returns <code>false</code>
	 * otherwise.
	 * 
	 * <p> Specifically, if this <code>IdlInterfaceDcl</code> this method returns <code>true</code> if the
	 * interface or any superinterface of the specified <code>IdlTypeDcl</code> argument
	 * implements this interface; it returns <code>false</code> otherwise.
	 * 
	 * @return  true if <code>obj</code> is an instance of this class
	 * 
	 * @since 2.0
	 * @param obj obj the object to check
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean isInstance(IdlTypeDcl obj);

} // IdlInterfaceDcl
