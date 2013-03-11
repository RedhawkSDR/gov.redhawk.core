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

import gov.redhawk.eclipsecorba.idl.expressions.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fixed Pt Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr1 <em>Expr1</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr2 <em>Expr2</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getFixedPtType()
 * @model
 * @generated
 */
public interface FixedPtType extends TemplateType {

	/**
	 * Returns the value of the '<em><b>Expr1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr1</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr1</em>' containment reference.
	 * @see #setExpr1(Expression)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getFixedPtType_Expr1()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpr1();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr1 <em>Expr1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr1</em>' containment reference.
	 * @see #getExpr1()
	 * @generated
	 */
	void setExpr1(Expression value);

	/**
	 * Returns the value of the '<em><b>Expr2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr2</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr2</em>' containment reference.
	 * @see #setExpr2(Expression)
	 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage#getFixedPtType_Expr2()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpr2();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr2 <em>Expr2</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr2</em>' containment reference.
	 * @see #getExpr2()
	 * @generated
	 */
	void setExpr2(Expression value);

} // FixedPtType
