/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package mil.jpeojtrs.sca.diagram;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gmf.runtime.notation.NotationPackage;

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
 * @see mil.jpeojtrs.sca.diagram.DiagramFactory
 * @model kind="package"
 * @generated
 */
public interface DiagramPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "diagram";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://sca.jpeojtrs.mil/diagram";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "diagram";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DiagramPackage eINSTANCE = mil.jpeojtrs.sca.diagram.impl.DiagramPackageImpl.init();
	/**
	 * The meta object id for the '{@link mil.jpeojtrs.sca.diagram.impl.EObjectContainerStyleImpl <em>EObject Container Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.diagram.impl.EObjectContainerStyleImpl
	 * @see mil.jpeojtrs.sca.diagram.impl.DiagramPackageImpl#getEObjectContainerStyle()
	 * @generated
	 */
	int EOBJECT_CONTAINER_STYLE = 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER_STYLE__NAME = NotationPackage.NAMED_STYLE__NAME;
	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER_STYLE__VALUE = NotationPackage.NAMED_STYLE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>EObject Container Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_CONTAINER_STYLE_FEATURE_COUNT = NotationPackage.NAMED_STYLE_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link mil.jpeojtrs.sca.diagram.EObjectContainerStyle <em>EObject Container Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject Container Style</em>'.
	 * @see mil.jpeojtrs.sca.diagram.EObjectContainerStyle
	 * @generated
	 */
	EClass getEObjectContainerStyle();

	/**
	 * Returns the meta object for the containment reference '{@link mil.jpeojtrs.sca.diagram.EObjectContainerStyle#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see mil.jpeojtrs.sca.diagram.EObjectContainerStyle#getValue()
	 * @see #getEObjectContainerStyle()
	 * @generated
	 */
	EReference getEObjectContainerStyle_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DiagramFactory getDiagramFactory();

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
		 * The meta object literal for the '{@link mil.jpeojtrs.sca.diagram.impl.EObjectContainerStyleImpl <em>EObject Container Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mil.jpeojtrs.sca.diagram.impl.EObjectContainerStyleImpl
		 * @see mil.jpeojtrs.sca.diagram.impl.DiagramPackageImpl#getEObjectContainerStyle()
		 * @generated
		 */
		EClass EOBJECT_CONTAINER_STYLE = eINSTANCE.getEObjectContainerStyle();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_CONTAINER_STYLE__VALUE = eINSTANCE.getEObjectContainerStyle_Value();

	}

} //DiagramPackage
