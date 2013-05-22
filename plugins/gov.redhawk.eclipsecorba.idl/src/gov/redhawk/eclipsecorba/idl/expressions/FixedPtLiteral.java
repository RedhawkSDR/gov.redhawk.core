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
 * A representation of the model object '<em><b>Fixed Pt Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getIntegerPart <em>Integer Part</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getDecimalPart <em>Decimal Part</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getFixedPtLiteral()
 * @model
 * @generated
 */
public interface FixedPtLiteral extends Expression {

	/**
	 * Returns the value of the '<em><b>Integer Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Integer Part</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Integer Part</em>' attribute.
	 * @see #setIntegerPart(int)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getFixedPtLiteral_IntegerPart()
	 * @model
	 * @generated
	 */
	int getIntegerPart();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getIntegerPart <em>Integer Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Integer Part</em>' attribute.
	 * @see #getIntegerPart()
	 * @generated
	 */
	void setIntegerPart(int value);

	/**
	 * Returns the value of the '<em><b>Decimal Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decimal Part</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decimal Part</em>' attribute.
	 * @see #setDecimalPart(int)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getFixedPtLiteral_DecimalPart()
	 * @model
	 * @generated
	 */
	int getDecimalPart();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getDecimalPart <em>Decimal Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Decimal Part</em>' attribute.
	 * @see #getDecimalPart()
	 * @generated
	 */
	void setDecimalPart(int value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#getFixedPtLiteral_Value()
	 * @model transient="true" volatile="true" derived="true"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // FixedPtLiteral
