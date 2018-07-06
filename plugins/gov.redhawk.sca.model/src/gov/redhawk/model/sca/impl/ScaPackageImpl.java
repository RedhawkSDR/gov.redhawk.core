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
import gov.redhawk.model.sca.ScaEventChannel;
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
import gov.redhawk.model.sca.WaveformsContainer;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.model.sca.services.IScaDataProviderService;
import gov.redhawk.model.sca.util.ScaValidator;

import java.util.Map;

import mil.jpeojtrs.sca.cf.CfPackage;
import mil.jpeojtrs.sca.cf.extended.ExtendedPackage;
import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dmd.DmdPackage;
import mil.jpeojtrs.sca.dpd.DpdPackage;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SpdPackage;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.omg.CORBA.Any;

import org.omg.CosEventChannelAdmin.EventChannel;
import CF.DataType;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>.
 * 
 * @since 13.0
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaPackageImpl extends EPackageImpl implements ScaPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass corbaObjWrapperEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataProviderObjectEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iDisposableEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass profileObjectWrapperEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertiesEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaAbstractComponentEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaPropertyContainerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaPortContainerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaAbstractPropertyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaComponentEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDeviceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDeviceManagerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaServiceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDeviceManagerFileSystemEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDocumentRootEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDomainManagerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDomainManagerFileSystemEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaDomainManagerRegistryEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaExecutableDeviceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaFileManagerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaFileStoreEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaFileSystemEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaLoadableDeviceEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaPortEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaProvidesPortEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaSimplePropertyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaSimpleSequencePropertyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaStructPropertyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaUsesPortEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaConnectionEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaWaveformEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaWaveformFactoryEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToStringMapEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaStructSequencePropertyEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iStatusProviderEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eventChannelEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iRefreshableEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scaEventChannelEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToObjectMapEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass waveformsContainerEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum domainConnectionStateEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum refreshDepthEEnum = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType adminTypeEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType domainConnectionExceptionEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType domainConnectionStateObjectEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iFileStoreEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iProgressMonitorEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iScaDataProviderEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iScaDataProviderServiceEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType iStatusEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType objectArrayEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType operationalTypeEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType refreshDepthObjectEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType poaEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType uriEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType usageTypeEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType dataTypeArrayEDataType = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType anyEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package
	 * package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see gov.redhawk.model.sca.ScaPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ScaPackageImpl() {
		super(eNS_URI, ScaFactory.eINSTANCE);
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
	 * <p>
	 * This method is used to initialize {@link ScaPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ScaPackage init() {
		if (isInited)
			return (ScaPackage) EPackage.Registry.INSTANCE.getEPackage(ScaPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredScaPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ScaPackageImpl theScaPackage = registeredScaPackage instanceof ScaPackageImpl ? (ScaPackageImpl) registeredScaPackage : new ScaPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		CfPackage.eINSTANCE.eClass();
		SpdPackage.eINSTANCE.eClass();
		ScdPackage.eINSTANCE.eClass();
		PrfPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();
		SadPackage.eINSTANCE.eClass();
		DcdPackage.eINSTANCE.eClass();
		DmdPackage.eINSTANCE.eClass();
		DpdPackage.eINSTANCE.eClass();
		PartitioningPackage.eINSTANCE.eClass();
		ExtendedPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theScaPackage.createPackageContents();

		// Initialize created meta-data
		theScaPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put(theScaPackage, new EValidator.Descriptor() {
			public EValidator getEValidator() {
				return ScaValidator.INSTANCE;
			}
		});

		// Mark meta-data to indicate it can't be changed
		theScaPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ScaPackage.eNS_URI, theScaPackage);
		return theScaPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCorbaObjWrapper() {
		return corbaObjWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCorbaObjWrapper_Ior() {
		return (EAttribute) corbaObjWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCorbaObjWrapper_Obj() {
		return (EAttribute) corbaObjWrapperEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCorbaObjWrapper_CorbaObj() {
		return (EAttribute) corbaObjWrapperEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCorbaObjWrapper_FeatureData() {
		return (EReference) corbaObjWrapperEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDataProviderObject() {
		return dataProviderObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataProviderObject_DataProviders() {
		return (EAttribute) dataProviderObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataProviderObject_DataProvidersEnabled() {
		return (EAttribute) dataProviderObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataProviderObject_EnabledDataProviders() {
		return (EAttribute) dataProviderObjectEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIDisposable() {
		return iDisposableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIDisposable_Disposed() {
		return (EAttribute) iDisposableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProfileObjectWrapper() {
		return profileObjectWrapperEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProfileObjectWrapper_ProfileURI() {
		return (EAttribute) profileObjectWrapperEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProfileObjectWrapper_ProfileObj() {
		return (EReference) profileObjectWrapperEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProperties() {
		return propertiesEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperties_Property() {
		return (EReference) propertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaAbstractComponent() {
		return scaAbstractComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractComponent_Identifier() {
		return (EAttribute) scaAbstractComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractComponent_Started() {
		return (EAttribute) scaAbstractComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScaAbstractComponent_Profile() {
		return (EAttribute) scaAbstractComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaPropertyContainer() {
		return scaPropertyContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaPropertyContainer_Properties() {
		return (EReference) scaPropertyContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaPortContainer() {
		return scaPortContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaPortContainer_Ports() {
		return (EReference) scaPortContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaAbstractProperty() {
		return scaAbstractPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaAbstractProperty_Definition() {
		return (EReference) scaAbstractPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractProperty_Description() {
		return (EAttribute) scaAbstractPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractProperty_Id() {
		return (EAttribute) scaAbstractPropertyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractProperty_Mode() {
		return (EAttribute) scaAbstractPropertyEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractProperty_Name() {
		return (EAttribute) scaAbstractPropertyEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaAbstractProperty_IgnoreRemoteSet() {
		return (EAttribute) scaAbstractPropertyEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaComponent() {
		return scaComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaComponent_ComponentInstantiation() {
		return (EReference) scaComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaComponent_Devices() {
		return (EReference) scaComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaComponent_InstantiationIdentifier() {
		return (EAttribute) scaComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaComponent_Waveform() {
		return (EReference) scaComponentEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaComponent_Name() {
		return (EAttribute) scaComponentEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDevice() {
		return scaDeviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDevice_ChildDevices() {
		return (EReference) scaDeviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDevice_AdminState() {
		return (EAttribute) scaDeviceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDevice_Label() {
		return (EAttribute) scaDeviceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDevice_OperationalState() {
		return (EAttribute) scaDeviceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDevice_UsageState() {
		return (EAttribute) scaDeviceEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDevice_ParentDevice() {
		return (EReference) scaDeviceEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDevice_DevMgr() {
		return (EReference) scaDeviceEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDeviceManager() {
		return scaDeviceManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDeviceManager_Devices() {
		return (EAttribute) scaDeviceManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_RootDevices() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_ChildDevices() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_AllDevices() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_FileSystem() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_DomMgr() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDeviceManager_Identifier() {
		return (EAttribute) scaDeviceManagerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDeviceManager_Label() {
		return (EAttribute) scaDeviceManagerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManager_Services() {
		return (EReference) scaDeviceManagerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDeviceManager_Profile() {
		return (EAttribute) scaDeviceManagerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaService() {
		return scaServiceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaService_Name() {
		return (EAttribute) scaServiceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaService_DevMgr() {
		return (EReference) scaServiceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDeviceManagerFileSystem() {
		return scaDeviceManagerFileSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDeviceManagerFileSystem_DeviceManager() {
		return (EReference) scaDeviceManagerFileSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDocumentRoot() {
		return scaDocumentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDocumentRoot_Mixed() {
		return (EAttribute) scaDocumentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDocumentRoot_XMLNSPrefixMap() {
		return (EReference) scaDocumentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDocumentRoot_XSISchemaLocation() {
		return (EReference) scaDocumentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDocumentRoot_DomainManagerRegistry() {
		return (EReference) scaDocumentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDomainManager() {
		return scaDomainManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_WaveformFactories() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_Waveforms() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_DeviceManagers() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_FileManager() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_ConnectionPropertiesContainer() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManager_ConnectionProperties() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_AutoConnect() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_Connected() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_Identifier() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_Name() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_RootContext() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_State() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaDomainManager_Profile() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getScaDomainManager_EventChannels() {
		return (EReference) scaDomainManagerEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScaDomainManager_LocalName() {
		return (EAttribute) scaDomainManagerEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDomainManagerFileSystem() {
		return scaDomainManagerFileSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManagerFileSystem_DomMgr() {
		return (EReference) scaDomainManagerFileSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaDomainManagerRegistry() {
		return scaDomainManagerRegistryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaDomainManagerRegistry_Domains() {
		return (EReference) scaDomainManagerRegistryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaExecutableDevice() {
		return scaExecutableDeviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaFileManager() {
		return scaFileManagerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaFileStore() {
		return scaFileStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaFileStore_FileStore() {
		return (EAttribute) scaFileStoreEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaFileStore_Children() {
		return (EReference) scaFileStoreEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaFileStore_ImageDesc() {
		return (EAttribute) scaFileStoreEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaFileStore_Directory() {
		return (EAttribute) scaFileStoreEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaFileStore_Name() {
		return (EAttribute) scaFileStoreEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaFileSystem() {
		return scaFileSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaFileSystem_FileSystemURI() {
		return (EAttribute) scaFileSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaLoadableDevice() {
		return scaLoadableDeviceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaPort() {
		return scaPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaPort_Name() {
		return (EAttribute) scaPortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaPort_ProfileObj() {
		return (EReference) scaPortEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaPort_Repid() {
		return (EAttribute) scaPortEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaPort_PortContainer() {
		return (EReference) scaPortEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaProvidesPort() {
		return scaProvidesPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaSimpleProperty() {
		return scaSimplePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaSimpleProperty_Value() {
		return (EAttribute) scaSimplePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaSimpleSequenceProperty() {
		return scaSimpleSequencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaSimpleSequenceProperty_Values() {
		return (EAttribute) scaSimpleSequencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaStructProperty() {
		return scaStructPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaStructProperty_Fields() {
		return (EReference) scaStructPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaStructProperty_Simples() {
		return (EReference) scaStructPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaUsesPort() {
		return scaUsesPortEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaUsesPort_Connections() {
		return (EReference) scaUsesPortEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaConnection() {
		return scaConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaConnection_Data() {
		return (EAttribute) scaConnectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaConnection_Id() {
		return (EAttribute) scaConnectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaConnection_Port() {
		return (EReference) scaConnectionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaWaveform() {
		return scaWaveformEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaWaveform_Components() {
		return (EReference) scaWaveformEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaWaveform_AssemblyController() {
		return (EReference) scaWaveformEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaWaveform_DomMgr() {
		return (EReference) scaWaveformEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveform_Identifier() {
		return (EAttribute) scaWaveformEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveform_Name() {
		return (EAttribute) scaWaveformEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveform_Started() {
		return (EAttribute) scaWaveformEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveform_Profile() {
		return (EAttribute) scaWaveformEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaWaveformFactory() {
		return scaWaveformFactoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaWaveformFactory_DomMgr() {
		return (EReference) scaWaveformFactoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveformFactory_Identifier() {
		return (EAttribute) scaWaveformFactoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveformFactory_Name() {
		return (EAttribute) scaWaveformFactoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScaWaveformFactory_Profile() {
		return (EAttribute) scaWaveformFactoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringToStringMap() {
		return stringToStringMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringToStringMap_Key() {
		return (EAttribute) stringToStringMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringToStringMap_Value() {
		return (EAttribute) stringToStringMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScaStructSequenceProperty() {
		return scaStructSequencePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getScaStructSequenceProperty_Structs() {
		return (EReference) scaStructSequencePropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIStatusProvider() {
		return iStatusProviderEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIStatusProvider_Status() {
		return (EAttribute) iStatusProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEventChannel() {
		return eventChannelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIRefreshable() {
		return iRefreshableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScaEventChannel() {
		return scaEventChannelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getScaEventChannel_Name() {
		return (EAttribute) scaEventChannelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStringToObjectMap() {
		return stringToObjectMapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStringToObjectMap_Key() {
		return (EAttribute) stringToObjectMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStringToObjectMap_Value() {
		return (EReference) stringToObjectMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWaveformsContainer() {
		return waveformsContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWaveformsContainer_SubContainers() {
		return (EReference) waveformsContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWaveformsContainer_Waveforms() {
		return (EReference) waveformsContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getWaveformsContainer_ContainerName() {
		return (EAttribute) waveformsContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getDomainConnectionState() {
		return domainConnectionStateEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getRefreshDepth() {
		return refreshDepthEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getAdminType() {
		return adminTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDomainConnectionException() {
		return domainConnectionExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 8.0
	 * <!-- end-user-doc -->
	 */
	@Override
	public EDataType getDomainConnectionStateObject() {
		return this.domainConnectionStateObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIFileStore() {
		return iFileStoreEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIProgressMonitor() {
		return iProgressMonitorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIScaDataProvider() {
		return iScaDataProviderEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIScaDataProviderService() {
		return iScaDataProviderServiceEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIStatus() {
		return iStatusEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getObject() {
		return objectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getObjectArray() {
		return objectArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getOperationalType() {
		return operationalTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getRefreshDepthObject() {
		return refreshDepthObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getPOA() {
		return poaEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getURI() {
		return uriEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getUsageType() {
		return usageTypeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDataTypeArray() {
		return dataTypeArrayEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getAny() {
		return anyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaFactory getScaFactory() {
		return (ScaFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package. This method is
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
		corbaObjWrapperEClass = createEClass(CORBA_OBJ_WRAPPER);
		createEAttribute(corbaObjWrapperEClass, CORBA_OBJ_WRAPPER__IOR);
		createEAttribute(corbaObjWrapperEClass, CORBA_OBJ_WRAPPER__OBJ);
		createEAttribute(corbaObjWrapperEClass, CORBA_OBJ_WRAPPER__CORBA_OBJ);
		createEReference(corbaObjWrapperEClass, CORBA_OBJ_WRAPPER__FEATURE_DATA);

		dataProviderObjectEClass = createEClass(DATA_PROVIDER_OBJECT);
		createEAttribute(dataProviderObjectEClass, DATA_PROVIDER_OBJECT__DATA_PROVIDERS);
		createEAttribute(dataProviderObjectEClass, DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED);
		createEAttribute(dataProviderObjectEClass, DATA_PROVIDER_OBJECT__ENABLED_DATA_PROVIDERS);

		iDisposableEClass = createEClass(IDISPOSABLE);
		createEAttribute(iDisposableEClass, IDISPOSABLE__DISPOSED);

		profileObjectWrapperEClass = createEClass(PROFILE_OBJECT_WRAPPER);
		createEAttribute(profileObjectWrapperEClass, PROFILE_OBJECT_WRAPPER__PROFILE_URI);
		createEReference(profileObjectWrapperEClass, PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

		propertiesEClass = createEClass(PROPERTIES);
		createEReference(propertiesEClass, PROPERTIES__PROPERTY);

		scaAbstractComponentEClass = createEClass(SCA_ABSTRACT_COMPONENT);
		createEAttribute(scaAbstractComponentEClass, SCA_ABSTRACT_COMPONENT__IDENTIFIER);
		createEAttribute(scaAbstractComponentEClass, SCA_ABSTRACT_COMPONENT__STARTED);
		createEAttribute(scaAbstractComponentEClass, SCA_ABSTRACT_COMPONENT__PROFILE);

		scaPropertyContainerEClass = createEClass(SCA_PROPERTY_CONTAINER);
		createEReference(scaPropertyContainerEClass, SCA_PROPERTY_CONTAINER__PROPERTIES);

		scaPortContainerEClass = createEClass(SCA_PORT_CONTAINER);
		createEReference(scaPortContainerEClass, SCA_PORT_CONTAINER__PORTS);

		scaAbstractPropertyEClass = createEClass(SCA_ABSTRACT_PROPERTY);
		createEReference(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__DEFINITION);
		createEAttribute(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__DESCRIPTION);
		createEAttribute(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__ID);
		createEAttribute(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__MODE);
		createEAttribute(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__NAME);
		createEAttribute(scaAbstractPropertyEClass, SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET);

		scaComponentEClass = createEClass(SCA_COMPONENT);
		createEReference(scaComponentEClass, SCA_COMPONENT__COMPONENT_INSTANTIATION);
		createEReference(scaComponentEClass, SCA_COMPONENT__DEVICES);
		createEAttribute(scaComponentEClass, SCA_COMPONENT__INSTANTIATION_IDENTIFIER);
		createEReference(scaComponentEClass, SCA_COMPONENT__WAVEFORM);
		createEAttribute(scaComponentEClass, SCA_COMPONENT__NAME);

		scaDeviceEClass = createEClass(SCA_DEVICE);
		createEReference(scaDeviceEClass, SCA_DEVICE__CHILD_DEVICES);
		createEAttribute(scaDeviceEClass, SCA_DEVICE__ADMIN_STATE);
		createEAttribute(scaDeviceEClass, SCA_DEVICE__LABEL);
		createEAttribute(scaDeviceEClass, SCA_DEVICE__OPERATIONAL_STATE);
		createEAttribute(scaDeviceEClass, SCA_DEVICE__USAGE_STATE);
		createEReference(scaDeviceEClass, SCA_DEVICE__PARENT_DEVICE);
		createEReference(scaDeviceEClass, SCA_DEVICE__DEV_MGR);

		scaDeviceManagerEClass = createEClass(SCA_DEVICE_MANAGER);
		createEAttribute(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__DEVICES);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__ROOT_DEVICES);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__CHILD_DEVICES);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__ALL_DEVICES);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__FILE_SYSTEM);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__DOM_MGR);
		createEAttribute(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__IDENTIFIER);
		createEAttribute(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__LABEL);
		createEReference(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__SERVICES);
		createEAttribute(scaDeviceManagerEClass, SCA_DEVICE_MANAGER__PROFILE);

		scaServiceEClass = createEClass(SCA_SERVICE);
		createEAttribute(scaServiceEClass, SCA_SERVICE__NAME);
		createEReference(scaServiceEClass, SCA_SERVICE__DEV_MGR);

		scaDeviceManagerFileSystemEClass = createEClass(SCA_DEVICE_MANAGER_FILE_SYSTEM);
		createEReference(scaDeviceManagerFileSystemEClass, SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER);

		scaDocumentRootEClass = createEClass(SCA_DOCUMENT_ROOT);
		createEAttribute(scaDocumentRootEClass, SCA_DOCUMENT_ROOT__MIXED);
		createEReference(scaDocumentRootEClass, SCA_DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(scaDocumentRootEClass, SCA_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(scaDocumentRootEClass, SCA_DOCUMENT_ROOT__DOMAIN_MANAGER_REGISTRY);

		scaDomainManagerEClass = createEClass(SCA_DOMAIN_MANAGER);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__WAVEFORMS);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__DEVICE_MANAGERS);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__FILE_MANAGER);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__AUTO_CONNECT);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__CONNECTED);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__IDENTIFIER);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__NAME);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__ROOT_CONTEXT);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__STATE);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__PROFILE);
		createEReference(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__EVENT_CHANNELS);
		createEAttribute(scaDomainManagerEClass, SCA_DOMAIN_MANAGER__LOCAL_NAME);

		scaDomainManagerFileSystemEClass = createEClass(SCA_DOMAIN_MANAGER_FILE_SYSTEM);
		createEReference(scaDomainManagerFileSystemEClass, SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR);

		scaDomainManagerRegistryEClass = createEClass(SCA_DOMAIN_MANAGER_REGISTRY);
		createEReference(scaDomainManagerRegistryEClass, SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS);

		scaExecutableDeviceEClass = createEClass(SCA_EXECUTABLE_DEVICE);

		scaFileManagerEClass = createEClass(SCA_FILE_MANAGER);

		scaFileStoreEClass = createEClass(SCA_FILE_STORE);
		createEAttribute(scaFileStoreEClass, SCA_FILE_STORE__FILE_STORE);
		createEReference(scaFileStoreEClass, SCA_FILE_STORE__CHILDREN);
		createEAttribute(scaFileStoreEClass, SCA_FILE_STORE__IMAGE_DESC);
		createEAttribute(scaFileStoreEClass, SCA_FILE_STORE__DIRECTORY);
		createEAttribute(scaFileStoreEClass, SCA_FILE_STORE__NAME);

		scaFileSystemEClass = createEClass(SCA_FILE_SYSTEM);
		createEAttribute(scaFileSystemEClass, SCA_FILE_SYSTEM__FILE_SYSTEM_URI);

		scaLoadableDeviceEClass = createEClass(SCA_LOADABLE_DEVICE);

		scaPortEClass = createEClass(SCA_PORT);
		createEAttribute(scaPortEClass, SCA_PORT__NAME);
		createEReference(scaPortEClass, SCA_PORT__PROFILE_OBJ);
		createEAttribute(scaPortEClass, SCA_PORT__REPID);
		createEReference(scaPortEClass, SCA_PORT__PORT_CONTAINER);

		scaProvidesPortEClass = createEClass(SCA_PROVIDES_PORT);

		scaSimplePropertyEClass = createEClass(SCA_SIMPLE_PROPERTY);
		createEAttribute(scaSimplePropertyEClass, SCA_SIMPLE_PROPERTY__VALUE);

		scaSimpleSequencePropertyEClass = createEClass(SCA_SIMPLE_SEQUENCE_PROPERTY);
		createEAttribute(scaSimpleSequencePropertyEClass, SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES);

		scaStructPropertyEClass = createEClass(SCA_STRUCT_PROPERTY);
		createEReference(scaStructPropertyEClass, SCA_STRUCT_PROPERTY__FIELDS);
		createEReference(scaStructPropertyEClass, SCA_STRUCT_PROPERTY__SIMPLES);

		scaUsesPortEClass = createEClass(SCA_USES_PORT);
		createEReference(scaUsesPortEClass, SCA_USES_PORT__CONNECTIONS);

		scaConnectionEClass = createEClass(SCA_CONNECTION);
		createEAttribute(scaConnectionEClass, SCA_CONNECTION__DATA);
		createEAttribute(scaConnectionEClass, SCA_CONNECTION__ID);
		createEReference(scaConnectionEClass, SCA_CONNECTION__PORT);

		scaWaveformEClass = createEClass(SCA_WAVEFORM);
		createEReference(scaWaveformEClass, SCA_WAVEFORM__COMPONENTS);
		createEReference(scaWaveformEClass, SCA_WAVEFORM__ASSEMBLY_CONTROLLER);
		createEReference(scaWaveformEClass, SCA_WAVEFORM__DOM_MGR);
		createEAttribute(scaWaveformEClass, SCA_WAVEFORM__IDENTIFIER);
		createEAttribute(scaWaveformEClass, SCA_WAVEFORM__NAME);
		createEAttribute(scaWaveformEClass, SCA_WAVEFORM__STARTED);
		createEAttribute(scaWaveformEClass, SCA_WAVEFORM__PROFILE);

		scaWaveformFactoryEClass = createEClass(SCA_WAVEFORM_FACTORY);
		createEReference(scaWaveformFactoryEClass, SCA_WAVEFORM_FACTORY__DOM_MGR);
		createEAttribute(scaWaveformFactoryEClass, SCA_WAVEFORM_FACTORY__IDENTIFIER);
		createEAttribute(scaWaveformFactoryEClass, SCA_WAVEFORM_FACTORY__NAME);
		createEAttribute(scaWaveformFactoryEClass, SCA_WAVEFORM_FACTORY__PROFILE);

		stringToStringMapEClass = createEClass(STRING_TO_STRING_MAP);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__KEY);
		createEAttribute(stringToStringMapEClass, STRING_TO_STRING_MAP__VALUE);

		scaStructSequencePropertyEClass = createEClass(SCA_STRUCT_SEQUENCE_PROPERTY);
		createEReference(scaStructSequencePropertyEClass, SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS);

		iStatusProviderEClass = createEClass(ISTATUS_PROVIDER);
		createEAttribute(iStatusProviderEClass, ISTATUS_PROVIDER__STATUS);

		eventChannelEClass = createEClass(EVENT_CHANNEL);

		iRefreshableEClass = createEClass(IREFRESHABLE);

		scaEventChannelEClass = createEClass(SCA_EVENT_CHANNEL);
		createEAttribute(scaEventChannelEClass, SCA_EVENT_CHANNEL__NAME);

		stringToObjectMapEClass = createEClass(STRING_TO_OBJECT_MAP);
		createEAttribute(stringToObjectMapEClass, STRING_TO_OBJECT_MAP__KEY);
		createEReference(stringToObjectMapEClass, STRING_TO_OBJECT_MAP__VALUE);

		waveformsContainerEClass = createEClass(WAVEFORMS_CONTAINER);
		createEReference(waveformsContainerEClass, WAVEFORMS_CONTAINER__SUB_CONTAINERS);
		createEReference(waveformsContainerEClass, WAVEFORMS_CONTAINER__WAVEFORMS);
		createEAttribute(waveformsContainerEClass, WAVEFORMS_CONTAINER__CONTAINER_NAME);

		// Create enums
		domainConnectionStateEEnum = createEEnum(DOMAIN_CONNECTION_STATE);
		refreshDepthEEnum = createEEnum(REFRESH_DEPTH);

		// Create data types
		adminTypeEDataType = createEDataType(ADMIN_TYPE);
		domainConnectionExceptionEDataType = createEDataType(DOMAIN_CONNECTION_EXCEPTION);
		domainConnectionStateObjectEDataType = createEDataType(DOMAIN_CONNECTION_STATE_OBJECT);
		iFileStoreEDataType = createEDataType(IFILE_STORE);
		iProgressMonitorEDataType = createEDataType(IPROGRESS_MONITOR);
		iScaDataProviderEDataType = createEDataType(ISCA_DATA_PROVIDER);
		iScaDataProviderServiceEDataType = createEDataType(ISCA_DATA_PROVIDER_SERVICE);
		iStatusEDataType = createEDataType(ISTATUS);
		objectEDataType = createEDataType(OBJECT);
		objectArrayEDataType = createEDataType(OBJECT_ARRAY);
		operationalTypeEDataType = createEDataType(OPERATIONAL_TYPE);
		refreshDepthObjectEDataType = createEDataType(REFRESH_DEPTH_OBJECT);
		poaEDataType = createEDataType(POA);
		uriEDataType = createEDataType(URI);
		usageTypeEDataType = createEDataType(USAGE_TYPE);
		dataTypeArrayEDataType = createEDataType(DATA_TYPE_ARRAY);
		anyEDataType = createEDataType(ANY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model. This
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		SpdPackage theSpdPackage = (SpdPackage) EPackage.Registry.INSTANCE.getEPackage(SpdPackage.eNS_URI);
		CfPackage theCfPackage = (CfPackage) EPackage.Registry.INSTANCE.getEPackage(CfPackage.eNS_URI);
		PrfPackage thePrfPackage = (PrfPackage) EPackage.Registry.INSTANCE.getEPackage(PrfPackage.eNS_URI);
		SadPackage theSadPackage = (SadPackage) EPackage.Registry.INSTANCE.getEPackage(SadPackage.eNS_URI);
		DcdPackage theDcdPackage = (DcdPackage) EPackage.Registry.INSTANCE.getEPackage(DcdPackage.eNS_URI);
		ScdPackage theScdPackage = (ScdPackage) EPackage.Registry.INSTANCE.getEPackage(ScdPackage.eNS_URI);
		DmdPackage theDmdPackage = (DmdPackage) EPackage.Registry.INSTANCE.getEPackage(DmdPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		ExtendedPackage theExtendedPackage = (ExtendedPackage) EPackage.Registry.INSTANCE.getEPackage(ExtendedPackage.eNS_URI);

		// Create type parameters
		ETypeParameter corbaObjWrapperEClass_T = addETypeParameter(corbaObjWrapperEClass, "T");
		ETypeParameter profileObjectWrapperEClass_O = addETypeParameter(profileObjectWrapperEClass, "O");
		ETypeParameter scaAbstractComponentEClass_R = addETypeParameter(scaAbstractComponentEClass, "R");
		ETypeParameter scaPropertyContainerEClass_P = addETypeParameter(scaPropertyContainerEClass, "P");
		ETypeParameter scaPropertyContainerEClass_E = addETypeParameter(scaPropertyContainerEClass, "E");
		ETypeParameter scaAbstractPropertyEClass_T = addETypeParameter(scaAbstractPropertyEClass, "T");
		ETypeParameter scaDeviceEClass_D = addETypeParameter(scaDeviceEClass, "D");
		ETypeParameter scaFileSystemEClass_F = addETypeParameter(scaFileSystemEClass, "F");
		ETypeParameter scaLoadableDeviceEClass_L = addETypeParameter(scaLoadableDeviceEClass, "L");
		ETypeParameter scaPortEClass_P = addETypeParameter(scaPortEClass, "P");
		ETypeParameter scaPortEClass_P2 = addETypeParameter(scaPortEClass, "P2");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getObject());
		corbaObjWrapperEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(ecorePackage.getEJavaObject());
		profileObjectWrapperEClass_O.getEBounds().add(g1);
		g1 = createEGenericType(theCfPackage.getResource());
		scaAbstractComponentEClass_R.getEBounds().add(g1);
		g1 = createEGenericType(this.getObject());
		scaPropertyContainerEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(ecorePackage.getEJavaObject());
		scaPropertyContainerEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(thePrfPackage.getAbstractProperty());
		scaAbstractPropertyEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(theCfPackage.getDevice());
		scaDeviceEClass_D.getEBounds().add(g1);
		g1 = createEGenericType(theCfPackage.getFileSystem());
		scaFileSystemEClass_F.getEBounds().add(g1);
		g1 = createEGenericType(theCfPackage.getLoadableDevice());
		scaLoadableDeviceEClass_L.getEBounds().add(g1);
		g1 = createEGenericType(theScdPackage.getAbstractPort());
		scaPortEClass_P.getEBounds().add(g1);
		g1 = createEGenericType(theCfPackage.getObject());
		scaPortEClass_P2.getEBounds().add(g1);

		// Add supertypes to classes
		corbaObjWrapperEClass.getESuperTypes().add(this.getDataProviderObject());
		dataProviderObjectEClass.getESuperTypes().add(this.getIStatusProvider());
		dataProviderObjectEClass.getESuperTypes().add(this.getIDisposable());
		dataProviderObjectEClass.getESuperTypes().add(this.getIRefreshable());
		profileObjectWrapperEClass.getESuperTypes().add(this.getIStatusProvider());
		g1 = createEGenericType(this.getScaPropertyContainer());
		EGenericType g2 = createEGenericType(scaAbstractComponentEClass_R);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theSpdPackage.getSoftPkg());
		g1.getETypeArguments().add(g2);
		scaAbstractComponentEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getResourceOperations());
		scaAbstractComponentEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPortContainer());
		scaAbstractComponentEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCorbaObjWrapper());
		g2 = createEGenericType(scaPropertyContainerEClass_P);
		g1.getETypeArguments().add(g2);
		scaPropertyContainerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getProfileObjectWrapper());
		g2 = createEGenericType(scaPropertyContainerEClass_E);
		g1.getETypeArguments().add(g2);
		scaPropertyContainerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getPropertyEmitterOperations());
		scaPropertyContainerEClass.getEGenericSuperTypes().add(g1);
		scaPortContainerEClass.getESuperTypes().add(this.getIRefreshable());
		scaPortContainerEClass.getESuperTypes().add(this.getIStatusProvider());
		scaAbstractPropertyEClass.getESuperTypes().add(this.getIStatusProvider());
		g1 = createEGenericType(this.getScaAbstractComponent());
		g2 = createEGenericType(theCfPackage.getResource());
		g1.getETypeArguments().add(g2);
		scaComponentEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaAbstractComponent());
		g2 = createEGenericType(scaDeviceEClass_D);
		g1.getETypeArguments().add(g2);
		scaDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getDeviceOperations());
		scaDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPropertyContainer());
		g2 = createEGenericType(theCfPackage.getDeviceManager());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theDcdPackage.getDeviceConfiguration());
		g1.getETypeArguments().add(g2);
		scaDeviceManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getDeviceManagerOperations());
		scaDeviceManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPortContainer());
		scaDeviceManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPropertyContainer());
		g2 = createEGenericType(this.getObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theSpdPackage.getSoftPkg());
		g1.getETypeArguments().add(g2);
		scaServiceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPortContainer());
		scaServiceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaFileSystem());
		g2 = createEGenericType(theCfPackage.getFileSystem());
		g1.getETypeArguments().add(g2);
		scaDeviceManagerFileSystemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPropertyContainer());
		g2 = createEGenericType(theCfPackage.getDomainManager());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theDmdPackage.getDomainManagerConfiguration());
		g1.getETypeArguments().add(g2);
		scaDomainManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getDomainManagerOperations());
		scaDomainManagerEClass.getEGenericSuperTypes().add(g1);
		scaDomainManagerFileSystemEClass.getESuperTypes().add(this.getScaFileManager());
		scaDomainManagerRegistryEClass.getESuperTypes().add(this.getIDisposable());
		g1 = createEGenericType(this.getScaLoadableDevice());
		g2 = createEGenericType(theCfPackage.getExecutableDevice());
		g1.getETypeArguments().add(g2);
		scaExecutableDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getExecutableDeviceOperations());
		scaExecutableDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaFileSystem());
		g2 = createEGenericType(theCfPackage.getFileManager());
		g1.getETypeArguments().add(g2);
		scaFileManagerEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getFileManagerOperations());
		scaFileManagerEClass.getEGenericSuperTypes().add(g1);
		scaFileStoreEClass.getESuperTypes().add(this.getIStatusProvider());
		scaFileStoreEClass.getESuperTypes().add(this.getIRefreshable());
		g1 = createEGenericType(this.getCorbaObjWrapper());
		g2 = createEGenericType(scaFileSystemEClass_F);
		g1.getETypeArguments().add(g2);
		scaFileSystemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getFileSystemOperations());
		scaFileSystemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaFileStore());
		scaFileSystemEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType(scaLoadableDeviceEClass_L);
		g1.getETypeArguments().add(g2);
		scaLoadableDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getLoadableDeviceOperations());
		scaLoadableDeviceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCorbaObjWrapper());
		g2 = createEGenericType(scaPortEClass_P2);
		g1.getETypeArguments().add(g2);
		scaPortEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPort());
		g2 = createEGenericType(theScdPackage.getProvides());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theCfPackage.getObject());
		g1.getETypeArguments().add(g2);
		scaProvidesPortEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType(thePrfPackage.getSimple());
		g1.getETypeArguments().add(g2);
		scaSimplePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType(thePrfPackage.getSimpleSequence());
		g1.getETypeArguments().add(g2);
		scaSimpleSequencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType(thePrfPackage.getStruct());
		g1.getETypeArguments().add(g2);
		scaStructPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getPropertySetOperations());
		scaStructPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPort());
		g2 = createEGenericType(theScdPackage.getUses());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theCfPackage.getPort());
		g1.getETypeArguments().add(g2);
		scaUsesPortEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getPortOperations());
		scaUsesPortEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPropertyContainer());
		g2 = createEGenericType(theCfPackage.getApplication());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(theSadPackage.getSoftwareAssembly());
		g1.getETypeArguments().add(g2);
		scaWaveformEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getApplicationOperations());
		scaWaveformEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaPortContainer());
		scaWaveformEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCorbaObjWrapper());
		g2 = createEGenericType(theCfPackage.getApplicationFactory());
		g1.getETypeArguments().add(g2);
		scaWaveformFactoryEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getApplicationFactoryOperations());
		scaWaveformFactoryEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getProfileObjectWrapper());
		g2 = createEGenericType(theSadPackage.getSoftwareAssembly());
		g1.getETypeArguments().add(g2);
		scaWaveformFactoryEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType(thePrfPackage.getStructSequence());
		g1.getETypeArguments().add(g2);
		scaStructSequencePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theCfPackage.getPropertySetOperations());
		scaStructSequencePropertyEClass.getEGenericSuperTypes().add(g1);
		eventChannelEClass.getESuperTypes().add(theCfPackage.getObject());
		g1 = createEGenericType(this.getCorbaObjWrapper());
		g2 = createEGenericType(this.getEventChannel());
		g1.getETypeArguments().add(g2);
		scaEventChannelEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes and features; add operations and parameters
		initEClass(corbaObjWrapperEClass, CorbaObjWrapper.class, "CorbaObjWrapper", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCorbaObjWrapper_Ior(), ecorePackage.getEString(), "ior", null, 0, 1, CorbaObjWrapper.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(corbaObjWrapperEClass_T);
		initEAttribute(getCorbaObjWrapper_Obj(), g1, "obj", null, 0, 1, CorbaObjWrapper.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID,
			IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCorbaObjWrapper_CorbaObj(), this.getObject(), "corbaObj", null, 0, 1, CorbaObjWrapper.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCorbaObjWrapper_FeatureData(), this.getStringToObjectMap(), null, "featureData", null, 0, -1, CorbaObjWrapper.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(corbaObjWrapperEClass, theXMLTypePackage.getBoolean(), "exists", 0, 1, IS_UNIQUE, IS_ORDERED);

		EOperation op = addEOperation(corbaObjWrapperEClass, null, "fetchAttributes", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(corbaObjWrapperEClass, null, "fetchNarrowedObject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(corbaObjWrapperEClass_T);
		initEOperation(op, g1);

		op = addEOperation(corbaObjWrapperEClass, ecorePackage.getEBoolean(), "_is_a", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "repId", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dataProviderObjectEClass, DataProviderObject.class, "DataProviderObject", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataProviderObject_DataProviders(), this.getIScaDataProvider(), "dataProviders", null, 0, -1, DataProviderObject.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataProviderObject_DataProvidersEnabled(), ecorePackage.getEBoolean(), "dataProvidersEnabled", "true", 0, 1, DataProviderObject.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDataProviderObject_EnabledDataProviders(), ecorePackage.getEString(), "enabledDataProviders", null, 0, -1, DataProviderObject.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(dataProviderObjectEClass, null, "attachDataProviders", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(dataProviderObjectEClass, null, "detachDataProviders", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iDisposableEClass, IDisposable.class, "IDisposable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIDisposable_Disposed(), ecorePackage.getEBoolean(), "disposed", null, 0, 1, IDisposable.class, IS_TRANSIENT, !IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(iDisposableEClass, null, "dispose", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(profileObjectWrapperEClass, ProfileObjectWrapper.class, "ProfileObjectWrapper", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProfileObjectWrapper_ProfileURI(), theSpdPackage.getURI(), "profileURI", null, 0, 1, ProfileObjectWrapper.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(profileObjectWrapperEClass_O);
		initEReference(getProfileObjectWrapper_ProfileObj(), g1, null, "profileObj", null, 0, 1, ProfileObjectWrapper.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(profileObjectWrapperEClass, null, "fetchProfileObject", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(profileObjectWrapperEClass_O);
		initEOperation(op, g1);

		op = addEOperation(profileObjectWrapperEClass, theSpdPackage.getURI(), "fetchProfileURI", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(propertiesEClass, Properties.class, "Properties", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProperties_Property(), this.getStringToStringMap(), null, "property", null, 0, -1, Properties.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scaAbstractComponentEClass, ScaAbstractComponent.class, "ScaAbstractComponent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaAbstractComponent_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ScaAbstractComponent.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractComponent_Started(), theXMLTypePackage.getBooleanObject(), "started", null, 0, 1, ScaAbstractComponent.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractComponent_Profile(), ecorePackage.getEString(), "profile", null, 0, 1, ScaAbstractComponent.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaAbstractComponentEClass, ecorePackage.getEString(), "fetchIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractComponentEClass, ecorePackage.getEBooleanObject(), "fetchStarted", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractComponentEClass, ecorePackage.getEString(), "fetchProfile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaPropertyContainerEClass, ScaPropertyContainer.class, "ScaPropertyContainer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaPropertyContainer_Properties(), g1, null, "properties", null, 0, -1, ScaPropertyContainer.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaPropertyContainerEClass, null, "fetchProperties", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaPropertyContainerEClass, null, "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(scaPortContainerEClass, ScaPortContainer.class, "ScaPortContainer", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getScaPort());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaPortContainer_Ports(), g1, this.getScaPort_PortContainer(), "ports", null, 0, -1, ScaPortContainer.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaPortContainerEClass, null, "getScaPort", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaPort());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaPortContainerEClass, null, "fetchPorts", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaPort());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(scaAbstractPropertyEClass, ScaAbstractProperty.class, "ScaAbstractProperty", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(scaAbstractPropertyEClass_T);
		initEReference(getScaAbstractProperty_Definition(), g1, null, "definition", null, 0, 1, ScaAbstractProperty.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractProperty_Description(), ecorePackage.getEString(), "description", null, 0, 1, ScaAbstractProperty.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractProperty_Id(), ecorePackage.getEString(), "id", null, 0, 1, ScaAbstractProperty.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractProperty_Mode(), thePrfPackage.getAccessType(), "mode", null, 0, 1, ScaAbstractProperty.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractProperty_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaAbstractProperty.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaAbstractProperty_IgnoreRemoteSet(), ecorePackage.getEBoolean(), "ignoreRemoteSet", null, 0, 1, ScaAbstractProperty.class,
			IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		addEOperation(scaAbstractPropertyEClass, this.getAny(), "toAny", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractPropertyEClass, null, "fromAny", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAny(), "any", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractPropertyEClass, null, "setRemoteValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAny(), "any", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getPartialConfiguration());
		addEException(op, theCfPackage.getInvalidConfiguration());

		addEOperation(scaAbstractPropertyEClass, theCfPackage.getDataType(), "getProperty", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaAbstractPropertyEClass, ecorePackage.getEBoolean(), "isDefaultValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaAbstractPropertyEClass, null, "restoreDefaultValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractPropertyEClass, ecorePackage.getEBoolean(), "valueEquals", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, thePrfPackage.getAny(), "any", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaAbstractPropertyEClass, null, "createPropertyRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(thePrfPackage.getAbstractPropertyRef());
		g2 = createEGenericType(scaAbstractPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaAbstractPropertyEClass, null, "setValueFromRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(thePrfPackage.getAbstractPropertyRef());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "refValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaComponentEClass, ScaComponent.class, "ScaComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaComponent_ComponentInstantiation(), theSadPackage.getSadComponentInstantiation(), null, "componentInstantiation", null, 1, 1,
			ScaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaComponent_Devices(), g1, null, "devices", null, 0, -1, ScaComponent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaComponent_InstantiationIdentifier(), ecorePackage.getEString(), "instantiationIdentifier", null, 0, 1, ScaComponent.class,
			IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaComponent_Waveform(), this.getScaWaveform(), this.getScaWaveform_Components(), "waveform", null, 0, 1, ScaComponent.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaComponent_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaComponentEClass, null, "fetchDevices", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(scaDeviceEClass, ScaDevice.class, "ScaDevice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaDevice_ChildDevices(), g1, this.getScaDevice_ParentDevice(), "childDevices", null, 0, -1, ScaDevice.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDevice_AdminState(), this.getAdminType(), "adminState", null, 0, 1, ScaDevice.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDevice_Label(), theXMLTypePackage.getString(), "label", null, 0, 1, ScaDevice.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDevice_OperationalState(), this.getOperationalType(), "operationalState", null, 0, 1, ScaDevice.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDevice_UsageState(), this.getUsageType(), "usageState", null, 0, 1, ScaDevice.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaDevice_ParentDevice(), g1, this.getScaDevice_ChildDevices(), "parentDevice", null, 0, 1, ScaDevice.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDevice_DevMgr(), this.getScaDeviceManager(), null, "devMgr", null, 0, 1, ScaDevice.class, IS_TRANSIENT, IS_VOLATILE,
			!IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaDeviceEClass, null, "fetchAggregateDevices", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaDeviceEClass, null, "fetchAggregateDevices", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaDeviceEClass, this.getAdminType(), "fetchAdminState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceEClass, ecorePackage.getEString(), "fetchLabel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceEClass, this.getOperationalType(), "fetchOperationalState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceEClass, this.getUsageType(), "fetchUsageState", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaDeviceManagerEClass, ScaDeviceManager.class, "ScaDeviceManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaDeviceManager_Devices(), ecorePackage.getEFeatureMapEntry(), "devices", null, 0, -1, ScaDeviceManager.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaDeviceManager_RootDevices(), g1, null, "rootDevices", null, 0, -1, ScaDeviceManager.class, IS_TRANSIENT, IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaDeviceManager_ChildDevices(), g1, null, "childDevices", null, 0, -1, ScaDeviceManager.class, IS_TRANSIENT, IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaDeviceManager_AllDevices(), g1, null, "allDevices", null, 0, -1, ScaDeviceManager.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
			IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaDeviceManager_FileSystem(), this.getScaDeviceManagerFileSystem(), this.getScaDeviceManagerFileSystem_DeviceManager(), "fileSystem",
			null, 0, 1, ScaDeviceManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getScaDeviceManager_DomMgr(), this.getScaDomainManager(), this.getScaDomainManager_DeviceManagers(), "domMgr", null, 0, 1,
			ScaDeviceManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getScaDeviceManager_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ScaDeviceManager.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDeviceManager_Label(), ecorePackage.getEString(), "label", null, 0, 1, ScaDeviceManager.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaDeviceManager_Services(), this.getScaService(), this.getScaService_DevMgr(), "services", null, 0, -1, ScaDeviceManager.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getScaDeviceManager_Profile(), ecorePackage.getEString(), "profile", null, 0, 1, ScaDeviceManager.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, null, "getDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "deviceId", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaDeviceManagerEClass, null, "fetchDevices", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaDeviceManagerEClass, this.getScaDeviceManagerFileSystem(), "fetchFileSystem", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, ecorePackage.getEString(), "fetchIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, ecorePackage.getEString(), "fetchLabel", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, this.getScaService(), "fetchServices", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, this.getScaService(), "registerScaService", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getObject(), "registeringService", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getInvalidObjectReference());

		op = addEOperation(scaDeviceManagerEClass, this.getScaService(), "getScaService", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDeviceManagerEClass, ecorePackage.getEString(), "fetchProfile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaServiceEClass, ScaService.class, "ScaService", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaService_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaService.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaService_DevMgr(), this.getScaDeviceManager(), this.getScaDeviceManager_Services(), "devMgr", null, 0, 1, ScaService.class,
			IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = addEOperation(scaServiceEClass, theXMLTypePackage.getBoolean(), "isInstance", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theScdPackage.getInterface(), "intf", 1, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaDeviceManagerFileSystemEClass, ScaDeviceManagerFileSystem.class, "ScaDeviceManagerFileSystem", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaDeviceManagerFileSystem_DeviceManager(), this.getScaDeviceManager(), this.getScaDeviceManager_FileSystem(), "deviceManager", null,
			0, 1, ScaDeviceManagerFileSystem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(scaDocumentRootEClass, ScaDocumentRoot.class, "ScaDocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDocumentRoot_DomainManagerRegistry(), this.getScaDomainManagerRegistry(), null, "domainManagerRegistry", null, 0, -2, null,
			IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(scaDomainManagerEClass, ScaDomainManager.class, "ScaDomainManager", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaDomainManager_WaveformFactories(), this.getScaWaveformFactory(), this.getScaWaveformFactory_DomMgr(), "waveformFactories", null, 0,
			-1, ScaDomainManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getScaDomainManager_Waveforms(), this.getScaWaveform(), this.getScaWaveform_DomMgr(), "waveforms", null, 0, -1, ScaDomainManager.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDomainManager_DeviceManagers(), this.getScaDeviceManager(), this.getScaDeviceManager_DomMgr(), "deviceManagers", null, 0, -1,
			ScaDomainManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getScaDomainManager_FileManager(), this.getScaDomainManagerFileSystem(), this.getScaDomainManagerFileSystem_DomMgr(), "fileManager",
			null, 0, 1, ScaDomainManager.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);
		initEReference(getScaDomainManager_ConnectionPropertiesContainer(), this.getProperties(), null, "connectionPropertiesContainer", null, 1, 1,
			ScaDomainManager.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEReference(getScaDomainManager_ConnectionProperties(), this.getStringToStringMap(), null, "connectionProperties", null, 0, -1,
			ScaDomainManager.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getScaDomainManager_AutoConnect(), ecorePackage.getEBoolean(), "autoConnect", null, 0, 1, ScaDomainManager.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_Connected(), ecorePackage.getEBoolean(), "connected", null, 1, 1, ScaDomainManager.class, IS_TRANSIENT, IS_VOLATILE,
			!IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ScaDomainManager.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_Name(), ecorePackage.getEString(), "name", null, 1, 1, ScaDomainManager.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_RootContext(), theCfPackage.getNamingContextExt(), "rootContext", null, 1, 1, ScaDomainManager.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_State(), this.getDomainConnectionState(), "state", null, 1, 1, ScaDomainManager.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_Profile(), ecorePackage.getEString(), "profile", null, 0, 1, ScaDomainManager.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaDomainManager_EventChannels(), this.getScaEventChannel(), null, "eventChannels", null, 0, -1, ScaDomainManager.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaDomainManager_LocalName(), ecorePackage.getEString(), "localName", null, 0, 1, ScaDomainManager.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, null, "getDevice", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "deviceId", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaDevice());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(scaDomainManagerEClass, null, "connect", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "refreshDepth", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getDomainConnectionException());

		addEOperation(scaDomainManagerEClass, null, "disconnect", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaDeviceManager(), "fetchDeviceManagers", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaWaveformFactory(), "fetchWaveformFactories", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaWaveform(), "fetchWaveforms", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaDomainManagerFileSystem(), "fetchFileManager", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, ecorePackage.getEString(), "fetchIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaWaveformFactory(), "installScaWaveformFactory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "profilePath", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getInvalidProfile());
		addEException(op, theCfPackage.getInvalidFileName());
		addEException(op, theCfPackage.getApplicationInstallationError());
		addEException(op, theCfPackage.getApplicationAlreadyInstalled());

		op = addEOperation(scaDomainManagerEClass, null, "uninstallScaWaveformFactory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getScaWaveformFactory(), "factory", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getApplicationUninstallationError());
		addEException(op, theCfPackage.getInvalidDomMgrIdentifier());

		op = addEOperation(scaDomainManagerEClass, ecorePackage.getEString(), "fetchProfile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerEClass, this.getScaEventChannel(), "fetchEventChannels", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaDomainManagerEClass, theXMLTypePackage.getString(), "getLabel", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaDomainManagerFileSystemEClass, ScaDomainManagerFileSystem.class, "ScaDomainManagerFileSystem", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaDomainManagerFileSystem_DomMgr(), this.getScaDomainManager(), this.getScaDomainManager_FileManager(), "domMgr", null, 0, 1,
			ScaDomainManagerFileSystem.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
			!IS_DERIVED, IS_ORDERED);

		initEClass(scaDomainManagerRegistryEClass, ScaDomainManagerRegistry.class, "ScaDomainManagerRegistry", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaDomainManagerRegistry_Domains(), this.getScaDomainManager(), null, "domains", null, 0, -1, ScaDomainManagerRegistry.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaDomainManagerRegistryEClass, this.getScaDomainManager(), "findDomain", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "domainName", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaDomainManagerRegistryEClass, this.getScaDomainManager(), "createDomain", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "localName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "autoConnect", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "connectionProperties", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaExecutableDeviceEClass, ScaExecutableDevice.class, "ScaExecutableDevice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaFileManagerEClass, ScaFileManager.class, "ScaFileManager", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaFileStoreEClass, ScaFileStore.class, "ScaFileStore", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaFileStore_FileStore(), this.getIFileStore(), "fileStore", null, 0, 1, ScaFileStore.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaFileStore_Children(), this.getScaFileStore(), null, "children", null, 0, -1, ScaFileStore.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaFileStore_ImageDesc(), ecorePackage.getEJavaObject(), "imageDesc", null, 0, 1, ScaFileStore.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaFileStore_Directory(), ecorePackage.getEBoolean(), "directory", null, 0, 1, ScaFileStore.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaFileStore_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaFileStore.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaFileStoreEClass, this.getScaFileStore(), "fetchChildren", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaFileSystemEClass, ScaFileSystem.class, "ScaFileSystem", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaFileSystem_FileSystemURI(), this.getURI(), "fileSystemURI", "", 1, 1, ScaFileSystem.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaFileSystemEClass, theSpdPackage.getURI(), "createURI", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaLoadableDeviceEClass, ScaLoadableDevice.class, "ScaLoadableDevice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaPortEClass, ScaPort.class, "ScaPort", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaPort_Name(), ecorePackage.getEString(), "name", null, 1, 1, ScaPort.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		g1 = createEGenericType(scaPortEClass_P);
		initEReference(getScaPort_ProfileObj(), g1, null, "profileObj", null, 0, 1, ScaPort.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
			IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaPort_Repid(), ecorePackage.getEString(), "repid", null, 1, 1, ScaPort.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaPort_PortContainer(), this.getScaPortContainer(), this.getScaPortContainer_Ports(), "portContainer", null, 1, 1, ScaPort.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scaProvidesPortEClass, ScaProvidesPort.class, "ScaProvidesPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaSimplePropertyEClass, ScaSimpleProperty.class, "ScaSimpleProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaSimpleProperty_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, ScaSimpleProperty.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(scaSimplePropertyEClass, thePrfPackage.getSimpleRef(), "createPropertyRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaSimplePropertyEClass, null, "setValueFromRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, thePrfPackage.getSimpleRef(), "refValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaSimpleSequencePropertyEClass, ScaSimpleSequenceProperty.class, "ScaSimpleSequenceProperty", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaSimpleSequenceProperty_Values(), ecorePackage.getEJavaObject(), "values", null, 0, -1, ScaSimpleSequenceProperty.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaSimpleSequencePropertyEClass, null, "setValue", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getObjectArray(), "newValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaSimpleSequencePropertyEClass, this.getObjectArray(), "getValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaSimpleSequencePropertyEClass, thePrfPackage.getSimpleSequenceRef(), "createPropertyRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaSimpleSequencePropertyEClass, null, "setValueFromRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, thePrfPackage.getSimpleSequenceRef(), "refValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaStructPropertyEClass, ScaStructProperty.class, "ScaStructProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEReference(getScaStructProperty_Fields(), g1, null, "fields", null, 0, -1, ScaStructProperty.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getScaStructProperty_Simples(), this.getScaSimpleProperty(), null, "simples", null, 0, -1, ScaStructProperty.class, IS_TRANSIENT,
			IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaStructPropertyEClass, this.getScaSimpleProperty(), "getSimple", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaStructPropertyEClass, null, "getField", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "id", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(this.getScaAbstractProperty());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		addEOperation(scaStructPropertyEClass, thePrfPackage.getStructRef(), "createPropertyRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaStructPropertyEClass, null, "setValueFromRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, thePrfPackage.getStructRef(), "refValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaStructPropertyEClass, thePrfPackage.getStructValue(), "createStructValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaUsesPortEClass, ScaUsesPort.class, "ScaUsesPort", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaUsesPort_Connections(), this.getScaConnection(), this.getScaConnection_Port(), "connections", null, 0, -1, ScaUsesPort.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaUsesPortEClass, this.getScaConnection(), "fetchConnections", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaUsesPortEClass, null, "disconnectPort", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getScaConnection(), "connection", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getInvalidPort());

		initEClass(scaConnectionEClass, ScaConnection.class, "ScaConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaConnection_Data(), theExtendedPackage.getUsesConnection(), "data", null, 0, 1, ScaConnection.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaConnection_Id(), ecorePackage.getEString(), "id", null, 0, 1, ScaConnection.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaConnection_Port(), this.getScaUsesPort(), this.getScaUsesPort_Connections(), "port", null, 1, 1, ScaConnection.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scaWaveformEClass, ScaWaveform.class, "ScaWaveform", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaWaveform_Components(), this.getScaComponent(), this.getScaComponent_Waveform(), "components", null, 0, -1, ScaWaveform.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getScaWaveform_Components().getEKeys().add(this.getScaAbstractComponent_Identifier());
		initEReference(getScaWaveform_AssemblyController(), this.getScaComponent(), null, "assemblyController", null, 0, 1, ScaWaveform.class, IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getScaWaveform_DomMgr(), this.getScaDomainManager(), this.getScaDomainManager_Waveforms(), "domMgr", null, 0, 1, ScaWaveform.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveform_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ScaWaveform.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveform_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaWaveform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveform_Started(), theXMLTypePackage.getBooleanObject(), "started", null, 0, 1, ScaWaveform.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveform_Profile(), ecorePackage.getEString(), "profile", null, 0, 1, ScaWaveform.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, this.getScaComponent(), "fetchComponents", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, this.getScaComponent(), "fetchComponents", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getRefreshDepth(), "depth", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, ecorePackage.getEString(), "fetchIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, ecorePackage.getEString(), "fetchName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, ecorePackage.getEBooleanObject(), "fetchStarted", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, this.getScaComponent(), "findComponent", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "instantiationId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, this.getScaComponent(), "getScaComponent", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "instantiationId", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformEClass, ecorePackage.getEString(), "fetchProfile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaWaveformEClass, this.getScaComponent(), "getComponentsCopy", 0, -1, IS_UNIQUE, IS_ORDERED);

		initEClass(scaWaveformFactoryEClass, ScaWaveformFactory.class, "ScaWaveformFactory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaWaveformFactory_DomMgr(), this.getScaDomainManager(), this.getScaDomainManager_WaveformFactories(), "domMgr", null, 1, 1,
			ScaWaveformFactory.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
			IS_ORDERED);
		initEAttribute(getScaWaveformFactory_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ScaWaveformFactory.class, !IS_TRANSIENT,
			!IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveformFactory_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaWaveformFactory.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getScaWaveformFactory_Profile(), ecorePackage.getEString(), "profile", null, 0, 1, ScaWaveformFactory.class, IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(scaWaveformFactoryEClass, this.getScaWaveform(), "createWaveform", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCfPackage.getDataTypeArray(), "initConfiguration", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCfPackage.getDeviceAssignmentTypeArray(), "deviceAssignments", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCfPackage.getCreateApplicationError());
		addEException(op, theCfPackage.getCreateApplicationRequestError());
		addEException(op, theCfPackage.getInvalidInitConfiguration());
		addEException(op, theCfPackage.getCreateApplicationInsufficientCapacityError());

		op = addEOperation(scaWaveformFactoryEClass, ecorePackage.getEString(), "fetchIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformFactoryEClass, ecorePackage.getEString(), "fetchName", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaWaveformFactoryEClass, ecorePackage.getEString(), "fetchProfile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(stringToStringMapEClass, Map.Entry.class, "StringToStringMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToStringMap_Key(), ecorePackage.getEString(), "key", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToStringMap_Value(), ecorePackage.getEString(), "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scaStructSequencePropertyEClass, ScaStructSequenceProperty.class, "ScaStructSequenceProperty", !IS_ABSTRACT, !IS_INTERFACE,
			IS_GENERATED_INSTANCE_CLASS);
		initEReference(getScaStructSequenceProperty_Structs(), this.getScaStructProperty(), null, "structs", null, 0, -1, ScaStructSequenceProperty.class,
			IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(scaStructSequencePropertyEClass, this.getScaStructProperty(), "createScaStructProperty", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(scaStructSequencePropertyEClass, thePrfPackage.getStructSequenceRef(), "createPropertyRef", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(scaStructSequencePropertyEClass, null, "setValueFromRef", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, thePrfPackage.getStructSequenceRef(), "refValue", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(iStatusProviderEClass, IStatusProvider.class, "IStatusProvider", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIStatusProvider_Status(), this.getIStatus(), "status", null, 0, 1, IStatusProvider.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		op = addEOperation(iStatusProviderEClass, null, "setStatus", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEStructuralFeature(), "feature", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIStatus(), "status", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(iStatusProviderEClass, null, "clearAllStatus", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(iStatusProviderEClass, this.getIStatus(), "getStatus", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEStructuralFeature(), "feature", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(eventChannelEClass, EventChannel.class, "EventChannel", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iRefreshableEClass, IRefreshable.class, "IRefreshable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(scaEventChannelEClass, ScaEventChannel.class, "ScaEventChannel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScaEventChannel_Name(), ecorePackage.getEString(), "name", null, 0, 1, ScaEventChannel.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToObjectMapEClass, Map.Entry.class, "StringToObjectMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToObjectMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
			!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStringToObjectMap_Value(), theEcorePackage.getEObject(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE,
			IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(waveformsContainerEClass, WaveformsContainer.class, "WaveformsContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWaveformsContainer_SubContainers(), this.getWaveformsContainer(), null, "subContainers", null, 0, -1, WaveformsContainer.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWaveformsContainer_Waveforms(), theSadPackage.getSoftwareAssembly(), null, "waveforms", null, 0, -1, WaveformsContainer.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWaveformsContainer_ContainerName(), theXMLTypePackage.getString(), "containerName", null, 0, 1, WaveformsContainer.class,
			!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(domainConnectionStateEEnum, DomainConnectionState.class, "DomainConnectionState");
		addEEnumLiteral(domainConnectionStateEEnum, DomainConnectionState.DISCONNECTED);
		addEEnumLiteral(domainConnectionStateEEnum, DomainConnectionState.CONNECTING);
		addEEnumLiteral(domainConnectionStateEEnum, DomainConnectionState.CONNECTED);
		addEEnumLiteral(domainConnectionStateEEnum, DomainConnectionState.DISCONNECTING);
		addEEnumLiteral(domainConnectionStateEEnum, DomainConnectionState.FAILED);

		initEEnum(refreshDepthEEnum, RefreshDepth.class, "RefreshDepth");
		addEEnumLiteral(refreshDepthEEnum, RefreshDepth.NONE);
		addEEnumLiteral(refreshDepthEEnum, RefreshDepth.SELF);
		addEEnumLiteral(refreshDepthEEnum, RefreshDepth.CHILDREN);
		addEEnumLiteral(refreshDepthEEnum, RefreshDepth.FULL);

		// Initialize data types
		initEDataType(adminTypeEDataType, AdminType.class, "AdminType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(domainConnectionExceptionEDataType, DomainConnectionException.class, "DomainConnectionException", IS_SERIALIZABLE,
			!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(domainConnectionStateObjectEDataType, DomainConnectionState.class, "DomainConnectionStateObject", IS_SERIALIZABLE,
			IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iFileStoreEDataType, IFileStore.class, "IFileStore", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iProgressMonitorEDataType, IProgressMonitor.class, "IProgressMonitor", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iScaDataProviderEDataType, IScaDataProvider.class, "IScaDataProvider", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iScaDataProviderServiceEDataType, IScaDataProviderService.class, "IScaDataProviderService", !IS_SERIALIZABLE,
			!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iStatusEDataType, IStatus.class, "IStatus", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectEDataType, org.omg.CORBA.Object.class, "Object", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(objectArrayEDataType, Object[].class, "ObjectArray", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(operationalTypeEDataType, OperationalType.class, "OperationalType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(refreshDepthObjectEDataType, RefreshDepth.class, "RefreshDepthObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
		initEDataType(poaEDataType, org.omg.PortableServer.POA.class, "POA", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(uriEDataType, java.net.URI.class, "URI", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(usageTypeEDataType, UsageType.class, "UsageType", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(dataTypeArrayEDataType, DataType[].class, "DataTypeArray", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(anyEDataType, Any.class, "Any", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(adminTypeEDataType, source, new String[] { "name", "AdminType", "enumeration", "LOCKED SHUTTING%20DOWN UNLOCKED" });
		addAnnotation(corbaObjWrapperEClass, source, new String[] { "name", "CorbaObjWrapper", "kind", "empty" });
		addAnnotation(getCorbaObjWrapper_Ior(), source, new String[] { "kind", "attribute", "name", "ior" });
		addAnnotation(getCorbaObjWrapper_Obj(), source, new String[] { "kind", "attribute", "name", "obj" });
		addAnnotation(dataProviderObjectEClass, source, new String[] { "name", "DataProviderObject", "kind", "empty" });
		addAnnotation(getDataProviderObject_DataProviders(), source, new String[] { "kind", "attribute", "name", "dataProviders" });
		addAnnotation(domainConnectionStateEEnum, source, new String[] { "name", "DomainConnectionState" });
		addAnnotation(domainConnectionStateObjectEDataType, source,
			new String[] { "name", "DomainConnectionState:Object", "baseType", "DomainConnectionState" });
		addAnnotation(iDisposableEClass, source, new String[] { "name", "IDisposable", "kind", "empty" });
		addAnnotation(getIDisposable_Disposed(), source, new String[] { "kind", "attribute", "name", "disposed" });
		addAnnotation(iFileStoreEDataType, source, new String[] { "name", "IFileStore" });
		addAnnotation(iProgressMonitorEDataType, source, new String[] { "name", "IProgressMonitor" });
		addAnnotation(iScaDataProviderEDataType, source, new String[] { "name", "dataProvider" });
		addAnnotation(iScaDataProviderServiceEDataType, source, new String[] { "name", "dataProviderService" });
		addAnnotation(iStatusEDataType, source, new String[] { "name", "IStatus" });
		addAnnotation(objectEDataType, source, new String[] { "name", "Object" });
		addAnnotation(objectArrayEDataType, source, new String[] { "name", "ObjectArray" });
		addAnnotation(operationalTypeEDataType, source, new String[] { "name", "OperationalType", "enumeration", "ENABLED DISABLED" });
		addAnnotation(profileObjectWrapperEClass, source, new String[] { "name", "ProfileObjectWrapper", "kind", "empty" });
		addAnnotation(getProfileObjectWrapper_ProfileURI(), source, new String[] { "kind", "attribute", "name", "profile" });
		addAnnotation(getProfileObjectWrapper_ProfileObj(), source, new String[] { "kind", "attribute", "name", "profileObj" });
		addAnnotation(propertiesEClass, source, new String[] { "name", "Properties", "kind", "elementOnly" });
		addAnnotation(getProperties_Property(), source, new String[] { "kind", "element", "name", "property" });
		addAnnotation(refreshDepthEEnum, source, new String[] { "name", "RefreshDepth" });
		addAnnotation(refreshDepthObjectEDataType, source, new String[] { "name", "RefreshDepth:Object", "baseType", "RefreshDepth" });
		addAnnotation(scaAbstractComponentEClass, source, new String[] { "name", "ScaAbstractComponent", "kind", "elementOnly" });
		addAnnotation(getScaAbstractComponent_Identifier(), source, new String[] { "kind", "attribute", "name", "identifier" });
		addAnnotation(scaPropertyContainerEClass, source, new String[] { "name", "ScaPropertyContainer", "kind", "elementOnly" });
		addAnnotation(getScaPropertyContainer_Properties(), source, new String[] { "kind", "element", "name", "properties" });
		addAnnotation(scaAbstractPropertyEClass, source, new String[] { "name", "ScaProperty", "kind", "empty" });
		addAnnotation(getScaAbstractProperty_Definition(), source, new String[] { "kind", "attribute", "name", "definition" });
		addAnnotation(getScaAbstractProperty_Description(), source, new String[] { "kind", "attribute", "name", "description" });
		addAnnotation(getScaAbstractProperty_Id(), source, new String[] { "kind", "attribute", "name", "id" });
		addAnnotation(getScaAbstractProperty_Mode(), source, new String[] { "kind", "attribute", "name", "mode" });
		addAnnotation(getScaAbstractProperty_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(scaComponentEClass, source, new String[] { "name", "ScaComponent", "kind", "elementOnly" });
		addAnnotation(getScaComponent_ComponentInstantiation(), source, new String[] { "kind", "attribute", "name", "componentInstantiation" });
		addAnnotation(getScaComponent_Devices(), source, new String[] { "kind", "attribute", "name", "devices" });
		addAnnotation(getScaComponent_InstantiationIdentifier(), source, new String[] { "kind", "attribute", "name", "instantiationIdentifier" });
		addAnnotation(getScaComponent_Waveform(), source, new String[] { "kind", "attribute", "name", "waveform" });
		addAnnotation(scaDeviceEClass, source, new String[] { "name", "ScaDevice", "kind", "elementOnly" });
		addAnnotation(getScaDevice_ChildDevices(), source, new String[] { "kind", "element", "name", "childDevices" });
		addAnnotation(getScaDevice_AdminState(), source, new String[] { "kind", "attribute", "name", "adminState" });
		addAnnotation(getScaDevice_Label(), source, new String[] { "kind", "attribute", "name", "label" });
		addAnnotation(getScaDevice_OperationalState(), source, new String[] { "kind", "attribute", "name", "operationalState" });
		addAnnotation(getScaDevice_UsageState(), source, new String[] { "kind", "attribute", "name", "usageState" });
		addAnnotation(scaDeviceManagerEClass, source, new String[] { "name", "ScaDeviceManager", "kind", "elementOnly" });
		addAnnotation(getScaDeviceManager_Devices(), source, new String[] { "kind", "group", "name", "devices:1" });
		addAnnotation(getScaDeviceManager_RootDevices(), source, new String[] { "group", "#devices:1" });
		addAnnotation(getScaDeviceManager_ChildDevices(), source, new String[] { "group", "#devices:1" });
		addAnnotation(getScaDeviceManager_FileSystem(), source, new String[] { "kind", "element", "name", "fileSystem" });
		addAnnotation(getScaDeviceManager_DomMgr(), source, new String[] { "kind", "attribute", "name", "domMgr" });
		addAnnotation(getScaDeviceManager_Identifier(), source, new String[] { "kind", "attribute", "name", "identifier" });
		addAnnotation(getScaDeviceManager_Label(), source, new String[] { "kind", "attribute", "name", "label" });
		addAnnotation(scaDeviceManagerFileSystemEClass, source, new String[] { "name", "ScaDeviceManagerFileSystem", "kind", "empty" });
		addAnnotation(scaDocumentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getScaDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getScaDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name", "xmlns:prefix" });
		addAnnotation(getScaDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name", "xsi:schemaLocation" });
		addAnnotation(getScaDocumentRoot_DomainManagerRegistry(), source,
			new String[] { "kind", "element", "name", "domainManagerRegistry", "namespace", "##targetNamespace" });
		addAnnotation(scaDomainManagerEClass, source, new String[] { "name", "ScaDomainManager", "kind", "elementOnly" });
		addAnnotation(getScaDomainManager_WaveformFactories(), source, new String[] { "kind", "element", "name", "waveformFactories" });
		addAnnotation(getScaDomainManager_Waveforms(), source, new String[] { "kind", "element", "name", "waveforms" });
		addAnnotation(getScaDomainManager_DeviceManagers(), source, new String[] { "kind", "element", "name", "deviceManagers" });
		addAnnotation(getScaDomainManager_FileManager(), source, new String[] { "kind", "element", "name", "fileManager" });
		addAnnotation(getScaDomainManager_ConnectionPropertiesContainer(), source, new String[] { "kind", "element", "name", "connectionProperties" });
		addAnnotation(getScaDomainManager_ConnectionProperties(), source, new String[] { "kind", "element", "name", "connectionPropertiesTransient" });
		addAnnotation(getScaDomainManager_AutoConnect(), source, new String[] { "kind", "attribute", "name", "autoConnect" });
		addAnnotation(getScaDomainManager_Connected(), source, new String[] { "kind", "attribute", "name", "connected" });
		addAnnotation(getScaDomainManager_Identifier(), source, new String[] { "kind", "attribute", "name", "identifier" });
		addAnnotation(getScaDomainManager_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getScaDomainManager_RootContext(), source, new String[] { "kind", "attribute", "name", "rootContext" });
		addAnnotation(getScaDomainManager_State(), source, new String[] { "kind", "attribute", "name", "state" });
		addAnnotation(scaDomainManagerFileSystemEClass, source, new String[] { "name", "ScaDomainManagerFileSystem", "kind", "empty" });
		addAnnotation(getScaDomainManagerFileSystem_DomMgr(), source, new String[] { "kind", "attribute", "name", "domMgr" });
		addAnnotation(scaDomainManagerRegistryEClass, source, new String[] { "name", "ScaDomainManagerRegistry", "kind", "elementOnly" });
		addAnnotation(getScaDomainManagerRegistry_Domains(), source, new String[] { "kind", "element", "name", "domain" });
		addAnnotation(scaExecutableDeviceEClass, source, new String[] { "name", "ScaExecutableDevice", "kind", "elementOnly" });
		addAnnotation(scaFileManagerEClass, source, new String[] { "name", "ScaFileManager", "kind", "empty" });
		addAnnotation(scaFileStoreEClass, source, new String[] { "name", "ScaFileStore", "kind", "empty" });
		addAnnotation(getScaFileStore_FileStore(), source, new String[] { "kind", "attribute", "name", "fileStore" });
		addAnnotation(getScaFileStore_Children(), source, new String[] { "kind", "attribute", "name", "children" });
		addAnnotation(scaFileSystemEClass, source, new String[] { "name", "ScaFileSystem", "kind", "empty" });
		addAnnotation(getScaFileSystem_FileSystemURI(), source, new String[] { "kind", "attribute", "name", "fileSystemURI" });
		addAnnotation(scaLoadableDeviceEClass, source, new String[] { "name", "ScaLoadableDevice", "kind", "elementOnly" });
		addAnnotation(scaPortEClass, source, new String[] { "name", "ScaPort", "kind", "empty" });
		addAnnotation(getScaPort_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getScaPort_ProfileObj(), source, new String[] { "kind", "attribute", "name", "profileObj" });
		addAnnotation(getScaPort_Repid(), source, new String[] { "kind", "attribute", "name", "repid" });
		addAnnotation(scaProvidesPortEClass, source, new String[] { "name", "ScaProvidesPort", "kind", "empty" });
		addAnnotation(scaSimplePropertyEClass, source, new String[] { "name", "ScaSimpleProperty", "kind", "empty" });
		addAnnotation(getScaSimpleProperty_Value(), source, new String[] { "kind", "attribute", "name", "currentValue" });
		addAnnotation(scaSimpleSequencePropertyEClass, source, new String[] { "name", "ScaSimpleSequenceProperty", "kind", "empty" });
		addAnnotation(getScaSimpleSequenceProperty_Values(), source, new String[] { "kind", "attribute", "name", "currentValue" });
		addAnnotation(scaStructPropertyEClass, source, new String[] { "name", "ScaStructProperty", "kind", "empty" });
		addAnnotation(getScaStructProperty_Fields(), source, new String[] { "kind", "attribute", "name", "fields" });
		addAnnotation(getScaStructProperty_Simples(), source, new String[] { "kind", "attribute", "name", "simples" });
		addAnnotation(scaUsesPortEClass, source, new String[] { "name", "ScaUsesPort", "kind", "empty" });
		addAnnotation(scaWaveformEClass, source, new String[] { "name", "ScaWaveform", "kind", "elementOnly" });
		addAnnotation(getScaWaveform_Components(), source, new String[] { "kind", "element", "name", "components" });
		addAnnotation(getScaWaveform_AssemblyController(), source, new String[] { "kind", "attribute", "name", "assemblyController" });
		addAnnotation(getScaWaveform_DomMgr(), source, new String[] { "kind", "attribute", "name", "domMgr" });
		addAnnotation(getScaWaveform_Identifier(), source, new String[] { "kind", "attribute", "name", "identifier" });
		addAnnotation(getScaWaveform_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(scaWaveformFactoryEClass, source, new String[] { "name", "ScaWaveformFactory", "kind", "empty" });
		addAnnotation(getScaWaveformFactory_DomMgr(), source, new String[] { "kind", "attribute", "name", "domMgr" });
		addAnnotation(getScaWaveformFactory_Identifier(), source, new String[] { "kind", "attribute", "name", "identifier" });
		addAnnotation(getScaWaveformFactory_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(stringToStringMapEClass, source, new String[] { "name", "StringToStringMap", "kind", "empty" });
		addAnnotation(getStringToStringMap_Key(), source, new String[] { "kind", "attribute", "name", "key" });
		addAnnotation(getStringToStringMap_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(uriEDataType, source, new String[] { "name", "URI" });
		addAnnotation(usageTypeEDataType, source, new String[] { "name", "UsageType", "enumeration", "ACTIVE BUSY IDLE" });
		addAnnotation(scaStructSequencePropertyEClass, source, new String[] { "name", "ScaStructSequenceProperty", "kind", "empty" });
		addAnnotation(getScaStructSequenceProperty_Structs(), source, new String[] { "kind", "attribute", "name", "structs" });
	}

} // ScaPackageImpl
