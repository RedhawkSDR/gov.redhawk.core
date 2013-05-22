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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.types.TypesPackage
 * @generated
 */
public class TypesAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypesPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TypesPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypesSwitch<Adapter> modelSwitch =
		new TypesSwitch<Adapter>() {
			@Override
			public Adapter caseTypeDef(TypeDef object) {
				return createTypeDefAdapter();
			}
			@Override
			public Adapter caseVoidType(VoidType object) {
				return createVoidTypeAdapter();
			}
			@Override
			public Adapter caseUnionType(UnionType object) {
				return createUnionTypeAdapter();
			}
			@Override
			public Adapter caseSwitch(Switch object) {
				return createSwitchAdapter();
			}
			@Override
			public Adapter caseCase(Case object) {
				return createCaseAdapter();
			}
			@Override
			public Adapter caseCaseLabel(CaseLabel object) {
				return createCaseLabelAdapter();
			}
			@Override
			public Adapter caseDefaultCaseLabel(DefaultCaseLabel object) {
				return createDefaultCaseLabelAdapter();
			}
			@Override
			public Adapter caseExprCaseLabel(ExprCaseLabel object) {
				return createExprCaseLabelAdapter();
			}
			@Override
			public Adapter caseElementSpec(ElementSpec object) {
				return createElementSpecAdapter();
			}
			@Override
			public Adapter caseEnumType(EnumType object) {
				return createEnumTypeAdapter();
			}
			@Override
			public Adapter caseStructType(StructType object) {
				return createStructTypeAdapter();
			}
			@Override
			public Adapter caseTemplateType(TemplateType object) {
				return createTemplateTypeAdapter();
			}
			@Override
			public Adapter caseSequenceType(SequenceType object) {
				return createSequenceTypeAdapter();
			}
			@Override
			public Adapter caseIdlString(IdlString object) {
				return createIdlStringAdapter();
			}
			@Override
			public Adapter caseWString(WString object) {
				return createWStringAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter caseShort(gov.redhawk.eclipsecorba.idl.types.Short object) {
				return createShortAdapter();
			}
			@Override
			public Adapter caseLong(gov.redhawk.eclipsecorba.idl.types.Long object) {
				return createLongAdapter();
			}
			@Override
			public Adapter caseOctet(Octet object) {
				return createOctetAdapter();
			}
			@Override
			public Adapter caseFloat(gov.redhawk.eclipsecorba.idl.types.Float object) {
				return createFloatAdapter();
			}
			@Override
			public Adapter caseDouble(gov.redhawk.eclipsecorba.idl.types.Double object) {
				return createDoubleAdapter();
			}
			@Override
			public Adapter caseIdlChar(IdlChar object) {
				return createIdlCharAdapter();
			}
			@Override
			public Adapter caseIdlWChar(IdlWChar object) {
				return createIdlWCharAdapter();
			}
			@Override
			public Adapter caseWChar(WChar object) {
				return createWCharAdapter();
			}
			@Override
			public Adapter caseBoolean(gov.redhawk.eclipsecorba.idl.types.Boolean object) {
				return createBooleanAdapter();
			}
			@Override
			public Adapter caseLongLong(LongLong object) {
				return createLongLongAdapter();
			}
			@Override
			public Adapter caseLongDouble(LongDouble object) {
				return createLongDoubleAdapter();
			}
			@Override
			public Adapter caseUShort(UShort object) {
				return createUShortAdapter();
			}
			@Override
			public Adapter caseULong(ULong object) {
				return createULongAdapter();
			}
			@Override
			public Adapter caseULongLong(ULongLong object) {
				return createULongLongAdapter();
			}
			@Override
			public Adapter caseAny(Any object) {
				return createAnyAdapter();
			}
			@Override
			public Adapter caseIdlObject(IdlObject object) {
				return createIdlObjectAdapter();
			}
			@Override
			public Adapter caseUnionForwardDcl(UnionForwardDcl object) {
				return createUnionForwardDclAdapter();
			}
			@Override
			public Adapter caseStructForwardDcl(StructForwardDcl object) {
				return createStructForwardDclAdapter();
			}
			@Override
			public Adapter caseFixedPtType(FixedPtType object) {
				return createFixedPtTypeAdapter();
			}
			@Override
			public Adapter caseValueBaseType(ValueBaseType object) {
				return createValueBaseTypeAdapter();
			}
			@Override
			public Adapter caseEnumeration(Enumeration object) {
				return createEnumerationAdapter();
			}
			@Override
			public Adapter caseIdentifiable(Identifiable object) {
				return createIdentifiableAdapter();
			}
			@Override
			public Adapter caseFileRegion(FileRegion object) {
				return createFileRegionAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseTyped(Typed object) {
				return createTypedAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			@Override
			public Adapter caseCommentable(Commentable object) {
				return createCommentableAdapter();
			}
			@Override
			public Adapter caseDefinition(Definition object) {
				return createDefinitionAdapter();
			}
			@Override
			public Adapter caseIdlType(IdlType object) {
				return createIdlTypeAdapter();
			}
			@Override
			public Adapter caseExport(Export object) {
				return createExportAdapter();
			}
			@Override
			public Adapter caseIdlTypeDcl(IdlTypeDcl object) {
				return createIdlTypeDclAdapter();
			}
			@Override
			public Adapter caseMemberContainer(MemberContainer object) {
				return createMemberContainerAdapter();
			}
			@Override
			public Adapter caseDeclarator(Declarator object) {
				return createDeclaratorAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.TypeDef <em>Type Def</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.TypeDef
	 * @generated
	 */
	public Adapter createTypeDefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.VoidType <em>Void Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.VoidType
	 * @generated
	 */
	public Adapter createVoidTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.UnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionType
	 * @generated
	 */
	public Adapter createUnionTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Switch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Switch
	 * @generated
	 */
	public Adapter createSwitchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Case <em>Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Case
	 * @generated
	 */
	public Adapter createCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.CaseLabel <em>Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.CaseLabel
	 * @generated
	 */
	public Adapter createCaseLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel <em>Default Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel
	 * @generated
	 */
	public Adapter createDefaultCaseLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel <em>Expr Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel
	 * @generated
	 */
	public Adapter createExprCaseLabelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.ElementSpec <em>Element Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.ElementSpec
	 * @generated
	 */
	public Adapter createElementSpecAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.EnumType <em>Enum Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.EnumType
	 * @generated
	 */
	public Adapter createEnumTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.StructType <em>Struct Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructType
	 * @generated
	 */
	public Adapter createStructTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.TemplateType <em>Template Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.TemplateType
	 * @generated
	 */
	public Adapter createTemplateTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.SequenceType
	 * @generated
	 */
	public Adapter createSequenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.IdlString <em>Idl String</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlString
	 * @generated
	 */
	public Adapter createIdlStringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.WString <em>WString</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.WString
	 * @generated
	 */
	public Adapter createWStringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Short <em>Short</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Short
	 * @generated
	 */
	public Adapter createShortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Long <em>Long</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Long
	 * @generated
	 */
	public Adapter createLongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Octet <em>Octet</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Octet
	 * @generated
	 */
	public Adapter createOctetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Float <em>Float</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Float
	 * @generated
	 */
	public Adapter createFloatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Double <em>Double</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Double
	 * @generated
	 */
	public Adapter createDoubleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.IdlChar <em>Idl Char</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlChar
	 * @generated
	 */
	public Adapter createIdlCharAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.IdlWChar <em>Idl WChar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlWChar
	 * @generated
	 */
	public Adapter createIdlWCharAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.WChar <em>WChar</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.WChar
	 * @generated
	 */
	public Adapter createWCharAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Boolean
	 * @generated
	 */
	public Adapter createBooleanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.LongLong <em>Long Long</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.LongLong
	 * @generated
	 */
	public Adapter createLongLongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.LongDouble <em>Long Double</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.LongDouble
	 * @generated
	 */
	public Adapter createLongDoubleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.UShort <em>UShort</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.UShort
	 * @generated
	 */
	public Adapter createUShortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.ULong <em>ULong</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.ULong
	 * @generated
	 */
	public Adapter createULongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.ULongLong <em>ULong Long</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.ULongLong
	 * @generated
	 */
	public Adapter createULongLongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Any <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Any
	 * @generated
	 */
	public Adapter createAnyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.IdlObject <em>Idl Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlObject
	 * @generated
	 */
	public Adapter createIdlObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl <em>Union Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl
	 * @generated
	 */
	public Adapter createUnionForwardDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl <em>Struct Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructForwardDcl
	 * @generated
	 */
	public Adapter createStructForwardDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType <em>Fixed Pt Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.FixedPtType
	 * @generated
	 */
	public Adapter createFixedPtTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.ValueBaseType <em>Value Base Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.ValueBaseType
	 * @generated
	 */
	public Adapter createValueBaseTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.types.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.types.Enumeration
	 * @generated
	 */
	public Adapter createEnumerationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable
	 * @generated
	 */
	public Adapter createIdentifiableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.FileRegion <em>File Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion
	 * @generated
	 */
	public Adapter createFileRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Typed <em>Typed</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Typed
	 * @generated
	 */
	public Adapter createTypedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Commentable <em>Commentable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Commentable
	 * @generated
	 */
	public Adapter createCommentableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Definition
	 * @generated
	 */
	public Adapter createDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.IdlType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.IdlType
	 * @generated
	 */
	public Adapter createIdlTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Export <em>Export</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Export
	 * @generated
	 */
	public Adapter createExportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.IdlTypeDcl <em>Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.IdlTypeDcl
	 * @generated
	 */
	public Adapter createIdlTypeDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.MemberContainer <em>Member Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.MemberContainer
	 * @generated
	 */
	public Adapter createMemberContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Declarator <em>Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Declarator
	 * @generated
	 */
	public Adapter createDeclaratorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TypesAdapterFactory
