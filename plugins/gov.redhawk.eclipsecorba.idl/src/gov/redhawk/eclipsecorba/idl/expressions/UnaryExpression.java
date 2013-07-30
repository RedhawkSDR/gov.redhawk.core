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
package gov.redhawk.eclipsecorba.idl.expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getExpr <em>Expr</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getUnaryExpression()
 * @model
 * @generated
 */
public interface UnaryExpression extends Expression {

	/**
	 * Returns the value of the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr</em>' containment reference.
	 * @see #setExpr(Expression)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getUnaryExpression_Expr()
	 * @model containment="true"
	 * @generated
	 */
	Expression getExpr();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getExpr <em>Expr</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr</em>' containment reference.
	 * @see #getExpr()
	 * @generated
	 */
	void setExpr(Expression value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link gov.redhawk.eclipsecorba.idl.expressions.UnaryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryType
	 * @see #setType(UnaryType)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getUnaryExpression_Type()
	 * @model
	 * @generated
	 */
	UnaryType getType();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryType
	 * @see #getType()
	 * @generated
	 */
	void setType(UnaryType value);

} // UnaryExpression
