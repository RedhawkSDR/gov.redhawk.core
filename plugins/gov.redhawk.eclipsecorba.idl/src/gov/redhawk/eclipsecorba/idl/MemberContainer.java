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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.MemberContainer#getMembers <em>Members</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getMemberContainer()
 * @model abstract="true"
 * @generated
 */
public interface MemberContainer extends EObject {

	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.Member}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see gov.redhawk.eclipsecorba.idl.IdlPackage#getMemberContainer_Members()
	 * @model containment="true"
	 * @generated
	 */
	EList<Member> getMembers();

} // MemberContainer
