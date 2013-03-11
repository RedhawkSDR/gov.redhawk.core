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
 * A representation of the model object '<em><b>Forward Dcl</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isLocal <em>Local</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getForwardDcl()
 * @model
 * @generated
 */
public interface ForwardDcl extends IdlTypeDcl {

	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' reference.
	 * @see #setImplementation(IdlInterfaceDcl)
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getForwardDcl_Implementation()
	 * @model
	 * @generated
	 */
	IdlInterfaceDcl getImplementation();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#getImplementation <em>Implementation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' reference.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(IdlInterfaceDcl value);

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
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getForwardDcl_Abstract()
	 * @model
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isAbstract <em>Abstract</em>}' attribute.
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
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getForwardDcl_Local()
	 * @model
	 * @generated
	 */
	boolean isLocal();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isLocal <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Local</em>' attribute.
	 * @see #isLocal()
	 * @generated
	 */
	void setLocal(boolean value);

} // ForwardDcl
