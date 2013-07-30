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
package gov.redhawk.eclipsecorba.idl.types;

import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.idl.types.TypesFactory
 * @model kind="package"
 * @generated
 */
public interface TypesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "types";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///gov/redhawk/eclipsecorba/idl.types.ecore";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "types";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypesPackage eINSTANCE = gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.TypeDefImpl <em>Type Def</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypeDefImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getTypeDef()
	 * @generated
	 */
	int TYPE_DEF = 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__NAME = IdlPackage.TYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__SCOPED_NAME = IdlPackage.TYPED_ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__REP_ID = IdlPackage.TYPED_ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__PREFIX = IdlPackage.TYPED_ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__VERSION = IdlPackage.TYPED_ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__ID = IdlPackage.TYPED_ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__START_LINE = IdlPackage.TYPED_ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__START_COLUMN = IdlPackage.TYPED_ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__END_LINE = IdlPackage.TYPED_ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__END_COLUMN = IdlPackage.TYPED_ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__TYPE = IdlPackage.TYPED_ELEMENT__TYPE;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF__COMMENT = IdlPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Type Def</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_DEF_FEATURE_COUNT = IdlPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.VoidTypeImpl <em>Void Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.VoidTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getVoidType()
	 * @generated
	 */
	int VOID_TYPE = 1;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOID_TYPE__START_LINE = IdlPackage.IDL_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOID_TYPE__START_COLUMN = IdlPackage.IDL_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOID_TYPE__END_LINE = IdlPackage.IDL_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOID_TYPE__END_COLUMN = IdlPackage.IDL_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Void Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VOID_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl <em>Union Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUnionType()
	 * @generated
	 */
	int UNION_TYPE = 2;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__NAME = IdlPackage.IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__SCOPED_NAME = IdlPackage.IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__REP_ID = IdlPackage.IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__PREFIX = IdlPackage.IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__VERSION = IdlPackage.IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__ID = IdlPackage.IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__START_LINE = IdlPackage.IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__START_COLUMN = IdlPackage.IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__END_LINE = IdlPackage.IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__END_COLUMN = IdlPackage.IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__COMMENT = IdlPackage.IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__TYPE = IdlPackage.IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__FORWARD_DCL = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Idl Switch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE__IDL_SWITCH = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Union Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.SwitchImpl <em>Switch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.SwitchImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getSwitch()
	 * @generated
	 */
	int SWITCH = 3;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__START_LINE = IdlPackage.FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__START_COLUMN = IdlPackage.FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__END_LINE = IdlPackage.FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__END_COLUMN = IdlPackage.FILE_REGION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__TYPE = IdlPackage.FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Cases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH__CASES = IdlPackage.FILE_REGION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Switch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWITCH_FEATURE_COUNT = IdlPackage.FILE_REGION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl <em>Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getCase()
	 * @generated
	 */
	int CASE = 4;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__START_LINE = IdlPackage.FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__START_COLUMN = IdlPackage.FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__END_LINE = IdlPackage.FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__END_COLUMN = IdlPackage.FILE_REGION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Labels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__LABELS = IdlPackage.FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Spec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE__SPEC = IdlPackage.FILE_REGION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_FEATURE_COUNT = IdlPackage.FILE_REGION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseLabelImpl <em>Case Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.CaseLabelImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getCaseLabel()
	 * @generated
	 */
	int CASE_LABEL = 5;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_LABEL__START_LINE = IdlPackage.FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_LABEL__START_COLUMN = IdlPackage.FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_LABEL__END_LINE = IdlPackage.FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_LABEL__END_COLUMN = IdlPackage.FILE_REGION__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Case Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASE_LABEL_FEATURE_COUNT = IdlPackage.FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.DefaultCaseLabelImpl <em>Default Case Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.DefaultCaseLabelImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getDefaultCaseLabel()
	 * @generated
	 */
	int DEFAULT_CASE_LABEL = 6;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_CASE_LABEL__START_LINE = CASE_LABEL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_CASE_LABEL__START_COLUMN = CASE_LABEL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_CASE_LABEL__END_LINE = CASE_LABEL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_CASE_LABEL__END_COLUMN = CASE_LABEL__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Default Case Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_CASE_LABEL_FEATURE_COUNT = CASE_LABEL_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ExprCaseLabelImpl <em>Expr Case Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ExprCaseLabelImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getExprCaseLabel()
	 * @generated
	 */
	int EXPR_CASE_LABEL = 7;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL__START_LINE = CASE_LABEL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL__START_COLUMN = CASE_LABEL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL__END_LINE = CASE_LABEL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL__END_COLUMN = CASE_LABEL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Expr</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL__EXPR = CASE_LABEL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Expr Case Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPR_CASE_LABEL_FEATURE_COUNT = CASE_LABEL_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ElementSpecImpl <em>Element Spec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ElementSpecImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getElementSpec()
	 * @generated
	 */
	int ELEMENT_SPEC = 8;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__START_LINE = IdlPackage.FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__START_COLUMN = IdlPackage.FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__END_LINE = IdlPackage.FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__END_COLUMN = IdlPackage.FILE_REGION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__TYPE = IdlPackage.FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Declarator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC__DECLARATOR = IdlPackage.FILE_REGION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Element Spec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_SPEC_FEATURE_COUNT = IdlPackage.FILE_REGION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.EnumTypeImpl <em>Enum Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.EnumTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getEnumType()
	 * @generated
	 */
	int ENUM_TYPE = 9;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__NAME = IdlPackage.IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__SCOPED_NAME = IdlPackage.IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__REP_ID = IdlPackage.IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__PREFIX = IdlPackage.IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__VERSION = IdlPackage.IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__ID = IdlPackage.IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__START_LINE = IdlPackage.IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__START_COLUMN = IdlPackage.IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__END_LINE = IdlPackage.IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__END_COLUMN = IdlPackage.IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__COMMENT = IdlPackage.IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__TYPE = IdlPackage.IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Enumerators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE__ENUMERATORS = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Enum Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.StructTypeImpl <em>Struct Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.StructTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getStructType()
	 * @generated
	 */
	int STRUCT_TYPE = 10;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__NAME = IdlPackage.IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__SCOPED_NAME = IdlPackage.IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__REP_ID = IdlPackage.IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__PREFIX = IdlPackage.IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__VERSION = IdlPackage.IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__ID = IdlPackage.IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__START_LINE = IdlPackage.IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__START_COLUMN = IdlPackage.IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__END_LINE = IdlPackage.IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__END_COLUMN = IdlPackage.IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__COMMENT = IdlPackage.IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__TYPE = IdlPackage.IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__MEMBERS = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Forward Declaration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__FORWARD_DECLARATION = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE__FORWARD_DCL = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Struct Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.TemplateTypeImpl <em>Template Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TemplateTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getTemplateType()
	 * @generated
	 */
	int TEMPLATE_TYPE = 11;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE__START_LINE = IdlPackage.IDL_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE__START_COLUMN = IdlPackage.IDL_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE__END_LINE = IdlPackage.IDL_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE__END_COLUMN = IdlPackage.IDL_TYPE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE__SIZE = IdlPackage.IDL_TYPE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Template Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.SequenceTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getSequenceType()
	 * @generated
	 */
	int SEQUENCE_TYPE = 12;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__START_LINE = TEMPLATE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__START_COLUMN = TEMPLATE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__END_LINE = TEMPLATE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__END_COLUMN = TEMPLATE_TYPE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__SIZE = TEMPLATE_TYPE__SIZE;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE__TYPE = TEMPLATE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Sequence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEQUENCE_TYPE_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlStringImpl <em>Idl String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlStringImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlString()
	 * @generated
	 */
	int IDL_STRING = 13;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING__START_LINE = TEMPLATE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING__START_COLUMN = TEMPLATE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING__END_LINE = TEMPLATE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING__END_COLUMN = TEMPLATE_TYPE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING__SIZE = TEMPLATE_TYPE__SIZE;
	/**
	 * The number of structural features of the '<em>Idl String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_STRING_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.WStringImpl <em>WString</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.WStringImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getWString()
	 * @generated
	 */
	int WSTRING = 14;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING__START_LINE = TEMPLATE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING__START_COLUMN = TEMPLATE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING__END_LINE = TEMPLATE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING__END_COLUMN = TEMPLATE_TYPE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING__SIZE = TEMPLATE_TYPE__SIZE;
	/**
	 * The number of structural features of the '<em>WString</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WSTRING_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.PrimitiveTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getPrimitiveType()
	 * @generated
	 */
	int PRIMITIVE_TYPE = 15;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__START_LINE = IdlPackage.IDL_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__START_COLUMN = IdlPackage.IDL_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__END_LINE = IdlPackage.IDL_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE__END_COLUMN = IdlPackage.IDL_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Primitive Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRIMITIVE_TYPE_FEATURE_COUNT = IdlPackage.IDL_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ShortImpl <em>Short</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ShortImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getShort()
	 * @generated
	 */
	int SHORT = 16;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHORT__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHORT__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHORT__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHORT__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Short</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHORT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongImpl <em>Long</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLong()
	 * @generated
	 */
	int LONG = 17;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Long</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.OctetImpl <em>Octet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.OctetImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getOctet()
	 * @generated
	 */
	int OCTET = 18;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCTET__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCTET__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCTET__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCTET__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Octet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OCTET_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.FloatImpl <em>Float</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.FloatImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getFloat()
	 * @generated
	 */
	int FLOAT = 19;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Float</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOAT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.DoubleImpl <em>Double</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.DoubleImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getDouble()
	 * @generated
	 */
	int DOUBLE = 20;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Double</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlCharImpl <em>Idl Char</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlCharImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlChar()
	 * @generated
	 */
	int IDL_CHAR = 21;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CHAR__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CHAR__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CHAR__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CHAR__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Idl Char</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CHAR_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlWCharImpl <em>Idl WChar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlWCharImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlWChar()
	 * @generated
	 */
	int IDL_WCHAR = 22;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_WCHAR__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_WCHAR__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_WCHAR__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_WCHAR__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Idl WChar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_WCHAR_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.WCharImpl <em>WChar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.WCharImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getWChar()
	 * @generated
	 */
	int WCHAR = 23;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WCHAR__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WCHAR__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WCHAR__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WCHAR__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>WChar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WCHAR_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.BooleanImpl <em>Boolean</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.BooleanImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getBoolean()
	 * @generated
	 */
	int BOOLEAN = 24;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongLongImpl <em>Long Long</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongLongImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLongLong()
	 * @generated
	 */
	int LONG_LONG = 25;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_LONG__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_LONG__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_LONG__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_LONG__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Long Long</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_LONG_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongDoubleImpl <em>Long Double</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongDoubleImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLongDouble()
	 * @generated
	 */
	int LONG_DOUBLE = 26;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_DOUBLE__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_DOUBLE__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_DOUBLE__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_DOUBLE__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Long Double</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_DOUBLE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UShortImpl <em>UShort</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.UShortImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUShort()
	 * @generated
	 */
	int USHORT = 27;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USHORT__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USHORT__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USHORT__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USHORT__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>UShort</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USHORT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ULongImpl <em>ULong</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ULongImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getULong()
	 * @generated
	 */
	int ULONG = 28;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>ULong</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ULongLongImpl <em>ULong Long</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ULongLongImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getULongLong()
	 * @generated
	 */
	int ULONG_LONG = 29;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_LONG__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_LONG__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_LONG__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_LONG__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>ULong Long</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ULONG_LONG_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.AnyImpl <em>Any</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.AnyImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getAny()
	 * @generated
	 */
	int ANY = 30;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Any</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANY_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlObjectImpl <em>Idl Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlObjectImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlObject()
	 * @generated
	 */
	int IDL_OBJECT = 31;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_OBJECT__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_OBJECT__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_OBJECT__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_OBJECT__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Idl Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_OBJECT_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionForwardDclImpl <em>Union Forward Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.UnionForwardDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUnionForwardDcl()
	 * @generated
	 */
	int UNION_FORWARD_DCL = 32;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__NAME = IdlPackage.IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__SCOPED_NAME = IdlPackage.IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__REP_ID = IdlPackage.IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__PREFIX = IdlPackage.IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__VERSION = IdlPackage.IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__ID = IdlPackage.IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__START_LINE = IdlPackage.IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__START_COLUMN = IdlPackage.IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__END_LINE = IdlPackage.IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__END_COLUMN = IdlPackage.IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__COMMENT = IdlPackage.IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__TYPE = IdlPackage.IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL__IMPLEMENTATION = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Union Forward Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FORWARD_DCL_FEATURE_COUNT = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.StructForwardDclImpl <em>Struct Forward Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.StructForwardDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getStructForwardDcl()
	 * @generated
	 */
	int STRUCT_FORWARD_DCL = 33;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__NAME = IdlPackage.IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__SCOPED_NAME = IdlPackage.IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__REP_ID = IdlPackage.IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__PREFIX = IdlPackage.IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__VERSION = IdlPackage.IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__ID = IdlPackage.IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__START_LINE = IdlPackage.IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__START_COLUMN = IdlPackage.IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__END_LINE = IdlPackage.IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__END_COLUMN = IdlPackage.IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__COMMENT = IdlPackage.IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__TYPE = IdlPackage.IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL__IMPLEMENTATION = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Struct Forward Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCT_FORWARD_DCL_FEATURE_COUNT = IdlPackage.IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl <em>Fixed Pt Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getFixedPtType()
	 * @generated
	 */
	int FIXED_PT_TYPE = 34;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__START_LINE = TEMPLATE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__START_COLUMN = TEMPLATE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__END_LINE = TEMPLATE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__END_COLUMN = TEMPLATE_TYPE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Size</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__SIZE = TEMPLATE_TYPE__SIZE;
	/**
	 * The feature id for the '<em><b>Expr1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__EXPR1 = TEMPLATE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Expr2</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE__EXPR2 = TEMPLATE_TYPE_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Fixed Pt Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIXED_PT_TYPE_FEATURE_COUNT = TEMPLATE_TYPE_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ValueBaseTypeImpl <em>Value Base Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.ValueBaseTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getValueBaseType()
	 * @generated
	 */
	int VALUE_BASE_TYPE = 35;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASE_TYPE__START_LINE = PRIMITIVE_TYPE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASE_TYPE__START_COLUMN = PRIMITIVE_TYPE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASE_TYPE__END_LINE = PRIMITIVE_TYPE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASE_TYPE__END_COLUMN = PRIMITIVE_TYPE__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Value Base Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASE_TYPE_FEATURE_COUNT = PRIMITIVE_TYPE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.EnumerationImpl <em>Enumeration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.EnumerationImpl
	 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getEnumeration()
	 * @generated
	 */
	int ENUMERATION = 36;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__NAME = IdlPackage.DECLARATOR__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__SCOPED_NAME = IdlPackage.DECLARATOR__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__REP_ID = IdlPackage.DECLARATOR__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__PREFIX = IdlPackage.DECLARATOR__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__VERSION = IdlPackage.DECLARATOR__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__ID = IdlPackage.DECLARATOR__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__START_LINE = IdlPackage.DECLARATOR__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__START_COLUMN = IdlPackage.DECLARATOR__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__END_LINE = IdlPackage.DECLARATOR__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__END_COLUMN = IdlPackage.DECLARATOR__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__COMMENT = IdlPackage.DECLARATOR_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION__TYPE = IdlPackage.DECLARATOR_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Enumeration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_FEATURE_COUNT = IdlPackage.DECLARATOR_FEATURE_COUNT + 2;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.TypeDef <em>Type Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Def</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.TypeDef
	 * @generated
	 */
	EClass getTypeDef();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.VoidType <em>Void Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Void Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.VoidType
	 * @generated
	 */
	EClass getVoidType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.UnionType <em>Union Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionType
	 * @generated
	 */
	EClass getUnionType();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionType#getForwardDcl()
	 * @see #getUnionType()
	 * @generated
	 */
	EReference getUnionType_ForwardDcl();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.UnionType#getIdlSwitch <em>Idl Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Idl Switch</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionType#getIdlSwitch()
	 * @see #getUnionType()
	 * @generated
	 */
	EReference getUnionType_IdlSwitch();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Switch <em>Switch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Switch</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Switch
	 * @generated
	 */
	EClass getSwitch();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.Switch#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Switch#getType()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.types.Switch#getCases <em>Cases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cases</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Switch#getCases()
	 * @see #getSwitch()
	 * @generated
	 */
	EReference getSwitch_Cases();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Case <em>Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Case
	 * @generated
	 */
	EClass getCase();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.types.Case#getLabels <em>Labels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Labels</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Case#getLabels()
	 * @see #getCase()
	 * @generated
	 */
	EReference getCase_Labels();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.Case#getSpec <em>Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Spec</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Case#getSpec()
	 * @see #getCase()
	 * @generated
	 */
	EReference getCase_Spec();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.CaseLabel <em>Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Case Label</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.CaseLabel
	 * @generated
	 */
	EClass getCaseLabel();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel <em>Default Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Case Label</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.DefaultCaseLabel
	 * @generated
	 */
	EClass getDefaultCaseLabel();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel <em>Expr Case Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expr Case Label</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel
	 * @generated
	 */
	EClass getExprCaseLabel();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ExprCaseLabel#getExpr()
	 * @see #getExprCaseLabel()
	 * @generated
	 */
	EReference getExprCaseLabel_Expr();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.ElementSpec <em>Element Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Spec</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ElementSpec
	 * @generated
	 */
	EClass getElementSpec();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.ElementSpec#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ElementSpec#getType()
	 * @see #getElementSpec()
	 * @generated
	 */
	EReference getElementSpec_Type();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.ElementSpec#getDeclarator <em>Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declarator</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ElementSpec#getDeclarator()
	 * @see #getElementSpec()
	 * @generated
	 */
	EReference getElementSpec_Declarator();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.EnumType <em>Enum Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.EnumType
	 * @generated
	 */
	EClass getEnumType();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.types.EnumType#getEnumerators <em>Enumerators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enumerators</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.EnumType#getEnumerators()
	 * @see #getEnumType()
	 * @generated
	 */
	EReference getEnumType_Enumerators();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.StructType <em>Struct Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Struct Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructType
	 * @generated
	 */
	EClass getStructType();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDeclaration <em>Forward Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Forward Declaration</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDeclaration()
	 * @see #getStructType()
	 * @generated
	 */
	EReference getStructType_ForwardDeclaration();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructType#getForwardDcl()
	 * @see #getStructType()
	 * @generated
	 */
	EReference getStructType_ForwardDcl();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.TemplateType <em>Template Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.TemplateType
	 * @generated
	 */
	EClass getTemplateType();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.TemplateType#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Size</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.TemplateType#getSize()
	 * @see #getTemplateType()
	 * @generated
	 */
	EReference getTemplateType_Size();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.SequenceType
	 * @generated
	 */
	EClass getSequenceType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.IdlString <em>Idl String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idl String</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlString
	 * @generated
	 */
	EClass getIdlString();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.WString <em>WString</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>WString</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.WString
	 * @generated
	 */
	EClass getWString();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Short <em>Short</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Short</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Short
	 * @generated
	 */
	EClass getShort();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Long <em>Long</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Long
	 * @generated
	 */
	EClass getLong();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Octet <em>Octet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Octet</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Octet
	 * @generated
	 */
	EClass getOctet();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Float <em>Float</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Float</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Float
	 * @generated
	 */
	EClass getFloat();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Double <em>Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Double
	 * @generated
	 */
	EClass getDouble();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.IdlChar <em>Idl Char</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idl Char</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlChar
	 * @generated
	 */
	EClass getIdlChar();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.IdlWChar <em>Idl WChar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idl WChar</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlWChar
	 * @generated
	 */
	EClass getIdlWChar();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.WChar <em>WChar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>WChar</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.WChar
	 * @generated
	 */
	EClass getWChar();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Boolean
	 * @generated
	 */
	EClass getBoolean();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.LongLong <em>Long Long</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Long</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.LongLong
	 * @generated
	 */
	EClass getLongLong();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.LongDouble <em>Long Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Double</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.LongDouble
	 * @generated
	 */
	EClass getLongDouble();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.UShort <em>UShort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UShort</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UShort
	 * @generated
	 */
	EClass getUShort();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.ULong <em>ULong</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ULong</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ULong
	 * @generated
	 */
	EClass getULong();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.ULongLong <em>ULong Long</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ULong Long</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ULongLong
	 * @generated
	 */
	EClass getULongLong();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Any <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Any
	 * @generated
	 */
	EClass getAny();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.IdlObject <em>Idl Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idl Object</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.IdlObject
	 * @generated
	 */
	EClass getIdlObject();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl <em>Union Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl
	 * @generated
	 */
	EClass getUnionForwardDcl();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Implementation</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.UnionForwardDcl#getImplementation()
	 * @see #getUnionForwardDcl()
	 * @generated
	 */
	EReference getUnionForwardDcl_Implementation();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl <em>Struct Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Struct Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructForwardDcl
	 * @generated
	 */
	EClass getStructForwardDcl();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.types.StructForwardDcl#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Implementation</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.StructForwardDcl#getImplementation()
	 * @see #getStructForwardDcl()
	 * @generated
	 */
	EReference getStructForwardDcl_Implementation();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType <em>Fixed Pt Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fixed Pt Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.FixedPtType
	 * @generated
	 */
	EClass getFixedPtType();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr1 <em>Expr1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr1</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr1()
	 * @see #getFixedPtType()
	 * @generated
	 */
	EReference getFixedPtType_Expr1();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr2 <em>Expr2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expr2</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.FixedPtType#getExpr2()
	 * @see #getFixedPtType()
	 * @generated
	 */
	EReference getFixedPtType_Expr2();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.ValueBaseType <em>Value Base Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Base Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.ValueBaseType
	 * @generated
	 */
	EClass getValueBaseType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.types.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.types.Enumeration
	 * @generated
	 */
	EClass getEnumeration();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypesFactory getTypesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.TypeDefImpl <em>Type Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypeDefImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getTypeDef()
		 * @generated
		 */
		EClass TYPE_DEF = eINSTANCE.getTypeDef();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.VoidTypeImpl <em>Void Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.VoidTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getVoidType()
		 * @generated
		 */
		EClass VOID_TYPE = eINSTANCE.getVoidType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl <em>Union Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.UnionTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUnionType()
		 * @generated
		 */
		EClass UNION_TYPE = eINSTANCE.getUnionType();
		/**
		 * The meta object literal for the '<em><b>Forward Dcl</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_TYPE__FORWARD_DCL = eINSTANCE.getUnionType_ForwardDcl();
		/**
		 * The meta object literal for the '<em><b>Idl Switch</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_TYPE__IDL_SWITCH = eINSTANCE.getUnionType_IdlSwitch();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.SwitchImpl <em>Switch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.SwitchImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getSwitch()
		 * @generated
		 */
		EClass SWITCH = eINSTANCE.getSwitch();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__TYPE = eINSTANCE.getSwitch_Type();
		/**
		 * The meta object literal for the '<em><b>Cases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWITCH__CASES = eINSTANCE.getSwitch_Cases();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl <em>Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.CaseImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getCase()
		 * @generated
		 */
		EClass CASE = eINSTANCE.getCase();
		/**
		 * The meta object literal for the '<em><b>Labels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE__LABELS = eINSTANCE.getCase_Labels();
		/**
		 * The meta object literal for the '<em><b>Spec</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CASE__SPEC = eINSTANCE.getCase_Spec();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.CaseLabelImpl <em>Case Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.CaseLabelImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getCaseLabel()
		 * @generated
		 */
		EClass CASE_LABEL = eINSTANCE.getCaseLabel();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.DefaultCaseLabelImpl <em>Default Case Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.DefaultCaseLabelImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getDefaultCaseLabel()
		 * @generated
		 */
		EClass DEFAULT_CASE_LABEL = eINSTANCE.getDefaultCaseLabel();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ExprCaseLabelImpl <em>Expr Case Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ExprCaseLabelImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getExprCaseLabel()
		 * @generated
		 */
		EClass EXPR_CASE_LABEL = eINSTANCE.getExprCaseLabel();
		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPR_CASE_LABEL__EXPR = eINSTANCE.getExprCaseLabel_Expr();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ElementSpecImpl <em>Element Spec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ElementSpecImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getElementSpec()
		 * @generated
		 */
		EClass ELEMENT_SPEC = eINSTANCE.getElementSpec();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_SPEC__TYPE = eINSTANCE.getElementSpec_Type();
		/**
		 * The meta object literal for the '<em><b>Declarator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_SPEC__DECLARATOR = eINSTANCE.getElementSpec_Declarator();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.EnumTypeImpl <em>Enum Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.EnumTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getEnumType()
		 * @generated
		 */
		EClass ENUM_TYPE = eINSTANCE.getEnumType();
		/**
		 * The meta object literal for the '<em><b>Enumerators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_TYPE__ENUMERATORS = eINSTANCE.getEnumType_Enumerators();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.StructTypeImpl <em>Struct Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.StructTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getStructType()
		 * @generated
		 */
		EClass STRUCT_TYPE = eINSTANCE.getStructType();
		/**
		 * The meta object literal for the '<em><b>Forward Declaration</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCT_TYPE__FORWARD_DECLARATION = eINSTANCE.getStructType_ForwardDeclaration();
		/**
		 * The meta object literal for the '<em><b>Forward Dcl</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCT_TYPE__FORWARD_DCL = eINSTANCE.getStructType_ForwardDcl();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.TemplateTypeImpl <em>Template Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TemplateTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getTemplateType()
		 * @generated
		 */
		EClass TEMPLATE_TYPE = eINSTANCE.getTemplateType();
		/**
		 * The meta object literal for the '<em><b>Size</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_TYPE__SIZE = eINSTANCE.getTemplateType_Size();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.SequenceTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getSequenceType()
		 * @generated
		 */
		EClass SEQUENCE_TYPE = eINSTANCE.getSequenceType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlStringImpl <em>Idl String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlStringImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlString()
		 * @generated
		 */
		EClass IDL_STRING = eINSTANCE.getIdlString();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.WStringImpl <em>WString</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.WStringImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getWString()
		 * @generated
		 */
		EClass WSTRING = eINSTANCE.getWString();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.PrimitiveTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ShortImpl <em>Short</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ShortImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getShort()
		 * @generated
		 */
		EClass SHORT = eINSTANCE.getShort();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongImpl <em>Long</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLong()
		 * @generated
		 */
		EClass LONG = eINSTANCE.getLong();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.OctetImpl <em>Octet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.OctetImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getOctet()
		 * @generated
		 */
		EClass OCTET = eINSTANCE.getOctet();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.FloatImpl <em>Float</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.FloatImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getFloat()
		 * @generated
		 */
		EClass FLOAT = eINSTANCE.getFloat();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.DoubleImpl <em>Double</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.DoubleImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getDouble()
		 * @generated
		 */
		EClass DOUBLE = eINSTANCE.getDouble();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlCharImpl <em>Idl Char</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlCharImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlChar()
		 * @generated
		 */
		EClass IDL_CHAR = eINSTANCE.getIdlChar();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlWCharImpl <em>Idl WChar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlWCharImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlWChar()
		 * @generated
		 */
		EClass IDL_WCHAR = eINSTANCE.getIdlWChar();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.WCharImpl <em>WChar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.WCharImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getWChar()
		 * @generated
		 */
		EClass WCHAR = eINSTANCE.getWChar();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.BooleanImpl <em>Boolean</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.BooleanImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getBoolean()
		 * @generated
		 */
		EClass BOOLEAN = eINSTANCE.getBoolean();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongLongImpl <em>Long Long</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongLongImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLongLong()
		 * @generated
		 */
		EClass LONG_LONG = eINSTANCE.getLongLong();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.LongDoubleImpl <em>Long Double</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.LongDoubleImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getLongDouble()
		 * @generated
		 */
		EClass LONG_DOUBLE = eINSTANCE.getLongDouble();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UShortImpl <em>UShort</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.UShortImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUShort()
		 * @generated
		 */
		EClass USHORT = eINSTANCE.getUShort();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ULongImpl <em>ULong</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ULongImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getULong()
		 * @generated
		 */
		EClass ULONG = eINSTANCE.getULong();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ULongLongImpl <em>ULong Long</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ULongLongImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getULongLong()
		 * @generated
		 */
		EClass ULONG_LONG = eINSTANCE.getULongLong();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.AnyImpl <em>Any</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.AnyImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getAny()
		 * @generated
		 */
		EClass ANY = eINSTANCE.getAny();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.IdlObjectImpl <em>Idl Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.IdlObjectImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getIdlObject()
		 * @generated
		 */
		EClass IDL_OBJECT = eINSTANCE.getIdlObject();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.UnionForwardDclImpl <em>Union Forward Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.UnionForwardDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getUnionForwardDcl()
		 * @generated
		 */
		EClass UNION_FORWARD_DCL = eINSTANCE.getUnionForwardDcl();
		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_FORWARD_DCL__IMPLEMENTATION = eINSTANCE.getUnionForwardDcl_Implementation();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.StructForwardDclImpl <em>Struct Forward Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.StructForwardDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getStructForwardDcl()
		 * @generated
		 */
		EClass STRUCT_FORWARD_DCL = eINSTANCE.getStructForwardDcl();
		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCT_FORWARD_DCL__IMPLEMENTATION = eINSTANCE.getStructForwardDcl_Implementation();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl <em>Fixed Pt Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.FixedPtTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getFixedPtType()
		 * @generated
		 */
		EClass FIXED_PT_TYPE = eINSTANCE.getFixedPtType();
		/**
		 * The meta object literal for the '<em><b>Expr1</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIXED_PT_TYPE__EXPR1 = eINSTANCE.getFixedPtType_Expr1();
		/**
		 * The meta object literal for the '<em><b>Expr2</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIXED_PT_TYPE__EXPR2 = eINSTANCE.getFixedPtType_Expr2();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.ValueBaseTypeImpl <em>Value Base Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.ValueBaseTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getValueBaseType()
		 * @generated
		 */
		EClass VALUE_BASE_TYPE = eINSTANCE.getValueBaseType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.types.impl.EnumerationImpl <em>Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.EnumerationImpl
		 * @see gov.redhawk.eclipsecorba.idl.types.impl.TypesPackageImpl#getEnumeration()
		 * @generated
		 */
		EClass ENUMERATION = eINSTANCE.getEnumeration();

	}

} //TypesPackage
