/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */

// BEGIN GENERATED CODE
package gov.redhawk.core.graphiti.sad.ui.ext.impl;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxFactory;
import gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxPackage;

import gov.redhawk.core.graphiti.ui.ext.RHGxPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.graphiti.mm.algorithms.AlgorithmsPackage;

import org.eclipse.graphiti.mm.pictograms.PictogramsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RHSadGxPackageImpl extends EPackageImpl implements RHSadGxPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentShapeEClass = null;

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
	 * @see gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RHSadGxPackageImpl() {
		super(eNS_URI, RHSadGxFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RHSadGxPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RHSadGxPackage init() {
		if (isInited)
			return (RHSadGxPackage) EPackage.Registry.INSTANCE.getEPackage(RHSadGxPackage.eNS_URI);

		// Obtain or create and register package
		RHSadGxPackageImpl theRHSadGxPackage = (RHSadGxPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RHSadGxPackageImpl
			? EPackage.Registry.INSTANCE.get(eNS_URI) : new RHSadGxPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RHGxPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRHSadGxPackage.createPackageContents();

		// Initialize created meta-data
		theRHSadGxPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRHSadGxPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RHSadGxPackage.eNS_URI, theRHSadGxPackage);
		return theRHSadGxPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentShape() {
		return componentShapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RHSadGxFactory getRHSadGxFactory() {
		return (RHSadGxFactory) getEFactoryInstance();
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
		componentShapeEClass = createEClass(COMPONENT_SHAPE);
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
		RHGxPackage theRHGxPackage = (RHGxPackage) EPackage.Registry.INSTANCE.getEPackage(RHGxPackage.eNS_URI);
		PictogramsPackage thePictogramsPackage = (PictogramsPackage) EPackage.Registry.INSTANCE.getEPackage(PictogramsPackage.eNS_URI);
		AlgorithmsPackage theAlgorithmsPackage = (AlgorithmsPackage) EPackage.Registry.INSTANCE.getEPackage(AlgorithmsPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		componentShapeEClass.getESuperTypes().add(theRHGxPackage.getRHContainerShape());

		// Initialize classes and features; add operations and parameters
		initEClass(componentShapeEClass, ComponentShape.class, "ComponentShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(componentShapeEClass, thePictogramsPackage.getContainerShape(), "getStartOrderEllipseShape", 0, 1, !IS_UNIQUE, IS_ORDERED);

		addEOperation(componentShapeEClass, theAlgorithmsPackage.getText(), "getStartOrderText", 0, 1, !IS_UNIQUE, IS_ORDERED);

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

} //RHSadGxPackageImpl
