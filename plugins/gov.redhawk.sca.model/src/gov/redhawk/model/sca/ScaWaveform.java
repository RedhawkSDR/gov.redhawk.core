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
import org.eclipse.emf.common.util.EList;

import CF.Application;
import CF.ApplicationOperations;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Waveform</b></em>'.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              </p>
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getComponents <em>Components</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController <em>Assembly Controller</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getDomMgr <em>Dom Mgr</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier <em>Identifier</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getName <em>Name</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getStarted <em>Started</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaWaveform#getProfile <em>Profile</em>}</li>
 *              </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform()
 * @model superTypes=
 *        "gov.redhawk.model.sca.ScaPropertyContainer<mil.jpeojtrs.sca.cf.Application, mil.jpeojtrs.sca.sad.SoftwareAssembly> mil.jpeojtrs.sca.cf.ApplicationOperations gov.redhawk.model.sca.ScaPortContainer"
 *        extendedMetaData="name='ScaWaveform' kind='elementOnly'"
 * @generated
 */
public interface ScaWaveform extends ScaPropertyContainer<Application, SoftwareAssembly>, ApplicationOperations, ScaPortContainer {

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaComponent}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaComponent#getWaveform <em>Waveform</em>}
	 * '.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see #isSetComponents()
	 * @see #unsetComponents()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_Components()
	 * @see gov.redhawk.model.sca.ScaComponent#getWaveform
	 * @model opposite="waveform" containment="true" unsettable="true" keys="identifier" transient="true"
	 *        extendedMetaData="kind='element' name='components'"
	 * @generated
	 */
	EList<ScaComponent> getComponents();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getComponents <em>Components</em>}' containment
	 * reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetComponents()
	 * @see #getComponents()
	 * @generated
	 */
	void unsetComponents();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getComponents <em>Components</em>}'
	 * containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Components</em>' containment reference list is set.
	 * @see #unsetComponents()
	 * @see #getComponents()
	 * @generated
	 */
	boolean isSetComponents();

	/**
	 * Returns the value of the '<em><b>Assembly Controller</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Controller</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Assembly Controller</em>' reference.
	 * @see #isSetAssemblyController()
	 * @see #unsetAssemblyController()
	 * @see #setAssemblyController(ScaComponent)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_AssemblyController()
	 * @model unsettable="true" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='assemblyController'"
	 * @generated
	 */
	ScaComponent getAssemblyController();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController
	 * <em>Assembly Controller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Assembly Controller</em>' reference.
	 * @see #isSetAssemblyController()
	 * @see #unsetAssemblyController()
	 * @see #getAssemblyController()
	 * @generated
	 */
	void setAssemblyController(ScaComponent value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController
	 * <em>Assembly Controller</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetAssemblyController()
	 * @see #getAssemblyController()
	 * @see #setAssemblyController(ScaComponent)
	 * @generated
	 */
	void unsetAssemblyController();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController
	 * <em>Assembly Controller</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Assembly Controller</em>' reference is set.
	 * @see #unsetAssemblyController()
	 * @see #getAssemblyController()
	 * @see #setAssemblyController(ScaComponent)
	 * @generated
	 */
	boolean isSetAssemblyController();

	/**
	 * Returns the value of the '<em><b>Dom Mgr</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDomainManager#getWaveforms
	 * <em>Waveforms</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dom Mgr</em>' container reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dom Mgr</em>' container reference.
	 * @see #setDomMgr(ScaDomainManager)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_DomMgr()
	 * @see gov.redhawk.model.sca.ScaDomainManager#getWaveforms
	 * @model opposite="waveforms"
	 *        extendedMetaData="kind='attribute' name='domMgr'"
	 * @generated
	 */
	ScaDomainManager getDomMgr();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getDomMgr <em>Dom Mgr</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_Identifier()
	 * @model unsettable="true" id="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='identifier'"
	 * @generated
	 */
	String getIdentifier();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier <em>Identifier</em>}' attribute.
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
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier <em>Identifier</em>}' attribute.
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
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier <em>Identifier</em>}'
	 * attribute is set.
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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_Name()
	 * @model unsettable="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getName <em>Name</em>}' attribute.
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
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getName <em>Name</em>}' attribute.
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
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getName <em>Name</em>}' attribute is
	 * set.
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
	 * Returns the value of the '<em><b>Started</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Started</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Started</em>' attribute.
	 * @see #isSetStarted()
	 * @see #unsetStarted()
	 * @see #setStarted(Boolean)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_Started()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.BooleanObject" transient="true"
	 * @generated
	 */
	Boolean getStarted();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getStarted <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Started</em>' attribute.
	 * @see #isSetStarted()
	 * @see #unsetStarted()
	 * @see #getStarted()
	 * @generated
	 */
	void setStarted(Boolean value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getStarted <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetStarted()
	 * @see #getStarted()
	 * @see #setStarted(Boolean)
	 * @generated
	 */
	void unsetStarted();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getStarted <em>Started</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Started</em>' attribute is set.
	 * @see #unsetStarted()
	 * @see #getStarted()
	 * @see #setStarted(Boolean)
	 * @generated
	 */
	boolean isSetStarted();

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
	 * @see gov.redhawk.model.sca.ScaPackage#getScaWaveform_Profile()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getProfile <em>Profile</em>}' attribute.
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
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getProfile <em>Profile</em>}' attribute.
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
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaWaveform#getProfile <em>Profile</em>}'
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
	 * 
	 * @deprecated Use {@link #fetchComponents(IProgressMonitor, RefreshDepth)}
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaComponent> fetchComponents(IProgressMonitor monitor);

	/**
	 * @since 20.0
	 */
	EList<ScaComponent> fetchComponents(IProgressMonitor monitor, RefreshDepth depth);

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
	Boolean fetchStarted(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	ScaComponent findComponent(String instantiationId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model
	 * @generated
	 */
	ScaComponent getScaComponent(String instantiationId);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchProfile(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * Gets a copy of the list of components safely using a read-only command in the transactional editing domain.
	 * @see #getComponents()
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @model kind="operation"
	 * @generated
	 */
	EList<ScaComponent> getComponentsCopy();

} // ScaWaveform
