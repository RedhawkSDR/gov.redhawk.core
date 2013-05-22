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
package gov.redhawk.eclipsecorba.idl.util;

import gov.redhawk.eclipsecorba.idl.ArrayDeclarator;
import gov.redhawk.eclipsecorba.idl.BlockComment;
import gov.redhawk.eclipsecorba.idl.Comment;
import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Declarator;
import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.DefinitionContainer;
import gov.redhawk.eclipsecorba.idl.Element;
import gov.redhawk.eclipsecorba.idl.Export;
import gov.redhawk.eclipsecorba.idl.ExportContainer;
import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlConstDcl;
import gov.redhawk.eclipsecorba.idl.IdlException;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.IdlType;
import gov.redhawk.eclipsecorba.idl.IdlTypeDcl;
import gov.redhawk.eclipsecorba.idl.LineComment;
import gov.redhawk.eclipsecorba.idl.Member;
import gov.redhawk.eclipsecorba.idl.MemberContainer;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.NativeTypeDcl;
import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.Typed;
import gov.redhawk.eclipsecorba.idl.TypedElement;
import gov.redhawk.eclipsecorba.idl.ValueBoxDcl;
import gov.redhawk.eclipsecorba.idl.ValueDcl;
import gov.redhawk.eclipsecorba.idl.ValueForwardDcl;
import gov.redhawk.eclipsecorba.idl.ValueType;
import gov.redhawk.eclipsecorba.idl.ValueTypeDcl;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage
 * @generated
 */
public class IdlAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IdlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = IdlPackage.eINSTANCE;
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
	protected IdlSwitch<Adapter> modelSwitch =
		new IdlSwitch<Adapter>() {
			@Override
			public Adapter caseFileRegion(FileRegion object) {
				return createFileRegionAdapter();
			}
			@Override
			public Adapter caseIdentifiable(Identifiable object) {
				return createIdentifiableAdapter();
			}
			@Override
			public Adapter caseSpecification(Specification object) {
				return createSpecificationAdapter();
			}
			@Override
			public Adapter caseDefinition(Definition object) {
				return createDefinitionAdapter();
			}
			@Override
			public Adapter caseDefinitionContainer(DefinitionContainer object) {
				return createDefinitionContainerAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseDeclarator(Declarator object) {
				return createDeclaratorAdapter();
			}
			@Override
			public Adapter caseArrayDeclarator(ArrayDeclarator object) {
				return createArrayDeclaratorAdapter();
			}
			@Override
			public Adapter caseTypedElement(TypedElement object) {
				return createTypedElementAdapter();
			}
			@Override
			public Adapter caseTyped(Typed object) {
				return createTypedAdapter();
			}
			@Override
			public Adapter caseModule(Module object) {
				return createModuleAdapter();
			}
			@Override
			public Adapter caseIdlConstDcl(IdlConstDcl object) {
				return createIdlConstDclAdapter();
			}
			@Override
			public Adapter caseIdlException(IdlException object) {
				return createIdlExceptionAdapter();
			}
			@Override
			public Adapter caseMember(Member object) {
				return createMemberAdapter();
			}
			@Override
			public Adapter caseMemberContainer(MemberContainer object) {
				return createMemberContainerAdapter();
			}
			@Override
			public Adapter caseForwardDcl(ForwardDcl object) {
				return createForwardDclAdapter();
			}
			@Override
			public Adapter caseIdlInterfaceDcl(IdlInterfaceDcl object) {
				return createIdlInterfaceDclAdapter();
			}
			@Override
			public Adapter caseExport(Export object) {
				return createExportAdapter();
			}
			@Override
			public Adapter caseIdlType(IdlType object) {
				return createIdlTypeAdapter();
			}
			@Override
			public Adapter caseIdlTypeDcl(IdlTypeDcl object) {
				return createIdlTypeDclAdapter();
			}
			@Override
			public Adapter caseCommentable(Commentable object) {
				return createCommentableAdapter();
			}
			@Override
			public Adapter caseExportContainer(ExportContainer object) {
				return createExportContainerAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseBlockComment(BlockComment object) {
				return createBlockCommentAdapter();
			}
			@Override
			public Adapter caseLineComment(LineComment object) {
				return createLineCommentAdapter();
			}
			@Override
			public Adapter caseNativeTypeDcl(NativeTypeDcl object) {
				return createNativeTypeDclAdapter();
			}
			@Override
			public Adapter caseValueType(ValueType object) {
				return createValueTypeAdapter();
			}
			@Override
			public Adapter caseValueTypeDcl(ValueTypeDcl object) {
				return createValueTypeDclAdapter();
			}
			@Override
			public Adapter caseValueForwardDcl(ValueForwardDcl object) {
				return createValueForwardDclAdapter();
			}
			@Override
			public Adapter caseValueDcl(ValueDcl object) {
				return createValueDclAdapter();
			}
			@Override
			public Adapter caseValueBoxDcl(ValueBoxDcl object) {
				return createValueBoxDclAdapter();
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Specification
	 * @generated
	 */
	public Adapter createSpecificationAdapter() {
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.DefinitionContainer <em>Definition Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.DefinitionContainer
	 * @generated
	 */
	public Adapter createDefinitionContainerAdapter() {
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ArrayDeclarator <em>Array Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ArrayDeclarator
	 * @generated
	 */
	public Adapter createArrayDeclaratorAdapter() {
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Module
	 * @generated
	 */
	public Adapter createModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.IdlConstDcl <em>Const Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.IdlConstDcl
	 * @generated
	 */
	public Adapter createIdlConstDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.IdlException <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.IdlException
	 * @generated
	 */
	public Adapter createIdlExceptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Member
	 * @generated
	 */
	public Adapter createMemberAdapter() {
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ForwardDcl
	 * @generated
	 */
	public Adapter createForwardDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl <em>Interface Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl
	 * @generated
	 */
	public Adapter createIdlInterfaceDclAdapter() {
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
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ExportContainer <em>Export Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ExportContainer
	 * @generated
	 */
	public Adapter createExportContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.BlockComment <em>Block Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.BlockComment
	 * @generated
	 */
	public Adapter createBlockCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.LineComment <em>Line Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.LineComment
	 * @generated
	 */
	public Adapter createLineCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.NativeTypeDcl <em>Native Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.NativeTypeDcl
	 * @generated
	 */
	public Adapter createNativeTypeDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ValueType
	 * @generated
	 */
	public Adapter createValueTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ValueTypeDcl <em>Value Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ValueTypeDcl
	 * @generated
	 */
	public Adapter createValueTypeDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ValueForwardDcl <em>Value Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ValueForwardDcl
	 * @generated
	 */
	public Adapter createValueForwardDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ValueDcl <em>Value Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl
	 * @generated
	 */
	public Adapter createValueDclAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.ValueBoxDcl <em>Value Box Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.ValueBoxDcl
	 * @generated
	 */
	public Adapter createValueBoxDclAdapter() {
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

} //IdlAdapterFactory
