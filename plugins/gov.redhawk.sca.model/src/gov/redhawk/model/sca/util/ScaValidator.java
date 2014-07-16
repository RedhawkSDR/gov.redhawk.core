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
import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.RefreshDepth;
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
import gov.redhawk.model.sca.ScaFactory;
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
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.model.sca.services.IScaDataProviderService;
import java.net.URI;
import java.util.Collection;
import java.util.Map;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.omg.CORBA.Any;
import org.omg.CosEventChannelAdmin.EventChannel;
import org.omg.PortableServer.POA;
import CF.DataType;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;
import gov.redhawk.model.sca.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * @since 10.0
 * <!-- end-user-doc -->
 * @see gov.redhawk.model.sca.ScaPackage
 * @generated
 */
public class ScaValidator extends EObjectValidator {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ScaValidator INSTANCE = new ScaValidator();
	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic
	 * {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "gov.redhawk.model.sca";
	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;
	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a
	 * derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
		return ScaPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
		case ScaPackage.CORBA_OBJ_WRAPPER:
			return validateCorbaObjWrapper((CorbaObjWrapper< ? >) value, diagnostics, context);
		case ScaPackage.DATA_PROVIDER_OBJECT:
			return validateDataProviderObject((DataProviderObject) value, diagnostics, context);
		case ScaPackage.IDISPOSABLE:
			return validateIDisposable((IDisposable) value, diagnostics, context);
		case ScaPackage.PROFILE_OBJECT_WRAPPER:
			return validateProfileObjectWrapper((ProfileObjectWrapper< ? >) value, diagnostics, context);
		case ScaPackage.PROPERTIES:
			return validateProperties((Properties) value, diagnostics, context);
		case ScaPackage.SCA_ABSTRACT_COMPONENT:
			return validateScaAbstractComponent((ScaAbstractComponent< ? >) value, diagnostics, context);
		case ScaPackage.SCA_PROPERTY_CONTAINER:
			return validateScaPropertyContainer((ScaPropertyContainer< ? , ? >) value, diagnostics, context);
		case ScaPackage.SCA_PORT_CONTAINER:
			return validateScaPortContainer((ScaPortContainer) value, diagnostics, context);
		case ScaPackage.SCA_ABSTRACT_PROPERTY:
			return validateScaAbstractProperty((ScaAbstractProperty< ? >) value, diagnostics, context);
		case ScaPackage.SCA_COMPONENT:
			return validateScaComponent((ScaComponent) value, diagnostics, context);
		case ScaPackage.SCA_DEVICE:
			return validateScaDevice((ScaDevice< ? >) value, diagnostics, context);
		case ScaPackage.SCA_DEVICE_MANAGER:
			return validateScaDeviceManager((ScaDeviceManager) value, diagnostics, context);
		case ScaPackage.SCA_SERVICE:
			return validateScaService((ScaService) value, diagnostics, context);
		case ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM:
			return validateScaDeviceManagerFileSystem((ScaDeviceManagerFileSystem) value, diagnostics, context);
		case ScaPackage.SCA_DOCUMENT_ROOT:
			return validateScaDocumentRoot((ScaDocumentRoot) value, diagnostics, context);
		case ScaPackage.SCA_DOMAIN_MANAGER:
			return validateScaDomainManager((ScaDomainManager) value, diagnostics, context);
		case ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM:
			return validateScaDomainManagerFileSystem((ScaDomainManagerFileSystem) value, diagnostics, context);
		case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY:
			return validateScaDomainManagerRegistry((ScaDomainManagerRegistry) value, diagnostics, context);
		case ScaPackage.SCA_EXECUTABLE_DEVICE:
			return validateScaExecutableDevice((ScaExecutableDevice) value, diagnostics, context);
		case ScaPackage.SCA_FILE_MANAGER:
			return validateScaFileManager((ScaFileManager) value, diagnostics, context);
		case ScaPackage.SCA_FILE_STORE:
			return validateScaFileStore((ScaFileStore) value, diagnostics, context);
		case ScaPackage.SCA_FILE_SYSTEM:
			return validateScaFileSystem((ScaFileSystem< ? >) value, diagnostics, context);
		case ScaPackage.SCA_LOADABLE_DEVICE:
			return validateScaLoadableDevice((ScaLoadableDevice< ? >) value, diagnostics, context);
		case ScaPackage.SCA_PORT:
			return validateScaPort((ScaPort< ? , ? >) value, diagnostics, context);
		case ScaPackage.SCA_PROVIDES_PORT:
			return validateScaProvidesPort((ScaProvidesPort) value, diagnostics, context);
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			return validateScaSimpleProperty((ScaSimpleProperty) value, diagnostics, context);
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			return validateScaSimpleSequenceProperty((ScaSimpleSequenceProperty) value, diagnostics, context);
		case ScaPackage.SCA_STRUCT_PROPERTY:
			return validateScaStructProperty((ScaStructProperty) value, diagnostics, context);
		case ScaPackage.SCA_USES_PORT:
			return validateScaUsesPort((ScaUsesPort) value, diagnostics, context);
		case ScaPackage.SCA_CONNECTION:
			return validateScaConnection((ScaConnection) value, diagnostics, context);
		case ScaPackage.SCA_WAVEFORM:
			return validateScaWaveform((ScaWaveform) value, diagnostics, context);
		case ScaPackage.SCA_WAVEFORM_FACTORY:
			return validateScaWaveformFactory((ScaWaveformFactory) value, diagnostics, context);
		case ScaPackage.STRING_TO_STRING_MAP:
			return validateStringToStringMap((Map.Entry< ? , ? >) value, diagnostics, context);
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			return validateScaStructSequenceProperty((ScaStructSequenceProperty) value, diagnostics, context);
		case ScaPackage.ISTATUS_PROVIDER:
			return validateIStatusProvider((IStatusProvider) value, diagnostics, context);
		case ScaPackage.EVENT_CHANNEL:
			return validateEventChannel((EventChannel) value, diagnostics, context);
		case ScaPackage.IREFRESHABLE:
			return validateIRefreshable((IRefreshable) value, diagnostics, context);
		case ScaPackage.SCA_EVENT_CHANNEL:
			return validateScaEventChannel((ScaEventChannel) value, diagnostics, context);
		case ScaPackage.STRING_TO_OBJECT_MAP:
			return validateStringToObjectMap((Map.Entry< ? , ? >) value, diagnostics, context);
		case ScaPackage.DOMAIN_CONNECTION_STATE:
			return validateDomainConnectionState((DomainConnectionState) value, diagnostics, context);
		case ScaPackage.REFRESH_DEPTH:
			return validateRefreshDepth((RefreshDepth) value, diagnostics, context);
		case ScaPackage.ADMIN_TYPE:
			return validateAdminType((AdminType) value, diagnostics, context);
		case ScaPackage.DOMAIN_CONNECTION_EXCEPTION:
			return validateDomainConnectionException((DomainConnectionException) value, diagnostics, context);
		case ScaPackage.DOMAIN_CONNECTION_STATE_OBJECT:
			return validateDomainConnectionStateObject((DomainConnectionState) value, diagnostics, context);
		case ScaPackage.IFILE_STORE:
			return validateIFileStore((IFileStore) value, diagnostics, context);
		case ScaPackage.IPROGRESS_MONITOR:
			return validateIProgressMonitor((IProgressMonitor) value, diagnostics, context);
		case ScaPackage.ISCA_DATA_PROVIDER:
			return validateIScaDataProvider((IScaDataProvider) value, diagnostics, context);
		case ScaPackage.ISCA_DATA_PROVIDER_SERVICE:
			return validateIScaDataProviderService((IScaDataProviderService) value, diagnostics, context);
		case ScaPackage.ISTATUS:
			return validateIStatus((IStatus) value, diagnostics, context);
		case ScaPackage.OBJECT:
			return validateObject((org.omg.CORBA.Object) value, diagnostics, context);
		case ScaPackage.OBJECT_ARRAY:
			return validateObjectArray((Object[]) value, diagnostics, context);
		case ScaPackage.OPERATIONAL_TYPE:
			return validateOperationalType((OperationalType) value, diagnostics, context);
		case ScaPackage.REFRESH_DEPTH_OBJECT:
			return validateRefreshDepthObject((RefreshDepth) value, diagnostics, context);
		case ScaPackage.POA:
			return validatePOA((POA) value, diagnostics, context);
		case ScaPackage.URI:
			return validateURI((URI) value, diagnostics, context);
		case ScaPackage.USAGE_TYPE:
			return validateUsageType((UsageType) value, diagnostics, context);
		case ScaPackage.DATA_TYPE_ARRAY:
			return validateDataTypeArray((DataType[]) value, diagnostics, context);
		case ScaPackage.ANY:
			return validateAny((Any) value, diagnostics, context);
		default:
			return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCorbaObjWrapper(CorbaObjWrapper< ? > corbaObjWrapper, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(corbaObjWrapper, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataProviderObject(DataProviderObject dataProviderObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataProviderObject, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIDisposable(IDisposable iDisposable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iDisposable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProfileObjectWrapper(ProfileObjectWrapper< ? > profileObjectWrapper, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(profileObjectWrapper, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProperties(Properties properties, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(properties, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaAbstractComponent(ScaAbstractComponent< ? > scaAbstractComponent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaAbstractComponent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaPropertyContainer(ScaPropertyContainer< ? , ? > scaPropertyContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaPropertyContainer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaPortContainer(ScaPortContainer scaPortContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaPortContainer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaAbstractProperty(ScaAbstractProperty< ? > scaAbstractProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaAbstractProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaComponent(ScaComponent scaComponent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaComponent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDevice(ScaDevice< ? > scaDevice, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDevice, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDeviceManager(ScaDeviceManager scaDeviceManager, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDeviceManager, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaService(ScaService scaService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaService, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDeviceManagerFileSystem(ScaDeviceManagerFileSystem scaDeviceManagerFileSystem, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDeviceManagerFileSystem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDocumentRoot(ScaDocumentRoot scaDocumentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDocumentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDomainManager(ScaDomainManager scaDomainManager, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDomainManager, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDomainManagerFileSystem(ScaDomainManagerFileSystem scaDomainManagerFileSystem, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDomainManagerFileSystem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaDomainManagerRegistry(ScaDomainManagerRegistry scaDomainManagerRegistry, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaDomainManagerRegistry, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaExecutableDevice(ScaExecutableDevice scaExecutableDevice, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaExecutableDevice, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaFileManager(ScaFileManager scaFileManager, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaFileManager, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaFileStore(ScaFileStore scaFileStore, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaFileStore, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaFileSystem(ScaFileSystem< ? > scaFileSystem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaFileSystem, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaLoadableDevice(ScaLoadableDevice< ? > scaLoadableDevice, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaLoadableDevice, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaPort(ScaPort< ? , ? > scaPort, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaPort, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaProvidesPort(ScaProvidesPort scaProvidesPort, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaProvidesPort, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaSimpleProperty(ScaSimpleProperty scaSimpleProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaSimpleProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaSimpleSequenceProperty(ScaSimpleSequenceProperty scaSimpleSequenceProperty, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaSimpleSequenceProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaStructProperty(ScaStructProperty scaStructProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaStructProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaUsesPort(ScaUsesPort scaUsesPort, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaUsesPort, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaConnection(ScaConnection scaConnection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaConnection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaWaveform(ScaWaveform scaWaveform, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaWaveform, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaWaveformFactory(ScaWaveformFactory scaWaveformFactory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaWaveformFactory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringToStringMap(Map.Entry< ? , ? > stringToStringMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) stringToStringMap, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaStructSequenceProperty(ScaStructSequenceProperty scaStructSequenceProperty, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaStructSequenceProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIStatusProvider(IStatusProvider iStatusProvider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(iStatusProvider, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEventChannel(EventChannel eventChannel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) eventChannel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIRefreshable(IRefreshable iRefreshable, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) iRefreshable, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScaEventChannel(ScaEventChannel scaEventChannel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scaEventChannel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringToObjectMap(Map.Entry< ? , ? > stringToObjectMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject) stringToObjectMap, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDomainConnectionState(DomainConnectionState domainConnectionState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRefreshDepth(RefreshDepth refreshDepth, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAdminType(AdminType adminType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateAdminType_Enumeration(adminType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateAdminType_Enumeration
	 */
	public static final Collection<Object> ADMIN_TYPE__ENUMERATION__VALUES = wrapEnumerationValues(new Object[] {
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getAdminType(), "LOCKED"),
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getAdminType(), "SHUTTING DOWN"),
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getAdminType(), "UNLOCKED") });

	/**
	 * Validates the Enumeration constraint of '<em>Admin Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAdminType_Enumeration(AdminType adminType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = ADMIN_TYPE__ENUMERATION__VALUES.contains(adminType);
		if (!result && diagnostics != null)
			reportEnumerationViolation(ScaPackage.Literals.ADMIN_TYPE, adminType, ADMIN_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDomainConnectionException(DomainConnectionException domainConnectionException, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDomainConnectionStateObject(DomainConnectionState domainConnectionStateObject, DiagnosticChain diagnostics,
		Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIFileStore(IFileStore iFileStore, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIProgressMonitor(IProgressMonitor iProgressMonitor, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIScaDataProvider(IScaDataProvider iScaDataProvider, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIScaDataProviderService(IScaDataProviderService iScaDataProviderService, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIStatus(IStatus iStatus, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateObject(org.omg.CORBA.Object object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateObjectArray(Object[] objectArray, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationalType(OperationalType operationalType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateOperationalType_Enumeration(operationalType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateOperationalType_Enumeration
	 */
	public static final Collection<Object> OPERATIONAL_TYPE__ENUMERATION__VALUES = wrapEnumerationValues(new Object[] {
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getOperationalType(), "ENABLED"),
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getOperationalType(), "DISABLED") });

	/**
	 * Validates the Enumeration constraint of '<em>Operational Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationalType_Enumeration(OperationalType operationalType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = OPERATIONAL_TYPE__ENUMERATION__VALUES.contains(operationalType);
		if (!result && diagnostics != null)
			reportEnumerationViolation(ScaPackage.Literals.OPERATIONAL_TYPE, operationalType, OPERATIONAL_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRefreshDepthObject(RefreshDepth refreshDepthObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePOA(POA poa, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateURI(URI uri, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUsageType(UsageType usageType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateUsageType_Enumeration(usageType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @see #validateUsageType_Enumeration
	 */
	public static final Collection<Object> USAGE_TYPE__ENUMERATION__VALUES = wrapEnumerationValues(new Object[] {
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getUsageType(), "ACTIVE"),
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getUsageType(), "BUSY"),
		ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getUsageType(), "IDLE") });

	/**
	 * Validates the Enumeration constraint of '<em>Usage Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUsageType_Enumeration(UsageType usageType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = USAGE_TYPE__ENUMERATION__VALUES.contains(usageType);
		if (!result && diagnostics != null)
			reportEnumerationViolation(ScaPackage.Literals.USAGE_TYPE, usageType, USAGE_TYPE__ENUMERATION__VALUES, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataTypeArray(DataType[] dataTypeArray, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAny(Any any, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} // ScaValidator
