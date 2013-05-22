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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.IdlPackage
 * @generated
 */
public class IdlSwitch< T > {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static IdlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdlSwitch() {
		if (modelPackage == null) {
			modelPackage = IdlPackage.eINSTANCE;
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
			case IdlPackage.FILE_REGION: {
				FileRegion fileRegion = (FileRegion)theEObject;
				T result = caseFileRegion(fileRegion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDENTIFIABLE: {
				Identifiable identifiable = (Identifiable)theEObject;
				T result = caseIdentifiable(identifiable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.SPECIFICATION: {
				Specification specification = (Specification)theEObject;
				T result = caseSpecification(specification);
				if (result == null) result = caseDefinitionContainer(specification);
				if (result == null) result = caseFileRegion(specification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.DEFINITION: {
				Definition definition = (Definition)theEObject;
				T result = caseDefinition(definition);
				if (result == null) result = caseElement(definition);
				if (result == null) result = caseCommentable(definition);
				if (result == null) result = caseIdentifiable(definition);
				if (result == null) result = caseFileRegion(definition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.DEFINITION_CONTAINER: {
				DefinitionContainer definitionContainer = (DefinitionContainer)theEObject;
				T result = caseDefinitionContainer(definitionContainer);
				if (result == null) result = caseFileRegion(definitionContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.ELEMENT: {
				Element element = (Element)theEObject;
				T result = caseElement(element);
				if (result == null) result = caseIdentifiable(element);
				if (result == null) result = caseFileRegion(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.DECLARATOR: {
				Declarator declarator = (Declarator)theEObject;
				T result = caseDeclarator(declarator);
				if (result == null) result = caseElement(declarator);
				if (result == null) result = caseIdentifiable(declarator);
				if (result == null) result = caseFileRegion(declarator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.ARRAY_DECLARATOR: {
				ArrayDeclarator arrayDeclarator = (ArrayDeclarator)theEObject;
				T result = caseArrayDeclarator(arrayDeclarator);
				if (result == null) result = caseDeclarator(arrayDeclarator);
				if (result == null) result = caseElement(arrayDeclarator);
				if (result == null) result = caseIdentifiable(arrayDeclarator);
				if (result == null) result = caseFileRegion(arrayDeclarator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.TYPED_ELEMENT: {
				TypedElement typedElement = (TypedElement)theEObject;
				T result = caseTypedElement(typedElement);
				if (result == null) result = caseElement(typedElement);
				if (result == null) result = caseTyped(typedElement);
				if (result == null) result = caseIdentifiable(typedElement);
				if (result == null) result = caseFileRegion(typedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.TYPED: {
				Typed typed = (Typed)theEObject;
				T result = caseTyped(typed);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.MODULE: {
				Module module = (Module)theEObject;
				T result = caseModule(module);
				if (result == null) result = caseDefinition(module);
				if (result == null) result = caseDefinitionContainer(module);
				if (result == null) result = caseElement(module);
				if (result == null) result = caseCommentable(module);
				if (result == null) result = caseIdentifiable(module);
				if (result == null) result = caseFileRegion(module);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDL_CONST_DCL: {
				IdlConstDcl idlConstDcl = (IdlConstDcl)theEObject;
				T result = caseIdlConstDcl(idlConstDcl);
				if (result == null) result = caseDefinition(idlConstDcl);
				if (result == null) result = caseExport(idlConstDcl);
				if (result == null) result = caseTypedElement(idlConstDcl);
				if (result == null) result = caseElement(idlConstDcl);
				if (result == null) result = caseTyped(idlConstDcl);
				if (result == null) result = caseCommentable(idlConstDcl);
				if (result == null) result = caseIdentifiable(idlConstDcl);
				if (result == null) result = caseFileRegion(idlConstDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDL_EXCEPTION: {
				IdlException idlException = (IdlException)theEObject;
				T result = caseIdlException(idlException);
				if (result == null) result = caseDefinition(idlException);
				if (result == null) result = caseExport(idlException);
				if (result == null) result = caseMemberContainer(idlException);
				if (result == null) result = caseCommentable(idlException);
				if (result == null) result = caseTypedElement(idlException);
				if (result == null) result = caseElement(idlException);
				if (result == null) result = caseIdentifiable(idlException);
				if (result == null) result = caseFileRegion(idlException);
				if (result == null) result = caseTyped(idlException);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.MEMBER: {
				Member member = (Member)theEObject;
				T result = caseMember(member);
				if (result == null) result = caseTypedElement(member);
				if (result == null) result = caseCommentable(member);
				if (result == null) result = caseElement(member);
				if (result == null) result = caseTyped(member);
				if (result == null) result = caseIdentifiable(member);
				if (result == null) result = caseFileRegion(member);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.MEMBER_CONTAINER: {
				MemberContainer memberContainer = (MemberContainer)theEObject;
				T result = caseMemberContainer(memberContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.FORWARD_DCL: {
				ForwardDcl forwardDcl = (ForwardDcl)theEObject;
				T result = caseForwardDcl(forwardDcl);
				if (result == null) result = caseIdlTypeDcl(forwardDcl);
				if (result == null) result = caseDefinition(forwardDcl);
				if (result == null) result = caseIdlType(forwardDcl);
				if (result == null) result = caseExport(forwardDcl);
				if (result == null) result = caseCommentable(forwardDcl);
				if (result == null) result = caseTypedElement(forwardDcl);
				if (result == null) result = caseElement(forwardDcl);
				if (result == null) result = caseIdentifiable(forwardDcl);
				if (result == null) result = caseFileRegion(forwardDcl);
				if (result == null) result = caseTyped(forwardDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDL_INTERFACE_DCL: {
				IdlInterfaceDcl idlInterfaceDcl = (IdlInterfaceDcl)theEObject;
				T result = caseIdlInterfaceDcl(idlInterfaceDcl);
				if (result == null) result = caseIdlTypeDcl(idlInterfaceDcl);
				if (result == null) result = caseDefinitionContainer(idlInterfaceDcl);
				if (result == null) result = caseExportContainer(idlInterfaceDcl);
				if (result == null) result = caseDefinition(idlInterfaceDcl);
				if (result == null) result = caseIdlType(idlInterfaceDcl);
				if (result == null) result = caseExport(idlInterfaceDcl);
				if (result == null) result = caseCommentable(idlInterfaceDcl);
				if (result == null) result = caseTypedElement(idlInterfaceDcl);
				if (result == null) result = caseElement(idlInterfaceDcl);
				if (result == null) result = caseIdentifiable(idlInterfaceDcl);
				if (result == null) result = caseFileRegion(idlInterfaceDcl);
				if (result == null) result = caseTyped(idlInterfaceDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.EXPORT: {
				Export export = (Export)theEObject;
				T result = caseExport(export);
				if (result == null) result = caseTypedElement(export);
				if (result == null) result = caseElement(export);
				if (result == null) result = caseTyped(export);
				if (result == null) result = caseIdentifiable(export);
				if (result == null) result = caseFileRegion(export);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDL_TYPE: {
				IdlType idlType = (IdlType)theEObject;
				T result = caseIdlType(idlType);
				if (result == null) result = caseFileRegion(idlType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.IDL_TYPE_DCL: {
				IdlTypeDcl idlTypeDcl = (IdlTypeDcl)theEObject;
				T result = caseIdlTypeDcl(idlTypeDcl);
				if (result == null) result = caseDefinition(idlTypeDcl);
				if (result == null) result = caseIdlType(idlTypeDcl);
				if (result == null) result = caseExport(idlTypeDcl);
				if (result == null) result = caseCommentable(idlTypeDcl);
				if (result == null) result = caseTypedElement(idlTypeDcl);
				if (result == null) result = caseElement(idlTypeDcl);
				if (result == null) result = caseIdentifiable(idlTypeDcl);
				if (result == null) result = caseFileRegion(idlTypeDcl);
				if (result == null) result = caseTyped(idlTypeDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.COMMENTABLE: {
				Commentable commentable = (Commentable)theEObject;
				T result = caseCommentable(commentable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.EXPORT_CONTAINER: {
				ExportContainer exportContainer = (ExportContainer)theEObject;
				T result = caseExportContainer(exportContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				T result = caseComment(comment);
				if (result == null) result = caseFileRegion(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.BLOCK_COMMENT: {
				BlockComment blockComment = (BlockComment)theEObject;
				T result = caseBlockComment(blockComment);
				if (result == null) result = caseComment(blockComment);
				if (result == null) result = caseFileRegion(blockComment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.LINE_COMMENT: {
				LineComment lineComment = (LineComment)theEObject;
				T result = caseLineComment(lineComment);
				if (result == null) result = caseComment(lineComment);
				if (result == null) result = caseFileRegion(lineComment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.NATIVE_TYPE_DCL: {
				NativeTypeDcl nativeTypeDcl = (NativeTypeDcl)theEObject;
				T result = caseNativeTypeDcl(nativeTypeDcl);
				if (result == null) result = caseIdlTypeDcl(nativeTypeDcl);
				if (result == null) result = caseDefinition(nativeTypeDcl);
				if (result == null) result = caseIdlType(nativeTypeDcl);
				if (result == null) result = caseExport(nativeTypeDcl);
				if (result == null) result = caseCommentable(nativeTypeDcl);
				if (result == null) result = caseTypedElement(nativeTypeDcl);
				if (result == null) result = caseElement(nativeTypeDcl);
				if (result == null) result = caseIdentifiable(nativeTypeDcl);
				if (result == null) result = caseFileRegion(nativeTypeDcl);
				if (result == null) result = caseTyped(nativeTypeDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.VALUE_TYPE: {
				ValueType valueType = (ValueType)theEObject;
				T result = caseValueType(valueType);
				if (result == null) result = caseFileRegion(valueType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.VALUE_TYPE_DCL: {
				ValueTypeDcl valueTypeDcl = (ValueTypeDcl)theEObject;
				T result = caseValueTypeDcl(valueTypeDcl);
				if (result == null) result = caseDefinition(valueTypeDcl);
				if (result == null) result = caseValueType(valueTypeDcl);
				if (result == null) result = caseExport(valueTypeDcl);
				if (result == null) result = caseCommentable(valueTypeDcl);
				if (result == null) result = caseTypedElement(valueTypeDcl);
				if (result == null) result = caseElement(valueTypeDcl);
				if (result == null) result = caseIdentifiable(valueTypeDcl);
				if (result == null) result = caseFileRegion(valueTypeDcl);
				if (result == null) result = caseTyped(valueTypeDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.VALUE_FORWARD_DCL: {
				ValueForwardDcl valueForwardDcl = (ValueForwardDcl)theEObject;
				T result = caseValueForwardDcl(valueForwardDcl);
				if (result == null) result = caseValueTypeDcl(valueForwardDcl);
				if (result == null) result = caseDefinition(valueForwardDcl);
				if (result == null) result = caseValueType(valueForwardDcl);
				if (result == null) result = caseExport(valueForwardDcl);
				if (result == null) result = caseCommentable(valueForwardDcl);
				if (result == null) result = caseTypedElement(valueForwardDcl);
				if (result == null) result = caseElement(valueForwardDcl);
				if (result == null) result = caseIdentifiable(valueForwardDcl);
				if (result == null) result = caseFileRegion(valueForwardDcl);
				if (result == null) result = caseTyped(valueForwardDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.VALUE_DCL: {
				ValueDcl valueDcl = (ValueDcl)theEObject;
				T result = caseValueDcl(valueDcl);
				if (result == null) result = caseValueTypeDcl(valueDcl);
				if (result == null) result = caseExportContainer(valueDcl);
				if (result == null) result = caseDefinitionContainer(valueDcl);
				if (result == null) result = caseDefinition(valueDcl);
				if (result == null) result = caseValueType(valueDcl);
				if (result == null) result = caseExport(valueDcl);
				if (result == null) result = caseCommentable(valueDcl);
				if (result == null) result = caseTypedElement(valueDcl);
				if (result == null) result = caseElement(valueDcl);
				if (result == null) result = caseIdentifiable(valueDcl);
				if (result == null) result = caseFileRegion(valueDcl);
				if (result == null) result = caseTyped(valueDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case IdlPackage.VALUE_BOX_DCL: {
				ValueBoxDcl valueBoxDcl = (ValueBoxDcl)theEObject;
				T result = caseValueBoxDcl(valueBoxDcl);
				if (result == null) result = caseValueTypeDcl(valueBoxDcl);
				if (result == null) result = caseDefinition(valueBoxDcl);
				if (result == null) result = caseValueType(valueBoxDcl);
				if (result == null) result = caseExport(valueBoxDcl);
				if (result == null) result = caseCommentable(valueBoxDcl);
				if (result == null) result = caseTypedElement(valueBoxDcl);
				if (result == null) result = caseElement(valueBoxDcl);
				if (result == null) result = caseIdentifiable(valueBoxDcl);
				if (result == null) result = caseFileRegion(valueBoxDcl);
				if (result == null) result = caseTyped(valueBoxDcl);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
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
	 * Returns the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecification(Specification object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Definition Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Definition Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefinitionContainer(DefinitionContainer object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Array Declarator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Array Declarator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArrayDeclarator(ArrayDeclarator object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModule(Module object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Const Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Const Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlConstDcl(IdlConstDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exception</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exception</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlException(IdlException object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Member</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Member</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMember(Member object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Forward Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForwardDcl(ForwardDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interface Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interface Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdlInterfaceDcl(IdlInterfaceDcl object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Export Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Export Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExportContainer(ExportContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlockComment(BlockComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Line Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Line Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLineComment(LineComment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Native Type Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Native Type Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNativeTypeDcl(NativeTypeDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueType(ValueType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Type Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Type Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueTypeDcl(ValueTypeDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Forward Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Forward Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueForwardDcl(ValueForwardDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueDcl(ValueDcl object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Box Dcl</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Box Dcl</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueBoxDcl(ValueBoxDcl object) {
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

} //IdlSwitch
