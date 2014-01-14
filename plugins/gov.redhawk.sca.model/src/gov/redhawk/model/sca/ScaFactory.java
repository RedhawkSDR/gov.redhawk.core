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
package gov.redhawk.model.sca;

import org.eclipse.emf.ecore.EFactory;

import CF.Device;
import CF.LoadableDevice;

/**
 * <!-- begin-user-doc --> 
 * The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model.
 * @noimplement This interface is not intended to be implemented by clients. 
 * <!-- end-user-doc -->
 * @see gov.redhawk.model.sca.ScaPackage
 * @generated
 */
public interface ScaFactory extends EFactory {

	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ScaFactory eINSTANCE = gov.redhawk.model.sca.impl.ScaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Properties</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Properties</em>'.
	 * @generated
	 */
	Properties createProperties();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	ScaComponent createScaComponent();

	/**
	 * Returns a new object of class '<em>Device</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Device</em>'.
	 * @generated
	 */
	< D extends Device > ScaDevice<D> createScaDevice();

	/**
	 * Returns a new object of class '<em>Device Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Device Manager</em>'.
	 * @generated
	 */
	ScaDeviceManager createScaDeviceManager();

	/**
	 * Returns a new object of class '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Service</em>'.
	 * @generated
	 */
	ScaService createScaService();

	/**
	 * Returns a new object of class '<em>Device Manager File System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Device Manager File System</em>'.
	 * @generated
	 */
	ScaDeviceManagerFileSystem createScaDeviceManagerFileSystem();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	ScaDocumentRoot createScaDocumentRoot();

	/**
	 * Returns a new object of class '<em>Domain Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Manager</em>'.
	 * @generated
	 */
	ScaDomainManager createScaDomainManager();

	/**
	 * Returns a new object of class '<em>Domain Manager File System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Manager File System</em>'.
	 * @generated
	 */
	ScaDomainManagerFileSystem createScaDomainManagerFileSystem();

	/**
	 * Returns a new object of class '<em>Domain Manager Registry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Manager Registry</em>'.
	 * @generated
	 */
	ScaDomainManagerRegistry createScaDomainManagerRegistry();

	/**
	 * Returns a new object of class '<em>Executable Device</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Executable Device</em>'.
	 * @generated
	 */
	ScaExecutableDevice createScaExecutableDevice();

	/**
	 * Returns a new object of class '<em>File Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File Store</em>'.
	 * @generated
	 */
	ScaFileStore createScaFileStore();

	/**
	 * Returns a new object of class '<em>Loadable Device</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loadable Device</em>'.
	 * @generated
	 */
	< L extends LoadableDevice > ScaLoadableDevice<L> createScaLoadableDevice();

	/**
	 * Returns a new object of class '<em>Provides Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provides Port</em>'.
	 * @generated
	 */
	ScaProvidesPort createScaProvidesPort();

	/**
	 * Returns a new object of class '<em>Simple Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Property</em>'.
	 * @generated
	 */
	ScaSimpleProperty createScaSimpleProperty();

	/**
	 * Returns a new object of class '<em>Simple Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Simple Sequence Property</em>'.
	 * @generated
	 */
	ScaSimpleSequenceProperty createScaSimpleSequenceProperty();

	/**
	 * Returns a new object of class '<em>Struct Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Struct Property</em>'.
	 * @generated
	 */
	ScaStructProperty createScaStructProperty();

	/**
	 * Returns a new object of class '<em>Uses Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Uses Port</em>'.
	 * @generated
	 */
	ScaUsesPort createScaUsesPort();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	ScaConnection createScaConnection();

	/**
	 * Returns a new object of class '<em>Waveform</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Waveform</em>'.
	 * @generated
	 */
	ScaWaveform createScaWaveform();

	/**
	 * Returns a new object of class '<em>Waveform Factory</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Waveform Factory</em>'.
	 * @generated
	 */
	ScaWaveformFactory createScaWaveformFactory();

	/**
	 * Returns a new object of class '<em>Struct Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Struct Sequence Property</em>'.
	 * @generated
	 */
	ScaStructSequenceProperty createScaStructSequenceProperty();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ScaPackage getScaPackage();

} // ScaFactory
