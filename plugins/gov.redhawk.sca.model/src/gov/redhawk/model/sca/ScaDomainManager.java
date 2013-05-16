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

import mil.jpeojtrs.sca.dmd.DomainManagerConfiguration;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.omg.CosNaming.NamingContextExt;

import CF.DomainManager;
import CF.DomainManagerOperations;
import CF.InvalidFileName;
import CF.InvalidProfile;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.InvalidIdentifier;

/**
 * <!-- begin-user-doc --> 
 * A representation of the model object '<em><b>Domain Manager</b></em>'.
 * @noimplement This interface is not intended to be implemented by clients. 
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories <em>Waveform Factories</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getWaveforms <em>Waveforms</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers <em>Device Managers</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File Manager</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionPropertiesContainer <em>Connection Properties Container</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#isAutoConnect <em>Auto Connect</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#isConnected <em>Connected</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getRootContext <em>Root Context</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getState <em>State</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager()
 * @model superTypes="gov.redhawk.model.sca.ScaPropertyContainer<mil.jpeojtrs.sca.cf.DomainManager, mil.jpeojtrs.sca.dmd.DomainManagerConfiguration> mil.jpeojtrs.sca.cf.DomainManagerOperations"
 *        extendedMetaData="name='ScaDomainManager' kind='elementOnly'"
 * @generated
 */
public interface ScaDomainManager extends ScaPropertyContainer<DomainManager, DomainManagerConfiguration>, DomainManagerOperations {

	/**
	 * Returns the value of the '<em><b>Waveform Factories</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaWaveformFactory}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waveform Factories</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waveform Factories</em>' containment reference list.
	 * @see #isSetWaveformFactories()
	 * @see #unsetWaveformFactories()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_WaveformFactories()
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr
	 * @model opposite="domMgr" containment="true" unsettable="true" transient="true"
	 *        extendedMetaData="kind='element' name='waveformFactories'"
	 * @generated
	 */
	EList<ScaWaveformFactory> getWaveformFactories();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories <em>Waveform Factories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWaveformFactories()
	 * @see #getWaveformFactories()
	 * @generated
	 */
	void unsetWaveformFactories();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories <em>Waveform Factories</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Waveform Factories</em>' containment reference list is set.
	 * @see #unsetWaveformFactories()
	 * @see #getWaveformFactories()
	 * @generated
	 */
	boolean isSetWaveformFactories();

	/**
	 * Returns the value of the '<em><b>Waveforms</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaWaveform}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaWaveform#getDomMgr <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Waveforms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Waveforms</em>' containment reference list.
	 * @see #isSetWaveforms()
	 * @see #unsetWaveforms()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_Waveforms()
	 * @see gov.redhawk.model.sca.ScaWaveform#getDomMgr
	 * @model opposite="domMgr" containment="true" unsettable="true" transient="true"
	 *        extendedMetaData="kind='element' name='waveforms'"
	 * @generated
	 */
	EList<ScaWaveform> getWaveforms();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveforms <em>Waveforms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetWaveforms()
	 * @see #getWaveforms()
	 * @generated
	 */
	void unsetWaveforms();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveforms <em>Waveforms</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Waveforms</em>' containment reference list is set.
	 * @see #unsetWaveforms()
	 * @see #getWaveforms()
	 * @generated
	 */
	boolean isSetWaveforms();

