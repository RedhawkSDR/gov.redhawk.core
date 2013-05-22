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
package gov.redhawk.eclipsecorba.idl.operations;

import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see gov.redhawk.eclipsecorba.idl.operations.OperationsFactory
 * @model kind="package"
 * @generated
 */
public interface OperationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "operations";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///gov/redhawk/eclipsecorba/idl.operations.ecore";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "operations";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OperationsPackage eINSTANCE = gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = IdlPackage.EXPORT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__SCOPED_NAME = IdlPackage.EXPORT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__REP_ID = IdlPackage.EXPORT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PREFIX = IdlPackage.EXPORT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__VERSION = IdlPackage.EXPORT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ID = IdlPackage.EXPORT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__START_LINE = IdlPackage.EXPORT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__START_COLUMN = IdlPackage.EXPORT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__END_LINE = IdlPackage.EXPORT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__END_COLUMN = IdlPackage.EXPORT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TYPE = IdlPackage.EXPORT__TYPE;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__COMMENT = IdlPackage.EXPORT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Oneway</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ONEWAY = IdlPackage.EXPORT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PARAMETERS = IdlPackage.EXPORT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Exceptions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__EXCEPTIONS = IdlPackage.EXPORT_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__CONTEXT = IdlPackage.EXPORT_FEATURE_COUNT + 4;
	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = IdlPackage.EXPORT_FEATURE_COUNT + 5;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.ParameterImpl <em>Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.ParameterImpl
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getParameter()
	 * @generated
	 */
	int PARAMETER = 1;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__NAME = IdlPackage.TYPED_ELEMENT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__SCOPED_NAME = IdlPackage.TYPED_ELEMENT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__REP_ID = IdlPackage.TYPED_ELEMENT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__PREFIX = IdlPackage.TYPED_ELEMENT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__VERSION = IdlPackage.TYPED_ELEMENT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__ID = IdlPackage.TYPED_ELEMENT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__START_LINE = IdlPackage.TYPED_ELEMENT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__START_COLUMN = IdlPackage.TYPED_ELEMENT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__END_LINE = IdlPackage.TYPED_ELEMENT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__END_COLUMN = IdlPackage.TYPED_ELEMENT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__TYPE = IdlPackage.TYPED_ELEMENT__TYPE;
	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER__DIRECTION = IdlPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_FEATURE_COUNT = IdlPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.AttributeImpl
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 2;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__NAME = IdlPackage.EXPORT__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__SCOPED_NAME = IdlPackage.EXPORT__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__REP_ID = IdlPackage.EXPORT__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__PREFIX = IdlPackage.EXPORT__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VERSION = IdlPackage.EXPORT__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__ID = IdlPackage.EXPORT__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__START_LINE = IdlPackage.EXPORT__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__START_COLUMN = IdlPackage.EXPORT__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__END_LINE = IdlPackage.EXPORT__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__END_COLUMN = IdlPackage.EXPORT__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__TYPE = IdlPackage.EXPORT__TYPE;
	/**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__READ_ONLY = IdlPackage.EXPORT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = IdlPackage.EXPORT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.idl.operations.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.idl.operations.Direction
	 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 3;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.operations.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.operations.Operation#isOneway <em>Oneway</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Oneway</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Operation#isOneway()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_Oneway();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getParameters <em>Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameters</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Operation#getParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Parameters();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getExceptions <em>Exceptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Exceptions</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Operation#getExceptions()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Exceptions();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.idl.operations.Operation#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Operation#getContext()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Context();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.operations.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.operations.Parameter#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Parameter#getDirection()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_Direction();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.idl.operations.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.idl.operations.Attribute#isReadOnly <em>Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Only</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Attribute#isReadOnly()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_ReadOnly();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.eclipsecorba.idl.operations.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see gov.redhawk.eclipsecorba.idl.operations.Direction
	 * @generated
	 */
	EEnum getDirection();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OperationsFactory getOperationsFactory();

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
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();
		/**
		 * The meta object literal for the '<em><b>Oneway</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__ONEWAY = eINSTANCE.getOperation_Oneway();
		/**
		 * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PARAMETERS = eINSTANCE.getOperation_Parameters();
		/**
		 * The meta object literal for the '<em><b>Exceptions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__EXCEPTIONS = eINSTANCE.getOperation_Exceptions();
		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__CONTEXT = eINSTANCE.getOperation_Context();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.ParameterImpl
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();
		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__DIRECTION = eINSTANCE.getParameter_Direction();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.operations.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.AttributeImpl
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();
		/**
		 * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__READ_ONLY = eINSTANCE.getAttribute_ReadOnly();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.idl.operations.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.idl.operations.Direction
		 * @see gov.redhawk.eclipsecorba.idl.operations.impl.OperationsPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

	}

} //OperationsPackage
