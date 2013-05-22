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
package gov.redhawk.eclipsecorba.idl.types.util;

import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Declarator;
import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Element;
import gov.redhawk.eclipsecorba.idl.Export;
import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlType;
import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;
import gov.redhawk.eclipsecorba.idl.MemberContainer;
import gov.redhawk.eclipsecorba.idl.Typed;
import gov.redhawk.eclipsecorba.idl.TypedElement;
import gov.redhawk.eclipsecorba.idl.types.Any;
import gov.redhawk.eclipsecorba.idl.types.Case;
import gov.redhawk.eclipsecorba.idl.types.CaseLabel;
import gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel;
import gov.redhawk.eclipsecorba.idl.types.ElementSpec;
import gov.redhawk.eclipsecorba.idl.types.EnumType;
import gov.redhawk.eclipsecorba.idl.types.Enumeration;
import gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel;
import gov.redhawk.eclipsecorba.idl.types.FixedPtType;
import gov.redhawk.eclipsecorba.idl.types.IdlChar;
import gov.redhawk.eclipsecorba.idl.types.IdlObject;
import gov.redhawk.eclipsecorba.idl.types.IdlString;
import gov.redhawk.eclipsecorba.idl.types.IdlWChar;
import gov.redhawk.eclipsecorba.idl.types.LongDouble;
import gov.redhawk.eclipsecorba.idl.types.LongLong;
import gov.redhawk.eclipsecorba.idl.types.Octet;
import gov.redhawk.eclipsecorba.idl.types.PrimitiveType;
import gov.redhawk.eclipsecorba.idl.types.SequenceType;
import gov.redhawk.eclipsecorba.idl.types.StructForwardDcl;
import gov.redhawk.eclipsecorba.idl.types.StructType;
import gov.redhawk.eclipsecorba.idl.types.Switch;
import gov.redhawk.eclipsecorba.idl.types.TemplateType;
import gov.redhawk.eclipsecorba.idl.types.TypeDef;
import gov.redhawk.eclipsecorba.idl.types.TypesPackage;
import gov.redhawk.eclipsecorba.idl.types.ULong;
import gov.redhawk.eclipsecorba.idl.types.ULongLong;
import gov.redhawk.eclipsecorba.idl.types.UShort;
import gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl;
import gov.redhawk.eclipsecorba.idl.types.UnionType;
import gov.redhawk.eclipsecorba.idl.types.ValueBaseType;
import gov.redhawk.eclipsecorba.idl.types.VoidType;
import gov.redhawk.eclipsecorba.idl.types.WChar;
import gov.redhawk.eclipsecorba.idl.types.WString;

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
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage
 * @generated
 */
