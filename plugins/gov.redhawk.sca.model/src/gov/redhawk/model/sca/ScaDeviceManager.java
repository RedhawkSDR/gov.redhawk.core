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

import mil.jpeojtrs.sca.dcd.DeviceConfiguration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.FeatureMap;

import CF.DeviceManager;
import CF.DeviceManagerOperations;
import CF.InvalidObjectReference;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Device Manager</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getDevices <em>Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getRootDevices <em>Root Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getChildDevices <em>Child Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices <em>All Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem <em>File System</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getDomMgr <em>Dom Mgr</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier <em>Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel <em>Label</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getServices <em>Services</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager()
 * @model superTypes=
 * "gov.redhawk.model.sca.ScaPropertyContainer<mil.jpeojtrs.sca.cf.DeviceManager, mil.jpeojtrs.sca.dcd.DeviceConfiguration> mil.jpeojtrs.sca.cf.DeviceManagerOperations gov.redhawk.model.sca.ScaPortContainer"
 * extendedMetaData="name='ScaDeviceManager' kind='elementOnly'"
 * @generated
 */
public interface ScaDeviceManager extends ScaPropertyContainer<DeviceManager, DeviceConfiguration>, DeviceManagerOperations, ScaPortContainer {

	/**
	 * Returns the value of the '<em><b>Devices</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Devices</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Devices</em>' attribute list.
	 * @see #isSetDevices()
	 * @see #unsetDevices()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_Devices()
	 * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 * transient="true"
	 * extendedMetaData="kind='group' name='devices:1'"
	 * @generated
	 */
	FeatureMap getDevices();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getDevices <em>Devices</em>}' attribute
	 * list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDevices()
	 * @see #getDevices()
	 * @generated
	 */
	void unsetDevices();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getDevices <em>Devices</em>}'
	 * attribute list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Devices</em>' attribute list is set.
	 * @see #unsetDevices()
	 * @see #getDevices()
	 * @generated
	 */
	boolean isSetDevices();

	/**
	 * Returns the value of the '<em><b>Root Devices</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDevice}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Devices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Devices</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_RootDevices()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 * extendedMetaData="group='#devices:1'"
	 * @generated
	 */
	EList<ScaDevice< ? >> getRootDevices();

	/**
	 * Returns the value of the '<em><b>Child Devices</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDevice}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Devices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Devices</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_ChildDevices()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 * extendedMetaData="group='#devices:1'"
	 * @generated
	 */
	EList<ScaDevice< ? >> getChildDevices();

	/**
	 * Returns the value of the '<em><b>All Devices</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDevice}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Devices</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Devices</em>' containment reference list.
	 * @see #isSetAllDevices()
	 * @see #unsetAllDevices()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_AllDevices()
	 * @model containment="true" resolveProxies="true" unsettable="true" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList<ScaDevice< ? >> getAllDevices();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices <em>All Devices</em>}'
	 * containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAllDevices()
	 * @see #getAllDevices()
	 * @generated
	 */
	void unsetAllDevices();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices <em>All
	 * Devices</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>All Devices</em>' containment reference list is set.
	 * @see #unsetAllDevices()
	 * @see #getAllDevices()
	 * @generated
	 */
	boolean isSetAllDevices();

	/**
	 * Returns the value of the '<em><b>File System</b></em>' containment reference.
	 * It is bidirectional and its opposite is '
	 * {@link gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager <em>Device Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File System</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File System</em>' containment reference.
	 * @see #isSetFileSystem()
	 * @see #unsetFileSystem()
	 * @see #setFileSystem(ScaDeviceManagerFileSystem)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_FileSystem()
	 * @see gov.redhawk.model.sca.ScaDeviceManagerFileSystem#getDeviceManager
	 * @model opposite="deviceManager" containment="true" unsettable="true" transient="true"
	 * extendedMetaData="kind='element' name='fileSystem'"
	 * @generated
	 */
	ScaDeviceManagerFileSystem getFileSystem();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem <em>File System</em>}'
	 * containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File System</em>' containment reference.
	 * @see #isSetFileSystem()
	 * @see #unsetFileSystem()
	 * @see #getFileSystem()
	 * @generated
	 */
	void setFileSystem(ScaDeviceManagerFileSystem value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem <em>File System</em>}'
	 * containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFileSystem()
	 * @see #getFileSystem()
	 * @see #setFileSystem(ScaDeviceManagerFileSystem)
	 * @generated
	 */
	void unsetFileSystem();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem <em>File
	 * System</em>}' containment reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>File System</em>' containment reference is set.
	 * @see #unsetFileSystem()
	 * @see #getFileSystem()
	 * @see #setFileSystem(ScaDeviceManagerFileSystem)
	 * @generated
	 */
	boolean isSetFileSystem();

	/**
	 * Returns the value of the '<em><b>Dom Mgr</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers
	 * <em>Device Managers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dom Mgr</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dom Mgr</em>' container reference.
	 * @see #setDomMgr(ScaDomainManager)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_DomMgr()
	 * @see gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers
	 * @model opposite="deviceManagers"
	 * extendedMetaData="kind='attribute' name='domMgr'"
	 * @generated
	 */
	ScaDomainManager getDomMgr();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getDomMgr <em>Dom Mgr</em>}' container
	 * reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dom Mgr</em>' container reference.
	 * @see #getDomMgr()
	 * @generated
	 */
	void setDomMgr(ScaDomainManager value);

	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #setIdentifier(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_Identifier()
	 * @model unsettable="true" id="true" derived="true"
	 * extendedMetaData="kind='attribute' name='identifier'"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier <em>Identifier</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier <em>Identifier</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @see #isSetIdentifier()
	 * @see #getIdentifier()
	 * @see #setIdentifier(String)
	 * @generated
	 */
	void unsetIdentifier();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier
	 * <em>Identifier</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Identifier</em>' attribute is set.
	 * @see #unsetIdentifier()
	 * @see #getIdentifier()
	 * @see #setIdentifier(String)
	 * @generated
	 */
	boolean isSetIdentifier();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #isSetLabel()
	 * @see #unsetLabel()
	 * @see #setLabel(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_Label()
	 * @model unsettable="true" transient="true" derived="true"
	 * extendedMetaData="kind='attribute' name='label'"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #isSetLabel()
	 * @see #unsetLabel()
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @see #isSetLabel()
	 * @see #getLabel()
	 * @see #setLabel(String)
	 * @generated
	 */
	void unsetLabel();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel <em>Label</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Label</em>' attribute is set.
	 * @see #unsetLabel()
	 * @see #getLabel()
	 * @see #setLabel(String)
	 * @generated
	 */
	boolean isSetLabel();

	/**
	 * Returns the value of the '<em><b>Services</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaService}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaService#getDevMgr <em>Dev Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' containment reference list.
	 * @see #isSetServices()
	 * @see #unsetServices()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_Services()
	 * @see gov.redhawk.model.sca.ScaService#getDevMgr
	 * @model opposite="devMgr" containment="true" unsettable="true" transient="true" ordered="false"
	 * @generated
	 */
	EList<ScaService> getServices();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getServices <em>Services</em>}'
	 * containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetServices()
	 * @see #getServices()
	 * @generated
	 */
	void unsetServices();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getServices <em>Services</em>}'
	 * containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Services</em>' containment reference list is set.
	 * @see #unsetServices()
	 * @see #getServices()
	 * @generated
	 */
	boolean isSetServices();

	/**
	 * Returns the value of the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #setProfile(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDeviceManager_Profile()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #getProfile()
	 * @generated
	 */
	void setProfile(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	void unsetProfile();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDeviceManager#getProfile <em>Profile</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Profile</em>' attribute is set.
	 * @see #unsetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	boolean isSetProfile();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaDevice< ? > getDevice(String deviceId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaDevice< ? >> fetchDevices(IProgressMonitor monitor);

	/**
	 * @since 19.0
	 */
	EList<ScaDevice< ? >> fetchDevices(IProgressMonitor monitor, RefreshDepth depth);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	ScaDeviceManagerFileSystem fetchFileSystem(IProgressMonitor monitor);

	/**
	 * @since 19.0
	 */
	ScaDeviceManagerFileSystem fetchFileSystem(IProgressMonitor monitor, RefreshDepth depth);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchIdentifier(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchLabel(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaService> fetchServices(IProgressMonitor monitor);

	/**
	 * @since 19.0
	 */
	EList<ScaService> fetchServices(IProgressMonitor monitor, RefreshDepth depth);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="mil.jpeojtrs.sca.cf.InvalidObjectReference"
	 * registeringServiceDataType="gov.redhawk.model.sca.Object"
	 * @generated
	 */
	ScaService registerScaService(org.omg.CORBA.Object registeringService, String name) throws InvalidObjectReference;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaService getScaService(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchProfile(IProgressMonitor monitor);
} // ScaDeviceManager
