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

import gov.redhawk.core.graphiti.dcd.ui.ext.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RHDeviceGxFactoryImpl extends EFactoryImpl implements RHDeviceGxFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RHDeviceGxFactory init() {
		try {
			RHDeviceGxFactory theRHDeviceGxFactory = (RHDeviceGxFactory) EPackage.Registry.INSTANCE.getEFactory(RHDeviceGxPackage.eNS_URI);
			if (theRHDeviceGxFactory != null) {
				return theRHDeviceGxFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RHDeviceGxFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RHDeviceGxFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case RHDeviceGxPackage.DEVICE_SHAPE:
			return createDeviceShape();
		case RHDeviceGxPackage.SERVICE_SHAPE:
			return createServiceShape();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeviceShape createDeviceShape() {
		DeviceShapeImpl deviceShape = new DeviceShapeImpl();
		return deviceShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceShape createServiceShape() {
		ServiceShapeImpl serviceShape = new ServiceShapeImpl();
		return serviceShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RHDeviceGxPackage getRHDeviceGxPackage() {
		return (RHDeviceGxPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RHDeviceGxPackage getPackage() {
		return RHDeviceGxPackage.eINSTANCE;
	}

} //RHDeviceGxFactoryImpl
