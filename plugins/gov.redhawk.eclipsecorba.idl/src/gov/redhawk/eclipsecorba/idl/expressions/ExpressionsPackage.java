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

import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsFactory
 * @model kind="package"
 * @generated
 */
public interface ExpressionsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "expressions";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///gov/redhawk/eclipsecorba/idl.expressions.ecore";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "expressions";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionsPackage eINSTANCE = gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 0;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__START_LINE = IdlPackage.FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__START_COLUMN = IdlPackage.FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__END_LINE = IdlPackage.FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__END_COLUMN = IdlPackage.FILE_REGION__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = IdlPackage.FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ConstExpressionImpl <em>Const Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ConstExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getConstExpression()
	 * @generated
	 */
	int CONST_EXPRESSION = 1;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION__EXPRESSION = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Const Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONST_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.OrExpressionImpl <em>Or Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.OrExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getOrExpression()
	 * @generated
	 */
	int OR_EXPRESSION = 2;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Or Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.XOrExpressionImpl <em>XOr Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.XOrExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getXOrExpression()
	 * @generated
	 */
	int XOR_EXPRESSION = 3;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>XOr Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XOR_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.AndExpressionImpl <em>And Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.AndExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAndExpression()
	 * @generated
	 */
	int AND_EXPRESSION = 4;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>And Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AND_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ShiftExpressionImpl <em>Shift Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ShiftExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getShiftExpression()
	 * @generated
	 */
	int SHIFT_EXPRESSION = 5;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Shift Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHIFT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.AddExpressionImpl <em>Add Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.AddExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAddExpression()
	 * @generated
	 */
	int ADD_EXPRESSION = 6;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Add Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADD_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.MultExpressionImpl <em>Mult Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.MultExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getMultExpression()
	 * @generated
	 */
	int MULT_EXPRESSION = 7;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__LEFT = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__RIGHT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Mult Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULT_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.UnaryExpressionImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getUnaryExpression()
	 * @generated
	 */
	int UNARY_EXPRESSION = 8;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__EXPR = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION__TYPE = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Unary Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNARY_EXPRESSION_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ScopeLiteralImpl <em>Scope Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ScopeLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getScopeLiteral()
	 * @generated
	 */
	int SCOPE_LITERAL = 9;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Scope Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCOPE_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.IntegerLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getIntegerLiteral()
	 * @generated
	 */
	int INTEGER_LITERAL = 10;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Integer Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.StringLiteralImpl <em>String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.StringLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getStringLiteral()
	 * @generated
	 */
	int STRING_LITERAL = 11;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.CharacterLiteralImpl <em>Character Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.CharacterLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getCharacterLiteral()
	 * @generated
	 */
	int CHARACTER_LITERAL = 12;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHARACTER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FloatingPointLiteralImpl <em>Floating Point Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.FloatingPointLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getFloatingPointLiteral()
	 * @generated
	 */
	int FLOATING_POINT_LITERAL = 13;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Floating Point Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOATING_POINT_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.DoubleLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getDoubleLiteral()
	 * @generated
	 */
	int DOUBLE_LITERAL = 14;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Double Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.BooleanLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getBooleanLiteral()
	 * @generated
	 */
	int BOOLEAN_LITERAL = 15;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Boolean Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl <em>Fixed Pt Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getFixedPtLiteral()
	 * @generated
	 */
	int FIXED_PT_LITERAL = 16;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Integer Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__INTEGER_PART = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Decimal Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__DECIMAL_PART = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Fixed Pt Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.WideStringLiteralImpl <em>Wide String Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.WideStringLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getWideStringLiteral()
	 * @generated
	 */
	int WIDE_STRING_LITERAL = 17;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Wide String Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_STRING_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.WideCharacterLiteralImpl <em>Wide Character Literal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.WideCharacterLiteralImpl
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getWideCharacterLiteral()
	 * @generated
	 */
	int WIDE_CHARACTER_LITERAL = 18;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL__START_LINE = EXPRESSION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL__START_COLUMN = EXPRESSION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL__END_LINE = EXPRESSION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL__END_COLUMN = EXPRESSION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL__VALUE = EXPRESSION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Wide Character Literal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WIDE_CHARACTER_LITERAL_FEATURE_COUNT = EXPRESSION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftType <em>Shift Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftType
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getShiftType()
	 * @generated
	 */
	int SHIFT_TYPE = 19;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.AddType <em>Add Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddType
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAddType()
	 * @generated
	 */
	int ADD_TYPE = 20;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.MultiType <em>Multi Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultiType
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getMultiType()
	 * @generated
	 */
	int MULTI_TYPE = 21;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryType <em>Unary Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryType
	 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getUnaryType()
	 * @generated
	 */
	int UNARY_TYPE = 22;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.Expression
	 * @generated
	 */
	EClass getExpression();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.ConstExpression <em>Const Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Const Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ConstExpression
	 * @generated
	 */
	EClass getConstExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.ConstExpression#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ConstExpression#getExpression()
	 * @see #getConstExpression()
	 * @generated
	 */
	EReference getConstExpression_Expression();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.OrExpression <em>Or Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Or Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.OrExpression
	 * @generated
	 */
	EClass getOrExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.OrExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.OrExpression#getLeft()
	 * @see #getOrExpression()
	 * @generated
	 */
	EReference getOrExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.OrExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.OrExpression#getRight()
	 * @see #getOrExpression()
	 * @generated
	 */
	EReference getOrExpression_Right();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.XOrExpression <em>XOr Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XOr Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.XOrExpression
	 * @generated
	 */
	EClass getXOrExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.XOrExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.XOrExpression#getLeft()
	 * @see #getXOrExpression()
	 * @generated
	 */
	EReference getXOrExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.XOrExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.XOrExpression#getRight()
	 * @see #getXOrExpression()
	 * @generated
	 */
	EReference getXOrExpression_Right();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.AndExpression <em>And Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>And Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AndExpression
	 * @generated
	 */
	EClass getAndExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.AndExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AndExpression#getLeft()
	 * @see #getAndExpression()
	 * @generated
	 */
	EReference getAndExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.AndExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AndExpression#getRight()
	 * @see #getAndExpression()
	 * @generated
	 */
	EReference getAndExpression_Right();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression <em>Shift Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shift Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression
	 * @generated
	 */
	EClass getShiftExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getLeft()
	 * @see #getShiftExpression()
	 * @generated
	 */
	EReference getShiftExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getRight()
	 * @see #getShiftExpression()
	 * @generated
	 */
	EReference getShiftExpression_Right();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression#getType()
	 * @see #getShiftExpression()
	 * @generated
	 */
	EAttribute getShiftExpression_Type();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.AddExpression <em>Add Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Add Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddExpression
	 * @generated
	 */
	EClass getAddExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getLeft()
	 * @see #getAddExpression()
	 * @generated
	 */
	EReference getAddExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getRight()
	 * @see #getAddExpression()
	 * @generated
	 */
	EReference getAddExpression_Right();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddExpression#getType()
	 * @see #getAddExpression()
	 * @generated
	 */
	EAttribute getAddExpression_Type();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression <em>Mult Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mult Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultExpression
	 * @generated
	 */
	EClass getMultExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getLeft()
	 * @see #getMultExpression()
	 * @generated
	 */
	EReference getMultExpression_Left();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getRight()
	 * @see #getMultExpression()
	 * @generated
	 */
	EReference getMultExpression_Right();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultExpression#getType()
	 * @see #getMultExpression()
	 * @generated
	 */
	EAttribute getMultExpression_Type();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression <em>Unary Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unary Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression
	 * @generated
	 */
	EClass getUnaryExpression();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getExpr()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EReference getUnaryExpression_Expr();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression#getType()
	 * @see #getUnaryExpression()
	 * @generated
	 */
	EAttribute getUnaryExpression_Type();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral <em>Scope Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scope Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral
	 * @generated
	 */
	EClass getScopeLiteral();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral#getValue()
	 * @see #getScopeLiteral()
	 * @generated
	 */
	EReference getScopeLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral <em>Integer Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral
	 * @generated
	 */
	EClass getIntegerLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral#getValue()
	 * @see #getIntegerLiteral()
	 * @generated
	 */
	EAttribute getIntegerLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.StringLiteral
	 * @generated
	 */
	EClass getStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.StringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.StringLiteral#getValue()
	 * @see #getStringLiteral()
	 * @generated
	 */
	EAttribute getStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral <em>Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Character Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral
	 * @generated
	 */
	EClass getCharacterLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral#getValue()
	 * @see #getCharacterLiteral()
	 * @generated
	 */
	EAttribute getCharacterLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral <em>Floating Point Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Floating Point Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral
	 * @generated
	 */
	EClass getFloatingPointLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral#getValue()
	 * @see #getFloatingPointLiteral()
	 * @generated
	 */
	EAttribute getFloatingPointLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral <em>Double Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral
	 * @generated
	 */
	EClass getDoubleLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral#getValue()
	 * @see #getDoubleLiteral()
	 * @generated
	 */
	EAttribute getDoubleLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral
	 * @generated
	 */
	EClass getBooleanLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral#isValue()
	 * @see #getBooleanLiteral()
	 * @generated
	 */
	EAttribute getBooleanLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral <em>Fixed Pt Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fixed Pt Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral
	 * @generated
	 */
	EClass getFixedPtLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getIntegerPart <em>Integer Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Part</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getIntegerPart()
	 * @see #getFixedPtLiteral()
	 * @generated
	 */
	EAttribute getFixedPtLiteral_IntegerPart();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getDecimalPart <em>Decimal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decimal Part</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getDecimalPart()
	 * @see #getFixedPtLiteral()
	 * @generated
	 */
	EAttribute getFixedPtLiteral_DecimalPart();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral#getValue()
	 * @see #getFixedPtLiteral()
	 * @generated
	 */
	EAttribute getFixedPtLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral <em>Wide String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wide String Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral
	 * @generated
	 */
	EClass getWideStringLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral#getValue()
	 * @see #getWideStringLiteral()
	 * @generated
	 */
	EAttribute getWideStringLiteral_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral <em>Wide Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wide Character Literal</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral
	 * @generated
	 */
	EClass getWideCharacterLiteral();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral#getValue()
	 * @see #getWideCharacterLiteral()
	 * @generated
	 */
	EAttribute getWideCharacterLiteral_Value();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftType <em>Shift Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Shift Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftType
	 * @generated
	 */
	EEnum getShiftType();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.eclipsecorba.idl.expressions.AddType <em>Add Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Add Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.AddType
	 * @generated
	 */
	EEnum getAddType();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.eclipsecorba.idl.expressions.MultiType <em>Multi Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Multi Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.MultiType
	 * @generated
	 */
	EEnum getMultiType();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryType <em>Unary Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Unary Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryType
	 * @generated
	 */
	EEnum getUnaryType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExpressionsFactory getExpressionsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionImpl <em>Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getExpression()
		 * @generated
		 */
		EClass EXPRESSION = eINSTANCE.getExpression();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ConstExpressionImpl <em>Const Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ConstExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getConstExpression()
		 * @generated
		 */
		EClass CONST_EXPRESSION = eINSTANCE.getConstExpression();
		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONST_EXPRESSION__EXPRESSION = eINSTANCE.getConstExpression_Expression();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.OrExpressionImpl <em>Or Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.OrExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getOrExpression()
		 * @generated
		 */
		EClass OR_EXPRESSION = eINSTANCE.getOrExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR_EXPRESSION__LEFT = eINSTANCE.getOrExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OR_EXPRESSION__RIGHT = eINSTANCE.getOrExpression_Right();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.XOrExpressionImpl <em>XOr Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.XOrExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getXOrExpression()
		 * @generated
		 */
		EClass XOR_EXPRESSION = eINSTANCE.getXOrExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XOR_EXPRESSION__LEFT = eINSTANCE.getXOrExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference XOR_EXPRESSION__RIGHT = eINSTANCE.getXOrExpression_Right();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.AndExpressionImpl <em>And Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.AndExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAndExpression()
		 * @generated
		 */
		EClass AND_EXPRESSION = eINSTANCE.getAndExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AND_EXPRESSION__LEFT = eINSTANCE.getAndExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AND_EXPRESSION__RIGHT = eINSTANCE.getAndExpression_Right();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ShiftExpressionImpl <em>Shift Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ShiftExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getShiftExpression()
		 * @generated
		 */
		EClass SHIFT_EXPRESSION = eINSTANCE.getShiftExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHIFT_EXPRESSION__LEFT = eINSTANCE.getShiftExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHIFT_EXPRESSION__RIGHT = eINSTANCE.getShiftExpression_Right();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHIFT_EXPRESSION__TYPE = eINSTANCE.getShiftExpression_Type();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.AddExpressionImpl <em>Add Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.AddExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAddExpression()
		 * @generated
		 */
		EClass ADD_EXPRESSION = eINSTANCE.getAddExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_EXPRESSION__LEFT = eINSTANCE.getAddExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADD_EXPRESSION__RIGHT = eINSTANCE.getAddExpression_Right();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ADD_EXPRESSION__TYPE = eINSTANCE.getAddExpression_Type();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.MultExpressionImpl <em>Mult Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.MultExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getMultExpression()
		 * @generated
		 */
		EClass MULT_EXPRESSION = eINSTANCE.getMultExpression();
		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULT_EXPRESSION__LEFT = eINSTANCE.getMultExpression_Left();
		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULT_EXPRESSION__RIGHT = eINSTANCE.getMultExpression_Right();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MULT_EXPRESSION__TYPE = eINSTANCE.getMultExpression_Type();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.UnaryExpressionImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getUnaryExpression()
		 * @generated
		 */
		EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();
		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNARY_EXPRESSION__EXPR = eINSTANCE.getUnaryExpression_Expr();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNARY_EXPRESSION__TYPE = eINSTANCE.getUnaryExpression_Type();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.ScopeLiteralImpl <em>Scope Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ScopeLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getScopeLiteral()
		 * @generated
		 */
		EClass SCOPE_LITERAL = eINSTANCE.getScopeLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCOPE_LITERAL__VALUE = eINSTANCE.getScopeLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.IntegerLiteralImpl <em>Integer Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.IntegerLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getIntegerLiteral()
		 * @generated
		 */
		EClass INTEGER_LITERAL = eINSTANCE.getIntegerLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_LITERAL__VALUE = eINSTANCE.getIntegerLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.StringLiteralImpl <em>String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.StringLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getStringLiteral()
		 * @generated
		 */
		EClass STRING_LITERAL = eINSTANCE.getStringLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL__VALUE = eINSTANCE.getStringLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.CharacterLiteralImpl <em>Character Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.CharacterLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getCharacterLiteral()
		 * @generated
		 */
		EClass CHARACTER_LITERAL = eINSTANCE.getCharacterLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHARACTER_LITERAL__VALUE = eINSTANCE.getCharacterLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FloatingPointLiteralImpl <em>Floating Point Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.FloatingPointLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getFloatingPointLiteral()
		 * @generated
		 */
		EClass FLOATING_POINT_LITERAL = eINSTANCE.getFloatingPointLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOATING_POINT_LITERAL__VALUE = eINSTANCE.getFloatingPointLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.DoubleLiteralImpl <em>Double Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.DoubleLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getDoubleLiteral()
		 * @generated
		 */
		EClass DOUBLE_LITERAL = eINSTANCE.getDoubleLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOUBLE_LITERAL__VALUE = eINSTANCE.getDoubleLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.BooleanLiteralImpl <em>Boolean Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.BooleanLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getBooleanLiteral()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL = eINSTANCE.getBooleanLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL__VALUE = eINSTANCE.getBooleanLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl <em>Fixed Pt Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.FixedPtLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getFixedPtLiteral()
		 * @generated
		 */
		EClass FIXED_PT_LITERAL = eINSTANCE.getFixedPtLiteral();
		/**
		 * The meta object literal for the '<em><b>Integer Part</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_PT_LITERAL__INTEGER_PART = eINSTANCE.getFixedPtLiteral_IntegerPart();
		/**
		 * The meta object literal for the '<em><b>Decimal Part</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_PT_LITERAL__DECIMAL_PART = eINSTANCE.getFixedPtLiteral_DecimalPart();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIXED_PT_LITERAL__VALUE = eINSTANCE.getFixedPtLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.WideStringLiteralImpl <em>Wide String Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.WideStringLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getWideStringLiteral()
		 * @generated
		 */
		EClass WIDE_STRING_LITERAL = eINSTANCE.getWideStringLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIDE_STRING_LITERAL__VALUE = eINSTANCE.getWideStringLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.impl.WideCharacterLiteralImpl <em>Wide Character Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.WideCharacterLiteralImpl
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getWideCharacterLiteral()
		 * @generated
		 */
		EClass WIDE_CHARACTER_LITERAL = eINSTANCE.getWideCharacterLiteral();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WIDE_CHARACTER_LITERAL__VALUE = eINSTANCE.getWideCharacterLiteral_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.ShiftType <em>Shift Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.ShiftType
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getShiftType()
		 * @generated
		 */
		EEnum SHIFT_TYPE = eINSTANCE.getShiftType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.AddType <em>Add Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.AddType
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getAddType()
		 * @generated
		 */
		EEnum ADD_TYPE = eINSTANCE.getAddType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.MultiType <em>Multi Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.MultiType
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getMultiType()
		 * @generated
		 */
		EEnum MULTI_TYPE = eINSTANCE.getMultiType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.expressions.UnaryType <em>Unary Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.expressions.UnaryType
		 * @see gov.redhawk.eclipsecorba.idl.expressions.impl.ExpressionsPackageImpl#getUnaryType()
		 * @generated
		 */
		EEnum UNARY_TYPE = eINSTANCE.getUnaryType();

	}

} //ExpressionsPackage