	/**
	 * Returns the value of the '<em><b>Device Managers</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDeviceManager}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDeviceManager#getDomMgr <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Managers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Managers</em>' containment reference list.
	 * @see #isSetDeviceManagers()
	 * @see #unsetDeviceManagers()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_DeviceManagers()
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getDomMgr
	 * @model opposite="domMgr" containment="true" unsettable="true" transient="true"
	 *        extendedMetaData="kind='element' name='deviceManagers'"
	 * @generated
	 */
	EList<ScaDeviceManager> getDeviceManagers();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers <em>Device Managers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDeviceManagers()
	 * @see #getDeviceManagers()
	 * @generated
	 */
	void unsetDeviceManagers();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers <em>Device Managers</em>}' containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Device Managers</em>' containment reference list is set.
	 * @see #unsetDeviceManagers()
	 * @see #getDeviceManagers()
	 * @generated
	 */
	boolean isSetDeviceManagers();

	/**
	 * Returns the value of the '<em><b>File Manager</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr <em>Dom Mgr</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>File Manager</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>File Manager</em>' containment reference.
	 * @see #isSetFileManager()
	 * @see #unsetFileManager()
	 * @see #setFileManager(ScaDomainManagerFileSystem)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_FileManager()
	 * @see gov.redhawk.model.sca.ScaDomainManagerFileSystem#getDomMgr
	 * @model opposite="domMgr" containment="true" unsettable="true" transient="true"
	 *        extendedMetaData="kind='element' name='fileManager'"
	 * @generated
	 */
	ScaDomainManagerFileSystem getFileManager();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>File Manager</em>' containment reference.
	 * @see #isSetFileManager()
	 * @see #unsetFileManager()
	 * @see #getFileManager()
	 * @generated
	 */
	void setFileManager(ScaDomainManagerFileSystem value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @see #isSetFileManager()
	 * @see #getFileManager()
	 * @see #setFileManager(ScaDomainManagerFileSystem)
	 * @generated
	 */
	void unsetFileManager();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager <em>File Manager</em>}' containment reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>File Manager</em>' containment reference is set.
	 * @see #unsetFileManager()
	 * @see #getFileManager()
	 * @see #setFileManager(ScaDomainManagerFileSystem)
	 * @generated
	 */
	boolean isSetFileManager();

	/**
	 * Returns the value of the '<em><b>Connection Properties Container</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Properties Container</em>' containment reference.
	 * @see #setConnectionPropertiesContainer(Properties)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_ConnectionPropertiesContainer()
	 * @model containment="true" resolveProxies="true" required="true"
	 *        extendedMetaData="kind='element' name='connectionProperties'"
	 * @generated
	 */
	Properties getConnectionPropertiesContainer();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionPropertiesContainer <em>Connection Properties Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Properties Container</em>' containment reference.
	 * @see #getConnectionPropertiesContainer()
	 * @generated
	 */
	void setConnectionPropertiesContainer(Properties value);

	/**
	 * Returns the value of the '<em><b>Connection Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Properties</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Properties</em>' map.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_ConnectionProperties()
	 * @model mapType="gov.redhawk.model.sca.StringToStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connectionPropertiesTransient'"
	 * @generated
	 */
	EMap<String, String> getConnectionProperties();

	/**
	 * Returns the value of the '<em><b>Auto Connect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auto Connect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auto Connect</em>' attribute.
	 * @see #setAutoConnect(boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_AutoConnect()
	 * @model extendedMetaData="kind='attribute' name='autoConnect'"
	 * @generated
	 */
	boolean isAutoConnect();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#isAutoConnect <em>Auto Connect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auto Connect</em>' attribute.
	 * @see #isAutoConnect()
	 * @generated
	 */
	void setAutoConnect(boolean value);

	/**
	 * Returns the value of the '<em><b>Connected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connected</em>' attribute.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_Connected()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='connected'"
	 * @generated
	 */
	boolean isConnected();

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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_Identifier()
	 * @model unsettable="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='identifier'"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getIdentifier <em>Identifier</em>}' attribute.
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
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getIdentifier <em>Identifier</em>}' attribute.
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
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getIdentifier <em>Identifier</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_Name()
	 * @model id="true" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Root Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Context</em>' attribute.
	 * @see #isSetRootContext()
	 * @see #unsetRootContext()
	 * @see #setRootContext(NamingContextExt)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_RootContext()
	 * @model unsettable="true" dataType="mil.jpeojtrs.sca.cf.NamingContextExt" required="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='rootContext'"
	 * @generated
	 */
	NamingContextExt getRootContext();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getRootContext <em>Root Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Context</em>' attribute.
	 * @see #isSetRootContext()
	 * @see #unsetRootContext()
	 * @see #getRootContext()
	 * @generated
	 */
	void setRootContext(NamingContextExt value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getRootContext <em>Root Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @see #isSetRootContext()
	 * @see #getRootContext()
	 * @see #setRootContext(NamingContextExt)
	 * @generated
	 */
	void unsetRootContext();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getRootContext <em>Root Context</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Root Context</em>' attribute is set.
	 * @see #unsetRootContext()
	 * @see #getRootContext()
	 * @see #setRootContext(NamingContextExt)
	 * @generated
	 */
	boolean isSetRootContext();

	/**
	 * Returns the value of the '<em><b>State</b></em>' attribute.
	 * The literals are from the enumeration {@link gov.redhawk.model.sca.DomainConnectionState}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' attribute.
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @see #setState(DomainConnectionState)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_State()
	 * @model required="true" transient="true"
	 *        extendedMetaData="kind='attribute' name='state'"
	 * @generated
	 */
	DomainConnectionState getState();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getState <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 16.0
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>State</em>' attribute.
	 * @see gov.redhawk.model.sca.DomainConnectionState
	 * @see #getState()
	 * @generated
	 */
	void setState(DomainConnectionState value);

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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManager_Profile()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getProfile <em>Profile</em>}' attribute.
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
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	void unsetProfile();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDomainManager#getProfile <em>Profile</em>}' attribute is set.
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
	ScaDevice<?> getDevice(String deviceId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="gov.redhawk.model.sca.DomainConnectionException" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	void connect(IProgressMonitor monitor) throws DomainConnectionException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="gov.redhawk.model.sca.DomainConnectionException" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	void connect(IProgressMonitor monitor, RefreshDepth refreshDepth) throws DomainConnectionException;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void disconnect();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaDeviceManager> fetchDeviceManagers(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaWaveformFactory> fetchWaveformFactories(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaWaveform> fetchWaveforms(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	ScaDomainManagerFileSystem fetchFileManager(IProgressMonitor monitor);

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
	 * @model exceptions="mil.jpeojtrs.sca.cf.InvalidProfile mil.jpeojtrs.sca.cf.InvalidFileName mil.jpeojtrs.sca.cf.ApplicationInstallationError mil.jpeojtrs.sca.cf.ApplicationAlreadyInstalled"
	 * @generated
	 */
	ScaWaveformFactory installScaWaveformFactory(String profilePath) throws InvalidProfile, InvalidFileName, ApplicationInstallationError, ApplicationAlreadyInstalled;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="mil.jpeojtrs.sca.cf.ApplicationUninstallationError mil.jpeojtrs.sca.cf.InvalidIdentifier"
	 * @generated
	 */
	void uninstallScaWaveformFactory(ScaWaveformFactory factory) throws ApplicationUninstallationError, InvalidIdentifier;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchProfile(IProgressMonitor monitor);

	/**
	 * @since 8.0
	 */
	public static final String NAMING_SERVICE_PROP = "ORBInitRef.NameService";

} // ScaDomainManager
