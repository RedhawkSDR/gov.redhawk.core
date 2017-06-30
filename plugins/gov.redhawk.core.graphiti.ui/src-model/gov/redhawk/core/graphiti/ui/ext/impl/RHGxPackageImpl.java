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
package gov.redhawk.core.graphiti.ui.ext.impl;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.ext.RHGxFactory;
import gov.redhawk.core.graphiti.ui.ext.RHGxPackage;

import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.MmPackage;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;

import org.eclipse.graphiti.mm.algorithms.styles.StylesPackage;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RHGxPackageImpl extends EPackageImpl implements RHGxPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rhContainerShapeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType usesPortStubEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType providesPortStubEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see gov.redhawk.core.graphiti.ui.ext.RHGxPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RHGxPackageImpl() {
		super(eNS_URI, RHGxFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link RHGxPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RHGxPackage init() {
		if (isInited)
			return (RHGxPackage) EPackage.Registry.INSTANCE.getEPackage(RHGxPackage.eNS_URI);

		// Obtain or create and register package
		RHGxPackageImpl theRHGxPackage = (RHGxPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RHGxPackageImpl
			? EPackage.Registry.INSTANCE.get(eNS_URI)
			: new RHGxPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PictogramsPackage.eINSTANCE.eClass();
		MmPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		AlgorithmsPackage.eINSTANCE.eClass();
		StylesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRHGxPackage.createPackageContents();

		// Initialize created meta-data
		theRHGxPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRHGxPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RHGxPackage.eNS_URI, theRHGxPackage);
		return theRHGxPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRHContainerShape() {
		return rhContainerShapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_Started() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_Enabled() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_IStatusSeverity() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_Collapsed() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_HasSuperPortsContainerShape() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_HasPortsContainerShape() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRHContainerShape_HideUnusedPorts() {
		return (EAttribute) rhContainerShapeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getUsesPortStub() {
		return usesPortStubEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getProvidesPortStub() {
		return providesPortStubEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RHGxFactory getRHGxFactory() {
		return (RHGxFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		rhContainerShapeEClass = createEClass(RH_CONTAINER_SHAPE);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__STARTED);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__ENABLED);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__ISTATUS_SEVERITY);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__COLLAPSED);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__HAS_SUPER_PORTS_CONTAINER_SHAPE);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__HAS_PORTS_CONTAINER_SHAPE);
		createEAttribute(rhContainerShapeEClass, RH_CONTAINER_SHAPE__HIDE_UNUSED_PORTS);

		// Create data types
		usesPortStubEDataType = createEDataType(USES_PORT_STUB);
		providesPortStubEDataType = createEDataType(PROVIDES_PORT_STUB);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PictogramsPackage thePictogramsPackage = (PictogramsPackage) EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		AlgorithmsPackage theAlgorithmsPackage = (AlgorithmsPackage) EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		rhContainerShapeEClass.getESuperTypes().add(thePictogramsPackage.getContainerShape());

		// Initialize classes and features; add operations and parameters
		initEClass(rhContainerShapeEClass, RHContainerShape.class, "RHContainerShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRHContainerShape_Started(), theEcorePackage.getEBoolean(), "started", null, 0, 1, RHContainerShape.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_Enabled(), theEcorePackage.getEBoolean(), "enabled", "true", 0, 1, RHContainerShape.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_IStatusSeverity(), theEcorePackage.getEInt(), "iStatusSeverity", null, 0, 1, RHContainerShape.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_Collapsed(), theEcorePackage.getEBoolean(), "collapsed", null, 0, 1, RHContainerShape.class, IS_TRANSIENT,
			IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_HasSuperPortsContainerShape(), theEcorePackage.getEBoolean(), "hasSuperPortsContainerShape", null, 0, 1,
			RHContainerShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_HasPortsContainerShape(), theEcorePackage.getEBoolean(), "hasPortsContainerShape", null, 0, 1,
			RHContainerShape.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRHContainerShape_HideUnusedPorts(), theEcorePackage.getEBoolean(), "hideUnusedPorts", null, 0, 1, RHContainerShape.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, null, "init", 0, 1, !IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(rhContainerShapeEClass, null, "getProvidesPortStubs", 0, 1, !IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(theEcorePackage.getEEList());
		EGenericType g2 = createEGenericType(this.getProvidesPortStub());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(rhContainerShapeEClass, null, "getUsesPortStubs", 0, 1, !IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(theEcorePackage.getEEList());
		g2 = createEGenericType(this.getUsesPortStub());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		addEOperation(rhContainerShapeEClass, theEcorePackage.getEBoolean(), "update", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, theAlgorithmsPackage.getText(), "getOuterText", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, theAlgorithmsPackage.getImage(), "getOuterImage", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, theAlgorithmsPackage.getImage(), "getInnerImage", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, theAlgorithmsPackage.getText(), "getInnerText", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, theAlgorithmsPackage.getPolyline(), "getInnerPolyline", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getInnerContainerShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getLollipop", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getProvidesPortsContainerShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getSuperProvidesPortsContainerShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getUsesPortsContainerShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(rhContainerShapeEClass, thePictogramsPackage.getContainerShape(), "getSuperUsesPortsContainerShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		// Initialize data types
		initEDataType(usesPortStubEDataType, UsesPortStub.class, "UsesPortStub", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(providesPortStubEDataType, ProvidesPortStub.class, "ProvidesPortStub", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2011/Xcore
		createXcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2011/Xcore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createXcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2011/Xcore";
		addAnnotation(this, source, new String[] { "GenModel", "http://www.eclipse.org/emf/2002/GenModel", "Ecore", "http://www.eclipse.org/emf/2002/Ecore" });
	}

} //RHGxPackageImpl
