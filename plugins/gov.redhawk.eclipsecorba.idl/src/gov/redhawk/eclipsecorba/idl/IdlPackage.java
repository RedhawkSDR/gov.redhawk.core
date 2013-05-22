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

import org.eclipse.emf.ecore.EAttribute;
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
 * @see gov.redhawk.eclipsecorba.idl.IdlFactory
 * @model kind="package"
 * @generated
 */
public interface IdlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "idl";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///gov/redhawk/eclipsecorba/idl.ecore";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "idl";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	IdlPackage eINSTANCE = gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.FileRegionImpl <em>File Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.FileRegionImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getFileRegion()
	 * @generated
	 */
	int FILE_REGION = 0;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REGION__START_LINE = 0;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REGION__START_COLUMN = 1;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REGION__END_LINE = 2;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REGION__END_COLUMN = 3;
	/**
	 * The number of structural features of the '<em>File Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_REGION_FEATURE_COUNT = 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl <em>Identifiable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdentifiable()
	 * @generated
	 */
	int IDENTIFIABLE = 1;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__NAME = 0;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__SCOPED_NAME = 1;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__REP_ID = 2;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__PREFIX = 3;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__VERSION = 4;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE__ID = 5;
	/**
	 * The number of structural features of the '<em>Identifiable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTIFIABLE_FEATURE_COUNT = 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.DefinitionContainerImpl <em>Definition Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.DefinitionContainerImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDefinitionContainer()
	 * @generated
	 */
	int DEFINITION_CONTAINER = 4;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER__START_LINE = FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER__START_COLUMN = FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER__END_LINE = FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER__END_COLUMN = FILE_REGION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER__DEFINITIONS = FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Definition Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_CONTAINER_FEATURE_COUNT = FILE_REGION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.SpecificationImpl <em>Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.SpecificationImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getSpecification()
	 * @generated
	 */
	int SPECIFICATION = 2;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__START_LINE = DEFINITION_CONTAINER__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__START_COLUMN = DEFINITION_CONTAINER__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__END_LINE = DEFINITION_CONTAINER__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__END_COLUMN = DEFINITION_CONTAINER__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__DEFINITIONS = DEFINITION_CONTAINER__DEFINITIONS;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__NAME = DEFINITION_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION__PREFIX = DEFINITION_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPECIFICATION_FEATURE_COUNT = DEFINITION_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ElementImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 5;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__NAME = IDENTIFIABLE__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__SCOPED_NAME = IDENTIFIABLE__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__REP_ID = IDENTIFIABLE__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__PREFIX = IDENTIFIABLE__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__VERSION = IDENTIFIABLE__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__ID = IDENTIFIABLE__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__START_LINE = IDENTIFIABLE_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__START_COLUMN = IDENTIFIABLE_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__END_LINE = IDENTIFIABLE_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__END_COLUMN = IDENTIFIABLE_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = IDENTIFIABLE_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.DefinitionImpl <em>Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.DefinitionImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDefinition()
	 * @generated
	 */
	int DEFINITION = 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__NAME = ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__SCOPED_NAME = ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__REP_ID = ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__PREFIX = ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__VERSION = ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__ID = ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__START_LINE = ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__START_COLUMN = ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__END_LINE = ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__END_COLUMN = ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION__COMMENT = ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFINITION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.DeclaratorImpl <em>Declarator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.DeclaratorImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDeclarator()
	 * @generated
	 */
	int DECLARATOR = 6;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__NAME = ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__SCOPED_NAME = ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__REP_ID = ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__PREFIX = ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__VERSION = ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__ID = ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__START_LINE = ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__START_COLUMN = ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__END_LINE = ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR__END_COLUMN = ELEMENT__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Declarator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECLARATOR_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ArrayDeclaratorImpl <em>Array Declarator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ArrayDeclaratorImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getArrayDeclarator()
	 * @generated
	 */
	int ARRAY_DECLARATOR = 7;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__NAME = DECLARATOR__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__SCOPED_NAME = DECLARATOR__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__REP_ID = DECLARATOR__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__PREFIX = DECLARATOR__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__VERSION = DECLARATOR__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__ID = DECLARATOR__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__START_LINE = DECLARATOR__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__START_COLUMN = DECLARATOR__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__END_LINE = DECLARATOR__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__END_COLUMN = DECLARATOR__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Array Size Expressions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS = DECLARATOR_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Array Declarator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_DECLARATOR_FEATURE_COUNT = DECLARATOR_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.TypedElementImpl <em>Typed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.TypedElementImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getTypedElement()
	 * @generated
	 */
	int TYPED_ELEMENT = 8;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__NAME = ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__SCOPED_NAME = ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__REP_ID = ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__PREFIX = ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__VERSION = ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__ID = ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__START_LINE = ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__START_COLUMN = ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__END_LINE = ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__END_COLUMN = ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__TYPE = ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.TypedImpl <em>Typed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.TypedImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getTyped()
	 * @generated
	 */
	int TYPED = 9;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED__TYPE = 0;
	/**
	 * The number of structural features of the '<em>Typed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ModuleImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getModule()
	 * @generated
	 */
	int MODULE = 10;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__NAME = DEFINITION__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__SCOPED_NAME = DEFINITION__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__REP_ID = DEFINITION__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__PREFIX = DEFINITION__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__VERSION = DEFINITION__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__ID = DEFINITION__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__START_LINE = DEFINITION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__START_COLUMN = DEFINITION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__END_LINE = DEFINITION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__END_COLUMN = DEFINITION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__COMMENT = DEFINITION__COMMENT;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__DEFINITIONS = DEFINITION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__EXTENDS = DEFINITION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlConstDclImpl <em>Const Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlConstDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlConstDcl()
	 * @generated
	 */
	int IDL_CONST_DCL = 11;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__NAME = TYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__SCOPED_NAME = TYPED_ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__REP_ID = TYPED_ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__PREFIX = TYPED_ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__VERSION = TYPED_ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__ID = TYPED_ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__START_LINE = TYPED_ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__START_COLUMN = TYPED_ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__END_LINE = TYPED_ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__END_COLUMN = TYPED_ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__TYPE = TYPED_ELEMENT__TYPE;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__COMMENT = TYPED_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL__EXPRESSION = TYPED_ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Const Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_CONST_DCL_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlExceptionImpl <em>Exception</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlExceptionImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlException()
	 * @generated
	 */
	int IDL_EXCEPTION = 12;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__NAME = DEFINITION__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__SCOPED_NAME = DEFINITION__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__REP_ID = DEFINITION__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__PREFIX = DEFINITION__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__VERSION = DEFINITION__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__ID = DEFINITION__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__START_LINE = DEFINITION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__START_COLUMN = DEFINITION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__END_LINE = DEFINITION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__END_COLUMN = DEFINITION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__COMMENT = DEFINITION__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__TYPE = DEFINITION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION__MEMBERS = DEFINITION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Exception</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_EXCEPTION_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.MemberImpl <em>Member</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.MemberImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getMember()
	 * @generated
	 */
	int MEMBER = 13;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__NAME = TYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__SCOPED_NAME = TYPED_ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__REP_ID = TYPED_ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__PREFIX = TYPED_ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__VERSION = TYPED_ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__ID = TYPED_ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__START_LINE = TYPED_ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__START_COLUMN = TYPED_ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__END_LINE = TYPED_ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__END_COLUMN = TYPED_ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__TYPE = TYPED_ELEMENT__TYPE;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__COMMENT = TYPED_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Declarators</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER__DECLARATORS = TYPED_ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Member</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.MemberContainerImpl <em>Member Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.MemberContainerImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getMemberContainer()
	 * @generated
	 */
	int MEMBER_CONTAINER = 14;
	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_CONTAINER__MEMBERS = 0;
	/**
	 * The number of structural features of the '<em>Member Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_CONTAINER_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlTypeDclImpl <em>Type Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlTypeDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlTypeDcl()
	 * @generated
	 */
	int IDL_TYPE_DCL = 19;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__NAME = DEFINITION__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__SCOPED_NAME = DEFINITION__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__REP_ID = DEFINITION__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__PREFIX = DEFINITION__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__VERSION = DEFINITION__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__ID = DEFINITION__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__START_LINE = DEFINITION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__START_COLUMN = DEFINITION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__END_LINE = DEFINITION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__END_COLUMN = DEFINITION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__COMMENT = DEFINITION__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL__TYPE = DEFINITION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Type Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_DCL_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl <em>Forward Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getForwardDcl()
	 * @generated
	 */
	int FORWARD_DCL = 15;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__NAME = IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__SCOPED_NAME = IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__REP_ID = IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__PREFIX = IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__VERSION = IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__ID = IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__START_LINE = IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__START_COLUMN = IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__END_LINE = IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__END_COLUMN = IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__COMMENT = IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__TYPE = IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Implementation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__IMPLEMENTATION = IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__ABSTRACT = IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL__LOCAL = IDL_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Forward Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORWARD_DCL_FEATURE_COUNT = IDL_TYPE_DCL_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl <em>Interface Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlInterfaceDcl()
	 * @generated
	 */
	int IDL_INTERFACE_DCL = 16;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__NAME = IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__SCOPED_NAME = IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__REP_ID = IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__PREFIX = IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__VERSION = IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__ID = IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__START_LINE = IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__START_COLUMN = IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__END_LINE = IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__END_COLUMN = IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__COMMENT = IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__TYPE = IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__DEFINITIONS = IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__BODY = IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Inherited Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__INHERITED_INTERFACES = IDL_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__ABSTRACT = IDL_TYPE_DCL_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__LOCAL = IDL_TYPE_DCL_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL__FORWARD_DCL = IDL_TYPE_DCL_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the '<em>Interface Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_INTERFACE_DCL_FEATURE_COUNT = IDL_TYPE_DCL_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ExportImpl <em>Export</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ExportImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getExport()
	 * @generated
	 */
	int EXPORT = 17;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__NAME = TYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__SCOPED_NAME = TYPED_ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__REP_ID = TYPED_ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__PREFIX = TYPED_ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__VERSION = TYPED_ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__ID = TYPED_ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__START_LINE = TYPED_ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__START_COLUMN = TYPED_ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__END_LINE = TYPED_ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__END_COLUMN = TYPED_ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT__TYPE = TYPED_ELEMENT__TYPE;
	/**
	 * The number of structural features of the '<em>Export</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlTypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlType()
	 * @generated
	 */
	int IDL_TYPE = 18;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE__START_LINE = FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE__START_COLUMN = FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE__END_LINE = FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE__END_COLUMN = FILE_REGION__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_TYPE_FEATURE_COUNT = FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.CommentableImpl <em>Commentable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.CommentableImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getCommentable()
	 * @generated
	 */
	int COMMENTABLE = 20;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE__COMMENT = 0;
	/**
	 * The number of structural features of the '<em>Commentable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENTABLE_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ExportContainerImpl <em>Export Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ExportContainerImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getExportContainer()
	 * @generated
	 */
	int EXPORT_CONTAINER = 21;
	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_CONTAINER__BODY = 0;
	/**
	 * The number of structural features of the '<em>Export Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPORT_CONTAINER_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.CommentImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 22;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__START_LINE = FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__START_COLUMN = FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__END_LINE = FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__END_COLUMN = FILE_REGION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__CONTENT = FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = FILE_REGION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.BlockCommentImpl <em>Block Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.BlockCommentImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getBlockComment()
	 * @generated
	 */
	int BLOCK_COMMENT = 23;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__START_LINE = COMMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__START_COLUMN = COMMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__END_LINE = COMMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__END_COLUMN = COMMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT__CONTENT = COMMENT__CONTENT;
	/**
	 * The number of structural features of the '<em>Block Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_COMMENT_FEATURE_COUNT = COMMENT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.LineCommentImpl <em>Line Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.LineCommentImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getLineComment()
	 * @generated
	 */
	int LINE_COMMENT = 24;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__START_LINE = COMMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__START_COLUMN = COMMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__END_LINE = COMMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__END_COLUMN = COMMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT__CONTENT = COMMENT__CONTENT;
	/**
	 * The number of structural features of the '<em>Line Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINE_COMMENT_FEATURE_COUNT = COMMENT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.NativeTypeDclImpl <em>Native Type Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.NativeTypeDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getNativeTypeDcl()
	 * @generated
	 */
	int NATIVE_TYPE_DCL = 25;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__NAME = IDL_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__SCOPED_NAME = IDL_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__REP_ID = IDL_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__PREFIX = IDL_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__VERSION = IDL_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__ID = IDL_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__START_LINE = IDL_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__START_COLUMN = IDL_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__END_LINE = IDL_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__END_COLUMN = IDL_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__COMMENT = IDL_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__TYPE = IDL_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Declarator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL__DECLARATOR = IDL_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Native Type Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NATIVE_TYPE_DCL_FEATURE_COUNT = IDL_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueTypeImpl <em>Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ValueTypeImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueType()
	 * @generated
	 */
	int VALUE_TYPE = 26;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__START_LINE = FILE_REGION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__START_COLUMN = FILE_REGION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__END_LINE = FILE_REGION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__END_COLUMN = FILE_REGION__END_COLUMN;
	/**
	 * The number of structural features of the '<em>Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_FEATURE_COUNT = FILE_REGION_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueTypeDclImpl <em>Value Type Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ValueTypeDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueTypeDcl()
	 * @generated
	 */
	int VALUE_TYPE_DCL = 27;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__NAME = DEFINITION__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__SCOPED_NAME = DEFINITION__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__REP_ID = DEFINITION__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__PREFIX = DEFINITION__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__VERSION = DEFINITION__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__ID = DEFINITION__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__START_LINE = DEFINITION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__START_COLUMN = DEFINITION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__END_LINE = DEFINITION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__END_COLUMN = DEFINITION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__COMMENT = DEFINITION__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL__TYPE = DEFINITION_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Value Type Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_DCL_FEATURE_COUNT = DEFINITION_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueForwardDclImpl <em>Value Forward Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ValueForwardDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueForwardDcl()
	 * @generated
	 */
	int VALUE_FORWARD_DCL = 28;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__NAME = VALUE_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__SCOPED_NAME = VALUE_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__REP_ID = VALUE_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__PREFIX = VALUE_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__VERSION = VALUE_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__ID = VALUE_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__START_LINE = VALUE_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__START_COLUMN = VALUE_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__END_LINE = VALUE_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__END_COLUMN = VALUE_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__COMMENT = VALUE_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__TYPE = VALUE_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__VALUE = VALUE_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__ABSTRACT = VALUE_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL__LOCAL = VALUE_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Value Forward Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FORWARD_DCL_FEATURE_COUNT = VALUE_TYPE_DCL_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl <em>Value Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueDcl()
	 * @generated
	 */
	int VALUE_DCL = 29;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__NAME = VALUE_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__SCOPED_NAME = VALUE_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__REP_ID = VALUE_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__PREFIX = VALUE_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__VERSION = VALUE_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__ID = VALUE_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__START_LINE = VALUE_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__START_COLUMN = VALUE_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__END_LINE = VALUE_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__END_COLUMN = VALUE_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__COMMENT = VALUE_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__TYPE = VALUE_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__BODY = VALUE_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__DEFINITIONS = VALUE_TYPE_DCL_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Inherited Values</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__INHERITED_VALUES = VALUE_TYPE_DCL_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Supports Interface</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__SUPPORTS_INTERFACE = VALUE_TYPE_DCL_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Custom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__CUSTOM = VALUE_TYPE_DCL_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Forward Dcl</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL__FORWARD_DCL = VALUE_TYPE_DCL_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the '<em>Value Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_DCL_FEATURE_COUNT = VALUE_TYPE_DCL_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueBoxDclImpl <em>Value Box Dcl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.impl.ValueBoxDclImpl
	 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueBoxDcl()
	 * @generated
	 */
	int VALUE_BOX_DCL = 30;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__NAME = VALUE_TYPE_DCL__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__SCOPED_NAME = VALUE_TYPE_DCL__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__REP_ID = VALUE_TYPE_DCL__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__PREFIX = VALUE_TYPE_DCL__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__VERSION = VALUE_TYPE_DCL__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__ID = VALUE_TYPE_DCL__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__START_LINE = VALUE_TYPE_DCL__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__START_COLUMN = VALUE_TYPE_DCL__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__END_LINE = VALUE_TYPE_DCL__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__END_COLUMN = VALUE_TYPE_DCL__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__COMMENT = VALUE_TYPE_DCL__COMMENT;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__TYPE = VALUE_TYPE_DCL__TYPE;
	/**
	 * The feature id for the '<em><b>Type Spec</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL__TYPE_SPEC = VALUE_TYPE_DCL_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Value Box Dcl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BOX_DCL_FEATURE_COUNT = VALUE_TYPE_DCL_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.FileRegion <em>File Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File Region</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion
	 * @generated
	 */
	EClass getFileRegion();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.FileRegion#getStartLine <em>Start Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Line</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion#getStartLine()
	 * @see #getFileRegion()
	 * @generated
	 */
	EAttribute getFileRegion_StartLine();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.FileRegion#getStartColumn <em>Start Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start Column</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion#getStartColumn()
	 * @see #getFileRegion()
	 * @generated
	 */
	EAttribute getFileRegion_StartColumn();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.FileRegion#getEndLine <em>End Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Line</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion#getEndLine()
	 * @see #getFileRegion()
	 * @generated
	 */
	EAttribute getFileRegion_EndLine();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.FileRegion#getEndColumn <em>End Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End Column</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion#getEndColumn()
	 * @see #getFileRegion()
	 * @generated
	 */
	EAttribute getFileRegion_EndColumn();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Identifiable</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable
	 * @generated
	 */
	EClass getIdentifiable();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getName()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName <em>Scoped Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scoped Name</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_ScopedName();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId <em>Rep Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rep Id</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getRepId()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_RepId();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Prefix();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getVersion()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Version();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getId()
	 * @see #getIdentifiable()
	 * @generated
	 */
	EAttribute getIdentifiable_Id();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Specification <em>Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Specification</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Specification
	 * @generated
	 */
	EClass getSpecification();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Specification#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Specification#getName()
	 * @see #getSpecification()
	 * @generated
	 */
	EAttribute getSpecification_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Specification#getPrefix <em>Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Prefix</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Specification#getPrefix()
	 * @see #getSpecification()
	 * @generated
	 */
	EAttribute getSpecification_Prefix();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Definition</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Definition
	 * @generated
	 */
	EClass getDefinition();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.DefinitionContainer <em>Definition Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Definition Container</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.DefinitionContainer
	 * @generated
	 */
	EClass getDefinitionContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.DefinitionContainer#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Definitions</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.DefinitionContainer#getDefinitions()
	 * @see #getDefinitionContainer()
	 * @generated
	 */
	EReference getDefinitionContainer_Definitions();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Declarator <em>Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Declarator</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Declarator
	 * @generated
	 */
	EClass getDeclarator();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ArrayDeclarator <em>Array Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array Declarator</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ArrayDeclarator
	 * @generated
	 */
	EClass getArrayDeclarator();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.ArrayDeclarator#getArraySizeExpressions <em>Array Size Expressions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Array Size Expressions</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ArrayDeclarator#getArraySizeExpressions()
	 * @see #getArrayDeclarator()
	 * @generated
	 */
	EReference getArrayDeclarator_ArraySizeExpressions();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Element</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.TypedElement
	 * @generated
	 */
	EClass getTypedElement();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Typed <em>Typed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Typed
	 * @generated
	 */
	EClass getTyped();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.Typed#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Typed#getType()
	 * @see #getTyped()
	 * @generated
	 */
	EReference getTyped_Type();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Module
	 * @generated
	 */
	EClass getModule();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.Module#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extends</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Module#getExtends()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Extends();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.IdlConstDcl <em>Const Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Const Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlConstDcl
	 * @generated
	 */
	EClass getIdlConstDcl();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.IdlConstDcl#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Expression</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlConstDcl#getExpression()
	 * @see #getIdlConstDcl()
	 * @generated
	 */
	EReference getIdlConstDcl_Expression();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.IdlException <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlException
	 * @generated
	 */
	EClass getIdlException();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Member <em>Member</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Member
	 * @generated
	 */
	EClass getMember();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.Member#getDeclarators <em>Declarators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Declarators</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Member#getDeclarators()
	 * @see #getMember()
	 * @generated
	 */
	EReference getMember_Declarators();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.MemberContainer <em>Member Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Container</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.MemberContainer
	 * @generated
	 */
	EClass getMemberContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.MemberContainer#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.MemberContainer#getMembers()
	 * @see #getMemberContainer()
	 * @generated
	 */
	EReference getMemberContainer_Members();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ForwardDcl
	 * @generated
	 */
	EClass getForwardDcl();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Implementation</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ForwardDcl#getImplementation()
	 * @see #getForwardDcl()
	 * @generated
	 */
	EReference getForwardDcl_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ForwardDcl#isAbstract()
	 * @see #getForwardDcl()
	 * @generated
	 */
	EAttribute getForwardDcl_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.ForwardDcl#isLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ForwardDcl#isLocal()
	 * @see #getForwardDcl()
	 * @generated
	 */
	EAttribute getForwardDcl_Local();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl <em>Interface Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl
	 * @generated
	 */
	EClass getIdlInterfaceDcl();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getInheritedInterfaces <em>Inherited Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inherited Interfaces</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getInheritedInterfaces()
	 * @see #getIdlInterfaceDcl()
	 * @generated
	 */
	EReference getIdlInterfaceDcl_InheritedInterfaces();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isAbstract()
	 * @see #getIdlInterfaceDcl()
	 * @generated
	 */
	EAttribute getIdlInterfaceDcl_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#isLocal()
	 * @see #getIdlInterfaceDcl()
	 * @generated
	 */
	EAttribute getIdlInterfaceDcl_Local();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl#getForwardDcl()
	 * @see #getIdlInterfaceDcl()
	 * @generated
	 */
	EReference getIdlInterfaceDcl_ForwardDcl();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Export <em>Export</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Export</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Export
	 * @generated
	 */
	EClass getExport();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.IdlType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlType
	 * @generated
	 */
	EClass getIdlType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.IdlTypeDcl <em>Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.IdlTypeDcl
	 * @generated
	 */
	EClass getIdlTypeDcl();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Commentable <em>Commentable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Commentable</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Commentable
	 * @generated
	 */
	EClass getCommentable();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.Commentable#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Comment</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Commentable#getComment()
	 * @see #getCommentable()
	 * @generated
	 */
	EReference getCommentable_Comment();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ExportContainer <em>Export Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Export Container</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ExportContainer
	 * @generated
	 */
	EClass getExportContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.ExportContainer#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ExportContainer#getBody()
	 * @see #getExportContainer()
	 * @generated
	 */
	EReference getExportContainer_Body();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.Comment#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.Comment#getContent()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Content();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.BlockComment <em>Block Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block Comment</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.BlockComment
	 * @generated
	 */
	EClass getBlockComment();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.LineComment <em>Line Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Line Comment</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.LineComment
	 * @generated
	 */
	EClass getLineComment();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.NativeTypeDcl <em>Native Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Native Type Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.NativeTypeDcl
	 * @generated
	 */
	EClass getNativeTypeDcl();

	/**
	 * Returns the meta object for the containment reference '{@link gov.redhawk.eclipsecorba.idl.NativeTypeDcl#getDeclarator <em>Declarator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Declarator</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.NativeTypeDcl#getDeclarator()
	 * @see #getNativeTypeDcl()
	 * @generated
	 */
	EReference getNativeTypeDcl_Declarator();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Type</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueType
	 * @generated
	 */
	EClass getValueType();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ValueTypeDcl <em>Value Type Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Type Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueTypeDcl
	 * @generated
	 */
	EClass getValueTypeDcl();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ValueForwardDcl <em>Value Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueForwardDcl
	 * @generated
	 */
	EClass getValueForwardDcl();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.ValueForwardDcl#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueForwardDcl#getValue()
	 * @see #getValueForwardDcl()
	 * @generated
	 */
	EReference getValueForwardDcl_Value();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.ValueForwardDcl#isAbstract <em>Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abstract</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueForwardDcl#isAbstract()
	 * @see #getValueForwardDcl()
	 * @generated
	 */
	EAttribute getValueForwardDcl_Abstract();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.ValueForwardDcl#isLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueForwardDcl#isLocal()
	 * @see #getValueForwardDcl()
	 * @generated
	 */
	EAttribute getValueForwardDcl_Local();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ValueDcl <em>Value Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl
	 * @generated
	 */
	EClass getValueDcl();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getInheritedValues <em>Inherited Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inherited Values</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl#getInheritedValues()
	 * @see #getValueDcl()
	 * @generated
	 */
	EReference getValueDcl_InheritedValues();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getSupportsInterface <em>Supports Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Supports Interface</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl#getSupportsInterface()
	 * @see #getValueDcl()
	 * @generated
	 */
	EReference getValueDcl_SupportsInterface();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#isCustom <em>Custom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Custom</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl#isCustom()
	 * @see #getValueDcl()
	 * @generated
	 */
	EAttribute getValueDcl_Custom();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.ValueDcl#getForwardDcl <em>Forward Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Forward Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueDcl#getForwardDcl()
	 * @see #getValueDcl()
	 * @generated
	 */
	EReference getValueDcl_ForwardDcl();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.ValueBoxDcl <em>Value Box Dcl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Box Dcl</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueBoxDcl
	 * @generated
	 */
	EClass getValueBoxDcl();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.eclipsecorba.idl.ValueBoxDcl#getTypeSpec <em>Type Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Spec</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.ValueBoxDcl#getTypeSpec()
	 * @see #getValueBoxDcl()
	 * @generated
	 */
	EReference getValueBoxDcl_TypeSpec();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	IdlFactory getIdlFactory();

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
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.FileRegionImpl <em>File Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.FileRegionImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getFileRegion()
		 * @generated
		 */
		EClass FILE_REGION = eINSTANCE.getFileRegion();
		/**
		 * The meta object literal for the '<em><b>Start Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REGION__START_LINE = eINSTANCE.getFileRegion_StartLine();
		/**
		 * The meta object literal for the '<em><b>Start Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REGION__START_COLUMN = eINSTANCE.getFileRegion_StartColumn();
		/**
		 * The meta object literal for the '<em><b>End Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REGION__END_LINE = eINSTANCE.getFileRegion_EndLine();
		/**
		 * The meta object literal for the '<em><b>End Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE_REGION__END_COLUMN = eINSTANCE.getFileRegion_EndColumn();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl <em>Identifiable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdentifiable()
		 * @generated
		 */
		EClass IDENTIFIABLE = eINSTANCE.getIdentifiable();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__NAME = eINSTANCE.getIdentifiable_Name();
		/**
		 * The meta object literal for the '<em><b>Scoped Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__SCOPED_NAME = eINSTANCE.getIdentifiable_ScopedName();
		/**
		 * The meta object literal for the '<em><b>Rep Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__REP_ID = eINSTANCE.getIdentifiable_RepId();
		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__PREFIX = eINSTANCE.getIdentifiable_Prefix();
		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__VERSION = eINSTANCE.getIdentifiable_Version();
		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTIFIABLE__ID = eINSTANCE.getIdentifiable_Id();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.SpecificationImpl <em>Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.SpecificationImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getSpecification()
		 * @generated
		 */
		EClass SPECIFICATION = eINSTANCE.getSpecification();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION__NAME = eINSTANCE.getSpecification_Name();
		/**
		 * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPECIFICATION__PREFIX = eINSTANCE.getSpecification_Prefix();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.DefinitionImpl <em>Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.DefinitionImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDefinition()
		 * @generated
		 */
		EClass DEFINITION = eINSTANCE.getDefinition();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.DefinitionContainerImpl <em>Definition Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.DefinitionContainerImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDefinitionContainer()
		 * @generated
		 */
		EClass DEFINITION_CONTAINER = eINSTANCE.getDefinitionContainer();
		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEFINITION_CONTAINER__DEFINITIONS = eINSTANCE.getDefinitionContainer_Definitions();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ElementImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.DeclaratorImpl <em>Declarator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.DeclaratorImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getDeclarator()
		 * @generated
		 */
		EClass DECLARATOR = eINSTANCE.getDeclarator();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ArrayDeclaratorImpl <em>Array Declarator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ArrayDeclaratorImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getArrayDeclarator()
		 * @generated
		 */
		EClass ARRAY_DECLARATOR = eINSTANCE.getArrayDeclarator();
		/**
		 * The meta object literal for the '<em><b>Array Size Expressions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY_DECLARATOR__ARRAY_SIZE_EXPRESSIONS = eINSTANCE.getArrayDeclarator_ArraySizeExpressions();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.TypedElementImpl <em>Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.TypedElementImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getTypedElement()
		 * @generated
		 */
		EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.TypedImpl <em>Typed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.TypedImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getTyped()
		 * @generated
		 */
		EClass TYPED = eINSTANCE.getTyped();
		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED__TYPE = eINSTANCE.getTyped_Type();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ModuleImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getModule()
		 * @generated
		 */
		EClass MODULE = eINSTANCE.getModule();
		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__EXTENDS = eINSTANCE.getModule_Extends();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlConstDclImpl <em>Const Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlConstDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlConstDcl()
		 * @generated
		 */
		EClass IDL_CONST_DCL = eINSTANCE.getIdlConstDcl();
		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDL_CONST_DCL__EXPRESSION = eINSTANCE.getIdlConstDcl_Expression();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlExceptionImpl <em>Exception</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlExceptionImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlException()
		 * @generated
		 */
		EClass IDL_EXCEPTION = eINSTANCE.getIdlException();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.MemberImpl <em>Member</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.MemberImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getMember()
		 * @generated
		 */
		EClass MEMBER = eINSTANCE.getMember();
		/**
		 * The meta object literal for the '<em><b>Declarators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER__DECLARATORS = eINSTANCE.getMember_Declarators();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.MemberContainerImpl <em>Member Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.MemberContainerImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getMemberContainer()
		 * @generated
		 */
		EClass MEMBER_CONTAINER = eINSTANCE.getMemberContainer();
		/**
		 * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_CONTAINER__MEMBERS = eINSTANCE.getMemberContainer_Members();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl <em>Forward Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ForwardDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getForwardDcl()
		 * @generated
		 */
		EClass FORWARD_DCL = eINSTANCE.getForwardDcl();
		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FORWARD_DCL__IMPLEMENTATION = eINSTANCE.getForwardDcl_Implementation();
		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORWARD_DCL__ABSTRACT = eINSTANCE.getForwardDcl_Abstract();
		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORWARD_DCL__LOCAL = eINSTANCE.getForwardDcl_Local();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl <em>Interface Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlInterfaceDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlInterfaceDcl()
		 * @generated
		 */
		EClass IDL_INTERFACE_DCL = eINSTANCE.getIdlInterfaceDcl();
		/**
		 * The meta object literal for the '<em><b>Inherited Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDL_INTERFACE_DCL__INHERITED_INTERFACES = eINSTANCE.getIdlInterfaceDcl_InheritedInterfaces();
		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDL_INTERFACE_DCL__ABSTRACT = eINSTANCE.getIdlInterfaceDcl_Abstract();
		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDL_INTERFACE_DCL__LOCAL = eINSTANCE.getIdlInterfaceDcl_Local();
		/**
		 * The meta object literal for the '<em><b>Forward Dcl</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDL_INTERFACE_DCL__FORWARD_DCL = eINSTANCE.getIdlInterfaceDcl_ForwardDcl();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ExportImpl <em>Export</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ExportImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getExport()
		 * @generated
		 */
		EClass EXPORT = eINSTANCE.getExport();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlTypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlType()
		 * @generated
		 */
		EClass IDL_TYPE = eINSTANCE.getIdlType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.IdlTypeDclImpl <em>Type Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlTypeDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getIdlTypeDcl()
		 * @generated
		 */
		EClass IDL_TYPE_DCL = eINSTANCE.getIdlTypeDcl();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.CommentableImpl <em>Commentable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.CommentableImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getCommentable()
		 * @generated
		 */
		EClass COMMENTABLE = eINSTANCE.getCommentable();
		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENTABLE__COMMENT = eINSTANCE.getCommentable_Comment();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ExportContainerImpl <em>Export Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ExportContainerImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getExportContainer()
		 * @generated
		 */
		EClass EXPORT_CONTAINER = eINSTANCE.getExportContainer();
		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPORT_CONTAINER__BODY = eINSTANCE.getExportContainer_Body();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.CommentImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();
		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__CONTENT = eINSTANCE.getComment_Content();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.BlockCommentImpl <em>Block Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.BlockCommentImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getBlockComment()
		 * @generated
		 */
		EClass BLOCK_COMMENT = eINSTANCE.getBlockComment();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.LineCommentImpl <em>Line Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.LineCommentImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getLineComment()
		 * @generated
		 */
		EClass LINE_COMMENT = eINSTANCE.getLineComment();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.NativeTypeDclImpl <em>Native Type Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.NativeTypeDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getNativeTypeDcl()
		 * @generated
		 */
		EClass NATIVE_TYPE_DCL = eINSTANCE.getNativeTypeDcl();
		/**
		 * The meta object literal for the '<em><b>Declarator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NATIVE_TYPE_DCL__DECLARATOR = eINSTANCE.getNativeTypeDcl_Declarator();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueTypeImpl <em>Value Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ValueTypeImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueType()
		 * @generated
		 */
		EClass VALUE_TYPE = eINSTANCE.getValueType();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueTypeDclImpl <em>Value Type Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ValueTypeDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueTypeDcl()
		 * @generated
		 */
		EClass VALUE_TYPE_DCL = eINSTANCE.getValueTypeDcl();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueForwardDclImpl <em>Value Forward Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ValueForwardDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueForwardDcl()
		 * @generated
		 */
		EClass VALUE_FORWARD_DCL = eINSTANCE.getValueForwardDcl();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_FORWARD_DCL__VALUE = eINSTANCE.getValueForwardDcl_Value();
		/**
		 * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_FORWARD_DCL__ABSTRACT = eINSTANCE.getValueForwardDcl_Abstract();
		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_FORWARD_DCL__LOCAL = eINSTANCE.getValueForwardDcl_Local();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl <em>Value Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ValueDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueDcl()
		 * @generated
		 */
		EClass VALUE_DCL = eINSTANCE.getValueDcl();
		/**
		 * The meta object literal for the '<em><b>Inherited Values</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_DCL__INHERITED_VALUES = eINSTANCE.getValueDcl_InheritedValues();
		/**
		 * The meta object literal for the '<em><b>Supports Interface</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_DCL__SUPPORTS_INTERFACE = eINSTANCE.getValueDcl_SupportsInterface();
		/**
		 * The meta object literal for the '<em><b>Custom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_DCL__CUSTOM = eINSTANCE.getValueDcl_Custom();
		/**
		 * The meta object literal for the '<em><b>Forward Dcl</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_DCL__FORWARD_DCL = eINSTANCE.getValueDcl_ForwardDcl();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.impl.ValueBoxDclImpl <em>Value Box Dcl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.impl.ValueBoxDclImpl
		 * @see gov.redhawk.eclipsecorba.idl.impl.IdlPackageImpl#getValueBoxDcl()
		 * @generated
		 */
		EClass VALUE_BOX_DCL = eINSTANCE.getValueBoxDcl();
		/**
		 * The meta object literal for the '<em><b>Type Spec</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_BOX_DCL__TYPE_SPEC = eINSTANCE.getValueBoxDcl_TypeSpec();

	}

} //IdlPackage
