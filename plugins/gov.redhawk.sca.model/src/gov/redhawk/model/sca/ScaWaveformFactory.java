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

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.IProgressMonitor;

import CF.ApplicationFactory;
import CF.ApplicationFactoryOperations;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Waveform Factory</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr <em>Dom Mgr</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier <em>Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getName <em>Name</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getProfile <em>Profile</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveformFactory()
 * @model superTypes="gov.redhawk.model.sca.CorbaObjWrapper&lt;mil.jpeojtrs.sca.cf.ApplicationFactory&gt;
 *        mil.jpeojtrs.sca.cf.ApplicationFactoryOperations
 *        gov.redhawk.model.sca.ProfileObjectWrapper&lt;mil.jpeojtrs.sca.sad.SoftwareAssembly&gt;"
 *        extendedMetaData="name='ScaWaveformFactory' kind='empty'"
 * @generated
 */
public interface ScaWaveformFactory extends CorbaObjWrapper<ApplicationFactory>, ApplicationFactoryOperations, ProfileObjectWrapper<SoftwareAssembly> {

	/**
	 * Returns the value of the '<em><b>Dom Mgr</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories
	 * <em>Waveform Factories</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dom Mgr</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dom Mgr</em>' container reference.
	 * @see #setDomMgr(ScaDomainManager)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveformFactory_DomMgr()
	 * @see gov.redhawk.model.sca.ScaDomainManager#getWaveformFactories
	 * @model opposite="waveformFactories" required="true"
	 *        extendedMetaData="kind='attribute' name='domMgr'"
	 * @generated
	 */
	ScaDomainManager getDomMgr();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr <em>Dom Mgr</em>}' container
	 * reference.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dom Mgr</em>' container reference.
	 * @see #getDomMgr()
	 * @generated
	 */
	void setDomMgr(ScaDomainManager value);

	/**
	 * Returns the value of the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifier</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #setIdentifier(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveformFactory_Identifier()
	 * @model unsettable="true" id="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='identifier'"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier <em>Identifier</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Identifier</em>' attribute.
	 * @see #isSetIdentifier()
	 * @see #unsetIdentifier()
	 * @see #getIdentifier()
	 * @generated
	 */
	void setIdentifier(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier <em>Identifier</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetIdentifier()
	 * @see #getIdentifier()
	 * @see #setIdentifier(String)
	 * @generated
	 */
	void unsetIdentifier();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier
	 * <em>Identifier</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #isSetName()
	 * @see #unsetName()
	 * @see #setName(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveformFactory_Name()
	 * @model unsettable="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #isSetName()
	 * @see #unsetName()
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetName()
	 * @see #getName()
	 * @see #setName(String)
	 * @generated
	 */
	void unsetName();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getName <em>Name</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Name</em>' attribute is set.
	 * @see #unsetName()
	 * @see #getName()
	 * @see #setName(String)
	 * @generated
	 */
	boolean isSetName();

	/**
	 * Returns the value of the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #setProfile(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveformFactory_Profile()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Profile</em>' attribute.
	 * @see #isSetProfile()
	 * @see #unsetProfile()
	 * @see #getProfile()
	 * @generated
	 */
	void setProfile(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetProfile()
	 * @see #getProfile()
	 * @see #setProfile(String)
	 * @generated
	 */
	void unsetProfile();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getProfile <em>Profile</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * 
	 * @model exceptions="mil.jpeojtrs.sca.cf.CreateApplicationError mil.jpeojtrs.sca.cf.CreateApplicationRequestError
	 *        mil.jpeojtrs.sca.cf.InvalidInitConfiguration
	 *        mil.jpeojtrs.sca.cf.CreateApplicationInsufficientCapacityError"
	 *        monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 *        initConfigurationDataType="mil.jpeojtrs.sca.cf.DataTypeArray"
	 *        deviceAssignmentsDataType="mil.jpeojtrs.sca.cf.DeviceAssignmentTypeArray"
	 * @generated
	 */
	ScaWaveform createWaveform(IProgressMonitor monitor, String name, DataType[] initConfiguration, DeviceAssignmentType[] deviceAssignments)
		throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError;

	/**
	 * @since 20.0
	 */
	ScaWaveform createWaveform(IProgressMonitor monitor, String name, DataType[] initConfiguration, DeviceAssignmentType[] deviceAssignments,
		RefreshDepth depth) throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchIdentifier(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchName(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchProfile(IProgressMonitor monitor);

} // ScaWaveformFactory
