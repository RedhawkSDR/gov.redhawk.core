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
package gov.redhawk.eclipsecorba.idl.expressions.util;

import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.expressions.AddExpression;
import gov.redhawk.eclipsecorba.idl.expressions.AndExpression;
import gov.redhawk.eclipsecorba.idl.expressions.BooleanLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.CharacterLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.ConstExpression;
import gov.redhawk.eclipsecorba.idl.expressions.DoubleLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.Expression;
import gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage;
import gov.redhawk.eclipsecorba.idl.expressions.FixedPtLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.FloatingPointLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.IntegerLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.MultExpression;
import gov.redhawk.eclipsecorba.idl.expressions.OrExpression;
import gov.redhawk.eclipsecorba.idl.expressions.ScopeLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.ShiftExpression;
import gov.redhawk.eclipsecorba.idl.expressions.StringLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.UnaryExpression;
import gov.redhawk.eclipsecorba.idl.expressions.WideCharacterLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.WideStringLiteral;
import gov.redhawk.eclipsecorba.idl.expressions.XOrExpression;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.expressions.ExpressionsPackage
 * @generated
 */
public class ExpressionsSwitch<T> {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExpressionsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsSwitch() {
		if (modelPackage == null) {
			modelPackage = ExpressionsPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ExpressionsPackage.EXPRESSION: {
				Expression expression = (Expression)theEObject;
				T result = caseExpression(expression);
				if (result == null) result = caseFileRegion(expression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.CONST_EXPRESSION: {
				ConstExpression constExpression = (ConstExpression)theEObject;
				T result = caseConstExpression(constExpression);
				if (result == null) result = caseExpression(constExpression);
				if (result == null) result = caseFileRegion(constExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.OR_EXPRESSION: {
				OrExpression orExpression = (OrExpression)theEObject;
				T result = caseOrExpression(orExpression);
				if (result == null) result = caseExpression(orExpression);
				if (result == null) result = caseFileRegion(orExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.XOR_EXPRESSION: {
				XOrExpression xOrExpression = (XOrExpression)theEObject;
				T result = caseXOrExpression(xOrExpression);
				if (result == null) result = caseExpression(xOrExpression);
				if (result == null) result = caseFileRegion(xOrExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.AND_EXPRESSION: {
				AndExpression andExpression = (AndExpression)theEObject;
				T result = caseAndExpression(andExpression);
				if (result == null) result = caseExpression(andExpression);
				if (result == null) result = caseFileRegion(andExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.SHIFT_EXPRESSION: {
				ShiftExpression shiftExpression = (ShiftExpression)theEObject;
				T result = caseShiftExpression(shiftExpression);
				if (result == null) result = caseExpression(shiftExpression);
				if (result == null) result = caseFileRegion(shiftExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.ADD_EXPRESSION: {
				AddExpression addExpression = (AddExpression)theEObject;
				T result = caseAddExpression(addExpression);
				if (result == null) result = caseExpression(addExpression);
				if (result == null) result = caseFileRegion(addExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.MULT_EXPRESSION: {
				MultExpression multExpression = (MultExpression)theEObject;
				T result = caseMultExpression(multExpression);
				if (result == null) result = caseExpression(multExpression);
				if (result == null) result = caseFileRegion(multExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.UNARY_EXPRESSION: {
				UnaryExpression unaryExpression = (UnaryExpression)theEObject;
				T result = caseUnaryExpression(unaryExpression);
				if (result == null) result = caseExpression(unaryExpression);
				if (result == null) result = caseFileRegion(unaryExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.SCOPE_LITERAL: {
				ScopeLiteral scopeLiteral = (ScopeLiteral)theEObject;
				T result = caseScopeLiteral(scopeLiteral);
				if (result == null) result = caseExpression(scopeLiteral);
				if (result == null) result = caseFileRegion(scopeLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.INTEGER_LITERAL: {
				IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
				T result = caseIntegerLiteral(integerLiteral);
				if (result == null) result = caseExpression(integerLiteral);
				if (result == null) result = caseFileRegion(integerLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.STRING_LITERAL: {
				StringLiteral stringLiteral = (StringLiteral)theEObject;
				T result = caseStringLiteral(stringLiteral);
				if (result == null) result = caseExpression(stringLiteral);
				if (result == null) result = caseFileRegion(stringLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.CHARACTER_LITERAL: {
				CharacterLiteral characterLiteral = (CharacterLiteral)theEObject;
				T result = caseCharacterLiteral(characterLiteral);
				if (result == null) result = caseExpression(characterLiteral);
				if (result == null) result = caseFileRegion(characterLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.FLOATING_POINT_LITERAL: {
				FloatingPointLiteral floatingPointLiteral = (FloatingPointLiteral)theEObject;
				T result = caseFloatingPointLiteral(floatingPointLiteral);
				if (result == null) result = caseExpression(floatingPointLiteral);
				if (result == null) result = caseFileRegion(floatingPointLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.DOUBLE_LITERAL: {
				DoubleLiteral doubleLiteral = (DoubleLiteral)theEObject;
				T result = caseDoubleLiteral(doubleLiteral);
				if (result == null) result = caseExpression(doubleLiteral);
				if (result == null) result = caseFileRegion(doubleLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.BOOLEAN_LITERAL: {
				BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
				T result = caseBooleanLiteral(booleanLiteral);
				if (result == null) result = caseExpression(booleanLiteral);
				if (result == null) result = caseFileRegion(booleanLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.FIXED_PT_LITERAL: {
				FixedPtLiteral fixedPtLiteral = (FixedPtLiteral)theEObject;
				T result = caseFixedPtLiteral(fixedPtLiteral);
				if (result == null) result = caseExpression(fixedPtLiteral);
				if (result == null) result = caseFileRegion(fixedPtLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.WIDE_STRING_LITERAL: {
				WideStringLiteral wideStringLiteral = (WideStringLiteral)theEObject;
				T result = caseWideStringLiteral(wideStringLiteral);
				if (result == null) result = caseExpression(wideStringLiteral);
				if (result == null) result = caseFileRegion(wideStringLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ExpressionsPackage.WIDE_CHARACTER_LITERAL: {
				WideCharacterLiteral wideCharacterLiteral = (WideCharacterLiteral)theEObject;
				T result = caseWideCharacterLiteral(wideCharacterLiteral);
				if (result == null) result = caseExpression(wideCharacterLiteral);
				if (result == null) result = caseFileRegion(wideCharacterLiteral);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExpression(Expression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Const Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Const Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstExpression(ConstExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Or Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrExpression(OrExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>XOr Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>XOr Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseXOrExpression(XOrExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>And Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAndExpression(AndExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Shift Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Shift Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShiftExpression(ShiftExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Add Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Add Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddExpression(AddExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mult Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mult Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultExpression(MultExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnaryExpression(UnaryExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Scope Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Scope Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseScopeLiteral(ScopeLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntegerLiteral(IntegerLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringLiteral(StringLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Character Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCharacterLiteral(CharacterLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Floating Point Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Floating Point Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloatingPointLiteral(FloatingPointLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDoubleLiteral(DoubleLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanLiteral(BooleanLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fixed Pt Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fixed Pt Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixedPtLiteral(FixedPtLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wide String Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wide String Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWideStringLiteral(WideStringLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wide Character Literal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wide Character Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWideCharacterLiteral(WideCharacterLiteral object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Region</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Region</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFileRegion(FileRegion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //ExpressionsSwitch
