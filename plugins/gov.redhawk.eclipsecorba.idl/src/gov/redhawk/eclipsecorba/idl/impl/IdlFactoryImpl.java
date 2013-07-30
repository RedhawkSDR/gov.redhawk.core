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
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.ArrayDeclarator;
import gov.redhawk.eclipsecorba.idl.BlockComment;
import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Declarator;
import gov.redhawk.eclipsecorba.idl.ExportContainer;
import gov.redhawk.eclipsecorba.idl.ForwardDcl;
import gov.redhawk.eclipsecorba.idl.IdlConstDcl;
import gov.redhawk.eclipsecorba.idl.IdlException;
import gov.redhawk.eclipsecorba.idl.IdlFactory;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.LineComment;
import gov.redhawk.eclipsecorba.idl.Member;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.NativeTypeDcl;
import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.ValueBoxDcl;
import gov.redhawk.eclipsecorba.idl.ValueDcl;
import gov.redhawk.eclipsecorba.idl.ValueForwardDcl;
import gov.redhawk.eclipsecorba.idl.ValueType;
import gov.redhawk.eclipsecorba.idl.ValueTypeDcl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. 
 * @since 6.0
 * <!--end-user-doc -->
 * @generated
 */
public class IdlFactoryImpl extends EFactoryImpl implements IdlFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static IdlFactory init() {
		try {
			IdlFactory theIdlFactory = (IdlFactory)EPackage.Registry.INSTANCE.getEFactory(IdlPackage.eNS_URI);
			if (theIdlFactory != null) {
				return theIdlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new IdlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlFactoryImpl() {
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
			case IdlPackage.SPECIFICATION: return createSpecification();
			case IdlPackage.DECLARATOR: return createDeclarator();
			case IdlPackage.ARRAY_DECLARATOR: return createArrayDeclarator();
			case IdlPackage.MODULE: return createModule();
			case IdlPackage.IDL_CONST_DCL: return createIdlConstDcl();
			case IdlPackage.IDL_EXCEPTION: return createIdlException();
			case IdlPackage.MEMBER: return createMember();
			case IdlPackage.FORWARD_DCL: return createForwardDcl();
			case IdlPackage.IDL_INTERFACE_DCL: return createIdlInterfaceDcl();
			case IdlPackage.COMMENTABLE: return createCommentable();
			case IdlPackage.EXPORT_CONTAINER: return createExportContainer();
			case IdlPackage.BLOCK_COMMENT: return createBlockComment();
			case IdlPackage.LINE_COMMENT: return createLineComment();
			case IdlPackage.NATIVE_TYPE_DCL: return createNativeTypeDcl();
			case IdlPackage.VALUE_TYPE: return createValueType();
			case IdlPackage.VALUE_TYPE_DCL: return createValueTypeDcl();
			case IdlPackage.VALUE_FORWARD_DCL: return createValueForwardDcl();
			case IdlPackage.VALUE_DCL: return createValueDcl();
			case IdlPackage.VALUE_BOX_DCL: return createValueBoxDcl();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Specification createSpecification() {
		SpecificationImpl specification = new SpecificationImpl();
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Declarator createDeclarator() {
		DeclaratorImpl declarator = new DeclaratorImpl();
		return declarator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrayDeclarator createArrayDeclarator() {
		ArrayDeclaratorImpl arrayDeclarator = new ArrayDeclaratorImpl();
		return arrayDeclarator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Module createModule() {
		ModuleImpl module = new ModuleImpl();
		return module;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlConstDcl createIdlConstDcl() {
		IdlConstDclImpl idlConstDcl = new IdlConstDclImpl();
		return idlConstDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlException createIdlException() {
		IdlExceptionImpl idlException = new IdlExceptionImpl();
		return idlException;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Member createMember() {
		MemberImpl member = new MemberImpl();
		return member;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForwardDcl createForwardDcl() {
		ForwardDclImpl forwardDcl = new ForwardDclImpl();
		return forwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlInterfaceDcl createIdlInterfaceDcl() {
		IdlInterfaceDclImpl idlInterfaceDcl = new IdlInterfaceDclImpl();
		return idlInterfaceDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Commentable createCommentable() {
		CommentableImpl commentable = new CommentableImpl();
		return commentable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExportContainer createExportContainer() {
		ExportContainerImpl exportContainer = new ExportContainerImpl();
		return exportContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockComment createBlockComment() {
		BlockCommentImpl blockComment = new BlockCommentImpl();
		return blockComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LineComment createLineComment() {
		LineCommentImpl lineComment = new LineCommentImpl();
		return lineComment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NativeTypeDcl createNativeTypeDcl() {
		NativeTypeDclImpl nativeTypeDcl = new NativeTypeDclImpl();
		return nativeTypeDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueType createValueType() {
		ValueTypeImpl valueType = new ValueTypeImpl();
		return valueType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueTypeDcl createValueTypeDcl() {
		ValueTypeDclImpl valueTypeDcl = new ValueTypeDclImpl();
		return valueTypeDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueForwardDcl createValueForwardDcl() {
		ValueForwardDclImpl valueForwardDcl = new ValueForwardDclImpl();
		return valueForwardDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueDcl createValueDcl() {
		ValueDclImpl valueDcl = new ValueDclImpl();
		return valueDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueBoxDcl createValueBoxDcl() {
		ValueBoxDclImpl valueBoxDcl = new ValueBoxDclImpl();
		return valueBoxDcl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlPackage getIdlPackage() {
		return (IdlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static IdlPackage getPackage() {
		return IdlPackage.eINSTANCE;
	}

} //IdlFactoryImpl
