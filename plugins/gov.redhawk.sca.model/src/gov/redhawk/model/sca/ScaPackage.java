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

import mil.jpeojtrs.sca.cf.CfPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * 
 * @since 2.0
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 * @see gov.redhawk.model.sca.ScaFactory
 * @model kind="package"
 * @generated
 */
public interface ScaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "sca";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.redhawk.gov/model/sca/2.0.0";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "sca";
	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eCONTENT_TYPE = "http://www.redhawk.gov/model/sca/2.0.0";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	ScaPackage eINSTANCE = gov.redhawk.model.sca.impl.ScaPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.IStatusProviderImpl <em>IStatus Provider</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.IStatusProviderImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIStatusProvider()
	 * @generated
	 */
	int ISTATUS_PROVIDER = 34;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ISTATUS_PROVIDER__STATUS = 0;
	/**
	 * The number of structural features of the '<em>IStatus Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int ISTATUS_PROVIDER_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.DataProviderObjectImpl
	 * <em>Data Provider Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.DataProviderObjectImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDataProviderObject()
	 * @generated
	 */
	int DATA_PROVIDER_OBJECT = 1;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT__STATUS = ISTATUS_PROVIDER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT__DISPOSED = ISTATUS_PROVIDER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT__DATA_PROVIDERS = ISTATUS_PROVIDER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED = ISTATUS_PROVIDER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT__ENABLED_DATA_PROVIDERS = ISTATUS_PROVIDER_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Data Provider Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_OBJECT_FEATURE_COUNT = ISTATUS_PROVIDER_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.CorbaObjWrapperImpl <em>Corba Obj Wrapper</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.CorbaObjWrapperImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getCorbaObjWrapper()
	 * @generated
	 */
	int CORBA_OBJ_WRAPPER = 0;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__STATUS = DATA_PROVIDER_OBJECT__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__DISPOSED = DATA_PROVIDER_OBJECT__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__DATA_PROVIDERS = DATA_PROVIDER_OBJECT__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED = DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS = DATA_PROVIDER_OBJECT__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__IOR = DATA_PROVIDER_OBJECT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__OBJ = DATA_PROVIDER_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__CORBA_OBJ = DATA_PROVIDER_OBJECT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER__FEATURE_DATA = DATA_PROVIDER_OBJECT_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Corba Obj Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CORBA_OBJ_WRAPPER_FEATURE_COUNT = DATA_PROVIDER_OBJECT_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.IDisposable <em>IDisposable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.IDisposable
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIDisposable()
	 * @generated
	 */
	int IDISPOSABLE = 2;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IDISPOSABLE__DISPOSED = 0;
	/**
	 * The number of structural features of the '<em>IDisposable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IDISPOSABLE_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.ProfileObjectWrapper <em>Profile Object Wrapper</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getProfileObjectWrapper()
	 * @generated
	 */
	int PROFILE_OBJECT_WRAPPER = 3;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROFILE_OBJECT_WRAPPER__STATUS = ISTATUS_PROVIDER__STATUS;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROFILE_OBJECT_WRAPPER__PROFILE_URI = ISTATUS_PROVIDER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROFILE_OBJECT_WRAPPER__PROFILE_OBJ = ISTATUS_PROVIDER_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Profile Object Wrapper</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROFILE_OBJECT_WRAPPER_FEATURE_COUNT = ISTATUS_PROVIDER_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.PropertiesImpl <em>Properties</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.PropertiesImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 4;
	/**
	 * The feature id for the '<em><b>Property</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__PROPERTY = 0;
	/**
	 * The number of structural features of the '<em>Properties</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl
	 * <em>Property Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaPropertyContainerImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPropertyContainer()
	 * @generated
	 */
	int SCA_PROPERTY_CONTAINER = 6;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__STATUS = CORBA_OBJ_WRAPPER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__DISPOSED = CORBA_OBJ_WRAPPER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__DATA_PROVIDERS = CORBA_OBJ_WRAPPER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED = CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS = CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__IOR = CORBA_OBJ_WRAPPER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__OBJ = CORBA_OBJ_WRAPPER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__CORBA_OBJ = CORBA_OBJ_WRAPPER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__FEATURE_DATA = CORBA_OBJ_WRAPPER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__PROFILE_URI = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__PROFILE_OBJ = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER__PROPERTIES = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Property Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROPERTY_CONTAINER_FEATURE_COUNT = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl
	 * <em>Abstract Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaAbstractComponentImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaAbstractComponent()
	 * @generated
	 */
	int SCA_ABSTRACT_COMPONENT = 5;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__STATUS = SCA_PROPERTY_CONTAINER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__DISPOSED = SCA_PROPERTY_CONTAINER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS_ENABLED = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__ENABLED_DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__IOR = SCA_PROPERTY_CONTAINER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__OBJ = SCA_PROPERTY_CONTAINER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__CORBA_OBJ = SCA_PROPERTY_CONTAINER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__FEATURE_DATA = SCA_PROPERTY_CONTAINER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__PROFILE_URI = SCA_PROPERTY_CONTAINER__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__PROFILE_OBJ = SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__PROPERTIES = SCA_PROPERTY_CONTAINER__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__PORTS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__IDENTIFIER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__STARTED = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT__PROFILE = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Abstract Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_COMPONENT_FEATURE_COUNT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.IRefreshable <em>IRefreshable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.IRefreshable
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIRefreshable()
	 * @generated
	 */
	int IREFRESHABLE = 36;
	/**
	 * The number of structural features of the '<em>IRefreshable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IREFRESHABLE_FEATURE_COUNT = 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.ScaPortContainer <em>Port Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.ScaPortContainer
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPortContainer()
	 * @generated
	 */
	int SCA_PORT_CONTAINER = 7;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT_CONTAINER__STATUS = IREFRESHABLE_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT_CONTAINER__PORTS = IREFRESHABLE_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Port Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT_CONTAINER_FEATURE_COUNT = IREFRESHABLE_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl <em>Abstract Property</em>}
	 * ' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaAbstractProperty()
	 * @generated
	 */
	int SCA_ABSTRACT_PROPERTY = 8;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__STATUS = ISTATUS_PROVIDER__STATUS;
	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__DEFINITION = ISTATUS_PROVIDER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__DESCRIPTION = ISTATUS_PROVIDER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__ID = ISTATUS_PROVIDER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__MODE = ISTATUS_PROVIDER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__NAME = ISTATUS_PROVIDER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET = ISTATUS_PROVIDER_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the '<em>Abstract Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_ABSTRACT_PROPERTY_FEATURE_COUNT = ISTATUS_PROVIDER_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaComponentImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaComponent()
	 * @generated
	 */
	int SCA_COMPONENT = 9;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__STATUS = SCA_ABSTRACT_COMPONENT__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__DISPOSED = SCA_ABSTRACT_COMPONENT__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__DATA_PROVIDERS = SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__DATA_PROVIDERS_ENABLED = SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__ENABLED_DATA_PROVIDERS = SCA_ABSTRACT_COMPONENT__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__IOR = SCA_ABSTRACT_COMPONENT__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__OBJ = SCA_ABSTRACT_COMPONENT__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__CORBA_OBJ = SCA_ABSTRACT_COMPONENT__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__FEATURE_DATA = SCA_ABSTRACT_COMPONENT__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__PROFILE_URI = SCA_ABSTRACT_COMPONENT__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__PROFILE_OBJ = SCA_ABSTRACT_COMPONENT__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__PROPERTIES = SCA_ABSTRACT_COMPONENT__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__PORTS = SCA_ABSTRACT_COMPONENT__PORTS;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__IDENTIFIER = SCA_ABSTRACT_COMPONENT__IDENTIFIER;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__STARTED = SCA_ABSTRACT_COMPONENT__STARTED;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__PROFILE = SCA_ABSTRACT_COMPONENT__PROFILE;
	/**
	 * The feature id for the '<em><b>Component Instantiation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__COMPONENT_INSTANTIATION = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__DEVICES = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Instantiation Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__INSTANTIATION_IDENTIFIER = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Waveform</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__WAVEFORM = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT__NAME = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 4;
	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_COMPONENT_FEATURE_COUNT = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 5;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDeviceImpl <em>Device</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDeviceImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDevice()
	 * @generated
	 */
	int SCA_DEVICE = 10;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__STATUS = SCA_ABSTRACT_COMPONENT__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__DISPOSED = SCA_ABSTRACT_COMPONENT__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__DATA_PROVIDERS = SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__DATA_PROVIDERS_ENABLED = SCA_ABSTRACT_COMPONENT__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__ENABLED_DATA_PROVIDERS = SCA_ABSTRACT_COMPONENT__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__IOR = SCA_ABSTRACT_COMPONENT__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__OBJ = SCA_ABSTRACT_COMPONENT__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__CORBA_OBJ = SCA_ABSTRACT_COMPONENT__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__FEATURE_DATA = SCA_ABSTRACT_COMPONENT__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PROFILE_URI = SCA_ABSTRACT_COMPONENT__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PROFILE_OBJ = SCA_ABSTRACT_COMPONENT__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PROPERTIES = SCA_ABSTRACT_COMPONENT__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PORTS = SCA_ABSTRACT_COMPONENT__PORTS;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__IDENTIFIER = SCA_ABSTRACT_COMPONENT__IDENTIFIER;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__STARTED = SCA_ABSTRACT_COMPONENT__STARTED;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PROFILE = SCA_ABSTRACT_COMPONENT__PROFILE;
	/**
	 * The feature id for the '<em><b>Child Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__CHILD_DEVICES = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Admin State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__ADMIN_STATE = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__LABEL = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Operational State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__OPERATIONAL_STATE = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Usage State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__USAGE_STATE = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Parent Device</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__PARENT_DEVICE = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Dev Mgr</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE__DEV_MGR = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 6;
	/**
	 * The number of structural features of the '<em>Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_FEATURE_COUNT = SCA_ABSTRACT_COMPONENT_FEATURE_COUNT + 7;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl <em>Device Manager</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDeviceManagerImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDeviceManager()
	 * @generated
	 */
	int SCA_DEVICE_MANAGER = 11;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__STATUS = SCA_PROPERTY_CONTAINER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__DISPOSED = SCA_PROPERTY_CONTAINER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__DATA_PROVIDERS_ENABLED = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__ENABLED_DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__IOR = SCA_PROPERTY_CONTAINER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__OBJ = SCA_PROPERTY_CONTAINER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__CORBA_OBJ = SCA_PROPERTY_CONTAINER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__FEATURE_DATA = SCA_PROPERTY_CONTAINER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__PROFILE_URI = SCA_PROPERTY_CONTAINER__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__PROFILE_OBJ = SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__PROPERTIES = SCA_PROPERTY_CONTAINER__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__PORTS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Devices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__DEVICES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Root Devices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__ROOT_DEVICES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Child Devices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__CHILD_DEVICES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>All Devices</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__ALL_DEVICES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>File System</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__FILE_SYSTEM = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Dom Mgr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__DOM_MGR = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__IDENTIFIER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 7;
	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__LABEL = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 8;
	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__SERVICES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 9;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER__PROFILE = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 10;
	/**
	 * The number of structural features of the '<em>Device Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FEATURE_COUNT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 11;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaServiceImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaService()
	 * @generated
	 */
	int SCA_SERVICE = 12;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__STATUS = SCA_PROPERTY_CONTAINER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__DISPOSED = SCA_PROPERTY_CONTAINER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__DATA_PROVIDERS_ENABLED = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__ENABLED_DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__IOR = SCA_PROPERTY_CONTAINER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__OBJ = SCA_PROPERTY_CONTAINER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__CORBA_OBJ = SCA_PROPERTY_CONTAINER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__FEATURE_DATA = SCA_PROPERTY_CONTAINER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__PROFILE_URI = SCA_PROPERTY_CONTAINER__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__PROFILE_OBJ = SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__PROPERTIES = SCA_PROPERTY_CONTAINER__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__PORTS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__NAME = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Dev Mgr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE__DEV_MGR = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SERVICE_FEATURE_COUNT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl <em>File System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaFileSystemImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileSystem()
	 * @generated
	 */
	int SCA_FILE_SYSTEM = 21;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__STATUS = CORBA_OBJ_WRAPPER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__DISPOSED = CORBA_OBJ_WRAPPER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__DATA_PROVIDERS = CORBA_OBJ_WRAPPER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__DATA_PROVIDERS_ENABLED = CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__ENABLED_DATA_PROVIDERS = CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__IOR = CORBA_OBJ_WRAPPER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__OBJ = CORBA_OBJ_WRAPPER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__CORBA_OBJ = CORBA_OBJ_WRAPPER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__FEATURE_DATA = CORBA_OBJ_WRAPPER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__FILE_STORE = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__CHILDREN = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__IMAGE_DESC = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__DIRECTORY = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__NAME = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>File System URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM__FILE_SYSTEM_URI = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the '<em>File System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_SYSTEM_FEATURE_COUNT = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDeviceManagerFileSystemImpl
	 * <em>Device Manager File System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDeviceManagerFileSystemImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDeviceManagerFileSystem()
	 * @generated
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM = 13;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__STATUS = SCA_FILE_SYSTEM__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__DISPOSED = SCA_FILE_SYSTEM__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__DATA_PROVIDERS = SCA_FILE_SYSTEM__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__DATA_PROVIDERS_ENABLED = SCA_FILE_SYSTEM__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__ENABLED_DATA_PROVIDERS = SCA_FILE_SYSTEM__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__IOR = SCA_FILE_SYSTEM__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__OBJ = SCA_FILE_SYSTEM__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__CORBA_OBJ = SCA_FILE_SYSTEM__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__FEATURE_DATA = SCA_FILE_SYSTEM__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__FILE_STORE = SCA_FILE_SYSTEM__FILE_STORE;
	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__CHILDREN = SCA_FILE_SYSTEM__CHILDREN;
	/**
	 * The feature id for the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__IMAGE_DESC = SCA_FILE_SYSTEM__IMAGE_DESC;
	/**
	 * The feature id for the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__DIRECTORY = SCA_FILE_SYSTEM__DIRECTORY;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__NAME = SCA_FILE_SYSTEM__NAME;
	/**
	 * The feature id for the '<em><b>File System URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__FILE_SYSTEM_URI = SCA_FILE_SYSTEM__FILE_SYSTEM_URI;
	/**
	 * The feature id for the '<em><b>Device Manager</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER = SCA_FILE_SYSTEM_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Device Manager File System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DEVICE_MANAGER_FILE_SYSTEM_FEATURE_COUNT = SCA_FILE_SYSTEM_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDocumentRootImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDocumentRoot()
	 * @generated
	 */
	int SCA_DOCUMENT_ROOT = 14;
	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOCUMENT_ROOT__MIXED = 0;
	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;
	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;
	/**
	 * The feature id for the '<em><b>Domain Manager Registry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOCUMENT_ROOT__DOMAIN_MANAGER_REGISTRY = 3;
	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOCUMENT_ROOT_FEATURE_COUNT = 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl <em>Domain Manager</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDomainManagerImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManager()
	 * @generated
	 */
	int SCA_DOMAIN_MANAGER = 15;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__STATUS = SCA_PROPERTY_CONTAINER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__DISPOSED = SCA_PROPERTY_CONTAINER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__DATA_PROVIDERS_ENABLED = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__ENABLED_DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__IOR = SCA_PROPERTY_CONTAINER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__OBJ = SCA_PROPERTY_CONTAINER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__CORBA_OBJ = SCA_PROPERTY_CONTAINER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__FEATURE_DATA = SCA_PROPERTY_CONTAINER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__PROFILE_URI = SCA_PROPERTY_CONTAINER__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__PROFILE_OBJ = SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__PROPERTIES = SCA_PROPERTY_CONTAINER__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Waveform Factories</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Waveforms</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__WAVEFORMS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Device Managers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__DEVICE_MANAGERS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>File Manager</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__FILE_MANAGER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Connection Properties Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Connection Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Auto Connect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__AUTO_CONNECT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__CONNECTED = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 7;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__IDENTIFIER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 8;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__NAME = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 9;
	/**
	 * The feature id for the '<em><b>Root Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__ROOT_CONTEXT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 10;
	/**
	 * The feature id for the '<em><b>State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__STATE = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 11;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__PROFILE = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 12;
	/**
	 * The feature id for the '<em><b>Event Channels</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__EVENT_CHANNELS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 13;
	/**
	 * The feature id for the '<em><b>Local Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER__LOCAL_NAME = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 14;
	/**
	 * The number of structural features of the '<em>Domain Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FEATURE_COUNT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 15;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaFileManagerImpl <em>File Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaFileManagerImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileManager()
	 * @generated
	 */
	int SCA_FILE_MANAGER = 19;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__STATUS = SCA_FILE_SYSTEM__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__DISPOSED = SCA_FILE_SYSTEM__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__DATA_PROVIDERS = SCA_FILE_SYSTEM__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__DATA_PROVIDERS_ENABLED = SCA_FILE_SYSTEM__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__ENABLED_DATA_PROVIDERS = SCA_FILE_SYSTEM__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__IOR = SCA_FILE_SYSTEM__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__OBJ = SCA_FILE_SYSTEM__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__CORBA_OBJ = SCA_FILE_SYSTEM__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__FEATURE_DATA = SCA_FILE_SYSTEM__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__FILE_STORE = SCA_FILE_SYSTEM__FILE_STORE;
	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__CHILDREN = SCA_FILE_SYSTEM__CHILDREN;
	/**
	 * The feature id for the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__IMAGE_DESC = SCA_FILE_SYSTEM__IMAGE_DESC;
	/**
	 * The feature id for the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__DIRECTORY = SCA_FILE_SYSTEM__DIRECTORY;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__NAME = SCA_FILE_SYSTEM__NAME;
	/**
	 * The feature id for the '<em><b>File System URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER__FILE_SYSTEM_URI = SCA_FILE_SYSTEM__FILE_SYSTEM_URI;
	/**
	 * The number of structural features of the '<em>File Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_MANAGER_FEATURE_COUNT = SCA_FILE_SYSTEM_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerFileSystemImpl
	 * <em>Domain Manager File System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDomainManagerFileSystemImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManagerFileSystem()
	 * @generated
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM = 16;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__STATUS = SCA_FILE_MANAGER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__DISPOSED = SCA_FILE_MANAGER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__DATA_PROVIDERS = SCA_FILE_MANAGER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__DATA_PROVIDERS_ENABLED = SCA_FILE_MANAGER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__ENABLED_DATA_PROVIDERS = SCA_FILE_MANAGER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__IOR = SCA_FILE_MANAGER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__OBJ = SCA_FILE_MANAGER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__CORBA_OBJ = SCA_FILE_MANAGER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__FEATURE_DATA = SCA_FILE_MANAGER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__FILE_STORE = SCA_FILE_MANAGER__FILE_STORE;
	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__CHILDREN = SCA_FILE_MANAGER__CHILDREN;
	/**
	 * The feature id for the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__IMAGE_DESC = SCA_FILE_MANAGER__IMAGE_DESC;
	/**
	 * The feature id for the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__DIRECTORY = SCA_FILE_MANAGER__DIRECTORY;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__NAME = SCA_FILE_MANAGER__NAME;
	/**
	 * The feature id for the '<em><b>File System URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__FILE_SYSTEM_URI = SCA_FILE_MANAGER__FILE_SYSTEM_URI;
	/**
	 * The feature id for the '<em><b>Dom Mgr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR = SCA_FILE_MANAGER_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Domain Manager File System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_FILE_SYSTEM_FEATURE_COUNT = SCA_FILE_MANAGER_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl
	 * <em>Domain Manager Registry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManagerRegistry()
	 * @generated
	 */
	int SCA_DOMAIN_MANAGER_REGISTRY = 17;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_REGISTRY__DISPOSED = IDISPOSABLE__DISPOSED;
	/**
	 * The feature id for the '<em><b>Domains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS = IDISPOSABLE_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Domain Manager Registry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_DOMAIN_MANAGER_REGISTRY_FEATURE_COUNT = IDISPOSABLE_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaLoadableDeviceImpl <em>Loadable Device</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaLoadableDeviceImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaLoadableDevice()
	 * @generated
	 */
	int SCA_LOADABLE_DEVICE = 22;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__STATUS = SCA_DEVICE__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__DISPOSED = SCA_DEVICE__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__DATA_PROVIDERS = SCA_DEVICE__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__DATA_PROVIDERS_ENABLED = SCA_DEVICE__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__ENABLED_DATA_PROVIDERS = SCA_DEVICE__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__IOR = SCA_DEVICE__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__OBJ = SCA_DEVICE__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__CORBA_OBJ = SCA_DEVICE__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__FEATURE_DATA = SCA_DEVICE__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PROFILE_URI = SCA_DEVICE__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PROFILE_OBJ = SCA_DEVICE__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PROPERTIES = SCA_DEVICE__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PORTS = SCA_DEVICE__PORTS;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__IDENTIFIER = SCA_DEVICE__IDENTIFIER;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__STARTED = SCA_DEVICE__STARTED;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PROFILE = SCA_DEVICE__PROFILE;
	/**
	 * The feature id for the '<em><b>Child Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__CHILD_DEVICES = SCA_DEVICE__CHILD_DEVICES;
	/**
	 * The feature id for the '<em><b>Admin State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__ADMIN_STATE = SCA_DEVICE__ADMIN_STATE;
	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__LABEL = SCA_DEVICE__LABEL;
	/**
	 * The feature id for the '<em><b>Operational State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__OPERATIONAL_STATE = SCA_DEVICE__OPERATIONAL_STATE;
	/**
	 * The feature id for the '<em><b>Usage State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__USAGE_STATE = SCA_DEVICE__USAGE_STATE;
	/**
	 * The feature id for the '<em><b>Parent Device</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__PARENT_DEVICE = SCA_DEVICE__PARENT_DEVICE;
	/**
	 * The feature id for the '<em><b>Dev Mgr</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE__DEV_MGR = SCA_DEVICE__DEV_MGR;
	/**
	 * The number of structural features of the '<em>Loadable Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_LOADABLE_DEVICE_FEATURE_COUNT = SCA_DEVICE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaExecutableDeviceImpl <em>Executable Device</em>}
	 * ' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaExecutableDeviceImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaExecutableDevice()
	 * @generated
	 */
	int SCA_EXECUTABLE_DEVICE = 18;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__STATUS = SCA_LOADABLE_DEVICE__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__DISPOSED = SCA_LOADABLE_DEVICE__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__DATA_PROVIDERS = SCA_LOADABLE_DEVICE__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__DATA_PROVIDERS_ENABLED = SCA_LOADABLE_DEVICE__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__ENABLED_DATA_PROVIDERS = SCA_LOADABLE_DEVICE__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__IOR = SCA_LOADABLE_DEVICE__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__OBJ = SCA_LOADABLE_DEVICE__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__CORBA_OBJ = SCA_LOADABLE_DEVICE__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__FEATURE_DATA = SCA_LOADABLE_DEVICE__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PROFILE_URI = SCA_LOADABLE_DEVICE__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PROFILE_OBJ = SCA_LOADABLE_DEVICE__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PROPERTIES = SCA_LOADABLE_DEVICE__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PORTS = SCA_LOADABLE_DEVICE__PORTS;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__IDENTIFIER = SCA_LOADABLE_DEVICE__IDENTIFIER;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__STARTED = SCA_LOADABLE_DEVICE__STARTED;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PROFILE = SCA_LOADABLE_DEVICE__PROFILE;
	/**
	 * The feature id for the '<em><b>Child Devices</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__CHILD_DEVICES = SCA_LOADABLE_DEVICE__CHILD_DEVICES;
	/**
	 * The feature id for the '<em><b>Admin State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__ADMIN_STATE = SCA_LOADABLE_DEVICE__ADMIN_STATE;
	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__LABEL = SCA_LOADABLE_DEVICE__LABEL;
	/**
	 * The feature id for the '<em><b>Operational State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__OPERATIONAL_STATE = SCA_LOADABLE_DEVICE__OPERATIONAL_STATE;
	/**
	 * The feature id for the '<em><b>Usage State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__USAGE_STATE = SCA_LOADABLE_DEVICE__USAGE_STATE;
	/**
	 * The feature id for the '<em><b>Parent Device</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__PARENT_DEVICE = SCA_LOADABLE_DEVICE__PARENT_DEVICE;
	/**
	 * The feature id for the '<em><b>Dev Mgr</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE__DEV_MGR = SCA_LOADABLE_DEVICE__DEV_MGR;
	/**
	 * The number of structural features of the '<em>Executable Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_EXECUTABLE_DEVICE_FEATURE_COUNT = SCA_LOADABLE_DEVICE_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl <em>File Store</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaFileStoreImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileStore()
	 * @generated
	 */
	int SCA_FILE_STORE = 20;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__STATUS = ISTATUS_PROVIDER__STATUS;
	/**
	 * The feature id for the '<em><b>File Store</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__FILE_STORE = ISTATUS_PROVIDER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__CHILDREN = ISTATUS_PROVIDER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Image Desc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__IMAGE_DESC = ISTATUS_PROVIDER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__DIRECTORY = ISTATUS_PROVIDER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE__NAME = ISTATUS_PROVIDER_FEATURE_COUNT + 4;
	/**
	 * The number of structural features of the '<em>File Store</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_FILE_STORE_FEATURE_COUNT = ISTATUS_PROVIDER_FEATURE_COUNT + 5;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaPortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaPortImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPort()
	 * @generated
	 */
	int SCA_PORT = 23;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__STATUS = CORBA_OBJ_WRAPPER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__DISPOSED = CORBA_OBJ_WRAPPER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__DATA_PROVIDERS = CORBA_OBJ_WRAPPER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__DATA_PROVIDERS_ENABLED = CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__ENABLED_DATA_PROVIDERS = CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__IOR = CORBA_OBJ_WRAPPER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__OBJ = CORBA_OBJ_WRAPPER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__CORBA_OBJ = CORBA_OBJ_WRAPPER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__FEATURE_DATA = CORBA_OBJ_WRAPPER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__NAME = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__PROFILE_OBJ = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Repid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__REPID = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Port Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT__PORT_CONTAINER = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 3;
	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PORT_FEATURE_COUNT = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 4;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaProvidesPortImpl <em>Provides Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaProvidesPortImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaProvidesPort()
	 * @generated
	 */
	int SCA_PROVIDES_PORT = 24;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__STATUS = SCA_PORT__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__DISPOSED = SCA_PORT__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__DATA_PROVIDERS = SCA_PORT__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__DATA_PROVIDERS_ENABLED = SCA_PORT__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__ENABLED_DATA_PROVIDERS = SCA_PORT__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__IOR = SCA_PORT__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__OBJ = SCA_PORT__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__CORBA_OBJ = SCA_PORT__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__FEATURE_DATA = SCA_PORT__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__NAME = SCA_PORT__NAME;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__PROFILE_OBJ = SCA_PORT__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Repid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__REPID = SCA_PORT__REPID;
	/**
	 * The feature id for the '<em><b>Port Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT__PORT_CONTAINER = SCA_PORT__PORT_CONTAINER;
	/**
	 * The number of structural features of the '<em>Provides Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_PROVIDES_PORT_FEATURE_COUNT = SCA_PORT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaSimplePropertyImpl <em>Simple Property</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaSimplePropertyImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaSimpleProperty()
	 * @generated
	 */
	int SCA_SIMPLE_PROPERTY = 25;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__STATUS = SCA_ABSTRACT_PROPERTY__STATUS;
	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__DEFINITION = SCA_ABSTRACT_PROPERTY__DEFINITION;
	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__DESCRIPTION = SCA_ABSTRACT_PROPERTY__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__ID = SCA_ABSTRACT_PROPERTY__ID;
	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__MODE = SCA_ABSTRACT_PROPERTY__MODE;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__NAME = SCA_ABSTRACT_PROPERTY__NAME;
	/**
	 * The feature id for the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__IGNORE_REMOTE_SET = SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY__VALUE = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Simple Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_PROPERTY_FEATURE_COUNT = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl
	 * <em>Simple Sequence Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaSimpleSequenceProperty()
	 * @generated
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY = 26;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__STATUS = SCA_ABSTRACT_PROPERTY__STATUS;
	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__DEFINITION = SCA_ABSTRACT_PROPERTY__DEFINITION;
	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__DESCRIPTION = SCA_ABSTRACT_PROPERTY__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__ID = SCA_ABSTRACT_PROPERTY__ID;
	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__MODE = SCA_ABSTRACT_PROPERTY__MODE;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__NAME = SCA_ABSTRACT_PROPERTY__NAME;
	/**
	 * The feature id for the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__IGNORE_REMOTE_SET = SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET;
	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Simple Sequence Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_SIMPLE_SEQUENCE_PROPERTY_FEATURE_COUNT = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl <em>Struct Property</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaStructPropertyImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaStructProperty()
	 * @generated
	 */
	int SCA_STRUCT_PROPERTY = 27;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__STATUS = SCA_ABSTRACT_PROPERTY__STATUS;
	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__DEFINITION = SCA_ABSTRACT_PROPERTY__DEFINITION;
	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__DESCRIPTION = SCA_ABSTRACT_PROPERTY__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__ID = SCA_ABSTRACT_PROPERTY__ID;
	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__MODE = SCA_ABSTRACT_PROPERTY__MODE;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__NAME = SCA_ABSTRACT_PROPERTY__NAME;
	/**
	 * The feature id for the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__IGNORE_REMOTE_SET = SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET;
	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__FIELDS = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Simples</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY__SIMPLES = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Struct Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_PROPERTY_FEATURE_COUNT = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaUsesPortImpl <em>Uses Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaUsesPortImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaUsesPort()
	 * @generated
	 */
	int SCA_USES_PORT = 28;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__STATUS = SCA_PORT__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__DISPOSED = SCA_PORT__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__DATA_PROVIDERS = SCA_PORT__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__DATA_PROVIDERS_ENABLED = SCA_PORT__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__ENABLED_DATA_PROVIDERS = SCA_PORT__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__IOR = SCA_PORT__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__OBJ = SCA_PORT__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__CORBA_OBJ = SCA_PORT__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__FEATURE_DATA = SCA_PORT__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__NAME = SCA_PORT__NAME;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__PROFILE_OBJ = SCA_PORT__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Repid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__REPID = SCA_PORT__REPID;
	/**
	 * The feature id for the '<em><b>Port Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__PORT_CONTAINER = SCA_PORT__PORT_CONTAINER;
	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT__CONNECTIONS = SCA_PORT_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Uses Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_USES_PORT_FEATURE_COUNT = SCA_PORT_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaConnectionImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaConnection()
	 * @generated
	 */
	int SCA_CONNECTION = 29;
	/**
	 * The feature id for the '<em><b>Data</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION__DATA = 0;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION__ID = 1;
	/**
	 * The feature id for the '<em><b>Port</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION__PORT = 2;
	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_CONNECTION_FEATURE_COUNT = 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaWaveformImpl <em>Waveform</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaWaveformImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaWaveform()
	 * @generated
	 */
	int SCA_WAVEFORM = 30;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__STATUS = SCA_PROPERTY_CONTAINER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__DISPOSED = SCA_PROPERTY_CONTAINER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__DATA_PROVIDERS_ENABLED = SCA_PROPERTY_CONTAINER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__ENABLED_DATA_PROVIDERS = SCA_PROPERTY_CONTAINER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__IOR = SCA_PROPERTY_CONTAINER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__OBJ = SCA_PROPERTY_CONTAINER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__CORBA_OBJ = SCA_PROPERTY_CONTAINER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__FEATURE_DATA = SCA_PROPERTY_CONTAINER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__PROFILE_URI = SCA_PROPERTY_CONTAINER__PROFILE_URI;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__PROFILE_OBJ = SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__PROPERTIES = SCA_PROPERTY_CONTAINER__PROPERTIES;
	/**
	 * The feature id for the '<em><b>Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__PORTS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__COMPONENTS = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Assembly Controller</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__ASSEMBLY_CONTROLLER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Dom Mgr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__DOM_MGR = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__IDENTIFIER = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__NAME = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__STARTED = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 6;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM__PROFILE = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 7;
	/**
	 * The number of structural features of the '<em>Waveform</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FEATURE_COUNT = SCA_PROPERTY_CONTAINER_FEATURE_COUNT + 8;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl <em>Waveform Factory</em>}'
	 * class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaWaveformFactory()
	 * @generated
	 */
	int SCA_WAVEFORM_FACTORY = 31;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__STATUS = CORBA_OBJ_WRAPPER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__DISPOSED = CORBA_OBJ_WRAPPER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__DATA_PROVIDERS = CORBA_OBJ_WRAPPER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__DATA_PROVIDERS_ENABLED = CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__ENABLED_DATA_PROVIDERS = CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__IOR = CORBA_OBJ_WRAPPER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__OBJ = CORBA_OBJ_WRAPPER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__CORBA_OBJ = CORBA_OBJ_WRAPPER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__FEATURE_DATA = CORBA_OBJ_WRAPPER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Profile URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__PROFILE_URI = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Profile Obj</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__PROFILE_OBJ = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Dom Mgr</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__DOM_MGR = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__IDENTIFIER = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__NAME = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 4;
	/**
	 * The feature id for the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY__PROFILE = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 5;
	/**
	 * The number of structural features of the '<em>Waveform Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_WAVEFORM_FACTORY_FEATURE_COUNT = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 6;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.StringToStringMapImpl <em>String To String Map</em>
	 * }' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.StringToStringMapImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getStringToStringMap()
	 * @generated
	 */
	int STRING_TO_STRING_MAP = 32;
	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__KEY = 0;
	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP__VALUE = 1;
	/**
	 * The number of structural features of the '<em>String To String Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int STRING_TO_STRING_MAP_FEATURE_COUNT = 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl
	 * <em>Struct Sequence Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaStructSequenceProperty()
	 * @generated
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY = 33;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__STATUS = SCA_ABSTRACT_PROPERTY__STATUS;
	/**
	 * The feature id for the '<em><b>Definition</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__DEFINITION = SCA_ABSTRACT_PROPERTY__DEFINITION;
	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__DESCRIPTION = SCA_ABSTRACT_PROPERTY__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__ID = SCA_ABSTRACT_PROPERTY__ID;
	/**
	 * The feature id for the '<em><b>Mode</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__MODE = SCA_ABSTRACT_PROPERTY__MODE;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__NAME = SCA_ABSTRACT_PROPERTY__NAME;
	/**
	 * The feature id for the '<em><b>Ignore Remote Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__IGNORE_REMOTE_SET = SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET;
	/**
	 * The feature id for the '<em><b>Structs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Struct Sequence Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SCA_STRUCT_SEQUENCE_PROPERTY_FEATURE_COUNT = SCA_ABSTRACT_PROPERTY_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link org.omg.CosEventChannelAdmin.EventChannel <em>Event Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @see org.omg.CosEventChannelAdmin.EventChannel
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getEventChannel()
	 * @generated
	 */
	int EVENT_CHANNEL = 35;
	/**
	 * The number of structural features of the '<em>Event Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_CHANNEL_FEATURE_COUNT = CfPackage.OBJECT_FEATURE_COUNT + 0;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.ScaEventChannelImpl <em>Event Channel</em>}' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.impl.ScaEventChannelImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaEventChannel()
	 * @generated
	 */
	int SCA_EVENT_CHANNEL = 37;
	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__STATUS = CORBA_OBJ_WRAPPER__STATUS;
	/**
	 * The feature id for the '<em><b>Disposed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__DISPOSED = CORBA_OBJ_WRAPPER__DISPOSED;
	/**
	 * The feature id for the '<em><b>Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__DATA_PROVIDERS = CORBA_OBJ_WRAPPER__DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Data Providers Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__DATA_PROVIDERS_ENABLED = CORBA_OBJ_WRAPPER__DATA_PROVIDERS_ENABLED;
	/**
	 * The feature id for the '<em><b>Enabled Data Providers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__ENABLED_DATA_PROVIDERS = CORBA_OBJ_WRAPPER__ENABLED_DATA_PROVIDERS;
	/**
	 * The feature id for the '<em><b>Ior</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__IOR = CORBA_OBJ_WRAPPER__IOR;
	/**
	 * The feature id for the '<em><b>Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__OBJ = CORBA_OBJ_WRAPPER__OBJ;
	/**
	 * The feature id for the '<em><b>Corba Obj</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__CORBA_OBJ = CORBA_OBJ_WRAPPER__CORBA_OBJ;
	/**
	 * The feature id for the '<em><b>Feature Data</b></em>' map.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__FEATURE_DATA = CORBA_OBJ_WRAPPER__FEATURE_DATA;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL__NAME = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Event Channel</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCA_EVENT_CHANNEL_FEATURE_COUNT = CORBA_OBJ_WRAPPER_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.StringToObjectMapImpl <em>String To Object Map</em>
	 * }' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.impl.StringToObjectMapImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getStringToObjectMap()
	 * @generated
	 */
	int STRING_TO_OBJECT_MAP = 38;
	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAP__KEY = 0;
	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAP__VALUE = 1;
	/**
	 * The number of structural features of the '<em>String To Object Map</em>' class.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_OBJECT_MAP_FEATURE_COUNT = 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.impl.WaveformsContainerImpl <em>Waveforms Container</em>
	 * }' class.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.WaveformsContainerImpl
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getWaveformsContainer()
	 * @generated
	 */
	int WAVEFORMS_CONTAINER = 39;
	/**
	 * The feature id for the '<em><b>Sub Containers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAVEFORMS_CONTAINER__SUB_CONTAINERS = 0;
	/**
	 * The feature id for the '<em><b>Waveforms</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAVEFORMS_CONTAINER__WAVEFORMS = 1;
	/**
	 * The feature id for the '<em><b>Container Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAVEFORMS_CONTAINER__CONTAINER_NAME = 2;
	/**
	 * The number of structural features of the '<em>Waveforms Container</em>' class.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int WAVEFORMS_CONTAINER_FEATURE_COUNT = 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.DomainConnectionState <em>Domain Connection State</em>}'
	 * enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionState()
	 * @generated
	 */
	int DOMAIN_CONNECTION_STATE = 40;
	/**
	 * The meta object id for the '{@link gov.redhawk.model.sca.RefreshDepth <em>Refresh Depth</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.RefreshDepth
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getRefreshDepth()
	 * @generated
	 */
	int REFRESH_DEPTH = 41;
	/**
	 * The meta object id for the '<em>Admin Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see CF.DevicePackage.AdminType
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getAdminType()
	 * @generated
	 */
	int ADMIN_TYPE = 42;
	/**
	 * The meta object id for the '<em>Domain Connection Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.DomainConnectionException
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionException()
	 * @generated
	 */
	int DOMAIN_CONNECTION_EXCEPTION = 43;
	/**
	 * The meta object id for the '<em>Domain Connection State Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionStateObject()
	 * @generated
	 */
	int DOMAIN_CONNECTION_STATE_OBJECT = 44;
	/**
	 * The meta object id for the '<em>IFile Store</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.filesystem.IFileStore
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIFileStore()
	 * @generated
	 */
	int IFILE_STORE = 45;
	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 46;
	/**
	 * The meta object id for the '<em>ISca Data Provider</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.services.IScaDataProvider
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIScaDataProvider()
	 * @generated
	 */
	int ISCA_DATA_PROVIDER = 47;
	/**
	 * The meta object id for the '<em>ISca Data Provider Service</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.services.IScaDataProviderService
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIScaDataProviderService()
	 * @generated
	 */
	int ISCA_DATA_PROVIDER_SERVICE = 48;
	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.core.runtime.IStatus
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 49;
	/**
	 * The meta object id for the '<em>Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.omg.CORBA.Object
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 50;
	/**
	 * The meta object id for the '<em>Object Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getObjectArray()
	 * @generated
	 */
	int OBJECT_ARRAY = 51;
	/**
	 * The meta object id for the '<em>Operational Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see CF.DevicePackage.OperationalType
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getOperationalType()
	 * @generated
	 */
	int OPERATIONAL_TYPE = 52;
	/**
	 * The meta object id for the '<em>Refresh Depth Object</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.RefreshDepth
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getRefreshDepthObject()
	 * @generated
	 */
	int REFRESH_DEPTH_OBJECT = 53;
	/**
	 * The meta object id for the '<em>POA</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.omg.PortableServer.POA
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getPOA()
	 * @generated
	 */
	int POA = 54;
	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see java.net.URI
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getURI()
	 * @generated
	 */
	int URI = 55;
	/**
	 * The meta object id for the '<em>Usage Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see CF.DevicePackage.UsageType
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getUsageType()
	 * @generated
	 */
	int USAGE_TYPE = 56;
	/**
	 * The meta object id for the '<em>Data Type Array</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDataTypeArray()
	 * @generated
	 */
	int DATA_TYPE_ARRAY = 57;
	/**
	 * The meta object id for the '<em>Any</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see org.omg.CORBA.Any
	 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getAny()
	 * @generated
	 */
	int ANY = 58;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.CorbaObjWrapper <em>Corba Obj Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Corba Obj Wrapper</em>'.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper
	 * @generated
	 */
	EClass getCorbaObjWrapper();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.CorbaObjWrapper#getIor <em>Ior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ior</em>'.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#getIor()
	 * @see #getCorbaObjWrapper()
	 * @generated
	 */
	EAttribute getCorbaObjWrapper_Ior();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.CorbaObjWrapper#getObj <em>Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Obj</em>'.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#getObj()
	 * @see #getCorbaObjWrapper()
	 * @generated
	 */
	EAttribute getCorbaObjWrapper_Obj();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj
	 * <em>Corba Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Corba Obj</em>'.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#getCorbaObj()
	 * @see #getCorbaObjWrapper()
	 * @generated
	 */
	EAttribute getCorbaObjWrapper_CorbaObj();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.model.sca.CorbaObjWrapper#getFeatureData
	 * <em>Feature Data</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Feature Data</em>'.
	 * @see gov.redhawk.model.sca.CorbaObjWrapper#getFeatureData()
	 * @see #getCorbaObjWrapper()
	 * @generated
	 */
	EReference getCorbaObjWrapper_FeatureData();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.DataProviderObject <em>Data Provider Object</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Data Provider Object</em>'.
	 * @see gov.redhawk.model.sca.DataProviderObject
	 * @generated
	 */
	EClass getDataProviderObject();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.model.sca.DataProviderObject#getDataProviders
	 * <em>Data Providers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Data Providers</em>'.
	 * @see gov.redhawk.model.sca.DataProviderObject#getDataProviders()
	 * @see #getDataProviderObject()
	 * @generated
	 */
	EAttribute getDataProviderObject_DataProviders();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.DataProviderObject#isDataProvidersEnabled
	 * <em>Data Providers Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Data Providers Enabled</em>'.
	 * @see gov.redhawk.model.sca.DataProviderObject#isDataProvidersEnabled()
	 * @see #getDataProviderObject()
	 * @generated
	 */
	EAttribute getDataProviderObject_DataProvidersEnabled();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link gov.redhawk.model.sca.DataProviderObject#getEnabledDataProviders <em>Enabled Data Providers</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Enabled Data Providers</em>'.
	 * @see gov.redhawk.model.sca.DataProviderObject#getEnabledDataProviders()
	 * @see #getDataProviderObject()
	 * @generated
	 */
	EAttribute getDataProviderObject_EnabledDataProviders();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.IDisposable <em>IDisposable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IDisposable</em>'.
	 * @see gov.redhawk.model.sca.IDisposable
	 * @generated
	 */
	EClass getIDisposable();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.IDisposable#isDisposed <em>Disposed</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Disposed</em>'.
	 * @see gov.redhawk.model.sca.IDisposable#isDisposed()
	 * @see #getIDisposable()
	 * @generated
	 */
	EAttribute getIDisposable_Disposed();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ProfileObjectWrapper
	 * <em>Profile Object Wrapper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Profile Object Wrapper</em>'.
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper
	 * @generated
	 */
	EClass getProfileObjectWrapper();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI
	 * <em>Profile URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Profile URI</em>'.
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI()
	 * @see #getProfileObjectWrapper()
	 * @generated
	 */
	EAttribute getProfileObjectWrapper_ProfileURI();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj
	 * <em>Profile Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Profile Obj</em>'.
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj()
	 * @see #getProfileObjectWrapper()
	 * @generated
	 */
	EReference getProfileObjectWrapper_ProfileObj();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.Properties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see gov.redhawk.model.sca.Properties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.model.sca.Properties#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Property</em>'.
	 * @see gov.redhawk.model.sca.Properties#getProperty()
	 * @see #getProperties()
	 * @generated
	 */
	EReference getProperties_Property();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaAbstractComponent <em>Abstract Component</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Abstract Component</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractComponent
	 * @generated
	 */
	EClass getScaAbstractComponent();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier
	 * <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier()
	 * @see #getScaAbstractComponent()
	 * @generated
	 */
	EAttribute getScaAbstractComponent_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractComponent#getStarted
	 * <em>Started</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Started</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#getStarted()
	 * @see #getScaAbstractComponent()
	 * @generated
	 */
	EAttribute getScaAbstractComponent_Started();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractComponent#getProfile
	 * <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Profile</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#getProfile()
	 * @see #getScaAbstractComponent()
	 * @generated
	 */
	EAttribute getScaAbstractComponent_Profile();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaPropertyContainer <em>Property Container</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Property Container</em>'.
	 * @see gov.redhawk.model.sca.ScaPropertyContainer
	 * @generated
	 */
	EClass getScaPropertyContainer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaPropertyContainer#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see gov.redhawk.model.sca.ScaPropertyContainer#getProperties()
	 * @see #getScaPropertyContainer()
	 * @generated
	 */
	EReference getScaPropertyContainer_Properties();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaPortContainer <em>Port Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Port Container</em>'.
	 * @see gov.redhawk.model.sca.ScaPortContainer
	 * @generated
	 */
	EClass getScaPortContainer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaPortContainer#getPorts <em>Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Ports</em>'.
	 * @see gov.redhawk.model.sca.ScaPortContainer#getPorts()
	 * @see #getScaPortContainer()
	 * @generated
	 */
	EReference getScaPortContainer_Ports();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaAbstractProperty <em>Abstract Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Abstract Property</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty
	 * @generated
	 */
	EClass getScaAbstractProperty();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaAbstractProperty#getDefinition
	 * <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Definition</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getDefinition()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EReference getScaAbstractProperty_Definition();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractProperty#getDescription
	 * <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getDescription()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EAttribute getScaAbstractProperty_Description();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractProperty#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getId()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EAttribute getScaAbstractProperty_Id();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractProperty#getMode <em>Mode</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mode</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getMode()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EAttribute getScaAbstractProperty_Mode();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractProperty#getName <em>Name</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getName()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EAttribute getScaAbstractProperty_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet
	 * <em>Ignore Remote Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Ignore Remote Set</em>'.
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet()
	 * @see #getScaAbstractProperty()
	 * @generated
	 */
	EAttribute getScaAbstractProperty_IgnoreRemoteSet();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Component</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent
	 * @generated
	 */
	EClass getScaComponent();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaComponent#getComponentInstantiation
	 * <em>Component Instantiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Component Instantiation</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent#getComponentInstantiation()
	 * @see #getScaComponent()
	 * @generated
	 */
	EReference getScaComponent_ComponentInstantiation();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.model.sca.ScaComponent#getDevices
	 * <em>Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent#getDevices()
	 * @see #getScaComponent()
	 * @generated
	 */
	EReference getScaComponent_Devices();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier
	 * <em>Instantiation Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Instantiation Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent#getInstantiationIdentifier()
	 * @see #getScaComponent()
	 * @generated
	 */
	EAttribute getScaComponent_InstantiationIdentifier();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaComponent#getWaveform
	 * <em>Waveform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Waveform</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent#getWaveform()
	 * @see #getScaComponent()
	 * @generated
	 */
	EReference getScaComponent_Waveform();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaComponent#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaComponent#getName()
	 * @see #getScaComponent()
	 * @generated
	 */
	EAttribute getScaComponent_Name();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDevice <em>Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Device</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice
	 * @generated
	 */
	EClass getScaDevice();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.model.sca.ScaDevice#getChildDevices
	 * <em>Child Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Child Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getChildDevices()
	 * @see #getScaDevice()
	 * @generated
	 */
	EReference getScaDevice_ChildDevices();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDevice#getAdminState
	 * <em>Admin State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Admin State</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getAdminState()
	 * @see #getScaDevice()
	 * @generated
	 */
	EAttribute getScaDevice_AdminState();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDevice#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getLabel()
	 * @see #getScaDevice()
	 * @generated
	 */
	EAttribute getScaDevice_Label();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDevice#getOperationalState
	 * <em>Operational State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Operational State</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getOperationalState()
	 * @see #getScaDevice()
	 * @generated
	 */
	EAttribute getScaDevice_OperationalState();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDevice#getUsageState
	 * <em>Usage State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Usage State</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getUsageState()
	 * @see #getScaDevice()
	 * @generated
	 */
	EAttribute getScaDevice_UsageState();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaDevice#getParentDevice
	 * <em>Parent Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Parent Device</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getParentDevice()
	 * @see #getScaDevice()
	 * @generated
	 */
	EReference getScaDevice_ParentDevice();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaDevice#getDevMgr <em>Dev Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Dev Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaDevice#getDevMgr()
	 * @see #getScaDevice()
	 * @generated
	 */
	EReference getScaDevice_DevMgr();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDeviceManager <em>Device Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Device Manager</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager
	 * @generated
	 */
	EClass getScaDeviceManager();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.model.sca.ScaDeviceManager#getDevices
	 * <em>Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getDevices()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EAttribute getScaDeviceManager_Devices();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDeviceManager#getRootDevices <em>Root Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Root Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getRootDevices()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_RootDevices();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDeviceManager#getChildDevices <em>Child Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Child Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getChildDevices()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_ChildDevices();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices <em>All Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>All Devices</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getAllDevices()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_AllDevices();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem <em>File System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>File System</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getFileSystem()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_FileSystem();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaDeviceManager#getDomMgr
	 * <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Dom Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getDomMgr()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_DomMgr();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier
	 * <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getIdentifier()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EAttribute getScaDeviceManager_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel <em>Label</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getLabel()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EAttribute getScaDeviceManager_Label();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDeviceManager#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getServices()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EReference getScaDeviceManager_Services();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDeviceManager#getProfile
	 * <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Profile</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getProfile()
	 * @see #getScaDeviceManager()
	 * @generated
	 */
	EAttribute getScaDeviceManager_Profile();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaService <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Service</em>'.
	 * @see gov.redhawk.model.sca.ScaService
	 * @generated
	 */
	EClass getScaService();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaService#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaService#getName()
	 * @see #getScaService()
	 * @generated
	 */
	EAttribute getScaService_Name();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaService#getDevMgr
	 * <em>Dev Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Dev Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaService#getDevMgr()
	 * @see #getScaService()
	 * @generated
	 */
	EReference getScaService_DevMgr();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem
	 * <em>Device Manager File System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Device Manager File System</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManagerFileSystem
	 * @generated
	 */
	EClass getScaDeviceManagerFileSystem();

	/**
	 * Returns the meta object for the container reference '
	 * {@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager <em>Device Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Device Manager</em>'.
	 * @see gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager()
	 * @see #getScaDeviceManagerFileSystem()
	 * @generated
	 */
	EReference getScaDeviceManagerFileSystem_DeviceManager();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot
	 * @generated
	 */
	EClass getScaDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.model.sca.ScaDocumentRoot#getMixed
	 * <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#getMixed()
	 * @see #getScaDocumentRoot()
	 * @generated
	 */
	EAttribute getScaDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.model.sca.ScaDocumentRoot#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#getXMLNSPrefixMap()
	 * @see #getScaDocumentRoot()
	 * @generated
	 */
	EReference getScaDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.model.sca.ScaDocumentRoot#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#getXSISchemaLocation()
	 * @see #getScaDocumentRoot()
	 * @generated
	 */
	EReference getScaDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link gov.redhawk.model.sca.ScaDocumentRoot#getDomainManagerRegistry <em>Domain Manager Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Domain Manager Registry</em>'.
	 * @see gov.redhawk.model.sca.ScaDocumentRoot#getDomainManagerRegistry()
	 * @see #getScaDocumentRoot()
	 * @generated
	 */
	EReference getScaDocumentRoot_DomainManagerRegistry();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDomainManager <em>Domain Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Domain Manager</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager
	 * @generated
	 */
	EClass getScaDomainManager();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories <em>Waveform Factories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Waveform Factories</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_WaveformFactories();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getWaveforms <em>Waveforms</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Waveforms</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getWaveforms()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_Waveforms();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers <em>Device Managers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Device Managers</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_DeviceManagers();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>File Manager</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getFileManager()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_FileManager();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getConnectionPropertiesContainer
	 * <em>Connection Properties Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Connection Properties Container</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getConnectionPropertiesContainer()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_ConnectionPropertiesContainer();

	/**
	 * Returns the meta object for the map '{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties
	 * <em>Connection Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Connection Properties</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_ConnectionProperties();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#isAutoConnect
	 * <em>Auto Connect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Auto Connect</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#isAutoConnect()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_AutoConnect();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#isConnected
	 * <em>Connected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Connected</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#isConnected()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_Connected();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getIdentifier
	 * <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getIdentifier()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getName()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getRootContext
	 * <em>Root Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Root Context</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getRootContext()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_RootContext();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getState <em>State</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>State</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getState()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_State();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getProfile
	 * <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Profile</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getProfile()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_Profile();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#getEventChannels <em>Event Channels</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Channels</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getEventChannels()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EReference getScaDomainManager_EventChannels();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaDomainManager#getLocalName
	 * <em>Local Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Name</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManager#getLocalName()
	 * @see #getScaDomainManager()
	 * @generated
	 */
	EAttribute getScaDomainManager_LocalName();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDomainManagerFileSystem
	 * <em>Domain Manager File System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Domain Manager File System</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManagerFileSystem
	 * @generated
	 */
	EClass getScaDomainManagerFileSystem();

	/**
	 * Returns the meta object for the container reference '
	 * {@link gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Dom Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr()
	 * @see #getScaDomainManagerFileSystem()
	 * @generated
	 */
	EReference getScaDomainManagerFileSystem_DomMgr();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaDomainManagerRegistry
	 * <em>Domain Manager Registry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Domain Manager Registry</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry
	 * @generated
	 */
	EClass getScaDomainManagerRegistry();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaDomainManagerRegistry#getDomains <em>Domains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Domains</em>'.
	 * @see gov.redhawk.model.sca.ScaDomainManagerRegistry#getDomains()
	 * @see #getScaDomainManagerRegistry()
	 * @generated
	 */
	EReference getScaDomainManagerRegistry_Domains();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaExecutableDevice <em>Executable Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Executable Device</em>'.
	 * @see gov.redhawk.model.sca.ScaExecutableDevice
	 * @generated
	 */
	EClass getScaExecutableDevice();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaFileManager <em>File Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>File Manager</em>'.
	 * @see gov.redhawk.model.sca.ScaFileManager
	 * @generated
	 */
	EClass getScaFileManager();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaFileStore <em>File Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>File Store</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore
	 * @generated
	 */
	EClass getScaFileStore();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaFileStore#getFileStore
	 * <em>File Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>File Store</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore#getFileStore()
	 * @see #getScaFileStore()
	 * @generated
	 */
	EAttribute getScaFileStore_FileStore();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.model.sca.ScaFileStore#getChildren
	 * <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore#getChildren()
	 * @see #getScaFileStore()
	 * @generated
	 */
	EReference getScaFileStore_Children();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaFileStore#getImageDesc
	 * <em>Image Desc</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Image Desc</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore#getImageDesc()
	 * @see #getScaFileStore()
	 * @generated
	 */
	EAttribute getScaFileStore_ImageDesc();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaFileStore#isDirectory
	 * <em>Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Directory</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore#isDirectory()
	 * @see #getScaFileStore()
	 * @generated
	 */
	EAttribute getScaFileStore_Directory();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaFileStore#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaFileStore#getName()
	 * @see #getScaFileStore()
	 * @generated
	 */
	EAttribute getScaFileStore_Name();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaFileSystem <em>File System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>File System</em>'.
	 * @see gov.redhawk.model.sca.ScaFileSystem
	 * @generated
	 */
	EClass getScaFileSystem();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI
	 * <em>File System URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>File System URI</em>'.
	 * @see gov.redhawk.model.sca.ScaFileSystem#getFileSystemURI()
	 * @see #getScaFileSystem()
	 * @generated
	 */
	EAttribute getScaFileSystem_FileSystemURI();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaLoadableDevice <em>Loadable Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Loadable Device</em>'.
	 * @see gov.redhawk.model.sca.ScaLoadableDevice
	 * @generated
	 */
	EClass getScaLoadableDevice();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Port</em>'.
	 * @see gov.redhawk.model.sca.ScaPort
	 * @generated
	 */
	EClass getScaPort();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaPort#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaPort#getName()
	 * @see #getScaPort()
	 * @generated
	 */
	EAttribute getScaPort_Name();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaPort#getProfileObj
	 * <em>Profile Obj</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Profile Obj</em>'.
	 * @see gov.redhawk.model.sca.ScaPort#getProfileObj()
	 * @see #getScaPort()
	 * @generated
	 */
	EReference getScaPort_ProfileObj();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaPort#getRepid <em>Repid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Repid</em>'.
	 * @see gov.redhawk.model.sca.ScaPort#getRepid()
	 * @see #getScaPort()
	 * @generated
	 */
	EAttribute getScaPort_Repid();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaPort#getPortContainer
	 * <em>Port Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Port Container</em>'.
	 * @see gov.redhawk.model.sca.ScaPort#getPortContainer()
	 * @see #getScaPort()
	 * @generated
	 */
	EReference getScaPort_PortContainer();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaProvidesPort <em>Provides Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Provides Port</em>'.
	 * @see gov.redhawk.model.sca.ScaProvidesPort
	 * @generated
	 */
	EClass getScaProvidesPort();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaSimpleProperty <em>Simple Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Simple Property</em>'.
	 * @see gov.redhawk.model.sca.ScaSimpleProperty
	 * @generated
	 */
	EClass getScaSimpleProperty();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaSimpleProperty#getValue <em>Value</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see gov.redhawk.model.sca.ScaSimpleProperty#getValue()
	 * @see #getScaSimpleProperty()
	 * @generated
	 */
	EAttribute getScaSimpleProperty_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty
	 * <em>Simple Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Simple Sequence Property</em>'.
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty
	 * @generated
	 */
	EClass getScaSimpleSequenceProperty();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.model.sca.ScaSimpleSequenceProperty#getValues
	 * <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see gov.redhawk.model.sca.ScaSimpleSequenceProperty#getValues()
	 * @see #getScaSimpleSequenceProperty()
	 * @generated
	 */
	EAttribute getScaSimpleSequenceProperty_Values();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaStructProperty <em>Struct Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Struct Property</em>'.
	 * @see gov.redhawk.model.sca.ScaStructProperty
	 * @generated
	 */
	EClass getScaStructProperty();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaStructProperty#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see gov.redhawk.model.sca.ScaStructProperty#getFields()
	 * @see #getScaStructProperty()
	 * @generated
	 */
	EReference getScaStructProperty_Fields();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.model.sca.ScaStructProperty#getSimples
	 * <em>Simples</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Simples</em>'.
	 * @see gov.redhawk.model.sca.ScaStructProperty#getSimples()
	 * @see #getScaStructProperty()
	 * @generated
	 */
	EReference getScaStructProperty_Simples();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaUsesPort <em>Uses Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Uses Port</em>'.
	 * @see gov.redhawk.model.sca.ScaUsesPort
	 * @generated
	 */
	EClass getScaUsesPort();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see gov.redhawk.model.sca.ScaUsesPort#getConnections()
	 * @see #getScaUsesPort()
	 * @generated
	 */
	EReference getScaUsesPort_Connections();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see gov.redhawk.model.sca.ScaConnection
	 * @generated
	 */
	EClass getScaConnection();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaConnection#getData <em>Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Data</em>'.
	 * @see gov.redhawk.model.sca.ScaConnection#getData()
	 * @see #getScaConnection()
	 * @generated
	 */
	EAttribute getScaConnection_Data();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaConnection#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see gov.redhawk.model.sca.ScaConnection#getId()
	 * @see #getScaConnection()
	 * @generated
	 */
	EAttribute getScaConnection_Id();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaConnection#getPort
	 * <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Port</em>'.
	 * @see gov.redhawk.model.sca.ScaConnection#getPort()
	 * @see #getScaConnection()
	 * @generated
	 */
	EReference getScaConnection_Port();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaWaveform <em>Waveform</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Waveform</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform
	 * @generated
	 */
	EClass getScaWaveform();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaWaveform#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getComponents()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EReference getScaWaveform_Components();

	/**
	 * Returns the meta object for the reference '{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController
	 * <em>Assembly Controller</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Assembly Controller</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getAssemblyController()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EReference getScaWaveform_AssemblyController();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaWaveform#getDomMgr
	 * <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Dom Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getDomMgr()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EReference getScaWaveform_DomMgr();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier
	 * <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getIdentifier()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EAttribute getScaWaveform_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveform#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getName()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EAttribute getScaWaveform_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveform#getStarted <em>Started</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Started</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getStarted()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EAttribute getScaWaveform_Started();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveform#getProfile <em>Profile</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Profile</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveform#getProfile()
	 * @see #getScaWaveform()
	 * @generated
	 */
	EAttribute getScaWaveform_Profile();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaWaveformFactory <em>Waveform Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Waveform Factory</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory
	 * @generated
	 */
	EClass getScaWaveformFactory();

	/**
	 * Returns the meta object for the container reference '{@link gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr
	 * <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the container reference '<em>Dom Mgr</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr()
	 * @see #getScaWaveformFactory()
	 * @generated
	 */
	EReference getScaWaveformFactory_DomMgr();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier
	 * <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier()
	 * @see #getScaWaveformFactory()
	 * @generated
	 */
	EAttribute getScaWaveformFactory_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveformFactory#getName <em>Name</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getName()
	 * @see #getScaWaveformFactory()
	 * @generated
	 */
	EAttribute getScaWaveformFactory_Name();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaWaveformFactory#getProfile
	 * <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Profile</em>'.
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getProfile()
	 * @see #getScaWaveformFactory()
	 * @generated
	 */
	EAttribute getScaWaveformFactory_Profile();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To String Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>String To String Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        keyExtendedMetaData="kind='attribute' name='key'"
	 *        valueDataType="org.eclipse.emf.ecore.EString" valueRequired="true"
	 *        valueExtendedMetaData="kind='attribute' name='value'"
	 *        extendedMetaData="name='StringToStringMap' kind='empty'"
	 * @generated
	 */
	EClass getStringToStringMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToStringMap()
	 * @generated
	 */
	EAttribute getStringToStringMap_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaStructSequenceProperty
	 * <em>Struct Sequence Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Struct Sequence Property</em>'.
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty
	 * @generated
	 */
	EClass getScaStructSequenceProperty();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link gov.redhawk.model.sca.ScaStructSequenceProperty#getStructs <em>Structs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Structs</em>'.
	 * @see gov.redhawk.model.sca.ScaStructSequenceProperty#getStructs()
	 * @see #getScaStructSequenceProperty()
	 * @generated
	 */
	EReference getScaStructSequenceProperty_Structs();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.IStatusProvider <em>IStatus Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IStatus Provider</em>'.
	 * @see gov.redhawk.model.sca.IStatusProvider
	 * @generated
	 */
	EClass getIStatusProvider();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.IStatusProvider#getStatus <em>Status</em>
	 * }'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see gov.redhawk.model.sca.IStatusProvider#getStatus()
	 * @see #getIStatusProvider()
	 * @generated
	 */
	EAttribute getIStatusProvider_Status();

	/**
	 * Returns the meta object for class '{@link org.omg.CosEventChannelAdmin.EventChannel <em>Event Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Channel</em>'.
	 * @see org.omg.CosEventChannelAdmin.EventChannel
	 * @model instanceClass="org.omg.CosEventChannelAdmin.EventChannel" superTypes="mil.jpeojtrs.sca.cf.Object"
	 * @generated
	 */
	EClass getEventChannel();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.IRefreshable <em>IRefreshable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IRefreshable</em>'.
	 * @see gov.redhawk.model.sca.IRefreshable
	 * @model instanceClass="gov.redhawk.model.sca.IRefreshable"
	 * @generated
	 */
	EClass getIRefreshable();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.ScaEventChannel <em>Event Channel</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Channel</em>'.
	 * @see gov.redhawk.model.sca.ScaEventChannel
	 * @generated
	 */
	EClass getScaEventChannel();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.ScaEventChannel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see gov.redhawk.model.sca.ScaEventChannel#getName()
	 * @see #getScaEventChannel()
	 * @generated
	 */
	EAttribute getScaEventChannel_Name();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Object Map</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Object Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.eclipse.emf.ecore.EObject" valueContainment="true" valueResolveProxies="true"
	 * @generated
	 */
	EClass getStringToObjectMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObjectMap()
	 * @generated
	 */
	EAttribute getStringToObjectMap_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToObjectMap()
	 * @generated
	 */
	EReference getStringToObjectMap_Value();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.model.sca.WaveformsContainer <em>Waveforms Container</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Waveforms Container</em>'.
	 * @see gov.redhawk.model.sca.WaveformsContainer
	 * @generated
	 */
	EClass getWaveformsContainer();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.model.sca.WaveformsContainer#getSubContainers
	 * <em>Sub Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Sub Containers</em>'.
	 * @see gov.redhawk.model.sca.WaveformsContainer#getSubContainers()
	 * @see #getWaveformsContainer()
	 * @generated
	 */
	EReference getWaveformsContainer_SubContainers();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.model.sca.WaveformsContainer#getWaveforms
	 * <em>Waveforms</em>}'.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Waveforms</em>'.
	 * @see gov.redhawk.model.sca.WaveformsContainer#getWaveforms()
	 * @see #getWaveformsContainer()
	 * @generated
	 */
	EReference getWaveformsContainer_Waveforms();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.model.sca.WaveformsContainer#getContainerName
	 * <em>Container Name</em>}'.
	 * <!-- begin-user-doc -->
	 * @since 20.2
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Container Name</em>'.
	 * @see gov.redhawk.model.sca.WaveformsContainer#getContainerName()
	 * @see #getWaveformsContainer()
	 * @generated
	 */
	EAttribute getWaveformsContainer_ContainerName();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.model.sca.DomainConnectionState
	 * <em>Domain Connection State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Domain Connection State</em>'.
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @generated
	 */
	EEnum getDomainConnectionState();

	/**
	 * Returns the meta object for enum '{@link gov.redhawk.model.sca.RefreshDepth <em>Refresh Depth</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Refresh Depth</em>'.
	 * @see gov.redhawk.model.sca.RefreshDepth
	 * @generated
	 */
	EEnum getRefreshDepth();

	/**
	 * Returns the meta object for data type '{@link CF.DevicePackage.AdminType <em>Admin Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Admin Type</em>'.
	 * @see CF.DevicePackage.AdminType
	 * @model instanceClass="CF.DevicePackage.AdminType"
	 *        extendedMetaData="name='AdminType' enumeration='LOCKED SHUTTING%20DOWN UNLOCKED'"
	 * @generated
	 */
	EDataType getAdminType();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.DomainConnectionException
	 * <em>Domain Connection Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Domain Connection Exception</em>'.
	 * @see gov.redhawk.model.sca.DomainConnectionException
	 * @model instanceClass="gov.redhawk.model.sca.DomainConnectionException"
	 * @generated
	 */
	EDataType getDomainConnectionException();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.DomainConnectionState
	 * <em>Domain Connection State Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Domain Connection State Object</em>'.
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @model instanceClass="gov.redhawk.model.sca.DomainConnectionState"
	 *        extendedMetaData="name='DomainConnectionState:Object' baseType='DomainConnectionState'"
	 * @generated
	 */
	EDataType getDomainConnectionStateObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.filesystem.IFileStore <em>IFile Store</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IFile Store</em>'.
	 * @see org.eclipse.core.filesystem.IFileStore
	 * @model instanceClass="org.eclipse.core.filesystem.IFileStore"
	 *        extendedMetaData="name='IFileStore'"
	 * @generated
	 */
	EDataType getIFileStore();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor
	 * <em>IProgress Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor" serializeable="false"
	 *        extendedMetaData="name='IProgressMonitor'"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.services.IScaDataProvider
	 * <em>ISca Data Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>ISca Data Provider</em>'.
	 * @see gov.redhawk.model.sca.services.IScaDataProvider
	 * @model instanceClass="gov.redhawk.model.sca.services.IScaDataProvider" serializeable="false"
	 *        extendedMetaData="name='dataProvider'"
	 * @generated
	 */
	EDataType getIScaDataProvider();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.services.IScaDataProviderService
	 * <em>ISca Data Provider Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>ISca Data Provider Service</em>'.
	 * @see gov.redhawk.model.sca.services.IScaDataProviderService
	 * @model instanceClass="gov.redhawk.model.sca.services.IScaDataProviderService" serializeable="false"
	 *        extendedMetaData="name='dataProviderService'"
	 * @generated
	 */
	EDataType getIScaDataProviderService();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus" serializeable="false"
	 *        extendedMetaData="name='IStatus'"
	 * @generated
	 */
	EDataType getIStatus();

	/**
	 * Returns the meta object for data type '{@link org.omg.CORBA.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see org.omg.CORBA.Object
	 * @model instanceClass="org.omg.CORBA.Object"
	 *        extendedMetaData="name='Object'"
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the meta object for data type '<em>Object Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Object Array</em>'.
	 * @model instanceClass="java.lang.Object[]" serializeable="false"
	 *        extendedMetaData="name='ObjectArray'"
	 * @generated
	 */
	EDataType getObjectArray();

	/**
	 * Returns the meta object for data type '{@link CF.DevicePackage.OperationalType <em>Operational Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Operational Type</em>'.
	 * @see CF.DevicePackage.OperationalType
	 * @model instanceClass="CF.DevicePackage.OperationalType"
	 *        extendedMetaData="name='OperationalType' enumeration='ENABLED DISABLED'"
	 * @generated
	 */
	EDataType getOperationalType();

	/**
	 * Returns the meta object for data type '{@link gov.redhawk.model.sca.RefreshDepth <em>Refresh Depth Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Refresh Depth Object</em>'.
	 * @see gov.redhawk.model.sca.RefreshDepth
	 * @model instanceClass="gov.redhawk.model.sca.RefreshDepth"
	 *        extendedMetaData="name='RefreshDepth:Object' baseType='RefreshDepth'"
	 * @generated
	 */
	EDataType getRefreshDepthObject();

	/**
	 * Returns the meta object for data type '{@link org.omg.PortableServer.POA <em>POA</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>POA</em>'.
	 * @see org.omg.PortableServer.POA
	 * @model instanceClass="org.omg.PortableServer.POA" serializeable="false"
	 * @generated
	 */
	EDataType getPOA();

	/**
	 * Returns the meta object for data type '{@link java.net.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see java.net.URI
	 * @model instanceClass="java.net.URI"
	 *        extendedMetaData="name='URI'"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link CF.DevicePackage.UsageType <em>Usage Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Usage Type</em>'.
	 * @see CF.DevicePackage.UsageType
	 * @model instanceClass="CF.DevicePackage.UsageType"
	 *        extendedMetaData="name='UsageType' enumeration='ACTIVE BUSY IDLE'"
	 * @generated
	 */
	EDataType getUsageType();

	/**
	 * Returns the meta object for data type '<em>Data Type Array</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Data Type Array</em>'.
	 * @model instanceClass="CF.DataType[]" serializeable="false"
	 * @generated
	 */
	EDataType getDataTypeArray();

	/**
	 * Returns the meta object for data type '{@link org.omg.CORBA.Any <em>Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for data type '<em>Any</em>'.
	 * @see org.omg.CORBA.Any
	 * @model instanceClass="org.omg.CORBA.Any" serializeable="false"
	 * @generated
	 */
	EDataType getAny();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ScaFactory getScaFactory();

	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.CorbaObjWrapperImpl
		 * <em>Corba Obj Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.CorbaObjWrapperImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getCorbaObjWrapper()
		 * @generated
		 */
		EClass CORBA_OBJ_WRAPPER = eINSTANCE.getCorbaObjWrapper();
		/**
		 * The meta object literal for the '<em><b>Ior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CORBA_OBJ_WRAPPER__IOR = eINSTANCE.getCorbaObjWrapper_Ior();
		/**
		 * The meta object literal for the '<em><b>Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CORBA_OBJ_WRAPPER__OBJ = eINSTANCE.getCorbaObjWrapper_Obj();
		/**
		 * The meta object literal for the '<em><b>Corba Obj</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute CORBA_OBJ_WRAPPER__CORBA_OBJ = eINSTANCE.getCorbaObjWrapper_CorbaObj();
		/**
		 * The meta object literal for the '<em><b>Feature Data</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORBA_OBJ_WRAPPER__FEATURE_DATA = eINSTANCE.getCorbaObjWrapper_FeatureData();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.DataProviderObjectImpl
		 * <em>Data Provider Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.DataProviderObjectImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDataProviderObject()
		 * @generated
		 */
		EClass DATA_PROVIDER_OBJECT = eINSTANCE.getDataProviderObject();
		/**
		 * The meta object literal for the '<em><b>Data Providers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DATA_PROVIDER_OBJECT__DATA_PROVIDERS = eINSTANCE.getDataProviderObject_DataProviders();
		/**
		 * The meta object literal for the '<em><b>Data Providers Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED = eINSTANCE.getDataProviderObject_DataProvidersEnabled();
		/**
		 * The meta object literal for the '<em><b>Enabled Data Providers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_PROVIDER_OBJECT__ENABLED_DATA_PROVIDERS = eINSTANCE.getDataProviderObject_EnabledDataProviders();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.IDisposable <em>IDisposable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.IDisposable
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIDisposable()
		 * @generated
		 */
		EClass IDISPOSABLE = eINSTANCE.getIDisposable();
		/**
		 * The meta object literal for the '<em><b>Disposed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IDISPOSABLE__DISPOSED = eINSTANCE.getIDisposable_Disposed();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.ProfileObjectWrapper
		 * <em>Profile Object Wrapper</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.ProfileObjectWrapper
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getProfileObjectWrapper()
		 * @generated
		 */
		EClass PROFILE_OBJECT_WRAPPER = eINSTANCE.getProfileObjectWrapper();
		/**
		 * The meta object literal for the '<em><b>Profile URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROFILE_OBJECT_WRAPPER__PROFILE_URI = eINSTANCE.getProfileObjectWrapper_ProfileURI();
		/**
		 * The meta object literal for the '<em><b>Profile Obj</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROFILE_OBJECT_WRAPPER__PROFILE_OBJ = eINSTANCE.getProfileObjectWrapper_ProfileObj();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.PropertiesImpl <em>Properties</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.PropertiesImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();
		/**
		 * The meta object literal for the '<em><b>Property</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTIES__PROPERTY = eINSTANCE.getProperties_Property();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl
		 * <em>Abstract Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaAbstractComponentImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaAbstractComponent()
		 * @generated
		 */
		EClass SCA_ABSTRACT_COMPONENT = eINSTANCE.getScaAbstractComponent();
		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_COMPONENT__IDENTIFIER = eINSTANCE.getScaAbstractComponent_Identifier();
		/**
		 * The meta object literal for the '<em><b>Started</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_COMPONENT__STARTED = eINSTANCE.getScaAbstractComponent_Started();
		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_COMPONENT__PROFILE = eINSTANCE.getScaAbstractComponent_Profile();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl
		 * <em>Property Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaPropertyContainerImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPropertyContainer()
		 * @generated
		 */
		EClass SCA_PROPERTY_CONTAINER = eINSTANCE.getScaPropertyContainer();
		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_PROPERTY_CONTAINER__PROPERTIES = eINSTANCE.getScaPropertyContainer_Properties();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.ScaPortContainer <em>Port Container</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.ScaPortContainer
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPortContainer()
		 * @generated
		 */
		EClass SCA_PORT_CONTAINER = eINSTANCE.getScaPortContainer();
		/**
		 * The meta object literal for the '<em><b>Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_PORT_CONTAINER__PORTS = eINSTANCE.getScaPortContainer_Ports();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl
		 * <em>Abstract Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaAbstractProperty()
		 * @generated
		 */
		EClass SCA_ABSTRACT_PROPERTY = eINSTANCE.getScaAbstractProperty();
		/**
		 * The meta object literal for the '<em><b>Definition</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_ABSTRACT_PROPERTY__DEFINITION = eINSTANCE.getScaAbstractProperty_Definition();
		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_PROPERTY__DESCRIPTION = eINSTANCE.getScaAbstractProperty_Description();
		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_PROPERTY__ID = eINSTANCE.getScaAbstractProperty_Id();
		/**
		 * The meta object literal for the '<em><b>Mode</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_PROPERTY__MODE = eINSTANCE.getScaAbstractProperty_Mode();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_PROPERTY__NAME = eINSTANCE.getScaAbstractProperty_Name();
		/**
		 * The meta object literal for the '<em><b>Ignore Remote Set</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET = eINSTANCE.getScaAbstractProperty_IgnoreRemoteSet();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaComponentImpl <em>Component</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaComponentImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaComponent()
		 * @generated
		 */
		EClass SCA_COMPONENT = eINSTANCE.getScaComponent();
		/**
		 * The meta object literal for the '<em><b>Component Instantiation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_COMPONENT__COMPONENT_INSTANTIATION = eINSTANCE.getScaComponent_ComponentInstantiation();
		/**
		 * The meta object literal for the '<em><b>Devices</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_COMPONENT__DEVICES = eINSTANCE.getScaComponent_Devices();
		/**
		 * The meta object literal for the '<em><b>Instantiation Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_COMPONENT__INSTANTIATION_IDENTIFIER = eINSTANCE.getScaComponent_InstantiationIdentifier();
		/**
		 * The meta object literal for the '<em><b>Waveform</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_COMPONENT__WAVEFORM = eINSTANCE.getScaComponent_Waveform();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_COMPONENT__NAME = eINSTANCE.getScaComponent_Name();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDeviceImpl <em>Device</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDeviceImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDevice()
		 * @generated
		 */
		EClass SCA_DEVICE = eINSTANCE.getScaDevice();
		/**
		 * The meta object literal for the '<em><b>Child Devices</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE__CHILD_DEVICES = eINSTANCE.getScaDevice_ChildDevices();
		/**
		 * The meta object literal for the '<em><b>Admin State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE__ADMIN_STATE = eINSTANCE.getScaDevice_AdminState();
		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE__LABEL = eINSTANCE.getScaDevice_Label();
		/**
		 * The meta object literal for the '<em><b>Operational State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE__OPERATIONAL_STATE = eINSTANCE.getScaDevice_OperationalState();
		/**
		 * The meta object literal for the '<em><b>Usage State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE__USAGE_STATE = eINSTANCE.getScaDevice_UsageState();
		/**
		 * The meta object literal for the '<em><b>Parent Device</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE__PARENT_DEVICE = eINSTANCE.getScaDevice_ParentDevice();
		/**
		 * The meta object literal for the '<em><b>Dev Mgr</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE__DEV_MGR = eINSTANCE.getScaDevice_DevMgr();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl
		 * <em>Device Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDeviceManagerImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDeviceManager()
		 * @generated
		 */
		EClass SCA_DEVICE_MANAGER = eINSTANCE.getScaDeviceManager();
		/**
		 * The meta object literal for the '<em><b>Devices</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE_MANAGER__DEVICES = eINSTANCE.getScaDeviceManager_Devices();
		/**
		 * The meta object literal for the '<em><b>Root Devices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__ROOT_DEVICES = eINSTANCE.getScaDeviceManager_RootDevices();
		/**
		 * The meta object literal for the '<em><b>Child Devices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__CHILD_DEVICES = eINSTANCE.getScaDeviceManager_ChildDevices();
		/**
		 * The meta object literal for the '<em><b>All Devices</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__ALL_DEVICES = eINSTANCE.getScaDeviceManager_AllDevices();
		/**
		 * The meta object literal for the '<em><b>File System</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__FILE_SYSTEM = eINSTANCE.getScaDeviceManager_FileSystem();
		/**
		 * The meta object literal for the '<em><b>Dom Mgr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__DOM_MGR = eINSTANCE.getScaDeviceManager_DomMgr();
		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE_MANAGER__IDENTIFIER = eINSTANCE.getScaDeviceManager_Identifier();
		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE_MANAGER__LABEL = eINSTANCE.getScaDeviceManager_Label();
		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER__SERVICES = eINSTANCE.getScaDeviceManager_Services();
		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DEVICE_MANAGER__PROFILE = eINSTANCE.getScaDeviceManager_Profile();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaServiceImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaService()
		 * @generated
		 */
		EClass SCA_SERVICE = eINSTANCE.getScaService();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_SERVICE__NAME = eINSTANCE.getScaService_Name();
		/**
		 * The meta object literal for the '<em><b>Dev Mgr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 18.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCA_SERVICE__DEV_MGR = eINSTANCE.getScaService_DevMgr();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDeviceManagerFileSystemImpl
		 * <em>Device Manager File System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDeviceManagerFileSystemImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDeviceManagerFileSystem()
		 * @generated
		 */
		EClass SCA_DEVICE_MANAGER_FILE_SYSTEM = eINSTANCE.getScaDeviceManagerFileSystem();
		/**
		 * The meta object literal for the '<em><b>Device Manager</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER = eINSTANCE.getScaDeviceManagerFileSystem_DeviceManager();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDocumentRootImpl <em>Document Root</em>
		 * }' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDocumentRootImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDocumentRoot()
		 * @generated
		 */
		EClass SCA_DOCUMENT_ROOT = eINSTANCE.getScaDocumentRoot();
		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOCUMENT_ROOT__MIXED = eINSTANCE.getScaDocumentRoot_Mixed();
		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getScaDocumentRoot_XMLNSPrefixMap();
		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getScaDocumentRoot_XSISchemaLocation();
		/**
		 * The meta object literal for the '<em><b>Domain Manager Registry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOCUMENT_ROOT__DOMAIN_MANAGER_REGISTRY = eINSTANCE.getScaDocumentRoot_DomainManagerRegistry();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl
		 * <em>Domain Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDomainManagerImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManager()
		 * @generated
		 */
		EClass SCA_DOMAIN_MANAGER = eINSTANCE.getScaDomainManager();
		/**
		 * The meta object literal for the '<em><b>Waveform Factories</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES = eINSTANCE.getScaDomainManager_WaveformFactories();
		/**
		 * The meta object literal for the '<em><b>Waveforms</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__WAVEFORMS = eINSTANCE.getScaDomainManager_Waveforms();
		/**
		 * The meta object literal for the '<em><b>Device Managers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__DEVICE_MANAGERS = eINSTANCE.getScaDomainManager_DeviceManagers();
		/**
		 * The meta object literal for the '<em><b>File Manager</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__FILE_MANAGER = eINSTANCE.getScaDomainManager_FileManager();
		/**
		 * The meta object literal for the '<em><b>Connection Properties Container</b></em>' containment reference
		 * feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER = eINSTANCE.getScaDomainManager_ConnectionPropertiesContainer();
		/**
		 * The meta object literal for the '<em><b>Connection Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES = eINSTANCE.getScaDomainManager_ConnectionProperties();
		/**
		 * The meta object literal for the '<em><b>Auto Connect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__AUTO_CONNECT = eINSTANCE.getScaDomainManager_AutoConnect();
		/**
		 * The meta object literal for the '<em><b>Connected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__CONNECTED = eINSTANCE.getScaDomainManager_Connected();
		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__IDENTIFIER = eINSTANCE.getScaDomainManager_Identifier();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__NAME = eINSTANCE.getScaDomainManager_Name();
		/**
		 * The meta object literal for the '<em><b>Root Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__ROOT_CONTEXT = eINSTANCE.getScaDomainManager_RootContext();
		/**
		 * The meta object literal for the '<em><b>State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__STATE = eINSTANCE.getScaDomainManager_State();
		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__PROFILE = eINSTANCE.getScaDomainManager_Profile();
		/**
		 * The meta object literal for the '<em><b>Event Channels</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER__EVENT_CHANNELS = eINSTANCE.getScaDomainManager_EventChannels();
		/**
		 * The meta object literal for the '<em><b>Local Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 20.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCA_DOMAIN_MANAGER__LOCAL_NAME = eINSTANCE.getScaDomainManager_LocalName();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerFileSystemImpl
		 * <em>Domain Manager File System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDomainManagerFileSystemImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManagerFileSystem()
		 * @generated
		 */
		EClass SCA_DOMAIN_MANAGER_FILE_SYSTEM = eINSTANCE.getScaDomainManagerFileSystem();
		/**
		 * The meta object literal for the '<em><b>Dom Mgr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR = eINSTANCE.getScaDomainManagerFileSystem_DomMgr();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl
		 * <em>Domain Manager Registry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaDomainManagerRegistryImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaDomainManagerRegistry()
		 * @generated
		 */
		EClass SCA_DOMAIN_MANAGER_REGISTRY = eINSTANCE.getScaDomainManagerRegistry();
		/**
		 * The meta object literal for the '<em><b>Domains</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS = eINSTANCE.getScaDomainManagerRegistry_Domains();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaExecutableDeviceImpl
		 * <em>Executable Device</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaExecutableDeviceImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaExecutableDevice()
		 * @generated
		 */
		EClass SCA_EXECUTABLE_DEVICE = eINSTANCE.getScaExecutableDevice();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaFileManagerImpl <em>File Manager</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaFileManagerImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileManager()
		 * @generated
		 */
		EClass SCA_FILE_MANAGER = eINSTANCE.getScaFileManager();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl <em>File Store</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaFileStoreImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileStore()
		 * @generated
		 */
		EClass SCA_FILE_STORE = eINSTANCE.getScaFileStore();
		/**
		 * The meta object literal for the '<em><b>File Store</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_FILE_STORE__FILE_STORE = eINSTANCE.getScaFileStore_FileStore();
		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_FILE_STORE__CHILDREN = eINSTANCE.getScaFileStore_Children();
		/**
		 * The meta object literal for the '<em><b>Image Desc</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_FILE_STORE__IMAGE_DESC = eINSTANCE.getScaFileStore_ImageDesc();
		/**
		 * The meta object literal for the '<em><b>Directory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_FILE_STORE__DIRECTORY = eINSTANCE.getScaFileStore_Directory();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_FILE_STORE__NAME = eINSTANCE.getScaFileStore_Name();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl <em>File System</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaFileSystemImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaFileSystem()
		 * @generated
		 */
		EClass SCA_FILE_SYSTEM = eINSTANCE.getScaFileSystem();
		/**
		 * The meta object literal for the '<em><b>File System URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_FILE_SYSTEM__FILE_SYSTEM_URI = eINSTANCE.getScaFileSystem_FileSystemURI();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaLoadableDeviceImpl
		 * <em>Loadable Device</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaLoadableDeviceImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaLoadableDevice()
		 * @generated
		 */
		EClass SCA_LOADABLE_DEVICE = eINSTANCE.getScaLoadableDevice();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaPortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaPortImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaPort()
		 * @generated
		 */
		EClass SCA_PORT = eINSTANCE.getScaPort();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_PORT__NAME = eINSTANCE.getScaPort_Name();
		/**
		 * The meta object literal for the '<em><b>Profile Obj</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_PORT__PROFILE_OBJ = eINSTANCE.getScaPort_ProfileObj();
		/**
		 * The meta object literal for the '<em><b>Repid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_PORT__REPID = eINSTANCE.getScaPort_Repid();
		/**
		 * The meta object literal for the '<em><b>Port Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_PORT__PORT_CONTAINER = eINSTANCE.getScaPort_PortContainer();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaProvidesPortImpl <em>Provides Port</em>
		 * }' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaProvidesPortImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaProvidesPort()
		 * @generated
		 */
		EClass SCA_PROVIDES_PORT = eINSTANCE.getScaProvidesPort();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaSimplePropertyImpl
		 * <em>Simple Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaSimplePropertyImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaSimpleProperty()
		 * @generated
		 */
		EClass SCA_SIMPLE_PROPERTY = eINSTANCE.getScaSimpleProperty();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_SIMPLE_PROPERTY__VALUE = eINSTANCE.getScaSimpleProperty_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl
		 * <em>Simple Sequence Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaSimpleSequenceProperty()
		 * @generated
		 */
		EClass SCA_SIMPLE_SEQUENCE_PROPERTY = eINSTANCE.getScaSimpleSequenceProperty();
		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES = eINSTANCE.getScaSimpleSequenceProperty_Values();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl
		 * <em>Struct Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaStructPropertyImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaStructProperty()
		 * @generated
		 */
		EClass SCA_STRUCT_PROPERTY = eINSTANCE.getScaStructProperty();
		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 20.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCA_STRUCT_PROPERTY__FIELDS = eINSTANCE.getScaStructProperty_Fields();
		/**
		 * The meta object literal for the '<em><b>Simples</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_STRUCT_PROPERTY__SIMPLES = eINSTANCE.getScaStructProperty_Simples();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaUsesPortImpl <em>Uses Port</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaUsesPortImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaUsesPort()
		 * @generated
		 */
		EClass SCA_USES_PORT = eINSTANCE.getScaUsesPort();
		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_USES_PORT__CONNECTIONS = eINSTANCE.getScaUsesPort_Connections();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaConnectionImpl <em>Connection</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaConnectionImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaConnection()
		 * @generated
		 */
		EClass SCA_CONNECTION = eINSTANCE.getScaConnection();
		/**
		 * The meta object literal for the '<em><b>Data</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_CONNECTION__DATA = eINSTANCE.getScaConnection_Data();
		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_CONNECTION__ID = eINSTANCE.getScaConnection_Id();
		/**
		 * The meta object literal for the '<em><b>Port</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_CONNECTION__PORT = eINSTANCE.getScaConnection_Port();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaWaveformImpl <em>Waveform</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaWaveformImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaWaveform()
		 * @generated
		 */
		EClass SCA_WAVEFORM = eINSTANCE.getScaWaveform();
		/**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_WAVEFORM__COMPONENTS = eINSTANCE.getScaWaveform_Components();
		/**
		 * The meta object literal for the '<em><b>Assembly Controller</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_WAVEFORM__ASSEMBLY_CONTROLLER = eINSTANCE.getScaWaveform_AssemblyController();
		/**
		 * The meta object literal for the '<em><b>Dom Mgr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_WAVEFORM__DOM_MGR = eINSTANCE.getScaWaveform_DomMgr();
		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM__IDENTIFIER = eINSTANCE.getScaWaveform_Identifier();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM__NAME = eINSTANCE.getScaWaveform_Name();
		/**
		 * The meta object literal for the '<em><b>Started</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM__STARTED = eINSTANCE.getScaWaveform_Started();
		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM__PROFILE = eINSTANCE.getScaWaveform_Profile();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl
		 * <em>Waveform Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaWaveformFactory()
		 * @generated
		 */
		EClass SCA_WAVEFORM_FACTORY = eINSTANCE.getScaWaveformFactory();
		/**
		 * The meta object literal for the '<em><b>Dom Mgr</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_WAVEFORM_FACTORY__DOM_MGR = eINSTANCE.getScaWaveformFactory_DomMgr();
		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM_FACTORY__IDENTIFIER = eINSTANCE.getScaWaveformFactory_Identifier();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM_FACTORY__NAME = eINSTANCE.getScaWaveformFactory_Name();
		/**
		 * The meta object literal for the '<em><b>Profile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SCA_WAVEFORM_FACTORY__PROFILE = eINSTANCE.getScaWaveformFactory_Profile();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.StringToStringMapImpl
		 * <em>String To String Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.StringToStringMapImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getStringToStringMap()
		 * @generated
		 */
		EClass STRING_TO_STRING_MAP = eINSTANCE.getStringToStringMap();
		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__KEY = eINSTANCE.getStringToStringMap_Key();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute STRING_TO_STRING_MAP__VALUE = eINSTANCE.getStringToStringMap_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl
		 * <em>Struct Sequence Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaStructSequenceProperty()
		 * @generated
		 */
		EClass SCA_STRUCT_SEQUENCE_PROPERTY = eINSTANCE.getScaStructSequenceProperty();
		/**
		 * The meta object literal for the '<em><b>Structs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS = eINSTANCE.getScaStructSequenceProperty_Structs();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.IStatusProviderImpl
		 * <em>IStatus Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.IStatusProviderImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIStatusProvider()
		 * @generated
		 */
		EClass ISTATUS_PROVIDER = eINSTANCE.getIStatusProvider();
		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute ISTATUS_PROVIDER__STATUS = eINSTANCE.getIStatusProvider_Status();
		/**
		 * The meta object literal for the '{@link org.omg.CosEventChannelAdmin.EventChannel <em>Event Channel</em>}'
		 * class.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @see org.omg.CosEventChannelAdmin.EventChannel
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getEventChannel()
		 * @generated
		 */
		EClass EVENT_CHANNEL = eINSTANCE.getEventChannel();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.IRefreshable <em>IRefreshable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.IRefreshable
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIRefreshable()
		 * @generated
		 */
		EClass IREFRESHABLE = eINSTANCE.getIRefreshable();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.ScaEventChannelImpl <em>Event Channel</em>
		 * }' class.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @see gov.redhawk.model.sca.impl.ScaEventChannelImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getScaEventChannel()
		 * @generated
		 */
		EClass SCA_EVENT_CHANNEL = eINSTANCE.getScaEventChannel();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCA_EVENT_CHANNEL__NAME = eINSTANCE.getScaEventChannel_Name();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.StringToObjectMapImpl
		 * <em>String To Object Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @see gov.redhawk.model.sca.impl.StringToObjectMapImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getStringToObjectMap()
		 * @generated
		 */
		EClass STRING_TO_OBJECT_MAP = eINSTANCE.getStringToObjectMap();
		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_OBJECT_MAP__KEY = eINSTANCE.getStringToObjectMap_Key();
		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * 
		 * @since 19.0
		 *        <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRING_TO_OBJECT_MAP__VALUE = eINSTANCE.getStringToObjectMap_Value();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.impl.WaveformsContainerImpl
		 * <em>Waveforms Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * @since 20.2
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.WaveformsContainerImpl
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getWaveformsContainer()
		 * @generated
		 */
		EClass WAVEFORMS_CONTAINER = eINSTANCE.getWaveformsContainer();
		/**
		 * The meta object literal for the '<em><b>Sub Containers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * @since 20.2
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WAVEFORMS_CONTAINER__SUB_CONTAINERS = eINSTANCE.getWaveformsContainer_SubContainers();
		/**
		 * The meta object literal for the '<em><b>Waveforms</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * @since 20.2
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference WAVEFORMS_CONTAINER__WAVEFORMS = eINSTANCE.getWaveformsContainer_Waveforms();
		/**
		 * The meta object literal for the '<em><b>Container Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * @since 20.2
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute WAVEFORMS_CONTAINER__CONTAINER_NAME = eINSTANCE.getWaveformsContainer_ContainerName();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.DomainConnectionState
		 * <em>Domain Connection State</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.DomainConnectionState
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionState()
		 * @generated
		 */
		EEnum DOMAIN_CONNECTION_STATE = eINSTANCE.getDomainConnectionState();
		/**
		 * The meta object literal for the '{@link gov.redhawk.model.sca.RefreshDepth <em>Refresh Depth</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.RefreshDepth
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getRefreshDepth()
		 * @generated
		 */
		EEnum REFRESH_DEPTH = eINSTANCE.getRefreshDepth();
		/**
		 * The meta object literal for the '<em>Admin Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see CF.DevicePackage.AdminType
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getAdminType()
		 * @generated
		 */
		EDataType ADMIN_TYPE = eINSTANCE.getAdminType();
		/**
		 * The meta object literal for the '<em>Domain Connection Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.DomainConnectionException
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionException()
		 * @generated
		 */
		EDataType DOMAIN_CONNECTION_EXCEPTION = eINSTANCE.getDomainConnectionException();
		/**
		 * The meta object literal for the '<em>Domain Connection State Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.DomainConnectionState
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDomainConnectionStateObject()
		 * @generated
		 */
		EDataType DOMAIN_CONNECTION_STATE_OBJECT = eINSTANCE.getDomainConnectionStateObject();
		/**
		 * The meta object literal for the '<em>IFile Store</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.filesystem.IFileStore
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIFileStore()
		 * @generated
		 */
		EDataType IFILE_STORE = eINSTANCE.getIFileStore();
		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();
		/**
		 * The meta object literal for the '<em>ISca Data Provider</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.services.IScaDataProvider
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIScaDataProvider()
		 * @generated
		 */
		EDataType ISCA_DATA_PROVIDER = eINSTANCE.getIScaDataProvider();
		/**
		 * The meta object literal for the '<em>ISca Data Provider Service</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.services.IScaDataProviderService
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIScaDataProviderService()
		 * @generated
		 */
		EDataType ISCA_DATA_PROVIDER_SERVICE = eINSTANCE.getIScaDataProviderService();
		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.core.runtime.IStatus
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();
		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.omg.CORBA.Object
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();
		/**
		 * The meta object literal for the '<em>Object Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getObjectArray()
		 * @generated
		 */
		EDataType OBJECT_ARRAY = eINSTANCE.getObjectArray();
		/**
		 * The meta object literal for the '<em>Operational Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see CF.DevicePackage.OperationalType
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getOperationalType()
		 * @generated
		 */
		EDataType OPERATIONAL_TYPE = eINSTANCE.getOperationalType();
		/**
		 * The meta object literal for the '<em>Refresh Depth Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.RefreshDepth
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getRefreshDepthObject()
		 * @generated
		 */
		EDataType REFRESH_DEPTH_OBJECT = eINSTANCE.getRefreshDepthObject();
		/**
		 * The meta object literal for the '<em>POA</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.omg.PortableServer.POA
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getPOA()
		 * @generated
		 */
		EDataType POA = eINSTANCE.getPOA();
		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see java.net.URI
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();
		/**
		 * The meta object literal for the '<em>Usage Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see CF.DevicePackage.UsageType
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getUsageType()
		 * @generated
		 */
		EDataType USAGE_TYPE = eINSTANCE.getUsageType();
		/**
		 * The meta object literal for the '<em>Data Type Array</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getDataTypeArray()
		 * @generated
		 */
		EDataType DATA_TYPE_ARRAY = eINSTANCE.getDataTypeArray();
		/**
		 * The meta object literal for the '<em>Any</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.omg.CORBA.Any
		 * @see gov.redhawk.model.sca.impl.ScaPackageImpl#getAny()
		 * @generated
		 */
		EDataType ANY = eINSTANCE.getAny();

	}

} // ScaPackage
