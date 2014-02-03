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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.RefreshDepth;
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
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaLoadableDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import java.net.URI;
import java.util.Map;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import CF.Device;
import CF.LoadableDevice;
import gov.redhawk.model.sca.*;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>.
 * @since 12.0 
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaFactoryImpl extends EFactoryImpl implements ScaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScaFactory init() {
		try {
			ScaFactory theScaFactory = (ScaFactory) EPackage.Registry.INSTANCE.getEFactory(ScaPackage.eNS_URI);
			if (theScaFactory != null) {
				return theScaFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ScaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaFactoryImpl() {
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
		case ScaPackage.PROPERTIES:
			return createProperties();
		case ScaPackage.SCA_COMPONENT:
			return createScaComponent();
		case ScaPackage.SCA_DEVICE:
			return createScaDevice();
		case ScaPackage.SCA_DEVICE_MANAGER:
			return createScaDeviceManager();
		case ScaPackage.SCA_SERVICE:
			return createScaService();
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM:
			return createScaDeviceManagerFileSystem();
		case ScaPackage.SCA_DOCUMENT_ROOT:
			return createScaDocumentRoot();
		case ScaPackage.SCA_DOMAIN_MANAGER:
			return createScaDomainManager();
		case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM:
			return createScaDomainManagerFileSystem();
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY:
			return createScaDomainManagerRegistry();
		case ScaPackage.SCA_EXECUTABLE_DEVICE:
			return createScaExecutableDevice();
		case ScaPackage.SCA_FILE_STORE:
			return createScaFileStore();
		case ScaPackage.SCA_LOADABLE_DEVICE:
			return createScaLoadableDevice();
		case ScaPackage.SCA_PROVIDES_PORT:
			return createScaProvidesPort();
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			return createScaSimpleProperty();
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			return createScaSimpleSequenceProperty();
		case ScaPackage.SCA_STRUCT_PROPERTY:
			return createScaStructProperty();
		case ScaPackage.SCA_USES_PORT:
			return createScaUsesPort();
		case ScaPackage.SCA_CONNECTION:
			return createScaConnection();
		case ScaPackage.SCA_WAVEFORM:
			return createScaWaveform();
		case ScaPackage.SCA_WAVEFORM_FACTORY:
			return createScaWaveformFactory();
		case ScaPackage.STRING_TO_STRING_MAP:
			return (EObject) createStringToStringMap();
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			return createScaStructSequenceProperty();
		case ScaPackage.SCA_EVENT_CHANNEL:
			return createScaEventChannel();
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
		case ScaPackage.DOMAIN_CONNECTION_STATE:
			return createDomainConnectionStateFromString(eDataType, initialValue);
		case ScaPackage.REFRESH_DEPTH:
			return createRefreshDepthFromString(eDataType, initialValue);
		case ScaPackage.ADMIN_TYPE:
			return createAdminTypeFromString(eDataType, initialValue);
		case ScaPackage.DOMAIN_CONNECTION_EXCEPTION:
			return createDomainConnectionExceptionFromString(eDataType, initialValue);
		case ScaPackage.DOMAIN_CONNECTION_STATE_OBJECT:
			return createDomainConnectionStateObjectFromString(eDataType, initialValue);
		case ScaPackage.IFILE_STORE:
			return createIFileStoreFromString(eDataType, initialValue);
		case ScaPackage.OBJECT:
			return createObjectFromString(eDataType, initialValue);
		case ScaPackage.OPERATIONAL_TYPE:
			return createOperationalTypeFromString(eDataType, initialValue);
		case ScaPackage.REFRESH_DEPTH_OBJECT:
			return createRefreshDepthObjectFromString(eDataType, initialValue);
		case ScaPackage.URI:
			return createURIFromString(eDataType, initialValue);
		case ScaPackage.USAGE_TYPE:
			return createUsageTypeFromString(eDataType, initialValue);
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
		case ScaPackage.DOMAIN_CONNECTION_STATE:
			return convertDomainConnectionStateToString(eDataType, instanceValue);
		case ScaPackage.REFRESH_DEPTH:
			return convertRefreshDepthToString(eDataType, instanceValue);
		case ScaPackage.ADMIN_TYPE:
			return convertAdminTypeToString(eDataType, instanceValue);
		case ScaPackage.DOMAIN_CONNECTION_EXCEPTION:
			return convertDomainConnectionExceptionToString(eDataType, instanceValue);
		case ScaPackage.DOMAIN_CONNECTION_STATE_OBJECT:
			return convertDomainConnectionStateObjectToString(eDataType, instanceValue);
		case ScaPackage.IFILE_STORE:
			return convertIFileStoreToString(eDataType, instanceValue);
		case ScaPackage.OBJECT:
			return convertObjectToString(eDataType, instanceValue);
		case ScaPackage.OPERATIONAL_TYPE:
			return convertOperationalTypeToString(eDataType, instanceValue);
		case ScaPackage.REFRESH_DEPTH_OBJECT:
			return convertRefreshDepthObjectToString(eDataType, instanceValue);
		case ScaPackage.URI:
			return convertURIToString(eDataType, instanceValue);
		case ScaPackage.USAGE_TYPE:
			return convertUsageTypeToString(eDataType, instanceValue);
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
	public Properties createProperties() {
		PropertiesImpl properties = new PropertiesImpl();
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaComponent createScaComponent() {
		ScaComponentImpl scaComponent = new ScaComponentImpl();
		return scaComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public < D extends Device > ScaDevice<D> createScaDevice() {
		ScaDeviceImpl<D> scaDevice = new ScaDeviceImpl<D>();
		return scaDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDeviceManager createScaDeviceManager() {
		ScaDeviceManagerImpl scaDeviceManager = new ScaDeviceManagerImpl();
		return scaDeviceManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaService createScaService() {
		ScaServiceImpl scaService = new ScaServiceImpl();
		return scaService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDeviceManagerFileSystem createScaDeviceManagerFileSystem() {
		ScaDeviceManagerFileSystemImpl scaDeviceManagerFileSystem = new ScaDeviceManagerFileSystemImpl();
		return scaDeviceManagerFileSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDocumentRoot createScaDocumentRoot() {
		ScaDocumentRootImpl scaDocumentRoot = new ScaDocumentRootImpl();
		return scaDocumentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDomainManager createScaDomainManager() {
		ScaDomainManagerImpl scaDomainManager = new ScaDomainManagerImpl();
		return scaDomainManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDomainManagerFileSystem createScaDomainManagerFileSystem() {
		ScaDomainManagerFileSystemImpl scaDomainManagerFileSystem = new ScaDomainManagerFileSystemImpl();
		return scaDomainManagerFileSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDomainManagerRegistry createScaDomainManagerRegistry() {
		ScaDomainManagerRegistryImpl scaDomainManagerRegistry = new ScaDomainManagerRegistryImpl();
		return scaDomainManagerRegistry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaExecutableDevice createScaExecutableDevice() {
		ScaExecutableDeviceImpl scaExecutableDevice = new ScaExecutableDeviceImpl();
		return scaExecutableDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaFileStore createScaFileStore() {
		ScaFileStoreImpl scaFileStore = new ScaFileStoreImpl();
		return scaFileStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public < L extends LoadableDevice > ScaLoadableDevice<L> createScaLoadableDevice() {
		ScaLoadableDeviceImpl<L> scaLoadableDevice = new ScaLoadableDeviceImpl<L>();
		return scaLoadableDevice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaProvidesPort createScaProvidesPort() {
		ScaProvidesPortImpl scaProvidesPort = new ScaProvidesPortImpl();
		return scaProvidesPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaSimpleProperty createScaSimpleProperty() {
		ScaSimplePropertyImpl scaSimpleProperty = new ScaSimplePropertyImpl();
		return scaSimpleProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaSimpleSequenceProperty createScaSimpleSequenceProperty() {
		ScaSimpleSequencePropertyImpl scaSimpleSequenceProperty = new ScaSimpleSequencePropertyImpl();
		return scaSimpleSequenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaStructProperty createScaStructProperty() {
		ScaStructPropertyImpl scaStructProperty = new ScaStructPropertyImpl();
		return scaStructProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaUsesPort createScaUsesPort() {
		ScaUsesPortImpl scaUsesPort = new ScaUsesPortImpl();
		return scaUsesPort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaConnection createScaConnection() {
		ScaConnectionImpl scaConnection = new ScaConnectionImpl();
		return scaConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaWaveform createScaWaveform() {
		ScaWaveformImpl scaWaveform = new ScaWaveformImpl();
		return scaWaveform;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaWaveformFactory createScaWaveformFactory() {
		ScaWaveformFactoryImpl scaWaveformFactory = new ScaWaveformFactoryImpl();
		return scaWaveformFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createStringToStringMap() {
		StringToStringMapImpl stringToStringMap = new StringToStringMapImpl();
		return stringToStringMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaStructSequenceProperty createScaStructSequenceProperty() {
		ScaStructSequencePropertyImpl scaStructSequenceProperty = new ScaStructSequencePropertyImpl();
		return scaStructSequenceProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaEventChannel createScaEventChannel() {
		ScaEventChannelImpl scaEventChannel = new ScaEventChannelImpl();
		return scaEventChannel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainConnectionState createDomainConnectionStateFromString(EDataType eDataType, String initialValue) {
		DomainConnectionState result = DomainConnectionState.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDomainConnectionStateToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefreshDepth createRefreshDepthFromString(EDataType eDataType, String initialValue) {
		RefreshDepth result = RefreshDepth.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRefreshDepthToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 3.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertIFileStoreToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue instanceof IFileStore) {
			return ((IFileStore) instanceValue).toURI().toString();
		}
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.omg.CORBA.Object createObjectFromString(EDataType eDataType, String initialValue) {
		return (org.omg.CORBA.Object) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertObjectToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public URI createURIFromString(EDataType eDataType, String initialValue) {
		return (initialValue == null) ? null : URI.create(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *  @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public OperationalType createOperationalTypeFromString(EDataType eDataType, String initialValue) {
		if ("ENABLED".equalsIgnoreCase(initialValue)) {
			return OperationalType.ENABLED;
		} else if ("DISABLED".equalsIgnoreCase(initialValue)) {
			return OperationalType.DISABLED;
		}
		return (OperationalType) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *  @since 10.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertOperationalTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == OperationalType.ENABLED) {
			return "ENABLED";
		} else if (instanceValue == OperationalType.DISABLED) {
			return "DISABLED";
		}
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RefreshDepth createRefreshDepthObjectFromString(EDataType eDataType, String initialValue) {
		return createRefreshDepthFromString(ScaPackage.Literals.REFRESH_DEPTH, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRefreshDepthObjectToString(EDataType eDataType, Object instanceValue) {
		return convertRefreshDepthToString(ScaPackage.Literals.REFRESH_DEPTH, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *  @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 *
	 */
	public UsageType createUsageTypeFromString(EDataType eDataType, String initialValue) {
		if ("ACTIVE".equalsIgnoreCase(initialValue)) {
			return UsageType.ACTIVE;
		} else if ("BUSY".equalsIgnoreCase(initialValue)) {
			return UsageType.BUSY;
		} else if ("IDLE".equalsIgnoreCase(initialValue)) {
			return UsageType.IDLE;
		}
		return (UsageType) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 10.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertUsageTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == UsageType.ACTIVE) {
			return "ACTIVE";
		} else if (instanceValue == UsageType.BUSY) {
			return "BUSY";
		} else if (instanceValue == UsageType.IDLE) {
			return "IDLE";
		}
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaPackage getScaPackage() {
		return (ScaPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ScaPackage getPackage() {
		return ScaPackage.eINSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 *  @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public AdminType createAdminTypeFromString(EDataType eDataType, String initialValue) {
		if ("LOCKED".equalsIgnoreCase(initialValue)) {
			return AdminType.LOCKED;
		} else if ("SHUTTING DOWN".equalsIgnoreCase(initialValue)) {
			return AdminType.SHUTTING_DOWN;
		} else if ("UNLOCKED".equalsIgnoreCase(initialValue)) {
			return AdminType.UNLOCKED;
		}
		return (AdminType) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 *  @since 10.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String convertAdminTypeToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == AdminType.LOCKED) {
			return "LOCKED";
		} else if (instanceValue == AdminType.SHUTTING_DOWN) {
			return "SHUTTING DOWN";
		} else if (instanceValue == AdminType.UNLOCKED) {
			return "UNLOCKED";
		}
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainConnectionException createDomainConnectionExceptionFromString(EDataType eDataType, String initialValue) {
		return (DomainConnectionException) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDomainConnectionExceptionToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainConnectionState createDomainConnectionStateObjectFromString(EDataType eDataType, String initialValue) {
		return createDomainConnectionStateFromString(ScaPackage.Literals.DOMAIN_CONNECTION_STATE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDomainConnectionStateObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDomainConnectionStateToString(ScaPackage.Literals.DOMAIN_CONNECTION_STATE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IFileStore createIFileStoreFromString(EDataType eDataType, String initialValue) {
		return (IFileStore) super.createFromString(eDataType, initialValue);
	}

} // ScaFactoryImpl