public class TypesSwitch<T> {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypesPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesSwitch() {
		if (modelPackage == null) {
			modelPackage = TypesPackage.eINSTANCE;
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
			case TypesPackage.TYPE_DEF: {
				TypeDef typeDef = (TypeDef)theEObject;
				T result = caseTypeDef(typeDef);
				if (result == null) result = caseIdlTypeDcl(typeDef);
				if (result == null) result = caseTyped(typeDef);
				if (result == null) result = caseDefinition(typeDef);
				if (result == null) result = caseIdlType(typeDef);
				if (result == null) result = caseExport(typeDef);
				if (result == null) result = caseTypedElement(typeDef);
				if (result == null) result = caseElement(typeDef);
				if (result == null) result = caseIdentifiable(typeDef);
				if (result == null) result = caseFileRegion(typeDef);
				if (result == null) result = caseCommentable(typeDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.VOID_TYPE: {
				VoidType voidType = (VoidType)theEObject;
				T result = caseVoidType(voidType);
				if (result == null) result = caseIdlType(voidType);
				if (result == null) result = caseFileRegion(voidType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.UNION_TYPE: {
				UnionType unionType = (UnionType)theEObject;
				T result = caseUnionType(unionType);
				if (result == null) result = caseIdlTypeDcl(unionType);
				if (result == null) result = caseDefinition(unionType);
				if (result == null) result = caseIdlType(unionType);
				if (result == null) result = caseExport(unionType);
				if (result == null) result = caseCommentable(unionType);
				if (result == null) result = caseTypedElement(unionType);
				if (result == null) result = caseElement(unionType);
				if (result == null) result = caseIdentifiable(unionType);
				if (result == null) result = caseFileRegion(unionType);
				if (result == null) result = caseTyped(unionType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.SWITCH: {
				Switch switch_ = (Switch)theEObject;
				T result = caseSwitch(switch_);
				if (result == null) result = caseFileRegion(switch_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.CASE: {
				Case case_ = (Case)theEObject;
				T result = caseCase(case_);
				if (result == null) result = caseFileRegion(case_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.CASE_LABEL: {
				CaseLabel caseLabel = (CaseLabel)theEObject;
				T result = caseCaseLabel(caseLabel);
				if (result == null) result = caseFileRegion(caseLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.DEFAULT_CASE_LABEL: {
				DefaultCaseLabel defaultCaseLabel = (DefaultCaseLabel)theEObject;
				T result = caseDefaultCaseLabel(defaultCaseLabel);
				if (result == null) result = caseCaseLabel(defaultCaseLabel);
				if (result == null) result = caseFileRegion(defaultCaseLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.EXPR_CASE_LABEL: {
				ExprCaseLabel exprCaseLabel = (ExprCaseLabel)theEObject;
				T result = caseExprCaseLabel(exprCaseLabel);
				if (result == null) result = caseCaseLabel(exprCaseLabel);
				if (result == null) result = caseFileRegion(exprCaseLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ELEMENT_SPEC: {
				ElementSpec elementSpec = (ElementSpec)theEObject;
				T result = caseElementSpec(elementSpec);
				if (result == null) result = caseFileRegion(elementSpec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ENUM_TYPE: {
				EnumType enumType = (EnumType)theEObject;
				T result = caseEnumType(enumType);
				if (result == null) result = caseIdlTypeDcl(enumType);
				if (result == null) result = caseDefinition(enumType);
				if (result == null) result = caseIdlType(enumType);
				if (result == null) result = caseExport(enumType);
				if (result == null) result = caseCommentable(enumType);
				if (result == null) result = caseTypedElement(enumType);
				if (result == null) result = caseElement(enumType);
				if (result == null) result = caseIdentifiable(enumType);
				if (result == null) result = caseFileRegion(enumType);
				if (result == null) result = caseTyped(enumType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.STRUCT_TYPE: {
				StructType structType = (StructType)theEObject;
				T result = caseStructType(structType);
				if (result == null) result = caseIdlTypeDcl(structType);
				if (result == null) result = caseMemberContainer(structType);
				if (result == null) result = caseDefinition(structType);
				if (result == null) result = caseIdlType(structType);
				if (result == null) result = caseExport(structType);
				if (result == null) result = caseCommentable(structType);
				if (result == null) result = caseTypedElement(structType);
				if (result == null) result = caseElement(structType);
				if (result == null) result = caseIdentifiable(structType);
				if (result == null) result = caseFileRegion(structType);
				if (result == null) result = caseTyped(structType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.TEMPLATE_TYPE: {
				TemplateType templateType = (TemplateType)theEObject;
				T result = caseTemplateType(templateType);
				if (result == null) result = caseIdlType(templateType);
				if (result == null) result = caseFileRegion(templateType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.SEQUENCE_TYPE: {
				SequenceType sequenceType = (SequenceType)theEObject;
				T result = caseSequenceType(sequenceType);
				if (result == null) result = caseTemplateType(sequenceType);
				if (result == null) result = caseTyped(sequenceType);
				if (result == null) result = caseIdlType(sequenceType);
				if (result == null) result = caseFileRegion(sequenceType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IDL_STRING: {
				IdlString idlString = (IdlString)theEObject;
				T result = caseIdlString(idlString);
				if (result == null) result = caseTemplateType(idlString);
				if (result == null) result = caseIdlType(idlString);
				if (result == null) result = caseFileRegion(idlString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.WSTRING: {
				WString wString = (WString)theEObject;
				T result = caseWString(wString);
				if (result == null) result = caseTemplateType(wString);
				if (result == null) result = caseIdlType(wString);
				if (result == null) result = caseFileRegion(wString);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.PRIMITIVE_TYPE: {
				PrimitiveType primitiveType = (PrimitiveType)theEObject;
				T result = casePrimitiveType(primitiveType);
				if (result == null) result = caseIdlType(primitiveType);
				if (result == null) result = caseFileRegion(primitiveType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.SHORT: {
				gov.redhawk.eclipsecorba.idl.types.Short short_ = (gov.redhawk.eclipsecorba.idl.types.Short)theEObject;
				T result = caseShort(short_);
				if (result == null) result = casePrimitiveType(short_);
				if (result == null) result = caseIdlType(short_);
				if (result == null) result = caseFileRegion(short_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.LONG: {
				gov.redhawk.eclipsecorba.idl.types.Long long_ = (gov.redhawk.eclipsecorba.idl.types.Long)theEObject;
				T result = caseLong(long_);
				if (result == null) result = casePrimitiveType(long_);
				if (result == null) result = caseIdlType(long_);
				if (result == null) result = caseFileRegion(long_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.OCTET: {
				Octet octet = (Octet)theEObject;
				T result = caseOctet(octet);
				if (result == null) result = casePrimitiveType(octet);
				if (result == null) result = caseIdlType(octet);
				if (result == null) result = caseFileRegion(octet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.FLOAT: {
				gov.redhawk.eclipsecorba.idl.types.Float float_ = (gov.redhawk.eclipsecorba.idl.types.Float)theEObject;
				T result = caseFloat(float_);
				if (result == null) result = casePrimitiveType(float_);
				if (result == null) result = caseIdlType(float_);
				if (result == null) result = caseFileRegion(float_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.DOUBLE: {
				gov.redhawk.eclipsecorba.idl.types.Double double_ = (gov.redhawk.eclipsecorba.idl.types.Double)theEObject;
				T result = caseDouble(double_);
				if (result == null) result = casePrimitiveType(double_);
				if (result == null) result = caseIdlType(double_);
				if (result == null) result = caseFileRegion(double_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IDL_CHAR: {
				IdlChar idlChar = (IdlChar)theEObject;
				T result = caseIdlChar(idlChar);
				if (result == null) result = casePrimitiveType(idlChar);
				if (result == null) result = caseIdlType(idlChar);
				if (result == null) result = caseFileRegion(idlChar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IDL_WCHAR: {
				IdlWChar idlWChar = (IdlWChar)theEObject;
				T result = caseIdlWChar(idlWChar);
				if (result == null) result = casePrimitiveType(idlWChar);
				if (result == null) result = caseIdlType(idlWChar);
				if (result == null) result = caseFileRegion(idlWChar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.WCHAR: {
				WChar wChar = (WChar)theEObject;
				T result = caseWChar(wChar);
				if (result == null) result = casePrimitiveType(wChar);
				if (result == null) result = caseIdlType(wChar);
				if (result == null) result = caseFileRegion(wChar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.BOOLEAN: {
				gov.redhawk.eclipsecorba.idl.types.Boolean boolean_ = (gov.redhawk.eclipsecorba.idl.types.Boolean)theEObject;
				T result = caseBoolean(boolean_);
				if (result == null) result = casePrimitiveType(boolean_);
				if (result == null) result = caseIdlType(boolean_);
				if (result == null) result = caseFileRegion(boolean_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.LONG_LONG: {
				LongLong longLong = (LongLong)theEObject;
				T result = caseLongLong(longLong);
				if (result == null) result = casePrimitiveType(longLong);
				if (result == null) result = caseIdlType(longLong);
				if (result == null) result = caseFileRegion(longLong);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.LONG_DOUBLE: {
				LongDouble longDouble = (LongDouble)theEObject;
				T result = caseLongDouble(longDouble);
				if (result == null) result = casePrimitiveType(longDouble);
				if (result == null) result = caseIdlType(longDouble);
				if (result == null) result = caseFileRegion(longDouble);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.USHORT: {
				UShort uShort = (UShort)theEObject;
				T result = caseUShort(uShort);
				if (result == null) result = casePrimitiveType(uShort);
				if (result == null) result = caseIdlType(uShort);
				if (result == null) result = caseFileRegion(uShort);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ULONG: {
				ULong uLong = (ULong)theEObject;
				T result = caseULong(uLong);
				if (result == null) result = casePrimitiveType(uLong);
				if (result == null) result = caseIdlType(uLong);
				if (result == null) result = caseFileRegion(uLong);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ULONG_LONG: {
				ULongLong uLongLong = (ULongLong)theEObject;
				T result = caseULongLong(uLongLong);
				if (result == null) result = casePrimitiveType(uLongLong);
				if (result == null) result = caseIdlType(uLongLong);
				if (result == null) result = caseFileRegion(uLongLong);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ANY: {
				Any any = (Any)theEObject;
				T result = caseAny(any);
				if (result == null) result = casePrimitiveType(any);
				if (result == null) result = caseIdlType(any);
				if (result == null) result = caseFileRegion(any);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.IDL_OBJECT: {
				IdlObject idlObject = (IdlObject)theEObject;
				T result = caseIdlObject(idlObject);
				if (result == null) result = casePrimitiveType(idlObject);
				if (result == null) result = caseIdlType(idlObject);
				if (result == null) result = caseFileRegion(idlObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.UNION_FORWARD_DCL: {
				UnionForwardDcl unionForwardDcl = (UnionForwardDcl)theEObject;
				T result = caseUnionForwardDcl(unionForwardDcl);
				if (result == null) result = caseIdlTypeDcl(unionForwardDcl);
				if (result == null) result = caseDefinition(unionForwardDcl);
				if (result == null) result = caseIdlType(unionForwardDcl);
				if (result == null) result = caseExport(unionForwardDcl);
				if (result == null) result = caseCommentable(unionForwardDcl);
				if (result == null) result = caseTypedElement(unionForwardDcl);
				if (result == null) result = caseElement(unionForwardDcl);
				if (result == null) result = caseIdentifiable(unionForwardDcl);
				if (result == null) result = caseFileRegion(unionForwardDcl);
				if (result == null) result = caseTyped(unionForwardDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.STRUCT_FORWARD_DCL: {
				StructForwardDcl structForwardDcl = (StructForwardDcl)theEObject;
				T result = caseStructForwardDcl(structForwardDcl);
				if (result == null) result = caseIdlTypeDcl(structForwardDcl);
				if (result == null) result = caseDefinition(structForwardDcl);
				if (result == null) result = caseIdlType(structForwardDcl);
				if (result == null) result = caseExport(structForwardDcl);
				if (result == null) result = caseCommentable(structForwardDcl);
				if (result == null) result = caseTypedElement(structForwardDcl);
				if (result == null) result = caseElement(structForwardDcl);
				if (result == null) result = caseIdentifiable(structForwardDcl);
				if (result == null) result = caseFileRegion(structForwardDcl);
				if (result == null) result = caseTyped(structForwardDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.FIXED_PT_TYPE: {
				FixedPtType fixedPtType = (FixedPtType)theEObject;
				T result = caseFixedPtType(fixedPtType);
				if (result == null) result = caseTemplateType(fixedPtType);
				if (result == null) result = caseIdlType(fixedPtType);
				if (result == null) result = caseFileRegion(fixedPtType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.VALUE_BASE_TYPE: {
				ValueBaseType valueBaseType = (ValueBaseType)theEObject;
				T result = caseValueBaseType(valueBaseType);
				if (result == null) result = casePrimitiveType(valueBaseType);
				if (result == null) result = caseIdlType(valueBaseType);
				if (result == null) result = caseFileRegion(valueBaseType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TypesPackage.ENUMERATION: {
				Enumeration enumeration = (Enumeration)theEObject;
				T result = caseEnumeration(enumeration);
				if (result == null) result = caseDeclarator(enumeration);
				if (result == null) result = caseIdlTypeDcl(enumeration);
				if (result == null) result = caseDefinition(enumeration);
				if (result == null) result = caseIdlType(enumeration);
				if (result == null) result = caseExport(enumeration);
				if (result == null) result = caseIdentifiable(enumeration);
				if (result == null) result = caseFileRegion(enumeration);
				if (result == null) result = caseCommentable(enumeration);
				if (result == null) result = caseTypedElement(enumeration);
				if (result == null) result = caseElement(enumeration);
				if (result == null) result = caseTyped(enumeration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Def</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeDef(TypeDef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVoidType(VoidType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionType(UnionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Switch</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Switch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSwitch(Switch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Case</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Case</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCase(Case object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Case Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Case Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCaseLabel(CaseLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Default Case Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Default Case Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefaultCaseLabel(DefaultCaseLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Expr Case Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Expr Case Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExprCaseLabel(ExprCaseLabel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Spec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Spec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementSpec(ElementSpec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumType(EnumType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Struct Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Struct Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructType(StructType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateType(TemplateType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceType(SequenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idl String</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idl String</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlString(IdlString object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>WString</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>WString</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWString(WString object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveType(PrimitiveType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Short</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Short</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseShort(gov.redhawk.eclipsecorba.idl.types.Short object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLong(gov.redhawk.eclipsecorba.idl.types.Long object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Octet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Octet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOctet(Octet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloat(gov.redhawk.eclipsecorba.idl.types.Float object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Double</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Double</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDouble(gov.redhawk.eclipsecorba.idl.types.Double object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idl Char</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idl Char</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlChar(IdlChar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idl WChar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idl WChar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlWChar(IdlWChar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>WChar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>WChar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWChar(WChar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoolean(gov.redhawk.eclipsecorba.idl.types.Boolean object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Long</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Long</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongLong(LongLong object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Long Double</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Long Double</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLongDouble(LongDouble object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>UShort</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UShort</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUShort(UShort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ULong</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ULong</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseULong(ULong object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>ULong Long</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>ULong Long</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseULongLong(ULongLong object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAny(Any object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Idl Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Idl Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlObject(IdlObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Forward Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionForwardDcl(UnionForwardDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Struct Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Struct Forward Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructForwardDcl(StructForwardDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Fixed Pt Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Fixed Pt Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFixedPtType(FixedPtType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Base Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Base Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueBaseType(ValueBaseType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumeration(Enumeration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifiable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifiable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifiable(Identifiable object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTyped(Typed object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Commentable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Commentable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCommentable(Commentable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefinition(Definition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlType(IdlType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Export</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Export</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExport(Export object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlTypeDcl(IdlTypeDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemberContainer(MemberContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Declarator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Declarator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeclarator(Declarator object) {
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

} //TypesSwitch
