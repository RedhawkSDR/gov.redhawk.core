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
package gov.redhawk.model.sca.util;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaExecutableDevice;
import gov.redhawk.model.sca.ScaFileManager;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaFileSystem;
import gov.redhawk.model.sca.ScaLoadableDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import java.util.Map;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.scd.AbstractPort;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.omg.CosEventChannelAdmin.EventChannel;
import CF.ApplicationFactoryOperations;
import CF.ApplicationOperations;
import CF.Device;
import CF.DeviceManagerOperations;
import CF.DeviceOperations;
import CF.DomainManagerOperations;
import CF.ExecutableDeviceOperations;
import CF.FileManagerOperations;
import CF.FileSystem;
import CF.FileSystemOperations;
import CF.LifeCycleOperations;
import CF.LoadableDevice;
import CF.LoadableDeviceOperations;
import CF.PortOperations;
import CF.PortSetOperations;
import CF.PortSupplierOperations;
import CF.PropertyEmitterOperations;
import CF.PropertySetOperations;
import CF.Resource;
import CF.ResourceOperations;
import CF.TestableObjectOperations;
import gov.redhawk.model.sca.*;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method
 * for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see gov.redhawk.model.sca.ScaPackage
 * @generated
 */
public class ScaSwitch< T1 > extends Switch<T1> {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ScaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ScaSwitch() {
		if (modelPackage == null) {
			modelPackage = ScaPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
	 * result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case ScaPackage.CORBA_OBJ_WRAPPER: {
			CorbaObjWrapper< ? > corbaObjWrapper = (CorbaObjWrapper< ? >) theEObject;
			T1 result = caseCorbaObjWrapper(corbaObjWrapper);
			if (result == null)
				result = caseDataProviderObject(corbaObjWrapper);
			if (result == null)
				result = caseIStatusProvider(corbaObjWrapper);
			if (result == null)
				result = caseIDisposable(corbaObjWrapper);
			if (result == null)
				result = caseIRefreshable(corbaObjWrapper);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.DATA_PROVIDER_OBJECT: {
			DataProviderObject dataProviderObject = (DataProviderObject) theEObject;
			T1 result = caseDataProviderObject(dataProviderObject);
			if (result == null)
				result = caseIStatusProvider(dataProviderObject);
			if (result == null)
				result = caseIDisposable(dataProviderObject);
			if (result == null)
				result = caseIRefreshable(dataProviderObject);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.IDISPOSABLE: {
			IDisposable iDisposable = (IDisposable) theEObject;
			T1 result = caseIDisposable(iDisposable);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.PROFILE_OBJECT_WRAPPER: {
			ProfileObjectWrapper< ? > profileObjectWrapper = (ProfileObjectWrapper< ? >) theEObject;
			T1 result = caseProfileObjectWrapper(profileObjectWrapper);
			if (result == null)
				result = caseIStatusProvider(profileObjectWrapper);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.PROPERTIES: {
			Properties properties = (Properties) theEObject;
			T1 result = caseProperties(properties);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_ABSTRACT_COMPONENT: {
			ScaAbstractComponent< ? > scaAbstractComponent = (ScaAbstractComponent< ? >) theEObject;
			T1 result = caseScaAbstractComponent(scaAbstractComponent);
			if (result == null)
				result = caseScaPropertyContainer(scaAbstractComponent);
			if (result == null)
				result = caseResourceOperations(scaAbstractComponent);
			if (result == null)
				result = caseScaPortContainer(scaAbstractComponent);
			if (result == null)
				result = caseCorbaObjWrapper(scaAbstractComponent);
			if (result == null)
				result = caseProfileObjectWrapper(scaAbstractComponent);
			if (result == null)
				result = casePropertyEmitterOperations(scaAbstractComponent);
			if (result == null)
				result = caseLifeCycleOperations(scaAbstractComponent);
			if (result == null)
				result = caseTestableObjectOperations(scaAbstractComponent);
			if (result == null)
				result = casePortSupplierOperations(scaAbstractComponent);
			if (result == null)
				result = caseDataProviderObject(scaAbstractComponent);
			if (result == null)
				result = casePropertySetOperations(scaAbstractComponent);
			if (result == null)
				result = caseIStatusProvider(scaAbstractComponent);
			if (result == null)
				result = caseIDisposable(scaAbstractComponent);
			if (result == null)
				result = caseIRefreshable(scaAbstractComponent);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_PROPERTY_CONTAINER: {
			ScaPropertyContainer< ? , ? > scaPropertyContainer = (ScaPropertyContainer< ? , ? >) theEObject;
			T1 result = caseScaPropertyContainer(scaPropertyContainer);
			if (result == null)
				result = caseCorbaObjWrapper(scaPropertyContainer);
			if (result == null)
				result = caseProfileObjectWrapper(scaPropertyContainer);
			if (result == null)
				result = casePropertyEmitterOperations(scaPropertyContainer);
			if (result == null)
				result = caseDataProviderObject(scaPropertyContainer);
			if (result == null)
				result = casePropertySetOperations(scaPropertyContainer);
			if (result == null)
				result = caseIStatusProvider(scaPropertyContainer);
			if (result == null)
				result = caseIDisposable(scaPropertyContainer);
			if (result == null)
				result = caseIRefreshable(scaPropertyContainer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_PORT_CONTAINER: {
			ScaPortContainer scaPortContainer = (ScaPortContainer) theEObject;
			T1 result = caseScaPortContainer(scaPortContainer);
			if (result == null)
				result = caseIRefreshable(scaPortContainer);
			if (result == null)
				result = caseIStatusProvider(scaPortContainer);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_ABSTRACT_PROPERTY: {
			ScaAbstractProperty< ? > scaAbstractProperty = (ScaAbstractProperty< ? >) theEObject;
			T1 result = caseScaAbstractProperty(scaAbstractProperty);
			if (result == null)
				result = caseIStatusProvider(scaAbstractProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_COMPONENT: {
			ScaComponent scaComponent = (ScaComponent) theEObject;
			T1 result = caseScaComponent(scaComponent);
			if (result == null)
				result = caseScaAbstractComponent(scaComponent);
			if (result == null)
				result = caseScaPropertyContainer(scaComponent);
			if (result == null)
				result = caseResourceOperations(scaComponent);
			if (result == null)
				result = caseScaPortContainer(scaComponent);
			if (result == null)
				result = caseCorbaObjWrapper(scaComponent);
			if (result == null)
				result = caseProfileObjectWrapper(scaComponent);
			if (result == null)
				result = casePropertyEmitterOperations(scaComponent);
			if (result == null)
				result = caseLifeCycleOperations(scaComponent);
			if (result == null)
				result = caseTestableObjectOperations(scaComponent);
			if (result == null)
				result = casePortSupplierOperations(scaComponent);
			if (result == null)
				result = caseDataProviderObject(scaComponent);
			if (result == null)
				result = casePropertySetOperations(scaComponent);
			if (result == null)
				result = caseIStatusProvider(scaComponent);
			if (result == null)
				result = caseIDisposable(scaComponent);
			if (result == null)
				result = caseIRefreshable(scaComponent);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DEVICE: {
			ScaDevice< ? > scaDevice = (ScaDevice< ? >) theEObject;
			T1 result = caseScaDevice(scaDevice);
			if (result == null)
				result = caseScaAbstractComponent(scaDevice);
			if (result == null)
				result = caseDeviceOperations(scaDevice);
			if (result == null)
				result = caseScaPropertyContainer(scaDevice);
			if (result == null)
				result = caseResourceOperations(scaDevice);
			if (result == null)
				result = caseScaPortContainer(scaDevice);
			if (result == null)
				result = caseCorbaObjWrapper(scaDevice);
			if (result == null)
				result = caseProfileObjectWrapper(scaDevice);
			if (result == null)
				result = casePropertyEmitterOperations(scaDevice);
			if (result == null)
				result = caseLifeCycleOperations(scaDevice);
			if (result == null)
				result = caseTestableObjectOperations(scaDevice);
			if (result == null)
				result = casePortSupplierOperations(scaDevice);
			if (result == null)
				result = caseDataProviderObject(scaDevice);
			if (result == null)
				result = casePropertySetOperations(scaDevice);
			if (result == null)
				result = caseIStatusProvider(scaDevice);
			if (result == null)
				result = caseIDisposable(scaDevice);
			if (result == null)
				result = caseIRefreshable(scaDevice);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DEVICE_MANAGER: {
			ScaDeviceManager scaDeviceManager = (ScaDeviceManager) theEObject;
			T1 result = caseScaDeviceManager(scaDeviceManager);
			if (result == null)
				result = caseScaPropertyContainer(scaDeviceManager);
			if (result == null)
				result = caseDeviceManagerOperations(scaDeviceManager);
			if (result == null)
				result = caseScaPortContainer(scaDeviceManager);
			if (result == null)
				result = caseCorbaObjWrapper(scaDeviceManager);
			if (result == null)
				result = caseProfileObjectWrapper(scaDeviceManager);
			if (result == null)
				result = casePropertyEmitterOperations(scaDeviceManager);
			if (result == null)
				result = casePortSetOperations(scaDeviceManager);
			if (result == null)
				result = caseDataProviderObject(scaDeviceManager);
			if (result == null)
				result = casePropertySetOperations(scaDeviceManager);
			if (result == null)
				result = caseIStatusProvider(scaDeviceManager);
			if (result == null)
				result = caseIDisposable(scaDeviceManager);
			if (result == null)
				result = caseIRefreshable(scaDeviceManager);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_SERVICE: {
			ScaService scaService = (ScaService) theEObject;
			T1 result = caseScaService(scaService);
			if (result == null)
				result = caseScaPropertyContainer(scaService);
			if (result == null)
				result = caseScaPortContainer(scaService);
			if (result == null)
				result = caseCorbaObjWrapper(scaService);
			if (result == null)
				result = caseProfileObjectWrapper(scaService);
			if (result == null)
				result = casePropertyEmitterOperations(scaService);
			if (result == null)
				result = caseDataProviderObject(scaService);
			if (result == null)
				result = casePropertySetOperations(scaService);
			if (result == null)
				result = caseIStatusProvider(scaService);
			if (result == null)
				result = caseIDisposable(scaService);
			if (result == null)
				result = caseIRefreshable(scaService);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM: {
			ScaDeviceManagerFileSystem scaDeviceManagerFileSystem = (ScaDeviceManagerFileSystem) theEObject;
			T1 result = caseScaDeviceManagerFileSystem(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseScaFileSystem(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseCorbaObjWrapper(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseFileSystemOperations(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseScaFileStore(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseDataProviderObject(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseIStatusProvider(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseIDisposable(scaDeviceManagerFileSystem);
			if (result == null)
				result = caseIRefreshable(scaDeviceManagerFileSystem);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DOCUMENT_ROOT: {
			ScaDocumentRoot scaDocumentRoot = (ScaDocumentRoot) theEObject;
			T1 result = caseScaDocumentRoot(scaDocumentRoot);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DOMAIN_MANAGER: {
			ScaDomainManager scaDomainManager = (ScaDomainManager) theEObject;
			T1 result = caseScaDomainManager(scaDomainManager);
			if (result == null)
				result = caseScaPropertyContainer(scaDomainManager);
			if (result == null)
				result = caseDomainManagerOperations(scaDomainManager);
			if (result == null)
				result = caseCorbaObjWrapper(scaDomainManager);
			if (result == null)
				result = caseProfileObjectWrapper(scaDomainManager);
			if (result == null)
				result = casePropertyEmitterOperations(scaDomainManager);
			if (result == null)
				result = caseDataProviderObject(scaDomainManager);
			if (result == null)
				result = casePropertySetOperations(scaDomainManager);
			if (result == null)
				result = caseIStatusProvider(scaDomainManager);
			if (result == null)
				result = caseIDisposable(scaDomainManager);
			if (result == null)
				result = caseIRefreshable(scaDomainManager);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM: {
			ScaDomainManagerFileSystem scaDomainManagerFileSystem = (ScaDomainManagerFileSystem) theEObject;
			T1 result = caseScaDomainManagerFileSystem(scaDomainManagerFileSystem);
			if (result == null)
				result = caseScaFileManager(scaDomainManagerFileSystem);
			if (result == null)
				result = caseScaFileSystem(scaDomainManagerFileSystem);
			if (result == null)
				result = caseFileManagerOperations(scaDomainManagerFileSystem);
			if (result == null)
				result = caseCorbaObjWrapper(scaDomainManagerFileSystem);
			if (result == null)
				result = caseFileSystemOperations(scaDomainManagerFileSystem);
			if (result == null)
				result = caseScaFileStore(scaDomainManagerFileSystem);
			if (result == null)
				result = caseDataProviderObject(scaDomainManagerFileSystem);
			if (result == null)
				result = caseIStatusProvider(scaDomainManagerFileSystem);
			if (result == null)
				result = caseIDisposable(scaDomainManagerFileSystem);
			if (result == null)
				result = caseIRefreshable(scaDomainManagerFileSystem);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY: {
			ScaDomainManagerRegistry scaDomainManagerRegistry = (ScaDomainManagerRegistry) theEObject;
			T1 result = caseScaDomainManagerRegistry(scaDomainManagerRegistry);
			if (result == null)
				result = caseIDisposable(scaDomainManagerRegistry);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_EXECUTABLE_DEVICE: {
			ScaExecutableDevice scaExecutableDevice = (ScaExecutableDevice) theEObject;
			T1 result = caseScaExecutableDevice(scaExecutableDevice);
			if (result == null)
				result = caseScaLoadableDevice(scaExecutableDevice);
			if (result == null)
				result = caseExecutableDeviceOperations(scaExecutableDevice);
			if (result == null)
				result = caseScaDevice(scaExecutableDevice);
			if (result == null)
				result = caseLoadableDeviceOperations(scaExecutableDevice);
			if (result == null)
				result = caseScaAbstractComponent(scaExecutableDevice);
			if (result == null)
				result = caseDeviceOperations(scaExecutableDevice);
			if (result == null)
				result = caseScaPropertyContainer(scaExecutableDevice);
			if (result == null)
				result = caseResourceOperations(scaExecutableDevice);
			if (result == null)
				result = caseScaPortContainer(scaExecutableDevice);
			if (result == null)
				result = caseCorbaObjWrapper(scaExecutableDevice);
			if (result == null)
				result = caseProfileObjectWrapper(scaExecutableDevice);
			if (result == null)
				result = casePropertyEmitterOperations(scaExecutableDevice);
			if (result == null)
				result = caseLifeCycleOperations(scaExecutableDevice);
			if (result == null)
				result = caseTestableObjectOperations(scaExecutableDevice);
			if (result == null)
				result = casePortSupplierOperations(scaExecutableDevice);
			if (result == null)
				result = caseDataProviderObject(scaExecutableDevice);
			if (result == null)
				result = casePropertySetOperations(scaExecutableDevice);
			if (result == null)
				result = caseIStatusProvider(scaExecutableDevice);
			if (result == null)
				result = caseIDisposable(scaExecutableDevice);
			if (result == null)
				result = caseIRefreshable(scaExecutableDevice);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_FILE_MANAGER: {
			ScaFileManager scaFileManager = (ScaFileManager) theEObject;
			T1 result = caseScaFileManager(scaFileManager);
			if (result == null)
				result = caseScaFileSystem(scaFileManager);
			if (result == null)
				result = caseFileManagerOperations(scaFileManager);
			if (result == null)
				result = caseCorbaObjWrapper(scaFileManager);
			if (result == null)
				result = caseFileSystemOperations(scaFileManager);
			if (result == null)
				result = caseScaFileStore(scaFileManager);
			if (result == null)
				result = caseDataProviderObject(scaFileManager);
			if (result == null)
				result = caseIStatusProvider(scaFileManager);
			if (result == null)
				result = caseIDisposable(scaFileManager);
			if (result == null)
				result = caseIRefreshable(scaFileManager);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_FILE_STORE: {
			ScaFileStore scaFileStore = (ScaFileStore) theEObject;
			T1 result = caseScaFileStore(scaFileStore);
			if (result == null)
				result = caseIStatusProvider(scaFileStore);
			if (result == null)
				result = caseIRefreshable(scaFileStore);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_FILE_SYSTEM: {
			ScaFileSystem< ? > scaFileSystem = (ScaFileSystem< ? >) theEObject;
			T1 result = caseScaFileSystem(scaFileSystem);
			if (result == null)
				result = caseCorbaObjWrapper(scaFileSystem);
			if (result == null)
				result = caseFileSystemOperations(scaFileSystem);
			if (result == null)
				result = caseScaFileStore(scaFileSystem);
			if (result == null)
				result = caseDataProviderObject(scaFileSystem);
			if (result == null)
				result = caseIStatusProvider(scaFileSystem);
			if (result == null)
				result = caseIDisposable(scaFileSystem);
			if (result == null)
				result = caseIRefreshable(scaFileSystem);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_LOADABLE_DEVICE: {
			ScaLoadableDevice< ? > scaLoadableDevice = (ScaLoadableDevice< ? >) theEObject;
			T1 result = caseScaLoadableDevice(scaLoadableDevice);
			if (result == null)
				result = caseScaDevice(scaLoadableDevice);
			if (result == null)
				result = caseLoadableDeviceOperations(scaLoadableDevice);
			if (result == null)
				result = caseScaAbstractComponent(scaLoadableDevice);
			if (result == null)
				result = caseDeviceOperations(scaLoadableDevice);
			if (result == null)
				result = caseScaPropertyContainer(scaLoadableDevice);
			if (result == null)
				result = caseResourceOperations(scaLoadableDevice);
			if (result == null)
				result = caseScaPortContainer(scaLoadableDevice);
			if (result == null)
				result = caseCorbaObjWrapper(scaLoadableDevice);
			if (result == null)
				result = caseProfileObjectWrapper(scaLoadableDevice);
			if (result == null)
				result = casePropertyEmitterOperations(scaLoadableDevice);
			if (result == null)
				result = caseLifeCycleOperations(scaLoadableDevice);
			if (result == null)
				result = caseTestableObjectOperations(scaLoadableDevice);
			if (result == null)
				result = casePortSupplierOperations(scaLoadableDevice);
			if (result == null)
				result = caseDataProviderObject(scaLoadableDevice);
			if (result == null)
				result = casePropertySetOperations(scaLoadableDevice);
			if (result == null)
				result = caseIStatusProvider(scaLoadableDevice);
			if (result == null)
				result = caseIDisposable(scaLoadableDevice);
			if (result == null)
				result = caseIRefreshable(scaLoadableDevice);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_PORT: {
			ScaPort< ? , ? > scaPort = (ScaPort< ? , ? >) theEObject;
			T1 result = caseScaPort(scaPort);
			if (result == null)
				result = caseCorbaObjWrapper(scaPort);
			if (result == null)
				result = caseDataProviderObject(scaPort);
			if (result == null)
				result = caseIStatusProvider(scaPort);
			if (result == null)
				result = caseIDisposable(scaPort);
			if (result == null)
				result = caseIRefreshable(scaPort);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_PROVIDES_PORT: {
			ScaProvidesPort scaProvidesPort = (ScaProvidesPort) theEObject;
			T1 result = caseScaProvidesPort(scaProvidesPort);
			if (result == null)
				result = caseScaPort(scaProvidesPort);
			if (result == null)
				result = caseCorbaObjWrapper(scaProvidesPort);
			if (result == null)
				result = caseDataProviderObject(scaProvidesPort);
			if (result == null)
				result = caseIStatusProvider(scaProvidesPort);
			if (result == null)
				result = caseIDisposable(scaProvidesPort);
			if (result == null)
				result = caseIRefreshable(scaProvidesPort);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_SIMPLE_PROPERTY: {
			ScaSimpleProperty scaSimpleProperty = (ScaSimpleProperty) theEObject;
			T1 result = caseScaSimpleProperty(scaSimpleProperty);
			if (result == null)
				result = caseScaAbstractProperty(scaSimpleProperty);
			if (result == null)
				result = caseIStatusProvider(scaSimpleProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY: {
			ScaSimpleSequenceProperty scaSimpleSequenceProperty = (ScaSimpleSequenceProperty) theEObject;
			T1 result = caseScaSimpleSequenceProperty(scaSimpleSequenceProperty);
			if (result == null)
				result = caseScaAbstractProperty(scaSimpleSequenceProperty);
			if (result == null)
				result = caseIStatusProvider(scaSimpleSequenceProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_STRUCT_PROPERTY: {
			ScaStructProperty scaStructProperty = (ScaStructProperty) theEObject;
			T1 result = caseScaStructProperty(scaStructProperty);
			if (result == null)
				result = caseScaAbstractProperty(scaStructProperty);
			if (result == null)
				result = casePropertySetOperations(scaStructProperty);
			if (result == null)
				result = caseIStatusProvider(scaStructProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_USES_PORT: {
			ScaUsesPort scaUsesPort = (ScaUsesPort) theEObject;
			T1 result = caseScaUsesPort(scaUsesPort);
			if (result == null)
				result = caseScaPort(scaUsesPort);
			if (result == null)
				result = casePortOperations(scaUsesPort);
			if (result == null)
				result = caseCorbaObjWrapper(scaUsesPort);
			if (result == null)
				result = caseDataProviderObject(scaUsesPort);
			if (result == null)
				result = caseIStatusProvider(scaUsesPort);
			if (result == null)
				result = caseIDisposable(scaUsesPort);
			if (result == null)
				result = caseIRefreshable(scaUsesPort);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_CONNECTION: {
			ScaConnection scaConnection = (ScaConnection) theEObject;
			T1 result = caseScaConnection(scaConnection);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_WAVEFORM: {
			ScaWaveform scaWaveform = (ScaWaveform) theEObject;
			T1 result = caseScaWaveform(scaWaveform);
			if (result == null)
				result = caseScaPropertyContainer(scaWaveform);
			if (result == null)
				result = caseApplicationOperations(scaWaveform);
			if (result == null)
				result = caseScaPortContainer(scaWaveform);
			if (result == null)
				result = caseCorbaObjWrapper(scaWaveform);
			if (result == null)
				result = caseProfileObjectWrapper(scaWaveform);
			if (result == null)
				result = caseResourceOperations(scaWaveform);
			if (result == null)
				result = caseDataProviderObject(scaWaveform);
			if (result == null)
				result = casePropertyEmitterOperations(scaWaveform);
			if (result == null)
				result = casePropertySetOperations(scaWaveform);
			if (result == null)
				result = caseLifeCycleOperations(scaWaveform);
			if (result == null)
				result = caseTestableObjectOperations(scaWaveform);
			if (result == null)
				result = casePortSupplierOperations(scaWaveform);
			if (result == null)
				result = caseIStatusProvider(scaWaveform);
			if (result == null)
				result = caseIDisposable(scaWaveform);
			if (result == null)
				result = caseIRefreshable(scaWaveform);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_WAVEFORM_FACTORY: {
			ScaWaveformFactory scaWaveformFactory = (ScaWaveformFactory) theEObject;
			T1 result = caseScaWaveformFactory(scaWaveformFactory);
			if (result == null)
				result = caseCorbaObjWrapper(scaWaveformFactory);
			if (result == null)
				result = caseApplicationFactoryOperations(scaWaveformFactory);
			if (result == null)
				result = caseProfileObjectWrapper(scaWaveformFactory);
			if (result == null)
				result = caseDataProviderObject(scaWaveformFactory);
			if (result == null)
				result = caseIStatusProvider(scaWaveformFactory);
			if (result == null)
				result = caseIDisposable(scaWaveformFactory);
			if (result == null)
				result = caseIRefreshable(scaWaveformFactory);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.STRING_TO_STRING_MAP: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, String> stringToStringMap = (Map.Entry<String, String>) theEObject;
			T1 result = caseStringToStringMap(stringToStringMap);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY: {
			ScaStructSequenceProperty scaStructSequenceProperty = (ScaStructSequenceProperty) theEObject;
			T1 result = caseScaStructSequenceProperty(scaStructSequenceProperty);
			if (result == null)
				result = caseScaAbstractProperty(scaStructSequenceProperty);
			if (result == null)
				result = casePropertySetOperations(scaStructSequenceProperty);
			if (result == null)
				result = caseIStatusProvider(scaStructSequenceProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.ISTATUS_PROVIDER: {
			IStatusProvider iStatusProvider = (IStatusProvider) theEObject;
			T1 result = caseIStatusProvider(iStatusProvider);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.EVENT_CHANNEL: {
			EventChannel eventChannel = (EventChannel) theEObject;
			T1 result = caseEventChannel(eventChannel);
			if (result == null)
				result = caseObject(eventChannel);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.IREFRESHABLE: {
			IRefreshable iRefreshable = (IRefreshable) theEObject;
			T1 result = caseIRefreshable(iRefreshable);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.SCA_EVENT_CHANNEL: {
			ScaEventChannel scaEventChannel = (ScaEventChannel) theEObject;
			T1 result = caseScaEventChannel(scaEventChannel);
			if (result == null)
				result = caseCorbaObjWrapper(scaEventChannel);
			if (result == null)
				result = caseDataProviderObject(scaEventChannel);
			if (result == null)
				result = caseIStatusProvider(scaEventChannel);
			if (result == null)
				result = caseIDisposable(scaEventChannel);
			if (result == null)
				result = caseIRefreshable(scaEventChannel);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case ScaPackage.STRING_TO_OBJECT_MAP: {
			@SuppressWarnings("unchecked")
			Map.Entry<String, EObject> stringToObjectMap = (Map.Entry<String, EObject>) theEObject;
			T1 result = caseStringToObjectMap(stringToObjectMap);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Corba Obj Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Corba Obj Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < T extends org.omg.CORBA.Object > T1 caseCorbaObjWrapper(CorbaObjWrapper<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Provider Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Provider Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDataProviderObject(DataProviderObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IDisposable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IDisposable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIDisposable(IDisposable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Profile Object Wrapper</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Profile Object Wrapper</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < O extends Object > T1 caseProfileObjectWrapper(ProfileObjectWrapper<O> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Properties</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Properties</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseProperties(Properties object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < R extends Resource > T1 caseScaAbstractComponent(ScaAbstractComponent<R> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < P extends org.omg.CORBA.Object, E extends Object > T1 caseScaPropertyContainer(ScaPropertyContainer<P, E> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaPortContainer(ScaPortContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < T extends AbstractProperty > T1 caseScaAbstractProperty(ScaAbstractProperty<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaComponent(ScaComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Device</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Device</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < D extends Device > T1 caseScaDevice(ScaDevice<D> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Device Manager</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Device Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDeviceManager(ScaDeviceManager object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Service</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Service</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaService(ScaService object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Device Manager File System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Device Manager File System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDeviceManagerFileSystem(ScaDeviceManagerFileSystem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDocumentRoot(ScaDocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Manager</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDomainManager(ScaDomainManager object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Manager File System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Manager File System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDomainManagerFileSystem(ScaDomainManagerFileSystem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Manager Registry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Manager Registry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaDomainManagerRegistry(ScaDomainManagerRegistry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executable Device</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executable Device</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaExecutableDevice(ScaExecutableDevice object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Manager</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Manager</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaFileManager(ScaFileManager object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Store</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Store</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaFileStore(ScaFileStore object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File System</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File System</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < F extends FileSystem > T1 caseScaFileSystem(ScaFileSystem<F> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Loadable Device</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Loadable Device</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < L extends LoadableDevice > T1 caseScaLoadableDevice(ScaLoadableDevice<L> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public < P extends AbstractPort, P2 extends org.omg.CORBA.Object > T1 caseScaPort(ScaPort<P, P2> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Provides Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Provides Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaProvidesPort(ScaProvidesPort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaSimpleProperty(ScaSimpleProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simple Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simple Sequence Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaSimpleSequenceProperty(ScaSimpleSequenceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Struct Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Struct Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaStructProperty(ScaStructProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uses Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uses Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaUsesPort(ScaUsesPort object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaConnection(ScaConnection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Waveform</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Waveform</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaWaveform(ScaWaveform object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Waveform Factory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Waveform Factory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaWaveformFactory(ScaWaveformFactory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To String Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringToStringMap(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Struct Sequence Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Struct Sequence Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaStructSequenceProperty(ScaStructSequenceProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IStatus Provider</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IStatus Provider</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIStatusProvider(IStatusProvider object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseEventChannel(EventChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IRefreshable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IRefreshable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIRefreshable(IRefreshable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Channel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Channel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseScaEventChannel(ScaEventChannel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String To Object Map</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String To Object Map</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringToObjectMap(Map.Entry<String, EObject> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Set Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Set Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertySetOperations(PropertySetOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Emitter Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Emitter Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePropertyEmitterOperations(PropertyEmitterOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Life Cycle Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Life Cycle Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLifeCycleOperations(LifeCycleOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Testable Object Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Testable Object Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseTestableObjectOperations(TestableObjectOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Supplier Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Supplier Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePortSupplierOperations(PortSupplierOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseResourceOperations(ResourceOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Device Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Device Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeviceOperations(DeviceOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Set Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * @since 20.1
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Set Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePortSetOperations(PortSetOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Device Manager Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Device Manager Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDeviceManagerOperations(DeviceManagerOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File System Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File System Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFileSystemOperations(FileSystemOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Domain Manager Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Domain Manager Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDomainManagerOperations(DomainManagerOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>File Manager Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>File Manager Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseFileManagerOperations(FileManagerOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Loadable Device Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Loadable Device Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseLoadableDeviceOperations(LoadableDeviceOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executable Device Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executable Device Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseExecutableDeviceOperations(ExecutableDeviceOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 casePortOperations(PortOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseApplicationOperations(ApplicationOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Application Factory Operations</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Application Factory Operations</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseApplicationFactoryOperations(ApplicationFactoryOperations object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseObject(org.omg.CORBA.Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} // ScaSwitch
