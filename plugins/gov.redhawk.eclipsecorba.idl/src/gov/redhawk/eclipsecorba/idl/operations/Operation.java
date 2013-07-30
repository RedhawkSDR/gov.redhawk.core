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
package gov.redhawk.eclipsecorba.idl.operations;

import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Export;
import gov.redhawk.eclipsecorba.idl.IdlException;
import gov.redhawk.eclipsecorba.idl.expressions.StringLiteral;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.Operation#isOneway <em>Oneway</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getParameters <em>Parameters</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Export, Commentable {

	/**
	 * Returns the value of the '<em><b>Oneway</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Oneway</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Oneway</em>' attribute.
	 * @see #setOneway(boolean)
	 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsPackage#getOperation_Oneway()
	 * @model
	 * @generated
	 */
	boolean isOneway();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.operations.Operation#isOneway <em>Oneway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Oneway</em>' attribute.
	 * @see #isOneway()
	 * @generated
	 */
	void setOneway(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.operations.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsPackage#getOperation_Parameters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Parameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Exceptions</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.IdlException}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exceptions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exceptions</em>' reference list.
	 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsPackage#getOperation_Exceptions()
	 * @model
	 * @generated
	 */
	EList<IdlException> getExceptions();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.eclipsecorba.idl.expressions.StringLiteral}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' containment reference list.
	 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsPackage#getOperation_Context()
	 * @model containment="true"
	 * @generated
	 */
	EList<StringLiteral> getContext();

} // Operation
