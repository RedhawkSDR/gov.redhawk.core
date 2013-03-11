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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage
 * @generated
 */
public interface ExpressionsFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionsFactory eINSTANCE = gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Const Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Const Expression</em>'.
	 * @generated
	 */
	ConstExpression createConstExpression();

	/**
	 * Returns a new object of class '<em>Or Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Or Expression</em>'.
	 * @generated
	 */
	OrExpression createOrExpression();

	/**
	 * Returns a new object of class '<em>XOr Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XOr Expression</em>'.
	 * @generated
	 */
	XOrExpression createXOrExpression();

	/**
	 * Returns a new object of class '<em>And Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>And Expression</em>'.
	 * @generated
	 */
	AndExpression createAndExpression();

	/**
	 * Returns a new object of class '<em>Shift Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Shift Expression</em>'.
	 * @generated
	 */
	ShiftExpression createShiftExpression();

	/**
	 * Returns a new object of class '<em>Add Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Add Expression</em>'.
	 * @generated
	 */
	AddExpression createAddExpression();

	/**
	 * Returns a new object of class '<em>Mult Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mult Expression</em>'.
	 * @generated
	 */
	MultExpression createMultExpression();

	/**
	 * Returns a new object of class '<em>Unary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unary Expression</em>'.
	 * @generated
	 */
	UnaryExpression createUnaryExpression();

	/**
	 * Returns a new object of class '<em>Scope Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope Literal</em>'.
	 * @generated
	 */
	ScopeLiteral createScopeLiteral();

	/**
	 * Returns a new object of class '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Integer Literal</em>'.
	 * @generated
	 */
	IntegerLiteral createIntegerLiteral();

	/**
	 * Returns a new object of class '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>String Literal</em>'.
	 * @generated
	 */
	StringLiteral createStringLiteral();

	/**
	 * Returns a new object of class '<em>Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Character Literal</em>'.
	 * @generated
	 */
	CharacterLiteral createCharacterLiteral();

	/**
	 * Returns a new object of class '<em>Floating Point Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Floating Point Literal</em>'.
	 * @generated
	 */
	FloatingPointLiteral createFloatingPointLiteral();

	/**
	 * Returns a new object of class '<em>Double Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Double Literal</em>'.
	 * @generated
	 */
	DoubleLiteral createDoubleLiteral();

	/**
	 * Returns a new object of class '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Boolean Literal</em>'.
	 * @generated
	 */
	BooleanLiteral createBooleanLiteral();

	/**
	 * Returns a new object of class '<em>Fixed Pt Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fixed Pt Literal</em>'.
	 * @generated
	 */
	FixedPtLiteral createFixedPtLiteral();

	/**
	 * Returns a new object of class '<em>Wide String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wide String Literal</em>'.
	 * @generated
	 */
	WideStringLiteral createWideStringLiteral();

	/**
	 * Returns a new object of class '<em>Wide Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wide Character Literal</em>'.
	 * @generated
	 */
	WideCharacterLiteral createWideCharacterLiteral();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExpressionsPackage getExpressionsPackage();

} //ExpressionsFactory
