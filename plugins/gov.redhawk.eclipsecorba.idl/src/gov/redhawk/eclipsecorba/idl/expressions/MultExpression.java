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
package gov.redhawk.eclipsecorba.idl.expressions;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mult Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getLeft <em>Left</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getRight <em>Right</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getMultExpression()
 * @model
 * @generated
 */
public interface MultExpression extends Expression {

	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(Expression)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getMultExpression_Left()
	 * @model containment="true"
	 * @generated
	 */
	Expression getLeft();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Expression value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(Expression)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getMultExpression_Right()
	 * @model containment="true"
	 * @generated
	 */
	Expression getRight();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Expression value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link gov.redhawk.eclipsecorba.idl.expressions.MultiType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultiType
	 * @see #setType(MultiType)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getMultExpression_Type()
	 * @model
	 * @generated
	 */
	MultiType getType();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultiType
	 * @see #getType()
	 * @generated
	 */
	void setType(MultiType value);

} // MultExpression
