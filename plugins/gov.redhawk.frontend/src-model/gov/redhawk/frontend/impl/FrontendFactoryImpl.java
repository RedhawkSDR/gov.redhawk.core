/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
// BEGIN GENERATED CODE
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.*;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class FrontendFactoryImpl extends EFactoryImpl implements FrontendFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FrontendFactory init() {
		try {
			FrontendFactory theFrontendFactory = (FrontendFactory) EPackage.Registry.INSTANCE.getEFactory(FrontendPackage.eNS_URI);
			if (theFrontendFactory != null) {
				return theFrontendFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FrontendFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrontendFactoryImpl() {
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
		case FrontendPackage.TUNER_CONTAINER:
			return createTunerContainer();
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER:
			return createUnallocatedTunerContainer();
		case FrontendPackage.TUNER_STATUS:
			return createTunerStatus();
		case FrontendPackage.LISTENER_ALLOCATION:
			return createListenerAllocation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case FrontendPackage.SCA_STRUCT_PROPERTY:
			return createScaStructPropertyFromString(eDataType, initialValue);
		case FrontendPackage.SCA_SIMPLE_PROPERTY:
			return createScaSimplePropertyFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case FrontendPackage.SCA_STRUCT_PROPERTY:
			return convertScaStructPropertyToString(eDataType, instanceValue);
		case FrontendPackage.SCA_SIMPLE_PROPERTY:
			return convertScaSimplePropertyToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TunerContainer createTunerContainer() {
		TunerContainerImpl tunerContainer = new TunerContainerImpl();
		return tunerContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnallocatedTunerContainer createUnallocatedTunerContainer() {
		UnallocatedTunerContainerImpl unallocatedTunerContainer = new UnallocatedTunerContainerImpl();
		return unallocatedTunerContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TunerStatus createTunerStatus() {
		TunerStatusImpl tunerStatus = new TunerStatusImpl();
		return tunerStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListenerAllocation createListenerAllocation() {
		ListenerAllocationImpl listenerAllocation = new ListenerAllocationImpl();
		return listenerAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaStructProperty createScaStructProperty(String literal) {
		return (ScaStructProperty) super.createFromString(FrontendPackage.Literals.SCA_STRUCT_PROPERTY, literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaStructProperty createScaStructPropertyFromString(EDataType eDataType, String initialValue) {
		return createScaStructProperty(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScaStructProperty(ScaStructProperty instanceValue) {
		return super.convertToString(FrontendPackage.Literals.SCA_STRUCT_PROPERTY, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScaStructPropertyToString(EDataType eDataType, Object instanceValue) {
		return convertScaStructProperty((ScaStructProperty) instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimpleProperty createScaSimpleProperty(String literal) {
		return (ScaSimpleProperty) super.createFromString(FrontendPackage.Literals.SCA_SIMPLE_PROPERTY, literal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaSimpleProperty createScaSimplePropertyFromString(EDataType eDataType, String initialValue) {
		return createScaSimpleProperty(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScaSimpleProperty(ScaSimpleProperty instanceValue) {
		return super.convertToString(FrontendPackage.Literals.SCA_SIMPLE_PROPERTY, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScaSimplePropertyToString(EDataType eDataType, Object instanceValue) {
		return convertScaSimpleProperty((ScaSimpleProperty) instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrontendPackage getFrontendPackage() {
		return (FrontendPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static FrontendPackage getPackage() {
		return FrontendPackage.eINSTANCE;
	}

} // FrontendFactoryImpl
