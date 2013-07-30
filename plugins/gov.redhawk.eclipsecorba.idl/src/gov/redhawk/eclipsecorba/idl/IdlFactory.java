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
package gov.redhawk.eclipsecorba.idl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage
 * @generated
 */
public interface IdlFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdlFactory eINSTANCE = gov.redhawk.eclipsecorba.idl.impl.IdlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Specification</em>'.
	 * @generated
	 */
	Specification createSpecification();

	/**
	 * Returns a new object of class '<em>Declarator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Declarator</em>'.
	 * @generated
	 */
	Declarator createDeclarator();

	/**
	 * Returns a new object of class '<em>Array Declarator</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array Declarator</em>'.
	 * @generated
	 */
	ArrayDeclarator createArrayDeclarator();

	/**
	 * Returns a new object of class '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Module</em>'.
	 * @generated
	 */
	Module createModule();

	/**
	 * Returns a new object of class '<em>Const Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Const Dcl</em>'.
	 * @generated
	 */
	IdlConstDcl createIdlConstDcl();

	/**
	 * Returns a new object of class '<em>Exception</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exception</em>'.
	 * @generated
	 */
	IdlException createIdlException();

	/**
	 * Returns a new object of class '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Member</em>'.
	 * @generated
	 */
	Member createMember();

	/**
	 * Returns a new object of class '<em>Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Forward Dcl</em>'.
	 * @generated
	 */
	ForwardDcl createForwardDcl();

	/**
	 * Returns a new object of class '<em>Interface Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interface Dcl</em>'.
	 * @generated
	 */
	IdlInterfaceDcl createIdlInterfaceDcl();

	/**
	 * Returns a new object of class '<em>Commentable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Commentable</em>'.
	 * @generated
	 */
	Commentable createCommentable();

	/**
	 * Returns a new object of class '<em>Export Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Export Container</em>'.
	 * @generated
	 */
	ExportContainer createExportContainer();

	/**
	 * Returns a new object of class '<em>Block Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block Comment</em>'.
	 * @generated
	 */
	BlockComment createBlockComment();

	/**
	 * Returns a new object of class '<em>Line Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Line Comment</em>'.
	 * @generated
	 */
	LineComment createLineComment();

	/**
	 * Returns a new object of class '<em>Native Type Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Native Type Dcl</em>'.
	 * @generated
	 */
	NativeTypeDcl createNativeTypeDcl();

	/**
	 * Returns a new object of class '<em>Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Type</em>'.
	 * @generated
	 */
	ValueType createValueType();

	/**
	 * Returns a new object of class '<em>Value Type Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Type Dcl</em>'.
	 * @generated
	 */
	ValueTypeDcl createValueTypeDcl();

	/**
	 * Returns a new object of class '<em>Value Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Forward Dcl</em>'.
	 * @generated
	 */
	ValueForwardDcl createValueForwardDcl();

	/**
	 * Returns a new object of class '<em>Value Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Dcl</em>'.
	 * @generated
	 */
	ValueDcl createValueDcl();

	/**
	 * Returns a new object of class '<em>Value Box Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Value Box Dcl</em>'.
	 * @generated
	 */
	ValueBoxDcl createValueBoxDcl();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	IdlPackage getIdlPackage();

} //IdlFactory
