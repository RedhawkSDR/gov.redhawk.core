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
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
import CF.PortSupplierOperations;
import CF.PropertyEmitterOperations;
import CF.PropertySetOperations;
import CF.Resource;
import CF.ResourceOperations;
import CF.TestableObjectOperations;
import gov.redhawk.model.sca.*;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see gov.redhawk.model.sca.ScaPackage
 * @generated
 */
public class ScaAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static ScaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ScaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ScaPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance
	 * object of the model.
	 * <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaSwitch<Adapter> modelSwitch = new ScaSwitch<Adapter>() {
		@Override
		public < T extends org.omg.CORBA.Object > Adapter caseCorbaObjWrapper(CorbaObjWrapper<T> object) {
			return createCorbaObjWrapperAdapter();
		}

		@Override
		public Adapter caseDataProviderObject(DataProviderObject object) {
			return createDataProviderObjectAdapter();
		}

		@Override
		public Adapter caseIDisposable(IDisposable object) {
			return createIDisposableAdapter();
		}

		@Override
		public < O extends Object > Adapter caseProfileObjectWrapper(ProfileObjectWrapper<O> object) {
			return createProfileObjectWrapperAdapter();
		}

		@Override
		public Adapter caseProperties(Properties object) {
			return createPropertiesAdapter();
		}

		@Override
		public < R extends Resource > Adapter caseScaAbstractComponent(ScaAbstractComponent<R> object) {
			return createScaAbstractComponentAdapter();
		}

		@Override
		public < P extends org.omg.CORBA.Object, E extends Object > Adapter caseScaPropertyContainer(ScaPropertyContainer<P, E> object) {
			return createScaPropertyContainerAdapter();
		}

		@Override
		public Adapter caseScaPortContainer(ScaPortContainer object) {
			return createScaPortContainerAdapter();
		}

		@Override
		public < T extends AbstractProperty > Adapter caseScaAbstractProperty(ScaAbstractProperty<T> object) {
			return createScaAbstractPropertyAdapter();
		}

		@Override
		public Adapter caseScaComponent(ScaComponent object) {
			return createScaComponentAdapter();
		}

		@Override
		public < D extends Device > Adapter caseScaDevice(ScaDevice<D> object) {
			return createScaDeviceAdapter();
		}

		@Override
		public Adapter caseScaDeviceManager(ScaDeviceManager object) {
			return createScaDeviceManagerAdapter();
		}

		@Override
		public Adapter caseScaService(ScaService object) {
			return createScaServiceAdapter();
		}

		@Override
		public Adapter caseScaDeviceManagerFileSystem(ScaDeviceManagerFileSystem object) {
			return createScaDeviceManagerFileSystemAdapter();
		}

		@Override
		public Adapter caseScaDocumentRoot(ScaDocumentRoot object) {
			return createScaDocumentRootAdapter();
		}

		@Override
		public Adapter caseScaDomainManager(ScaDomainManager object) {
			return createScaDomainManagerAdapter();
		}

		@Override
		public Adapter caseScaDomainManagerFileSystem(ScaDomainManagerFileSystem object) {
			return createScaDomainManagerFileSystemAdapter();
		}

		@Override
		public Adapter caseScaDomainManagerRegistry(ScaDomainManagerRegistry object) {
			return createScaDomainManagerRegistryAdapter();
		}

		@Override
		public Adapter caseScaExecutableDevice(ScaExecutableDevice object) {
			return createScaExecutableDeviceAdapter();
		}

		@Override
		public Adapter caseScaFileManager(ScaFileManager object) {
			return createScaFileManagerAdapter();
		}

		@Override
		public Adapter caseScaFileStore(ScaFileStore object) {
			return createScaFileStoreAdapter();
		}

		@Override
		public < F extends FileSystem > Adapter caseScaFileSystem(ScaFileSystem<F> object) {
			return createScaFileSystemAdapter();
		}

		@Override
		public < L extends LoadableDevice > Adapter caseScaLoadableDevice(ScaLoadableDevice<L> object) {
			return createScaLoadableDeviceAdapter();
		}

		@Override
		public < P extends AbstractPort, P2 extends org.omg.CORBA.Object > Adapter caseScaPort(ScaPort<P, P2> object) {
			return createScaPortAdapter();
		}

		@Override
		public Adapter caseScaProvidesPort(ScaProvidesPort object) {
			return createScaProvidesPortAdapter();
		}

		@Override
		public Adapter caseScaSimpleProperty(ScaSimpleProperty object) {
			return createScaSimplePropertyAdapter();
		}

		@Override
		public Adapter caseScaSimpleSequenceProperty(ScaSimpleSequenceProperty object) {
			return createScaSimpleSequencePropertyAdapter();
		}

		@Override
		public Adapter caseScaStructProperty(ScaStructProperty object) {
			return createScaStructPropertyAdapter();
		}

		@Override
		public Adapter caseScaUsesPort(ScaUsesPort object) {
			return createScaUsesPortAdapter();
		}

		@Override
		public Adapter caseScaConnection(ScaConnection object) {
			return createScaConnectionAdapter();
		}

		@Override
		public Adapter caseScaWaveform(ScaWaveform object) {
			return createScaWaveformAdapter();
		}

		@Override
		public Adapter caseScaWaveformFactory(ScaWaveformFactory object) {
			return createScaWaveformFactoryAdapter();
		}

		@Override
		public Adapter caseStringToStringMap(Map.Entry<String, String> object) {
			return createStringToStringMapAdapter();
		}

		@Override
		public Adapter caseScaStructSequenceProperty(ScaStructSequenceProperty object) {
			return createScaStructSequencePropertyAdapter();
		}

		@Override
		public Adapter caseIStatusProvider(IStatusProvider object) {
			return createIStatusProviderAdapter();
		}

		@Override
		public Adapter caseEventChannel(EventChannel object) {
			return createEventChannelAdapter();
		}

		@Override
		public Adapter caseIRefreshable(IRefreshable object) {
			return createIRefreshableAdapter();
		}

		@Override
		public Adapter caseScaEventChannel(ScaEventChannel object) {
			return createScaEventChannelAdapter();
		}

		@Override
		public Adapter caseStringToObjectMap(Map.Entry<String, EObject> object) {
			return createStringToObjectMapAdapter();
		}

		@Override
		public Adapter casePropertySetOperations(PropertySetOperations object) {
			return createPropertySetOperationsAdapter();
		}

		@Override
		public Adapter casePropertyEmitterOperations(PropertyEmitterOperations object) {
			return createPropertyEmitterOperationsAdapter();
		}

		@Override
		public Adapter caseLifeCycleOperations(LifeCycleOperations object) {
			return createLifeCycleOperationsAdapter();
		}

		@Override
		public Adapter caseTestableObjectOperations(TestableObjectOperations object) {
			return createTestableObjectOperationsAdapter();
		}

		@Override
		public Adapter casePortSupplierOperations(PortSupplierOperations object) {
			return createPortSupplierOperationsAdapter();
		}

		@Override
		public Adapter caseResourceOperations(ResourceOperations object) {
			return createResourceOperationsAdapter();
		}

		@Override
		public Adapter caseDeviceOperations(DeviceOperations object) {
			return createDeviceOperationsAdapter();
		}

		@Override
		public Adapter caseDeviceManagerOperations(DeviceManagerOperations object) {
			return createDeviceManagerOperationsAdapter();
		}

		@Override
		public Adapter caseFileSystemOperations(FileSystemOperations object) {
			return createFileSystemOperationsAdapter();
		}

		@Override
		public Adapter caseDomainManagerOperations(DomainManagerOperations object) {
			return createDomainManagerOperationsAdapter();
		}

		@Override
		public Adapter caseFileManagerOperations(FileManagerOperations object) {
			return createFileManagerOperationsAdapter();
		}

		@Override
		public Adapter caseLoadableDeviceOperations(LoadableDeviceOperations object) {
			return createLoadableDeviceOperationsAdapter();
		}

		@Override
		public Adapter caseExecutableDeviceOperations(ExecutableDeviceOperations object) {
			return createExecutableDeviceOperationsAdapter();
		}

		@Override
		public Adapter casePortOperations(PortOperations object) {
			return createPortOperationsAdapter();
		}

		@Override
		public Adapter caseApplicationOperations(ApplicationOperations object) {
			return createApplicationOperationsAdapter();
		}

		@Override
		public Adapter caseApplicationFactoryOperations(ApplicationFactoryOperations object) {
			return createApplicationFactoryOperationsAdapter();
		}

		@Override
		public Adapter caseObject(org.omg.CORBA.Object object) {
			return createObjectAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated_NOT
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		if (target instanceof EObject) {
			return modelSwitch.doSwitch((EObject) target);
		}
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.CorbaObjWrapper
	 * <em>Corba Obj Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper
	 * @generated
	 */
	public Adapter createCorbaObjWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.DataProviderObject
	 * <em>Data Provider Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.DataProviderObject
	 * @generated
	 */
	public Adapter createDataProviderObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.IDisposable <em>IDisposable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.IDisposable
	 * @generated
	 */
	public Adapter createIDisposableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ProfileObjectWrapper
	 * <em>Profile Object Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper
	 * @generated
	 */
	public Adapter createProfileObjectWrapperAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.Properties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.Properties
	 * @generated
	 */
	public Adapter createPropertiesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaAbstractComponent
	 * <em>Abstract Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaAbstractComponent
	 * @generated
	 */
	public Adapter createScaAbstractComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaPropertyContainer
	 * <em>Property Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaPropertyContainer
	 * @generated
	 */
	public Adapter createScaPropertyContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaPortContainer
	 * <em>Port Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaPortContainer
	 * @generated
	 */
	public Adapter createScaPortContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaAbstractProperty
	 * <em>Abstract Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty
	 * @generated
	 */
	public Adapter createScaAbstractPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaComponent
	 * @generated
	 */
	public Adapter createScaComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDevice <em>Device</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDevice
	 * @generated
	 */
	public Adapter createScaDeviceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDeviceManager
	 * <em>Device Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDeviceManager
	 * @generated
	 */
	public Adapter createScaDeviceManagerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaService
	 * @generated
	 */
	public Adapter createScaServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem
	 * <em>Device Manager File System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDeviceManagerFileSystem
	 * @generated
	 */
	public Adapter createScaDeviceManagerFileSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDocumentRoot <em>Document Root</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot
	 * @generated
	 */
	public Adapter createScaDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDomainManager
	 * <em>Domain Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDomainManager
	 * @generated
	 */
	public Adapter createScaDomainManagerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDomainManagerFileSystem
	 * <em>Domain Manager File System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDomainManagerFileSystem
	 * @generated
	 */
	public Adapter createScaDomainManagerFileSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaDomainManagerRegistry
	 * <em>Domain Manager Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry
	 * @generated
	 */
	public Adapter createScaDomainManagerRegistryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaExecutableDevice
	 * <em>Executable Device</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaExecutableDevice
	 * @generated
	 */
	public Adapter createScaExecutableDeviceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaFileManager <em>File Manager</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaFileManager
	 * @generated
	 */
	public Adapter createScaFileManagerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaFileStore <em>File Store</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaFileStore
	 * @generated
	 */
	public Adapter createScaFileStoreAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaFileSystem <em>File System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaFileSystem
	 * @generated
	 */
	public Adapter createScaFileSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaLoadableDevice
	 * <em>Loadable Device</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaLoadableDevice
	 * @generated
	 */
	public Adapter createScaLoadableDeviceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaPort
	 * @generated
	 */
	public Adapter createScaPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaProvidesPort <em>Provides Port</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaProvidesPort
	 * @generated
	 */
	public Adapter createScaProvidesPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaSimpleProperty
	 * <em>Simple Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaSimpleProperty
	 * @generated
	 */
	public Adapter createScaSimplePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty
	 * <em>Simple Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty
	 * @generated
	 */
	public Adapter createScaSimpleSequencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaStructProperty
	 * <em>Struct Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaStructProperty
	 * @generated
	 */
	public Adapter createScaStructPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaUsesPort <em>Uses Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaUsesPort
	 * @generated
	 */
	public Adapter createScaUsesPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaConnection
	 * @generated
	 */
	public Adapter createScaConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaWaveform <em>Waveform</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaWaveform
	 * @generated
	 */
	public Adapter createScaWaveformAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaWaveformFactory
	 * <em>Waveform Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory
	 * @generated
	 */
	public Adapter createScaWaveformFactoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToStringMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaStructSequenceProperty
	 * <em>Struct Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty
	 * @generated
	 */
	public Adapter createScaStructSequencePropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.IStatusProvider
	 * <em>IStatus Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.IStatusProvider
	 * @generated
	 */
	public Adapter createIStatusProviderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.CosEventChannelAdmin.EventChannel
	 * <em>Event Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.CosEventChannelAdmin.EventChannel
	 * @generated
	 */
	public Adapter createEventChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.IRefreshable <em>IRefreshable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.IRefreshable
	 * @generated
	 */
	public Adapter createIRefreshableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.model.sca.ScaEventChannel <em>Event Channel</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.model.sca.ScaEventChannel
	 * @generated
	 */
	public Adapter createScaEventChannelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>String To Object Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createStringToObjectMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.PropertySetOperations <em>Property Set Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.PropertySetOperations
	 * @generated
	 */
	public Adapter createPropertySetOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.PropertyEmitterOperations
	 * <em>Property Emitter Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.PropertyEmitterOperations
	 * @generated
	 */
	public Adapter createPropertyEmitterOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.LifeCycleOperations <em>Life Cycle Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.LifeCycleOperations
	 * @generated
	 */
	public Adapter createLifeCycleOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.TestableObjectOperations
	 * <em>Testable Object Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.TestableObjectOperations
	 * @generated
	 */
	public Adapter createTestableObjectOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.PortSupplierOperations <em>Port Supplier Operations</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.PortSupplierOperations
	 * @generated
	 */
	public Adapter createPortSupplierOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.ResourceOperations <em>Resource Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.ResourceOperations
	 * @generated
	 */
	public Adapter createResourceOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.DeviceOperations <em>Device Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.DeviceOperations
	 * @generated
	 */
	public Adapter createDeviceOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.DeviceManagerOperations
	 * <em>Device Manager Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.DeviceManagerOperations
	 * @generated
	 */
	public Adapter createDeviceManagerOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.FileSystemOperations <em>File System Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.FileSystemOperations
	 * @generated
	 */
	public Adapter createFileSystemOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.DomainManagerOperations
	 * <em>Domain Manager Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.DomainManagerOperations
	 * @generated
	 */
	public Adapter createDomainManagerOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.FileManagerOperations <em>File Manager Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.FileManagerOperations
	 * @generated
	 */
	public Adapter createFileManagerOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.LoadableDeviceOperations
	 * <em>Loadable Device Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.LoadableDeviceOperations
	 * @generated
	 */
	public Adapter createLoadableDeviceOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.ExecutableDeviceOperations
	 * <em>Executable Device Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.ExecutableDeviceOperations
	 * @generated
	 */
	public Adapter createExecutableDeviceOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.PortOperations <em>Port Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.PortOperations
	 * @generated
	 */
	public Adapter createPortOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.ApplicationOperations <em>Application Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.ApplicationOperations
	 * @generated
	 */
	public Adapter createApplicationOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link CF.ApplicationFactoryOperations
	 * <em>Application Factory Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see CF.ApplicationFactoryOperations
	 * @generated
	 */
	public Adapter createApplicationFactoryOperationsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.CORBA.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.CORBA.Object
	 * @generated
	 */
	public Adapter createObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} // ScaAdapterFactory
