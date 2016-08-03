/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

// BEGIN GENERATED CODE
package gov.redhawk.core.graphiti.ui.ext;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

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
 * @see gov.redhawk.core.graphiti.ui.ext.RHGxFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel prefix='RHGx' codeFormatting='true' modelPluginVariables='org.eclipse.xtext.xbase.lib' contentTypeIdentifier='http://www.redhawk.gov/model/rhgext/1.0.0' operationReflection='false' modelDirectory='/gov.redhawk.core.graphiti.ui/src-model' basePackage='gov.redhawk.core.graphiti.ui'"
 *        annotation="http://www.eclipse.org/emf/2011/Xcore GenModel='http://www.eclipse.org/emf/2002/GenModel' Ecore='http://www.eclipse.org/emf/2002/Ecore'"
 * @generated
 */
public interface RHGxPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ext";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.redhawk.gov/model/rhgext/1.0.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ext";

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "http://www.redhawk.gov/model/rhgext/1.0.0";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RHGxPackage eINSTANCE = gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl.init();

	/**
	 * The meta object id for the '{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl <em>RH Container Shape</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl
	 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getRHContainerShape()
	 * @generated
	 */
	int RH_CONTAINER_SHAPE = 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__PROPERTIES = PictogramsPackage.CONTAINER_SHAPE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__VISIBLE = PictogramsPackage.CONTAINER_SHAPE__VISIBLE;

	/**
	 * The feature id for the '<em><b>Graphics Algorithm</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__GRAPHICS_ALGORITHM = PictogramsPackage.CONTAINER_SHAPE__GRAPHICS_ALGORITHM;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__ACTIVE = PictogramsPackage.CONTAINER_SHAPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__LINK = PictogramsPackage.CONTAINER_SHAPE__LINK;

	/**
	 * The feature id for the '<em><b>Anchors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__ANCHORS = PictogramsPackage.CONTAINER_SHAPE__ANCHORS;

	/**
	 * The feature id for the '<em><b>Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__CONTAINER = PictogramsPackage.CONTAINER_SHAPE__CONTAINER;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__CHILDREN = PictogramsPackage.CONTAINER_SHAPE__CHILDREN;

	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__STARTED = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__ENABLED = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>IStatus Severity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__ISTATUS_SEVERITY = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__COLLAPSED = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Has Super Ports Container Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Has Ports Container Shape</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Hide Unused Ports</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>RH Container Shape</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RH_CONTAINER_SHAPE_FEATURE_COUNT = PictogramsPackage.CONTAINER_SHAPE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '<em>Uses Port Stub</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.partitioning.UsesPortStub
	 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getUsesPortStub()
	 * @generated
	 */
	int USES_PORT_STUB = 1;

	/**
	 * The meta object id for the '<em>Provides Port Stub</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.partitioning.ProvidesPortStub
	 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getProvidesPortStub()
	 * @generated
	 */
	int PROVIDES_PORT_STUB = 2;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape <em>RH Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>RH Container Shape</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape
	 * @generated
	 */
	EClass getRHContainerShape();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isStarted <em>Started</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Started</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isStarted()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_Started();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isEnabled()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_Enabled();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#getIStatusSeverity <em>IStatus Severity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>IStatus Severity</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#getIStatusSeverity()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_IStatusSeverity();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isCollapsed <em>Collapsed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collapsed</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isCollapsed()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_Collapsed();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasSuperPortsContainerShape <em>Has Super Ports Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Super Ports Container Shape</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasSuperPortsContainerShape()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_HasSuperPortsContainerShape();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasPortsContainerShape <em>Has Ports Container Shape</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Ports Container Shape</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHasPortsContainerShape()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_HasPortsContainerShape();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHideUnusedPorts <em>Hide Unused Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hide Unused Ports</em>'.
	 * @see gov.redhawk.core.graphiti.ui.ext.RHContainerShape#isHideUnusedPorts()
	 * @see #getRHContainerShape()
	 * @generated
	 */
	EAttribute getRHContainerShape_HideUnusedPorts();

	/**
	 * Returns the meta object for data type '{@link mil.jpeojtrs.sca.partitioning.UsesPortStub <em>Uses Port Stub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Uses Port Stub</em>'.
	 * @see mil.jpeojtrs.sca.partitioning.UsesPortStub
	 * @model instanceClass="mil.jpeojtrs.sca.partitioning.UsesPortStub"
	 * @generated
	 */
	EDataType getUsesPortStub();

	/**
	 * Returns the meta object for data type '{@link mil.jpeojtrs.sca.partitioning.ProvidesPortStub <em>Provides Port Stub</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Provides Port Stub</em>'.
	 * @see mil.jpeojtrs.sca.partitioning.ProvidesPortStub
	 * @model instanceClass="mil.jpeojtrs.sca.partitioning.ProvidesPortStub"
	 * @generated
	 */
	EDataType getProvidesPortStub();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RHGxFactory getRHGxFactory();

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
		 * The meta object literal for the '{@link gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl <em>RH Container Shape</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHContainerShapeImpl
		 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getRHContainerShape()
		 * @generated
		 */
		EClass RH_CONTAINER_SHAPE = eINSTANCE.getRHContainerShape();

		/**
		 * The meta object literal for the '<em><b>Started</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__STARTED = eINSTANCE.getRHContainerShape_Started();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__ENABLED = eINSTANCE.getRHContainerShape_Enabled();

		/**
		 * The meta object literal for the '<em><b>IStatus Severity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__ISTATUS_SEVERITY = eINSTANCE.getRHContainerShape_IStatusSeverity();

		/**
		 * The meta object literal for the '<em><b>Collapsed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__COLLAPSED = eINSTANCE.getRHContainerShape_Collapsed();

		/**
		 * The meta object literal for the '<em><b>Has Super Ports Container Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE = eINSTANCE.getRHContainerShape_HasSuperPortsContainerShape();

		/**
		 * The meta object literal for the '<em><b>Has Ports Container Shape</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE = eINSTANCE.getRHContainerShape_HasPortsContainerShape();

		/**
		 * The meta object literal for the '<em><b>Hide Unused Ports</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS = eINSTANCE.getRHContainerShape_HideUnusedPorts();

		/**
		 * The meta object literal for the '<em>Uses Port Stub</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mil.jpeojtrs.sca.partitioning.UsesPortStub
		 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getUsesPortStub()
		 * @generated
		 */
		EDataType USES_PORT_STUB = eINSTANCE.getUsesPortStub();

		/**
		 * The meta object literal for the '<em>Provides Port Stub</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mil.jpeojtrs.sca.partitioning.ProvidesPortStub
		 * @see gov.redhawk.core.graphiti.ui.ext.impl.RHGxPackageImpl#getProvidesPortStub()
		 * @generated
		 */
		EDataType PROVIDES_PORT_STUB = eINSTANCE.getProvidesPortStub();

	}

} //RHGxPackage
