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

import gov.redhawk.eclipsecorba.idl.expressions.AddExpression;
import gov.redhawk.eclipsecorba.idl.expressions.AddType;
import gov.redhawk.eclipsecorba.idl.expressions.AndExpression;
import gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.ConstExpression;
import gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral;
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionsFactoryImpl extends EFactoryImpl implements ExpressionsFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExpressionsFactory init() {
		try {
			ExpressionsFactory theExpressionsFactory = (ExpressionsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///gov/redhawk/eclipsecorba/idl.expressions.ecore"); 
			if (theExpressionsFactory != null) {
				return theExpressionsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExpressionsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ExpressionsPackage.CONST_EXPRESSION: return createConstExpression();
			case ExpressionsPackage.OR_EXPRESSION: return createOrExpression();
			case ExpressionsPackage.XOR_EXPRESSION: return createXOrExpression();
			case ExpressionsPackage.AND_EXPRESSION: return createAndExpression();
			case ExpressionsPackage.SHIFT_EXPRESSION: return createShiftExpression();
			case ExpressionsPackage.ADD_EXPRESSION: return createAddExpression();
			case ExpressionsPackage.MULT_EXPRESSION: return createMultExpression();
			case ExpressionsPackage.UNARY_EXPRESSION: return createUnaryExpression();
			case ExpressionsPackage.SCOPE_LITERAL: return createScopeLiteral();
			case ExpressionsPackage.INTEGER_LITERAL: return createIntegerLiteral();
			case ExpressionsPackage.STRING_LITERAL: return createStringLiteral();
			case ExpressionsPackage.CHARACTER_LITERAL: return createCharacterLiteral();
			case ExpressionsPackage.FLOATING_POINT_LITERAL: return createFloatingPointLiteral();
			case ExpressionsPackage.DOUBLE_LITERAL: return createDoubleLiteral();
			case ExpressionsPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
			case ExpressionsPackage.FIXED_PT_LITERAL: return createFixedPtLiteral();
			case ExpressionsPackage.WIDE_STRING_LITERAL: return createWideStringLiteral();
			case ExpressionsPackage.WIDE_CHARACTER_LITERAL: return createWideCharacterLiteral();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionsPackage.SHIFT_TYPE:
				return createShiftTypeFromString(eDataType, initialValue);
			case ExpressionsPackage.ADD_TYPE:
				return createAddTypeFromString(eDataType, initialValue);
			case ExpressionsPackage.MULTI_TYPE:
				return createMultiTypeFromString(eDataType, initialValue);
			case ExpressionsPackage.UNARY_TYPE:
				return createUnaryTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ExpressionsPackage.SHIFT_TYPE:
				return convertShiftTypeToString(eDataType, instanceValue);
			case ExpressionsPackage.ADD_TYPE:
				return convertAddTypeToString(eDataType, instanceValue);
			case ExpressionsPackage.MULTI_TYPE:
				return convertMultiTypeToString(eDataType, instanceValue);
			case ExpressionsPackage.UNARY_TYPE:
				return convertUnaryTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstExpression createConstExpression() {
		ConstExpressionImpl constExpression = new ConstExpressionImpl();
		return constExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrExpression createOrExpression() {
		OrExpressionImpl orExpression = new OrExpressionImpl();
		return orExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XOrExpression createXOrExpression() {
		XOrExpressionImpl xOrExpression = new XOrExpressionImpl();
		return xOrExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AndExpression createAndExpression() {
		AndExpressionImpl andExpression = new AndExpressionImpl();
		return andExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShiftExpression createShiftExpression() {
		ShiftExpressionImpl shiftExpression = new ShiftExpressionImpl();
		return shiftExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddExpression createAddExpression() {
		AddExpressionImpl addExpression = new AddExpressionImpl();
		return addExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultExpression createMultExpression() {
		MultExpressionImpl multExpression = new MultExpressionImpl();
		return multExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryExpression createUnaryExpression() {
		UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
		return unaryExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeLiteral createScopeLiteral() {
		ScopeLiteralImpl scopeLiteral = new ScopeLiteralImpl();
		return scopeLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntegerLiteral createIntegerLiteral() {
		IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
		return integerLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StringLiteral createStringLiteral() {
		StringLiteralImpl stringLiteral = new StringLiteralImpl();
		return stringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CharacterLiteral createCharacterLiteral() {
		CharacterLiteralImpl characterLiteral = new CharacterLiteralImpl();
		return characterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FloatingPointLiteral createFloatingPointLiteral() {
		FloatingPointLiteralImpl floatingPointLiteral = new FloatingPointLiteralImpl();
		return floatingPointLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DoubleLiteral createDoubleLiteral() {
		DoubleLiteralImpl doubleLiteral = new DoubleLiteralImpl();
		return doubleLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BooleanLiteral createBooleanLiteral() {
		BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
		return booleanLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixedPtLiteral createFixedPtLiteral() {
		FixedPtLiteralImpl fixedPtLiteral = new FixedPtLiteralImpl();
		return fixedPtLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WideStringLiteral createWideStringLiteral() {
		WideStringLiteralImpl wideStringLiteral = new WideStringLiteralImpl();
		return wideStringLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WideCharacterLiteral createWideCharacterLiteral() {
		WideCharacterLiteralImpl wideCharacterLiteral = new WideCharacterLiteralImpl();
		return wideCharacterLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ShiftType createShiftTypeFromString(EDataType eDataType, String initialValue) {
		ShiftType result = ShiftType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShiftTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddType createAddTypeFromString(EDataType eDataType, String initialValue) {
		AddType result = AddType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAddTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiType createMultiTypeFromString(EDataType eDataType, String initialValue) {
		MultiType result = MultiType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnaryType createUnaryTypeFromString(EDataType eDataType, String initialValue) {
		UnaryType result = UnaryType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUnaryTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsPackage getExpressionsPackage() {
		return (ExpressionsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExpressionsPackage getPackage() {
		return ExpressionsPackage.eINSTANCE;
	}

} //ExpressionsFactoryImpl
