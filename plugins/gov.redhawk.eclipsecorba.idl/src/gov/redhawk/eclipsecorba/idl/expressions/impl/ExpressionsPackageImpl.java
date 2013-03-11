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
package gov.redhawk.eclipsecorba.idl.expressions.impl;

import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.expressions.AddExpression;
import gov.redhawk.eclipsecorba.idl.expressions.AddType;
import gov.redhawk.eclipsecorba.idl.expressions.AndExpression;
import gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.ConstExpression;
import gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.Expression;
import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsFactory;
import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage;
import gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.MultExpression;
import gov.redhawk.eclipsecorba.idl.expressions.MultiType;
import gov.redhawk.eclipsecorba.idl.expressions.OrExpression;
import gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression;
import gov.redhawk.eclipsecorba.idl.expressions.ShiftType;
import gov.redhawk.eclipsecorba.idl.expressions.StringLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression;
import gov.redhawk.eclipsecorba.idl.expressions.UnaryType;
import gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.XOrExpression;
import gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl;
import gov.redhawk.eclipsecorba.idl.operations.OperationsPackage;
import gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;
import gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionsPackageImpl extends EPackageImpl implements ExpressionsPackage {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xOrExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass shiftExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass addExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryExpressionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scopeLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass characterLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass floatingPointLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fixedPtLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wideStringLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wideCharacterLiteralEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum shiftTypeEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum addTypeEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum multiTypeEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum unaryTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExpressionsPackageImpl() {
		super(eNS_URI, ExpressionsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExpressionsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExpressionsPackage init() {
		if (isInited) return (ExpressionsPackage)EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI);

		// Obtain or create and register package
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExpressionsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		IdlPackageImpl theIdlPackage = (IdlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(IdlPackage.eNS_URI) instanceof IdlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(IdlPackage.eNS_URI) : IdlPackage.eINSTANCE);
		OperationsPackageImpl theOperationsPackage = (OperationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) instanceof OperationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OperationsPackage.eNS_URI) : OperationsPackage.eINSTANCE);
		TypesPackageImpl theTypesPackage = (TypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI) : TypesPackage.eINSTANCE);

		// Create package meta-data objects
		theExpressionsPackage.createPackageContents();
		theIdlPackage.createPackageContents();
		theOperationsPackage.createPackageContents();
		theTypesPackage.createPackageContents();

		// Initialize created meta-data
		theExpressionsPackage.initializePackageContents();
		theIdlPackage.initializePackageContents();
		theOperationsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExpressionsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExpressionsPackage.eNS_URI, theExpressionsPackage);
		return theExpressionsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExpression() {
		return expressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstExpression() {
		return constExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConstExpression_Expression() {
		return (EReference)constExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOrExpression() {
		return orExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOrExpression_Left() {
		return (EReference)orExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOrExpression_Right() {
		return (EReference)orExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXOrExpression() {
		return xOrExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getXOrExpression_Left() {
		return (EReference)xOrExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getXOrExpression_Right() {
		return (EReference)xOrExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAndExpression() {
		return andExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAndExpression_Left() {
		return (EReference)andExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAndExpression_Right() {
		return (EReference)andExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getShiftExpression() {
		return shiftExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getShiftExpression_Left() {
		return (EReference)shiftExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getShiftExpression_Right() {
		return (EReference)shiftExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getShiftExpression_Type() {
		return (EAttribute)shiftExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAddExpression() {
		return addExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddExpression_Left() {
		return (EReference)addExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAddExpression_Right() {
		return (EReference)addExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAddExpression_Type() {
		return (EAttribute)addExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultExpression() {
		return multExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultExpression_Left() {
		return (EReference)multExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultExpression_Right() {
		return (EReference)multExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMultExpression_Type() {
		return (EAttribute)multExpressionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryExpression() {
		return unaryExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryExpression_Expr() {
		return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnaryExpression_Type() {
		return (EAttribute)unaryExpressionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScopeLiteral() {
		return scopeLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScopeLiteral_Value() {
		return (EReference)scopeLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntegerLiteral() {
		return integerLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntegerLiteral_Value() {
		return (EAttribute)integerLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringLiteral() {
		return stringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringLiteral_Value() {
		return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCharacterLiteral() {
		return characterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCharacterLiteral_Value() {
		return (EAttribute)characterLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFloatingPointLiteral() {
		return floatingPointLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFloatingPointLiteral_Value() {
		return (EAttribute)floatingPointLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDoubleLiteral() {
		return doubleLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDoubleLiteral_Value() {
		return (EAttribute)doubleLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBooleanLiteral() {
		return booleanLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBooleanLiteral_Value() {
		return (EAttribute)booleanLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFixedPtLiteral() {
		return fixedPtLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixedPtLiteral_IntegerPart() {
		return (EAttribute)fixedPtLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixedPtLiteral_DecimalPart() {
		return (EAttribute)fixedPtLiteralEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFixedPtLiteral_Value() {
		return (EAttribute)fixedPtLiteralEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWideStringLiteral() {
		return wideStringLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWideStringLiteral_Value() {
		return (EAttribute)wideStringLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWideCharacterLiteral() {
		return wideCharacterLiteralEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWideCharacterLiteral_Value() {
		return (EAttribute)wideCharacterLiteralEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getShiftType() {
		return shiftTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getAddType() {
		return addTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getMultiType() {
		return multiTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getUnaryType() {
		return unaryTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsFactory getExpressionsFactory() {
		return (ExpressionsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		expressionEClass = createEClass(EXPRESSION);

		constExpressionEClass = createEClass(CONST_EXPRESSION);
		createEReference(constExpressionEClass, CONST_EXPRESSION__EXPRESSION);

		orExpressionEClass = createEClass(OR_EXPRESSION);
		createEReference(orExpressionEClass, OR_EXPRESSION__LEFT);
		createEReference(orExpressionEClass, OR_EXPRESSION__RIGHT);

		xOrExpressionEClass = createEClass(XOR_EXPRESSION);
		createEReference(xOrExpressionEClass, XOR_EXPRESSION__LEFT);
		createEReference(xOrExpressionEClass, XOR_EXPRESSION__RIGHT);

		andExpressionEClass = createEClass(AND_EXPRESSION);
		createEReference(andExpressionEClass, AND_EXPRESSION__LEFT);
		createEReference(andExpressionEClass, AND_EXPRESSION__RIGHT);

		shiftExpressionEClass = createEClass(SHIFT_EXPRESSION);
		createEReference(shiftExpressionEClass, SHIFT_EXPRESSION__LEFT);
		createEReference(shiftExpressionEClass, SHIFT_EXPRESSION__RIGHT);
		createEAttribute(shiftExpressionEClass, SHIFT_EXPRESSION__TYPE);

		addExpressionEClass = createEClass(ADD_EXPRESSION);
		createEReference(addExpressionEClass, ADD_EXPRESSION__LEFT);
		createEReference(addExpressionEClass, ADD_EXPRESSION__RIGHT);
		createEAttribute(addExpressionEClass, ADD_EXPRESSION__TYPE);

		multExpressionEClass = createEClass(MULT_EXPRESSION);
		createEReference(multExpressionEClass, MULT_EXPRESSION__LEFT);
		createEReference(multExpressionEClass, MULT_EXPRESSION__RIGHT);
		createEAttribute(multExpressionEClass, MULT_EXPRESSION__TYPE);

		unaryExpressionEClass = createEClass(UNARY_EXPRESSION);
		createEReference(unaryExpressionEClass, UNARY_EXPRESSION__EXPR);
		createEAttribute(unaryExpressionEClass, UNARY_EXPRESSION__TYPE);

		scopeLiteralEClass = createEClass(SCOPE_LITERAL);
		createEReference(scopeLiteralEClass, SCOPE_LITERAL__VALUE);

		integerLiteralEClass = createEClass(INTEGER_LITERAL);
		createEAttribute(integerLiteralEClass, INTEGER_LITERAL__VALUE);

		stringLiteralEClass = createEClass(STRING_LITERAL);
		createEAttribute(stringLiteralEClass, STRING_LITERAL__VALUE);

		characterLiteralEClass = createEClass(CHARACTER_LITERAL);
		createEAttribute(characterLiteralEClass, CHARACTER_LITERAL__VALUE);

		floatingPointLiteralEClass = createEClass(FLOATING_POINT_LITERAL);
		createEAttribute(floatingPointLiteralEClass, FLOATING_POINT_LITERAL__VALUE);

		doubleLiteralEClass = createEClass(DOUBLE_LITERAL);
		createEAttribute(doubleLiteralEClass, DOUBLE_LITERAL__VALUE);

		booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
		createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__VALUE);

		fixedPtLiteralEClass = createEClass(FIXED_PT_LITERAL);
		createEAttribute(fixedPtLiteralEClass, FIXED_PT_LITERAL__INTEGER_PART);
		createEAttribute(fixedPtLiteralEClass, FIXED_PT_LITERAL__DECIMAL_PART);
		createEAttribute(fixedPtLiteralEClass, FIXED_PT_LITERAL__VALUE);

		wideStringLiteralEClass = createEClass(WIDE_STRING_LITERAL);
		createEAttribute(wideStringLiteralEClass, WIDE_STRING_LITERAL__VALUE);

		wideCharacterLiteralEClass = createEClass(WIDE_CHARACTER_LITERAL);
		createEAttribute(wideCharacterLiteralEClass, WIDE_CHARACTER_LITERAL__VALUE);

		// Create enums
		shiftTypeEEnum = createEEnum(SHIFT_TYPE);
		addTypeEEnum = createEEnum(ADD_TYPE);
		multiTypeEEnum = createEEnum(MULTI_TYPE);
		unaryTypeEEnum = createEEnum(UNARY_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		IdlPackage theIdlPackage = (IdlPackage)EPackage.Registry.INSTANCE.getEPackage(IdlPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		expressionEClass.getESuperTypes().add(theIdlPackage.getFileRegion());
		constExpressionEClass.getESuperTypes().add(this.getExpression());
		orExpressionEClass.getESuperTypes().add(this.getExpression());
		xOrExpressionEClass.getESuperTypes().add(this.getExpression());
		andExpressionEClass.getESuperTypes().add(this.getExpression());
		shiftExpressionEClass.getESuperTypes().add(this.getExpression());
		addExpressionEClass.getESuperTypes().add(this.getExpression());
		multExpressionEClass.getESuperTypes().add(this.getExpression());
		unaryExpressionEClass.getESuperTypes().add(this.getExpression());
		scopeLiteralEClass.getESuperTypes().add(this.getExpression());
		integerLiteralEClass.getESuperTypes().add(this.getExpression());
		stringLiteralEClass.getESuperTypes().add(this.getExpression());
		characterLiteralEClass.getESuperTypes().add(this.getExpression());
		floatingPointLiteralEClass.getESuperTypes().add(this.getExpression());
		doubleLiteralEClass.getESuperTypes().add(this.getExpression());
		booleanLiteralEClass.getESuperTypes().add(this.getExpression());
		fixedPtLiteralEClass.getESuperTypes().add(this.getExpression());
		wideStringLiteralEClass.getESuperTypes().add(this.getExpression());
		wideCharacterLiteralEClass.getESuperTypes().add(this.getExpression());

		// Initialize classes and features; add operations and parameters
		initEClass(expressionEClass, Expression.class, "Expression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constExpressionEClass, ConstExpression.class, "ConstExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConstExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, ConstExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(orExpressionEClass, OrExpression.class, "OrExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOrExpression_Left(), this.getExpression(), null, "left", null, 0, 1, OrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOrExpression_Right(), this.getExpression(), null, "right", null, 0, 1, OrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(xOrExpressionEClass, XOrExpression.class, "XOrExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getXOrExpression_Left(), this.getExpression(), null, "left", null, 0, 1, XOrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getXOrExpression_Right(), this.getExpression(), null, "right", null, 0, 1, XOrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andExpressionEClass, AndExpression.class, "AndExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAndExpression_Left(), this.getExpression(), null, "left", null, 0, 1, AndExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAndExpression_Right(), this.getExpression(), null, "right", null, 0, 1, AndExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(shiftExpressionEClass, ShiftExpression.class, "ShiftExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getShiftExpression_Left(), this.getExpression(), null, "left", null, 0, 1, ShiftExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getShiftExpression_Right(), this.getExpression(), null, "right", null, 0, 1, ShiftExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getShiftExpression_Type(), this.getShiftType(), "type", null, 0, 1, ShiftExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(addExpressionEClass, AddExpression.class, "AddExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAddExpression_Left(), this.getExpression(), null, "left", null, 0, 1, AddExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAddExpression_Right(), this.getExpression(), null, "right", null, 0, 1, AddExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAddExpression_Type(), this.getAddType(), "type", null, 0, 1, AddExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(multExpressionEClass, MultExpression.class, "MultExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultExpression_Left(), this.getExpression(), null, "left", null, 0, 1, MultExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMultExpression_Right(), this.getExpression(), null, "right", null, 0, 1, MultExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMultExpression_Type(), this.getMultiType(), "type", null, 0, 1, MultExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryExpression_Expr(), this.getExpression(), null, "expr", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnaryExpression_Type(), this.getUnaryType(), "type", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scopeLiteralEClass, ScopeLiteral.class, "ScopeLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScopeLiteral_Value(), theIdlPackage.getIdlTypeDcl(), null, "value", null, 0, 1, ScopeLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntegerLiteral_Value(), ecorePackage.getEInt(), "value", null, 0, 1, IntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, StringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(characterLiteralEClass, CharacterLiteral.class, "CharacterLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCharacterLiteral_Value(), ecorePackage.getEChar(), "value", null, 0, 1, CharacterLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(floatingPointLiteralEClass, FloatingPointLiteral.class, "FloatingPointLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFloatingPointLiteral_Value(), ecorePackage.getEFloat(), "value", null, 0, 1, FloatingPointLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(doubleLiteralEClass, DoubleLiteral.class, "DoubleLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDoubleLiteral_Value(), ecorePackage.getEDouble(), "value", null, 0, 1, DoubleLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanLiteral_Value(), ecorePackage.getEBoolean(), "value", null, 0, 1, BooleanLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(fixedPtLiteralEClass, FixedPtLiteral.class, "FixedPtLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFixedPtLiteral_IntegerPart(), ecorePackage.getEInt(), "integerPart", null, 0, 1, FixedPtLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixedPtLiteral_DecimalPart(), ecorePackage.getEInt(), "decimalPart", null, 0, 1, FixedPtLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFixedPtLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, FixedPtLiteral.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(wideStringLiteralEClass, WideStringLiteral.class, "WideStringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWideStringLiteral_Value(), ecorePackage.getEString(), "value", null, 0, 1, WideStringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(wideCharacterLiteralEClass, WideCharacterLiteral.class, "WideCharacterLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWideCharacterLiteral_Value(), ecorePackage.getEChar(), "value", null, 0, 1, WideCharacterLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(shiftTypeEEnum, ShiftType.class, "ShiftType");
		addEEnumLiteral(shiftTypeEEnum, ShiftType.LEFT);
		addEEnumLiteral(shiftTypeEEnum, ShiftType.RIGHT);

		initEEnum(addTypeEEnum, AddType.class, "AddType");
		addEEnumLiteral(addTypeEEnum, AddType.ADDITION);
		addEEnumLiteral(addTypeEEnum, AddType.SUBTRACTION);

		initEEnum(multiTypeEEnum, MultiType.class, "MultiType");
		addEEnumLiteral(multiTypeEEnum, MultiType.MULTIPLICATION);
		addEEnumLiteral(multiTypeEEnum, MultiType.DIVISION);
		addEEnumLiteral(multiTypeEEnum, MultiType.MODULATION);

		initEEnum(unaryTypeEEnum, UnaryType.class, "UnaryType");
		addEEnumLiteral(unaryTypeEEnum, UnaryType.NEGATIVE);
		addEEnumLiteral(unaryTypeEEnum, UnaryType.POSITIVE);
		addEEnumLiteral(unaryTypeEEnum, UnaryType.TILDE);

		// Create resource
		createResource(eNS_URI);
	}

} //ExpressionsPackageImpl
