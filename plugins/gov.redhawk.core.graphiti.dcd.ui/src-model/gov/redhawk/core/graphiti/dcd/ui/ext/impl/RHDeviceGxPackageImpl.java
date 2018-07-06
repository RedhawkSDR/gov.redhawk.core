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
package gov.redhawk.core.graphiti.dcd.ui.ext.impl;

import gov.redhawk.core.graphiti.dcd.ui.ext.DeviceShape;
import gov.redhawk.core.graphiti.dcd.ui.ext.RHDeviceGxFactory;
import gov.redhawk.core.graphiti.dcd.ui.ext.RHDeviceGxPackage;
import gov.redhawk.core.graphiti.dcd.ui.ext.ServiceShape;

import gov.redhawk.core.graphiti.ui.ext.RHGxPackage;

import org.eclipse.emf.ecore.EClass;
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
public class RHDeviceGxPackageImpl extends EPackageImpl implements RHDeviceGxPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deviceShapeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceShapeEClass = null;

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
	 * @see gov.redhawk.core.graphiti.dcd.ui.ext.RHDeviceGxPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RHDeviceGxPackageImpl() {
		super(eNS_URI, RHDeviceGxFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RHDeviceGxPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RHDeviceGxPackage init() {
		if (isInited)
			return (RHDeviceGxPackage) EPackage.Registry.INSTANCE.getEPackage(RHDeviceGxPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredRHDeviceGxPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		RHDeviceGxPackageImpl theRHDeviceGxPackage = registeredRHDeviceGxPackage instanceof RHDeviceGxPackageImpl
			? (RHDeviceGxPackageImpl) registeredRHDeviceGxPackage
			: new RHDeviceGxPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		RHGxPackage.eINSTANCE.eClass();
		PictogramsPackage.eINSTANCE.eClass();
		MmPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		AlgorithmsPackage.eINSTANCE.eClass();
		StylesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRHDeviceGxPackage.createPackageContents();

		// Initialize created meta-data
		theRHDeviceGxPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRHDeviceGxPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RHDeviceGxPackage.eNS_URI, theRHDeviceGxPackage);
		return theRHDeviceGxPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeviceShape() {
		return deviceShapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getServiceShape() {
		return serviceShapeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RHDeviceGxFactory getRHDeviceGxFactory() {
		return (RHDeviceGxFactory) getEFactoryInstance();
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
		deviceShapeEClass = createEClass(DEVICE_SHAPE);

		serviceShapeEClass = createEClass(SERVICE_SHAPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		deviceShapeEClass.getESuperTypes().add(theRHGxPackage.getRHContainerShape());
		serviceShapeEClass.getESuperTypes().add(theRHGxPackage.getRHContainerShape());

		// Initialize classes and features; add operations and parameters
		initEClass(deviceShapeEClass, DeviceShape.class, "DeviceShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(serviceShapeEClass, ServiceShape.class, "ServiceShape", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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

} //RHDeviceGxPackageImpl
