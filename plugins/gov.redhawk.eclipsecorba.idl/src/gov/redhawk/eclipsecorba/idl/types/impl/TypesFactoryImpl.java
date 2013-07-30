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
package gov.redhawk.eclipsecorba.idl.types.impl;

import gov.redhawk.eclipsecorba.idl.types.Any;
import gov.redhawk.eclipsecorba.idl.types.Case;
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
import gov.redhawk.eclipsecorba.idl.types.TypeDef;
import gov.redhawk.eclipsecorba.idl.types.TypesFactory;
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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * @since 6.0
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesFactoryImpl extends EFactoryImpl implements TypesFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypesFactory init() {
		try {
			TypesFactory theTypesFactory = (TypesFactory)EPackage.Registry.INSTANCE.getEFactory(TypesPackage.eNS_URI);
			if (theTypesFactory != null) {
				return theTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesFactoryImpl() {
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
			case TypesPackage.TYPE_DEF: return createTypeDef();
			case TypesPackage.VOID_TYPE: return createVoidType();
			case TypesPackage.UNION_TYPE: return createUnionType();
			case TypesPackage.SWITCH: return createSwitch();
			case TypesPackage.CASE: return createCase();
			case TypesPackage.DEFAULT_CASE_LABEL: return createDefaultCaseLabel();
			case TypesPackage.EXPR_CASE_LABEL: return createExprCaseLabel();
			case TypesPackage.ELEMENT_SPEC: return createElementSpec();
			case TypesPackage.ENUM_TYPE: return createEnumType();
			case TypesPackage.STRUCT_TYPE: return createStructType();
			case TypesPackage.SEQUENCE_TYPE: return createSequenceType();
			case TypesPackage.IDL_STRING: return createIdlString();
			case TypesPackage.WSTRING: return createWString();
			case TypesPackage.PRIMITIVE_TYPE: return createPrimitiveType();
			case TypesPackage.SHORT: return createShort();
			case TypesPackage.LONG: return createLong();
			case TypesPackage.OCTET: return createOctet();
			case TypesPackage.FLOAT: return createFloat();
			case TypesPackage.DOUBLE: return createDouble();
			case TypesPackage.IDL_CHAR: return createIdlChar();
			case TypesPackage.IDL_WCHAR: return createIdlWChar();
			case TypesPackage.WCHAR: return createWChar();
			case TypesPackage.BOOLEAN: return createBoolean();
			case TypesPackage.LONG_LONG: return createLongLong();
			case TypesPackage.LONG_DOUBLE: return createLongDouble();
			case TypesPackage.USHORT: return createUShort();
			case TypesPackage.ULONG: return createULong();
			case TypesPackage.ULONG_LONG: return createULongLong();
			case TypesPackage.ANY: return createAny();
			case TypesPackage.IDL_OBJECT: return createIdlObject();
			case TypesPackage.UNION_FORWARD_DCL: return createUnionForwardDcl();
			case TypesPackage.STRUCT_FORWARD_DCL: return createStructForwardDcl();
			case TypesPackage.FIXED_PT_TYPE: return createFixedPtType();
			case TypesPackage.VALUE_BASE_TYPE: return createValueBaseType();
			case TypesPackage.ENUMERATION: return createEnumeration();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDef createTypeDef() {
		TypeDefImpl typeDef = new TypeDefImpl();
		return typeDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VoidType createVoidType() {
		VoidTypeImpl voidType = new VoidTypeImpl();
		return voidType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionType createUnionType() {
		UnionTypeImpl unionType = new UnionTypeImpl();
		return unionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Switch createSwitch() {
		SwitchImpl switch_ = new SwitchImpl();
		return switch_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Case createCase() {
		CaseImpl case_ = new CaseImpl();
		return case_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultCaseLabel createDefaultCaseLabel() {
		DefaultCaseLabelImpl defaultCaseLabel = new DefaultCaseLabelImpl();
		return defaultCaseLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExprCaseLabel createExprCaseLabel() {
		ExprCaseLabelImpl exprCaseLabel = new ExprCaseLabelImpl();
		return exprCaseLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementSpec createElementSpec() {
		ElementSpecImpl elementSpec = new ElementSpecImpl();
		return elementSpec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumType createEnumType() {
		EnumTypeImpl enumType = new EnumTypeImpl();
		return enumType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructType createStructType() {
		StructTypeImpl structType = new StructTypeImpl();
		return structType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceType createSequenceType() {
		SequenceTypeImpl sequenceType = new SequenceTypeImpl();
		return sequenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlString createIdlString() {
		IdlStringImpl idlString = new IdlStringImpl();
		return idlString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WString createWString() {
		WStringImpl wString = new WStringImpl();
		return wString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType createPrimitiveType() {
		PrimitiveTypeImpl primitiveType = new PrimitiveTypeImpl();
		return primitiveType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public gov.redhawk.eclipsecorba.idl.types.Short createShort() {
		ShortImpl short_ = new ShortImpl();
		return short_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public gov.redhawk.eclipsecorba.idl.types.Long createLong() {
		LongImpl long_ = new LongImpl();
		return long_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Octet createOctet() {
		OctetImpl octet = new OctetImpl();
		return octet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public gov.redhawk.eclipsecorba.idl.types.Float createFloat() {
		FloatImpl float_ = new FloatImpl();
		return float_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public gov.redhawk.eclipsecorba.idl.types.Double createDouble() {
		DoubleImpl double_ = new DoubleImpl();
		return double_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlChar createIdlChar() {
		IdlCharImpl idlChar = new IdlCharImpl();
		return idlChar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlWChar createIdlWChar() {
		IdlWCharImpl idlWChar = new IdlWCharImpl();
		return idlWChar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WChar createWChar() {
		WCharImpl wChar = new WCharImpl();
		return wChar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public gov.redhawk.eclipsecorba.idl.types.Boolean createBoolean() {
		BooleanImpl boolean_ = new BooleanImpl();
		return boolean_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongLong createLongLong() {
		LongLongImpl longLong = new LongLongImpl();
		return longLong;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LongDouble createLongDouble() {
		LongDoubleImpl longDouble = new LongDoubleImpl();
		return longDouble;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UShort createUShort() {
		UShortImpl uShort = new UShortImpl();
		return uShort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ULong createULong() {
		ULongImpl uLong = new ULongImpl();
		return uLong;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ULongLong createULongLong() {
		ULongLongImpl uLongLong = new ULongLongImpl();
		return uLongLong;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Any createAny() {
		AnyImpl any = new AnyImpl();
		return any;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlObject createIdlObject() {
		IdlObjectImpl idlObject = new IdlObjectImpl();
		return idlObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionForwardDcl createUnionForwardDcl() {
		UnionForwardDclImpl unionForwardDcl = new UnionForwardDclImpl();
		return unionForwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructForwardDcl createStructForwardDcl() {
		StructForwardDclImpl structForwardDcl = new StructForwardDclImpl();
		return structForwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixedPtType createFixedPtType() {
		FixedPtTypeImpl fixedPtType = new FixedPtTypeImpl();
		return fixedPtType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueBaseType createValueBaseType() {
		ValueBaseTypeImpl valueBaseType = new ValueBaseTypeImpl();
		return valueBaseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Enumeration createEnumeration() {
		EnumerationImpl enumeration = new EnumerationImpl();
		return enumeration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesPackage getTypesPackage() {
		return (TypesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypesPackage getPackage() {
		return TypesPackage.eINSTANCE;
	}

} //TypesFactoryImpl
